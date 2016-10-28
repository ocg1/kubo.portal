package mx.com.kubo.registro.datos.genero;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class GeneroIMP extends GeneroAMO
implements GeneroIMO
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
		
		gender_id = Integer.parseInt(select_menu.getValue().toString());
		
		init_change_control("gender_id", person.getGender_id().toString(), gender_id.toString());
		
		if(change_control_OK)
		{			
			person.setGender_id(gender_id);		
			
			person = service_natural_person.update(person);								
		}
		
		request.addCallbackParam("gender_id", gender_id);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
