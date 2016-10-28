package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.model.Phone;

public class TelefonoCelularIMP extends TelefonoDMO
implements AutenticacionIMO
{
	public final void init_celular(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		value_SELECTED = (String) input_text.getValue();
		
		for(Phone phone : celulares)
		{						
			phone_number = phone.getPhone_number();
			
			celular_ENABLED = phone_number.equals(value_SELECTED);
			
			if(celular_ENABLED) break;
		}
						
		request.addCallbackParam("celular_ENABLED", celular_ENABLED);
	}
	
	public boolean isValue_ENABLED() 
	{		
		return celular_ENABLED;
	}

	public String getValue_ORIGINAL() 
	{
		return phone_number;
	}

	public String getValue_NEW() 
	{		
		if(value_SELECTED != null)
		{
			return value_SELECTED.toString();
		}
		
		return "";
	}
}
