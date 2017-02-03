package mx.com.kubo.controller;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.PendingNotification;
import mx.com.kubo.services.PendingNotificationService;
import mx.com.kubo.tools.Utilities;

public class PendingNotificationController {
	
	PendingNotificationService pendingnotificationservice;
	List<PendingNotification> lstPending;
	
	public PendingNotificationController(){
		pendingnotificationservice = Utilities.findBean("pendingNotificationServiceImp");
	}
	
	public boolean updatePendingNotification( PendingNotification pending ){
		return pendingnotificationservice.updatePendingNotification(pending);
	}
	
	public List<PendingNotification> getPendingNotificationStatusCero( ){
		return pendingnotificationservice.getPendingNotificationStatusCero();
	}
	
	public boolean initPendingNotificationOBJ( int company_id, int prospectus_id, int event_id ){
		
		if( !pendingnotificationservice.existPendingNotification(  company_id,  prospectus_id,  event_id )){
		
			PendingNotification pending = new PendingNotification();
			
			pending.setCompany_id(company_id);
			pending.setEvent_id(event_id);
			pending.setPending_date(new Date());
			pending.setProspectus_id(prospectus_id);
			pending.setStatus_id(0);
			
			return pendingnotificationservice.savePendingNotification(pending);
			
		}else{
			return true;
		}
		
		
	}
	

}
