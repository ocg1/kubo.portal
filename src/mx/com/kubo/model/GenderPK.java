package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GenderPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GenderPK(){
		
	}
	
	@Column
	int gender_id;
	@Column
	int company_id;
	
	public int getGender_id() {
		return gender_id;
	}
	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
