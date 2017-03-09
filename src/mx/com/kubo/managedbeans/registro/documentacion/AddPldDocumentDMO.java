package mx.com.kubo.managedbeans.registro.documentacion;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.FileForScreenBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.files.CatalogoFileType;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.FileCategory;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PospectusComment;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.IdentificationType;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.AutomaticInvestmentService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.CapitalNetoService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.IncomeTypeService;
import mx.com.kubo.services.InfoNotificationService;
import mx.com.kubo.services.InfoScreenService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.PospectusCommentService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProyectLoanInfoService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.RiskTaskService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.services.impl.ExpensesTypeServiceImp;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

public abstract class AddPldDocumentDMO 
{
	
	protected final int TAREA1 = 1;
	
	protected final int TAREA2 = 2 ;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService  employmentService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService        service_address;
	
	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService businessservice;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService service_natural_person;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService prevencionldservice;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{capitalNetoServiceImp}")
	protected CapitalNetoService capitalnetoservice;
	
	@ManagedProperty("#{service_catalogos}")
	protected ServiceCatalogosIMO service_catalogos;
	
	@ManagedProperty("#{incomeTypeServiceImp}")
	protected IncomeTypeService service_income_type;
	
	@ManagedProperty("#{incomeServiceImp}")
	protected IncomeService ingresosService;
	
	@ManagedProperty("#{expensesServiceImp}")
	protected ExpensesService egresosService;
	
	@ManagedProperty("#{expensesTypeServiceImp}")
	protected ExpensesTypeServiceImp expensesTypeService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{proyectLoanInfoServiceImp}")
	protected ProyectLoanInfoService proyectLoanInfoService;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoreservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{automaticInvestmentServiceImp}")
	protected   	AutomaticInvestmentService service;
	
	@ManagedProperty("#{infoNotificationServiceImp}")
	protected InfoNotificationService infonotificationservice;
	
	@ManagedProperty("#{infoScreenServiceImp}")
	protected InfoScreenService infoscreenservice;
	
	@ManagedProperty("#{pospectusCommentServiceImp}")
	protected PospectusCommentService pospectuscommentservice;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService service_telefono;
	
	@ManagedProperty("#{riskTaskServiceImp}")
	protected  RiskTaskService	risktaskservice;
	
	protected ProyectLoanInfo info;
	protected Investor inv;
	
	protected HtmlInputText      input_text;
	protected HtmlSelectOneRadio input_radio;
	
	protected SessionBean   sesion;
	protected PrevencionLD  prevencionld;
	
	protected Logger log = Logger.getLogger(getClass());
	
	protected NaturalPerson naturalperson;
	protected Membership membership;
	protected ProyectLoan   thisProyectLoan;
	protected Proyect       thisProyect;
	protected Files         thisFiles;	
	private UploadedFile  fileLogo;	
	private Locale        locale;
	protected  NumberFormat  dec, num;
	
	protected FacesContext faces;
	protected RequestContext request;
	protected ELContext context;
	protected ELResolver resolver;
	protected ExternalContext external;
	
	protected gnNaturalPersonPK natural_person_PK;
	
	protected final String catalogo_meses_nombres[];
	
	protected StringBuilder sb;
	
	protected String maximum_balanceStr;
	protected String maximum_withdrawStr;
	protected String maximum_depositStr;	
	protected String uploaded_text;
	protected String third_party_name;
	protected String pr_first_name;
	protected String pr_father_last_name;
	protected String pr_mother_last_name;
	protected String horaInicioSys = "";
	protected String horaTerminoSys = "";
		
	protected List<ImagesBean>   listImgLogos; 
	protected List<Employment>   listEmployment;
	protected List<Business>     listBusiness;	
	protected List<FileCategory> listCategory;		
	protected List<Files>        listFiles;	
	protected List<FileType>     listCboIncome;
	protected List<FileType>     listCREFM2;		
	protected List<FileType>     listCredFm2;	
	protected List<FileType>     listCompIncome;
	protected List<FileType>     listAcredProBusiness;
	protected List<FileType>     listCompDomi;
	protected List<FileType>     listCompActEcon;
	protected List<IdentificationType> lista_tipo_credencial;
	
