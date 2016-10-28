/**
 * ReferencesUpdateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.request;

public class ReferencesUpdateRequest  implements java.io.Serializable {
    private com.soa.webServices.util.InputParam[] homeList;

    private java.lang.String prospectId;

    public ReferencesUpdateRequest() {
    }

    public ReferencesUpdateRequest(
           com.soa.webServices.util.InputParam[] homeList,
           java.lang.String prospectId) {
           this.homeList = homeList;
           this.prospectId = prospectId;
    }


    /**
     * Gets the homeList value for this ReferencesUpdateRequest.
     * 
     * @return homeList
     */
    public com.soa.webServices.util.InputParam[] getHomeList() {
        return homeList;
    }


    /**
     * Sets the homeList value for this ReferencesUpdateRequest.
     * 
     * @param homeList
     */
    public void setHomeList(com.soa.webServices.util.InputParam[] homeList) {
        this.homeList = homeList;
    }


    /**
     * Gets the prospectId value for this ReferencesUpdateRequest.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this ReferencesUpdateRequest.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReferencesUpdateRequest)) return false;
        ReferencesUpdateRequest other = (ReferencesUpdateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.homeList==null && other.getHomeList()==null) || 
             (this.homeList!=null &&
              java.util.Arrays.equals(this.homeList, other.getHomeList()))) &&
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
        if (getHomeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHomeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHomeList(), i);
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
        new org.apache.axis.description.TypeDesc(ReferencesUpdateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.webServices.soa.com", "ReferencesUpdateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "homeList"));
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
