package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ClientNotification;
import mx.com.kubo.model.NotificaCreditosDesembolsadosTresDiasCollector;
import mx.com.kubo.model.PublicadosSinAutorizar;

public interface ClientNotificationDao {

	public boolean updateClientNotification( ClientNotification cn );
	public List<ClientNotification> getClientNotificationWithOutCoach();
	public boolean callSPClientNotification();
	public ClientNotification getClientNotification( Integer prospectus_id , Integer notification_type_id );
	public List<PublicadosSinAutorizar> getPublicadosSinAutorizar( String send_type  );

	public List<NotificaCreditosDesembolsadosTresDiasCollector> getNotificaCreditosDesembolsadosTresDias( String send_type  );
	
}
