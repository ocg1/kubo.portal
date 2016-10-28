package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public final class NotificacionErrorDesarrollo extends NotificacionIMP
{
	public NotificacionErrorDesarrollo(ConexionIMO conexion_SMTP, Membership emisor, Scoring score, String errormsg)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));
//		asignar_LISTA_RECEPTORES(receptores);
		
		asignarError(errormsg);
		asignarDatosAcreditado(emisor);
	}
}
