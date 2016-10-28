package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity @Table(name="gn_country")
public class Country 
implements Serializable 
{	
	@EmbeddedId private CountryPK pk;
	
	@Column private String name;
	
	public CountryPK getPk() 
	{
		return pk;
	}
	
	public void setPk(CountryPK pk) {
		this.pk = pk;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
