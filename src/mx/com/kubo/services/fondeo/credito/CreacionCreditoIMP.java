package mx.com.kubo.services.fondeo.credito;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class CreacionCreditoIMP extends CreacionCreditoAMO
implements ServiceFondeoIMO 
{
	public final void init() 
	{										
		if(SAFI_solicitud_ENABLED)
		{			
			if(SAFI_credit_NEW)
			{			
				init_request();		
				
				if(creacion_credito_ENABLED)
				{
					init_creacion_credito();								
					init_response();
				}
				
			} else {
				
				creacion_credito_OK = true;				
			} 
			
		} else {
			
			lista_errores.add("No existe solicitud safi para este proyecto");
			
			creacion_credito_OK = false;			
		}				
	}
}
