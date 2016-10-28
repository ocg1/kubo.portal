package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_unusual_behavior")
public class UnusualBehavior
implements Serializable
{
	private static final long serialVersionUID = -8758177063556794710L;
	
	@EmbeddedId	
	private UnusualBehaviorPK pk;
	
	@Column private String description;

	public UnusualBehaviorPK getPk() 
	{
		return pk;
	}

	public void setPk(UnusualBehaviorPK pk) 
	{
		this.pk = pk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}		
}
