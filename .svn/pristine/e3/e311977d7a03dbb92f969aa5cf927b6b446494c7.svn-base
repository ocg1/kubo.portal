package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InstitutionalInvestorPK implements Serializable
{
	private static final long serialVersionUID = -7018121801982194229L;
	
	@Column private int institutional_investor_id;
	@Column private int company_id;

	public InstitutionalInvestorPK(int institutional_investor_id, int company_id) 
	{
		this.institutional_investor_id = institutional_investor_id;
		this.company_id = company_id;
	}
	
	public InstitutionalInvestorPK() 
	{
		super();
	}
	

	public int getInstitutional_investor_id() {
		return institutional_investor_id;
	}
	public void setInstitutional_investor_id(int institutional_investor_id) {
		this.institutional_investor_id = institutional_investor_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
	
}
