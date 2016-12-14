package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TSafiPosicionIntPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CLIENTEID") //
	private Integer clienteId ;
	@Column(name="creditoid")//
	private Integer creditoId;
	
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public Integer getCreditoId() {
		return creditoId;
	}
	public void setCreditoId(Integer creditoId) {
		this.creditoId = creditoId;
	}
	
	
}
