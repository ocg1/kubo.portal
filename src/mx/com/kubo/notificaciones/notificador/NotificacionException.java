package mx.com.kubo.notificaciones.notificador;

@SuppressWarnings("serial")
public class NotificacionException extends Exception
{
	public NotificacionException()
	{
		super();
	}
	
	public NotificacionException(String error_msg)
	{
		super(error_msg);
	}
}
