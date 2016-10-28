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
			resultClass = ClientesEnMora.class,
			name = "cobranzaClientesMoraQuery",
			query = "call CALL_COBRANZA_MORA(:event_id)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	),
	@NamedNativeQuery(
			resultClass = ClientesEnMora.class,
			name = "cobranzaClientesMoraProspectoQuery",
			query = "call CALL_COBRANZA_MORA_PROSPECTO(:prospectus_id)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})


@Entity
public class ClientesEnMora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="referencia")
	private String referencia;
	@Column(name="prospectus_id")
	private Integer prospectus_id;
	@Column(name="PrimerNombre")
	private String primernombre;
	@Column(name="celular")
	private String celular;
	@Column(name="DiasAtraso")
	private String dias;
	@Column(name="PagoExigible")
	private String pagoexigible;
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getPrimernombre() {
		return primernombre;
	}
	public void setPrimernombre(String primernombre) {
		this.primernombre = primernombre;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public String getPagoexigible() {
		return pagoexigible;
	}
	public void setPagoexigible(String pagoexigible) {
		this.pagoexigible = pagoexigible;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
	
	
}
