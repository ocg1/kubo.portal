package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhonePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	private int phone_id;
	@Column
	private int prospectus_id;//	"int(10) unsigned"
	@Column
	private int company_id;//	"tinyint(3) unsigned
	
	public PhonePK(){
		
	}
	public PhonePK(int phone_id,int prospectus_id,int company_id){
			this.phone_id=phone_id;
			this.prospectus_id=prospectus_id;
			this.company_id=company_id;
		}
	public PhonePK(int prospectus_id,int company_id){
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	
	public int getPhone_id() {
		return phone_id;
	}
	public void setPhone_id(int phone_id) {
		this.phone_id = phone_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
}
