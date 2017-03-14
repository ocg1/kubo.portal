package mx.com.kubo.rest.tienda;

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
import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.ViewForTiendaExec;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.rest.tienda.filters.FilterStoreIMO;
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

import mx.com.kubo.tools.Utilities;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public abstract class SAFIInvestmentDMO 
implements SAFIInvestmentIMO
{	
	protected SAFIServiciosServiceLocator 	locatorInvKuboSafi;
	protected SAFIServicios 				servicioInvKuboSafi;
		
//	protected safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorAccount ;
//	protected safisrv.ws.CuentasServicios.SAFIServicios servCuentasCliente;
	
	//protected SaldoInversionistaService saldoinversionistaservice;
	protected        ProyectLoanService proyectLoanService;
	protected     TiendaCreditosService tiendacreditosservice;
	protected     ProyectFundingService proyectFundingService;
	protected        SystemParamService systemparamservice;
	protected         ProfileInvService profile_inv_service;	
	protected   ProfileFormValueService profileformvalueservice;	
	protected        ProyectInfoService proyectInfoService;
	protected      SavingAccountService savingaccountservice;
	protected      InvestorParamService investorparamservice;
	protected MontoInvertido_F_G_CollectorService montoInvertido_F_G_service;
		
	protected InvestmentProgress  investmentprogress;
	protected FilterStore filter;
	//protected ConsultaCuentasPorClienteRequest request;
	//protected ConsultaCuentasPorClienteResponse resCliente;
	//protected SaldoInversionista saldoObj;
	//
	
	protected FilterStoreIMO filter_store;
	
	protected List<ViewForTiendaExec> viewForTiendaExec;
	protected List<ViewProyectTienda> temporalProyectListView;	
	protected List<ServiceCalling> lstService;
	protected List<String> storedString;
	protected List<ItemInversion> listToInv;
	protected List<ItemLoanList> proyectList;
	protected List<ProyectFunding> lstProyectosFondeados;
	protected List<InvestorsAccounts> listInvAccounts;	
	protected List<InvestmentProgressDet>  progressdetlst;	
			
	protected Hashtable<String,List<ProyectFunding> > hTFunding = null;
	
	protected String  investor_id;
	protected String investmentType;
	protected String account;
	protected String safiClientId;	
	protected String scriptStatus;
	
	protected Double montoinvertido	= 0D;
	protected Double montoNOinvertido = 0D;
	protected Double tasaPonderada = 0D;
	//protected Double saldoTotal	= 0D;
	protected Double saldoTotal;
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
	
	protected int proyectos = 0;
	protected int porcentaje = 0;
	protected int proyectosNoFondeados = 0;
	
	protected final int MIN_AMMOUNT_INVESTMENT_HIGH_RISK_ENABLED = 81;
	protected final int MAX_AMMOUNT_INVESTMENT_HIGH_RISK_ENABLED = 82;
	
	protected SAFIInvestmentDMO()
	{				
//		saldoinversionistaservice = Utilities.findBean("saldoInversionistaServiceImp");
		proyectLoanService		  = Utilities.findBean("proyectLoanServiceImp");
		tiendacreditosservice 	  = Utilities.findBean("tiendaCreditosServiceImp");
		proyectFundingService	  = Utilities.findBean("proyectFundingServiceImp");
		systemparamservice	  	  = Utilities.findBean("systemParamServiceImp");
		profile_inv_service	  	  = Utilities.findBean("profileInvServiceImp");
		montoInvertido_F_G_service= Utilities.findBean("montoInvertido_F_G_CollectorServiceImp");
		profileformvalueservice	  = Utilities.findBean("profileFormValueServiceImp");
		proyectInfoService		  = Utilities.findBean("proyectInfoServiceImp");
		savingaccountservice	  = Utilities.findBean("savingAccountServiceImp");
		investorparamservice	  = Utilities.findBean("investorParamServiceImp");
		
		lstService 				= new ArrayList<ServiceCalling>();
		storedString 			= new ArrayList<String>();
		listToInv				= new ArrayList<ItemInversion>();
		proyectList  			= new ArrayList<ItemLoanList>();
		lstProyectosFondeados 	= new ArrayList<ProyectFunding>();
//		listInvAccounts			= new ArrayList<InvestorsAccounts>();	
		progressdetlst			= new ArrayList<InvestmentProgressDet>();
		
		montoinvertido 			= 0D;
		montoNOinvertido 		= 0D;
		tasaPonderada			= 0D;			
		proyectos 				= 0;
		porcentaje 				= 0;
		proyectosNoFondeados 	= 0;
		
		scriptStatus = "";			
	}
	
	public void setSaldoTotal(Double saldoTotal)
	{
		this.saldoTotal = saldoTotal;
	}
	
	public void setListInvAccounts(List<InvestorsAccounts> listInvAccounts)
	{
		this.listInvAccounts = listInvAccounts;
	}
	
	public List<ItemLoanList> getProyectList() {
		return proyectList;
	}
	
	public void setProyectList(List<ItemLoanList> proyectList) 
	{
		this.proyectList = proyectList;
	}

	public SAFIServicios getServicioInvKuboSafi() {
		return servicioInvKuboSafi;
	}

/*	
	public List<InvestorsAccounts> getListInvAccounts() 
	{
		return listInvAccounts;
	}
	
	public Double getSaldoTotal() 
	{
		return saldoTotal;
	}
*/	

	public String getScriptStatus() 
	{
		return scriptStatus;
	}

	public Hashtable<String, List<ProyectFunding>> gethTFunding() {
		return hTFunding;
	}
	
	public void sethTFunding(Hashtable<String, List<ProyectFunding>> hTFunding) 
	{
		this.hTFunding = hTFunding;
	}
	
	public Double getMaxPorcPryG() 
	{
		return maxPorcPryG;
	}
	
	public Double getMaximoInvBySaldoG() 
	{
		return maximoInvBySaldoG;
	}
	
	public Double getMaximoInvBySaldoPryE5() 
	{
		return maximoInvBySaldoPryE5;
	}
	
	public Double getMaximoInvBySaldoPryE4() 
	{
		return maximoInvBySaldoPryE4;
	}

	public Double getMontoMinG() 
	{
		return montoMinG;
	}
	
	public Double getMontoMinE4E5G() 
	{
		return montoMinE4E5G;
	}
	
	public Double getMonto_a_invertir_temp() 
	{
		return monto_a_invertir_temp;
	}
	
	public void setMonto_a_invertir_temp(Double monto_a_invertir_temp) 
	{
		this.monto_a_invertir_temp = monto_a_invertir_temp;
	}
	
	public Double getMontoSaldoG() 
	{
		return montoSaldoG;
	}

	public Double getMonto_a_invertir() 
	{
		return monto_a_invertir;
	}
	
	public void setMonto_a_invertir(Double monto_a_invertir) 
	{
		this.monto_a_invertir = monto_a_invertir;
	}
	
	public Double getInvestmentBiteVAL()
	{
		return investmentBiteVAL;
	}
	
	public Double getPorcMaxSaldoG() {
		return porcMaxSaldoG;
	}
	
	public Double getPorcMaxSaldoPryE5G() 
	{
		return porcMaxSaldoPryE5G;
	}
	
	public Double getPorcMaxSaldoPryE4G()
	{
		return porcMaxSaldoPryE4G;
	}
	
	public boolean isFlagMin_E5_E4()
	{
		return flagMin_E5_E4;
	}
	
	public boolean isFlagNotRule() 
	{
		return flagNotRule;
	}

	public Double getMontoMinF1_G1_G()
	{
		return montoMinF1_G1_G;
	}
	
	public Double getPorcMaxSaldoProyF1_G1() 
	{
		return porcMaxSaldoProyF1_G1;
	}
	
	public Double getPorcent_suma_F1_G1_G() 
	{
		return porcent_suma_F1_G1_G;
	}
	
	public Double getMontoInvertido_F_G() 
	{
		return montoInvertido_F_G;
	}
	
	public Double getMontoMaximoPorProyecto_F_G() 
	{
		return montoMaximoPorProyecto_F_G;
	}
	
	public Double getLimiteMaximoInversion_F_G() 
	{
		return limiteMaximoInversion_F_G;
	}
	
	public Double getMontoInvertido_F_G_temp()
	{
		return montoInvertido_F_G_temp;
	}
	
	public void setMontoInvertido_F_G_temp(Double montoInvertido_F_G_temp)
	{
		this.montoInvertido_F_G_temp = montoInvertido_F_G_temp;
	}
	
	public FilterStore getFilter()
	{
		return filter;
	}
	
	public Double getMontoDisponibleEn_E5() 
	{
		return montoDisponibleEn_E5;
	}

	public Double getPorcMaxSUMSaldoProyE5() 
	{
		return porcMaxSUMSaldoProyE5;
	}
}
