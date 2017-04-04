package mx.com.kubo.mesa.solicitud.resumen.score;

import com.soa.webServices.WsSgbRiskServiceLocator;

public class BuroReprocessIMP extends BuroReprocessAMO 
implements BuroReprocessIMO 
{
	public void init()
	{
		try
		{
			response_msg = "";
			response_status = "0";
			reproccess_OK = false;
			
			init_service_calling(INIT);
			init_request();
			
			locator = new WsSgbRiskServiceLocator();
			service_SGB = locator.getWsSgbRisk();
						
			response = service_SGB.buroReprocess(request);	
			
			response_status = response.getStatus();			
			   response_msg = response.getMessage();
			   
			if(response_msg.length() > 120)
			{
				response_msg = response_msg.substring(0,120);					
			} 
			
			if(response_status.equals(SUCCESS))
			{
				init_service_calling(RESPONSE_OK);		
				
				reproccess_OK = true;
				
			} else {
				
				init_service_calling(ERROR);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
