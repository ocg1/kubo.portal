package mx.com.kubo.managedbeans;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.Amortization;

@ManagedBean
@RequestScoped
public class TablaAmortizacion extends TablaAmortizacionAMO
implements TablaAmortizacionIMO, Serializable 
{
	private static final long serialVersionUID = 5521191227288513392L;

	@PostConstruct
	public void init()
	{				
		try
		{								
			faces = FacesContext.getCurrentInstance();
			
			parameters = faces.getExternalContext().getRequestParameterMap();
			
			init_parameters();
			
			if(parameter_ammount != null)
			{						
				calculaTabla();
				
//				generaDocTablas();
				
			}
			
		} catch(Exception e){
			
			e.printStackTrace();
			
		}		
	}
	
	public void calculaTabla()
	{		
		try
		{			
			fecCalc = formatStr1.format(new Date());
			
			tasaconiva = (rate / 100) * 1.16;
			
			monto = dec.format(ammount);
			
			numPagos = term_id+"";
			fechaSim = formatStr.format(new Date());
			zone = "No Fronteriza";
			strTasa1 = rate+"";
			strTasa2 = ((tasaconiva)*100)+"";
			
			strTasa2 = (((double) Math.round(Double.parseDouble(strTasa2)*100)/100))+"";
			
			if(comision == null || comision.equals("null"))
				comision = "5.0";
			
			montoarecibir = dec.format(((100 - Double.parseDouble( comision ) )/100)*ammount);
			
			payment = dec.format(dPayment);
			
			Double lastPayment = totalPayment -( (term_id-1)*dPayment );						
			
			init_tabla_amortizacion();			
							
			Double interes = (lastPayment - ammount);
			
			interes = ((double) Math.round(interes*1000)/1000);
			
			Double capital = lastPayment -interes;
			
			capital = ((double) Math.round(capital*100)/100);
			
			ammount = ammount - capital;
			
			ammount = ((double) Math.round(ammount*100)/100);
			
			//System.out.println(ammount + "  -  " + capital	+"  -  "+interes+"  -  jjjj  -  "+lastPayment);
			
			amort = new Amortization();			
			amort.setBalance(dec.format(ammount));
			amort.setCapital(dec.format(capital));
			amort.setInterest(dec.format(interes));
			amort.setPayment(dec.format(lastPayment));
			amort.setNumCuota((term_id)+"");
			
			tamort.add(amort);
			
			frequency = freq.equals("mensuales")?"Mensual":freq.equals("catorcenales")?"Catorcenal":freq.equals("quincenales")?"Quincenal":freq.equals("semanales")?"Semanal":"";
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}		
	}			

	public Integer getDias(Date fecha1, Date fecha2)
	{	
		GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(fecha1);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(fecha2);
        int rangoAnyos = 0;
        Integer rango =0;
        
        /* COMPROBAMOS SI ESTAMOS EN EL MISMO ANYO */
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) 
        {
        	
        	rango = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        	
        } else {
            /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
             * SI ES BISIESTO SON 366 DIAS EL ANYO
             * SINO SON 365
             */
            int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

            /* CALCULAMOS EL RANGO DE ANYOS */
            rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

            /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
            rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));

        }
		
		return rango;
		
	}
		
	public void generaDocTablas()
	{		
		String nombreArchivo = "/opt/pruebaTablaAmortizacion.csv";
        StringBuilder texto = new StringBuilder();
               
        try
        {        	
        	//System.out.println( "INICIANDO ... " );
        	
        	faces = FacesContext.getCurrentInstance();
    		
    		elContext = faces.getELContext();
    		
    		simulador = (Simulator) faces.getApplication().getELResolver().getValue(elContext, null, "simulator");
        	
//    		String[] terms = {	"4,7,9,12,14,16,18,20,22,24,27,29,31,33,36",
//    							"8,14,18,24,28,32,36,40,44,48,54,58,62,66,72",
//    							"16,28,40,52,60,69,78,86,95,104,113,124,134,145,156"};
    		
//    		String[] terms = {	"145,156"};
    		
    		String[] terms = {	"4,7,9,12,14" };
    		
    		String[] frequencys = {	"2" };
    		
    		String[] frequencysName = {	"catorcenales" };
    		
    		int i = 0;
    		
    		for( i=0 ; i<frequencys.length ; i++ )
    		{    		
    			String[] terms_1 = terms[i].split(",");
    			
    			texto.append(  frequencysName[i].toUpperCase() +"  \n" );
    			
    		for( String t : terms_1 )
    		{
    		
    			simulador.setAmmount(10000D);
    			simulador.setTasaTotal(17.9D);
    			simulador.setFrequency_id( Integer.parseInt( frequencys[i] ) ); //*
    			simulador.setTerm_id(Integer.parseInt(t)); //*
    			
    			simulador.simulaCred(false);
    			
    			
    			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    			
    			ammount = 	simulador.getAmmount();
				term_id = 	simulador.getTerm_id() ;
				rate    =  	simulador.getTasaTotal();
				dPayment =  simulador.getMontoCuota();
				totalPayment = simulador.getTotalPagar();
				freq = frequencysName[i];
				comision="5.0";
				
				/*
				 
				 if(freq.equals("mensuales")){
						
						c1.add(Calendar.MONTH,1);
						
					}else if(freq.equals("semanales")){
						
						c1.add(Calendar.DATE,7);
						
					}else if(freq.equals("catorcenales")){
						
						c1.add(Calendar.DATE,14);
						
					}else if(freq.equals("quincenales")){
				 
				 * */
				
				//cat = simulador.getCatStr();
				
				safiSimulation = false;
				
				calculaTabla();
    			
				simulador.calculaCat(safiSimulation);
				
				cat = simulador.getCatStr();
				
    			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    			
				texto.append( simulador.getAmmountStr() + " A "+ simulador.getTerm_id()  +" PAGOS "+ freq.toUpperCase() +"  \n" );
				
				texto.append( "CUOTA;" );
				texto.append( "CAPITAL;" );
				texto.append( "INTERES;" );
				
				texto.append( "IVA;" );
				
				texto.append( "PAGO;" );
				
				texto.append( "CAT\n" );
				
				for(Amortization a : tamort)
				{
					
					texto.append( a.getNumCuota() + ";" );
					
					texto.append( a.getCapital()+ ";" );
					
					Double int_iva = Double.parseDouble( a.getInterest().replace("$", "").replace(",", "") );
					
					Double interes = int_iva/1.16;
					
					Double iva = int_iva - interes;
					
					texto.append( dec.format(interes) + ";" );
					
					texto.append( dec.format(iva) + ";" );
					
					texto.append( a.getPayment() + ";" );
					
					texto.append( cat + "\n" );
				}
				
				texto.append( "\n" );
				texto.append( "\n" );
    		}//*
    		
    		texto.append( "\n" );
			texto.append( "\n" );
    		
    		}
    		
    		
	            FileWriter fwriter = new FileWriter(nombreArchivo);
	            fwriter.write('\ufeff'); //si no se escribe esto, excel no muestra bien el texto con tildes
	            fwriter.write(texto.toString());
	            fwriter.flush();
	            fwriter.close();
            
	            
//	            System.out.println("*****************************************************************************");
//	    		System.out.println("*********************FINALIZA*DOCUMENTO**************************************");
//	    		System.out.println("*****************************************************************************");
    		
        }catch (IOException e){
          
        	e.printStackTrace();
        	
        }
		
	}
	
}
