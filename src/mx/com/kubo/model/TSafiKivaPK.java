package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TSafiKivaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CREDITOID")
	private Integer creditoId;
	@Column(name="FECHAEXIGIBLE")
	private Date fechaExigible;
	
	public Integer getCreditoId() {
		return creditoId;
	}
	public void setCreditoId(Integer creditoId) {
		this.creditoId = creditoId;
	}
	public Date getFechaExigible() {
		return fechaExigible;
	}
	public void setFechaExigible(Date fechaExigible) {
		this.fechaExigible = fechaExigible;
	}
	
}
