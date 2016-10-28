package mx.com.kubo.services.fondeo.credito;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class SolicitudCreditoIMP extends SolicitudCreditoAMO
implements ServiceFondeoIMO
{
	public void init() 
	{
		try
		{											
			if(SAFI_solicitud_NEW)
			{		
				init_values();
				init_request();
				init_solicitud_credito();	
				init_response();
				
			} else {
				
				init_SAFI_solicitud_id();
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			solicitud_credito_OK = false;
		}
	}
}
