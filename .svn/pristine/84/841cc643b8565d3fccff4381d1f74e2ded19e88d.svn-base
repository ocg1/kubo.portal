package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CountryPK 
implements Serializable
{
	@Column int country_id;
	@Column int company_id;
		
	public CountryPK(int country_id, int company_id)
	{
		this.country_id = country_id;
		this.company_id = company_id;
	}
	
	public CountryPK()
	{
		super();
	}
		
	public int getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public int getCountry_id() {
		return country_id;
	}
	
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}	
}
