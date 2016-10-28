/**
 * SimuladorCuotaCreditoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class SimuladorCuotaCreditoResponse  implements java.io.Serializable {
    private java.lang.String montoCuota;

    private java.lang.String numeroCuotas;

    private java.lang.String totalPagar;

    private java.lang.String cat;

    private java.lang.String codigoRespuesta;

    private java.lang.String mensajeRespuesta;

    public SimuladorCuotaCreditoResponse() {
    }

    public SimuladorCuotaCreditoResponse(
           java.lang.String montoCuota,
           java.lang.String numeroCuotas,
           java.lang.String totalPagar,
           java.lang.String cat,
           java.lang.String codigoRespuesta,
           java.lang.String mensajeRespuesta) {
           this.montoCuota = montoCuota;
           this.numeroCuotas = numeroCuotas;
           this.totalPagar = totalPagar;
           this.cat = cat;
           this.codigoRespuesta = codigoRespuesta;
           this.mensajeRespuesta = mensajeRespuesta;
    }


    /**
     * Gets the montoCuota value for this SimuladorCuotaCreditoResponse.
     * 
     * @return montoCuota
     */
    public java.lang.String getMontoCuota() {
        return montoCuota;
    }


    /**
     * Sets the montoCuota value for this SimuladorCuotaCreditoResponse.
     * 
     * @param montoCuota
     */
    public void setMontoCuota(java.lang.String montoCuota) {
        this.montoCuota = montoCuota;
    }


    /**
     * Gets the numeroCuotas value for this SimuladorCuotaCreditoResponse.
     * 
     * @return numeroCuotas
     */
    public java.lang.String getNumeroCuotas() {
        return numeroCuotas;
    }


    /**
     * Sets the numeroCuotas value for this SimuladorCuotaCreditoResponse.
     * 
     * @param numeroCuotas
     */
    public void setNumeroCuotas(java.lang.String numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }


    /**
     * Gets the totalPagar value for this SimuladorCuotaCreditoResponse.
     * 
     * @return totalPagar
     */
    public java.lang.String getTotalPagar() {
        return totalPagar;
    }


    /**
     * Sets the totalPagar value for this SimuladorCuotaCreditoResponse.
     * 
     * @param totalPagar
     */
    public void setTotalPagar(java.lang.String totalPagar) {
        this.totalPagar = totalPagar;
    }


    /**
     * Gets the cat value for this SimuladorCuotaCreditoResponse.
     * 
     * @return cat
     */
    public java.lang.String getCat() {
        return cat;
    }


    /**
     * Sets the cat value for this SimuladorCuotaCreditoResponse.
     * 
     * @param cat
     */
    public void setCat(java.lang.String cat) {
        this.cat = cat;
    }


    /**
     * Gets the codigoRespuesta value for this SimuladorCuotaCreditoResponse.
     * 
     * @return codigoRespuesta
     */
    public java.lang.String getCodigoRespuesta() {
        return codigoRespuesta;
    }


    /**
     * Sets the codigoRespuesta value for this SimuladorCuotaCreditoResponse.
     * 
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(java.lang.String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }


    /**
     * Gets the mensajeRespuesta value for this SimuladorCuotaCreditoResponse.
     * 
     * @return mensajeRespuesta
     */
    public java.lang.String getMensajeRespuesta() {
        return mensajeRespuesta;
    }


    /**
     * Sets the mensajeRespuesta value for this SimuladorCuotaCreditoResponse.
     * 
     * @param mensajeRespuesta
     */
    public void setMensajeRespuesta(java.lang.String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimuladorCuotaCreditoResponse)) return false;
        SimuladorCuotaCreditoResponse other = (SimuladorCuotaCreditoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.montoCuota==null && other.getMontoCuota()==null) || 
             (this.montoCuota!=null &&
              this.montoCuota.equals(other.getMontoCuota()))) &&
            ((this.numeroCuotas==null && other.getNumeroCuotas()==null) || 
             (this.numeroCuotas!=null &&
              this.numeroCuotas.equals(other.getNumeroCuotas()))) &&
            ((this.totalPagar==null && other.getTotalPagar()==null) || 
             (this.totalPagar!=null &&
              this.totalPagar.equals(other.getTotalPagar()))) &&
            ((this.cat==null && other.getCat()==null) || 
             (this.cat!=null &&
              this.cat.equals(other.getCat()))) &&
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
        if (getMontoCuota() != null) {
            _hashCode += getMontoCuota().hashCode();
        }
        if (getNumeroCuotas() != null) {
            _hashCode += getNumeroCuotas().hashCode();
        }
        if (getTotalPagar() != null) {
            _hashCode += getTotalPagar().hashCode();
        }
        if (getCat() != null) {
            _hashCode += getCat().hashCode();
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
        new org.apache.axis.description.TypeDesc(SimuladorCuotaCreditoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoCuota");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoCuota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCuotas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "numeroCuotas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalPagar");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "totalPagar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cat"));
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
