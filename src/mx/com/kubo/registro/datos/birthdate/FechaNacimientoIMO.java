package mx.com.kubo.registro.datos.birthdate;

import java.util.ArrayList;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.datos.DatosPersonalesIMO;
import mx.com.kubo.services.SystemParamService;

public interface FechaNacimientoIMO extends DatosPersonalesIMO
{
	void setService_system_param   (SystemParamService   service);
	
	void init();	
	
	void init_day  (AjaxBehaviorEvent evento);
	void init_month(AjaxBehaviorEvent evento);
	void init_year (AjaxBehaviorEvent evento);
	
	int getEDAD_MAX_IS_AGE_VALID();
	
	boolean isRenderMsg();
	boolean isRenderMsgTutor();
	boolean isRango_age_OK();
	boolean isEdad_min_OK();
	boolean isEdad_max_OK();
	
	String getMsgTutor();
	String getMsg();
	
	ArrayList<String> getLista_dias();
	ArrayList<String> getLista_meses();
	ArrayList<String> getLista_years();
}
