package mx.com.kubo.portal.simulador;

import java.text.SimpleDateFormat;
import java.util.Locale;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.SimulationCacheService;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

public class SAFICuotaCreditoDMO 
{
	protected SimulationCacheService simulationCacheService;	
	protected ServiceCallingDao servicecallingRepository;
	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios service;
	
	protected ServiceCalling srvCall;
	
	protected SimuladorCuotaCreditoRequest request;
	protected SimuladorCuotaCreditoResponse response;
	
	protected Simulation_Cache simulationCache;
	
	//protected SessionBean sesion;
	
	protected SimpleDateFormat format;	
	protected SimpleDateFormat formatStr;
	
	protected String fechaInicio;
	protected String freqStr;
	protected String catStr="0";
	protected String montoCuotaStr="0";
	protected String thisFechaInicio;
	
	protected Double tasaPeriodo;
	protected Double tasaTotal=52.6d;
	protected Double comisionApertura = 5D;
	protected Double montoComisionApert;
	protected Double cat;
	protected Double montoCuota;
	protected Double totalPagar;
	
	protected double ammount;
	
	protected int company_id;
	protected int prospectus_id;
	
	protected int term_id;
	protected int frequency_id;
	protected int numCuota;	
	
	protected final int SEMANAL = 1;
	protected final int CATORCENAL = 2;
	
	protected boolean flagSaveSimulationCache;
	
	protected SAFICuotaCreditoDMO()
	{
		simulationCacheService   = Utilities.findBean("simulationCacheServiceImp");
		servicecallingRepository = Utilities.findBean("serviceCallingDaoImp");
		
		format    = new SimpleDateFormat("yyyy-MM-dd");
		formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
	}

	public void initTasaComision(Double rate, Double commission) 
	{
		
		if( rate != null )
		{
			tasaTotal = rate;
		}
		
		if( commission != null)
		{
			comisionApertura = commission;
		}
	}

	public Double getCat() {
		return cat;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}
	
	public int getNumCuota() {
		return numCuota;
	}

	public boolean isFlagSaveSimulationCache() 
	{
		return flagSaveSimulationCache;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}				
}
