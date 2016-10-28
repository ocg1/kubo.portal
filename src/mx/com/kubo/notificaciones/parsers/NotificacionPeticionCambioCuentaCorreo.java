package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public class NotificacionPeticionCambioCuentaCorreo extends NotificacionIMP 
{
	public NotificacionPeticionCambioCuentaCorreo(ConexionIMO conexion_SMTP, Membership emisor)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));		
		
		setConfmail(true);		
		setNameTo(emisor.getPerson().NombreCompletoNPM());
		setClaveActivacion(emisor.getActivation_code());
		setStrTo(emisor.getEmail());
	}
}
