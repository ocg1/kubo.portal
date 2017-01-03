package mx.com.kubo.managedbeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.bean.SellingProyectBean;
//import mx.com.kubo.bean.SimulationInvestmentBean;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.model.EconActivityByInv;
import mx.com.kubo.model.InfoCalifPorc;
import mx.com.kubo.model.InfoNotification;
import mx.com.kubo.model.InvestmentDetail;
import mx.com.kubo.model.ProyeccionGraficaInv;
//import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectSaldosEdoCta;
//import mx.com.kubo.model.RendimientosInv;
import mx.com.kubo.model.RetornoAnual;
import mx.com.kubo.model.SPProyectActive;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.tools.NumberToLetterConverter;

import org.primefaces.context.RequestContext;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
//import safisrv.ws.InvKuboServicios.ConsultaDetalleInverRequest;
//import safisrv.ws.InvKuboServicios.ConsultaDetalleInverResponse;
import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.ConsultaInverResponse;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MyInvestments extends MyInvestmentsDMO implements Serializable 
{	
	@PostConstruct
    public void init() 
	{	
		
		
		formatStr  = new SimpleDateFormat("ddMMyyyy", new Locale("ES"));
		formatStr5 = new SimpleDateFormat("EEEE dd' de 'MMMM' de 'yyyy", new Locale("ES"));
    	formatStr1 = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));
    	
    	formatStr2 = new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("ES"));
		
    	FacesContext faces      = FacesContext.getCurrentInstance();
		    	
		ELContext elContext = faces.getELContext();
		
		sesion = (SessionBean) faces
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		if( isSesion_DISABLED( faces.getExternalContext() ) ){
			return;
		}
		
		if( sesion != null && sesion.getArea() != null ){
    	
			webService();
		
		}else{
			
			ExternalContext external   = faces.getExternalContext();
			
			try 
			{
		        external.redirect("expired.jsf");
		        
			} catch (IOException ex) {
			       ex.printStackTrace();
			}
			
		}
	}
	
	public void updateAccount()
	{
		webService();
		temporal++;
	}
	
	public void consultaCte()
    {
		
		Calendar wsAT_TotCargacliente = Calendar.getInstance();
		wsAT_TotCargacliente.setTime(new Date());
		
		gnNaturalPersonPK key = null ; 
		
		FacesContext facesContext   = FacesContext.getCurrentInstance();	
		ExternalContext external  	= facesContext.getExternalContext();
		
		ELContext elContext 		= facesContext.getELContext();
		
		String prospectus_id        = (String) external.getRequestParameterMap().get("prospectus_id");
		String company_id        	= (String) external.getRequestParameterMap().get("company_id");
		
		setSesion((SessionBean) facesContext
                .getApplication().getELResolver()
                .getValue(elContext, null, "sessionBean"));
		
		
		
		if( prospectus_id != null && company_id != null ){
			
			key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
			
		}else{
		
			if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
			
			key = new gnNaturalPersonPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
			}else{
				
				try 
				{
			        external.redirect("expired.jsf");
			        return;
				} catch (IOException ex) {
				       ex.printStackTrace();
				}
				
			}
		
		}
        persona = personaNaturalService.getNaturalPersonById(key);
        System.out.println("SAFI Client ID = "+persona.getSafi_client_id());
		//@SuppressWarnings("unused")
		//int valid=0;
		numCuentas.clear();
		numCuentas.add("Todas las cuentas");
		try{
			
			Calendar wsAT = Calendar.getInstance();
			wsAT.setTime(new Date());
			
			safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorA = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CuentasServicios.SAFIServicios serviceA =  locatorA.getSAFIServiciosSoap11();
			
			
			ConsultaCuentasPorClienteResponse replyA = serviceA.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest(persona.getSafi_client_id()));
			
			Calendar wsAT_2 = Calendar.getInstance();
			wsAT_2.setTime(new Date());
			
			long difWSA = wsAT_2.getTimeInMillis() - wsAT.getTimeInMillis();
			
			System.out.println("Tiempo en cargar WSA: "+difWSA+" milisegundos");
			
			if(replyA.getInfocuenta()!=null && replyA.getCodigoRespuesta()!=null && replyA.getCodigoRespuesta()[0].equals("0"))
            {
                String respuestas = replyA.getInfocuenta()[0];
//                if(respuestas ==null || respuestas.isEmpty())
//                    valid=-1;
                   
                String[] cuentas = respuestas.split("\\&\\|\\&");
                
                for(int i =0; i<cuentas.length;i++)
                {
                    String[] vars = cuentas[i].split("\\&\\;\\&");
                    
                    if(vars.length==3){
                    	
                    	numCuentas.add(vars[0]);
                    	
                    }else{
                        System.out.println("Cuenta inválida: " + cuentas[i]);
                        //valid=1;
                    }                    
                }
                
                if(numCuentas.size()==2){
                	selectedAccount = numCuentas.get(1);
                }
            }
