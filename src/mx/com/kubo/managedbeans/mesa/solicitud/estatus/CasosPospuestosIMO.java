package mx.com.kubo.managedbeans.mesa.solicitud.estatus;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

public interface CasosPospuestosIMO 
{	
	Date getFecha_ORIGINAL();
	Date getFecha_NEW();
	
	Date getFecha_ORIGINAL_pospuesta();
	Date getFecha_ORIGINAL_pre_autorizacion();
	
	Date getFecha_NEW_pospuesta();
	Date getFecha_NEW_pre_autorizacion();
	
	String getSelected_day();
	String getSelected_month();
	String getSelected_year();
	
	ArrayList<SelectItem> getList_of_days();
	ArrayList<SelectItem> getList_of_months();
	ArrayList<SelectItem> getList_of_years();
	
	void setEstatus_SELECTED (EstatusProyectLoan estatus);
	
	void setFecha_ORIGINAL (Date fecha_ORIGINAL);				
	void setFecha_ORIGINAL_pospuesta        (Date fecha_ORIGINAL);
	void setFecha_ORIGINAL_pre_autorizacion (Date fecha_ORIGINAL);
	
	void listener_selected_day   (ValueChangeEvent evento);
	void listener_selected_month (ValueChangeEvent evento);
	void listener_selected_year  (ValueChangeEvent evento);
	
	void listener_selected_day   (AjaxBehaviorEvent evento);
	void listener_selected_month (AjaxBehaviorEvent evento);
	void listener_selected_year  (AjaxBehaviorEvent evento);
}