	protected List<String> lista_dias;
	protected List<String> lista_meses;
	protected List<String> lista_years;
	
	protected CatalogoFileType file_type;
	protected PrevencionLDPK prevPK;
	protected ImagesBean imgLogos;
	protected Hashtable<String, Integer> htWH;
		
	protected File thumbFile = null;
	protected File origFile = null;
	
	protected Files regis_Dom ;
	
	protected String origFilePath = "";
	protected String formatFile = "";
	
	protected String displayKuboRelative;
	protected String displayPepRelative;	
	protected String tiCredFm2;
	protected String numCredFm2;
	protected String tiCompActEcon;
	protected String numCompActEcon;
	protected String tiCompIncome;
	protected String numCompIncome;
	protected String tiAcredProBusiness;
	protected String numAcredProBusiness;
	protected String tiCompDomi;
	protected String numCompDomi;
	protected String dispCheckCredFm2;
	protected String dispCheckCompActEcon;
	protected String dispCheckCompIncome;
	protected String dispCheckAcredProBusiness;
	protected String dispCheckCompDomi;	
	protected String displayBussiness;
	protected String realPath;
	protected String clave_elector;
	protected String mx_ife_numvertical;	
	protected String numero_emision;
	protected String ife_seccion;
	
	protected String dayD   = "0";
	protected String monthD = "0";
	protected String yearD  = "0";
	
	protected SimpleDateFormat frm;
	
	private String prevMovement_frequency;
	private String prevMaximum_depositStr;
	private String prevMaximum_withdrawStr;
	private String prevMaximum_balanceStr;
	private String dispCheckImgLog;	
	private String imgLogo;
	private String thisDocumentId;
	private String deleteString;
	
	protected String direccion;
	
	protected List<FileForScreenBean> lDocAddedCredFm2;
	protected List<FileForScreenBean> lDocAddedCompActEcon;
	protected List<FileForScreenBean> lDocAddedCompIncome;
	protected List<FileForScreenBean> lDocAddedAcredProBusiness;
	protected List<FileForScreenBean> lDocAddedCompDomi;
	
	protected Integer mx_ife_numemision;
	protected Integer tipo_credencial;
	protected String sameAddress;
	
	private int typeCredFm2;
	private int typeCompActEcon;
	private int typeCompIncome;
	private int typeAcredProBusiness;
	private int typeCompDomi;
	
	protected int identifyTypeId;
	
	protected int totalEmploy;
	protected int totalBusiness;
	protected int file_type_id;
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_loan_id;
	protected int economicActivity;
	
	protected static final int NACIONAL   = 1;
	protected static final int EXTRANJERO = 0;
	protected static final int HELP_NOTIFICATION_SCREEN = 1;
	protected static final int HELP_SEND_NOTIFICATION = 2;
	protected static final int NO_HELP_NOTIFICATION = 3;
	
	private boolean hasPLD;
	private boolean otherTitle;
	protected boolean datos_credencial_elector_ENABLED;
	protected boolean enabled_whatsapp;
	
	protected boolean tarea1 = true;
	
	protected String dispSelfieIdentificacion = "";
	protected FileForScreenBean selfieIdentification;
	
	protected PospectusComment prospectuscomment;
	
	protected boolean has_prospectuscomment;
	
	protected AddPldDocumentDMO()
	{		
		locale = new Locale("es","mx");
		dec    = NumberFormat.getCurrencyInstance(locale);
		num    = NumberFormat.getNumberInstance(locale);
		
		dispCheckImgLog           = "none";
		dispCheckCredFm2          = "none";
		dispCheckCompActEcon      = "none";
		dispCheckCompIncome       = "none";
		dispCheckAcredProBusiness = "none";
		dispCheckCompDomi         = "none";
		displayBussiness          = "none";
		displayKuboRelative       = "none";
		displayPepRelative        = "none";
		dispSelfieIdentificacion  = "none";
		
		tiCredFm2           = "";
		numCredFm2          = "";
		tiCompActEcon       = "";
		numCompActEcon      = "";
		tiCompIncome        = "";
		numCompIncome       = "";
		tiAcredProBusiness  = "";
		numAcredProBusiness = "";
		tiCompDomi          = "";
		numCompDomi         = "";
		
		catalogo_meses_nombres = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	}
	
