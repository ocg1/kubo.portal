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
		
		request.addCallbackParam("loan_type_id", loan_type_id);
	}
	
	public final void save() 
	{
		new_value = loan_type_id;
				
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}						
	}
	
	public final void init_editor() 
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
