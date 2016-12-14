package mx.com.kubo.managedbeans;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.SellingProyectBean;
import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.RendimientosInv;
import mx.com.kubo.model.SafiProyecInProcess;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AmortizacionInversionistaService;
import mx.com.kubo.services.InfoNotificationService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyeccionGraficaInvService;
import mx.com.kubo.services.ProyectLoanService;
// import mx.com.kubo.services.impl.InvestmentsAtraEdoCtaServiceImp;
import mx.com.kubo.services.RendimientosInvService;
import mx.com.kubo.services.RetornoAnualService;

import org.apache.log4j.Logger;

public abstract class MyInvestmentsDMO 
{
	

	protected final String[] meses ={"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
	 
	protected final String[] meses_c ={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"}; 
	
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService personaNaturalService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService servicioProyecto;
	
	@ManagedProperty("#{amortizacionInversionistaServiceImp}")
	protected AmortizacionInversionistaService amortInverService;
	
	@ManagedProperty("#{proyeccionGraficaInvServiceImp}")
	protected ProyeccionGraficaInvService proyeccionGraficaInvService;
	
	@ManagedProperty("#{rendimientosInvServiceImp}")
	protected RendimientosInvService rendimientosInvService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{retornoAnualServiceImp}")
	protected RetornoAnualService retornoAnualService;
	
	@ManagedProperty("#{infoNotificationServiceImp}")
	protected InfoNotificationService infonotificationservice;
	
	/*
	@ManagedProperty("#{investmentsAtraEdoCtaServiceImp}")
	protected InvestmentsAtraEdoCtaServiceImp investmentsAtras;
	*/
	protected Logger log;
	protected ResourceBundle recurso;
	
	protected final int NOTIFICATION_CONFIGURATION_SCREEN = 4;
	
	protected SessionBean sesion;
	protected NaturalPerson persona;
	protected ProyectLoanActiveInSafi active;
	
	protected ArrayList<String> numCuentas;
	protected ArrayList<String> infoCalEst;
	protected ArrayList<String> infoCalPorc;
	protected ArrayList<String> infoTasaEst;
	protected ArrayList<String> infoTasaPorc;
	
	protected List<SafiProyecInProcess>     safiProyecInProcessLst;	
	protected List<ProyectLoanActiveInSafi> safiProyecLoanActiveLst;	
	protected List<SellingProyectBean>      proyecSellingLst;
	protected List<AmortizacionInversionista> lst;
	protected List<RendimientosInv> lstRendimientos;
	
	protected SimpleDateFormat formatStr5;
	protected SimpleDateFormat formatStr1;
	protected SimpleDateFormat formatStr;
	protected SimpleDateFormat formatStr2;
	
	protected  Locale locale;
	protected  NumberFormat dec;
	
	protected StringBuilder detalle;

	protected String client;
	protected String selectedAccount;
	protected String selectedAccountAux;	
	protected String interest_charged;
	protected String total_balance;
	protected String total_payments;
	protected String balances_cash;
	protected String balances_investment_process;
	protected String balances_active_investments;
	protected String balances_accrued_interest;
	protected String valuesforGarantia;
	protected String valuesforticket;
	protected String ticketStr;
	protected String active_amount;
	protected String in_process_amount;
	protected String backward16_30_amount;
	protected String backward1_15_amount;
	protected String backward31_90_amount;
	protected String expired91_120_amount;
	protected String expired121_180_amount;
	protected String broken_amount;
	protected String fully_paid_amount;
	protected String payments_received_amount;	
	protected String capital_amount;	
	protected String ordinary_interests_amount;	
	protected String interest_on_arrears_amount;
	protected String depositos = "0";
	protected String valArrGraphic1;	
	protected String valArrGraphic2;
	protected String pageName;
	protected String valueClick;
	protected String valMontoStr;
	protected String montoProp;
	protected String porcProp;
	protected String valMonto;
	protected String valCredit;
	protected String valArrGraphic4;
	protected String valMontoUltOfer;
	protected String valMontoUltOferStr;
	protected String tableResult;
	protected char status_delinquentinv	= 'C';
	protected String retornoAnual;
	protected String retornoAnual_Ajustado;
	protected String scriptGraphicBar;
	protected String scriptGraphicSaldoDisponible;
	protected String scriptGraphicCombo;
	
	protected Double tasaPonderada;
	protected Double saldoIntDev;
	protected Double impuestos;
	protected Double gat;
	protected Double inverRealiz;
	protected Double pagCapRecib;
	protected Double intOrdRec;
	protected Double intMoraRec;
	protected Double recupMorosos;
	protected Double retenidoISR;
	protected Double comisCobrad;
	protected Double comisPagad;
	protected Double ajustes;
	protected Double quebrantos;
	protected Double quebranXapli;
	protected Double premiosRecom;
			
	protected Integer numPagosRecibidos;
	protected Integer numPagosCapital;
	protected Integer numPagosInterOrdi;
	protected Integer numPagosInteMora;	
	protected Integer comisPagadas;
	protected Integer numComisRecibidas;
	protected Integer temporal;
	protected Integer proyect_loan_id;
	protected Integer kuboFondeoInv_id;
	
	protected int notes_cash;
	protected int notes_investment_process;
	protected int notes_active_investments;
	protected int notes_active_investments_cli;
	protected int notes_accrued_interest;	
	protected int investment_summary;	
	protected int active_number;
	protected int active_number_cli;
	protected int in_process_number;	
	protected int backward1_15_number;
	protected int backward1_15_number_cli;
	protected int backward16_30_number;	
	protected int backward16_30_number_cli;
	protected int backward31_90_number;	
	protected int backward31_90_number_cli;
	protected int expired91_120_number;
	protected int expired91_120_number_cli;
	protected int expired121_180_number;
	protected int expired121_180_number_cli;
	protected int under_warranty_number_cli;
	protected int under_warranty_number;
	protected int broken_number;	
	protected int fully_paid_number;
	protected int payments_received_number;
	protected int capital_number;
	protected int ordinary_interests_number;
	protected int interest_on_arrears_number;
	protected String cuentaAhoID;
	protected int numInvActivas;
	protected String montoInversiones;
	protected String intARecibir;
	protected String saldoIntGlobal;
	protected String saldoIntPlazoFijo;
	protected String interesCobrado;
	protected String moraCobrado;
	
	// Variables para Inversiones Atrasadas
	protected String saldoVigente0;
	protected String saldoVigente1a15;
	protected String saldoVigente16a30;
	protected String saldoVigente31a90;
	protected String saldoVigente91a120;
	protected String saldoVigente120mas;
	protected String saldoAtrasado0;
	protected String saldoAtrasado1a15;
	protected String saldoAtrasado16a30;
	protected String saldoAtrasado31a90;
	protected String saldoAtrasado91a120;
	protected String saldoAtrasado120mas;
	protected String saldoCapVigTotal;
	protected String saldoCapAtrTotal;
	protected String saldoIntVigente;
	protected String saldoIntAtrasado;
	protected String saldoCapitalCtaOrden;
	protected String saldoInteresCtaOrden;
	protected String saldoTotalCtaOrden;
	
	//Variables para la vista de inversiones atrasadas por cliente o inversion
	protected String view_delinquentInv;
	protected String viewLabelDelinquent;
	protected String labelDelinquent;
	protected String labelBalances;
	protected int 	 numberOfClients;
	protected int    number_notes_active;
	protected int  	 number_NoDelinquent;
	protected int 	 number_backward1_15;
	protected int 	 number_backward16_30;
	protected int 	 number_backward31_90;
	protected int 	 number_backward91_120;
	protected int 	 number_backward121_180;
	protected int 	 number_under_warranty;
	
	protected boolean displayName;
	
	protected boolean displayNotificacion;
			
	protected MyInvestmentsDMO()
	{		
		numCuentas   = new ArrayList<String>();
		infoCalEst   = new ArrayList<String>();
		infoCalPorc  = new ArrayList<String>();
		infoTasaEst  = new ArrayList<String>();
		infoTasaPorc = new ArrayList<String>();
		
		locale = new Locale("es","mx");
		
		dec = NumberFormat.getCurrencyInstance(locale);
		
		log = Logger.getLogger(getClass());
			
			temporal = 0;
		
		displayName = false;
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Double getGat() {
		return gat;
	}
	public void setGat(Double gat) {
		this.gat = gat;
	}
	
	public String getBalances_accrued_interest() {
		return balances_accrued_interest;
	}
	public void setBalances_accrued_interest(String balances_accrued_interest) {
		this.balances_accrued_interest = balances_accrued_interest;
	}
	public int getNotes_cash() {
		return notes_cash;
	}
	public void setNotes_cash(int notes_cash) {
		this.notes_cash = notes_cash;
	}
	public int getNotes_investment_process() {
		return notes_investment_process;
	}
	public void setNotes_investment_process(int notes_investment_process) {
		this.notes_investment_process = notes_investment_process;
	}
	public int getNotes_active_investments() {
		return notes_active_investments;
	}
	public void setNotes_active_investments(int notes_active_investments) {
		this.notes_active_investments = notes_active_investments;
	}
	public int getNotes_accrued_interest() {
		return notes_accrued_interest;
	}
	public void setNotes_accrued_interest(int notes_accrued_interest) {
		this.notes_accrued_interest = notes_accrued_interest;
	}
	public int getInvestment_summary() {
		return investment_summary;
	}
	public void setInvestment_summary(int investment_summary) {
		this.investment_summary = investment_summary;
	}
	
	public int getActive_number() {
		return active_number;
	}
	public void setActive_number(int active_number) {
		this.active_number = active_number;
	}
	
	public int getIn_process_number() {
		return in_process_number;
	}
	public void setIn_process_number(int in_process_number) {
		this.in_process_number = in_process_number;
	}
	
	public int getBackward16_30_number() {
		return backward16_30_number;
	}
	public void setBackward16_30_number(int backward16_30_number) {
		this.backward16_30_number = backward16_30_number;
	}
	
	public int getBackward31_90_number() {
		return backward31_90_number;
	}
	public void setBackward31_90_number(int backward31_90_number) {
		this.backward31_90_number = backward31_90_number;
	}
	
	public int getExpired91_120_number() {
		return expired91_120_number;
	}
	public void setExpired91_120_number(int expired91_120_number) {
		this.expired91_120_number = expired91_120_number;
	}
	
	public int getExpired121_180_number() {
		return expired121_180_number;
	}
	public void setExpired121_180_number(int expired121_180_number) {
		this.expired121_180_number = expired121_180_number;
	}
	
	public int getBroken_number() {
		return broken_number;
	}
	public void setBroken_number(int broken_number) {
		this.broken_number = broken_number;
	}
	
	public int getFully_paid_number() {
		return fully_paid_number;
	}
	public void setFully_paid_number(int fully_paid_number) {
		this.fully_paid_number = fully_paid_number;
	}
	
	public int getPayments_received_number() {
		return payments_received_number;
	}
	public void setPayments_received_number(int payments_received_number) {
		this.payments_received_number = payments_received_number;
	}
	
	public int getCapital_number() {
		return capital_number;
	}
	public void setCapital_number(int capital_number) {
		this.capital_number = capital_number;
	}
	
	public int getOrdinary_interests_number() {
		return ordinary_interests_number;
	}
	public void setOrdinary_interests_number(int ordinary_interests_number) {
		this.ordinary_interests_number = ordinary_interests_number;
	}
	
	public int getInterest_on_arrears_number() {
		return interest_on_arrears_number;
	}
	public void setInterest_on_arrears_number(int interest_on_arrears_number) {
		this.interest_on_arrears_number = interest_on_arrears_number;
	}
	public String getSelectedAccount() {
		return selectedAccount;
	}
	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}
	public String getSelectedAccountAux() {
		return selectedAccountAux;
	}
	public void setSelectedAccountAux(String selectedAccountAux) {
		this.selectedAccountAux = selectedAccountAux;
	}
	public ArrayList<String> getNumCuentas() {
		return numCuentas;
	}
	public void setNumCuentas(ArrayList<String> numCuentas) {
		this.numCuentas = numCuentas;
	}
	public SessionBean getSesion() {
		return sesion;
	}
	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public void setPersonaNaturalService(NaturalPersonService personaNaturalService) {
		this.personaNaturalService = personaNaturalService;
	}
	public NaturalPerson getPersona() {
		return persona;
	}
	public void setPersona(NaturalPerson persona) {
		this.persona = persona;
	}
	public Double getTasaPonderada() {
		return tasaPonderada;
	}
	public void setTasaPonderada(Double tasaPonderada) {
		this.tasaPonderada = tasaPonderada;
	}
	public Double getSaldoIntDev() {
		return saldoIntDev;
	}
	public void setSaldoIntDev(Double saldoIntDev) {
		this.saldoIntDev = saldoIntDev;
	}
	public Integer getNumPagosRecibidos() {
		return numPagosRecibidos;
	}
	public void setNumPagosRecibidos(Integer numPagosRecibidos) {
		this.numPagosRecibidos = numPagosRecibidos;
	}
	public Integer getNumPagosCapital() {
		return numPagosCapital;
	}
	public void setNumPagosCapital(Integer numPagosCapital) {
		this.numPagosCapital = numPagosCapital;
	}
	public Integer getNumPagosInterOrdi() {
		return numPagosInterOrdi;
	}
	public void setNumPagosInterOrdi(Integer numPagosInterOrdi) {
		this.numPagosInterOrdi = numPagosInterOrdi;
	}
	public Integer getNumPagosInteMora() {
		return numPagosInteMora;
	}
	public void setNumPagosInteMora(Integer numPagosInteMora) {
		this.numPagosInteMora = numPagosInteMora;
	}
	public Double getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}
	public Integer getComisPagadas() {
		return comisPagadas;
	}
	public void setComisPagadas(Integer comisPagadas) {
		this.comisPagadas = comisPagadas;
	}
	public Integer getNumComisRecibidas() {
		return numComisRecibidas;
	}
	public void setNumComisRecibidas(Integer numComisRecibidas) {
		this.numComisRecibidas = numComisRecibidas;
	}
	
	public Double getInverRealiz() {
		return inverRealiz;
	}
	public void setInverRealiz(Double inverRealiz) {
		this.inverRealiz = inverRealiz;
	}
	public Double getPagCapRecib() {
		return pagCapRecib;
	}
	public void setPagCapRecib(Double pagCapRecib) {
		this.pagCapRecib = pagCapRecib;
	}
	public Double getIntOrdRec() {
		return intOrdRec;
	}
	public void setIntOrdRec(Double intOrdRec) {
		this.intOrdRec = intOrdRec;
	}
	public Double getIntMoraRec() {
		return intMoraRec;
	}
	public void setIntMoraRec(Double intMoraRec) {
		this.intMoraRec = intMoraRec;
	}
	public Double getRecupMorosos() {
		return recupMorosos;
	}
	public void setRecupMorosos(Double recupMorosos) {
		this.recupMorosos = recupMorosos;
	}
	public Double getRetenidoISR() {
		return retenidoISR;
	}
	public void setRetenidoISR(Double retenidoISR) {
		this.retenidoISR = retenidoISR;
	}
	public Double getComisCobrad() {
		return comisCobrad;
	}
	public void setComisCobrad(Double comisCobrad) {
		this.comisCobrad = comisCobrad;
	}
	public Double getComisPagad() {
		return comisPagad;
	}
	public void setComisPagad(Double comisPagad) {
		this.comisPagad = comisPagad;
	}
	public Double getAjustes() {
		return ajustes;
	}
	public void setAjustes(Double ajustes) {
		this.ajustes = ajustes;
	}
	public Double getQuebrantos() {
		return quebrantos;
	}
	public void setQuebrantos(Double quebrantos) {
		this.quebrantos = quebrantos;
	}
	public Double getQuebranXapli() {
		return quebranXapli;
	}
	public void setQuebranXapli(Double quebranXapli) {
		this.quebranXapli = quebranXapli;
	}
	public Double getPremiosRecom() {
		return premiosRecom;
	}
	public void setPremiosRecom(Double premiosRecom) {
		this.premiosRecom = premiosRecom;
	}
	
/*	
	public ProyectLoanService getServicioProyecto() {
		return servicioProyecto;
	}
	
	public NaturalPersonService getPersonaNaturalService() {
		return personaNaturalService;
	}
*/	

	public void setServicioProyecto(ProyectLoanService servicioProyecto) {
		this.servicioProyecto = servicioProyecto;
	}

	public String getTableResult() {
		return tableResult;
	}

	public void setTableResult(String tableResult) {
		this.tableResult = tableResult;
	}

	public List<SafiProyecInProcess> getSafiProyecInProcessLst() {
		return safiProyecInProcessLst;
	}

	public void setSafiProyecInProcessLst(
			List<SafiProyecInProcess> safiProyecInProcessLst) {
		this.safiProyecInProcessLst = safiProyecInProcessLst;
	}
	
	public List<ProyectLoanActiveInSafi> getSafiProyecLoanActiveLst() {
		return safiProyecLoanActiveLst;
	}

	public void setSafiProyecLoanActiveLst(
			List<ProyectLoanActiveInSafi> safiProyecLoanActiveLst) {
		this.safiProyecLoanActiveLst = safiProyecLoanActiveLst;
	}

	public String getBackward1_15_amount() {
		return backward1_15_amount;
	}

	public void setBackward1_15_amount(String backward1_15_amount) {
		this.backward1_15_amount = backward1_15_amount;
	}

	public int getBackward1_15_number() {
		return backward1_15_number;
	}

	public void setBackward1_15_number(int backward1_15_number) {
		this.backward1_15_number = backward1_15_number;
	}

	public String getValArrGraphic1() {
		return valArrGraphic1;
	}

	public void setValArrGraphic1(String valArrGraphic1) {
		this.valArrGraphic1 = valArrGraphic1;
	}

	public String getValArrGraphic2() {
		return valArrGraphic2;
	}

	public void setValArrGraphic2(String valArrGraphic2) {
		this.valArrGraphic2 = valArrGraphic2;
	}

	public boolean isDisplayName() {
		return displayName;
	}

	public void setDisplayName(boolean displayName) {
		this.displayName = displayName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getValArrGraphic4() {
		return valArrGraphic4;
	}

	public void setValArrGraphic4(String valArrGraphic4) {
		this.valArrGraphic4 = valArrGraphic4;
	}

	public List<SellingProyectBean> getProyecSellingLst() {
		return proyecSellingLst;
	}

	public void setProyecSellingLst(List<SellingProyectBean> proyecSellingLst) {
		this.proyecSellingLst = proyecSellingLst;
	}

	public String getValueClick() {
		return valueClick;
	}

	public void setValueClick(String valueClick) {
		this.valueClick = valueClick;
	}

	public String getValMonto() {
		return valMonto;
	}

	public void setValMonto(String valMonto) {
		this.valMonto = valMonto;
	}

	public String getValCredit() {
		return valCredit;
	}

	public void setValCredit(String valCredit) {
		this.valCredit = valCredit;
	}

	public String getValMontoStr() {
		return valMontoStr;
	}

	public void setValMontoStr(String valMontoStr) {
		this.valMontoStr = valMontoStr;
	}

	public String getMontoProp() {
		return montoProp;
	}

	public void setMontoProp(String montoProp) {
		this.montoProp = montoProp;
	}

	public String getPorcProp() {
		return porcProp;
	}

	public void setPorcProp(String porcProp) {
		this.porcProp = porcProp;
	}

	public String getValMontoUltOfer() {
		return valMontoUltOfer;
	}

	public void setValMontoUltOfer(String valMontoUltOfer) {
		this.valMontoUltOfer = valMontoUltOfer;
	}

	public String getValMontoUltOferStr() {
		return valMontoUltOferStr;
	}

	public void setValMontoUltOferStr(String valMontoUltOferStr) {
		this.valMontoUltOferStr = valMontoUltOferStr;
	}

	public String getValuesforGarantia() {
		return valuesforGarantia;
	}

	public void setValuesforGarantia(String valuesforGarantia) {
		this.valuesforGarantia = valuesforGarantia;
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

	public AmortizacionInversionistaService getAmortInverService() {
		return amortInverService;
	}

	public void setAmortInverService(
			AmortizacionInversionistaService amortInverService) {
		this.amortInverService = amortInverService;
	}
	
	public String getInterest_charged() {
		return interest_charged;
	}

	public void setInterest_charged(String interest_charged) {
		this.interest_charged = interest_charged;
	}

	public String getTotal_balance() {
		return total_balance;
	}

	public void setTotal_balance(String total_balance) {
		this.total_balance = total_balance;
	}

	public String getTotal_payments() {
		return total_payments;
	}

	public void setTotal_payments(String total_payments) {
		this.total_payments = total_payments;
	}

	public String getBalances_cash() {
		return balances_cash;
	}

	public void setBalances_cash(String balances_cash) {
		this.balances_cash = balances_cash;
	}

	public String getBalances_investment_process() {
		return balances_investment_process;
	}

	public void setBalances_investment_process(String balances_investment_process) {
		this.balances_investment_process = balances_investment_process;
	}

	public String getBalances_active_investments() {
		return balances_active_investments;
	}

	public void setBalances_active_investments(String balances_active_investments) {
		this.balances_active_investments = balances_active_investments;
	}

	public String getActive_amount() {
		return active_amount;
	}

	public void setActive_amount(String active_amount) {
		this.active_amount = active_amount;
	}

	public String getIn_process_amount() {
		return in_process_amount;
	}

	public void setIn_process_amount(String in_process_amount) {
		this.in_process_amount = in_process_amount;
	}

	public String getBackward16_30_amount() {
		return backward16_30_amount;
	}

	public void setBackward16_30_amount(String backward16_30_amount) {
		this.backward16_30_amount = backward16_30_amount;
	}

	public String getBackward31_90_amount() {
		return backward31_90_amount;
	}

	public void setBackward31_90_amount(String backward31_90_amount) {
		this.backward31_90_amount = backward31_90_amount;
	}

	public String getExpired91_120_amount() {
		return expired91_120_amount;
	}

	public void setExpired91_120_amount(String expired91_120_amount) {
		this.expired91_120_amount = expired91_120_amount;
	}

	public String getExpired121_180_amount() {
		return expired121_180_amount;
	}

	public void setExpired121_180_amount(String expired121_180_amount) {
		this.expired121_180_amount = expired121_180_amount;
	}

	public String getBroken_amount() {
		return broken_amount;
	}

	public void setBroken_amount(String broken_amount) {
		this.broken_amount = broken_amount;
	}

	public String getFully_paid_amount() {
		return fully_paid_amount;
	}

	public void setFully_paid_amount(String fully_paid_amount) {
		this.fully_paid_amount = fully_paid_amount;
	}

	public String getPayments_received_amount() {
		return payments_received_amount;
	}

	public void setPayments_received_amount(String payments_received_amount) {
		this.payments_received_amount = payments_received_amount;
	}

	public String getCapital_amount() {
		return capital_amount;
	}

	public void setCapital_amount(String capital_amount) {
		this.capital_amount = capital_amount;
	}

	public String getOrdinary_interests_amount() {
		return ordinary_interests_amount;
	}

	public void setOrdinary_interests_amount(String ordinary_interests_amount) {
		this.ordinary_interests_amount = ordinary_interests_amount;
	}

	public String getInterest_on_arrears_amount() {
		return interest_on_arrears_amount;
	}

	public void setInterest_on_arrears_amount(String interest_on_arrears_amount) {
		this.interest_on_arrears_amount = interest_on_arrears_amount;
	}

	public String getDepositos() {
		return depositos;
	}

	public void setDepositos(String depositos) {
		this.depositos = depositos;
	}

	public Integer getTemporal() {
		return temporal;
	}

	public void setTemporal(Integer temporal) {
		this.temporal = temporal;
	}
	
	public ArrayList<String> getInfoCalEst() {
		return infoCalEst;
	}
	public void setInfoCalEst(ArrayList<String> infoCalEst) {
		this.infoCalEst = infoCalEst;
	}
	public ArrayList<String> getInfoCalPorc() {
		return infoCalPorc;
	}
	public void setInfoCalPorc(ArrayList<String> infoCalPorc) {
		this.infoCalPorc = infoCalPorc;
	}
	public ArrayList<String> getInfoTasaEst() {
		return infoTasaEst;
	}
	public void setInfoTasaEst(ArrayList<String> infoTasaEst) {
		this.infoTasaEst = infoTasaEst;
	}
	public ArrayList<String> getInfoTasaPorc() {
		return infoTasaPorc;
	}
	public void setInfoTasaPorc(ArrayList<String> infoTasaPorc) {
		this.infoTasaPorc = infoTasaPorc;
	}

	public String getCuentaAhoID() {
		return cuentaAhoID;
	}

	public void setCuentaAhoID(String cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}

	public int getNumInvActivas() {
		return numInvActivas;
	}

	public void setNumInvActivas(int numInvActivas) {
		this.numInvActivas = numInvActivas;
	}

	public String getMontoInversiones() {
		return montoInversiones;
	}

	public void setMontoInversiones(String montoInversiones) {
		this.montoInversiones = montoInversiones;
	}

	public String getIntARecibir() {
		return intARecibir;
	}

	public void setIntARecibir(String intARecibir) {
		this.intARecibir = intARecibir;
	}

	public String getSaldoIntGlobal() {
		return saldoIntGlobal;
	}

	public void setSaldoIntGlobal(String saldoIntGlobal) {
		this.saldoIntGlobal = saldoIntGlobal;
	}

	public String getSaldoIntPlazoFijo() {
		return saldoIntPlazoFijo;
	}

	public void setSaldoIntPlazoFijo(String saldoIntPlazoFijo) {
		this.saldoIntPlazoFijo = saldoIntPlazoFijo;
	}

	public String getInteresCobrado() {
		return interesCobrado;
	}

	public void setInteresCobrado(String interesCobrado) {
		this.interesCobrado = interesCobrado;
	}

	public String getMoraCobrado() {
		return moraCobrado;
	}

	public void setMoraCobrado(String moraCobrado) {
		this.moraCobrado = moraCobrado;
	}

	public String getSaldoVigente0() {
		return saldoVigente0;
	}

	public void setSaldoVigente0(String saldoVigente0) {
		this.saldoVigente0 = saldoVigente0;
	}

	public String getSaldoVigente1a15() {
		return saldoVigente1a15;
	}

	public void setSaldoVigente1a15(String saldoVigente1a15) {
		this.saldoVigente1a15 = saldoVigente1a15;
	}

	public String getSaldoVigente16a30() {
		return saldoVigente16a30;
	}

	public void setSaldoVigente16a30(String saldoVigente16a30) {
		this.saldoVigente16a30 = saldoVigente16a30;
	}

	public String getSaldoVigente31a90() {
		return saldoVigente31a90;
	}

	public void setSaldoVigente31a90(String saldoVigente31a90) {
		this.saldoVigente31a90 = saldoVigente31a90;
	}

	public String getSaldoVigente91a120() {
		return saldoVigente91a120;
	}

	public void setSaldoVigente91a120(String saldoVigente91a120) {
		this.saldoVigente91a120 = saldoVigente91a120;
	}

	public String getSaldoVigente120mas() {
		return saldoVigente120mas;
	}

	public void setSaldoVigente120mas(String saldoVigente120mas) {
		this.saldoVigente120mas = saldoVigente120mas;
	}

	public String getSaldoAtrasado0() {
		return saldoAtrasado0;
	}

	public void setSaldoAtrasado0(String saldoAtrasado0) {
		this.saldoAtrasado0 = saldoAtrasado0;
	}

	public String getSaldoAtrasado1a15() {
		return saldoAtrasado1a15;
	}

	public void setSaldoAtrasado1a15(String saldoAtrasado1a15) {
		this.saldoAtrasado1a15 = saldoAtrasado1a15;
	}

	public String getSaldoAtrasado16a30() {
		return saldoAtrasado16a30;
	}

	public void setSaldoAtrasado16a30(String saldoAtrasado16a30) {
		this.saldoAtrasado16a30 = saldoAtrasado16a30;
	}

	public String getSaldoAtrasado31a90() {
		return saldoAtrasado31a90;
	}

	public void setSaldoAtrasado31a90(String saldoAtrasado31a90) {
		this.saldoAtrasado31a90 = saldoAtrasado31a90;
	}

	public String getSaldoAtrasado91a120() {
		return saldoAtrasado91a120;
	}

	public void setSaldoAtrasado91a120(String saldoAtrasado91a120) {
		this.saldoAtrasado91a120 = saldoAtrasado91a120;
	}

	public String getSaldoAtrasado120mas() {
		return saldoAtrasado120mas;
	}

	public void setSaldoAtrasado120mas(String saldoAtrasado120mas) {
		this.saldoAtrasado120mas = saldoAtrasado120mas;
	}

	/*public InvestmentsAtraEdoCtaServiceImp getInvestmentsAtras() {
		return investmentsAtras;
	}

	public void setInvestmentsAtras(InvestmentsAtraEdoCtaServiceImp investmentsAtras) {
		this.investmentsAtras = investmentsAtras;
	}*/

	public String getSaldoIntVigente() {
		return saldoIntVigente;
	}

	public void setSaldoIntVigente(String saldoIntVigente) {
		this.saldoIntVigente = saldoIntVigente;
	}

	public String getSaldoIntAtrasado() {
		return saldoIntAtrasado;
	}

	public void setSaldoIntAtrasado(String saldoIntAtrasado) {
		this.saldoIntAtrasado = saldoIntAtrasado;
	}

	public String getSaldoCapVigTotal() {
		return saldoCapVigTotal;
	}

	public void setSaldoCapVigTotal(String saldoCapVigTotal) {
		this.saldoCapVigTotal = saldoCapVigTotal;
	}

	public String getSaldoCapAtrTotal() {
		return saldoCapAtrTotal;
	}

	public void setSaldoCapAtrTotal(String saldoCapAtrTotal) {
		this.saldoCapAtrTotal = saldoCapAtrTotal;
	}

	public int getNotes_active_investments_cli() {
		return notes_active_investments_cli;
	}

	public void setNotes_active_investments_cli(int notes_active_investments_cli) {
		this.notes_active_investments_cli = notes_active_investments_cli;
	}

	public int getActive_number_cli() {
		return active_number_cli;
	}

	public void setActive_number_cli(int active_number_cli) {
		this.active_number_cli = active_number_cli;
	}

	public int getBackward1_15_number_cli() {
		return backward1_15_number_cli;
	}

	public void setBackward1_15_number_cli(int backward1_15_number_cli) {
		this.backward1_15_number_cli = backward1_15_number_cli;
	}

	public int getBackward16_30_number_cli() {
		return backward16_30_number_cli;
	}

	public void setBackward16_30_number_cli(int backward16_30_number_cli) {
		this.backward16_30_number_cli = backward16_30_number_cli;
	}

	public int getBackward31_90_number_cli() {
		return backward31_90_number_cli;
	}

	public void setBackward31_90_number_cli(int backward31_90_number_cli) {
		this.backward31_90_number_cli = backward31_90_number_cli;
	}

	public int getExpired91_120_number_cli() {
		return expired91_120_number_cli;
	}

	public void setExpired91_120_number_cli(int expired91_120_number_cli) {
		this.expired91_120_number_cli = expired91_120_number_cli;
	}

	public int getExpired121_180_number_cli() {
		return expired121_180_number_cli;
	}

	public void setExpired121_180_number_cli(int expired121_180_number_cli) {
		this.expired121_180_number_cli = expired121_180_number_cli;
	}

	public String getView_delinquentInv() {
		return view_delinquentInv;
	}

	public void setView_delinquentInv(String view_delinquentInv) {
		this.view_delinquentInv = view_delinquentInv;
	}

	public char getStatus_delinquentinv() {
		return status_delinquentinv;
	}

	public void setStatus_delinquentinv(char status_delinquentinv) {
		this.status_delinquentinv = status_delinquentinv;
	}

	public int getNumber_notes_active() {
		return number_notes_active;
	}

	public void setNumber_notes_active(int number_notes_active) {
		this.number_notes_active = number_notes_active;
	}

	public int getNumber_NoDelinquent() {
		return number_NoDelinquent;
	}

	public void setNumber_NoDelinquent(int number_NoDelinquent) {
		this.number_NoDelinquent = number_NoDelinquent;
	}

	public int getNumber_backward1_15() {
		return number_backward1_15;
	}

	public void setNumber_backward1_15(int number_backward1_15) {
		this.number_backward1_15 = number_backward1_15;
	}

	public int getNumber_backward16_30() {
		return number_backward16_30;
	}

	public void setNumber_backward16_30(int number_backward16_30) {
		this.number_backward16_30 = number_backward16_30;
	}

	public int getNumber_backward31_90() {
		return number_backward31_90;
	}

	public void setNumber_backward31_90(int number_backward31_90) {
		this.number_backward31_90 = number_backward31_90;
	}

	public int getNumber_backward91_120() {
		return number_backward91_120;
	}

	public void setNumber_backward91_120(int number_backward91_120) {
		this.number_backward91_120 = number_backward91_120;
	}

	public int getNumber_backward121_180() {
		return number_backward121_180;
	}

	public void setNumber_backward121_180(int number_backward121_180) {
		this.number_backward121_180 = number_backward121_180;
	}

	public String getViewLabelDelinquent() {
		return viewLabelDelinquent;
	}

	public void setViewLabelDelinquent(String viewLabelDelinquent) {
		this.viewLabelDelinquent = viewLabelDelinquent;
	}

	public String getLabelDelinquent() {
		return labelDelinquent;
	}

	public void setLabelDelinquent(String labelDelinquent) {
		this.labelDelinquent = labelDelinquent;
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public AccessService getService_access() {
		return service_access;
	}

	public void setService_access(AccessService service_access) {
		this.service_access = service_access;
	}

	public int getUnder_warranty_number_cli() {
		return under_warranty_number_cli;
	}

	public void setUnder_warranty_number_cli(int under_warranty_number_cli) {
		this.under_warranty_number_cli = under_warranty_number_cli;
	}

	public int getUnder_warranty_number() {
		return under_warranty_number;
	}

	public void setUnder_warranty_number(int under_warranty_number) {
		this.under_warranty_number = under_warranty_number;
	}

	public String getSaldoCapitalCtaOrden() {
		return saldoCapitalCtaOrden;
	}

	public void setSaldoCapitalCtaOrden(String saldoCapitalCtaOrden) {
		this.saldoCapitalCtaOrden = saldoCapitalCtaOrden;
	}

	public String getSaldoInteresCtaOrden() {
		return saldoInteresCtaOrden;
	}

	public void setSaldoInteresCtaOrden(String saldoInteresCtaOrden) {
		this.saldoInteresCtaOrden = saldoInteresCtaOrden;
	}

	public String getSaldoTotalCtaOrden() {
		return saldoTotalCtaOrden;
	}

	public void setSaldoTotalCtaOrden(String saldoTotalCtaOrden) {
		this.saldoTotalCtaOrden = saldoTotalCtaOrden;
	}

	public int getNumber_under_warranty() {
		return number_under_warranty;
	}

	public void setNumber_under_warranty(int number_under_warranty) {
		this.number_under_warranty = number_under_warranty;
	}

	public String getLabelBalances() {
		return labelBalances;
	}

	public void setLabelBalances(String labelBalances) {
		this.labelBalances = labelBalances;
	}

	public ProyeccionGraficaInvService getProyeccionGraficaInvService() {
		return proyeccionGraficaInvService;
	}

	public void setProyeccionGraficaInvService(ProyeccionGraficaInvService proyeccionGraficaInvService) {
		this.proyeccionGraficaInvService = proyeccionGraficaInvService;
	}
	
	public RendimientosInvService getRendimientosInvService() {
		return rendimientosInvService;
	}

	public void setRendimientosInvService(RendimientosInvService rendimientosInvService) {
		this.rendimientosInvService = rendimientosInvService;
	}
	
	public RetornoAnualService getRetornoAnualService() {
		return retornoAnualService;
	}

	public void setRetornoAnualService(RetornoAnualService retornoAnualService) {
		this.retornoAnualService = retornoAnualService;
	}
	
	public List<RendimientosInv> getLstRendimientos(){
		return this.lstRendimientos;
	}
	
	public void setLstRendimientos( List<RendimientosInv> lstRendimientos ){
		this.lstRendimientos = lstRendimientos;
	}
	
	public String getRetornoAnual(){
		return this.retornoAnual;
	}
	
	public void setRetornoAnual( String retornoAnual ){
		this.retornoAnual = retornoAnual;
	}
	
	public String getRetornoAnual_Ajustado(){
		return this.retornoAnual_Ajustado;
	}
	
	public void setRetornoAnual_Ajustado( String retornoAnual_Ajustado ){
		this.retornoAnual_Ajustado = retornoAnual_Ajustado;
	}
	
	public String getScriptGraphicBar(){
		return this.scriptGraphicBar;
	}
	
	public void setScriptGraphicBar( String scriptGraphicBar ){
		this.scriptGraphicBar = scriptGraphicBar;
	}

	public String getScriptGraphicSaldoDisponible() {
		return scriptGraphicSaldoDisponible;
	}

	public void setScriptGraphicSaldoDisponible(String scriptGraphicSaldoDisponible) {
		this.scriptGraphicSaldoDisponible = scriptGraphicSaldoDisponible;
	}
	public void setScriptGraphicCombo(String scriptGraphicCombo) {
		this.scriptGraphicCombo = scriptGraphicCombo;
	}
	
	public String getScriptGraphicCombo() {
		return this.scriptGraphicCombo ;
	}

	public InfoNotificationService getInfonotificationservice() {
		return infonotificationservice;
	}

	public void setInfonotificationservice(InfoNotificationService infonotificationservice) {
		this.infonotificationservice = infonotificationservice;
	}

	public boolean isDisplayNotificacion() {
		return displayNotificacion;
	}

	public void setDisplayNotificacion(boolean displayNotificacion) {
		this.displayNotificacion = displayNotificacion;
	}
}
