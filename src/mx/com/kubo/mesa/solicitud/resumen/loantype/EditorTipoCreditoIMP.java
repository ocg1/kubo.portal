package mx.com.kubo.mesa.solicitud.resumen.loantype;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class EditorTipoCreditoIMP extends EditorTipoCreditoDMO
implements EditorTipoCreditoIMO
{
	public final void listener_tipo_credito(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		loan_type_id = select_one_menu.getValue().toString();
		
		field_type_id = Integer.parseInt(loan_type_id);
		
		request.addCallbackParam("loan_type_id", loan_type_id);
	}
	
	public final void listener_guardar_cambios() 
	{
		change_control_bean.setNewValue(loan_type_id);
		
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
			proyect_loan.getLoantype().getPk().setLoan_type_id(loan_type_id);
			proyect_loan.setLoan_type(loan_type_id);
			
			update_OK = service_proyect_loan.update(proyect_loan);
			
			if(update_OK)
			{			
				proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
				
				tipo_de_credito = proyect_loan.getLoantype().getLoan_type_desc();
				
				original_value = loan_type_id + "";
			}
		}						
	}
}
