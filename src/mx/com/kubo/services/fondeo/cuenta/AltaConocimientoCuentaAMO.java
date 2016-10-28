package mx.com.kubo.services.fondeo.cuenta;

public abstract class AltaConocimientoCuentaAMO extends AltaConocimientoCuentaDMO
{
	protected final void init_PLD() 
	{
		pld = service_PLD.getPrevencionLDByProspectus(prospectus_id, company_id);
		
		if(pld != null)
		{						
			saving_account = service_saving_account.getSavingAccountByProspectus(prospectus_id, company_id);
			
			if(saving_account != null)
			{
				SAFI_account_id = saving_account.getSafi_account_id();
				
				SAFI_account_NEW = SAFI_account_id != null && !SAFI_account_id.equals("") && !SAFI_account_id.equals(" ");
			}
		}
	}
	
	protected void init_alta_conocimiento_cuenta()
	{
		response = service_SAFI.createPLDCuentaSAFI( pld, SAFI_account_id, prospectus_id, company_id);
		
		if(response != null)
		{
			codigo_respuesta  = response.getCodigoRespuesta();
			mensaje_respuesta = response.getMensajeRespuesta();
		}
	}
	
	protected void init_response() 
	{
		if(response != null && !codigo_respuesta.equals("0"))
		{
			int indexsp = mensaje_respuesta.indexOf("CONOCIMIENTOCTAALT");
			int indexdk = mensaje_respuesta.indexOf("Duplicate entry");
			
			if(indexsp > 0 && indexdk > 0)
			{
				lista_errores.add("Este cliente ya tiene Conocimiento de Cuenta.");
				
			} else {
				
				lista_errores.add(mensaje_respuesta);
			}			
		}		
	}
}
