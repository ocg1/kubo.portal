package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmploymentPK 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Column private int employment_id;
	@Column private int company_id;
	@Column private int prospectus_id;
	
	public EmploymentPK()
	{
		super();
	}
	
	public EmploymentPK(int employment_id, int company_id, int prospectus_id)
	{
		this.employment_id = employment_id;
		this.company_id    = company_id;
		this.prospectus_id = prospectus_id;
	}

	public int getEmployment_id() {
		return employment_id;
	}

	public void setEmployment_id(int employment_id) {
		this.employment_id = employment_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

}
