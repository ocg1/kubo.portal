package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_address_type")
public class AddressType
implements Serializable 
{
	private static final long serialVersionUID = 7754425595394370113L;
	
	@EmbeddedId
	private AddressTypePK pk;
	
	@Column private String name;
	
	public AddressTypePK getPk() 
	{
		return pk;
	}
	
	public void setPk(AddressTypePK pk) 
	{
		this.pk = pk;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
}
