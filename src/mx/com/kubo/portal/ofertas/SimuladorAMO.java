package mx.com.kubo.portal.ofertas;

import java.util.ArrayList;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.portal.simulador.CreditSimulatorIMP;

public abstract class SimuladorAMO extends SimuladorDMO
{
	protected void init_list_frequency() 
	{
		listFrequencyTmp = service_frequency.getFrequencyList();
		
		listFrequency = new ArrayList<Frequency>();
		
		for(Frequency frequency: listFrequencyTmp )
		{
			int frequency_id = frequency.getFrequencyPK().getFrequency_id();
			
			switch(frequency_id)
			{				
				case SEMANAL:
					frequency.setName("Semanas");
				break;
					
				case CATORCENAL:
					frequency.setName("Catorcenas");
				break;
					
				case QUINCENAL:
					frequency.setName("Quincenas");
				break;
					
				case MENSUAL:
					frequency.setName("Meses");
				break;					
			}
			
			listFrequency.add(frequency);
		}
	}
	
	protected void init_text_value() 
	{
		if(input_text.getId().equals("simulador_ammount"))
		{						
			try
			{
				String value = input_text.getValue().toString();
												
				ammount = Double.parseDouble(value.replace(",", "").replace("$",""));
				
				max_ammount_ENABLED = ammount > max_ammount ? true : false;
				
				request.addCallbackParam("ammount", ammount);
				request.addCallbackParam("max_ammount_ENABLED", max_ammount_ENABLED);
				
			} catch (Exception e) {
				
			}
			
		} else if(input_text.getId().equals("simulador_term_id")) {			
			
			try
			{
				term_id = Integer.parseInt(input_text.getValue().toString());
				
				request.addCallbackParam("term_id", term_id);
				
			} catch (Exception e) {
				
			}
		}
	}
	
	protected void init_select_value() 
	{		
		if(select_one.getId().equals("simulador-purpose-id"))
		{
			purpose_id = Integer.parseInt(select_one.getValue().toString());
			
			request.addCallbackParam("purpose_id", purpose_id);
			
		} else if(select_one.getId().equals("simulador-frequency-id")) {
						
			frequency_id = Integer.parseInt(select_one.getValue().toString());
			
			request.addCallbackParam("frequency_id", frequency_id);
		}
	}

	protected void init_simulator() 
	{				
		credit_simulator = new CreditSimulatorIMP(term_id);
		credit_simulator.setSesion(sesion);
		credit_simulator.setAmmount(ammount);
		credit_simulator.setFrequency_id(frequency_id);
		
		credit_simulator.init_cuota_by_formula();
		
		simulation = credit_simulator.getSimulation();
		
		max_payment_ENABLED = simulation.getPayment() > max_payment ? true : false;
	}
}