//            else
//            {
//                valid=-1;
//            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar wsAT_TotCargacliente_2 = Calendar.getInstance();
		wsAT_TotCargacliente_2.setTime(new Date());
		
		long difWSATOTCARGACLIENTE = wsAT_TotCargacliente_2.getTimeInMillis() - wsAT_TotCargacliente.getTimeInMillis();
		
		System.out.println("Tiempo Total consultaCte: "+difWSATOTCARGACLIENTE+" milisegundos");
		
    }
	
	public void webService()
	{
		
		Calendar wsTot1 = Calendar.getInstance();
		wsTot1.setTime(new Date());
		
		try
		{
//			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//			setSesion((SessionBean) FacesContext.getCurrentInstance()
//	                .getApplication().getELResolver()
//	                .getValue(elContext, null, "sessionBean"));
			
			
	        
			consultaCte();
			
			if ((selectedAccount == null) || (selectedAccount == "Todas las cuentas"))
			{
				selectedAccountAux="0";
				
			}else{
				
				selectedAccountAux=selectedAccount;
			}
			
			/*  SE INICIALIZAN LAS VARIABLES DE CONSULTA DE INTERESES DEL INVERSIONISTA  */
			cuentaAhoID 		= "";
			numInvActivas 		= 0;
			montoInversiones 	= "0.00";
			intARecibir			= "0.00";
			saldoIntGlobal		= "0.00";
			saldoIntPlazoFijo	= "0.00";
			interesCobrado		= "0.00";
			moraCobrado			= "0.00";
			/*    */
			
			Calendar wsSPT = Calendar.getInstance();
			wsSPT.setTime(new Date());
	
			SPProyectActive activeResume = servicioProyecto.getResumenCreditActive(selectedAccountAux);
			
			Calendar wsSP2T01 = Calendar.getInstance();
			wsSP2T01.setTime(new Date());
			
			long difWS_sp101 = wsSP2T01.getTimeInMillis() - wsSPT.getTimeInMillis();
			
			System.out.println("Tiempo en cargar servicioProyecto.getResumenCreditActive: "+difWS_sp101+" milisegundos");
			
			
			Calendar wsSPT01 = Calendar.getInstance();
			wsSPT01.setTime(new Date());
			
			ProyectSaldosEdoCta saldosEdoCta = servicioProyecto.getSaldosEdoCtaClient(selectedAccountAux);
			
			
			System.out.println("---> "+selectedAccountAux);
			
			Calendar wsSP2T = Calendar.getInstance();
			wsSP2T.setTime(new Date());
			
			long difWS_sp1 = wsSP2T.getTimeInMillis() - wsSP2T01.getTimeInMillis();
			
			System.out.println("Tiempo en servicioProyecto.getSaldosEdoCtaClient: "+difWS_sp1+" milisegundos");
			
			cuentaAhoID 	  = Integer.toString(saldosEdoCta.getCuentaAhoID());
			numInvActivas 	  = saldosEdoCta.getNumInvActivas();
			montoInversiones  = Double.toString(saldosEdoCta.getMontoInversiones());
			intARecibir 	  = Double.toString(saldosEdoCta.getIntARecibir());
			saldoIntGlobal 	  = Double.toString(saldosEdoCta.getSaldoIntGlobal());
			saldoIntPlazoFijo = Double.toString(saldosEdoCta.getSaldoIntPlazoFijo());
			interesCobrado	  = Double.toString(saldosEdoCta.getInteresCobrado());
			moraCobrado		  = Double.toString(saldosEdoCta.getMoraCobrado());
			
			Calendar ws1T = Calendar.getInstance();
			ws1T.setTime(new Date());
			
			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios serviceSafi = locator.getSAFIServiciosSoap11();
			
			ConsultaInverResponse replyB = serviceSafi.consultaInver(new ConsultaInverRequest(persona.getSafi_client_id(),selectedAccountAux));
			
			Calendar ws2T = Calendar.getInstance();
			ws2T.setTime(new Date());
			
			long difWS1 = ws2T.getTimeInMillis() - ws1T.getTimeInMillis();
			
			System.out.println("Tiempo en cargar WS1: "+difWS1+" milisegundos");
			
			//ConsultaDetalleInverResponse replyC = serviceSafi.consultaDetalleInver(new ConsultaDetalleInverRequest(persona.getSafi_client_id(),selectedAccountAux));
			
			List<InfoCalifPorc> lstinfo =  servicioProyecto.getInfoCalifPorc( persona.getSafi_client_id(),cuentaAhoID );
			
			Calendar wsFT = Calendar.getInstance();
			wsFT.setTime(new Date());
			
			long difWS2 = wsFT.getTimeInMillis() - ws2T.getTimeInMillis();
			
			System.out.println("Tiempo en cargar WS2: "+difWS2+" milisegundos");
			
			//System.out.println( replyC.getInfoCalifPorc() );
			
			// servicioProyecto.add(newProyectLoan);
			
			//String[] arreglo1 = replyC.getInfoCalifPorc().split("\\&\\|\\&") ;  /// KUBOGRAFICASCON  tipo 1
			
			Calendar graphic1T = Calendar.getInstance();
			graphic1T.setTime(new Date());
			
			Hashtable<String,Double> ht1 = new Hashtable<String,Double> ();
				
			ht1.put("A",0D);
			ht1.put("B",0D);
			ht1.put("C",0D);
			ht1.put("D",0D);
			ht1.put("E",0D);
			ht1.put("F",0D);
			ht1.put("G",0D);
			ht1.put("V",0D);
			ht1.put("R",0D);
			
			for( InfoCalifPorc info : lstinfo ){
				//if((arreglo1[i].split("\\&;\\&")).length == 3 ){
					String riesgo 			= info.getCalificacion();
					String numInversiones 	= info.getNumFondeos()+"";
					//String porc 			= (arreglo1[i].split("\\&;\\&"))[2];
					
					if( riesgo != null && numInversiones != null ){
						ht1.put ( (riesgo.charAt(0)+"") , (ht1.get( riesgo.charAt(0)+"" )+Double.parseDouble(numInversiones)) ) ;
					}
				//}
			} 

			valArrGraphic1 = "<script> graphic1 = [";
					    
			String[] keys = (String[]) ht1.keySet().toArray(new String[0]);  
	        Arrays.sort(keys);
			
		    int n = 0;
		    for ( String key : keys ) {
		    	
		    	if(n>0)
		    		valArrGraphic1+= ",";
		    	
		    	n++;
		    		
		    	valArrGraphic1+= "['"+key+"',"+ht1.get(key)+"]";
		    }
			
			valArrGraphic1 += "]</script>";
			
			Calendar graphic1T_2 = Calendar.getInstance();
			graphic1T_2.setTime(new Date());
			long difBr_2 = graphic1T_2.getTimeInMillis() - graphic1T.getTimeInMillis();
			
			System.out.println("Tiempo en cargar Grafica 1: "+difBr_2+" milisegundos");
			
			/**********************Gráfica 2**********************************/
			
			Calendar graphic2T = Calendar.getInstance();
			graphic2T.setTime(new Date());
			
			
			//String[] arreglo2 = replyC.getInfoTasasPonCalif().split("\\&\\|\\&") ; // KUBOGRAFICASCON tipo 1
			
			Hashtable< String,List<Double> > ht2 = new Hashtable<String,List<Double>> ();
			
			ht2.put("A",new ArrayList<Double>());
			ht2.put("B",new ArrayList<Double>());
			ht2.put("C",new ArrayList<Double>());
			ht2.put("D",new ArrayList<Double>());
			ht2.put("E",new ArrayList<Double>());
			ht2.put("F",new ArrayList<Double>());
			ht2.put("G",new ArrayList<Double>());
			ht2.put("V",new ArrayList<Double>());
			ht2.put("R",new ArrayList<Double>());
			
			for( InfoCalifPorc info : lstinfo ){
				
				//if(( arreglo2[i].split("\\&;\\&") ).length > 1 ){
				
					String riesgo 			= info.getCalificacion();
					String tasa			 	= info.getPromTasa()+"";
					
					ArrayList<Double> tmp = (ArrayList<Double>) ht2.get( riesgo.charAt(0)+"" );
					
					tmp.add(Double.parseDouble( tasa ));
					
					ht2.put ( (riesgo.charAt(0)+"") , tmp  );
					
				//}
				
			}
			
			
			keys = (String[]) ht2.keySet().toArray(new String[0]);  
	        Arrays.sort(keys);
			
		    n = 0;
		    
		    String axisX = " [''";
		    String axisY = "['Tasa ponderada'";
		    
		    for ( String key : keys ) {
		    	
		    	axisX += ",'"+key+"'";
		    	
		    	ArrayList<Double> tmp = (ArrayList<Double>) ht2.get( key );
		    	
		    	Double t = 0D;
		    	
		    	for (Double d : tmp){
		    		t += d;
		    	}
		    	
		    	t = (t)/tmp.size();
		    
		    	t = (double)Math.round((t)*100)/100;

		    	axisY += ","+t;
		    	
		    }
		    
		    axisX += "]";
		    axisY += "]";
		    
		    valArrGraphic2 = "<script>graphic2 =["+axisX+","+axisY+"]</script>";
		    
		    Calendar graphic2T_2 = Calendar.getInstance();
			graphic2T_2.setTime(new Date());
			long difGr_2 = graphic2T_2.getTimeInMillis() - graphic2T.getTimeInMillis();
			
			System.out.println("Tiempo en cargar Grafica 2: "+difGr_2+" milisegundos");
		    
		    /*********************fin Gráfica 2***********************************/
		    
		    /*********************Gráfica Actividades***********************************/
		    
			Calendar graphic3T = Calendar.getInstance();
			graphic3T.setTime(new Date());
			
			
		    valArrGraphic4 = "<script> graphic4 = [";
			
		    List<EconActivityByInv> lst = servicioProyecto.getEconActivityByInvLst( persona.getSafi_client_id(), cuentaAhoID );
		    
		    int ig4 = 0;
		    
		    for( EconActivityByInv tmpAct : lst ){
		    	
		    	if(ig4!=0)
		    		valArrGraphic4+=",";
		    	
		    	ig4++;
		    	
		    	valArrGraphic4 += "['"+tmpAct.getDescription()+"',"+tmpAct.getQuantity()+"]";
		    	
		    }
		    
		    
			
			valArrGraphic4 += "]</script>";
		    
			
			Calendar graphic3T_2 = Calendar.getInstance();
			graphic3T_2.setTime(new Date());
			long difGr_3 = graphic3T_2.getTimeInMillis() - graphic3T.getTimeInMillis();
			
			System.out.println("Tiempo en cargar Grafica 3: "+difGr_3+" milisegundos");
		    
		    /*********************fin Gráfica Actividades***********************************/
				
			//String var1 = replyC.getInfoCalifPorc();
			
			//String [] calificaciones = null ;
			//if(var1!=null && !var1.equals(""))
			//	calificaciones= var1.split("\\&\\|\\&");
			//if(calificaciones != null){
				for(InfoCalifPorc info : lstinfo ){
					//String[] vars = calificaciones[i].split("\\&\\;\\&");
		            //    if(vars.length>0 && vars.length==3){
		                	infoCalEst.add(info.getCalificacion());
		                	infoCalPorc.add(info.getPorcCalificacion()+"");
		             //   }
	                }
			//}
               
			/*
            String var2 = replyC.getInfoTasasPonCalif();
    		
            String [] calificaciones2;
            
            if(var2!=null && var2.length()>0)
				calificaciones2 = var2.split("\\&\\|\\&");
            else
            	calificaciones2 = new String [0];
            */
//			for(InfoCalifPorc info : lstinfo ){
//    			String[] vars2 = calificaciones2[i].split("\\&\\;\\&");
//    				if( vars2.length == 3 )
//    					infoTasaEst.add(vars2[0]); -- 
//                    	infoTasaPorc.add(vars2[1]); -- 
//                    };
			
			client = persona.NombreCompletoNPM();
			
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
			// balances_accrued_interest = dec.format(new Double(replyB.getSaldoIntDevengados()==null?"0":replyB.getSaldoIntDevengados()));
			notes_cash = new Integer(replyB.getNumeroEfectivoDispon()==null?"0":replyB.getNumeroEfectivoDispon());
			notes_investment_process = new Integer(replyB.getNumeroInverEnProceso()==null?"0":replyB.getNumeroInverEnProceso());
			// notes_active_investments = new Integer(replyB.getNumeroInvActivas()==null?"0":replyB.getNumeroInvActivas());
			
			notes_accrued_interest = new Integer(replyB.getNumeroIntDevengados()==null?"0":replyB.getNumeroIntDevengados());
			
			investment_summary = new Integer(replyB.getNumeroTotInversiones()==null?"0":replyB.getNumeroTotInversiones()); //0
					
			in_process_amount = dec.format(new Double(replyB.getSaldoInverEnProceso()==null?"0":replyB.getSaldoInverEnProceso()));
			
			broken_amount = dec.format(new Double(replyB.getSaldoInvQuebrantadasResumen()==null?"0":replyB.getSaldoInvQuebrantadasResumen())); //0
			fully_paid_amount = dec.format(new Double(replyB.getSaldoInvLiquidadasResumen()==null?"0":replyB.getSaldoInvLiquidadasResumen()));
		
			// active_number = new Integer(replyB.getNumeroInvActivasResumen()==null?"0":replyB.getNumeroInvActivasResumen());
			
			in_process_number = new Integer(replyB.getNumeroInverEnProceso()==null?"0":replyB.getNumeroInverEnProceso());
			
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
			
			System.out.println("SALDO CTAS ORDEN "+saldoCapitalCtaOrden+" "+saldoInteresCtaOrden+" "+under_warranty_number_cli+" "+under_warranty_number+" "+saldoTotalCtaOrden);
			
			getInvestmentsbyLink();
			
			balances_accrued_interest 	 = dec.format(new Double(activeResume.getSaldoIntVig() + activeResume.getSaldoIntAtr()));
	
			active_amount 		 = dec.format(new Double(replyB.getSaldoInvActivasResumen()==null?"0":replyB.getSaldoInvActivasResumen()));
			
			/*
			backward1_15_amount  = dec.format(new Double(replyB.getSaldoInvAtrasadas1A15Resumen()==null?"0":replyB.getSaldoInvAtrasadas1A15Resumen()));
			backward16_30_amount = dec.format(new Double(replyB.getSaldoInvAtrasadas16A30Resumen()==null?"0":replyB.getSaldoInvAtrasadas16A30Resumen()));
			backward31_90_amount = dec.format(new Double(replyB.getSaldoInvAtrasadas31A90Resumen()==null?"0":replyB.getSaldoInvAtrasadas31A90Resumen()));
			expired91_120_amount = dec.format(new Double(replyB.getSaldoInvVencidas91A120Resumen()==null?"0":replyB.getSaldoInvVencidas91A120Resumen()));
			expired121_180_amount= dec.format(new Double(replyB.getSaldoInvVencidas121A180Resumen()==null?"0":replyB.getSaldoInvVencidas121A180Resumen()));
			
			broken_number = new Integer(replyB.getNumeroInvQuebrantadasResumen()==null?"0":replyB.getNumeroInvQuebrantadasResumen());
			*/
			fully_paid_number = new Integer(replyB.getNumeroInvLiquidadasResumen()==null?"0":replyB.getNumeroInvLiquidadasResumen());
			
//				payments_received_number = new Integer(replyB.getNumComFalPago()==null?"0":replyB.getNumComFalPago());
			payments_received_number = 0; // new Integer(replyB.getNumComFalPago()==null?"0":replyB.getNumComFalPago());
			capital_number = new Integer(replyB.getNumCapitalCobrado()==null?"0":replyB.getNumCapitalCobrado());
			ordinary_interests_number = new Integer(replyB.getNumInteresCobrado()==null?"0":replyB.getNumInteresCobrado());
			interest_on_arrears_number = new Integer(replyB.getNumMoraCobrado()==null?"0":replyB.getNumMoraCobrado()); //0
			
			// payments_received_amount = dec.format(new Double(replyB.getPagTotalRecib() ComFalPago()==null?"0":replyB.getComFalPago()));
			payments_received_amount = dec.format(new Double(replyB.getPagTotalRecib()==null?"0":replyB.getPagTotalRecib()));
			capital_amount = dec.format(new Double(replyB.getCapitalCobrado()==null?"0":replyB.getCapitalCobrado()));
			ordinary_interests_amount = dec.format(new Double(replyB.getInteresCobrado()==null?"0":replyB.getInteresCobrado()));
			interest_on_arrears_amount = dec.format(new Double(replyB.getMoraCobrado()==null?"0":replyB.getMoraCobrado()));
			
			/*
			tasaPonderada 		= new Double(replyC.getTasaPonderada()==null?"0":replyC.getTasaPonderada());
			saldoIntDev 		= new Double (replyC.getSaldoIntDev()==null?"0":replyC.getSaldoIntDev());
			numPagosRecibidos 	= new Integer (replyC.getNumPagosRecibidos()==null?"0":(replyC.getNumPagosRecibidos().split("\\."))[0]);
			numPagosCapital 	= new Integer (replyC.getNumPagosCapital()==null?"0":(replyC.getNumPagosCapital().split("\\."))[0]);
			numPagosInterOrdi 	= new Integer (replyC.getNumPagosInterOrdi()==null?"0":(replyC.getNumPagosInterOrdi().split("\\."))[0]);
			numPagosInteMora 	= new Integer (replyC.getNumPagosInteMora()==null?"0":(replyC.getNumPagosInteMora().split("\\."))[0]);
			impuestos 			= Double.parseDouble(replyC.getImpuestos()==null?"0":replyC.getImpuestos());
			comisPagadas 		= new Integer (replyC.getComisPagadas()==null?"0":(replyC.getComisPagadas().split("\\."))[0]);
			numComisRecibidas 	= new Integer (replyC.getNumComisRecibidas()==null?"0":(replyC.getNumComisRecibidas().split("\\."))[0]);
			
			depositos 			= dec.format(new Double(replyC.getDepositos()==null?"0.0":replyC.getDepositos()));
			inverRealiz 		= new Double (replyC.getInverRealiz()==null?"0":replyC.getInverRealiz());
			pagCapRecib 		= new Double (replyC.getPagCapRecib()==null?"0":replyC.getPagCapRecib());
			intOrdRec 			= new Double (replyC.getIntOrdRec()==null?"0":replyC.getIntOrdRec());
			intMoraRec 			= new Double (replyC.getIntMoraRec()==null?"0":replyC.getIntMoraRec());
			recupMorosos 		= new Double (replyC.getRecupMorosos()==null?"0":replyC.getRecupMorosos());
			retenidoISR 		= new Double (replyC.getISRretenido()==null?"0":replyC.getISRretenido());
			comisCobrad 		= new Double (replyC.getComisCobrad()==null?"0":replyC.getComisCobrad());
			comisPagad 			= new Double (replyC.getComisPagad()==null?"0":replyC.getComisPagad());
			ajustes 			= new Double (replyC.getAjustes()==null?"0":replyC.getAjustes());
			quebrantos 			= new Double (replyC.getQuebrantos()==null?"0":replyC.getQuebrantos());
			quebranXapli 		= new Double (replyC.getQuebranXapli()==null?"0":replyC.getQuebranXapli());
			premiosRecom 		= new Double (replyC.getPremiosRecom()==null?"0":replyC.getPremiosRecom());
			*/
			
			generaGraficaProyecccion( cuentaAhoID );
			
			generaDatosRendimientos( cuentaAhoID );
			
			initRetornoAnual( cuentaAhoID );
			
			service_access.add(15, 0, sesion, true);
			
		}
		catch(RemoteException ce){
			System.out.println( ce.getMessage() );
			
			String url = ("connectionOut.jsf");
					FacesContext fc = FacesContext.getCurrentInstance();
					ExternalContext ec = fc.getExternalContext();
					try {
					        ec.redirect(url);
					} catch (IOException ex) {
					       ex.printStackTrace();
					}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar wsTot2 = Calendar.getInstance();
		wsTot2.setTime(new Date());
		
		long difTOT = wsTot2.getTimeInMillis() - wsTot1.getTimeInMillis();
		
		initScreenNotificacion(  );
		
		System.out.println("Tiempo TOTAL DE CARGAR WEBSERVICES: "+difTOT+" milisegundos");
	}
	
	private void initScreenNotificacion() {
		
		displayNotificacion = false;
		
		List<InfoNotification>  lstNoti = infonotificationservice.getInfoNotificationByProspectus(persona.getNatPerPK().getCompany_id(), persona.getNatPerPK().getProspectus_id(), NOTIFICATION_CONFIGURATION_SCREEN );
				
		if( lstNoti == null || lstNoti.size() == 0 ){
			
			displayNotificacion = true;
			
			insertInfoNotification();
			
		}
		
	}
	
	private void generaDatosRendimientos( String cuentaAhoID ){
		
		lstRendimientos =	rendimientosInvService.getRendimientosInvLst( Integer.parseInt( cuentaAhoID ) );
		
	}
	
	private void generaGraficaProyecccion(String cuentaAhoID) {
		
		//cuentaAhoID = "100006987";
		
		List<ProyeccionGraficaInv> lstPG = proyeccionGraficaInvService.getProyeccionGraficaInvLst( Integer.parseInt( cuentaAhoID ) ) ;
		
		setScriptGraphicBar("");
		
		String script = "<script>";
		
		
		script +=  "\nvar dataProyection  = new google.visualization.DataTable();";
		
		 script += "\ndataProyection.addColumn('string', 'Fecha');";
		 script += "\ndataProyection.addColumn( { type: 'string',  role: 'tooltip', 'p': {'html': true} } );";
		 script += "\ndataProyection.addColumn( { type: 'boolean', role: 'scope'});";
//		 script += "\ndataProyection.addColumn('number', 'Capital Invertido');";
		 script += "\ndataProyection.addColumn('number', 'Garantía por ejercer');";
		 script += "\ndataProyection.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataProyection.addColumn('number', 'Capital por recibir');";
		 script += "\ndataProyection.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataProyection.addColumn('number', 'Interés por recibir');";
		 script += "\ndataProyection.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataProyection.addColumn('number', 'Garantía Aplicada');";
		 script += "\ndataProyection.addColumn('number', 'Capital Pagado');";
		 script += "\ndataProyection.addColumn('number', 'Interés Pagado');";
		 script += "\ndataProyection.addColumn( {  type: 'string',  role: 'annotation' });";
		 
		 
//		 script += "\ndataProyection.addColumn('number', 'Capital Sin Invertir');";
		 
		 script += "\ndataProyection.addRows([";
		 
		 int i = 0;
		 
		 Date d = new Date();
		
		String s2 = formatStr2.format(d);
		
		//String dd = s2.split("/")[0];
		String mm = s2.split("/")[1];
		String yy = s2.split("/")[2];
		
		boolean b1 = true; 
		boolean b2 = true; 
		
		 for( ProyeccionGraficaInv py : lstPG    ){
				 
			 if( i != 0 ){
				 script +=",";
			 }
			 i++;
			 
			 script +=" \n ['"+ meses[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', "; // MES FECHA
	
			 if( b1  ){
				 
			/* tooltip */			script += "createCustomHTMLBarRendInv('"+ meses_c[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', '"+dec.format(py.getCapitalGanado())+"','"+ dec.format(py.getInteresGanado())  +"','" +dec.format(py.getCapitalPerdido())+"',true),   ";
			
			
					 }else{
						 
						 script += "createCustomHTMLBarRendInv('"+ meses_c[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', '"+dec.format(py.getCapitalGanado())+"','"+ dec.format(py.getInteresGanado())  +"','" +dec.format(py.getCapitalPerdido())+"',false),   ";
						 
					 }
			 
			// if(y > 10){
			 /* scope */ script +=  ""+true+",   ";
			// }else{
				 /* scope */ // script +=  ""+false+",   ";
			// }
	
			 
			 
			 
	
			 if( b1  ){
			 
				 /* Capital Invertido PY * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido PY*/	script		+= "-0.00, true, "
					/* Capital Pagado PY*/			+ "0.00, true, "
					/* Interes Pagado PY*/			+ "0.00,true, "
				 
				 /* Capital Invertido * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido */		+ "-"+py.getCapitalPerdido()+", "
					/* Capital Pagado */			+ ""+py.getCapitalGanado()+", "
					/* Interes Pagado */			+ ""+py.getInteresGanado()+" ";
					
					
	
			 }else{
				 
				
					/* Capital Invertido * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido */	script	+= "-"+py.getCapitalPerdido()+",true, "
					/* Capital Pagado */			+ ""+py.getCapitalGanado()+", true, "
					/* Interes Pagado */			+ ""+py.getInteresGanado()+", true, "
					
					/* Capital Invertido PY * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido PY*/			+ "-0.00, "
					/* Capital Pagado PY*/			+ "0.00, "
					/* Interes Pagado PY*/			+ "0.00 ";
			 }
			 
			
	
	/* Capital disponible * /+ ""+(py.getSaldoDisponible()  )+" " */
			
			 
			 if( Integer.parseInt( mm ) == py.getPk().getMes() && Integer.parseInt( yy ) == py.getPk().getAnio() && b2 ){
				 b1 = false;
				 b2 = false;
				 script	+= ",'Hoy'";
			 }else{
				 script	+= ",null";
			 }
			 
			 script	+= "] " ;
		
		}
				 
		script +=" \n ]); " ;
		
		script += "\n</script>";
		
		System.out.println(script);
		
		setScriptGraphicBar(script);
		
		initGrophicArea(   lstPG );
		
	}
	
	protected void initGrophicArea(  List<ProyeccionGraficaInv> lstPG ){
		
		setScriptGraphicSaldoDisponible("");
		
		Date d = new Date();
		
		boolean b1 = true; 
		boolean b2 = true; 
		
		String s2 = formatStr2.format(d);
		
		//String dd = s2.split("/")[0];
		String mm = s2.split("/")[1];
		String yy = s2.split("/")[2];
		
		String script = "<script>";
		
		
		script +=  "var dataA  = new google.visualization.DataTable();";
		
		 script += "dataA.addColumn('string', 'Meses');";
		 script += "dataA.addColumn('number', 'Depósitos');";
		 script += "dataA.addColumn('number', 'Retiros');";		 
		 script += "dataA.addColumn('number', 'Saldo Disponible');";
		 script += "dataA.addColumn( { type: 'string', role: 'annotation' });";
		 script += "dataA.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );";
		 script += "dataA.addColumn( { type: 'boolean', role: 'scope' } );";
		 script += "dataA.addColumn( { type: 'boolean', role: 'certainty' } );";
		 
		 script += "dataA.addRows([";
		 
		 int i = 0;
		 int y = 0;
		 Double L =  ( lstPG.size() / 6D );
		 int v = L.intValue();
		 
		Double disponible = 0D;
		 
		 for( ProyeccionGraficaInv py : lstPG    ){
			 
			String anotation = "null"; 
			
			if( i%v == 0){
			 
				anotation = "'"+dec.format(disponible)+ "'";
			 
			}
			 
			if( i != 0 ){
				script +=",";
			}
			 
			 i++;
			 
			 if( b1  ){
				 
				 disponible = py.getSaldoDisponible();
				 
			 }else{
				 
				 if( y == 0 ){
				 
					 disponible =  py.getSaldoDisponible() + py.getInteresGanado() + py.getCapitalGanado();
					 y++;
				
				 }else{
					 
					 disponible =  disponible + py.getInteresGanado() + py.getCapitalGanado();
					 
				 }
				 
			 }
			 
			 Double deposito = py.getDepositosMes();
			 Double retiro = py.getRetirosMes();
			 
                
			 script +=    " ['"+ meses[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "',"+deposito+","+retiro+","+disponible+","+anotation+",createCustomHTMLAreaSaldoDispCntnt( '"+dec.format(disponible)+ "','"+dec.format(deposito)+ "','"+dec.format(retiro)+ "', '"+ meses_c[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "'),"+b1+", "+b1+" ] " ;
			 
			 if( Integer.parseInt( mm ) == py.getPk().getMes() && Integer.parseInt( yy ) == py.getPk().getAnio() && b2 ){
				 b1 = false;
				 b2 = false;
				
			 }
			 
		 }
                 
		
		script +=    " ]);" ;
		
		script += "</script>";
		
		System.out.println(script);
		
		setScriptGraphicSaldoDisponible(script);
		
		initComboGraphic( lstPG );
		
	}
	
	protected void initComboGraphic(  List<ProyeccionGraficaInv> lstPG ){
		
		setScriptGraphicCombo("");
		
		String script = "<script>";
		
		
		script +=  "\nvar dataCombo  = new google.visualization.DataTable();";
		
		 script += "\ndataCombo.addColumn('string', 'Fecha');";
		 script += "\ndataCombo.addColumn( { type: 'string',  role: 'tooltip', 'p': {'html': true} } );";
		 script += "\ndataCombo.addColumn('number', 'Garantía por ejercer');";
		 script += "\ndataCombo.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataCombo.addColumn('number', 'Capital por recibir');";
		 script += "\ndataCombo.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataCombo.addColumn('number', 'Interés por recibir');";
		 script += "\ndataCombo.addColumn( { type: 'boolean', role: 'certainty' } );";
		 script += "\ndataCombo.addColumn('number', 'Garantía Aplicada');";
		 script += "\ndataCombo.addColumn('number', 'Capital Pagado');";
		 script += "\ndataCombo.addColumn('number', 'Interés Pagado');";
		 script += "\ndataCombo.addColumn( {  type: 'string',  role: 'annotation' });";
		 script += "\ndataCombo.addColumn('number', 'Saldo Disponible');";
		 script += "\ndataCombo.addColumn( {  type: 'string',  role: 'annotation' });";
		 script += "\ndataCombo.addColumn( { type: 'boolean', role: 'scope' } );";
		 
		 
		 
//		 script += "\ndataCombo.addColumn('number', 'Capital Sin Invertir');";
		 
		 script += "\ndataCombo.addRows([";
		 
		 int i = 0;
		 
		 Date d = new Date();
		
		String s2 = formatStr2.format(d);
		
		//String dd = s2.split("/")[0];
		String mm = s2.split("/")[1];
		String yy = s2.split("/")[2];
		
		boolean b1 = true; 
		boolean b2 = true; 
		int y = 0;
		
		Double disponible = 0D;
		Double L =  ( lstPG.size() / 6D );
		int v = L.intValue();
		
		 for( ProyeccionGraficaInv py : lstPG    ){
			 
			 if( b1  ){
				 
				 disponible = py.getSaldoDisponible();
				 
			 }else{
				 
				 if( y == 0 ){
				 
					 disponible =  py.getSaldoDisponible() + py.getInteresGanado() + py.getCapitalGanado();
					 y++;
				
				 }else{
					 
					 disponible =  disponible + py.getInteresGanado() + py.getCapitalGanado();
					 
				 }
				 
			 }
				 
			 String  anotation = "null";
			 
			 if( i != 0 ){
				 script +=",";
			 }
			 i++;
			 
			 script +=" \n ['"+ meses[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', "; // MES FECHA
	
			 
			 if( b1 ){
				 
				 /* tooltip */			script	+= " createCustomHTMLComboInv('"+ meses_c[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', '"+dec.format(py.getCapitalGanado())+"','"+ dec.format(py.getInteresGanado())  +"','" +dec.format(py.getCapitalPerdido())+"','"+dec.format(disponible)+"',true),   ";
		
				 }else{
					 
					 /* tooltip */			script	+= " createCustomHTMLComboInv('"+ meses_c[ py.getPk().getMes() -1] +" - "+ py.getPk().getAnio() + "', '"+dec.format(py.getCapitalGanado())+"','"+ dec.format(py.getInteresGanado())  +"','" +dec.format(py.getCapitalPerdido())+"','"+dec.format(disponible)+"',false),   ";
					 
				 }
			 
			 
			 
			// if(y > 10){
			 /* scope */ 
			// }else{
				 /* scope */ // script +=  ""+false+",   ";
			// }
	
			 
			 
			 
	
			 if( b1  ){
			 
				 /* Capital Invertido PY * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido PY*/	script		+= "-0.00, true, "
					/* Capital Pagado PY*/			+ "0.00, true, "
					/* Interes Pagado PY*/			+ "0.00,true, "
				 
				 /* Capital Invertido * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido */		+ "-"+py.getCapitalPerdido()+", "
					/* Capital Pagado */			+ ""+py.getCapitalGanado()+", "
					/* Interes Pagado */			+ ""+py.getInteresGanado()+" ";
					
					
	
			 }else{
				 
				
					/* Capital Invertido * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido */	script	+= "-"+py.getCapitalPerdido()+",true, "
					/* Capital Pagado */			+ ""+py.getCapitalGanado()+", true, "
					/* Interes Pagado */			+ ""+py.getInteresGanado()+", true, "
					
					/* Capital Invertido PY * /	 += ""+py.getSaldoInversiones()+", " */
					/* Capital Perdido PY*/			+ "-0.00, "
					/* Capital Pagado PY*/			+ "0.00, "
					/* Interes Pagado PY*/			+ "0.00 ";
			 }
			 
			
	
	/* Capital disponible * /+ ""+(py.getSaldoDisponible()  )+" " */
			
			 
			 if( Integer.parseInt( mm ) == py.getPk().getMes() && Integer.parseInt( yy ) == py.getPk().getAnio() && b2 ){
				 script	+= ",'Hoy'";
			 }else{
				 script	+= ",null";
			 }
			 
			 // -- SALDO DISPONIBLE
			 
			 
			 
			 if( i%v == 0){
				 
					anotation = "'"+dec.format(disponible)+ "'";
				 
				}
			 
			 script	+= ","+disponible+","+anotation+","+b1+"";
			 
			// -- 
			 
			 
			
				
			 
			 // -- FIN SALDO DISPONIBLE
			 
			 script	+= "] " ;
			 
			 
			 if( Integer.parseInt( mm ) == py.getPk().getMes() && Integer.parseInt( yy ) == py.getPk().getAnio() && b2 ){
				 b1 = false;
				 b2 = false;
			 }
			 
		
		}
				 
		script +=" \n ]); " ;
		
		script += "\n</script>";
		
		System.out.println(script);
		
		setScriptGraphicCombo(script);
		
	}
	
	private void initRetornoAnual( String cuentaAhoID ){
		
		RetornoAnual retorno =  retornoAnualService.getRetornoAnual( Integer.parseInt( cuentaAhoID ) );
		
		if( retorno != null ){
			retornoAnual= retorno.getRa() +"";
			retornoAnual_Ajustado= retorno.getRaAjus() + "";
		}else{
			retornoAnual= "0";
			retornoAnual_Ajustado="0";
		}
	}

	public void getProjectProcess( ActionEvent e )
	{
		
		//String status = (String) e.getComponent().getAttributes().get("status").toString();
		
		String cuenta = getStrCuentas();
		
			safiProyecInProcessLst = servicioProyecto.getProyectLoanInProcessBySafi(cuenta);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		NavigationInvest nav =((NavigationInvest) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "navigationInvest"));
		
		nav.changePage(e);
		
	}
	
	public void getProjectActive(ActionEvent e)
	{

		String status = (String) e.getComponent().getAttributes().get("status").toString();
		String cuenta = getStrCuentas();
		
		safiProyecLoanActiveLst = servicioProyecto.getProyectLoanActiveInSafiByAccount(cuenta, status,status_delinquentinv);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		NavigationInvest nav =((NavigationInvest) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "navigationInvest"));
		
		System.out.println("Status: " + status);
		
		displayName = false;
		
		pageName = "";

		if(status.equals("A1")){
			displayName = true;
			
			pageName = "Proyectos Activos Activas y sin mora";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = active_number_cli;
			};
		}else if( status.equals("A2") ){
			
			displayName = true;
			pageName = "Proyectos Activos Atrasadas de 1-15 días";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = backward1_15_number_cli;
			};
		}else if( status.equals("A3") ){
			
			displayName = true;
			pageName = "Proyectos Activos Atrasadas de 16 - 30 días";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = backward16_30_number_cli;
			};
		}else if( status.equals("A4") ){
			
			displayName = true;
			pageName = "Proyectos Activos Atrasadas de 31-90 días";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = backward31_90_number_cli;
			};
		}else if( status.equals("A5") ){
			
			displayName = true;
			pageName = "Proyectos Activos Atrasadas de 91-120 días";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = expired91_120_number_cli;
			};
		}else if(status.equals("A6")){
			displayName = true;
			pageName = "Proyectos Activos Atrasadas más 120 días";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = expired121_180_number_cli;
			};
		}else if(status.equals("A10")){
			displayName = true;
			pageName = "Garantías Aplicadas";
			
			if(status_delinquentinv == 'C'){
				numberOfClients = under_warranty_number_cli;
			};
		}else if(status.equals("A")){
			if(status_delinquentinv == 'C'){
				numberOfClients = notes_active_investments_cli;
			};
		};
		if(status.equals("A10")){
			labelBalances = "Garantías aplicadas \n Capital / \n Interes";
		}else{
			labelBalances = "Saldo \n Vigente \n Atrasado";
		};
		nav.changePage(e);
	}
	
	public void getSellingProject(ActionEvent e)
	{

		String status = (String) e.getComponent().getAttributes().get("status").toString();
		
		//String cuenta = getStrCuentas();
		
//		if(sesion.getProspectus_id().toString().equals("7161")){
		
		
			ProyectLoanPK pk = new ProyectLoanPK();
			
			pk.setCompany_id(1);
			pk.setProspectus_id(1858);
			pk.setProyect_id(1068);
			pk.setProyect_loan_id(1016);
			
//			ProyectLoan proyect_loan = servicioProyecto.getProyectLoanById(pk);
			
			proyecSellingLst = new ArrayList<SellingProyectBean>();
			
//			SellingProyectBean sell = new SellingProyectBean();
//			
//			sell.setCredit_id(proyect_loan.getSafi_credit_id());
//			sell.setCuotas_atrasadas("4");
//			sell.setDias_atraso("170");
//			sell.setDias_rest("6");
//			sell.setFec_limit("12 de septiembre de 2014");
//			sell.setNum_ofertas("0");
//			sell.setPorc_ult_oferta("0");
//			sell.setSaldo_cap("$16,713.76");
//			sell.setStatus_str("En Venta");
//			sell.setUltima_oferta("$0.00");
			
//			proyecSellingLst.add(sell);
			
//		}else{
//			
//			proyecSellingLst = new ArrayList<SellingProyectBean>();
//		}
//		
		
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		NavigationInvest nav =((NavigationInvest) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "navigationInvest"));
		
		System.out.println("Status: " + status);
		
		displayName = false;
		
		pageName = "";
		
		
		
		nav.changePage(e);
	}
	
	public void clickEvent()
	{
		
		System.out.println( " venta de cartera: "+ valueClick );
		
		String[] val = valueClick.split("::");
		
		if(val[3].equals("true")){
			valMontoStr = val[1];
			valMonto 	= val[1].replace("$","").replace(",", "");
			valCredit 	= val[0];
			valMontoUltOferStr = val[2];
			valMontoUltOfer = val[2].replace("$","").replace(",", "");
		}else{
			valMonto 		= "";
			valCredit 		= "";
			valMontoStr 	= "";
			valMontoUltOfer = "";
			valMontoUltOferStr = "";
		}
		
	}
	
	public void realizarOferta()
	{
		
		for(SellingProyectBean sell : proyecSellingLst){
			
			if(sell.getCredit_id().equals(valCredit)){
				
				sell.setNum_ofertas( "" + (Integer.parseInt( sell.getNum_ofertas() ) + 1 ) );
				
				sell.setPorc_ult_oferta( porcProp );
				
				sell.setUltima_oferta( dec.format( Double.parseDouble( montoProp.replace(",", "") ) ) );
				
				valCredit = "";
				valMonto = "";
				valMontoStr = "";
				valMontoUltOfer = "";
				
			}
			
		}
		
	}

	private String getStrCuentas()
	{
		String cuenta	=	"";
		
		if(selectedAccount == null || selectedAccount.trim().length()==0 || selectedAccount.equals("Todas las cuentas") )
		{
			int i = 0;
			
			for(String account : numCuentas )
			{
				if(i>0)
				{
					cuenta += ",";
				}
				
				if(!account.equals("Todas las cuentas")){
					cuenta += "'"+account+"'";
					i++;
				}
			}
		}else{
			cuenta = "'"+selectedAccount+"'";
		}
		return cuenta;
		
	}
	
	public void getTicketPagos()
	{
		System.out.println("Generando ticket..............");
		
			
			if (valuesforticket != null)
			{ 
				////System.out.println(valuesforticket);
				
				ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
				//Integer proyect_loan_id		= Integer.parseInt( valuesforticket.split("::")[0] );
				Integer kuboFondeoInv_id	= Integer.parseInt( valuesforticket.split("::")[1] );
				
				//ProyectLoanActiveInSafi active = servicioProyecto.getProyectLoanActiveInSafiByID(kuboFondeoInv_id,proyect_loan_id);
				
				InvestmentDetail active = servicioProyecto.getInvestmentDetailByID(kuboFondeoInv_id);
				
				ticketStr = getHtml("ticketPagos");
				
				if(getSesion().getArea().toString().equals("I")){
					
					ticketStr=ticketStr.replace("###NOMBRE###",getPersona().NombreCompletoNPM());
					ticketStr=ticketStr.replace("###RFC###", getPersona().getMx_rfc());
					ticketStr=ticketStr.replace("###CLIENTE###", getPersona().getSafi_client_id());
				
				}else if(getSesion().getArea().toString().equals("M")){
					
					ticketStr=ticketStr.replace("###NOMBRE###", active.getPerson().NombreCompletoNPM());
					ticketStr=ticketStr.replace("###RFC###", active.getPerson().getMx_rfc());
					ticketStr=ticketStr.replace("###CLIENTE###", active.getPerson().getSafi_client_id());
				
				
				}else{
					
					ticketStr=ticketStr.replace("###NOMBRE###", "NO DISPONIBLE");
					ticketStr=ticketStr.replace("###RFC###", "NO DISPONIBLE");
					
				}
				
				
				ticketStr=ticketStr.replace("###NUMCREDITO###", active.getFondeoKuboID()+"");
				
				ticketStr=ticketStr.replace("###FECHAINICIO###", formatStr5.format(active.getFechaInicio()));
				
				ticketStr=ticketStr.replace("###FECHAFIN###", formatStr5.format( active.getFechaVencimiento()));
				
				ticketStr=ticketStr.replace("###MONTOINV###", dec.format(active.getMontoFondeo())+"");
				
				ticketStr=ticketStr.replace("###TASA_INV###", active.getRate_investor()+"%");
				
				ticketStr=ticketStr.replace("###TASA_MORATORIA###", (active.getRate_investor() * 2)+"%");
				
				ticketStr=ticketStr.replace("###RIESGO_KUBO###", active.getKubo_score_a()+active.getKubo_score_b()+"");
				
				ticketStr=ticketStr.replace("###FREQ###", active.getFrequency().getName()+"");
				
				ticketStr=ticketStr.replace("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofipo")+"");
				
				ticketStr=ticketStr.replace("###RFC_KUBO###", recurso.getString( "rfc_kubo_sofipo" )+"");
				
				ticketStr=ticketStr.replace("###TELEFONO###", recurso.getString("Kubo_telefono")+"");
				
				ticketStr=ticketStr.replace("###FECHA_PAGO###",formatStr5.format(active.getFechaInicio())+"");
				
				ticketStr=ticketStr.replace("###CUENTA###",active.getCuentaAhoID()+"" );
				
				String sTotal = (active.getMontoFondeo()+"");
				String centavos ="";
				String pesos = sTotal.split("\\.")[0];
		         
		         if(sTotal.split("\\.").length > 1)
		        	 centavos = sTotal.split("\\.")[1];
		         
		         if(centavos.length()<2){
		        	 centavos += "0";
		         }
		         
		         String letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
		         
		         ticketStr = ticketStr.replaceAll("###IMPORTE_LETRA###", letter );
				
		         ticketStr = ticketStr.replaceAll("###CENTAVOS###", centavos );
		         
		         List<AmortizacionInversionista> lst = amortInverService.getAmortizacionInversionistaListByIdInver( active.getFondeoKuboID().intValue() );
		         
		         String str = "";
		         Double tot = 0D;
		         int count = 0;
		         Double sumCap = 0D;
		         Double sumInt = 0D;
		         Double sumISR = 0D;
		         
		         if(lst != null && lst.size()>0 ){
		        	 
		        	 str += "<ol>";
		        	 str += "<li>FECHA</li>";
		        	 str += "<li>CAPITAL</li>";
		        	 str += "<li>INTERÉS</li>";
		        	 str += "<li>RETENCIÓN <br /> DE ISR</li>";
		        	 str += "<li>TOTAL</li>";
		        	 str += "<li>ESTATUS</li>";
		        	 str += "</ol>";
		        	 
		        	 for( AmortizacionInversionista amort : lst ){
		        		 
		        		 Double total = 0D;
		        		 
		        		 str += "<ul>";
		        		 	str += "<li><sub>Fecha</sub>"+formatStr1.format(amort.getFechaExigible())+"</li>";
		        		 	str += "<li><sub>Capital</sub>\\"+dec.format( amort.getCapital() )+
		        		 			"</li>";
		        		 	
		        		 	sumCap += amort.getCapital();
		        		 	
		        		 	str += "<li><sub>Interés</sub>\\"+dec.format( amort.getInteresGenerado() )+
		        		 			"</li>";
		        		 	
		        		 	sumInt += amort.getInteresGenerado();
		        		 	
		        		 	str += "<li><sub>Retención de ISR </sub>\\"+dec.format( amort.getInteresRetener() )+
		        		 			"</li>";
		        		 	
		        		 	sumISR += amort.getInteresRetener();
		        		 
		        		 	total = (amort.getCapital() + amort.getInteresGenerado() ) - amort.getInteresRetener();
		        		 	
		        		 	tot += total;
		        		 	
		        		 	str += "<li><sub>Total </sub>\\"+ dec.format( total ) +"</li>";
		        		 
		        		 	str += "<li><sub>EStatus</sub>\\"+ parseStatus( amort.getEstatus(), amort.getFechaExigible()) +"</li>";
		        		 	
		        		 	
		        		 	
		        		 str += "</ul>";
		        		 
		        		 count++;
		        		 
		        	 }
		        	 
		         }
		         
		         ticketStr = ticketStr.replaceAll("###DETALLE###", str );
		         
		         ticketStr = ticketStr.replaceAll("###SUMCAPITAL###", "\\"+dec.format(sumCap) );
		         ticketStr = ticketStr.replaceAll("###SUMINTERES###", "\\"+ dec.format(sumInt) );
		         ticketStr = ticketStr.replaceAll("###SUMISR###", "\\"+ dec.format(sumISR) );
		         
		         ticketStr = ticketStr.replaceAll("###TOTAL_PAGADO###", "\\"+dec.format( tot ) );
		         ticketStr = ticketStr.replaceAll("###NUM_PAGOS###", count+"");
		         ticketStr = ticketStr.replaceAll("###MONTOGARANTIA###", "\\"+ dec.format(active.getMontoFondeo())+"" );
		         
		         Double d = (Math.round(active.getPorcentajeFondeo() * 1000)) / 1000D;
		         
		         ticketStr = ticketStr.replaceAll("###PORCENTAJE_PARTICIPA###", ""+d+"%" );
		         
		         //String sello = ""+active.getPerson().getSafi_client_id()+"|"+active.getFondeokuboid()+"|"+formatStr.format(active.getFechaInicioInv())+"|"+formatStr.format(active.getFechaVencimientoInv())+"|"+active.getMontofondeoinv()+"|"+d;
					
		         String sello = "" + active.getPerson().getSafi_client_id() + "|";
				 sello += active.getCuentaAhoID()    + "|";
				 sello += active.getFondeoKuboID()   + "|";
				 sello += active.getFondeoKuboID() + "|";
				 sello += formatStr.format(active.getFechaInicio()) + "|";
				 sello += active.getAmmount() + "|";
				 sello += active.getMontoFondeo() + "|";
				 sello += d;
		         
				ticketStr = ticketStr.replaceAll("###SELLO_DIGITAL###", sello );
			}
			valuesforticket = null;
	}
	
	
	public void getTicket()
	{
		////System.out.println("Generando ticket..............");

			if (valuesforticket != null)
			{ 
				////System.out.println(valuesforticket);
				
				ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
				//Integer proyect_loan_id		= Integer.parseInt( valuesforticket.split("::")[0] );
				Integer kuboFondeoInv_id	= Integer.parseInt( valuesforticket.split("::")[1] );
				
				InvestmentDetail active = servicioProyecto.getInvestmentDetailByID(kuboFondeoInv_id);
				
				ticketStr = getHtml("ticket");
				
				if(getSesion().getArea().toString().equals("I")){
					
					ticketStr=ticketStr.replace("###NOMBRE###",getPersona().NombreCompletoNPM());
					ticketStr=ticketStr.replace("###RFC###", getPersona().getMx_rfc());
					ticketStr=ticketStr.replace("###CLIENTE###", getPersona().getSafi_client_id());
				
				}else if(getSesion().getArea().toString().equals("M")){
					
					ticketStr=ticketStr.replace("###NOMBRE###", active.getPerson().NombreCompletoNPM());
					ticketStr=ticketStr.replace("###RFC###", active.getPerson().getMx_rfc());
					ticketStr=ticketStr.replace("###CLIENTE###", active.getPerson().getSafi_client_id());
				
				
				}else{
					
					ticketStr=ticketStr.replace("###NOMBRE###", "NO DISPONIBLE");
					ticketStr=ticketStr.replace("###RFC###", "NO DISPONIBLE");
					
				}
				
				
				ticketStr=ticketStr.replace("###NUMCREDITO###", active.getFondeoKuboID()+"");
				
				ticketStr=ticketStr.replace("###FECHAINICIO###", formatStr5.format(active.getFechaInicio()));
				
				ticketStr=ticketStr.replace("###FECHAFIN###", formatStr5.format( active.getFechaVencimiento()));
				
				ticketStr=ticketStr.replace("###MONTOINV###", dec.format(active.getMontoFondeo())+"");
				
				ticketStr=ticketStr.replace("###TASA_INV###", active.getRate_investor()+"%");
				
				ticketStr=ticketStr.replace("###TASA_MORATORIA###", (active.getRate_investor() * 2)+"%");
				
				ticketStr=ticketStr.replace("###RIESGO_KUBO###", active.getKubo_score_a()+active.getKubo_score_b()+"");
				
				ticketStr=ticketStr.replace("###FREQ###", active.getFrequency().getName()+"");
				
				ticketStr=ticketStr.replace("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofipo")+"");
				
				ticketStr=ticketStr.replace("###RFC_KUBO###", recurso.getString( "rfc_kubo_sofipo" )+"");
				
				ticketStr=ticketStr.replace("###TELEFONO###", recurso.getString("Kubo_telefono")+"");
				
				ticketStr=ticketStr.replace("###FECHA_PAGO###",formatStr5.format(active.getFechaInicio())+"");
				
				ticketStr=ticketStr.replace("###CUENTA###",active.getCuentaAhoID()+"" );
				
				ticketStr=ticketStr.replace("###GAT_NOMINAL###",active.getGatNominal()+"" );
				
				ticketStr=ticketStr.replace("###GAT_REAL###",active.getGatReal()+"" );
				
				String sTotal = (active.getMontoFondeo()+"");
				String centavos ="";
				String pesos = sTotal.split("\\.")[0];
		         
		         if(sTotal.split("\\.").length > 1)
		        	 centavos = sTotal.split("\\.")[1];
		         
		         if(centavos.length()<2){
		        	 centavos += "0";
		         }
		         
		         String letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
		         
		         ticketStr = ticketStr.replaceAll("###IMPORTE_LETRA###", letter );
				
		         ticketStr = ticketStr.replaceAll("###CENTAVOS###", centavos );
		         
		         List<AmortizacionInversionista> lst = amortInverService.getAmortizacionInversionistaListByIdInver( active.getFondeoKuboID().intValue());
		         
		         String str = "";
		         Double tot = 0D;
		         int count = 0;
		         
		         if(lst != null && lst.size()>0 ){
		        	 
		        	 str += "<ol>";
		        	 str += "<li>FECHA</li>";
		        	 str += "<li>CAPITAL</li>";
		        	 str += "<li>INTERÉS</li>";
		        	 str += "<li>RETENCIÓN <br /> DE ISR</li>";
		        	 str += "<li>TOTAL</li>";
		        	 str += "</ol>";
		        	 
		        	 for( AmortizacionInversionista amort : lst ){
		        		 Double total = 0D;
		        		 str += "<ul>";
		        		 	str += "<li><sub>Fecha</sub>"+formatStr1.format(amort.getFechaExigible())+"</li>";
		        		 	str += "<li><sub>Capital</sub>\\"+dec.format( amort.getCapital() )+
		        		 			"</li>";
		        		 	str += "<li><sub>Interés</sub>\\"+dec.format( amort.getInteresGenerado() )+
		        		 			"</li>";
		        		 	str += "<li><sub>Retención de ISR</sub>\\"+dec.format( amort.getInteresRetener() )+
		        		 			"</li>";
		        		 
		        		 	total = (amort.getCapital() + amort.getInteresGenerado() ) - amort.getInteresRetener();
		        		 	
		        		 	tot += total;
		        		 	
		        		 	str += "<li><sub>Total</sub>\\"+ dec.format( total ) +"</li>";
		        		 
		        		 str += "</ul>";
		        		 
		        		 count++;
		        		 
		        	 }
		        	 
		         }
		         
		        ticketStr = ticketStr.replaceAll("###DETALLE###", str );
		        ticketStr = ticketStr.replaceAll("###TOTAL_PAGADO###", "\\"+dec.format( tot ) );
		        ticketStr=ticketStr.replace("###NUM_PAGOS###", count+"");
		         
		         
		        String sello = ""+active.getPerson().getSafi_client_id()+"|"+active.getFondeoKuboID()+"|"+formatStr.format(active.getFechaInicio())+"|"+formatStr.format(active.getFechaVencimiento())+"|"+active.getMontoFondeo()+"|"+active.getRate_investor()+"|"+active.getKubo_score_a()+active.getKubo_score_b()+"|"+count+"|"+active.getFrequency_id();
					
				ticketStr = ticketStr.replaceAll("###SELLO_DIGITAL###", sello );
		         
			}
			
			valuesforticket = null;
			
	}
		
	public void getGarantia()
	{
		////System.out.println("Generando garantía..............");
			
		if (valuesforGarantia != null)
		{ 
			//System.out.println(valuesforGarantia);
				
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
			proyect_loan_id  = Integer.parseInt( valuesforGarantia.split("::")[0] );
			kuboFondeoInv_id = Integer.parseInt( valuesforGarantia.split("::")[1] );
		
			active = servicioProyecto.getProyectLoanActiveInSafiByID(kuboFondeoInv_id,proyect_loan_id);
				
			ticketStr = getHtml("Garantia");

//			if(getSesion().getArea().toString().equals("M"))
//			{
			
//				ticketStr=ticketStr.replace("###NOMBRE###", active.getPerson().NombreCompletoNPM());
//				ticketStr=ticketStr.replace("###RFC###", active.getPerson().getMx_rfc());
			
//			} else {
//					
//				ticketStr=ticketStr.replace("###NOMBRE###", "NO DISPONIBLE");
//				ticketStr=ticketStr.replace("###RFC###", "NO DISPONIBLE");
//					
//			}
				
			if(getSesion().getArea().toString().equals("I"))
			{
				
				ticketStr=ticketStr.replace("###NOMBRE###",getPersona().NombreCompletoNPM());
				ticketStr=ticketStr.replace("###RFC###", getPersona().getMx_rfc());
				ticketStr=ticketStr.replace("###CLIENTE###", getPersona().getSafi_client_id());
			
			}else if(getSesion().getArea().toString().equals("M")){
				
				ticketStr=ticketStr.replace("###NOMBRE###", active.getPerson().NombreCompletoNPM());
				ticketStr=ticketStr.replace("###RFC###", active.getPerson().getMx_rfc());
				ticketStr=ticketStr.replace("###CLIENTE###", active.getPerson().getSafi_client_id());				
			
			}else{
				
				ticketStr=ticketStr.replace("###NOMBRE###", "NO DISPONIBLE");
				ticketStr=ticketStr.replace("###RFC###", "NO DISPONIBLE");					
			}
						
			//ticketStr=ticketStr.replace("###CLIENTE###", active.getPerson().getSafi_client_id());
			ticketStr=ticketStr.replace("###NUMCREDITO###", active.getSafi_credit_id()+"");
			ticketStr=ticketStr.replace("###NUM_INVER###", active.getFondeokuboid()+"");
			
			ticketStr=ticketStr.replace("###CUENTA###",active.getCuentaAhoID()+"" );
							
			Double porcentaje = Math.rint(active.getPorcentajefondeo()*100)/100;
				
			ticketStr = ticketStr.replace("###PORCENTAJE###",   porcentaje+"%" );				
			ticketStr = ticketStr.replace("###FECHAINICIO###",  formatStr5.format(active.getFechaInicioInv()));				
			ticketStr = ticketStr.replace("###FECHAFIN###",     formatStr5.format( active.getFechaVencimientoInv()));				
			ticketStr = ticketStr.replace("###MONTOINV###",     dec.format(active.getMontofondeoinv())+"");				
			ticketStr = ticketStr.replace("###TASA_INV###",     active.getRate_investor()+"%");				
			ticketStr = ticketStr.replace("###RIESGO_KUBO###",  active.getKubo_score_a()+active.getKubo_score_b()+"");				
			ticketStr = ticketStr.replace("###FREQ###",         active.getFrequency().getName()+"");				
			ticketStr = ticketStr.replace("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofipo")+"");				
			ticketStr = ticketStr.replace("###RFC_KUBO###",     recurso.getString( "rfc_kubo_sofipo" )+"");				
			ticketStr = ticketStr.replace("###TELEFONO###",     recurso.getString("Kubo_telefono")+"");				
			ticketStr = ticketStr.replace("###FECHA_PAGO###",   formatStr5.format(active.getFechaInicioInv())+"");
				
			String sTotal = (active.getMontofondeoinv()+"");
			String centavos ="";
			String pesos = sTotal.split("\\.")[0];
		         
			if(sTotal.split("\\.").length > 1)
			{
				centavos = sTotal.split("\\.")[1];
			}
			 
			 if(centavos.length()<2)
			 {
				 centavos += "0";
			 }
			         
	         String letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
		         
	         ticketStr = ticketStr.replaceAll("###IMPORTE_LETRA###", letter );				
	         ticketStr = ticketStr.replaceAll("###CENTAVOS###", centavos );
	         
	         sTotal = (active.getAmmount()+"");
			 centavos ="";
			 pesos = sTotal.split("\\.")[0];
			         
	         if(sTotal.split("\\.").length > 1)
	         {
	        	 centavos = sTotal.split("\\.")[1];
	         }
	         
	         if(centavos.length()<2)
	         {
	        	 centavos += "0";
	         }
			         
	         letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
	         
	         ticketStr = ticketStr.replaceAll("###IMPORTE_CREDITO_LETRA###", letter );			
	         ticketStr = ticketStr.replaceAll("###CENTAVOS_CREDITO###", centavos );	         
	         ticketStr = ticketStr.replaceAll("###MONTO_CRED###","\\"+ dec.format( active.getAmmount() ) );
		         
	         lst = amortInverService.getAmortizacionInversionistaListByIdInver( active.getFondeokuboid());
		         
	         detalle = new StringBuilder();	         
	         Double total_capital   = 0D;
	         Double total_interes   = 0D;
	         Double total_retencion = 0D;
	         Double total           = 0D;
	         int count = 0;
		         
	         if(lst != null && lst.size() > 0)
	         {		        	 
	        	 detalle.append("<ol>");
	        	 detalle.append("<li>").append("FECHA").append("</li>");
	        	 detalle.append("<li>").append("CAPITAL").append("</li>");
	        	 detalle.append("<li>").append("INTERÉS").append("</li>");
	        	 detalle.append("<li>").append("RETENCIÓN <br /> DE ISR").append("</li>");
	        	 detalle.append("<li>").append("TOTAL").append("</li>");
	        	 detalle.append("</ol>");
		        	 
	        	 for( AmortizacionInversionista amort : lst )
	        	 {
	        		 String fecha_exigible = formatStr1.format(amort.getFechaExigible());
	        		 
	        		 Double total_pagos = 0D;
	        		 
	        		 Double capital   = amort.getCapital();
	        		 Double interes   = amort.getInteresGenerado();
	        		 Double retencion = amort.getInteresRetener();
	        		 
	        		 detalle.append("<ul>");
	        		 detalle.append("<li><sub>Fecha</sub> " ).append(fecha_exigible).append("</li>");
	        		 detalle.append("<li><sub>Capital</sub>\\").append(dec.format(capital)).append("</li>");
	        		 detalle.append("<li><sub>Interes</sub>\\").append(dec.format(interes)).append("</li>");
	        		 detalle.append("<li><sub>Retención</sub>\\").append(dec.format(retencion )).append("</li>");
	        		 
	        		 total_capital   += capital;
	        		 total_interes   += interes;
	        		 total_retencion += retencion;
	        		 
	        		 total_pagos = (capital + interes ) - retencion;
	        		 	
	        		 total += total_pagos;
	        		 	
	        		 detalle.append("<li><sub>Total</sub>\\").append(dec.format(total_pagos)).append("</li>");	        		 
	        		 detalle.append("</ul>");
	        		 
	        		 count++;	        		 
	        	 }		        	
	         }
		         
	         ticketStr = ticketStr.replaceAll("###DETALLE###", detalle.toString());	         
	         ticketStr = ticketStr.replaceAll("###TOTAL_CAPITAL###", "\\"   + dec.format(total_capital));
	         ticketStr = ticketStr.replaceAll("###TOTAL_INTERES###", "\\"   + dec.format(total_interes));
	         ticketStr = ticketStr.replaceAll("###TOTAL_RETENCION###", "\\" + dec.format(total_retencion));
	         ticketStr = ticketStr.replaceAll("###TOTAL_PAGADO###", "\\"    + dec.format(total));
	         ticketStr = ticketStr.replace("###NUM_PAGOS###", count+"");
		         		        
	         String sello = "" + active.getPerson().getSafi_client_id() + "|";
			 sello += active.getCuentaAhoID()    + "|";
			 sello += active.getFondeokuboid()   + "|";
			 sello += active.getSafi_credit_id() + "|";
			 sello += formatStr.format(active.getFechaInicioInv()) + "|";
			 sello += active.getAmmount() + "|";
			 sello += active.getMontofondeoinv() + "|";
			 sello += porcentaje;
					
			ticketStr = ticketStr.replaceAll("###SELLO_DIGITAL###", sello );      
		}

		valuesforticket = null;
	}

	@SuppressWarnings("resource")
	private String getHtml(String type)
	{
		
		String res = "";
		
		try
		{
			File archivo = null; 
			
			  FileReader fr = null;
			  BufferedReader fileBr = null;
			  StringBuilder sb = new StringBuilder();
			  
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsf/docs/");
			  //String path = new File("Escritorio/Desarrollo_Kubo/Kubodb/WebContent/jsf/docs").getAbsolutePath();
			  
			  if(type.equals("ticket"))
			  {			  
				  path += "/ticketInv.html";
				  
			  }else if( type.equals("Garantia") ){
				  
				  path += "/comprobante_garantia.html";
				  
			  }else if( type.equals("ticketPagos") ){
				  
				  path += "/comprobante_pagos_inv.html";
				  
			  }
			  
			  //////System.out.println(path);
			  
			  archivo = new File (path);
			  fr = new FileReader (archivo);
			  
			  fileBr = new BufferedReader(fr);
			
			    // Lectura del fichero
			String linea;
			
			while((linea=fileBr.readLine())!=null){
				
				sb.append(linea);
				
			}
		
			res= sb.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public final void initSearch(ActionEvent evento_AJAX)
	{
		String proyect_loan_SEARCH_TOKEN = (String) evento_AJAX.getComponent().getAttributes().get("proyectAtrr").toString();
		
		RequestContext 	request   = RequestContext.getCurrentInstance();		
		FacesContext 	faces     = FacesContext.getCurrentInstance();
		
		ELContext elContext = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
		
		Calendar cal1;
		
		if (sesion.getArea().equals('M')) 
		{		
			cal1 = Calendar.getInstance();			
			cal1.setTime(new Date());									
			
			MenuControlTableBean control_table = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
			
			evento_AJAX.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
			
			control_table.cambiaPagina(evento_AJAX);
			
			SearchSummaySession summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
						
			summarysesion.setPerson(false);			
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);			
			summarysesion.setTypeLog("EVA");
			
			SummaryRequest summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");
			
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			
//			long milis1 = cal1.getTimeInMillis();
//			long milis2 = cal2.getTimeInMillis();
			
			// calcular la diferencia en milisengundos
			
			//long diff = milis2 - milis1;
			
			//long diffSeconds = diff / 1000;
			
			//System.out.println( "tardó: "+diff+" milisegundos");
			
			cal1.setTime(new Date());
			
			if( sesion.getArea().toString().equals("I") )
			{
				NavigationInvest nav = (NavigationInvest) resolver.getValue(elContext, null, "navigationInvest");
				
				nav.setFlagDispEdoCuenta(true);
			}
			
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			if(sesion.getArea().toString().equals("I")){
				
				NavigationInvest nav = (NavigationInvest) resolver.getValue(elContext, null, "navigationInvest");
				
				nav.setFlagDispEdoCuenta(true);
			}
			
			cal2.setTime(new Date());
			
//			milis1 = cal1.getTimeInMillis();
//			milis2 = cal2.getTimeInMillis();
			
			// calcular la diferencia en milisengundos
			
			//diff = milis2 - milis1;
			
			//diffSeconds = diff / 1000;
			
			//System.out.println( " tardó: " + diff + " milisegundos en cargar la sabana");
			
			request.addPartialUpdateTarget("form_Prin");
			request.addPartialUpdateTarget("actualPage");
			request.addPartialUpdateTarget("pnlCancel");
			
//			validaIsCancel( summarysesion.getSearchSummary() );
			
		} else if (sesion.getArea().equals('I')) {
			
			SearchSummaySession summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setTypeLog("SOL");
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
			
			summarysesion.setShowInvestPnl(false);
			
			SummaryRequest summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");			
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			NavigationInvest navigationinvest = (NavigationInvest) resolver.getValue(elContext, null, "navigationInvest");
			
			evento_AJAX.getComponent().getAttributes().put("seccionInv", "summary");
			
			navigationinvest.changePage(evento_AJAX);
			
			navigationinvest.setFlagDispEdoCuenta(true);
			
			request = RequestContext.getCurrentInstance();
			request.addPartialUpdateTarget("pnlContentInvest");	
			request.addPartialUpdateTarget("panelContentListProyect");
			request.addPartialUpdateTarget("actualPage");
			
		} else {	
			
			SearchSummaySession summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
//			
//			navigation = "resumen";
		}
	}
	
//	public String actionNavLogs(){
//		return getLogs_navegation();
//	}

	public void goToLogs(ActionEvent e)
	{
		String value=(String) e.getComponent().getAttributes().get("proyectAtrr").toString();	
		String valueLog=(String) e.getComponent().getAttributes().get("gotypeLog").toString();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		if (sesion.getArea().equals('M')) 
		{			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			
			MenuControlTableBean navMenu= (MenuControlTableBean) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "menuControlTableBean");			
			
			e.getComponent().getAttributes().put("section", "controlTable/Logs::16::menu4");
			navMenu.cambiaPagina(e);
			
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
			
			summarysesion.setSearchSummary(null);
			summarysesion.setSearchSummary(value);			
			summarysesion.setTypeLog(valueLog);
			
			requestContext.addPartialUpdateTarget("form_Prin");	
			requestContext.addPartialUpdateTarget("actualPage");
			
		}else if (sesion.getArea().equals('I')) {
			
				SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
				
				summarysesion.setSearchSummary(null);
				summarysesion.setSearchSummary(value);
				summarysesion.setTypeLog(valueLog);
				
				NavigationInvest navigationinvest = (NavigationInvest) FacesContext.getCurrentInstance()
		                .getApplication().getELResolver()
		                .getValue(elContext, null, "navigationInvest");
				
				e.getComponent().getAttributes().put("seccionInv", "logs");
				
				navigationinvest.changePage(e);
				
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.addPartialUpdateTarget("pnlContentInvest");	
				requestContext.addPartialUpdateTarget("panelContentListProyect");
				
		}
	}
	
	public String parseStatus( String status , Date fechaExigible ){
		String res = "";
		
		if(status == null)
		{
			res = "";
		
		}else if ( status.equals("P") ){
		
			res = "PAGADA";
			
		}else if (status.equals("V") || status.equals("PA_V") || status.equals("N") ){
			
			Calendar cE = Calendar.getInstance();
			
			cE.setTime(fechaExigible);
			
			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			
			c1.set(Calendar.HOUR, cE.get(Calendar.HOUR) );
			c1.set(Calendar.MINUTE, cE.get(Calendar.MINUTE) );
			c1.set(Calendar.SECOND, cE.get(Calendar.SECOND) );
			c1.set(Calendar.MILLISECOND, cE.get(Calendar.MILLISECOND) );
			
			if( fechaExigible.after( c1.getTime() ) ){
				res = "VIGENTE";
			}else{
				res = "ATRASADA";
			}
			
		}else if(status.equals("A") || equals("PA_A") ){
			
			res = "ATRASADA";
			
		}else if( status.equals("I") ){
			
			res = "INACTIVA";
			
		}else if( status.equals("B") || status.equals("PA_B") ){
			
			res = "VENCIDA";
			
		}else{
			
			res = status;
			
		}
		
		return res;
	}
	
	public void getInvestmentsbyLink(){
		
		if(status_delinquentinv == 'C'){
			
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
			
		}else if(status_delinquentinv == 'I'){
			
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
	
	public void cambiaVistaInv(){

		if(status_delinquentinv == 'C'){
			status_delinquentinv	= 'I';
		}else{
			status_delinquentinv   = 'C';
		}
		
		getInvestmentsbyLink();
		
	}
	
	private boolean isSesion_DISABLED( ExternalContext external )
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath( external ) + "/Portal/sesion-expirada.xhtml?redirecFrom=MyInvestments");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				  external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private void insertInfoNotification(){
		
			
			InfoNotification info = new InfoNotification();
			
			info.setCompany_id(persona.getNatPerPK().getCompany_id());
			info.setInfo_screen_id(NOTIFICATION_CONFIGURATION_SCREEN);
			info.setProspectus_id(persona.getNatPerPK().getProspectus_id());
			info.setProyect_loan_id(null);
			info.setViewed_date(new Date());
			
			infonotificationservice.saveInfoNotificationBy(info);
			
	}
	
	private String getPath( ExternalContext external )
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
}