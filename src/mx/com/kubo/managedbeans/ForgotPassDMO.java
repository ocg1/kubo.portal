package mx.com.kubo.managedbeans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestion;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TempPassword;
import mx.com.kubo.model.TempPasswordPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProspectusExtraService;
import mx.com.kubo.services.SecurityQuestionPoolService;
import mx.com.kubo.services.SecurityQuestionService;
import mx.com.kubo.services.TempPasswordService;
import mx.com.kubo.services.impl.SystemParamServiceImp;

public abstract class ForgotPassDMO 
implements ForgotPassIMO
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{tempPasswordServiceImp}")
	protected TempPasswordService tempPassService;
	
	@ManagedProperty("#{securityQuestionPoolServiceImp}")
	protected SecurityQuestionPoolService secquestpoolservice;
	
	@ManagedProperty("#{securityQuestionServiceImp}")
	protected SecurityQuestionService securityQService;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
		
	protected NotificadorIMO notificador;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 
	
	@ManagedProperty("#{systemParamServiceImp}")	
	protected SystemParamServiceImp system_param_service;
	
	@ManagedProperty("#{prospectusExtraServiceImp}")
	protected ProspectusExtraService prospectusextraservice;
	
	protected FacesContext       faces;
	protected ELContext          context;
	protected ExternalContext    external;
	protected HttpServletRequest request;
	protected ELResolver         resolver;
	protected HttpServletRequest servlet_request;
	
	protected SessionBean       sesion;
	protected HeaderBean        header_sesion;
	protected Membership        membership;
	protected NaturalPerson     natural_person;
	protected gnNaturalPersonPK natural_person_PK;
		
	protected Access            access;
	protected Change_control    changeCtrl;
	protected Change_controlPK  changeCtrlPK;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected TempPassword         tempPassword;
	protected TempPasswordPK       tempPassPK;
	protected SecurityQuestionPool security_question_pool;
	protected SecQuestPoolPK       security_question_pool_PK;	
	protected SystemParamPK        system_param_PK;
	
	protected List<SecurityQuestion> securityQList;
	protected List<SecurityQuestionPool> lista_security_question_pool;
	
	protected SimpleDateFormat date_format;
	protected Date     fechaActual;
	protected Calendar valid_date;
	
	protected String passtemp;
	protected String passTemp;
	protected String path;
	protected String url;
	protected String newPass;
	protected String newPassConf;
	protected String security_question;
	protected String answerQuestion;
	protected String answer;
	protected String answer2;
	protected String answer3;
	protected String options;
	protected String faces_action;
	protected String email;
	protected String errorMsg;	
	protected String name;	
	protected String browser_name;
	protected String browser_version;
	protected String browser_OS;
	protected String browser_width;
	protected String browser_height;
	protected String user_agent;
	protected String device_info;		
	protected String ipAddressClient;
	protected String security_answer_upper;
	protected String security_answer_lower;
	protected String system_param;
	protected String ipAddress;
	protected String passNewEncript;
	
	protected String [] lista_receptores;
	
	protected final String CONFIRMACION_CAMBIO_PASSWORD = "34";	

	protected Integer seqQuest;
	protected Integer security_question_id;
		
	protected int prospectus_id;
	protected int company_id;
	protected int tipo_reestalecer_password_SELECTED;
	protected int failed_question_attempts;
	protected int max_number_attempts;
	
	protected final static int RESPONDER_PREGUNTA_SEGURIDAD = 1;
	protected final static int ENVIAR_PASSWORD_TEMPORAL     = 2;	
	protected final static int SCREEN_ID_FORGOT_PASS = 34;
	protected final static int PARAM_ATTEMPTS        = 64;
		
	protected boolean validSession    = false;	
	protected boolean validMail       = false;
	protected boolean changePass      = false;
	protected boolean recoveryByEmail = false;
	protected boolean displayQuestion; 	
	protected boolean displayButtonSave;
	protected boolean panel_opciones_ENABLED;
	protected boolean is_email_OK;
	protected boolean is_membership_active;
	protected boolean is_membership_not_active;
	protected boolean is_change_control_OK;
	protected boolean is_security_answer_OK;
	protected boolean is_cambio_password_OK;
	protected boolean max_number_attempts_reached;
	protected boolean is_success;
			
	private String preg2;
	private String res2;
	private String preg3;
	private String res3;
	protected String mensajeError;

	private Integer seqQuest2;
	private Integer seqQuest3;				
	
	protected ForgotPassDMO()
	{
		date_format =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		
		panel_opciones_ENABLED = false;
	}
	
	protected void init_max_number_attempts() 
	{
		system_param_PK = new SystemParamPK(PARAM_ATTEMPTS, 1);
		
		system_param = system_param_service.loadSelectedSystemParam(system_param_PK).getValue();
		
		max_number_attempts = Integer.parseInt(system_param);
		
		max_number_attempts_reached = false;
	}

	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setChangeControlService(Change_controlService service) 
	{
		changeControlService = service;
	}
	
	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}	
	
	public void setTempPassService(TempPasswordService service) 
	{
		tempPassService = service;
	}
	
	public void setSecquestpoolservice(SecurityQuestionPoolService service) 
	{
		secquestpoolservice = service;
	}
	
	public void setSecurityQService(SecurityQuestionService service) 
	{
		securityQService = service;
	}

	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}
	
	public void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public void setSystem_param_service(SystemParamServiceImp service) 
	{
		system_param_service = service;
	}
	
	public String getFaces_action() {
		return faces_action;
	}

	public void setFaces_action(String faces_action) {
		this.faces_action = faces_action;
	}
	
	public boolean isIs_email_OK() {
		return is_email_OK;
	}

	public void setIs_email_OK(boolean is_email_OK) {
		this.is_email_OK = is_email_OK;
	}

	public static int getEnviarPasswordTemporal() {
		return ENVIAR_PASSWORD_TEMPORAL;
	}
			
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public final void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getSecurityQuestion() {
		return security_question;
	}
	
	public void setSecurityQuestion(String securityQuestion) {
		this.security_question = securityQuestion;
	}
	
	public String getAnswerQuestion() {
		return answerQuestion;
	}
	
	public void setAnswerQuestion(String answerQuestion) {
		this.answerQuestion = answerQuestion;
	}
	
	public final boolean isChangePass() 
	{
		return changePass;
	}
	
	public final boolean isPanel_opciones_ENABLED() 
	{
		return panel_opciones_ENABLED;
	}
	
	public final boolean isDisplayButtonSave() 
	{
		return displayButtonSave;
	}
	
	public boolean isValidMail() 
	{
		return validMail;
	}
	
	public String getNewPass() {
		return newPass;
	}
	
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public String getNewPassConf() 
	{
		return newPassConf;
	}
	
	public void setNewPassConf(String newPassConf) {
		this.newPassConf = newPassConf;
	}
	
	public final String getOptions() 
	{
		return options;
	}
	
	public final void setOptions(String tipo_reestalecer_password_SELECTED) 
	{
		options = tipo_reestalecer_password_SELECTED;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public Membership getMembership() {
		return membership;
	}
	public boolean isValidSession() {
		return validSession;
	}
	
	public void setValidSession(boolean validSession) {
		this.validSession = validSession;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
/*
	public boolean isRecoveryByEmail() {
		return recoveryByEmail;
	}
*/	
	
	public TempPassword getTempPassword() {
		return tempPassword;
	}
	
	public void setTempPassword(TempPassword tempPassword) {
		this.tempPassword = tempPassword;
	}
	
	public final boolean isDisplayQuestion() 
	{
		return displayQuestion;
	}

	public String getPreg2() {
		return preg2;
	}

	public void setPreg2(String preg2) {
		this.preg2 = preg2;
	}

	public String getRes2() {
		return res2;
	}

	public void setRes2(String res2) {
		this.res2 = res2;
	}

	public String getPreg3() {
		return preg3;
	}

	public void setPreg3(String preg3) {
		this.preg3 = preg3;
	}

	public String getRes3() {
		return res3;
	}

	public void setRes3(String res3) {
		this.res3 = res3;
	}

	public List<SecurityQuestion> getSecurityQList() {
		return securityQList;
	}

	public void setSecurityQList(List<SecurityQuestion> securityQList) {
		this.securityQList = securityQList;
	}

	public Integer getSeqQuest() {
		return seqQuest;
	}

	public void setSeqQuest(Integer seqQuest) {
		this.seqQuest = seqQuest;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getSeqQuest2() {
		return seqQuest2;
	}

	public void setSeqQuest2(Integer seqQuest2) {
		this.seqQuest2 = seqQuest2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public Integer getSeqQuest3() {
		return seqQuest3;
	}

	public void setSeqQuest3(Integer seqQuest3) {
		this.seqQuest3 = seqQuest3;
	}

	public String getAnswer3() {
		return answer3;
	}
	
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	
	public final boolean isMax_number_attempts_reached() 
	{
		return max_number_attempts_reached;
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

	public ProspectusExtraService getProspectusextraservice() {
		return prospectusextraservice;
	}

	public void setProspectusextraservice(ProspectusExtraService prospectusextraservice) {
		this.prospectusextraservice = prospectusextraservice;
	}
}
