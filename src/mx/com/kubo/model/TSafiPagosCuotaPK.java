package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TSafiPagosCuotaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CREDITOID") 
	private Integer creditoId;
	@Column(name="CLIENTEID") 
	private Integer clienteId;
	@Column(name="AMORTIZACIONID")
	private Integer amortizacionId;
	
	
	public Integer getCreditoId() {
		return creditoId;
	}
	public void setCreditoId(Integer creditoId) {
		this.creditoId = creditoId;
	}
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public Integer getAmortizacionId() {
		return amortizacionId;
	}
	public void setAmortizacionId(Integer amortizacionId) {
		this.amortizacionId = amortizacionId;
	}

	
}
