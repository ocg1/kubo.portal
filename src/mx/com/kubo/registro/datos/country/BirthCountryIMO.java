package mx.com.kubo.registro.datos.country;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.datos.DatosPersonalesIMO;

public interface BirthCountryIMO extends DatosPersonalesIMO
{
	void init_change(AjaxBehaviorEvent event);
}
