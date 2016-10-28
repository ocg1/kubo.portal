package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionPreregistroInversionista;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;

public class NotificablePreregistroInversionista extends NotificableEMO 
implements NotificableIMO
{
	public NotificablePreregistroInversionista(Membership emisor)
	{
		this.emisor     = emisor;
		this.acreditado = emisor;
		
		receptores    = new ReceptoresIMP(Evento.PREREGISTRO_INVERSIONISTA, emisor);
		
		if( receptores != null && receptores.getLista_receptores() != null && receptores.getLista_receptores().size() > 0 ){
			conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
			
			notificacion  = new NotificacionPreregistroInversionista(conexion_SMTP, emisor);		
			mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);
			
		}else{
			
			System.out.println( "Sin receptores : mx.com.kubo.notificaciones.eventos.NotificablePreregistroInversionista.NotificablePreregistroInversionista" );
			
		}
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_lista(Evento.PREREGISTRO_INVERSIONISTA);	
	}
}
