package mx.com.kubo.managedbeans.preregistro;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.SecurityQuestion;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectusExtraService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ReferredService;
import mx.com.kubo.services.RegistrationReasonService;
import mx.com.kubo.services.SecurityQuestionService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

public abstract class PreregistroDMO 
implements PreregistroIMO
{						
	@ManagedProperty("#{clientViewFullNameServiceImp}")
	protected ClientViewFullNameService clientViewFullNameService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{securityQuestionServiceImp}")
	protected SecurityQuestionService securityQService;
	
	@ManagedProperty("#{registrationReasonServiceImp}")
	protected RegistrationReasonService reasonsService;
	
	@ManagedProperty("#{loginServiceImp}")
	protected LoginServiceIMO loginService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService service_system_param;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;	
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{referredServiceImp}")
	protected ReferredService referredservice;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService service_employment;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService service_address;
	
	@ManagedProperty("#{prospectusExtraServiceImp}")
	protected ProspectusExtraService prospectusextraservice;
	
	protected FacesContext faces;
	protected FacesMessage faces_msg;
	protected ELContext       context;
	protected ELResolver      resolver;
	protected RequestContext  request;
	protected ExternalContext external;
	protected Map<String, String> request_map;
	
	protected HtmlInputText ajax_input_text;
	
	protected Logger log;	
	protected ResourceBundle recurso;
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected SessionBean session;
	protected HeaderBean  header;
	protected InicioValuesIMP inicioValues;
	protected  NotificadorIMO notificador;
		
	protected NaturalPerson natural_person;		
	protected Prospectus   prospectus;
	protected ProspectusPK prospectus_PK;
	protected Membership   membership;
	protected MembershipPK membership_PK;
	protected Investor   investor;	
	protected InvestorPK investor_PK;

	protected PasswordHistory passHis;
	protected PasswordHistoryPK pssPk;
	protected SecurityQuestion securityQ;
	protected NavigationRule navigationRule;
	protected Phone newPhone;
	protected PhonePK phonePK;
	protected Address   address;
	protected AddressPK address_PK;
	protected NeighborhoodCat colonia;
	protected Pattern reg_exp;
	protected Matcher matcher;
	
	protected List<NaturalPerson>      personList;
	protected List<SecurityQuestion>   lista_security_question;
	protected List<RegistrationReason> lista_registration_reason;
	protected List<NeighborhoodCat>    lista_colonias;;
	
	protected String first_name;
	protected String middle_name;
	protected String father_last_name;
	protected String mother_last_name;		
	//protected String sm_lada_phone_number;		
	protected String sm_phone_number;		
	protected String sm_email;		
	protected String sm_id;		
	protected String partner;
	protected String partner_id; 
	protected String utm_partner_id;	
	protected String nickname;
	protected String email;
	protected String password;
	protected String answer;
	protected String contract;
	protected String codeID;
	protected String codigo_postal;
	protected String who_recommends;
	protected String priceshoes_number;
	protected String utm_medium;
	protected String medium;
	protected String utm_campaign;
	
	protected String fb_id;
	protected String fb_email;
	
//	protected String ladaCelProspectus;
	protected String phoneCellProspectus;
	protected String other_registration_reason;		
	protected String confPass;
	protected String confMail;		
	protected String forwardMail;
	protected String forwardMailChng;
	protected String imgValMail;
	protected String imgValPass;
	protected String imgValConfMail;
	protected String imgValAlias;
	protected String warningMail;
	protected String warningAlias;
	protected String warningConfMail;
	protected String warningConfPass;	
	protected String warningName;	
	protected String warningUser;
	protected String warningPromotorOn         = "";
	protected String warningPromotorOff        = "";
	protected String isActiveInvestmentSection = "";	
	protected String subscribeVal	          = "N";
	protected String displayValMail            = "none";	
	protected String displayValPass            = "none";
	protected String displayValConfMail        = "none";
	protected String displayValAlias           = "none";
	protected String displayWarningUser        = "none";
	protected String displayWarningMail 	    = "none";		
	protected String displayWarningAlias       = "none";
	protected String displayWarningConfMail    = "none";
	protected String displayWarningConfPass    = "none";
	protected String displayWarningPromotor    = "none";
	protected String displayWarningName        = "none";
	protected String publicKey;
	protected String privateKey;
	protected String refered_search  = "";
	protected String activation_code;
	protected String tracking_id;
	protected String estado_ENABLED;
	protected String town_name;
	protected String state_name;
	protected String user_agent;
	protected String device_info;
	protected String version_description;
	protected String promotorID;
	protected String havePromotor = null;
	

	protected List<String> selectedSuscribe;
		
	protected boolean pre_registro_OK;
	protected boolean exitoGuardarProspecto;
	protected boolean flagActiveCode;
	protected boolean displayOtherReason;
	protected boolean disabledMail;
	protected boolean fbview;
	protected boolean flagActive           = false;
	protected boolean disabledRegistration = false;
	protected boolean promotorDisp 		 = false;
	protected boolean notificar_evento_OK;
	protected boolean zona_cobertura_ENABLED;
	protected boolean codigo_postal_OK;
	protected boolean save_address_OK;
	protected boolean email_OK;
	protected boolean pide_contrasena_segura = false;
	
	protected Character area;
	
	protected Integer registration_reason_id;
	protected Integer promotor_id;
	protected Integer security_question;	
	
	protected int prospectus_id;
	protected int company_id;
	protected int address_id;
	protected int town_id;
	protected int state_id;
	protected int colonia_id;
	
	protected final int RECOMENDACION_CLIENTE_KUBO = 3;
	protected final int RECOMENDACION = 4;
	
	protected final Integer ACTIVO;
	protected final int COMPANY_ID;
	protected final int CASA;
	protected final int MEXICO;
	protected final int SECCION_INVERSIONISTA_ENABLED;
	protected final int PEDIR_CONTRASENA_SEGURA = 101;
	protected final int MEMBER_NOT_ACTIVE ;
	protected final int MEMBER_ACTIVE;
	protected final int MEMBER_NEW;
	protected final int EMAIL_ERROR;
		
	protected PreregistroDMO()
	{
		securityQ = new SecurityQuestion();
		
		log = Logger.getLogger(getClass());
		
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		publicKey  = "6LeKfdkSAAAAAEyZJeEkqgc4kq29OPTdccETQYvi";
		privateKey = "6LeKfdkSAAAAACxCCOvMleA-ejJY7pTqQNdbWfLR";
		
		MEMBER_NOT_ACTIVE = 0;
		MEMBER_ACTIVE     = 1;
		MEMBER_NEW        = 2;
		EMAIL_ERROR       = 3;
		
		CASA       = 1;
		COMPANY_ID = 1;
		ACTIVO      = 1;
		
		SECCION_INVERSIONISTA_ENABLED = 51;
		
		MEXICO = 700;
	}

	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}
	
	public void setProspectusService(ProspectusService service)
	{
		prospectusService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setSecurityQService(SecurityQuestionService service) 
	{
		securityQService = service;
	}
	
	public void setReasonsService(RegistrationReasonService service) {
		reasonsService = service;
	}

	public void setLoginService(LoginServiceIMO service) 
	{
		loginService = service;
	}
	
	public void setService_system_param(SystemParamService service) 
	{
		service_system_param = service;
	}

	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}
	
	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}
	
	public void setPhoneService(PhoneService service) 
	{
		phoneService = service;
	}

	public void setReferredservice(ReferredService service) 
	{
		referredservice = service;
	}

	public void setClientViewFullNameService(ClientViewFullNameService service) 
	{
		clientViewFullNameService = service;
	}

	public void setInvestorservice(InvestorService service) 
	{
		investorservice = service;
	}
	
	public final void setService_employment(EmploymentService service) 
	{
		service_employment = service;
	}
	
	public final void setService_address(AddressService service) 
	{
		service_address = service;
	}

	public NaturalPerson getNewNaturalPerson() 
	{
		return natural_person;
	}
	
	public String getCompleteName()
	{		
		return natural_person.getFirst_name()       + " "  + 
				natural_person.getMiddle_name()      + " " +
				natural_person.getFather_last_name() + " " + 
				natural_person.getMother_last_name();
	}
	
	public SecurityQuestion getSecurityQ() {
		return securityQ;
	}
	
	public void setSecurityQ(SecurityQuestion securityQ) {
		this.securityQ = securityQ;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
/*		
	public void setDisplayValConfMail(String displayValConfMail) 
	{
		this.displayValConfMail = displayValConfMail;
	}
	
	public void setImgValConfMail(String imgValConfMail) 
	{
		this.imgValConfMail = imgValConfMail;
	}
	
	public void setDisplayWarningConfMail(String displayWarningConfMail) 
	{
		this.displayWarningConfMail = displayWarningConfMail;
	}
	
	public void setWarningConfMail(String warningConfMail) 
	{
		this.warningConfMail = warningConfMail;
	}	
		
	public void setDisplayValMail(String displayValMail) 
	{
		this.displayValMail = displayValMail;
	}
	
	public void setImgValMail(String imgValMail) 
	{
		this.imgValMail = imgValMail;
	}	
	
	public void setDisplayWarningMail(String displayWarningMail) 
	{
		this.displayWarningMail = displayWarningMail;
	}
	
	public void setWarningMail(String warningMail) 
	{
		this.warningMail = warningMail;
	}
*/	
	
	public String getUtm_partner_id() {
		return utm_partner_id;
	}
	public void setUtm_partner_id(String utm_partner_id) {
		this.utm_partner_id = utm_partner_id;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public String getConfPass() 
	{
		return confPass;
	}
	
	public String getConfMail() 
	{
		return confMail;
	}
		
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	
	public final String getCodigo_postal() 
	{
		return codigo_postal;
	}

	public final void setCodigo_postal(String codigo_postal) 
	{
		this.codigo_postal = codigo_postal;
	}

	public String getWho_recommends() {
		return who_recommends;
	}
	public void setWho_recommends(String who_recommends) {
		this.who_recommends = who_recommends;
	}
	public String getPriceshoes_number() {
		return priceshoes_number;
	}
	public void setPriceshoes_number(String priceshoes_number) {
		this.priceshoes_number = priceshoes_number;
	}
//	public String getLadaCelProspectus() {
//		return ladaCelProspectus;
//	}
//	public void setLadaCelProspectus(String ladaCelProspectus) {
//		this.ladaCelProspectus = ladaCelProspectus;
//	}
	public String getPhoneCellProspectus() {
		return phoneCellProspectus;
	}
	public void setPhoneCellProspectus(String phoneCellProspectus) {
		this.phoneCellProspectus = phoneCellProspectus;
	}
	public String getOther_registration_reason() {
		return other_registration_reason;
	}
	public void setOther_registration_reason(String other_registration_reason) {
		this.other_registration_reason = other_registration_reason;
	}
		
	public void setConfPass(String confPass) 
	{
		this.confPass = confPass;
	}
		
	public void setConfMail(String confMail) 
	{
		this.confMail = confMail;
	}
	
	public String getForwardMail() {
		return forwardMail;
	}
	
	public void setForwardMail(String forwardMail) {
		this.forwardMail = forwardMail;
	}
	public String getImgValMail() {
		return imgValMail;
	}

	public String getImgValPass() {
		return imgValPass;
	}
	public void setImgValPass(String imgValPass) {
		this.imgValPass = imgValPass;
	}
	public String getImgValConfMail() {
		return imgValConfMail;
	}

	public String getImgValAlias() {
		return imgValAlias;
	}
	public void setImgValAlias(String imgValAlias) {
		this.imgValAlias = imgValAlias;
	}
	public String getWarningMail() {
		return warningMail;
	}

	public String getWarningAlias() {
		return warningAlias;
	}
	public void setWarningAlias(String warningAlias) {
		this.warningAlias = warningAlias;
	}
	public String getWarningConfMail() {
		return warningConfMail;
	}

	public String getWarningConfPass() {
		return warningConfPass;
	}
	public void setWarningConfPass(String warningConfPass) {
		this.warningConfPass = warningConfPass;
	}
	public String getWarningName() {
		return warningName;
	}
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
	public String getWarningUser() {
		return warningUser;
	}
	public void setWarningUser(String warningUser) {
		this.warningUser = warningUser;
	}
	public String getWarningPromotorOn() {
		return warningPromotorOn;
	}
	public void setWarningPromotorOn(String warningPromotorOn) {
		this.warningPromotorOn = warningPromotorOn;
	}
	public String getWarningPromotorOff() {
		return warningPromotorOff;
	}
	public void setWarningPromotorOff(String warningPromotorOff) {
		this.warningPromotorOff = warningPromotorOff;
	}
	public String getIsActiveInvestmentSection() {
		return isActiveInvestmentSection;
	}
	public void setIsActiveInvestmentSection(String isActiveInvestmentSection) {
		this.isActiveInvestmentSection = isActiveInvestmentSection;
	}
	
	public String getSubscribeVal() 
	{
		return subscribeVal;
	}
	
	public void setSubscribeVal(String subscribeVal) 
	{
		this.subscribeVal = subscribeVal;
	}
	
	public String getDisplayValMail() {
		return displayValMail;
	}

	public String getDisplayValPass() {
		return displayValPass;
	}
	public void setDisplayValPass(String displayValPass) {
		this.displayValPass = displayValPass;
	}
	public String getDisplayValConfMail() {
		return displayValConfMail;
	}

	public String getDisplayValAlias() {
		return displayValAlias;
	}
	public void setDisplayValAlias(String displayValAlias) {
		this.displayValAlias = displayValAlias;
	}
	public String getDisplayWarningUser() {
		return displayWarningUser;
	}
	public void setDisplayWarningUser(String displayWarningUser) {
		this.displayWarningUser = displayWarningUser;
	}
	public String getDisplayWarningMail() {
		return displayWarningMail;
	}

	public String getDisplayWarningAlias() {
		return displayWarningAlias;
	}
	public void setDisplayWarningAlias(String displayWarningAlias) {
		this.displayWarningAlias = displayWarningAlias;
	}
	public String getDisplayWarningConfMail() {
		return displayWarningConfMail;
	}

	public String getDisplayWarningConfPass() {
		return displayWarningConfPass;
	}
	public void setDisplayWarningConfPass(String displayWarningConfPass) {
		this.displayWarningConfPass = displayWarningConfPass;
	}
	public String getDisplayWarningPromotor() {
		return displayWarningPromotor;
	}
	public void setDisplayWarningPromotor(String displayWarningPromotor) {
		this.displayWarningPromotor = displayWarningPromotor;
	}
	public String getDisplayWarningName() {
		return displayWarningName;
	}
	public void setDisplayWarningName(String displayWarningName) {
		this.displayWarningName = displayWarningName;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public boolean isDisplayOtherReason() {
		return displayOtherReason;
	}
	public void setDisplayOtherReason(boolean displayOtherReason) {
		this.displayOtherReason = displayOtherReason;
	}
	public boolean isDisabledMail() {
		return disabledMail;
	}
	public void setDisabledMail(boolean disabledMail) {
		this.disabledMail = disabledMail;
	}
	public boolean isFbview() {
		return fbview;
	}
	public void setFbview(boolean fbview) {
		this.fbview = fbview;
	}
	public boolean isFlagActive() {
		return flagActive;
	}
	public void setFlagActive(boolean flagActive) {
		this.flagActive = flagActive;
	}
	public boolean isDisabledRegistration() {
		return disabledRegistration;
	}
	public void setDisabledRegistration(boolean disabledRegistration) {
		this.disabledRegistration = disabledRegistration;
	}
	
	public Character getArea() 
	{
		return area;
	}
	
	public void setArea(Character area) 
	{
		this.area = area;
	}
	
	public Integer getRegistration_reason_id() {
		return registration_reason_id;
	}
	public void setRegistration_reason_id(Integer registration_reason_id) {
		this.registration_reason_id = registration_reason_id;
	}
	
	public Integer getPromotor_id() 
	{
		return promotor_id;
	}
	
	public void setPromotor_id(Integer promotor_id) 
	{
		this.promotor_id = promotor_id;
	}
	
	public Integer getSecurity_question() {
		return security_question;
	}
	public void setSecurity_question(Integer security_question) {
		this.security_question = security_question;
	}
	public List<String> getSelectedSuscribe() {
		return selectedSuscribe;
	}
	public void setSelectedSuscribe(List<String> selectedSuscribe) {
		this.selectedSuscribe = selectedSuscribe;
	}
	public List<NaturalPerson> getPersonList() {
		return personList;
	}
	public void setPersonList(List<NaturalPerson> personList) {
		this.personList = personList;
	}
	
	public List<SecurityQuestion> getSecurityQList() 
	{
		return lista_security_question;
	}

	public List<RegistrationReason> getReasonsLst() 
	{
		return lista_registration_reason;
	}
	
	public String getForwardMailChng() {
		return forwardMailChng;
	}
	public void setForwardMailChng(String forwardMailChng) {
		this.forwardMailChng = forwardMailChng;
	}	
	
	public Logger getLog() 
	{
		return log;
	}
	
	public void setLog(Logger log) 
	{
		this.log = log;
	}

	public boolean isPromotorDisp() {
		return promotorDisp;
	}

	public void setPromotorDisp(boolean promotorDisp) {
		this.promotorDisp = promotorDisp;
	}
	
	public String getRefered_search() {
		return refered_search;
	}

	public void setRefered_search(String refered_search) {
		this.refered_search = refered_search;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getHavePromotor() {
		return havePromotor;
	}

	public void setHavePromotor(String havePromotor) {
		this.havePromotor = havePromotor;
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

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getUtm_campaign() {
		return utm_campaign;
	}

	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}

	public ProspectusExtraService getProspectusextraservice() {
		return prospectusextraservice;
	}

	public void setProspectusextraservice(ProspectusExtraService prospectusextraservice) {
		this.prospectusextraservice = prospectusextraservice;
	}

	public boolean isPide_contrasena_segura() {
		return pide_contrasena_segura;
	}

	public void setPide_contrasena_segura(boolean pide_contrasena_segura) {
		this.pide_contrasena_segura = pide_contrasena_segura;
	}
	
	
}
