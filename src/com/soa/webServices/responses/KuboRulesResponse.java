/**
 * KuboRulesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.webServices.responses;

public class KuboRulesResponse  implements java.io.Serializable {
    private java.lang.Double actualValue;

    private java.lang.Double maxValue;

    private java.lang.Double minValue;

    private java.lang.String renderRule;

    private java.lang.String ruleDescription;

    private java.lang.String ruleId;

    private java.lang.String ruleName;

    private java.lang.String text;

    private java.lang.String textCSS;

    public KuboRulesResponse() {
    }

    public KuboRulesResponse(
           java.lang.Double actualValue,
           java.lang.Double maxValue,
           java.lang.Double minValue,
           java.lang.String renderRule,
           java.lang.String ruleDescription,
           java.lang.String ruleId,
           java.lang.String ruleName,
           java.lang.String text,
           java.lang.String textCSS) {
           this.actualValue = actualValue;
           this.maxValue = maxValue;
           this.minValue = minValue;
           this.renderRule = renderRule;
           this.ruleDescription = ruleDescription;
           this.ruleId = ruleId;
           this.ruleName = ruleName;
           this.text = text;
           this.textCSS = textCSS;
    }


    /**
     * Gets the actualValue value for this KuboRulesResponse.
     * 
     * @return actualValue
     */
    public java.lang.Double getActualValue() {
        return actualValue;
    }


    /**
     * Sets the actualValue value for this KuboRulesResponse.
     * 
     * @param actualValue
     */
    public void setActualValue(java.lang.Double actualValue) {
        this.actualValue = actualValue;
    }


    /**
     * Gets the maxValue value for this KuboRulesResponse.
     * 
     * @return maxValue
     */
    public java.lang.Double getMaxValue() {
        return maxValue;
    }


    /**
     * Sets the maxValue value for this KuboRulesResponse.
     * 
     * @param maxValue
     */
    public void setMaxValue(java.lang.Double maxValue) {
        this.maxValue = maxValue;
    }


    /**
     * Gets the minValue value for this KuboRulesResponse.
     * 
     * @return minValue
     */
    public java.lang.Double getMinValue() {
        return minValue;
    }


    /**
     * Sets the minValue value for this KuboRulesResponse.
     * 
     * @param minValue
     */
    public void setMinValue(java.lang.Double minValue) {
        this.minValue = minValue;
    }


    /**
     * Gets the renderRule value for this KuboRulesResponse.
     * 
     * @return renderRule
     */
    public java.lang.String getRenderRule() {
        return renderRule;
    }


    /**
     * Sets the renderRule value for this KuboRulesResponse.
     * 
     * @param renderRule
     */
    public void setRenderRule(java.lang.String renderRule) {
        this.renderRule = renderRule;
    }


    /**
     * Gets the ruleDescription value for this KuboRulesResponse.
     * 
     * @return ruleDescription
     */
    public java.lang.String getRuleDescription() {
        return ruleDescription;
    }


    /**
     * Sets the ruleDescription value for this KuboRulesResponse.
     * 
     * @param ruleDescription
     */
    public void setRuleDescription(java.lang.String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }


    /**
     * Gets the ruleId value for this KuboRulesResponse.
     * 
     * @return ruleId
     */
    public java.lang.String getRuleId() {
        return ruleId;
    }


    /**
     * Sets the ruleId value for this KuboRulesResponse.
     * 
     * @param ruleId
     */
    public void setRuleId(java.lang.String ruleId) {
        this.ruleId = ruleId;
    }


    /**
     * Gets the ruleName value for this KuboRulesResponse.
     * 
     * @return ruleName
     */
    public java.lang.String getRuleName() {
        return ruleName;
    }


    /**
     * Sets the ruleName value for this KuboRulesResponse.
     * 
     * @param ruleName
     */
    public void setRuleName(java.lang.String ruleName) {
        this.ruleName = ruleName;
    }


    /**
     * Gets the text value for this KuboRulesResponse.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this KuboRulesResponse.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }


    /**
     * Gets the textCSS value for this KuboRulesResponse.
     * 
     * @return textCSS
     */
    public java.lang.String getTextCSS() {
        return textCSS;
    }


    /**
     * Sets the textCSS value for this KuboRulesResponse.
     * 
     * @param textCSS
     */
    public void setTextCSS(java.lang.String textCSS) {
        this.textCSS = textCSS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KuboRulesResponse)) return false;
        KuboRulesResponse other = (KuboRulesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualValue==null && other.getActualValue()==null) || 
             (this.actualValue!=null &&
              this.actualValue.equals(other.getActualValue()))) &&
            ((this.maxValue==null && other.getMaxValue()==null) || 
             (this.maxValue!=null &&
              this.maxValue.equals(other.getMaxValue()))) &&
            ((this.minValue==null && other.getMinValue()==null) || 
             (this.minValue!=null &&
              this.minValue.equals(other.getMinValue()))) &&
            ((this.renderRule==null && other.getRenderRule()==null) || 
             (this.renderRule!=null &&
              this.renderRule.equals(other.getRenderRule()))) &&
            ((this.ruleDescription==null && other.getRuleDescription()==null) || 
             (this.ruleDescription!=null &&
              this.ruleDescription.equals(other.getRuleDescription()))) &&
            ((this.ruleId==null && other.getRuleId()==null) || 
             (this.ruleId!=null &&
              this.ruleId.equals(other.getRuleId()))) &&
            ((this.ruleName==null && other.getRuleName()==null) || 
             (this.ruleName!=null &&
              this.ruleName.equals(other.getRuleName()))) &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText()))) &&
            ((this.textCSS==null && other.getTextCSS()==null) || 
             (this.textCSS!=null &&
              this.textCSS.equals(other.getTextCSS())));
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
        if (getActualValue() != null) {
            _hashCode += getActualValue().hashCode();
        }
        if (getMaxValue() != null) {
            _hashCode += getMaxValue().hashCode();
        }
        if (getMinValue() != null) {
            _hashCode += getMinValue().hashCode();
        }
        if (getRenderRule() != null) {
            _hashCode += getRenderRule().hashCode();
        }
        if (getRuleDescription() != null) {
            _hashCode += getRuleDescription().hashCode();
        }
        if (getRuleId() != null) {
            _hashCode += getRuleId().hashCode();
        }
        if (getRuleName() != null) {
            _hashCode += getRuleName().hashCode();
        }
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        if (getTextCSS() != null) {
            _hashCode += getTextCSS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KuboRulesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "KuboRulesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "actualValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "maxValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "minValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("renderRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "renderRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruleDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ruleDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ruleId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "ruleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textCSS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://responses.webServices.soa.com", "textCSS"));
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
