package mx.com.kubo.mesa.solicitud.resumen.rate;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorCommissionIMP extends EditorCommissionDMO
implements EditorCommissionIMO 
{
	public void init_opening_commission(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		try
		{
			opening_commission = Double.parseDouble(input_text.getValue().toString());
			
			request.addCallbackParam("opening_commision", opening_commission);
			
		} catch (Exception e) {
			
			request.addCallbackParam("error_MSG", e.getMessage());			
		}				
	}
	
	public final void save() 
	{							
		new_value = opening_commission + "";
		
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}		
	}
	
	public final void init_editor() 
	{
		proyect_loan.setOpening_commission(opening_commission);
		
		update_OK = service_proyect_loan.update(proyect_loan);
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			opening_commission = proyect_loan.getOpening_commission();
			
			original_value = opening_commission + "";
		}
	}
}
