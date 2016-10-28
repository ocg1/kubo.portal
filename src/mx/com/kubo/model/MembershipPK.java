package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MembershipPK 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="prospectus_id")
	private int prospectus_id;//,"int(10) unsigned",NO,PRI,NULL,
	
	@Column(name="company_id")
	private int company_id;//,"tinyint(3) unsigned",NO,PRI,NULL,
	
	public MembershipPK() 
	{
		
	}

	public MembershipPK(int prospectus_id, int company_id) 
	{
		super();
		this.prospectus_id = prospectus_id;
		this.company_id = company_id;
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
