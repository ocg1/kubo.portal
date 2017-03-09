package mx.com.kubo.mesa.solicitud.resumen.rate;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorRateInvestorIMP extends EditorRateInvestorDMO 
implements EditorRateIMO
{
	public void init_rate(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		try
		{
			rate_investor = Double.parseDouble(input_text.getValue().toString());
			
			request.addCallbackParam("rate_investor", rate_investor);
			
		} catch (Exception e) {
			
			request.addCallbackParam("error_MSG", e.getMessage());			
		}				
	}
	
	public final void save() 
	{							
		new_value = rate_investor + "";
		
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}		
	}

	public final void init_editor() 
	{
		proyect_loan.setRate_investor(rate_investor);
		
		update_OK = service_proyect_loan.update(proyect_loan);
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			rate_investor = proyect_loan.getRate_investor();
			
			original_value = rate_investor + "";
		}
	}
}
