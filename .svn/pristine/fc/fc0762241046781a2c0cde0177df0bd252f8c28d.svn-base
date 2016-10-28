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
			resultClass = RetornoAnual.class,
			name = "collectorRetornoAnual",    
			 
			query = "call microfin.RACONK( :CuentaAhoID )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class RetornoAnual implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="CuentaAhoID")
	private int cuentaAhoID;
	@Column(name="Ra")
	private double ra; 
	@Column(name="RaAjus")
	private double raAjus;
	
	
	public int getCuentaAhoID() {
		return cuentaAhoID;
	}
	public void setCuentaAhoID(int cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}
	public double getRa() {
		return ra;
	}
	public void setRa(double ra) {
		this.ra = ra;
	}
	public double getRaAjus() {
		return raAjus;
	}
	public void setRaAjus(double raAjus) {
		this.raAjus = raAjus;
	} 
	
	
}
