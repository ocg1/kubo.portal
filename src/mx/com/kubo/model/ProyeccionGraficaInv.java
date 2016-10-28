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
			resultClass = ProyeccionGraficaInv.class,
			name = "collectorProyeccionGraficaInv",    
			 
			query = "call microfin.PROYECCIONGRAFICALISK( :CuentaAhoID )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ProyeccionGraficaInv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProyeccionGraficaInvPK pk;
	@Column(name="SaldoDisponible")  
	private Double saldoDisponible;
	@Column(name="CapitalGanado")
	private Double capitalGanado;
	@Column(name="CapitalPerdido")
	private Double capitalPerdido;
	@Column(name="InteresGanado")
	private Double interesGanado;
	@Column(name="SaldoInversiones")
	private Double saldoInversiones;
	@Column(name="DepositosMes")
	private Double depositosMes;
	@Column(name="RetirosMes")
	private Double retirosMes;
	
	public ProyeccionGraficaInvPK getPk() {
		return pk;
	}
	public void setPk(ProyeccionGraficaInvPK pk) {
		this.pk = pk;
	}
	public Double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public Double getCapitalGanado() {
		return capitalGanado;
	}
	public void setCapitalGanado(Double capitalGanado) {
		this.capitalGanado = capitalGanado;
	}
	public Double getCapitalPerdido() {
		return capitalPerdido;
	}
	public void setCapitalPerdido(Double capitalPerdido) {
		this.capitalPerdido = capitalPerdido;
	}
	public Double getInteresGanado() {
		return interesGanado;
	}
	public void setInteresGanado(Double interesGanado) {
		this.interesGanado = interesGanado;
	}
	public Double getSaldoInversiones() {
		return saldoInversiones;
	}
	public void setSaldoInversiones(Double saldoInversiones) {
		this.saldoInversiones = saldoInversiones;
	}
	public Double getDepositosMes() {
		return depositosMes;
	}
	public void setDepositosMes(Double depositosMes) {
		this.depositosMes = depositosMes;
	}
	public Double getRetirosMes() {
		return retirosMes;
	}
	public void setRetirosMes(Double retirosMes) {
		this.retirosMes = retirosMes;
	}
	
}
