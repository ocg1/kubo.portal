package mx.com.kubo.registro.datos.moral;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class PersonaMoralIMP extends PersonaMoralAMO
implements PersonaMoralIMO
{
	public final void init()
	{
		lista_society_type = service_catalogos.getLista_society_type();				
	}

	public final void init_person_type_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
				
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		person_type_id = select_radio.getValue().toString();
		
		init_persona_moral_ENABLED();
		
		new_value = person_type_id;
		
		original_value = prospecto.getPerson_type().toString();						
		
		if(!new_value.equals(original_value))
		{
			prospecto.setPerson_type(person_type_id.charAt(0));			
						
			service_prospectus.update(prospecto);	
			
			init_change_control(PROSPECTUS, "person_type_id", original_value, new_value);
		}				
		
		request.addCallbackParam("person_type_id", person_type_id);
		request.addCallbackParam("persona_moral_ENABLED", persona_moral_ENABLED);
	}

	public final void init_legal_name(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		legal_name = input_text.getValue().toString();
		
		new_value = legal_name;
						
		if(!new_value.equals(legal_name_ORIGINAL))
		{
			person.setLegal_name(legal_name);
			
			init_change_control(NATURAL_PERSON, "legal_name", legal_name_ORIGINAL, new_value);
			
			service_natural_person.update(person);
			
			legal_name_ORIGINAL = person.getLegal_name();						
		}
		
		request.addCallbackParam("legal_name", legal_name);
	}
	
	public final void init_society_type_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();		
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		society_type_id = Integer.parseInt(select_menu.getValue().toString());
		
		new_value = society_type_id.toString();
		
		if(person.getSociety_type_id() != null)
		{
			original_value = person.getSociety_type_id().toString();
			
		} else {
			
			original_value = "";
		}
		
		if(!new_value.equals(original_value))
		{
			person.setSociety_type_id(society_type_id);
			
			service_natural_person.update(person);	
			
			init_change_control(NATURAL_PERSON, "society_type_id", original_value, new_value);
		}
		
		request.addCallbackParam("society_type_id", society_type_id);
	}
	
	public final void init_company_rfc(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		mx_company_rfc = input_text.getValue().toString();
		
		new_value = mx_company_rfc;
		
		if(!new_value.equals(company_RFC_ORIGINAL))
		{		
			person.setMx_company_rfc(mx_company_rfc);
			
			init_change_control(NATURAL_PERSON, "mx_company_rfc", company_RFC_ORIGINAL, new_value);
			
			service_natural_person.update(person);
			
			company_RFC_ORIGINAL = person.getMx_company_rfc();						
		}
		
		request.addCallbackParam("mx_company_rfc", mx_company_rfc);
	}
}
