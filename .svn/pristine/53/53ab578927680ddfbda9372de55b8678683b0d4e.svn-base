package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_company_relationship")
public class Company_Relationship implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyRelationshipPK companyRelationshipPK;
	@Column
	private String description;
	
	public CompanyRelationshipPK getCompanyRelationshipPK() {
		return companyRelationshipPK;
	}
	public void setCompanyRelationshipPK(CompanyRelationshipPK companyRelationshipPK) {
		this.companyRelationshipPK = companyRelationshipPK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}