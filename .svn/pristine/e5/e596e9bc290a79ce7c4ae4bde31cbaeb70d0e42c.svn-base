/**
 * ConsultaDirClienteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.ClienteServicios;

public class ConsultaDirClienteResponse  implements java.io.Serializable {
    private java.lang.String numero;

    private java.lang.String direccionCompleta;

    public ConsultaDirClienteResponse() {
    }

    public ConsultaDirClienteResponse(
           java.lang.String numero,
           java.lang.String direccionCompleta) {
           this.numero = numero;
           this.direccionCompleta = direccionCompleta;
    }


    /**
     * Gets the numero value for this ConsultaDirClienteResponse.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this ConsultaDirClienteResponse.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the direccionCompleta value for this ConsultaDirClienteResponse.
     * 
     * @return direccionCompleta
     */
    public java.lang.String getDireccionCompleta() {
        return direccionCompleta;
    }


    /**
     * Sets the direccionCompleta value for this ConsultaDirClienteResponse.
     * 
     * @param direccionCompleta
     */
    public void setDireccionCompleta(java.lang.String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDirClienteResponse)) return false;
        ConsultaDirClienteResponse other = (ConsultaDirClienteResponse) obj;
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
            ((this.direccionCompleta==null && other.getDireccionCompleta()==null) || 
             (this.direccionCompleta!=null &&
              this.direccionCompleta.equals(other.getDireccionCompleta())));
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
        if (getDireccionCompleta() != null) {
            _hashCode += getDireccionCompleta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDirClienteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDirClienteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccionCompleta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "direccionCompleta"));
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
