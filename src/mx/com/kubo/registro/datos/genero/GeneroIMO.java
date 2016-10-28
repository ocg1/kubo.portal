package mx.com.kubo.registro.datos.genero;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.datos.DatosPersonalesIMO;

public interface GeneroIMO extends DatosPersonalesIMO
{		
	void init_change(AjaxBehaviorEvent event);
}
