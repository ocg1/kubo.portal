package mx.com.kubo.notificaciones.notificables;

import mx.com.kubo.notificaciones.notificador.NotificacionException;

public abstract class NotificableEMO extends NotificableDMO
{	 	
	public final void notificar_receptores_por_iteracion(Evento evento) throws NotificacionException
	{	
		try
		{
			mail_sender.connect();				
			mail_sender.send_to_receptores_por_iteracion(evento);
			
		} finally {
			mail_sender.disconnect();		
		}
	}
		
	public final void notificar_receptores_por_lista(Evento evento) throws NotificacionException
	{
		try
		{
			mail_sender.connect();		
			mail_sender.send_to_receptores_por_lista(evento);	
			
		} finally {
			mail_sender.disconnect();
		}
	}
}
