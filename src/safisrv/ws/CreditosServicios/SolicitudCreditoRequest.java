/**
 * SolicitudCreditoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class SolicitudCreditoRequest  implements java.io.Serializable {
    private java.lang.String prospectoID;

    private java.lang.String productoCreditoID;

    private java.lang.String fechaRegistro;

    private java.lang.String montoSolici;

    private java.lang.String tasaActiva;

    private java.lang.String periodicidad;

    private java.lang.String plazo;

    private java.lang.String calificacion;

    private java.lang.String tipoDispersion;

    private java.lang.String cuentaClabe;

    private java.lang.String clienteID;

    private java.lang.String forCobComApert;

    private java.lang.String montoComApert;

    public SolicitudCreditoRequest() {
    }

    public SolicitudCreditoRequest(
           java.lang.String prospectoID,
           java.lang.String productoCreditoID,
           java.lang.String fechaRegistro,
           java.lang.String montoSolici,
           java.lang.String tasaActiva,
           java.lang.String periodicidad,
           java.lang.String plazo,
           java.lang.String calificacion,
           java.lang.String tipoDispersion,
           java.lang.String cuentaClabe,
           java.lang.String clienteID,
           java.lang.String forCobComApert,
           java.lang.String montoComApert) {
           this.prospectoID = prospectoID;
           this.productoCreditoID = productoCreditoID;
           this.fechaRegistro = fechaRegistro;
           this.montoSolici = montoSolici;
           this.tasaActiva = tasaActiva;
           this.periodicidad = periodicidad;
           this.plazo = plazo;
           this.calificacion = calificacion;
           this.tipoDispersion = tipoDispersion;
           this.cuentaClabe = cuentaClabe;
           this.clienteID = clienteID;
           this.forCobComApert = forCobComApert;
           this.montoComApert = montoComApert;
    }


    /**
     * Gets the prospectoID value for this SolicitudCreditoRequest.
     * 
     * @return prospectoID
     */
    public java.lang.String getProspectoID() {
        return prospectoID;
    }


    /**
     * Sets the prospectoID value for this SolicitudCreditoRequest.
     * 
     * @param prospectoID
     */
    public void setProspectoID(java.lang.String prospectoID) {
        this.prospectoID = prospectoID;
    }


    /**
     * Gets the productoCreditoID value for this SolicitudCreditoRequest.
     * 
     * @return productoCreditoID
     */
    public java.lang.String getProductoCreditoID() {
        return productoCreditoID;
    }


    /**
     * Sets the productoCreditoID value for this SolicitudCreditoRequest.
     * 
     * @param productoCreditoID
     */
    public void setProductoCreditoID(java.lang.String productoCreditoID) {
        this.productoCreditoID = productoCreditoID;
    }


    /**
     * Gets the fechaRegistro value for this SolicitudCreditoRequest.
     * 
     * @return fechaRegistro
     */
    public java.lang.String getFechaRegistro() {
        return fechaRegistro;
    }


    /**
     * Sets the fechaRegistro value for this SolicitudCreditoRequest.
     * 
     * @param fechaRegistro
     */
    public void setFechaRegistro(java.lang.String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    /**
     * Gets the montoSolici value for this SolicitudCreditoRequest.
     * 
     * @return montoSolici
     */
    public java.lang.String getMontoSolici() {
        return montoSolici;
    }


    /**
     * Sets the montoSolici value for this SolicitudCreditoRequest.
     * 
     * @param montoSolici
     */
    public void setMontoSolici(java.lang.String montoSolici) {
        this.montoSolici = montoSolici;
    }


    /**
     * Gets the tasaActiva value for this SolicitudCreditoRequest.
     * 
     * @return tasaActiva
     */
    public java.lang.String getTasaActiva() {
        return tasaActiva;
    }


    /**
     * Sets the tasaActiva value for this SolicitudCreditoRequest.
     * 
     * @param tasaActiva
     */
    public void setTasaActiva(java.lang.String tasaActiva) {
        this.tasaActiva = tasaActiva;
    }


    /**
     * Gets the periodicidad value for this SolicitudCreditoRequest.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this SolicitudCreditoRequest.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the plazo value for this SolicitudCreditoRequest.
     * 
     * @return plazo
     */
    public java.lang.String getPlazo() {
        return plazo;
    }


    /**
     * Sets the plazo value for this SolicitudCreditoRequest.
     * 
     * @param plazo
     */
    public void setPlazo(java.lang.String plazo) {
        this.plazo = plazo;
    }


    /**
     * Gets the calificacion value for this SolicitudCreditoRequest.
     * 
     * @return calificacion
     */
    public java.lang.String getCalificacion() {
        return calificacion;
    }


    /**
     * Sets the calificacion value for this SolicitudCreditoRequest.
     * 
     * @param calificacion
     */
    public void setCalificacion(java.lang.String calificacion) {
        this.calificacion = calificacion;
    }


    /**
     * Gets the tipoDispersion value for this SolicitudCreditoRequest.
     * 
     * @return tipoDispersion
     */
    public java.lang.String getTipoDispersion() {
        return tipoDispersion;
    }


    /**
     * Sets the tipoDispersion value for this SolicitudCreditoRequest.
     * 
     * @param tipoDispersion
     */
    public void setTipoDispersion(java.lang.String tipoDispersion) {
        this.tipoDispersion = tipoDispersion;
    }


    /**
     * Gets the cuentaClabe value for this SolicitudCreditoRequest.
     * 
     * @return cuentaClabe
     */
    public java.lang.String getCuentaClabe() {
        return cuentaClabe;
    }


    /**
     * Sets the cuentaClabe value for this SolicitudCreditoRequest.
     * 
     * @param cuentaClabe
     */
    public void setCuentaClabe(java.lang.String cuentaClabe) {
        this.cuentaClabe = cuentaClabe;
    }


    /**
     * Gets the clienteID value for this SolicitudCreditoRequest.
     * 
     * @return clienteID
     */
    public java.lang.String getClienteID() {
        return clienteID;
    }


    /**
     * Sets the clienteID value for this SolicitudCreditoRequest.
     * 
     * @param clienteID
     */
    public void setClienteID(java.lang.String clienteID) {
        this.clienteID = clienteID;
    }


    /**
     * Gets the forCobComApert value for this SolicitudCreditoRequest.
     * 
     * @return forCobComApert
     */
    public java.lang.String getForCobComApert() {
        return forCobComApert;
    }


    /**
     * Sets the forCobComApert value for this SolicitudCreditoRequest.
     * 
     * @param forCobComApert
     */
    public void setForCobComApert(java.lang.String forCobComApert) {
        this.forCobComApert = forCobComApert;
    }


    /**
     * Gets the montoComApert value for this SolicitudCreditoRequest.
     * 
     * @return montoComApert
     */
    public java.lang.String getMontoComApert() {
        return montoComApert;
    }


    /**
     * Sets the montoComApert value for this SolicitudCreditoRequest.
     * 
     * @param montoComApert
     */
    public void setMontoComApert(java.lang.String montoComApert) {
        this.montoComApert = montoComApert;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudCreditoRequest)) return false;
        SolicitudCreditoRequest other = (SolicitudCreditoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.prospectoID==null && other.getProspectoID()==null) || 
             (this.prospectoID!=null &&
              this.prospectoID.equals(other.getProspectoID()))) &&
            ((this.productoCreditoID==null && other.getProductoCreditoID()==null) || 
             (this.productoCreditoID!=null &&
              this.productoCreditoID.equals(other.getProductoCreditoID()))) &&
            ((this.fechaRegistro==null && other.getFechaRegistro()==null) || 
             (this.fechaRegistro!=null &&
              this.fechaRegistro.equals(other.getFechaRegistro()))) &&
            ((this.montoSolici==null && other.getMontoSolici()==null) || 
             (this.montoSolici!=null &&
              this.montoSolici.equals(other.getMontoSolici()))) &&
            ((this.tasaActiva==null && other.getTasaActiva()==null) || 
             (this.tasaActiva!=null &&
              this.tasaActiva.equals(other.getTasaActiva()))) &&
            ((this.periodicidad==null && other.getPeriodicidad()==null) || 
             (this.periodicidad!=null &&
              this.periodicidad.equals(other.getPeriodicidad()))) &&
            ((this.plazo==null && other.getPlazo()==null) || 
             (this.plazo!=null &&
              this.plazo.equals(other.getPlazo()))) &&
            ((this.calificacion==null && other.getCalificacion()==null) || 
             (this.calificacion!=null &&
              this.calificacion.equals(other.getCalificacion()))) &&
            ((this.tipoDispersion==null && other.getTipoDispersion()==null) || 
             (this.tipoDispersion!=null &&
              this.tipoDispersion.equals(other.getTipoDispersion()))) &&
            ((this.cuentaClabe==null && other.getCuentaClabe()==null) || 
             (this.cuentaClabe!=null &&
              this.cuentaClabe.equals(other.getCuentaClabe()))) &&
            ((this.clienteID==null && other.getClienteID()==null) || 
             (this.clienteID!=null &&
              this.clienteID.equals(other.getClienteID()))) &&
            ((this.forCobComApert==null && other.getForCobComApert()==null) || 
             (this.forCobComApert!=null &&
              this.forCobComApert.equals(other.getForCobComApert()))) &&
            ((this.montoComApert==null && other.getMontoComApert()==null) || 
             (this.montoComApert!=null &&
              this.montoComApert.equals(other.getMontoComApert())));
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
        if (getProspectoID() != null) {
            _hashCode += getProspectoID().hashCode();
        }
        if (getProductoCreditoID() != null) {
            _hashCode += getProductoCreditoID().hashCode();
        }
        if (getFechaRegistro() != null) {
            _hashCode += getFechaRegistro().hashCode();
        }
        if (getMontoSolici() != null) {
            _hashCode += getMontoSolici().hashCode();
        }
        if (getTasaActiva() != null) {
            _hashCode += getTasaActiva().hashCode();
        }
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getPlazo() != null) {
            _hashCode += getPlazo().hashCode();
        }
        if (getCalificacion() != null) {
            _hashCode += getCalificacion().hashCode();
        }
        if (getTipoDispersion() != null) {
            _hashCode += getTipoDispersion().hashCode();
        }
        if (getCuentaClabe() != null) {
            _hashCode += getCuentaClabe().hashCode();
        }
        if (getClienteID() != null) {
            _hashCode += getClienteID().hashCode();
        }
        if (getForCobComApert() != null) {
            _hashCode += getForCobComApert().hashCode();
        }
        if (getMontoComApert() != null) {
            _hashCode += getMontoComApert().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudCreditoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">solicitudCreditoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "prospectoID"));
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
        elemField.setFieldName("fechaRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "fechaRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoSolici");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoSolici"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasaActiva");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tasaActiva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodicidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "periodicidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plazo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "plazo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "calificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDispersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tipoDispersion"));
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
        elemField.setFieldName("clienteID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "clienteID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forCobComApert");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "forCobComApert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoComApert");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoComApert"));
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
