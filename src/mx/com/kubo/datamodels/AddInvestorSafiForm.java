package mx.com.kubo.datamodels;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
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

public class AddInvestorSafiForm 
{
	protected String msg;
	protected boolean success =false;
	protected boolean error =false;
	protected boolean wait =true;
	protected boolean displayAction = false;
	protected boolean successFull = false;
	protected boolean errorDisp=false;
	protected int prospectus_id = 0;
	protected boolean flagaccount = false;
	
	protected List<MenuRegBean> listRequiredMenu;
	
	protected NaturalPerson personSel;
	
	@ManagedProperty("#{notificadorImp}")
	protected NotificadorIMP notificador;
	
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

	public NaturalPerson getPersonSel() {
		return personSel;
	}

	public void setPersonSel(NaturalPerson personSel) {
		this.personSel = personSel;
	}
	
	public NotificadorIMP getNotificador() {
		return notificador;
	}

	public void setNotificador(NotificadorIMP notificador) {
		this.notificador = notificador;
	}

	public SavingAccountService getSavingaccountservice() {
		return savingaccountservice;
	}

	public void setSavingaccountservice(SavingAccountService savingaccountservice) {
		this.savingaccountservice = savingaccountservice;
	}

	public PrevencionLDService getPrevencionldservice() {
		return prevencionldservice;
	}

	public void setPrevencionldservice(PrevencionLDService prevencionldservice) {
		this.prevencionldservice = prevencionldservice;
	}

	public NaturalPersonService getNaturalPersonService() {
		return naturalPersonService;
	}

	public void setNaturalPersonService(NaturalPersonService naturalPersonService) {
		this.naturalPersonService = naturalPersonService;
	}

	public EventNotificationService getEventnotificationservice() {
		return eventnotificationservice;
	}

	public void setEventnotificationservice(
			EventNotificationService eventnotificationservice) {
		this.eventnotificationservice = eventnotificationservice;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public MembershipService getMembershipservice() {
		return membershipservice;
	}

	public void setMembershipservice(MembershipService membershipservice) {
		this.membershipservice = membershipservice;
	}

	public InvestorService getInvestorservice() {
		return investorservice;
	}

	public void setInvestorservice(InvestorService investorservice) {
		this.investorservice = investorservice;
	}

	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}

	public void setServicecallingService(ServiceCallingService servicecallingService) {
		this.servicecallingService = servicecallingService;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public MailLogService getMailService() {
		return mailService;
	}

	public void setMailService(MailLogService mailService) {
		this.mailService = mailService;
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
