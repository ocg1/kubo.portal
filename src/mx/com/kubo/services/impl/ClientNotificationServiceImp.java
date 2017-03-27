package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ClientNotification;
import mx.com.kubo.repositories.ClientNotificationDao;
import mx.com.kubo.services.ClientNotificationService;

@Service
public class ClientNotificationServiceImp implements Serializable,ClientNotificationService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ClientNotificationDao dao;
	
	public boolean updateClientNotification( ClientNotification cn ){
		return dao.updateClientNotification(cn);
	}
	
	public List<ClientNotification> getClientNotificationWithOutCoach(){
		return dao.getClientNotificationWithOutCoach();
	}
	
	public boolean callSPClientNotification(){
		return dao.callSPClientNotification();
	}
	
	public ClientNotification getClientNotification( Integer prospectus_id , Integer notification_type_id ){
		return dao.getClientNotification(prospectus_id, notification_type_id);
	}
	
	
}
