package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_society_type")
public class SocietyType implements Serializable
{
	private static final long serialVersionUID = 5318784298019075731L;
	
	@EmbeddedId
	private SocietyTypePK pk;
	
	@Column private String name;

	public SocietyTypePK getPk() 
	{
		return pk;
	}

	public void setPk(SocietyTypePK pk) 
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
