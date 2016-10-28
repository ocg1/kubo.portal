package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

@ManagedBean
@RequestScoped
public class ConfirmationMail extends ConfirmationMailDMO 
implements Serializable 
{
	private static final long serialVersionUID = -8724355033416634963L;
	
	@PostConstruct
	public void init() 
	{
		faces = FacesContext.getCurrentInstance();
		
		external = faces.getExternalContext();
		
		servlet = (HttpServletRequest) external.getRequest();
		
		ipAddress  = servlet.getHeader("X-FORWARDED-FOR");  
		
		if(ipAddress == null)  
		{  
			ipAddress = servlet.getRemoteAddr();  
		}
		
		counter  = (String) external.getRequestParameterMap().get("counter");
		newemail = (String) external.getRequestParameterMap().get("email");
		
		if(counter != null && newemail!=null )
		{
			membership = membershipService.getMemberShipByActivCode(counter);
			
			if(membership != null && !membership.getEmail().equals(newemail))
			{
				prospectus_id = membership.getMembershipPK().getProspectus_id();
				company_id    = membership.getMembershipPK().getCompany_id();
				
				lisChangMail = changeControlService.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"ln_membership"},new String[]{"email"});
				
				if(lisChangMail!=null && !lisChangMail.isEmpty())
				{
					changeMail = lisChangMail.get(lisChangMail.size()-1);
					
					boolean change_mail_ENABLED = changeMail.getNew_value().equals(newemail) && changeMail.getComments().endsWith("Solicito cambio de correo");
					
					if(change_mail_ENABLED)
					{							
						membership.setEmail(newemail);
						
						membershipService.update(membership);
						
						init_change_control();

						if(change_OK)
						{
							success = true;
						}
							
					}else{
						success=false;
					}
				}else{
					success=false;
				}
			}else{
				success=false;
			}
								
		}
	}

	private void init_change_control() 
	{
		changeCtrlPK = new Change_controlPK();
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		changeCtrl = new Change_control();
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(prospectus_id);
		changeCtrl.setAfected_table("ln_membership");
		changeCtrl.setIp(ipAddress);			
		changeCtrl.setOriginal_value(membership.getEmail());
		changeCtrl.setNew_value(newemail);
		changeCtrl.setField("email");
		changeCtrl.setComments("Confirmo cambio de correo");
			
		change_OK = changeControlService.addChangeControl(changeCtrl,prospectus_id,company_id);		
	}
}
