package mx.com.kubo.managedbeans.mesa.solicitud.estatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.model.SelectItem;

import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.impl.SystemParamServiceImp;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

public abstract class CasosPospuestosDMO 
implements CasosPospuestosIMO
{		
	protected SystemParamServiceImp system_param_service;
	
	protected Change_controlService change_control_service;
	
	protected RequestContext request;
	protected HtmlInputText  ajax_inputText;
	
	protected EstatusProyectLoan estatus;	
	protected SystemParamPK      system_param_PK;
	
	protected Calendar calendar;
	protected Calendar selected_calendar;
	
	protected Date fecha_ORIGINAL;
	protected Date fecha_ORIGINAL_pospuesta;
	protected Date fecha_ORIGINAL_pre_autorizacion;
	
	protected Date fecha_NEW;
	protected Date fecha_NEW_pospuesta;
	protected Date fecha_NEW_pre_autorizacion;	
	
	protected Date yyyy_anterior;
	protected Date yyyy_actual;
	protected Date yyyy_posterior;	
		
	protected SimpleDateFormat formatter_date;
	protected SimpleDateFormat formatter_dd;
	protected SimpleDateFormat formatter_MM;
	protected SimpleDateFormat formatter_yyyy;

	protected ArrayList<SelectItem> list_of_days;  
	protected ArrayList<SelectItem> list_of_months;
	protected ArrayList<SelectItem> list_of_years;		
	
	protected StringBuilder sb;
	
	protected String selected_day;
	protected String selected_month; 
	protected String selected_year;	
	protected String system_param;
	
	protected final String lista_months[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		
	protected final int PARAM_DIAS_DEFAULT_POSPUESTOS       = 53;
	protected final int PARAM_DIAS_DEFAULT_PRE_AUTORIZACION = 61;
	
	protected boolean fecha_posterior_to_ORIGINAL;	
	
	protected CasosPospuestosDMO()
	{		
		formatter_dd   = new SimpleDateFormat("dd");
		formatter_MM   = new SimpleDateFormat("MM");
		formatter_yyyy = new SimpleDateFormat("yyyy");
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
		
		system_param_service   = Utilities.findBean("systemParamServiceImp");
		change_control_service = Utilities.findBean("change_controlServiceImp");
	}
	
	protected int getDias_default_POSPUESTO()
	{
		system_param_PK = new SystemParamPK(PARAM_DIAS_DEFAULT_POSPUESTOS, 1);
		
		system_param = system_param_service.loadSelectedSystemParam(system_param_PK).getValue();
		
		assert(system_param.equals("180"));
				
		return Integer.parseInt(system_param);
	}
	
	protected int getDias_default_PRE_APROBACION()
	{
		system_param_PK = new SystemParamPK(PARAM_DIAS_DEFAULT_PRE_AUTORIZACION, 1);
		
		system_param = system_param_service.loadSelectedSystemParam(system_param_PK).getValue();
		
		assert(system_param.equals("14"));
				
		return Integer.parseInt(system_param);
	}
					
	public final void setEstatus_SELECTED(EstatusProyectLoan selected)
	{
		estatus = selected;
	}
	
	public final void setFecha_ORIGINAL(Date fecha)
	{
		fecha_ORIGINAL = fecha;
	}
	
	public final void setFecha_ORIGINAL_pospuesta(Date fecha)
	{
		fecha_ORIGINAL_pospuesta = fecha;
	}
	
	public final void setFecha_ORIGINAL_pre_autorizacion(Date fecha)
	{
		fecha_ORIGINAL_pre_autorizacion = fecha;
	}
	
	public final Date getFecha_ORIGINAL()
	{
		return fecha_ORIGINAL;
	}
	
	public final Date getFecha_ORIGINAL_pospuesta()
	{
		return fecha_ORIGINAL_pospuesta;
	}
	
	public final Date getFecha_ORIGINAL_pre_autorizacion()
	{
		return fecha_ORIGINAL_pre_autorizacion;
	}
	
	public final Date getFecha_NEW()
	{
		return fecha_NEW;
	}
	
	public final Date getFecha_NEW_pospuesta()
	{
		return fecha_NEW_pospuesta;
	}
	
	public final Date getFecha_NEW_pre_autorizacion()
	{
		return fecha_NEW_pre_autorizacion;
	}
	
	public final String getSelected_day() 
	{
		return selected_day;
	}
	
	public final String getSelected_month() 
	{
		return selected_month;
	}
	
	public final String getSelected_year() 
	{
		return selected_year;
	}
			
	public final ArrayList<SelectItem> getList_of_days() 
	{
		return list_of_days;
	}

	public final ArrayList<SelectItem> getList_of_months() 
	{
		return list_of_months;
	}

	public final ArrayList<SelectItem> getList_of_years() 
	{
		return list_of_years;
	}
}
