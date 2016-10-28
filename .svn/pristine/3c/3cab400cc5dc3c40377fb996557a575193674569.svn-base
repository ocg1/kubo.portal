package mx.com.kubo.managedbeans;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.CreditoCaracteristicas;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.services.AmortizacionInversionistaService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RoleSearchingService;
import mx.com.kubo.services.SaldoInversionistaService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TasasAcreditadoService;
import mx.com.kubo.services.TiendaCreditosService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public abstract class ListaCreditosDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{proyectFundingServiceImp}")
	protected ProyectFundingService proyectFundingService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{tiendaCreditosServiceImp}")
	protected TiendaCreditosService tiendacreditosservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{roleSearchingServiceImp}")
	protected RoleSearchingService rolesearchingservice;
	
	@ManagedProperty("#{statusProyectCatServiceImp}")
	protected StatusProyectCatService statusproyectcatservice;
	
	@ManagedProperty("#{tasasAcreditadoServiceImp}")
	protected TasasAcreditadoService tasaacreditadoservice;
	
	@ManagedProperty("#{amortizacionInversionistaServiceImp}")
	protected AmortizacionInversionistaService amortInverService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{profileInvServiceImp}")
	protected ProfileInvService profileinvservice;
	
	@ManagedProperty("#{saldoInversionistaServiceImp}")
	protected SaldoInversionistaService saldoinversionistaservice;
	
	protected RequestContext request;
	protected FacesContext   faces;	
	protected ELContext      elContext;
	protected ELResolver     resolver;
	
	protected MenuControlTableBean control_table;
	protected SearchSummaySession  summarysesion;
	protected SummaryRequest       summary;
	protected NavigationInvest     navigationinvest;
	
	protected SAFIServiciosServiceLocator locatorSafi;
	protected SAFIServicios servicioSafi;
		
	protected Calendar cal1, cal2;
	
	protected String proyect_loan_SEARCH_TOKEN;
	
	protected long milis1, milis2, diff;
	
	protected Logger        log;			
	protected SessionBean   sesion;
	protected NaturalPerson naturalPerson;	
	protected Membership    member;
	protected MembershipPK  membership_PK;
	
	protected ProyectLoan selectedRowProyectLoan;
	protected Role_Searching rolesearching;
	
	protected SimpleDateFormat formatStr;
	protected SimpleDateFormat formatStr5;
	protected SimpleDateFormat formatStr1;
	
    protected Locale locale;
    protected NumberFormat dec;
    protected NumberFormat num;
	
	protected List<ItemLoanList>   proyectList;
	protected List<ProyectFunding> proyectFunding;	
	protected List<ItemLoanList>   proyectListForInvesInd;	
	protected List<ProyectFunding> proyectFundingByInvestor;
	
	protected List<InvestorsAccounts>      listInvAccounts;
	protected List<StatusProyectCat>       lstStatus;
	protected List<CreditoCaracteristicas> listCred;
	
	protected List<Byte> onlyTermOfProyectLoan;
	
	protected String navigation;	
	protected String scriptStatus; 	
	protected String tr; 	
	protected String valuesforGarantia;	
	protected String tr1; 
	protected String valuesforticket;
    protected String tagActual;
    protected String ticketStr;
	protected String ExceptionOnFunding;
	protected String labelOnFilteringByAmmount;
	protected String PID_cliente;
	protected String logs_navegation;
	protected String vecesQuePuedeFondear;
	protected String tagAccount;	
	protected String cuentaActual;
	protected String timer;
	protected String ammoutToInvStr;
	
	protected List<String> SAFI_cuenta;
	
	protected int creditFounded;       
    protected int creditNotFounded;
    
    protected boolean fundingFunction;
	protected boolean displayInvButton;	
	protected boolean blnPaquete;
	protected boolean displayInvestAction;
	protected boolean flagNotRule;
	protected boolean displayMsgMaxSug;
	protected boolean displayMsgMinSug;
	protected boolean flagNumberProy=true;
	
	protected Double montoLimiteMaxSugerido = 1000000D;
	protected Double montoLimiteMinSugerido=400D;
    protected Double ammountFounded;
    protected Double ammountNotFounded;    
    protected Double investmentBiteVAL;    
    protected Double monto_a_invertir;
    protected Double monto_a_invertir_temp;
    protected Double investmentBite;    
    protected Double ammountFoundedInv;    
    protected Double saldoTotal;
    protected Double ammountFounedScooped;
	protected Double montoMaximo;
	protected Double saldoActual;
	protected Double montoMinG;
	protected Double maximoInvBySaldoG;
	protected Double maxPorcPryG;
	protected Double montoSaldoG;
	protected Double porcMaxSaldoG;
	
	protected Double ammoutToInv;
	protected Double monto_a_invertir_total;
	protected Double montoTotal;
	
	protected String newBiteInv;
	protected String profileStr = "";
	protected String maxInvBiteRecomded = ""; 
    
    protected List<Double> listOfPercentByKuboScore;   
    
    protected Boolean moreThanOneAccountFlag;
    
    protected ProfileInv profile;
    
    protected int aToInv;
    protected int bToInv;
    protected int cToInv;
    protected int dToInv;
    protected int eToInv;
    
    protected int typeSearch;
    
    protected ListaCreditosDMO()
    {
    	moreThanOneAccountFlag = false;
    	blnPaquete             = true;
    	
        ammountFounded        = 0D;
        ammountNotFounded     = 0D;    
        investmentBiteVAL     = 0D;    
        monto_a_invertir      = 0D;
        monto_a_invertir_temp = 0D;
        investmentBite        = 0D;    
        ammountFoundedInv     = 0D;    
        saldoTotal            = 0D;
        ammountFounedScooped  = 0.00;
    	saldoActual           = 0.00;
    	
    	creditFounded     = 0;
    	creditNotFounded  = 0;
    	
    	locale = new Locale("es","mx");
    	
    	dec = NumberFormat.getCurrencyInstance(locale);
    	num = NumberFormat.getNumberInstance(locale);
    	
    	formatStr  = new SimpleDateFormat("ddMMyyyy", new Locale("ES"));
    	formatStr5 = new SimpleDateFormat("EEEE dd' de 'MMMM' de 'yyyy", new Locale("ES"));
    	formatStr1 = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));

    	log = Logger.getLogger(getClass());
    	
    	navigation = null;
    	
    	tr = "<tr>";
    	tr1 = "</tr>";     	
    	valuesforGarantia ="";	
    	PID_cliente       = "";    	
        tagActual                 = new String();    
        cuentaActual              = new String();
    	ExceptionOnFunding        = new String("");
    	labelOnFilteringByAmmount = new String("");
    	
    	
    }
    
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setAmortInverService(AmortizacionInversionistaService service) 
	{
		amortInverService = service;
	}

	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}
	
	public void setStatusproyectcatservice(StatusProyectCatService service) 
	{
		statusproyectcatservice = service;
	}
	
	public void setRolesearchingservice(RoleSearchingService service) 
	{
		rolesearchingservice = service;
	}
	
	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setProyectFundingService(ProyectFundingService service) 
	{
		proyectFundingService = service;
	}
	
	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}
	
	public void setTiendacreditosservice(TiendaCreditosService service) 
	{
		tiendacreditosservice = service;
	}
	
	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}
	
	public String getPID_cliente() 
	{
		return PID_cliente;
	}
	
	public void setPID_cliente(String pID_cliente) 
	{
		PID_cliente = pID_cliente;
	}
	
	public List<String> getSAFI_cuenta() {
		return SAFI_cuenta;
	}
	
	public String getTagAccount() {
		return tagAccount;
	}

	public void setTagAccount(String tagAccount) {
		this.tagAccount = tagAccount;
	}

	public void setSAFI_cuenta(List<String> sAFI_cuenta) {
		SAFI_cuenta = sAFI_cuenta;
	}
	
	public List<InvestorsAccounts> getListInvAccounts() {
		return listInvAccounts;
	}
	
	public void setListInvAccounts(List<InvestorsAccounts> listInvAccounts) {
		this.listInvAccounts = listInvAccounts;
	}
	
	public String getNavigation() {
		return navigation;
	}
	
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
	
	public String getLogs_navegation() {
		return logs_navegation;
	}
	
	public void setLogs_navegation(String logs_navegation) {
		this.logs_navegation = logs_navegation;
	}
	
	public List<ItemLoanList> getProyectList() {
		return proyectList;
	}

	public List<ProyectFunding> getProyectFunding() {
		return proyectFunding;
	}

	public void setProyectFunding(List<ProyectFunding> proyectFunding) {
		this.proyectFunding = proyectFunding;
	}

	public void setProyectList(List<ItemLoanList> proyectList) {
		this.proyectList = proyectList;
	}

	public List<CreditoCaracteristicas> getListCred() {
		return listCred;
	}

	public void setListCred(List<CreditoCaracteristicas> listCred) {
		this.listCred = listCred;
	}

	public int getSizeList() {
		return listCred.size();
	}
	
	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}
	
	public String getCuentaActual() {
		return cuentaActual;
	}
	
	public void setCuentaActual(String cuentaActual) {
		this.cuentaActual = cuentaActual;
	}
	
	public ProyectLoan getSelectedRowProyectLoan() {
		return selectedRowProyectLoan;
	}
	
	public void setSelectedRowProyectLoan(ProyectLoan selectedRowProyectLoan) {
		this.selectedRowProyectLoan = selectedRowProyectLoan;
	}

	public String getExceptionOnFunding() {
		return ExceptionOnFunding;
	}

	public void setExceptionOnFunding(String exceptionOnFunding) {
		ExceptionOnFunding = exceptionOnFunding;
	}

	public String getTagActual() {
		return tagActual;
	}
	
	public String getLabelOnFilteringByAmmount() {
		return labelOnFilteringByAmmount;
	}

	public void setLabelOnFilteringByAmmount(String labelOnFilteringByAmmount) {
		this.labelOnFilteringByAmmount = labelOnFilteringByAmmount;
	}

	public List<Byte> getOnlyTermOfProyectLoan() {
		return onlyTermOfProyectLoan;
	}

	public void setOnlyTermOfProyectLoan(List<Byte> onlyTermOfProyectLoan) {
		this.onlyTermOfProyectLoan = onlyTermOfProyectLoan;
	}
	
	public boolean isFundingFunction() {
		return fundingFunction;
	}

	public void setFundingFunction(boolean fundingFunction) {
		this.fundingFunction = fundingFunction;
	}
	
	public String getVecesQuePuedeFondear() {
		return vecesQuePuedeFondear;
	}

	public void setVecesQuePuedeFondear(String vecesQuePuedeFondear) {
		this.vecesQuePuedeFondear = vecesQuePuedeFondear;
	}

	public Role_Searching getRolesearching() {
		return rolesearching;
	}

	public void setRolesearching(Role_Searching rolesearching) {
		this.rolesearching = rolesearching;
	}

	public String getScriptStatus() {
		return scriptStatus;
	}

	public void setScriptStatus(String scriptStatus) {
		this.scriptStatus = scriptStatus;
	}

	public List<StatusProyectCat> getLstStatus() {
		return lstStatus;
	}

	public void setLstStatus(List<StatusProyectCat> lstStatus) {
		this.lstStatus = lstStatus;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public String getTr1() {
		return tr1;
	}

	public void setTr1(String tr1) {
		this.tr1 = tr1;
	}
	
	public int getCreditFounded() {
		return creditFounded;
	}

	public void setCreditFounded(int creditFounded) {
		this.creditFounded = creditFounded;
	}

	public Double getAmmountFounded() {
		return ammountFounded;
	}

	public void setAmmountFounded(Double ammountFounded) {
		this.ammountFounded = ammountFounded;
	}
	
	public int getCreditNotFounded() {
		return creditNotFounded;
	}

	public void setCreditNotFounded(int creditNotFounded) {
		this.creditNotFounded = creditNotFounded;
	}

	public Double getAmmountNotFounded() {
		return ammountNotFounded;
	}

	public void setAmmountNotFounded(Double ammountNotFounded) {
		this.ammountNotFounded = ammountNotFounded;
	}

	public Double getInvestmentBite() {
		return investmentBite;
	}

	public void setInvestmentBite(Double investmentBite) {
		this.investmentBite = investmentBite;
	}

	public Double getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(Double montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public boolean isDisplayInvButton() {
		return displayInvButton;
	}

	public void setDisplayInvButton(boolean displayInvButton) {
		this.displayInvButton = displayInvButton;
	}
	
	public TasasAcreditadoService getTasaacreditadoservice() {
		return tasaacreditadoservice;
	}

	public void setTasaacreditadoservice(
			TasasAcreditadoService tasaacreditadoservice) {
		this.tasaacreditadoservice = tasaacreditadoservice;
	}

	public String getValuesforticket() {
		return valuesforticket;
	}

	public void setValuesforticket(String valuesforticket) {
		this.valuesforticket = valuesforticket;
	}
	
	public String getTicketStr() {
		return ticketStr;
	}

	public void setTicketStr(String ticketStr) {
		this.ticketStr = ticketStr;
	}
	
	public NaturalPerson getNaturalPerson() 
	{
		return naturalPerson;
	}

	public void setNaturalPerson(NaturalPerson naturalPerson) 
	{
		this.naturalPerson = naturalPerson;
	}
	
	public List<ItemLoanList> getProyectListForInvesInd() {
		return proyectListForInvesInd;
	}

	public void setProyectListForInvesInd(List<ItemLoanList> proyectListForInvesInd) {
		this.proyectListForInvesInd = proyectListForInvesInd;
	}

	public Double getAmmountFoundedInv() {
		return ammountFoundedInv;
	}

	public void setAmmountFoundedInv(Double ammountFoundedInv) {
		this.ammountFoundedInv = ammountFoundedInv;
	}

	public boolean isBlnPaquete() {
		return blnPaquete;
	}

	public Double getInvestmentBiteVAL() {
		return investmentBiteVAL;
	}

	public void setInvestmentBiteVAL(Double investmentBiteVAL) {
		this.investmentBiteVAL = investmentBiteVAL;
	}

	public void setBlnPaquete(boolean blnPaquete) {
		this.blnPaquete = blnPaquete;
	}
	
	public String getValuesforGarantia() {
		return valuesforGarantia;
	}

	public void setValuesforGarantia(String valuesforGarantia) {
		this.valuesforGarantia = valuesforGarantia;
	}

	public Double getMonto_a_invertir() {
		return monto_a_invertir;
	}

	public void setMonto_a_invertir(Double monto_a_invertir) {
		this.monto_a_invertir = monto_a_invertir;
	}
	
	public Double getMonto_a_invertir_temp() {
		return monto_a_invertir_temp;
	}

	public void setMonto_a_invertir_temp(Double monto_a_invertir_temp) {
		this.monto_a_invertir_temp = monto_a_invertir_temp;
	}

	public boolean isDisplayInvestAction() {
		return displayInvestAction;
	}

	public void setDisplayInvestAction(boolean displayInvestAction) {
		this.displayInvestAction = displayInvestAction;
	}

	public String getNewBiteInv() {
		return newBiteInv;
	}

	public void setNewBiteInv(String newBiteInv) {
		this.newBiteInv = newBiteInv;
	}

	public Double getMontoMinG() {
		return montoMinG;
	}

	public void setMontoMinG(Double montoMinG) {
		this.montoMinG = montoMinG;
	}

	public Double getMaximoInvBySaldoG() {
		return maximoInvBySaldoG;
	}

	public void setMaximoInvBySaldoG(Double maximoInvBySaldoG) {
		this.maximoInvBySaldoG = maximoInvBySaldoG;
	}

	public Double getMaxPorcPryG() {
		return maxPorcPryG;
	}

	public void setMaxPorcPryG(Double maxPorcPryG) {
		this.maxPorcPryG = maxPorcPryG;
	}

	public Double getMontoSaldoG() {
		return montoSaldoG;
	}

	public void setMontoSaldoG(Double montoSaldoG) {
		this.montoSaldoG = montoSaldoG;
	}

	public Double getPorcMaxSaldoG() {
		return porcMaxSaldoG;
	}

	public void setPorcMaxSaldoG(Double porcMaxSaldoG) {
		this.porcMaxSaldoG = porcMaxSaldoG;
	}

	public boolean isDisplayMsgMaxSug() {
		return displayMsgMaxSug;
	}

	public void setDisplayMsgMaxSug(boolean displayMsgMaxSug) {
		this.displayMsgMaxSug = displayMsgMaxSug;
	}

	public boolean isDisplayMsgMinSug() {
		return displayMsgMinSug;
	}

	public void setDisplayMsgMinSug(boolean displayMsgMinSug) {
		this.displayMsgMinSug = displayMsgMinSug;
	}

	public Double getMontoLimiteMaxSugerido() {
		return montoLimiteMaxSugerido;
	}

	public void setMontoLimiteMaxSugerido(Double montoLimiteMaxSugerido) {
		this.montoLimiteMaxSugerido = montoLimiteMaxSugerido;
	}

	public Double getMontoLimiteMinSugerido() {
		return montoLimiteMinSugerido;
	}

	public void setMontoLimiteMinSugerido(Double montoLimiteMinSugerido) {
		this.montoLimiteMinSugerido = montoLimiteMinSugerido;
	}

	public ProfileInvService getProfileinvservice() {
		return profileinvservice;
	}

	public void setProfileinvservice(ProfileInvService profileinvservice) {
		this.profileinvservice = profileinvservice;
	}

	public ProfileInv getProfile() {
		return profile;
	}

	public void setProfile(ProfileInv profile) {
		this.profile = profile;
	}

	public String getProfileStr() {
		return profileStr;
	}

	public void setProfileStr(String profileStr) {
		this.profileStr = profileStr;
	}

	public String getMaxInvBiteRecomded() {
		return maxInvBiteRecomded;
	}

	public void setMaxInvBiteRecomded(String maxInvBiteRecomded) {
		this.maxInvBiteRecomded = maxInvBiteRecomded;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date());
		
		System.out.println("Procesando Fondeo de Casos: "+( cal.getTimeInMillis() ) + " milisegundos "  );
		
		this.timer = timer;
	}

	public int getaToInv() {
		return aToInv;
	}

	public void setaToInv(int aToInv) {
		this.aToInv = aToInv;
	}

	public int getbToInv() {
		return bToInv;
	}

	public void setbToInv(int bToInv) {
		this.bToInv = bToInv;
	}

	public int getcToInv() {
		return cToInv;
	}

	public void setcToInv(int cToInv) {
		this.cToInv = cToInv;
	}

	public int getdToInv() {
		return dToInv;
	}

	public void setdToInv(int dToInv) {
		this.dToInv = dToInv;
	}

	public int geteToInv() {
		return eToInv;
	}

	public void seteToInv(int eToInv) {
		this.eToInv = eToInv;
	}

	public Double getAmmoutToInv() {
		return ammoutToInv;
	}

	public void setAmmoutToInv(Double ammoutToInv) {
		this.ammoutToInv = ammoutToInv;
	}

	public Double getMonto_a_invertir_total() {
		return monto_a_invertir_total;
	}

	public void setMonto_a_invertir_total(Double monto_a_invertir_total) {
		this.monto_a_invertir_total = monto_a_invertir_total;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getAmmoutToInvStr() {
		return  num.format( ammoutToInv );
	}

	public void setAmmoutToInvStr(String ammoutToInvStr) {
		
		if(ammoutToInvStr == null)
			ammoutToInv = 0D;
		else{
			if(ammoutToInvStr.trim().length()>0){
				try{
					ammoutToInv = Double.parseDouble( ammoutToInvStr.replaceAll(",", "") );
				}catch( Exception e ){
					ammoutToInv = 0D;
					ammoutToInvStr = "";
				}
			}
			
		}
		
		this.ammoutToInvStr = ammoutToInvStr;
	}

	public int getTypeSearch() {
		return typeSearch;
	}

	public void setTypeSearch(int typeSearch) {
		this.typeSearch = typeSearch;
	}

	public SaldoInversionistaService getSaldoinversionistaservice() {
		return saldoinversionistaservice;
	}

	public void setSaldoinversionistaservice(
			SaldoInversionistaService saldoinversionistaservice) {
		this.saldoinversionistaservice = saldoinversionistaservice;
	}

	public boolean isFlagNumberProy() {
		return flagNumberProy;
	}

	public void setFlagNumberProy(boolean flagNumberProy) {
		this.flagNumberProy = flagNumberProy;
	}

}
