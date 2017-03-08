package mx.com.kubo.mesa.solicitud.resumen.rate;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorRateIMP extends EditorRateDMO
implements EditorRateIMO 
{
	public void init_rate_investor(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		rate_investor = Double.parseDouble(input_text.getValue().toString());				
		
		request.addCallbackParam("rate_investor", rate_investor);
	}
	
	public final void save() 
	{				
		change_control_bean.setNewValue(rate_investor + "");
		
		change_control_OK = update_OK = false;
		
		if(change_control_bean.getWhyChangeData() != null)
		{
			save_change_control();
		}
		
		if(change_control_OK)
		{
			change_control_bean.setWhyChangeData(null);
			change_control_bean.setHasChange(true);
			
			bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
			if(bitacora_change_control != null && bitacora_change_control.size() > 0)
			{						
				change_control_bean.setLstChanges(bitacora_change_control);
				
			} else {
				
				change_control_bean.setLstChanges(null);	
			}	
		}
		
		if(change_control_OK)
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
}
