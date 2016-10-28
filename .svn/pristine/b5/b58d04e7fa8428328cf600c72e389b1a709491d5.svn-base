package mx.com.kubo.managedbeans.mesa.control;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.catalogos.Channel;

public interface AltaPartnerIMO 
{
	void setSesion(SessionBean sesion);
	
	void listener_partner_id         (AjaxBehaviorEvent evento);	
	void listener_partner_name       (AjaxBehaviorEvent evento);
	void listener_partner_descripcion(AjaxBehaviorEvent evento);
	void listener_titulo_lista       (AjaxBehaviorEvent evento);
	void listener_orden_lista        (AjaxBehaviorEvent evento);
	void listener_is_active          (AjaxBehaviorEvent evento);
	void listener_channel            (AjaxBehaviorEvent evento);	
	void listener_guardar            (AjaxBehaviorEvent evento);
	
	List<Channel> getLista_canales();
}
