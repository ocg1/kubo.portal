package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.SystemNotificationLog;

public interface MailLogDAO 
{
	boolean saveMailLog(MailLog mail);
	
	List<SystemNotificationLog> getSystem_notification_log(int prospectus_id);
}
