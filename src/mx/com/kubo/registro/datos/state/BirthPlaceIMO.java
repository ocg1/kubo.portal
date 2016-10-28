package mx.com.kubo.registro.datos.state;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.datos.DatosPersonalesIMO;

public interface BirthPlaceIMO extends DatosPersonalesIMO
{
	void init_change(AjaxBehaviorEvent event);
}
