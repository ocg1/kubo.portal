package mx.com.kubo.managedbeans.mesa.solicitud.estatus;

import static mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan.BORRADOR;

import java.text.ParseException;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

import org.primefaces.context.RequestContext;

public final class CasosPospuestosIMP extends CasosPospuestosPMO
implements CasosPospuestosIMO
{	
	public CasosPospuestosIMP(EstatusProyectLoan estatus, Date fecha_ORIGINAL)
	{
		this.estatus        = estatus;
		this.fecha_ORIGINAL = fecha_ORIGINAL;
		
		init_calendario();
	}
	
	public CasosPospuestosIMP()
	{
		estatus = BORRADOR;
		
		fecha_ORIGINAL                  = null;
		fecha_ORIGINAL_pospuesta        = null;
		fecha_ORIGINAL_pre_autorizacion = null;
		
		init_calendario();
	}
	
	public final void listener_selected_day(ValueChangeEvent evento)
	{
		selected_day = (String) evento.getNewValue();
		
		System.out.printf("\nCasosPospuestosPMO.listener_selected_day(): " + selected_day);
		
		asignar_fecha_pospuesta();
	}
	
	public final void listener_selected_month(ValueChangeEvent evento)
	{
		selected_month = (String) evento.getNewValue();
		
		System.out.printf("\nCasosPospuestosPMO.listener_selected_month(): " + selected_month);
		
		asignar_fecha_pospuesta();
	}
	
	public final void listener_selected_year(ValueChangeEvent evento)
	{
		selected_year = (String) evento.getNewValue();
		
		System.out.printf("\nCasosPospuestosPMO.listener_selected_year(): " + selected_year);	
		
		asignar_fecha_pospuesta();
	}
		
	public final void listener_selected_day(AjaxBehaviorEvent evento)
	{
		ajax_inputText = (HtmlInputText) evento.getComponent();
		selected_day   = (String) ajax_inputText.getValue();
		
		System.out.printf("\nCasosPospuestosPMO.listener_day_selected(): " + selected_day);
		
		asignar_fecha_pospuesta();
	}
	
	public final void listener_selected_month(AjaxBehaviorEvent evento)
	{
		ajax_inputText = (HtmlInputText) evento.getComponent();
		selected_month = (String) ajax_inputText.getValue();
		
		System.out.printf("\nCasosPospuestosPMO.listener_month_selected(): " + selected_month);
		
		asignar_fecha_pospuesta();
	}
	
	public final void listener_selected_year(AjaxBehaviorEvent evento)
	{
		ajax_inputText = (HtmlInputText) evento.getComponent();
		selected_year  = (String) ajax_inputText.getValue();
		
		System.out.println("\nCasosPospuestosPMO.listener_year_selected(): " + selected_year);		
		
		asignar_fecha_pospuesta();
	}
	
	private void asignar_fecha_pospuesta()
	{				
		request = RequestContext.getCurrentInstance();
		
		if(validar_fecha())
		{							
			System.out.printf("\nCasosPospuestosPMO.asignar_fecha_pospuesta(): ");
			
			try
			{
				switch(estatus)
				{
					case POSPUESTO:
						fecha_NEW = formatter_date.parse(asignar_fecha_SELECTED());
						
						fecha_NEW_pospuesta = fecha_NEW;
					break;
					
					case PRE_AUTORIZADO:
						fecha_NEW = formatter_date.parse(asignar_fecha_SELECTED());
						
						fecha_NEW_pre_autorizacion = fecha_NEW;
					break;
					
					default: break;
				}				
				
				System.out.println(fecha_NEW);
				
				if(asignar_fecha_posterior_to_ORIGINAL())
				{										
					request.addCallbackParam("isFechaValida", true);
					
				} else {
					
					request.addCallbackParam("isFechaValida", false);
				}
							
			} catch (ParseException e) {		
				
				e.printStackTrace();
				
				request.addCallbackParam("isFechaValida", false);
			} 
			
		} else {
			
			request.addCallbackParam("isFechaValida", false);
		}
	}
}