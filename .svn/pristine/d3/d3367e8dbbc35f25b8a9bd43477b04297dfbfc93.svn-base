package mx.com.kubo.services.fondeo.cliente;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class CreacionClienteIMP extends CreacionClienteAMO
implements ServiceFondeoIMO
{
	public void init() 
	{
		try
		{				
			if(SAFI_client_NEW)
			{
				init_values();		
				init_creacion_cliente();	
				init_response();
				
			} else {		
					
				lista_errores.add("El prospecto ya cuenta con número de cliente");
			}
		
		} catch(Exception e) {
			
			e.printStackTrace();			
			
			if(e.getStackTrace().toString().length() > 1999)
			{
				exception_INFO = e.getStackTrace().toString().substring(0,1999);
				
			} else {
				
				exception_INFO = e.getStackTrace().toString();
			}		
			
			SAFI_client_id = null;
			
			lista_errores.add(SAFI_CREACION_CLIENTE_ERROR);
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_CREACION_CLIENTE_ERROR, exception_INFO);						
		}
	}
}
