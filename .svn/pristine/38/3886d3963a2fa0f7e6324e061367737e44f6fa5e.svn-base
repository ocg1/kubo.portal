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
import mx.com.kubo.notificaciones.parsers.NotificacionPruebaEnvioCorreo;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;

public class NotificablePruebaEnvioCorreo extends NotificableEMO
implements NotificableIMO 
{
	public NotificablePruebaEnvioCorreo(Membership emisor, Scoring score, ProyectLoan proyecto)
	{
		this.emisor     = emisor;
		this.acreditado = emisor;
		
		receptores    = new ReceptoresIMP(Evento.PRUEBA_ENVIO_CORREO, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionPruebaEnvioCorreo(conexion_SMTP, score, proyecto);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);				
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_lista(Evento.PRUEBA_ENVIO_CORREO);
	}
}
