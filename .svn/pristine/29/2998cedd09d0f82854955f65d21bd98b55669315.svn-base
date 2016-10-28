package mx.com.kubo.notificaciones.parsers;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionDMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;

public class NotificacionEnvioContratos extends NotificacionDMO
implements NotificacionIMO 
{
	public NotificacionEnvioContratos(ConexionIMO conexion_SMTP, Membership acreditado, List<String> lista_archivos)
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
		
		lista_archivos_adjuntos = lista_archivos;
		
		subject = "Tus Contratos Kubo.Financiero ";
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
	        
	        
	        //---------
	        
	        if( lista_archivos_adjuntos != null && lista_archivos_adjuntos.size() > 0  ){
	        
			     	// Se compone la parte del texto 
		           
		            List<BodyPart> bp = new LinkedList<BodyPart>();//<-------creamos una lista de adjuntos 
		            // Se compone el adjunto con la imagen 
		             
		             for( String archivostr : lista_archivos_adjuntos ) 
		             { 
		            	 
		            	 String ruta = archivostr.split("::")[0];
		            	 String nombre = archivostr.split("::")[1];
		            	 
		             BodyPart adjunto = new MimeBodyPart(); 
		             adjunto.setDataHandler(new DataHandler(new FileDataSource( ruta ))); 
		             adjunto.setFileName( nombre ); 
		             bp.add(adjunto);//<----------------añadimos el elemento a la lista 
		             } 
		            
		             Iterator<BodyPart> it = bp.iterator();//<------------la iteramos 
		             
		             while(it.hasNext())//<----------------la recorremos 
		             { 
		            	 BodyPart attach =(BodyPart)it.next();//<------------obtenemos el objeto 
		            	 mimeMultipart.addBodyPart(attach);//<-----------------finalmente lo añadimos al mensaje 
		             } 
            
	        }
	        //---------
	        
	        mimeMultipart.addBodyPart( body );
	        
	        MIME_message.setContent(mimeMultipart);
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		return MIME_message;
	}	
	
	private void asignarFileMIME_message()
	{	        	        	                                                                        					
    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/envio_contratos.html");  
    	
    	body_text = body_text.replaceAll("###Fecha###",           date_format_hora.format(new Date()));
    	body_text = body_text.replaceAll("###prospectus_id###",   prospectus_id  );        	
    	body_text = body_text.replaceAll("###prospectus_name###", prospectName);
	}
}

