package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StateCatPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column
	private int state_id;
	@Column
	private int company_id;
	
	public StateCatPK(){
		
	}
	public StateCatPK(int state_id,int company_id){
		this.state_id=state_id;
		this.company_id=company_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	

}
