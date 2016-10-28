package mx.com.kubo.notificaciones.eventos;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMP;
import mx.com.kubo.notificaciones.notificables.NotificableEMO;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.parsers.NotificacionCancelacionCuenta;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMP;
import static mx.com.kubo.notificaciones.notificables.Evento.CANCELACION_CUENTA_INVERSIONES;

public class NotificableCancelacionCuenta extends NotificableEMO
implements NotificableIMO 
{
	public NotificableCancelacionCuenta(Membership emisor, Membership acreditado, MovimientosIMO movimiento)
	{
		this.emisor     = emisor;
		this.acreditado = acreditado;
		
		receptores    = new ReceptoresIMP(CANCELACION_CUENTA_INVERSIONES, emisor);
		conexion_SMTP = new ConexionIMP(receptores.getSMTPServer());
		
		notificacion  = new NotificacionCancelacionCuenta(conexion_SMTP, acreditado, movimiento);
		mail_sender   = new MailSenderIMP(notificacion, emisor, receptores, acreditado);			
	}
	
	public final void notificar() throws NotificacionException
	{
		promotor_enabled = receptores.isEnvioPromotorENABLED() && tienePromotor(acreditado);
		
		if(promotor_enabled)
		{
			new NotificablePromotores(emisor, acreditado, notificacion).notificar();
		}
		
		notificar_receptores_por_lista(CANCELACION_CUENTA_INVERSIONES);
	}
}
