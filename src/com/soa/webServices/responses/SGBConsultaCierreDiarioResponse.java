/**
 * SGBConsultaCierreDiarioResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.responses;

public class SGBConsultaCierreDiarioResponse  implements java.io.Serializable {
    private com.soa.model.businessobject.EstatusCierre[] auditoriaCierreLT;

    private java.lang.String estatus;

    public SGBConsultaCierreDiarioResponse() {
    }

    public SGBConsultaCierreDiarioResponse(
           com.soa.model.businessobject.EstatusCierre[] auditoriaCierreLT,
           java.lang.String estatus) {
           this.auditoriaCierreLT = auditoriaCierreLT;
           this.estatus = estatus;
    }


    /**
     * Gets the auditoriaCierreLT value for this SGBConsultaCierreDiarioResponse.
     * 
     * @return auditoriaCierreLT
     */
    public com.soa.model.businessobject.EstatusCierre[] getAuditoriaCierreLT() {
        return auditoriaCierreLT;
    }


    /**
     * Sets the auditoriaCierreLT value for this SGBConsultaCierreDiarioResponse.
     * 
     * @param auditoriaCierreLT
     */
    public void setAuditoriaCierreLT(com.soa.model.businessobject.EstatusCierre[] auditoriaCierreLT) {
        this.auditoriaCierreLT = auditoriaCierreLT;
    }


    /**
     * Gets the estatus value for this SGBConsultaCierreDiarioResponse.
     * 
     * @return estatus
     */
    public java.lang.String getEstatus() {
        return estatus;
    }


    /**
     * Sets the estatus value for this SGBConsultaCierreDiarioResponse.
     * 
     * @param estatus
     */
    public void setEstatus(java.lang.String estatus) {
        this.estatus = estatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SGBConsultaCierreDiarioResponse)) return false;
        SGBConsultaCierreDiarioResponse other = (SGBConsultaCierreDiarioResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auditoriaCierreLT==null && other.getAuditoriaCierreLT()==null) || 
             (this.auditoriaCierreLT!=null &&
              java.util.Arrays.equals(this.auditoriaCierreLT, other.getAuditoriaCierreLT()))) &&
            ((this.estatus==null && other.getEstatus()==null) || 
             (this.estatus!=null &&
              this.estatus.equals(other.getEstatus())));
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
        if (getAuditoriaCierreLT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuditoriaCierreLT());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuditoriaCierreLT(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEstatus() != null) {
            _hashCode += getEstatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SGBConsultaCierreDiarioResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "SGBConsultaCierreDiarioResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditoriaCierreLT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "auditoriaCierreLT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "EstatusCierre"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "estatus"));
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
