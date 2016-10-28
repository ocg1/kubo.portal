package mx.com.kubo.repositories.impl;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public final class AccessIPCheckIMP extends AccessIPCheckAMO
implements AccessIPCheckIMO
{
	public final void init_ip_address_CHANGED() 
	{		
		faces = FacesContext.getCurrentInstance();
		
		if( faces != null ){
			
			external = faces.getExternalContext();	
					
			if(ip_address_CHANGED && tiempo_inactividad_ENABLED)
			{			
				init_change_control();			
				init_baja_usuario_firmado();
				isSesion_DISABLED();
				System.out.println("AccessIPCheckIMP.init_ip_address_CHANGED(): OK");
			}
		
		}
	}
	
	protected final boolean isSesion_DISABLED()
	{
//		boolean bandera = false;
//		
//		//System.out.println(getPath());
//																											
//			String url = "/Kubo/Portal/sesion-expirada.xhtml?redirecFrom=accessIpChange";
//							
//			try 
//			{
//				System.out.println( "Redirigiendo desde AccessIpChange: " + url);
//				external.redirect(url);
//			        
//			} catch (IOException ex) {						      
//				ex.printStackTrace();
//			}catch(Exception e){
//				System.out.println("Redirect "+url);
//			}
//			
//			bandera = true;
//		
//		
//		return bandera;
		
		System.out.println( "<<<<<<<<<<<<<<<<<<<  isSesion_DISABLED  >>>>>>>>>>>>>>>>>>>" );
		
		return true;
		
	}
	
	
}
