package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Marital_StatusPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 16758554546748769L;
	
	public Marital_StatusPK(){
		
	}
	
	public Marital_StatusPK(int marital_status_id, int company_id){
		this.marital_status_id = marital_status_id;
		this.company_id = company_id;
	}
	
	@Column
	int marital_status_id;
	@Column
	int company_id;

	public int getMarital_status_id() {
		return marital_status_id;
	}
	public void setMarital_status_id(int marital_status_id) {
		this.marital_status_id = marital_status_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
	
}
