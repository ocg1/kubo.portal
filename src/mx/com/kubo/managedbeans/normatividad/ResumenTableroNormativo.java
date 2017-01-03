package mx.com.kubo.managedbeans.normatividad;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

@ManagedBean
@ViewScoped
public class ResumenTableroNormativo extends ResumenTableroNormativoPMO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
		
		FacesContext 	faces     = FacesContext.getCurrentInstance();
		ELContext		context   = faces.getELContext();
		ELResolver		resolver  = faces.getApplication().getELResolver();
		
        sesion          = (SessionBean)         resolver.getValue(context, null, "sessionBean");
		
		accessRegister();
		
		initResumenTablero();
		
	}
	
	public String formatNumber( Double value ){
		
		return num.format(value);
		
	}

	
	
}
