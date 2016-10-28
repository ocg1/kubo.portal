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

public class NotificacionPreregistroInversionista extends NotificacionDMO
implements NotificacionIMO 
{
	public NotificacionPreregistroInversionista(ConexionIMO conexion_SMTP, Membership acreditado)
	{	
		setStrFecha(date_format.format(new Date()));
		
		this.conexion_SMTP    = conexion_SMTP;
	
		this.acreditado = acreditado;
		
		company_id    = acreditado.getMembershipPK().getCompany_id();
		prospectus_id = acreditado.getMembershipPK().getProspectus_id() + "";
		
		prospectName  = acreditado.getPerson().getFirst_name()        + " "  
				      + acreditado.getPerson().getMiddle_name()       + " "
				      + acreditado.getPerson().getFather_last_name()  + " "
				      + acreditado.getPerson().getMother_last_name();
		
		emailAcred = acreditado.getEmail();	
		
		registrationReason = service_reason.get_reason_TOKEN(acreditado);
		
		subject = "Preregistro del Inversionista :: " + prospectus_id + " :: " + prospectName;
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
    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/preregistro-inversionista.html");  
    	
    	body_text = body_text.replaceAll("###Fecha###",           date_format_hora.format(new Date()));
    	body_text = body_text.replaceAll("###prospectus_id###",   prospectus_id  );        	
    	body_text = body_text.replaceAll("###prospectus_name###", prospectName);
    	body_text = body_text.replaceAll("###email###",           emailAcred);     
    	body_text = body_text.replaceAll("###REGISTRATION_REASON###", registrationReason);
	}
}

