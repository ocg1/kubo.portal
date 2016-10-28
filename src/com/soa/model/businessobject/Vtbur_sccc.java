/**
 * Vtbur_sccc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class Vtbur_sccc  implements java.io.Serializable {
    private java.lang.String acountType;

    private java.lang.String aggregateLimit;

    private java.lang.String bursol;

    private java.lang.String cliProsFlag;

    private java.lang.String clientId;

    private java.lang.String contractType;

    private java.lang.String credits;

    private java.lang.String grantor;

    private java.lang.String prospectId;

    private java.lang.String regId;

    public Vtbur_sccc() {
    }

    public Vtbur_sccc(
           java.lang.String acountType,
           java.lang.String aggregateLimit,
           java.lang.String bursol,
           java.lang.String cliProsFlag,
           java.lang.String clientId,
           java.lang.String contractType,
           java.lang.String credits,
           java.lang.String grantor,
           java.lang.String prospectId,
           java.lang.String regId) {
           this.acountType = acountType;
           this.aggregateLimit = aggregateLimit;
           this.bursol = bursol;
           this.cliProsFlag = cliProsFlag;
           this.clientId = clientId;
           this.contractType = contractType;
           this.credits = credits;
           this.grantor = grantor;
           this.prospectId = prospectId;
           this.regId = regId;
    }


    /**
     * Gets the acountType value for this Vtbur_sccc.
     * 
     * @return acountType
     */
    public java.lang.String getAcountType() {
        return acountType;
    }


    /**
     * Sets the acountType value for this Vtbur_sccc.
     * 
     * @param acountType
     */
    public void setAcountType(java.lang.String acountType) {
        this.acountType = acountType;
    }


    /**
     * Gets the aggregateLimit value for this Vtbur_sccc.
     * 
     * @return aggregateLimit
     */
    public java.lang.String getAggregateLimit() {
        return aggregateLimit;
    }


    /**
     * Sets the aggregateLimit value for this Vtbur_sccc.
     * 
     * @param aggregateLimit
     */
    public void setAggregateLimit(java.lang.String aggregateLimit) {
        this.aggregateLimit = aggregateLimit;
    }


    /**
     * Gets the bursol value for this Vtbur_sccc.
     * 
     * @return bursol
     */
    public java.lang.String getBursol() {
        return bursol;
    }


    /**
     * Sets the bursol value for this Vtbur_sccc.
     * 
     * @param bursol
     */
    public void setBursol(java.lang.String bursol) {
        this.bursol = bursol;
    }


    /**
     * Gets the cliProsFlag value for this Vtbur_sccc.
     * 
     * @return cliProsFlag
     */
    public java.lang.String getCliProsFlag() {
        return cliProsFlag;
    }


    /**
     * Sets the cliProsFlag value for this Vtbur_sccc.
     * 
     * @param cliProsFlag
     */
    public void setCliProsFlag(java.lang.String cliProsFlag) {
        this.cliProsFlag = cliProsFlag;
    }


    /**
     * Gets the clientId value for this Vtbur_sccc.
     * 
     * @return clientId
     */
    public java.lang.String getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Vtbur_sccc.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the contractType value for this Vtbur_sccc.
     * 
     * @return contractType
     */
    public java.lang.String getContractType() {
        return contractType;
    }


    /**
     * Sets the contractType value for this Vtbur_sccc.
     * 
     * @param contractType
     */
    public void setContractType(java.lang.String contractType) {
        this.contractType = contractType;
    }


    /**
     * Gets the credits value for this Vtbur_sccc.
     * 
     * @return credits
     */
    public java.lang.String getCredits() {
        return credits;
    }


    /**
     * Sets the credits value for this Vtbur_sccc.
     * 
     * @param credits
     */
    public void setCredits(java.lang.String credits) {
        this.credits = credits;
    }


    /**
     * Gets the grantor value for this Vtbur_sccc.
     * 
     * @return grantor
     */
    public java.lang.String getGrantor() {
        return grantor;
    }


    /**
     * Sets the grantor value for this Vtbur_sccc.
     * 
     * @param grantor
     */
    public void setGrantor(java.lang.String grantor) {
        this.grantor = grantor;
    }


    /**
     * Gets the prospectId value for this Vtbur_sccc.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this Vtbur_sccc.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }


    /**
     * Gets the regId value for this Vtbur_sccc.
     * 
     * @return regId
     */
    public java.lang.String getRegId() {
        return regId;
    }


    /**
     * Sets the regId value for this Vtbur_sccc.
     * 
     * @param regId
     */
    public void setRegId(java.lang.String regId) {
        this.regId = regId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Vtbur_sccc)) return false;
        Vtbur_sccc other = (Vtbur_sccc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acountType==null && other.getAcountType()==null) || 
             (this.acountType!=null &&
              this.acountType.equals(other.getAcountType()))) &&
            ((this.aggregateLimit==null && other.getAggregateLimit()==null) || 
             (this.aggregateLimit!=null &&
              this.aggregateLimit.equals(other.getAggregateLimit()))) &&
            ((this.bursol==null && other.getBursol()==null) || 
             (this.bursol!=null &&
              this.bursol.equals(other.getBursol()))) &&
            ((this.cliProsFlag==null && other.getCliProsFlag()==null) || 
             (this.cliProsFlag!=null &&
              this.cliProsFlag.equals(other.getCliProsFlag()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.contractType==null && other.getContractType()==null) || 
             (this.contractType!=null &&
              this.contractType.equals(other.getContractType()))) &&
            ((this.credits==null && other.getCredits()==null) || 
             (this.credits!=null &&
              this.credits.equals(other.getCredits()))) &&
            ((this.grantor==null && other.getGrantor()==null) || 
             (this.grantor!=null &&
              this.grantor.equals(other.getGrantor()))) &&
            ((this.prospectId==null && other.getProspectId()==null) || 
             (this.prospectId!=null &&
              this.prospectId.equals(other.getProspectId()))) &&
            ((this.regId==null && other.getRegId()==null) || 
             (this.regId!=null &&
              this.regId.equals(other.getRegId())));
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
        if (getAcountType() != null) {
            _hashCode += getAcountType().hashCode();
        }
        if (getAggregateLimit() != null) {
            _hashCode += getAggregateLimit().hashCode();
        }
        if (getBursol() != null) {
            _hashCode += getBursol().hashCode();
        }
        if (getCliProsFlag() != null) {
            _hashCode += getCliProsFlag().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getContractType() != null) {
            _hashCode += getContractType().hashCode();
        }
        if (getCredits() != null) {
            _hashCode += getCredits().hashCode();
        }
        if (getGrantor() != null) {
            _hashCode += getGrantor().hashCode();
        }
        if (getProspectId() != null) {
            _hashCode += getProspectId().hashCode();
        }
        if (getRegId() != null) {
            _hashCode += getRegId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Vtbur_sccc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "acountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aggregateLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "aggregateLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bursol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "bursol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliProsFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "cliProsFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "clientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "contractType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credits");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grantor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "grantor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "prospectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "regId"));
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
