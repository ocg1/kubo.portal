package mx.com.kubo.services.fondeo.seguro;

import java.util.Date;

import safisrv.ws.CreditosServicios.SegurosVidaRequest;
import mx.com.kubo.model.SystemParamPK;

public abstract class CreacionSeguroVidaAMO extends CreacionSeguroVidaDMO
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
			SAFI_account_id = saving_account.getSafi_account_id();
		}
	}
	
	protected void init_values() 
	{
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id(company_id);
		
		system_param_PK.setSystem_param_id(25);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		cobertura = system_param.getValue();
		
		
		system_param_PK.setSystem_param_id(26);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		companiaAsegura = system_param.getValue();
		
		
		system_param_PK.setSystem_param_id(27);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		polizaSeguro = system_param.getValue();
	}
	
	protected void init_request() 
	{
		request = new SegurosVidaRequest();
		
		request.setClienteID  (SAFI_client_id);
		request.setCreditoID  (SAFI_credit_id);
		request.setCuentaAhoID(SAFI_account_id);
		
		request.setCobertura(cobertura);
		request.setCompaniaAsegura(companiaAsegura);
		request.setPolizaSeguro(polizaSeguro);
		request.setMontoPrima(monto_prima +"");
		request.setFechaVigencia("2013-06-19");						
	}
	
	protected void init_response() 
	{
		response = service_SAFI.creaSeguroVidaSAFI(request, prospectus_id, company_id);
		
		if(response != null)
		{
			codigo_respuesta  = response.getCodigoRespuesta();
			mensaje_respuesta = response.getMensajeRespuesta();
		}
		
		if(codigo_respuesta != null && codigo_respuesta.equals(SUCCESS))
		{
			SAFI_seguro_id = response.getSeguroVidaID();
			
			proyect_loan.setFunding_date(new Date());
			proyect_loan.setSafi_mx_seguro_id(SAFI_seguro_id);
			proyect_loan.setStatus_id(3);
			
			service_proyect_loan.update(proyect_loan);			
			service_proyect_loan.spSetOnProyectFunding(proyect_loan_id, proyect_id, company_id, prospectus_id, 0, monto_prima - monto_fondeo,"");
			
			creacion_seguro_OK = true;
			
		} else {
			
			if(response != null)
			{				
				lista_errores.add("Seguro de Vida: " + mensaje_respuesta);
				
			} else {
			
				lista_errores.add("Error al dar de alta el Seguro de Vida ");			
			}
			
			creacion_seguro_OK = false;			
		}
	}
}
