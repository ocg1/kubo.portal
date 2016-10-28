/**
 * AltaCuentaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CuentasServicios;

public class AltaCuentaRequest  implements java.io.Serializable {
    private java.lang.String sucursalID;

    private java.lang.String clienteID;

    private java.lang.String clabe;

    private java.lang.String monedaID;

    private java.lang.String tipoCuentaID;

    private java.lang.String fechaReg;

    private java.lang.String etiqueta;

    private java.lang.String edoCta;

    private java.lang.String institucionID;

    private java.lang.String esPrincipal;

    public AltaCuentaRequest() {
    }

    public AltaCuentaRequest(
           java.lang.String sucursalID,
           java.lang.String clienteID,
           java.lang.String clabe,
           java.lang.String monedaID,
           java.lang.String tipoCuentaID,
           java.lang.String fechaReg,
           java.lang.String etiqueta,
           java.lang.String edoCta,
           java.lang.String institucionID,
           java.lang.String esPrincipal) {
           this.sucursalID = sucursalID;
           this.clienteID = clienteID;
           this.clabe = clabe;
           this.monedaID = monedaID;
           this.tipoCuentaID = tipoCuentaID;
           this.fechaReg = fechaReg;
           this.etiqueta = etiqueta;
           this.edoCta = edoCta;
           this.institucionID = institucionID;
           this.esPrincipal = esPrincipal;
    }


    /**
     * Gets the sucursalID value for this AltaCuentaRequest.
     * 
     * @return sucursalID
     */
    public java.lang.String getSucursalID() {
        return sucursalID;
    }


    /**
     * Sets the sucursalID value for this AltaCuentaRequest.
     * 
     * @param sucursalID
     */
    public void setSucursalID(java.lang.String sucursalID) {
        this.sucursalID = sucursalID;
    }


    /**
     * Gets the clienteID value for this AltaCuentaRequest.
     * 
     * @return clienteID
     */
    public java.lang.String getClienteID() {
        return clienteID;
    }


    /**
     * Sets the clienteID value for this AltaCuentaRequest.
     * 
     * @param clienteID
     */
    public void setClienteID(java.lang.String clienteID) {
        this.clienteID = clienteID;
    }


    /**
     * Gets the clabe value for this AltaCuentaRequest.
     * 
     * @return clabe
     */
    public java.lang.String getClabe() {
        return clabe;
    }


    /**
     * Sets the clabe value for this AltaCuentaRequest.
     * 
     * @param clabe
     */
    public void setClabe(java.lang.String clabe) {
        this.clabe = clabe;
    }


    /**
     * Gets the monedaID value for this AltaCuentaRequest.
     * 
     * @return monedaID
     */
    public java.lang.String getMonedaID() {
        return monedaID;
    }


    /**
     * Sets the monedaID value for this AltaCuentaRequest.
     * 
     * @param monedaID
     */
    public void setMonedaID(java.lang.String monedaID) {
        this.monedaID = monedaID;
    }


    /**
     * Gets the tipoCuentaID value for this AltaCuentaRequest.
     * 
     * @return tipoCuentaID
     */
    public java.lang.String getTipoCuentaID() {
        return tipoCuentaID;
    }


    /**
     * Sets the tipoCuentaID value for this AltaCuentaRequest.
     * 
     * @param tipoCuentaID
     */
    public void setTipoCuentaID(java.lang.String tipoCuentaID) {
        this.tipoCuentaID = tipoCuentaID;
    }


    /**
     * Gets the fechaReg value for this AltaCuentaRequest.
     * 
     * @return fechaReg
     */
    public java.lang.String getFechaReg() {
        return fechaReg;
    }


    /**
     * Sets the fechaReg value for this AltaCuentaRequest.
     * 
     * @param fechaReg
     */
    public void setFechaReg(java.lang.String fechaReg) {
        this.fechaReg = fechaReg;
    }


    /**
     * Gets the etiqueta value for this AltaCuentaRequest.
     * 
     * @return etiqueta
     */
    public java.lang.String getEtiqueta() {
        return etiqueta;
    }


    /**
     * Sets the etiqueta value for this AltaCuentaRequest.
     * 
     * @param etiqueta
     */
    public void setEtiqueta(java.lang.String etiqueta) {
        this.etiqueta = etiqueta;
    }


    /**
     * Gets the edoCta value for this AltaCuentaRequest.
     * 
     * @return edoCta
     */
    public java.lang.String getEdoCta() {
        return edoCta;
    }


    /**
     * Sets the edoCta value for this AltaCuentaRequest.
     * 
     * @param edoCta
     */
    public void setEdoCta(java.lang.String edoCta) {
        this.edoCta = edoCta;
    }


    /**
     * Gets the institucionID value for this AltaCuentaRequest.
     * 
     * @return institucionID
     */
    public java.lang.String getInstitucionID() {
        return institucionID;
    }


    /**
     * Sets the institucionID value for this AltaCuentaRequest.
     * 
     * @param institucionID
     */
    public void setInstitucionID(java.lang.String institucionID) {
        this.institucionID = institucionID;
    }


    /**
     * Gets the esPrincipal value for this AltaCuentaRequest.
     * 
     * @return esPrincipal
     */
    public java.lang.String getEsPrincipal() {
        return esPrincipal;
    }


    /**
     * Sets the esPrincipal value for this AltaCuentaRequest.
     * 
     * @param esPrincipal
     */
    public void setEsPrincipal(java.lang.String esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AltaCuentaRequest)) return false;
        AltaCuentaRequest other = (AltaCuentaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sucursalID==null && other.getSucursalID()==null) || 
             (this.sucursalID!=null &&
              this.sucursalID.equals(other.getSucursalID()))) &&
            ((this.clienteID==null && other.getClienteID()==null) || 
             (this.clienteID!=null &&
              this.clienteID.equals(other.getClienteID()))) &&
            ((this.clabe==null && other.getClabe()==null) || 
             (this.clabe!=null &&
              this.clabe.equals(other.getClabe()))) &&
            ((this.monedaID==null && other.getMonedaID()==null) || 
             (this.monedaID!=null &&
              this.monedaID.equals(other.getMonedaID()))) &&
            ((this.tipoCuentaID==null && other.getTipoCuentaID()==null) || 
             (this.tipoCuentaID!=null &&
              this.tipoCuentaID.equals(other.getTipoCuentaID()))) &&
            ((this.fechaReg==null && other.getFechaReg()==null) || 
             (this.fechaReg!=null &&
              this.fechaReg.equals(other.getFechaReg()))) &&
            ((this.etiqueta==null && other.getEtiqueta()==null) || 
             (this.etiqueta!=null &&
              this.etiqueta.equals(other.getEtiqueta()))) &&
            ((this.edoCta==null && other.getEdoCta()==null) || 
             (this.edoCta!=null &&
              this.edoCta.equals(other.getEdoCta()))) &&
            ((this.institucionID==null && other.getInstitucionID()==null) || 
             (this.institucionID!=null &&
              this.institucionID.equals(other.getInstitucionID()))) &&
            ((this.esPrincipal==null && other.getEsPrincipal()==null) || 
             (this.esPrincipal!=null &&
              this.esPrincipal.equals(other.getEsPrincipal())));
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
        if (getSucursalID() != null) {
            _hashCode += getSucursalID().hashCode();
        }
        if (getClienteID() != null) {
            _hashCode += getClienteID().hashCode();
        }
        if (getClabe() != null) {
            _hashCode += getClabe().hashCode();
        }
        if (getMonedaID() != null) {
            _hashCode += getMonedaID().hashCode();
        }
        if (getTipoCuentaID() != null) {
            _hashCode += getTipoCuentaID().hashCode();
        }
        if (getFechaReg() != null) {
            _hashCode += getFechaReg().hashCode();
        }
        if (getEtiqueta() != null) {
            _hashCode += getEtiqueta().hashCode();
        }
        if (getEdoCta() != null) {
            _hashCode += getEdoCta().hashCode();
        }
        if (getInstitucionID() != null) {
            _hashCode += getInstitucionID().hashCode();
        }
        if (getEsPrincipal() != null) {
            _hashCode += getEsPrincipal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AltaCuentaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">altaCuentaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sucursalID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "sucursalID"));
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
        elemField.setFieldName("clabe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "clabe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monedaID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "monedaID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuentaID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "tipoCuentaID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaReg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "fechaReg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etiqueta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "etiqueta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edoCta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "edoCta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institucionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "institucionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "esPrincipal"));
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
