/**
 * SimuladorCuotaCreditoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CreditosServicios;

public class SimuladorCuotaCreditoRequest  implements java.io.Serializable {
    private java.lang.String montoSolici;

    private java.lang.String frecuencia;

    private java.lang.String plazo;

    private java.lang.String tasaAnualizada;

    private java.lang.String fechaInicio;

    private java.lang.String ajustarFecVen;

    private java.lang.String comisionApertura;

    private java.lang.String formaCobroComAp;

    public SimuladorCuotaCreditoRequest() {
    }

    public SimuladorCuotaCreditoRequest(
           java.lang.String montoSolici,
           java.lang.String frecuencia,
           java.lang.String plazo,
           java.lang.String tasaAnualizada,
           java.lang.String fechaInicio,
           java.lang.String ajustarFecVen,
           java.lang.String comisionApertura,
           java.lang.String formaCobroComAp) {
           this.montoSolici = montoSolici;
           this.frecuencia = frecuencia;
           this.plazo = plazo;
           this.tasaAnualizada = tasaAnualizada;
           this.fechaInicio = fechaInicio;
           this.ajustarFecVen = ajustarFecVen;
           this.comisionApertura = comisionApertura;
           this.formaCobroComAp = formaCobroComAp;
    }


    /**
     * Gets the montoSolici value for this SimuladorCuotaCreditoRequest.
     * 
     * @return montoSolici
     */
    public java.lang.String getMontoSolici() {
        return montoSolici;
    }


    /**
     * Sets the montoSolici value for this SimuladorCuotaCreditoRequest.
     * 
     * @param montoSolici
     */
    public void setMontoSolici(java.lang.String montoSolici) {
        this.montoSolici = montoSolici;
    }


    /**
     * Gets the frecuencia value for this SimuladorCuotaCreditoRequest.
     * 
     * @return frecuencia
     */
    public java.lang.String getFrecuencia() {
        return frecuencia;
    }


    /**
     * Sets the frecuencia value for this SimuladorCuotaCreditoRequest.
     * 
     * @param frecuencia
     */
    public void setFrecuencia(java.lang.String frecuencia) {
        this.frecuencia = frecuencia;
    }


    /**
     * Gets the plazo value for this SimuladorCuotaCreditoRequest.
     * 
     * @return plazo
     */
    public java.lang.String getPlazo() {
        return plazo;
    }


    /**
     * Sets the plazo value for this SimuladorCuotaCreditoRequest.
     * 
     * @param plazo
     */
    public void setPlazo(java.lang.String plazo) {
        this.plazo = plazo;
    }


    /**
     * Gets the tasaAnualizada value for this SimuladorCuotaCreditoRequest.
     * 
     * @return tasaAnualizada
     */
    public java.lang.String getTasaAnualizada() {
        return tasaAnualizada;
    }


    /**
     * Sets the tasaAnualizada value for this SimuladorCuotaCreditoRequest.
     * 
     * @param tasaAnualizada
     */
    public void setTasaAnualizada(java.lang.String tasaAnualizada) {
        this.tasaAnualizada = tasaAnualizada;
    }


    /**
     * Gets the fechaInicio value for this SimuladorCuotaCreditoRequest.
     * 
     * @return fechaInicio
     */
    public java.lang.String getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this SimuladorCuotaCreditoRequest.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.lang.String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the ajustarFecVen value for this SimuladorCuotaCreditoRequest.
     * 
     * @return ajustarFecVen
     */
    public java.lang.String getAjustarFecVen() {
        return ajustarFecVen;
    }


    /**
     * Sets the ajustarFecVen value for this SimuladorCuotaCreditoRequest.
     * 
     * @param ajustarFecVen
     */
    public void setAjustarFecVen(java.lang.String ajustarFecVen) {
        this.ajustarFecVen = ajustarFecVen;
    }


    /**
     * Gets the comisionApertura value for this SimuladorCuotaCreditoRequest.
     * 
     * @return comisionApertura
     */
    public java.lang.String getComisionApertura() {
        return comisionApertura;
    }


    /**
     * Sets the comisionApertura value for this SimuladorCuotaCreditoRequest.
     * 
     * @param comisionApertura
     */
    public void setComisionApertura(java.lang.String comisionApertura) {
        this.comisionApertura = comisionApertura;
    }


    /**
     * Gets the formaCobroComAp value for this SimuladorCuotaCreditoRequest.
     * 
     * @return formaCobroComAp
     */
    public java.lang.String getFormaCobroComAp() {
        return formaCobroComAp;
    }


    /**
     * Sets the formaCobroComAp value for this SimuladorCuotaCreditoRequest.
     * 
     * @param formaCobroComAp
     */
    public void setFormaCobroComAp(java.lang.String formaCobroComAp) {
        this.formaCobroComAp = formaCobroComAp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimuladorCuotaCreditoRequest)) return false;
        SimuladorCuotaCreditoRequest other = (SimuladorCuotaCreditoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.montoSolici==null && other.getMontoSolici()==null) || 
             (this.montoSolici!=null &&
              this.montoSolici.equals(other.getMontoSolici()))) &&
            ((this.frecuencia==null && other.getFrecuencia()==null) || 
             (this.frecuencia!=null &&
              this.frecuencia.equals(other.getFrecuencia()))) &&
            ((this.plazo==null && other.getPlazo()==null) || 
             (this.plazo!=null &&
              this.plazo.equals(other.getPlazo()))) &&
            ((this.tasaAnualizada==null && other.getTasaAnualizada()==null) || 
             (this.tasaAnualizada!=null &&
              this.tasaAnualizada.equals(other.getTasaAnualizada()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.ajustarFecVen==null && other.getAjustarFecVen()==null) || 
             (this.ajustarFecVen!=null &&
              this.ajustarFecVen.equals(other.getAjustarFecVen()))) &&
            ((this.comisionApertura==null && other.getComisionApertura()==null) || 
             (this.comisionApertura!=null &&
              this.comisionApertura.equals(other.getComisionApertura()))) &&
            ((this.formaCobroComAp==null && other.getFormaCobroComAp()==null) || 
             (this.formaCobroComAp!=null &&
              this.formaCobroComAp.equals(other.getFormaCobroComAp())));
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
        if (getMontoSolici() != null) {
            _hashCode += getMontoSolici().hashCode();
        }
        if (getFrecuencia() != null) {
            _hashCode += getFrecuencia().hashCode();
        }
        if (getPlazo() != null) {
            _hashCode += getPlazo().hashCode();
        }
        if (getTasaAnualizada() != null) {
            _hashCode += getTasaAnualizada().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getAjustarFecVen() != null) {
            _hashCode += getAjustarFecVen().hashCode();
        }
        if (getComisionApertura() != null) {
            _hashCode += getComisionApertura().hashCode();
        }
        if (getFormaCobroComAp() != null) {
            _hashCode += getFormaCobroComAp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimuladorCuotaCreditoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">simuladorCuotaCreditoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoSolici");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "montoSolici"));
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
        elemField.setFieldName("plazo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "plazo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasaAnualizada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tasaAnualizada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "fechaInicio"));
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
        elemField.setFieldName("comisionApertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "comisionApertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formaCobroComAp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "formaCobroComAp"));
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
