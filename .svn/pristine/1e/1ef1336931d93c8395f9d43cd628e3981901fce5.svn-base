package mx.com.kubo.managedbeans;

import java.io.IOException;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.tools.Utilities;

public abstract class ForgotPassPMO extends ForgotPassAMO
{	
	protected void procesar_tipo_reestalecer_password_SELECTED_portal() 
	{		
		switch (tipo_reestalecer_password_SELECTED) 
		{
			case RESPONDER_PREGUNTA_SEGURIDAD:	
				if(security_question_id != null) 
				{
					asignar_pregunta_seguridad();
					
				} else {
					
					errorMsg = "No tenemos registrada ninguna pregunta de seguridad en esta cuenta, por favor elige la otra opción.";
				}
			break;
		
			case ENVIAR_PASSWORD_TEMPORAL:					
				enviar_password_temporal_portal();								
			break;

			default: break;		
		}	
	}
	
	protected void procesar_tipo_reestalecer_password_SELECTED() 
	{		
		switch (tipo_reestalecer_password_SELECTED) 
		{
			case RESPONDER_PREGUNTA_SEGURIDAD:
				
				if(security_question_id != null) 
				{
					asignar_pregunta_seguridad();
						
				} else {
						
						errorMsg = "No tenemos registrada ninguna pregunta de seguridad en esta cuenta, por favor elige la otra opción.";
				}
			break;
			
			case ENVIAR_PASSWORD_TEMPORAL:					
				
				enviar_password_temporal();		
									
			break;

			default: break;		
		}	
	}
	
	protected void procesar_password_temporal() 
	{
		String tmp = Utilities.encrypt(passtemp);
		
		tempPassword = tempPassService.getTempPassByPass(tmp);
		
		if(tempPassword != null)
		{
			if(tempPassword.getIs_used().equals('N') && tempPassword.getValid_date().after(new Date()))
			{
				prospectus_id = tempPassword.getProspectus_id();
				company_id    = tempPassword.getTempPassPK().getCompany_id();
				
				membership = membershipService.getMembershipById(new MembershipPK(prospectus_id, company_id));
									
				prospectus_id = membership.getMembershipPK().getProspectus_id();
				company_id    = membership.getMembershipPK().getCompany_id();
				
				natural_person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
				natural_person    = naturalPersonService.getNaturalPersonById(natural_person_PK);
				
				email = membership.getEmail();
				name  = natural_person.getFirst_name();
				
				recoveryByEmail = true;
				changePass      = true;
				
			} else if(tempPassword.getValid_date().before(new Date())) {
				
				faces    = FacesContext.getCurrentInstance();
				external = faces.getExternalContext();
				request  = (HttpServletRequest) external.getRequest();
				
				path = request.getContextPath();
				url  = (path +"/jsf/recoverymsg.jsf");
				
				try 
				{
					external.redirect(url);	
				      
				} catch (IOException ex) {
					
				}
				
			} else if(tempPassword.getIs_used().equals('S')) {
				
				faces    = FacesContext.getCurrentInstance();
				external = faces.getExternalContext();
				request  = (HttpServletRequest)external.getRequest();
				
				path = request.getContextPath();
				url  = path + "/jsf/recoverymessage.jsf";
				
				try 
				{
					external.redirect(url);		
					
				} catch (IOException ex) {}
			}
			
		} else {
			
			panel_opciones_ENABLED = true;
		}	
	}
}
