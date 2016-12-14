/**
 * ReprocessBuroDataDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mx.kubo.sgbws.models.dto;

public class ReprocessBuroDataDTO  implements java.io.Serializable {
    private java.lang.String age;

    private java.lang.String businessType;

    private java.lang.String companyId;

    private java.lang.String genderId;

    private java.lang.String mxSolicitudBuro;

    private java.lang.String mxTipoConsultaBuro;

    private java.lang.String prospectusId;

    private java.lang.String prospectusIdPromotor;

    private java.lang.String residenceId;

    public ReprocessBuroDataDTO() {
    }

    public ReprocessBuroDataDTO(
           java.lang.String age,
           java.lang.String businessType,
           java.lang.String companyId,
           java.lang.String genderId,
           java.lang.String mxSolicitudBuro,
           java.lang.String mxTipoConsultaBuro,
           java.lang.String prospectusId,
           java.lang.String prospectusIdPromotor,
           java.lang.String residenceId) {
           this.age = age;
           this.businessType = businessType;
           this.companyId = companyId;
           this.genderId = genderId;
           this.mxSolicitudBuro = mxSolicitudBuro;
           this.mxTipoConsultaBuro = mxTipoConsultaBuro;
           this.prospectusId = prospectusId;
           this.prospectusIdPromotor = prospectusIdPromotor;
           this.residenceId = residenceId;
    }


    /**
     * Gets the age value for this ReprocessBuroDataDTO.
     * 
     * @return age
     */
    public java.lang.String getAge() {
        return age;
    }


    /**
     * Sets the age value for this ReprocessBuroDataDTO.
     * 
     * @param age
     */
    public void setAge(java.lang.String age) {
        this.age = age;
    }


    /**
     * Gets the businessType value for this ReprocessBuroDataDTO.
     * 
     * @return businessType
     */
    public java.lang.String getBusinessType() {
        return businessType;
    }


    /**
     * Sets the businessType value for this ReprocessBuroDataDTO.
     * 
     * @param businessType
     */
    public void setBusinessType(java.lang.String businessType) {
        this.businessType = businessType;
    }


    /**
     * Gets the companyId value for this ReprocessBuroDataDTO.
     * 
     * @return companyId
     */
    public java.lang.String getCompanyId() {
        return companyId;
    }


    /**
     * Sets the companyId value for this ReprocessBuroDataDTO.
     * 
     * @param companyId
     */
    public void setCompanyId(java.lang.String companyId) {
        this.companyId = companyId;
    }


    /**
     * Gets the genderId value for this ReprocessBuroDataDTO.
     * 
     * @return genderId
     */
    public java.lang.String getGenderId() {
        return genderId;
    }


    /**
     * Sets the genderId value for this ReprocessBuroDataDTO.
     * 
     * @param genderId
     */
    public void setGenderId(java.lang.String genderId) {
        this.genderId = genderId;
    }


    /**
     * Gets the mxSolicitudBuro value for this ReprocessBuroDataDTO.
     * 
     * @return mxSolicitudBuro
     */
    public java.lang.String getMxSolicitudBuro() {
        return mxSolicitudBuro;
    }


    /**
     * Sets the mxSolicitudBuro value for this ReprocessBuroDataDTO.
     * 
     * @param mxSolicitudBuro
     */
    public void setMxSolicitudBuro(java.lang.String mxSolicitudBuro) {
        this.mxSolicitudBuro = mxSolicitudBuro;
    }


    /**
     * Gets the mxTipoConsultaBuro value for this ReprocessBuroDataDTO.
     * 
     * @return mxTipoConsultaBuro
     */
    public java.lang.String getMxTipoConsultaBuro() {
        return mxTipoConsultaBuro;
    }


    /**
     * Sets the mxTipoConsultaBuro value for this ReprocessBuroDataDTO.
     * 
     * @param mxTipoConsultaBuro
     */
    public void setMxTipoConsultaBuro(java.lang.String mxTipoConsultaBuro) {
        this.mxTipoConsultaBuro = mxTipoConsultaBuro;
    }


    /**
     * Gets the prospectusId value for this ReprocessBuroDataDTO.
     * 
     * @return prospectusId
     */
    public java.lang.String getProspectusId() {
        return prospectusId;
    }


    /**
     * Sets the prospectusId value for this ReprocessBuroDataDTO.
     * 
     * @param prospectusId
     */
    public void setProspectusId(java.lang.String prospectusId) {
        this.prospectusId = prospectusId;
    }


    /**
     * Gets the prospectusIdPromotor value for this ReprocessBuroDataDTO.
     * 
     * @return prospectusIdPromotor
     */
    public java.lang.String getProspectusIdPromotor() {
        return prospectusIdPromotor;
    }


    /**
     * Sets the prospectusIdPromotor value for this ReprocessBuroDataDTO.
     * 
     * @param prospectusIdPromotor
     */
    public void setProspectusIdPromotor(java.lang.String prospectusIdPromotor) {
        this.prospectusIdPromotor = prospectusIdPromotor;
    }


    /**
     * Gets the residenceId value for this ReprocessBuroDataDTO.
     * 
     * @return residenceId
     */
    public java.lang.String getResidenceId() {
        return residenceId;
    }


    /**
     * Sets the residenceId value for this ReprocessBuroDataDTO.
     * 
     * @param residenceId
     */
    public void setResidenceId(java.lang.String residenceId) {
        this.residenceId = residenceId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReprocessBuroDataDTO)) return false;
        ReprocessBuroDataDTO other = (ReprocessBuroDataDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.age==null && other.getAge()==null) || 
             (this.age!=null &&
              this.age.equals(other.getAge()))) &&
            ((this.businessType==null && other.getBusinessType()==null) || 
             (this.businessType!=null &&
              this.businessType.equals(other.getBusinessType()))) &&
            ((this.companyId==null && other.getCompanyId()==null) || 
             (this.companyId!=null &&
              this.companyId.equals(other.getCompanyId()))) &&
            ((this.genderId==null && other.getGenderId()==null) || 
             (this.genderId!=null &&
              this.genderId.equals(other.getGenderId()))) &&
            ((this.mxSolicitudBuro==null && other.getMxSolicitudBuro()==null) || 
             (this.mxSolicitudBuro!=null &&
              this.mxSolicitudBuro.equals(other.getMxSolicitudBuro()))) &&
            ((this.mxTipoConsultaBuro==null && other.getMxTipoConsultaBuro()==null) || 
             (this.mxTipoConsultaBuro!=null &&
              this.mxTipoConsultaBuro.equals(other.getMxTipoConsultaBuro()))) &&
            ((this.prospectusId==null && other.getProspectusId()==null) || 
             (this.prospectusId!=null &&
              this.prospectusId.equals(other.getProspectusId()))) &&
            ((this.prospectusIdPromotor==null && other.getProspectusIdPromotor()==null) || 
             (this.prospectusIdPromotor!=null &&
              this.prospectusIdPromotor.equals(other.getProspectusIdPromotor()))) &&
            ((this.residenceId==null && other.getResidenceId()==null) || 
             (this.residenceId!=null &&
              this.residenceId.equals(other.getResidenceId())));
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
        if (getAge() != null) {
            _hashCode += getAge().hashCode();
        }
        if (getBusinessType() != null) {
            _hashCode += getBusinessType().hashCode();
        }
        if (getCompanyId() != null) {
            _hashCode += getCompanyId().hashCode();
        }
        if (getGenderId() != null) {
            _hashCode += getGenderId().hashCode();
        }
        if (getMxSolicitudBuro() != null) {
            _hashCode += getMxSolicitudBuro().hashCode();
        }
        if (getMxTipoConsultaBuro() != null) {
            _hashCode += getMxTipoConsultaBuro().hashCode();
        }
        if (getProspectusId() != null) {
            _hashCode += getProspectusId().hashCode();
        }
        if (getProspectusIdPromotor() != null) {
            _hashCode += getProspectusIdPromotor().hashCode();
        }
        if (getResidenceId() != null) {
            _hashCode += getResidenceId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReprocessBuroDataDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "ReprocessBuroDataDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "age"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "businessType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "companyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genderId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "genderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mxSolicitudBuro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "mxSolicitudBuro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mxTipoConsultaBuro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "mxTipoConsultaBuro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectusId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "prospectusId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectusIdPromotor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "prospectusIdPromotor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("residenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.models.sgbws.kubo.mx.com", "residenceId"));
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
