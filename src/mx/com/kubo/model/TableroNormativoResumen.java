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
	
	@Column(name="Var_Total_Creditos")
	private Integer Total_Creditos			;
	@Column(name="Var_ident_CumpleVigencia")
	private Integer ident_CumpleVigencia	;
	@Column(name="Var_ident_CumpleLegibilidad")
	private Integer ident_CumpleLegibilidad	;
	@Column(name="Var_ident_FueValidado")
	private Integer ident_FueValidado		;
	@Column(name="Var_ident_No_CumpleVigencia")
	private Integer ident_No_CumpleVigencia	;
	@Column(name="Var_ident_No_CumpleLegibilidad")
	private Integer ident_No_CumpleLegibilidad;
	@Column(name="Var_ident_No_FueValidado")
	private Integer ident_No_FueValidado	;
	@Column(name="Var_ident_Revisados")
	private Integer ident_Revisados			;
	@Column(name="Var_ident_ConObservaciones")
	private Integer ident_ConObservaciones	;
	
	@Column(name="Var_sol_CumpleVigencia")
	private Integer sol_CumpleVigencia	;
	@Column(name="Var_sol_CumpleLegibilidad")
	private Integer sol_CumpleLegibilidad	;
	@Column(name="Var_sol_FueValidado")
	private Integer sol_FueValidado		;
	@Column(name="Var_sol_No_CumpleVigencia")
	private Integer sol_No_CumpleVigencia	;
	@Column(name="Var_sol_No_CumpleLegibilidad")
	private Integer sol_No_CumpleLegibilidad;
	@Column(name="Var_sol_No_FueValidado")
	private Integer sol_No_FueValidado	;
	@Column(name="Var_sol_Revisados")
	private Integer sol_Revisados			;
	@Column(name="Var_sol_ConObservaciones")
	private Integer sol_ConObservaciones	;
	
	@Column(name="Var_ing_CumpleVigencia")
	private Integer ing_CumpleVigencia	;
	@Column(name="Var_ing_CumpleLegibilidad")
	private Integer ing_CumpleLegibilidad	;
	@Column(name="Var_ing_FueValidado")
	private Integer ing_FueValidado		;
	@Column(name="Var_ing_No_CumpleVigencia")
	private Integer ing_No_CumpleVigencia	;
	@Column(name="Var_ing_No_CumpleLegibilidad")
	private Integer ing_No_CumpleLegibilidad;
	@Column(name="Var_ing_No_FueValidado")
	private Integer ing_No_FueValidado	;
	@Column(name="Var_ing_Revisados")
	private Integer ing_Revisados			;
	@Column(name="Var_ing_ConObservaciones")
	private Integer ing_ConObservaciones	;
	
	@Column(name="Var_buro_CumpleVigencia")
	private Integer buro_CumpleVigencia	;
	@Column(name="Var_buro_CumpleLegibilidad")
	private Integer buro_CumpleLegibilidad	;
	@Column(name="Var_buro_FueValidado")
	private Integer buro_FueValidado		;
	@Column(name="Var_buro_No_CumpleVigencia")
	private Integer buro_No_CumpleVigencia	;
	@Column(name="Var_buro_No_CumpleLegibilidad")
	private Integer buro_No_CumpleLegibilidad;
	@Column(name="Var_buro_No_FueValidado")
	private Integer buro_No_FueValidado	;
	@Column(name="Var_buro_Revisados")
	private Integer buro_Revisados			;
	@Column(name="Var_buro_ConObservaciones")
	private Integer buro_ConObservaciones	;
	
	@Column(name="Var_domi_CumpleVigencia")
	private Integer domi_CumpleVigencia	;
	@Column(name="Var_domi_CumpleLegibilidad")
	private Integer domi_CumpleLegibilidad	;
	@Column(name="Var_domi_FueValidado")
	private Integer domi_FueValidado		;
	@Column(name="Var_domi_No_CumpleVigencia")
	private Integer domi_No_CumpleVigencia	;
	@Column(name="Var_domi_No_CumpleLegibilidad")
	private Integer domi_No_CumpleLegibilidad;
	@Column(name="Var_domi_No_FueValidado")
	private Integer domi_No_FueValidado	;
	@Column(name="Var_domi_Revisados")
	private Integer domi_Revisados			;
	@Column(name="Var_domi_ConObservaciones")
	private Integer domi_ConObservaciones	;
	
	@Column(name="Var_gar_CumpleVigencia")
	private Integer gar_CumpleVigencia	;
	@Column(name="Var_gar_CumpleLegibilidad")
	private Integer gar_CumpleLegibilidad	;
	@Column(name="Var_gar_FueValidado")
	private Integer gar_FueValidado		;
	@Column(name="Var_gar_No_CumpleVigencia")
	private Integer gar_No_CumpleVigencia	;
	@Column(name="Var_gar_No_CumpleLegibilidad")
	private Integer gar_No_CumpleLegibilidad;
	@Column(name="Var_gar_No_FueValidado")
	private Integer gar_No_FueValidado	;
	@Column(name="Var_gar_Revisados")
	private Integer gar_Revisados			;
	@Column(name="Var_gar_ConObservaciones")
	private Integer gar_ConObservaciones	;
	
	@Column(name="Var_ent_CumpleVigencia")
	private Integer ent_CumpleVigencia	;
	@Column(name="Var_ent_CumpleLegibilidad")
	private Integer ent_CumpleLegibilidad	;
	@Column(name="Var_ent_FueValidado")
	private Integer ent_FueValidado		;
	@Column(name="Var_ent_No_CumpleVigencia")
	private Integer ent_No_CumpleVigencia	;
	@Column(name="Var_ent_No_CumpleLegibilidad")
	private Integer ent_No_CumpleLegibilidad;
	@Column(name="Var_ent_No_FueValidado")
	private Integer ent_No_FueValidado	;
	@Column(name="Var_ent_Revisados")
	private Integer ent_Revisados			;
	@Column(name="Var_ent_ConObservaciones")
	private Integer ent_ConObservaciones	;
	
	
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
	public Integer getTotal_Creditos() {
		return Total_Creditos;
	}
	public void setTotal_Creditos(Integer total_Creditos) {
		Total_Creditos = total_Creditos;
	}
	public Integer getIdent_CumpleVigencia() {
		return ident_CumpleVigencia;
	}
	public void setIdent_CumpleVigencia(Integer ident_CumpleVigencia) {
		this.ident_CumpleVigencia = ident_CumpleVigencia;
	}
	public Integer getIdent_CumpleLegibilidad() {
		return ident_CumpleLegibilidad;
	}
	public void setIdent_CumpleLegibilidad(Integer ident_CumpleLegibilidad) {
		this.ident_CumpleLegibilidad = ident_CumpleLegibilidad;
	}
	public Integer getIdent_FueValidado() {
		return ident_FueValidado;
	}
	public void setIdent_FueValidado(Integer ident_FueValidado) {
		this.ident_FueValidado = ident_FueValidado;
	}
	public Integer getIdent_No_CumpleVigencia() {
		return ident_No_CumpleVigencia;
	}
	public void setIdent_No_CumpleVigencia(Integer ident_No_CumpleVigencia) {
		this.ident_No_CumpleVigencia = ident_No_CumpleVigencia;
	}
	public Integer getIdent_No_CumpleLegibilidad() {
		return ident_No_CumpleLegibilidad;
	}
	public void setIdent_No_CumpleLegibilidad(Integer ident_No_CumpleLegibilidad) {
		this.ident_No_CumpleLegibilidad = ident_No_CumpleLegibilidad;
	}
	public Integer getIdent_No_FueValidado() {
		return ident_No_FueValidado;
	}
	public void setIdent_No_FueValidado(Integer ident_No_FueValidado) {
		this.ident_No_FueValidado = ident_No_FueValidado;
	}
	public Integer getIdent_Revisados() {
		return ident_Revisados;
	}
	public void setIdent_Revisados(Integer ident_Revisados) {
		this.ident_Revisados = ident_Revisados;
	}
	public Integer getIdent_ConObservaciones() {
		return ident_ConObservaciones;
	}
	public void setIdent_ConObservaciones(Integer ident_ConObservaciones) {
		this.ident_ConObservaciones = ident_ConObservaciones;
	}
	public Integer getSol_CumpleVigencia() {
		return sol_CumpleVigencia;
	}
	public void setSol_CumpleVigencia(Integer sol_CumpleVigencia) {
		this.sol_CumpleVigencia = sol_CumpleVigencia;
	}
	public Integer getSol_CumpleLegibilidad() {
		return sol_CumpleLegibilidad;
	}
	public void setSol_CumpleLegibilidad(Integer sol_CumpleLegibilidad) {
		this.sol_CumpleLegibilidad = sol_CumpleLegibilidad;
	}
	public Integer getSol_FueValidado() {
		return sol_FueValidado;
	}
	public void setSol_FueValidado(Integer sol_FueValidado) {
		this.sol_FueValidado = sol_FueValidado;
	}
	public Integer getSol_No_CumpleVigencia() {
		return sol_No_CumpleVigencia;
	}
	public void setSol_No_CumpleVigencia(Integer sol_No_CumpleVigencia) {
		this.sol_No_CumpleVigencia = sol_No_CumpleVigencia;
	}
	public Integer getSol_No_CumpleLegibilidad() {
		return sol_No_CumpleLegibilidad;
	}
	public void setSol_No_CumpleLegibilidad(Integer sol_No_CumpleLegibilidad) {
		this.sol_No_CumpleLegibilidad = sol_No_CumpleLegibilidad;
	}
	public Integer getSol_No_FueValidado() {
		return sol_No_FueValidado;
	}
	public void setSol_No_FueValidado(Integer sol_No_FueValidado) {
		this.sol_No_FueValidado = sol_No_FueValidado;
	}
	public Integer getSol_Revisados() {
		return sol_Revisados;
	}
	public void setSol_Revisados(Integer sol_Revisados) {
		this.sol_Revisados = sol_Revisados;
	}
	public Integer getSol_ConObservaciones() {
		return sol_ConObservaciones;
	}
	public void setSol_ConObservaciones(Integer sol_ConObservaciones) {
		this.sol_ConObservaciones = sol_ConObservaciones;
	}
	public Integer getIng_CumpleVigencia() {
		return ing_CumpleVigencia;
	}
	public void setIng_CumpleVigencia(Integer ing_CumpleVigencia) {
		this.ing_CumpleVigencia = ing_CumpleVigencia;
	}
	public Integer getIng_CumpleLegibilidad() {
		return ing_CumpleLegibilidad;
	}
	public void setIng_CumpleLegibilidad(Integer ing_CumpleLegibilidad) {
		this.ing_CumpleLegibilidad = ing_CumpleLegibilidad;
	}
	public Integer getIng_FueValidado() {
		return ing_FueValidado;
	}
	public void setIng_FueValidado(Integer ing_FueValidado) {
		this.ing_FueValidado = ing_FueValidado;
	}
	public Integer getIng_No_CumpleVigencia() {
		return ing_No_CumpleVigencia;
	}
	public void setIng_No_CumpleVigencia(Integer ing_No_CumpleVigencia) {
		this.ing_No_CumpleVigencia = ing_No_CumpleVigencia;
	}
	public Integer getIng_No_CumpleLegibilidad() {
		return ing_No_CumpleLegibilidad;
	}
	public void setIng_No_CumpleLegibilidad(Integer ing_No_CumpleLegibilidad) {
		this.ing_No_CumpleLegibilidad = ing_No_CumpleLegibilidad;
	}
	public Integer getIng_No_FueValidado() {
		return ing_No_FueValidado;
	}
	public void setIng_No_FueValidado(Integer ing_No_FueValidado) {
		this.ing_No_FueValidado = ing_No_FueValidado;
	}
	public Integer getIng_Revisados() {
		return ing_Revisados;
	}
	public void setIng_Revisados(Integer ing_Revisados) {
		this.ing_Revisados = ing_Revisados;
	}
	public Integer getIng_ConObservaciones() {
		return ing_ConObservaciones;
	}
	public void setIng_ConObservaciones(Integer ing_ConObservaciones) {
		this.ing_ConObservaciones = ing_ConObservaciones;
	}
	public Integer getBuro_CumpleVigencia() {
		return buro_CumpleVigencia;
	}
	public void setBuro_CumpleVigencia(Integer buro_CumpleVigencia) {
		this.buro_CumpleVigencia = buro_CumpleVigencia;
	}
	public Integer getBuro_CumpleLegibilidad() {
		return buro_CumpleLegibilidad;
	}
	public void setBuro_CumpleLegibilidad(Integer buro_CumpleLegibilidad) {
		this.buro_CumpleLegibilidad = buro_CumpleLegibilidad;
	}
	public Integer getBuro_FueValidado() {
		return buro_FueValidado;
	}
	public void setBuro_FueValidado(Integer buro_FueValidado) {
		this.buro_FueValidado = buro_FueValidado;
	}
	public Integer getBuro_No_CumpleVigencia() {
		return buro_No_CumpleVigencia;
	}
	public void setBuro_No_CumpleVigencia(Integer buro_No_CumpleVigencia) {
		this.buro_No_CumpleVigencia = buro_No_CumpleVigencia;
	}
	public Integer getBuro_No_CumpleLegibilidad() {
		return buro_No_CumpleLegibilidad;
	}
	public void setBuro_No_CumpleLegibilidad(Integer buro_No_CumpleLegibilidad) {
		this.buro_No_CumpleLegibilidad = buro_No_CumpleLegibilidad;
	}
	public Integer getBuro_No_FueValidado() {
		return buro_No_FueValidado;
	}
	public void setBuro_No_FueValidado(Integer buro_No_FueValidado) {
		this.buro_No_FueValidado = buro_No_FueValidado;
	}
	public Integer getBuro_Revisados() {
		return buro_Revisados;
	}
	public void setBuro_Revisados(Integer buro_Revisados) {
		this.buro_Revisados = buro_Revisados;
	}
	public Integer getBuro_ConObservaciones() {
		return buro_ConObservaciones;
	}
	public void setBuro_ConObservaciones(Integer buro_ConObservaciones) {
		this.buro_ConObservaciones = buro_ConObservaciones;
	}
	public Integer getDomi_CumpleVigencia() {
		return domi_CumpleVigencia;
	}
	public void setDomi_CumpleVigencia(Integer domi_CumpleVigencia) {
		this.domi_CumpleVigencia = domi_CumpleVigencia;
	}
	public Integer getDomi_CumpleLegibilidad() {
		return domi_CumpleLegibilidad;
	}
	public void setDomi_CumpleLegibilidad(Integer domi_CumpleLegibilidad) {
		this.domi_CumpleLegibilidad = domi_CumpleLegibilidad;
	}
	public Integer getDomi_FueValidado() {
		return domi_FueValidado;
	}
	public void setDomi_FueValidado(Integer domi_FueValidado) {
		this.domi_FueValidado = domi_FueValidado;
	}
	public Integer getDomi_No_CumpleVigencia() {
		return domi_No_CumpleVigencia;
	}
	public void setDomi_No_CumpleVigencia(Integer domi_No_CumpleVigencia) {
		this.domi_No_CumpleVigencia = domi_No_CumpleVigencia;
	}
	public Integer getDomi_No_CumpleLegibilidad() {
		return domi_No_CumpleLegibilidad;
	}
	public void setDomi_No_CumpleLegibilidad(Integer domi_No_CumpleLegibilidad) {
		this.domi_No_CumpleLegibilidad = domi_No_CumpleLegibilidad;
	}
	public Integer getDomi_No_FueValidado() {
		return domi_No_FueValidado;
	}
	public void setDomi_No_FueValidado(Integer domi_No_FueValidado) {
		this.domi_No_FueValidado = domi_No_FueValidado;
	}
	public Integer getDomi_Revisados() {
		return domi_Revisados;
	}
	public void setDomi_Revisados(Integer domi_Revisados) {
		this.domi_Revisados = domi_Revisados;
	}
	public Integer getDomi_ConObservaciones() {
		return domi_ConObservaciones;
	}
	public void setDomi_ConObservaciones(Integer domi_ConObservaciones) {
		this.domi_ConObservaciones = domi_ConObservaciones;
	}
	public Integer getGar_CumpleVigencia() {
		return gar_CumpleVigencia;
	}
	public void setGar_CumpleVigencia(Integer gar_CumpleVigencia) {
		this.gar_CumpleVigencia = gar_CumpleVigencia;
	}
	public Integer getGar_CumpleLegibilidad() {
		return gar_CumpleLegibilidad;
	}
	public void setGar_CumpleLegibilidad(Integer gar_CumpleLegibilidad) {
		this.gar_CumpleLegibilidad = gar_CumpleLegibilidad;
	}
	public Integer getGar_FueValidado() {
		return gar_FueValidado;
	}
	public void setGar_FueValidado(Integer gar_FueValidado) {
		this.gar_FueValidado = gar_FueValidado;
	}
	public Integer getGar_No_CumpleVigencia() {
		return gar_No_CumpleVigencia;
	}
	public void setGar_No_CumpleVigencia(Integer gar_No_CumpleVigencia) {
		this.gar_No_CumpleVigencia = gar_No_CumpleVigencia;
	}
	public Integer getGar_No_CumpleLegibilidad() {
		return gar_No_CumpleLegibilidad;
	}
	public void setGar_No_CumpleLegibilidad(Integer gar_No_CumpleLegibilidad) {
		this.gar_No_CumpleLegibilidad = gar_No_CumpleLegibilidad;
	}
	public Integer getGar_No_FueValidado() {
		return gar_No_FueValidado;
	}
	public void setGar_No_FueValidado(Integer gar_No_FueValidado) {
		this.gar_No_FueValidado = gar_No_FueValidado;
	}
	public Integer getGar_Revisados() {
		return gar_Revisados;
	}
	public void setGar_Revisados(Integer gar_Revisados) {
		this.gar_Revisados = gar_Revisados;
	}
	public Integer getGar_ConObservaciones() {
		return gar_ConObservaciones;
	}
	public void setGar_ConObservaciones(Integer gar_ConObservaciones) {
		this.gar_ConObservaciones = gar_ConObservaciones;
	}
	public Integer getEnt_CumpleVigencia() {
		return ent_CumpleVigencia;
	}
	public void setEnt_CumpleVigencia(Integer ent_CumpleVigencia) {
		this.ent_CumpleVigencia = ent_CumpleVigencia;
	}
	public Integer getEnt_CumpleLegibilidad() {
		return ent_CumpleLegibilidad;
	}
	public void setEnt_CumpleLegibilidad(Integer ent_CumpleLegibilidad) {
		this.ent_CumpleLegibilidad = ent_CumpleLegibilidad;
	}
	public Integer getEnt_FueValidado() {
		return ent_FueValidado;
	}
	public void setEnt_FueValidado(Integer ent_FueValidado) {
		this.ent_FueValidado = ent_FueValidado;
	}
	public Integer getEnt_No_CumpleVigencia() {
		return ent_No_CumpleVigencia;
	}
	public void setEnt_No_CumpleVigencia(Integer ent_No_CumpleVigencia) {
		this.ent_No_CumpleVigencia = ent_No_CumpleVigencia;
	}
	public Integer getEnt_No_CumpleLegibilidad() {
		return ent_No_CumpleLegibilidad;
	}
	public void setEnt_No_CumpleLegibilidad(Integer ent_No_CumpleLegibilidad) {
		this.ent_No_CumpleLegibilidad = ent_No_CumpleLegibilidad;
	}
	public Integer getEnt_No_FueValidado() {
		return ent_No_FueValidado;
	}
	public void setEnt_No_FueValidado(Integer ent_No_FueValidado) {
		this.ent_No_FueValidado = ent_No_FueValidado;
	}
	public Integer getEnt_Revisados() {
		return ent_Revisados;
	}
	public void setEnt_Revisados(Integer ent_Revisados) {
		this.ent_Revisados = ent_Revisados;
	}
	public Integer getEnt_ConObservaciones() {
		return ent_ConObservaciones;
	}
	public void setEnt_ConObservaciones(Integer ent_ConObservaciones) {
		this.ent_ConObservaciones = ent_ConObservaciones;
	}
	
}
