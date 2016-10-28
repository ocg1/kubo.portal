package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = RendimientosInv.class,
			name = "collectorRendimientosInv",    
			 
			query = "call microfin.RENDIMIENTOSLISK( :CuentaAhoID )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class RendimientosInv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RendimientosInvPK pk;
	
	@Column(name="MontoInvertidoProm")
	private Double montoInvertidoProm;
	@Column(name="SaldoDisponibleProm")
	private Double saldoDisponibleProm;
	@Column(name="InteresMensual")
	private Double interesMensual;
	@Column(name="PorcInversion")
	private Double porcInversion;
	@Column(name="InteresMensulAcum")
	private Double interesMensulAcum;
	@Column(name="RendimientoMensual")
	private Double rendimientoMensual;
	@Column(name="RendimientoMenAcum")
	private Double rendimientoMenAcum;
	
	public RendimientosInvPK getPk() {
		return pk;
	}
	public void setPk(RendimientosInvPK pk) {
		this.pk = pk;
	}
	public Double getMontoInvertidoProm() {
		return montoInvertidoProm;
	}
	public void setMontoInvertidoProm(Double montoInvertidoProm) {
		this.montoInvertidoProm = montoInvertidoProm;
	}
	public Double getSaldoDisponibleProm() {
		return saldoDisponibleProm;
	}
	public void setSaldoDisponibleProm(Double saldoDisponibleProm) {
		this.saldoDisponibleProm = saldoDisponibleProm;
	}
	public Double getInteresMensual() {
		return interesMensual;
	}
	public void setInteresMensual(Double interesMensual) {
		this.interesMensual = interesMensual;
	}
	public Double getPorcInversion() {
		return porcInversion;
	}
	public void setPorcInversion(Double porcInversion) {
		this.porcInversion = porcInversion;
	}
	public Double getInteresMensulAcum() {
		return interesMensulAcum;
	}
	public void setInteresMensulAcum(Double interesMensulAcum) {
		this.interesMensulAcum = interesMensulAcum;
	}
	public Double getRendimientoMensual() {
		return rendimientoMensual;
	}
	public void setRendimientoMensual(Double rendimientoMensual) {
		this.rendimientoMensual = rendimientoMensual;
	}
	public Double getRendimientoMenAcum() {
		return rendimientoMenAcum;
	}
	public void setRendimientoMenAcum(Double rendimientoMenAcum) {
		this.rendimientoMenAcum = rendimientoMenAcum;
	}

	
}
