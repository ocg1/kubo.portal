package mx.com.kubo.managedbeans.investor.movimientos;

import static mx.com.kubo.notificaciones.notificables.Evento.CANCELACION_CUENTA_INVERSIONES;
import static mx.com.kubo.notificaciones.notificables.Evento.CANCELACION_CUENTA_INVERSIONISTA;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.tools.GeneradorCodigos;

public final class CancelacionCuentaIMP extends CancelacionCuentaDMO
implements CancelacionCuentaIMO
{			
	public final void listener_cuenta(AjaxBehaviorEvent evento)
	{
		requestContext = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		cuenta = (String) select_one_menu.getValue();
		
		sb = new StringBuilder();
		
		sb.append(company_id);
		sb.append(cuenta);
		sb.append("-");
		sb.append(GeneradorCodigos.digitoVerificador(cuenta));
		
		folio = sb.toString(); 
		
		System.out.println("CancelacionCuentaIMP.listener_cuenta(): " + cuenta);
		
		requestContext.addCallbackParam("folio", folio);
	}
	
	public final void listener_motivo(AjaxBehaviorEvent evento)
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		motivo_id = (String) select_one_menu.getValue();
		
		motivo = lista_motivos.get(Integer.parseInt(motivo_id) - 1);
		
		System.out.println("CancelacionCuentaIMP.listener_motivo(): " + motivo.getDescription());
	}
	
	public final void notificar()
	{		
		try 
		{
			System.out.println("CancelacionCuentaIMP.notificar(): INIT");
			
			requestContext = RequestContext.getCurrentInstance();
			
			notificacion_OK = false;
			
			if(cuenta != null && motivo != null && descripcion != null) 
			{			
				cancelacion = new MovimientoCancelacionDMO(cuenta, motivo_id, motivo.getDescription(), folio, descripcion);
				
				service_notificador.setEmisor(inversionista);
				service_notificador.setAcreditado(inversionista);
				service_notificador.notificar(CANCELACION_CUENTA_INVERSIONES,   cancelacion);
				service_notificador.notificar(CANCELACION_CUENTA_INVERSIONISTA, cancelacion);
				
				service_movimientos.setSesion(sesion);
				service_movimientos.setMovimiento(cancelacion);
				service_movimientos.registrar_cancelacion();
				
				lista_movimientos = service_movimientos.getLista_movimientos();
				
				notificacion_OK = true;
			}
			
			requestContext.addCallbackParam("notificacion_OK", notificacion_OK);	
			
		} catch (NotificacionException e) {

			requestContext.addCallbackParam("notificacion_OK", notificacion_OK);	
			
			e.printStackTrace();
		}
	}
}
