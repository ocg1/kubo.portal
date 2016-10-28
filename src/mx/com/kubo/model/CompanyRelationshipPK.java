package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyRelationshipPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompanyRelationshipPK(){
		
	}
	
	@Column
	int company_relationship_id;
	@Column
	int company_id;

	public int getCompany_relationship_id() {
		return company_relationship_id;
	}
	public void setCompany_relationship_id(int company_relationship_id) {
		this.company_relationship_id = company_relationship_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

		
	
}
