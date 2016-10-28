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
			resultClass = ProyectSaldosEdoCta.class,
			name = "getSaldosEdoCtaClient",    
			query = "call SALDOSINVPLAZOFIJOCON(:CuentaAhoID)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ProyectSaldosEdoCta  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int CuentaAhoID;
	
	@Column
	private int NumInvActivas;
	
	@Column 
	private  Double MontoInversiones;
	
	@Column
	private Double IntARecibir;
	
	@Column (name="Var_KuboGlobal")
	private Double SaldoIntGlobal;
	
	@Column (name="Var_KuboPlazoFijo")
	private Double SaldoIntPlazoFijo;
	
	@Column
	private Double InteresCobrado;
	
	@Column
	private Double MoraCobrado;

	public int getCuentaAhoID() {
		return CuentaAhoID;
	}
	public void setCuentaAhoID(int cuentaAhoID) {
		CuentaAhoID = cuentaAhoID;
	}
	public int getNumInvActivas() {
		return NumInvActivas;
	}
	public void setNumInvActivas(int numInvActivas) {
		NumInvActivas = numInvActivas;
	}
	public Double getMontoInversiones() {
		return MontoInversiones;
	}
	public void setMontoInversiones(Double montoInversiones) {
		MontoInversiones = montoInversiones;
	}
	public Double getIntARecibir() {
		return IntARecibir;
	}
	public void setIntARecibir(Double intARecibir) {
		IntARecibir = intARecibir;
	}
	public Double getSaldoIntGlobal() {
		return SaldoIntGlobal;
	}
	public void setSaldoIntGlobal(Double saldoIntGlobal) {
		SaldoIntGlobal = saldoIntGlobal;
	}
	public Double getSaldoIntPlazoFijo() {
		return SaldoIntPlazoFijo;
	}
	public void setSaldoIntPlazoFijo(Double saldoIntPlazoFijo) {
		SaldoIntPlazoFijo = saldoIntPlazoFijo;
	}
	public Double getInteresCobrado() {
		return InteresCobrado;
	}
	public void setInteresCobrado(Double interesCobrado) {
		InteresCobrado = interesCobrado;
	}
	public Double getMoraCobrado() {
		return MoraCobrado;
	}
	public void setMoraCobrado(Double moraCobrado) {
		MoraCobrado = moraCobrado;
	}	
}
