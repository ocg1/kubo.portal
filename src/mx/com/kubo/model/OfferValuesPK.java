package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OfferValuesPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int offer_id; // ', 'int(11) unsigned', 'NO', 'PRI', NULL, ''
	@Column
	private int promo_id; // ', 'int(11)', 'NO', 'PRI', NULL, ''
	@Column
	private int position; // ', 'int(4)', 'NO', 'PRI', NULL, ''
	
	public int getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}
	public int getPromo_id() {
		return promo_id;
	}
	public void setPromo_id(int promo_id) {
		this.promo_id = promo_id;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
