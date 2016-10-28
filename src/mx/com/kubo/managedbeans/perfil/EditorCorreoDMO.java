package mx.com.kubo.managedbeans.perfil;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;

public class EditorCorreoDMO implements EditorCorreoIMO
{
	protected Change_controlService service_change_control;
	protected LoginServiceIMO       service_login;	
	protected MembershipService     service_membership;	
	
	protected FacesContext   faces;
	protected ELContext      context;
	protected ELResolver     resolver;
	protected RequestContext request;
	
	protected Membership membership;
	protected NaturalPerson person;
	
	protected Change_control   change_control;
	protected Change_controlPK change_controlPK;
	
	protected NotificadorIMO notificador;
	
	protected String newemail;
	protected String msg="";
	
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer change_prospectus_id;
	
	public void setService_change_control(Change_controlService service)
	{
		service_change_control = service;
	}
	
	public void setService_login(LoginServiceIMO service)
	{
		service_login = service;
	}
	
	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public void setMembership(Membership membership)
	{
		this.membership = membership;
		
		person = membership.getPerson();
	}
}
