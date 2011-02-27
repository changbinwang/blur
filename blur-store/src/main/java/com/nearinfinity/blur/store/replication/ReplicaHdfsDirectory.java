package com.nearinfinity.blur.store.replication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.apache.lucene.store.BufferedIndexInput;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.LockFactory;

import com.nearinfinity.blur.log.Log;
import com.nearinfinity.blur.log.LogFactory;
import com.nearinfinity.blur.store.WritableHdfsDirectory;
import com.nearinfinity.blur.store.cache.LocalFileCache;


public class ReplicaHdfsDirectory extends WritableHdfsDirectory {

    private static final Log LOG = LogFactory.getLog(ReplicaHdfsDirectory.class);
    private LocalIOWrapper wrapper;
    private ReplicationDaemon replicationDaemon;

    public ReplicaHdfsDirectory(String table, String shard, Path hdfsDirPath, FileSystem fileSystem, final LocalFileCache localFileCache, 
            LockFactory lockFactory, Progressable progressable, ReplicationDaemon replicationDaemon)
            throws IOException {
        this(table, shard, hdfsDirPath, fileSystem, localFileCache, lockFactory, progressable, replicationDaemon, new LocalIOWrapper() {
            @Override
            public IndexOutput wrapOutput(IndexOutput indexOutput) {
                return indexOutput;
            }

            @Override
            public IndexInput wrapInput(IndexInput indexInput) {
                return indexInput;
            }
        });
    }

    public ReplicaHdfsDirectory(String table, String shard, Path hdfsDirPath, FileSystem fileSystem, final LocalFileCache localFileCache,
            LockFactory lockFactory, Progressable progressable, ReplicationDaemon replicationDaemon, final LocalIOWrapper wrapper) throws IOException {
        super(table, shard, hdfsDirPath, fileSystem, localFileCache, lockFactory, progressable);
        this.wrapper = wrapper;
        this.replicationDaemon = replicationDaemon;
    }

    @Override
    public IndexInput openInput(String name, int bufferSize) throws IOException {
        if (!fileExists(name)) {
            throw new FileNotFoundException(name);
        }
        ReplicaIndexInput input = new ReplicaIndexInput(name, fileLength(name), BUFFER_SIZE, this);
        if (fileExistsHdfs(name)) {
            input.baseInput = new AtomicReference<IndexInput>(openFromHdfs(name, BUFFER_SIZE));
        } else {
            // scary...
            // The only way this would happen is during a write phase, once the commit is 
            // called this file is synced to hdfs via WritableHdfsDirectory.  The index 
            // reader reopens the file and will open the base from hdfs.
            input.baseInput = new AtomicReference<IndexInput>(wrapper.wrapInput(openFromLocal(name, BUFFER_SIZE)));
        }
        
        if (fileExistsLocally(name)) {
            if (isLocalFileValid(name)) {
                input.localInput = new AtomicReference<IndexInput>(wrapper.wrapInput(openFromLocal(name, BUFFER_SIZE)));
            } else {
                LOG.error("Local file [{0}/{1}] is not valid, opening remote file.",dirName,name);
                input.localInput = new AtomicReference<IndexInput>();
            }
        } else {
            input.localInput = new AtomicReference<IndexInput>();
        }
        return input;
    }

    private synchronized boolean isLocalFileValid(String name) throws IOException {
        if (replicationDaemon.isBeingReplicated(dirName, name)) {
            return false;
        }
        File localFile = localFileCache.getLocalFile(dirName, name);
        if (fileLength(name) != localFile.length()) {
            localFile.delete();
            return false;
        }
        return true;
    }

    protected void readInternal(ReplicaIndexInput replicaIndexInput, byte[] b, int offset, int length)
            throws IOException {
        IndexInput baseIndexInputSource = replicaIndexInput.baseInput.get();
        IndexInput localIndexInputSource = replicaIndexInput.localInput.get();
        long filePointer = replicaIndexInput.getFilePointer();
        if (baseIndexInputSource == null) {
            throw new IOException("Fatal exception base indexinput is null, time to give up!");
        }
        setupBaseIndexInput(replicaIndexInput, baseIndexInputSource, filePointer);
        setupLocalIndexInput(replicaIndexInput, localIndexInputSource, filePointer);
        performRead(replicaIndexInput,filePointer,b,offset,length);
    }

