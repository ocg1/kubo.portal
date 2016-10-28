package mx.com.kubo.managedbeans.perfil;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestion;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.NotificationPreference;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FullNameService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SecurityQuestionPoolService;
import mx.com.kubo.services.SecurityQuestionService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.services.impl.FullNameServiceImp;

import org.primefaces.context.RequestContext;

public abstract class PerfilControllerDMO 
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;	
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;	
	
	@ManagedProperty("#{loginServiceImp}")
	protected LoginServiceIMO loginService;	
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{notificadorImp}")
	protected NotificadorIMP notificador;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{securityQuestionPoolServiceImp}")
	protected SecurityQuestionPoolService service_pool_preguntas;
	
	@ManagedProperty("#{securityQuestionServiceImp}")
	protected SecurityQuestionService service_preguntas;
	
	@ManagedProperty("#{service_catalogos}")
	protected ServiceCatalogosIMO service_catalogos;
	
	@ManagedProperty("#{fullNameServiceImp}")
	protected FullNameService fullnameservice;
	
	protected FacesContext   faces;
	protected ELContext      context;
	protected ELResolver     resolver;
	protected RequestContext request;
	
	protected HtmlInputText     input_text;
	protected HtmlSelectOneMenu select_menu;
	
	protected SessionBean sesion;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected NaturalPerson person;
	protected NaturalPerson prevPerson;
	protected gnNaturalPersonPK natural_person_PK;
	
	protected EditorCorreoIMO correo;
	
	protected HeaderBean header;
	protected Access access;
	
	protected Change_control   change_control;
	protected Change_controlPK change_controlPK;
	
	protected SecurityQuestionPool question_pool;
	protected SecQuestPoolPK       question_pool_PK;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected List<SavingAccount> countList;
	protected List<SecurityQuestion>  catalogo_preguntas_seguridad;
	protected List<SecurityQuestionPool>  pool_preguntas_seguridad;
	protected List<NotificationPreference> lista_notification_preference;
	
	protected String safi_client_id;
	protected String area;
	protected String respuesta;
	protected String answer_1;
	protected String answer_2;
	protected String answer_3;
	protected String msg="";
	protected String completeName;
	protected String newPass;
	protected String newPassConf;
	protected String prevCompletName;
	protected String pantalla_a_mostrar;
	protected String comments;
	protected String ip_address;
	
	private String newemail;
	private String pass;	
	
	protected final String PANEL_EDICION;
	protected final String PANEL_SELECCION;
	protected final String CONFIRMACION_CAMBIO_PASSWORD = "34";
	
	protected String [] lista_receptores;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer preference_id_ORIGINAL;
	protected Integer preference_id_NEW;
	
	protected int pregunta_selected;
	protected int question_id;
	protected int question_id_1;
	protected int question_id_2;
	protected int question_id_3;	
	
	protected final static int SCREEN_ID_PERFIL = 36;
	
	protected final int WS_CALL_INIT = 0;
	protected final int WS_CALL_OK   = 1;
		
	protected boolean hasScoring;
	protected boolean is_saved_OK;
	protected boolean is_answer_OK;
	protected boolean change_control_OK;	
	protected boolean cambio_pregunta_OK;
	
	protected PerfilControllerDMO()
	{
		PANEL_EDICION   = "secciones/perfil/preguntas_seguridad/panel_edicion.xhtml";
		PANEL_SELECCION = "secciones/perfil/addSecurityQuest.xhtml";
		
		comments = "El usuario cambio el medio de notificación";
	}
	
	public void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setLoginService(LoginServiceIMO service) 
	{
		loginService = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public void setNotificador(NotificadorIMP service) 
	{
		notificador = service;
	}

	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}

	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}
	
	public final void setService_pool_preguntas(SecurityQuestionPoolService service) 
	{
		service_pool_preguntas = service;
	}

	public final void setService_preguntas(SecurityQuestionService service) 
	{
		service_preguntas = service;
	}
	
	public final void setService_catalogos(ServiceCatalogosIMO service) 
	{
		service_catalogos = service;
	}

	public EditorCorreoIMO getCorreo() 
	{
		return correo;
	}

	public final List<NotificationPreference> getLista_notification_preference() 
	{
		return lista_notification_preference;
	}

	public final List<SecurityQuestionPool> getPool_preguntas_seguridad() 
	{
		return pool_preguntas_seguridad;
	}

	public final List<SecurityQuestion> getCatalogo_preguntas_seguridad() 
	{
		return catalogo_preguntas_seguridad;
	}

	public final Integer getNotification_preference_id() 
	{
		return preference_id_ORIGINAL;
	}

	public String getCompleteName() {
		return completeName;
	}
	
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	
	public String getNewemail() {
		return newemail;
	}
	
	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	
	public NaturalPerson getPerson() {
		return person;
	}
	
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	
	public Membership getMembership() {
		return membership;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}	
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getNewPass() {
		return newPass;
	}
	
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}	

	public String getNewPassConf() {
		return newPassConf;
	}
	
	public void setNewPassConf(String newPassConf) {
		this.newPassConf = newPassConf;
	}

	public boolean isHasScoring() {
		return hasScoring;
	}
	
	public void setHasScoring(boolean hasScoring) {
		this.hasScoring = hasScoring;
	}
	
	public NaturalPerson getPrevPerson() {
		return prevPerson;
	}

	public void setPrevPerson(NaturalPerson prevPerson) {
		this.prevPerson = prevPerson;
	}
	
	public String getPrevCompletName() {
		return prevCompletName;
	}
	
	public void setPrevCompletName(String prevCompletName) {
		this.prevCompletName = prevCompletName;
	}

	public String getPantalla_a_mostrar() {
		return pantalla_a_mostrar;
	}

	public void setPantalla_a_mostrar(String pantalla_a_mostrar) {
		this.pantalla_a_mostrar = pantalla_a_mostrar;
	}

	public FullNameService getFullnameservice() {
		return fullnameservice;
	}

	public void setFullnameservice(FullNameService fullnameservice) {
		this.fullnameservice = fullnameservice;
	}
}
