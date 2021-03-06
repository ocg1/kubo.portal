package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.SystemNotificationLog;

public interface MailLogService 
{
	void add(MailLog mail);
	
	MailLog getMaxMailLog(Integer event_id);
	
	List<SystemNotificationLog> getSystem_notification_log(int prospectus_id);
}
