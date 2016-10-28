package mx.com.kubo.managedbeans.portal.password;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

@ManagedBean (name = "desbloqueo_password") @ViewScoped 
public final class DesbloqueoPasswordIMP extends DesbloqueoPasswordAMO
implements Serializable, DesbloqueoPasswordIMO
{
	private static final long serialVersionUID = 8858411503794740595L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
	
		try
		{
			init_sesion();
			init_desbloqueo();
			
		} catch(Exception e) {}
	}

	public final void listener_email(AjaxBehaviorEvent event)
	{
		input_text = (HtmlInputText) event.getComponent();
		
		email = input_text.getValue().toString();
		
		System.out.println("DesbloquearPasswordIMP.listener_email(): " + email);
	}

	public final void init_security_question()
	{
		request = RequestContext.getCurrentInstance();
		
		membership = service_membership.getMembershipByEmail(email);
		
		if(membership != null)
		{
			por_pregunta = new PreguntaSeguridadIMP();
			por_pregunta.setMembership(membership);
			
			security_question_ENABLED = por_pregunta.isSecurity_question_ENABLED();
		} 	
		
		request.addCallbackParam("security_question_ENABLED", security_question_ENABLED);
	}
	
	public final void init_desbloqueo_por_correo()
	{
		request = RequestContext.getCurrentInstance();
		
		membership = service_membership.getMembershipByEmail(email);
		
		desbloqueo_por_correo_OK = false;
		
		if(membership != null)
		{
			por_correo = new DesbloqueoPorCorreoIMP();
			por_correo.setMembership(membership);			
			por_correo.notificar();
			
			desbloqueo_por_correo_OK = true;
		} 	
		
		request.addCallbackParam("desbloqueo_por_correo_OK", desbloqueo_por_correo_OK);
	}

	public final void init_login_redirect()
	{		
		faces = FacesContext.getCurrentInstance();
		
		external = faces.getExternalContext();				

		servlet = (HttpServletRequest) external.getRequest();
		
		path = servlet.getContextPath();
		
		sb = new StringBuilder();
		sb.append(path);
		sb.append(HOME);
		sb.append(email);
		
		url  = sb.toString();
		
		try 
		{
			external.redirect(url);	
		      
		} catch (IOException ex) {
			
		}
	}
}
