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
			resultClass = MontoInvertido_F_G_Collector.class,
			name = "queryMontoInvertido_FG",    
			query = "call MINIMOPORCENPORCTACON2(:safi_account)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class MontoInvertido_F_G_Collector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="Par_CuentaAhoID")
	private String cuentAhoId;
	@Column(name="MontoInvertido")
	private Double totalFondeado;
	@Column(name="NumInversiones")
	private Double numInversiones;
	@Id
	@Column(name="kubo_score")
	private String kubo_score;
	
	public String getCuentAhoId() {
		return cuentAhoId;
	}
	public void setCuentAhoId(String cuentAhoId) {
		this.cuentAhoId = cuentAhoId;
	}
	public Double getTotalFondeado() {
		return totalFondeado;
	}
	public void setTotalFondeado(Double totalFondeado) {
		this.totalFondeado = totalFondeado;
	}
	public Double getNumInversiones() {
		return numInversiones;
	}
	public void setNumInversiones(Double numInversiones) {
		this.numInversiones = numInversiones;
	}
	public String getKubo_score() {
		return kubo_score;
	}
	public void setKubo_score(String kubo_score) {
		this.kubo_score = kubo_score;
	}
	
}
