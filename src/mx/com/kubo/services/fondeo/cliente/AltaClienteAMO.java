package mx.com.kubo.services.fondeo.cliente;

public abstract class AltaClienteAMO extends AltaClienteDMO
{
	protected void init_response() 
	{
		if(objIFE != null)
		{
			if(objIFE.getNumErr().equals("001") || objIFE.getNumErr().equals("000"))
			{										
				service_calling.registrar(RESPONSE, prospectus_id, company_id, SAFI_ALTA_CLIENTE_OK + objIFE.getErrMen());
				
				alta_cliente_OK = true;
				
			} else {										
				
				service_calling.registrar(RESPONSE, prospectus_id, company_id, SAFI_ALTA_CLIENTE_OK + objIFE.getErrMen());
				
				lista_errores.add(objIFE.getErrMen());
				
				alta_cliente_OK = false;
			}
			
		} else {
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_ALTA_CLIENTE_ERROR, SAFI_ALTA_CLIENTE_ERROR_INFO);
			
			lista_errores.add(SAFI_ALTA_CLIENTE_ERROR_RESP);
			
			alta_cliente_OK = false;
		}	
	}
	
	protected void init_tipo_identificacion() 
	{
		if(tipo_identificacion != null)
		{
			request.setOficial(OFICIAL);
			request.setNumIdentif(acreditado_ID);
			request.setAudDireccionIP(sesion.getIP_address_client());
			
			safi_tipo_identi_id    = person.getIdentification_type().getSafi_tipo_identi_id();
			safi_numero_caracteres = person.getIdentification_type().getSafi_numero_caracteres();
			
			if(safi_numero_caracteres == 0 && acreditado_ID == null)
			{
				acreditado_ID = "";
			}
			
			request.setTipoIdentID(safi_tipo_identi_id);
			
			switch(tipo_identificacion)
			{						
				case INE:				
					acreditado_ID = person.getMx_ine_cic();
					
					request.setNumIdentif(acreditado_ID);
				break;											
				
				case CEDULA_PROFESIONAL:										
					
					request.setOficial(NO_OFICIAL);
				break;				
			}
		}
	}
}
