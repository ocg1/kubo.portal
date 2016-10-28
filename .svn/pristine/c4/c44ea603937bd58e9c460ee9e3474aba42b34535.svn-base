package mx.com.kubo.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.bean.Amortization;

public abstract class TablaAmortizacionAMO extends TablaAmortizacionDMO 
{
	protected void init_parameters() 
	{
		parameter_ammount = (String) parameters.get("monto");
		
		if(parameter_ammount != null)
		{			
			ammount = Double.parseDouble( (parameter_ammount).replace(",", "") );
			term_id = Integer.parseInt( (String) parameters.get("term") ) ;
			rate =  Double.parseDouble( (String) parameters.get("rate") );
			dPayment =  Double.parseDouble( ((String) parameters.get("payment")).replace(",", "") );
			totalPayment = Double.parseDouble( ((String) parameters.get("totalPayment")).replace(",", "") );
			freq = (String) parameters.get("frequency");
			cat = (String) parameters.get("cat");
			comision=(String) parameters.get("comision");
			
			String safiSimStr = (String) parameters.get("isSafiSim");
			
			//System.out.println("--- safiSimStr: "+safiSimStr);
			
			if( safiSimStr != null && safiSimStr.equals("S") )
			{
				safiSimulation = true;
				
			} else {
				
				safiSimulation = false;
			}				
		}
	}
	
	
	protected void init_tabla_amortizacion() 
	{
		Date fecha1 = new Date();
		
		tamort = new ArrayList<Amortization>();
		
		for(int i = 0;i<(term_id-1);i++)
		{				
			amort = new Amortization();
			
			Calendar c1 = Calendar.getInstance(); 
			c1.setTime(fecha1);
			
			if(safiSimulation)
			{ 
				
				if(freq.equals("mensuales")){
					
					c1.add(Calendar.MONTH,1);
					
				}else if(freq.equals("semanales")){
					
					c1.add(Calendar.DATE,7);
					
				}else if(freq.equals("catorcenales")){
					
					c1.add(Calendar.DATE,14);
					
				}else if(freq.equals("quincenales")){
					
					//System.out.println("Fecha: " + formatStr.format(c1.getTime()));
					
						if((c1.get(Calendar.DAY_OF_MONTH) < 15 || c1.get(Calendar.DAY_OF_MONTH) > 27)  )
						{
							
							Calendar c3 = Calendar.getInstance(); 
							
							if(i!=0)
							{
							
								Calendar temporalc1 = Calendar.getInstance();
								temporalc1.setTime(c1.getTime());
								temporalc1.add(Calendar.MONTH, 1);
								c3.set(temporalc1.get(Calendar.YEAR),temporalc1.get(Calendar.MONTH),15);
								
							} else {
								
								c3.set(c1.get(Calendar.YEAR),c1.get(Calendar.MONTH),15);
								
							}
							
							Integer d1 = getDias(c1.getTime(),c3.getTime());
							//System.out.println("Dias que faltan para la quincena: " + d1);
							
							if(d1<9)
							{
								
								Calendar cc1 = Calendar.getInstance(); 
								cc1.set(c1.get(Calendar.YEAR),c1.get((Calendar.MONTH)),c1.getActualMaximum(Calendar.DAY_OF_MONTH));
								c1 = null;
								c1 = Calendar.getInstance(); 
								c1.setTime(cc1.getTime());
								
							} else {
																										
								c1 = null;
								c1 = Calendar.getInstance(); 
								c1.setTime(c3.getTime());									
							}
							
						} else {
							
							Calendar c4 = Calendar.getInstance(); 
							c4.set(c1.get(Calendar.YEAR),c1.get(Calendar.MONTH),c1.getActualMaximum(Calendar.DAY_OF_MONTH));
							
							Integer d1 = getDias(c1.getTime(),c4.getTime());
							//System.out.println("Dias que faltan para fin de mes: " + d1);
							
							if(d1 < 9)
							{									
								c1.add( Calendar.DATE, (d1+15) );
								
							} else {
								
								c1.setTime(c4.getTime());										
							}								
						}						
				}
			
			} else {
				
				if(freq.equals("mensuales"))
				{					
					c1.add(Calendar.DATE,30);
					
				} else if(freq.equals("semanales")){
					
					c1.add(Calendar.DATE,7);
					
				} else if(freq.equals("catorcenales")){
					
					c1.add(Calendar.DATE,14);
					
				} else if(freq.equals("quincenales")){
					
					c1.add(Calendar.DATE,15);						
				}					
			}
			
			
			Date fecha2 = c1.getTime();				
			
			Integer dias = getDias(fecha1 ,fecha2);
			
			Double interes = (ammount*tasaconiva*dias)/360;
			
			interes = ((double) Math.round(interes*1000)/1000);
			
			Double capital = dPayment-interes;
			
			capital = ((double) Math.round(capital*100)/100);
			
			ammount = ammount - capital;
			
			ammount = ((double) Math.round(ammount*100)/100);
			
			//System.out.println(ammount + "  -  " + capital	+"  -  "+interes+"  -  "+dias+"  -  "+dPayment);
			fecha1 = fecha2;
			
			amort.setBalance(dec.format(ammount));
			amort.setCapital(dec.format(capital));
			amort.setInterest(dec.format(interes));
			amort.setPayment(dec.format(dPayment));
			amort.setNumCuota((i+1)+"");
			
			tamort.add(amort);
			
		}
	}
}
