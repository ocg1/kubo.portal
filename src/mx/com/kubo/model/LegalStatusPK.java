package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LegalStatusPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LegalStatusPK(){
		
	}
	
	public LegalStatusPK(int legal_status_id, int company_id){
		this.legal_status_id = legal_status_id;
		this.company_id = company_id;
	}
	
	@Column
	int legal_status_id;
	@Column
	int company_id;

	public int getLegal_status_id() {
		return legal_status_id;
	}
	public void setLegal_status_id(int legal_status_id) {
		this.legal_status_id = legal_status_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
	
}
