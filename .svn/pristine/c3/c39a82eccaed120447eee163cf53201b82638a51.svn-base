package mx.com.kubo.registro.datos.birthdate;

import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public final class FechaNacimientoIMP extends FechaNacimientoAMO
implements FechaNacimientoIMO
{		
	public final void init() 
	{
		init_system_param();
		init_lista_dias_meses();
		init_lista_years();	
		init_fecha_nacimiento();
	}

	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_day(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		day = (String) select_menu.getValue();
		
		asignar_fecha_nacimiento();
				
		if(fecha_OK)
		{
			guardar_fecha_nacimiento();
		}
		
		request.addCallbackParam("fecha_OK", fecha_OK);
	}
	
	public final void init_month(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		month = (String) select_menu.getValue();
		
		asignar_fecha_nacimiento();
				
		if(fecha_OK)
		{
			guardar_fecha_nacimiento();
		}
		
		request.addCallbackParam("fecha_OK", fecha_OK);
	}
	
	public final void init_year(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		year = (String) select_menu.getValue();
		
		asignar_fecha_nacimiento();
				
		if(fecha_OK)
		{
			guardar_fecha_nacimiento();
		}
		
		request.addCallbackParam("fecha_OK", fecha_OK);
	}
}