	public NaturalPerson getNaturalperson() 
	{
		return naturalperson;
	} 
	
	public boolean isDatos_credencial_elector_ENABLED()
	{
		return datos_credencial_elector_ENABLED;
	}
	
	public final String getThird_party_name()
	{
		return third_party_name;
	}
	
	public void setNaturalperson( NaturalPerson naturalperson ) 
	{
		this.naturalperson = naturalperson;
	} 
	
	public List<IdentificationType> getLista_tipo_credencial() 
	{
		return lista_tipo_credencial;
	}

	public List<Employment> getListEmployment() {
		return listEmployment;
	}
	
	public List<Business> getListBusiness() {
		return listBusiness;
	}
	
	public List<FileType> getListCompActEcon() {
		return listCompActEcon;
	}
	
	public List<FileType> getListAcredProBusiness() {
		return listAcredProBusiness;
	}
	
	public List<FileType> getListCompDomi() {
		return listCompDomi;
	}
	
	public List<FileType> getListCompIncome() {
		return listCompIncome;
	}
	
	public List<FileCategory> getListCategory() {
		return listCategory;
	} 
	
	public int getTotalEmploy() {
		return totalEmploy;
	} 
	
	public int getTotalBusiness() {
		return totalBusiness;
	} 
	
	public int getTypeCompActEcon() {
		return typeCompActEcon;
	} 
	
	public int getTypeCompIncome() {
		return typeCompIncome;
	} 
	
	public int getTypeAcredProBusiness() {
		return typeAcredProBusiness;
	} 
	
	public int getTypeCompDomi() {
		return typeCompDomi;
	} 
	
	public String getTiCredFm2() {
		return tiCredFm2;
	} 
	
	public String getTiCompActEcon() {
		return tiCompActEcon;
	}
	
	public String getTiCompIncome() {
		return tiCompIncome;
	}
	
	public String getTiAcredProBusiness() {
		return tiAcredProBusiness;
	}
	
	public String getTiCompDomi() {
		return tiCompDomi;
	}
	
	public String getDisplayBussiness() {
		return displayBussiness;
	}
	
	public List<FileType> getListCboIncome() {
		return listCboIncome;
	} 
	
	public void setListEmployment(List<Employment> listEmployment) {
		this.listEmployment = listEmployment;
	}
	
	public void setListBusiness(List<Business> listBusiness) {
		this.listBusiness = listBusiness;
	}
	
	public void setListCompActEcon(List<FileType> listCompActEcon) {
		this.listCompActEcon = listCompActEcon;
	}
	
	public void setListAcredProBusiness(List<FileType> listAcredProBusiness) {
		this.listAcredProBusiness = listAcredProBusiness;
	}
	
	public void setListCompIncome(List<FileType> listCompIncome) {
		this.listCompIncome = listCompIncome;
	}
	
	public void setListCategory(List<FileCategory> listCategory) {
		this.listCategory = listCategory;
	}
	
	public void setTypeCompActEcon(int typeCompActEcon) {
		this.typeCompActEcon = typeCompActEcon;
	}
	
	public void setTypeCompIncome(int typeCompIncome) {
		this.typeCompIncome = typeCompIncome;
	}
	
	public void setTypeAcredProBusiness(int typeAcredProBusiness) {
		this.typeAcredProBusiness = typeAcredProBusiness;
	}
	
	public void setTypeCompDomi(int typeCompDomi) {
		this.typeCompDomi = typeCompDomi;
	}
						
	public void setListCboIncome(List<FileType> listCboIncome) {
		this.listCboIncome = listCboIncome;
	} 	

	public Proyect getThisProyect() {
		return thisProyect;
	}
	public void setThisProyect(Proyect thisProyect) {
		this.thisProyect = thisProyect;
	}
	
	public Files getThisFiles() {
		return thisFiles;
	}
	public void setThisFiles(Files thisFiles) {
		this.thisFiles = thisFiles;
	}
	
