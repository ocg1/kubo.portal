package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.util.List;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ServiceSearchRequestIMO;

public abstract class SearchEngineDMO
implements SearchEngineIMO
{
	protected ServiceSearchRequestIMO service_search_request;
	protected MembershipService       service_membership;
	protected ProyectLoanService      service_proyect_loan;
	
	protected RequestContext request;
	
	protected SessionBean sesion;
	protected Membership  membership;		
	
	protected ProyectLoan   proyect_loan;	
	protected ProyectLoanPK proyect_loan_PK;
	
	protected SummaryRequest  summary_request;
	protected SearchSummaySession sesion_search_request;
	
	protected List<UserViewFullNameForCoach> lista_users;
	
	protected StringBuilder sb;
	protected String email;		
	protected String search;
	protected String proyect_loan_TOKEN;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer proyect_loan_id;
	protected Integer proyect_id;
	
	protected boolean proyect_loan_ENABLED;				
	
	public final void setService_search_request(ServiceSearchRequestIMO service) 
	{
		service_search_request = service;
	}

	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}

	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
	}
	
	public final void setSummary_request(SummaryRequest summary_request) 
	{
		this.summary_request = summary_request;
	}

	public final void setSesion_search_request(SearchSummaySession sesion_search_request) 
	{
		this.sesion_search_request = sesion_search_request;
	}

	public final void setSearch(String search) 
	{
		this.search = search;
	}
	
	public final String getSearch() 
	{
		return search;
	}
}
