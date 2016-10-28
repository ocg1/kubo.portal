package mx.com.kubo.managedbeans.registro.consulta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.registro.consulta.AutorizadorIMO;
import mx.com.kubo.registro.consulta.PersonDataIMO;
import mx.com.kubo.registro.consulta.historial.SearchRequestIMO;
import mx.com.kubo.registro.consulta.historial.car.CarIMO;
import mx.com.kubo.registro.consulta.historial.card.CreditCardIMO;
import mx.com.kubo.registro.consulta.historial.mortage.MortageIMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.CreditHistoryService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

public abstract class CreditHistoryControllerDMO 
implements CreditHistoryControllerIMO
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService servicioProyecto;
	
	@ManagedProperty("#{creditHistoryServiceImp}")
	protected CreditHistoryService service_credit_history;
	
	@ManagedProperty("#{bankServiceImp}")
	protected BankService bankService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	@ManagedProperty("#{residenceServiceImp}")
	protected ResidenceService residenciaService;	
	
	@ManagedProperty("#{notesServiceImp}")
	protected NotesService service_notas;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	protected RequestContext request;
	protected FacesContext    faces;
	protected ExternalContext external;
	protected ELContext       context;
	protected ELResolver      resolver;
	
	protected HtmlSelectOneRadio ajax_input_radio;
	protected HtmlInputText      ajax_inputText;
	protected HtmlSelectOneRadio select_radio;
	
	protected    PersonDataIMO data;
	protected SearchRequestIMO bank_engine;
	protected    CreditCardIMO creditcard;
	protected       MortageIMO mortage;
	protected           CarIMO car;
	protected   AutorizadorIMO consulta;
	
	protected HttpSession sessionUsed;
	protected SessionBean sesion;		
	
	protected Membership        membership;
	protected MembershipPK      membership_PK;
		
	protected Scoring score;
	protected CreditHistory credithistory;
	protected CreditHistoryPK credit_history_PK;
	
	protected Residence residencia;
	protected Address address;
	protected Phone phone;
	
	protected Notes   nota_coach;
	protected NotesPK nota_coach_PK;
	
	protected List<ProyectLoan> lista_proyect_loan;
	
	protected String hidden2;
	protected String dateCreditCar;
	protected String sessionId;
	protected String kubo_rate;
	protected String kubo_score_A;
	protected String kubo_score_B;
	protected String bur_sol_num;
	protected String nota_del_coach;	
	
	protected Integer have_credit;
	protected Integer first_recent_credit;
	
	protected Integer has_targeta_departamental;
	protected Integer promotor_id;
	
	protected Character area;
	
	protected int prospectus_id;
	protected int company_id;
	protected int change_prospectus_id;
	
	protected final int PARTICULAR_CELULAR = 6;
	protected final int NOTA_DEL_COACH = 13;
	protected final int ALTA  = 1;
	protected final int MEDIA = 2;
	
	protected boolean muestraPreaprobacion;
	protected boolean hasHistory;
	protected boolean is_score_ENABLED;
	protected boolean is_credit_history_ENABLED;
	protected boolean is_nota_OK;

	private String tarjetaVigente;
	private String tarjetaDep;
	private String credHip;
	private String credAuto;
	
	protected CreditHistoryControllerDMO()
	{
		hidden2 = "none";				
	}
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public final void setService_notas(NotesService service)
	{
		service_notas = service;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public void setAddressService(AddressService service) 
	{
		addressService = service;
	}

	public void setPhoneService(PhoneService service) 
	{
		phoneService = service;
	}

	public void setBankService(BankService service) 
	{
		bankService = service;
	}

	public void setResidenciaService(ResidenceService service) 
	{
		residenciaService = service;
	}

	public void setScoringService(ScoringService service)
	{
		scoringService = service;
	}

	public void setServicioProyecto(ProyectLoanService service) 
	{
		servicioProyecto = service;
	}
	
	public final void setService_credit_history(CreditHistoryService service) 
	{
		service_credit_history = service;
	}
	
	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public final String getNota_del_coach() 
	{
		return nota_del_coach;
	}
	
	public final PersonDataIMO getData() 
	{
		return data;
	}
	
	public final SearchRequestIMO getBank_engine()
	{
		return bank_engine;
	}
	
	public final CreditCardIMO getCreditcard()
	{
		return creditcard;
	}
	
	public final MortageIMO getMortage()
	{
		return mortage;
	}
	
	public final CarIMO getCar()
	{
		return car;
	}
	
	public final AutorizadorIMO getConsulta()
	{
		return consulta;
	}		

	public final Integer getPromotor_id() 
	{
		return promotor_id;
	}
	
	public String getHidden2() {
		return hidden2;
	}
	
	public String getTarjetaVigente() {
		return tarjetaVigente;
	}
	
	public void setTarjetaVigente(String tarjetaVigente) {
		this.tarjetaVigente = tarjetaVigente;
	}
	
	public String getTarjetaDep() {
		return tarjetaDep;
	}
	
	public void setTarjetaDep(String tarjetaDep) {
		this.tarjetaDep = tarjetaDep;
	}
	
	public String getCredHip() {
		return credHip;
	}
	
	public void setCredHip(String credHip) {
		this.credHip = credHip;
	}
	
	public String getCredAuto() {
		return credAuto;
	}
	
	public void setCredAuto(String credAuto) {
		this.credAuto = credAuto;
	}	
	
	public Residence getResidencia() {
		return residencia;
	}

	public void setResidencia(Residence residencia) {
		this.residencia = residencia;
	}

	public boolean isMuestraPreaprobacion() {
		return muestraPreaprobacion;
	}

	public void setMuestraPreaprobacion(boolean muestraPreaprobacion) {
		this.muestraPreaprobacion = muestraPreaprobacion;
	}
		
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public CreditHistory getCredithistory() 
	{
		return credithistory;
	}

	public void setCredithistory(CreditHistory credithistory) {
		this.credithistory = credithistory;
	}
	
	public boolean isHasHistory() 
	{
		return hasHistory;
	}

	public void setHasHistory(boolean hasHistory) {
		this.hasHistory = hasHistory;
	}

	public String getDateCreditCar() {
		
		SimpleDateFormat frm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date() );
		
		c.add(Calendar.YEAR, -2);
		
		dateCreditCar = frm.format( c.getTime() );
		
		return dateCreditCar;
	}

	public void setDateCreditCar(String dateCreditCar) 
	{
		this.dateCreditCar = dateCreditCar;
	}

	public SystemParamService getSystemParamService() {
		return systemParamService;
	}

	public void setSystemParamService(SystemParamService systemParamService) {
		this.systemParamService = systemParamService;
	}
}
