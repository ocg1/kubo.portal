/**
 * AutorizaCuentaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CuentasServicios;

public class AutorizaCuentaRequest  implements java.io.Serializable {
    private java.lang.String cuentaAhoID;

    private java.lang.String usuarioApeID;

    private java.lang.String fechaApertura;

    public AutorizaCuentaRequest() {
    }

    public AutorizaCuentaRequest(
           java.lang.String cuentaAhoID,
           java.lang.String usuarioApeID,
           java.lang.String fechaApertura) {
           this.cuentaAhoID = cuentaAhoID;
           this.usuarioApeID = usuarioApeID;
           this.fechaApertura = fechaApertura;
    }


    /**
     * Gets the cuentaAhoID value for this AutorizaCuentaRequest.
     * 
     * @return cuentaAhoID
     */
    public java.lang.String getCuentaAhoID() {
        return cuentaAhoID;
    }


    /**
     * Sets the cuentaAhoID value for this AutorizaCuentaRequest.
     * 
     * @param cuentaAhoID
     */
    public void setCuentaAhoID(java.lang.String cuentaAhoID) {
        this.cuentaAhoID = cuentaAhoID;
    }


    /**
     * Gets the usuarioApeID value for this AutorizaCuentaRequest.
     * 
     * @return usuarioApeID
     */
    public java.lang.String getUsuarioApeID() {
        return usuarioApeID;
    }


    /**
     * Sets the usuarioApeID value for this AutorizaCuentaRequest.
     * 
     * @param usuarioApeID
     */
    public void setUsuarioApeID(java.lang.String usuarioApeID) {
        this.usuarioApeID = usuarioApeID;
    }


    /**
     * Gets the fechaApertura value for this AutorizaCuentaRequest.
     * 
     * @return fechaApertura
     */
    public java.lang.String getFechaApertura() {
        return fechaApertura;
    }


    /**
     * Sets the fechaApertura value for this AutorizaCuentaRequest.
     * 
     * @param fechaApertura
     */
    public void setFechaApertura(java.lang.String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutorizaCuentaRequest)) return false;
        AutorizaCuentaRequest other = (AutorizaCuentaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cuentaAhoID==null && other.getCuentaAhoID()==null) || 
             (this.cuentaAhoID!=null &&
              this.cuentaAhoID.equals(other.getCuentaAhoID()))) &&
            ((this.usuarioApeID==null && other.getUsuarioApeID()==null) || 
             (this.usuarioApeID!=null &&
              this.usuarioApeID.equals(other.getUsuarioApeID()))) &&
            ((this.fechaApertura==null && other.getFechaApertura()==null) || 
             (this.fechaApertura!=null &&
              this.fechaApertura.equals(other.getFechaApertura())));
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
        if (getCuentaAhoID() != null) {
            _hashCode += getCuentaAhoID().hashCode();
        }
        if (getUsuarioApeID() != null) {
            _hashCode += getUsuarioApeID().hashCode();
        }
        if (getFechaApertura() != null) {
            _hashCode += getFechaApertura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutorizaCuentaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">autorizaCuentaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentaAhoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "cuentaAhoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioApeID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "usuarioApeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaApertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "fechaApertura"));
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
