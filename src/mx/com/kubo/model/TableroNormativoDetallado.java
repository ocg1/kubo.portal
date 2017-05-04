package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = TableroNormativoDetallado.class,
			name = "DetalleTableroNormativo",
			query = "call microfin.LIMITENORMATIVOCON(:param_proyect_loan_id , 1)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})

@Entity
public class TableroNormativoDetallado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="CapitalNeto")
	private Double capitalNeto;// decimal(12,2)
	@Column(name="TotalCartera")
	private Double totalCartera;// decimal(12,2)
	@Column(name="MontoPersonaRel")
	private Double montoPersonaRel;// decimal(12,2)
	@Column(name="PorcentajePersRel")
	private Double porcentajePersRel;// decimal(5,2)
	@Column(name="DescripPersRel")
	private String descripPersRel;// varchar(40)
	@Column(name="EstatusPersRel")
	private String estatusPersRel;// varchar(20)
	@Column(name="Monto60Meses")
	private Double monto60Meses;// decimal(12,2)
	@Column(name="Porcentaje60meses")
	private Double porcentaje60meses;// decimal(5,2)
	@Column(name="Descrip60Meses")
	private String descrip60Meses;// varchar(40)
	@Column(name="Estatus60Meses")
	private String estatus60Meses; // varchar(20)
	@Column(name="Monto30Anios")
	private Double monto30Anios;// decimal(12,2)
	@Column(name="Porcentaje30Anios")
	private Double porcentaje30Anios;// decimal(5,2)
	@Column(name="Descrip30Anios")
	private String descrip30Anios;// varchar(40)
	@Column(name="Estatus30Anios")
	private String estatus30Anios;// varchar(20)
	@Column(name="MontoCreditoMax")
	private Double montoCreditoMax;// decimal(12,2)
	@Column(name="PorcCreditoMax")
	private Double porcCreditoMax;// decimal(5,2)
	@Column(name="DescripCredMax")
	private String descripCredMax;// varchar(40)
	@Column(name="EstatusCredMax")
	private String estatusCredMax;// varchar(20)
	@Column(name="AplicaIdentif")
	private String aplicaIdentif;// char(1)
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
	@Column(name="RequiereParent")
	private String requiereParent;// char(1)
	@Column(name="MontoSolicitud")
	private Double montoSolicitud;// decimal(12,2)
	@Column(name="PorcSolicitud")
	private Double porcSolicitud;// decimal(5,2)
	@Column(name="DescripSolicitud")
	private String descripSolicitud;// varchar(40)
	@Column(name="EstatusSolicitud")
	private String estatusSolicitud;// varchar(20)
	@Column(name="PasivoCortoPlazo")
	private Double pasivoCortoPlazo;// decimal(12,2)
	@Column(name="MontoPasivoMenor")
	private Double montoPasivoMenor;// decimal(12,2)
	@Column(name="MontoLiquidez")
	private Double montoLiquidez; // decimal(12,2)
	@Column(name="PorcLiquidez")
	private Double porcLiquidez;// decimal(5,2)
	@Column(name="descripLiquidez")
	private String descripLiquidez;// varchar(40)
	@Column(name="EstatusLiquidez")
	private String estatusLiquidez;// varchar(20)
	@Column(name="FechaInforme")
	private Date fechaInforme; // date
	@Column(name="Var_has_identification")	//		varchar(40);
	private String has_identification ;
	@Column(name="Var_has_consulting")	// 				varchar(40);
	private String has_consulting ;
	@Column(name="Var_has_income")	// 					varchar(40);
	private String has_income ;
	@Column(name="Var_has_address")	// 				varchar(40);
	private String has_address;
	@Column(name="Var_has_note")	// 					varchar(40);
	private String has_note;
	@Column(name="Var_has_proyect_loan")	// 			varchar(40);
	private String has_proyect_loan;
	@Column(name="Var_has_warranty")	// 				varchar(40);
	private String has_warranty;
	
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
	public String getAplicaIdentif() {
		return aplicaIdentif;
	}
	public void setAplicaIdentif(String aplicaIdentif) {
		this.aplicaIdentif = aplicaIdentif;
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
	public String getRequiereParent() {
		return requiereParent;
	}
	public void setRequiereParent(String requiereParent) {
		this.requiereParent = requiereParent;
	}
	public Double getMontoSolicitud() {
		return montoSolicitud;
	}
	public void setMontoSolicitud(Double montoSolicitud) {
		this.montoSolicitud = montoSolicitud;
	}
	public Double getPorcSolicitud() {
		return porcSolicitud;
	}
	public void setPorcSolicitud(Double porcSolicitud) {
		this.porcSolicitud = porcSolicitud;
	}
	public String getDescripSolicitud() {
		return descripSolicitud;
	}
	public void setDescripSolicitud(String descripSolicitud) {
		this.descripSolicitud = descripSolicitud;
	}
	public String getEstatusSolicitud() {
		return estatusSolicitud;
	}
	public void setEstatusSolicitud(String estatusSolicitud) {
		this.estatusSolicitud = estatusSolicitud;
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
	public Date getFechaInforme() {
		return fechaInforme;
	}
	public void setFechaInforme(Date fechaInforme) {
		this.fechaInforme = fechaInforme;
	}
	public String getHas_identification() {
		return has_identification;
	}
	public void setHas_identification(String has_identification) {
		this.has_identification = has_identification;
	}
	public String getHas_consulting() {
		return has_consulting;
	}
	public void setHas_consulting(String has_consulting) {
		this.has_consulting = has_consulting;
	}
	public String getHas_income() {
		return has_income;
	}
	public void setHas_income(String has_income) {
		this.has_income = has_income;
	}
	public String getHas_address() {
		return has_address;
	}
	public void setHas_address(String has_address) {
		this.has_address = has_address;
	}
	public String getHas_note() {
		return has_note;
	}
	public void setHas_note(String has_note) {
		this.has_note = has_note;
	}
	public String getHas_proyect_loan() {
		return has_proyect_loan;
	}
	public void setHas_proyect_loan(String has_proyect_loan) {
		this.has_proyect_loan = has_proyect_loan;
	}
	public String getHas_warranty() {
		return has_warranty;
	}
	public void setHas_warranty(String has_warranty) {
		this.has_warranty = has_warranty;
	}

}
