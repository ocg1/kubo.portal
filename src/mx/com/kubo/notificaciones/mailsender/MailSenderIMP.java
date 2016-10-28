package mx.com.kubo.notificaciones.mailsender;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;

public final class MailSenderIMP extends MailSenderPMO 
implements MailSenderIMO
{		
	public MailSenderIMP(NotificacionIMO notificacion, Membership emisor, ReceptoresIMO receptores, Membership acreditado)
	{
		this.conexion_SMTP = notificacion.getConexion_SMTP();
		this.notificacion  = notificacion;		
		this.emisor        = emisor;
		this.receptores    = receptores;
		this.acreditado    = acreditado;
	}

	public final void send_to_receptores_por_iteracion(Evento evento) throws NotificacionException
	{	
		for(Membership receptor: receptores.getLista_receptores_TO())
		{			
			send_to_receptor(evento, emisor, receptor, acreditado);
		}
	}
		
	public void send_to_receptores_por_lista(Evento evento) throws NotificacionException
	{
		super.send_to_receptores_por_lista(evento, emisor, receptores, acreditado);
	}
}