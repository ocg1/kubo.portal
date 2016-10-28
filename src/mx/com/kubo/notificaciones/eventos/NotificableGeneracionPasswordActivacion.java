package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionGeneracionPasswordActivacion;
import mx.com.kubo.notificaciones.receptores.ReceptorIMP;

public class NotificableGeneracionPasswordActivacion extends NotificableEMO
implements NotificableIMO
{
	public NotificableGeneracionPasswordActivacion(Membership emisor, String password)
	{
		this.emisor     = emisor;
		this.acreditado = emisor;
		
		receptores    = new ReceptorIMP(Evento.GENERACION_PASSWORD_ACTIVACION, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionGeneracionPasswordActivacion(conexion_SMTP, password, emisor);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);			
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_iteracion(Evento.GENERACION_PASSWORD_ACTIVACION);
	}
}
