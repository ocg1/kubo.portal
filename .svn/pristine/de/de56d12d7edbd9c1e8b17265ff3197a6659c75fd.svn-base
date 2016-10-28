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

public abstract class SAFICuotaCreditoDMO 
implements SAFICuotaCreditoIMO
{
	protected SimulationCacheService simulationCacheService;	
	protected ServiceCallingDao servicecallingRepository;
	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios service;
	
	protected ServiceCalling srvCall;
	
	protected SimuladorCuotaCreditoRequest request;
	protected SimuladorCuotaCreditoResponse response;
	
	protected Simulation_Cache simulationCache;
	
	protected SessionBean sesion;
	
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

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		if(sesion.getRate() != null && !sesion.getArea().toString().equals("M"))
		{
			tasaTotal = sesion.getRate();
		}
		
		if(sesion.getOpeningCommission() != null)
		{
			comisionApertura = sesion.getOpeningCommission();
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
}
