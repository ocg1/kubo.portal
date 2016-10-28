package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_channel")
public class Channel 
implements Serializable
{
	private static final long serialVersionUID = 8101270569062662661L;

	@EmbeddedId
	private ChannelPK pk;
	
	@Column String description;

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public ChannelPK getPk() 
	{
		return pk;
	}

	public void setPk(ChannelPK pk) 
	{
		this.pk = pk;
	}	
}
