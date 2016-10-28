package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

import org.primefaces.context.RequestContext;

public final class LugarNacimientoIMP extends LugarNacimientoDMO
implements AutenticacionIMO
{
	public final void init_state_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		value_SELECTED = (String) select_one_menu.getValue();
		
		state_id_NEW = Integer.parseInt(value_SELECTED);
		
		birthplace_ENABLED = (state_id_NEW == state_id_ORIGINAL) ? true : false;
		
		request.addCallbackParam("birthplace_ENABLED", birthplace_ENABLED);
	}
}
