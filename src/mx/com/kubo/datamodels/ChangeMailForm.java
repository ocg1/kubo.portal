package mx.com.kubo.datamodels;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.MailLogService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ServiceCallingService;

public abstract class ChangeMailForm 
{
	
	protected final Integer CELLPHONE = 6;	
  //private final Integer CASA = 1;
	
	protected String 
		email,	
		confMail,	
		warningMail,
		password,
		yourpassword,
		confPass,	
		warningConfPass,	
		ladaCelProspectus,
		phoneCellProspectus,
		zip_code,
		warningConfMail;
	
	protected boolean 
		displayPhone,	
		displayZipCode,		
		canChange,
		canShow,		
		canChangeMail = false,
		isValidMail   = false,
		showPass      = false,	
		showName      = false;	
	
	protected Membership      member;	
	protected NaturalPerson   newNaturalPerson;	
	protected Phone           phone;		
	protected Integer         rememberPass;
		
	protected NotificadorIMO notificador;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{mailLogServiceImp}")
	protected MailLogService mailService;	
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice;
	
	@ManagedProperty("#{eventServiceImp}")
	protected EventService eventService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	public boolean isChangeable(){
		return canChange && canChangeMail && isValidMail;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfMail() {
		return confMail;
	}

	public void setConfMail(String confMail) {
		this.confMail = confMail;
	}

	public String getWarningMail() {
		return warningMail;
	}

	public void setWarningMail(String warningMail) {
		this.warningMail = warningMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getYourpassword() {
		return yourpassword;
	}

	public void setYourpassword(String yourpassword) {
		System.out.println("valor password" + yourpassword);
		this.yourpassword = yourpassword;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public String getWarningConfPass() {
		return warningConfPass;
	}

	public void setWarningConfPass(String warningConfPass) {
		this.warningConfPass = warningConfPass;
	}

	public String getLadaCelProspectus() {
		return ladaCelProspectus;
	}

	public void setLadaCelProspectus(String ladaCelProspectus) {
		this.ladaCelProspectus = ladaCelProspectus;
	}

	public String getPhoneCellProspectus() {
		return phoneCellProspectus;
	}

	public void setPhoneCellProspectus(String phoneCellProspectus) {
		this.phoneCellProspectus = phoneCellProspectus;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getWarningConfMail() {
		return warningConfMail;
	}

	public void setWarningConfMail(String warningConfMail) {
		this.warningConfMail = warningConfMail;
	}

	public boolean isDisplayPhone() {
		return displayPhone;
	}

	public void setDisplayPhone(boolean displayPhone) {
		this.displayPhone = displayPhone;
	}

	public boolean isDisplayZipCode() {
		return displayZipCode;
	}

	public void setDisplayZipCode(boolean displayZipCode) {
		this.displayZipCode = displayZipCode;
	}

	public boolean isCanChange() {
		return canChange;
	}

	public void setCanChange(boolean canChange) {
		this.canChange = canChange;
	}

	public boolean isCanShow() {
		return canShow;
	}

	public void setCanShow(boolean canShow) {
		this.canShow = canShow;
	}

	public boolean isCanChangeMail() {
		return canChangeMail;
	}

	public void setCanChangeMail(boolean canChangeMail) {
		this.canChangeMail = canChangeMail;
	}

	public boolean isValidMail() {
		return isValidMail;
	}

	public void setValidMail(boolean isValidMail) {
		this.isValidMail = isValidMail;
	}

	public boolean isShowPass() {
		return showPass;
	}

	public void setShowPass(boolean showPass) {
		this.showPass = showPass;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public NaturalPerson getNewNaturalPerson() {
		return newNaturalPerson;
	}

	public void setNewNaturalPerson(NaturalPerson newNaturalPerson) {
		this.newNaturalPerson = newNaturalPerson;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Integer getRememberPass() {
		return rememberPass;
	}

	public void setRememberPass(Integer rememberPass) {
		this.rememberPass = rememberPass;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public PasswordHistoryService getPasswordhistoryservice() {
		return passwordhistoryservice;
	}

	public void setPasswordhistoryservice(
			PasswordHistoryService passwordhistoryservice) {
		this.passwordhistoryservice = passwordhistoryservice;
	}

	public MailLogService getMailService() {
		return mailService;
	}

	public void setMailService(MailLogService mailService) {
		this.mailService = mailService;
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

	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}

	public void setServicecallingService(ServiceCallingService servicecallingService) {
		this.servicecallingService = servicecallingService;
	}
	
}
