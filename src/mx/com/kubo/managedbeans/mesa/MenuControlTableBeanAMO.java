package mx.com.kubo.managedbeans.mesa;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;

public abstract class MenuControlTableBeanAMO extends MenuControlTableBeanDMO
{
	private Access  access;
	
	protected final void registrar_bitacora_accesso(int screen_id, int percentage, SessionBean  sesion, boolean valida )
	{
		registrar_bitacora_accesso(screen_id, percentage, sesion, -1,valida);
	}
	
	protected final void registrar_bitacora_accesso(int screen_id, int percentage, SessionBean  sesion, int prospectus_id_viewed, boolean valida)
	{
		access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		
		if(prospectus_id_viewed >= 0)
		{
			access.setProspectus_id_viewed(prospectus_id_viewed);
		} 
		
		access.setScreen_id(screen_id);
		access.setPercentage(percentage);
		
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size  (sesion.getBrowser_height());
		access.setUser_agent     (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		access.setIpaddress      (sesion.getIP_address_client());
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access, valida);
	}
}
