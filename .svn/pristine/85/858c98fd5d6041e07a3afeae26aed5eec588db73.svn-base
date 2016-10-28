package mx.com.kubo.managedbeans;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.ConsultingErrorBean;
import mx.com.kubo.bean.TemporalBean;
import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.model.ConsultingManualPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.ConsultingManualService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.MailLogService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.RegistrationReasonService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.ViewClientInfoService;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;
import com.soa.webServices.responses.ProspectBCRiskResponse;

public abstract class ConsultingControllerDMO 
{
	@ManagedProperty("#{notificadorImp}")
	protected NotificadorIMP  notificador;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice;
	
	@ManagedProperty("#{consultingManualServiceImp}")
	protected ConsultingManualService consultingmanualservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{viewClientInfoServiceImp}")
	protected ViewClientInfoService viewclientinfoservice;
	
	@ManagedProperty("#{eventServiceImp}")
	protected EventService eventService;
	
	@ManagedProperty("#{mailLogServiceImp}")
	protected MailLogService mailService;
	
	@ManagedProperty("#{registrationReasonServiceImp}")
	protected RegistrationReasonService reasonsService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	@ManagedProperty("#{townServiceImp}")
	protected TownService townService;
	
	@ManagedProperty("#{neighborhoodServiceImp}")
	protected NeighborhoodService neighborhoodService;
	
	@ManagedProperty("#{stateServiceImp}")
	protected StateService stateService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	protected ELContext elContext;
	protected SessionBean sesion;
	protected Simulator simulator;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk service;
	protected ServiceCalling srvCall;
	protected BCRiskRequest request;
	protected ProspectBCRiskResponse prospect_bc_risk_response;	
		
	protected Membership   memberSel;
	protected Membership   membership;
	protected MembershipPK membership_PK;
	protected Membership[] clientList;
				
	protected Proyect       proyect;
	protected ProyectPK     proyect_pk;
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected Scoring score;
	protected Evento  evento;
	protected SimulatorBean simulador;
	protected ClientView clientView;	
	
	protected ConsultingManual   consulting;
	protected ConsultingManualPK consulting_PK;
	
	protected Writer writer;
	protected PrintWriter printWriter;
	
	protected List<ClientView> suggestions;
	protected List<ConsultingErrorBean> lstError;
	
	protected SimpleDateFormat date_format;
	
	protected String msg;
	protected String exception;
	protected String birthday;
	protected String califKubo;
	protected String bcscore;
	protected String rate;
	protected String messageErrorDatos;
	protected String messageErrorConsulta;
	protected String search;
	protected String loan_type;
	protected String origin_value;
	protected String valPerSelStr; 
	protected String valores_a_consultar;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_id;
	protected int registration_reason_id;
	protected int searchingType;
	protected int intConsultas;
	
	protected boolean displaydataSuccess;
	protected boolean displayConsulSuccess;
	protected boolean consultaBuro;
	protected boolean is_proyect_OK;
	
	protected List<RegistrationReason> reasonsLst;
	
	protected List<TemporalBean> listSearchingType;
	
	protected RoleFunctionController role_function;
	
	protected ConsultingControllerDMO()
	{
		date_format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
	}
	
	public Membership[] getClientList() {
		return clientList;
	}

	public void setClientList(Membership[] clientList) {
		this.clientList = clientList;
	}

	public Membership getMemberSel() {
		return memberSel;
	}

	public void setMemberSel(Membership memberSel) {
		this.memberSel = memberSel;
	}

	public Membership getUser() {
		return membership;
	}

	public void setUser(Membership user) {
		this.membership = user;
	}

	public ClientView getClientView() {
		return clientView;
	}

	public void setClientView(ClientView clientView) {
		this.clientView = clientView;
	}

	public boolean isDisplaydataSuccess() {
		return displaydataSuccess;
	}

	public void setDisplaydataSuccess(boolean displaydataSuccess) {
		this.displaydataSuccess = displaydataSuccess;
	}

	public boolean isDisplayConsulSuccess() {
		return displayConsulSuccess;
	}

