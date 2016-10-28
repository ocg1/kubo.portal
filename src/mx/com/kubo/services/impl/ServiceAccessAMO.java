package mx.com.kubo.services.impl;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;

public abstract class ServiceAccessAMO extends ServiceAccessDMO
{
	protected Access init_access_NEW(int screen_id, int porcentaje, SessionBean sesion) 
	{
		Access access = new Access();
		
		access.setScreen_id(screen_id);
		access.setPercentage(porcentaje);
		
		access.setCompany_id     (sesion.getCompany_id());
		access.setProspectus_id  (sesion.getProspectus_id());
		access.setOp_system      (sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size  (sesion.getBrowser_height());
		access.setIpaddress      (sesion.getIP_address_client());
		access.setWeb_browser    (sesion.getNamebrawser());		
		access.setUser_agent     (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());		
		access.setWeb_browser_version (sesion.getVersionbrawser());		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());		
		access.setUrl_access		  (sesion.getUrl_access());
		
		return access;
	}
}
