package mx.com.kubo.registro.datos.state;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class BirthPlaceIMP extends BirthPlaceAMO
implements BirthPlaceIMO
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
		
		state_id = Integer.parseInt(select_menu.getValue().toString());
		
		value_ORIGINAL = person.getState_id() != null ? person.getState_id().toString() : "";
		
		if( state_id != null && state_id.intValue() != 99 ){
		
			if( value_ORIGINAL.equals("") && person.getBirth_place() != null && person.getBirth_place().equals("1") ){
			
				init_change_control("state_id", "EXTRANJERO", state_id.toString());
				
			}else{
				
				init_change_control("state_id", value_ORIGINAL, state_id.toString());
				
			}
			
			if(change_control_OK)
			{			
				person.setState_id(state_id);
				person.setBirth_place("0");
				person = service_natural_person.update(person);
			}
		
		}else{
			
			init_change_control("state_id", value_ORIGINAL, "EXTRANJERO");
			
			if(change_control_OK)
			{			
				person.setState_id(null);
				person.setBirth_place("1");
				person = service_natural_person.update(person);
			}
		}
		
		request.addCallbackParam("state_id",  state_id);	
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
