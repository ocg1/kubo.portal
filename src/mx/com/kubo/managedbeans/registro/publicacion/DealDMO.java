package mx.com.kubo.managedbeans.registro.publicacion;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.DealBean;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMO;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
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
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.impl.SystemParamServiceImp;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;
import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

public abstract class DealDMO 
implements DealIMO
{
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{bankServiceImp}")
	protected BankService bankService;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	protected ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService addressService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	@ManagedProperty("#{townServiceImp}")
	protected TownService townService;

	@ManagedProperty("#{neighborhoodServiceImp}")
	protected NeighborhoodService neighborhoodService;
	
	@ManagedProperty("#{serviceCallingDaoImp}")
	protected ServiceCallingDao servicecallingRepository;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
		
	protected NotificadorIMO notificador;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamServiceImp system_param_service;			
		
	protected ReasignadorIMO reasignador;
	protected NavigationBeanIMO  menu_registro_service;
	
	protected FacesContext faces;
	protected ELContext    elContext;
	protected ELResolver   resolver;	
	protected ExternalContext external;
			
	protected SessionBean       sesion;		
	protected Prospectus        prospectus;
	protected ProspectusPK      prosPK;
	protected Membership        membership;
	protected MembershipPK      membership_PK;
	protected NaturalPerson     natural_person;
	protected gnNaturalPersonPK natural_person_PK;

	protected Proyect       proyect;
	protected ProyectLoan   proyect_loan_reasignable;
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected EstatusProyectLoan estatus;
	protected Scoring      score;
				
	protected DealBean       selectedDeal;
	protected ClabeAccount   thisClabe;
	protected ResourceBundle recurso;
	protected MenuRegBean    menureq;
	
	protected SAFIServiciosServiceLocator   locator;
	protected SAFIServicios                 service;
	protected ServiceCalling                srvCall;
	
	protected Simulator                     simulator;
	protected SimulatorBean  				 sim;
	protected SimuladorCuotaCreditoRequest  simulador;		
	protected SimuladorCuotaCreditoResponse response;
		
	protected Locale locale;
	
	protected SimpleDateFormat format;
	protected SimpleDateFormat formatStr;
	
	protected NumberFormat num;
	protected NumberFormat dec;
	
	protected List<MenuRegBean>     listRequiredMenu;
	protected List<DealBean>        dealList;
	protected List<AccessCollector> menuIncomplete;
	protected List<ClabeAccount>    accountList;
	
	protected String monto;
	protected String kubo_score_A;
	protected String kubo_score_B;
	protected String bur_sol_num;
	protected String cci_score;
	protected String nota;
	
	protected Double liquidez;
	protected Double comision_apertura;
	protected Double tasa;
	protected Double tasa_inversion;
	protected Double cat;
	protected Double tasa_apertura;
	
	protected Integer bc_score;
	protected Integer metodo_deposito_id;
	protected Integer term_id;	
	
	protected Integer termSel;
	protected Integer frequencySel;
	protected Boolean dispInvestor; 

	protected Double amount;
	protected Double montoMin;
	protected Double monto_minimo;
	protected Double pagos;
	protected Double comision_apertura_monto;
	
	protected Integer promotor_id;
		
	protected double rate;	
	
	protected int proyect_id;
	protected int proyect_loan_id;		
	protected int prospectus_id;
	protected int company_id;
	protected int change_prospectus_id;
	
	protected char area;	
	protected char tipo_fondeo;
	
	protected boolean flag;
	protected boolean documents_reviwed_OK;	
	protected boolean success_OK = false;
	
	protected final boolean VALIDACION_VIGENCIA_ENABLED = true;
	
	private DealBean         beanSelOther;
	private NaturalPerson    naturalPerson;
	private Phone            thisPhoneFixed;
	private Address          thisAddress; 
	private SystemParamPK    system_param_PK;	
	
	private Calendar         calendar;
	private SimpleDateFormat formatter_date;
	private DecimalFormat    decimal_format;
	
	private List<Files>  listFiles;
	
	private List<String> lDocAddedCredFm2;
	private List<String> lDocAddedCompActEcon;
	private List<String> lDocAddedCompIncome;
	private List<String> lDocAddedAcredProBusiness;
	private List<String> lDocAddedCompDomi;
	
	private Date 
		fecha_pospuesta,
		fecha_de_cancelacion, 
		fecha_en_cancelacion;
		
	protected String fundingType = "T";	
	private String accountDisp = "block";			
	private String errorDesc;
	private String selAccount;	
	private String fechaInicio   =	"";
	protected String formaPagoCom  = "D"; //descuento
	private String montoRecibido = "";	
	private String dispCheckCredFm2;
	private String dispCheckCompActEcon;
	private String dispCheckCompIncome;
	private String dispCheckAcredProBusiness;
	private String dispCheckCompDomi;
	private String system_param;
	private String target_item;
	private String screen_id;
	private String id_item;
	private String diferencia_fecha_pospuesta;
	private String amountSel;
	private Double ammount;
	private Double tasaTotal;	
	private Double comision    = 0D;
	private Double ratewithCom = 0D;
	
	protected Integer frequency_id;
	private Integer catStr;
	private Integer purpose_id;
	private Integer publishTime;

	protected boolean hasAccount;
	private boolean reanudar_solicitud_ENABLED = false;
	private boolean displayBtnDeal     = false;
	private boolean contResp           = false;
	private boolean beginDisp          = false;
	private boolean errorDisp          = false;
	private boolean successDisp        = false;
	private boolean proyecto_cancelado = false;
	private boolean proyecto_pospuesto = false;
	private boolean proyecto_desistido = false;
	
	protected boolean proyecto_rechazado_automaticamente = false;
	
	protected DealDMO()
	{
		decimal_format = new DecimalFormat("##");
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
		locale         = new Locale("es","mx");
		
		num = NumberFormat.getNumberInstance(locale);
		dec = NumberFormat.getCurrencyInstance(locale);		
		
		target_item  = "registro/historialCred";
		screen_id    = "3";
		id_item      = "menu2";
	}

	public final void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}

	public final void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}
	
	public final void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}
	
	public final void setProspectusService(ProspectusService service) 
	{
		prospectusService = service;
	}

	public final void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}

	public final void setAddressService(AddressService service) 
	{
		addressService = service;
	}

	public final void setPhoneService(PhoneService service) 
	{
		phoneService = service;
	}

	public final void setTownService(TownService service) 
	{
		townService = service;
	}
	
	public final void setNeighborhoodService(NeighborhoodService service) 
	{
		neighborhoodService = service;
	}

	public final void setProyectloanservice(ProyectLoanService service) 
	{
		proyectloanservice = service;
	}

	public final void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}

	public final void setProyectservice(ProyectService service) 
	{
		proyectservice = service;
	}

	public final void setFileTypeService(FileTypeService service) 
	{
		fileTypeService = service;
	}

	public final void setFilesService(FilesService service) 
	{
		filesService = service;
	}

	public final void setAccessService(AccessService service) 
	{
		accessService = service;
	}
	
	public final void setSystem_param_service(SystemParamServiceImp service) 
	{
		system_param_service = service;
	}

	public final void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}	
	
	public final void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}
	
	public final void setClabeaccountservice(ClabeAccountService service) 
	{
		clabeaccountservice = service;
	}
	
	public final void setServicecallingRepository(ServiceCallingDao service) 
	{
		servicecallingRepository = service;
	}
	
	public final void setBankService(BankService service) 
	{
		bankService = service;
	}

	public final void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}
	
	protected final void setReanudar_solicitud_ENABLED(boolean bandera)
	{
		reanudar_solicitud_ENABLED = bandera;
	}
	
	public final boolean isReanudar_solicitud_ENABLED()
	{																
		return reanudar_solicitud_ENABLED;
	}
	
	protected final Date getFecha_pospuesta()
	{
		if(proyect_loan_reasignable.getPosposed_date() != null)
		{
			fecha_pospuesta = proyect_loan_reasignable.getPosposed_date();
		} else {
			fecha_pospuesta = getFecha_en_cancelacion();
		}
		
		return fecha_pospuesta;
	}
	
	protected final Date getFecha_preaprobacion()
	{
		if(proyect_loan_reasignable.getPreapproved_date() != null)
		{
			fecha_pospuesta = proyect_loan_reasignable.getPreapproved_date();
		} else {
			fecha_pospuesta = getFecha_en_cancelacion();
		}
		
		return fecha_pospuesta;
	}
	
	protected final Date getFecha_consulta()
	{
		
		Calendar c = Calendar.getInstance();
		c.setTime(proyect_loan_reasignable.getConsulting_date());
		c.add(Calendar.DAY_OF_YEAR,  getDiasCanceladosDefault() );
		 
		fecha_pospuesta = c.getTime();
		
		return fecha_pospuesta;
	}
		
	protected final Date getFecha_en_cancelacion()
	{
		calendar = Calendar.getInstance();	
		
		fecha_de_cancelacion = proyect_loan_reasignable.getDay_published();
		
		calendar.setTime(fecha_de_cancelacion);		
		calendar.add(Calendar.DAY_OF_YEAR, getDiasCanceladosDefault());
		
		fecha_en_cancelacion = calendar.getTime();
		
		System.out.println("\nDealDMO.getFecha_en_cancelacion(): " + formatter_date.format(fecha_en_cancelacion));
		
		return fecha_en_cancelacion;
	}
	
	public final String getDiferencia_fecha_pospuesta()
	{
		Calendar pospuesta = Calendar.getInstance();
	    Calendar today     = Calendar.getInstance();
	    
	    pospuesta.setTime(getFecha_pospuesta());
	    
	    decimal_format.setRoundingMode(RoundingMode.DOWN);
	    
	    long pospuesta_milis = pospuesta.getTimeInMillis();
	    long today_milis     = today.getTimeInMillis();	    
	    long diff            = pospuesta_milis - today_milis;
/*	    
	    long diffSeconds = diff / 1000;
	    long diffMinutes = diff / (60 * 1000);
	    long diffHours   = diff / (60 * 60 * 1000);
*/	    
	    long diffDays    = diff / (24 * 60 * 60 * 1000);	  
	    
	    if(diffDays > 1)
	    {	    	   		    		    
		    if(diffDays / 30 > 0)
		    {
		    	if(diffDays / 30 > 1)
		    	{
		    		diferencia_fecha_pospuesta = decimal_format.format(diffDays / 30) + " meses";
		    		
		    	} else {
		    		
		    		diferencia_fecha_pospuesta = "1 mes";
		    	}
		    		    	
		    } else {
		    	
		    	diferencia_fecha_pospuesta = decimal_format.format(diffDays) + " días";
		    	
		    }
		    
	    } else {
	    	
	    	diferencia_fecha_pospuesta = "1 día";
	    }
	    
	    return diferencia_fecha_pospuesta;
	} 	

	private final int getDiasCanceladosDefault()
	{
		system_param_PK = new SystemParamPK(54, 1);
		system_param    = system_param_service.loadSelectedSystemParam(system_param_PK).getValue();
				
		return Integer.parseInt(system_param);
	}
		
	protected String getFrecuencia(Integer freq)
	{
		String res = "";
		
		switch (freq)
		{
			case 1:
				res = "S";
				break;
			case 2:
				res = "C";
				break;
			case 3:
				res = "Q";
				break;
			case 4:
				res = "M";
				break;
		}
		
		return res;
	}
	
	protected String getMontoComision(Scoring score,Double montoSolici)
	{
		Double comision = 0D;
		
		if(score!=null)
		{
			if(score.getOpening_commission() != null && (score.getOpening_commission()+"").length()>0)
			{
				Double c = score.getOpening_commission()/100;
				comision = c * montoSolici;
				comision = ((double)Math.round((comision)*100)/100);
			}
		}
		
		return comision.toString();
	}
	
	protected String generateRateDif(Double rate)
	{
		return (rate + 6) + "";
	}
	
	protected String getMenu_item_selected() 
	{		
		return target_item + "::" + screen_id + "::" + id_item;
	}
	
	public Boolean getDispInvestor() {
		return dispInvestor;
	}

	public void setDispInvestor(Boolean dispInvestor) {
		this.dispInvestor = dispInvestor;
	}

	public Integer getTermSel() {
		return termSel;
	}

	public void setTermSel(Integer termSel) {
		this.termSel = termSel;
	}

	public Integer getFrequencySel() {
		return frequencySel;
	}

	public void setFrequencySel(Integer frequencySel) {
		this.frequencySel = frequencySel;
	}
	
	public boolean isHasAccount() 
	{
		return hasAccount;
	}

	public void setHasAccount(boolean hasAccount) 
	{
		this.hasAccount = hasAccount;
	}
	
	public final boolean isDisplayBtnDeal() 
	{
		return displayBtnDeal;
	}

	public void setDisplayBtnDeal(boolean displayBtnDeal) 
	{
		this.displayBtnDeal = displayBtnDeal;
	}
	
	public final boolean isSuccessDisp() 
	{
		return successDisp;
	}

	public final void setSuccessDisp(boolean bandera) 
	{
		successDisp = bandera;
	}

	public final boolean isBeginDisp() 
	{
		return beginDisp;
	}		

	public final boolean isProyecto_cancelado() 
	{
		return proyecto_cancelado;
	}

	public final void setProyecto_cancelado(boolean bandera) 
	{
		proyecto_cancelado = bandera;
	}

	public final boolean isProyecto_pospuesto() 
	{
		return proyecto_pospuesto;
	}

	public final void setProyecto_pospuesto(boolean bandera) 
	{
		proyecto_pospuesto = bandera;
	}

	public boolean isProyecto_desistido() 
	{
		return proyecto_desistido;
	}

	public void setProyecto_desistido(boolean bandera)
	{
		proyecto_desistido = bandera;
	}

	public final void setBeginDisp(boolean bandera) 
	{
		beginDisp = bandera;
	}

	public final boolean isContResp() 
	{
		return contResp;
	}

	public final void setContResp(boolean bandera) 
	{
		contResp = bandera;
	}

	public final boolean isErrorDisp() 
	{
		return errorDisp;
	}

	public final void setErrorDisp(boolean bandera) 
	{
		errorDisp = bandera;
	}

	public List<ClabeAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<ClabeAccount> accountList) {
		this.accountList = accountList;
	}
	
	public List<AccessCollector> getMenuIncomplete() {
		return menuIncomplete;
	}

	public void setMenuIncomplete(List<AccessCollector> menuIncomplete) {
		this.menuIncomplete = menuIncomplete;
	}

	public ClabeAccount getThisClabe() {
		return thisClabe;
	}

	public void setThisClabe(ClabeAccount thisClabe) {
		this.thisClabe = thisClabe;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}


	public String getMontoMinStr() 
	{
		if(montoMin!=null)
		{
			return num.format(montoMin);
		} else {
			return "0";
		}
	}

	public void setMontoMinStr(String montoMinStr) 
	{
		if(montoMinStr!=null)
		{
			montoMin =  Double.parseDouble(montoMinStr.replaceAll("$", "").replaceAll(",", "").replaceAll(" ", ""));
		} else {
			montoMin = 0D;
		}
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

	public String getSelAccount() {
		return selAccount;
	}

	public void setSelAccount(String selAccount) {
		this.selAccount = selAccount;
	}

	public List<DealBean> getDealList() {
		return dealList;
	}

	public void setDealList(List<DealBean> dealList) {
		this.dealList = dealList;
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

	public DealBean getSelectedDeal() 
	{
		return selectedDeal;
	}

	public void setSelectedDeal(DealBean selectedDeal) 
	{
		this.selectedDeal = selectedDeal;
		String monto = getSelectedDeal().getAmountStr();
		monto = monto.replace("$", "");
		monto = monto.replace(",", "");
		monto = monto.replace(" ", "");
		
		setMontoMin(Double.parseDouble(monto));
	}
	
	public ProyectLoan getMyPyLn() {
		return proyect_loan_reasignable;
	}

	public void setMyPyLn(ProyectLoan myPyLn) {
		this.proyect_loan_reasignable = myPyLn;
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
	
	public List<MenuRegBean> getListRequiredMenu() {
		return listRequiredMenu;
	}

	public void setListRequiredMenu(List<MenuRegBean> listRequiredMenu) {
		this.listRequiredMenu = listRequiredMenu;
	}

	public Proyect getMyPy() {
		return proyect;
	}

	public void setMyPy(Proyect myPy) {
		this.proyect = myPy;
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
	
	public DealBean getBeanSelOther() {
		return beanSelOther;
	}

	public void setBeanSelOther(DealBean beanSelOther) {
		this.beanSelOther = beanSelOther;
	}
	
	public Integer getCatStr() {
		return catStr;
	}

	public void setCatStr(Integer catStr) {
		this.catStr = catStr;
	}

	public String getAccountDisp() {
		return accountDisp;
	}

	public void setAccountDisp(String accountDisp) {
		this.accountDisp = accountDisp;
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

	public boolean isProyecto_rechazado_automaticamente() {
		return proyecto_rechazado_automaticamente;
	}

	public void setProyecto_rechazado_automaticamente(
			boolean proyecto_rechazado_automaticamente) {
		this.proyecto_rechazado_automaticamente = proyecto_rechazado_automaticamente;
	}

	public ProyectLoan getProyect_loan_reasignable() {
		return proyect_loan_reasignable;
	}

	public void setProyect_loan_reasignable(ProyectLoan proyect_loan_reasignable) {
		this.proyect_loan_reasignable = proyect_loan_reasignable;
	}

	public boolean isSuccess_OK() {
		return success_OK;
	}

	public void setSuccess_OK(boolean success_OK) {
		this.success_OK = success_OK;
	}
	
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public String getAmountSel() {
		return amountSel;
	}

	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

	public void setAmountSel(String amountSel) {
		this.amountSel = amountSel;
	}
	
}
