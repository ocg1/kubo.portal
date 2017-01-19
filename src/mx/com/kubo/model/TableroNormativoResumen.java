package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = TableroNormativoResumen.class,
			name = "ResumenTableroNormativo",
			query = "call microfin.LIMITENORMATIVOCON(null , 2)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})

@Entity
public class TableroNormativoResumen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column
	private Double capitalNeto;// decimal(12,,2)
	@Column
	private Double totalCartera;// decimal(12,2)
	@Column(name="PasivoCortoPlazo")
	private Double pasivoCortoPlazo;// decimal(12,2)
	@Column(name="MontoPasivoMenor")
	private Double montoPasivoMenor;// decimal(12,2)
	@Column
	private Integer numCreEmpleados;// int
	@Column
	private Integer numOperacionesRel;// int
	@Column
	private Integer autorizaComite;// int
	@Column
	private Integer autorizaConsejo;// int
	@Column
	private Integer autorizaParentesco;// int
	@Column(name="MontoPersonaRel")
	private Double montoPersonaRel;// decimal(12,2)
	@Column(name="PorcentajePersRel")
	private Double porcentajePersRel;// decimal(5,2)
	@Column(name="DescripPersRel")
	private String descripPersRel;// varchar(40)
	@Column(name="EstatusPersRel")
	private String estatusPersRel;// varchar(20)
	@Column(name="Monto20CredMax")
	private Double monto20CredMax;// decimal(12,2)
	@Column(name="Porc20CredMax")
	private Double porc20CredMax;// decimal(5,2)
	@Column(name="Descrip20CreMax")
	private String descrip20CreMax;// varchar(40)
	@Column(name="Estatus20CreMax")
	private String estatus20CreMax;// varchar(20)
	@Column(name="MontoCreditoGde")
	private Double montoCreditoGde;// decimal(12,2)
	@Column(name="PorcCreditoGde")
	private Double porcCreditoGde;// decimal(5,2)
	@Column(name="DescripCredGde")
	private String descripCredGde;// varchar(40)
	@Column(name="EstatusCreditoGde")
	private String estatusCreditoGde;// varchar(20)
	@Column(name="AplicaIdentif")
	private String aplicaIdentif;// int
	@Column(name="Monto60Meses")
	private Double monto60Meses;// decimal(12,2)
	@Column(name="Porcentaje60meses")
	private Double porcentaje60meses;// decimal(5,2)
	@Column(name="Descrip60Meses")
	private String descrip60Meses;// varchar(40)
	@Column(name="Estatus60Meses")
	private String estatus60Meses;// varchar(20)
	@Column(name="Monto30Anios")
	private Double monto30Anios;// decimal(12,2)
	@Column(name="Porcentaje30Anios")
	private Double porcentaje30Anios;// decimal(5,2)
	@Column(name="Descrip30Anios")
	private String descrip30Anios;// varchar(40)
	@Column(name="Estatus30Anios")
	private String estatus30Anios;// varchar(20)
	@Column(name="MontoLiquidez")
	private Double montoLiquidez;// decimal(12,2)
	@Column(name="PorcLiquidez")
	private Double porcLiquidez;// decimal(5,2)
	@Column
	private String descripLiquidez;// varchar(40)
	@Column(name="EstatusLiquidez")
	private String estatusLiquidez;// varchar(20)
	@Column(name="MontoCreditoMax")
	private Double montoCreditoMax;// decimal(12,2)
	@Column(name="PorcCreditoMax")
	private Double porcCreditoMax;// decimal(5,2)
	@Column(name="DescripCredMax")
	private String descripCredMax;// varchar(40)
	@Column(name="EstatusCredMax")
	private String estatusCredMax;// varchar(20)
	public Double getCapitalNeto() {
		return capitalNeto;
	}
	public void setCapitalNeto(Double capitalNeto) {
		this.capitalNeto = capitalNeto;
	}
	public Double getTotalCartera() {
		return totalCartera;
	}
	public void setTotalCartera(Double totalCartera) {
		this.totalCartera = totalCartera;
	}
	public Double getPasivoCortoPlazo() {
		return pasivoCortoPlazo;
	}
	public void setPasivoCortoPlazo(Double pasivoCortoPlazo) {
		this.pasivoCortoPlazo = pasivoCortoPlazo;
	}
	public Double getMontoPasivoMenor() {
		return montoPasivoMenor;
	}
	public void setMontoPasivoMenor(Double montoPasivoMenor) {
		this.montoPasivoMenor = montoPasivoMenor;
	}
	public Integer getNumCreEmpleados() {
		return numCreEmpleados;
	}
	public void setNumCreEmpleados(Integer numCreEmpleados) {
		this.numCreEmpleados = numCreEmpleados;
	}
	public Integer getNumOperacionesRel() {
		return numOperacionesRel;
	}
	public void setNumOperacionesRel(Integer numOperacionesRel) {
		this.numOperacionesRel = numOperacionesRel;
	}
	public Integer getAutorizaComite() {
		return autorizaComite;
	}
	public void setAutorizaComite(Integer autorizaComite) {
		this.autorizaComite = autorizaComite;
	}
	public Integer getAutorizaConsejo() {
		return autorizaConsejo;
	}
	public void setAutorizaConsejo(Integer autorizaConsejo) {
		this.autorizaConsejo = autorizaConsejo;
	}
	public Integer getAutorizaParentesco() {
		return autorizaParentesco;
	}
	public void setAutorizaParentesco(Integer autorizaParentesco) {
		this.autorizaParentesco = autorizaParentesco;
	}
	public Double getMontoPersonaRel() {
		return montoPersonaRel;
	}
	public void setMontoPersonaRel(Double montoPersonaRel) {
		this.montoPersonaRel = montoPersonaRel;
	}
	public Double getPorcentajePersRel() {
		return porcentajePersRel;
	}
	public void setPorcentajePersRel(Double porcentajePersRel) {
		this.porcentajePersRel = porcentajePersRel;
	}
	public String getDescripPersRel() {
		return descripPersRel;
	}
	public void setDescripPersRel(String descripPersRel) {
		this.descripPersRel = descripPersRel;
	}
	public String getEstatusPersRel() {
		return estatusPersRel;
	}
	public void setEstatusPersRel(String estatusPersRel) {
		this.estatusPersRel = estatusPersRel;
	}
	public Double getMonto20CredMax() {
		return monto20CredMax;
	}
	public void setMonto20CredMax(Double monto20CredMax) {
		this.monto20CredMax = monto20CredMax;
	}
	public Double getPorc20CredMax() {
		return porc20CredMax;
	}
	public void setPorc20CredMax(Double porc20CredMax) {
		this.porc20CredMax = porc20CredMax;
	}
	public String getDescrip20CreMax() {
		return descrip20CreMax;
	}
	public void setDescrip20CreMax(String descrip20CreMax) {
		this.descrip20CreMax = descrip20CreMax;
	}
	public String getEstatus20CreMax() {
		return estatus20CreMax;
	}
	public void setEstatus20CreMax(String estatus20CreMax) {
		this.estatus20CreMax = estatus20CreMax;
	}
	public Double getMontoCreditoGde() {
		return montoCreditoGde;
	}
	public void setMontoCreditoGde(Double montoCreditoGde) {
		this.montoCreditoGde = montoCreditoGde;
	}
	public Double getPorcCreditoGde() {
		return porcCreditoGde;
	}
	public void setPorcCreditoGde(Double porcCreditoGde) {
		this.porcCreditoGde = porcCreditoGde;
	}
	public String getDescripCredGde() {
		return descripCredGde;
	}
	public void setDescripCredGde(String descripCredGde) {
		this.descripCredGde = descripCredGde;
	}
	public String getEstatusCreditoGde() {
		return estatusCreditoGde;
	}
	public void setEstatusCreditoGde(String estatusCreditoGde) {
		this.estatusCreditoGde = estatusCreditoGde;
	}
	public String getAplicaIdentif() {
		return aplicaIdentif;
	}
	public void setAplicaIdentif(String aplicaIdentif) {
		this.aplicaIdentif = aplicaIdentif;
	}
	public Double getMonto60Meses() {
		return monto60Meses;
	}
	public void setMonto60Meses(Double monto60Meses) {
		this.monto60Meses = monto60Meses;
	}
	public Double getPorcentaje60meses() {
		return porcentaje60meses;
	}
	public void setPorcentaje60meses(Double porcentaje60meses) {
		this.porcentaje60meses = porcentaje60meses;
	}
	public String getDescrip60Meses() {
		return descrip60Meses;
	}
	public void setDescrip60Meses(String descrip60Meses) {
		this.descrip60Meses = descrip60Meses;
	}
	public String getEstatus60Meses() {
		return estatus60Meses;
	}
	public void setEstatus60Meses(String estatus60Meses) {
		this.estatus60Meses = estatus60Meses;
	}
	public Double getMonto30Anios() {
		return monto30Anios;
	}
	public void setMonto30Anios(Double monto30Anios) {
		this.monto30Anios = monto30Anios;
	}
	public Double getPorcentaje30Anios() {
		return porcentaje30Anios;
	}
	public void setPorcentaje30Anios(Double porcentaje30Anios) {
		this.porcentaje30Anios = porcentaje30Anios;
	}
	public String getDescrip30Anios() {
		return descrip30Anios;
	}
	public void setDescrip30Anios(String descrip30Anios) {
		this.descrip30Anios = descrip30Anios;
	}
	public String getEstatus30Anios() {
		return estatus30Anios;
	}
	public void setEstatus30Anios(String estatus30Anios) {
		this.estatus30Anios = estatus30Anios;
	}
	public Double getMontoLiquidez() {
		return montoLiquidez;
	}
	public void setMontoLiquidez(Double montoLiquidez) {
		this.montoLiquidez = montoLiquidez;
	}
	public Double getPorcLiquidez() {
		return porcLiquidez;
	}
	public void setPorcLiquidez(Double porcLiquidez) {
		this.porcLiquidez = porcLiquidez;
	}
	public String getDescripLiquidez() {
		return descripLiquidez;
	}
	public void setDescripLiquidez(String descripLiquidez) {
		this.descripLiquidez = descripLiquidez;
	}
	public String getEstatusLiquidez() {
		return estatusLiquidez;
	}
	public void setEstatusLiquidez(String estatusLiquidez) {
		this.estatusLiquidez = estatusLiquidez;
	}
	public Double getMontoCreditoMax() {
		return montoCreditoMax;
	}
	public void setMontoCreditoMax(Double montoCreditoMax) {
		this.montoCreditoMax = montoCreditoMax;
	}
	public Double getPorcCreditoMax() {
		return porcCreditoMax;
	}
	public void setPorcCreditoMax(Double porcCreditoMax) {
		this.porcCreditoMax = porcCreditoMax;
	}
	public String getDescripCredMax() {
		return descripCredMax;
	}
	public void setDescripCredMax(String descripCredMax) {
		this.descripCredMax = descripCredMax;
	}
	public String getEstatusCredMax() {
		return estatusCredMax;
	}
	public void setEstatusCredMax(String estatusCredMax) {
		this.estatusCredMax = estatusCredMax;
	}
	
}