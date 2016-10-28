package mx.com.kubo.notificaciones.eventos;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionEnvioContratos;
import mx.com.kubo.notificaciones.receptores.ReceptorIMP;

public class NotificableEnvioContratos extends NotificableEMO
implements NotificableIMO 
{
	public NotificableEnvioContratos(Membership emisor,List<String> lista_archivos)
	{
		this.emisor     = emisor;
		this.acreditado = emisor;
		
		receptores    = new ReceptorIMP(Evento.ENVIO_DE_CONTRATOS, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionEnvioContratos(conexion_SMTP, emisor,lista_archivos);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);						
	}
	
	public final void notificar() throws NotificacionException
	{		
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_iteracion(Evento.ENVIO_DE_CONTRATOS);
	}
}

