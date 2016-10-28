package mx.com.kubo.datamodels;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.DealBean;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
//import mx.com.kubo.services.RegistroMailLogService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.impl.FilesServiceImp;

public abstract class DealForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{simulatorServiceImp}")
	private SimulatorService simulatorService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	private ProyectService proyectService;
	
	@ManagedProperty("#{scoringServiceImp}")
	private ScoringService scoringService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{proyectServiceImp}")
	private ProyectService proyectservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	private ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{bankServiceImp}")
	private BankService bankService;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	private ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{filesServiceImp}")
	private FilesServiceImp filesservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	private ProspectusService prospectusService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	private NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{addressServiceImp}")
	private AddressService addressService;
	
	@ManagedProperty("#{phoneServiceImp}")
	private PhoneService phoneService;
	
	@ManagedProperty("#{townServiceImp}")
	private TownService townService;

	@ManagedProperty("#{neighborhoodServiceImp}")
	private NeighborhoodService neighborhoodService;
	
	@ManagedProperty("#{serviceCallingDaoImp}")
	private ServiceCallingDao servicecallingRepository;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	private FileTypeService fileTypeService;
	
	@ManagedProperty("#{filesServiceImp}")
	private FilesService filesService;
	
	@ManagedProperty("#{accessServiceImp}")
	private AccessService accessService;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	private EventNotificationService eventnotificationservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	private MembershipService membershipservice;
	
	//TODO
	//mailService está mal
	
