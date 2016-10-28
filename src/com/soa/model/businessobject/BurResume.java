/**
 * BurResume.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soa.model.businessobject;

public class BurResume  implements java.io.Serializable {
    private java.lang.Integer aplicogarantia;

    private java.lang.String burFol;

    private java.lang.String burSolNumber;

    private java.lang.String bur_solnum;

    private java.lang.Integer codemandado;

    private java.lang.Integer condonaciones;

    private java.lang.Double credito_max_oto;

    private java.lang.Double credito_max_oto_c;

    private java.lang.Double credito_max_oto_nc;

    private java.lang.Double credito_maximo_uti;

    private java.lang.Double credito_maximo_uti_c;

    private java.lang.Double credito_maximo_uti_nc;

    private java.lang.Integer creditos;

    private java.lang.Integer creditos_c;

    private java.lang.Integer creditos_despacho;

    private java.lang.Integer creditos_nc;

    private java.lang.String cta_cgarantia;

    private java.lang.Integer ctas_noactivas;

    private java.lang.String fecConsulta;

    private java.lang.String fec_credantiguo;

    private java.lang.Long fecha_despachoreciente;

    private java.lang.Integer fianzas;

    private java.lang.Integer fraudes;

    private java.lang.Integer fraudesnoatribuible;

    private java.lang.String idcliente;

    private java.lang.String idprospecto;

    private com.soa.model.businessobject.Vbur_maxoto[] maxoto;

    private java.lang.Double monto_pagar;

    private java.lang.Double monto_pagar_c;

    private java.lang.Double monto_pagar_nc;

    private java.lang.Integer nolocalizable;

    private java.lang.String numsols;

    private java.lang.String quebrantos;

    private java.lang.Integer reestructura;

    private java.lang.Integer roboidentidad;

    private java.lang.Double saldo_actual;

    private java.lang.Double saldo_actual_c;

    private java.lang.Double saldo_actual_nc;

    private com.soa.model.businessobject.Vtbur_scaa[] scaa;

    private com.soa.model.businessobject.Vtbur_scag[] scag;

    private com.soa.model.businessobject.Vtbur_scapm[] scapm;

    private com.soa.model.businessobject.Vtbur_sccc[] sccc;

    private com.soa.model.businessobject.Vtbur_sccpm[] sccpm;

    private java.lang.String scoreIcc;

    private java.lang.String score_buro;

    private com.soa.model.businessobject.Vtbur_spca[] spca;

    private java.lang.String tipoConsulta;

    public BurResume() {
    }

    public BurResume(
           java.lang.Integer aplicogarantia,
           java.lang.String burFol,
           java.lang.String burSolNumber,
           java.lang.String bur_solnum,
           java.lang.Integer codemandado,
           java.lang.Integer condonaciones,
           java.lang.Double credito_max_oto,
           java.lang.Double credito_max_oto_c,
           java.lang.Double credito_max_oto_nc,
           java.lang.Double credito_maximo_uti,
           java.lang.Double credito_maximo_uti_c,
           java.lang.Double credito_maximo_uti_nc,
           java.lang.Integer creditos,
           java.lang.Integer creditos_c,
           java.lang.Integer creditos_despacho,
           java.lang.Integer creditos_nc,
           java.lang.String cta_cgarantia,
           java.lang.Integer ctas_noactivas,
           java.lang.String fecConsulta,
           java.lang.String fec_credantiguo,
           java.lang.Long fecha_despachoreciente,
           java.lang.Integer fianzas,
           java.lang.Integer fraudes,
           java.lang.Integer fraudesnoatribuible,
           java.lang.String idcliente,
           java.lang.String idprospecto,
           com.soa.model.businessobject.Vbur_maxoto[] maxoto,
           java.lang.Double monto_pagar,
           java.lang.Double monto_pagar_c,
           java.lang.Double monto_pagar_nc,
           java.lang.Integer nolocalizable,
           java.lang.String numsols,
           java.lang.String quebrantos,
           java.lang.Integer reestructura,
           java.lang.Integer roboidentidad,
           java.lang.Double saldo_actual,
           java.lang.Double saldo_actual_c,
           java.lang.Double saldo_actual_nc,
           com.soa.model.businessobject.Vtbur_scaa[] scaa,
           com.soa.model.businessobject.Vtbur_scag[] scag,
           com.soa.model.businessobject.Vtbur_scapm[] scapm,
           com.soa.model.businessobject.Vtbur_sccc[] sccc,
           com.soa.model.businessobject.Vtbur_sccpm[] sccpm,
           java.lang.String scoreIcc,
           java.lang.String score_buro,
           com.soa.model.businessobject.Vtbur_spca[] spca,
           java.lang.String tipoConsulta) {
           this.aplicogarantia = aplicogarantia;
           this.burFol = burFol;
           this.burSolNumber = burSolNumber;
           this.bur_solnum = bur_solnum;
           this.codemandado = codemandado;
           this.condonaciones = condonaciones;
           this.credito_max_oto = credito_max_oto;
           this.credito_max_oto_c = credito_max_oto_c;
           this.credito_max_oto_nc = credito_max_oto_nc;
           this.credito_maximo_uti = credito_maximo_uti;
           this.credito_maximo_uti_c = credito_maximo_uti_c;
           this.credito_maximo_uti_nc = credito_maximo_uti_nc;
           this.creditos = creditos;
           this.creditos_c = creditos_c;
           this.creditos_despacho = creditos_despacho;
           this.creditos_nc = creditos_nc;
           this.cta_cgarantia = cta_cgarantia;
           this.ctas_noactivas = ctas_noactivas;
           this.fecConsulta = fecConsulta;
           this.fec_credantiguo = fec_credantiguo;
           this.fecha_despachoreciente = fecha_despachoreciente;
           this.fianzas = fianzas;
           this.fraudes = fraudes;
           this.fraudesnoatribuible = fraudesnoatribuible;
           this.idcliente = idcliente;
           this.idprospecto = idprospecto;
           this.maxoto = maxoto;
           this.monto_pagar = monto_pagar;
           this.monto_pagar_c = monto_pagar_c;
           this.monto_pagar_nc = monto_pagar_nc;
           this.nolocalizable = nolocalizable;
           this.numsols = numsols;
           this.quebrantos = quebrantos;
           this.reestructura = reestructura;
           this.roboidentidad = roboidentidad;
           this.saldo_actual = saldo_actual;
           this.saldo_actual_c = saldo_actual_c;
           this.saldo_actual_nc = saldo_actual_nc;
           this.scaa = scaa;
           this.scag = scag;
           this.scapm = scapm;
           this.sccc = sccc;
           this.sccpm = sccpm;
           this.scoreIcc = scoreIcc;
           this.score_buro = score_buro;
           this.spca = spca;
           this.tipoConsulta = tipoConsulta;
    }


    /**
     * Gets the aplicogarantia value for this BurResume.
     * 
     * @return aplicogarantia
     */
    public java.lang.Integer getAplicogarantia() {
        return aplicogarantia;
    }


    /**
     * Sets the aplicogarantia value for this BurResume.
     * 
     * @param aplicogarantia
     */
    public void setAplicogarantia(java.lang.Integer aplicogarantia) {
        this.aplicogarantia = aplicogarantia;
    }


    /**
     * Gets the burFol value for this BurResume.
     * 
     * @return burFol
     */
    public java.lang.String getBurFol() {
        return burFol;
    }


    /**
     * Sets the burFol value for this BurResume.
     * 
     * @param burFol
     */
    public void setBurFol(java.lang.String burFol) {
        this.burFol = burFol;
    }


    /**
     * Gets the burSolNumber value for this BurResume.
     * 
     * @return burSolNumber
     */
    public java.lang.String getBurSolNumber() {
        return burSolNumber;
    }


    /**
     * Sets the burSolNumber value for this BurResume.
     * 
     * @param burSolNumber
     */
    public void setBurSolNumber(java.lang.String burSolNumber) {
        this.burSolNumber = burSolNumber;
    }


    /**
     * Gets the bur_solnum value for this BurResume.
     * 
     * @return bur_solnum
     */
    public java.lang.String getBur_solnum() {
        return bur_solnum;
    }


    /**
     * Sets the bur_solnum value for this BurResume.
     * 
     * @param bur_solnum
     */
    public void setBur_solnum(java.lang.String bur_solnum) {
        this.bur_solnum = bur_solnum;
    }


    /**
     * Gets the codemandado value for this BurResume.
     * 
     * @return codemandado
     */
    public java.lang.Integer getCodemandado() {
        return codemandado;
    }


    /**
     * Sets the codemandado value for this BurResume.
     * 
     * @param codemandado
     */
    public void setCodemandado(java.lang.Integer codemandado) {
        this.codemandado = codemandado;
    }


    /**
     * Gets the condonaciones value for this BurResume.
     * 
     * @return condonaciones
     */
    public java.lang.Integer getCondonaciones() {
        return condonaciones;
    }


    /**
     * Sets the condonaciones value for this BurResume.
     * 
     * @param condonaciones
     */
    public void setCondonaciones(java.lang.Integer condonaciones) {
        this.condonaciones = condonaciones;
    }


    /**
     * Gets the credito_max_oto value for this BurResume.
     * 
     * @return credito_max_oto
     */
    public java.lang.Double getCredito_max_oto() {
        return credito_max_oto;
    }


    /**
     * Sets the credito_max_oto value for this BurResume.
     * 
     * @param credito_max_oto
     */
    public void setCredito_max_oto(java.lang.Double credito_max_oto) {
        this.credito_max_oto = credito_max_oto;
    }


    /**
     * Gets the credito_max_oto_c value for this BurResume.
     * 
     * @return credito_max_oto_c
     */
    public java.lang.Double getCredito_max_oto_c() {
        return credito_max_oto_c;
    }


    /**
     * Sets the credito_max_oto_c value for this BurResume.
     * 
     * @param credito_max_oto_c
     */
    public void setCredito_max_oto_c(java.lang.Double credito_max_oto_c) {
        this.credito_max_oto_c = credito_max_oto_c;
    }


    /**
     * Gets the credito_max_oto_nc value for this BurResume.
     * 
     * @return credito_max_oto_nc
     */
    public java.lang.Double getCredito_max_oto_nc() {
        return credito_max_oto_nc;
    }


    /**
     * Sets the credito_max_oto_nc value for this BurResume.
     * 
     * @param credito_max_oto_nc
     */
    public void setCredito_max_oto_nc(java.lang.Double credito_max_oto_nc) {
        this.credito_max_oto_nc = credito_max_oto_nc;
    }


    /**
     * Gets the credito_maximo_uti value for this BurResume.
     * 
     * @return credito_maximo_uti
     */
    public java.lang.Double getCredito_maximo_uti() {
        return credito_maximo_uti;
    }


    /**
     * Sets the credito_maximo_uti value for this BurResume.
     * 
     * @param credito_maximo_uti
     */
    public void setCredito_maximo_uti(java.lang.Double credito_maximo_uti) {
        this.credito_maximo_uti = credito_maximo_uti;
    }


    /**
     * Gets the credito_maximo_uti_c value for this BurResume.
     * 
     * @return credito_maximo_uti_c
     */
    public java.lang.Double getCredito_maximo_uti_c() {
        return credito_maximo_uti_c;
    }


    /**
     * Sets the credito_maximo_uti_c value for this BurResume.
     * 
     * @param credito_maximo_uti_c
     */
    public void setCredito_maximo_uti_c(java.lang.Double credito_maximo_uti_c) {
        this.credito_maximo_uti_c = credito_maximo_uti_c;
    }


    /**
     * Gets the credito_maximo_uti_nc value for this BurResume.
     * 
     * @return credito_maximo_uti_nc
     */
    public java.lang.Double getCredito_maximo_uti_nc() {
        return credito_maximo_uti_nc;
    }


    /**
     * Sets the credito_maximo_uti_nc value for this BurResume.
     * 
     * @param credito_maximo_uti_nc
     */
    public void setCredito_maximo_uti_nc(java.lang.Double credito_maximo_uti_nc) {
        this.credito_maximo_uti_nc = credito_maximo_uti_nc;
    }


    /**
     * Gets the creditos value for this BurResume.
     * 
     * @return creditos
     */
    public java.lang.Integer getCreditos() {
        return creditos;
    }


    /**
     * Sets the creditos value for this BurResume.
     * 
     * @param creditos
     */
    public void setCreditos(java.lang.Integer creditos) {
        this.creditos = creditos;
    }


    /**
     * Gets the creditos_c value for this BurResume.
     * 
     * @return creditos_c
     */
    public java.lang.Integer getCreditos_c() {
        return creditos_c;
    }


    /**
     * Sets the creditos_c value for this BurResume.
     * 
     * @param creditos_c
     */
    public void setCreditos_c(java.lang.Integer creditos_c) {
        this.creditos_c = creditos_c;
    }


    /**
     * Gets the creditos_despacho value for this BurResume.
     * 
     * @return creditos_despacho
     */
    public java.lang.Integer getCreditos_despacho() {
        return creditos_despacho;
    }


    /**
     * Sets the creditos_despacho value for this BurResume.
     * 
     * @param creditos_despacho
     */
    public void setCreditos_despacho(java.lang.Integer creditos_despacho) {
        this.creditos_despacho = creditos_despacho;
    }


    /**
     * Gets the creditos_nc value for this BurResume.
     * 
     * @return creditos_nc
     */
    public java.lang.Integer getCreditos_nc() {
        return creditos_nc;
    }


    /**
     * Sets the creditos_nc value for this BurResume.
     * 
     * @param creditos_nc
     */
    public void setCreditos_nc(java.lang.Integer creditos_nc) {
        this.creditos_nc = creditos_nc;
    }


    /**
     * Gets the cta_cgarantia value for this BurResume.
     * 
     * @return cta_cgarantia
     */
    public java.lang.String getCta_cgarantia() {
        return cta_cgarantia;
    }


    /**
     * Sets the cta_cgarantia value for this BurResume.
     * 
     * @param cta_cgarantia
     */
    public void setCta_cgarantia(java.lang.String cta_cgarantia) {
        this.cta_cgarantia = cta_cgarantia;
    }


    /**
     * Gets the ctas_noactivas value for this BurResume.
     * 
     * @return ctas_noactivas
     */
    public java.lang.Integer getCtas_noactivas() {
        return ctas_noactivas;
    }


    /**
     * Sets the ctas_noactivas value for this BurResume.
     * 
     * @param ctas_noactivas
     */
    public void setCtas_noactivas(java.lang.Integer ctas_noactivas) {
        this.ctas_noactivas = ctas_noactivas;
    }


    /**
     * Gets the fecConsulta value for this BurResume.
     * 
     * @return fecConsulta
     */
    public java.lang.String getFecConsulta() {
        return fecConsulta;
    }


    /**
     * Sets the fecConsulta value for this BurResume.
     * 
     * @param fecConsulta
     */
    public void setFecConsulta(java.lang.String fecConsulta) {
        this.fecConsulta = fecConsulta;
    }


    /**
     * Gets the fec_credantiguo value for this BurResume.
     * 
     * @return fec_credantiguo
     */
    public java.lang.String getFec_credantiguo() {
        return fec_credantiguo;
    }


    /**
     * Sets the fec_credantiguo value for this BurResume.
     * 
     * @param fec_credantiguo
     */
    public void setFec_credantiguo(java.lang.String fec_credantiguo) {
        this.fec_credantiguo = fec_credantiguo;
    }


    /**
     * Gets the fecha_despachoreciente value for this BurResume.
     * 
     * @return fecha_despachoreciente
     */
    public java.lang.Long getFecha_despachoreciente() {
        return fecha_despachoreciente;
    }


    /**
     * Sets the fecha_despachoreciente value for this BurResume.
     * 
     * @param fecha_despachoreciente
     */
    public void setFecha_despachoreciente(java.lang.Long fecha_despachoreciente) {
        this.fecha_despachoreciente = fecha_despachoreciente;
    }


    /**
     * Gets the fianzas value for this BurResume.
     * 
     * @return fianzas
     */
    public java.lang.Integer getFianzas() {
        return fianzas;
    }


    /**
     * Sets the fianzas value for this BurResume.
     * 
     * @param fianzas
     */
    public void setFianzas(java.lang.Integer fianzas) {
        this.fianzas = fianzas;
    }


    /**
     * Gets the fraudes value for this BurResume.
     * 
     * @return fraudes
     */
    public java.lang.Integer getFraudes() {
        return fraudes;
    }


    /**
     * Sets the fraudes value for this BurResume.
     * 
     * @param fraudes
     */
    public void setFraudes(java.lang.Integer fraudes) {
        this.fraudes = fraudes;
    }


    /**
     * Gets the fraudesnoatribuible value for this BurResume.
     * 
     * @return fraudesnoatribuible
     */
    public java.lang.Integer getFraudesnoatribuible() {
        return fraudesnoatribuible;
    }


    /**
     * Sets the fraudesnoatribuible value for this BurResume.
     * 
     * @param fraudesnoatribuible
     */
    public void setFraudesnoatribuible(java.lang.Integer fraudesnoatribuible) {
        this.fraudesnoatribuible = fraudesnoatribuible;
    }


    /**
     * Gets the idcliente value for this BurResume.
     * 
     * @return idcliente
     */
    public java.lang.String getIdcliente() {
        return idcliente;
    }


    /**
     * Sets the idcliente value for this BurResume.
     * 
     * @param idcliente
     */
    public void setIdcliente(java.lang.String idcliente) {
        this.idcliente = idcliente;
    }


    /**
     * Gets the idprospecto value for this BurResume.
     * 
     * @return idprospecto
     */
    public java.lang.String getIdprospecto() {
        return idprospecto;
    }


    /**
     * Sets the idprospecto value for this BurResume.
     * 
     * @param idprospecto
     */
    public void setIdprospecto(java.lang.String idprospecto) {
        this.idprospecto = idprospecto;
    }


    /**
     * Gets the maxoto value for this BurResume.
     * 
     * @return maxoto
     */
    public com.soa.model.businessobject.Vbur_maxoto[] getMaxoto() {
        return maxoto;
    }


    /**
     * Sets the maxoto value for this BurResume.
     * 
     * @param maxoto
     */
    public void setMaxoto(com.soa.model.businessobject.Vbur_maxoto[] maxoto) {
        this.maxoto = maxoto;
    }


    /**
     * Gets the monto_pagar value for this BurResume.
     * 
     * @return monto_pagar
     */
    public java.lang.Double getMonto_pagar() {
        return monto_pagar;
    }


    /**
     * Sets the monto_pagar value for this BurResume.
     * 
     * @param monto_pagar
     */
    public void setMonto_pagar(java.lang.Double monto_pagar) {
        this.monto_pagar = monto_pagar;
    }


    /**
     * Gets the monto_pagar_c value for this BurResume.
     * 
     * @return monto_pagar_c
     */
    public java.lang.Double getMonto_pagar_c() {
        return monto_pagar_c;
    }


    /**
     * Sets the monto_pagar_c value for this BurResume.
     * 
     * @param monto_pagar_c
     */
    public void setMonto_pagar_c(java.lang.Double monto_pagar_c) {
        this.monto_pagar_c = monto_pagar_c;
    }


    /**
     * Gets the monto_pagar_nc value for this BurResume.
     * 
     * @return monto_pagar_nc
     */
    public java.lang.Double getMonto_pagar_nc() {
        return monto_pagar_nc;
    }


    /**
     * Sets the monto_pagar_nc value for this BurResume.
     * 
     * @param monto_pagar_nc
     */
    public void setMonto_pagar_nc(java.lang.Double monto_pagar_nc) {
        this.monto_pagar_nc = monto_pagar_nc;
    }


    /**
     * Gets the nolocalizable value for this BurResume.
     * 
     * @return nolocalizable
     */
    public java.lang.Integer getNolocalizable() {
        return nolocalizable;
    }


    /**
     * Sets the nolocalizable value for this BurResume.
     * 
     * @param nolocalizable
     */
    public void setNolocalizable(java.lang.Integer nolocalizable) {
        this.nolocalizable = nolocalizable;
    }


    /**
     * Gets the numsols value for this BurResume.
     * 
     * @return numsols
     */
    public java.lang.String getNumsols() {
        return numsols;
    }


    /**
     * Sets the numsols value for this BurResume.
     * 
     * @param numsols
     */
    public void setNumsols(java.lang.String numsols) {
        this.numsols = numsols;
    }


    /**
     * Gets the quebrantos value for this BurResume.
     * 
     * @return quebrantos
     */
    public java.lang.String getQuebrantos() {
        return quebrantos;
    }


    /**
     * Sets the quebrantos value for this BurResume.
     * 
     * @param quebrantos
     */
    public void setQuebrantos(java.lang.String quebrantos) {
        this.quebrantos = quebrantos;
    }


    /**
     * Gets the reestructura value for this BurResume.
     * 
     * @return reestructura
     */
    public java.lang.Integer getReestructura() {
        return reestructura;
    }


    /**
     * Sets the reestructura value for this BurResume.
     * 
     * @param reestructura
     */
    public void setReestructura(java.lang.Integer reestructura) {
        this.reestructura = reestructura;
    }


    /**
     * Gets the roboidentidad value for this BurResume.
     * 
     * @return roboidentidad
     */
    public java.lang.Integer getRoboidentidad() {
        return roboidentidad;
    }


    /**
     * Sets the roboidentidad value for this BurResume.
     * 
     * @param roboidentidad
     */
    public void setRoboidentidad(java.lang.Integer roboidentidad) {
        this.roboidentidad = roboidentidad;
    }


    /**
     * Gets the saldo_actual value for this BurResume.
     * 
     * @return saldo_actual
     */
    public java.lang.Double getSaldo_actual() {
        return saldo_actual;
    }


    /**
     * Sets the saldo_actual value for this BurResume.
     * 
     * @param saldo_actual
     */
    public void setSaldo_actual(java.lang.Double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }


    /**
     * Gets the saldo_actual_c value for this BurResume.
     * 
     * @return saldo_actual_c
     */
    public java.lang.Double getSaldo_actual_c() {
        return saldo_actual_c;
    }


    /**
     * Sets the saldo_actual_c value for this BurResume.
     * 
     * @param saldo_actual_c
     */
    public void setSaldo_actual_c(java.lang.Double saldo_actual_c) {
        this.saldo_actual_c = saldo_actual_c;
    }


    /**
     * Gets the saldo_actual_nc value for this BurResume.
     * 
     * @return saldo_actual_nc
     */
    public java.lang.Double getSaldo_actual_nc() {
        return saldo_actual_nc;
    }


    /**
     * Sets the saldo_actual_nc value for this BurResume.
     * 
     * @param saldo_actual_nc
     */
    public void setSaldo_actual_nc(java.lang.Double saldo_actual_nc) {
        this.saldo_actual_nc = saldo_actual_nc;
    }


    /**
     * Gets the scaa value for this BurResume.
     * 
     * @return scaa
     */
    public com.soa.model.businessobject.Vtbur_scaa[] getScaa() {
        return scaa;
    }


    /**
     * Sets the scaa value for this BurResume.
     * 
     * @param scaa
     */
    public void setScaa(com.soa.model.businessobject.Vtbur_scaa[] scaa) {
        this.scaa = scaa;
    }


    /**
     * Gets the scag value for this BurResume.
     * 
     * @return scag
     */
    public com.soa.model.businessobject.Vtbur_scag[] getScag() {
        return scag;
    }


    /**
     * Sets the scag value for this BurResume.
     * 
     * @param scag
     */
    public void setScag(com.soa.model.businessobject.Vtbur_scag[] scag) {
        this.scag = scag;
    }


    /**
     * Gets the scapm value for this BurResume.
     * 
     * @return scapm
     */
    public com.soa.model.businessobject.Vtbur_scapm[] getScapm() {
        return scapm;
    }


    /**
     * Sets the scapm value for this BurResume.
     * 
     * @param scapm
     */
    public void setScapm(com.soa.model.businessobject.Vtbur_scapm[] scapm) {
        this.scapm = scapm;
    }


    /**
     * Gets the sccc value for this BurResume.
     * 
     * @return sccc
     */
    public com.soa.model.businessobject.Vtbur_sccc[] getSccc() {
        return sccc;
    }


    /**
     * Sets the sccc value for this BurResume.
     * 
     * @param sccc
     */
    public void setSccc(com.soa.model.businessobject.Vtbur_sccc[] sccc) {
        this.sccc = sccc;
    }


    /**
     * Gets the sccpm value for this BurResume.
     * 
     * @return sccpm
     */
    public com.soa.model.businessobject.Vtbur_sccpm[] getSccpm() {
        return sccpm;
    }


    /**
     * Sets the sccpm value for this BurResume.
     * 
     * @param sccpm
     */
    public void setSccpm(com.soa.model.businessobject.Vtbur_sccpm[] sccpm) {
        this.sccpm = sccpm;
    }


    /**
     * Gets the scoreIcc value for this BurResume.
     * 
     * @return scoreIcc
     */
    public java.lang.String getScoreIcc() {
        return scoreIcc;
    }


    /**
     * Sets the scoreIcc value for this BurResume.
     * 
     * @param scoreIcc
     */
    public void setScoreIcc(java.lang.String scoreIcc) {
        this.scoreIcc = scoreIcc;
    }


    /**
     * Gets the score_buro value for this BurResume.
     * 
     * @return score_buro
     */
    public java.lang.String getScore_buro() {
        return score_buro;
    }


    /**
     * Sets the score_buro value for this BurResume.
     * 
     * @param score_buro
     */
    public void setScore_buro(java.lang.String score_buro) {
        this.score_buro = score_buro;
    }


    /**
     * Gets the spca value for this BurResume.
     * 
     * @return spca
     */
    public com.soa.model.businessobject.Vtbur_spca[] getSpca() {
        return spca;
    }


    /**
     * Sets the spca value for this BurResume.
     * 
     * @param spca
     */
    public void setSpca(com.soa.model.businessobject.Vtbur_spca[] spca) {
        this.spca = spca;
    }


    /**
     * Gets the tipoConsulta value for this BurResume.
     * 
     * @return tipoConsulta
     */
    public java.lang.String getTipoConsulta() {
        return tipoConsulta;
    }


    /**
     * Sets the tipoConsulta value for this BurResume.
     * 
     * @param tipoConsulta
     */
    public void setTipoConsulta(java.lang.String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BurResume)) return false;
        BurResume other = (BurResume) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aplicogarantia==null && other.getAplicogarantia()==null) || 
             (this.aplicogarantia!=null &&
              this.aplicogarantia.equals(other.getAplicogarantia()))) &&
            ((this.burFol==null && other.getBurFol()==null) || 
             (this.burFol!=null &&
              this.burFol.equals(other.getBurFol()))) &&
            ((this.burSolNumber==null && other.getBurSolNumber()==null) || 
             (this.burSolNumber!=null &&
              this.burSolNumber.equals(other.getBurSolNumber()))) &&
            ((this.bur_solnum==null && other.getBur_solnum()==null) || 
             (this.bur_solnum!=null &&
              this.bur_solnum.equals(other.getBur_solnum()))) &&
            ((this.codemandado==null && other.getCodemandado()==null) || 
             (this.codemandado!=null &&
              this.codemandado.equals(other.getCodemandado()))) &&
            ((this.condonaciones==null && other.getCondonaciones()==null) || 
             (this.condonaciones!=null &&
              this.condonaciones.equals(other.getCondonaciones()))) &&
            ((this.credito_max_oto==null && other.getCredito_max_oto()==null) || 
             (this.credito_max_oto!=null &&
              this.credito_max_oto.equals(other.getCredito_max_oto()))) &&
            ((this.credito_max_oto_c==null && other.getCredito_max_oto_c()==null) || 
             (this.credito_max_oto_c!=null &&
              this.credito_max_oto_c.equals(other.getCredito_max_oto_c()))) &&
            ((this.credito_max_oto_nc==null && other.getCredito_max_oto_nc()==null) || 
             (this.credito_max_oto_nc!=null &&
              this.credito_max_oto_nc.equals(other.getCredito_max_oto_nc()))) &&
            ((this.credito_maximo_uti==null && other.getCredito_maximo_uti()==null) || 
             (this.credito_maximo_uti!=null &&
              this.credito_maximo_uti.equals(other.getCredito_maximo_uti()))) &&
            ((this.credito_maximo_uti_c==null && other.getCredito_maximo_uti_c()==null) || 
             (this.credito_maximo_uti_c!=null &&
              this.credito_maximo_uti_c.equals(other.getCredito_maximo_uti_c()))) &&
            ((this.credito_maximo_uti_nc==null && other.getCredito_maximo_uti_nc()==null) || 
             (this.credito_maximo_uti_nc!=null &&
              this.credito_maximo_uti_nc.equals(other.getCredito_maximo_uti_nc()))) &&
            ((this.creditos==null && other.getCreditos()==null) || 
             (this.creditos!=null &&
              this.creditos.equals(other.getCreditos()))) &&
            ((this.creditos_c==null && other.getCreditos_c()==null) || 
             (this.creditos_c!=null &&
              this.creditos_c.equals(other.getCreditos_c()))) &&
            ((this.creditos_despacho==null && other.getCreditos_despacho()==null) || 
             (this.creditos_despacho!=null &&
              this.creditos_despacho.equals(other.getCreditos_despacho()))) &&
            ((this.creditos_nc==null && other.getCreditos_nc()==null) || 
             (this.creditos_nc!=null &&
              this.creditos_nc.equals(other.getCreditos_nc()))) &&
            ((this.cta_cgarantia==null && other.getCta_cgarantia()==null) || 
             (this.cta_cgarantia!=null &&
              this.cta_cgarantia.equals(other.getCta_cgarantia()))) &&
            ((this.ctas_noactivas==null && other.getCtas_noactivas()==null) || 
             (this.ctas_noactivas!=null &&
              this.ctas_noactivas.equals(other.getCtas_noactivas()))) &&
            ((this.fecConsulta==null && other.getFecConsulta()==null) || 
             (this.fecConsulta!=null &&
              this.fecConsulta.equals(other.getFecConsulta()))) &&
            ((this.fec_credantiguo==null && other.getFec_credantiguo()==null) || 
             (this.fec_credantiguo!=null &&
              this.fec_credantiguo.equals(other.getFec_credantiguo()))) &&
            ((this.fecha_despachoreciente==null && other.getFecha_despachoreciente()==null) || 
             (this.fecha_despachoreciente!=null &&
              this.fecha_despachoreciente.equals(other.getFecha_despachoreciente()))) &&
            ((this.fianzas==null && other.getFianzas()==null) || 
             (this.fianzas!=null &&
              this.fianzas.equals(other.getFianzas()))) &&
            ((this.fraudes==null && other.getFraudes()==null) || 
             (this.fraudes!=null &&
              this.fraudes.equals(other.getFraudes()))) &&
            ((this.fraudesnoatribuible==null && other.getFraudesnoatribuible()==null) || 
             (this.fraudesnoatribuible!=null &&
              this.fraudesnoatribuible.equals(other.getFraudesnoatribuible()))) &&
            ((this.idcliente==null && other.getIdcliente()==null) || 
             (this.idcliente!=null &&
              this.idcliente.equals(other.getIdcliente()))) &&
            ((this.idprospecto==null && other.getIdprospecto()==null) || 
             (this.idprospecto!=null &&
              this.idprospecto.equals(other.getIdprospecto()))) &&
            ((this.maxoto==null && other.getMaxoto()==null) || 
             (this.maxoto!=null &&
              java.util.Arrays.equals(this.maxoto, other.getMaxoto()))) &&
            ((this.monto_pagar==null && other.getMonto_pagar()==null) || 
             (this.monto_pagar!=null &&
              this.monto_pagar.equals(other.getMonto_pagar()))) &&
            ((this.monto_pagar_c==null && other.getMonto_pagar_c()==null) || 
             (this.monto_pagar_c!=null &&
              this.monto_pagar_c.equals(other.getMonto_pagar_c()))) &&
            ((this.monto_pagar_nc==null && other.getMonto_pagar_nc()==null) || 
             (this.monto_pagar_nc!=null &&
              this.monto_pagar_nc.equals(other.getMonto_pagar_nc()))) &&
            ((this.nolocalizable==null && other.getNolocalizable()==null) || 
             (this.nolocalizable!=null &&
              this.nolocalizable.equals(other.getNolocalizable()))) &&
            ((this.numsols==null && other.getNumsols()==null) || 
             (this.numsols!=null &&
              this.numsols.equals(other.getNumsols()))) &&
            ((this.quebrantos==null && other.getQuebrantos()==null) || 
             (this.quebrantos!=null &&
              this.quebrantos.equals(other.getQuebrantos()))) &&
            ((this.reestructura==null && other.getReestructura()==null) || 
             (this.reestructura!=null &&
              this.reestructura.equals(other.getReestructura()))) &&
            ((this.roboidentidad==null && other.getRoboidentidad()==null) || 
             (this.roboidentidad!=null &&
              this.roboidentidad.equals(other.getRoboidentidad()))) &&
            ((this.saldo_actual==null && other.getSaldo_actual()==null) || 
             (this.saldo_actual!=null &&
              this.saldo_actual.equals(other.getSaldo_actual()))) &&
            ((this.saldo_actual_c==null && other.getSaldo_actual_c()==null) || 
             (this.saldo_actual_c!=null &&
              this.saldo_actual_c.equals(other.getSaldo_actual_c()))) &&
            ((this.saldo_actual_nc==null && other.getSaldo_actual_nc()==null) || 
             (this.saldo_actual_nc!=null &&
              this.saldo_actual_nc.equals(other.getSaldo_actual_nc()))) &&
            ((this.scaa==null && other.getScaa()==null) || 
             (this.scaa!=null &&
              java.util.Arrays.equals(this.scaa, other.getScaa()))) &&
            ((this.scag==null && other.getScag()==null) || 
             (this.scag!=null &&
              java.util.Arrays.equals(this.scag, other.getScag()))) &&
            ((this.scapm==null && other.getScapm()==null) || 
             (this.scapm!=null &&
              java.util.Arrays.equals(this.scapm, other.getScapm()))) &&
            ((this.sccc==null && other.getSccc()==null) || 
             (this.sccc!=null &&
              java.util.Arrays.equals(this.sccc, other.getSccc()))) &&
            ((this.sccpm==null && other.getSccpm()==null) || 
             (this.sccpm!=null &&
              java.util.Arrays.equals(this.sccpm, other.getSccpm()))) &&
            ((this.scoreIcc==null && other.getScoreIcc()==null) || 
             (this.scoreIcc!=null &&
              this.scoreIcc.equals(other.getScoreIcc()))) &&
            ((this.score_buro==null && other.getScore_buro()==null) || 
             (this.score_buro!=null &&
              this.score_buro.equals(other.getScore_buro()))) &&
            ((this.spca==null && other.getSpca()==null) || 
             (this.spca!=null &&
              java.util.Arrays.equals(this.spca, other.getSpca()))) &&
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
        if (getAplicogarantia() != null) {
            _hashCode += getAplicogarantia().hashCode();
        }
        if (getBurFol() != null) {
            _hashCode += getBurFol().hashCode();
        }
        if (getBurSolNumber() != null) {
            _hashCode += getBurSolNumber().hashCode();
        }
        if (getBur_solnum() != null) {
            _hashCode += getBur_solnum().hashCode();
        }
        if (getCodemandado() != null) {
            _hashCode += getCodemandado().hashCode();
        }
        if (getCondonaciones() != null) {
            _hashCode += getCondonaciones().hashCode();
        }
        if (getCredito_max_oto() != null) {
            _hashCode += getCredito_max_oto().hashCode();
        }
        if (getCredito_max_oto_c() != null) {
            _hashCode += getCredito_max_oto_c().hashCode();
        }
        if (getCredito_max_oto_nc() != null) {
            _hashCode += getCredito_max_oto_nc().hashCode();
        }
        if (getCredito_maximo_uti() != null) {
            _hashCode += getCredito_maximo_uti().hashCode();
        }
        if (getCredito_maximo_uti_c() != null) {
            _hashCode += getCredito_maximo_uti_c().hashCode();
        }
        if (getCredito_maximo_uti_nc() != null) {
            _hashCode += getCredito_maximo_uti_nc().hashCode();
        }
        if (getCreditos() != null) {
            _hashCode += getCreditos().hashCode();
        }
        if (getCreditos_c() != null) {
            _hashCode += getCreditos_c().hashCode();
        }
        if (getCreditos_despacho() != null) {
            _hashCode += getCreditos_despacho().hashCode();
        }
        if (getCreditos_nc() != null) {
            _hashCode += getCreditos_nc().hashCode();
        }
        if (getCta_cgarantia() != null) {
            _hashCode += getCta_cgarantia().hashCode();
        }
        if (getCtas_noactivas() != null) {
            _hashCode += getCtas_noactivas().hashCode();
        }
        if (getFecConsulta() != null) {
            _hashCode += getFecConsulta().hashCode();
        }
        if (getFec_credantiguo() != null) {
            _hashCode += getFec_credantiguo().hashCode();
        }
        if (getFecha_despachoreciente() != null) {
            _hashCode += getFecha_despachoreciente().hashCode();
        }
        if (getFianzas() != null) {
            _hashCode += getFianzas().hashCode();
        }
        if (getFraudes() != null) {
            _hashCode += getFraudes().hashCode();
        }
        if (getFraudesnoatribuible() != null) {
            _hashCode += getFraudesnoatribuible().hashCode();
        }
        if (getIdcliente() != null) {
            _hashCode += getIdcliente().hashCode();
        }
        if (getIdprospecto() != null) {
            _hashCode += getIdprospecto().hashCode();
        }
        if (getMaxoto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMaxoto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMaxoto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMonto_pagar() != null) {
            _hashCode += getMonto_pagar().hashCode();
        }
        if (getMonto_pagar_c() != null) {
            _hashCode += getMonto_pagar_c().hashCode();
        }
        if (getMonto_pagar_nc() != null) {
            _hashCode += getMonto_pagar_nc().hashCode();
        }
        if (getNolocalizable() != null) {
            _hashCode += getNolocalizable().hashCode();
        }
        if (getNumsols() != null) {
            _hashCode += getNumsols().hashCode();
        }
        if (getQuebrantos() != null) {
            _hashCode += getQuebrantos().hashCode();
        }
        if (getReestructura() != null) {
            _hashCode += getReestructura().hashCode();
        }
        if (getRoboidentidad() != null) {
            _hashCode += getRoboidentidad().hashCode();
        }
        if (getSaldo_actual() != null) {
            _hashCode += getSaldo_actual().hashCode();
        }
        if (getSaldo_actual_c() != null) {
            _hashCode += getSaldo_actual_c().hashCode();
        }
        if (getSaldo_actual_nc() != null) {
            _hashCode += getSaldo_actual_nc().hashCode();
        }
        if (getScaa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScaa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScaa(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScag() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScag());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScag(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScapm() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScapm());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScapm(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSccc() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSccc());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSccc(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSccpm() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSccpm());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSccpm(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScoreIcc() != null) {
            _hashCode += getScoreIcc().hashCode();
        }
        if (getScore_buro() != null) {
            _hashCode += getScore_buro().hashCode();
        }
        if (getSpca() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSpca());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSpca(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipoConsulta() != null) {
            _hashCode += getTipoConsulta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BurResume.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "BurResume"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicogarantia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "aplicogarantia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burFol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "burFol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burSolNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "burSolNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bur_solnum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "bur_solnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codemandado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "codemandado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("condonaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "condonaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_max_oto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_max_oto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_max_oto_c");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_max_oto_c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_max_oto_nc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_max_oto_nc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_maximo_uti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_maximo_uti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_maximo_uti_c");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_maximo_uti_c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credito_maximo_uti_nc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "credito_maximo_uti_nc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "creditos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditos_c");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "creditos_c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditos_despacho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "creditos_despacho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditos_nc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "creditos_nc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cta_cgarantia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "cta_cgarantia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctas_noactivas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "ctas_noactivas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fecConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_credantiguo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fec_credantiguo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_despachoreciente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fecha_despachoreciente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fianzas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fianzas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fraudes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fraudes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fraudesnoatribuible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "fraudesnoatribuible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idcliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "idcliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idprospecto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "idprospecto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxoto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "maxoto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vbur_maxoto"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto_pagar");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "monto_pagar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto_pagar_c");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "monto_pagar_c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto_pagar_nc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "monto_pagar_nc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nolocalizable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "nolocalizable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numsols");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "numsols"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quebrantos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "quebrantos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reestructura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "reestructura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roboidentidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "roboidentidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldo_actual");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "saldo_actual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldo_actual_c");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "saldo_actual_c"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldo_actual_nc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "saldo_actual_nc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scaa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "scaa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scaa"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "scag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scag"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scapm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "scapm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_scapm"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sccc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "sccc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccc"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sccpm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "sccpm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_sccpm"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scoreIcc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "scoreIcc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score_buro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "score_buro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spca");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "spca"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobject.model.soa.com", "Vtbur_spca"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webServices.soa.com", "item"));
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
