package mx.com.kubo.controller.inversion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemInversion;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.controller.ThreadInvestmentAction;
import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.InvestorParamPK;
import mx.com.kubo.model.MontoInvertido_F_G_Collector;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

/**
 * 
 * Genera Inversión de proyectos
 * 
 * @author Rodrigo Martinez Balderas
 * 
 *
 */

public class Inversion extends InversionPMO {
	
	public Inversion(){
	
		try{
			
			lstService 				= new ArrayList<ServiceCalling>();
			storedString 			= new ArrayList<String>();
			listToInv				= new ArrayList<ItemInversion>();
			lstProyectosFondeados	= new ArrayList<ProyectFunding>();
			lstProyectosFondeados	= new ArrayList<ProyectFunding>();
			
			montoinvertido 			= 0D;
			montoNOinvertido 		= 0D;
			tasaPonderada			= 0D;
			
			proyectos 				= 0;
			porcentaje 				= 0;
			proyectosNoFondeados 	= 0;
			
			scriptStatus			= "";
			
			locatorInvKuboSafi = new SAFIServiciosServiceLocator();
			servicioInvKuboSafi = locatorInvKuboSafi.getSAFIServiciosSoap11();
			
			locatorAccount = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			servCuentasCliente = locatorAccount.getSAFIServiciosSoap11();
			
			saldoinversionistaservice = Utilities.findBean("saldoInversionistaServiceImp");
			proyectLoanService		  = Utilities.findBean("proyectLoanServiceImp");
			tiendacreditosservice 	  = Utilities.findBean("tiendaCreditosServiceImp");
			proyectFundingService	  = Utilities.findBean("proyectFundingServiceImp");
			systemparamservice	  	  = Utilities.findBean("systemParamServiceImp");
			profile_inv_service	  	  = Utilities.findBean("profileInvServiceImp");
			montoInvertido_F_G_service= Utilities.findBean("montoInvertido_F_G_CollectorServiceImp");
			profileformvalueservice	  = Utilities.findBean("profileFormValueServiceImp");
			proyectInfoService		  = Utilities.findBean("proyectInfoServiceImp");
			savingaccountservice	  = Utilities.findBean("savingAccountServiceImp");
			investorparamservice	  = Utilities.findBean("investorParamServiceImp");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	/**
	 * Para ejecutar este método antes se deben setear las siguientes variables:
	 * <br>
	 * <b>proyectList: Lista de proyectos a invertir
	 * <br><b>investor_id:</b> 
	 * prospectus_id del inversionista
	 * <br><b>account:</b> 
	 * safi_account_id del inversionista
	 * <br><b>SafiClientId:</b> 
	 * safiClientId del inversionista 
	 * 
	 * 
	 */
	public void ejecutaInvestment(){
		
		
		
		lstService 				= new ArrayList<ServiceCalling>();
		storedString 			= new  ArrayList<String>();
		montoinvertido 			= 0D;
		montoNOinvertido 		= 0D;
		proyectos 				= 0;
		porcentaje 				= 0;
		proyectosNoFondeados 	= 0;
		listToInv				= new ArrayList<ItemInversion>();
		
		
		for( ItemLoanList item : proyectList ){
			
			if(item.getInvestment_bite() > 0)
		    {
			
				String solicitudID 	= null;
			    String creditoID 	= null;
			    
			    String fundingType 	= null;
			    
			    if(item.getSafi_credit_id() != null){
			    	
			    	creditoID 		= item.getSafi_credit_id();
			    	solicitudID 	= "0";
			    	fundingType 	= "C";
			    	
			    }else{
			    	
			    	solicitudID 	= item.getSafi_solicitud_id()+"";
			    	creditoID 		= "0";
			    	fundingType 	= "S";
			    	
			    }
				
			    initServiceCalling( item.getCompany_id() , ( Integer.parseInt( investor_id ) ) , "C: "+creditoID+" S: "+solicitudID, (item.getInvestment_bite()+"") );
			    
				String[] res = storeInvestmentInWS( solicitudID, creditoID, getAccount(), getSafiClientId(), item.getRate_investor(), item.getInvestment_bite(), fundingType, item.getCompany_id(), item.getProspectus_id(),item);
				
				if(res[0].equals("0") ||  res[0].equals("00") || res[0].equals("000")){
			    	
			    	Calendar cal1 = Calendar.getInstance();
			    	cal1.setTime(new Date());
				    
			    	String str = item.getProyect_loan_id() +"::"+ item.getProyect_id() +"::"+ item.getCompany_id() +"::"+ item.getProspectus_id() +"::"+ investor_id +"::"+ item.getInvestment_bite()+"::"+res[1];
			    	
			    	System.out.println( "Ineversion ejecutada: " + str );
			    	
			    	storedString.add(str);
				   
				}
				
		    }else{
		    	
		    	proyectosNoFondeados++;
		    	
		    	ItemInversion inv = new ItemInversion();
				
				inv.setAmmount( item.getInvestment_bite() );
				inv.setKubo_score_a( item.getKubo_score_a() );
				inv.setKubo_score_b( item.getKubo_score_b() );
				inv.setTasa(item.getRate_investor()+"");
				inv.setMotivo("El monto para fondear este proyecto es de $0.00");
				inv.setProyect_loan_id(item.getProyect_loan_id());
				inv.setStatus("0");
				
				listToInv.add( inv );
				
				InvestmentProgressDet progress = new InvestmentProgressDet();
				
				progress.setProspectus_investor_id( Integer.parseInt( investor_id ) );
				
				if( investmentType != null && investmentType.equals("Automática") ){
				
					progress.setAutomatic_investment_id( investmentprogress.getAutomatic_investment_id() );
					progress.setFondeo_kubo_id(null);
					progress.setInvestment_date( investmentprogress.getInvestment_date() );
				
				}
				
				Date dT = new Date();
				
				progress.setInvestment_bite(item.getInvestment_bite());
				progress.setInvestment_execute_date(dT);
				progress.setKubo_score(item.getKubo_score_a()+item.getKubo_score_b());
				progress.setMessage( "El monto para fondear este proyecto es de $0.00" );
				
				progress.setProyect_loan_id(item.getProyect_loan_id());
				progress.setSafi_credit_id(item.getSafi_credit_id());
				progress.setSafi_mx_solicitud_id(item.getSafi_solicitud_id());
				progress.setStatus_progress_det(2);
				progress.setInvestment_type(investmentType);
				
				progressdetlst.add( progress );
		    	
		    }
			
		}
		
		ejecutaSPRecargaListaClienteCredito(  getSafiClientId() );
		
		procesaInversiones();
		
	}

	public void inicializaSaldos( List<String> safiCuentas ){
		
		try
		{
			saldoTotal = 0D;
			
			Calendar cargaServ_CuentasT_1 = Calendar.getInstance();
			cargaServ_CuentasT_1.setTime(new Date());
			
			ConsultaCuentasPorClienteResponse resCliente = servCuentasCliente.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest(safiCuentas.get(0).toString()));

			
			 if(resCliente.getInfocuenta()!=null && resCliente.getCodigoRespuesta()!=null && resCliente.getCodigoRespuesta()[0].equals("0"))
	            {
	                String respuestas = resCliente.getInfocuenta()[0];                   
	                String[] cuentas = respuestas.split("\\&\\|\\&");
	                listInvAccounts=new ArrayList<InvestorsAccounts>();
	                InvestorsAccounts invsAccts=null;
	                		
	                for (int i = 0; i < cuentas.length; i++) {
	                	
	                	String[] vars = cuentas[i].split("\\&\\;\\&",3);
	                	
	                	if(vars.length==3)
	                	{
	                		invsAccts=new InvestorsAccounts();
	                		invsAccts.setAccount(vars[0]);
	                		invsAccts.setAccountName(vars[1]);
	                		invsAccts.setSaldo(vars[2].equals("null")?0.00:Double.parseDouble(vars[2]));
	                		listInvAccounts.add(invsAccts);
	                	}             			                	
					}
	            }
	            
			 
			Calendar cargaServ_cuenta_2 = Calendar.getInstance();
			cargaServ_cuenta_2.setTime(new Date());
			
			//long difCL1 = cargaServ_cuenta_2.getTimeInMillis() - cargaServ_CuentasT_1.getTimeInMillis();
			
			//System.out.println("Tiempo en Consultar WS Cuentas: "+difCL1+" milisegundos");
			 
			Calendar cargaServ_Saldo_1 = Calendar.getInstance();
			cargaServ_Saldo_1.setTime(new Date());
//			
			SaldoInversionista saldoObj = saldoinversionistaservice.getSaldoByAccount(listInvAccounts.get(0).getAccount());
			
			if(saldoObj != null){
				saldoTotal = saldoObj.getSaldoTotal();
			}else{
				saldoTotal = 0D;
			}
//			
			//System.out.println("SALDO TOTAL: "+saldoTotal );
			
			Calendar cargaServ_Saldo_2 = Calendar.getInstance();
			cargaServ_Saldo_2.setTime(new Date());
			
			//long difSaldo1 = cargaServ_Saldo_2.getTimeInMillis() - cargaServ_Saldo_1.getTimeInMillis();
			
			//System.out.println("Tiempo en Consultar WS Saldos: "+difSaldo1+" milisegundos");
			
			
		}catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
	}

	public void cargaListaTienda( String query, int prospectusInvestor_id, int companyInvestor_id, String flagRisk,String safi_investor_client_id, String safi_account_id ){
		
		String ultimoFiltro  	= query;
		String concatRiskCad   	= "";
		String concatAmmountCad	= "";
		//String concatTermCad	= "";
		
		filter = new FilterStore();
		
		filter.setClienteID(safi_investor_client_id);
		filter.setCuentaAhoID(safi_account_id);
		
		System.out.println( "flagRisk: " + flagRisk );
    	
    	String[] filtros = ultimoFiltro.split("\\|\\|");
    	
    	// PLAZO
    	
    	String termCad = filtros[0] ;
    	
    	termCad = termCad.split("_")[0];
    	
    	termCad = termCad.split("T")[1];
    	
    	Integer intTmp = 0;
    	
    	if( !termCad.equals("12") && !termCad.equals("24") && !termCad.equals("36") ){
    		
    		intTmp = Integer.parseInt( termCad );
    		
    		if ( intTmp > 12 &&  intTmp < 24 ){
    			
    			intTmp = ( 365 ) + ( 30 * (intTmp-12) );
    			
    		}else if ( intTmp > 24 &&  intTmp < 36 ) {
    			
    			intTmp = ( 365*2 ) + ( 30 * (intTmp-24) );
    			
    		}else{
    			intTmp = intTmp * 30;
    		}
    		
    	}else{
    		
    		if(termCad.equals("12")){
    			intTmp = 365;
    		}else if(termCad.equals("24")){
    			intTmp = 365*2;
    		}else if(termCad.equals("36")){
    			intTmp = 365*5;
    		}
    		
    	}
    	
    	//termCad = intTmp.toString();
    	
    	filter.setDiasPorTrans(intTmp +"");
    	
    	//String concatGenderCad = "";
    	
    	String riskCad = filtros[1];
    	
    	riskCad = riskCad.substring( ("risk:").length() );
    	
    	/*if(!riskCad.equals(""))
    	{
    		if( flagRisk == null || flagRisk.equals("0") ){
    			
    			concatRiskCad = "l.kubo_score_a in ("+riskCad+")";
    		
    		}else{
    			concatRiskCad = "CONCAT( l.kubo_score_a,l.kubo_score_b ) in ("+riskCad+")";
    		}
    		
    	}  */
    	
    	filter.setKuboScore(riskCad);
    	
    	String genderCad = "";
    	boolean flagGender = false;
    	String genderCadTemp = filtros[2] ;
    	
    	genderCadTemp = genderCadTemp.substring( ("gender:").length() );
    	
    	
    	if(genderCadTemp != null && genderCadTemp.trim().length()>0 ){
    		
    		if(genderCadTemp.indexOf("H") != (-1) ){
    			genderCad = "1";
    			flagGender = true;
    		}
    		
    		if(genderCadTemp.indexOf("M") != (-1) ){
    			if(flagGender){
    				genderCad += ",";
    			}
    			
    			genderCad += "2";
    			
    		}
    		
    	}
    	
    	filter.setGenderID( genderCad );
    	
    	/*if( genderCad !=null && genderCad.trim().length()>0){
    		if(concatRiskCad.trim().length()>0)
    			concatGenderCad = " and ";
    		
    		concatGenderCad += "per.gender_id in ("+genderCad+")";
    		
    	}*/
    	
    	//FONDEO PREVIO
    
    	String fondPrev = filtros[3];
    	
    	typeSearch = Integer.parseInt( fondPrev.substring( ("typeSearch:").length() ) ); 
    	
    	/*
    	
    	1" itemLabel="Fondeados previamente" />
		2" itemLabel="Sin mi fondeo previo" />
		0" itemLabel="Todos" />
    	
    	*/
    	
    	
    	if( typeSearch == 0 ){
    		
    		filter.setTipoFondeo("T");
    		
    	}else if( typeSearch == 1 ){
    		
    		filter.setTipoFondeo("C");
    		
    	}else if( typeSearch == 2 ){
    		
    		filter.setTipoFondeo("S");
    		
    	}
    	 
    	//Fin FONDEO PREVIO
    	
    	String tmpstr = filtros[4] ;
    	
    	tmpstr = tmpstr.substring( ("between:").length() );
    	
    	System.out.println( "between: " + tmpstr +" length: " + (tmpstr.split("_")).length);
    	
    	if(tmpstr.trim().length()>0  ){
    		
    		String ammountCadFrom = "";
	    	String ammountCadTo = "";
    		
    		if((tmpstr.split("_")).length == 2){
    		
    		 ammountCadFrom = tmpstr.split("_")[0];
	    	 ammountCadTo = tmpstr.split("_")[1];
    	
    		}
    		else if ( (tmpstr.split("_")).length == 1 ) {
    			 ammountCadFrom = tmpstr.split("_")[0];
    		}
    	
	    	if(ammountCadTo !=null && ammountCadFrom!= null && (!ammountCadFrom.equals("") || !ammountCadTo.equals(""))){
	    		if(ammountCadFrom.trim().length() == 0){
	    			ammountCadFrom = "0";
	    		}
	    		
	    		if( ammountCadTo.trim().length() > 0 ){
	    		
		    		if(Double.parseDouble(ammountCadFrom)>Double.parseDouble(ammountCadTo)){
		    			// this.setLabelOnFilteringByAmmount("El limite inferior no puede ser mayor al limite superior.");
		    			return;
		    		}
	    		
	    		}
	    		
	    		if(!concatRiskCad.equals("")){
	    			
	    			if(ammountCadTo.trim().length() == 0){
		    			
	    				concatAmmountCad = " >= "+ammountCadFrom+" ";
	    				
		    		}else {
	    			
		    			concatAmmountCad = "  BETWEEN "+ammountCadFrom+" and "+ammountCadTo+" ";
		    			
		    		}
	    		}
	    		else{
	    			concatAmmountCad = " BETWEEN "+ammountCadFrom+" and "+ammountCadTo+" ";			
	
	    		}
	    		
	    		
		    	
	    	}
	    	else{
	    		concatAmmountCad = " >= 0";
	    	}
	    	
    	}
    	
    	filter.setAmmount( concatAmmountCad );
    			
		/*if(!termCad.equals("")){
			concatTermCad = " and l.DiasPorTrans <= ("+termCad+") ";
		}
		else{
			concatTermCad = "";
		}*/
    	
		//filter.setDiasPorTrans(termCad);
		
		//previousType:"+previousType+"||destinyValues:"+destiny_values;
		
		tmpstr = filtros[5] ;
    	
		String  previousType = tmpstr.substring( ("previousType:").length() );
		
    	String concatPrevLoanCad = "";
    	
    	if( previousType.equals("0")  ){
    		
    	}else if( previousType.equals("1")  ){
    		
    		concatPrevLoanCad = "  'NVO'  ";
    		
    	}else if( previousType.equals("2")  ){
    		concatPrevLoanCad = " 'ADD','REN','RDC' ";
    	}
    	
    	filter.setLoanType(concatPrevLoanCad);
    	
    	tmpstr = filtros[6] ;
    	
		String  destiny_values = tmpstr.substring( ("destinyValues:").length() );
    	
    	/*String concatDestCad = "";
    	
    	if(destiny_values != null && destiny_values.length() > 0){
    		concatDestCad = " and l.purpose_id in ( "+destiny_values+" ) ";
    	}*/
    	
    	filter.setPurpose_id( destiny_values );
    	
    	
    	// cad = concatRiskCad+concatGenderCad+concatAmmountCad+concatTermCad+concatPrevLoanCad+concatDestCad;
    	
    	//if(cad != null && !cad.equals("")){
    		
    		preparaProyectList(filter ,prospectusInvestor_id,companyInvestor_id, safi_account_id);
    		
    	//}
    	
    	generaScript(  ultimoFiltro,  flagRisk );
		
	}
	
	public List<ItemLoanList> calculaInvestmentBite( int prospectusInvestor_id, int companyInvestor_id, Double ammoutToInv , List<ItemLoanList> proyect_list_for_dipersion  ){
		
		//InvestorsAccounts account = null;
		
		Double monto_a_invertir_acum = 0D;
		
		SystemParamPK sysPk = new SystemParamPK();
		
		
		List<ItemLoanList>	lstDelete = new ArrayList<ItemLoanList>();
		
		
		sysPk.setCompany_id(companyInvestor_id);
		sysPk.setSystem_param_id(55);//Monto mínimo que se puede invertir en cada proyecto
		
		SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double montoMinGRAL = Double.parseDouble( sys.getValue() );

		montoMinG = montoMinGRAL;
		
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(companyInvestor_id);
		sysPk.setSystem_param_id(56);//Porcentaje del monto otorgado que sirve como tope máximo del monto a invertir
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double porcMaxPry = Double.parseDouble( sys.getValue() );
		
		maxPorcPryG = porcMaxPry;
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(companyInvestor_id);
		sysPk.setSystem_param_id(57);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double porcMaxSaldo = Double.parseDouble( sys.getValue() );
		
		//Hashtable<String,List<ProyectFunding> > hTFunding = generaHashFunding();
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(companyInvestor_id);
		sysPk.setSystem_param_id(74);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (E5)
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double porcMaxSaldoProyE5 = Double.parseDouble( sys.getValue() );
		
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(companyInvestor_id);
		sysPk.setSystem_param_id(76);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (E4)
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double porcMaxSaldoProyE4 = Double.parseDouble( sys.getValue() );
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id( companyInvestor_id );
		sysPk.setSystem_param_id(77);//Monto mínimo para invertir en proyectos de alto riesgo(E4,E5)
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double montoMinE4E5 = Double.parseDouble( sys.getValue() );
		
		montoMinE4E5G = montoMinE4E5;

		/*******/
		
		/*																*/
		/*			CONDICIONES PARA PROYECTOS CON RIESGO E5			*/
		/*																*/
		
		if( !flag_alto_riesgo ){
			
			InvestorParamPK invParamPK = new InvestorParamPK();
			
			invParamPK.setCompany_id(companyInvestor_id);
			invParamPK.setInvestment_param_id(1);
			invParamPK.setProspectus_id(prospectusInvestor_id);
			
			InvestorParam inParam = investorparamservice.getInvestorParamByPK(invParamPK);
			
			if( inParam != null && inParam.getValue_str() != null && Utilities.isDouble(inParam.getValue_str()) ){
				
				porcMaxSUMSaldoProyE5 = Double.parseDouble( inParam.getValue_str() ); //(5%)Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de alto riesgo (E5)
				
			}else {
		
				sysPk = new SystemParamPK();
				
				sysPk.setCompany_id(companyInvestor_id);
				sysPk.setSystem_param_id(95);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (E5)
				
				sys = systemparamservice.loadSelectedSystemParam(sysPk);
				
				porcMaxSUMSaldoProyE5 = Double.parseDouble( sys.getValue() ); //(5%)Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de alto riesgo (E5)
			
			}
			
		}else{
			
			porcMaxSUMSaldoProyE5 = 100D;
			
		}
		
		montoDisponibleEn_E5 = initMontoDisponibleEn_E5(); 
		
		boolean puede_invertir_en_E5 = montoDisponibleEn_E5 >  montoMinE4E5G; 
		
		/********/
		
		/*																*/
		/*			CONDICIONES PARA PROYECTOS CON RIESGO F1,G1			*/
		/*																*/
		
		if( !flag_alto_riesgo ){
			
			InvestorParamPK invParamPK = new InvestorParamPK();
			
			invParamPK.setCompany_id(companyInvestor_id);
			invParamPK.setInvestment_param_id(2);
			invParamPK.setProspectus_id(prospectusInvestor_id);
			
			InvestorParam inParam = investorparamservice.getInvestorParamByPK(invParamPK);
			
			if( inParam != null && inParam.getValue_str() != null && Utilities.isDouble(inParam.getValue_str()) ){
		
				porcMaxSaldoProyF1_G1 = Double.parseDouble( inParam.getValue_str() );
				
			}else{
				sysPk = new SystemParamPK();
				
				sysPk.setCompany_id(companyInvestor_id);
				sysPk.setSystem_param_id(83);//(0.33%)Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (F1,G1)
				
				sys = systemparamservice.loadSelectedSystemParam(sysPk);
				
				porcMaxSaldoProyF1_G1 = Double.parseDouble( sys.getValue() );
			}
		
		}else{
			porcMaxSaldoProyF1_G1 = 100D;
		}
		//    --------------
		
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id( companyInvestor_id );
		sysPk.setSystem_param_id(81);//($50)Monto mínimo para invertir en proyectos de alto riesgo(F1,G1)
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double montoMinF1_G1 = Double.parseDouble( sys.getValue() );
		
		montoMinF1_G1_G = montoMinF1_G1;
		
		
		//    --------------
		
		montoInvertido_F_G_temp = montoInvertido_F_G;
		
		//    --------------
		
		sysPk = new SystemParamPK();
		
		sysPk.setCompany_id( companyInvestor_id );
		sysPk.setSystem_param_id(82);//(10%)Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de alto riesgo (F1,G1)
		
		sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double porcent_suma_paraintir_en_F1_G1 = Double.parseDouble( sys.getValue() );
		
		porcent_suma_F1_G1_G = porcent_suma_paraintir_en_F1_G1;
		
		// Get  Monto Invertido en Proyectos de Muy Alto Riesgo
		
		
		montoMaximoPorProyecto_F_G = (saldoTotal*porcMaxSaldoProyF1_G1)/100;
		
		if( montoMaximoPorProyecto_F_G < montoMinF1_G1 ){
			
			montoMaximoPorProyecto_F_G = montoMinF1_G1;
			
		}
		
		limiteMaximoInversion_F_G = (saldoTotal*porcent_suma_F1_G1_G)/100;
		
		
		/*																	*/
		/*			FIN CONDICIONES PARA PROYECTOS CON RIESGO F1,G1			*/
		/*																	*/
		
			
			if(ammoutToInv!=null){
			
				monto_a_invertir_temp 	= ammoutToInv;
				
				montoSaldoG = monto_a_invertir_temp;
				
				investmentBiteVAL =  ammoutToInv / Double.parseDouble( (proyect_list_for_dipersion.size())  + "");
				
			}else{
				
				//System.out.println( " ---- account == null   ---  " + Double.parseDouble( (proyect_list_for_dipersion.size())  + "") );
				investmentBiteVAL = 251D;
				
			}
			
			
				
				// System.out.println("cachito Inicial: "+investmentBiteVAL);
				porcMaxSaldoG = porcMaxSaldo;
				Double maxAmmountBalance = (saldoTotal*porcMaxSaldo)/100;
				maximoInvBySaldoG = maxAmmountBalance;
				
				maximoInvBySaldoG = ( Math.floor(maximoInvBySaldoG*100) ) / 100;
				
				Double biteAmmountIni = 0D;
				flagMin_E5_E4 = false;
				maximoInvBySaldoPryE5 = (saldoTotal*porcMaxSaldoProyE5)/100;
				
				maximoInvBySaldoPryE5 = ( Math.floor(maximoInvBySaldoPryE5*100) ) / 100;
				
				System.out.println( "maximoInvBySaldoPryE5: " + maximoInvBySaldoPryE5 );
				
				if( maximoInvBySaldoPryE5 < montoMinE4E5 ){
				
					maximoInvBySaldoPryE5 = montoMinE4E5;
					flagMin_E5_E4 = true;
				
				}
				
				porcMaxSaldoPryE5G = porcMaxSaldoProyE5;
				
				maximoInvBySaldoPryE4 = (saldoTotal*porcMaxSaldoProyE4)/100;
				
				maximoInvBySaldoPryE4 = ( Math.floor(maximoInvBySaldoPryE4*100) ) / 100;
				
				System.out.println( "maximoInvBySaldoPryE4: " + maximoInvBySaldoPryE4 );
				
				if( maximoInvBySaldoPryE4 < montoMinE4E5 ){
					maximoInvBySaldoPryE4 = montoMinE4E5;
					flagMin_E5_E4 = true;
				}
				
				porcMaxSaldoPryE4G = porcMaxSaldoProyE4;
				
				if(investmentBiteVAL<montoMinGRAL)
				{
					
					biteAmmountIni = montoMinGRAL;
					
				}else if( investmentBiteVAL > maxAmmountBalance ){
					
					biteAmmountIni = maxAmmountBalance;
					
				}else{
					
					biteAmmountIni = investmentBiteVAL;
					
				}
				
				boolean flgfirst = false;
				int y = 0;
				
				//Limpiamos montos de  Proyectos
				
				for( ItemLoanList item :  proyect_list_for_dipersion)
				{
					item.setInvestment_bite(0D);
				}
				
				if(maxAmmountBalance < montoMinGRAL){
					maxAmmountBalance = montoMinGRAL;
					maximoInvBySaldoG = maxAmmountBalance;
					flagNotRule = true;
				}
				
				// System.out.println("Cachito calculado: "+maxAmmountBalance);
				// System.out.println("Máximo que puede invertir de acuero a su cuenta: "+maxAmmountBalance);
				// System.out.println("Monto inicial del cachito: "+biteAmmountIni);
				// System.out.println("Lista de Proyectos: "+proyect_list_for_dipersion.size());
				
				if( investmentType != null && investmentType.equals( "Automática" ) ){
					
					sysPk = new SystemParamPK();
					
					sysPk.setCompany_id( companyInvestor_id );
					sysPk.setSystem_param_id(87);//Límite de monto máximo para invertir por proyecto en inversion automática
					
					sys = systemparamservice.loadSelectedSystemParam(sysPk);
					
					Double montoMax_inversion_automatica = Double.parseDouble( sys.getValue() );
					
					
					if(maxAmmountBalance > montoMax_inversion_automatica){
						maxAmmountBalance = montoMax_inversion_automatica;
					}
					if(maximoInvBySaldoPryE4 > montoMax_inversion_automatica){
						maximoInvBySaldoPryE4 = montoMax_inversion_automatica;
					}
					if(maximoInvBySaldoPryE5 > montoMax_inversion_automatica){
						maximoInvBySaldoPryE5 = montoMax_inversion_automatica;
					}
					
					if(biteAmmountIni > montoMax_inversion_automatica){
						biteAmmountIni = montoMax_inversion_automatica;
					}
					if( maximoInvBySaldoG > montoMax_inversion_automatica ){
						maximoInvBySaldoG = montoMax_inversion_automatica;
					}
					if( montoMaximoPorProyecto_F_G > montoMax_inversion_automatica  ){
						montoMaximoPorProyecto_F_G = montoMax_inversion_automatica;
					}
					
				}
				
				while( monto_a_invertir_temp >0 && y<3 ){
					
					if(y>0)
						flgfirst = true;
					
					y++;
					
					// System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+y );
					// System.out.println( "Vuelta No. "+y +"  -- monto disponible: "+monto_a_invertir_temp );
					
					for( ItemLoanList item :  proyect_list_for_dipersion){
						
						Double biteAmmount = 0D;
						Double montoMin = 0D;
						
						Double maxProyectAmmount =  (item.getAmmount()*porcMaxPry)/100;
						
						Double max_monto_a_fondear = maxProyectAmmount>maxAmmountBalance?maxAmmountBalance:maxProyectAmmount;
						
						String itemKuboScore = item.getKubo_score_a()+item.getKubo_score_b();
						
						if( ( itemKuboScore.equals("E4") || itemKuboScore.equals("E5") ) ){
						
							if(itemKuboScore.equals("E4")){
								
								max_monto_a_fondear = maxProyectAmmount>maximoInvBySaldoPryE4?maximoInvBySaldoPryE4:maxProyectAmmount ;
								
							}else if( itemKuboScore.equals("E5") ) {
								
								if( puede_invertir_en_E5 ){
									
									if ( montoDisponibleEn_E5 < montoMinE4E5 ) {
										
										continue;
										
									}
								
									max_monto_a_fondear = maxProyectAmmount>maximoInvBySaldoPryE5?maximoInvBySaldoPryE5:maxProyectAmmount ;
								
									if( (montoDisponibleEn_E5 - max_monto_a_fondear) < 0  ){
									
										max_monto_a_fondear = montoDisponibleEn_E5;
										
									}
									
									montoDisponibleEn_E5 = montoDisponibleEn_E5 - max_monto_a_fondear;
									
								}else{
									
									continue;
									
								}
								
							}
							
							montoMin = montoMinE4E5;
							
						}else if( ( itemKuboScore.equals("G1") || itemKuboScore.equals("F1") ) ){
							
							if(itemKuboScore.equals("G1")){
								
								max_monto_a_fondear = maxProyectAmmount>montoMaximoPorProyecto_F_G?montoMaximoPorProyecto_F_G:maxProyectAmmount ;
								
							}else if( itemKuboScore.equals("F1") ) {
								
								max_monto_a_fondear = maxProyectAmmount>montoMaximoPorProyecto_F_G?montoMaximoPorProyecto_F_G:maxProyectAmmount ;
								
							}
							
							montoMin = montoMinF1_G1;
							
						}else{
							
							montoMin = montoMinGRAL;
							
						}
						
						//*  	NUEVA ASIGNACIÓN DE MONTO MINIMO
						//*		13-JUNIO-2016
						
						Double montoMinTemp = getMontoMinByProyect( item.getProyect_loan_id() );
						
						if( montoMinTemp != null  ){
							
							if(montoMinTemp > max_monto_a_fondear){
								
								lstDelete.add(item);
								
								continue;
								
							}else{
								
								if(montoMinTemp > montoMin){
									montoMin =  montoMinTemp;
								}
								
							}
						}
						
//						System.out.println( "****************************************************" );
//						System.out.println( "****************************************************" );
//						System.out.println( "");
//						System.out.println( "      MONTO  MINIMO    "+montoMin+"                 " );
//						System.out.println( "");
//						System.out.println( "****************************************************" );
//						System.out.println( "****************************************************" );
						
						//*   FIN NUEVA ASIGNACIÓN DE MONTO MINIMO
						
						if( maxProyectAmmount > max_monto_a_fondear){
							maxProyectAmmount = max_monto_a_fondear;
						}
						
						// System.out.println( "-- proyecto: "+item.getProyect_loan_id() + " maximo a invertir:  " + maxProyectAmmount + " Riesgo: " + itemKuboScore + " maximo a inverti (saldo) "+max_monto_a_fondear );
						
						if( biteAmmountIni > max_monto_a_fondear ){
							
							if( max_monto_a_fondear < montoMin ){
								
								System.out.println( "Sin Fondeo: el monto que le toca no alcanza el monto mínimo -- "+max_monto_a_fondear );
								
								
								
							}else{
							
								biteAmmount = max_monto_a_fondear;
								// System.out.println( "1cachito: "+ biteAmmount);
							
							}
							
						}else{
							
							biteAmmount = biteAmmountIni;
							// System.out.println( "2cachito: "+ biteAmmount);
						}
						
//						if( item.getAmmountPreviousFounded() != null && flagListFunding ){
//						
//							List<ProyectFunding> lstFunding =  proyectFundingService.getproyectbyInvestor( (sesion.getProspectus_id()+"") , (item.getProyect_loan_id()+"") );
//							
						String key = item.getProspectus_id() + "::"+ item.getProyect_loan_id();
						
						List<ProyectFunding> lstFunding = null;
						
						if( hTFunding != null ){
						
							lstFunding = hTFunding.get(key);
							
						}
						
						
//							flagListFunding = false;
							
							if( lstFunding != null && lstFunding.size()>0  ){
								
								if( flgfirst ){
								
									Double sum = 0D;
									
									for( ProyectFunding funding : lstFunding ){
										
										sum = sum + funding.getAmount() ;
										
									}
									
									item.setAmmountPreviousFounded( sum );
									
									// System.out.println("suma: "+sum+" + "+item.getInvestment_bite());
									
									sum += item.getInvestment_bite() ;
									
									if((sum+biteAmmount  ) > max_monto_a_fondear){
										Double m = max_monto_a_fondear - sum;
										
										if(m>=montoMin)
										{
											biteAmmount = m; 
											// System.out.println( "6cachito: "+ biteAmmount);
											
										}else{
											 System.out.println("..monto no suficiente: monto acumulado: "+sum+"  maximo montoa a fondear: "+max_monto_a_fondear+" -  monto calculado: "+m);
											 
											 continue;
										}
										
										
									}
								
								}else{
									System.out.println("fondeado previamente");
									
									Double sum = 0D;
									
									for( ProyectFunding funding : lstFunding ){
										
										sum = sum + funding.getAmount() ;
										
									}
									
									item.setAmmountPreviousFounded( sum );
									
									continue;
								}
								
							}
						
						
						
						if( biteAmmount > item.getAvailableAmmount()  )
						{
							
							if( item.getAvailableAmmount() > montoMin )
							{
								
								biteAmmount = item.getAvailableAmmount();
								// System.out.println( "3cachito: "+ biteAmmount);
								
								if( ( item.getInvestment_bite()+biteAmmount ) > item.getAvailableAmmount() ){
									
									biteAmmount = item.getAvailableAmmount() - item.getInvestment_bite();
									
									// System.out.println( "3.1cachito: "+ biteAmmount);
									
								}
								
								
							}else{
								
								if(item.getAvailableAmmount() < montoMin){
									System.out.println("Sin Fondeo: el monto disponible no alcanza el monto mínimo --- "+item.getAvailableAmmount());
									
									continue;
								}else{
								
									biteAmmount = item.getAvailableAmmount();
									// System.out.println( "4cachito: "+ biteAmmount);
								}
									
							}
							
						}else if( ( item.getInvestment_bite()+biteAmmount ) > item.getAvailableAmmount() ){
							
							biteAmmount = item.getAvailableAmmount() - item.getInvestment_bite();
							
							// System.out.println( "5cachito: "+ biteAmmount);
							
						}
						
						if( ( item.getInvestment_bite()+biteAmmount )>max_monto_a_fondear ){
							
							if( item.getInvestment_bite()>0 && maxAmmountBalance>max_monto_a_fondear ){
								
								Double biteAmmountT = maxProyectAmmount - item.getInvestment_bite();
								
								if( (item.getInvestment_bite()+biteAmmountT) <= maxProyectAmmount ){
									
									biteAmmount = biteAmmountT;
									
								}
								else {
									System.out.println("sin Fondeo, sobrepasaría monto saldo máximo: "+( item.getInvestment_bite()+biteAmmount ));
									continue;
								}
							}else{
							
								if(  item.getInvestment_bite()>0 && maxAmmountBalance<=max_monto_a_fondear ){
									
									// System.out.println("se fondea, con remanente de maximo-saldo : "+( maxAmmountBalance-item.getInvestment_bite() ));
									biteAmmount = maxAmmountBalance-item.getInvestment_bite();
									
								}else{
								
									// System.out.println("sin Fondeo, sobrepasaría monto máximo: "+( item.getInvestment_bite()+biteAmmount ));
									continue;
								}
							
							}
							
						}
						
						 
						 
						if( ( monto_a_invertir_temp - biteAmmount ) >= 0 )
						{
							
							boolean flag = true;
							
							if( itemKuboScore.equals("G1") || itemKuboScore.equals("F1")  ){
								
								Double mFG =  montoInvertido_F_G_temp +  biteAmmount;
								
								if( mFG > limiteMaximoInversion_F_G ){
									
									Double m2FG = limiteMaximoInversion_F_G - montoInvertido_F_G_temp ;
									
									if(m2FG < montoMinF1_G1){
										flag = false;
									}else{
										biteAmmount = m2FG;
									}
									
								}
								
								if(flag){
									montoInvertido_F_G_temp = montoInvertido_F_G_temp +  biteAmmount;
								}
								
							}
							
							if( flag ){
							
								monto_a_invertir_temp = monto_a_invertir_temp - biteAmmount;
								
								// item.setAvailableAmmount(  item.getAvailableAmmount()- biteAmmount);
								
								Double t = item.getInvestment_bite()+biteAmmount;
								
	//							if( t > item.getAvailableAmmount() || !flgfirst ){
									
									t = (Math.floor(t*100))/100;
									
									item.setInvestment_bite(t);
									
									// System.out.println("Cachito asignado al proyecto 1: "+biteAmmount);
									
									monto_a_invertir_acum += biteAmmount;
									 
									// System.out.println("Acumulado de inversión al proyecto 1: "+item.getInvestment_bite() );
								
	//							}else{
	//								
	//								System.out.println( "No se fondea en esta vuelta: sobre pasa el monto disponible" );
	//								//continue;
	//								
	//							}
							
								
							}
								
						}else{
							
							boolean flag = true;
							
							if( itemKuboScore.equals("G1") || itemKuboScore.equals("F1")  ){
								
								Double mFG =  montoInvertido_F_G_temp +  biteAmmount;
								
								if( mFG > limiteMaximoInversion_F_G ){
									
									Double m2FG = limiteMaximoInversion_F_G - montoInvertido_F_G_temp ;
									
									if(m2FG < montoMinF1_G1){
										flag = false;
									}else{
										biteAmmount = m2FG;
									}
									
								}
								
								if(flag){
									
									montoInvertido_F_G_temp = montoInvertido_F_G_temp +  biteAmmount;
									
									monto_a_invertir_temp = monto_a_invertir_temp - biteAmmount;
									
									if( monto_a_invertir_temp >= 0 ){
									
										Double t = item.getInvestment_bite()+biteAmmount;
											
										t = (Math.floor(t*100))/100;
										
										item.setInvestment_bite(t);
										
										monto_a_invertir_acum += biteAmmount;
									
									}else{
										monto_a_invertir_temp = monto_a_invertir_temp + biteAmmount;
									}
										 
								}
								
								
								
								
								
							} else if (  !itemKuboScore.equals("G1") && !itemKuboScore.equals("F1")   ) {
							
									if( monto_a_invertir_temp < montoMin && item.getInvestment_bite() != 0  )
									{
										
										if( (item.getInvestment_bite() + monto_a_invertir_temp)<= max_monto_a_fondear )
										{
											
											item.setAvailableAmmount(  item.getAvailableAmmount()- monto_a_invertir_temp);
											
											Double t = item.getInvestment_bite()+monto_a_invertir_temp;
											
											t = (Math.floor(t*100))/100;
											
											item.setInvestment_bite(t);
											
											// System.out.println("Cachito asignado al proyecto 2: "+monto_a_invertir_temp );
											 
											// System.out.println("Acumulado de inversión al proyecto 2: "+item.getInvestment_bite() );
											
											monto_a_invertir_acum += monto_a_invertir_temp;
											
											monto_a_invertir_temp = 0D;
											
											break;
											
										}
								
									}
								
							}else{
							
									// System.out.println( "monto no suficiente: disponible- "+monto_a_invertir_temp+"  cachito"+ biteAmmount);
									continue;
									
							}
							
						}
						
					}
				}
			
			
			/* 
			 * Se remueven todos los proyectos en los que no se puede invertir
			 */
			
			//System.out.println("Inicia: " + proyect_list_for_dipersion.size() );
			
			if( lstDelete.size() > 0 ){
				
				Iterator<ItemLoanList> itrDelete = proyect_list_for_dipersion.iterator();
				
				while ( itrDelete.hasNext()) 
				{
					ItemLoanList item = itrDelete.next();
					boolean flgDelete = false;
					
					for( ItemLoanList i :  lstDelete){
						
						if( i.getProyect_loan_id() 	== item.getProyect_loan_id() && 
							i.getProspectus_id() 	== item.getProspectus_id() &&
							i.getProyect_id()	== item.getProyect_id()
								){
							
							flgDelete = true;
							break;
							
						}
						
					}
					
					if( flgDelete  ){
						System.out.println( "Delete proyect_loan_id : " + item.getProyect_loan_id());
						itrDelete.remove();
					}
				}
			
			}
			
			Iterator<ItemLoanList> itr = proyect_list_for_dipersion.iterator();
			
//			int r = 0;
//			int previous = 0;
			
			List<ItemLoanList> temp = new ArrayList<ItemLoanList>() ;
			
			List<ItemLoanList> lstFondeadoPrev = new ArrayList<ItemLoanList>();
			
			List<ItemLoanList> lstFondeadoPrev_SinInv = new ArrayList<ItemLoanList>();
			
			
			while ( itr.hasNext()) 
			{
				
				ItemLoanList item = itr.next();
				
				if( item.getAmmountPreviousFounded() != null && item.getAmmountPreviousFounded() > 0 )
				{
					
					if (item.getInvestment_bite() > 0D) {
				    	
				    	item.setBlnChck(true);
				    	lstFondeadoPrev.add( item );
				    	//previous ++;
				    	
				    }else{
				    	item.setBlnChck(false);
				    	lstFondeadoPrev_SinInv.add( item );
				    }
					
					
				    itr.remove();
				    
				}
				
			}
			
			//System.out.println("primer recorte: " + proyect_list_for_dipersion.size() );
			
			Iterator<ItemLoanList> itr2 = proyect_list_for_dipersion.iterator();
			
			while ( itr2.hasNext()) 
			{
				
				ItemLoanList item = itr2.next();

			    if (item.getInvestment_bite() == 0D) {
			    	temp.add( item );
			       itr2.remove();
			       //r++;
			    }else{
			    	item.setBlnChck(true);
			    }

			}
			
			///System.out.println("segundo recorte: " + proyect_list_for_dipersion.size() );
			
			for( ItemLoanList t : lstFondeadoPrev ){
				
				proyect_list_for_dipersion.add(t);
				
			}
			
			// asignaListForInvest();
			
			
			
			//Se envian al final los proyectos que no se pueden fondear 
			for( ItemLoanList t : temp ){
				
				Double montoMin = 0D;
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") || ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ){
					montoMin = montoMinE4E5;
				}else{
					montoMin = montoMinGRAL;
				}
				
				Double maximoInv = 0D;
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") ){
					maximoInv = maximoInvBySaldoPryE4;
				}
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ){
					maximoInv = maximoInvBySaldoPryE5;
				}
				
