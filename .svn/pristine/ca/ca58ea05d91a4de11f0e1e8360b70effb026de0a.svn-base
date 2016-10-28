package mx.com.kubo.listeners.mesa.solicitud;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public final class EditorTipoCreditoIMP extends EditorTipoCreditoDMO
implements EditorTipoCreditoIMO
{
	public final void listener_tipo_credito(AjaxBehaviorEvent evento)
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		loan_type_id = (String) select_one_menu.getValue();
		
		System.out.println("EditorTipoCreditoIMP.listener_tipo_credito(): " + loan_type_id);
	}
	
	public final void listener_guardar_cambios() 
	{
		change_control_bean.setNewValue(loan_type_id);
		
		if(change_control_bean.getWhyChangeData() != null)
		{
			saveChangeData();
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
			}
		}
				
		System.out.println("EditorTipoCreditoIMP.listener_guardar_cambios(): " + update_OK);
	}
	
	private void saveChangeData()
	{
		changeCtrlPK = new Change_controlPK();		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(emisor_prospectus_id);
		changeCtrl.setAfected_table("ln_proyect_loan");
		changeCtrl.setField("loan_type");
		changeCtrl.setIp(ip_address);			
		changeCtrl.setOriginal_value(change_control_bean.getOrigValue());
		changeCtrl.setNew_value     (change_control_bean.getNewValue());		
		changeCtrl.setComments      (change_control_bean.getWhyChangeData());
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			change_control_OK = true;	
			
		} else {
			
			change_control_OK = false;
		}
	}
}
