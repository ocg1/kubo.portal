package mx.com.kubo.registro.datos.citizen;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class NacionalidadIMP extends NacionalidadAMO
implements NacionalidadIMO
{
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_change(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		citizenship = Integer.parseInt(select_menu.getValue().toString());
		
		init_change_control("citizenship", person.getCitizenship().toString(), citizenship.toString());
		
		if(change_control_OK)
		{
			init_country_id();								
		
			person.setCitizenship(citizenship);
			person.setCountry_id(country_id);
			person.setState_id(null);	
			
			person = service_natural_person.update(person);
		}
		
		request.addCallbackParam("citizenship", citizenship);
		request.addCallbackParam("country_id",  country_id != null ? country_id : "");	
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
