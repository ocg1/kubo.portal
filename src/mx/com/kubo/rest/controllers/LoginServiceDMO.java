package mx.com.kubo.rest.controllers;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.rest.model.LoginMembership;
import mx.com.kubo.services.LoginServiceIMO;

public abstract class LoginServiceDMO 
{
	protected LoginServiceIMO service_login;	
	protected  ServletContext servlet;
	
	protected Membership membership;
	protected LoginMembership login;
	
	protected String http_session_key;	
	protected String tracking_id;
	
	protected HttpSession http_session;
	
	protected Hashtable<String, Hashtable <String, HttpSession>> usuarios_firmados;
	protected Hashtable<String, HttpSession> usuario_http_session;
	
	protected Enumeration<String> lista_http_session_keys;	
	
	protected void init_faces_context(HttpServletRequest request, HttpServletResponse response) 
	{
		FacesContext facesContext = null;
		
		LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

        // Create new FacesContext.
        FacesContextFactory contextFactory  = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            
        facesContext = contextFactory.getFacesContext(request.getSession().getServletContext(), request, response, lifecycle);

        // Create new View.
        UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
        facesContext.setViewRoot(view);                

        // Set current FacesContext.
        //FacesContextWrapper.setCurrentInstance(facesContext);
        
        //facesContext = FacesContext.getCurrentInstance();
        ELContext context   = facesContext.getELContext();
        ELResolver resolver  = facesContext.getApplication().getELResolver();
		
        SessionBean sesion  = (SessionBean) resolver.getValue(context, null, "sessionBean");
        
        Integer prospectus_id = sesion.getProspectus_id();
	}

	@SuppressWarnings("unchecked")
	protected void init_log_out(HttpServletRequest request)
	{
		tracking_id = membership.getPerson().getProspectus().getTracking_id();		
		
		http_session = request.getSession();
		
		servlet = http_session.getServletContext();
		
		usuarios_firmados = (Hashtable<String, Hashtable<String, HttpSession>>) servlet.getAttribute("usuariosFirmados");
		
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
				
				boolean remove_OK = true;
				
				break;
			}		
		}
	}
}
