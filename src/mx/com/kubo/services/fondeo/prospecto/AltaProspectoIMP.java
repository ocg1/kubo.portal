package mx.com.kubo.services.fondeo.prospecto;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class AltaProspectoIMP extends AltaProspectoAMO 
implements ServiceFondeoIMO
{
	public final void init() 
	{					
		try 
		{									
			if(SAFI_prospecto_NEW)
			{				
				init_values();
				init_request();
				init_alta_prospecto();
				init_response();
				
			} else {
				
				init_SAFI_prospecto_id();										
			}									
							
		} catch (Exception e) {
			
			e.printStackTrace();
			
			lista_errores.add(SAFI_ALTA_PROSPECTO_ERROR);
			
			if(e.getStackTrace().toString().length() > 1999)
			{
				exception_INFO = e.getStackTrace().toString().substring(0,1999);
				
			} else {
				
				exception_INFO = e.getStackTrace().toString();
			}
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_ALTA_PROSPECTO_ERROR, exception_INFO);
			
			alta_prospecto_OK = false;		
		}			
	}
}
