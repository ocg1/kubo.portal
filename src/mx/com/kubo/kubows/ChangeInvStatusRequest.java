/**
 * ChangeInvStatusRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class ChangeInvStatusRequest  implements java.io.Serializable {
    private java.lang.String description;

    private java.lang.String motive_inv_id;

    private java.lang.String prospectus_change_id;

    private java.lang.String prospectus_investor_id;

    private java.lang.String status_id;

    public ChangeInvStatusRequest() {
    }

    public ChangeInvStatusRequest(
           java.lang.String description,
           java.lang.String motive_inv_id,
           java.lang.String prospectus_change_id,
           java.lang.String prospectus_investor_id,
           java.lang.String status_id) {
           this.description = description;
           this.motive_inv_id = motive_inv_id;
           this.prospectus_change_id = prospectus_change_id;
           this.prospectus_investor_id = prospectus_investor_id;
           this.status_id = status_id;
    }


    /**
     * Gets the description value for this ChangeInvStatusRequest.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ChangeInvStatusRequest.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the motive_inv_id value for this ChangeInvStatusRequest.
     * 
     * @return motive_inv_id
     */
    public java.lang.String getMotive_inv_id() {
        return motive_inv_id;
    }


    /**
     * Sets the motive_inv_id value for this ChangeInvStatusRequest.
     * 
     * @param motive_inv_id
     */
    public void setMotive_inv_id(java.lang.String motive_inv_id) {
        this.motive_inv_id = motive_inv_id;
    }


    /**
     * Gets the prospectus_change_id value for this ChangeInvStatusRequest.
     * 
     * @return prospectus_change_id
     */
    public java.lang.String getProspectus_change_id() {
        return prospectus_change_id;
    }


    /**
     * Sets the prospectus_change_id value for this ChangeInvStatusRequest.
     * 
     * @param prospectus_change_id
     */
    public void setProspectus_change_id(java.lang.String prospectus_change_id) {
        this.prospectus_change_id = prospectus_change_id;
    }


    /**
     * Gets the prospectus_investor_id value for this ChangeInvStatusRequest.
     * 
     * @return prospectus_investor_id
     */
    public java.lang.String getProspectus_investor_id() {
        return prospectus_investor_id;
    }


    /**
     * Sets the prospectus_investor_id value for this ChangeInvStatusRequest.
     * 
     * @param prospectus_investor_id
     */
    public void setProspectus_investor_id(java.lang.String prospectus_investor_id) {
        this.prospectus_investor_id = prospectus_investor_id;
    }


    /**
     * Gets the status_id value for this ChangeInvStatusRequest.
     * 
     * @return status_id
     */
    public java.lang.String getStatus_id() {
        return status_id;
    }


    /**
     * Sets the status_id value for this ChangeInvStatusRequest.
     * 
     * @param status_id
     */
    public void setStatus_id(java.lang.String status_id) {
        this.status_id = status_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChangeInvStatusRequest)) return false;
        ChangeInvStatusRequest other = (ChangeInvStatusRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.motive_inv_id==null && other.getMotive_inv_id()==null) || 
             (this.motive_inv_id!=null &&
              this.motive_inv_id.equals(other.getMotive_inv_id()))) &&
            ((this.prospectus_change_id==null && other.getProspectus_change_id()==null) || 
             (this.prospectus_change_id!=null &&
              this.prospectus_change_id.equals(other.getProspectus_change_id()))) &&
            ((this.prospectus_investor_id==null && other.getProspectus_investor_id()==null) || 
             (this.prospectus_investor_id!=null &&
              this.prospectus_investor_id.equals(other.getProspectus_investor_id()))) &&
            ((this.status_id==null && other.getStatus_id()==null) || 
             (this.status_id!=null &&
              this.status_id.equals(other.getStatus_id())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getMotive_inv_id() != null) {
            _hashCode += getMotive_inv_id().hashCode();
        }
        if (getProspectus_change_id() != null) {
            _hashCode += getProspectus_change_id().hashCode();
        }
        if (getProspectus_investor_id() != null) {
            _hashCode += getProspectus_investor_id().hashCode();
        }
        if (getStatus_id() != null) {
            _hashCode += getStatus_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChangeInvStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ChangeInvStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motive_inv_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "motive_inv_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectus_change_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_change_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectus_investor_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_investor_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "status_id"));
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
