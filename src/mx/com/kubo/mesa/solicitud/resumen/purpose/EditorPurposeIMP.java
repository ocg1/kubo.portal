package mx.com.kubo.mesa.solicitud.resumen.purpose;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Purpose;

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
		
		purpose = purpose_list.get(purpose_id - 1);
		
		type_id = purpose.getType_id();
		   name = purpose.getName();
		
		request.addCallbackParam("name", name);
		request.addCallbackParam("type_id", type_id);
		request.addCallbackParam("purpose_id", purpose_id);
	}
	
	public final void save() 
	{						
		sb = new StringBuilder();
		sb.append("purpose_id = ").append(purpose_id).append(" - ");
		sb.append("type_id = ").append(type_id).append(" - ");
		sb.append(name);
		
		new_value = sb.toString();
		
		field_type_id = purpose_id;
		
		save_change_control();		
		init_change_control_bean();
		
		if(change_control_OK)
		{
			init_editor();
		}		
	}
	
	public final void init_editor() 
	{
		proyect_loan.getProyect().setPurpose_id(purpose_id);
		proyect_loan.getProyect().setType_id(type_id);
		
		update_OK = service_proyect.update(proyect_loan.getProyect());
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			purpose_id = proyect_loan.getProyect().getPurpose_id();
			
			original_value = purpose_id + "";
		}
	}
}
