package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public class NotificacionPruebaEnvioCorreo extends NotificacionIMP 
{
	public NotificacionPruebaEnvioCorreo(ConexionIMO conexion_SMTP, Scoring score, ProyectLoan proyecto)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));		
		setPruebaCorreo(true);
	}
}
