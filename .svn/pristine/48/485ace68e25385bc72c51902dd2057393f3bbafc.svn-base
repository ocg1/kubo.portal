package mx.com.kubo.managedbeans.mesa.autenticacion;

import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.autenticacion.Autenticacion;
import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.mesa.autenticacion.buscador.BuscadorIMO;
import mx.com.kubo.mesa.autenticacion.fabrica.AutenticacionFactoryIMO;
import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;

public abstract class TableroAutenticacionDMO
implements TableroAutenticacionIMO
{	
	@ManagedProperty("#{membershipServiceImp}")	
	protected MembershipService service_membership;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	protected FacesContext    faces;				
	protected RequestContext  request;
	protected ELResolver      resolver;
	protected ELContext       elContext;
	
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;
	protected SearchSummaySession sesion_search;
	
	protected Membership membership;
	
	protected AuthenticationPool authentication;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected Autenticacion autenticacion_id;
	protected AutenticacionFactoryIMO fabrica;	
	protected AutenticacionIMO autenticacion;
	protected AutenticacionIMO birthdate;
	protected AutenticacionIMO birthplace;
	protected AutenticacionIMO domicilio;
	protected AutenticacionIMO telefono;
	protected AutenticacionIMO cuenta;
	protected AutenticacionIMO zipcode;
			
	protected List<AutenticacionIMO> lista_preguntas;
	
	protected Change_control   change_control;
	protected Change_controlPK change_controlPK;
	
	protected BuscadorIMO buscador;
	
	protected Date date_of_birth; 
		
	protected StringBuilder sb;
		
	protected String prospectus_TOKEN;
	protected String email;	
	protected String IP_address_client;
	protected String original_value;
	protected String new_value;
	protected String comments;
	protected String autenticacion_id_TOKEN;
	
	protected String [] lista_receptores;
	
	protected Integer company_id;
	protected Integer prospectus_id;			
	protected Integer change_prospectus_id;
	protected Integer respuesta_id;
	protected Integer authentication_id;
	
	protected boolean prospectus_OK;
	protected boolean autenticacion_OK;
	protected boolean change_control_OK;
	protected boolean pool_autenticacion_ENABLED;
	protected boolean desbloquear_password_ENABLED;
	
	protected boolean[] respuestas_CORRECTAS;
	
	protected int contador_CORRECTAS;
	
	protected final int MAX_NUMBER_QUESTIONS = 3;
	
	protected final String CONFIRMACION_DESBLOQUEO_PASSWORD = "37";
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
	
	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}

	public final BuscadorIMO getBuscador() 
	{
		return buscador;
	}
	
	public final AutenticacionIMO getBirthdate() 
	{
		return birthdate;
	}
	
	public final AutenticacionIMO getBirthplace() 
	{
		return birthplace;
	}
	
	public final AutenticacionIMO getDomicilio() 
	{
		return domicilio;
	}
	
	public final AutenticacionIMO getTelefono() 
	{
		return telefono;
	}

	public final AutenticacionIMO getCuenta() 
	{
		return cuenta;
	}
	
	public final AutenticacionIMO getZipcode() 
	{
		return zipcode;
	}

	public void setZipcode(AutenticacionIMO zipcode) {
		this.zipcode = zipcode;
	}

	public final List<AutenticacionIMO> getLista_preguntas() 
	{
		return lista_preguntas;
	}

	public final String getAutenticacion_id_TOKEN() 
	{
		return autenticacion_id_TOKEN;
	}

	public final boolean isPool_autenticacion_ENABLED() 
	{
		return pool_autenticacion_ENABLED;
	}

	public final boolean isDesbloquear_password_ENABLED() 
	{
		return desbloquear_password_ENABLED;
	}
}
