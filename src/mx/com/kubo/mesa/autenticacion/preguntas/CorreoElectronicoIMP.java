package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.mesa.autenticacion.AutenticacionDMO;
import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

public class CorreoElectronicoIMP extends AutenticacionDMO 
implements AutenticacionIMO
{
	public final void init_email(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		value_SELECTED = (String) input_text.getValue();
			
		email_ENABLED = email.equals(value_SELECTED);
						
		request.addCallbackParam("email_ENABLED", email_ENABLED);
	}
	
	public final boolean isValue_ENABLED() 
	{		
		return email_ENABLED;
	}

	public final String getValue_ORIGINAL() 
	{
		return email;
	}
}
