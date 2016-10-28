package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_notification_preference")
public class NotificationPreference 
implements Serializable
{
	private static final long serialVersionUID = 2701628361353037017L;

	@EmbeddedId
	private NotificationPreferencePK pk;
	
	@Column String description;

	public NotificationPreferencePK getPk() {
		return pk;
	}

	public void setPk(NotificationPreferencePK pk) {
		this.pk = pk;
	}
	
	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
}
