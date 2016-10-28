package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.receptores.ReceptorIMP;

public class NotificablePromotores extends NotificableEMO
implements NotificableIMO
{	
	public NotificablePromotores(Membership emisor, Membership acreditado, NotificacionIMO notificacion)
	{
		this.emisor       = emisor;
		this.acreditado   = acreditado;
		this.notificacion = notificacion;
		
		receptores    = new ReceptorIMP(Evento.ENVIO_CORREO_ESPEC_PROMOTORES, acreditado.getPromotor().getMembership());
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
//		notificacion  = new NotificacionPromotores(conexion_SMTP, emisor);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);						
	}
	
	public final void notificar() throws NotificacionException 
	{
		notificar_receptores_por_iteracion(Evento.ENVIO_CORREO_ESPEC_PROMOTORES);
	}

/*	
	private final void send_to_promotor(Membership emisor, Membership promotor, Membership acreditado) throws NotificacionException
	{		
		Event gn_evento        = getEventoFromCatalogo(Evento.ENVIO_CORREO_ESPEC_PROMOTORES, emisor);		
		Integer SMTP_server_id = new Integer(gn_evento.getAccountType().getAccountTypePK().getAccount_type_id());
		
		notificacion.setStrTo(promotor.getEmail());	
		notificacion.setAccountCode(gn_evento.getAccountType().getAccount_code());		
		notificacion.setProspectusID(promotor.getPerson().getProspectus().getProspectusPK().getProspectus_id());	
		
		send_to_receptor(Evento.ENVIO_CORREO_ESPEC_PROMOTORES, SMTP_server_id, emisor, promotor, acreditado);
	}	
*/
}
