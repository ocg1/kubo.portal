package mx.com.kubo.registro.verificacion;

public final class UsuariosFirmadosIMP extends UsuariosFirmadosDMO
implements UsuariosFirmadosIMO
{
	public final void init() 
	{		
		http_session_ENABLED = false;
		
		while(lista_http_session_keys.hasMoreElements()) 
		{					
			http_session_key = lista_http_session_keys.nextElement();
			
			usuario_http_session = usuarios_firmados.get(http_session_key);
			
			http_session = usuario_http_session.get(tracking_id);
			
			if(http_session != null)
			{
				http_session_ENABLED = true;
				
				break;	
			}
		}
	}
	
	public final void remove()
	{							
		if(http_session_ENABLED)
		{			
			String sesion_id = http_session.getId();
			
			http_session.invalidate();
			
			usuarios_firmados.remove(sesion_id);				
		}				
	}
}
