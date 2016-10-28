package mx.com.kubo.notificaciones.control;

import mx.com.kubo.excepciones.ErrorNotificacionException;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.bitacora.BitacoraMailLogIMO;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionTipoEnvio;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ControlNotificacionDMO
{
	private MailSenderIMO sender;
	
	@Autowired @Qualifier("bitacoraMailLogIMP")
	protected BitacoraMailLogIMO bitacora;
	
	protected ErrorNotificacionException error;	
	
	public final void setSendMailNotificacion(MailSenderIMO sender)
	{
		this.sender = sender;
	}
	
	protected MailSenderIMO getMailSender() throws NotificacionException
	{
		if(sender != null)
		{
			return sender;
		} else {
			throw new NotificacionException();
		}
		
	}
	
	public void setSendType(NotificacionTipoEnvio delivery)
	{
		bitacora.setSendType(delivery);
	}
	
	
	public void setReceptor(Membership receptor)
	{
		bitacora.setReceptor(receptor);		
	}
	
	protected void setError(ErrorNotificacionException error){
		this.error = error;
	}
	
}
