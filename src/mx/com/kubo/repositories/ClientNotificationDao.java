package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ClientNotification;

public interface ClientNotificationDao {

	public boolean updateClientNotification( ClientNotification cn );
	public List<ClientNotification> getClientNotificationWithOutCoach();
	public boolean callSPClientNotification();
	public ClientNotification getClientNotification( Integer prospectus_id , Integer notification_type_id );
	
}
