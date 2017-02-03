package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.PendingNotification;
import mx.com.kubo.repositories.PendingNotificationDao;
import mx.com.kubo.services.PendingNotificationService;

@Service
public class PendingNotificationServiceImp implements Serializable , PendingNotificationService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PendingNotificationDao dao;
	
	public boolean savePendingNotification( PendingNotification pending ){
		return dao.savePendingNotification(pending);
	}
	
	public boolean updatePendingNotification( PendingNotification pending ){
		return dao.updatePendingNotification(pending); 
	}
	
	public List<PendingNotification> getPendingNotificationStatusCero( ){
		return dao.getPendingNotificationStatusCero();
	}
	
	public boolean existPendingNotification( int company_id, int prospectus_id, int event_id ){
		return dao.existPendingNotification( company_id, prospectus_id, event_id );
	}

}
