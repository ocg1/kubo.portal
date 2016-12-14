package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TSAFI_KIVA")
public class TSafiKiva implements Serializable {
	
	@EmbeddedId
	private TSafiKivaPK pk;

	@Column(name="CAPITAL")
	private Double capital;  
	@Column(name="INTERES")
	private Double interes;
	public TSafiKivaPK getPk() {
		return pk;
	}
	public void setPk(TSafiKivaPK pk) {
		this.pk = pk;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	public Double getInteres() {
		return interes;
	}
	public void setInteres(Double interes) {
		this.interes = interes;
	}
	
}
