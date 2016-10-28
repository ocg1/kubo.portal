/**
 * CancelaCreditoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class CancelaCreditoRequest  implements java.io.Serializable {
    private java.lang.String creditoID;

    public CancelaCreditoRequest() {
    }

    public CancelaCreditoRequest(
           java.lang.String creditoID) {
           this.creditoID = creditoID;
    }


    /**
     * Gets the creditoID value for this CancelaCreditoRequest.
     * 
     * @return creditoID
     */
    public java.lang.String getCreditoID() {
        return creditoID;
    }


    /**
     * Sets the creditoID value for this CancelaCreditoRequest.
     * 
     * @param creditoID
     */
    public void setCreditoID(java.lang.String creditoID) {
        this.creditoID = creditoID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelaCreditoRequest)) return false;
        CancelaCreditoRequest other = (CancelaCreditoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.creditoID==null && other.getCreditoID()==null) || 
             (this.creditoID!=null &&
              this.creditoID.equals(other.getCreditoID())));
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
        if (getCreditoID() != null) {
            _hashCode += getCreditoID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelaCreditoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">cancelaCreditoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "creditoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
