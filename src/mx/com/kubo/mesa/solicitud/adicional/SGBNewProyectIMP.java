package mx.com.kubo.mesa.solicitud.adicional;

import com.soa.webServices.WsSgbRiskServiceLocator;

public class SGBNewProyectIMP extends SGBNewProyectAMO 
implements SGBNewProyectIMO
{
	public void init() 
	{
		try
		{
			init_service_calling(INIT);
			init_new_project_request();
			
			locator = new WsSgbRiskServiceLocator();
			service_SGB = locator.getWsSgbRisk();
						
			response = service_SGB.newProjectDTO(request);			
			
			if(response.getStatus().equals("0"))
			{
				init_service_calling(RESPONSE_OK);
				init_creation_date();
				init_cambio_status();									
				
			} else {
				
				init_service_calling(ERROR);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}


}
