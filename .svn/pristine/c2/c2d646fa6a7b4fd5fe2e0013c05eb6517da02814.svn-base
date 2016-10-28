package mx.com.kubo.mesa.solicitud;

import java.util.Date;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.mesa.solicitud.PldNotification;

public abstract class ReporteInusualAMO extends ReporteInusualDMO
{
	protected void init_notification_log() 
	{
		notification_log = new PldNotification();
		
		if(behavior_id > 0)
		{
			notification_log.setUnusual_behavior_id(behavior_id);
		}
		
		notification_log.setComments(comments);
		notification_log.setNotification_date(new Date());
		notification_log.setCompany_id(company_id);
		notification_log.setProspectus_id(prospectus_id);
		notification_log.setProspectus_id_viewed(prospectus_id_viewed);
		
		persist_OK = false;
		
		persist_OK = service_PLD.add(notification_log);	
	}
	
	protected void init_access() 
	{
		access = new Access();
		access.setScreen_id(REPORTE_INUSUAL);
		access.setCompany_id(company_id);
		access.setProspectus_id       (prospectus_id);
		access.setProspectus_id_viewed(prospectus_id_viewed);		
		access.setIpaddress          (sesion.getIP_address_client());
		access.setHorizontal_size    (sesion.getBrowser_height());
		access.setVertical_size      (sesion.getBrowser_width());		
		access.setUser_agent         (sesion.getUser_agent());
		access.setVersion_description(sesion.getVersion_description());
		access.setUrl_access		  (sesion.getUrl_access());
		
		service_access.add(access, true);
	}	
}
