package mx.com.kubo.managedbeans.registro.datos;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.bean.ValBusiness;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMO;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.Gender;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.registro.datos.birthdate.FechaNacimientoIMO;
import mx.com.kubo.registro.datos.citizen.NacionalidadIMO;
import mx.com.kubo.registro.datos.country.BirthCountryIMO;
import mx.com.kubo.registro.datos.curp.CURPGeneratorIMO;
import mx.com.kubo.registro.datos.domicilio.DomicilioIMO;
import mx.com.kubo.registro.datos.genero.GeneroIMO;
import mx.com.kubo.registro.datos.moral.PersonaMoralIMO;
import mx.com.kubo.registro.datos.nombre.PersonNameIMO;
import mx.com.kubo.registro.datos.pais.PaisOrigenIMO;
import mx.com.kubo.registro.datos.simulador.SimuladorIMO;
import mx.com.kubo.registro.datos.state.BirthPlaceIMO;
import mx.com.kubo.registro.verificacion.ProspectoDuplicadoIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.AddressTypeService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.ContactWayProspectusService;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.services.EconomicActivityService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.FullNameService;
import mx.com.kubo.services.GenderServiceIMO;
import mx.com.kubo.services.LegalStatusService;
import mx.com.kubo.services.MaritalStatusService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectorService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SegmentProspectusService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.StudyLevelService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

