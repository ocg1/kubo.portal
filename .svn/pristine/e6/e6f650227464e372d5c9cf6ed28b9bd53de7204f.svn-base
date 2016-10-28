package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionDMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;

public class NotificacionDepositoEfectivo  extends NotificacionDMO
implements NotificacionIMO  
{	
	public NotificacionDepositoEfectivo(ConexionIMO conexion_SMTP, Membership acreditado, MovimientosIMO movimiento)
	{
		this.conexion_SMTP    = conexion_SMTP;
	
		this.acreditado       = acreditado;
		
		this.monto            = movimiento.getMonto();
		this.cuenta           = movimiento.getCuenta();
		this.clabe_account_id = movimiento.getClabe_account_id();
		
		company_id    = acreditado.getMembershipPK().getCompany_id();
		prospectus_id = acreditado.getMembershipPK().getProspectus_id() + "";
		
		prospectName  = acreditado.getPerson().getFirst_name()        + " "  
				      + acreditado.getPerson().getMiddle_name()       + " "
				      + acreditado.getPerson().getFather_last_name()  + " "
				      + acreditado.getPerson().getMother_last_name();
		
		emailAcred = acreditado.getEmail();
		
		cliente_SAFI = acreditado.getPerson().getSafi_client_id();
		
		clabe_account_PK = new ClabeAccountPK(Integer.parseInt(prospectus_id), company_id, this.clabe_account_id);
		
		clabe_account = service_clabe_account.loadSelectedClabeAccount(clabe_account_PK);
		
		cuenta_CLABE = clabe_account.getMx_clabe();
		banco        = clabe_account.getBank_description();		
		
		fechaDep	 = movimiento.getFecha_deposito();		
		descripcion	 = movimiento.getDescripcion() == null ? "" : movimiento.getDescripcion().replaceAll("\\$", "") ;		
		
		subject = "Notificación de Depósito" + " :: " + prospectus_id + " :: " + prospectName;
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
    	body_text = leer_notificacion_HTML("jsf/docs/notificaciones/deposito_de_efectivo.html");  
    	
    	body_text = body_text.replaceAll("###Fecha###",           date_format_hora.format(new Date()));
    	body_text = body_text.replaceAll("###prospectus_id###",   prospectus_id  );        	
    	body_text = body_text.replaceAll("###prospectus_name###", prospectName);
    	body_text = body_text.replaceAll("###email###",           emailAcred);
    	body_text = body_text.replaceAll("###cliente_SAFI###",    cliente_SAFI);
    	body_text = body_text.replaceAll("###cuenta###",          cuenta);
    	body_text = body_text.replaceAll("###monto###",           monto);
    	body_text = body_text.replaceAll("###cuenta_CLABE###",    cuenta_CLABE);
    	body_text = body_text.replaceAll("###banco###",           banco);
    	
    	body_text = body_text.replaceAll("###fecha_deposito###",  fechaDep);
    	body_text = body_text.replaceAll("###descripcion###",     descripcion);	            	    	  	   
	}

}
