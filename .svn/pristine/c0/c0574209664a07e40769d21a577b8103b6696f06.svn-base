package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;

@ManagedBean @SessionScoped
public class MovimientosInversionista extends MovimientosInversionistaAMO  
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init()
	{	
		faces = FacesContext.getCurrentInstance();
		
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		if(sesion != null && sesion.getProspectus_id() != null)
		{		
			prospectus_id = sesion.getProspectus_id();
			company_id    = sesion.getCompany_id();
			
			membership_PK = new MembershipPK(prospectus_id, company_id);
	
			membership = service_membership.getMembershipById(membership_PK);
			
			SAFI_client_id = membership.getPerson().getSafi_client_id();
			
			movimientoActual = HTML_DEPOSITO; 
			menuSelActual    = MENU_DEPOSITO;	
			
			movimiento = "deposito";
			
			init_catalogos();	
		}
	}
	
	public void cambiaMovimiento(ActionEvent e)
	{				
		requestContext = RequestContext.getCurrentInstance();
		
		faces = FacesContext.getCurrentInstance();
		
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		movimiento = (String) e.getComponent().getAttributes().get("movimiento").toString();
		
		System.out.println(" ****  MovimientosInversionista.cambiaMovimiento(): " + movimiento);
		
		procesar_movimiento();		
	}	
	
	public void initPaginaMovimientos(){
		//Este método se ejecuta al Darle Click al menú general de Movimentos 
		procesar_movimiento();
	}
}
