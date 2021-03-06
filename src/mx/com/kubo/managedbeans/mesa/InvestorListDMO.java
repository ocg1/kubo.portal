package mx.com.kubo.managedbeans.mesa;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.CreaCreditoService;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.Role_Searching_PK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.Tutor;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.RoleSearchingService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.StatusInvService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TutorService;

public abstract class InvestorListDMO 
{	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService prevencionldservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice; 
	
	@ManagedProperty("#{statusInvServiceImp}")
	protected StatusInvService statusinvcatservice;
	
	@ManagedProperty("#{roleSearchingServiceImp}")
	protected RoleSearchingService rolesearchingservice;
	
	@ManagedProperty("#{eventServiceImp}")
	protected EventService eventService;
	
	@ManagedProperty("#{tutorServiceImp}")
	protected TutorService tutorservice;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService gnNaturalService;
		
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext elContext;
	protected ELResolver resolver;	
	
	protected SessionBean sesion;
	protected SearchSummaySession summarysesion;
	protected SummaryRequest summary;
	protected MenuControlTableBean navMenu;
	protected NavigationInvest navigationinvest;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected Investor   investor;
	protected InvestorPK investor_PK;
	
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected NotificadorIMO notificador;
	protected CreaCreditoService creacredito;
	
	protected Tutor thisTutor;
	protected PrevencionLD pld;
	protected SavingAccount saving;
	
	protected RoleFunctionController rfc;
	protected Role_Searching    role_searching;
	protected Role_Searching_PK role_searching_PK;		
	
	protected List<RoleFunction> rolefunctionlistbyrole;
	
	protected List<Investor> investorlist;
	protected List<StatusInvCat> lstStatus;	
	
	protected String tr = "<tr>"; 	
	protected String tr1 = "</tr>";
	protected String scriptStatus;
	protected String SAFI_client_id;
	protected String strRes;
	protected String acreditado_IFE;
	
	protected List<String> lista_errores;
	
	protected Integer company_id;
	
	protected boolean activation;
	protected boolean alta_prospecto_OK;
	protected boolean cuenta_OK;
	
	protected int prospectus_id_inv;
	
	protected final int EXTRANJERO = 0;
	protected final int SOLICITUD_INVERSION = 15;
	protected static final int IFE = 1;
	protected static final int INE = 2;

	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}

	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}

	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public void setPrevencionldservice(PrevencionLDService service) 
	{
		prevencionldservice = service;
	}

	public void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}

	public void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}

	public void setStatusinvcatservice(StatusInvService service) 
	{
		statusinvcatservice = service;
	}

	public void setRolesearchingservice(RoleSearchingService service) 
	{
		rolesearchingservice = service;
	}
	
	public void setEventService(EventService service) 
	{
		eventService = service;
	}

	public void setTutorservice(TutorService service) 
	{
		tutorservice = service;
	}

	public void setGnNaturalService(NaturalPersonService service) 
	{
		gnNaturalService = service;
	}

	public void setInvestorservice(InvestorService investorservice) 
	{
		this.investorservice = investorservice;
	}

	public List<Investor> getInvestorlist() 
	{
		return investorlist;
	}

	public void setInvestorlist(List<Investor> investorlist) 
	{
		this.investorlist = investorlist;
	}

	public SessionBean getSesion() 
	{
		return sesion;
	}

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
	}

	public boolean isActivation() 
	{
		return activation;
	}
	
	public void setActivation(boolean activation) 
	{
		this.activation = activation;
	}
	
	public int getProspectus_id_inv() {
		return prospectus_id_inv;
	}

	public void setProspectus_id_inv(int prospectus_id_inv) {
		this.prospectus_id_inv = prospectus_id_inv;
	}

	public String getScriptStatus() {
		return scriptStatus;
	}

	public void setScriptStatus(String scriptStatus) {
		this.scriptStatus = scriptStatus;
	}

	public List<StatusInvCat> getLstStatus() {
		return lstStatus;
	}

	public void setLstStatus(List<StatusInvCat> lstStatus) {
		this.lstStatus = lstStatus;
	}

	public Role_Searching getRolesearching() {
		return role_searching;
	}

	public void setRolesearching(Role_Searching rolesearching) 
	{
		role_searching = rolesearching;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public String getTr1() {
		return tr1;
	}

	public void setTr1(String tr1) {
		this.tr1 = tr1;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}
}
