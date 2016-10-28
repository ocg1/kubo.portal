/**
 * Files.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.util;

public class Files  implements java.io.Serializable {
    private java.lang.String actTypeId;

    private java.lang.String fileDesc;

    private java.lang.String fileName;

    private java.lang.String file_type_id;

    private java.lang.String location;

    private com.soa.webServices.util.InputParam[] params;

    private java.lang.String productId;

    private java.lang.String prospectId;

    public Files() {
    }

    public Files(
           java.lang.String actTypeId,
           java.lang.String fileDesc,
           java.lang.String fileName,
           java.lang.String file_type_id,
           java.lang.String location,
           com.soa.webServices.util.InputParam[] params,
           java.lang.String productId,
           java.lang.String prospectId) {
           this.actTypeId = actTypeId;
           this.fileDesc = fileDesc;
           this.fileName = fileName;
           this.file_type_id = file_type_id;
           this.location = location;
           this.params = params;
           this.productId = productId;
           this.prospectId = prospectId;
    }


    /**
     * Gets the actTypeId value for this Files.
     * 
     * @return actTypeId
     */
    public java.lang.String getActTypeId() {
        return actTypeId;
    }


    /**
     * Sets the actTypeId value for this Files.
     * 
     * @param actTypeId
     */
    public void setActTypeId(java.lang.String actTypeId) {
        this.actTypeId = actTypeId;
    }


    /**
     * Gets the fileDesc value for this Files.
     * 
     * @return fileDesc
     */
    public java.lang.String getFileDesc() {
        return fileDesc;
    }


    /**
     * Sets the fileDesc value for this Files.
     * 
     * @param fileDesc
     */
    public void setFileDesc(java.lang.String fileDesc) {
        this.fileDesc = fileDesc;
    }


    /**
     * Gets the fileName value for this Files.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this Files.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the file_type_id value for this Files.
     * 
     * @return file_type_id
     */
    public java.lang.String getFile_type_id() {
        return file_type_id;
    }


    /**
     * Sets the file_type_id value for this Files.
     * 
     * @param file_type_id
     */
    public void setFile_type_id(java.lang.String file_type_id) {
        this.file_type_id = file_type_id;
    }


    /**
     * Gets the location value for this Files.
     * 
     * @return location
     */
    public java.lang.String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this Files.
     * 
     * @param location
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }


    /**
     * Gets the params value for this Files.
     * 
     * @return params
     */
    public com.soa.webServices.util.InputParam[] getParams() {
        return params;
    }


    /**
     * Sets the params value for this Files.
     * 
     * @param params
     */
    public void setParams(com.soa.webServices.util.InputParam[] params) {
        this.params = params;
    }


    /**
     * Gets the productId value for this Files.
     * 
     * @return productId
     */
    public java.lang.String getProductId() {
        return productId;
    }


    /**
     * Sets the productId value for this Files.
     * 
     * @param productId
     */
    public void setProductId(java.lang.String productId) {
        this.productId = productId;
    }


    /**
     * Gets the prospectId value for this Files.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this Files.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Files)) return false;
        Files other = (Files) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actTypeId==null && other.getActTypeId()==null) || 
             (this.actTypeId!=null &&
              this.actTypeId.equals(other.getActTypeId()))) &&
            ((this.fileDesc==null && other.getFileDesc()==null) || 
             (this.fileDesc!=null &&
              this.fileDesc.equals(other.getFileDesc()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.file_type_id==null && other.getFile_type_id()==null) || 
             (this.file_type_id!=null &&
              this.file_type_id.equals(other.getFile_type_id()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.params==null && other.getParams()==null) || 
             (this.params!=null &&
              java.util.Arrays.equals(this.params, other.getParams()))) &&
            ((this.productId==null && other.getProductId()==null) || 
             (this.productId!=null &&
              this.productId.equals(other.getProductId()))) &&
            ((this.prospectId==null && other.getProspectId()==null) || 
             (this.prospectId!=null &&
              this.prospectId.equals(other.getProspectId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getActTypeId() != null) {
            _hashCode += getActTypeId().hashCode();
        }
        if (getFileDesc() != null) {
            _hashCode += getFileDesc().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getFile_type_id() != null) {
            _hashCode += getFile_type_id().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProductId() != null) {
            _hashCode += getProductId().hashCode();
        }
        if (getProspectId() != null) {
            _hashCode += getProspectId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Files.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://util.webServices.soa.com", "Files"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "actTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "fileDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "file_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("params");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "params"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://util.webServices.soa.com", "InputParam"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "productId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://util.webServices.soa.com", "prospectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
