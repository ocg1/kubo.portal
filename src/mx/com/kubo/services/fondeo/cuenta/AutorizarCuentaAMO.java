package mx.com.kubo.services.fondeo.cuenta;

import safisrv.ws.CuentasServicios.AutorizaCuentaRequest;

public abstract class AutorizarCuentaAMO extends AutorizarCuentaDMO
{
	protected final void init_saving_account() 
	{	
		lista_saving_account = service_saving_account.getListAccountByProspect(prospectus_id, company_id);
		
		int ultimo = lista_saving_account.size() - 1;
		
		if(ultimo > -1)
		{
			saving_account = lista_saving_account.get(ultimo);
		}
		
		if(saving_account != null)
		{
			SAFI_related_person    = saving_account.getHas_related_person();
			SAFI_account_id        = saving_account.getSafi_account_id();				
			SAFI_cuenta_autorizada = saving_account.getStatus();
		}
	}
	
	protected void init_request() 
	{
		request = new AutorizaCuentaRequest();
		
		request.setCuentaAhoID(SAFI_account_id);
		
		request.setFechaApertura(fecha_TODAY);
		request.setUsuarioApeID(SAFI_FINANZAS_ID);
	}
	
	protected void init_autorizar_credito()
	{	
		try
		{				
			SAFI_web_service = locator.getSAFIServiciosSoap11();
			
			response = SAFI_web_service.autorizaCuenta(request);
			
			if(response != null)
			{
				codigo_respuesta  = response.getCodigoRespuesta();
				mensaje_respuesta = response.getMensajeRespuesta();	
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void init_response() 
	{		
		if(codigo_respuesta != null && codigo_respuesta.equals(SUCCESS))
		{
			//SAFI_related_person = response.getPersonaID() == "1" ? "S" : "N";
			
			String SAFI_cuenta_autorizada = response.getCuentaAhoID();
			
			saving_account.setStatus(ACTIVADA);
			
			service_saving_account.updateSavingAccount(saving_account);
			
			System.out.println("AutorizarCuentaAMO.init_response(SUCCESS): " + SAFI_cuenta_autorizada);
			
		} else {
			
			lista_errores.add(mensaje_respuesta != null ? mensaje_respuesta : "ERROR");
		}				
	}
}
