package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionDMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;

public class NotificacionSolicitudInversionista extends NotificacionDMO
implements NotificacionIMO 
{
	public NotificacionSolicitudInversionista(ConexionIMO conexion_SMTP, Membership inversionista)
	{
		this.conexion_SMTP = conexion_SMTP;
		
		strFecha = date_format.format(new Date());		
		
		prospectName  = inversionista.getPerson().NombreCompletoNPM();
		prospectus_id = inversionista.getMembershipPK().getProspectus_id() + "";
		emailAcred    = inversionista.getEmail();
		
		creation_DATE = date_format_hora.format(inversionista.getCreation_date());
		finish_DATE   = date_format_hora.format(new Date());	
				
		registrationReason = service_reason.validaRegistrationReason(inversionista);		
		
		subject = "Solicitud de Inversión: "+getProspectus_id()+" - "+getProspectName().toUpperCase();
	}
	
	public final MimeMessage getMIME_message()
	{		
		try
		{
	        mimeMultipart  = new MimeMultipart();
	        body           = new MimeBodyPart();	
	        
			MIME_message  = conexion_SMTP.getMessage();
			MIME_message.setSubject(subject);
			
			asignarFileMIME_message();
			
	        body.setText( body_text,"ISO-8859-1","html" );
	        
	        mimeMultipart.addBodyPart( body );
	        
	        MIME_message.setContent(mimeMultipart);
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		return MIME_message;
	}	
	
	private void asignarFileMIME_message()
	{	        	        	                                                                        					
    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/solicitud-inversion-completada.html");  
    	
    	body_text = body_text.replaceAll("###Fecha###", strFecha);
    	body_text = body_text.replaceAll("###prospectNumber###", prospectus_id);
    	body_text = body_text.replaceAll("###prospectName###",   prospectName.toUpperCase());
    	body_text = body_text.replaceAll("###e-mailAcred###", emailAcred);
    	body_text = body_text.replaceAll("###REGISTRATION_REASON###", registrationReason);
    	body_text = body_text.replaceAll("###creation_date###", creation_DATE);
    	body_text = body_text.replaceAll("###finish_date###",finish_DATE);
	}
}
