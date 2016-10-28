package mx.com.kubo.managedbeans.registro.consulta;

import java.util.Date;

import mx.com.kubo.registro.consulta.ConsultaCompletaIMO;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMO;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

public interface PreaprobacionIMO 
{
	void setService_notas(NotesService service);
	
	void init();
	void callWSSGB();
	
	int calcularEdad(Date fecha);		
	
	PersonaBloqueadaIMO getBlocked();
}
