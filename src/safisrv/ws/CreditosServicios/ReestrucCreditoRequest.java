/**
 * ReestrucCreditoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class ReestrucCreditoRequest  implements java.io.Serializable {
    private java.lang.String clienteID;

    private java.lang.String productoCreditoID;

    private java.lang.String relacionado;

    private java.lang.String factorMora;

    private java.lang.String tasaFija;

    private java.lang.String frecuencia;

    private java.lang.String tipoPagoCapital;

    private java.lang.String numeroAmortizaciones;

    private java.lang.String ajustarFecVen;

    private java.lang.String montoComisionApertura;

    private java.lang.String ivaComisionApertura;

    public ReestrucCreditoRequest() {
    }

    public ReestrucCreditoRequest(
           java.lang.String clienteID,
           java.lang.String productoCreditoID,
           java.lang.String relacionado,
           java.lang.String factorMora,
           java.lang.String tasaFija,
           java.lang.String frecuencia,
           java.lang.String tipoPagoCapital,
           java.lang.String numeroAmortizaciones,
           java.lang.String ajustarFecVen,
           java.lang.String montoComisionApertura,
           java.lang.String ivaComisionApertura) {
           this.clienteID = clienteID;
           this.productoCreditoID = productoCreditoID;
           this.relacionado = relacionado;
           this.factorMora = factorMora;
           this.tasaFija = tasaFija;
           this.frecuencia = frecuencia;
           this.tipoPagoCapital = tipoPagoCapital;
           this.numeroAmortizaciones = numeroAmortizaciones;
           this.ajustarFecVen = ajustarFecVen;
           this.montoComisionApertura = montoComisionApertura;
           this.ivaComisionApertura = ivaComisionApertura;
    }


    /**
     * Gets the clienteID value for this ReestrucCreditoRequest.
     * 
     * @return clienteID
     */
    public java.lang.String getClienteID() {
        return clienteID;
    }


    /**
     * Sets the clienteID value for this ReestrucCreditoRequest.
     * 
     * @param clienteID
     */
    public void setClienteID(java.lang.String clienteID) {
        this.clienteID = clienteID;
    }


    /**
     * Gets the productoCreditoID value for this ReestrucCreditoRequest.
     * 
     * @return productoCreditoID
     */
    public java.lang.String getProductoCreditoID() {
        return productoCreditoID;
    }


    /**
     * Sets the productoCreditoID value for this ReestrucCreditoRequest.
     * 
     * @param productoCreditoID
     */
    public void setProductoCreditoID(java.lang.String productoCreditoID) {
        this.productoCreditoID = productoCreditoID;
    }


    /**
     * Gets the relacionado value for this ReestrucCreditoRequest.
     * 
     * @return relacionado
     */
    public java.lang.String getRelacionado() {
        return relacionado;
    }


    /**
     * Sets the relacionado value for this ReestrucCreditoRequest.
     * 
     * @param relacionado
     */
    public void setRelacionado(java.lang.String relacionado) {
        this.relacionado = relacionado;
    }


    /**
     * Gets the factorMora value for this ReestrucCreditoRequest.
     * 
     * @return factorMora
     */
    public java.lang.String getFactorMora() {
        return factorMora;
    }


    /**
     * Sets the factorMora value for this ReestrucCreditoRequest.
     * 
     * @param factorMora
     */
    public void setFactorMora(java.lang.String factorMora) {
        this.factorMora = factorMora;
    }


    /**
     * Gets the tasaFija value for this ReestrucCreditoRequest.
     * 
     * @return tasaFija
     */
    public java.lang.String getTasaFija() {
        return tasaFija;
    }


    /**
     * Sets the tasaFija value for this ReestrucCreditoRequest.
     * 
     * @param tasaFija
     */
    public void setTasaFija(java.lang.String tasaFija) {
        this.tasaFija = tasaFija;
    }


    /**
     * Gets the frecuencia value for this ReestrucCreditoRequest.
     * 
     * @return frecuencia
     */
    public java.lang.String getFrecuencia() {
        return frecuencia;
    }


    /**
     * Sets the frecuencia value for this ReestrucCreditoRequest.
     * 
     * @param frecuencia
     */
    public void setFrecuencia(java.lang.String frecuencia) {
        this.frecuencia = frecuencia;
    }


    /**
     * Gets the tipoPagoCapital value for this ReestrucCreditoRequest.
     * 
     * @return tipoPagoCapital
     */
    public java.lang.String getTipoPagoCapital() {
        return tipoPagoCapital;
    }


    /**
     * Sets the tipoPagoCapital value for this ReestrucCreditoRequest.
     * 
     * @param tipoPagoCapital
     */
    public void setTipoPagoCapital(java.lang.String tipoPagoCapital) {
        this.tipoPagoCapital = tipoPagoCapital;
    }


    /**
     * Gets the numeroAmortizaciones value for this ReestrucCreditoRequest.
     * 
     * @return numeroAmortizaciones
     */
    public java.lang.String getNumeroAmortizaciones() {
        return numeroAmortizaciones;
    }


    /**
     * Sets the numeroAmortizaciones value for this ReestrucCreditoRequest.
     * 
     * @param numeroAmortizaciones
     */
    public void setNumeroAmortizaciones(java.lang.String numeroAmortizaciones) {
        this.numeroAmortizaciones = numeroAmortizaciones;
    }


    /**
     * Gets the ajustarFecVen value for this ReestrucCreditoRequest.
     * 
     * @return ajustarFecVen
     */
    public java.lang.String getAjustarFecVen() {
        return ajustarFecVen;
    }


    /**
     * Sets the ajustarFecVen value for this ReestrucCreditoRequest.
     * 
     * @param ajustarFecVen
     */
    public void setAjustarFecVen(java.lang.String ajustarFecVen) {
        this.ajustarFecVen = ajustarFecVen;
    }


    /**
     * Gets the montoComisionApertura value for this ReestrucCreditoRequest.
     * 
     * @return montoComisionApertura
     */
    public java.lang.String getMontoComisionApertura() {
        return montoComisionApertura;
    }


    /**
     * Sets the montoComisionApertura value for this ReestrucCreditoRequest.
     * 
     * @param montoComisionApertura
     */
    public void setMontoComisionApertura(java.lang.String montoComisionApertura) {
        this.montoComisionApertura = montoComisionApertura;
    }


    /**
     * Gets the ivaComisionApertura value for this ReestrucCreditoRequest.
     * 
     * @return ivaComisionApertura
     */
    public java.lang.String getIvaComisionApertura() {
        return ivaComisionApertura;
    }


    /**
     * Sets the ivaComisionApertura value for this ReestrucCreditoRequest.
     * 
     * @param ivaComisionApertura
     */
    public void setIvaComisionApertura(java.lang.String ivaComisionApertura) {
        this.ivaComisionApertura = ivaComisionApertura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReestrucCreditoRequest)) return false;
        ReestrucCreditoRequest other = (ReestrucCreditoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clienteID==null && other.getClienteID()==null) || 
             (this.clienteID!=null &&
              this.clienteID.equals(other.getClienteID()))) &&
            ((this.productoCreditoID==null && other.getProductoCreditoID()==null) || 
             (this.productoCreditoID!=null &&
              this.productoCreditoID.equals(other.getProductoCreditoID()))) &&
            ((this.relacionado==null && other.getRelacionado()==null) || 
             (this.relacionado!=null &&
              this.relacionado.equals(other.getRelacionado()))) &&
            ((this.factorMora==null && other.getFactorMora()==null) || 
             (this.factorMora!=null &&
              this.factorMora.equals(other.getFactorMora()))) &&
            ((this.tasaFija==null && other.getTasaFija()==null) || 
             (this.tasaFija!=null &&
              this.tasaFija.equals(other.getTasaFija()))) &&
            ((this.frecuencia==null && other.getFrecuencia()==null) || 
             (this.frecuencia!=null &&
              this.frecuencia.equals(other.getFrecuencia()))) &&
            ((this.tipoPagoCapital==null && other.getTipoPagoCapital()==null) || 
             (this.tipoPagoCapital!=null &&
              this.tipoPagoCapital.equals(other.getTipoPagoCapital()))) &&
            ((this.numeroAmortizaciones==null && other.getNumeroAmortizaciones()==null) || 
             (this.numeroAmortizaciones!=null &&
              this.numeroAmortizaciones.equals(other.getNumeroAmortizaciones()))) &&
            ((this.ajustarFecVen==null && other.getAjustarFecVen()==null) || 
             (this.ajustarFecVen!=null &&
              this.ajustarFecVen.equals(other.getAjustarFecVen()))) &&
            ((this.montoComisionApertura==null && other.getMontoComisionApertura()==null) || 
             (this.montoComisionApertura!=null &&
              this.montoComisionApertura.equals(other.getMontoComisionApertura()))) &&
            ((this.ivaComisionApertura==null && other.getIvaComisionApertura()==null) || 
             (this.ivaComisionApertura!=null &&
              this.ivaComisionApertura.equals(other.getIvaComisionApertura())));
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
        if (getClienteID() != null) {
            _hashCode += getClienteID().hashCode();
        }
        if (getProductoCreditoID() != null) {
            _hashCode += getProductoCreditoID().hashCode();
        }
        if (getRelacionado() != null) {
            _hashCode += getRelacionado().hashCode();
        }
        if (getFactorMora() != null) {
            _hashCode += getFactorMora().hashCode();
        }
        if (getTasaFija() != null) {
            _hashCode += getTasaFija().hashCode();
        }
        if (getFrecuencia() != null) {
            _hashCode += getFrecuencia().hashCode();
        }
        if (getTipoPagoCapital() != null) {
            _hashCode += getTipoPagoCapital().hashCode();
        }
        if (getNumeroAmortizaciones() != null) {
            _hashCode += getNumeroAmortizaciones().hashCode();
        }
        if (getAjustarFecVen() != null) {
            _hashCode += getAjustarFecVen().hashCode();
        }
        if (getMontoComisionApertura() != null) {
            _hashCode += getMontoComisionApertura().hashCode();
        }
        if (getIvaComisionApertura() != null) {
            _hashCode += getIvaComisionApertura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReestrucCreditoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">reestrucCreditoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clienteID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "clienteID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productoCreditoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "productoCreditoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relacionado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "relacionado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("factorMora");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "factorMora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasaFija");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tasaFija"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "frecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagoCapital");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tipoPagoCapital"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAmortizaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "numeroAmortizaciones"));
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
        elemField.setFieldName("montoComisionApertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoComisionApertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ivaComisionApertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "ivaComisionApertura"));
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
