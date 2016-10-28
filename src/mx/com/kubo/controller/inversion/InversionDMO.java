package mx.com.kubo.controller.inversion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemInversion;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.services.InvestorParamService;
import mx.com.kubo.services.MontoInvertido_F_G_CollectorService;
import mx.com.kubo.services.ProfileFormValueService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.services.ProyectInfoService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SaldoInversionistaService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TiendaCreditosService;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public class InversionDMO {

	protected List<ServiceCalling> 		lstService 				= new ArrayList<ServiceCalling>();
	protected List<String> 				storedString 			= new ArrayList<String>();
	protected List<ItemInversion> 			listToInv				= new ArrayList<ItemInversion>();
	protected List<ItemLoanList>  			proyectList  			= new ArrayList<ItemLoanList>();
	protected List<ProyectFunding>			lstProyectosFondeados 	= new ArrayList<ProyectFunding>();
	protected List<InvestorsAccounts>		listInvAccounts			= new ArrayList<InvestorsAccounts>();
	protected List<ViewProyectTienda> 		temporalProyectListView = null;
	protected List<InvestmentProgressDet>  progressdetlst			= new ArrayList<InvestmentProgressDet>();
	
	protected safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator 	locatorInvKuboSafi;
	protected safisrv.ws.InvKuboServicios.SAFIServicios 				servicioInvKuboSafi;
	
	
	protected safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorAccount ;
	protected safisrv.ws.CuentasServicios.SAFIServicios servCuentasCliente;
	
	protected SaldoInversionistaService saldoinversionistaservice;
	protected ProyectLoanService proyectLoanService;
	protected TiendaCreditosService tiendacreditosservice;
	protected ProyectFundingService proyectFundingService;
	protected SystemParamService systemparamservice;
	protected ProfileInvService profile_inv_service;
	protected MontoInvertido_F_G_CollectorService montoInvertido_F_G_service;
	protected ProfileFormValueService profileformvalueservice;
	protected InvestmentProgress  investmentprogress;
	protected ProyectInfoService proyectInfoService;
	protected SavingAccountService savingaccountservice;
	
	protected InvestorParamService investorparamservice;
	
	protected String investmentType;
	
	protected Double montoinvertido 			= 0D;
	protected Double montoNOinvertido 			= 0D;
	protected Double tasaPonderada				= 0D;
	protected Double saldoTotal				= 0D;
	
	//Variables para calcular dispersion
	
	Hashtable<String,List<ProyectFunding> > hTFunding = null;
	
	protected Double maxPorcPryG = 0D;
	protected Double maximoInvBySaldoG = 0D;
	protected Double maximoInvBySaldoPryE5 = 0D;
	protected Double maximoInvBySaldoPryE4 = 0D;
	protected Double montoMinF1_G1_G = 0D;
	protected Double montoInvertido_F_G = 0D;
	protected Double montoMaximoPorProyecto_F_G = 0D;
	
	protected Double limiteMaximoInversion_F_G = 0D;
	
	protected Double montoDisponibleEn_E5 = 0D;
	 
	protected Double montoMinG = 0D;
	protected Double montoMinE4E5G = 0D;
	protected Double monto_a_invertir_temp = 0D;
	protected Double montoSaldoG = 0D;
	protected Double monto_a_invertir = 0D;
	protected Double montoInvertido_F_G_temp = 0D;
	
	protected Double investmentBiteVAL = 0D;
	
	protected Double porcMaxSaldoG = 0D;
	protected Double porcMaxSaldoPryE5G = 0D;
	protected Double porcMaxSaldoPryE4G = 0D;
	protected Double porcMaxSUMSaldoProyE5 = 0D;
	
	protected Double porc_invertido_en_E5 = 0D;
	protected Double porc_invertido_en_FG = 0D;
	
	protected Double porcMaxSaldoProyF1_G1 = 0D;
	protected Double porcent_suma_F1_G1_G = 0D;
	
	
	protected boolean flagMin_E5_E4 = false;
	protected boolean flagNotRule = false;
	protected boolean puedeInvertire_en_F_G = false;
	protected boolean puedeInvertire_en_E5 = false;
	
	protected boolean flag_alto_riesgo = false;
	
	//fin Variables para calcular dispersion
	
	protected int proyectos 					= 0;
	protected int porcentaje 					= 0;
	protected int proyectosNoFondeados 		= 0;
	protected int typeSearch					= 0;
	
	protected String  investor_id;
    
	protected String account;
	protected String safiClientId;
	
	protected String scriptStatus;
	
	//protected String cad; // Cadena que sirve para realizar un filtro desde NavigationInvestment
	protected FilterStore filter;
	
	public List<ServiceCalling> getLstService() {
		return lstService;
	}
	public void setLstService(List<ServiceCalling> lstService) {
		this.lstService = lstService;
	}
	public List<String> getStoredString() {
		return storedString;
	}
	public void setStoredString(List<String> storedString) {
		this.storedString = storedString;
	}
	public List<ItemInversion> getListToInv() {
		return listToInv;
	}
	public void setListToInv(List<ItemInversion> listToInv) {
		this.listToInv = listToInv;
	}
	public List<ItemLoanList> getProyectList() {
		return proyectList;
	}
	public void setProyectList(List<ItemLoanList> proyectList) {
		this.proyectList = proyectList;
	}
	public Double getMontoinvertido() {
		return montoinvertido;
	}
	public void setMontoinvertido(Double montoinvertido) {
		this.montoinvertido = montoinvertido;
	}
	public Double getMontoNOinvertido() {
		return montoNOinvertido;
	}
	public void setMontoNOinvertido(Double montoNOinvertido) {
		this.montoNOinvertido = montoNOinvertido;
	}
	public int getProyectos() {
		return proyectos;
	}
	public void setProyectos(int proyectos) {
		this.proyectos = proyectos;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public int getProyectosNoFondeados() {
		return proyectosNoFondeados;
	}
	public void setProyectosNoFondeados(int proyectosNoFondeados) {
		this.proyectosNoFondeados = proyectosNoFondeados;
	}
	public String getInvestor_id() {
		return investor_id;
	}
	public void setInvestor_id(String investor_id) {
		this.investor_id = investor_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSafiClientId() {
		return safiClientId;
	}
	public void setSafiClientId(String safiClientId) {
		this.safiClientId = safiClientId;
	}
	public List<ProyectFunding> getLstProyectosFondeados() {
		return lstProyectosFondeados;
	}
	public void setLstProyectosFondeados(List<ProyectFunding> lstProyectosFondeados) {
		this.lstProyectosFondeados = lstProyectosFondeados;
	}
	public Double getTasaPonderada() {
		return tasaPonderada;
	}
	public void setTasaPonderada(Double tasaPonderada) {
		this.tasaPonderada = tasaPonderada;
	}
	public SAFIServiciosServiceLocator getLocatorInvKuboSafi() {
		return locatorInvKuboSafi;
	}
	public void setLocatorInvKuboSafi(SAFIServiciosServiceLocator locatorInvKuboSafi) {
		this.locatorInvKuboSafi = locatorInvKuboSafi;
	}
	public safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator getLocatorAccount() {
		return locatorAccount;
	}
	public void setLocatorAccount(safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorAccount) {
		this.locatorAccount = locatorAccount;
	}
	public SAFIServicios getServicioInvKuboSafi() {
		return servicioInvKuboSafi;
	}
	public void setServicioInvKuboSafi(SAFIServicios servicioInvKuboSafi) {
		this.servicioInvKuboSafi = servicioInvKuboSafi;
	}
	public safisrv.ws.CuentasServicios.SAFIServicios getServCuentasCliente() {
		return servCuentasCliente;
	}
	public void setServCuentasCliente(safisrv.ws.CuentasServicios.SAFIServicios servCuentasCliente) {
		this.servCuentasCliente = servCuentasCliente;
	}
	public List<InvestorsAccounts> getListInvAccounts() {
		return listInvAccounts;
	}
	public void setListInvAccounts(List<InvestorsAccounts> listInvAccounts) {
		this.listInvAccounts = listInvAccounts;
	}
	public Double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	public String getScriptStatus() {
		return scriptStatus;
	}
	public void setScriptStatus(String scriptStatus) {
		this.scriptStatus = scriptStatus;
	}
	public int getTypeSearch() {
		return typeSearch;
	}
	public void setTypeSearch(int typeSearch) {
		this.typeSearch = typeSearch;
	}
	public TiendaCreditosService getTiendacreditosservice() {
		return tiendacreditosservice;
	}
	public void setTiendacreditosservice(TiendaCreditosService tiendacreditosservice) {
		this.tiendacreditosservice = tiendacreditosservice;
	}
	public Hashtable<String, List<ProyectFunding>> gethTFunding() {
		return hTFunding;
	}
	public void sethTFunding(Hashtable<String, List<ProyectFunding>> hTFunding) {
		this.hTFunding = hTFunding;
	}
	public Double getMaxPorcPryG() {
		return maxPorcPryG;
	}
	public void setMaxPorcPryG(Double maxPorcPryG) {
		this.maxPorcPryG = maxPorcPryG;
	}
	public Double getMaximoInvBySaldoG() {
		return maximoInvBySaldoG;
	}
	public void setMaximoInvBySaldoG(Double maximoInvBySaldoG) {
		this.maximoInvBySaldoG = maximoInvBySaldoG;
	}
	public Double getMaximoInvBySaldoPryE5() {
		return maximoInvBySaldoPryE5;
	}
	public void setMaximoInvBySaldoPryE5(Double maximoInvBySaldoPryE5) {
		this.maximoInvBySaldoPryE5 = maximoInvBySaldoPryE5;
	}
	public Double getMaximoInvBySaldoPryE4() {
		return maximoInvBySaldoPryE4;
	}
	public void setMaximoInvBySaldoPryE4(Double maximoInvBySaldoPryE4) {
		this.maximoInvBySaldoPryE4 = maximoInvBySaldoPryE4;
	}
	public Double getMontoMinG() {
		return montoMinG;
	}
	public void setMontoMinG(Double montoMinG) {
		this.montoMinG = montoMinG;
	}
	public Double getMontoMinE4E5G() {
		return montoMinE4E5G;
	}
	public void setMontoMinE4E5G(Double montoMinE4E5G) {
		this.montoMinE4E5G = montoMinE4E5G;
	}
	public Double getMonto_a_invertir_temp() {
		return monto_a_invertir_temp;
	}
	public void setMonto_a_invertir_temp(Double monto_a_invertir_temp) {
		this.monto_a_invertir_temp = monto_a_invertir_temp;
	}
	public Double getMontoSaldoG() {
		return montoSaldoG;
	}
	public void setMontoSaldoG(Double montoSaldoG) {
		this.montoSaldoG = montoSaldoG;
	}
	public Double getMonto_a_invertir() {
		return monto_a_invertir;
	}
	public void setMonto_a_invertir(Double monto_a_invertir) {
		this.monto_a_invertir = monto_a_invertir;
	}
	public Double getInvestmentBiteVAL() {
		return investmentBiteVAL;
	}
	public void setInvestmentBiteVAL(Double investmentBiteVAL) {
		this.investmentBiteVAL = investmentBiteVAL;
	}
	public Double getPorcMaxSaldoG() {
		return porcMaxSaldoG;
	}
	public void setPorcMaxSaldoG(Double porcMaxSaldoG) {
		this.porcMaxSaldoG = porcMaxSaldoG;
	}
	public Double getPorcMaxSaldoPryE5G() {
		return porcMaxSaldoPryE5G;
	}
	public void setPorcMaxSaldoPryE5G(Double porcMaxSaldoPryE5G) {
		this.porcMaxSaldoPryE5G = porcMaxSaldoPryE5G;
	}
	public Double getPorcMaxSaldoPryE4G() {
		return porcMaxSaldoPryE4G;
	}
	public void setPorcMaxSaldoPryE4G(Double porcMaxSaldoPryE4G) {
		this.porcMaxSaldoPryE4G = porcMaxSaldoPryE4G;
	}
	public boolean isFlagMin_E5_E4() {
		return flagMin_E5_E4;
	}
	public void setFlagMin_E5_E4(boolean flagMin_E5_E4) {
		this.flagMin_E5_E4 = flagMin_E5_E4;
	}
	public boolean isFlagNotRule() {
		return flagNotRule;
	}
	public void setFlagNotRule(boolean flagNotRule) {
		this.flagNotRule = flagNotRule;
	}
	
	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}
	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}
	public Double getMontoMinF1_G1_G() {
		return montoMinF1_G1_G;
	}
	public void setMontoMinF1_G1_G(Double montoMinF1_G1_G) {
		this.montoMinF1_G1_G = montoMinF1_G1_G;
	}
	public Double getPorcMaxSaldoProyF1_G1() {
		return porcMaxSaldoProyF1_G1;
	}
	public void setPorcMaxSaldoProyF1_G1(Double porcMaxSaldoProyF1_G1) {
		this.porcMaxSaldoProyF1_G1 = porcMaxSaldoProyF1_G1;
	}
	public Double getPorcent_suma_F1_G1_G() {
		return porcent_suma_F1_G1_G;
	}
	public void setPorcent_suma_F1_G1_G(Double porcent_suma_F1_G1_G) {
		this.porcent_suma_F1_G1_G = porcent_suma_F1_G1_G;
	}
	public Double getMontoInvertido_F_G() {
		return montoInvertido_F_G;
	}
	public void setMontoInvertido_F_G(Double montoInvertido_F_G) {
		this.montoInvertido_F_G = montoInvertido_F_G;
	}
	public boolean isPuedeInvertire_en_F_G() {
		return puedeInvertire_en_F_G;
	}
	public void setPuedeInvertire_en_F_G(boolean puedeInvertire_en_F_G) {
		this.puedeInvertire_en_F_G = puedeInvertire_en_F_G;
	}
	public Double getMontoMaximoPorProyecto_F_G() {
		return montoMaximoPorProyecto_F_G;
	}
	public void setMontoMaximoPorProyecto_F_G(Double montoMaximoPorProyecto_F_G) {
		this.montoMaximoPorProyecto_F_G = montoMaximoPorProyecto_F_G;
	}
	public Double getLimiteMaximoInversion_F_G() {
		return limiteMaximoInversion_F_G;
	}
	public void setLimiteMaximoInversion_F_G(Double limiteMaximoInversion_F_G) {
		this.limiteMaximoInversion_F_G = limiteMaximoInversion_F_G;
	}
	public Double getMontoInvertido_F_G_temp() {
		return montoInvertido_F_G_temp;
	}
	public void setMontoInvertido_F_G_temp(Double montoInvertido_F_G_temp) {
		this.montoInvertido_F_G_temp = montoInvertido_F_G_temp;
	}
	public List<InvestmentProgressDet> getProgressdetlst() {
		return progressdetlst;
	}
	public void setProgressdetlst(List<InvestmentProgressDet> progressdetlst) {
		this.progressdetlst = progressdetlst;
	}
	
	public String getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
	public InvestmentProgress getInvestmentprogress() {
		return investmentprogress;
	}
	public void setInvestmentprogress(InvestmentProgress investmentprogress) {
		this.investmentprogress = investmentprogress;
	}
	public FilterStore getFilter() {
		return filter;
	}
	public void setFilter(FilterStore filter) {
		this.filter = filter;
	}
	public Double getMontoDisponibleEn_E5() {
		return montoDisponibleEn_E5;
	}
	public void setMontoDisponibleEn_E5(Double montoDisponibleEn_E5) {
		this.montoDisponibleEn_E5 = montoDisponibleEn_E5;
	}
	public Double getPorcMaxSUMSaldoProyE5() {
		return porcMaxSUMSaldoProyE5;
	}
	public void setPorcMaxSUMSaldoProyE5(Double porcMaxSUMSaldoProyE5) {
		this.porcMaxSUMSaldoProyE5 = porcMaxSUMSaldoProyE5;
	}
	public boolean isPuedeInvertire_en_E5() {
		return puedeInvertire_en_E5;
	}
	public void setPuedeInvertire_en_E5(boolean puedeInvertire_en_E5) {
		this.puedeInvertire_en_E5 = puedeInvertire_en_E5;
	}
	
	
	
	
	
}
