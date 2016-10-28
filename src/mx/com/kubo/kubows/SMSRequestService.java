/**
 * SMSRequestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class SMSRequestService  implements java.io.Serializable {
    private java.lang.String bursolnum;

    private java.lang.String campaign;

    private java.lang.String emisor_id;

    private java.lang.String event_id;

    private java.lang.String message;

    private java.lang.String[] prospectus_id;

    public SMSRequestService() {
    }

    public SMSRequestService(
           java.lang.String bursolnum,
           java.lang.String campaign,
           java.lang.String emisor_id,
           java.lang.String event_id,
           java.lang.String message,
           java.lang.String[] prospectus_id) {
           this.bursolnum = bursolnum;
           this.campaign = campaign;
           this.emisor_id = emisor_id;
           this.event_id = event_id;
           this.message = message;
           this.prospectus_id = prospectus_id;
    }


    /**
     * Gets the bursolnum value for this SMSRequestService.
     * 
     * @return bursolnum
     */
    public java.lang.String getBursolnum() {
        return bursolnum;
    }


    /**
     * Sets the bursolnum value for this SMSRequestService.
     * 
     * @param bursolnum
     */
    public void setBursolnum(java.lang.String bursolnum) {
        this.bursolnum = bursolnum;
    }


    /**
     * Gets the campaign value for this SMSRequestService.
     * 
     * @return campaign
     */
    public java.lang.String getCampaign() {
        return campaign;
    }


    /**
     * Sets the campaign value for this SMSRequestService.
     * 
     * @param campaign
     */
    public void setCampaign(java.lang.String campaign) {
        this.campaign = campaign;
    }


    /**
     * Gets the emisor_id value for this SMSRequestService.
     * 
     * @return emisor_id
     */
    public java.lang.String getEmisor_id() {
        return emisor_id;
    }


    /**
     * Sets the emisor_id value for this SMSRequestService.
     * 
     * @param emisor_id
     */
    public void setEmisor_id(java.lang.String emisor_id) {
        this.emisor_id = emisor_id;
    }


    /**
     * Gets the event_id value for this SMSRequestService.
     * 
     * @return event_id
     */
    public java.lang.String getEvent_id() {
        return event_id;
    }


    /**
     * Sets the event_id value for this SMSRequestService.
     * 
     * @param event_id
     */
    public void setEvent_id(java.lang.String event_id) {
        this.event_id = event_id;
    }


    /**
     * Gets the message value for this SMSRequestService.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SMSRequestService.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the prospectus_id value for this SMSRequestService.
     * 
     * @return prospectus_id
     */
    public java.lang.String[] getProspectus_id() {
        return prospectus_id;
    }


    /**
     * Sets the prospectus_id value for this SMSRequestService.
     * 
     * @param prospectus_id
     */
    public void setProspectus_id(java.lang.String[] prospectus_id) {
        this.prospectus_id = prospectus_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSRequestService)) return false;
        SMSRequestService other = (SMSRequestService) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bursolnum==null && other.getBursolnum()==null) || 
             (this.bursolnum!=null &&
              this.bursolnum.equals(other.getBursolnum()))) &&
            ((this.campaign==null && other.getCampaign()==null) || 
             (this.campaign!=null &&
              this.campaign.equals(other.getCampaign()))) &&
            ((this.emisor_id==null && other.getEmisor_id()==null) || 
             (this.emisor_id!=null &&
              this.emisor_id.equals(other.getEmisor_id()))) &&
            ((this.event_id==null && other.getEvent_id()==null) || 
             (this.event_id!=null &&
              this.event_id.equals(other.getEvent_id()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.prospectus_id==null && other.getProspectus_id()==null) || 
             (this.prospectus_id!=null &&
              java.util.Arrays.equals(this.prospectus_id, other.getProspectus_id())));
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
        if (getBursolnum() != null) {
            _hashCode += getBursolnum().hashCode();
        }
        if (getCampaign() != null) {
            _hashCode += getCampaign().hashCode();
        }
        if (getEmisor_id() != null) {
            _hashCode += getEmisor_id().hashCode();
        }
        if (getEvent_id() != null) {
            _hashCode += getEvent_id().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getProspectus_id() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProspectus_id());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProspectus_id(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSRequestService.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "SMSRequestService"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bursolnum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "bursolnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campaign");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "campaign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emisor_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "emisor_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("event_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "event_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectus_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "item"));
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
