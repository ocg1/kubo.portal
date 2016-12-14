package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="historicos.TSAFI_CUENTASAHOMOVDEP")
public class TSafiCuentasAhoMovDep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUENTASAHOMOVDEP_ID")
	private BigInteger cuentasAhoMovDep_id ;
	@Column(name="CUENTAAHOID")
	private Integer cuentaAhoId;
	@Column(name="NUMEROMOV")
	private Integer numeroMov;
	@Column(name="FECHA")
	private Date fecha;
	@Column(name="NATMOVIMIENTO")
	private String natMovimiento;
	@Column(name="CANTIDADMOV")
	private Integer cantidadMov;
	@Column(name="DESCRIPCIONMOV")
	private String descripcionMov;
	@Column(name="REFERENCIAMOV")
	private String referenciaMov;
	@Column(name="CONCEPTO")
	private String concepto;
	@Column(name="MONEDA")
	private String moneda;
	@Column(name="FECHAACTUAL")
	private Date fechaActual;
	@Column(name="FECHACARGA")
	private Date fechaCarga;
	@Column(name="FECHAAPLICA")
	private Date fechaAplica;
	@Column(name="DEPOSITO")
	private Double deposito;
	@Column(name="NOMBRECORTO")
	private String nombreCorto;
	@Column(name="TIPODEPOSITO")
	private String tipoDeposito;
	
	public BigInteger getCuentasAhoMovDep_id() {
		return cuentasAhoMovDep_id;
	}
	public void setCuentasAhoMovDep_id(BigInteger cuentasAhoMovDep_id) {
		this.cuentasAhoMovDep_id = cuentasAhoMovDep_id;
	}
	public Integer getCuentaAhoId() {
		return cuentaAhoId;
	}
	public void setCuentaAhoId(Integer cuentaAhoId) {
		this.cuentaAhoId = cuentaAhoId;
	}
	public Integer getNumeroMov() {
		return numeroMov;
	}
	public void setNumeroMov(Integer numeroMov) {
		this.numeroMov = numeroMov;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNatMovimiento() {
		return natMovimiento;
	}
	public void setNatMovimiento(String natMovimiento) {
		this.natMovimiento = natMovimiento;
	}
	public Integer getCantidadMov() {
		return cantidadMov;
	}
	public void setCantidadMov(Integer cantidadMov) {
		this.cantidadMov = cantidadMov;
	}
	public String getDescripcionMov() {
		return descripcionMov;
	}
	public void setDescripcionMov(String descripcionMov) {
		this.descripcionMov = descripcionMov;
	}
	public String getReferenciaMov() {
		return referenciaMov;
	}
	public void setReferenciaMov(String referenciaMov) {
		this.referenciaMov = referenciaMov;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public Date getFechaAplica() {
		return fechaAplica;
	}
	public void setFechaAplica(Date fechaAplica) {
		this.fechaAplica = fechaAplica;
	}
	public Double getDeposito() {
		return deposito;
	}
	public void setDeposito(Double deposito) {
		this.deposito = deposito;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getTipoDeposito() {
		return tipoDeposito;
	}
	public void setTipoDeposito(String tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}
	
	
	
}
