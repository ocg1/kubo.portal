/**
 * CreaCreditoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class CreaCreditoRequest  implements java.io.Serializable {
    private java.lang.String solicitudCreditoID;

    private java.lang.String cuentaClabe;

    private java.lang.String ajustarFecVen;

    private java.lang.String folioConBuro;

    private java.lang.String fechaConBuro;

    private java.lang.String tasaMora;

    public CreaCreditoRequest() {
    }

    public CreaCreditoRequest(
           java.lang.String solicitudCreditoID,
           java.lang.String cuentaClabe,
           java.lang.String ajustarFecVen,
           java.lang.String folioConBuro,
           java.lang.String fechaConBuro,
           java.lang.String tasaMora) {
           this.solicitudCreditoID = solicitudCreditoID;
           this.cuentaClabe = cuentaClabe;
           this.ajustarFecVen = ajustarFecVen;
           this.folioConBuro = folioConBuro;
           this.fechaConBuro = fechaConBuro;
           this.tasaMora = tasaMora;
    }


    /**
     * Gets the solicitudCreditoID value for this CreaCreditoRequest.
     * 
     * @return solicitudCreditoID
     */
    public java.lang.String getSolicitudCreditoID() {
        return solicitudCreditoID;
    }


    /**
     * Sets the solicitudCreditoID value for this CreaCreditoRequest.
     * 
     * @param solicitudCreditoID
     */
    public void setSolicitudCreditoID(java.lang.String solicitudCreditoID) {
        this.solicitudCreditoID = solicitudCreditoID;
    }


    /**
     * Gets the cuentaClabe value for this CreaCreditoRequest.
     * 
     * @return cuentaClabe
     */
    public java.lang.String getCuentaClabe() {
        return cuentaClabe;
    }


    /**
     * Sets the cuentaClabe value for this CreaCreditoRequest.
     * 
     * @param cuentaClabe
     */
    public void setCuentaClabe(java.lang.String cuentaClabe) {
        this.cuentaClabe = cuentaClabe;
    }


    /**
     * Gets the ajustarFecVen value for this CreaCreditoRequest.
     * 
     * @return ajustarFecVen
     */
    public java.lang.String getAjustarFecVen() {
        return ajustarFecVen;
    }


    /**
     * Sets the ajustarFecVen value for this CreaCreditoRequest.
     * 
     * @param ajustarFecVen
     */
    public void setAjustarFecVen(java.lang.String ajustarFecVen) {
        this.ajustarFecVen = ajustarFecVen;
    }


    /**
     * Gets the folioConBuro value for this CreaCreditoRequest.
     * 
     * @return folioConBuro
     */
    public java.lang.String getFolioConBuro() {
        return folioConBuro;
    }


    /**
     * Sets the folioConBuro value for this CreaCreditoRequest.
     * 
     * @param folioConBuro
     */
    public void setFolioConBuro(java.lang.String folioConBuro) {
        this.folioConBuro = folioConBuro;
    }


    /**
     * Gets the fechaConBuro value for this CreaCreditoRequest.
     * 
     * @return fechaConBuro
     */
    public java.lang.String getFechaConBuro() {
        return fechaConBuro;
    }


    /**
     * Sets the fechaConBuro value for this CreaCreditoRequest.
     * 
     * @param fechaConBuro
     */
    public void setFechaConBuro(java.lang.String fechaConBuro) {
        this.fechaConBuro = fechaConBuro;
    }


    /**
     * Gets the tasaMora value for this CreaCreditoRequest.
     * 
     * @return tasaMora
     */
    public java.lang.String getTasaMora() {
        return tasaMora;
    }


    /**
     * Sets the tasaMora value for this CreaCreditoRequest.
     * 
     * @param tasaMora
     */
    public void setTasaMora(java.lang.String tasaMora) {
        this.tasaMora = tasaMora;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreaCreditoRequest)) return false;
        CreaCreditoRequest other = (CreaCreditoRequest) obj;
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
            ((this.cuentaClabe==null && other.getCuentaClabe()==null) || 
             (this.cuentaClabe!=null &&
              this.cuentaClabe.equals(other.getCuentaClabe()))) &&
            ((this.ajustarFecVen==null && other.getAjustarFecVen()==null) || 
             (this.ajustarFecVen!=null &&
              this.ajustarFecVen.equals(other.getAjustarFecVen()))) &&
            ((this.folioConBuro==null && other.getFolioConBuro()==null) || 
             (this.folioConBuro!=null &&
              this.folioConBuro.equals(other.getFolioConBuro()))) &&
            ((this.fechaConBuro==null && other.getFechaConBuro()==null) || 
             (this.fechaConBuro!=null &&
              this.fechaConBuro.equals(other.getFechaConBuro()))) &&
            ((this.tasaMora==null && other.getTasaMora()==null) || 
             (this.tasaMora!=null &&
              this.tasaMora.equals(other.getTasaMora())));
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
        if (getCuentaClabe() != null) {
            _hashCode += getCuentaClabe().hashCode();
        }
        if (getAjustarFecVen() != null) {
            _hashCode += getAjustarFecVen().hashCode();
        }
        if (getFolioConBuro() != null) {
            _hashCode += getFolioConBuro().hashCode();
        }
        if (getFechaConBuro() != null) {
            _hashCode += getFechaConBuro().hashCode();
        }
        if (getTasaMora() != null) {
            _hashCode += getTasaMora().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreaCreditoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">creaCreditoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudCreditoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "solicitudCreditoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentaClabe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cuentaClabe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ajustarFecVen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "ajustarFecVen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folioConBuro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "folioConBuro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaConBuro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "fechaConBuro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasaMora");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tasaMora"));
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
