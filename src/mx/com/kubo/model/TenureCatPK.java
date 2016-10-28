package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TenureCatPK 
implements Serializable 
{
	private static final long serialVersionUID = 1544729884718758951L;
	
	@Column private int tenure_id;
	@Column private int company_id;
	
	public TenureCatPK()
	{
		super();
	}
	
	public TenureCatPK(int tenure_id, int company_id)
	{
		this.tenure_id  = tenure_id;
		this.company_id = company_id;
	}
		
	public void setTenure_id(int tenure_id) 
	{
		this.tenure_id = tenure_id;
	}
	
	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}	
	
	public int getCompany_id() 
	{
		return company_id;
	}
	
	public int getTenure_id() 
	{
		return tenure_id;
	}
}
