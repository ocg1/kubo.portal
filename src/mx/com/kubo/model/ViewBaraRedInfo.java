package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery(name = "findViewBaraRedInfo", query = "select * from view_barared_status", resultClass = ViewBaraRedInfo.class)
@Entity
public class ViewBaraRedInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer prospectus_id;//
	@Column
	private String email;
	@Column
	private String nombre;
	@Column
	private String fecha_creacion;
	@Column
	private String last_login_attempt;
	@Column
	private String kubo_score;
	@Column
	private String estatus;
	@Column
	private String monto_solicitado;
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getLast_login_attempt() {
		return last_login_attempt;
	}
	public void setLast_login_attempt(String last_login_attempt) {
		this.last_login_attempt = last_login_attempt;
	}
	public String getKubo_score() {
		return kubo_score;
	}
	public void setKubo_score(String kubo_score) {
		this.kubo_score = kubo_score;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getMonto_solicitado() {
		return monto_solicitado;
	}
	public void setMonto_solicitado(String monto_solicitado) {
		this.monto_solicitado = monto_solicitado;
	}
	
}
