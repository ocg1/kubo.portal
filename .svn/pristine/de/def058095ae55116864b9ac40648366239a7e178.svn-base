package mx.com.kubo.registro.verificacion;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;

public abstract class UsuariosFirmadosDMO 
implements UsuariosFirmadosIMO
{	
	protected    FacesContext faces;
	protected ExternalContext external;
	protected  ServletContext servlet;
	
	protected HttpSession http_session;
	
	protected String http_session_key;
	protected String tracking_id;
	protected boolean http_session_ENABLED;
	
	protected Enumeration<String> lista_http_session_keys;	
	
	protected Hashtable<String, HttpSession> usuario_http_session;
	protected Hashtable<String, Hashtable <String, HttpSession>> usuarios_firmados;
	
	@SuppressWarnings("unchecked")
	public final void setFacesContext(FacesContext faces)
	{				
		external = faces.getExternalContext();
		
		http_session = (HttpSession) external.getSession(false);
		
		servlet = http_session.getServletContext();
		
		usuarios_firmados = (Hashtable<String, Hashtable<String, HttpSession>>) servlet.getAttribute("usuariosFirmados");		
		
		lista_http_session_keys = usuarios_firmados.keys();
	}
	
	public final void setSesion(SessionBean sesion)
	{				
		tracking_id = sesion.getTrackId();  
	}
}
