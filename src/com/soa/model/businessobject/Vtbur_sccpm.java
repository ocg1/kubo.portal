/**
 * Vtbur_sccpm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class Vtbur_sccpm  implements java.io.Serializable {
    private java.lang.String bursol;

    private java.lang.String cliProsFlag;

    private java.lang.String clientId;

    private java.lang.String grantor;

    private java.lang.String months;

    private java.lang.String mopDesc;

    private java.lang.String prospectId;

    private java.lang.String regId;

    public Vtbur_sccpm() {
    }

    public Vtbur_sccpm(
           java.lang.String bursol,
           java.lang.String cliProsFlag,
           java.lang.String clientId,
           java.lang.String grantor,
           java.lang.String months,
           java.lang.String mopDesc,
           java.lang.String prospectId,
           java.lang.String regId) {
           this.bursol = bursol;
           this.cliProsFlag = cliProsFlag;
           this.clientId = clientId;
           this.grantor = grantor;
           this.months = months;
           this.mopDesc = mopDesc;
           this.prospectId = prospectId;
           this.regId = regId;
    }


    /**
     * Gets the bursol value for this Vtbur_sccpm.
     * 
     * @return bursol
     */
    public java.lang.String getBursol() {
        return bursol;
    }


    /**
     * Sets the bursol value for this Vtbur_sccpm.
     * 
     * @param bursol
     */
    public void setBursol(java.lang.String bursol) {
        this.bursol = bursol;
    }


    /**
     * Gets the cliProsFlag value for this Vtbur_sccpm.
     * 
     * @return cliProsFlag
     */
    public java.lang.String getCliProsFlag() {
        return cliProsFlag;
    }


    /**
     * Sets the cliProsFlag value for this Vtbur_sccpm.
     * 
     * @param cliProsFlag
     */
    public void setCliProsFlag(java.lang.String cliProsFlag) {
        this.cliProsFlag = cliProsFlag;
    }


    /**
     * Gets the clientId value for this Vtbur_sccpm.
     * 
     * @return clientId
     */
    public java.lang.String getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Vtbur_sccpm.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the grantor value for this Vtbur_sccpm.
     * 
     * @return grantor
     */
    public java.lang.String getGrantor() {
        return grantor;
    }


    /**
     * Sets the grantor value for this Vtbur_sccpm.
     * 
     * @param grantor
     */
    public void setGrantor(java.lang.String grantor) {
        this.grantor = grantor;
    }


    /**
     * Gets the months value for this Vtbur_sccpm.
     * 
     * @return months
     */
    public java.lang.String getMonths() {
        return months;
    }


    /**
     * Sets the months value for this Vtbur_sccpm.
     * 
     * @param months
     */
    public void setMonths(java.lang.String months) {
        this.months = months;
    }


    /**
     * Gets the mopDesc value for this Vtbur_sccpm.
     * 
     * @return mopDesc
     */
    public java.lang.String getMopDesc() {
        return mopDesc;
    }


    /**
     * Sets the mopDesc value for this Vtbur_sccpm.
     * 
     * @param mopDesc
     */
    public void setMopDesc(java.lang.String mopDesc) {
        this.mopDesc = mopDesc;
    }


    /**
     * Gets the prospectId value for this Vtbur_sccpm.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this Vtbur_sccpm.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }


    /**
     * Gets the regId value for this Vtbur_sccpm.
     * 
     * @return regId
     */
    public java.lang.String getRegId() {
        return regId;
    }


    /**
     * Sets the regId value for this Vtbur_sccpm.
     * 
     * @param regId
     */
    public void setRegId(java.lang.String regId) {
        this.regId = regId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Vtbur_sccpm)) return false;
        Vtbur_sccpm other = (Vtbur_sccpm) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bursol==null && other.getBursol()==null) || 
             (this.bursol!=null &&
              this.bursol.equals(other.getBursol()))) &&
            ((this.cliProsFlag==null && other.getCliProsFlag()==null) || 
             (this.cliProsFlag!=null &&
              this.cliProsFlag.equals(other.getCliProsFlag()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.grantor==null && other.getGrantor()==null) || 
             (this.grantor!=null &&
              this.grantor.equals(other.getGrantor()))) &&
            ((this.months==null && other.getMonths()==null) || 
             (this.months!=null &&
              this.months.equals(other.getMonths()))) &&
            ((this.mopDesc==null && other.getMopDesc()==null) || 
             (this.mopDesc!=null &&
              this.mopDesc.equals(other.getMopDesc()))) &&
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
        if (getBursol() != null) {
            _hashCode += getBursol().hashCode();
        }
        if (getCliProsFlag() != null) {
            _hashCode += getCliProsFlag().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getGrantor() != null) {
            _hashCode += getGrantor().hashCode();
        }
        if (getMonths() != null) {
            _hashCode += getMonths().hashCode();
        }
        if (getMopDesc() != null) {
            _hashCode += getMopDesc().hashCode();
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
        new org.apache.axis.description.TypeDesc(Vtbur_sccpm.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccpm"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("grantor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "grantor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("months");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "months"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mopDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "mopDesc"));
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
