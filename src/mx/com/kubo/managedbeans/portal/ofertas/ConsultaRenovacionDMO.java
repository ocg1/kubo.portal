package mx.com.kubo.managedbeans.portal.ofertas;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.mesa.buro.ProspectRiskIMO;
import mx.com.kubo.mesa.buro.ProyectLoanCreatorIMO;
import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.model.ConsultingManualPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ConsultingManualService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ScoringService;

public abstract class ConsultaRenovacionDMO 
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{consultingManualServiceImp}")
	protected ConsultingManualService service_consulting_manual;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService service_score;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan; 
	
	protected RequestContext request;
	protected FacesContext faces; 
	protected ELContext elContext;  
	protected ExternalContext external;
	protected ELResolver resolver;
	
	protected HtmlInputSecret input_secret;
	
	protected SessionBean sesion;
	protected SearchSummaySession sesion_search_request;
	
	protected NavigationBeanIMP navigation;
	
	protected NaturalPerson person;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected Scoring score;
	
	protected ConsultingManual   consulting;
	protected ConsultingManualPK consulting_PK;
	
	protected       AccessIMO auditor;
	protected ProspectRiskIMO risk;	
	
	protected ParserRenovacionAutomaticaIMO parser;
	protected         ProyectLoanCreatorIMO creator;	
	
	protected String bur_sol_num;
	
	protected String nombreCliente;
	protected String nombreCoach;
	
	protected final String access_from = "/Kubo/jsf/renovaciones_consulta.xhtml";
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected final int BORRADOR = 0;
	protected final int SCREEN_REGISTRO_AFTER_CONSULTA = 5;
	
	protected final int SCREEN_CONSULTING_AUTOMATIC_RENOVATION = 80;
	
	protected boolean prospect_risk_ENABLED;
	protected boolean ID_provider_ENABLED;
	protected boolean password_ENABLED;
	protected boolean is_risk_processed;
	protected boolean redirect_to_wait_ENABLED;
	protected boolean redirect_to_ofert_ENABLED;
	protected boolean redirect_to_registro_ENABLED;
	protected boolean ofert_ENABLED;
	protected boolean consulta_vigente_OK;
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setService_membership(MembershipService service)
	{
		service_membership = service; 
	}
	
	public void setService_consulting_manual(ConsultingManualService service)
	{
		service_consulting_manual = service;
	}
	
	public void setService_score(ScoringService service)
	{
		service_score = service;
	}
	
	public AccessIMO getAuditor()
	{
		return auditor;
	}

	public boolean isID_provider_ENABLED() 
	{
		return ID_provider_ENABLED;
	}		
	
	public boolean isRedirect_to_wait_ENABLED() 
	{
		return redirect_to_wait_ENABLED;
	}
	
	public boolean isRedirect_to_ofert_ENABLED() 
	{
		return redirect_to_ofert_ENABLED;
	}
	
	public boolean isRedirect_to_registro_ENABLED()
	{
		return redirect_to_registro_ENABLED;
	}

	public ProyectLoanService getService_proyect_loan() {
		return service_proyect_loan;
	}

	public void setService_proyect_loan(ProyectLoanService service_proyect_loan) {
		this.service_proyect_loan = service_proyect_loan;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCoach() {
		return nombreCoach;
	}

	public void setNombreCoach(String nombreCoach) {
		this.nombreCoach = nombreCoach;
	}
}
