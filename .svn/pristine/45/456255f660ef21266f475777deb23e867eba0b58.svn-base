/**
 * DocumentsReviewRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.request;

public class DocumentsReviewRequest  implements java.io.Serializable {
    private com.soa.webServices.util.Files[] files;

    private java.lang.String requestNumber;

    public DocumentsReviewRequest() {
    }

    public DocumentsReviewRequest(
           com.soa.webServices.util.Files[] files,
           java.lang.String requestNumber) {
           this.files = files;
           this.requestNumber = requestNumber;
    }


    /**
     * Gets the files value for this DocumentsReviewRequest.
     * 
     * @return files
     */
    public com.soa.webServices.util.Files[] getFiles() {
        return files;
    }


    /**
     * Sets the files value for this DocumentsReviewRequest.
     * 
     * @param files
     */
    public void setFiles(com.soa.webServices.util.Files[] files) {
        this.files = files;
    }


    /**
     * Gets the requestNumber value for this DocumentsReviewRequest.
     * 
     * @return requestNumber
     */
    public java.lang.String getRequestNumber() {
        return requestNumber;
    }


    /**
     * Sets the requestNumber value for this DocumentsReviewRequest.
     * 
     * @param requestNumber
     */
    public void setRequestNumber(java.lang.String requestNumber) {
        this.requestNumber = requestNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentsReviewRequest)) return false;
        DocumentsReviewRequest other = (DocumentsReviewRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.files==null && other.getFiles()==null) || 
             (this.files!=null &&
              java.util.Arrays.equals(this.files, other.getFiles()))) &&
            ((this.requestNumber==null && other.getRequestNumber()==null) || 
             (this.requestNumber!=null &&
              this.requestNumber.equals(other.getRequestNumber())));
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
        if (getFiles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFiles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFiles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRequestNumber() != null) {
            _hashCode += getRequestNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentsReviewRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.webServices.soa.com", "DocumentsReviewRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("files");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "files"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://util.webServices.soa.com", "Files"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://request.webServices.soa.com", "requestNumber"));
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
