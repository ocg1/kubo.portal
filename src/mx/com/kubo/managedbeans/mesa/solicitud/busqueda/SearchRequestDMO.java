package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.ActivityPersonIMO;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ServiceSearchRequestIMO;

public abstract class SearchRequestDMO 
implements SearchRequestIMO
{		
	@ManagedProperty("#{service_search_request}")
	protected ServiceSearchRequestIMO service_search_request;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;	
	
	@ManagedProperty("#{prospectusServiceImp}")	
	protected ProspectusService service_prospectus;
	
	@ManagedProperty("#{membershipServiceImp}")	
	protected MembershipService service_membership;
		
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService service_investor;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService access_service;
	
	protected FacesContext   faces;
	protected ELContext      elContext;
	protected ELResolver     resolver;
	protected RequestContext request;
	
	protected HtmlSelectOneRadio select_one_radio; 
	
	protected SessionBean  sesion;		
	protected SearchSummaySession sesion_search_request;
	protected SummaryRequest  summary_request;
	protected MenuControlTableBean menu;
	protected RoleFunctionController controller_role_function;
	
	protected List<ClientViewFullName> suggestions;
	protected List<RoleFunction> lista_role_function;	
	
	protected SearchEngineIMO coach_engine;
	protected ActivityPersonIMO    actividad;
	
	protected Prospectus   prospectus;
	protected ProspectusPK prospectusPK;
	protected MembershipPK membership_PK;
	protected Membership   member;
	protected InvestorPK   investor_PK;
	protected Investor     investor; 	
	protected ProyectLoan  proyectLoan;		
	
	protected StringBuilder sb;
		
	protected String search;
	protected String out;	
	protected String display;
	protected String sesion_search;
	protected String acreditado_TOKEN;
	protected String type_log;
	
	protected Integer prospectus_id;
	protected Integer role_id;
	protected Integer filtro_area_SELECTED;
			
	protected int company_id;
	protected int radioTypeSearch;
	protected int function_id;
	
	protected boolean displayUser;
	protected boolean isPerson;
	protected boolean isValid;
	protected boolean filtro_area_ENABLED;
	protected boolean actividad_ENABLED;
	protected boolean investor_ENABLED;
	protected boolean proyect_loan_ENABLED;
	
	protected static final int FILTRO_POR_NOMBRE        = 1;
	protected static final int FILTRO_POR_MAIL          = 3;
	protected static final int FILTRO_POR_USUARIO       = 4;
	protected static final int FILTRO_POR_ACREDITADO    = 5;
	protected static final int FILTRO_POR_INVERSIONISTA = 6;
	protected static final int BUSQUEDA_USUARIOS_INACTIVOS = 19;
	protected static final int BUSQUEDA_INVERSIONISTAS     = 24;
	protected static final int INVERSIONISTA_ENABLED    = 2;
	
	public final void setService_search_request(ServiceSearchRequestIMO service)
	{
		service_search_request = service;
	}

	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}

	public final void setService_prospectus(ProspectusService service) 
	{
		service_prospectus = service;
	}
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
	
	public final void setService_investor(InvestorService service) 
	{
		service_investor = service;
	}		

	public final SearchEngineIMO getCoach_engine() 
	{
		return coach_engine;
	}

	public final boolean isDisplayUser() 
	{
		return displayUser;
	}
	
	public final boolean isFiltro_area_ENABLED() 
	{
		return filtro_area_ENABLED;
	}

	public final void setRadioTypeSearch(int filtro_SELECTED) 
	{
		radioTypeSearch = filtro_SELECTED;				
		
		System.out.println("SearchRequestDMO.setRadioTypeSearch(): " + filtro_SELECTED);
	}
	
	public final void setFiltro_area_SELECTED(Integer filtro_SELECTED) 
	{
		filtro_area_SELECTED = filtro_SELECTED;
		
		System.out.println("SearchRequestDMO.setFiltro_area_SELECTED(): " + filtro_SELECTED);
	}
	
	public final Integer getFiltro_area_SELECTED() 
	{
		return filtro_area_SELECTED;
	}

	public final int getRadioTypeSearch() 
	{
		return radioTypeSearch;
	}
	
	public final void setSearch(String search) 
	{
		this.search = search;
	}
	
	public final String getSearch() 
	{
		return search;
	}

	public String getOut() 
	{
		return out;
	}

	public void setOut(String out) 
	{
		this.out = out;
	}

	public String getDisplay() 
	{
		return display;
	}

	public void setDisplay(String display) 
	{
		this.display = display;
	}	
	
	public void setProspectus_id( Integer prospectus_id ){
		this.prospectus_id = prospectus_id ; 
		
	}
	
	public void setCompany_id( Integer company_id ){
		this.company_id = company_id;
	}

	public AccessService getAccess_service() {
		return access_service;
	}

	public void setAccess_service(AccessService access_service) {
		this.access_service = access_service;
	}
	
}
