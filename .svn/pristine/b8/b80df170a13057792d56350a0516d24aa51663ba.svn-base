package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountTypePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private int account_type_id;
	@Column
	private int company_id;
	
	public AccountTypePK(){
		
	}
	
	public AccountTypePK(int account_type_id, int company_id) {
		this.account_type_id = account_type_id;
		this.company_id = company_id;
	}

	public int getAccount_type_id() {
		return account_type_id;
	}

	public void setAccount_type_id(int account_type_id) {
		this.account_type_id = account_type_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
