package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.ActivityPersonIMO;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.RoleFunction;

public abstract class SearchRequestAMO extends SearchRequestDMO
{
	protected void init_sesion_variables() 
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();		
		
		sesion                = (SessionBean)          resolver.getValue(elContext, null, "sessionBean");
		sesion_search_request = (SearchSummaySession)  resolver.getValue(elContext, null, "searchSummaySession");		
		summary_request       = (SummaryRequest)       resolver.getValue(elContext, null, "summaryRequest");
		actividad             = (ActivityPersonIMO)    resolver.getValue(elContext, null, "activityPerson");
		menu                  = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
				
		display = "none";
		
		isPerson             = false;
		isValid              = false;
		actividad_ENABLED    = false;
		investor_ENABLED     = false;
        proyect_loan_ENABLED = false;
        displayUser          = false;
        				
		radioTypeSearch      = FILTRO_POR_NOMBRE;
		filtro_area_SELECTED = FILTRO_POR_ACREDITADO;
				
        if(sesion.getCompany_id() != null)
        {
        	company_id  = sesion.getCompany_id();
        }
				
		role_id = sesion.getRole_id();
		
		if (role_id != null)
		{
			init_permisos(role_id);			
		}
	}
	
	private void init_permisos(int role_id)
	{		
		controller_role_function = (RoleFunctionController) resolver.getValue(elContext, null, "roleFunctionController");
		
		lista_role_function = controller_role_function.getFunctionByRole(role_id);
		
		for(RoleFunction role_function : lista_role_function)
		{	
			function_id = role_function.getPk().getFunction_id();
			
			switch(function_id)
			{
				case BUSQUEDA_USUARIOS_INACTIVOS:
					displayUser = true;
				break;
				
				case BUSQUEDA_INVERSIONISTAS:
						filtro_area_ENABLED = true;
				break;
			}			
		}		
	}
		
	protected void init_prospectus()
	{
		prospectus_id = Integer.parseInt(search);
		
		prospectusPK = new ProspectusPK();
		prospectusPK.setProspectus_id(prospectus_id);
		prospectusPK.setCompany_id(company_id);	
		
		prospectus = service_prospectus.getProspectusById(prospectusPK);
	}
	
	protected void init_acreditado_TOKEN() 
	{									
		sb = new StringBuilder();				
		sb.append(proyectLoan.getProyectloanPk().getProyect_loan_id()).append("::");
		sb.append(proyectLoan.getProyectloanPk().getProyect_id()).append("::");
		sb.append(proyectLoan.getProyectloanPk().getProspectus_id()).append("::");
		sb.append(proyectLoan.getProyectloanPk().getCompany_id());
		
		acreditado_TOKEN = sb.toString();							
	}
	
	protected void procesar_area() 
	{
		switch(prospectus.getArea())
		{
			case 'I':
				prospectus_id = prospectus.getProspectusPK().getProspectus_id();
				company_id    = prospectus.getProspectusPK().getCompany_id();
				
				acreditado_TOKEN = prospectus_id + "::" + company_id;	
				
				init_investor();									
				
				type_log = "EVA";
				investor_ENABLED = true;
				//isPerson = true;
				
				
				sesion_search_request.setTypeLog(type_log);
				sesion_search_request.setSearchSummary(acreditado_TOKEN);
				
				faces = FacesContext.getCurrentInstance();
				
				faces .getViewRoot().getViewMap().remove("summaryRequest");
				
				faces .getViewRoot().getViewMap().remove("chartBackBean");
				
				faces .getViewRoot().getViewMap().remove("checkScoreProcessed");
				
				
				resolver  = faces.getApplication().getELResolver();	
				
				elContext = faces.getELContext();
				
				summary_request = (SummaryRequest)  resolver.getValue(elContext, null, "summaryRequest");
				
		        summary_request.init();
				//summary_request.cargaInfoCompleta();	
		        //summary_request.initInvesmentData();

			break;
			
			default:
				prospectus_id = prospectus.getProspectusPK().getProspectus_id();
				company_id    = prospectus.getProspectusPK().getCompany_id();								
				
				actividad.setProspectus_id(prospectus_id);
				actividad.setCompany_id(company_id);				
				actividad.cargaInfo();
				
				acreditado_TOKEN = prospectus_id + "::" + company_id;																																				
				type_log = "EVA";				
				
				isPerson          = true;
				actividad_ENABLED = true;
				
				sesion_search_request.setTypeLog(type_log);
				sesion_search_request.setSearchSummary(acreditado_TOKEN);
				
				faces = FacesContext.getCurrentInstance();
				
				faces .getViewRoot().getViewMap().remove("summaryRequest");
				
				faces .getViewRoot().getViewMap().remove("chartBackBean");
				
				faces .getViewRoot().getViewMap().remove("checkScoreProcessed");
				
				resolver  = faces.getApplication().getELResolver();	
				
				elContext = faces.getELContext();
				
				summary_request = (SummaryRequest)  resolver.getValue(elContext, null, "summaryRequest");
				
				summary_request.init();				
			break;
		}		
	}
	
	
	private void init_investor() 
	{												
		investor_PK = new InvestorPK();
		investor_PK.setCompany_id(company_id);
		investor_PK.setProspectus_id(prospectus_id);
		
		investor = service_investor.getInvestorById(investor_PK);	
		
		if(investor != null && investor.getStatus_id() < INVERSIONISTA_ENABLED)
		{
			actividad_ENABLED = true;
			
		} else {
			
			actividad_ENABLED = false;
		}
	}
	

	protected void init_menu() 
	{		
		membership_PK = new  MembershipPK();
		
		membership_PK.setCompany_id    (Integer.parseInt(acreditado_TOKEN.split("::")[3]));
		membership_PK.setProspectus_id (Integer.parseInt(acreditado_TOKEN.split("::")[2]));
		
		member = service_membership.getMembershipById(membership_PK);
		
		if( member.getIs_canceled() != null 
		&&  member.getIs_canceled().trim().length() > 0 
		&& !member.getIs_canceled().equals("N"))
		{						
			menu.setProspectus_is_canceled( true );
			menu.setCanceledReason(member.getIs_canceled());
			
		} else {			
			
			menu.setProspectus_is_canceled( false );
			menu.setCanceledReason("");						
		}	
	}
}
