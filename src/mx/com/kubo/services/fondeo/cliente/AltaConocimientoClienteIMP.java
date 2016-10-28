package mx.com.kubo.services.fondeo.cliente;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class AltaConocimientoClienteIMP extends AltaConocimientoClienteAMO
implements ServiceFondeoIMO 
{
	public final void init() 
	{
		try
		{	
			if(pld != null && SAFI_client_ENABLED)
			{
				init_values();
				init_alta_conocimiento_cliente();
			}
																		
		} catch(Exception e) {
						
			e.printStackTrace();
			
/*			
			if(e.getStackTrace().toString().length()>990)
			{
				exception_INFO = e.getStackTrace().toString().substring(0,990);
				
			} else {
				
				exception_INFO = e.getStackTrace().toString().substring(0,990);
			}
*/			
			
			lista_errores.add(SAFI_ALTA_PROSPECTO_ERROR);
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_ALTA_PROSPECTO_ERROR);
		}	
	}
}
