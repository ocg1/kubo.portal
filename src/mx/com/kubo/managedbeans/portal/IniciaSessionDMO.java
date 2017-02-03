package mx.com.kubo.managedbeans.portal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Promotor;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ClientsService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SegmentProspectusService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.SystemParamService;

public class IniciaSessionDMO 
{
	@ManagedProperty("#{loginServiceImp}")
	protected LoginServiceIMO service_login;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{clientsServiceImp}")
	protected ClientsService clientsservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService service_prospectus;
	
	@ManagedProperty("#{segmentProspectusServiceImp}")
	protected SegmentProspectusService segment_prospectus_service;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoring_service;
	
	protected final int EVENT_TIENDA_DISPONIBLE = 65; 
	
	protected RequestContext request;
	protected FacesContext    faces;
	protected ELResolver      resolver;
	protected ELContext       elContext;
	protected ExternalContext external;
	protected ServletContext  servlet;
	
	protected HtmlInputText input_text;
	protected HttpSession        http_session;
	protected HttpServletRequest http_request;
	
	protected PersonaBloqueadaIMO blocked_person;
	protected MembershipCheckIMO login_check;
	
	protected ResourceBundle recurso;
	protected Simulator simulator;
	
	protected Enumeration<String> lista_http_session_keys;
	protected Enumeration<String> lista_traking_id;
	
	protected Hashtable<String, HttpSession> usuario_http_session;
	protected Hashtable<String, Hashtable <String, HttpSession>> usuarios_firmados;
	
	protected SessionBean sesion;
	protected Prospectus   prospectus;
	protected ProspectusPK prospectus_PK;
	protected NaturalPerson person;
	protected Membership   membership;
	protected Promotor promotor;
	protected MembershipPK membership_PK;	
	protected Clients   cliente;
	protected ClientsPK cliente_PK;
	
	protected ProyectLoan proyectloan;
	
	protected Access access;
	protected Screen   screen;
	protected ScreenPK screen_PK;
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected Investor investor;
	protected InvestorPK investor_PK;
	
	protected List<SavingAccount> countList;
	
	protected String usr;
	protected String pwd;
	protected String partner;
	protected String temporalUser;
	protected String email;
	protected String password;
	protected String action; 
	protected String warningUser;
	protected String displayWarningUser;
	protected String nameb;
	protected String verionb;
	protected String osb;
	protected String width;
	protected String height;
	protected String user_agent;
	protected String device_info;
	protected String ipAddressClient;
	protected String version_description;
	protected String http_session_key;
	protected String tracking_id;	
	protected String mail_infusion; 
	protected String url_access;
	protected String fb_id;
	protected String fb_email;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected Boolean response_boolean;
	
	protected boolean sessionUsedB;
	protected boolean flagAccount;
	protected boolean desbloqueo_password_ENABLED;
	protected boolean kubo_person_ENABLED;
	protected boolean blocked_person_ENABLED;
	protected boolean membership_ENABLED;
	protected boolean remove_OK;
	protected boolean changeClientPass = false;
	
	protected boolean checkLogin = true;
	
	protected final long MILLSECS_PER_DAY;
	
	protected final int ESTADO_CUENTA_ENABLED = 13;
	protected final int INVERSIONES_SALDOS_MOVIMIENTOS = 15;
	protected final int INVERSIONES_ALTA_CLABE_INTERBANCARIA = 18;
	protected final int RECHAZO_AUTOMATICO = 6;
	
	public IniciaSessionDMO()
	{	
		MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;		
	}
	
	public final boolean isDesbloqueo_password_ENABLED() 
	{
		return desbloqueo_password_ENABLED;
	}

	public final boolean isBlocked_person_ENABLED() 
	{
		return blocked_person_ENABLED;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public void setService_login(LoginServiceIMO service) 
	{
		service_login = service;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setInvestorservice(InvestorService service) 
	{
		investorservice = service;
	}

	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public void setScreenservice(ScreenService service) 
	{
		screenservice = service;
	}

	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public void setProyectloanservice(ProyectLoanService service) 
	{
		proyectloanservice = service;
	}

	
	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}

	public void setClientsservice(ClientsService service) 
	{
		clientsservice = service;
	}

	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}

	public void setService_prospectus(ProspectusService service) 
	{
		service_prospectus = service;
	}

	public String getWarningUser() 
	{
		return warningUser;
	}

	public String getDisplayWarningUser() {
		return displayWarningUser;
	}

	public String getMail_infusion() {
		return mail_infusion;
	}

	public void setMail_infusion(String mail_infusion) {
		this.mail_infusion = mail_infusion;
	}

	public String getUrl_access() {
		return url_access;
	}

	public void setUrl_access(String url_access) {
		this.url_access = url_access;
	}

	public String getFb_id() {
		return fb_id;
	}

	public void setFb_id(String fb_id) {
		this.fb_id = fb_id;
	}

	public String getFb_email() {
		return fb_email;
	}

	public void setFb_email(String fb_email) {
		this.fb_email = fb_email;
	}

	public SegmentProspectusService getSegment_prospectus_service() {
		return segment_prospectus_service;
	}

	public void setSegment_prospectus_service(SegmentProspectusService segment_prospectus_service) {
		this.segment_prospectus_service = segment_prospectus_service;
	}

	public ScoringService getScoring_service() {
		return scoring_service;
	}

	public void setScoring_service(ScoringService scoring_service) {
		this.scoring_service = scoring_service;
	}

	public boolean isCheckLogin() {
		return checkLogin;
	}

	public void setCheckLogin(boolean checkLogin) {
		this.checkLogin = checkLogin;
	}
}
