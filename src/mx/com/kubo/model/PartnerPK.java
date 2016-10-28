package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartnerPK implements Serializable
{

	private static final long serialVersionUID = 603336686557286615L;
	
	@Column private String partner_id;
	@Column private int company_id;
	
	public PartnerPK()
	{
		super();
	}
	
	public PartnerPK(String partnerID, int companyID)
	{
		this.partner_id = partnerID;
		this.company_id = companyID;
	}

	public String getPartner_id() 
	{
		return partner_id;
	}

	public void setPartner_id(String partner_id) 
	{
		this.partner_id = partner_id;
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
