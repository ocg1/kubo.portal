package mx.com.kubo.portal.ofertas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import mx.com.kubo.portal.simulador.CreditSimulatorIMP;

public abstract class ParserRenovacionAutomaticaAMO extends ParserRenovacionAutomaticaDMO
{
	protected void init_ofert_ENABLED() 
	{
		if(r_data != null)
		{
			r_data = r_data.trim().toUpperCase();
			
			r_data_split = r_data.split(" ", 5);
			
			if(r_data_split.length > 4)
			{
				renovaciones_TOKEN = r_data_split[4];
				
				renovaciones_TOKEN = renovaciones_TOKEN.trim();
								
		        pattern = Pattern.compile(REN_AUTO);
		        
		        matcher = pattern.matcher(renovaciones_TOKEN);		        		        
	
		        ofert_count = 0;
		        
		        while (matcher.find())
		        {
		        	ofert_count++;
		        }
		        
		        ofert_ENABLED = ofert_count > 0 ? true : false; 
		        
		        loan_type_id = REN;
		        
		        if(ofert_count < 1)
		        {
			        pattern = Pattern.compile(RDC_AUTO);
			        
			        matcher = pattern.matcher(renovaciones_TOKEN);		        		        
		
			        ofert_count = 0;
			        
			        while (matcher.find())
			        {
			        	ofert_count++;
			        }
			        
			        ofert_ENABLED = ofert_count > 0 ? true : false; 
			        
			        loan_type_id = RDC;
		        }
			}
		}
	}
		
	protected void init_R_data()
	{	       
        renovaciones = new ArrayList<RenovacionBean>(ofert_count);
        
        diccionario = new HashMap<String, Integer>();
        
		max_ammount_from_offer = 0;
		min_ammount_from_offer = 500000;
		
        for(int index = 1; index <= ofert_count; index++)
        {
        	renovacion = new RenovacionBean();
        	
        	init_renovacion_TOKEN(index);		        	
    		init_ofert_ammount_TOKEN();			    				    		
    		
    		diccionario.put(renovacion.getSafi_credit_id(), index - 1);
    		
        	renovaciones.add(index -1, renovacion);
        }		
	}
	
	private void init_renovacion_TOKEN(int ren_auto_index) 
	{	
		String ren_auto_N = "";
		
		if(only_one_offert_ENABLED)
		{
			if(loan_type_id.equals(REN))
			{
				ren_auto_N = REN_AUTO;
			}
			
			if(loan_type_id.equals(RDC))
			{
				ren_auto_N = RDC_AUTO;
			}			
			
		} else {
			
			if(loan_type_id.equals(REN))
			{
				ren_auto_N = REN_AUTO + "_" + ren_auto_index;
			}
			
			if(loan_type_id.equals(RDC))
			{
				ren_auto_N = RDC_AUTO + "_" + ren_auto_index;
			}						
		}
				
		String ren_auto_M = REN_AUTO + (ren_auto_index + 1);
		
		int ren_auto_N_index = renovaciones_TOKEN.indexOf(ren_auto_N);
		int ren_auto_M_index = renovaciones_TOKEN.indexOf(ren_auto_M);
		
		if(ren_auto_N_index >= 0)
		{
			if(ren_auto_M_index >= 0)
			{
				renovacion_TOKEN = renovaciones_TOKEN.substring(ren_auto_N_index, ren_auto_M_index);
				
			} else {
				
				renovacion_TOKEN = renovaciones_TOKEN.substring(ren_auto_N_index);
			}
			
			init_credit_INFO(renovacion_TOKEN);
						
			for(int index = 1; index <= MAX_OFERT_NUMBER_PER_RENOVATION; index++)
			{				
				String term_frequency_TOKEN = init_oferta_TOKEN(index);								
				
				tabla_oferta_N = init_tabla_ofertas();
				
				switch(index)
				{
					case 1:
						renovacion.setTerm_frequency_1_TOKEN(term_frequency_TOKEN);
						renovacion.setTabla_oferta_1(tabla_oferta_N);
					break;
					
					case 2:
						renovacion.setTerm_frequency_2_TOKEN(term_frequency_TOKEN);
						renovacion.setTabla_oferta_2(tabla_oferta_N);
					break;
					
					case MAX_OFERT_NUMBER_PER_RENOVATION:
						renovacion.setTerm_frequency_3_TOKEN(term_frequency_TOKEN);
						renovacion.setTabla_oferta_3(tabla_oferta_N);
					break;
				}																
			}
		}
	}
	
