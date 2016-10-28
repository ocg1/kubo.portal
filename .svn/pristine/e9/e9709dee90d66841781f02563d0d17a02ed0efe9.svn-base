package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionSolicitudInversionistaFinalizada;
import mx.com.kubo.notificaciones.receptores.ReceptorIMP;

public class NotificableSolicitudInversionistaFinalizada extends NotificableEMO 
implements NotificableIMO
{
	public NotificableSolicitudInversionistaFinalizada(Membership emisor, Membership acreditado)
	{
		this.emisor     = emisor;
		//this.acreditado = acreditado;
		
		receptores    = new ReceptorIMP(Evento.SOLICITUD_INVERSIONISTA_FINALIZADA, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionSolicitudInversionistaFinalizada(conexion_SMTP, emisor);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);			
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_iteracion(Evento.SOLICITUD_INVERSIONISTA_FINALIZADA);
	}
}
