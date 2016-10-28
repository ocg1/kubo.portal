package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionDMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;

import static mx.com.kubo.notificaciones.notificacion.NotificacionHTML.*;

public class NotificacionGeneracionPasswordActivacion extends NotificacionDMO
implements NotificacionIMO
{	
	public NotificacionGeneracionPasswordActivacion(ConexionIMO conexion_SMTP, String password, Membership emisor)
	{
		this.conexion_SMTP       = conexion_SMTP;
		this.emisor              = emisor;		
		this.password_activacion = password;     
		
		MIME_message  = conexion_SMTP.getMessage();	
		
		asinar_URL_activacion();
		
		asignarFileMIME_message();				
	}
	
	public final MimeMessage getMIME_message()
	{		
		return MIME_message;
	}	
	
	private void asinar_URL_activacion()
	{
		url_activacion = URL + PAGE + CLAVE + "&" + PROSPECTO + "&" + REGISTRO 
					   + "&counter=" + emisor.getActivation_code() + "&" 
				       + VALOR + "&" + CLAVE_1 + "&" + CLAVE_2;
	}
	
	private void asignarFileMIME_message()
	{	        	        	                                                                        
		try 
		{
			MIME_message.setSubject("Hola, bienvenido a kubo.financiero");
			
	    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/generacion_password_activacion.html");  
        	
        	body_text = body_text.replaceAll("###Fecha###",               date_format.format(new Date()));
        	body_text = body_text.replaceAll("###membership_name###",     emisor.getPerson().getFirst_name());        	
        	body_text = body_text.replaceAll("###membership_email###",    emisor.getEmail());
        	body_text = body_text.replaceAll("###password_activacion###", password_activacion);
        	body_text = body_text.replaceAll("###url_activacion###",      url_activacion);        	

	        body.setText( body_text,"ISO-8859-1","html" );
	        
	        mimeMultipart.addBodyPart( body );
	        
	        MIME_message.setContent(mimeMultipart);
	        
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}			            	    	  	   
	}
	
}
