package mx.com.kubo.portal;

import mx.com.kubo.model.Access;

public abstract class AccessAMO extends AccessDMO 
{
	protected void init_access_CONFIG() 
	{					
		try
		{		
			lista_access_CONFIG = access_CONFIG.split("::", 3);
			
			browser_width   = Integer.parseInt(lista_access_CONFIG[0]);
			browser_height  = Integer.parseInt(lista_access_CONFIG[1]);
			user_agent      = lista_access_CONFIG[2];								
		
		} catch (Exception e) {
			
			e.printStackTrace();						
		}
	}
	
	protected void init_access() 
	{		
		access = new Access();
		
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		
		access.setCompany_id          (company_id);
		access.setProspectus_id       (prospectus_id);
		access.setProspectus_id_viewed(prospectus_id_viewed);		
		
		access.setIpaddress(access_client_IP);
		access.setUser_agent(user_agent);
		access.setDevice_info(device_info);
		access.setVersion_description(version_description);
		access.setAccess_from(access_from);
		
		access.setWeb_browser         (browser_name);
		access.setWeb_browser_version (browser_version);
		access.setOp_system           (browser_OS);		
		access.setVertical_size       (browser_height);
		access.setHorizontal_size     (browser_width);
				
		service_access.add(access, false);		
		
		access_OK = true;
	}
}
