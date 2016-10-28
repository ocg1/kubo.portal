package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class gnNaturalPersonPK 
implements Serializable
{
	private static final long serialVersionUID = 6903256214164043256L;
	
	@Column private int prospectus_id;//"int(10) unsigned",NO,PRI,NULL,	
	@Column private int company_id;// "tinyint(3) unsigned",NO,PRI,NULL,
	
	public gnNaturalPersonPK() 
	{
		super();
	}

	public gnNaturalPersonPK(int prospectus_id, int company_id) 
	{
		super();
		
		this.prospectus_id = prospectus_id;
		this.company_id = company_id;
	}

	public int getProspectus_id() 
	{
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public int getCompany_id() 
	{
		return company_id;
	}

	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
}