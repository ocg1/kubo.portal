package mx.com.kubo.portal.simulador;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.TablaAmortizacion;

public class CreditSimulatorIMP extends CreditSimulatorAMO
implements CreditSimulatorIMO
{	
	public CreditSimulatorIMP(int term_id, int purpose_id)
	{
		super();
		
		this.term_id = term_id;
		this.purpose_id = purpose_id;
	}
	
	
	public CreditSimulatorIMP(int term_id, Double payment)
	{
		super();
				
		this.term_id = term_id;
		this.payment = payment;
	}
	
	public CreditSimulatorIMP(double ammount, int term_id, int frequency_id)
	{
		super();
		
		this.ammount      = ammount;
		this.term_id      = term_id;
		this.frequency_id = frequency_id;
	}
	
	public void init_cuota_by_formula()
	{		
		tasaPeriodo = generaTasaPeriodo(true);
		
		Double num = (Math.pow((1+tasaPeriodo), term_id)) * tasaPeriodo;
		Double den = (Math.pow((1+tasaPeriodo), term_id)) - 1;
		Double montoAPagar = ammount * (num / den);
	
		payment = Math.ceil(montoAPagar);
		
		montoCuota = payment;	
		
		totalPagar = (double) Math.round((montoAPagar * term_id) * 100) / 100;
		
		interes = generaInteres();	
		
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		amortization = (TablaAmortizacion) resolver.getValue(elContext, null, "tablaAmortizacion");
		
		CAT_calculator = new CATCalculatorIMP(ammount, term_id, diasFreq, payment, tasaTotal, comisionApertura, frequencyStr, totalRecibir);
		CAT_calculator.setTotalPagar(totalPagar);
		CAT_calculator.setAmortization(amortization);
		CAT_calculator.setTasaTotal(tasaTotal);
		CAT_calculator.setComisionApertura(comisionApertura);
		//CAT_calculator.setpro
		CAT_calculator.setSafiSimulation(false);
		//CAT_calculator.setSesion(sesion);
		CAT_calculator.init();
	
		cat = CAT_calculator.getCat();	
		
		add_simulation();
	}
	
	public void init_cat_SAFI() 
	{
		tasaPeriodo = generaTasaPeriodo(true);
		
		generaMontoCuota2();		
		
		interes = generaInteres();
		
		add_simulation();
	}
	
	public void simulaCred(boolean isSafi) 
	{		
		this.isSafi = isSafi;
		
		init_rate();		
		
		generaNumCuotas();		
		
		tasaPeriodo = generaTasaPeriodo(true);
		
		if(isSafi)
		{
			generaMontoCuota2();
			
		} else {
						
			getCuotaByFormula(true, isSafi);
		}
		
		interes = generaInteres();
		
		if(flagSaveSimulationCache)
		{
			saveSimulatorCache();
		}
		
		if(isConnected && !getTotalPagarStr().equals("No disponible"))
		{
			saveSimulator();
			add_simulation();
			update_proyect_loan();			
			
		} else {
			
			add_simulation_anonimo();			
		}
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if(requestContext != null)
		{
			requestContext.addCallbackParam("valor", true);
		}
	}
}
