package mx.com.kubo.managedbeans.registro.consulta;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.CreditHistoryAttemptPK;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.CreditHistoryAttemptService;
import mx.com.kubo.services.CreditHistoryService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectorService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

import com.soa.model.businessobject.BurResponse;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class PreaprobacionDMO 
implements PreaprobacionIMO
{		
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{creditHistoryServiceImp}")
	protected CreditHistoryService credithistoryService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	@ManagedProperty("#{townServiceImp}")
	protected TownService townService;
	
	@ManagedProperty("#{neighborhoodServiceImp}")
	protected NeighborhoodService neighborhoodService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{stateServiceImp}")
	protected StateService stateService;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice; 
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{creditHistoryAttemptServiceImp}")
	protected CreditHistoryAttemptService creditAttemptService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	protected NotesService service_notas;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;	
	
	@ManagedProperty("#{prospectorServiceImp}")
	protected ProspectorService prospector_service; 
	
	protected NavigationBeanIMP navigation;
	protected PersonaBloqueadaIMO blocked;
	protected NotificadorIMO notificador;
	
	protected FacesContext faces;
	protected RequestContext request;
	protected ELContext   	elContext;
	protected ELResolver   resolver;
	
	protected HtmlInputText  ajax_inputText;
	
	protected SessionBean       sesion;	
	protected Membership        membership;
	protected MembershipPK      membership_PK;
	protected Prospectus        prospectus;
	protected ProspectusPK      prospectusPK;			
	protected NaturalPerson     naturalPerson;
	protected gnNaturalPersonPK naturalPersonPK;
		
	protected Proyect       proyect;
	protected ProyectPK     proyect_PK;
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
		
	protected Simulator     simulator;
	protected SimulatorPK   simulator_PK;
	protected SimulatorBean simulacion_ACTUAL;
	
	protected CreditHistoryAttempt   intento;
	protected CreditHistoryAttemptPK intentoPK;
	protected CreditHistory   credithistory;	
	protected CreditHistoryPK creditHistoryPK;	

	protected Scoring score;
	protected Access access;
	
	protected Phone   thisPhoneFixed;
	protected Address thisAddress; 
	protected NeighborhoodCat   neig;
	protected NeighborhoodCatPK nPK;
	protected TownCat   town;
	protected TownCatPK tPK;
	protected StateCat state;
	protected StateCatPK sPK;
	
	protected Writer      writer;
	protected PrintWriter printWriter;
	protected ResourceBundle recurso;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk 			   service;	
	
	protected ServiceCalling srvCall;
	protected BurResponse    response;
	
	protected SystemParam   system;
	protected SystemParamPK paramPK;	
	
	protected Notes nota_del_coach;
	
	protected List<Notes> lista_notas_del_coach;
	
	protected SimpleDateFormat format;
	protected SimpleDateFormat formatUtilDate;
	
	protected ConvertCalendar cv2;
	
	protected Date fechaLimite;
	protected Date fecLimiteUtilDate;
	protected Date birth;
	
	protected String errorMsg;
	protected String messageInit;
	protected String user;
	protected String prospectId;
	protected String password;
	protected String birthday;
	protected String lada;
	protected String phone;
	protected String colonia;
	protected String municipio;
	protected String thisEstado;
	protected String msg;		
	protected String msgWarningBurConsult;
	protected String fecLimiteStr;
	protected String birthStr;
	protected String bur_sol_num;
	protected String tarjeta_credito_company;
	protected String tarjeta_credito_number;
	protected String credit_company_name;
	
	protected String loan_type = null;
	protected String is_call_to_client = null;
	
	protected String hid_value;
	
	String msgStr = "<b>No nos fue posible autenticarte ante Buro de Crédito con la información que nos proporcionaste.</b>"
			  	  + "<br /> Por favor considera lo siguiente:"
				  + "<ul>"
				  + "<li>Revisa que tu nombre esté escrito exactamente como está en tu credencial de elector.</li>"
				  + "<li>Verifica tu fecha de cumpleaños.</li>"
				  + "<li>Si has contratado una tarjeta de crédito, crédito hipotecario o crédito de auto hace menos de un mes, es probable que aún no esté reflejado en buró de crédito, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
				  + "<li>Si has tenido un crédito de auto o hipotecario que ya liquidaste hace más de 3 meses, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
				  + "<li>Si tienes un crédito hipotecario de INFONAVIT, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>";
	
	protected Integer diasValidos;
	protected Integer intentosPermitidos;
	protected Integer consultasAnteriores;
	protected Integer numConsulBuro;
	protected Integer has_tarjeta_credito;
	protected Integer has_credito_hipotecario;
	protected Integer has_credito_automotriz;
	protected Integer promotor_id;
	protected Double monto_solicitado;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_id;
	protected int change_prospectus_id;
		
	protected final int LAST = 0;
	protected final int SCREEN_SUCCESS_CONSULTA_BC = 66;
		
	protected boolean success; 
	protected boolean wrong;
	protected boolean fail;
	protected boolean error;
	protected boolean wait;
	protected boolean compara;
	protected boolean simula;
	protected boolean noHit;
	protected boolean consultaBuro;
	protected boolean successTitle;
	protected boolean displayGreen;
	protected boolean displayBlue;
	protected boolean displayErrBurConsult;
	protected boolean displayAction;
	protected boolean is_proyect_OK;
	protected boolean is_prospect_SGB_OK;
	protected boolean is_nota_OK;
	protected boolean tarjeta_credito_company_ENABLED;
	protected boolean has_credito_ENABLED;
	protected boolean is_this_credit_company;	
	protected boolean nota_coach_ENABLED;
	protected boolean success_OK = false;
	protected boolean blocked_person_ENABLED;
	
	protected boolean excecuteJSF = true;
	
	protected boolean hid_flag = false;
	protected int 	    i = 0;
	protected boolean flag = false;
	protected boolean flagDoubleCheck = false;
	protected boolean flagDoubleCheck_2 = true;
	
	protected PreaprobacionDMO()
	{
		wait = true;
		
		prospectId			 = "";
		password   			 = "";
		birthday  			 = "";
		lada      			 = "";
		phone   		     = "";
		colonia    			 = "";
		municipio  			 = "";
		thisEstado 			 = "";
		msg        			 = "";		
		msgWarningBurConsult = "";
	}
	
	public final void setService_notas(NotesService service)
	{
		service_notas = service;
	}
	
	public final PersonaBloqueadaIMO getBlocked() 
	{
		return blocked;
	}

/*	
	public final ConsultaCompletaIMO getConsulta() 
	{
		return consulta;
	}
*/	

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) 
	{
		this.success = success;
	}
	
	public boolean isWrong() {
		return wrong;
	}
	
	public void setWrong(boolean wrong) {
		this.wrong = wrong;
	}
	
	public boolean isFail() {
		return fail;
	}
	
	public void setFail(boolean fail) {
		this.fail = fail;
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
	
	public boolean isBlocked_person_ENABLED() {
		return blocked_person_ENABLED;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}
	
	public boolean isCompara() {
		return compara;
	}
	
	public void setCompara(boolean compara) {
		this.compara = compara;
	}
	
	public boolean isSimula() {
		return simula;
	}
	
	public void setSimula(boolean simula) {
		this.simula = simula;
	}
	
	public boolean isNoHit() {
		return noHit;
	}
	
	public void setNoHit(boolean noHit) {
		this.noHit = noHit;
	}
	
	public boolean isConsultaBuro() {
		return consultaBuro;
	}
	
	public void setConsultaBuro(boolean consultaBuro) {
		this.consultaBuro = consultaBuro;
	}
	
	public boolean isSuccessTitle() {
		return successTitle;
	}
	
	public void setSuccessTitle(boolean successTitle) {
		this.successTitle = successTitle;
	}
	
	public boolean isDisplayGreen() {
		return displayGreen;
	}
	
	public void setDisplayGreen(boolean displayGreen) {
		this.displayGreen = displayGreen;
	}
	
	public boolean isDisplayBlue() {
		return displayBlue;
	}
	
	public void setDisplayBlue(boolean displayBlue) {
		this.displayBlue = displayBlue;
	}
	
	public boolean isDisplayErrBurConsult() {
		return displayErrBurConsult;
	}
	
	public void setDisplayErrBurConsult(boolean displayErrBurConsult) {
		this.displayErrBurConsult = displayErrBurConsult;
	}
	
	public boolean isDisplayAction() {
		return displayAction;
	}
	
	public void setDisplayAction(boolean displayAction) {
		this.displayAction = displayAction;
	}
	
	public Prospectus getProspectus() {
		return prospectus;
	}
	
	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}
	
	public Membership getMembership() {
		return membership;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public CreditHistory getCredithistory() {
		return credithistory;
	}
	
	public void setCredithistory(CreditHistory credithistory) {
		this.credithistory = credithistory;
	}
	
	public NaturalPerson getNaturalPerson() {
		return naturalPerson;
	}
	
	public void setNaturalPerson(NaturalPerson naturalPerson) {
		this.naturalPerson = naturalPerson;
	}
	
	public Phone getThisPhoneFixed() {
		return thisPhoneFixed;
	}
	
	public void setThisPhoneFixed(Phone thisPhoneFixed) 
	{
		this.thisPhoneFixed = thisPhoneFixed;
	}
	
	public Address getThisAddress() {
		return thisAddress;
	}
	
	public void setThisAddress(Address thisAddress) {
		this.thisAddress = thisAddress;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getMessageInit() {
		return messageInit;
	}
	
	public void setMessageInit(String messageInit) {
		this.messageInit = messageInit;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getProspectId() {
		return prospectId;
	}
	
	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getLada() {
		return lada;
	}
	
	public void setLada(String lada) {
		this.lada = lada;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getColonia() {
		return colonia;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getThisEstado() {
		return thisEstado;
	}
	
	public void setThisEstado(String thisEstado) {
		this.thisEstado = thisEstado;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsgWarningBurConsult() {
		return msgWarningBurConsult;
	}
	
	public void setMsgWarningBurConsult(String msgWarningBurConsult) {
		this.msgWarningBurConsult = msgWarningBurConsult;
	}
	
	public void setProspectusService(ProspectusService service) 
	{
		prospectusService = service;
	}
	
	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setCredithistoryService(CreditHistoryService service) 
	{
		credithistoryService = service;
	}
	
	public void setAddressService(AddressService service) 
	{
		addressService = service;
	}
	
	public void setPhoneService(PhoneService service) 
	{
		phoneService = service;
	}
	
	public void setTownService(TownService service) 
	{
		townService = service;
	}
	
	public void setNeighborhoodService(NeighborhoodService service) 
	{
		neighborhoodService = service;
	}
	
	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}
	
	public void setStateService(StateService service) 
	{
		stateService = service;
	}
	
	public void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}
	
	public void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}
	
	public void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}
	
	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}
	
	public void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}
	
	public void setCreditAttemptService(CreditHistoryAttemptService service) 
	{
		creditAttemptService = service;
	}
	
	public void setSystemParamService(SystemParamService service) 
	{
		systemParamService = service;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public boolean isSuccess_OK() {
		return success_OK;
	}

	public void setSuccess_OK(boolean success_OK) {
		this.success_OK = success_OK;
	}

	public Scoring getScore() {
		return score;
	}

	public void setScore(Scoring score) {
		this.score = score;
	}

	public String getHid_value() {
		return hid_value;
	}

	public void setHid_value(String hid_value) {
		this.hid_value = hid_value;
	}

	public boolean isHid_flag() {
		return hid_flag;
	}

	public void setHid_flag(boolean hid_flag) {
		this.hid_flag = hid_flag;
	}

	public ProspectorService getProspector_service() {
		return prospector_service;
	}

	public void setProspector_service(ProspectorService prospector_service) {
		this.prospector_service = prospector_service;
	}

	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

	public Double getMonto_solicitado() {
		return monto_solicitado;
	}

	public void setMonto_solicitado(Double monto_solicitado) {
		this.monto_solicitado = monto_solicitado;
	}

	public int getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public ProyectLoan getProyect_loan() {
		return proyect_loan;
	}

	public void setProyect_loan(ProyectLoan proyect_loan) {
		this.proyect_loan = proyect_loan;
	}

	public boolean isExcecuteJSF() {
		return excecuteJSF;
	}

	public void setExcecuteJSF(boolean excecuteJSF) {
		this.excecuteJSF = excecuteJSF;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public String getIs_call_to_client() {
		return is_call_to_client;
	}

	public void setIs_call_to_client(String is_call_to_client) {
		this.is_call_to_client = is_call_to_client;
	}

}
