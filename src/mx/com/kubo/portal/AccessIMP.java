package mx.com.kubo.portal;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class AccessIMP extends AccessAMO
implements AccessIMO
{
	public final void init_access_CONFIG(AjaxBehaviorEvent event) 
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		access_CONFIG = null;
		
		if( input_text != null && input_text.getValue() != null ){
				
				access_CONFIG = input_text.getValue().toString();
				
				version_description = systemparamservice.getVersion_description();
				
				if( access_CONFIG != null && access_CONFIG.length() > 3)
				{
					init_access_CONFIG();
					init_access();	
				}
		
		}
			
		request.addCallbackParam("init_access_OK", access_OK);	
	}
}