	private void init_credit_INFO(String renovacion_TOKEN) 
	{		
		beginIndex = renovacion_TOKEN.indexOf("[");
		endIndex   = renovacion_TOKEN.indexOf("]");
				
		ofertas_TOKEN = renovacion_TOKEN.substring(beginIndex + 1, endIndex);
		
		ofertas_TOKEN = ofertas_TOKEN.trim();
		
		ofert_data_split = ofertas_TOKEN.split(" ");				
		
		String safi_credit_id   = ofert_data_split[SAFI_CREDIT_ID];
		String actual_ammount   = ofert_data_split[ACTUAL_AMMOUNT];
		String actual_payment   = ofert_data_split[ACTUAL_PAYMENT];
		String actual_frequency = ofert_data_split[ACTUAL_FREQUENCY];
		String actual_term      = ofert_data_split[ACTUAL_TERM];
		String max_ammount      = ofert_data_split[MAX_AMMOUNT];
		String max_payment      = ofert_data_split[MAX_PAYMENT];
		
		renovacion.setSafi_credit_id(safi_credit_id);
		renovacion.setActual_ammount(actual_ammount);
		renovacion.setActual_payment(actual_payment);
		renovacion.setActual_frequency(actual_frequency);
		renovacion.setActual_term(actual_term);
		renovacion.setMax_ammount(max_ammount);
		renovacion.setMax_payment(max_payment);
	}

	private String init_oferta_TOKEN(int oferta_N) 
	{
		String term_frequency_TOKEN  = "";
		
		String oferta_to_process = OFERTA + oferta_N;
				
		beginIndex = ofertas_TOKEN.indexOf(oferta_to_process);
		
		switch(oferta_N)
		{
			case 1:
			case 2:
				String oferta_limit = OFERTA + (oferta_N + 1);
				
				endIndex = ofertas_TOKEN.indexOf(oferta_limit);
			break;
			
			case 3:
				endIndex = ofertas_TOKEN.length();
			break;
		}	
		
		if(beginIndex >= 0)
		{
			ofert_data = ofertas_TOKEN.substring(beginIndex + "Oferta_N".length(), endIndex);
			
			ofert_data = ofert_data.trim();
			
			ofert_data_split = ofert_data.split(" ");
			
			ofert_term      = ofert_data_split[OFERT_TERM];
			ofert_frequency = ofert_data_split[OFERT_FREQUENCY];					
			
			term_frequency_TOKEN = init_term_frequency_TOKEN(ofert_term, ofert_frequency);			
		}
		
		return term_frequency_TOKEN;
	}
	
	private List<OfertaBean> init_tabla_ofertas()
	{
		List<OfertaBean> tabla_oferta_N = new ArrayList<OfertaBean>();		
		
		for(int index = 2; index < ofert_data_split.length - 1; index = index + 2)
		{
			ofert_ammount = ofert_data_split[index];						
			ofert_payment = ofert_data_split[index + 1];
			
			int ammount = Integer.parseInt(ofert_ammount);
			int payment = Integer.parseInt(ofert_payment);					
			
			if(ammount > 0 && payment > 0)
			{
				oferta = new OfertaBean();			
				oferta.setAmmount(format.format(ammount));
				oferta.setPayment(format.format(payment));
				
				tabla_oferta_N.add(oferta);
				
				max_ammount_from_offer = ammount > max_ammount_from_offer ? ammount : max_ammount_from_offer;			
				min_ammount_from_offer = ammount < min_ammount_from_offer ? ammount : min_ammount_from_offer;
			}						
		}				
		
		return tabla_oferta_N;
	}

	private String init_term_frequency_TOKEN(String ofert_term, String ofert_frequency)
	{		
		String term_frequency_TOKEN;
		
		switch(ofert_frequency.charAt(0))
		{
			case 'S':
				ofert_frequency = " semanas";
			break;
			
			case 'C':
				ofert_frequency = " catorcenas";
			break;
			
			case 'Q':
				ofert_frequency = " quincenas";
			break;
			
			case 'M':
				ofert_frequency = " meses";
			break;
		}
		
		sb = new StringBuilder();
		sb.append("A ").append(ofert_term);
		sb.append(ofert_frequency);
		
		term_frequency_TOKEN = sb.toString();
		
		return term_frequency_TOKEN;
	}
	
	private void init_ofert_ammount_TOKEN() 
	{		
		sb = new StringBuilder();
		sb.append(MONTO_APROBADO);
		sb.append("$");		
		sb.append(format.format(min_ammount_from_offer));
		sb.append(HASTA);
		sb.append("$").append(format.format(max_ammount_from_offer));
		sb.append(PLAZO);
		
		ofert_ammount_TOKEN = sb.toString();
		
		renovacion.setOfert_ammount_TOKEN(ofert_ammount_TOKEN);
	}	
	
	protected void init_CAT_simulation() 
	{		
		credit_simulator = new CreditSimulatorIMP(term_id, payment);
		credit_simulator.setSesion(sesion);
		credit_simulator.setAmmount(ammount);
		credit_simulator.setFrequency_id(frequency_id);
		
		credit_simulator.init_cuota_by_formula();
		
		simulation = credit_simulator.getSimulation();
	}
	
	protected void init_CAT_simulation_SAFI() 
	{	
		credit_simulator = new CreditSimulatorIMP(term_id, payment);
		credit_simulator.setSesion(sesion);
		credit_simulator.setAmmount(ammount);
		credit_simulator.setFrequency_id(frequency_id);
		
		credit_simulator.init_cat_SAFI();
		
		simulation = credit_simulator.getSimulation();
	}
}
