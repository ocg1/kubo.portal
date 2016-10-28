package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProfileInvestFormPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int profile_form_id;
	@Column
	private int company_id;
	
	public int getProfile_form_id() {
		return profile_form_id;
	}
	public void setProfile_form_id(int profile_form_id) {
		this.profile_form_id = profile_form_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
