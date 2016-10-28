package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionDMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;

public class NotificacionCancelacionCuenta extends NotificacionDMO
implements NotificacionIMO  
{
	public NotificacionCancelacionCuenta(ConexionIMO conexion_SMTP, Membership acreditado, MovimientosIMO movimiento)
	{
		this.conexion_SMTP = conexion_SMTP;	
		this.acreditado    = acreditado;
		
		cuenta      = movimiento.getCuenta();
		folio       = movimiento.getFolio();
		motive_catalog_description = movimiento.getMotivo();
		descripcion = movimiento.getDescripcion() == null ? "" : movimiento.getDescripcion() ;	
		
		company_id    = acreditado.getMembershipPK().getCompany_id();
		prospectus_id = acreditado.getMembershipPK().getProspectus_id() + "";
		
		prospectName  = acreditado.getPerson().getFirst_name()        + " "  
				      + acreditado.getPerson().getMiddle_name()       + " "
				      + acreditado.getPerson().getFather_last_name()  + " "
				      + acreditado.getPerson().getMother_last_name();
		
		cliente_SAFI = acreditado.getPerson().getSafi_client_id();						
	}
	
	public final MimeMessage getMIME_message()
	{		
		MIME_message  = conexion_SMTP.getMessage();
		
		asignarFileMIME_message();
		
		return MIME_message;
	}	
	
	private void asignarFileMIME_message()
	{	        	        	                                                                        
		try 
		{			
			MIME_message.setSubject("Notificación de Cancelación de Cuenta");
			
	    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/cancelacion_de_cuenta.html");  
        	
        	body_text = body_text.replaceAll("###Fecha###",           date_format_hora.format(new Date()));
        	body_text = body_text.replaceAll("###prospectus_id###",   prospectus_id  );        	
        	body_text = body_text.replaceAll("###prospectus_name###", prospectName);        	
        	body_text = body_text.replaceAll("###cliente_SAFI###",    cliente_SAFI);
        	body_text = body_text.replaceAll("###cuenta###",          cuenta);   
        	body_text = body_text.replaceAll("###folio###",           folio);
        	body_text = body_text.replaceAll("###motivo###",          motive_catalog_description);
        	body_text = body_text.replaceAll("###descripcion###",     descripcion);

	        body.setText( body_text,"ISO-8859-1","html" );
	        
	        mimeMultipart.addBodyPart( body );
	        
	        MIME_message.setContent(mimeMultipart);
	        
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}			            	    	  	   
	}
}
