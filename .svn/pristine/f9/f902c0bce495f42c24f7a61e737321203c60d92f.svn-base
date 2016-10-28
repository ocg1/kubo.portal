package mx.com.kubo.notificaciones.notificacion;

public enum NotificacionEstatusEnvio 
{
	ENVIO_EN_PROCESO,
	ENVIO_SATISFACTORIO,
	ERROR_DE_ENVIO("Error en el envío de correo"),
	ERROR_DESCONOCIDO;
	
	private String mensajeConfirmacion;
	
	private NotificacionEstatusEnvio(){
		mensajeConfirmacion = "";
	}
	
	private NotificacionEstatusEnvio(String msj){
		mensajeConfirmacion = msj;
	}		
	
	public void setMensajeConfirmacion(String msj)
	{
		mensajeConfirmacion = msj;
	}

	@Override
	public String toString() {
		return mensajeConfirmacion;
	}	
}
