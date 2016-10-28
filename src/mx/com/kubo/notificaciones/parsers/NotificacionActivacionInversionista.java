package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public class NotificacionActivacionInversionista extends NotificacionIMP 
{
	public NotificacionActivacionInversionista(ConexionIMO conexion_SMTP, Membership emisor, Membership acreditado, Scoring score, ProyectLoan proyecto)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));
		
		asignarDatosAcreditado(acreditado);
		
		setAltaInversion(true);
		setSafiClientID(acreditado.getPerson().getSafi_client_id());
		
		registrationReason = service_reason.validaRegistrationReason(emisor);
	}
}
