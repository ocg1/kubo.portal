package mx.com.kubo.managedbeans.portal.password;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;

public abstract class DesbloqueoPasswordDMO
implements DesbloqueoPasswordIMO
{	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 			
	
	protected    FacesContext faces;
	protected  RequestContext request;	
	protected ExternalContext external;
	
	protected HttpServletRequest servlet;	
	
	protected HtmlInputText input_text;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected SessionBean sesion;
	
	protected              AccessIMO auditor;
	protected     ParameterReaderIMO reader;	
	protected   PreguntaSeguridadIMO por_pregunta;
	protected DesbloqueoPorCorreoIMO por_correo;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
			
	protected StringBuilder sb;
	
	protected String email;
	protected String security_question;	
	protected String display_desbloqueo_MSG;
	protected String path;
	protected String url;
	protected String access_from;
			
	protected final String HOME  = "/Portal/index.xhtml?iniciarSesion=true&email_access=";
	protected final String NONE  = "none";
	protected final String BLOCK = "block";
	protected final String CONFIRMACION_DESBLOQUEO_PASSWORD = "37";
	
	protected String [] lista_receptores;	
	
	protected int prospectus_id;
	
	protected int company_id;
	
	protected final static int SCREEN_ID_DESBLOQUEO_PASSWORD = 56;
	
	protected boolean security_question_ENABLED;	
	protected boolean desbloqueo_por_correo_OK;	
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public final DesbloqueoPorCorreoIMO getPor_correo() 
	{
		return por_correo;
	}
	
	public final AccessIMO getAuditor() 
	{
		return auditor;
	}

	public final PreguntaSeguridadIMO getPor_pregunta() 
	{
		return por_pregunta;
	}

	public final boolean isSecurity_question_ENABLED() 
	{
		return security_question_ENABLED;
	}

	public final String getDisplay_desbloqueo_MSG() 
	{
		return display_desbloqueo_MSG;
	}
}
