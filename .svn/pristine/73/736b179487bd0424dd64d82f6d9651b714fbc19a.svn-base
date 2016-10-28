package mx.com.kubo.notificaciones.control;

import mx.com.kubo.excepciones.ErrorNotificacionException;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.bitacora.BitacoraMailLogIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionEstatusEnvio;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public final class ControlNotificacionIMP extends ControlNotificacionDMO 
implements ControlNotificacionIMO
{	
	public ControlNotificacionIMP(Evento evento, SMTPServerDMO SMTP_server, Membership emisor, Membership receptor, Membership acreditado)
	{
		bitacora = new BitacoraMailLogIMP(evento, SMTP_server, emisor, receptor, acreditado);
	}	
	
	public ControlNotificacionIMP(Evento evento, SMTPServerDMO SMTP_server, Membership emisor, ReceptoresIMO receptores, Membership acreditado)
	{	
		bitacora = new BitacoraMailLogIMP(evento, SMTP_server, emisor, receptores, acreditado);
	}
		
	public final void registrar(NotificacionEstatusEnvio estatus)
	{
		switch(estatus)
		{							
			case ERROR_DE_ENVIO:	
				bitacora.setEstatus(estatus);
				setError(new ErrorNotificacionException(estatus));
			break;						
						
			case ERROR_DESCONOCIDO:		
				bitacora.setEstatus(estatus);
				setError(new ErrorNotificacionException(estatus));
			break;						
				
			default: 
				bitacora.setEstatus(estatus);
			break;
		}		
				
		bitacora.registrar();		
	}
}
