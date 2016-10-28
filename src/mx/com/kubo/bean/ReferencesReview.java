package mx.com.kubo.bean;

import java.io.Serializable;

public class ReferencesReview implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tracking_id;
	private String nombreCliente;
	private String montoSolicitado;
	private String status;
	private String nombreReferencia;
	private String telefonoReferencia;
	private String email;
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(String montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNombreReferencia() {
		return nombreReferencia;
	}
	public void setNombreReferencia(String nombreReferencia) {
		this.nombreReferencia = nombreReferencia;
	}
	public String getTelefonoReferencia() {
		return telefonoReferencia;
	}
	public void setTelefonoReferencia(String telefonoReferencia) {
		this.telefonoReferencia = telefonoReferencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}
}
