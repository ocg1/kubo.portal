package mx.com.kubo.registro.datos.simulador;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class SimuladorIMP extends SimuladorAMO 
implements SimuladorIMO 
{
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public void init_input_text(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		if(input_text.getId().equals("simulador_ammount"))
		{						
			try
			{
				String value = input_text.getValue().toString();
												
				ammount = Double.parseDouble(value.replace(",", "").replace("$",""));								
				
				init_change_control("ammount", ammount_value, value);
				init_simulator();
				
				request.addCallbackParam("ammount", ammount);
				request.addCallbackParam("change_control_OK", change_control_OK);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}			
		}
	}
	
	public final void initSelectOneMenu(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) event.getComponent();
		
		if(select_one.getId().equals("simulador-purpose-id"))
		{
			purpose_id = Integer.parseInt(select_one.getValue().toString());
			
			original_value = simulator.getPurpose_id() + "";
			new_value = purpose_id.toString();
			
			init_change_control("purpose_id", original_value, new_value);
			
			if(change_control_OK)
			{
				init_simulator();
			}
			
			request.addCallbackParam("purpose_id", purpose_id);
			request.addCallbackParam("change_control_OK", change_control_OK);
			
		} else if(select_one.getId().equals("simulador-frequency-id")) {
						
			frequency_id = Integer.parseInt(select_one.getValue().toString());
			
			original_value = simulator.getFrequency_id() + "";
			new_value = frequency_id.toString();
			
			init_change_control("frequency_id", original_value, new_value);
			
			if(change_control_OK)
			{
				init_simulator();
			}
			
			request.addCallbackParam("frequency_id", frequency_id);
			request.addCallbackParam("change_control_OK", change_control_OK);
		}
	}	
}
