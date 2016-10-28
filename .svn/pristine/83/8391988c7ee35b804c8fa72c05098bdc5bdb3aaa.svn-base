package mx.com.kubo.services.fondeo.cliente;

public abstract class CreacionClienteAMO extends CreacionClienteDMO 
{
	protected void init_values() 
	{		
		view_client_info = service_client_INFO.getClientInfo(prospectus_id, company_id);
	}
	
	protected void init_creacion_cliente() 
	{
		if(view_client_info != null)
		{						
			response = service_SAFI.createClientSAFI(view_client_info);
			
			if(response != null)
			{
				codigo_respuesta   = response.getCodigoRespuesta();											
				mensaje_respuesta  = response.getMensajeRespuesta();
			}
			
		} else {
			
			lista_errores.add("No existe información para el prospecto");
		}
	}
	
	protected void init_response() 
	{
		if(response != null)
		{
			if(codigo_respuesta.equals(SUCCESS))
			{							
				SAFI_client_id = response.getNumero();
				
				acreditado.setSafi_client_id(SAFI_client_id);
				
				service_natural_person.update(acreditado);
				
				System.out.println("SAFICreacionClienteAMO.init_creacion_cliente_response(SUCCESS):" + SAFI_client_id);
				
			} else {
				
				lista_errores.add(mensaje_respuesta);
			}
		}
	}
}
