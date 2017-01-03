package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LegalLimitPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id;
	@Column
	private int legal_limit_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getLegal_limit_id() {
		return legal_limit_id;
	}
	public void setLegal_limit_id(int legal_limit_id) {
		this.legal_limit_id = legal_limit_id;
	}
	
}
