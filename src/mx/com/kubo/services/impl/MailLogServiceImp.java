package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.SystemNotificationLog;
import mx.com.kubo.repositories.MailLogDAO;
import mx.com.kubo.services.MailLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailLogServiceImp 
implements MailLogService 
{	
	@Autowired
	private MailLogDAO dao;
	
	public void add(MailLog mail) 
	{
		dao.saveMailLog(mail);		
	}
	
	public final List<SystemNotificationLog> getSystem_notification_log(int prospectus_id)
	{
		return dao.getSystem_notification_log(prospectus_id);
	}
}