				if( t.getAvailableAmmount() > montoMin ){
				
					t.setBlnChck(false);
					
					if( montoMin > maximoInv){
					
						if( ! ( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") || ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ) ){
						
							proyect_list_for_dipersion.add(t);
							
						}
						
					}else{
						
						proyect_list_for_dipersion.add(t);
						
					}
				}
				
				
				
			}
			
			for( ItemLoanList t : lstFondeadoPrev_SinInv ){
				
				Double montoMin = 0D;
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") || ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ){
					montoMin = montoMinE4E5;
				}else{
					montoMin = montoMinGRAL;
				}
				
				Double maximoInv = 0D;
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") ){
					maximoInv = maximoInvBySaldoPryE4;
				}
				
				if( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ){
					maximoInv = maximoInvBySaldoPryE5;
				}
				
				if( montoMin > maximoInv){
					
					if( ! ( ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E4") || ( t.getKubo_score_a() + t.getKubo_score_b() ).equals("E5") ) ){
					
						proyect_list_for_dipersion.add(t);
						
					}
						
				}else{
				
					proyect_list_for_dipersion.add(t);
				
				}
				
			}
			
			//System.out.println("prviamente fondeados: "+previous);
			//System.out.println(" se removieron "+r+" elementos");
			
			//System.out.println("finalizó con: " + proyect_list_for_dipersion.size() );
			
			
			monto_a_invertir = monto_a_invertir_acum;
			
		
			return proyect_list_for_dipersion;
		
		
	}
	
	
	
	public void ejecutaThreadDespuesDeFondeo(){
		
		ThreadInvestmentAction ejecuta = new ThreadInvestmentAction();
		
		ejecuta.setLstService	( getLstService() );
		ejecuta.setStrStored	( getStoredString() );
		ejecuta.setLstProgressDet(getProgressdetlst() );
		ejecuta.excecuteAction	();
		
	}
	
	private void ejecutaSPRecargaListaClienteCredito( String safi_client_id ){
		proyectLoanService.ejecutaSPDeleteCreditsByClient(safi_client_id);
	}
	
	private Double getAmmountInvinProyE5( String safi_account_id ){
		
		List<MontoInvertido_F_G_Collector> collector = montoInvertido_F_G_service.getMontoInvertido_F_G(safi_account_id);
		
		Double sum_F_G = 0D;
		
		if( collector != null && collector.size() > 0 ){
			
			for( MontoInvertido_F_G_Collector coll : collector ){
				
				if( coll != null && coll.getKubo_score() != null && coll.getKubo_score().equals( "E5" ) ){
					
					sum_F_G += coll.getTotalFondeado();
					
				}
				
			}
			
		}
		
		return sum_F_G;
				
	}
	
	public Double  initMontoDisponibleEn_E5(){
		
		Double ammountInverInProyE5 = getAmmountInvinProyE5( listInvAccounts.get(0).getAccount() );
	
		Double montoMaximoEn_E5s = (saldoTotal*porcMaxSUMSaldoProyE5)/100;
	
		return  montoMaximoEn_E5s - ammountInverInProyE5;
		
	}
	
}
