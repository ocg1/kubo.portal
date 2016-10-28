package mx.com.kubo.session;


import java.io.IOException;

//import javax.faces.FacesException;
import javax.faces.FactoryFinder;
/*import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;*/
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

public class LoggedInCheck implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private String timeoutPage = "jsf/inicio.jsf";
	Logger log = Logger.getLogger(getClass());
	@SuppressWarnings("deprecation")
	@Override
	public void beforePhase(PhaseEvent event) {
		 
		FacesContext fc = event.getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		//HttpServletRequest origRequest = (HttpServletRequest)ec.getRequest();

        try {
        	boolean newSession = (session == null) || (session.isNew());
			boolean postback = !ec.getRequestParameterMap().isEmpty();
			boolean timedout = postback && newSession;
			if (timedout) {	
						
		 	            if (ec.isResponseCommitted()) {
		 	                // redirect is not possible
		                 return;
		 	            }
		 	           HttpSession session02 =
		 	  				(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 	            // fix for renderer kit (Mojarra's and PrimeFaces's ajax redirect)
		 	            if ((RequestContext.getCurrentInstance().isAjaxRequest()
		 	                || fc.getPartialViewContext().isPartialRequest())
		 	                && fc.getResponseWriter() == null
		 	                && fc.getRenderKit() == null) {
		 	            	 session02.invalidate();
		 	                    ServletResponse response = (ServletResponse) ec.getResponse();
		 	                    ServletRequest request = (ServletRequest) ec.getRequest();
		 	                    response.setCharacterEncoding(request.getCharacterEncoding());
		  
		 	                    RenderKitFactory factory = (RenderKitFactory) 
		 	                     FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
		 	 
		 	                    RenderKit renderKit = factory.getRenderKit(fc,
		 	                     fc.getApplication().getViewHandler().calculateRenderKitId(fc));
		 	 
		 	                    ResponseWriter responseWriter =
		 	                        renderKit.createResponseWriter(
		 	                        response.getWriter(), null, request.getCharacterEncoding());
		 	                        fc.setResponseWriter(responseWriter);
		 	            }
		 	            
		 	           //session.invalidate();
			          
		 	         String timeoutUrl = fc.getExternalContext().getRequestContextPath() + "/" + getTimeoutPage();
		 	 		 String redirectPage = timeoutUrl;
		 	            
		             ec.redirect(ec.getRequestContextPath() +
		 	                (redirectPage != null ? redirectPage : ""));
		             
		             
			}
 	        } catch (IOException e) {
 	        	e.printStackTrace();
 	            /*LOG.error("Redirect to the specified page '" +
 	                redirectPage + "' failed");
 	            throw new FacesException(e);*/
 	        }
    }
	@Override
    public void afterPhase(PhaseEvent e) {
	
    
	}
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
	public String getTimeoutPage() {
		return timeoutPage;
	}
	public void setTimeoutPage(String timeoutPage) {
		this.timeoutPage = timeoutPage;
	}
}