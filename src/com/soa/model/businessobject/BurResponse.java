/**
 * BurResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class BurResponse  implements java.io.Serializable {
    private java.lang.String burDate;

    private java.lang.String burFol;

    private java.lang.String burScore;

    private java.lang.String cliProFlag;

    private java.lang.String clientId;

    private java.lang.String icc;

    private java.lang.String message;

    private java.lang.String prospectId;

    private java.lang.String solNum;

    private java.lang.String status;

    private java.lang.String tipoConsulta;

    public BurResponse() {
    }

    public BurResponse(
           java.lang.String burDate,
           java.lang.String burFol,
           java.lang.String burScore,
           java.lang.String cliProFlag,
           java.lang.String clientId,
           java.lang.String icc,
           java.lang.String message,
           java.lang.String prospectId,
           java.lang.String solNum,
           java.lang.String status,
           java.lang.String tipoConsulta) {
           this.burDate = burDate;
           this.burFol = burFol;
           this.burScore = burScore;
           this.cliProFlag = cliProFlag;
           this.clientId = clientId;
           this.icc = icc;
           this.message = message;
           this.prospectId = prospectId;
           this.solNum = solNum;
           this.status = status;
           this.tipoConsulta = tipoConsulta;
    }


    /**
     * Gets the burDate value for this BurResponse.
     * 
     * @return burDate
     */
    public java.lang.String getBurDate() {
        return burDate;
    }


    /**
     * Sets the burDate value for this BurResponse.
     * 
     * @param burDate
     */
    public void setBurDate(java.lang.String burDate) {
        this.burDate = burDate;
    }


    /**
     * Gets the burFol value for this BurResponse.
     * 
     * @return burFol
     */
    public java.lang.String getBurFol() {
        return burFol;
    }


    /**
     * Sets the burFol value for this BurResponse.
     * 
     * @param burFol
     */
    public void setBurFol(java.lang.String burFol) {
        this.burFol = burFol;
    }


    /**
     * Gets the burScore value for this BurResponse.
     * 
     * @return burScore
     */
    public java.lang.String getBurScore() {
        return burScore;
    }


    /**
     * Sets the burScore value for this BurResponse.
     * 
     * @param burScore
     */
    public void setBurScore(java.lang.String burScore) {
        this.burScore = burScore;
    }


    /**
     * Gets the cliProFlag value for this BurResponse.
     * 
     * @return cliProFlag
     */
    public java.lang.String getCliProFlag() {
        return cliProFlag;
    }


    /**
     * Sets the cliProFlag value for this BurResponse.
     * 
     * @param cliProFlag
     */
    public void setCliProFlag(java.lang.String cliProFlag) {
        this.cliProFlag = cliProFlag;
    }


    /**
     * Gets the clientId value for this BurResponse.
     * 
     * @return clientId
     */
    public java.lang.String getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this BurResponse.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the icc value for this BurResponse.
     * 
     * @return icc
     */
    public java.lang.String getIcc() {
        return icc;
    }


    /**
     * Sets the icc value for this BurResponse.
     * 
     * @param icc
     */
    public void setIcc(java.lang.String icc) {
        this.icc = icc;
    }


    /**
     * Gets the message value for this BurResponse.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this BurResponse.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the prospectId value for this BurResponse.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this BurResponse.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }


    /**
     * Gets the solNum value for this BurResponse.
     * 
     * @return solNum
     */
    public java.lang.String getSolNum() {
        return solNum;
    }


    /**
     * Sets the solNum value for this BurResponse.
     * 
     * @param solNum
     */
    public void setSolNum(java.lang.String solNum) {
        this.solNum = solNum;
    }


    /**
     * Gets the status value for this BurResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this BurResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the tipoConsulta value for this BurResponse.
     * 
     * @return tipoConsulta
     */
    public java.lang.String getTipoConsulta() {
        return tipoConsulta;
    }


    /**
     * Sets the tipoConsulta value for this BurResponse.
     * 
     * @param tipoConsulta
     */
    public void setTipoConsulta(java.lang.String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BurResponse)) return false;
        BurResponse other = (BurResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.burDate==null && other.getBurDate()==null) || 
             (this.burDate!=null &&
              this.burDate.equals(other.getBurDate()))) &&
            ((this.burFol==null && other.getBurFol()==null) || 
             (this.burFol!=null &&
              this.burFol.equals(other.getBurFol()))) &&
            ((this.burScore==null && other.getBurScore()==null) || 
             (this.burScore!=null &&
              this.burScore.equals(other.getBurScore()))) &&
            ((this.cliProFlag==null && other.getCliProFlag()==null) || 
             (this.cliProFlag!=null &&
              this.cliProFlag.equals(other.getCliProFlag()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.icc==null && other.getIcc()==null) || 
             (this.icc!=null &&
              this.icc.equals(other.getIcc()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.prospectId==null && other.getProspectId()==null) || 
             (this.prospectId!=null &&
              this.prospectId.equals(other.getProspectId()))) &&
            ((this.solNum==null && other.getSolNum()==null) || 
             (this.solNum!=null &&
              this.solNum.equals(other.getSolNum()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.tipoConsulta==null && other.getTipoConsulta()==null) || 
             (this.tipoConsulta!=null &&
              this.tipoConsulta.equals(other.getTipoConsulta())));
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
        if (getBurDate() != null) {
            _hashCode += getBurDate().hashCode();
        }
        if (getBurFol() != null) {
            _hashCode += getBurFol().hashCode();
        }
        if (getBurScore() != null) {
            _hashCode += getBurScore().hashCode();
        }
        if (getCliProFlag() != null) {
            _hashCode += getCliProFlag().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getIcc() != null) {
            _hashCode += getIcc().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getProspectId() != null) {
            _hashCode += getProspectId().hashCode();
        }
        if (getSolNum() != null) {
            _hashCode += getSolNum().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTipoConsulta() != null) {
            _hashCode += getTipoConsulta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BurResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "burDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burFol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "burFol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "burScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliProFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "cliProFlag"));
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
        elemField.setFieldName("icc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "icc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "message"));
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
        elemField.setFieldName("solNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "solNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "tipoConsulta"));
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
