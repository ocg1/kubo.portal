package mx.com.kubo.services.fondeo.cuenta;

import safisrv.ws.CuentasServicios.AltaRelacionadoCtaRequest;
import safisrv.ws.CuentasServicios.AltaRelacionadoCtaResponse;
import safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator;

public final class ServiceAltaRelacionadosIMP extends ServiceAltaRelacionadosDMO
{	
	public AltaRelacionadoCtaResponse init(AltaRelacionadoCtaRequest alta, int prospectus_id, int company_id)
	{				
		try
		{
			locator = new SAFIServiciosServiceLocator();
			
			service = locator.getSAFIServiciosSoap11();
			
			service_calling.registrar(INIT, prospectus_id, company_id, INIT_CALL_INFO);
			
			response = service.altaRelacionadoCta(alta);
						
			if(response != null)
			{
				codigo  = response.getCodigoRespuesta();
				mensaje = response.getMensajeRespuesta();
				
				if(codigo.equals(SUCCESS))
				{							
					service_calling.registrar(RESPONSE, prospectus_id, company_id, RESPONSE_CALL_INFO + mensaje);
													
				} else {
					
					service_calling.registrar(ERROR, prospectus_id, company_id, ERROR_CALL_INFO + mensaje);
				}
				
			} else {
				
				service_calling.registrar(ERROR, prospectus_id, company_id, ERROR_CALL_INFO);
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return response;
	}	
}
