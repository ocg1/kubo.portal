/**
 * ConsultaDisponiblePorClienteRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package safisrv.ws.CuentasServicios;

public class ConsultaDisponiblePorClienteRequest  implements java.io.Serializable {
    private java.lang.String clienteID;

    public ConsultaDisponiblePorClienteRequest() {
    }

    public ConsultaDisponiblePorClienteRequest(
           java.lang.String clienteID) {
           this.clienteID = clienteID;
    }


    /**
     * Gets the clienteID value for this ConsultaDisponiblePorClienteRequest.
     * 
     * @return clienteID
     */
    public java.lang.String getClienteID() {
        return clienteID;
    }


    /**
     * Sets the clienteID value for this ConsultaDisponiblePorClienteRequest.
     * 
     * @param clienteID
     */
    public void setClienteID(java.lang.String clienteID) {
        this.clienteID = clienteID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDisponiblePorClienteRequest)) return false;
        ConsultaDisponiblePorClienteRequest other = (ConsultaDisponiblePorClienteRequest) obj;
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
              this.clienteID.equals(other.getClienteID())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDisponiblePorClienteRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://safisrv/ws/schemas", ">consultaDisponiblePorClienteRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clienteID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://safisrv/ws/schemas", "clienteID"));
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
