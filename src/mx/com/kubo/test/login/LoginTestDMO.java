package mx.com.kubo.test.login;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.test.acreditado.PanelSeleccionIMO;

public abstract class LoginTestDMO 
implements LoginTestIMO
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{loginServiceImp}")
	protected LoginServiceIMO service_login;
	
	protected FacesContext    faces;
	protected ELResolver      resolver;
	protected ELContext       context;
	protected ExternalContext external;
	protected HttpSession     sessionUsed;	
	
	protected SessionBean   sesion;	
	protected Membership    member_selected;
	protected NaturalPerson person;
	
	protected PanelSeleccionIMO panel_seleccion;
	
	protected String user_email;
	
	protected boolean sesion_ENABLED;
	
	public final void setService_membership(MembershipService service)
	{
		service_membership = service;
	}
	
	public void setService_login(LoginServiceIMO service) 
	{
		service_login = service;
	}
	
	public final boolean isSesion_ENABLED() 
	{
		return sesion_ENABLED;
	}
}
