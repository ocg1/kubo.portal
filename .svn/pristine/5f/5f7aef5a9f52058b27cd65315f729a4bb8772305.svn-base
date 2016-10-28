package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WhoAnsweredCatPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column
	private int who_answered_id;
	@Column
	private int company_id;
	
	public WhoAnsweredCatPK(){
		
	}
	public WhoAnsweredCatPK(int who_answered_id,int company_id){
		this.who_answered_id=who_answered_id;
		this.company_id=company_id;
	}

	public int getWho_answered_id() {
		return who_answered_id;
	}

	public void setWho_answered_id(int who_answered_id) {
		this.who_answered_id = who_answered_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	

}
