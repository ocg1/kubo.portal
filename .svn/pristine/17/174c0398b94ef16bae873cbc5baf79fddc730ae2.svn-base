package mx.com.kubo.mesa.autenticacion.preguntas;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

import org.primefaces.context.RequestContext;

public class CodigoPostalIMP extends DomicilioDMO
implements AutenticacionIMO
{
	public final void init_zipcode(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		value_SELECTED = (String) input_text.getValue();
		
		zipcode_ENABLED = zipcode.equals(value_SELECTED);
		
		request.addCallbackParam("zipcode_ENABLED", zipcode_ENABLED);
	}
	
	public boolean isValue_ENABLED() 
	{		
		return zipcode_ENABLED;
	}

	public String getValue_ORIGINAL() 
	{
		return zipcode;
	}
}
