package mx.com.kubo.mesa.solicitud;

import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class ReporteInusualIMP extends ReporteInusualAMO  
implements ReporteInusualIMO
{
	public final void init()
	{
		lista_unusual_behavior = service_catalogos.getLista_unusual_behavior();
	}
	
	public final void init_behavior_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) event.getComponent();
		
		behavior_id = Integer.parseInt(select_one_menu.getValue().toString());
		
		request.addCallbackParam("behavior_id", behavior_id);
	}
	
	public final void init_comments(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		text_area = (HtmlInputTextarea) event.getComponent();
		
		comments = text_area.getValue().toString();
		
		request.addCallbackParam("comments", comments);
	}
	
	public final void init_reporte()
	{
		request = RequestContext.getCurrentInstance();
		
		init_notification_log();
		init_access();
		
		if(persist_OK)
		{
			behavior_id = 0;
			
			comments = null;
		}
		
		request.addCallbackParam("persist_OK", persist_OK);
	}
}
