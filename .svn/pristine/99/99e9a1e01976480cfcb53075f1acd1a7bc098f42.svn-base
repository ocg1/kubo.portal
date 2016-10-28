package mx.com.kubo.managedbeans.perfil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

@ManagedBean (name = "notificaciones_sistema") @ViewScoped 
public class SystemNotificationIMP extends SystemNotificationDMO
implements Serializable
{
	private static final long serialVersionUID = 164172922891384568L;
	
	@PostConstruct
	public void init() 
	{
		faces    = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
				
		company_id    = sesion.getCompany_id();
		prospectus_id = sesion.getProspectus_id();
		
		bitacora = service_bitacoras.getSystem_notification_log(prospectus_id);		
		
		contratos = service_file.getListaContratos(prospectus_id, company_id);
	}
}
