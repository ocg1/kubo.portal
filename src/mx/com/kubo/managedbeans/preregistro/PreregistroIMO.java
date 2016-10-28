package mx.com.kubo.managedbeans.preregistro;

import javax.faces.event.AjaxBehaviorEvent;

public interface PreregistroIMO 
{
	void listener_email          (AjaxBehaviorEvent evento);
	void listener_email_confirmar(AjaxBehaviorEvent evento);
	
	void setArea(Character area);
	
	String addNewNaturalPerson();
}
