/**
 * SolicitudInversionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.InvKuboServicios;

public class SolicitudInversionRequest  implements java.io.Serializable {
    private java.lang.String solicitudCreditoID;

    private java.lang.String clienteID;

    private java.lang.String cuentaAhoID;

    private java.lang.String montoFondeo;

    private java.lang.String tasaPasiva;

    private java.lang.String creditoID;

    private java.lang.String tipoFondeo;

    public SolicitudInversionRequest() {
    }

    public SolicitudInversionRequest(
           java.lang.String solicitudCreditoID,
           java.lang.String clienteID,
           java.lang.String cuentaAhoID,
           java.lang.String montoFondeo,
           java.lang.String tasaPasiva,
           java.lang.String creditoID,
           java.lang.String tipoFondeo) {
           this.solicitudCreditoID = solicitudCreditoID;
           this.clienteID = clienteID;
           this.cuentaAhoID = cuentaAhoID;
           this.montoFondeo = montoFondeo;
           this.tasaPasiva = tasaPasiva;
           this.creditoID = creditoID;
           this.tipoFondeo = tipoFondeo;
    }


    /**
     * Gets the solicitudCreditoID value for this SolicitudInversionRequest.
     * 
     * @return solicitudCreditoID
     */
    public java.lang.String getSolicitudCreditoID() {
        return solicitudCreditoID;
    }


    /**
     * Sets the solicitudCreditoID value for this SolicitudInversionRequest.
     * 
     * @param solicitudCreditoID
     */
    public void setSolicitudCreditoID(java.lang.String solicitudCreditoID) {
        this.solicitudCreditoID = solicitudCreditoID;
    }


    /**
     * Gets the clienteID value for this SolicitudInversionRequest.
     * 
     * @return clienteID
     */
    public java.lang.String getClienteID() {
        return clienteID;
    }


    /**
     * Sets the clienteID value for this SolicitudInversionRequest.
     * 
     * @param clienteID
     */
    public void setClienteID(java.lang.String clienteID) {
        this.clienteID = clienteID;
    }


    /**
     * Gets the cuentaAhoID value for this SolicitudInversionRequest.
     * 
     * @return cuentaAhoID
     */
    public java.lang.String getCuentaAhoID() {
        return cuentaAhoID;
    }


    /**
     * Sets the cuentaAhoID value for this SolicitudInversionRequest.
     * 
     * @param cuentaAhoID
     */
    public void setCuentaAhoID(java.lang.String cuentaAhoID) {
        this.cuentaAhoID = cuentaAhoID;
    }


    /**
     * Gets the montoFondeo value for this SolicitudInversionRequest.
     * 
     * @return montoFondeo
     */
    public java.lang.String getMontoFondeo() {
        return montoFondeo;
    }


    /**
     * Sets the montoFondeo value for this SolicitudInversionRequest.
     * 
     * @param montoFondeo
     */
    public void setMontoFondeo(java.lang.String montoFondeo) {
        this.montoFondeo = montoFondeo;
    }


    /**
     * Gets the tasaPasiva value for this SolicitudInversionRequest.
     * 
     * @return tasaPasiva
     */
    public java.lang.String getTasaPasiva() {
        return tasaPasiva;
    }


    /**
     * Sets the tasaPasiva value for this SolicitudInversionRequest.
     * 
     * @param tasaPasiva
     */
    public void setTasaPasiva(java.lang.String tasaPasiva) {
        this.tasaPasiva = tasaPasiva;
    }


    /**
     * Gets the creditoID value for this SolicitudInversionRequest.
     * 
     * @return creditoID
     */
    public java.lang.String getCreditoID() {
        return creditoID;
    }


    /**
     * Sets the creditoID value for this SolicitudInversionRequest.
     * 
     * @param creditoID
     */
    public void setCreditoID(java.lang.String creditoID) {
        this.creditoID = creditoID;
    }


    /**
     * Gets the tipoFondeo value for this SolicitudInversionRequest.
     * 
     * @return tipoFondeo
     */
    public java.lang.String getTipoFondeo() {
        return tipoFondeo;
    }


    /**
     * Sets the tipoFondeo value for this SolicitudInversionRequest.
     * 
     * @param tipoFondeo
     */
    public void setTipoFondeo(java.lang.String tipoFondeo) {
        this.tipoFondeo = tipoFondeo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudInversionRequest)) return false;
        SolicitudInversionRequest other = (SolicitudInversionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.solicitudCreditoID==null && other.getSolicitudCreditoID()==null) || 
             (this.solicitudCreditoID!=null &&
              this.solicitudCreditoID.equals(other.getSolicitudCreditoID()))) &&
            ((this.clienteID==null && other.getClienteID()==null) || 
             (this.clienteID!=null &&
              this.clienteID.equals(other.getClienteID()))) &&
            ((this.cuentaAhoID==null && other.getCuentaAhoID()==null) || 
             (this.cuentaAhoID!=null &&
              this.cuentaAhoID.equals(other.getCuentaAhoID()))) &&
            ((this.montoFondeo==null && other.getMontoFondeo()==null) || 
             (this.montoFondeo!=null &&
              this.montoFondeo.equals(other.getMontoFondeo()))) &&
            ((this.tasaPasiva==null && other.getTasaPasiva()==null) || 
             (this.tasaPasiva!=null &&
              this.tasaPasiva.equals(other.getTasaPasiva()))) &&
            ((this.creditoID==null && other.getCreditoID()==null) || 
             (this.creditoID!=null &&
              this.creditoID.equals(other.getCreditoID()))) &&
            ((this.tipoFondeo==null && other.getTipoFondeo()==null) || 
             (this.tipoFondeo!=null &&
              this.tipoFondeo.equals(other.getTipoFondeo())));
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
        if (getSolicitudCreditoID() != null) {
            _hashCode += getSolicitudCreditoID().hashCode();
        }
        if (getClienteID() != null) {
            _hashCode += getClienteID().hashCode();
        }
        if (getCuentaAhoID() != null) {
            _hashCode += getCuentaAhoID().hashCode();
        }
        if (getMontoFondeo() != null) {
            _hashCode += getMontoFondeo().hashCode();
        }
        if (getTasaPasiva() != null) {
            _hashCode += getTasaPasiva().hashCode();
        }
        if (getCreditoID() != null) {
            _hashCode += getCreditoID().hashCode();
        }
        if (getTipoFondeo() != null) {
            _hashCode += getTipoFondeo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudInversionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudInversionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudCreditoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "solicitudCreditoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clienteID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "clienteID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentaAhoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cuentaAhoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoFondeo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoFondeo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasaPasiva");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tasaPasiva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "creditoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFondeo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tipoFondeo"));
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
