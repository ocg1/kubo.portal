package mx.com.kubo.portal.simulador;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.TablaAmortizacion;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.model.Simulation_Cache_PK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.SimulationCacheService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.tools.Utilities;

public abstract class CreditSimulatorDMO
implements CreditSimulatorIMO
{
	protected FacesContext faces;
	protected ELContext    elContext;
	protected ELResolver   resolver;
	
	protected ProyectLoanService proyectLoanService;
	protected SimulatorService simulatorService;
	protected ProyectService proyectService;
	protected SimulationCacheService simulationCacheService;	
	protected PrevencionLDService prevencionldservice;
	protected Change_controlService changeControlService;
	
	//protected SessionBean sesion;	
	
	protected ProyectLoan proyectLoan;	
	protected Frequency frequency;	
	protected PrevencionLD prevencionld;
	protected SimulatorPK   simulation_PK;
	protected SimulatorBean simulation;
	protected Simulation_Cache_PK simulationCachePK;
	protected Simulation_Cache    simulationCache;
	
	protected TablaAmortizacion amortization;
	
	protected SAFICuotaCreditoIMO SAFI_cuota_credito;
	protected    CATCalculatorIMO CAT_calculator;
	
	protected List<Change_control> listChangeByProspect;
	
	protected  Locale locale;
	protected  NumberFormat dec;
	
	protected String totalRecibir  = "0";
	protected String frequencyStr  = "0";
	protected String frequencyStr2 = "0";
	protected String frequencyName;
	protected String freqStr;
		
	protected Double payment;
	protected Double tasaPeriodo = 0d;	
	protected Double montoCuota  = 0d;
	protected Double tasaTotal   = 52.6d;
	protected Double totalPagar  = 0d;
	protected Double iva         = 0.16d;
	protected Double ivaInteres  = 0d;
	protected Double interes     = 0d;
	protected Double comisionApertura = 5D;
	protected Double cat;
	protected Double opening_commission_amount;
	protected Double total_to_receive;
	
	protected Double rate;
	
	protected double ammount = 50000;
	
	protected int numCuota = 0;
	protected int frequency_id = 4;
	protected int term_id = 12;
	protected int purpose_id;
	protected int frequencyAnual = 12;
	protected int diasFreq = 0;
	
	protected final int SEMANAL    = 1;
	protected final int CATORCENAL = 2;
	protected final int QUINCENAL  = 3;
	protected final int MENSUAL    = 4;
		
	protected boolean hasPLD;
	protected boolean flagSaveSimulationCache = false;
	protected boolean isConnected;
	protected boolean isSafi;
	
	protected int prospectus_id;
	protected int company_id;
	
	
	abstract String formatDec(String valor);
	
	abstract Integer generaNumCuotas();
	
	protected CreditSimulatorDMO()
	{
		proyectLoanService     = Utilities.findBean("proyectLoanServiceImp");
		simulatorService       = Utilities.findBean("simulatorServiceImp");
		proyectService         = Utilities.findBean("proyectServiceImp");
		prevencionldservice    = Utilities.findBean("prevencionLDServiceImp");
		simulationCacheService = Utilities.findBean("simulationCacheServiceImp");
		changeControlService   = Utilities.findBean("change_controlServiceImp");
		
		frequency = new Frequency();
		
		locale = new Locale("es","mx");
		dec = NumberFormat.getCurrencyInstance(locale);
	}
	
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}

	public void setComisionApertura(Double comisionApertura) {
		this.comisionApertura = comisionApertura;
	}
	
	public void setAmmount(double ammount)
	{
		this.ammount = ammount;
		
		opening_commission_amount = (ammount * (comisionApertura / 100));
		
		total_to_receive =  ammount - opening_commission_amount;
		
		total_to_receive = (double) Math.round( total_to_receive * 100) / 100;
		
		totalRecibir = dec.format(opening_commission_amount);
	}
	
	public void setFrequency_id(int frequency_id) 
	{
		this.frequency_id = frequency_id;
		
		generaNumCuotas();
	}
	
	/*public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		if(sesion.getProspectus_id() != null && sesion.getProspectus_id() > 0)
		{
			isConnected =  true;
			
		} else {
			
			isConnected = false;
		}	
		
		if(sesion.getRate() != null && !sesion.getArea().toString().equals("M"))
		{
			tasaTotal = sesion.getRate();
		}
		
		if(sesion.getOpeningCommission() != null)
		{
			comisionApertura = sesion.getOpeningCommission();
		}
	}*/
	
	public String getTotalPagarStr() 
	{
		if(totalPagar != null && totalPagar > 0)
		{
			return formatDec(dec.format(totalPagar));
			
		} else {
			
			return "No disponible";
		}
	}
	
	public SimulatorBean getSimulation()
	{
		return simulation;
	}
}
