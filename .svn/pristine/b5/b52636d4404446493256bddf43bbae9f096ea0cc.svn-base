package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = PagareCollector.class,
			name = "pagareCollectorData",    
			 
			//query = "call 	microfin.AMORTICREDITOCON(Par_CreditoID			int,Par_NumCon			tinyint unsigned,Par_EmpresaID			int,Aud_Usuario			int,Aud_FechaActual		DateTime,Aud_DireccionIP		varchar(15),Aud_ProgramaID		varchar(50),Aud_Sucursal			int,Aud_NumTransaccion		bigint
			
			query = "call 	microfin.AMORTICREDITOCON(:par_CreditoID,:par_NumCon,:par_EmpresaID,:aud_Usuario,:aud_FechaActual,:aud_DireccionIP,:aud_ProgramaID,:aud_Sucursal,:aud_NumTransaccion)" ,
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class PagareCollector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="AmortizacionID")
	private Integer amortizacionID;
	@Column(name="FechaInicio")
	private Date fechaInicio;
	@Column(name="FechaVencim")
	private Date fechaVencim;
	@Column(name="FechaExigible")
	private Date fechaExigible;
	@Column(name="montoCuota")
	private Double montoCuota;
	@Column(name="Interes")
	private Double interes;
	@Column(name="IVAInteres")
	private Double iVAInteres;
	@Column(name="Capital")
	private Double capital;
	@Column(name="DireccionInstitu")
	private String direccionInstitu;
	@Column(name="NombreInstitu")
	private String nombreInstitu;
	@Column(name="NombreCliente")
	private String nombreCliente;
	@Column(name="Var_TasaFija")
	private Double var_TasaFija;	
	@Column(name="FORMAT(MontoCred,2)")	
	private String montoCred;
	@Column(name="FechaVencCred")
	private Date fechaVencCred;
	@Column(name="fechaPTF")
	private Date fechaPTF;
	@Column(name="Sucurs")
	private Date sucurs;
	@Column(name="FactorM")
	private Double factorM;
	@Column(name="FechaMinis")
	private Date fechaMinis;
	@Column(name="DireccionCte")
	private String direccionCte;
	@Column(name="CreditoID")
	private BigInteger creditoID;
	@Column(name="SaldoCapital")
	private Double saldoCapital	;
	@Column(name="TasaFijaTexto")
	private String tasaFijaTexto;
	
	public Integer getAmortizacionID() {
		return amortizacionID;
	}
	public void setAmortizacionID(Integer amortizacionID) {
		this.amortizacionID = amortizacionID;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaVencim() {
		return fechaVencim;
	}
	public void setFechaVencim(Date fechaVencim) {
		this.fechaVencim = fechaVencim;
	}
	public Date getFechaExigible() {
		return fechaExigible;
	}
	public void setFechaExigible(Date fechaExigible) {
		this.fechaExigible = fechaExigible;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	public Double getiVAInteres() {
		return iVAInteres;
	}
	public void setiVAInteres(Double iVAInteres) {
		this.iVAInteres = iVAInteres;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	public String getDireccionInstitu() {
		return direccionInstitu;
	}
	public void setDireccionInstitu(String direccionInstitu) {
		this.direccionInstitu = direccionInstitu;
	}
	public String getNombreInstitu() {
		return nombreInstitu;
	}
	public void setNombreInstitu(String nombreInstitu) {
		this.nombreInstitu = nombreInstitu;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Double getVar_TasaFija() {
		return var_TasaFija;
	}
	public void setVar_TasaFija(Double var_TasaFija) {
		this.var_TasaFija = var_TasaFija;
	}
	public Date getFechaVencCred() {
		return fechaVencCred;
	}
	public void setFechaVencCred(Date fechaVencCred) {
		this.fechaVencCred = fechaVencCred;
	}
	public Date getFechaPTF() {
		return fechaPTF;
	}
	public void setFechaPTF(Date fechaPTF) {
		this.fechaPTF = fechaPTF;
	}
	public Date getSucurs() {
		return sucurs;
	}
	public void setSucurs(Date sucurs) {
		this.sucurs = sucurs;
	}
	public Double getFactorM() {
		return factorM;
	}
	public void setFactorM(Double factorM) {
		this.factorM = factorM;
	}
	public Date getFechaMinis() {
		return fechaMinis;
	}
	public void setFechaMinis(Date fechaMinis) {
		this.fechaMinis = fechaMinis;
	}
	public String getDireccionCte() {
		return direccionCte;
	}
	public void setDireccionCte(String direccionCte) {
		this.direccionCte = direccionCte;
	}
	public BigInteger getCreditoID() {
		return creditoID;
	}
	public void setCreditoID(BigInteger creditoID) {
		this.creditoID = creditoID;
	}
	public Double getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(Double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public String getMontoCred() {
		return montoCred;
	}
	public void setMontoCred(String montoCred) {
		this.montoCred = montoCred;
	}
	public String getTasaFijaTexto() {
		return tasaFijaTexto;
	}
	public void setTasaFijaTexto(String tasaFijaTexto) {
		this.tasaFijaTexto = tasaFijaTexto;
	}					
	
}
