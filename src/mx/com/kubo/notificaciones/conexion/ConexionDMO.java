package mx.com.kubo.notificaciones.conexion;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.controller.FileProp;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public abstract class ConexionDMO 
{
	protected SMTPServerDMO server_SMTP;		
	protected Transport     transport;			
	protected Session       session;
	
	protected ConexionDMO(SMTPServerDMO server_SMTP)
	{		
		this.server_SMTP = server_SMTP;
		
        session  = Session.getDefaultInstance(FileProp.propConfig.getThisPropertie());
        session.setDebug(false);
        
        transport = getTransport();
	}
	
	private Transport getTransport() 
	{	    		
		Transport transport = null;
		
    	try 
    	{
//    	    transport = session.getTransport( FileProp.propConfig.getProp( "mail.transport" ) );        		
    		transport = session.getTransport(FileProp.propConfig.getProp(server_SMTP.getAccount_code() + "transport"));			
		} catch (NoSuchProviderException e) {			
			e.printStackTrace();
		}
    	
    	return transport;
	}
	
	public final SMTPServerDMO getServerSMTP()
	{
		return server_SMTP;
	}
	
	public final MimeMessage getMessage() 
	{                
        MimeMessage message = new MimeMessage(session);
      
        try 
        {
//        	message.setFrom(new InternetAddress( FileProp.propConfig.getProp( "mail.smtp.user" )));
			message.setFrom(new InternetAddress(FileProp.propConfig.getProp(server_SMTP.getAccount_code() + "user")));			
		} catch (AddressException e) {			
			e.printStackTrace();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}	
        
        return message;
	}	
}
