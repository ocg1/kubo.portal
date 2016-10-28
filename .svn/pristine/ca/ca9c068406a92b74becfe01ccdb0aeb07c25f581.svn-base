package mx.com.kubo.managedbeans.perfil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.tools.Utilities;

public final class EditorCorreoIMP extends EditorCorreoDMO
{
	public final void saveNewMail()
	{		
		boolean flag = false;
		request  = RequestContext.getCurrentInstance();
		
		if(!membership.getEmail().equals(newemail))
		{
			if(Utilities.validaMail(newemail))
			{
				if(!service_login.verifyEmail(newemail))
				{
									
					try 
					{
						String correo_anterior;
						correo_anterior = membership.getEmail();
						membership.setEmail(newemail);
						
						notificador.setEmisor(membership);
						notificador.notificar(Evento.PETICION_CAMBIO_CUENTA_CORREO);
						
						msg = newemail;
						
						if(saveChangeData("ln_membership", "email", correo_anterior, newemail, "Solicito cambio de correo"))
						{
							HeaderBean sesion = (HeaderBean) resolver.getValue(context, null, "headerBean");
							sesion.SignOut();
							SessionBean session = (SessionBean) resolver.getValue(context, null, "sessionBean");
							session.setNombre(person.getFirst_name());
							session.setEmail(newemail);
							flag = true;
						} else {
							flag=false;
						}	
							
					} catch (NotificacionException e) {						
						e.printStackTrace();						
					}				
				
				}else{
					flag=false;
					msg="El correo "+newemail+" ya existe.";
				}
			}else{
				flag=false;
				msg="El correo "+newemail+" no es valido.";
			}
		}else{
			flag=false;
			msg="No se detectaron cambios al correo.";
		}
		request.addCallbackParam("isValid", flag);
		request.addCallbackParam("msg", msg);
		request.addCallbackParam("typeedit",2);
	}
	
	protected boolean saveChangeData(String table,String field,String origValue,String newValue,String comment)
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");
		
	    if(ipAddress == null)  
	    {  
	    	ipAddress = httpServletRequest.getRemoteAddr();  
	    }
	    
	    prospectus_id = person.getNatPerPK().getProspectus_id();
	    company_id    = person.getNatPerPK().getCompany_id();
	    
	    change_controlPK = new Change_controlPK();
	    change_controlPK.setProspectus_id(prospectus_id);
	    change_controlPK.setCompany_id(company_id);
		
		change_control = new Change_control();
		change_control.setChange_controlPK(change_controlPK);
		change_control.setChange_prospectus_id(change_prospectus_id);
		change_control.setAfected_table(table);
		change_control.setIp(ipAddress);			
		change_control.setOriginal_value(origValue);
		change_control.setNew_value(newValue);
		change_control.setField(field);
		change_control.setComments(comment);
		
		if(service_change_control.addChangeControl(change_control,prospectus_id,company_id))
		{
			return true;
			
		} else {
			
			return false;		
		}
	}
}
