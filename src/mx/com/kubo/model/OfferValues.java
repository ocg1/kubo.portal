package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ln_offer_values")
public class OfferValues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OfferValuesPK pk;
	@Column
	private String value; //, varchar(45), YES, , , 
	
	
	
	public OfferValuesPK getPk() {
		return pk;
	}
	public void setPk(OfferValuesPK pk) {
		this.pk = pk;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
