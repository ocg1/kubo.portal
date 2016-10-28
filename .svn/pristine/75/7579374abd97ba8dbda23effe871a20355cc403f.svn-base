package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionActivacionInversionista;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;

public class NotificableActivacionInversionista extends NotificableEMO
implements NotificableIMO 
{
	public NotificableActivacionInversionista(Membership emisor, Membership acreditado, Scoring score, ProyectLoan proyecto)
	{
		this.emisor     = emisor;
		this.acreditado = acreditado;
		
		receptores    = new ReceptoresIMP(Evento.ACTIVACION_INVERSIONISTA, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion = new NotificacionActivacionInversionista(conexion_SMTP, emisor, acreditado, score, proyecto);
		mail_sender  = new MailSenderIMP(notificacion, emisor, receptores, acreditado);						
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_lista(Evento.ACTIVACION_INVERSIONISTA);				
	}
}
