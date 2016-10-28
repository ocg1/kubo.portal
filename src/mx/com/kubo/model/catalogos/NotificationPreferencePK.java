package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotificationPreferencePK
implements Serializable
{	
	private static final long serialVersionUID = -6106013483342114174L;
	
	@Column private int notification_preference_id;
	@Column private int company_id;	

	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
	
	public int getCompany_id() 
	{
		return company_id;
	}

	public int getNotification_preference_id() {
		return notification_preference_id;
	}

	public void setNotification_preference_id(int notification_preference_id) {
		this.notification_preference_id = notification_preference_id;
	}
}
