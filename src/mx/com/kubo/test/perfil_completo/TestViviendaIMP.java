package mx.com.kubo.test.perfil_completo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMP;
import mx.com.kubo.test.acreditado.PanelSeleccionIMO;
import mx.com.kubo.test.login.LoginTestIMO;

@SuppressWarnings("serial")
@ManagedBean (name = "test_vivienda")
@ViewScoped
public final class TestViviendaIMP extends TestViviendaDMO
implements Serializable, TestViviendaIMO
{	
	@PostConstruct
	public final void init()
	{
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
        sesion           = (SessionBean)       resolver.getValue(context, null, "sessionBean");
        login_sesion     = (LoginTestIMO)      resolver.getValue(context, null, "login_test");
        panel_seleccion  = (PanelSeleccionIMO) resolver.getValue(context, null, "panel_seleccion");                
        
        if(sesion.getProspectus_id() != null)        	
        {
        	int prospectus_id = sesion.getProspectus_id();
        	int company_id    = sesion.getCompany_id();
        	
        	prospectus_PK = new ProspectusPK(prospectus_id, company_id);
        	
        	prospecto = service_prospecto.getProspectusById(prospectus_PK);
        	
        	editor_vivienda = new EditorViviendaIMP();
        	editor_vivienda.setSesion(sesion);
        	//editor_vivienda.setAcreditado(prospecto);
        	editor_vivienda.init();
        }        	
	}
}
