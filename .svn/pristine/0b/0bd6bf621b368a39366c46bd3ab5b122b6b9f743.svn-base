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
			resultClass = CreCertSegrepCollector.class,
			name = "creCertSegrepCollectorData",    
			 
			//query = "call microfin.CRECERTSEGREP(Par_CreditoID int(12),Par_NomInstitucion varchar(200),Par_NumCon tinyint unsigned,Par_EmpresaID int,
			//		Aud_Usuario int,Aud_FechaActual DateTime,Aud_DireccionIP varchar(15),Aud_ProgramaID varchar(50),Aud_Sucursal int,Aud_NumTransaccion bigint	)
			
			query = "call microfin.CRECERTSEGREP(:par_CreditoID,:par_NomInstitucion,:par_NumCon,:par_EmpresaID,:aud_Usuario,:aud_FechaActual,:aud_DireccionIP,:aud_ProgramaID,:aud_Sucursal,:aud_NumTransaccion )" ,
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})


@Entity
public class CreCertSegrepCollector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Var_FechaInicio")
	private Date  fechaInicio; //  	date;	
	@Column(name="Var_NombreCompleto")
	private String nombreCompleto; //  	varchar(50);	
	@Column(name="Var_ApellidoPaterno")
	private String apellidoPaterno; //  varchar(50);		
	@Column(name="Var_ApellidoMaterno")
	private String apellidoMaterno; //  varchar(50);		
	@Column(name="Var_PrimerNombre")
	private String primerNombre; // 	varchar(50);	
	@Column(name="Var_SegundoNombre")
	private String segundoNombre; // 	varchar(50);	
	@Column(name="Var_TercerNombre")
	private String tercerNombre; // 	varchar(50);	
	@Column(name="Var_FechaNacimiento")
	private String fechaNacimiento; // 	varchar(12);	
	@Column(name="Var_PolizaIni")
	private String polizaIni; // 		varchar(12);
	@Column(name="Var_Contratante")
	private String contratante	; // 	varchar(50);
	@Column(name="Var_PolizaVenc")
	private String polizaVenc	; // 	varchar(12);
	@Column(name="Var_PolizaID")
	private String polizaID	; // 	varchar(50);
	@Column(name="Var_MontoCredito")
	private String montoCredito; // 	varchar(15);	
	@Column(name="Var_DireccionCompleta")
	private String direccionCompleta; // 	varchar(200);	
	@Column(name="Var_CantidadLetras")
	private String cantidadLetras; // 	varchar(200);	
	@Column(name="Var_CtaBanamex")
	private String ctaBanamex; // 		varchar(50);
	@Column(name="Var_BanamexSuc")
	private String banamexSuc; //  		varchar(50);
	@Column(name="Var_ClabeBanorte")
	private String clabeBanorte; // 	varchar(50);	
	@Column(name="Var_BanorteConv")
	private String banorteConv	; // 	varchar(50);
	@Column(name="Var_ElevenEmp")
	private String elevenEmp; // 		varchar(50);
	@Column(name="Var_ElevenConv")
	private String elevenConv; // 		varchar(50);
	@Column(name="Var_MontoCuota")
	private Double montoCuota; // 		decimal(12,2);
	@Column(name="Var_Frecuencia")
	private String frecuencia; // 		VARCHAR(50);
	@Column(name="Var_ComGralDep")
	private Integer comGralDep	; // 	int(11);
	@Column(name="RefrenciaBanamex")
	private String  refrenciaBanamex; // 	varchar(17);	
	@Column(name="ReferenciaBanorte")
	private String  referenciaBanorte; // 	varchar(17);	
	@Column(name="Var_FechaFirmaContrato")
	private String fechaFirmaContrato; // 	varchar(10);	
	@Column(name="Var_VigenciaSeguro")
	private String vigenciaSeguro	; // 	varchar(10);
	
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getTercerNombre() {
		return tercerNombre;
	}
	public void setTercerNombre(String tercerNombre) {
		this.tercerNombre = tercerNombre;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPolizaIni() {
		return polizaIni;
	}
	public void setPolizaIni(String polizaIni) {
		this.polizaIni = polizaIni;
	}
	public String getContratante() {
		return contratante;
	}
	public void setContratante(String contratante) {
		this.contratante = contratante;
	}
	public String getPolizaVenc() {
		return polizaVenc;
	}
	public void setPolizaVenc(String polizaVenc) {
		this.polizaVenc = polizaVenc;
	}
	public String getPolizaID() {
		return polizaID;
	}
	public void setPolizaID(String polizaID) {
		this.polizaID = polizaID;
	}
	public String getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(String montoCredito) {
		this.montoCredito = montoCredito;
	}
	public String getDireccionCompleta() {
		return direccionCompleta;
	}
	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}
	public String getCantidadLetras() {
		return cantidadLetras;
	}
	public void setCantidadLetras(String cantidadLetras) {
		this.cantidadLetras = cantidadLetras;
	}
	public String getCtaBanamex() {
		return ctaBanamex;
	}
	public void setCtaBanamex(String ctaBanamex) {
		this.ctaBanamex = ctaBanamex;
	}
	public String getBanamexSuc() {
		return banamexSuc;
	}
	public void setBanamexSuc(String banamexSuc) {
		this.banamexSuc = banamexSuc;
	}
	public String getClabeBanorte() {
		return clabeBanorte;
	}
	public void setClabeBanorte(String clabeBanorte) {
		this.clabeBanorte = clabeBanorte;
	}
	public String getBanorteConv() {
		return banorteConv;
	}
	public void setBanorteConv(String banorteConv) {
		this.banorteConv = banorteConv;
	}
	public String getElevenEmp() {
		return elevenEmp;
	}
	public void setElevenEmp(String elevenEmp) {
		this.elevenEmp = elevenEmp;
	}
	public String getElevenConv() {
		return elevenConv;
	}
	public void setElevenConv(String elevenConv) {
		this.elevenConv = elevenConv;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public Integer getComGralDep() {
		return comGralDep;
	}
	public void setComGralDep(Integer comGralDep) {
		this.comGralDep = comGralDep;
	}
	public String getRefrenciaBanamex() {
		return refrenciaBanamex;
	}
	public void setRefrenciaBanamex(String refrenciaBanamex) {
		this.refrenciaBanamex = refrenciaBanamex;
	}
	public String getReferenciaBanorte() {
		return referenciaBanorte;
	}
	public void setReferenciaBanorte(String referenciaBanorte) {
		this.referenciaBanorte = referenciaBanorte;
	}
	public String getFechaFirmaContrato() {
		return fechaFirmaContrato;
	}
	public void setFechaFirmaContrato(String fechaFirmaContrato) {
		this.fechaFirmaContrato = fechaFirmaContrato;
	}
	public String getVigenciaSeguro() {
		return vigenciaSeguro;
	}
	public void setVigenciaSeguro(String vigenciaSeguro) {
		this.vigenciaSeguro = vigenciaSeguro;
	}

	

}
