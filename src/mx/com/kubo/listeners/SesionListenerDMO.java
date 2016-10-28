package mx.com.kubo.listeners;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.tools.Utilities;

public abstract class SesionListenerDMO 
{
	protected AccessService service_access;
	
	protected HttpSession    sesion;
	protected ServletContext servlet;
	protected FacesContext   faces;
	protected ELResolver     resolver;
	protected ELContext      context;
	protected SessionBean    sesion_bean;
	private Access         access;
	
	protected String id;
	
	protected final static int SCREEN_ID_LOG_OUT         = 35;
	protected final static int SCREEN_ID_SESSION_EXPIRED = 37;
	
	@SuppressWarnings("rawtypes")
	protected Map usuarios;
	
	protected void registar_bitacora_access(int screen_id) 
	{		
		service_access = Utilities.findBean("accessServiceImp");
		
		access = new Access();
		
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		
		access.setCompany_id          (sesion_bean.getCompany_id());
		access.setProspectus_id       (sesion_bean.getProspectus_id());
		access.setProspectus_id_viewed(sesion_bean.getProspectus_id());		
		access.setWeb_browser         (sesion_bean.getNamebrawser());
		access.setWeb_browser_version (sesion_bean.getVersionbrawser());
		access.setOp_system           (sesion_bean.getOsbrawser());
		access.setHorizontal_size     (sesion_bean.getBrowser_width());
		access.setVertical_size       (sesion_bean.getBrowser_height());
		access.setIpaddress           (sesion_bean.getIP_address_client());
		access.setUser_agent		  (sesion_bean.getUser_agent());
		access.setDevice_info		  (sesion_bean.getDevice_info());
		access.setProspectus_id_coach (sesion_bean.getCoachProspectus_id());
		access.setAccess_from		  (sesion_bean.getAccess_from());
		access.setVersion_description (sesion_bean.getVersion_description());
		
		service_access.add(access, false);
		
		System.out.printf("\nSessionListener.registar_bitacora_access(): OK");
	}
}
