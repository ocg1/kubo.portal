package mx.com.kubo.notificaciones.notificacion;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.notificaciones.conexion.ConexionIMO;

public abstract class NotificacionIMP extends NotificacionAMO
implements NotificacionIMO
{		
	public NotificacionIMP(ConexionIMO conexion_SMTP)
	{
		this.conexion_SMTP = conexion_SMTP;				
	}
		
	public final MimeMessage getMIME_message()
	{
		try 
		{	
			MIME_message = asignarMIME_message();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		
		return MIME_message;
	}	
}
