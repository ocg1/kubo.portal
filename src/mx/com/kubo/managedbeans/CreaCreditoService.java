package mx.com.kubo.managedbeans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;

@ManagedBean @RequestScoped
public class CreaCreditoService extends CreaCreditoServiceAMO
{		
	@PostConstruct
	public void init()
	{
		lista_errores = new ArrayList<String>();
		
		faces = FacesContext.getCurrentInstance();		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");		
	}
	
	public final void creaCliente(ActionEvent evento)
	{				
		attributes = evento.getComponent().getAttributes();
		
		init_param_values();
		try{
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
	
			String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
				    
					if(ipAddressClient == null)  
					{
				    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
					}
		
		validaInusualBehavior( ipAddressClient );
		}catch(Exception e){
			
		}
		
		if( !is_inussual_person ){
		
			init_proyect_loan();
			
			init_alta_prospecto();
			init_solicitud_credito();	
			init_creacion_cliente();
			init_alta_cliente();
			init_creacion_cuenta();			
			
			init_alta_relacionado_cuenta();
			
			init_alta_conocimiento_cuenta();
			init_alta_conocimiento_cliente();		
			
			init_autorizar_cuenta();
			init_creacion_credito();		
			init_activacion_cuenta();		
			init_creacion_seguro_vida();
						
			init_status_funding();
		
		}else{
			
			status_funding = false;
			
			try{
			
				request = RequestContext.getCurrentInstance();
				
				lista_errores = new ArrayList<String>();
				
				lista_errores.add("Se necesita permiso especial de Dirección");
				
				request.addCallbackParam("isFunding", status_funding);
				request.addCallbackParam("listaerror", new JSONArray(lista_errores.toArray(),true).toString());
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}

	public boolean callWSSafiAltaPros()
	{				
		init_alta_prospecto();
		
		return alta_prospecto_OK;
	}
	
	public final boolean creaSolicitud()
	{	
		init_solicitud_credito();
		
		return solicitud_credito_OK;
	}
	
	public final String creaClienteSAFI()
	{
		init_creacion_cliente();
		
		return SAFI_client_id;
	}
	
	public boolean callSPSafiAltaIFE(int prospectus_id, int company_id, ProyectLoan proyect_loan) 
	{
		init_alta_cliente();
		
		return status_funding;
	}
	
	public final boolean creaCuentaSafi()
	{	
		init_creacion_cuenta();
		
		return cuenta_OK;
	}
	
	public void creaPLDCuentaSAFI()
	{			
		init_alta_conocimiento_cuenta();
	}
	
	public void createPLDSAFI()
	{				
		init_alta_conocimiento_cliente();
	}
	
	public final boolean creaCreditoSafi()
	{
		init_creacion_credito();
		
		return status_funding;
	}
	
	public boolean creaSeguroVida(ProyectLoan pl , SavingAccount saving)
	{
		init_creacion_seguro_vida();
		
		return false;
	}
	
	public void actualizaPLD(int prospectus_id,int company_id, ProyectLoan pl)
	{				
		proyect_loan = pl;
		
		pld = service_PLD.getPrevencionLDByProspectus(prospectus_id, company_id);
		
		if(pld != null)
		{
			saving_account = service_saving_account.getSavingAccountByProspectus(prospectus_id, company_id);
			
			SAFI_account_id = saving_account.getSafi_account_id();
			
			if(saving_account != null && SAFI_account_id != null && !SAFI_account_id.equals("") && !SAFI_account_id.equals(" "))
			{
				creaPLDCuentaSAFI();
			}
			
			SAFI_client_id = pl.getPerson().getSafi_client_id();
			
			if(SAFI_client_id != null && !SAFI_client_id.equals("") && !SAFI_client_id.equals(" "))
			{
				createPLDSAFI();
			}
		}
	}
			
	public boolean creaPLDSafi()
	{
		boolean bres = false;
		
		return bres;
	}
}
