package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_company_type")
public class Company_Type implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyTypePK companyTypePK;
	@Column
	private String description;
	
	public CompanyTypePK getCompanyTypePK() {
		return companyTypePK;
	}
	public void setCompanyTypePK(CompanyTypePK companyTypePK) {
		this.companyTypePK = companyTypePK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}