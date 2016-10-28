package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionDepositoEfectivo;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;

public class NotificableDepositoEfectivo extends NotificableEMO
implements NotificableIMO {
	
	public NotificableDepositoEfectivo(Membership emisor, Membership acreditado, MovimientosIMO movimiento)
	{
		this.emisor     = emisor;
		this.acreditado = acreditado;
		
		receptores    = new ReceptoresIMP(Evento.DEPOSITO_DE_EFECTIVO, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionDepositoEfectivo(conexion_SMTP, acreditado, movimiento);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);			
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_iteracion(Evento.DEPOSITO_DE_EFECTIVO);
	}

}
