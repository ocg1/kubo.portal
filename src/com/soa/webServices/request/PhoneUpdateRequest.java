/**
 * PhoneUpdateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.request;

public class PhoneUpdateRequest  implements java.io.Serializable {
    private com.soa.webServices.util.InputParam[] phoneList;

    private java.lang.String prospectId;

    public PhoneUpdateRequest() {
    }

    public PhoneUpdateRequest(
           com.soa.webServices.util.InputParam[] phoneList,
           java.lang.String prospectId) {
           this.phoneList = phoneList;
           this.prospectId = prospectId;
    }


    /**
     * Gets the phoneList value for this PhoneUpdateRequest.
     * 
     * @return phoneList
     */
    public com.soa.webServices.util.InputParam[] getPhoneList() {
        return phoneList;
    }


    /**
     * Sets the phoneList value for this PhoneUpdateRequest.
     * 
     * @param phoneList
     */
    public void setPhoneList(com.soa.webServices.util.InputParam[] phoneList) {
        this.phoneList = phoneList;
    }


    /**
     * Gets the prospectId value for this PhoneUpdateRequest.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this PhoneUpdateRequest.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PhoneUpdateRequest)) return false;
        PhoneUpdateRequest other = (PhoneUpdateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.phoneList==null && other.getPhoneList()==null) || 
             (this.phoneList!=null &&
              java.util.Arrays.equals(this.phoneList, other.getPhoneList()))) &&
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
        if (getPhoneList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPhoneList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPhoneList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProspectId() != null) {
            _hashCode += getProspectId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PhoneUpdateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.webServices.soa.com", "PhoneUpdateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "phoneList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://util.webServices.soa.com", "InputParam"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "prospectId"));
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
