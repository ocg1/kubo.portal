/**
 * Logs.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class Logs  implements java.io.Serializable {
    private java.lang.String cliProInd;

    private java.lang.String clientId;

    private java.lang.String dateDate;

    private java.lang.String dateSgb;

    private java.lang.String dateTxt;

    private java.lang.String dateTxt1;

    private java.lang.String dateTxt2;

    private java.lang.String hour;

    private java.lang.String logId;

    private java.lang.String logTypeId;

    private java.lang.String prospectId;

    private java.lang.String text;

    public Logs() {
    }

    public Logs(
           java.lang.String cliProInd,
           java.lang.String clientId,
           java.lang.String dateDate,
           java.lang.String dateSgb,
           java.lang.String dateTxt,
           java.lang.String dateTxt1,
           java.lang.String dateTxt2,
           java.lang.String hour,
           java.lang.String logId,
           java.lang.String logTypeId,
           java.lang.String prospectId,
           java.lang.String text) {
           this.cliProInd = cliProInd;
           this.clientId = clientId;
           this.dateDate = dateDate;
           this.dateSgb = dateSgb;
           this.dateTxt = dateTxt;
           this.dateTxt1 = dateTxt1;
           this.dateTxt2 = dateTxt2;
           this.hour = hour;
           this.logId = logId;
           this.logTypeId = logTypeId;
           this.prospectId = prospectId;
           this.text = text;
    }


    /**
     * Gets the cliProInd value for this Logs.
     * 
     * @return cliProInd
     */
    public java.lang.String getCliProInd() {
        return cliProInd;
    }


    /**
     * Sets the cliProInd value for this Logs.
     * 
     * @param cliProInd
     */
    public void setCliProInd(java.lang.String cliProInd) {
        this.cliProInd = cliProInd;
    }


    /**
     * Gets the clientId value for this Logs.
     * 
     * @return clientId
     */
    public java.lang.String getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Logs.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the dateDate value for this Logs.
     * 
     * @return dateDate
     */
    public java.lang.String getDateDate() {
        return dateDate;
    }


    /**
     * Sets the dateDate value for this Logs.
     * 
     * @param dateDate
     */
    public void setDateDate(java.lang.String dateDate) {
        this.dateDate = dateDate;
    }


    /**
     * Gets the dateSgb value for this Logs.
     * 
     * @return dateSgb
     */
    public java.lang.String getDateSgb() {
        return dateSgb;
    }


    /**
     * Sets the dateSgb value for this Logs.
     * 
     * @param dateSgb
     */
    public void setDateSgb(java.lang.String dateSgb) {
        this.dateSgb = dateSgb;
    }


    /**
     * Gets the dateTxt value for this Logs.
     * 
     * @return dateTxt
     */
    public java.lang.String getDateTxt() {
        return dateTxt;
    }


    /**
     * Sets the dateTxt value for this Logs.
     * 
     * @param dateTxt
     */
    public void setDateTxt(java.lang.String dateTxt) {
        this.dateTxt = dateTxt;
    }


    /**
     * Gets the dateTxt1 value for this Logs.
     * 
     * @return dateTxt1
     */
    public java.lang.String getDateTxt1() {
        return dateTxt1;
    }


    /**
     * Sets the dateTxt1 value for this Logs.
     * 
     * @param dateTxt1
     */
    public void setDateTxt1(java.lang.String dateTxt1) {
        this.dateTxt1 = dateTxt1;
    }


    /**
     * Gets the dateTxt2 value for this Logs.
     * 
     * @return dateTxt2
     */
    public java.lang.String getDateTxt2() {
        return dateTxt2;
    }


    /**
     * Sets the dateTxt2 value for this Logs.
     * 
     * @param dateTxt2
     */
    public void setDateTxt2(java.lang.String dateTxt2) {
        this.dateTxt2 = dateTxt2;
    }


    /**
     * Gets the hour value for this Logs.
     * 
     * @return hour
     */
    public java.lang.String getHour() {
        return hour;
    }


    /**
     * Sets the hour value for this Logs.
     * 
     * @param hour
     */
    public void setHour(java.lang.String hour) {
        this.hour = hour;
    }


    /**
     * Gets the logId value for this Logs.
     * 
     * @return logId
     */
    public java.lang.String getLogId() {
        return logId;
    }


    /**
     * Sets the logId value for this Logs.
     * 
     * @param logId
     */
    public void setLogId(java.lang.String logId) {
        this.logId = logId;
    }


    /**
     * Gets the logTypeId value for this Logs.
     * 
     * @return logTypeId
     */
    public java.lang.String getLogTypeId() {
        return logTypeId;
    }


    /**
     * Sets the logTypeId value for this Logs.
     * 
     * @param logTypeId
     */
    public void setLogTypeId(java.lang.String logTypeId) {
        this.logTypeId = logTypeId;
    }


    /**
     * Gets the prospectId value for this Logs.
     * 
     * @return prospectId
     */
    public java.lang.String getProspectId() {
        return prospectId;
    }


    /**
     * Sets the prospectId value for this Logs.
     * 
     * @param prospectId
     */
    public void setProspectId(java.lang.String prospectId) {
        this.prospectId = prospectId;
    }


    /**
     * Gets the text value for this Logs.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this Logs.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Logs)) return false;
        Logs other = (Logs) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cliProInd==null && other.getCliProInd()==null) || 
             (this.cliProInd!=null &&
              this.cliProInd.equals(other.getCliProInd()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.dateDate==null && other.getDateDate()==null) || 
             (this.dateDate!=null &&
              this.dateDate.equals(other.getDateDate()))) &&
            ((this.dateSgb==null && other.getDateSgb()==null) || 
             (this.dateSgb!=null &&
              this.dateSgb.equals(other.getDateSgb()))) &&
            ((this.dateTxt==null && other.getDateTxt()==null) || 
             (this.dateTxt!=null &&
              this.dateTxt.equals(other.getDateTxt()))) &&
            ((this.dateTxt1==null && other.getDateTxt1()==null) || 
             (this.dateTxt1!=null &&
              this.dateTxt1.equals(other.getDateTxt1()))) &&
            ((this.dateTxt2==null && other.getDateTxt2()==null) || 
             (this.dateTxt2!=null &&
              this.dateTxt2.equals(other.getDateTxt2()))) &&
            ((this.hour==null && other.getHour()==null) || 
             (this.hour!=null &&
              this.hour.equals(other.getHour()))) &&
            ((this.logId==null && other.getLogId()==null) || 
             (this.logId!=null &&
              this.logId.equals(other.getLogId()))) &&
            ((this.logTypeId==null && other.getLogTypeId()==null) || 
             (this.logTypeId!=null &&
              this.logTypeId.equals(other.getLogTypeId()))) &&
            ((this.prospectId==null && other.getProspectId()==null) || 
             (this.prospectId!=null &&
              this.prospectId.equals(other.getProspectId()))) &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText())));
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
        if (getCliProInd() != null) {
            _hashCode += getCliProInd().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getDateDate() != null) {
            _hashCode += getDateDate().hashCode();
        }
        if (getDateSgb() != null) {
            _hashCode += getDateSgb().hashCode();
        }
        if (getDateTxt() != null) {
            _hashCode += getDateTxt().hashCode();
        }
        if (getDateTxt1() != null) {
            _hashCode += getDateTxt1().hashCode();
        }
        if (getDateTxt2() != null) {
            _hashCode += getDateTxt2().hashCode();
        }
        if (getHour() != null) {
            _hashCode += getHour().hashCode();
        }
        if (getLogId() != null) {
            _hashCode += getLogId().hashCode();
        }
        if (getLogTypeId() != null) {
            _hashCode += getLogTypeId().hashCode();
        }
        if (getProspectId() != null) {
            _hashCode += getProspectId().hashCode();
        }
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Logs.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Logs"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliProInd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "cliProInd"));
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
        elemField.setFieldName("dateDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "dateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateSgb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "dateSgb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTxt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "dateTxt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTxt1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "dateTxt1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTxt2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "dateTxt2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hour");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "hour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "logId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "logTypeId"));
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
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "text"));
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
