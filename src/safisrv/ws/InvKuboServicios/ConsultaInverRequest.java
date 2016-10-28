/**
 * ConsultaInverRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.InvKuboServicios;

public class ConsultaInverRequest  implements java.io.Serializable {
    private java.lang.String numero;

    private java.lang.String cuentaAhoID;

    public ConsultaInverRequest() {
    }

    public ConsultaInverRequest(
           java.lang.String numero,
           java.lang.String cuentaAhoID) {
           this.numero = numero;
           this.cuentaAhoID = cuentaAhoID;
    }


    /**
     * Gets the numero value for this ConsultaInverRequest.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this ConsultaInverRequest.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the cuentaAhoID value for this ConsultaInverRequest.
     * 
     * @return cuentaAhoID
     */
    public java.lang.String getCuentaAhoID() {
        return cuentaAhoID;
    }


    /**
     * Sets the cuentaAhoID value for this ConsultaInverRequest.
     * 
     * @param cuentaAhoID
     */
    public void setCuentaAhoID(java.lang.String cuentaAhoID) {
        this.cuentaAhoID = cuentaAhoID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaInverRequest)) return false;
        ConsultaInverRequest other = (ConsultaInverRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.cuentaAhoID==null && other.getCuentaAhoID()==null) || 
             (this.cuentaAhoID!=null &&
              this.cuentaAhoID.equals(other.getCuentaAhoID())));
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
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getCuentaAhoID() != null) {
            _hashCode += getCuentaAhoID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaInverRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaInverRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentaAhoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cuentaAhoID"));
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
