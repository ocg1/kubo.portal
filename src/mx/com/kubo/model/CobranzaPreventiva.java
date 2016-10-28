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
			resultClass = CobranzaPreventiva.class,
			name = "cobranzaPreventivaQuery",
			query = "call CALL_COBRANZA_PREVENTIVA(:fecha)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	),
	@NamedNativeQuery(
			resultClass = CobranzaPreventiva.class,
			name = "cobranzaPreventivaProspectoQuery",
			query = "call CALL_COBRANZA_PREVENTIVA_PROSPECTO(:fecha,:prospectus_id)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})


@Entity
public class CobranzaPreventiva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column
	private Integer prospectus_id;
	@Column
	private String celular;
	@Column(name="PrimerNombre")
	private String primerNombre;
	@Column(name="Cuota")
	private String cuota;
	@Column
	private String referencia;
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
}
