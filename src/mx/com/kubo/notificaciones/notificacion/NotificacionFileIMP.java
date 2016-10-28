package mx.com.kubo.notificaciones.notificacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.tools.Utilities;

public class NotificacionFileIMP extends NotificacionAMO
implements NotificacionIMO
{
	public NotificacionFileIMP(ConexionIMO conexion_SMTP)
	{
		this.conexion_SMTP = conexion_SMTP;
	}
	
	public final MimeMessage getMIME_message()
	{
		try 
		{	
			MIME_message = asignarFileMIME_message();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		
		return MIME_message;
	}	
		
	private MimeMessage asignarFileMIME_message() throws MessagingException, MessagingException
	{	        	        	           
        mimeMultipart = new MimeMultipart();
        body          = new MimeBodyPart();
                    
                   
        MimeMessage message = conexion_SMTP.getMessage();
                
    	message.setSubject("Hola, bienvenido a kubo.financiero");
    	body_text = getCuerpo();        

        body.setText( body_text,"ISO-8859-1","html" );
	            
	    mimeMultipart.addBodyPart( body );	    

	    message.setContent( mimeMultipart );

	    return message;
	}
	
	private String getCuerpo() 
	{		
		File archivo      = null;
	    FileReader fr     = null;
	    BufferedReader br = null;
	    StringBuilder sb  = new StringBuilder();
	      
	    String tmpCuerpoStr = null;
	 
	    try 
	    {
	    	String html_PATH = Utilities.getDeploymentPath();
	    	
	    	//html_PATH += "jsf/docs/newsletter/20130903/index.html";
	    	html_PATH += "jsf/docs/notificaciones/preregistro-inversionista.html";
	    	
	    	System.out.println(html_PATH);
	    	  
	    	archivo = new File (html_PATH);
	    	fr      = new FileReader (archivo);
	    	br      = new BufferedReader(fr);
		    String linea;
		         
		    while((linea = br.readLine()) != null)
		    {		        	 
		    	sb.append(linea);		        	
		    }
		         
		    tmpCuerpoStr = sb.toString();
		    
	    } catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    } finally {	        
	    	try
	    	{
	    		if( null != fr )
	    		{
					 fr.close();					 
				}				 	
			 } catch (Exception e2) {
				 e2.printStackTrace();				 
			 }
	    }
	      
	    return tmpCuerpoStr;	      
	 }
}
