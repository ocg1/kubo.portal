package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressTypePK 
implements Serializable 
{
	private static final long serialVersionUID = 7219806277465685564L;
	
	@Column private Integer address_type_id;
	@Column private Integer company_id;
	
	public Integer getAddress_type_id() 
	{
		return address_type_id;
	}
	
	public void setAddress_type_id(Integer address_type_id) 
	{
		this.address_type_id = address_type_id;
	}
	
	public Integer getCompany_id() 
	{
		return company_id;
	}
	
	public void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
}
