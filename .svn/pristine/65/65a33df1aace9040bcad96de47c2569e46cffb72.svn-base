package mx.com.kubo.managedbeans.mesa.autenticacion;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.mesa.autenticacion.buscador.BuscadorIMO;

public interface TableroAutenticacionIMO 
{
	void setService_membership(MembershipService  service);
	void setService_change_control(Change_controlService service);			
	
	void init_pool_autenticacion();
	void listener_autenticacion(AjaxBehaviorEvent event);
	void init_panel_desbloquear_password();
	void init_desbloquear_password();
	
	String getAutenticacion_id_TOKEN();
	
	boolean isPool_autenticacion_ENABLED();
	boolean isDesbloquear_password_ENABLED();
	
	BuscadorIMO      getBuscador();
	AutenticacionIMO getDomicilio();
	AutenticacionIMO getBirthplace();
	AutenticacionIMO getBirthdate(); 
	AutenticacionIMO getTelefono();
	AutenticacionIMO getCuenta();
	AutenticacionIMO getZipcode();
	
	List<AutenticacionIMO> getLista_preguntas();
}
