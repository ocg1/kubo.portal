/**
 * ProspectRiskRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.kubo.kubows;

public class ProspectRiskRequest  implements java.io.Serializable {
    private java.lang.String cci_score;

    private java.lang.String emisor_company_id;

    private java.lang.String emisor_prospect_id;

    private java.lang.String estatus_id;

    private java.lang.String evento;

    private java.lang.String kubo_rate;

    private java.lang.String kubo_score_a;

    private java.lang.String kubo_score_b;

    private java.lang.String liquidity;

    private java.lang.String mx_solicitud_buro;

    private java.lang.String openning_comision;

    private java.lang.String prospect_id;

    private java.lang.String rData;

    private java.lang.String rate;

    private java.lang.String rate_investor;

    private java.lang.String risk_level;

    private java.lang.String[] risk_level_TOKEN;

    private java.lang.String segment_id;

    private java.lang.String segment_type_id;

    public ProspectRiskRequest() {
    }

    public ProspectRiskRequest(
           java.lang.String cci_score,
           java.lang.String emisor_company_id,
           java.lang.String emisor_prospect_id,
           java.lang.String estatus_id,
           java.lang.String evento,
           java.lang.String kubo_rate,
           java.lang.String kubo_score_a,
           java.lang.String kubo_score_b,
           java.lang.String liquidity,
           java.lang.String mx_solicitud_buro,
           java.lang.String openning_comision,
           java.lang.String prospect_id,
           java.lang.String rData,
           java.lang.String rate,
           java.lang.String rate_investor,
           java.lang.String risk_level,
           java.lang.String[] risk_level_TOKEN,
           java.lang.String segment_id,
           java.lang.String segment_type_id) {
           this.cci_score = cci_score;
           this.emisor_company_id = emisor_company_id;
           this.emisor_prospect_id = emisor_prospect_id;
           this.estatus_id = estatus_id;
           this.evento = evento;
           this.kubo_rate = kubo_rate;
           this.kubo_score_a = kubo_score_a;
           this.kubo_score_b = kubo_score_b;
           this.liquidity = liquidity;
           this.mx_solicitud_buro = mx_solicitud_buro;
           this.openning_comision = openning_comision;
           this.prospect_id = prospect_id;
           this.rData = rData;
           this.rate = rate;
           this.rate_investor = rate_investor;
           this.risk_level = risk_level;
           this.risk_level_TOKEN = risk_level_TOKEN;
           this.segment_id = segment_id;
           this.segment_type_id = segment_type_id;
    }


    /**
     * Gets the cci_score value for this ProspectRiskRequest.
     * 
     * @return cci_score
     */
    public java.lang.String getCci_score() {
        return cci_score;
    }


    /**
     * Sets the cci_score value for this ProspectRiskRequest.
     * 
     * @param cci_score
     */
    public void setCci_score(java.lang.String cci_score) {
        this.cci_score = cci_score;
    }


    /**
     * Gets the emisor_company_id value for this ProspectRiskRequest.
     * 
     * @return emisor_company_id
     */
    public java.lang.String getEmisor_company_id() {
        return emisor_company_id;
    }


    /**
     * Sets the emisor_company_id value for this ProspectRiskRequest.
     * 
     * @param emisor_company_id
     */
    public void setEmisor_company_id(java.lang.String emisor_company_id) {
        this.emisor_company_id = emisor_company_id;
    }


    /**
     * Gets the emisor_prospect_id value for this ProspectRiskRequest.
     * 
     * @return emisor_prospect_id
     */
    public java.lang.String getEmisor_prospect_id() {
        return emisor_prospect_id;
    }


    /**
     * Sets the emisor_prospect_id value for this ProspectRiskRequest.
     * 
     * @param emisor_prospect_id
     */
    public void setEmisor_prospect_id(java.lang.String emisor_prospect_id) {
        this.emisor_prospect_id = emisor_prospect_id;
    }


    /**
     * Gets the estatus_id value for this ProspectRiskRequest.
     * 
     * @return estatus_id
     */
    public java.lang.String getEstatus_id() {
        return estatus_id;
    }


    /**
     * Sets the estatus_id value for this ProspectRiskRequest.
     * 
     * @param estatus_id
     */
    public void setEstatus_id(java.lang.String estatus_id) {
        this.estatus_id = estatus_id;
    }


    /**
     * Gets the evento value for this ProspectRiskRequest.
     * 
     * @return evento
     */
    public java.lang.String getEvento() {
        return evento;
    }


    /**
     * Sets the evento value for this ProspectRiskRequest.
     * 
     * @param evento
     */
    public void setEvento(java.lang.String evento) {
        this.evento = evento;
    }


    /**
     * Gets the kubo_rate value for this ProspectRiskRequest.
     * 
     * @return kubo_rate
     */
    public java.lang.String getKubo_rate() {
        return kubo_rate;
    }


    /**
     * Sets the kubo_rate value for this ProspectRiskRequest.
     * 
     * @param kubo_rate
     */
    public void setKubo_rate(java.lang.String kubo_rate) {
        this.kubo_rate = kubo_rate;
    }


    /**
     * Gets the kubo_score_a value for this ProspectRiskRequest.
     * 
     * @return kubo_score_a
     */
    public java.lang.String getKubo_score_a() {
        return kubo_score_a;
    }


    /**
     * Sets the kubo_score_a value for this ProspectRiskRequest.
     * 
     * @param kubo_score_a
     */
    public void setKubo_score_a(java.lang.String kubo_score_a) {
        this.kubo_score_a = kubo_score_a;
    }


    /**
     * Gets the kubo_score_b value for this ProspectRiskRequest.
     * 
     * @return kubo_score_b
     */
    public java.lang.String getKubo_score_b() {
        return kubo_score_b;
    }


    /**
     * Sets the kubo_score_b value for this ProspectRiskRequest.
     * 
     * @param kubo_score_b
     */
    public void setKubo_score_b(java.lang.String kubo_score_b) {
        this.kubo_score_b = kubo_score_b;
    }


    /**
     * Gets the liquidity value for this ProspectRiskRequest.
     * 
     * @return liquidity
     */
    public java.lang.String getLiquidity() {
        return liquidity;
    }


    /**
     * Sets the liquidity value for this ProspectRiskRequest.
     * 
     * @param liquidity
     */
    public void setLiquidity(java.lang.String liquidity) {
        this.liquidity = liquidity;
    }


    /**
     * Gets the mx_solicitud_buro value for this ProspectRiskRequest.
     * 
     * @return mx_solicitud_buro
     */
    public java.lang.String getMx_solicitud_buro() {
        return mx_solicitud_buro;
    }


    /**
     * Sets the mx_solicitud_buro value for this ProspectRiskRequest.
     * 
     * @param mx_solicitud_buro
     */
    public void setMx_solicitud_buro(java.lang.String mx_solicitud_buro) {
        this.mx_solicitud_buro = mx_solicitud_buro;
    }


    /**
     * Gets the openning_comision value for this ProspectRiskRequest.
     * 
     * @return openning_comision
     */
    public java.lang.String getOpenning_comision() {
        return openning_comision;
    }


    /**
     * Sets the openning_comision value for this ProspectRiskRequest.
     * 
     * @param openning_comision
     */
    public void setOpenning_comision(java.lang.String openning_comision) {
        this.openning_comision = openning_comision;
    }


    /**
     * Gets the prospect_id value for this ProspectRiskRequest.
     * 
     * @return prospect_id
     */
    public java.lang.String getProspect_id() {
        return prospect_id;
    }


    /**
     * Sets the prospect_id value for this ProspectRiskRequest.
     * 
     * @param prospect_id
     */
    public void setProspect_id(java.lang.String prospect_id) {
        this.prospect_id = prospect_id;
    }


    /**
     * Gets the rData value for this ProspectRiskRequest.
     * 
     * @return rData
     */
    public java.lang.String getRData() {
        return rData;
    }


    /**
     * Sets the rData value for this ProspectRiskRequest.
     * 
     * @param rData
     */
    public void setRData(java.lang.String rData) {
        this.rData = rData;
    }


    /**
     * Gets the rate value for this ProspectRiskRequest.
     * 
     * @return rate
     */
    public java.lang.String getRate() {
        return rate;
    }


    /**
     * Sets the rate value for this ProspectRiskRequest.
     * 
     * @param rate
     */
    public void setRate(java.lang.String rate) {
        this.rate = rate;
    }


    /**
     * Gets the rate_investor value for this ProspectRiskRequest.
     * 
     * @return rate_investor
     */
    public java.lang.String getRate_investor() {
        return rate_investor;
    }


    /**
     * Sets the rate_investor value for this ProspectRiskRequest.
     * 
     * @param rate_investor
     */
    public void setRate_investor(java.lang.String rate_investor) {
        this.rate_investor = rate_investor;
    }


    /**
     * Gets the risk_level value for this ProspectRiskRequest.
     * 
     * @return risk_level
     */
    public java.lang.String getRisk_level() {
        return risk_level;
    }


    /**
     * Sets the risk_level value for this ProspectRiskRequest.
     * 
     * @param risk_level
     */
    public void setRisk_level(java.lang.String risk_level) {
        this.risk_level = risk_level;
    }


    /**
     * Gets the risk_level_TOKEN value for this ProspectRiskRequest.
     * 
     * @return risk_level_TOKEN
     */
    public java.lang.String[] getRisk_level_TOKEN() {
        return risk_level_TOKEN;
    }


    /**
     * Sets the risk_level_TOKEN value for this ProspectRiskRequest.
     * 
     * @param risk_level_TOKEN
     */
    public void setRisk_level_TOKEN(java.lang.String[] risk_level_TOKEN) {
        this.risk_level_TOKEN = risk_level_TOKEN;
    }


    /**
     * Gets the segment_id value for this ProspectRiskRequest.
     * 
     * @return segment_id
     */
    public java.lang.String getSegment_id() {
        return segment_id;
    }


    /**
     * Sets the segment_id value for this ProspectRiskRequest.
     * 
     * @param segment_id
     */
    public void setSegment_id(java.lang.String segment_id) {
        this.segment_id = segment_id;
    }


    /**
     * Gets the segment_type_id value for this ProspectRiskRequest.
     * 
     * @return segment_type_id
     */
    public java.lang.String getSegment_type_id() {
        return segment_type_id;
    }


    /**
     * Sets the segment_type_id value for this ProspectRiskRequest.
     * 
     * @param segment_type_id
     */
    public void setSegment_type_id(java.lang.String segment_type_id) {
        this.segment_type_id = segment_type_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProspectRiskRequest)) return false;
        ProspectRiskRequest other = (ProspectRiskRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cci_score==null && other.getCci_score()==null) || 
             (this.cci_score!=null &&
              this.cci_score.equals(other.getCci_score()))) &&
            ((this.emisor_company_id==null && other.getEmisor_company_id()==null) || 
             (this.emisor_company_id!=null &&
              this.emisor_company_id.equals(other.getEmisor_company_id()))) &&
            ((this.emisor_prospect_id==null && other.getEmisor_prospect_id()==null) || 
             (this.emisor_prospect_id!=null &&
              this.emisor_prospect_id.equals(other.getEmisor_prospect_id()))) &&
            ((this.estatus_id==null && other.getEstatus_id()==null) || 
             (this.estatus_id!=null &&
              this.estatus_id.equals(other.getEstatus_id()))) &&
            ((this.evento==null && other.getEvento()==null) || 
             (this.evento!=null &&
              this.evento.equals(other.getEvento()))) &&
            ((this.kubo_rate==null && other.getKubo_rate()==null) || 
             (this.kubo_rate!=null &&
              this.kubo_rate.equals(other.getKubo_rate()))) &&
            ((this.kubo_score_a==null && other.getKubo_score_a()==null) || 
             (this.kubo_score_a!=null &&
              this.kubo_score_a.equals(other.getKubo_score_a()))) &&
            ((this.kubo_score_b==null && other.getKubo_score_b()==null) || 
             (this.kubo_score_b!=null &&
              this.kubo_score_b.equals(other.getKubo_score_b()))) &&
            ((this.liquidity==null && other.getLiquidity()==null) || 
             (this.liquidity!=null &&
              this.liquidity.equals(other.getLiquidity()))) &&
            ((this.mx_solicitud_buro==null && other.getMx_solicitud_buro()==null) || 
             (this.mx_solicitud_buro!=null &&
              this.mx_solicitud_buro.equals(other.getMx_solicitud_buro()))) &&
            ((this.openning_comision==null && other.getOpenning_comision()==null) || 
             (this.openning_comision!=null &&
              this.openning_comision.equals(other.getOpenning_comision()))) &&
            ((this.prospect_id==null && other.getProspect_id()==null) || 
             (this.prospect_id!=null &&
              this.prospect_id.equals(other.getProspect_id()))) &&
            ((this.rData==null && other.getRData()==null) || 
             (this.rData!=null &&
              this.rData.equals(other.getRData()))) &&
            ((this.rate==null && other.getRate()==null) || 
             (this.rate!=null &&
              this.rate.equals(other.getRate()))) &&
            ((this.rate_investor==null && other.getRate_investor()==null) || 
             (this.rate_investor!=null &&
              this.rate_investor.equals(other.getRate_investor()))) &&
            ((this.risk_level==null && other.getRisk_level()==null) || 
             (this.risk_level!=null &&
              this.risk_level.equals(other.getRisk_level()))) &&
            ((this.risk_level_TOKEN==null && other.getRisk_level_TOKEN()==null) || 
             (this.risk_level_TOKEN!=null &&
              java.util.Arrays.equals(this.risk_level_TOKEN, other.getRisk_level_TOKEN()))) &&
            ((this.segment_id==null && other.getSegment_id()==null) || 
             (this.segment_id!=null &&
              this.segment_id.equals(other.getSegment_id()))) &&
            ((this.segment_type_id==null && other.getSegment_type_id()==null) || 
             (this.segment_type_id!=null &&
              this.segment_type_id.equals(other.getSegment_type_id())));
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
        if (getCci_score() != null) {
            _hashCode += getCci_score().hashCode();
        }
        if (getEmisor_company_id() != null) {
            _hashCode += getEmisor_company_id().hashCode();
        }
        if (getEmisor_prospect_id() != null) {
            _hashCode += getEmisor_prospect_id().hashCode();
        }
        if (getEstatus_id() != null) {
            _hashCode += getEstatus_id().hashCode();
        }
        if (getEvento() != null) {
            _hashCode += getEvento().hashCode();
        }
        if (getKubo_rate() != null) {
            _hashCode += getKubo_rate().hashCode();
        }
        if (getKubo_score_a() != null) {
            _hashCode += getKubo_score_a().hashCode();
        }
        if (getKubo_score_b() != null) {
            _hashCode += getKubo_score_b().hashCode();
        }
        if (getLiquidity() != null) {
            _hashCode += getLiquidity().hashCode();
        }
        if (getMx_solicitud_buro() != null) {
            _hashCode += getMx_solicitud_buro().hashCode();
        }
        if (getOpenning_comision() != null) {
            _hashCode += getOpenning_comision().hashCode();
        }
        if (getProspect_id() != null) {
            _hashCode += getProspect_id().hashCode();
        }
        if (getRData() != null) {
            _hashCode += getRData().hashCode();
        }
        if (getRate() != null) {
            _hashCode += getRate().hashCode();
        }
        if (getRate_investor() != null) {
            _hashCode += getRate_investor().hashCode();
        }
        if (getRisk_level() != null) {
            _hashCode += getRisk_level().hashCode();
        }
        if (getRisk_level_TOKEN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRisk_level_TOKEN());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRisk_level_TOKEN(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSegment_id() != null) {
            _hashCode += getSegment_id().hashCode();
        }
        if (getSegment_type_id() != null) {
            _hashCode += getSegment_type_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProspectRiskRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "ProspectRiskRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cci_score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "cci_score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emisor_company_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "emisor_company_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emisor_prospect_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "emisor_prospect_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estatus_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "estatus_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("evento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "evento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kubo_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "kubo_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kubo_score_a");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "kubo_score_a"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kubo_score_b");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "kubo_score_b"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liquidity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "liquidity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mx_solicitud_buro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "mx_solicitud_buro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openning_comision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "openning_comision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prospect_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "prospect_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "rData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate_investor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "rate_investor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("risk_level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "risk_level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("risk_level_TOKEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "risk_level_TOKEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "segment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segment_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://kubows.kubo.com.mx", "segment_type_id"));
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
