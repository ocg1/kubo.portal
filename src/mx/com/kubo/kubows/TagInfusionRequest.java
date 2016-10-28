/**
 * TagInfusionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class TagInfusionRequest  implements java.io.Serializable {
    private java.lang.String company_id;

    private java.lang.String prospectus_id;

    private java.lang.String tag_id;

    public TagInfusionRequest() {
    }

    public TagInfusionRequest(
           java.lang.String company_id,
           java.lang.String prospectus_id,
           java.lang.String tag_id) {
           this.company_id = company_id;
           this.prospectus_id = prospectus_id;
           this.tag_id = tag_id;
    }


    /**
     * Gets the company_id value for this TagInfusionRequest.
     * 
     * @return company_id
     */
    public java.lang.String getCompany_id() {
        return company_id;
    }


    /**
     * Sets the company_id value for this TagInfusionRequest.
     * 
     * @param company_id
     */
    public void setCompany_id(java.lang.String company_id) {
        this.company_id = company_id;
    }


    /**
     * Gets the prospectus_id value for this TagInfusionRequest.
     * 
     * @return prospectus_id
     */
    public java.lang.String getProspectus_id() {
        return prospectus_id;
    }


    /**
     * Sets the prospectus_id value for this TagInfusionRequest.
     * 
     * @param prospectus_id
     */
    public void setProspectus_id(java.lang.String prospectus_id) {
        this.prospectus_id = prospectus_id;
    }


    /**
     * Gets the tag_id value for this TagInfusionRequest.
     * 
     * @return tag_id
     */
    public java.lang.String getTag_id() {
        return tag_id;
    }


    /**
     * Sets the tag_id value for this TagInfusionRequest.
     * 
     * @param tag_id
     */
    public void setTag_id(java.lang.String tag_id) {
        this.tag_id = tag_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TagInfusionRequest)) return false;
        TagInfusionRequest other = (TagInfusionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.company_id==null && other.getCompany_id()==null) || 
             (this.company_id!=null &&
              this.company_id.equals(other.getCompany_id()))) &&
            ((this.prospectus_id==null && other.getProspectus_id()==null) || 
             (this.prospectus_id!=null &&
              this.prospectus_id.equals(other.getProspectus_id()))) &&
            ((this.tag_id==null && other.getTag_id()==null) || 
             (this.tag_id!=null &&
              this.tag_id.equals(other.getTag_id())));
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
        if (getCompany_id() != null) {
            _hashCode += getCompany_id().hashCode();
        }
        if (getProspectus_id() != null) {
            _hashCode += getProspectus_id().hashCode();
        }
        if (getTag_id() != null) {
            _hashCode += getTag_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TagInfusionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "TagInfusionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectus_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospectus_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tag_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "tag_id"));
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
