/**
 * WsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class WsResponse  implements java.io.Serializable {
    private java.lang.String[] depositos_no_identificados;

    private java.lang.String ejecucion_cierre_del_dia;

    private java.lang.String folio;

    private java.lang.String[] listaPublicacionesPendientes;

    private java.lang.String message;

    private java.lang.String status;

    private java.lang.String tienda_DISABLED;

    public WsResponse() {
    }

    public WsResponse(
           java.lang.String[] depositos_no_identificados,
           java.lang.String ejecucion_cierre_del_dia,
           java.lang.String folio,
           java.lang.String[] listaPublicacionesPendientes,
           java.lang.String message,
           java.lang.String status,
           java.lang.String tienda_DISABLED) {
           this.depositos_no_identificados = depositos_no_identificados;
           this.ejecucion_cierre_del_dia = ejecucion_cierre_del_dia;
           this.folio = folio;
           this.listaPublicacionesPendientes = listaPublicacionesPendientes;
           this.message = message;
           this.status = status;
           this.tienda_DISABLED = tienda_DISABLED;
    }


    /**
     * Gets the depositos_no_identificados value for this WsResponse.
     * 
     * @return depositos_no_identificados
     */
    public java.lang.String[] getDepositos_no_identificados() {
        return depositos_no_identificados;
    }


    /**
     * Sets the depositos_no_identificados value for this WsResponse.
     * 
     * @param depositos_no_identificados
     */
    public void setDepositos_no_identificados(java.lang.String[] depositos_no_identificados) {
        this.depositos_no_identificados = depositos_no_identificados;
    }


    /**
     * Gets the ejecucion_cierre_del_dia value for this WsResponse.
     * 
     * @return ejecucion_cierre_del_dia
     */
    public java.lang.String getEjecucion_cierre_del_dia() {
        return ejecucion_cierre_del_dia;
    }


    /**
     * Sets the ejecucion_cierre_del_dia value for this WsResponse.
     * 
     * @param ejecucion_cierre_del_dia
     */
    public void setEjecucion_cierre_del_dia(java.lang.String ejecucion_cierre_del_dia) {
        this.ejecucion_cierre_del_dia = ejecucion_cierre_del_dia;
    }


    /**
     * Gets the folio value for this WsResponse.
     * 
     * @return folio
     */
    public java.lang.String getFolio() {
        return folio;
    }


    /**
     * Sets the folio value for this WsResponse.
     * 
     * @param folio
     */
    public void setFolio(java.lang.String folio) {
        this.folio = folio;
    }


    /**
     * Gets the listaPublicacionesPendientes value for this WsResponse.
     * 
     * @return listaPublicacionesPendientes
     */
    public java.lang.String[] getListaPublicacionesPendientes() {
        return listaPublicacionesPendientes;
    }


    /**
     * Sets the listaPublicacionesPendientes value for this WsResponse.
     * 
     * @param listaPublicacionesPendientes
     */
    public void setListaPublicacionesPendientes(java.lang.String[] listaPublicacionesPendientes) {
        this.listaPublicacionesPendientes = listaPublicacionesPendientes;
    }


    /**
     * Gets the message value for this WsResponse.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this WsResponse.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the status value for this WsResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this WsResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the tienda_DISABLED value for this WsResponse.
     * 
     * @return tienda_DISABLED
     */
    public java.lang.String getTienda_DISABLED() {
        return tienda_DISABLED;
    }


    /**
     * Sets the tienda_DISABLED value for this WsResponse.
     * 
     * @param tienda_DISABLED
     */
    public void setTienda_DISABLED(java.lang.String tienda_DISABLED) {
        this.tienda_DISABLED = tienda_DISABLED;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsResponse)) return false;
        WsResponse other = (WsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.depositos_no_identificados==null && other.getDepositos_no_identificados()==null) || 
             (this.depositos_no_identificados!=null &&
              java.util.Arrays.equals(this.depositos_no_identificados, other.getDepositos_no_identificados()))) &&
            ((this.ejecucion_cierre_del_dia==null && other.getEjecucion_cierre_del_dia()==null) || 
             (this.ejecucion_cierre_del_dia!=null &&
              this.ejecucion_cierre_del_dia.equals(other.getEjecucion_cierre_del_dia()))) &&
            ((this.folio==null && other.getFolio()==null) || 
             (this.folio!=null &&
              this.folio.equals(other.getFolio()))) &&
            ((this.listaPublicacionesPendientes==null && other.getListaPublicacionesPendientes()==null) || 
             (this.listaPublicacionesPendientes!=null &&
              java.util.Arrays.equals(this.listaPublicacionesPendientes, other.getListaPublicacionesPendientes()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.tienda_DISABLED==null && other.getTienda_DISABLED()==null) || 
             (this.tienda_DISABLED!=null &&
              this.tienda_DISABLED.equals(other.getTienda_DISABLED())));
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
        if (getDepositos_no_identificados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDepositos_no_identificados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDepositos_no_identificados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEjecucion_cierre_del_dia() != null) {
            _hashCode += getEjecucion_cierre_del_dia().hashCode();
        }
        if (getFolio() != null) {
            _hashCode += getFolio().hashCode();
        }
        if (getListaPublicacionesPendientes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaPublicacionesPendientes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaPublicacionesPendientes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTienda_DISABLED() != null) {
            _hashCode += getTienda_DISABLED().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "WsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depositos_no_identificados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "depositos_no_identificados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ejecucion_cierre_del_dia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ejecucion_cierre_del_dia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "folio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaPublicacionesPendientes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "listaPublicacionesPendientes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tienda_DISABLED");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "tienda_DISABLED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
