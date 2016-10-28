package mx.com.kubo.managedbeans.mesa;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.bean.ShowChangeSession;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.CreditHistoryAttemptService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;

public abstract class ActivityPersonDMO 
implements ActivityPersonIMO
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService telefonoService;
	
	@ManagedProperty("#{creditHistoryAttemptServiceImp}")
	protected CreditHistoryAttemptService service_historial_consultas;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenService;
	
	@ManagedProperty("#{notificadorImp}")
	protected NotificadorIMO notificador;	
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
					
	protected FacesContext faces;
	protected ELResolver resolver;
	protected ExternalContext external;
	protected ELContext context;
	
	protected SessionBean  sesion;
	protected ShowChangeSession showchange;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected RoleFunctionController role_function;
	protected ResourceBundle recurso;
	protected MenuRegBean menu;
	
	protected Scoring  score;
	protected CreditHistoryAttempt   ultima_consulta;
	//protected CreditHistoryAttemptPK ultima_consulta_PK;
	
	protected Access access;
	protected ChangeBean change_control_bean;
	protected Screen   screen;
	protected ScreenPK screen_PK;	
	
	protected SimpleDateFormat frm;	
	protected DecimalFormat    decimal_format;	
	protected Calendar pospuesta;
	protected Calendar today;
	
	protected List<Phone>                listPhone;
	protected List<CreditHistoryAttempt> attemps;
	protected List<MenuRegBean>          menus;
	protected List<AccessCollector>      lista_menu_access;
	protected List<RoleFunction>         lista_funciones;
	protected List<Change_control>   bitacora_change_control;

	protected String registrationReason;
	protected String paginaActual;
	protected String fechaConsulta;
	protected String activationDateStr;
	protected String clsActv;
	protected String fecUltimoAccesoStr;
	protected String ultimaPantallaStr;
	protected String datosInit;	
	protected String prospectusStr;
	protected String companyStr;
	protected String resource_name;
	protected String target_item;
	protected String obligatorio;
	protected String score_A;
	protected String nuevo_password;
	protected String diferencia_fecha_pospuesta;
	protected String dias;
	protected String area;
	protected String area_msg;
	protected String area_class;
	protected String ipAddress;
	
	protected String[] tables;
	protected String[] fields;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer menu_order;
	protected Integer screen_id;
	
	protected int porcentage;
	protected int emisor_prospectus_id;
	
	protected final int FUNCTION_BORRAR_INTENTOS_CONSULTA = 28;
	
	protected boolean flagBCScore;
	protected boolean change_control_OK;
	protected boolean panel_consulta_ENABLED;
	protected boolean panel_inversionista_ENABLED;
	protected boolean borrar_intentos_ENABLED;
	
	protected ActivityPersonDMO()
	{
		datosInit = "";
		flagBCScore =  false;
		
		tables = new String[]{"gn_credit_history_attempt"};
		fields = new String[]{"attempt_id"};	
		
		frm = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy ' a las ' hh:mm a ");
	}
	
	protected void initValues()
	{
		membership = null;		
		listPhone = null;
		attemps   = null;		
		score     = null;
		menus     = null;
		borrar_intentos_ENABLED = false;
		flagBCScore = false;
		change_control_OK = false;
		
		activationDateStr  = "";
		registrationReason = "";
	}
	
	public final void setProspectus_id(Integer prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public final void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}
	
	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public void setNotificador(NotificadorIMO notificador) 
	{
		this.notificador = notificador;
	}
	
	public void setScreenService(ScreenService service) 
	{
		screenService = service;
	}
	
	public void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}
	
	public void setTelefonoService(PhoneService service) 
	{
		telefonoService = service;
	}
	
	public void setService_historial_consultas(CreditHistoryAttemptService service)
	{
		service_historial_consultas = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}

	public List<CreditHistoryAttempt> getAttemps() {
		return attemps;
	}
	
	public List<MenuRegBean> getMenus() {
		return menus;
	}
	
	public final ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}

	public final boolean isPanel_consulta_ENABLED() 
	{
		return panel_consulta_ENABLED;
	}
	
	public final boolean isPanel_inversionista_ENABLED() 
	{
		return panel_inversionista_ENABLED;
	}
	
	public final boolean isBorrar_intentos_ENABLED() 
	{
		return borrar_intentos_ENABLED;
	}


	
	public boolean isFlagBCScore() 
	{
		return flagBCScore;
	}
	
	public Scoring getScore() {
		return score;
	}
	

	
	public String getFechaConsulta() 
	{
		return fechaConsulta;
	}
	
	public final String getRegistrationReason() 
	{
		return registrationReason;
	}

	public final Membership getMembership() 
	{
		return membership;
	}

	public final List<Phone> getListPhone() 
	{
		return listPhone;
	}

	public String getActivationDateStr() {
		return activationDateStr;
	}
	
	public final String getArea_msg() 
	{
		return area_msg;
	}

	public final String getArea_class() 
	{
		return area_class;
	}

	public String getClsActv() {
		return clsActv;
	}
	
	public void setClsActv(String clsActv) {
		this.clsActv = clsActv;
	}
	
	public String getFecUltimoAccesoStr() {
		return fecUltimoAccesoStr;
	}
	
	public void setFecUltimoAccesoStr(String fecUltimoAccesoStr) {
		this.fecUltimoAccesoStr = fecUltimoAccesoStr;
	}
	
	public String getUltimaPantallaStr() {
		return ultimaPantallaStr;
	}
	
	public void setUltimaPantallaStr(String ultimaPantallaStr) {
		this.ultimaPantallaStr = ultimaPantallaStr;
	}
	
	public String getDatosInit() {
		return datosInit;
	}
	
	public void setDatosInit(String datosInit) {
		this.datosInit = datosInit;
	}
	
}
