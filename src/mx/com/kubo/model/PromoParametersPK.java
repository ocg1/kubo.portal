package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromoParametersPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private String promo_code; //, varchar(20), NO, PRI, , 
	@Column
	private int position; //, int(4)
	
	public String getPromo_code() {
		return promo_code;
	}
	public void setPromo_code(String promo_code) {
		this.promo_code = promo_code;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
