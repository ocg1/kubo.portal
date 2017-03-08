package mx.com.kubo.mesa.solicitud.perfil.nombre;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class EditorNombreIMP extends EditorNombreAMO
implements EditorNombreIMO
{	
	public final void listener_first_name(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();				
		
		init_first_name();
	}
	
	public final void listener_middle_name(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		init_middle_name();
	}

	public final void listener_father_last_name(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		init_father_last_name();			
	}

	public final void listener_mother_last_name(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		init_mother_last_name();
	}

	public void listener_guardar_cambios() 
	{
		request = RequestContext.getCurrentInstance();
		
		change_control_OK = false;
		
		init_guardar_cambios();
				
		request.addCallbackParam("full_name",                      full_name);
		request.addCallbackParam("change_control_OK",              change_control_OK);		
		request.addCallbackParam("error_first_name_ENABLED",       error_first_name_ENABLED);
		request.addCallbackParam("error_father_last_name_ENABLED", error_father_last_name_ENABLED);				
	}

	public void listener_cancelar() 
	{
		request = RequestContext.getCurrentInstance();
		
		init_person();
		
		System.out.println("EditorNombreIMP.listener_cancelar(): OK");
		
		request.addCallbackParam("first_name",       first_name);
		request.addCallbackParam("middle_name",      middle_name);
		request.addCallbackParam("father_last_name", father_last_name);
		request.addCallbackParam("mother_last_name", mother_last_name);
	}
}
