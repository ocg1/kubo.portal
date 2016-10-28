package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CuentasServicios.AltaRelacionadoCtaResponse;
import safisrv.ws.CuentasServicios.SAFIServicios;
import safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator;

public abstract class ServiceAltaRelacionadosDMO 
{
	protected ServiceCallingService service_calling;
	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios service;
	
	protected AltaRelacionadoCtaResponse  response;
	
	protected String codigo;
	protected String mensaje;
	
	protected final String INIT_CALL_INFO;
	protected final String RESPONSE_CALL_INFO;
	protected final String ERROR_CALL_INFO;
	
	protected final String SUCCESS;
	
	protected final int INIT;
	protected final int RESPONSE;	
	protected final int ERROR;
	
	protected ServiceAltaRelacionadosDMO()
	{
		service_calling = Utilities.findBean("serviceCallingServiceImp");
		
		INIT_CALL_INFO     = "ServiceAltaRelacionadosIMP.alta_relacionado_cuenta(INIT): ";
		RESPONSE_CALL_INFO = "ServiceAltaRelacionadosIMP.alta_relacionado_cuenta(RESPONSE): ";
		ERROR_CALL_INFO    = "ServiceAltaRelacionadosIMP.alta_relacionado_cuenta(ERROR): ";
		
		SUCCESS = "0";
		
		INIT     = 1;
		RESPONSE = 2;	
		ERROR    = 3;
	}
}
