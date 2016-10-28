package mx.com.kubo.managedbeans.mesa.sesiones;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProspectusService;

public abstract class GestorSesionesDMO 
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService service_prospectus;
	
	protected  RequestContext request;
	protected    FacesContext faces;
	protected       ELContext context;
	protected ExternalContext external;
	protected  ServletContext servlet;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneMenu select_menu;
	
	protected ELResolver resolver;
	
	protected SessionBean sesion;
	protected HttpSession http_session;	
	
	protected Prospectus prospecto;	
	protected Membership   membership;
	protected MembershipPK membership_PK;	
	
	protected List<Membership> lista_membership;
	
	protected Access access;
	
	protected Enumeration<String> lista_http_session_keys;
	protected Enumeration<String> lista_traking_id;
	
	protected Hashtable<String, HttpSession> usuario_http_session;
	protected Hashtable<String, Hashtable <String, HttpSession>> usuarios_firmados;
	
	protected String tracking_id;
	protected String http_session_key;	
	
	protected Integer prospectus_id;
	protected Integer filter_type_id;
	
	protected boolean remove_OK;
	protected boolean filter_email_ENABLED;
	
	protected final int SCREEN_SESIONES = 54;
	protected final int KUBO_FINANCIERO = 1;
	
	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
	
	public void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setService_prospectus(ProspectusService service) 
	{
		service_prospectus = service;
	}	
	
	public Integer getFilter_type_id() 
	{
		return filter_type_id;
	}
	
	public void setFilter_type_id(Integer type_id) 
	{
		filter_type_id = type_id;
	}

	public List<Membership> getLista_membership() 
	{
		return lista_membership;
	}
}
