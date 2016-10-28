/**
 * ConsultaDetallePagosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class ConsultaDetallePagosResponse  implements java.io.Serializable {
    private java.lang.String[] infoDetallePagos;

    private java.lang.String[] codigoRespuesta;

    private java.lang.String[] mensajeRespuesta;

    public ConsultaDetallePagosResponse() {
    }

    public ConsultaDetallePagosResponse(
           java.lang.String[] infoDetallePagos,
           java.lang.String[] codigoRespuesta,
           java.lang.String[] mensajeRespuesta) {
           this.infoDetallePagos = infoDetallePagos;
           this.codigoRespuesta = codigoRespuesta;
           this.mensajeRespuesta = mensajeRespuesta;
    }


    /**
     * Gets the infoDetallePagos value for this ConsultaDetallePagosResponse.
     * 
     * @return infoDetallePagos
     */
    public java.lang.String[] getInfoDetallePagos() {
        return infoDetallePagos;
    }


    /**
     * Sets the infoDetallePagos value for this ConsultaDetallePagosResponse.
     * 
     * @param infoDetallePagos
     */
    public void setInfoDetallePagos(java.lang.String[] infoDetallePagos) {
        this.infoDetallePagos = infoDetallePagos;
    }

    public java.lang.String getInfoDetallePagos(int i) {
        return this.infoDetallePagos[i];
    }

    public void setInfoDetallePagos(int i, java.lang.String _value) {
        this.infoDetallePagos[i] = _value;
    }


    /**
     * Gets the codigoRespuesta value for this ConsultaDetallePagosResponse.
     * 
     * @return codigoRespuesta
     */
    public java.lang.String[] getCodigoRespuesta() {
        return codigoRespuesta;
    }


    /**
     * Sets the codigoRespuesta value for this ConsultaDetallePagosResponse.
     * 
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(java.lang.String[] codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public java.lang.String getCodigoRespuesta(int i) {
        return this.codigoRespuesta[i];
    }

    public void setCodigoRespuesta(int i, java.lang.String _value) {
        this.codigoRespuesta[i] = _value;
    }


    /**
     * Gets the mensajeRespuesta value for this ConsultaDetallePagosResponse.
     * 
     * @return mensajeRespuesta
     */
    public java.lang.String[] getMensajeRespuesta() {
        return mensajeRespuesta;
    }


    /**
     * Sets the mensajeRespuesta value for this ConsultaDetallePagosResponse.
     * 
     * @param mensajeRespuesta
     */
    public void setMensajeRespuesta(java.lang.String[] mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public java.lang.String getMensajeRespuesta(int i) {
        return this.mensajeRespuesta[i];
    }

    public void setMensajeRespuesta(int i, java.lang.String _value) {
        this.mensajeRespuesta[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDetallePagosResponse)) return false;
        ConsultaDetallePagosResponse other = (ConsultaDetallePagosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.infoDetallePagos==null && other.getInfoDetallePagos()==null) || 
             (this.infoDetallePagos!=null &&
              java.util.Arrays.equals(this.infoDetallePagos, other.getInfoDetallePagos()))) &&
            ((this.codigoRespuesta==null && other.getCodigoRespuesta()==null) || 
             (this.codigoRespuesta!=null &&
              java.util.Arrays.equals(this.codigoRespuesta, other.getCodigoRespuesta()))) &&
            ((this.mensajeRespuesta==null && other.getMensajeRespuesta()==null) || 
             (this.mensajeRespuesta!=null &&
              java.util.Arrays.equals(this.mensajeRespuesta, other.getMensajeRespuesta())));
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
        if (getInfoDetallePagos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInfoDetallePagos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInfoDetallePagos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodigoRespuesta() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCodigoRespuesta());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCodigoRespuesta(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMensajeRespuesta() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMensajeRespuesta());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMensajeRespuesta(), i);
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
        new org.apache.axis.description.TypeDesc(ConsultaDetallePagosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDetallePagosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoDetallePagos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "infoDetallePagos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "codigoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "mensajeRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
