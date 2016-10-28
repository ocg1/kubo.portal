package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "view_investor_deposit")
public class SafiDepositoRefere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "FolioCargaID") //	bigint(17)	NO	PRI
	private BigInteger folioCargaID;
	
	@Column(name = "FechaAplica") //	date	YES
	private Date fechaAplica;
	
	@Column(name = "MontoMov") //	decimal(12,2)	YES
	private Double montoMov;

	public BigInteger getFolioCargaID() {
		return folioCargaID;
	}

	public void setFolioCargaID(BigInteger folioCargaID) {
		this.folioCargaID = folioCargaID;
	}

	public Date getFechaAplica() {
		return fechaAplica;
	}

	public void setFechaAplica(Date fechaAplica) {
		this.fechaAplica = fechaAplica;
	}

	public Double getMontoMov() {
		return montoMov;
	}

	public void setMontoMov(Double montoMov) {
		this.montoMov = montoMov;
	}
	
	
	
}
