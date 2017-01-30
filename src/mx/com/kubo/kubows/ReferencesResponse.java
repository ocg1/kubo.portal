/**
 * ReferencesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class ReferencesResponse  implements java.io.Serializable {
    private java.lang.String company_id;

    private java.lang.String email;

    private java.lang.String father_last_name;

    private java.lang.String first_name;

    private java.lang.String middle_name;

    private java.lang.String mother_last_name;

    private java.lang.String phone;

    private java.lang.String prospectus_id;

    private java.lang.String reference_id;

    private java.lang.String relationship;

    public ReferencesResponse() {
    }

    public ReferencesResponse(
           java.lang.String company_id,
           java.lang.String email,
           java.lang.String father_last_name,
           java.lang.String first_name,
           java.lang.String middle_name,
           java.lang.String mother_last_name,
           java.lang.String phone,
           java.lang.String prospectus_id,
           java.lang.String reference_id,
           java.lang.String relationship) {
           this.company_id = company_id;
           this.email = email;
           this.father_last_name = father_last_name;
           this.first_name = first_name;
           this.middle_name = middle_name;
           this.mother_last_name = mother_last_name;
           this.phone = phone;
           this.prospectus_id = prospectus_id;
           this.reference_id = reference_id;
           this.relationship = relationship;
    }


    /**
     * Gets the company_id value for this ReferencesResponse.
     * 
     * @return company_id
     */
    public java.lang.String getCompany_id() {
        return company_id;
    }


    /**
     * Sets the company_id value for this ReferencesResponse.
     * 
     * @param company_id
     */
    public void setCompany_id(java.lang.String company_id) {
        this.company_id = company_id;
    }


    /**
     * Gets the email value for this ReferencesResponse.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this ReferencesResponse.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the father_last_name value for this ReferencesResponse.
     * 
     * @return father_last_name
     */
    public java.lang.String getFather_last_name() {
        return father_last_name;
    }


    /**
     * Sets the father_last_name value for this ReferencesResponse.
     * 
     * @param father_last_name
     */
    public void setFather_last_name(java.lang.String father_last_name) {
        this.father_last_name = father_last_name;
    }


    /**
     * Gets the first_name value for this ReferencesResponse.
     * 
     * @return first_name
     */
    public java.lang.String getFirst_name() {
        return first_name;
    }


    /**
     * Sets the first_name value for this ReferencesResponse.
     * 
     * @param first_name
     */
    public void setFirst_name(java.lang.String first_name) {
        this.first_name = first_name;
    }


    /**
     * Gets the middle_name value for this ReferencesResponse.
     * 
     * @return middle_name
     */
    public java.lang.String getMiddle_name() {
        return middle_name;
    }


    /**
     * Sets the middle_name value for this ReferencesResponse.
     * 
     * @param middle_name
     */
    public void setMiddle_name(java.lang.String middle_name) {
        this.middle_name = middle_name;
    }


    /**
     * Gets the mother_last_name value for this ReferencesResponse.
     * 
     * @return mother_last_name
     */
    public java.lang.String getMother_last_name() {
        return mother_last_name;
    }


    /**
     * Sets the mother_last_name value for this ReferencesResponse.
     * 
     * @param mother_last_name
     */
    public void setMother_last_name(java.lang.String mother_last_name) {
        this.mother_last_name = mother_last_name;
    }


    /**
     * Gets the phone value for this ReferencesResponse.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this ReferencesResponse.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the prospectus_id value for this ReferencesResponse.
     * 
     * @return prospectus_id
     */
    public java.lang.String getProspectus_id() {
        return prospectus_id;
    }


    /**
     * Sets the prospectus_id value for this ReferencesResponse.
     * 
     * @param prospectus_id
     */
    public void setProspectus_id(java.lang.String prospectus_id) {
        this.prospectus_id = prospectus_id;
    }


    /**
     * Gets the reference_id value for this ReferencesResponse.
     * 
     * @return reference_id
     */
    public java.lang.String getReference_id() {
        return reference_id;
    }


    /**
     * Sets the reference_id value for this ReferencesResponse.
     * 
     * @param reference_id
     */
    public void setReference_id(java.lang.String reference_id) {
        this.reference_id = reference_id;
    }


    /**
     * Gets the relationship value for this ReferencesResponse.
     * 
     * @return relationship
     */
    public java.lang.String getRelationship() {
        return relationship;
    }


    /**
     * Sets the relationship value for this ReferencesResponse.
     * 
     * @param relationship
     */
    public void setRelationship(java.lang.String relationship) {
        this.relationship = relationship;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReferencesResponse)) return false;
        ReferencesResponse other = (ReferencesResponse) obj;
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
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.father_last_name==null && other.getFather_last_name()==null) || 
             (this.father_last_name!=null &&
              this.father_last_name.equals(other.getFather_last_name()))) &&
            ((this.first_name==null && other.getFirst_name()==null) || 
             (this.first_name!=null &&
              this.first_name.equals(other.getFirst_name()))) &&
            ((this.middle_name==null && other.getMiddle_name()==null) || 
             (this.middle_name!=null &&
              this.middle_name.equals(other.getMiddle_name()))) &&
            ((this.mother_last_name==null && other.getMother_last_name()==null) || 
             (this.mother_last_name!=null &&
              this.mother_last_name.equals(other.getMother_last_name()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.prospectus_id==null && other.getProspectus_id()==null) || 
             (this.prospectus_id!=null &&
              this.prospectus_id.equals(other.getProspectus_id()))) &&
            ((this.reference_id==null && other.getReference_id()==null) || 
             (this.reference_id!=null &&
              this.reference_id.equals(other.getReference_id()))) &&
            ((this.relationship==null && other.getRelationship()==null) || 
             (this.relationship!=null &&
              this.relationship.equals(other.getRelationship())));
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
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFather_last_name() != null) {
            _hashCode += getFather_last_name().hashCode();
        }
        if (getFirst_name() != null) {
            _hashCode += getFirst_name().hashCode();
        }
        if (getMiddle_name() != null) {
            _hashCode += getMiddle_name().hashCode();
        }
        if (getMother_last_name() != null) {
            _hashCode += getMother_last_name().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getProspectus_id() != null) {
            _hashCode += getProspectus_id().hashCode();
        }
        if (getReference_id() != null) {
            _hashCode += getReference_id().hashCode();
        }
        if (getRelationship() != null) {
            _hashCode += getRelationship().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReferencesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ReferencesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "company_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("father_last_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "father_last_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "first_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middle_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "middle_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mother_last_name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "mother_last_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "phone"));
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
        elemField.setFieldName("reference_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "reference_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "relationship"));
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
