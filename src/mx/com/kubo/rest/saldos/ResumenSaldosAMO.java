package mx.com.kubo.rest.saldos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.EconActivityByInv;
import mx.com.kubo.model.InfoCalifPorc;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;

public abstract class ResumenSaldosAMO extends ResumenSaldosDMO
{
	protected void init_sesion() 
	{					
		faces = FacesContext.getCurrentInstance();	
		
		external  = faces.getExternalContext();		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		String prospectus_id = (String) external.getRequestParameterMap().get("prospectus_id");
		String company_id    = (String) external.getRequestParameterMap().get("company_id");
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
				
		if( prospectus_id != null && company_id != null )
		{			
			//key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
			
		} else {
		
			if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null )
			{
			
				//key = new gnNaturalPersonPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
			} else {
				
				try 
				{
			        external.redirect("expired.jsf");
			        
			        return;
			        
				} catch (IOException ex) {
					
				       ex.printStackTrace();
				}				
			}		
		}		
	}
	
	protected void consultaCte()
    {									
		try
		{
			safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorA;
			safisrv.ws.CuentasServicios.SAFIServicios serviceA;
			
			locatorA = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			
			serviceA =  locatorA.getSAFIServiciosSoap11();
			
			request = new ConsultaCuentasPorClienteRequest(safi_client_id);
			
			replyA = serviceA.consultaCuentasPorCliente(request);
			
			boolean request_ENABLED = replyA.getInfocuenta()!=null && replyA.getCodigoRespuesta()!=null && replyA.getCodigoRespuesta()[0].equals("0");
			
			if(request_ENABLED)
            {
                String respuestas = replyA.getInfocuenta()[0];
                   
                String[] cuentas = respuestas.split("\\&\\|\\&");
                
                for(int i = 0; i < cuentas.length; i++)
                {
                    String[] vars = cuentas[i].split("\\&\\;\\&");
                    
                    if(vars.length == 3)
                    {                    	
                    	numCuentas.add(vars[0]);
                    	
                    } else {
                    	
                        System.out.println("Cuenta inválida: " + cuentas[i]);                        
                    }                    
                }                
            }

		} catch (Exception e) {
			
			e.printStackTrace();
		}		
    }
	
	protected void init_saldos_estado_cuenta() 
	{
		cuentaAhoID 		= "";
		numInvActivas 		= 0;
		montoInversiones 	= "0.00";
		intARecibir			= "0.00";
		saldoIntGlobal		= "0.00";
		saldoIntPlazoFijo	= "0.00";
		interesCobrado		= "0.00";
		moraCobrado			= "0.00";										
		
		saldosEdoCta = servicioProyecto.getSaldosEdoCtaClient(selectedAccountAux);
		
		cuentaAhoID 	  = Integer.toString(saldosEdoCta.getCuentaAhoID());
		numInvActivas 	  = saldosEdoCta.getNumInvActivas();
		montoInversiones  = Double.toString(saldosEdoCta.getMontoInversiones());
		intARecibir 	  = Double.toString(saldosEdoCta.getIntARecibir());
		saldoIntGlobal 	  = Double.toString(saldosEdoCta.getSaldoIntGlobal());
		saldoIntPlazoFijo = Double.toString(saldosEdoCta.getSaldoIntPlazoFijo());
		interesCobrado	  = Double.toString(saldosEdoCta.getInteresCobrado());
		moraCobrado		  = Double.toString(saldosEdoCta.getMoraCobrado());	
	}
	
	protected void init_grafica_1() 
	{
		Hashtable<String,Double> ht1 = new Hashtable<String,Double> ();
		
		ht1.put("A",0D);
		ht1.put("B",0D);
		ht1.put("C",0D);
		ht1.put("D",0D);
		ht1.put("E",0D);
		ht1.put("F",0D);
		ht1.put("G",0D);
		
		for( InfoCalifPorc info : lstinfo )
		{				
			String riesgo 			= info.getCalificacion();
			String numInversiones 	= info.getNumFondeos()+"";
			
			if( riesgo != null && numInversiones != null )
			{
				ht1.put ( (riesgo.charAt(0)+"") , (ht1.get( riesgo.charAt(0)+"" )+Double.parseDouble(numInversiones)) ) ;
			}					
		} 

		valArrGraphic1 = "<script> graphic1 = [";
				    
		String[] keys = (String[]) ht1.keySet().toArray(new String[0]);  
        Arrays.sort(keys);
		
	    int n = 0;
	    
	    for ( String key : keys ) 
	    {		    
	    	if(n > 0)
	    	{
	    		valArrGraphic1+= ",";
	    	}
	    	
	    	n++;
	    		
	    	valArrGraphic1+= "['"+key+"',"+ht1.get(key)+"]";
	    }
		
		valArrGraphic1 += "]</script>";
	}
	
	protected void init_grafica_2() 
	{
		Hashtable< String,List<Double> > ht2 = new Hashtable<String,List<Double>> ();
		
		ht2.put("A",new ArrayList<Double>());
		ht2.put("B",new ArrayList<Double>());
		ht2.put("C",new ArrayList<Double>());
		ht2.put("D",new ArrayList<Double>());
		ht2.put("E",new ArrayList<Double>());
		ht2.put("F",new ArrayList<Double>());
		ht2.put("G",new ArrayList<Double>());
		
		for( InfoCalifPorc info : lstinfo )
		{											
				String riesgo 			= info.getCalificacion();
				String tasa			 	= info.getPromTasa()+"";
				
				ArrayList<Double> tmp = (ArrayList<Double>) ht2.get( riesgo.charAt(0)+"" );
				
				tmp.add(Double.parseDouble( tasa ));
				
				ht2.put ( (riesgo.charAt(0)+"") , tmp  );													
		}
		
		
		String[] keys = (String[]) ht2.keySet().toArray(new String[0]);  
        Arrays.sort(keys);			    
	    
	    String axisX = " [''";
	    String axisY = "['Tasa ponderada'";
	    
	    for ( String key : keys ) 
	    {		    	
	    	axisX += ",'"+key+"'";
	    	
	    	ArrayList<Double> tmp = (ArrayList<Double>) ht2.get( key );
	    	
	    	Double t = 0D;
	    	
	    	for (Double d : tmp)
	    	{
	    		t += d;
	    	}
	    	
	    	t = (t)/tmp.size();
	    
	    	t = (double)Math.round((t)*100)/100;

	    	axisY += ","+t;
	    	
	    }
	    
	    axisX += "]";
	    axisY += "]";
	    
	    valArrGraphic2 = "<script>graphic2 =["+axisX+","+axisY+"]</script>";
	}
	
	protected void init_grafica_actividades() 
	{
		Calendar graphic3T = Calendar.getInstance();
		graphic3T.setTime(new Date());
		
		
	    valArrGraphic4 = "<script> graphic4 = [";
		
	    List<EconActivityByInv> lst = servicioProyecto.getEconActivityByInvLst( persona.getSafi_client_id(), cuentaAhoID );
	    
	    int ig4 = 0;
	    
	    for( EconActivityByInv tmpAct : lst )
	    {
	    	
	    	if(ig4!=0)
	    	{
	    		valArrGraphic4+=",";
	    	}
	    	
	    	ig4++;
	    	
	    	valArrGraphic4 += "['"+tmpAct.getDescription()+"',"+tmpAct.getQuantity()+"]";
	    	
	    }
	    		    			
		valArrGraphic4 += "]</script>";		
	}
	
	protected void init_response() 
	{
		gat = new Double(replyB.getGananciaAnuTot()==null?"0":replyB.getGananciaAnuTot());
		
		Double totalInteres = new Double(replyB.getInteresCobrado()==null?
								"0":replyB.getInteresCobrado()) + new Double(replyB.getMoraCobrado()==null?
								"0":replyB.getMoraCobrado())+Double.parseDouble(saldoIntGlobal) + Double.parseDouble(saldoIntPlazoFijo);	 
		
		interest_charged = dec.format( totalInteres );
		
		montoInversiones = dec.format(Double.parseDouble(montoInversiones));
		intARecibir 	 = dec.format(Double.parseDouble(intARecibir));
		saldoIntGlobal 	 = dec.format(Double.parseDouble(saldoIntGlobal));
		saldoIntPlazoFijo= dec.format(Double.parseDouble(saldoIntPlazoFijo));
		
		total_balance 	 = dec.format(new Double(replyB.getSaldoTotal()==null?"0":replyB.getSaldoTotal()));
		total_payments 	 = dec.format(new Double(replyB.getPagTotalRecib()==null?"0":replyB.getPagTotalRecib()));
		
		balances_cash = dec.format(new Double(replyB.getSaldoEfectivoDispon()==null?"0":replyB.getSaldoEfectivoDispon()));
		balances_investment_process = dec.format(new Double(replyB.getSaldoInverEnProceso()==null?"0":replyB.getSaldoInverEnProceso()));
		balances_active_investments = dec.format(new Double(replyB.getSaldoInvActivas()==null?"0":replyB.getSaldoInvActivas()));			
		notes_cash = new Integer(replyB.getNumeroEfectivoDispon()==null?"0":replyB.getNumeroEfectivoDispon());
		notes_investment_process = new Integer(replyB.getNumeroInverEnProceso()==null?"0":replyB.getNumeroInverEnProceso());			
		
		notes_accrued_interest = new Integer(replyB.getNumeroIntDevengados()==null?"0":replyB.getNumeroIntDevengados());
		
		investment_summary = new Integer(replyB.getNumeroTotInversiones()==null?"0":replyB.getNumeroTotInversiones());
				
		in_process_amount = dec.format(new Double(replyB.getSaldoInverEnProceso()==null?"0":replyB.getSaldoInverEnProceso()));
		
		broken_amount = dec.format(new Double(replyB.getSaldoInvQuebrantadasResumen()==null?"0":replyB.getSaldoInvQuebrantadasResumen()));
		fully_paid_amount = dec.format(new Double(replyB.getSaldoInvLiquidadasResumen()==null?"0":replyB.getSaldoInvLiquidadasResumen()));					
		
		in_process_number = new Integer(replyB.getNumeroInverEnProceso()==null?"0":replyB.getNumeroInverEnProceso());
		
		active_amount 		 = dec.format(new Double(replyB.getSaldoInvActivasResumen()==null?"0":replyB.getSaldoInvActivasResumen()));
		
		fully_paid_number = new Integer(replyB.getNumeroInvLiquidadasResumen()==null?"0":replyB.getNumeroInvLiquidadasResumen());
				
		capital_number = new Integer(replyB.getNumCapitalCobrado()==null?"0":replyB.getNumCapitalCobrado());
		ordinary_interests_number = new Integer(replyB.getNumInteresCobrado()==null?"0":replyB.getNumInteresCobrado());
		interest_on_arrears_number = new Integer(replyB.getNumMoraCobrado()==null?"0":replyB.getNumMoraCobrado()); //0
					
		payments_received_amount = dec.format(new Double(replyB.getPagTotalRecib()==null?"0":replyB.getPagTotalRecib()));
		capital_amount = dec.format(new Double(replyB.getCapitalCobrado()==null?"0":replyB.getCapitalCobrado()));
		ordinary_interests_amount = dec.format(new Double(replyB.getInteresCobrado()==null?"0":replyB.getInteresCobrado()));
		interest_on_arrears_amount = dec.format(new Double(replyB.getMoraCobrado()==null?"0":replyB.getMoraCobrado()));
		
	}
	
	protected void init_active_resume() 
	{
		activeResume = servicioProyecto.getResumenCreditActive(selectedAccountAux);
		
		notes_active_investments 	 = activeResume.getTotal_creditos_activos();
		notes_active_investments_cli = activeResume.getTotal_creditos_actXcliente();
		active_number 				 = activeResume.getSin_mora();
		active_number_cli			 = activeResume.getSin_mora_cli();
		backward1_15_number 		 = activeResume.getAtraso1_15();
		backward1_15_number_cli	 	 = activeResume.getAtraso1_15_cli();
		backward16_30_number 		 = activeResume.getAtraso16_30();
		backward16_30_number_cli 	 = activeResume.getAtraso16_30_cli();
		backward31_90_number 		 = activeResume.getAtraso31_90();
		backward31_90_number_cli 	 = activeResume.getAtraso31_90_cli();
		expired91_120_number 		 = activeResume.getAtraso91_120();
		expired91_120_number_cli 	 = activeResume.getAtraso91_120_cli();
		expired121_180_number 		 = activeResume.getAtraso120_Mas();
		expired121_180_number_cli	 = activeResume.getAtraso120_MasCli();
		under_warranty_number_cli	 = activeResume.getNumClientesAplGa();
		under_warranty_number		 = activeResume.getNumInversionesAplGa();
		
		saldoVigente0				 = dec.format(new Double(activeResume.getMontoSinMoraVig()));
		saldoAtrasado0				 = dec.format(new Double(activeResume.getMontoSinMoraAtr()));
		saldoVigente1a15 			 = dec.format(new Double(activeResume.getMontoAtraso1_15Vig()));
		saldoAtrasado1a15			 = dec.format(new Double(activeResume.getMontoAtraso1_15Atr()));
		saldoVigente16a30 			 = dec.format(new Double(activeResume.getMontoAtraso16_30Vig()));
		saldoAtrasado16a30			 = dec.format(new Double(activeResume.getMontoAtraso16_30Atr()));
		saldoVigente31a90 			 = dec.format(new Double(activeResume.getMontoAtraso31_90Vig()));
		saldoAtrasado31a90			 = dec.format(new Double(activeResume.getMontoAtraso31_90Atr()));
		saldoVigente91a120			 = dec.format(new Double(activeResume.getMontoAtraso91_120Vi()));
		saldoAtrasado91a120		     = dec.format(new Double(activeResume.getMontoAtraso91_120At()));
		saldoVigente120mas			 = dec.format(new Double(activeResume.getMontoAtraso120_MasV()));
		saldoAtrasado120mas			 = dec.format(new Double(activeResume.getMontoAtraso120_MasA()));
		saldoCapVigTotal			 = dec.format(new Double(activeResume.getTotalVigente()));
		saldoCapAtrTotal			 = dec.format(new Double(activeResume.getTotalExigible()));
		saldoIntVigente				 = dec.format(new Double(activeResume.getSaldoIntVig()));
		saldoIntAtrasado			 = dec.format(new Double(activeResume.getSaldoIntAtr()));
		saldoCapitalCtaOrden		 = dec.format(new Double(activeResume.getMontoCtaOrden()));
		saldoInteresCtaOrden		 = dec.format(new Double(activeResume.getSaldoIntCtaOrden()));
		saldoTotalCtaOrden			 = dec.format(new Double((activeResume.getMontoCtaOrden() + activeResume.getSaldoIntCtaOrden())));		
		balances_accrued_interest 	 = dec.format(new Double (activeResume.getSaldoIntVig()   + activeResume.getSaldoIntAtr()));
		
		System.out.println("SALDO CTAS ORDEN "+saldoCapitalCtaOrden+" "+saldoInteresCtaOrden+" "+under_warranty_number_cli+" "+under_warranty_number+" "+saldoTotalCtaOrden);		
	}
	
	protected void getInvestmentsbyLink()
	{		
		if(status_delinquentinv == 'C')
		{			
			view_delinquentInv 	   = "Ver por proyecto";
			viewLabelDelinquent    = "Clientes";
			labelDelinquent		   = "Cliente";
			number_notes_active	   = notes_active_investments_cli;
			number_NoDelinquent    = active_number_cli;
			number_backward1_15    = backward1_15_number_cli; 
			number_backward16_30   = backward16_30_number_cli;
			number_backward31_90   = backward31_90_number_cli;
			number_backward91_120  = expired91_120_number_cli; 
			number_backward121_180 = expired121_180_number_cli;
			number_under_warranty  = under_warranty_number_cli; 
			//status_delinquentinv   = 'C';			
		}
		
		else if(status_delinquentinv == 'I')
		{			
			view_delinquentInv 		= "Ver por cliente";
			viewLabelDelinquent		= "Proyectos";
			labelDelinquent			= "Proyecto";
			number_notes_active		= notes_active_investments;
			number_NoDelinquent		= active_number;
			number_backward1_15		= backward1_15_number;
			number_backward16_30	= backward16_30_number;
			number_backward31_90	= backward31_90_number;
			number_backward91_120	= expired91_120_number;
			number_backward121_180	= expired121_180_number;
			number_under_warranty	= under_warranty_number;
			//status_delinquentinv	= 'I';
		}
	}
}
