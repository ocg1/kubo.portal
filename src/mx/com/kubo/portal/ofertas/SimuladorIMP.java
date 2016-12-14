package mx.com.kubo.portal.ofertas;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class SimuladorIMP extends SimuladorAMO 
implements SimuladorIMO 
{
	public void init()
	{
		init_list_frequency();		
	}
	
	public void init_input_text(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		init_text_value();										
	}		

	public final void initSelectOneMenu(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) event.getComponent();
		
		init_select_value();
	}	

	public final void init_simulator()
	{				
		request = RequestContext.getCurrentInstance();
		
		super.init_simulator();		
		
		request.addCallbackParam("max_payment_ENABLED", max_payment_ENABLED);
	}
}
