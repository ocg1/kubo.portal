package mx.com.kubo.managedbeans;

import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.bean.jackson.AplicationPublicationInvestorDataDTO;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.registro.publicacion.DocumentsReviewIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MailLogService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.SystemParamService;

public abstract class AddInvestorSafiDMO 
{	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService prevencionldservice;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice; 
	
	@ManagedProperty("#{eventServiceImp}")
	protected EventService eventService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;	
	
	@ManagedProperty("#{mailLogServiceImp}")
	protected MailLogService mailService;
	
	@ManagedProperty("#{countryServiceImp}")
	protected CountryService countryService;

	@ManagedProperty("#{stateServiceImp}")
	protected StateService service_estado;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	protected ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk  service;
	
	protected FacesContext faces;
	protected ELContext elContext;
	protected ELResolver resolver;
	
	protected SessionBean sesion;
	
	protected NaturalPerson person;		
	protected Membership   membership;
	protected MembershipPK membership_PK;
	protected Investor investor;
	protected InvestorPK investor_PK;
	
	protected NotificadorIMO notificador;
	protected DocumentsReviewIMO documents;
	
	protected HubSpotController hs;
	protected AplicationPublicationInvestorDataDTO ap_INV;
	protected MenuRegBean menureq;
	protected ResourceBundle recurso;
	protected SystemParam system_param_I;
	protected SystemParamPK system_param_PK_I;
	protected Access access;
	
	protected List<AccessCollector> menuIncomplete;
	protected List<MenuRegBean> listRequiredMenu;
	protected List<SavingAccount> countList;
	protected List<ClabeAccount> accountList;
	
	
	protected StringBuilder properties;
	protected String msg;
	
	protected int prospectus_id = 0;
	
	protected final int IS_INFUSION_ENABLED = 88;
	protected final int IS_HUBSPOT_ENABLED  = 96;
	
	protected boolean success =false;
	protected boolean error =false;
	protected boolean wait =true;
	protected boolean displayAction = false;
	protected boolean successFull = false;
	protected boolean errorDisp=false;
	protected boolean flagaccount = false;	
	protected boolean notificacion_OK;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}

	public boolean isDisplayAction() {
		return displayAction;
	}

	public void setDisplayAction(boolean displayAction) {
		this.displayAction = displayAction;
	}

	public boolean isSuccessFull() {
		return successFull;
	}

	public void setSuccessFull(boolean successFull) {
		this.successFull = successFull;
	}

	public boolean isErrorDisp() {
		return errorDisp;
	}

	public void setErrorDisp(boolean errorDisp) {
		this.errorDisp = errorDisp;
	}

	public int getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public List<MenuRegBean> getListRequiredMenu() {
		return listRequiredMenu;
	}

	public void setListRequiredMenu(List<MenuRegBean> listRequiredMenu) {
		this.listRequiredMenu = listRequiredMenu;
	}

	public NaturalPerson getPersonSel() 
	{
		return person;
	}
	
	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public void setPrevencionldservice(PrevencionLDService service) 
	{
		prevencionldservice = service;
	}

	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}

	public void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}

	public void setEventService(EventService service) 
	{
		eventService = service;
	}

	public void setMembershipservice(MembershipService service) {
		membershipservice = service;
	}

	public void setInvestorservice(InvestorService service) 
	{
		investorservice = service;
	}

	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}

	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public void setMailService(MailLogService service) 
	{
		mailService = service;
	}

	public boolean isFlagaccount() {
		return flagaccount;
	}

	public void setFlagaccount(boolean flagaccount) {
		this.flagaccount = flagaccount;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public StateService getService_estado() {
		return service_estado;
	}

	public void setService_estado(StateService service_estado) {
		this.service_estado = service_estado;
	}

	public ClabeAccountService getClabeaccountservice() {
		return clabeaccountservice;
	}

	public void setClabeaccountservice(ClabeAccountService clabeaccountservice) {
		this.clabeaccountservice = clabeaccountservice;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}	
}
