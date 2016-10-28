package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SavingAccountPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column
	private int saving_account_id;
	
	public SavingAccountPK(){
		
	}
	public SavingAccountPK(int prospectus_id,int company_id){
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	public SavingAccountPK(int prospectus_id,int company_id,int saving_account_id){
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.saving_account_id=saving_account_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public int getSaving_account_id() {
		return saving_account_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public void setSaving_account_id(int saving_account_id) {
		this.saving_account_id = saving_account_id;
	}
	
	
}
