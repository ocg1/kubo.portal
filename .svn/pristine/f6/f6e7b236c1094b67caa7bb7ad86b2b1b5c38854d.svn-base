package mx.com.kubo.registro.datos.nombre;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class PersonNameIMP extends PersonNameAMO
implements PersonNameIMO
{
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_first_name(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		first_name = input_text.getValue().toString();
				
		init_change_control("first_name", person.getFirst_name(), first_name);
		
		if(change_control_OK)
		{		
			person.setFirst_name(first_name);
			
			person = service_natural_person.update(person);
		}
		
		request.addCallbackParam("first_name", first_name);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
	
	public final void init_middle_name(AjaxBehaviorEvent event) 
	{		
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		middle_name = input_text.getValue().toString();
		
		init_change_control("middle_name", person.getMiddle_name(), middle_name);
		
		person.setMiddle_name(middle_name);
		
		person = service_natural_person.update(person);
		
		request.addCallbackParam("middle_name", middle_name);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}

	public final void init_father_last_name(AjaxBehaviorEvent event) 
	{	
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		father_last_name = input_text.getValue().toString();
		
		init_change_control("father_last_name", person.getFather_last_name(), father_last_name);
		
		person.setFather_last_name(father_last_name);
		
		person = service_natural_person.update(person);
		
		request.addCallbackParam("father_last_name", father_last_name);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}

	public final void init_mother_last_name(AjaxBehaviorEvent event)
	{	
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		mother_last_name = input_text.getValue().toString();
		
		init_change_control("mother_last_name", person.getMother_last_name(), mother_last_name);
		
		person.setMother_last_name(mother_last_name);
		
		person = service_natural_person.update(person);
		
		request.addCallbackParam("mother_last_name", mother_last_name);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