	public List<Files> getListFiles() {
		return listFiles;
	}
	
	public String getDispCheckCredFm2() {
		return dispCheckCredFm2;
	}
	
	public String getDispCheckCompActEcon() {
		return dispCheckCompActEcon;
	}
	
	public String getDispCheckCompIncome() {
		return dispCheckCompIncome;
	}
	
	public String getDispCheckAcredProBusiness() {
		return dispCheckAcredProBusiness;
	}
	
	public String getDispCheckCompDomi() {
		return dispCheckCompDomi;
	}
	
	public void setListFiles(List<Files> listFiles) {
		this.listFiles = listFiles;
	}
						
	public String getImgLogo() {
		return imgLogo;
	}
	
	public void setImgLogo(String imgLogo) {
		this.imgLogo = imgLogo;
	}
	
	public List<FileForScreenBean> getlDocAddedCompActEcon() {
		return lDocAddedCompActEcon;
	}
	
	public List<FileForScreenBean> getlDocAddedCompIncome() {
		return lDocAddedCompIncome;
	}
	
	public List<FileForScreenBean> getlDocAddedAcredProBusiness() {
		return lDocAddedAcredProBusiness;
	}
	
	public List<FileForScreenBean> getlDocAddedCompDomi() {
		return lDocAddedCompDomi;
	}
	
	public List<FileType> getListCredFm2() {
		return listCredFm2;
	}
	
	public List<FileForScreenBean> getlDocAddedCredFm2() {
		return lDocAddedCredFm2;
	}
	
	public int getTypeCredFm2() {
		return typeCredFm2;
	}
	
	public List<FileType> getListCREFM2() {
		return listCREFM2;
	}
	
	public void setListCredFm2(List<FileType> listCredFm2) {
		this.listCredFm2 = listCredFm2;
	}
	
	public void setTypeCredFm2(int typeCredFm2) {
		this.typeCredFm2 = typeCredFm2;
	}
	
	public void setListCREFM2(List<FileType> listCREFM2) {
		this.listCREFM2 = listCREFM2;
	}
	
	public String getThisDocumentId() {
		return thisDocumentId;
	}
	
	public void setThisDocumentId(String thisDocumentId) {
		this.thisDocumentId = thisDocumentId;
	}
	
	public String getNumCredFm2() {
		return numCredFm2;
	}
	
	public String getNumCompActEcon() {
		return numCompActEcon;
	}
	
	public String getNumCompIncome() {
		return numCompIncome;
	}
	
	public String getNumAcredProBusiness() {
		return numAcredProBusiness;
	}
	
	public String getNumCompDomi() {
		return numCompDomi;
	}
	
	public void setNumCompIncome(String numCompIncome) {
		this.numCompIncome = numCompIncome;
	}
			
	public String getDispCheckImgLog() {
		return dispCheckImgLog;
	}
	
	public void setDispCheckImgLog(String dispCheckImgLog) {
		this.dispCheckImgLog = dispCheckImgLog;
	}
	
	public List<ImagesBean> getListImgLogos() {
		return listImgLogos;
	}
	
	public void setListImgLogos(List<ImagesBean> listImgLogos) {
		this.listImgLogos = listImgLogos;
	}
	
	public boolean isOtherTitle() {
		return otherTitle;
	}
	
	public void setOtherTitle(boolean otherTitle) {
		this.otherTitle = otherTitle;
	}
	
	public String getPrevMovement_frequency() {
		return prevMovement_frequency;
	}
	
	public void setPrevMovement_frequency(String prevMovement_frequency) {
		this.prevMovement_frequency = prevMovement_frequency;
	}
	
	public String getPrevMaximum_depositStr() {
		return prevMaximum_depositStr;
	}
	
	public void setPrevMaximum_depositStr(String prevMaximum_depositStr) {
		this.prevMaximum_depositStr = prevMaximum_depositStr;
	}
	
	public String getPrevMaximum_withdrawStr() {
		return prevMaximum_withdrawStr;
	}
	
