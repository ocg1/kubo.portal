package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

import org.primefaces.context.RequestContext;

public class DelegacionMunicipioIMP extends DomicilioDMO
implements AutenticacionIMO
{
	public final void init_town_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		value_SELECTED = (String) select_one_menu.getValue();
		
		town_id_NEW = Integer.parseInt(value_SELECTED);
		
		town_ENABLED = (town_id_NEW == town_id_ORIGINAL) ? true : false;
		
		request.addCallbackParam("town_ENABLED", town_ENABLED);
	}
	
	public final boolean isValue_ENABLED() 
	{		
		return town_ENABLED;
	}

	public final String getValue_ORIGINAL() 
	{
		return town_id_ORIGINAL.toString();
	}

	public String getValue_NEW() 
	{		
		if(town_id_NEW != null)
		{
			return town_id_NEW.toString();
		}
		
		return "";
	}
}
