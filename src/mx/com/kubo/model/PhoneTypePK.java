package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhoneTypePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column
	private int phone_type_id;
	@Column
	private int company_id;
	
	public PhoneTypePK(){
		
	}
	public PhoneTypePK(int phone_type_id,int company_id){
		this.phone_type_id=phone_type_id;
		this.company_id=company_id;
	}
	public int getPhone_type_id() {
		return phone_type_id;
	}
	public void setPhone_type_id(int phone_type_id) {
		this.phone_type_id = phone_type_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
