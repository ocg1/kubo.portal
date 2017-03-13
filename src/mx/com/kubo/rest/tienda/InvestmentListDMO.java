package mx.com.kubo.rest.tienda;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

import mx.com.kubo.bean.CreditoCaracteristicas;
import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.InvestorSession;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AmortizacionInversionistaService;
import mx.com.kubo.services.InvestmentFilterService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.services.ProyectInfoService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.SaldoInversionistaService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TasasAcreditadoService;
import mx.com.kubo.services.TiendaCreditosService;
import mx.com.kubo.tools.Utilities;

public abstract class InvestmentListDMO 
implements InvestmentListIMO
{	
	protected        ProyectLoanService proyectLoanService;	
	protected     ProyectFundingService proyectFundingService;		
	protected             AccessService service_access;
	protected            PurposeService purposeservice;		
	protected         MembershipService membershipService;		
	protected         ProfileInvService profileinvservice;		
	protected        ProyectInfoService proyectInfoService;
	protected     ServiceCallingService servicecallingService;
	protected      NaturalPersonService naturalPersonService;
	protected     TiendaCreditosService tiendacreditosservice;		
	protected        SystemParamService systemparamservice;	
	protected   StatusProyectCatService statusproyectcatservice;		
	protected    TasasAcreditadoService tasaacreditadoservice;		
	protected SaldoInversionistaService saldoinversionistaservice;		
	protected   InvestmentFilterService investmentFilterServiceImp;		
	protected AmortizacionInversionistaService amortInverService;	
	
	protected SAFIServiciosServiceLocator locatorSafi;
	protected SAFIServicios servicioSafi;		
		
	protected RequestContext request;
	protected FacesContext   faces;	
	protected ELContext      elContext;
	protected ELResolver     resolver;
		
	protected InvestorSession 	investorSession;
	protected SearchSummaySession  summarysesion;
	protected SummaryRequest       summary;
	protected NavigationInvest     navigationinvest;
	protected MenuControlTableBean control_table;
	protected FilterStore filter;
	
	protected ProyectLoan selectedRowProyectLoan;
	
	protected SAFIInvestmentIMO inversion;
	
	protected Response responseJSON;
		
	protected Calendar cal1, cal2;		
	
	protected long milis1, milis2, diff;
	
	protected Logger        log;			
	protected SessionBean   sesion;
	protected NaturalPerson naturalPerson;	
	protected gnNaturalPersonPK naturalPersonPK;
	protected Membership    member;
	protected MembershipPK  membership_PK;	
	
	protected SimpleDateFormat formatStr;
	protected SimpleDateFormat formatStr5;
	protected SimpleDateFormat formatStr1;
	
    protected Locale locale;
    protected NumberFormat dec;
    protected NumberFormat num;
	
    protected List<ItemLoanList>   proyectList;
	protected List<ItemLoanList>   proyectList_A;
	protected List<ItemLoanList>   proyectList_B;
	protected List<ProyectFunding> proyectFunding;	
	protected List<ItemLoanList>   proyectListForInvesInd;	
	protected List<ProyectFunding> proyectFundingByInvestor;
	protected List<Purpose> purposelst;
	protected List<InvestorsAccounts>      listInvAccounts;
	protected List<StatusProyectCat>       lstStatus;
	protected List<CreditoCaracteristicas> listCred;
	protected List<Byte> onlyTermOfProyectLoan;		
	protected List<String> SAFI_cuenta;
	
	protected String proyect_loan_SEARCH_TOKEN;
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
	protected String flagRisk = "0";
	protected String risk_str;
	protected String previousType = "0";
	protected String destiny_str;
	protected String lastFilter;
	
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
	protected boolean flagMin_E5_E4 = false;
	protected int maxProxect = 80;
	protected boolean moreProyects = false;
	protected boolean flagInversionFG = false;
	protected boolean hold_selected = false;
	
	protected boolean flagMasivo =true;
	
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
	protected Double porcMaxSUMSaldoProyE5;
	protected Double montoDisponibleEn_E5;	
	protected Double montoMinF1G1 ;
	protected Double montoInvertido_F_G ;
	protected Double maximoInvBySaldoPryF1G1;
	protected Double porcMaxSaldoPryF1G1_G;
	protected Double porcent_suma_F1_G1_G;	
	protected Double limiteMaximoInversion_F_G;	
	protected Double maximoInvBySaldoPryE4;
	protected Double porcMaxSaldoPryE4;	
	protected Double maximoInvBySaldoPryE5;
	protected Double porcMaxSaldoPryE5;	
	protected Double montoMinE4E5G;	
	protected Double porcMaxSaldoPryE5G;
	protected Double porcMaxSaldoPryE4G;	
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
    protected int fToInv=0;
    protected int gToInv=0;
    
    protected Double tasaPon_A=0D;
    protected Double tasaPon_B=0D;
    protected Double tasaPon_C=0D;
    protected Double tasaPon_D=0D;
    protected Double tasaPon_E=0D;
    protected Double tasaPon_F=0D;
    protected Double tasaPon_G=0D;
    protected Double tasaPonderada=0D;
    
    protected int temp;	    
    protected int typeSearch;
    
    protected final int MAX_NUMBER_INVESTMENTS_ENABLED = 6;
    
    protected InvestmentListDMO()
    {
    	service_access     = Utilities.findBean("accessServiceImp");
    	purposeservice     = Utilities.findBean("purposeServiceImp");
    	amortInverService  = Utilities.findBean("amortizacionInversionistaServiceImp");
    	membershipService  = Utilities.findBean("membershipServiceImp");
    	profileinvservice  = Utilities.findBean("profileInvServiceImp");
    	proyectInfoService = Utilities.findBean("proyectInfoServiceImp");
    	proyectLoanService    = Utilities.findBean("proyectLoanServiceImp");
    	proyectFundingService = Utilities.findBean("proyectFundingServiceImp");
    	servicecallingService = Utilities.findBean("serviceCallingServiceImp");
    	naturalPersonService  = Utilities.findBean("naturalPersonServiceImp");
    	tiendacreditosservice = Utilities.findBean("tiendaCreditosServiceImp");
    	systemparamservice      = Utilities.findBean("systemParamServiceImp");
    	statusproyectcatservice = Utilities.findBean("statusProyectCatServiceImp");
    	tasaacreditadoservice   = Utilities.findBean("tasasAcreditadoServiceImp");
    	saldoinversionistaservice  = Utilities.findBean("saldoInversionistaServiceImp");
    	investmentFilterServiceImp = Utilities.findBean("investmentFilterServiceImp");        			
		
		proyectList = new ArrayList<ItemLoanList>();
			
		proyectListForInvesInd = new ArrayList<ItemLoanList>(); 
		
    	locale = new Locale("es","mx");
    	
    	dec = NumberFormat.getCurrencyInstance(locale);
    	num = NumberFormat.getNumberInstance(locale);
    	
    	formatStr  = new SimpleDateFormat("ddMMyyyy", new Locale("ES"));
    	formatStr5 = new SimpleDateFormat("EEEE dd' de 'MMMM' de 'yyyy", new Locale("ES"));
    	formatStr1 = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));

    	log = Logger.getLogger(getClass());
 
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
    
    public void setSesion(SessionBean sesion)
    {
    	this.sesion = sesion;
    	
    	PID_cliente = sesion.getProspectus_id().toString();
    	
    	naturalPersonPK = new gnNaturalPersonPK(sesion.getProspectus_id(),sesion.getCompany_id());
    	
    	naturalPerson = naturalPersonService.getNaturalPersonById(naturalPersonPK);
    }

    public Response getResponseJSON()
    {
    	return responseJSON;
    }
    
	public String redireNavigation()
	{
		return navigation;
	}
	
	public String actionNavLogs()
	{
		return logs_navegation;
	}
	
	protected Double generaMontoCuota(Double tasaPeriodo,Double numCuota,Double cachito)
	{
		Double intper = tasaPeriodo;
		Double num = (Math.pow((1+intper), numCuota))*intper;
		Double den = (Math.pow((1+intper), numCuota))-1;
		Double montoAPagar = cachito*(num/den);
		return (double)Math.round(montoAPagar*100)/100;
	}
	
    public void setPageDataTable() 
    {
//        DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
//            .findComponent(":table_proyect_list:creditos");
//        
//        d.setFirst(0);        
    }
    
	public void setTagActual(String tagActual) 
	{
		if(tagActual == null)
		{
			this.tagActual = "-1";
			
		} else {
			
			this.tagActual = tagActual;
		}
	}
    
    public Double getExpectedPerformanceByInvestorId(String InvId)
    {
    	Double suma=0.0;
    	List<ProyectLoan> theList = proyectFundingService.getProyectsOfInvestorById(InvId);
    	
    	if(Double.valueOf(InvId)>0.0)
    	{
    		for(Object x: theList)
    		{
        		Object[] pair = (Object[]) x;
        		BigDecimal z =  (BigDecimal)pair[0];
        		BigDecimal y = (BigDecimal) pair[1];
        		Byte t = (Byte) pair[2];
        		Byte f = (Byte) pair[3];
        		Double amount = z.doubleValue();
        		Double rate = y.doubleValue();
        		Integer term = t.intValue();
        		Integer freq = f.intValue();
        		suma=suma+getExpectedPerformanceForProyect(amount,rate,term,freq);
        	}
        	return suma;
    	}
    	else return 0.0;
    	
    }
    
	public Double getExpectedPerformanceForProyect(Double cachito,Double tasa,Integer plazo,Integer idPagos)
	{
		Integer pagos = 0;
		if(idPagos==1){ 
			pagos = 52;
		}
		else if (idPagos==2){ 
			pagos = 26;
		}
		else if (idPagos==3){ 
			pagos = 24;
		}
		else{
			pagos = 14;
		}
		if(tasa==null)
			tasa=52.6D;
		tasa = tasa/100;
		Double tasaAnual=tasa;
		Double tasaEnPeriodo = tasaAnual/pagos;	
		Double rendimiento = generaMontoCuota(tasaEnPeriodo, Double.parseDouble(plazo+""), cachito);
		rendimiento = (rendimiento*plazo)-cachito;
		if (rendimiento < 0.00) rendimiento = 0.00;
		return (double)Math.round(rendimiento*100)/100;
	}
	
	public boolean getEnabledFlag(ProyectLoan proyect,Integer InvId)
	{
		String proyectLoanId = ( ( proyect.getProyectloanPk().getProyect_loan_id() ) + "" );
		List<ProyectFunding> proyectFundingByInvestor = proyectFundingService.getproyectbyInvestor(InvId.toString(), proyectLoanId);
		
		if (proyect.getAmount_founded()>=proyect.getAmmount()) 
				return true;
		else if (proyectFundingByInvestor.size()>=Integer.parseInt(vecesQuePuedeFondear)) 
	    		return true;
	    else if ((saldoActual)<=0.00) 
				return true;
//		else if (proyect.getDaysLeft()>(long)0) 
//	    		return false;
	    else if ((proyect.getAmmount()-proyect.getAmount_founded())<=0.00) 
	    	return true;
	    else return false;
	}
	
	public Double getTotalCreditOnAccounts()
	{
		Double suma = 0.00;
		
		try 
		{			
			for (InvestorsAccounts iterElement : listInvAccounts) 
			{
				suma = suma + iterElement.getSaldo();
			}
			
		} catch (Exception e) {
			
			log.info("Se genero un error al hacer la suma del saldo de cuentas");
		}
		
		return suma;
	}

	public void setAmmoutToInvStr(String ammoutToInvStr) 
	{	
		if(ammoutToInvStr == null)
		{
			ammoutToInv = 0D;
			
		} else {
			
			if(ammoutToInvStr.trim().length()>0)
			{
				try
				{
					ammoutToInv = Double.parseDouble( ammoutToInvStr.replaceAll(",", "") );
					
				} catch( Exception e ) {
					
					ammoutToInv = 0D;
					ammoutToInvStr = "";
				}
			}			
		}
		
		this.ammoutToInvStr = ammoutToInvStr;
	}
}



