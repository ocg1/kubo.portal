package mx.com.kubo.services.fondeo.cliente;

import java.util.Date;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class AltaClienteIMP extends AltaClienteAMO
implements ServiceFondeoIMO
{
	public final void init() 
	{
		try 
		{																
			service_calling.registrar(INIT, prospectus_id, company_id, SAFI_ALTA_CLIENTE_INIT);
			
			if(SAFI_client_id != null)
			{			
				request.setClientID(Long.parseLong(SAFI_client_id));				
				request.setAudFechaActual(new Date());				
				request.setAudProgramaID("Portal Kubo " + pr_version);
				
/*
				if(nacionalidad == EXTRANJERO)
				{					
					objIFE = service_natural_person.getIdentificationCollector(clientID, 6, "S", "111111111111111111", null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
					
				} else {
				
					objIFE = service_natural_person.getIdentificationCollector(clientID, 1, "S", acreditado_IFE, null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
				}
*/				
			
				objIFE = service_natural_person.getIdentificationCollector(request);
				
				init_response();
			}

		} catch (Exception e) {
			
			e.printStackTrace();					
			
			if(e.getStackTrace().toString().length() > 1999)
			{
				exception_INFO = e.getStackTrace().toString().substring(0,1999);
				
			} else {
				
				exception_INFO = e.getStackTrace().toString();
			}
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_ALTA_CLIENTE_ERROR, exception_INFO);
			
			lista_errores.add(SAFI_ALTA_CLIENTE_ERROR_RESP);
			
			alta_cliente_OK = false;			
		}
	}
}
