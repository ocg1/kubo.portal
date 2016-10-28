package mx.com.kubo.portal.ofertas;

import static mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional.RENOVACION_APROBACION_AUTOMATICA;

import org.primefaces.context.RequestContext;

public class PreaprobadorIMP extends PreaprobadorDMO 
implements PreaprobadorIMO 
{
	public void obtener_prestamo()
	{							
		request = RequestContext.getCurrentInstance();
		
		loan_type = 3;
			
		reasignador.init_renovacion_aprobacion_automatica(RENOVACION_APROBACION_AUTOMATICA, loan_type);
		
		request.addCallbackParam("reasignador_OK", true);		
	}
}
