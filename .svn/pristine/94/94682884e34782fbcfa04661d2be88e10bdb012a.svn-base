package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResidencePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResidencePK(){
		
	}
	
	public ResidencePK(int residence_id, int company_id){
		this.residence_id = residence_id;
		this.company_id = company_id;
	}
	
	@Column
	int residence_id;
	@Column
	int company_id;

	public int getResidence_id() {
		return residence_id;
	}
	public void setResidence_id(int residence_id) {
		this.residence_id = residence_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	
	
	
}
