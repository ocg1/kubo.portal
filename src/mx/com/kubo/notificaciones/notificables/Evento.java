package mx.com.kubo.notificaciones.notificables;

public enum Evento 
{
	PUBLICACION					       (1),
	ERROR_DESARROLLO                   (2),
	CONSULTA_BC_EXITOSA			       (3),
	CONSULTA_BC_MANUAL		           (4),
	REGISTRO_SOCIO_PARTNER             (5),
	CREDITO_AUTORIZACION		       (6),
	CREDITO_PREAUTORIZACION		       (7),
	CAMBIO_ESTATUS_CREDITO		       (8),
	ENVIO_CORREO_ESPEC_PROMOTORES      (9),
	SOLICITUD_INVERSIONISTA_EXITOSA    (10),
	REGISTRO_USUARIO                   (11),
	REENVIO_MAIL_CONFIRMACION          (12),
	ACTIVACION_INVERSIONISTA           (13),
	PREREGISTRO_INVERSIONISTA          (14),
	CAMBIO_CUENTA_CORREO               (15),
	PRUEBA_ENVIO_CORREO				   (16),
	PETICION_CAMBIO_PASSWORD		   (17),
	PETICION_CAMBIO_CUENTA_CORREO      (18),
	CONSULTA_BC_MANUAL_SEGUIMIENTO     (19),
	SOLICITUD_INVERSIONISTA_FINALIZADA (20),
	GENERACION_PASSWORD_ACTIVACION     (21),
	DISPOSICION_DE_EFECTIVO            (22),
	CANCELACION_AUTOMATICA             (23),
	PROMESA_DE_PAGO				       (24),
	SEGUIMIENTO_ACTIVACION		       (25),
	REGISTRO_BITACORA_CHANGE_CONTROL   (26),
	DEPOSITO_DE_EFECTIVO               (27),
	CANCELACION_CUENTA_INVERSIONES     (29),
	CANCELACION_CUENTA_INVERSIONISTA   (30),
	ENVIO_DE_CONTRATOS   (45);
		
	private int    id;
	private String descripcion;
	
	private Evento(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setError(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public String getError()
	{
		return descripcion;
	}
}
