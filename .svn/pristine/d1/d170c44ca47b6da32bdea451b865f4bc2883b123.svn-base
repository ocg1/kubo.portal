package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.util.List;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;

public class SearchEngineIMP extends SearchEngineAMO
implements SearchEngineIMO
{
	public final List<UserViewFullNameForCoach> init_search(String search_TOKEN)
	{
		lista_users = service_search_request.getLista_prospectus_for_coach(search_TOKEN, sesion.getPromotor_id());
		
		return lista_users;		
	}
	
	public final void init_membership(SelectEvent event)
	{
		request = RequestContext.getCurrentInstance(); 
				
		email = event.getObject().toString();
		
		membership = service_membership.getMembershipByEmail(email);
		
		company_id    = membership.getMembershipPK().getCompany_id();
		prospectus_id = membership.getMembershipPK().getProspectus_id();
		
		proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
		
		proyect_loan_TOKEN   = "";
		proyect_loan_ENABLED = false;
		
		if(proyect_loan != null)
		{
			init_proyect_loan_TOKEN();
			
			proyect_loan_ENABLED = true;
		}
		
		request.addCallbackParam("email", email);
		request.addCallbackParam("proyect_loan_TOKEN", proyect_loan_TOKEN);
		request.addCallbackParam("proyect_loan_ENABLED", proyect_loan_ENABLED);
	}
	
	public final void init_summary_request()
	{
		request = RequestContext.getCurrentInstance();
		
		sesion_search_request.setSearchSummary(proyect_loan_TOKEN);
		
		summary_request.init();		
		
		request.addCallbackParam("isPerson", false);
		request.addCallbackParam("isValid", true);					
		request.addCallbackParam("actividad_ENABLED",    true);
		request.addCallbackParam("proyect_loan_ENABLED", proyect_loan_ENABLED);
		request.addCallbackParam("investor_ENABLED",     false);
	}
}
