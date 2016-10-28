package mx.com.kubo.test.perfil_completo;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMO;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.test.acreditado.PanelSeleccionIMO;
import mx.com.kubo.test.login.LoginTestIMO;

public abstract class TestViviendaDMO 
implements TestViviendaIMO
{
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService service_prospecto;
	
	protected EditorViviendaIMO editor_vivienda;
	
	protected FacesContext   faces;
	protected ELContext      context;
	protected ELResolver     resolver;
	
	protected SessionBean  sesion;
	
	protected Prospectus   prospecto;
	protected ProspectusPK prospectus_PK;
	
	protected LoginTestIMO      login_sesion;
	protected PanelSeleccionIMO panel_seleccion;
	
	public EditorViviendaIMO getEditor_vivienda() 
	{
		return editor_vivienda;
	}

	public void setService_prospecto(ProspectusService service) 
	{
		service_prospecto = service;
	}	
}
