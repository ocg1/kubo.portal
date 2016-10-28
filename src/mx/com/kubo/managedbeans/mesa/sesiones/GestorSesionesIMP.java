package mx.com.kubo.managedbeans.mesa.sesiones;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;

@ManagedBean(name = "gestor_sesiones") @ViewScoped
public final class GestorSesionesIMP extends GestorSesionesAMO
implements Serializable 
{
	private static final long serialVersionUID = -843021499278709130L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();	
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		filter_type_id = KUBO_FINANCIERO;
		
		filter_email_ENABLED = true;
		
		init_lista_membership();
		init_registro_access();	
	}
	
	public final void init_filter_type(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance(); 
				
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		filter_type_id = Integer.parseInt(select_menu.getValue().toString());
		
		switch(filter_type_id)
		{
			case KUBO_FINANCIERO:
				filter_email_ENABLED = true;
			break;
			
			default:
				filter_email_ENABLED = false;
			break;
		}
		
		init_lista_membership();
		
		request.addCallbackParam("filter_type_id", filter_type_id);
	}
	
	public final void listener_usuario_SELECTED(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		remove_OK = false;
		
		input_text = (HtmlInputText) event.getComponent();
		
		prospectus_id = Integer.parseInt((String) input_text.getValue());
		
		membership_PK = new MembershipPK(prospectus_id, 1);
		
		membership = service_membership.getMembershipById(membership_PK); 
		
		tracking_id = membership.getPerson().getProspectus().getTracking_id();
		
		System.out.println("AdministrationProfile.listener_usuario_SELECTED(): " + membership.getEmail());
		
		lista_http_session_keys = usuarios_firmados.keys();
		
		while(lista_http_session_keys.hasMoreElements()) 
		{					
			http_session_key = lista_http_session_keys.nextElement();
			
			usuario_http_session = usuarios_firmados.get(http_session_key);
			
			http_session = usuario_http_session.get(tracking_id);
			
			if(http_session != null)
			{
				http_session.invalidate();	
				
				usuarios_firmados.remove(http_session.getId());
				
				remove_OK = true;
				
				break;
			}		
		}
		
		if(remove_OK)
		{		
			init_lista_membership();			
		}
		
		request.addCallbackParam("remove_OK", remove_OK);
	}
}
