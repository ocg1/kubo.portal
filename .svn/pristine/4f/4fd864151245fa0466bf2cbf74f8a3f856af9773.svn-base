package mx.com.kubo.managedbeans.mesa.autenticacion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.autenticacion.buscador.BuscadorIMP;
import mx.com.kubo.mesa.autenticacion.fabrica.AutenticacionFactory;

@ManagedBean(name = "tablero_autenticacion") @ViewScoped
public class TableroAutenticacionIMP extends TableroAutenticacionAMO
implements Serializable, TableroAutenticacionIMO
{
	private static final long serialVersionUID = -5077092951112347099L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion        = (SessionBean)          resolver.getValue(elContext, null, "sessionBean");
		sesion_search = (SearchSummaySession)  resolver.getValue(elContext, null, "searchSummaySession");
		
		company_id           = sesion.getCompany_id();
		IP_address_client    = sesion.getIP_address_client();
		change_prospectus_id = sesion.getProspectus_id();
		
		buscador = new BuscadorIMP();
		buscador.setCompany_id(company_id);
		buscador.setSesion_search(sesion_search);
		buscador.init_prospectus();
		
		init_pool_autenticacion();
	}

	public final void init_pool_autenticacion() 
	{
		request = RequestContext.getCurrentInstance();
		
		prospectus_OK = buscador.isProspectus_OK();
		
		pool_autenticacion_ENABLED = false;
				
		if(prospectus_OK)
		{
			membership = buscador.getMembership();
			
			prospectus_id  = membership.getMembershipPK().getProspectus_id();
			
			fabrica = new AutenticacionFactory();
			fabrica.setMembership(membership);		
			fabrica.init();
			
			lista_preguntas = fabrica.getLista_preguntas();
			
			init_lista_preguntas();
								
			respuestas_CORRECTAS = new boolean[MAX_NUMBER_QUESTIONS];
			
			pool_autenticacion_ENABLED   = true;
			desbloquear_password_ENABLED = false;
			autenticacion_OK             = false;
		}
		
		if(request != null)
		{
			request.addCallbackParam("pool_autenticacion_ENABLED", pool_autenticacion_ENABLED);
		}
	}
	
	public final void listener_autenticacion(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		respuesta_id = Integer.parseInt(input_text.getValue().toString());				
		
		init_autenticacion();
		init_change_control();
		
		request.addCallbackParam("autenticacion_ID", respuesta_id);
		request.addCallbackParam("autenticacion_OK", autenticacion_OK);
		request.addCallbackParam("change_control_OK", change_control_OK);	
		request.addCallbackParam("contador_CORRECTAS", contador_CORRECTAS);
		request.addCallbackParam("desbloquear_password_ENABLED", desbloquear_password_ENABLED);
	}
	
	public final void init_panel_desbloquear_password()
	{
		request = RequestContext.getCurrentInstance();
				
		
		request.addCallbackParam("desbloquear_password_ENABLED", desbloquear_password_ENABLED);
	}
	
	public final void init_desbloquear_password()
	{
		request = RequestContext.getCurrentInstance();
		
		membership.setFailed_token_attempts(0);
		membership.setFailed_question_attempts(0);
		membership.setFailed_login_attempts(0);
		membership.setIs_blocked("N");
			
		service_membership.update(membership);
		
		init_notificar_evento();
		
		boolean desbloquear_password_OK = true;
		
		request.addCallbackParam("desbloquear_password_OK", desbloquear_password_OK);
	}
}
