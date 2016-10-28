package mx.com.kubo.notificaciones.receptores;

import java.io.Serializable;

import mx.com.kubo.constantes.SystemParamNotificacion;

import org.springframework.stereotype.Service;

@Service(value = "socioParterImp") @SuppressWarnings("serial")
public final class SocioParterIMP extends SocioParterDMO
implements Serializable
{	
	
	public final String getCorreo() 
	{
		return getCorreo(SystemParamNotificacion.CORREO_SOCIO_PARTNER);
	}
	
	public final String getCorreoAvisoAlta()
	{			
		return getCorreo(SystemParamNotificacion.CORREO_AVISO_ALTA_SOCIO_PARTNER);
	}
	
	public final String getNombreAvisoAlta()
	{				
		return getCorreo(SystemParamNotificacion.NOMBRE_RECEPTOR_ALTA_SOCIO_PARTNER);
	}
}
