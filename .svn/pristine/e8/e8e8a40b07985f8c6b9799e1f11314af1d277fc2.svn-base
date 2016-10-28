package mx.com.kubo.portal;

import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public abstract class AccessDMO 
implements AccessIMO
{
	protected SystemParamService systemparamservice;
	protected AccessService service_access; 
	
	protected   FacesContext faces;
	protected RequestContext request;	
	
	protected HtmlInputText input_text;
	
	protected Access access;
	
	protected SessionBean sesion;
	
	protected String access_client_IP;
	protected String access_CONFIG;
	protected String user_agent;
	protected String browser_name;
	protected String browser_version;
	protected String browser_OS;	
	protected String device_info;
	protected String version_description;
	protected String access_from;
	
	protected String [] lista_access_CONFIG;
	
	protected Integer screen_id;
	protected Integer browser_width;
	protected Integer browser_height;
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer prospectus_id_viewed;	
	
	protected boolean access_OK;
	
	protected AccessDMO()
	{
		systemparamservice = Utilities.findBean("systemParamServiceImp");
	}
			
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public final void setScreen_id(Integer screen_id) 
	{
		this.screen_id = screen_id;
	}
	
	public final void setAccess_from(String access_from)
	{
		this.access_from = access_from;
	}

	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		   company_id = sesion.getCompany_id();
		prospectus_id = sesion.getCoachProspectus_id();
		prospectus_id_viewed = sesion.getProspectus_id();
		access_client_IP = sesion.getIP_address_client();
	}

	public final boolean isAccess_OK() 
	{
		return access_OK;
	}		
}
