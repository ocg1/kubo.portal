package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity @Table(name = "gn_dependants_number")
public class DependantsNumber 
implements Serializable
{
	private static final long serialVersionUID = -4496102295768646086L;

	@EmbeddedId
	private DependantsNumberPK pk;
	
	@Column String description;

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public DependantsNumberPK getPk() 
	{
		return pk;
	}

	public void setPk(DependantsNumberPK pk) 
	{
		this.pk = pk;
	}
}
