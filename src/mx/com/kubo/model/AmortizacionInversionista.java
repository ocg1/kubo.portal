package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="microfin.AMORTIZAFONDEO")
public class AmortizacionInversionista implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private AmortizacionInversionistaPK pk;
	
	@Column(name="FechaInicio")
	private Date fechaInicio;// 		date
	@Column(name="FechaVencimiento")
	private Date fechaVencimiento;// 		date
	@Column(name="FechaExigible")
	private Date fechaExigible;// 		date
	@Column(name="Capital")
	private Double capital;// 		decimal(14,2)
	@Column(name="InteresGenerado")
	private Double interesGenerado;// 		decimal(14,2)
	@Column(name="InteresRetener")
	private Double interesRetener;// 		decimal(14,2)
	@Column(name="PorcentajeInteres")
	private Double porcentajeInteres;// 		decimal(9,6)
	@Column(name="PorcentajeCapital")
	private Double porcentajeCapital;// 		decimal(9,6)
	@Column(name="Estatus")
	private String estatus;// 		char(1)
	@Column(name="SaldoCapVigente")
	private Double saldoCapVigente;// 		decimal(12,2)
	@Column(name="SaldoCapExigible")
	private Double saldoCapExigible	;// 	decimal(12,2)
	@Column(name="SaldoInteres")
	private Double saldoInteres	;// 	decimal(14,4)
	@Column(name="ProvisionAcum")
	private Double provisionAcum;// 		decimal(14,4)
	@Column(name="RetencionIntAcum")
	private Double retencionIntAcum	;// 	decimal(14,4)
	@Column(name="MoratorioPagado")
	private Double moratorioPagado;// 		decimal(12,2)
	@Column(name="ComFalPagPagada")
	private Double comFalPagPagada;// 		decimal(12,2)
	@Column(name="IntOrdRetenido")
	private Double intOrdRetenido;// 		decimal(12,2)
	@Column(name="IntMorRetenido")
	private Double intMorRetenido;// 		decimal(12,2)
	@Column(name="ComFalPagRetenido")
	private Double comFalPagRetenido;// 		decimal(12,2)
	@Column(name="FechaLiquida")
	private Date fechaLiquida;// 		date
	@Column(name="EmpresaID")
	private Integer empresaID;// 		int(11)
	@Column(name="Usuario")
	private Integer usuario;// 		 int(11)
	@Column(name="FechaActual")
	private Date fechaActual;// 		datetime
	@Column(name="DireccionIP")
	private String direccionIP;// 		varchar(15)
	@Column(name="ProgramaID")
	private String programaID;// 		varchar(50)
	@Column(name="Sucursal")
	private Integer sucursal;// 		int(11)
	@Column(name="NumTransaccion")
	private Integer numTransaccion;// 		bigint(20)
	
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Date getFechaExigible() {
		return fechaExigible;
	}
	public void setFechaExigible(Date fechaExigible) {
		this.fechaExigible = fechaExigible;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	public Double getInteresGenerado() {
		return interesGenerado;
	}
	public void setInteresGenerado(Double interesGenerado) {
		this.interesGenerado = interesGenerado;
	}
	public Double getInteresRetener() {
		return interesRetener;
	}
	public void setInteresRetener(Double interesRetener) {
		this.interesRetener = interesRetener;
	}
	public Double getPorcentajeInteres() {
		return porcentajeInteres;
	}
	public void setPorcentajeInteres(Double porcentajeInteres) {
		this.porcentajeInteres = porcentajeInteres;
	}
	public Double getPorcentajeCapital() {
		return porcentajeCapital;
	}
	public void setPorcentajeCapital(Double porcentajeCapital) {
		this.porcentajeCapital = porcentajeCapital;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getSaldoCapVigente() {
		return saldoCapVigente;
	}
	public void setSaldoCapVigente(Double saldoCapVigente) {
		this.saldoCapVigente = saldoCapVigente;
	}
	public Double getSaldoCapExigible() {
		return saldoCapExigible;
	}
	public void setSaldoCapExigible(Double saldoCapExigible) {
		this.saldoCapExigible = saldoCapExigible;
	}
	public Double getSaldoInteres() {
		return saldoInteres;
	}
	public void setSaldoInteres(Double saldoInteres) {
		this.saldoInteres = saldoInteres;
	}
	public Double getProvisionAcum() {
		return provisionAcum;
	}
	public void setProvisionAcum(Double provisionAcum) {
		this.provisionAcum = provisionAcum;
	}
	public Double getRetencionIntAcum() {
		return retencionIntAcum;
	}
	public void setRetencionIntAcum(Double retencionIntAcum) {
		this.retencionIntAcum = retencionIntAcum;
	}
	public Double getMoratorioPagado() {
		return moratorioPagado;
	}
	public void setMoratorioPagado(Double moratorioPagado) {
		this.moratorioPagado = moratorioPagado;
	}
	public Double getComFalPagPagada() {
		return comFalPagPagada;
	}
	public void setComFalPagPagada(Double comFalPagPagada) {
		this.comFalPagPagada = comFalPagPagada;
	}
	public Double getIntOrdRetenido() {
		return intOrdRetenido;
	}
	public void setIntOrdRetenido(Double intOrdRetenido) {
		this.intOrdRetenido = intOrdRetenido;
	}
	public Double getIntMorRetenido() {
		return intMorRetenido;
	}
	public void setIntMorRetenido(Double intMorRetenido) {
		this.intMorRetenido = intMorRetenido;
	}
	public Double getComFalPagRetenido() {
		return comFalPagRetenido;
	}
	public void setComFalPagRetenido(Double comFalPagRetenido) {
		this.comFalPagRetenido = comFalPagRetenido;
	}
	public Date getFechaLiquida() {
		return fechaLiquida;
	}
	public void setFechaLiquida(Date fechaLiquida) {
		this.fechaLiquida = fechaLiquida;
	}
	public Integer getEmpresaID() {
		return empresaID;
	}
	public void setEmpresaID(Integer empresaID) {
		this.empresaID = empresaID;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public String getDireccionIP() {
		return direccionIP;
	}
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}
	public String getProgramaID() {
		return programaID;
	}
	public void setProgramaID(String programaID) {
		this.programaID = programaID;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	public Integer getNumTransaccion() {
		return numTransaccion;
	}
	public void setNumTransaccion(Integer numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public AmortizacionInversionistaPK getPk() {
		return pk;
	}
	public void setPk(AmortizacionInversionistaPK pk) {
		this.pk = pk;
	}
	
}
