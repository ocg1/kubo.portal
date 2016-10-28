package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionErrorDesarrollo;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;

public final class NotificableErrorDesarrollo extends NotificableEMO
implements NotificableIMO 
{
	public NotificableErrorDesarrollo(Membership emisor, Membership acreditado, Scoring score, String errormsg)
	{
		this.emisor = emisor;
		
		if(acreditado != null)
		{
			this.acreditado = acreditado;
		} else {
			this.acreditado = emisor;
		}
		
		 
		
		receptores    = new ReceptoresIMP(Evento.ERROR_DESARROLLO, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionErrorDesarrollo(conexion_SMTP, emisor, score, errormsg);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);
		
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_lista(Evento.ERROR_DESARROLLO);
	}
}
