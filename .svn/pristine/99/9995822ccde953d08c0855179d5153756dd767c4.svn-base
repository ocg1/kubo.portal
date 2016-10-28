/**
 * ApplicationLogResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.responses;

public class ApplicationLogResponse  implements java.io.Serializable {
    private com.soa.model.businessobject.VApplicationLog header;

    private com.soa.model.businessobject.Logs[] logs;

    public ApplicationLogResponse() {
    }

    public ApplicationLogResponse(
           com.soa.model.businessobject.VApplicationLog header,
           com.soa.model.businessobject.Logs[] logs) {
           this.header = header;
           this.logs = logs;
    }


    /**
     * Gets the header value for this ApplicationLogResponse.
     * 
     * @return header
     */
    public com.soa.model.businessobject.VApplicationLog getHeader() {
        return header;
    }


    /**
     * Sets the header value for this ApplicationLogResponse.
     * 
     * @param header
     */
    public void setHeader(com.soa.model.businessobject.VApplicationLog header) {
        this.header = header;
    }


    /**
     * Gets the logs value for this ApplicationLogResponse.
     * 
     * @return logs
     */
    public com.soa.model.businessobject.Logs[] getLogs() {
        return logs;
    }


    /**
     * Sets the logs value for this ApplicationLogResponse.
     * 
     * @param logs
     */
    public void setLogs(com.soa.model.businessobject.Logs[] logs) {
        this.logs = logs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplicationLogResponse)) return false;
        ApplicationLogResponse other = (ApplicationLogResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.logs==null && other.getLogs()==null) || 
             (this.logs!=null &&
              java.util.Arrays.equals(this.logs, other.getLogs())));
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
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getLogs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLogs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLogs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplicationLogResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ApplicationLogResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "VApplicationLog"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "logs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Logs"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
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
