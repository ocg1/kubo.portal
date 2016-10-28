package mx.com.kubo.managedbeans.mesa.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

@ManagedBean(name = "tablero_control") @ViewScoped
public final class TableroControlIMP extends TableroControlDMO
implements Serializable, TableroControlIMO
{
	private static final long serialVersionUID = 7252885235803199867L;

	@PostConstruct
	public final void init()
	{
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
        sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
        
		alta_partner = new AltaPartnerIMP();
		alta_partner.setSesion(sesion);
	}
}
