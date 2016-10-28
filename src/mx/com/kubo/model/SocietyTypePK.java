package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SocietyTypePK implements Serializable
{
	private static final long serialVersionUID = 7976557422758213573L;
	
	@Column private int society_type_id;	
	@Column private int company_id;
	
	public int getSociety_type_id() 
	{
		return society_type_id;
	}
	
	public void setSociety_type_id(int society_type_id) 
	{
		this.society_type_id = society_type_id;
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
