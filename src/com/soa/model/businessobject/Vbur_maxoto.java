/**
 * Vbur_maxoto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class Vbur_maxoto  implements java.io.Serializable {
    private java.lang.String bur_solnum;

    private java.lang.String idcliente;

    private java.lang.String idprospecto;

    private java.lang.String max_liquidado;

    private java.lang.String max_noliquidado;

    private java.lang.String otorgante;

    private java.util.Calendar prim_credito;

    private java.util.Calendar ult_credito;

    public Vbur_maxoto() {
    }

    public Vbur_maxoto(
           java.lang.String bur_solnum,
           java.lang.String idcliente,
           java.lang.String idprospecto,
           java.lang.String max_liquidado,
           java.lang.String max_noliquidado,
           java.lang.String otorgante,
           java.util.Calendar prim_credito,
           java.util.Calendar ult_credito) {
           this.bur_solnum = bur_solnum;
           this.idcliente = idcliente;
           this.idprospecto = idprospecto;
           this.max_liquidado = max_liquidado;
           this.max_noliquidado = max_noliquidado;
           this.otorgante = otorgante;
           this.prim_credito = prim_credito;
           this.ult_credito = ult_credito;
    }


    /**
     * Gets the bur_solnum value for this Vbur_maxoto.
     * 
     * @return bur_solnum
     */
    public java.lang.String getBur_solnum() {
        return bur_solnum;
    }


    /**
     * Sets the bur_solnum value for this Vbur_maxoto.
     * 
     * @param bur_solnum
     */
    public void setBur_solnum(java.lang.String bur_solnum) {
        this.bur_solnum = bur_solnum;
    }


    /**
     * Gets the idcliente value for this Vbur_maxoto.
     * 
     * @return idcliente
     */
    public java.lang.String getIdcliente() {
        return idcliente;
    }


    /**
     * Sets the idcliente value for this Vbur_maxoto.
     * 
     * @param idcliente
     */
    public void setIdcliente(java.lang.String idcliente) {
        this.idcliente = idcliente;
    }


    /**
     * Gets the idprospecto value for this Vbur_maxoto.
     * 
     * @return idprospecto
     */
    public java.lang.String getIdprospecto() {
        return idprospecto;
    }


    /**
     * Sets the idprospecto value for this Vbur_maxoto.
     * 
     * @param idprospecto
     */
    public void setIdprospecto(java.lang.String idprospecto) {
        this.idprospecto = idprospecto;
    }


    /**
     * Gets the max_liquidado value for this Vbur_maxoto.
     * 
     * @return max_liquidado
     */
    public java.lang.String getMax_liquidado() {
        return max_liquidado;
    }


    /**
     * Sets the max_liquidado value for this Vbur_maxoto.
     * 
     * @param max_liquidado
     */
    public void setMax_liquidado(java.lang.String max_liquidado) {
        this.max_liquidado = max_liquidado;
    }


    /**
     * Gets the max_noliquidado value for this Vbur_maxoto.
     * 
     * @return max_noliquidado
     */
    public java.lang.String getMax_noliquidado() {
        return max_noliquidado;
    }


    /**
     * Sets the max_noliquidado value for this Vbur_maxoto.
     * 
     * @param max_noliquidado
     */
    public void setMax_noliquidado(java.lang.String max_noliquidado) {
        this.max_noliquidado = max_noliquidado;
    }


    /**
     * Gets the otorgante value for this Vbur_maxoto.
     * 
     * @return otorgante
     */
    public java.lang.String getOtorgante() {
        return otorgante;
    }


    /**
     * Sets the otorgante value for this Vbur_maxoto.
     * 
     * @param otorgante
     */
    public void setOtorgante(java.lang.String otorgante) {
        this.otorgante = otorgante;
    }


    /**
     * Gets the prim_credito value for this Vbur_maxoto.
     * 
     * @return prim_credito
     */
    public java.util.Calendar getPrim_credito() {
        return prim_credito;
    }


    /**
     * Sets the prim_credito value for this Vbur_maxoto.
     * 
     * @param prim_credito
     */
    public void setPrim_credito(java.util.Calendar prim_credito) {
        this.prim_credito = prim_credito;
    }


    /**
     * Gets the ult_credito value for this Vbur_maxoto.
     * 
     * @return ult_credito
     */
    public java.util.Calendar getUlt_credito() {
        return ult_credito;
    }


    /**
     * Sets the ult_credito value for this Vbur_maxoto.
     * 
     * @param ult_credito
     */
    public void setUlt_credito(java.util.Calendar ult_credito) {
        this.ult_credito = ult_credito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Vbur_maxoto)) return false;
        Vbur_maxoto other = (Vbur_maxoto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bur_solnum==null && other.getBur_solnum()==null) || 
             (this.bur_solnum!=null &&
              this.bur_solnum.equals(other.getBur_solnum()))) &&
            ((this.idcliente==null && other.getIdcliente()==null) || 
             (this.idcliente!=null &&
              this.idcliente.equals(other.getIdcliente()))) &&
            ((this.idprospecto==null && other.getIdprospecto()==null) || 
             (this.idprospecto!=null &&
              this.idprospecto.equals(other.getIdprospecto()))) &&
            ((this.max_liquidado==null && other.getMax_liquidado()==null) || 
             (this.max_liquidado!=null &&
              this.max_liquidado.equals(other.getMax_liquidado()))) &&
            ((this.max_noliquidado==null && other.getMax_noliquidado()==null) || 
             (this.max_noliquidado!=null &&
              this.max_noliquidado.equals(other.getMax_noliquidado()))) &&
            ((this.otorgante==null && other.getOtorgante()==null) || 
             (this.otorgante!=null &&
              this.otorgante.equals(other.getOtorgante()))) &&
            ((this.prim_credito==null && other.getPrim_credito()==null) || 
             (this.prim_credito!=null &&
              this.prim_credito.equals(other.getPrim_credito()))) &&
            ((this.ult_credito==null && other.getUlt_credito()==null) || 
             (this.ult_credito!=null &&
              this.ult_credito.equals(other.getUlt_credito())));
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
        if (getBur_solnum() != null) {
            _hashCode += getBur_solnum().hashCode();
        }
        if (getIdcliente() != null) {
            _hashCode += getIdcliente().hashCode();
        }
        if (getIdprospecto() != null) {
            _hashCode += getIdprospecto().hashCode();
        }
        if (getMax_liquidado() != null) {
            _hashCode += getMax_liquidado().hashCode();
        }
        if (getMax_noliquidado() != null) {
            _hashCode += getMax_noliquidado().hashCode();
        }
        if (getOtorgante() != null) {
            _hashCode += getOtorgante().hashCode();
        }
        if (getPrim_credito() != null) {
            _hashCode += getPrim_credito().hashCode();
        }
        if (getUlt_credito() != null) {
            _hashCode += getUlt_credito().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Vbur_maxoto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vbur_maxoto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bur_solnum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "bur_solnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idcliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "idcliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idprospecto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "idprospecto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_liquidado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "max_liquidado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_noliquidado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "max_noliquidado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otorgante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "otorgante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prim_credito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "prim_credito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ult_credito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "ult_credito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
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
