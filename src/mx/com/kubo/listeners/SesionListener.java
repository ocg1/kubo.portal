package mx.com.kubo.listeners;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mx.com.kubo.managedbeans.SessionBean;

public final class SesionListener extends SesionListenerDMO
implements HttpSessionListener 
{
	public SesionListener() 
	{
		System.out.printf("\nSesionListener(): OK");
	}

	public void sessionCreated(HttpSessionEvent evento) 
	{
		String msg = "Current Session created : ";
		
		msg += evento.getSession().getId() + " at "+ new Date();
		
		System.out.println("\n" + msg + " timeOut: "+ evento.getSession().getMaxInactiveInterval() +"  \n");
		
//		evento.getSession().setMaxInactiveInterval(60); 
//		
//		System.out.println("\n" + msg + " timeOut: "+ evento.getSession().getMaxInactiveInterval() +"  \n");
	}

	@SuppressWarnings("rawtypes")
	public void sessionDestroyed(HttpSessionEvent evento) 
	{
		String 		id     = evento.getSession().getId();
		HttpSession sesion = evento.getSession();
		
		
		ServletContext servlet = sesion.getServletContext();
		
		System.out.printf("\nCurrent Session destroyed :"  + sesion.getId() + " Logging out user... \n");  
		
		usuarios = (Map) servlet.getAttribute("usuariosFirmados");
		
		faces    = FacesContext.getCurrentInstance();
		
		if(usuarios.containsKey(id))
		{
			usuarios.remove(id);
			
		/////
			
			Hashtable<String, String> logOutuser = (Hashtable<String, String>) servlet.getAttribute("logOutuser");
			
			if( logOutuser == null ){
				servlet.setAttribute("logOutuser",new Hashtable());
				logOutuser = (Hashtable<String, String>)servlet.getAttribute("logOutuser");
			}
			
			logOutuser.put(id, "EXPIRED");
			
			System.out.println( "put Session : " + id);
			
			/////
		
			if(faces != null)
			{
				context  = faces.getELContext();
				resolver = faces.getApplication().getELResolver();
				
				if( resolver != null ){
					sesion_bean = (SessionBean) resolver.getValue(context, null, "sessionBean");
				}
				
			} else {
				
				//sesion_bean = Utilities.findBean("sessionBean");
				System.out.println( "sessionDestroyed : faces is null" );
				
			}				
			
			
			
			if(sesion_bean != null && sesion_bean.getProspectus_id() != null)
			{
				
				if(sesion_bean.isLog_out_ENABLED())
				{
					//url =  (getPath(  ) + "/Portal/index.xhtml?redirecFrom=log-out");
					registar_bitacora_access(SCREEN_ID_LOG_OUT);
					
				} else {
					//url =  (getPath(  ) + "/Portal/sesion-expirada.xhtml?redirecFrom=expired");
					
					registar_bitacora_access(SCREEN_ID_SESSION_EXPIRED);
				}
				
			}		
		
		}else{
			
			System.out.println( " SessionListener Destroy Sin Registro  " );
			
//			String url = (getPath(  ) + "/Portal/index.xhtml");
//			
//			try 
//			{
//				System.out.println( "Redirigiendo desde NavigationBean: " + url);
//				 faces.getExternalContext().redirect(url);
//			        
//			} catch (IOException ex) {						      
//				ex.printStackTrace();
//			}catch(Exception e){
//				System.out.println("Redirect "+url);
//			}
			
		}
		
		
		
	}
	
	
}