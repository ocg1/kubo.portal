/**
 * SGBConsultaCierreDiarioRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.request;

public class SGBConsultaCierreDiarioRequest  implements java.io.Serializable {
    private java.lang.String ultimaEtapa;

    public SGBConsultaCierreDiarioRequest() {
    }

    public SGBConsultaCierreDiarioRequest(
           java.lang.String ultimaEtapa) {
           this.ultimaEtapa = ultimaEtapa;
    }


    /**
     * Gets the ultimaEtapa value for this SGBConsultaCierreDiarioRequest.
     * 
     * @return ultimaEtapa
     */
    public java.lang.String getUltimaEtapa() {
        return ultimaEtapa;
    }


    /**
     * Sets the ultimaEtapa value for this SGBConsultaCierreDiarioRequest.
     * 
     * @param ultimaEtapa
     */
    public void setUltimaEtapa(java.lang.String ultimaEtapa) {
        this.ultimaEtapa = ultimaEtapa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SGBConsultaCierreDiarioRequest)) return false;
        SGBConsultaCierreDiarioRequest other = (SGBConsultaCierreDiarioRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ultimaEtapa==null && other.getUltimaEtapa()==null) || 
             (this.ultimaEtapa!=null &&
              this.ultimaEtapa.equals(other.getUltimaEtapa())));
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
        if (getUltimaEtapa() != null) {
            _hashCode += getUltimaEtapa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SGBConsultaCierreDiarioRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.webServices.soa.com", "SGBConsultaCierreDiarioRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ultimaEtapa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "ultimaEtapa"));
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
