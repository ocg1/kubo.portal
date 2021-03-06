package mx.com.kubo.mesa.solicitud.resumen.rate;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorRateIMP extends EditorRateDMO
implements EditorRateIMO 
{
	public void init_rate(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		try
		{
			rate = Double.parseDouble(input_text.getValue().toString());
			
			request.addCallbackParam("rate", rate);
			
		} catch (Exception e) {
			
			request.addCallbackParam("error_MSG", e.getMessage());			
		}				
	}
	
	public final void save() 
	{							
		new_value = rate + "";
		
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}		
	}

	public final void init_editor() 
	{
		proyect_loan.setRate(rate);
		proyect_loan.setRate_with_opening(rate);
		
		update_OK = service_proyect_loan.update(proyect_loan);
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			rate = proyect_loan.getRate();
			
			original_value = rate + "";
		}
	}
}
