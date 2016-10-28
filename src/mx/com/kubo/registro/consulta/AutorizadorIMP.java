package mx.com.kubo.registro.consulta;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class AutorizadorIMP extends AutorizadorAMO
implements AutorizadorIMO
{
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_change(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
				
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		Integer consulta_ENABLED = Integer.parseInt(select_radio.getValue().toString());
		
		if(consulta_ENABLED == 0)
		{		
			init_change_control("", "", "", "S");
		}
		
		if(consulta_ENABLED > 0)
		{		
			init_change_control("", "", "", "N");
		}
		
		request.addCallbackParam("consulta_ENABLED", consulta_ENABLED);
	}
}
