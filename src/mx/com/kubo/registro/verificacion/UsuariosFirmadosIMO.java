package mx.com.kubo.registro.verificacion;

import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

public interface UsuariosFirmadosIMO 
{
	void setFacesContext(FacesContext faces);
	
	void setSesion(SessionBean sesion);
	
	void init();
	void remove();
}
