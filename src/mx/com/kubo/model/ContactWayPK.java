package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactWayPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id;
	@Column
	private int contact_way_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getContact_way_id() {
		return contact_way_id;
	}
	public void setContact_way_id(int contact_way_id) {
		this.contact_way_id = contact_way_id;
	}

}
