package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

@ManagedBean (name = "forgotPass") @ViewScoped 
public final class ForgotPass extends ForgotPassPMO
implements ForgotPassIMO, Serializable 
{					
	private static final long serialVersionUID = 5352590685935520790L;

	@PostConstruct
 	public void init()
	{
		faces    = FacesContext.getCurrentInstance();
		external = faces.getExternalContext();
		
		passtemp = (String) external.getRequestParameterMap().get("counter");
		
		System.out.printf("\nForgotPass.init(): valor de pass = " + passtemp);
		
		init_max_number_attempts();
		
		securityQList = securityQService.getSecurityQuestionList();
		
		if(passtemp != null)
		{
			procesar_password_temporal();		
			
		} else {
			
			panel_opciones_ENABLED = true;
		}
		
		if(membership != null)
		{
			answer   = membership.getAnswer();
			seqQuest = membership.getSecurity_question_id();
		}
	}
	
	public final String validateInformation()
	{
		faces     = FacesContext.getCurrentInstance();	
		external  = faces.getExternalContext();
		
		servlet_request = (HttpServletRequest) external.getRequest();
		
		faces_action = "fortpass";
		
		if(options == null)
		{
			errorMsg = "Seleccione una opción";
			
			return "fortpass";
			
		} else {
						
			tipo_reestalecer_password_SELECTED = Integer.parseInt(options);
			
			asignar_email_OK();		
		}
		
		if(is_email_OK)
		{
			procesar_tipo_reestalecer_password_SELECTED();
			
		}
		
		if (membership != null) {
			faces    = FacesContext.getCurrentInstance();				
			context  = faces.getELContext();
			resolver = faces.getApplication().getELResolver();
			SessionBean session = (SessionBean) resolver.getValue(context, null, "sessionBean");
			session.setNombre(membership.getPerson().NombreCompletoNPM());
			session.setEmail(getEmail());
		}				
		return faces_action;
		
	}	
	
	public String validateInformation_portal()
	{
		faces     = FacesContext.getCurrentInstance();	
		external  = faces.getExternalContext();
		
		servlet_request = (HttpServletRequest) external.getRequest();
		
		faces_action = "fortpass";
		
		if(options == null)
		{
			errorMsg = "Seleccione una opción";
			
			return "fortpass";
			
		} else {
						
			tipo_reestalecer_password_SELECTED = Integer.parseInt(options);
			
			asignar_email_OK();		
		}
		
		if(is_email_OK)
		{
			procesar_tipo_reestalecer_password_SELECTED_portal();
			
		}
		
		if (membership != null) {
			faces    = FacesContext.getCurrentInstance();				
			context  = faces.getELContext();
			resolver = faces.getApplication().getELResolver();
			SessionBean session = (SessionBean) resolver.getValue(context, null, "sessionBean");
			session.setNombre(membership.getPerson().NombreCompletoNPM());
			session.setEmail(getEmail());
		}				
		return faces_action;
		
	}
	
	public final String validateInformation_Portal()
	{
		faces     = FacesContext.getCurrentInstance();	
		external  = faces.getExternalContext();
		
		servlet_request = (HttpServletRequest) external.getRequest();
		
		faces_action = "fortpass";
		
		if(options == null)
		{
			errorMsg = "Seleccione una opción";
			
			return "fortpass";
			
		} else {
						
			tipo_reestalecer_password_SELECTED = Integer.parseInt(options);
			
			asignar_email_OK();		
		}
		
		if(is_email_OK)
		{
			procesar_tipo_reestalecer_password_SELECTED();
			
		}
		
		if (membership != null) {
			faces    = FacesContext.getCurrentInstance();				
			context  = faces.getELContext();
			resolver = faces.getApplication().getELResolver();
			SessionBean session = (SessionBean) resolver.getValue(context, null, "sessionBean");
			session.setNombre(membership.getPerson().NombreCompletoNPM());
			session.setEmail(getEmail());
		}				
		return faces_action;
		
	}

	public final String saveNewPassword()
	{
		faces    = FacesContext.getCurrentInstance();				
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		external = faces.getExternalContext();
		request  = (HttpServletRequest) external.getRequest();
		
		is_success = false;
		
		mensajeError = "";
		
		faces_action = "fortpass";
		
		if(changePass)
		{			 	
			ipAddress  = request.getHeader("X-FORWARDED-FOR");
			  
	        if(ipAddress == null)  
	        {  
	          ipAddress = request.getRemoteAddr();  
	        }
		        
			if(newPass != null && newPassConf != null && newPass.equals(newPassConf))
			{												
				company_id    = membership.getMembershipPK().getCompany_id();
				prospectus_id = membership.getMembershipPK().getProspectus_id();
				
				passNewEncript = Utilities.encrypt(newPass);
				
				membership.setPassword(passNewEncript);
				membership.setFailed_token_attempts(0);
				membership.setFailed_login_attempts(0);
				membership.setIs_blocked("N");				
					
				membershipService.update(membership);
				
				is_success = true;

				mensajeError = "";
				errorMsg = "";
			
				init_notificar_evento();
				registrar_change_control();
												
				if(is_change_control_OK)
				{
					System.out.println("ForgotPassPMO.registrar_change_control(): OK");
					
					validSession = true;
					
					header_sesion = (HeaderBean) resolver.getValue(context, null, "headerBean");
						
					header_sesion.setPassword(newPass);
					header_sesion.setUser(email);		
					header_sesion.iniciaSesion();
					
					faces_action = "validrecovery";
					
					if(displayQuestion)
					{
						guardar_nuevas_respuestas_seguridad();
					}
										
					if(recoveryByEmail && tempPassword != null)
					{
						tempPassword.setIs_used('S');
						tempPassword.setDate_used(new Date());
						
						if(tempPassService.updateTempPass(getTempPassword()))
						{
							System.out.printf("\nForgotPass.saveNewPassword(): Se actualizo el registro en tempasswork");
							
						} else {
							
							System.out.printf("\nForgotPass.saveNewPassword(): Error al actualizar en la tabla temporal de contraseña temporal.");
						}
					}
						
				} else {
					
					System.out.printf("\nForgotPass.saveNewPassword(): Error al guardar en tabla de control");
				}				
				
			} else {
				
				is_success = false;
				
				mensajeError = "Las contraseñas que ingresaste no coinciden";
				
				errorMsg = "Las contraseñas que ingresaste no coinciden";
			}
		}
		
		
		 RequestContext request   = RequestContext.getCurrentInstance();
		 
		 request.addCallbackParam("is_success", is_success);
		 request.addCallbackParam("mensajeError", mensajeError);
		 
		return faces_action;
	}
	
	public void validaifExistPass()
	{
		if(passwordhistoryservice.existPassword(Utilities.encrypt(newPass), membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id()) )
		{
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("existPass", true );
			requestContext.addCallbackParam("message", "La contraseña ya ha sido asignada anteriormente. Por favor intente con otra." );
			displayButtonSave = false;
			
		}else{
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("existPass", false );
			requestContext.addCallbackParam("message", "" );
			displayButtonSave = true;
		}
		
	}

	
	public void resetContrasena() {
		//resetear valores
		validSession = false;
		panel_opciones_ENABLED = true;
		validMail = false; 
		max_number_attempts_reached = false; 
		changePass = false;
	}
}
