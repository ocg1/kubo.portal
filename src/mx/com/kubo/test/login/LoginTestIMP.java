package mx.com.kubo.test.login;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.test.acreditado.PanelSeleccionIMO;

@SuppressWarnings("serial")
@ManagedBean(name = "login_test") 
@SessionScoped
public final class LoginTestIMP extends LoginTestAMO
implements LoginTestIMO, Serializable
{		
	public LoginTestIMP()
	{
		super();
	}
	
	public final void iniciar_sesion()
	{
		faces = FacesContext.getCurrentInstance();	
		
		external = faces.getExternalContext();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion           = (SessionBean)       resolver.getValue(context, null, "sessionBean");
		panel_seleccion  = (PanelSeleccionIMO) resolver.getValue(context, null, "panel_seleccion");
		
		member_selected = panel_seleccion.getUsuario_selected();
		
		if(member_selected != null)
		{
			user_email = member_selected.getEmail();
			person     = member_selected.getPerson();
			
			sessionUsed = (HttpSession) external.getSession(false);
			sessionUsed.invalidate();
			
			sessionUsed = (HttpSession) external.getSession(true);
			sessionUsed.setAttribute("new", Boolean.FALSE);
			
			sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
			
			if(service_login.checkCountUsed(person, user_email))
			{
				if(service_login.initSession(member_selected))
				{				
					//service_login.addLastLogin(member_selected);
													
					asignar_banderas_sesion();
					
					sesion_ENABLED = true;
					
					System.out.println("\nLoginTestIMP.iniciar_sesion(" + user_email + "): OK");
				}			
			} 		
		}
	}
}