	public void setPrevMaximum_withdrawStr(String prevMaximum_withdrawStr) {
		this.prevMaximum_withdrawStr = prevMaximum_withdrawStr;
	}
	
	public String getPrevMaximum_balanceStr() {
		return prevMaximum_balanceStr;
	}
	
	public void setPrevMaximum_balanceStr(String prevMaximum_balanceStr) {
		this.prevMaximum_balanceStr = prevMaximum_balanceStr;
	}	

	public ProyectLoan getThisProyectLoan() {
		return thisProyectLoan;
	}

	public void setThisProyectLoan(ProyectLoan thisProyectLoan) {
		this.thisProyectLoan = thisProyectLoan;
	}
	
	public boolean isHasPLD() {
		return hasPLD;
	}
	
	public void setHasPLD(boolean hasPLD) {
		this.hasPLD = hasPLD;
	}
	
	public PrevencionLD getPrevencionld() {
		return prevencionld;
	}
	
	public void setPrevencionld(PrevencionLD prevencionld) {
		this.prevencionld = prevencionld;
	}
	
	public void setDisplayKuboRelative(String displayKuboRelative) {
		this.displayKuboRelative = displayKuboRelative;
	}
	
	public UploadedFile getFileLogo() {
		return fileLogo;
	}

	public void setFileLogo(UploadedFile fileLogo) {
		this.fileLogo = fileLogo;
	}
	
	public void setDisplayPepRelative(String displayPepRelative) {
		this.displayPepRelative = displayPepRelative;
	}
	
	public void setEmploymentService(EmploymentService service) 
	{
		employmentService = service;
	} 
	
