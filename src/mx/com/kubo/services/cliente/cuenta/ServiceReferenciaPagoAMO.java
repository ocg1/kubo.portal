package mx.com.kubo.services.cliente.cuenta;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.referencia_pago_panel.PanelEMO;
import mx.com.kubo.tools.Utilities;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public abstract class ServiceReferenciaPagoAMO extends ServiceReferenciaPagoDMO
{
	protected void init_referencia_pago() 
	{
		panel = new PanelEMO();
		
		panel.setAcreditado(acreditado);		
		panel.setSafi_credit_id(credito_id);	
		
		panel.setCuota(cuota);	
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		ELResolver resolver = faces.getApplication().getELResolver();
		ELContext context  = faces.getELContext();
		
		sesion          = (SessionBean)       resolver.getValue(context, null, "sessionBean");
		
		if ( sesion.getCredito_SAFI() != null && sesion.getCredito_SAFI().equals(credito_id)  ){
			
			System.out.println( "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
			System.out.println( "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
			System.out.println( "/*/*/*/*/*/*/*/*/MISMO/CREDITO/*/*OK/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
			System.out.println( "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
			System.out.println( "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
			
			
		}else{
			
			if ( sesion.getCredito_SAFI() != null){
			
				System.out.println( "--**--**--**--**--**--*ERROR--**--**--**--**--**--**--**-ERROR**--**--**" );
				System.out.println( "--**--ERROR*--**--**--**--**--**--**--**ERROR-**--**--**--**--**--**--**" );
				System.out.println( "--**--**--**--**--**ERROR-**--**--**--**--**--**--**--**--**--**--**--**" );
				System.out.println( "--**--**--**--**--**--**--**--**--**--**--**--**--**-ERROR**--**--**--**" );
				System.out.println( "--**--*ERROR--**--**--**--**--**--**-ERROR**--**--**--**--**--**--**--**" );
				System.out.println( "\n\n");
				System.out.println( ""+sesion.getCredito_SAFI()  + "  " + credito_id);
				System.out.println( "\n\n");
				System.out.println( "--**--**--**--**--**--*ERROR--**--**--**--**--**--**--**-ERROR**--**--**" );
				System.out.println( "--**--ERROR*--**--**--**--**--**--**--**ERROR-**--**--**--**--**--**--**" );
				System.out.println( "--**--**--**--**--**ERROR-**--**--**--**--**--**--**--**--**--**--**--**" );
				System.out.println( "--**--**--**--**--**--**--**--**--**--**--**--**--**-ERROR**--**--**--**" );
				System.out.println( "--**--*ERROR--**--**--**--**--**--**-ERROR**--**--**--**--**--**--**--**" );
				
			}
		}
		
		panel.setLista_referencias_tmp(dao.getLista_referencias_pago(credito_id));
		panel.setListaRefenciasPago();
		panel.setListaReferenciasPago_SPEI();	
	}
		
	protected void asignarLog_message() 
	{				
		log_message = new StringBuilder();
		
		log_message.append("Prospectus_id_viewer: ").append(Integer.toString(sesion.getProspectus_id())).append(" -> ");
		log_message.append("Prospectus_id_viewed: ").append(Integer.toString(prospectus_id));		 		
		
		System.out.println("Se consultó Referencia de Pago: " + log_message.toString());			
	}

	protected final void asignarProspecto()
	{				
		FacesContext faces      = FacesContext.getCurrentInstance();
		ELResolver resolver   = faces.getApplication().getELResolver();
		ELContext context_EL = faces.getELContext();
		
		sesion     = (SessionBean) resolver.getValue(context_EL, null, "sessionBean");
		
		SearchSummaySession summary     = (SearchSummaySession) resolver.getValue(context_EL, null, "searchSummaySession");
		
		if(sesion.getArea() == 'L')
		{	
			prospectus_id = sesion.getProspectus_id();
			company_id    = sesion.getCompany_id();		
			
		} else {
			
			if( summary != null && summary.getSearchSummary() != null ){
				
				String[] summ = summary.getSearchSummary().split("::");
				
				if( summ != null && summ.length  == 4  ){
				
					prospectus_id = Integer.parseInt( summ[2] );
					company_id    = Integer.parseInt( summ[3] );
				}else{
					prospectus_id = Integer.parseInt( summ[0] );
					company_id    = Integer.parseInt( summ[1] );
				}
			}
		}
		
		if(prospectus_id != null && company_id != null)
		{
			
			gnNaturalPersonPK npk = new gnNaturalPersonPK(prospectus_id, company_id);
			
			persona = persona_service.getNaturalPersonById(npk);
			
			prospecto = persona.getProspectus();
			
		}			
	}	
	
	protected void asignarRegistroAcceso(int screen_id) 
	{
		access = new Access();
		
		access.setCompany_id(company_id);
		access.setProspectus_id(sesion.getProspectus_id());
		access.setProspectus_id_viewed(prospectus_id);
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		access.setWeb_browser        (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system          (sesion.getOsbrawser());
		access.setHorizontal_size    (sesion.getBrowser_width());
		access.setVertical_size      (sesion.getBrowser_height());
		access.setUser_agent         (sesion.getUser_agent());
		access.setDevice_info        (sesion.getDevice_info());
		access.setIpaddress          (sesion.getIP_address_client());
		
		access.setUrl_access		  (sesion.getUrl_access());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access, true);
	}
}
