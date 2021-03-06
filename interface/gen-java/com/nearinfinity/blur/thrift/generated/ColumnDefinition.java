/**
 * Autogenerated by Thrift Compiler (0.7.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.nearinfinity.blur.thrift.generated;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class ColumnDefinition implements org.apache.thrift.TBase<ColumnDefinition, ColumnDefinition._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ColumnDefinition");

  private static final org.apache.thrift.protocol.TField ANALYZER_CLASS_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("analyzerClassName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField FULL_TEXT_INDEX_FIELD_DESC = new org.apache.thrift.protocol.TField("fullTextIndex", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField ALTERNATE_COLUMN_DEFINITIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("alternateColumnDefinitions", org.apache.thrift.protocol.TType.MAP, (short)3);

  public String analyzerClassName; // required
  public boolean fullTextIndex; // required
  public Map<String,AlternateColumnDefinition> alternateColumnDefinitions; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ANALYZER_CLASS_NAME((short)1, "analyzerClassName"),
    FULL_TEXT_INDEX((short)2, "fullTextIndex"),
    ALTERNATE_COLUMN_DEFINITIONS((short)3, "alternateColumnDefinitions");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ANALYZER_CLASS_NAME
          return ANALYZER_CLASS_NAME;
        case 2: // FULL_TEXT_INDEX
          return FULL_TEXT_INDEX;
        case 3: // ALTERNATE_COLUMN_DEFINITIONS
          return ALTERNATE_COLUMN_DEFINITIONS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __FULLTEXTINDEX_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ANALYZER_CLASS_NAME, new org.apache.thrift.meta_data.FieldMetaData("analyzerClassName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FULL_TEXT_INDEX, new org.apache.thrift.meta_data.FieldMetaData("fullTextIndex", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ALTERNATE_COLUMN_DEFINITIONS, new org.apache.thrift.meta_data.FieldMetaData("alternateColumnDefinitions", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, AlternateColumnDefinition.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ColumnDefinition.class, metaDataMap);
  }

  public ColumnDefinition() {
    this.analyzerClassName = "org.apache.lucene.analysis.standard.StandardAnalyzer";

  }

  public ColumnDefinition(
    String analyzerClassName,
    boolean fullTextIndex,
    Map<String,AlternateColumnDefinition> alternateColumnDefinitions)
  {
    this();
    this.analyzerClassName = analyzerClassName;
    this.fullTextIndex = fullTextIndex;
    setFullTextIndexIsSet(true);
    this.alternateColumnDefinitions = alternateColumnDefinitions;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ColumnDefinition(ColumnDefinition other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetAnalyzerClassName()) {
      this.analyzerClassName = other.analyzerClassName;
    }
    this.fullTextIndex = other.fullTextIndex;
    if (other.isSetAlternateColumnDefinitions()) {
      Map<String,AlternateColumnDefinition> __this__alternateColumnDefinitions = new HashMap<String,AlternateColumnDefinition>();
      for (Map.Entry<String, AlternateColumnDefinition> other_element : other.alternateColumnDefinitions.entrySet()) {

        String other_element_key = other_element.getKey();
        AlternateColumnDefinition other_element_value = other_element.getValue();

        String __this__alternateColumnDefinitions_copy_key = other_element_key;

        AlternateColumnDefinition __this__alternateColumnDefinitions_copy_value = new AlternateColumnDefinition(other_element_value);

        __this__alternateColumnDefinitions.put(__this__alternateColumnDefinitions_copy_key, __this__alternateColumnDefinitions_copy_value);
      }
      this.alternateColumnDefinitions = __this__alternateColumnDefinitions;
    }
  }

  public ColumnDefinition deepCopy() {
    return new ColumnDefinition(this);
  }

  @Override
  public void clear() {
    this.analyzerClassName = "org.apache.lucene.analysis.standard.StandardAnalyzer";

    setFullTextIndexIsSet(false);
    this.fullTextIndex = false;
    this.alternateColumnDefinitions = null;
  }

  public String getAnalyzerClassName() {
    return this.analyzerClassName;
  }

  public ColumnDefinition setAnalyzerClassName(String analyzerClassName) {
    this.analyzerClassName = analyzerClassName;
    return this;
  }

  public void unsetAnalyzerClassName() {
    this.analyzerClassName = null;
  }

  /** Returns true if field analyzerClassName is set (has been assigned a value) and false otherwise */
  public boolean isSetAnalyzerClassName() {
    return this.analyzerClassName != null;
  }

  public void setAnalyzerClassNameIsSet(boolean value) {
    if (!value) {
      this.analyzerClassName = null;
    }
  }

  public boolean isFullTextIndex() {
    return this.fullTextIndex;
  }

  public ColumnDefinition setFullTextIndex(boolean fullTextIndex) {
    this.fullTextIndex = fullTextIndex;
    setFullTextIndexIsSet(true);
    return this;
  }

  public void unsetFullTextIndex() {
    __isset_bit_vector.clear(__FULLTEXTINDEX_ISSET_ID);
  }

  /** Returns true if field fullTextIndex is set (has been assigned a value) and false otherwise */
  public boolean isSetFullTextIndex() {
    return __isset_bit_vector.get(__FULLTEXTINDEX_ISSET_ID);
  }

  public void setFullTextIndexIsSet(boolean value) {
    __isset_bit_vector.set(__FULLTEXTINDEX_ISSET_ID, value);
  }

  public int getAlternateColumnDefinitionsSize() {
    return (this.alternateColumnDefinitions == null) ? 0 : this.alternateColumnDefinitions.size();
  }

  public void putToAlternateColumnDefinitions(String key, AlternateColumnDefinition val) {
    if (this.alternateColumnDefinitions == null) {
      this.alternateColumnDefinitions = new HashMap<String,AlternateColumnDefinition>();
    }
    this.alternateColumnDefinitions.put(key, val);
  }

  public Map<String,AlternateColumnDefinition> getAlternateColumnDefinitions() {
    return this.alternateColumnDefinitions;
  }

  public ColumnDefinition setAlternateColumnDefinitions(Map<String,AlternateColumnDefinition> alternateColumnDefinitions) {
    this.alternateColumnDefinitions = alternateColumnDefinitions;
    return this;
  }

  public void unsetAlternateColumnDefinitions() {
    this.alternateColumnDefinitions = null;
  }

  /** Returns true if field alternateColumnDefinitions is set (has been assigned a value) and false otherwise */
  public boolean isSetAlternateColumnDefinitions() {
    return this.alternateColumnDefinitions != null;
  }

  public void setAlternateColumnDefinitionsIsSet(boolean value) {
    if (!value) {
      this.alternateColumnDefinitions = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ANALYZER_CLASS_NAME:
      if (value == null) {
        unsetAnalyzerClassName();
      } else {
        setAnalyzerClassName((String)value);
      }
      break;

    case FULL_TEXT_INDEX:
      if (value == null) {
        unsetFullTextIndex();
      } else {
        setFullTextIndex((Boolean)value);
      }
      break;

    case ALTERNATE_COLUMN_DEFINITIONS:
      if (value == null) {
        unsetAlternateColumnDefinitions();
      } else {
        setAlternateColumnDefinitions((Map<String,AlternateColumnDefinition>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ANALYZER_CLASS_NAME:
      return getAnalyzerClassName();

    case FULL_TEXT_INDEX:
      return Boolean.valueOf(isFullTextIndex());

    case ALTERNATE_COLUMN_DEFINITIONS:
      return getAlternateColumnDefinitions();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ANALYZER_CLASS_NAME:
      return isSetAnalyzerClassName();
    case FULL_TEXT_INDEX:
      return isSetFullTextIndex();
    case ALTERNATE_COLUMN_DEFINITIONS:
      return isSetAlternateColumnDefinitions();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ColumnDefinition)
      return this.equals((ColumnDefinition)that);
    return false;
  }

  public boolean equals(ColumnDefinition that) {
    if (that == null)
      return false;

    boolean this_present_analyzerClassName = true && this.isSetAnalyzerClassName();
    boolean that_present_analyzerClassName = true && that.isSetAnalyzerClassName();
    if (this_present_analyzerClassName || that_present_analyzerClassName) {
      if (!(this_present_analyzerClassName && that_present_analyzerClassName))
        return false;
      if (!this.analyzerClassName.equals(that.analyzerClassName))
        return false;
    }

    boolean this_present_fullTextIndex = true;
    boolean that_present_fullTextIndex = true;
    if (this_present_fullTextIndex || that_present_fullTextIndex) {
      if (!(this_present_fullTextIndex && that_present_fullTextIndex))
        return false;
      if (this.fullTextIndex != that.fullTextIndex)
        return false;
    }

    boolean this_present_alternateColumnDefinitions = true && this.isSetAlternateColumnDefinitions();
    boolean that_present_alternateColumnDefinitions = true && that.isSetAlternateColumnDefinitions();
    if (this_present_alternateColumnDefinitions || that_present_alternateColumnDefinitions) {
      if (!(this_present_alternateColumnDefinitions && that_present_alternateColumnDefinitions))
        return false;
      if (!this.alternateColumnDefinitions.equals(that.alternateColumnDefinitions))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ColumnDefinition other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ColumnDefinition typedOther = (ColumnDefinition)other;

    lastComparison = Boolean.valueOf(isSetAnalyzerClassName()).compareTo(typedOther.isSetAnalyzerClassName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAnalyzerClassName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.analyzerClassName, typedOther.analyzerClassName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFullTextIndex()).compareTo(typedOther.isSetFullTextIndex());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFullTextIndex()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fullTextIndex, typedOther.fullTextIndex);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAlternateColumnDefinitions()).compareTo(typedOther.isSetAlternateColumnDefinitions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlternateColumnDefinitions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alternateColumnDefinitions, typedOther.alternateColumnDefinitions);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    org.apache.thrift.protocol.TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // ANALYZER_CLASS_NAME
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.analyzerClassName = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // FULL_TEXT_INDEX
          if (field.type == org.apache.thrift.protocol.TType.BOOL) {
            this.fullTextIndex = iprot.readBool();
            setFullTextIndexIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // ALTERNATE_COLUMN_DEFINITIONS
          if (field.type == org.apache.thrift.protocol.TType.MAP) {
            {
              org.apache.thrift.protocol.TMap _map60 = iprot.readMapBegin();
              this.alternateColumnDefinitions = new HashMap<String,AlternateColumnDefinition>(2*_map60.size);
              for (int _i61 = 0; _i61 < _map60.size; ++_i61)
              {
                String _key62; // required
                AlternateColumnDefinition _val63; // required
                _key62 = iprot.readString();
                _val63 = new AlternateColumnDefinition();
                _val63.read(iprot);
                this.alternateColumnDefinitions.put(_key62, _val63);
              }
              iprot.readMapEnd();
            }
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.analyzerClassName != null) {
      oprot.writeFieldBegin(ANALYZER_CLASS_NAME_FIELD_DESC);
      oprot.writeString(this.analyzerClassName);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(FULL_TEXT_INDEX_FIELD_DESC);
    oprot.writeBool(this.fullTextIndex);
    oprot.writeFieldEnd();
    if (this.alternateColumnDefinitions != null) {
      oprot.writeFieldBegin(ALTERNATE_COLUMN_DEFINITIONS_FIELD_DESC);
      {
        oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, this.alternateColumnDefinitions.size()));
        for (Map.Entry<String, AlternateColumnDefinition> _iter64 : this.alternateColumnDefinitions.entrySet())
        {
          oprot.writeString(_iter64.getKey());
          _iter64.getValue().write(oprot);
        }
        oprot.writeMapEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ColumnDefinition(");
    boolean first = true;

    sb.append("analyzerClassName:");
    if (this.analyzerClassName == null) {
      sb.append("null");
    } else {
      sb.append(this.analyzerClassName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("fullTextIndex:");
    sb.append(this.fullTextIndex);
    first = false;
    if (!first) sb.append(", ");
    sb.append("alternateColumnDefinitions:");
    if (this.alternateColumnDefinitions == null) {
      sb.append("null");
    } else {
      sb.append(this.alternateColumnDefinitions);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

}

