/**
 * AltaRelacionadoCtaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CuentasServicios;

public class AltaRelacionadoCtaResponse  implements java.io.Serializable {
    private java.lang.String personaID;

    private java.lang.String codigoRespuesta;

    private java.lang.String mensajeRespuesta;

    public AltaRelacionadoCtaResponse() {
    }

    public AltaRelacionadoCtaResponse(
           java.lang.String personaID,
           java.lang.String codigoRespuesta,
           java.lang.String mensajeRespuesta) {
           this.personaID = personaID;
           this.codigoRespuesta = codigoRespuesta;
           this.mensajeRespuesta = mensajeRespuesta;
    }


    /**
     * Gets the personaID value for this AltaRelacionadoCtaResponse.
     * 
     * @return personaID
     */
    public java.lang.String getPersonaID() {
        return personaID;
    }


    /**
     * Sets the personaID value for this AltaRelacionadoCtaResponse.
     * 
     * @param personaID
     */
    public void setPersonaID(java.lang.String personaID) {
        this.personaID = personaID;
    }


    /**
     * Gets the codigoRespuesta value for this AltaRelacionadoCtaResponse.
     * 
     * @return codigoRespuesta
     */
    public java.lang.String getCodigoRespuesta() {
        return codigoRespuesta;
    }


    /**
     * Sets the codigoRespuesta value for this AltaRelacionadoCtaResponse.
     * 
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(java.lang.String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }


    /**
     * Gets the mensajeRespuesta value for this AltaRelacionadoCtaResponse.
     * 
     * @return mensajeRespuesta
     */
    public java.lang.String getMensajeRespuesta() {
        return mensajeRespuesta;
    }


    /**
     * Sets the mensajeRespuesta value for this AltaRelacionadoCtaResponse.
     * 
     * @param mensajeRespuesta
     */
    public void setMensajeRespuesta(java.lang.String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AltaRelacionadoCtaResponse)) return false;
        AltaRelacionadoCtaResponse other = (AltaRelacionadoCtaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.personaID==null && other.getPersonaID()==null) || 
             (this.personaID!=null &&
              this.personaID.equals(other.getPersonaID()))) &&
            ((this.codigoRespuesta==null && other.getCodigoRespuesta()==null) || 
             (this.codigoRespuesta!=null &&
              this.codigoRespuesta.equals(other.getCodigoRespuesta()))) &&
            ((this.mensajeRespuesta==null && other.getMensajeRespuesta()==null) || 
             (this.mensajeRespuesta!=null &&
              this.mensajeRespuesta.equals(other.getMensajeRespuesta())));
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
        if (getPersonaID() != null) {
            _hashCode += getPersonaID().hashCode();
        }
        if (getCodigoRespuesta() != null) {
            _hashCode += getCodigoRespuesta().hashCode();
        }
        if (getMensajeRespuesta() != null) {
            _hashCode += getMensajeRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AltaRelacionadoCtaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaRelacionadoCtaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personaID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "personaID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "codigoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "mensajeRespuesta"));
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
