package mx.com.kubo.portal.simulador;

import java.util.Date;

public class SAFICuotaCreditoIMP extends SAFICuotaCreditoAMO
implements SAFICuotaCreditoIMO
{
	public SAFICuotaCreditoIMP(double ammount, int term_id, int frequency_id, String freqStr, Double tasaPeriodo)
	{
		super();
		
		this.ammount      = ammount;
		this.term_id      = term_id;
		this.frequency_id = frequency_id;
		this.freqStr      = freqStr;
		this.tasaPeriodo  = tasaPeriodo;
	}
	
	public void init()
	{			
		thisFechaInicio = format.format(new Date());
		fechaInicio     = formatStr.format(new Date());
						
		if(frequency_id == SEMANAL || frequency_id == CATORCENAL)
		{
			simulationCache = simulationCacheService.getMaxByAmmountRateTermFrequency(ammount, tasaPeriodo, term_id, frequency_id, 1);
		}
				
		if(simulationCache == null)
		{					
			init_request();						
			init_response();
			
		} else {
			
			init_simulation_cache();				
		}			
	}
}
