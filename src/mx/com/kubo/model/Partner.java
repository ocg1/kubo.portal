package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ln_partner")
public class Partner
implements Serializable
{
	private static final long serialVersionUID = -7042442225389540077L;

	@EmbeddedId
	private PartnerPK partnerPK;
		
	@Column String	name;
	@Column String	description;
	@Column String	url;
	@Column String	logo;
	@Column String	color;
	
	public PartnerPK getPartnerPK() 
	{
		return partnerPK;
	}
	
	public void setPartnerPK(PartnerPK partnerPK) 
	{
		this.partnerPK = partnerPK;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String getUrl() 
	{
		return url;
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public String getLogo() 
	{
		return logo;
	}
	
	public void setLogo(String logo) 
	{
		this.logo = logo;
	}
	
	public String getColor() 
	{
		return color;
	}
	
	public void setColor(String color) 
	{
		this.color = color;
	}
}