    private void performRead(ReplicaIndexInput replicaIndexInput, long filePointer, byte[] b, int offset, int length) throws IOException {
        if (replicaIndexInput.localInputClone == null) {
            replicate(replicaIndexInput);
            replicaIndexInput.baseInputClone.seek(filePointer);
            replicaIndexInput.baseInputClone.readBytes(b, offset, length);
        } else {
            try {
                replicaIndexInput.localInputClone.seek(filePointer);
                replicaIndexInput.localInputClone.readBytes(b, offset, length);
            } catch (IOException e) {
                replicaIndexInput.baseInputClone.seek(filePointer);
                replicaIndexInput.baseInputClone.readBytes(b, offset, length);
                resetLocal(replicaIndexInput);
                // increment error after base read because index files might be bad
                long numberOfErrors = replicaIndexInput.errorCounter.incrementAndGet();
                LOG.error("Error reading from local [{0}] at position [{1}], this file has errored out [{2}]",replicaIndexInput.fileName,filePointer,numberOfErrors);
            }
        }        
    }

    private void resetLocal(ReplicaIndexInput replicaIndexInput) {
        LOG.error("Local replica of [{0}] is no longer valid, reseting.",replicaIndexInput);
        try {
            replicaIndexInput.localInput.get().close();
        } catch (IOException e) {
            LOG.error("Error trying to close file [{0}] because needs to be replicated again.",replicaIndexInput.fileName);
        }
        replicaIndexInput.localInput.set(null);
        replicaIndexInput.localInputRef = null;
        replicaIndexInput.localInputClone = null;
    }

    private void setupLocalIndexInput(ReplicaIndexInput replicaIndexInput, IndexInput localIndexInputSource,
            long filePointer) throws IOException {
        if (localIndexInputSource == null) {
            replicaIndexInput.localInputRef = null;
            replicaIndexInput.localInputClone = null;
        } else if (replicaIndexInput.localInputRef == null || replicaIndexInput.localInputRef != localIndexInputSource) {
            replicaIndexInput.localInputClone = (IndexInput) localIndexInputSource.clone();
            replicaIndexInput.localInputClone.seek(filePointer);
            replicaIndexInput.localInputRef = localIndexInputSource;
        }
    }

    private void setupBaseIndexInput(ReplicaIndexInput replicaIndexInput, IndexInput baseIndexInputSource,
            long filePointer) throws IOException {
        if (replicaIndexInput.baseInputRef == null || replicaIndexInput.baseInputRef != baseIndexInputSource) {
            replicaIndexInput.baseInputClone = (IndexInput) baseIndexInputSource.clone();
            replicaIndexInput.baseInputClone.seek(filePointer);
            replicaIndexInput.baseInputRef = baseIndexInputSource;
        }
    }

    private void replicate(ReplicaIndexInput replicaIndexInput) {
        replicationDaemon.replicate(this, replicaIndexInput);
    }

    protected void close(ReplicaIndexInput replicaIndexInput) throws IOException {
        AtomicReference<IndexInput> baseInput = replicaIndexInput.baseInput;
        AtomicReference<IndexInput> localInput = replicaIndexInput.localInput;
        String name = replicaIndexInput.fileName;
        synchronized (baseInput) {
            IndexInput baseIndexInput = baseInput.get();
            IndexInput localIndexInput = localInput.get();
            if (localIndexInput != null) {
                try {
                    localIndexInput.close();
                } catch (IOException e) {
                    LOG.error("Error trying to close local file [{0}]",e,name);
                }
            }
            baseIndexInput.close();
        }
    }
    
    public static class ReplicaIndexInput extends BufferedIndexInput {

        protected AtomicReference<IndexInput> baseInput;
        protected AtomicReference<IndexInput> localInput;
        protected AtomicLong errorCounter = new AtomicLong();

        protected IndexInput baseInputRef;
        protected IndexInput localInputRef;

        protected IndexInput baseInputClone;
        protected IndexInput localInputClone;

        protected long length;
        protected boolean clone;
        protected ReplicaHdfsDirectory directory;
        protected String fileName;
        protected String dirName;

        public ReplicaIndexInput(String name, long length, int bufferSize, ReplicaHdfsDirectory directory) {
            super(bufferSize);
            this.length = length;
            this.directory = directory;
            this.fileName = name;
            this.dirName = directory.dirName;
        }

        @Override
        protected void readInternal(byte[] b, int offset, int length) throws IOException {
            directory.readInternal(this, b, offset, length);
        }

        @Override
        protected void seekInternal(long pos) throws IOException {

        }

        @Override
        public void close() throws IOException {
            if (!clone) {
                directory.close(this);
            }
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public Object clone() {
            ReplicaIndexInput clone = (ReplicaIndexInput) super.clone();
            clone.clone = true;
            clone.baseInputRef = null;
            clone.localInputRef = null;

            clone.baseInputClone = null;
            clone.localInputClone = null;
            return clone;
        }

        @Override
        public String toString() {
            return "IndexInput {" + directory.dirName + "/" + fileName + "}";
        }
    }

    @Override
    public String toString() {
        return "ReplicaHdfsDirectory [dirName=" + dirName + "]";
    }
}
