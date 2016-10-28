package mx.com.kubo.notificaciones.conexion;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public interface ConexionIMO 
{
	SMTPServerDMO getServerSMTP();
	MimeMessage   getMessage();
	
	boolean connect();
	void close() throws MessagingException;
	
	void send(MimeMessage message, Membership receptor) throws MessagingException, SendFailedException;
	void send(MimeMessage message, ReceptoresIMO receptores) throws MessagingException, SendFailedException;
}
