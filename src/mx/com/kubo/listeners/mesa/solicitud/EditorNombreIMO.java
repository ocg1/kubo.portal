package mx.com.kubo.listeners.mesa.solicitud;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;

public interface EditorNombreIMO 
{
	void listener_first_name      (AjaxBehaviorEvent evento);
	void listener_middle_name     (AjaxBehaviorEvent evento);
	void listener_father_last_name(AjaxBehaviorEvent evento);
	void listener_mother_last_name(AjaxBehaviorEvent evento);
	
	void setSesion(SessionBean   sesion);
	void setPerson(NaturalPerson person);
	
	void listener_guardar_cambios();
	void listener_cancelar();
	
	String getFull_name();
	String getFirst_name();
	String getMiddle_name();
	String getFather_last_name();
	String getMother_last_name();	
	
	ChangeBean getChange_control_bean();
}
