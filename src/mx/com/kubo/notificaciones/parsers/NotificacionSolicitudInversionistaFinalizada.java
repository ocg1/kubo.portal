package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public class NotificacionSolicitudInversionistaFinalizada extends NotificacionIMP 
{	
	public NotificacionSolicitudInversionistaFinalizada(ConexionIMO conexion_SMTP, Membership acreditado)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));
		
		setAvisoFinSolicitudInv(true);
		asignarDatosAcreditado(acreditado);				
	}
}
