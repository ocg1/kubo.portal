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
		
		request.addCallbackParam("purpose_id", purpose_id);
	}
	
	public final void save() 
	{								
		new_value = purpose_id + "";
		
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
		
		update_OK = service_proyect.update(proyect_loan.getProyect());
		
		if(update_OK)
		{			
			proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
			
			purpose_id = proyect_loan.getProyect().getPurpose_id();
			
			original_value = purpose_id + "";
		}
	}
}
