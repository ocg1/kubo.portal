package mx.com.kubo.controller;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.PendingNotification;
import mx.com.kubo.model.Phone;
import mx.com.kubo.services.PendingNotificationService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.tools.Utilities;

public class PendingNotificationController {
	
	PendingNotificationService pendingnotificationservice;
	PhoneService phoneservice;
	
	List<PendingNotification> lstPending;
	
	public PendingNotificationController(){
		pendingnotificationservice = Utilities.findBean("pendingNotificationServiceImp");
		phoneservice = Utilities.findBean("phoneServiceImp");
		
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
	
	
	public String getPhoneNumber( int company_id, int prospectus_id ){
		
		try{
			
			String phone_str = "";
			
			 List<Phone> lstPhone =  phoneservice.getPhoneListByType(prospectus_id, company_id, 6);
			 
			 if( lstPhone != null && lstPhone.size() > 0 ){
			 
				 for( Phone p : lstPhone ){
					 if( p  != null && p.getPhone_number() != null && p.getPhone_number().trim().length() > 0 ){
						 phone_str = p.getPhone_number();
					 }
				 }
			
			 }
			 
			 if( phone_str != null && phone_str.trim().length() > 0 ){
				 
				 phone_str = "******"+ phone_str.substring( phone_str.length()-4 );
			 }
			 
			 return phone_str;
		
		}catch( Exception e ){
			e.printStackTrace();
			return  "******" ;
		}
		
	}

}
