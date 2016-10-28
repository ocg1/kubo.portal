package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProfileCategoryInvPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column
	private int profile_category_id;
	@Column
	private int company_id;
	
	public int getProfile_category_id() {
		return profile_category_id;
	}
	public void setProfile_category_id(int profile_category_id) {
		this.profile_category_id = profile_category_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
