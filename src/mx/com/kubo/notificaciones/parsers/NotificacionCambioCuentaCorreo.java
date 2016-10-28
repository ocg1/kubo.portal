package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public class NotificacionCambioCuentaCorreo extends NotificacionIMP 
{
	public NotificacionCambioCuentaCorreo(ConexionIMO conexion_SMTP, Membership emisor)
	{
		super(conexion_SMTP);	
		
		setProspectus_id( emisor.getPerson().getNatPerPK().getProspectus_id() + "" );
		
		setStrFecha(date_format.format(new Date()));		
		setNameTo(emisor.getPerson().NombreCompletoNPM());
		setClaveActivacion(emisor.getActivation_code());
	}
}
