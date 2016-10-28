package mx.com.kubo.test.perfil_completo;

import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMO;
import mx.com.kubo.services.ProspectusService;

public interface TestViviendaIMO 
{
	EditorViviendaIMO getEditor_vivienda();
	
	void setService_prospecto(ProspectusService service);
}
