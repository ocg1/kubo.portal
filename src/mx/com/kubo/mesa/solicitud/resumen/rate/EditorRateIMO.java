package mx.com.kubo.mesa.solicitud.resumen.rate;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.mesa.solicitud.resumen.ChangeControlIMO;

public interface EditorRateIMO extends ChangeControlIMO
{
	void init_rate(AjaxBehaviorEvent event);
	
	void save();
	
	void init_editor();
}
