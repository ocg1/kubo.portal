package mx.com.kubo.model.investor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_cancellation_motive")
public class MotivosCancelacion 
implements Serializable
{
	private static final long serialVersionUID = -3926627155119397278L;
	
	@EmbeddedId
	private MotivosCancelacionPK pk;
	
	@Column private String description;
	
	public final MotivosCancelacionPK getPk() 
	{
		return pk;
	}

	public final void setPk(MotivosCancelacionPK pk) 
	{
		this.pk = pk;
	}

	public final String getDescription() 
	{
		return description;
	}

	public final void setDescription(String description) 
	{
		this.description = description;
	}
}
