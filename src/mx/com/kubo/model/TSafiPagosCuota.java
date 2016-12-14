package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="historicos.TSAFI_PAGOS_CUOTA")
public class TSafiPagosCuota implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TSafiPagosCuotaPK pk;
	
	@Column(name="PROSPECTOIDEXT")
	private Integer prospectoExt;
	@Column(name="FECHAEXIGIBLE")
	private Date fechaExigible;
	@Column(name="FECHALIQUIDA")
	private Date fechaLiquida;
	@Column(name="ESTATUS")
	private String estatus;
	@Column(name="CUOTA") 
	private Double cuota;
	@Column(name="CUOTA_MAS_COM")
	private Double cuotaMasCom;
	@Column(name="CAPITAL")
	private Double capital;
	@Column(name="INTERES")
	private Double interes;
	@Column(name="IVAINTERES")
	private Double ivaInteres;
	@Column(name="FECHA_CIERRE") 
	private Date fechaCierre;
	@Column(name="FECHA_DIA")
	private Date FechaDia;
	
	public TSafiPagosCuotaPK getPk() {
		return pk;
	}
	public void setPk(TSafiPagosCuotaPK pk) {
		this.pk = pk;
	}
	public Integer getProspectoExt() {
		return prospectoExt;
	}
	public void setProspectoExt(Integer prospectoExt) {
		this.prospectoExt = prospectoExt;
	}
	public Date getFechaExigible() {
		return fechaExigible;
	}
	public void setFechaExigible(Date fechaExigible) {
		this.fechaExigible = fechaExigible;
	}
	public Date getFechaLiquida() {
		return fechaLiquida;
	}
	public void setFechaLiquida(Date fechaLiquida) {
		this.fechaLiquida = fechaLiquida;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Double getCuotaMasCom() {
		return cuotaMasCom;
	}
	public void setCuotaMasCom(Double cuotaMasCom) {
		this.cuotaMasCom = cuotaMasCom;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	public Double getIvaInteres() {
		return ivaInteres;
	}
	public void setIvaInteres(Double ivaInteres) {
		this.ivaInteres = ivaInteres;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Date getFechaDia() {
		return FechaDia;
	}
	public void setFechaDia(Date fechaDia) {
		FechaDia = fechaDia;
	}
	
}
