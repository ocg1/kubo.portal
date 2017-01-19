package mx.com.kubo.portal;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;

public final class AccessIMP extends AccessAMO
implements AccessIMO
{
	public final void init_access_CONFIG(AjaxBehaviorEvent event) 
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		access_CONFIG = null;
		
		if( input_text != null && input_text.getValue() != null ){
				
				access_CONFIG = input_text.getValue().toString();
				
				version_description = systemparamservice.getVersion_description();
				
				if( access_CONFIG != null && access_CONFIG.length() > 3)
				{
					init_access_CONFIG();
					init_access();	
				}
		
		}
			
		request.addCallbackParam("init_access_OK", access_OK);	
	}
	
	public final void save_access(SessionBean sesion, int screen_id) 
	{
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		
		access.setScreen_id( screen_id );
		
		access.setPercentage(0);
		
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUrl_access		  (sesion.getUrl_access());
		
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access .add(access, false);
	}
	
	public final void save_access_coach(SessionBean sesion, int screen_id, int prospectus_id) 
	{
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(prospectus_id);
		
		access.setScreen_id( screen_id );
		
		access.setPercentage(0);
		
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUrl_access		  (sesion.getUrl_access());
		
		
		access.setProspectus_id_coach (sesion.getProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access .add(access, false);
	}
	
}
