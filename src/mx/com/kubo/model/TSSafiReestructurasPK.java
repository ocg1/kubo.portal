package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TSSafiReestructurasPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="FECHAREGISTRO")
	private Date fechaRegistro;
	@Column(name="CREDITOORIGENID")
	private Integer creditoOrigen;
	@Column(name="CREDITODESTINOID")
	private Integer creditoDestinoId;
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getCreditoOrigen() {
		return creditoOrigen;
	}
	public void setCreditoOrigen(Integer creditoOrigen) {
		this.creditoOrigen = creditoOrigen;
	}
	public Integer getCreditoDestinoId() {
		return creditoDestinoId;
	}
	public void setCreditoDestinoId(Integer creditoDestinoId) {
		this.creditoDestinoId = creditoDestinoId;
	}
	
}
