package mx.com.kubo.registro.datos.nombre;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.datos.DatosPersonalesIMO;

public interface PersonNameIMO extends DatosPersonalesIMO
{		
	void init_first_name (AjaxBehaviorEvent event);
	void init_middle_name(AjaxBehaviorEvent event);
	void init_father_last_name(AjaxBehaviorEvent event);
	void init_mother_last_name(AjaxBehaviorEvent event);
	String getFirst_name();
	String getMiddle_name();
	String getFather_last_name();
	String getMother_last_name();
}
