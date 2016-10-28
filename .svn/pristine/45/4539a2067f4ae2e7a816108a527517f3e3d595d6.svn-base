package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

import org.primefaces.context.RequestContext;

public final class FechaNacimientoIMP extends FechaNacimientoAMO
implements AutenticacionIMO
{			
	public final void listener_dia_SELECTED(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		day = (String) select_one_menu.getValue();
		
		init_birthdate_TOKEN();					
	}
	
	public final void listener_mes_SELECTED(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		month = (String) select_one_menu.getValue();
		
		init_birthdate_TOKEN();					
	}
	
	public final void listener_year_SELECTED(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		year = (String) select_one_menu.getValue();
		
		init_birthdate_TOKEN();				
	}
}