	public void setBusinessservice(BusinessService service) 
	{
		businessservice = service;
	} 
	
	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	} 
	
	public void setFileTypeService(FileTypeService service) 
	{
		fileTypeService = service;
	} 
	
	public void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}
	
	public void setFilesService(FilesService service) 
	{
		filesService = service;
	}

	public void setChangeControlService(Change_controlService service) 
	{
		changeControlService = service;
	}
	
	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}
	
	public void setPrevencionldservice(PrevencionLDService service) 
	{
		prevencionldservice = service;
	}

	public void setCapitalnetoservice(CapitalNetoService service) 
	{
		capitalnetoservice = service;
	}

	public void setService_catalogos(ServiceCatalogosIMO service) 
	{
		service_catalogos = service;
	}

	public String getDeleteString() {
		return deleteString;
	}

	public void setDeleteString(String deleteString) {
		this.deleteString = deleteString;
	}
	
	public boolean isEnabled_whatsapp() {
		return enabled_whatsapp;
	}

	public void setEnabled_whatsapp(boolean enabled_whatsapp) {
		this.enabled_whatsapp = enabled_whatsapp;
	}

	public IncomeTypeService getService_income_type() {
		return service_income_type;
	}

	public void setService_income_type(IncomeTypeService service_income_type) {
		this.service_income_type = service_income_type;
	}

	public IncomeService getIngresosService() {
		return ingresosService;
	}

	public void setIngresosService(IncomeService ingresosService) {
		this.ingresosService = ingresosService;
	}

	public ExpensesService getEgresosService() {
		return egresosService;
	}

	public void setEgresosService(ExpensesService egresosService) {
		this.egresosService = egresosService;
	}

	public ExpensesTypeServiceImp getExpensesTypeService() {
		return expensesTypeService;
	}

	public void setExpensesTypeService(ExpensesTypeServiceImp expensesTypeService) {
		this.expensesTypeService = expensesTypeService;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public FileForScreenBean getSelfieIdentification() {
		return selfieIdentification;
	}

	public void setSelfieIdentification(FileForScreenBean selfieIdentification) {
		this.selfieIdentification = selfieIdentification;
	}

	public void setDispCheckAcredProBusiness(String dispCheckAcredProBusiness) {
		this.dispCheckAcredProBusiness = dispCheckAcredProBusiness;
	}

	public String getDispSelfieIdentificacion() {
		return dispSelfieIdentificacion;
	}

	public void setDispSelfieIdentificacion(String dispSelfieIdentificacion) {
		this.dispSelfieIdentificacion = dispSelfieIdentificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSameAddress() {
		return sameAddress;
	}

	public void setSameAddress(String sameAddress) {
		this.sameAddress = sameAddress;
	}

	public ProyectLoanInfoService getProyectLoanInfoService() {
		return proyectLoanInfoService;
	}

	public void setProyectLoanInfoService(ProyectLoanInfoService proyectLoanInfoService) {
		this.proyectLoanInfoService = proyectLoanInfoService;
	}

	public InvestorService getInvestorservice() {
		return investorservice;
	}

	public void setInvestorservice(InvestorService investorservice) {
		this.investorservice = investorservice;
	}

	public List<String> getLista_dias() {
		return lista_dias;
	}

	public void setLista_dias(List<String> lista_dias) {
		this.lista_dias = lista_dias;
	}

	public List<String> getLista_meses() {
		return lista_meses;
	}

	public void setLista_meses(List<String> lista_meses) {
		this.lista_meses = lista_meses;
	}

	public List<String> getLista_years() {
		return lista_years;
	}

	public void setLista_years(List<String> lista_years) {
		this.lista_years = lista_years;
	}

	public String getDayD() {
		return dayD;
	}

	public void setDayD(String dayD) {
		this.dayD = dayD;
	}

	public String getMonthD() {
		return monthD;
	}

	public void setMonthD(String monthD) {
		this.monthD = monthD;
	}

	public String getYearD() {
		return yearD;
	}

	public void setYearD(String yearD) {
		this.yearD = yearD;
	}

	public ScoringService getScoreservice() {
		return scoreservice;
	}

	public void setScoreservice(ScoringService scoreservice) {
		this.scoreservice = scoreservice;
	}

	public AutomaticInvestmentService getService() {
		return service;
	}

	public void setService(AutomaticInvestmentService service) {
		this.service = service;
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}

	public String getHoraInicioSys() {
		return horaInicioSys;
	}

	public void setHoraInicioSys(String horaInicioSys) {
		this.horaInicioSys = horaInicioSys;
	}

	public String getHoraTerminoSys() {
		return horaTerminoSys;
	}

	public void setHoraTerminoSys(String horaTerminoSys) {
		this.horaTerminoSys = horaTerminoSys;
	}

	public InfoNotificationService getInfonotificationservice() {
		return infonotificationservice;
	}

	public void setInfonotificationservice(InfoNotificationService infonotificationservice) {
		this.infonotificationservice = infonotificationservice;
	}

	public InfoScreenService getInfoscreenservice() {
		return infoscreenservice;
	}

	public void setInfoscreenservice(InfoScreenService infoscreenservice) {
		this.infoscreenservice = infoscreenservice;
	}

	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}

	public void setServicecallingService(ServiceCallingService servicecallingService) {
		this.servicecallingService = servicecallingService;
	}

	public PhoneService getService_telefono() {
		return service_telefono;
	}

	public void setService_telefono(PhoneService service_telefono) {
		this.service_telefono = service_telefono;
	}

	public AddressService getService_address() {
		return service_address;
	}

	public void setService_address(AddressService service_address) {
		this.service_address = service_address;
	}

	public int getIdentifyTypeId() {
		return identifyTypeId;
	}

	public void setIdentifyTypeId(int identifyTypeId) {
		this.identifyTypeId = identifyTypeId;
	}

	public PospectusCommentService getPospectuscommentservice() {
		return pospectuscommentservice;
	}

	public void setPospectuscommentservice(PospectusCommentService pospectuscommentservice) {
		this.pospectuscommentservice = pospectuscommentservice;
	}

	public PospectusComment getProspectuscomment() {
		return prospectuscomment;
	}

	public void setPospectuscomment(PospectusComment prospectuscomment) {
		this.prospectuscomment = prospectuscomment;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public RiskTaskService getRisktaskservice() {
		return risktaskservice;
	}

	public void setRisktaskservice(RiskTaskService risktaskservice) {
		this.risktaskservice = risktaskservice;
	}

	public boolean isTarea1() {
		return tarea1;
	}

	public void setTarea1(boolean tarea1) {
		this.tarea1 = tarea1;
	}
	
}
