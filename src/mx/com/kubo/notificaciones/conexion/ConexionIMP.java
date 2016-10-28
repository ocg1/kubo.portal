package mx.com.kubo.notificaciones.conexion;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.controller.FileProp;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public final class ConexionIMP extends ConexionDMO
implements ConexionIMO
{				
	public ConexionIMP(SMTPServerDMO server_SMTP)
	{
		super(server_SMTP);
	}
			
	public final boolean connect()  
	{	
		boolean bandera = false;
		
		String user     = FileProp.propConfig.getProp(server_SMTP.getAccount_code() + "user");
		String password = FileProp.propConfig.getProp(server_SMTP.getAccount_code() + "password");				
		
		try 
		{
			System.out.println("Conectando al servidor SMTP ...");
			
			transport.connect(user, password);	
			
			bandera = true;
			
		} catch (MessagingException e) {						
			System.out.println(e.getMessage());			
		} 
		
		return bandera;
	}
	
	public final void close() throws MessagingException 
	{
		transport.close();
	}
	
	public final void send(MimeMessage message, Membership receptor) throws MessagingException, SendFailedException
	{
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor.getEmail()));     
        
		transport.sendMessage(message, message.getAllRecipients());
	}

	public final void send(MimeMessage message, ReceptoresIMO receptores) throws MessagingException, SendFailedException
	{
        for(Membership receptor: receptores.getLista_receptores_TO())
        {
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor.getEmail()));
        }
        
        for(Membership receptor: receptores.getLista_receptores_CC())
        {
        	message.addRecipient(Message.RecipientType.CC, new InternetAddress(receptor.getEmail()));
        }
        
        for(Membership receptor: receptores.getLista_receptores_CCO())
        {
        	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(receptor.getEmail()));
        }
        
		transport.sendMessage(message, message.getAllRecipients());
	}	
}
