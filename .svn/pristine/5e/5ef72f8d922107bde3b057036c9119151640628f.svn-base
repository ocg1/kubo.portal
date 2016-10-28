package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OccupationPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OccupationPK(){
		
	}
	
	public OccupationPK( int accupation_id, int company_id){
		this.occupation_id = accupation_id;
		this.company_id = company_id;
	}
	
	@Column
	int occupation_id;
	@Column
	int company_id;

	public int getOccupation_id() {
		return occupation_id;
	}
	public void setOccupation_id(int occupation_id) {
		this.occupation_id = occupation_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
