package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TempPasswordPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column
	private int temp_password_id;
	@Column
	private int company_id;
	
	public TempPasswordPK(){
		
	}
	public TempPasswordPK(int temp_password_id,int company_id){
		this.temp_password_id=temp_password_id;
		this.company_id=company_id;
	}

	public int getTemp_password_id() {
		return temp_password_id;
	}

	public void setTemp_password_id(int temp_password_id) {
		this.temp_password_id = temp_password_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	

}