//	@ManagedProperty("#{mailLogServiceImp}")
//	protected RegistroMailLogService mailService;
	
	private Prospectus prospectus;
	private NaturalPerson naturalPerson;
	private Phone thisPhoneFixed;
	private Address thisAddress; 
	private List<MenuRegBean> listRequiredMenu;
	/************************************/
	
	private List<DealBean> dealList;
	private List<Files> listFiles;
	private List<String> lDocAddedCredFm2;
	private List<String> lDocAddedCompActEcon;
	private List<String> lDocAddedCompIncome;
	private List<String> lDocAddedAcredProBusiness;
	private List<String> lDocAddedCompDomi;
	
	private String dispCheckCredFm2;
	private String dispCheckCompActEcon;
	private String dispCheckCompIncome;
	private String dispCheckAcredProBusiness;
	private String dispCheckCompDomi;
	
	private Double ammount;
	private Integer frequency_id;
	private Integer catStr;
	private Double tasaTotal;
	private Integer term_id;
	private Integer purpose_id;
	private DealBean selectedDeal;
	private DealBean beanSelOther;
	private ProyectLoan myPyLn;
	private Proyect myPy;
	private Double montoMin;
	private Integer publishTime;
	private String fundingType="T";
	private Scoring score;
	private String accountDisp = "block";
	private String selAccount;
	private boolean hasAccount;
	private List<ClabeAccount> accountList;
	private ClabeAccount thisClabe;
	private String errorDisp = "none";
	private String errorDesc;
	private String successDisp = "none";
	private String beginDisp = "none";
	private String contResp = "none";
	private String fechaInicio=	"";
	private String formaPagoCom= "D"; //descuento
	private String montoRecibido= "";
	private Double comision= 0D;
	private Double ratewithCom= 0D;
	
	private boolean displayBtnDeal;
	
	private List<AccessCollector> menuIncomplete;
	
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat num = java.text.NumberFormat.getNumberInstance(locale);
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	
	public SimulatorService getSimulatorService() {
		return simulatorService;
	}
	public void setSimulatorService(SimulatorService simulatorService) {
		this.simulatorService = simulatorService;
	}
	public ProyectLoanService getProyectloanService() {
		return proyectloanService;
	}
	public void setProyectloanService(ProyectLoanService proyectloanService) {
		this.proyectloanService = proyectloanService;
	}
	public ProyectService getProyectService() {
		return proyectService;
	}
	public void setProyectService(ProyectService proyectService) {
		this.proyectService = proyectService;
	}
	public ScoringService getScoringService() {
		return scoringService;
	}
	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}
	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}
	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}
	public ProyectService getProyectservice() {
		return proyectservice;
	}
	public void setProyectservice(ProyectService proyectservice) {
		this.proyectservice = proyectservice;
	}
	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}
	public void setServicecallingService(ServiceCallingService servicecallingService) {
		this.servicecallingService = servicecallingService;
	}
	public BankService getBankService() {
		return bankService;
	}
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}
	public ClabeAccountService getClabeaccountservice() {
		return clabeaccountservice;
	}
	public void setClabeaccountservice(ClabeAccountService clabeaccountservice) {
		this.clabeaccountservice = clabeaccountservice;
	}
	public FilesServiceImp getFilesservice() {
		return filesservice;
	}
	public void setFilesservice(FilesServiceImp filesservice) {
		this.filesservice = filesservice;
	}
	public ProspectusService getProspectusService() {
		return prospectusService;
	}
	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}
	public NaturalPersonService getNaturalPersonService() {
		return naturalPersonService;
	}
	public void setNaturalPersonService(NaturalPersonService naturalPersonService) {
		this.naturalPersonService = naturalPersonService;
	}
	public AddressService getAddressService() {
		return addressService;
	}
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
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
	public ServiceCallingDao getServicecallingRepository() {
		return servicecallingRepository;
	}
	public void setServicecallingRepository(
			ServiceCallingDao servicecallingRepository) {
		this.servicecallingRepository = servicecallingRepository;
	}
	public FileTypeService getFileTypeService() {
		return fileTypeService;
	}
	public void setFileTypeService(FileTypeService fileTypeService) {
		this.fileTypeService = fileTypeService;
	}
	public FilesService getFilesService() {
		return filesService;
	}
	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}
	public AccessService getAccessService() {
		return accessService;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public EventNotificationService getEventnotificationservice() {
		return eventnotificationservice;
	}
	public void setEventnotificationservice(
			EventNotificationService eventnotificationservice) {
		this.eventnotificationservice = eventnotificationservice;
	}
	public MembershipService getMembershipservice() {
		return membershipservice;
	}
	public void setMembershipservice(MembershipService membershipservice) {
		this.membershipservice = membershipservice;
	}
	public Prospectus getProspectus() {
		return prospectus;
	}
	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
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
	public void setThisPhoneFixed(Phone thisPhoneFixed) {
		this.thisPhoneFixed = thisPhoneFixed;
	}
	public Address getThisAddress() {
		return thisAddress;
	}
	public void setThisAddress(Address thisAddress) {
		this.thisAddress = thisAddress;
	}
	public List<MenuRegBean> getListRequiredMenu() {
		return listRequiredMenu;
	}
	public void setListRequiredMenu(List<MenuRegBean> listRequiredMenu) {
		this.listRequiredMenu = listRequiredMenu;
	}
	public List<DealBean> getDealList() {
		return dealList;
	}
	public void setDealList(List<DealBean> dealList) {
		this.dealList = dealList;
	}
	public List<Files> getListFiles() {
		return listFiles;
	}
	public void setListFiles(List<Files> listFiles) {
		this.listFiles = listFiles;
	}
	public List<String> getlDocAddedCredFm2() {
		return lDocAddedCredFm2;
	}
	public void setlDocAddedCredFm2(List<String> lDocAddedCredFm2) {
		this.lDocAddedCredFm2 = lDocAddedCredFm2;
	}
	public List<String> getlDocAddedCompActEcon() {
		return lDocAddedCompActEcon;
	}
	public void setlDocAddedCompActEcon(List<String> lDocAddedCompActEcon) {
		this.lDocAddedCompActEcon = lDocAddedCompActEcon;
	}
	public List<String> getlDocAddedCompIncome() {
		return lDocAddedCompIncome;
	}
	public void setlDocAddedCompIncome(List<String> lDocAddedCompIncome) {
		this.lDocAddedCompIncome = lDocAddedCompIncome;
	}
	public List<String> getlDocAddedAcredProBusiness() {
		return lDocAddedAcredProBusiness;
	}
	public void setlDocAddedAcredProBusiness(List<String> lDocAddedAcredProBusiness) {
		this.lDocAddedAcredProBusiness = lDocAddedAcredProBusiness;
	}
	public List<String> getlDocAddedCompDomi() {
		return lDocAddedCompDomi;
	}
	public void setlDocAddedCompDomi(List<String> lDocAddedCompDomi) {
		this.lDocAddedCompDomi = lDocAddedCompDomi;
	}
	public String getDispCheckCredFm2() {
		return dispCheckCredFm2;
	}
	public void setDispCheckCredFm2(String dispCheckCredFm2) {
		this.dispCheckCredFm2 = dispCheckCredFm2;
	}
	public String getDispCheckCompActEcon() {
		return dispCheckCompActEcon;
	}
	public void setDispCheckCompActEcon(String dispCheckCompActEcon) {
		this.dispCheckCompActEcon = dispCheckCompActEcon;
	}
	public String getDispCheckCompIncome() {
		return dispCheckCompIncome;
	}
	public void setDispCheckCompIncome(String dispCheckCompIncome) {
		this.dispCheckCompIncome = dispCheckCompIncome;
	}
	public String getDispCheckAcredProBusiness() {
		return dispCheckAcredProBusiness;
	}
	public void setDispCheckAcredProBusiness(String dispCheckAcredProBusiness) {
		this.dispCheckAcredProBusiness = dispCheckAcredProBusiness;
	}
	public String getDispCheckCompDomi() {
		return dispCheckCompDomi;
	}
	public void setDispCheckCompDomi(String dispCheckCompDomi) {
		this.dispCheckCompDomi = dispCheckCompDomi;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Integer getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
	}
	public Integer getCatStr() {
		return catStr;
	}
	public void setCatStr(Integer catStr) {
		this.catStr = catStr;
	}
	public Double getTasaTotal() {
		return tasaTotal;
	}
	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}
	public Integer getTerm_id() {
		return term_id;
	}
	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}
	public Integer getPurpose_id() {
		return purpose_id;
	}
	public void setPurpose_id(Integer purpose_id) {
		this.purpose_id = purpose_id;
	}
	public DealBean getSelectedDeal() {
		return selectedDeal;
	}
	public void setSelectedDeal(DealBean selectedDeal) {
		this.selectedDeal = selectedDeal;
	}
	public DealBean getBeanSelOther() {
		return beanSelOther;
	}
	public void setBeanSelOther(DealBean beanSelOther) {
		this.beanSelOther = beanSelOther;
	}
	public ProyectLoan getMyPyLn() {
		return myPyLn;
	}
	public void setMyPyLn(ProyectLoan myPyLn) {
		this.myPyLn = myPyLn;
	}
	public Proyect getMyPy() {
		return myPy;
	}
	public void setMyPy(Proyect myPy) {
		this.myPy = myPy;
	}
	public Double getMontoMin() {
		return montoMin;
	}
	public void setMontoMin(Double montoMin) {
		this.montoMin = montoMin;
	}
	public Integer getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Integer publishTime) {
		this.publishTime = publishTime;
	}
	public String getFundingType() {
		return fundingType;
	}
	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}
	public Scoring getScore() {
		return score;
	}
	public void setScore(Scoring score) {
		this.score = score;
	}
	public String getAccountDisp() {
		return accountDisp;
	}
	public void setAccountDisp(String accountDisp) {
		this.accountDisp = accountDisp;
	}
	public String getSelAccount() {
		return selAccount;
	}
	public void setSelAccount(String selAccount) {
		this.selAccount = selAccount;
	}
	public boolean isHasAccount() {
		return hasAccount;
	}
	public void setHasAccount(boolean hasAccount) {
		this.hasAccount = hasAccount;
	}
	public List<ClabeAccount> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<ClabeAccount> accountList) {
		this.accountList = accountList;
	}
	public ClabeAccount getThisClabe() {
		return thisClabe;
	}
	public void setThisClabe(ClabeAccount thisClabe) {
		this.thisClabe = thisClabe;
	}
	public String getErrorDisp() {
		return errorDisp;
	}
	public void setErrorDisp(String errorDisp) {
		this.errorDisp = errorDisp;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getSuccessDisp() {
		return successDisp;
	}
	public void setSuccessDisp(String successDisp) {
		this.successDisp = successDisp;
	}
	public String getBeginDisp() {
		return beginDisp;
	}
	public void setBeginDisp(String beginDisp) {
		this.beginDisp = beginDisp;
	}
	public String getContResp() {
		return contResp;
	}
	public void setContResp(String contResp) {
		this.contResp = contResp;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFormaPagoCom() {
		return formaPagoCom;
	}
	public void setFormaPagoCom(String formaPagoCom) {
		this.formaPagoCom = formaPagoCom;
	}
	public String getMontoRecibido() {
		return montoRecibido;
	}
	public void setMontoRecibido(String montoRecibido) {
		this.montoRecibido = montoRecibido;
	}
	public Double getComision() {
		return comision;
	}
	public void setComision(Double comision) {
		this.comision = comision;
	}
	public Double getRatewithCom() {
		return ratewithCom;
	}
	public void setRatewithCom(Double ratewithCom) {
		this.ratewithCom = ratewithCom;
	}
	public boolean isDisplayBtnDeal() {
		return displayBtnDeal;
	}
	public void setDisplayBtnDeal(boolean displayBtnDeal) {
		this.displayBtnDeal = displayBtnDeal;
	}
	public List<AccessCollector> getMenuIncomplete() {
		return menuIncomplete;
	}
	public void setMenuIncomplete(List<AccessCollector> menuIncomplete) {
		this.menuIncomplete = menuIncomplete;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public java.text.NumberFormat getNum() {
		return num;
	}
	public void setNum(java.text.NumberFormat num) {
		this.num = num;
	}
	public java.text.NumberFormat getDec() {
		return dec;
	}
	public void setDec(java.text.NumberFormat dec) {
		this.dec = dec;
	}
	/*
	public RegistroMailLogService getMailService() {
		return mailService;
	}
	public void setMailService(RegistroMailLogService mailService) {
		this.mailService = mailService;
	}
	*/
}
