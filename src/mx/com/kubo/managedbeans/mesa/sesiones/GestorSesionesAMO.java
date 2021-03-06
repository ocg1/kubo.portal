package mx.com.kubo.managedbeans.mesa.sesiones;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;

public abstract class GestorSesionesAMO extends GestorSesionesDMO 
{
	@SuppressWarnings("unchecked")
	protected void init_lista_membership() 
	{
		faces = FacesContext.getCurrentInstance();
		
		external = faces.getExternalContext();
		
		http_session = (HttpSession) external.getSession(false);
		
		servlet = http_session.getServletContext();
		
		usuarios_firmados = (Hashtable<String, Hashtable<String, HttpSession>>) servlet.getAttribute("usuariosFirmados");
		
		lista_http_session_keys = usuarios_firmados.keys();					
		
		lista_membership = new ArrayList<Membership>();
		
		while(lista_http_session_keys.hasMoreElements()) 
		{					
			http_session_key = lista_http_session_keys.nextElement();
		    
			usuario_http_session = (Hashtable<String, HttpSession>) usuarios_firmados.get(http_session_key);
			
			lista_traking_id = usuario_http_session.keys();
		    
		    while(lista_traking_id.hasMoreElements())
		    {
		    	tracking_id = lista_traking_id.nextElement();
		    	
		    	prospecto = service_prospectus.getProspectByTrackingID(tracking_id);
		    	
		    	if(prospecto != null)
		    	{
			    	int prospectus_id = prospecto.getProspectusPK().getProspectus_id();
			    	int company_id    = prospecto.getProspectusPK().getCompany_id();
			    	
			    	if(sesion.getProspectus_id() != prospectus_id)
			    	{			    	
				    	membership_PK = new MembershipPK(prospectus_id, company_id);
				    	
				    	membership = service_membership.getMembershipById(membership_PK);
				    	
				    	if(filter_email_ENABLED)
				    	{
				    		boolean kubo_email_ENABLED = membership.getEmail().indexOf("@kubofinanciero.com") > 0;
				    	
				    		if(kubo_email_ENABLED)
				    		{
				    			lista_membership.add(membership);
				    		}
				    		
				    	} else {
				    		
				    		lista_membership.add(membership);
				    	}
			    	}	
		    	}
		    }				    
		}
	}
	
	protected void init_registro_access() 
	{
		access = new Access();
		access.setCompany_id          (sesion.getCompany_id());
		access.setProspectus_id       (sesion.getProspectus_id());
		access.setProspectus_id_viewed(prospectus_id);
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_width());
		access.setVertical_size       (sesion.getBrowser_height());
		access.setIpaddress           (sesion.getIP_address_client());
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setVersion_description (sesion.getVersion_description());				
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());				
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setUrl_access		  (sesion.getUrl_access());
		access.setScreen_id(SCREEN_SESIONES);
		access.setPercentage(0);
		
		service_access.add(access, false);
	}
}
