package mx.com.kubo.registro.datos.country;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class BirthCountryIMP extends BirthCountryAMO
implements BirthCountryIMO
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
		
		country_id = Integer.parseInt(select_menu.getValue().toString());
		
		value_ORIGINAL = person.getCountry_id() != null ? person.getCountry_id().toString() : "";
		
		init_change_control("country_id", value_ORIGINAL, country_id.toString());
		
		if(change_control_OK)
		{								
			person.setCountry_id(country_id);
		
			person = service_natural_person.update(person);
		}
		
		request.addCallbackParam("country_id",  country_id);	
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
