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
					resultClass = InfoCalifPorc.class,
					name		= "getInfoCalifPorc",
					query 		= "call KUBOGRAFICASCON(:safi_client_id,:cuentaAhoID,1 )",
					hints		= {
									@QueryHint(name="org.hibernate.callable", value="true")
								  }
			)
		})
		

@Entity
public class InfoCalifPorc implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	private String  calificacion;
	@Column
	private Integer numFondeos;
	@Column
	private Double porcCalificacion;
	@Column
	private Double promTasa;
	
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public Integer getNumFondeos() {
		return numFondeos;
	}
	public void setNumFondeos(Integer numFondeos) {
		this.numFondeos = numFondeos;
	}
	public Double getPorcCalificacion() {
		return porcCalificacion;
	}
	public void setPorcCalificacion(Double porcCalificacion) {
		this.porcCalificacion = porcCalificacion;
	}
	public Double getPromTasa() {
		return promTasa;
	}
	public void setPromTasa(Double promTasa) {
		this.promTasa = promTasa;
	}
	
	
}
