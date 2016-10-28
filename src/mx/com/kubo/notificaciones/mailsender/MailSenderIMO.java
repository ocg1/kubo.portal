package mx.com.kubo.notificaciones.mailsender;

import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

public interface MailSenderIMO
{	
	void    connect() throws NotificacionException; 
	void disconnect() throws NotificacionException;
	
	void send_to_receptores_por_iteracion(Evento evento) throws NotificacionException;		
	void send_to_receptores_por_lista    (Evento evento) throws NotificacionException;
}
