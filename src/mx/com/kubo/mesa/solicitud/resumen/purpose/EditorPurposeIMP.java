package mx.com.kubo.mesa.solicitud.resumen.purpose;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class EditorPurposeIMP extends EditorPurposeDMO
implements EditorPurposeIMO
{
	public void init()
	{
		purpose_list = service_purpose.getPurposeList();
	}
	
	public final void init_purpose_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		purpose_id = Integer.parseInt(select_one_menu.getValue().toString());
		
		field_type_id = purpose_id;
		
		request.addCallbackParam("purpose_id", purpose_id);
	}
	
	public final void save() 
	{				
		change_control_bean.setNewValue(purpose_id + "");
		
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
			proyect_loan.getProyect().setPurpose_id(purpose_id);
			
			update_OK = service_proyect.update(proyect_loan.getProyect());
			
			if(update_OK)
			{			
				proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
				
				purpose_id = proyect_loan.getProyect().getPurpose_id();
				
				original_value = purpose_id + "";
			}
		}		
	}
}
