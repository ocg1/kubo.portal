/**
 * PhoneResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class PhoneResponse  implements java.io.Serializable {
    private java.lang.String area;

    private java.lang.String business_id;

    private java.lang.String employment_id;

    private java.lang.String extension;

    private java.lang.String owned;

    private java.lang.String phone_number;

    private java.lang.String phone_type_id;

    private java.lang.String phone_type_name;

    public PhoneResponse() {
    }

    public PhoneResponse(
           java.lang.String area,
           java.lang.String business_id,
           java.lang.String employment_id,
           java.lang.String extension,
           java.lang.String owned,
           java.lang.String phone_number,
           java.lang.String phone_type_id,
           java.lang.String phone_type_name) {
           this.area = area;
           this.business_id = business_id;
           this.employment_id = employment_id;
           this.extension = extension;
           this.owned = owned;
           this.phone_number = phone_number;
           this.phone_type_id = phone_type_id;
           this.phone_type_name = phone_type_name;
    }


    /**
     * Gets the area value for this PhoneResponse.
     * 
     * @return area
     */
    public java.lang.String getArea() {
        return area;
    }


    /**
     * Sets the area value for this PhoneResponse.
     * 
     * @param area
     */
    public void setArea(java.lang.String area) {
        this.area = area;
    }


    /**
     * Gets the business_id value for this PhoneResponse.
     * 
     * @return business_id
     */
    public java.lang.String getBusiness_id() {
        return business_id;
    }


    /**
     * Sets the business_id value for this PhoneResponse.
     * 
     * @param business_id
     */
    public void setBusiness_id(java.lang.String business_id) {
        this.business_id = business_id;
    }


    /**
     * Gets the employment_id value for this PhoneResponse.
     * 
     * @return employment_id
     */
    public java.lang.String getEmployment_id() {
        return employment_id;
    }


    /**
     * Sets the employment_id value for this PhoneResponse.
     * 
     * @param employment_id
     */
    public void setEmployment_id(java.lang.String employment_id) {
        this.employment_id = employment_id;
    }


    /**
     * Gets the extension value for this PhoneResponse.
     * 
     * @return extension
     */
    public java.lang.String getExtension() {
        return extension;
    }


    /**
     * Sets the extension value for this PhoneResponse.
     * 
     * @param extension
     */
    public void setExtension(java.lang.String extension) {
        this.extension = extension;
    }


    /**
     * Gets the owned value for this PhoneResponse.
     * 
     * @return owned
     */
    public java.lang.String getOwned() {
        return owned;
    }


    /**
     * Sets the owned value for this PhoneResponse.
     * 
     * @param owned
     */
    public void setOwned(java.lang.String owned) {
        this.owned = owned;
    }


    /**
     * Gets the phone_number value for this PhoneResponse.
     * 
     * @return phone_number
     */
    public java.lang.String getPhone_number() {
        return phone_number;
    }


    /**
     * Sets the phone_number value for this PhoneResponse.
     * 
     * @param phone_number
     */
    public void setPhone_number(java.lang.String phone_number) {
        this.phone_number = phone_number;
    }


    /**
     * Gets the phone_type_id value for this PhoneResponse.
     * 
     * @return phone_type_id
     */
    public java.lang.String getPhone_type_id() {
        return phone_type_id;
    }


    /**
     * Sets the phone_type_id value for this PhoneResponse.
     * 
     * @param phone_type_id
     */
    public void setPhone_type_id(java.lang.String phone_type_id) {
        this.phone_type_id = phone_type_id;
    }


    /**
     * Gets the phone_type_name value for this PhoneResponse.
     * 
     * @return phone_type_name
     */
    public java.lang.String getPhone_type_name() {
        return phone_type_name;
    }


    /**
     * Sets the phone_type_name value for this PhoneResponse.
     * 
     * @param phone_type_name
     */
    public void setPhone_type_name(java.lang.String phone_type_name) {
        this.phone_type_name = phone_type_name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PhoneResponse)) return false;
        PhoneResponse other = (PhoneResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.area==null && other.getArea()==null) || 
             (this.area!=null &&
              this.area.equals(other.getArea()))) &&
            ((this.business_id==null && other.getBusiness_id()==null) || 
             (this.business_id!=null &&
              this.business_id.equals(other.getBusiness_id()))) &&
            ((this.employment_id==null && other.getEmployment_id()==null) || 
             (this.employment_id!=null &&
              this.employment_id.equals(other.getEmployment_id()))) &&
            ((this.extension==null && other.getExtension()==null) || 
             (this.extension!=null &&
              this.extension.equals(other.getExtension()))) &&
            ((this.owned==null && other.getOwned()==null) || 
             (this.owned!=null &&
              this.owned.equals(other.getOwned()))) &&
            ((this.phone_number==null && other.getPhone_number()==null) || 
             (this.phone_number!=null &&
              this.phone_number.equals(other.getPhone_number()))) &&
            ((this.phone_type_id==null && other.getPhone_type_id()==null) || 
             (this.phone_type_id!=null &&
              this.phone_type_id.equals(other.getPhone_type_id()))) &&
            ((this.phone_type_name==null && other.getPhone_type_name()==null) || 
             (this.phone_type_name!=null &&
              this.phone_type_name.equals(other.getPhone_type_name())));
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
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        if (getBusiness_id() != null) {
            _hashCode += getBusiness_id().hashCode();
        }
        if (getEmployment_id() != null) {
            _hashCode += getEmployment_id().hashCode();
        }
        if (getExtension() != null) {
            _hashCode += getExtension().hashCode();
        }
        if (getOwned() != null) {
            _hashCode += getOwned().hashCode();
        }
        if (getPhone_number() != null) {
            _hashCode += getPhone_number().hashCode();
        }
        if (getPhone_type_id() != null) {
            _hashCode += getPhone_type_id().hashCode();
        }
        if (getPhone_type_name() != null) {
            _hashCode += getPhone_type_name().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PhoneResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "PhoneResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("business_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "business_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "employment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owned");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "owned"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone_number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "phone_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "phone_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone_type_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "phone_type_name"));
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