public abstract class BasicDataDMO 
implements BasicDataIMO
{
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService service_natural_person;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService service_prospectus;

	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;

	@ManagedProperty("#{legalStatusServiceImp}")
	protected LegalStatusService legalstatusService;

	@ManagedProperty("#{maritalStatusServiceImp}")
	protected MaritalStatusService maritalstatusService;

	@ManagedProperty("#{residenceServiceImp}")
	protected ResidenceService residenceService;

	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService employmentService;

	@ManagedProperty("#{economicActivityServiceImp}")
	protected EconomicActivityService economicactivityService;

	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;

	@ManagedProperty("#{addressTypeServiceImp}")
	protected AddressTypeService addresstypeService;

	@ManagedProperty("#{countryServiceImp}")
	protected CountryService countryService;

	@ManagedProperty("#{stateServiceImp}")
	protected StateService service_estado;

	@ManagedProperty("#{townServiceImp}")
	protected TownService townService;

	@ManagedProperty("#{neighborhoodServiceImp}")
	protected NeighborhoodService neighborhoodService;

	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService businessservice;

	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;

	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;

	@ManagedProperty("#{studyLevelServiceImp}")
	protected StudyLevelService studyLevelService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService accountService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
		
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	protected ProspectoDuplicadoIMO service_prospecto_duplicado;
	
	@ManagedProperty("#{purposeServiceImp}")
	protected PurposeService purposeService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;		
	
	@ManagedProperty("#{service_gender}")
	protected GenderServiceIMO service_gender;
	
	@ManagedProperty("#{prospectorServiceImp}")
	protected ProspectorService prospector_service; 
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{segmentProspectusServiceImp}")
	protected SegmentProspectusService segmentprospectusservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	@ManagedProperty("#{fullNameServiceImp}")
	protected FullNameService fullnameservice;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	protected ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{contactWayProspectusServiceImp}")
	protected ContactWayProspectusService contactwayprospectusservice;
	
	protected Logger log = Logger.getLogger(getClass());
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext    context;
	protected ELResolver   resolver;
	protected ExternalContext external;
	
	protected HeaderBean header;
		
	protected HtmlInputText input_text;
	protected HtmlSelectOneRadio select_radio;
		
	protected Simulator simulator;	
	protected SessionBean  sesion;	
	protected Prospectus    prospectus;
	protected ProspectusPK prosPK;
	protected NaturalPerson naturalPerson;
	protected gnNaturalPersonPK npPK;
	protected Membership    membership;
	protected MembershipPK mspk;		
	
	protected BusinessPK business_PK;
	protected AddressPK adpk;
	
	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;
	protected ChangeBean       change_control_bean;
	
	protected SavingAccount account;
	protected SavingAccountPK accountPk;		
	
	protected  NavigationBeanIMO navigation_bean;	
	protected      PersonNameIMO name;
	protected          GeneroIMO gender;
	protected      PaisOrigenIMO pais_origen;
	protected FechaNacimientoIMO birthday;	
	protected    NacionalidadIMO citizenship;
	protected      BirthPlaceIMO state;
	protected    BirthCountryIMO country;
	protected   CURPGeneratorIMO generator;
	protected    PersonaMoralIMO moral;	
	protected       DomicilioIMO domicilio;
	protected       DomicilioIMO fiscal;	
	protected     NotificadorIMO notificador;
	protected       SimuladorIMO simulador;
	
	protected Access access;
	protected Phone   thisPhoneFixed;
	protected Phone   thisPhoneCell;
	
	protected BmxEconActivityCat bmx;
	
	protected Employment   employment;
	protected EmploymentPK employment_PK;
	
	protected ValBusiness val_business;
	protected UploadedFile file;	
	protected ListNeighborhoodBean colonia;

	protected List<Legal_Status>      legalstatuslist;
	protected List<Study_Level>       studylevellist;	
	protected List<StateCat>          stateList;
	protected List<Country>           countryList;	
	protected List<SavingAccount>     listAccount;
	protected List<Marital_Status>    maritalstatuslist;	
	protected List<Economic_Activity> economicActivityList;
	protected List<Business> lista_business;
	protected List<Gender>   lista_de_generos;
	
	protected String dateStr;
	protected String businessName ="";
	protected String employmentName ="";
	protected String descriptioAccount;
	protected String dispAcred    = "none";
	protected String dispInvestor = "none";
	protected String photo = "img/sinimagen.jpg";
	protected String maritalstatusdis = "none";
	protected String completeName = "Nombre";
	protected String extDis = "none";
	protected String mexDis = "block";
	protected String displaySector = "none";
	protected String lista_prospectos_duplicados;
	protected String ladaFixedProspectus;
	protected String phoneFixedPropectus;
	protected String ladaCelProspectus;
	protected String phoneCellProspectus;
	protected String is_legal_address;	
	protected String RFC, CURP, comentario;					
	protected String father_last_name;
	
	protected String scriptContactWay;
	protected String contactWayValue;
	
	private String 	homaclave;
	private String 	moreEmployment = "none";
	private String 	uniqueEmployment;
	private String 	displayEmployed = "none";
	private String 	moreEmployedStr = "0";
	private String 	moreBusinessStr = "0";
	private String 	moreBusiness = "none";
	private String 	uniqueBusiness;		
	private String 	photoTemp = "img/sinimagen.jpg";
	private String 	edited_mx_rfc;
	private String 	edited_mx_curp;
	
	protected List<String> selectedComputer;	
	protected List<String> selectedInternet;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_coach;
	protected Integer change_prospectus_id;
	protected Integer country_id;
	
	protected Character coverage_zone;		
	protected Character person_type;
	
	protected int economicActivity;
	protected int residenceType;
	protected int address_type_id;
	protected int prospectus_id_viewed;
				
	protected boolean save_address_OK;	
	protected boolean hasPhoneFixedProspectus 	= false;
	protected boolean hasPhoneCell  = false;
	protected boolean hasBusiness   = false;		
	protected boolean hasEmployment = false;		
	protected boolean is_accionista_DUPLICADO;
	protected boolean is_prospecto_DUPLICADO;
	protected boolean inversionista_ENABLED;
	protected boolean coverage_zone_ENABLED;	
	protected boolean booleanListo   = false;	
	protected boolean protectorValid = false;
	protected boolean consultValid   = false;	
	protected boolean residence_ENABLED;
	protected boolean legal_address_ENABLED;
	protected boolean fiscal_ENABLED;
	protected boolean neighborhood_text_ENABLED;
	protected boolean address_ENABLED;
	protected boolean change_control_OK;
	
	protected final int RECHAZADO_AUTOMATICAMENTE = 3;
	protected final int RIESGO_BURO = 1;
	protected final int SCREEN_COVERAGE_ZONE = 41;
	
	protected static final int DOMICILIO_CASA    = 1;
	protected static final int DOMICILIO_EMPRESA = 8;
	protected static final int DOMICILIO_FISCAL  = 9;
	
	
	public void setAddresstypeService(AddressTypeService service) 
	{
		addresstypeService = service;
	}
	
	public void setCountryService(CountryService service) 
	{
		countryService = service;
	}

	public void setService_estado(StateService service) 
	{
		service_estado = service;
	}

	public void setTownService(TownService service) 
	{
		townService = service;
	}

	public void setNeighborhoodService(NeighborhoodService service)
	{
		neighborhoodService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setBusinessservice(BusinessService service)
	{
		businessservice = service;
	}
	
	public void setPhoneService(PhoneService service) 
	{
		phoneService = service;
	}
	
	public void setStudyLevelService(StudyLevelService service) 
	{
		studyLevelService = service;
	}
	
	public void setService_prospectus(ProspectusService service)
	{
		service_prospectus = service;
	}

	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}
	
	public void setLegalstatusService(LegalStatusService service) 
	{
		legalstatusService = service;
	}

	public void setResidenceService(ResidenceService service) 
	{
		residenceService = service;
	}

	public void setEmploymentService(EmploymentService service) 
	{
		employmentService = service;
	}
	
	public void setEconomicactivityService(EconomicActivityService service) 
	{
		economicactivityService = service;
	}
	
	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}
	
	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public void setAddressService(AddressService service) 
	{
		addressService = service;
	}
	
	public void setPurposeService(PurposeService service) 
	{
		purposeService = service;
	}

	public final void setService_gender(GenderServiceIMO service) 
	{
		service_gender = service;
	}
	
	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public void setProspector_service(ProspectorService service) 
	{
		this.prospector_service = service;
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

	public void setSegmentprospectusservice(SegmentProspectusService service) 
	{
		segmentprospectusservice = service;
	}

	public void setSystemParamService(SystemParamService service)
	{
		systemParamService = service;
	}

	public final NaturalPerson getNaturalPerson() 
	{
		return naturalPerson;
	}
	
	public final PaisOrigenIMO getPais_origen() 
	{
		return pais_origen;
	}
	
	public final PersonNameIMO getName()
	{
		return name;
	}
	
	public final FechaNacimientoIMO getBirthday() 
	{
		return birthday;
	}
	
	public final NacionalidadIMO getCitizenship()
	{
		return citizenship;
	}
	
	public final BirthPlaceIMO getState()	
	{
		return state;
	}
	
	public final BirthCountryIMO getCountry()
	{
		return country;
	}
	
	public final List<Gender> getLista_de_generos() 
	{
		return lista_de_generos;
	}
		
	public final PersonaMoralIMO getMoral() 
	{
		return moral;
	}
	
	public final DomicilioIMO getDomicilio() 
	{
		return domicilio;
	}
	
	public final DomicilioIMO getFiscal() 
	{
		return fiscal;
	}
	
	public GeneroIMO getGender() 
	{
		return gender;
	}
	
	public SimuladorIMO getSimulador()
	{
		return simulador;
	}
	
	public boolean isInversionista_ENABLED() 
	{
		return inversionista_ENABLED;
	}

	public final String getIs_legal_address() 
	{
		return is_legal_address;
	}
	
	public final boolean isLegal_address_ENABLED() 
	{
		return legal_address_ENABLED;
	}

	public final boolean isFiscal_ENABLED() 
	{
		return fiscal_ENABLED;
	}

	public String getMaritalstatusdis() 
	{
		if (naturalPerson.getLegal_status_id() != null && naturalPerson.getLegal_status_id() == 2) 
		{
			maritalstatusdis = "block";
			
		} else {
			
			maritalstatusdis = "none";
		}
		
		return maritalstatusdis;
	}
	

	public String getExtDis() 
	{
		if (naturalPerson.getCitizenship() == null || naturalPerson.getCitizenship() == 1 )
		{
			if( (naturalPerson.getBirth_place() != null && naturalPerson.getBirth_place().equals("1") ) ){
				extDis = "block";
			}else{
				extDis = "none";
			}
		} else {
			
			
				extDis = "block";
			
		}
		
		return extDis;
	}
	
	protected BmxEconActivityCat getActivity(String query) 
	{
		List<BmxEconActivityCat> results = new ArrayList<BmxEconActivityCat>();
		List<String> lista = new ArrayList<String>();
		lista.removeAll(results);
		results = employmentService.searchActivityList(query);
		log.info("Tamaño result= " + results.size());
		
		if(results.size()>0)
		{
			return results.get(0);
			
		} else {
			return null;
		}
	}

	public void setExtDis(String extDis) 
	{
		this.extDis = extDis;
	}

	public String getMexDis() 
	{
		if (naturalPerson.getCitizenship() == null || naturalPerson.getCitizenship() == 1)
		{
			mexDis = "block";
			
		} else {
			mexDis = "none";
		}
		
		return mexDis;
	}
	
	public String getDisplaySector() {
		return displaySector;
	}

	public void setDisplaySector(String displaySector) {
		this.displaySector = displaySector;
	}

	public String getMoreBusinessStr() {
		return moreBusinessStr;
	}

	public void setMoreBusinessStr(String moreBusinessStr) {
		this.moreBusinessStr = moreBusinessStr;
	}

	public String getUniqueBusiness() {
		return uniqueBusiness;
	}

	public void setUniqueBusiness(String uniqueBusiness) {
		this.uniqueBusiness = uniqueBusiness;
	}

	public String getMoreBusiness() {
		return moreBusiness;
	}

	public void setMoreBusiness(String moreBusiness) {
		this.moreBusiness = moreBusiness;
	}
	
	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLadaFixedProspectus() {
		return ladaFixedProspectus;
	}

	public void setLadaFixedProspectus(String ladaFixedProspectus) {
		this.ladaFixedProspectus = ladaFixedProspectus;
	}

	public String getPhoneFixedPropectus() {
		return phoneFixedPropectus;
	}
	
	public void setPhoneFixedPropectus(String phoneFixedPropectus) {
		this.phoneFixedPropectus = phoneFixedPropectus;
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
	
	public final String getEdited_mx_rfc() 
	{
		return edited_mx_rfc;
	}

	public final void setEdited_mx_rfc(String value) 
	{
		edited_mx_rfc = value;
	}		

	public final String getEdited_mx_curp() 
	{
		return edited_mx_curp;
	}

	public final void setEdited_mx_curp(String value) 
	{
		edited_mx_curp = value;
	}

	public boolean isHasPhoneFixedProspectus() {
		return hasPhoneFixedProspectus;
	}

	public void setHasPhoneFixedProspectus(boolean hasPhoneFixedProspectus) {
		this.hasPhoneFixedProspectus = hasPhoneFixedProspectus;
	}

	public boolean isHasPhoneCell() {
		return hasPhoneCell;
	}

	public void setHasPhoneCell(boolean hasPhoneCell) {
		this.hasPhoneCell = hasPhoneCell;
	}

	public Phone getThisPhoneFixed() {
		return thisPhoneFixed;
	}
	public void setThisPhoneFixed(Phone thisPhoneFixed) {
		this.thisPhoneFixed = thisPhoneFixed;
	}

	public Phone getThisPhoneCell() {
		return thisPhoneCell;
	}

	public void setThisPhoneCell(Phone thisPhoneCell) {
		this.thisPhoneCell = thisPhoneCell;
	}

	public List<String> getSelectedComputer() {
		return selectedComputer;
	}

	public void setSelectedComputer(List<String> selectedComputer) {
		this.selectedComputer = selectedComputer;
	}

	public List<String> getSelectedInternet() {
		return selectedInternet;
	}

	public void setSelectedInternet(List<String> selectedInternet) {
		this.selectedInternet = selectedInternet;
	}

	public List<Study_Level> getStudylevellist() {
		return studylevellist;
	}

	public void setStudylevellist(List<Study_Level> studylevellist) {
		this.studylevellist = studylevellist;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getDispAcred() {
		return dispAcred;
	}

	public String getDispInvestor() {
		return dispInvestor;
	}

	public void setDispAcred(String dispAcred) {
		this.dispAcred = dispAcred;
	}

	public void setDispInvestor(String dispInvestor) {
		this.dispInvestor = dispInvestor;
	}

	public List<SavingAccount> getListAccount() {
		return listAccount;
	}

	public String getDescriptioAccount() {
		return descriptioAccount;
	}

	public void setAccountService(SavingAccountService service)
	{
		accountService = service;
	}

	public void setListAccount(List<SavingAccount> listAccount) {
		this.listAccount = listAccount;
	}

	public void setDescriptioAccount(String descriptioAccount) {
		this.descriptioAccount = descriptioAccount;
	}
	

	public Prospectus getProspectus() {
		return prospectus;
	}

	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}

	public List<Legal_Status> getLegalstatuslist() {
		return legalstatuslist;
	}

	public void setLegalstatuslist(List<Legal_Status> legalstatuslist) {
		this.legalstatuslist = legalstatuslist;
	}

	public void setMaritalstatusService(MaritalStatusService service) 
	{
		maritalstatusService = service;
	}

	public List<Marital_Status> getMaritalstatuslist() {
		return maritalstatuslist;
	}

	public void setMaritalstatuslist(List<Marital_Status> maritalstatuslist) {
		this.maritalstatuslist = maritalstatuslist;
	}

	public List<Economic_Activity> getEconomicActivityList() {
		return economicActivityList;
	}

	public int getEconomicActivity() {
		return economicActivity;
	}

	public void setEconomicActivity(int economicActivity) {
		this.economicActivity = economicActivity;
	}

	public void setMexDis(String mexDis) {
		this.mexDis = mexDis;
	}

	public String getHomaclave() {
		return homaclave;
	}

	public void setHomaclave(String homaclave) {
		this.homaclave = homaclave;
	}

	public String getMoreEmployment() {
		return moreEmployment;
	}

	public void setMoreEmployment(String moreEmployment) {
		this.moreEmployment = moreEmployment;
	}

	public String getUniqueEmployment() {
		return uniqueEmployment;
	}

	public void setUniqueEmployment(String uniqueEmployment) {
		this.uniqueEmployment = uniqueEmployment;
	}

	public String getDisplayEmployed() {
		return displayEmployed;
	}

	public void setDisplayEmployed(String displayEmployed) {
		this.displayEmployed = displayEmployed;
	}

	public String getMoreEmployedStr() {
		return moreEmployedStr;
	}

	public void setMoreEmployedStr(String moreEmployedStr) {
		this.moreEmployedStr = moreEmployedStr;
	}

	public boolean isHasBusiness() {
		return hasBusiness;
	}

	public void setHasBusiness(boolean hasBusiness) {
		this.hasBusiness = hasBusiness;
	}

	public boolean isHasEmployment() {
		return hasEmployment;
	}

	public void setHasEmployment(boolean hasEmployment) {
		this.hasEmployment = hasEmployment;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmploymentName() {
		return employmentName;
	}

	public void setEmploymentName(String employmentName) {
		this.employmentName = employmentName;
	}
	
	public String getPhotoTemp() {
		return photoTemp;
	}

	public void setPhotoTemp(String photoTemp) {
		this.photoTemp = photoTemp;
	}
	
	public void setMaritalstatusdis(String maritalstatusdis) {
		this.maritalstatusdis = maritalstatusdis;
	}

	public int getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(int residenceType) {
		this.residenceType = residenceType;
	}
	
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public Membership getMembership() {
		return membership;
	}

	public List<StateCat> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateCat> stateList) {
		this.stateList = stateList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public boolean isProtectorValid() {
		return protectorValid;
	}

	public void setProtectorValid(boolean protectorValid) {
		this.protectorValid = protectorValid;
	}

	public boolean isConsultValid() {
		return consultValid;
	}

	public void setConsultValid(boolean consultValid) {
		this.consultValid = consultValid;
	}

	public boolean isBooleanListo() 
	{
		return booleanListo;
	}

	public void setFullnameservice(FullNameService fullnameservice) {
		this.fullnameservice = fullnameservice;
	}

	public void setClabeaccountservice(ClabeAccountService clabeaccountservice) {
		this.clabeaccountservice = clabeaccountservice;
	}

	public void setContactwayprospectusservice(ContactWayProspectusService contactwayprospectusservice) {
		this.contactwayprospectusservice = contactwayprospectusservice;
	}

	public String getScriptContactWay() {
		return scriptContactWay;
	}

	public void setScriptContactWay(String scriptContactWay) {
		this.scriptContactWay = scriptContactWay;
	}

	public String getContactWayValue() {
		return contactWayValue;
	}

	public void setContactWayValue(String contactWayValue) {
		this.contactWayValue = contactWayValue;
	}
}
