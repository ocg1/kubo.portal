package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OfferRejectionMotivePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; // ` TINYINT(3) UNSIGNED NOT NULL,
	@Column
	private int offer_rejection_motive_id;// `
	
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getOffer_rejection_motive_id() {
		return offer_rejection_motive_id;
	}
	public void setOffer_rejection_motive_id(int offer_rejection_motive_id) {
		this.offer_rejection_motive_id = offer_rejection_motive_id;
	}
}
