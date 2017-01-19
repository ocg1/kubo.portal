package mx.com.kubo.portal;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.services.AccessService;

public interface AccessIMO 
{
	void setService_access(AccessService service);
	
	void setSesion(SessionBean sesion);
	
	void setAccess_from(String access_from);
	
	void setScreen_id(Integer screen_id);
	
	boolean isAccess_OK();
	
	void init_access_CONFIG(AjaxBehaviorEvent event);
	
	void save_access(SessionBean sesion, int screen_id) ;
	
	void save_access_coach(SessionBean sesion, int screen_id, int prospectus_id); 
}
