package mx.com.kubo.mesa.buro;

import com.soa.webServices.WsSgbRiskServiceLocator;

public class ProspectRiskIMP extends ProspectRiskAMO
implements ProspectRiskIMO
{
	public void init()
	{		
		try
		{
			init_service_calling(1, INIT_MSG);		
			init_request();
			
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();
														
			//String testConnection = service.testConnection(); 
			
			prospect_bc_risk_response = service.getProspectBCRisk( request );

			//String status = prospect_bc_risk_response.getStatus();
			
			if( prospect_bc_risk_response != null )
			{
				init_service_calling(2, SUCCESS_MSG);		
				init_response();	
				
				prospect_risk_ENABLED = true;								
			}
			
		} catch(Exception e) {
						
			e.printStackTrace();
			
			messageErrorConsulta = e.getMessage();
			
			stack_trace = e.getStackTrace().toString();						
						
			exception = stack_trace.length() > 990 ? stack_trace.substring(0,990) : stack_trace;
			
			init_service_calling(3, ERROR_MSG);									
		}		
	}
}
