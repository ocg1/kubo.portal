package mx.com.kubo.portal.simulador;

import mx.com.kubo.bean.Amortization;

public abstract class CATCalculatorAMO extends CATCalculatorDMO
{
	protected void init_tabla_amortizacion() 
	{
		amortization.setAmmount(ammount);
		amortization.setTerm_id(term_id) ;
		amortization.setRate(tasaTotal);
						
		if( !getMontoCuotaStr().equals("No disponible") )
		{
			payment = Double.parseDouble( getMontoCuotaStr().replace("$", "").replace(",", "") );
			
			amortization.setdPayment(payment);
			
		} else {
			
			amortization.setdPayment(0.0);
		}
		
		if( !totalPagarTOKEN.equals("No disponible") )
		{
			total_payment = Double.parseDouble(totalPagarTOKEN.replace("$", "").replace(",", ""));
			
			amortization.setTotalPayment(total_payment);
			
		} else {
			
			amortization.setTotalPayment(0.0);
		}	
		
		amortization.setFreq(frequencyStr);
		amortization.setCat("");
		amortization.setComision( (comisionApertura +"").replace("$", "").replace(",", "")  );		
		amortization.setSafiSimulation( isSafiSimulation );
		
		amortization.calculaTabla();
		
		lista_amortizacion = amortization.getTamort();
	}
	
	protected void init_valor_cuotas() 
	{
		int counter = 0;
		
		for(Amortization amortization : lista_amortizacion)
		{			
			if(counter != 0)
			{
				valorCuotas += ",";
			}
			
			counter++;
			
			interes =  Double.parseDouble( amortization.getInterest().replace("$", "").replace(",", "") );
			
			interesSinIva = interes / (1.16);
			
			interesSinIva = Double.parseDouble( (dec.format(interesSinIva)).replace("$", "").replace(",", ""));
			
			capital = Double.parseDouble( amortization.getCapital().replace("$", "").replace(",", "") );
			
			valorCuotas += (capital + interesSinIva);
			
		}
	}
}