	public void setDisplayConsulSuccess(boolean displayConsulSuccess) {
		this.displayConsulSuccess = displayConsulSuccess;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCalifKubo() {
		return califKubo;
	}

	public void setCalifKubo(String califKubo) {
		this.califKubo = califKubo;
	}

	public String getBcscore() {
		return bcscore;
	}

	public void setBcscore(String bcscore) {
		this.bcscore = bcscore;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getMessageErrorDatos() {
		return messageErrorDatos;
	}

	public void setMessageErrorDatos(String messageErrorDatos) {
		this.messageErrorDatos = messageErrorDatos;
	}

	public String getMessageErrorConsulta() {
		return messageErrorConsulta;
	}

	public void setMessageErrorConsulta(String messageErrorConsulta) {
		this.messageErrorConsulta = messageErrorConsulta;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public NotificadorIMP getNotificador() {
		return notificador;
	}

	public void setNotificador(NotificadorIMP notificador) {
		this.notificador = notificador;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}

	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}

	public EventNotificationService getEventnotificationservice() {
		return eventnotificationservice;
	}

	public void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}

	public ConsultingManualService getConsultingmanualservice() {
		return consultingmanualservice;
	}

	public void setConsultingmanualservice(ConsultingManualService service) 
	{
		consultingmanualservice = service;
	}
	
	public ProyectLoanService getProyectloanService() {
		return proyectloanService;
	}

	public void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}

	public ProyectService getProyectService() {
		return proyectService;
	}

	public void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}

	public SimulatorService getSimulatorService() {
		return simulatorService;
	}

	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}

	public ViewClientInfoService getViewclientinfoservice() {
		return viewclientinfoservice;
	}

	public void setViewclientinfoservice(ViewClientInfoService service) 
	{
		viewclientinfoservice = service;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService service) 
	{
		eventService = service;
	}

	public MailLogService getMailService() {
		return mailService;
	}

	public void setMailService(MailLogService service) 
	{
		mailService = service;
	}

	public int getRegistration_reason_id() {
		return registration_reason_id;
	}

	public void setRegistration_reason_id(int registration_reason_id) {
		this.registration_reason_id = registration_reason_id;
	}

	public List<RegistrationReason> getReasonsLst() {
		return reasonsLst;
	}

	public void setReasonsLst(List<RegistrationReason> reasonsLst) {
		this.reasonsLst = reasonsLst;
	}

	public RegistrationReasonService getReasonsService() {
		return reasonsService;
	}

	public void setReasonsService(RegistrationReasonService reasonsService) {
		this.reasonsService = reasonsService;
	}

	public List<TemporalBean> getListSearchingType() {
		return listSearchingType;
	}

	public void setListSearchingType(List<TemporalBean> listSearchingType) {
		this.listSearchingType = listSearchingType;
	}

	public int getSearchingType() {
		return searchingType;
	}

	public void setSearchingType(int searchingType) {
		this.searchingType = searchingType;
	}

	public SystemParamService getSystemParamService() {
		return systemParamService;
	}

	public void setSystemParamService(SystemParamService systemParamService) {
		this.systemParamService = systemParamService;
	}

	public String getOrigin_value() {
		return origin_value;
	}

	public void setOrigin_value(String origin_value) {
		this.origin_value = origin_value;
	}

	public String getValPerSelStr() {
		return valPerSelStr;
	}

	public void setValPerSelStr(String valPerSelStr) {
		this.valPerSelStr = valPerSelStr;
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public TownService getTownService() {
		return townService;
	}

	public void setTownService(TownService townService) {
		this.townService = townService;
	}

	public NeighborhoodService getNeighborhoodService() {
		return neighborhoodService;
	}

	public void setNeighborhoodService(NeighborhoodService neighborhoodService) {
		this.neighborhoodService = neighborhoodService;
	}

	public StateService getStateService() {
		return stateService;
	}

	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public NaturalPersonService getNaturalPersonService() {
		return naturalPersonService;
	}

	public void setNaturalPersonService(NaturalPersonService naturalPersonService) {
		this.naturalPersonService = naturalPersonService;
	}

	public SimulatorBean getSimulador() {
		return simulador;
	}

	public void setSimulador(SimulatorBean simulador) {
		this.simulador = simulador;
	}

	public String getValores_a_consultar() {
		return valores_a_consultar;
	}

	public void setValores_a_consultar(String valores_a_consultar) {
		this.valores_a_consultar = valores_a_consultar;
	}

	public List<ConsultingErrorBean> getLstError() {
		return lstError;
	}

	public void setLstError(List<ConsultingErrorBean> lstError) {
		this.lstError = lstError;
	}

	public int getIntConsultas() {
		return intConsultas;
	}

	public void setIntConsultas(int intConsultas) {
		this.intConsultas = intConsultas;
	}

}
