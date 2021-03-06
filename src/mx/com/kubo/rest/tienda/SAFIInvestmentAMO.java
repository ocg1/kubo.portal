package mx.com.kubo.rest.tienda;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;

import mx.com.kubo.bean.ItemInversion;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.MontoInvertido_F_G_Collector;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ProyectInfo;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.TiendaCreditos;
import mx.com.kubo.model.ViewForTiendaExec;
import safisrv.ws.InvKuboServicios.SolicitudInversionRequest;
import safisrv.ws.InvKuboServicios.SolicitudInversionResponse;

public abstract class SAFIInvestmentAMO extends SAFIInvestmentDMO 
implements SAFIInvestmentIMO
{
	protected void init_item_loan_list(ViewForTiendaExec prln, int propsectus_invesrtor_id) 
	{
		ItemLoanList item = new ItemLoanList();
		
		TiendaCreditos tienda = tiendacreditosservice.getTiendaCreditosItemBySolOrCred( prln.getSafi_mx_solicitud_id(), prln.getSafi_credit_id() );
		
		if(tienda != null)
		{															
			item.setActualTerm( tienda.getPlazoEnDias()+" Días" );
			
			if( tienda.getSafi_credit_id()==null || Integer.parseInt( tienda.getSafi_credit_id() ) == 0)
			{												
				ProyectLoanPK tmpPk = new  ProyectLoanPK();
				
				tmpPk.setCompany_id(prln.getProyectloanPk().getCompany_id());
				tmpPk.setProspectus_id(prln.getProyectloanPk().getProspectus_id());
				tmpPk.setProyect_id(prln.getProyectloanPk().getProyect_id());
				tmpPk.setProyect_loan_id(prln.getProyectloanPk().getProyect_loan_id());
				
				List<BigDecimal> ammountlst =proyectFundingService.getAmountFundedByProyectLoanPK(tmpPk);
				
				BigDecimal deci = BigDecimal.ZERO;
				
				for(BigDecimal amm : ammountlst )
				{																					
					deci = deci.add(new BigDecimal( amm+"" ));							
				}
				
				Double disponible = prln.getAmmount()-((prln.getAmmount()*10)/100)-(Double.parseDouble( deci.toString() ));
				
				item.setAvailableAmmount(disponible);
				
			}else{
				
				item.setAvailableAmmount( Double.parseDouble( tienda.getDisponibleFondeo() ) );
				
			}
			
			item.setAvailableDays( tienda.getDiasPorTrans() + " Días" );
			
			item.setAmmount( tienda.getMontoCredito() );
			
			if( prln.getSafi_credit_id() != null )
			{						
				item.setActualAmmount( tienda.getSaldoCredito() );
				item.setDaysLeftStrInv("DESEMBOLSADO");
				
			}else{
				
				item.setActualAmmount( tienda.getMontoCredito() );
				item.setDaysLeftStrInv(prln.getDaysLeft()+" Días");
				
			}
			
		} else{
			
			item.setActualAmmount( null );					
			item.setActualTerm( null );					
			item.setAvailableAmmount( null);					
			item.setAvailableDays( null );					
			item.setAmmount(prln.getAmmount());
			
		}
		
		// aSIGNACION DE MONTO QUE PUEDE INVERTIR CADA INVERSIONISTA
		item.setInvestment_bite(0D); // init														
		item.setAmmountLeft(prln.getAmmountLeft());
		item.setAmount_founded(prln.getAmount_founded());
		item.setBarPorcentTotal(prln.getBarPorcentTotal());
		item.setBc_score_range(prln.getBc_score_range());
		item.setBottomPorcent(prln.getBottomPorcent());
		item.setCompany_id(prln.getProyectloanPk().getCompany_id());
		item.setCompleteName(prln.getPerson().NombreCompletoNPM());
		item.setDaysLeft(prln.getDaysLeft());
		item.setEnabledFundingBtn(false);				
		item.setEnabledFundingDetailBtn(prln.getEnabledBottomDetail());
		item.setExpectedPerformanceForProyect(getExpectedPerformanceForProyect(prln.getInvestment_bite2(saldoTotal + ""), prln.getRate_investor(), prln.getTerm().getMonths(), prln.getFrequency_id()));
		item.setFrequency_id(prln.getFrequency_id());				
		item.setKubo_score_a(prln.getKubo_score_a());
		item.setKubo_score_b(prln.getKubo_score_b());
		item.setKuboBarPorcent("width: 0.0%");
		
		item.setProspectus_id(prln.getProyectloanPk().getProspectus_id());
		item.setProyect_id(prln.getProyectloanPk().getProyect_id());
		item.setProyect_loan_id(prln.getProyectloanPk().getProyect_loan_id());
		item.setProyect_name(prln.getProyect().getName());
		
		if(prln.getProyect().getPurpose() !=null)
			item.setProyect_purpose_name(prln.getProyect().getPurpose().getName());
		else{
			item.setProyect_purpose_name("");
		}
		
		item.setRate(prln.getRate());
		item.setRate_investor(prln.getRate_investor());
		item.setSaldoActual(saldoTotal);
		item.setStatus_id(prln.getStatus_id());
		item.setTerm_months(prln.getTerm().getMonths());
		item.setVerification_score(prln.getVerification_score());
		item.setVerificationClass(prln.getVerificationClass()+"");
		item.setVerificationRange(prln.getVerificationRange()); 
		
		item.setName(prln.getStatusProyect().getName());
		item.setUrl_img(prln.getStatusProyect().getUrl_img());
		
		item.setSafi_credit_id( prln.getSafi_credit_id() );
		item.setSafi_solicitud_id( prln.getSafi_mx_solicitud_id() );
		
		
		if(prln.getSafi_credit_id() != null && !prln.getSafi_credit_id().equals("0") ){
			item.setNumInvestors( prln.getCantInvCred().toString()  );
		}else{
			item.setNumInvestors( prln.getCantInvSol().toString()  );
		}
		
		item.setProyectFunding(getMaxInvertionOnProyectFromInvestor(prln.getProyectloanPk(), propsectus_invesrtor_id));
	
		item.setLoan_type(prln.getLoan_type());
		
		proyectList.add(item);
	}
	
	protected Double getAmmountInvinProyE5( String safi_account_id )
	{
		
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
	
	protected void initServiceCalling(int company_id, int invId, String creditoID, String investment_Bite){
		
		ServiceCalling srvCall = new ServiceCalling();
	    srvCall.setAcces_datetime(new Date());
	    srvCall.setCompany_id(company_id);
	    srvCall.setStatus(1);
	    srvCall.setProspectus_id(invId);
	    srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudInversion: crd = "+creditoID+" monto: "+investment_Bite);
	    lstService.add(srvCall);
		
	}
	
	protected String[] storeInvestmentInWS(String solicitudCreditoId,String safiCreditId,  String cuentaAhorroId,String prospectus_investor_id, Double rate, Double investmentBite,String fundingType, int company_id,int prospectus_id, ItemLoanList item )
    {
    	try
    	{
    		
			SolicitudInversionRequest solicitudInversionRequest = new SolicitudInversionRequest();
			
			solicitudInversionRequest.setClienteID(prospectus_investor_id+"");
			solicitudInversionRequest.setCreditoID(safiCreditId);
			solicitudInversionRequest.setCuentaAhoID(cuentaAhorroId+"");
			solicitudInversionRequest.setMontoFondeo(investmentBite+"");
			solicitudInversionRequest.setSolicitudCreditoID(solicitudCreditoId);
			solicitudInversionRequest.setTasaPasiva(rate+"");
			solicitudInversionRequest.setTipoFondeo(fundingType);
			
			
			Calendar c1 = Calendar.getInstance();
			c1.setTime( new Date() );
			
			SolicitudInversionResponse res1 = servicioInvKuboSafi.solicitudInversion(solicitudInversionRequest);
			
			Calendar c2 = Calendar.getInstance();
			c2.setTime( new Date() );
			
			Long difW3 = c2.getTimeInMillis() - c1.getTimeInMillis();
			
			System.out.println("Tardó en Ejecutar WS solicitudInversion : "+difW3+" milisegundos");
			
			String ExceptionOnFunding = res1.getMensajeRespuesta(); 
			
			ServiceCalling srvCall;
			
			if(!res1.getCodigoRespuesta().equals("0") &&  !res1.getCodigoRespuesta().equals("00") && !res1.getCodigoRespuesta().equals("000"))
			{
		    	srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(ExceptionOnFunding);
				srvCall.setProspectus_id( Integer.parseInt( investor_id ));
				srvCall.setStatus(3);
				lstService.add(srvCall);
				
				ItemInversion inv = new ItemInversion();
				
				inv.setAmmount( item.getInvestment_bite() );
				inv.setKubo_score_a( item.getKubo_score_a() );
				inv.setKubo_score_b( item.getKubo_score_b() );
				inv.setTasa(item.getRate_investor()+"");
				inv.setMotivo(res1.getMensajeRespuesta());
				inv.setProyect_loan_id(item.getProyect_loan_id());
				inv.setStatus("0");
				inv.setDias_inversion(item.getAvailableDays());
				proyectosNoFondeados++;
				listToInv.add( inv );
				montoNOinvertido += item.getInvestment_bite();
				
				InvestmentProgressDet progress = new InvestmentProgressDet();
				
				progress.setProspectus_investor_id( Integer.parseInt( investor_id) );
				
				if( investmentType != null && investmentType.equals("Automática") ){
				
					progress.setAutomatic_investment_id( investmentprogress.getAutomatic_investment_id() );
					progress.setFondeo_kubo_id(null);
					progress.setInvestment_date( investmentprogress.getInvestment_date() );
				
				}
				
				Date dT = new Date();
				
				progress.setInvestment_bite(item.getInvestment_bite());
				progress.setInvestment_execute_date(dT);
				progress.setKubo_score(item.getKubo_score_a()+item.getKubo_score_b());
				progress.setMessage( res1.getCodigoRespuesta() + " - " + res1.getMensajeRespuesta());
				
				progress.setProyect_loan_id(item.getProyect_loan_id());
				progress.setSafi_credit_id(item.getSafi_credit_id());
				progress.setSafi_mx_solicitud_id(item.getSafi_solicitud_id());
				progress.setStatus_progress_det(2);
				progress.setInvestment_type(investmentType);
				
				progressdetlst.add( progress );
				
		    }else{
				
				srvCall = new ServiceCalling();
			    srvCall.setAcces_datetime(new Date());
			    srvCall.setCompany_id(company_id);
			    srvCall.setStatus(2);
			    srvCall.setProspectus_id( Integer.parseInt( investor_id ) );
			    
			    String v = res1.getInfoDetalleCuotas();
			    
			    if(v.length()>1900)
			    {
			    	v= v.substring(0,1900);
			    }
			    
			    srvCall.setInfo("Regresando Satisfactoriamente de solicitudInversion: "+v);
			    
			    montoinvertido += investmentBite;
				proyectos++;
				
			    lstService.add(srvCall);
			    
			    ItemInversion inv = new ItemInversion();
				
				inv.setAmmount( item.getInvestment_bite() );
				inv.setKubo_score_a( item.getKubo_score_a() );
				inv.setKubo_score_b( item.getKubo_score_b() );
				inv.setMotivo(null);
				inv.setProyect_loan_id(item.getProyect_loan_id());
				inv.setStatus("1");
				inv.setTasa(item.getRate_investor()+"");
				inv.setDias_inversion(item.getAvailableDays());
				
				listToInv.add( inv );
				
				ProyectFunding funding = new ProyectFunding();
				
				funding.setAmount(item.getInvestment_bite());
				funding.setKubo_score_a(item.getKubo_score_a());
				funding.setKubo_score_b(item.getKubo_score_b());
				
				ProyectFundingPK fundingPK = new ProyectFundingPK();
				
				fundingPK.setCompany_id(item.getCompany_id());
				fundingPK.setProspectus_id(item.getProspectus_id());
				fundingPK.setProspectus_investor_id( Integer.parseInt(prospectus_investor_id) );
				fundingPK.setProyect_id(item.getProyect_id());
				fundingPK.setProyect_loan_id(item.getProyect_loan_id());
				
				funding.setProyectloanfundingPk(fundingPK);
				
				funding.setFunding_date(new Date());
				funding.setFunding_time(new Time( (new Date()).getTime() ));
				
				funding.setSolicitudFondeo( res1.getSolicitudFondeo() );
				
				lstProyectosFondeados.add( funding );
				
				//montoinvertido = montoinvertido + item.getInvestment_bite(); 
				
				InvestmentProgressDet progress = new InvestmentProgressDet();
				
				progress.setProspectus_investor_id( Integer.parseInt( investor_id ) );
				
				if( investmentType != null && investmentType.equals("Automática") ){
				
					progress.setAutomatic_investment_id( investmentprogress.getAutomatic_investment_id() );
					progress.setInvestment_date( investmentprogress.getInvestment_date() );
				
				}
				
				progress.setFondeo_kubo_id( res1.getSolicitudFondeo() );
				
				Date dT = new Date();
				
				progress.setInvestment_bite(item.getInvestment_bite());
				progress.setInvestment_execute_date(dT);
				progress.setKubo_score(item.getKubo_score_a()+item.getKubo_score_b());
				progress.setMessage( res1.getCodigoRespuesta() + " - " + res1.getMensajeRespuesta());
				
				progress.setProyect_loan_id(item.getProyect_loan_id());
				progress.setSafi_credit_id(item.getSafi_credit_id());
				progress.setSafi_mx_solicitud_id(item.getSafi_solicitud_id());
				progress.setStatus_progress_det(1);
				progress.setInvestment_type(investmentType);
				
				progressdetlst.add( progress );
				
			}
			
			String[] res = new String[2];
			res[0] = res1.getCodigoRespuesta();
			res[1] = res1.getSolicitudFondeo();
			
			return res;
			
    	}catch (Exception e) {
    		
    		e.printStackTrace();
    		
    		String[] res = new String[2];
			res[0] = "-1";
			res[1] = "-1";
			
			return res;
    		
		}
    	
    }
	
	protected void procesaInversiones(){
		
		try{
		
			RequestContext requestContext = RequestContext.getCurrentInstance();
			List<ItemInversion> tmp = new ArrayList<ItemInversion>();
			
			Double suma = 0D;
		    tasaPonderada = 0D;
			for( ItemInversion item : listToInv ){
				
				if( item.getStatus().equals("1") ){
					tmp.add(item);
					suma +=	item.getAmmount();
				}
				
			}
			for( ItemInversion item : tmp ){
				tasaPonderada += (item.getAmmount()/suma)*Double.parseDouble(item.getTasa());
			}
			
			if( requestContext != null && tmp != null ){
				requestContext.addCallbackParam("invList", new JSONArray(tmp.toArray(),true).toString());
				requestContext.addCallbackParam("tasaPonderada", tasaPonderada );
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void generaScript( String ultimoFiltro, String flagRisk )
	{		
		String[] filtros = ultimoFiltro.split("\\|\\|");
		
		String riskCad = filtros[1];
		String genderCad = "" ;
    	
    	boolean flagGender = false;
    	String genderCadTemp = filtros[2] ;
    	
    	genderCadTemp = genderCadTemp.substring( ("gender:").length() );
    	
    	
    	if(genderCadTemp != null && genderCadTemp.trim().length()>0 )
    	{
    		
    		if(genderCadTemp.indexOf("H") != (-1) )
    		{
    			genderCad = "1";
    			flagGender = true;
    		}
    		
    		if(genderCadTemp.indexOf("M") != (-1) )
    		{
    			if(flagGender)
    			{
    				genderCad += ",";
    			}
    			
    			genderCad += "2";    			
    		}    		
    	}
    			
    	scriptStatus = "<script>";
    	
    	riskCad = riskCad.substring( ("risk:").length() );
    	
    	
    	if( flagRisk == null || flagRisk.equals("0") )
    	{
    	
	    	if(riskCad != null && riskCad.length()  >0)
	    	{
	    		
	    		if( riskCad.indexOf('A') != (-1) )
	    			scriptStatus += "$('#chkA').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('B') != (-1) )
	    			scriptStatus += "$('#chkB').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('C') != (-1) )
	    			scriptStatus += "$('#chkC').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('D') != (-1) )
	    			scriptStatus += "$('#chkD').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('E') != (-1) )
	    			scriptStatus += "$('#chkE').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('F') != (-1) )
	    			scriptStatus += "$('#chkF').attr('checked', true);";
	    		
	    		if( riskCad.indexOf('G') != (-1) )
	    			scriptStatus += "$('#chkG').attr('checked', true);";
	    		
	    		
	    	}
	    	
    	}else{
    		
    		scriptStatus += "initRiskDetailCheck();";
    		
    	}
    
    	
    	if( genderCad !=null && genderCad.trim().length()>0)
    	{
    		
    		if( genderCad.indexOf("2")!=(-1))
    		{
    			scriptStatus += "$('#gender_Mujer').addClass('itemCheck');";
    			
    		}
    		
    		if( genderCad.indexOf("1")!=(-1))
    		{
    			scriptStatus += "$('#gender_Hombre').addClass('itemCheck');";
    			
    		}
		}
    	
    	String termCadIni =  filtros[0] ;;
    	
    	scriptStatus += "seleccionaTerm('"+termCadIni+"');";
    	
    	
    	String tmpstr = filtros[4] ;
    	
    	tmpstr = tmpstr.substring( ("between:").length() );
    	
    	if(tmpstr.trim().length()>0 && (tmpstr.split("_")).length == 2 )
    	{
    		
    		String ammountCadFrom = tmpstr.split("_")[0];
	    	String ammountCadTo = tmpstr.split("_")[1];
    	
    	
    	
	    	if(ammountCadFrom != null && !ammountCadFrom.equals("0") && ammountCadFrom.trim().length()>0 )
	    		scriptStatus += "$('#inputFrom').val("+ammountCadFrom+");";
	    	
	    	if(ammountCadTo != null && !ammountCadTo.equals("0") && ammountCadTo.trim().length()>0 )
	    		scriptStatus += "$('#inputTo').val("+ammountCadTo+");";
	    	
    	}
    	
    	scriptStatus += "initViewRisk(); $('cadena1').val("+riskCad+"); initDestinyCheck();  </script>";
   
	}
		
	protected Double getExpectedPerformanceForProyect(Double cachito,Double tasa,Integer plazo,Integer idPagos)
	{
		Integer pagos = 0;
		if(idPagos==1){ 
			pagos = 52;
		}
		else if (idPagos==2){ 
			pagos = 26;
		}
		else if (idPagos==3){ 
			pagos = 24;
		}
		else{
			pagos = 14;
		}
		
		if(tasa==null)
			tasa=52.6D;
		
		tasa = tasa/100;
		Double tasaAnual=tasa;
		Double tasaEnPeriodo = tasaAnual/pagos;	
		Double rendimiento = generaMontoCuota(tasaEnPeriodo, Double.parseDouble(plazo+""), cachito);
		rendimiento = (rendimiento*plazo)-cachito;
		
		if (rendimiento < 0.00) 
			rendimiento = 0.00;
		
		return (double)Math.round(rendimiento*100)/100;
	
	}
	
	protected Double generaMontoCuota(Double tasaPeriodo,Double numCuota,Double cachito)
	{
		Double intper = tasaPeriodo;
		Double num = (Math.pow((1+intper), numCuota))*intper;
		Double den = (Math.pow((1+intper), numCuota))-1;
		Double montoAPagar = cachito*(num/den);
		return (double)Math.round(montoAPagar*100)/100;
	}
	
	protected List<ProyectFunding> getMaxInvertionOnProyectFromInvestor(ProyectLoanPK key, Integer InvID){
		try {
			if(key!=null && InvID!=null)
			{
				List<ProyectFunding> amount = this.proyectFundingService.getMaxProyectFundingByInvOnProyect(key, InvID);
				
				if(amount.size()>0)
				{
					
					/* ----- */
					
					// TODO 
					// List<ProyectFunding> lstInv = sessionInvestmentList;
					List<ProyectFunding> lstInv = new ArrayList<ProyectFunding> ();
					List<String> solicituondeo = new ArrayList<String> ();
					
					for( ProyectFunding pf : amount ){
						
						//System.out.println("solicitudFondeo1: "+pf.getSolicitudFondeo()+"   --  lstInv: " + (lstInv==null?"null":lstInv.size()) );
												
						if( pf.getSolicitudFondeo() != null && pf.getSolicitudFondeo().trim().length() > 0 && lstInv != null && lstInv.size() > 0 ){
							solicituondeo.add( pf.getSolicitudFondeo() );
						}
						
					}
					
					//int i = 0; 
					
					ArrayList<ProyectFunding> lst_index = new ArrayList<ProyectFunding>();
					
					for ( ProyectFunding fdg : lstInv ) {
						
						for( String str : solicituondeo ){
							
							System.out.println( fdg.getSolicitudFondeo().trim().equals(str) +" : " + fdg.getSolicitudFondeo() + "  " +str);
							
							if ( fdg.getSolicitudFondeo().trim().equals(str) ){
								lst_index.add(fdg);
							}
							
						}
						// i++;
					}
					
					if( lst_index != null && lst_index.size()>0 ){
					
						//Collections.reverse(lst_index);
						
						for( ProyectFunding in : lst_index ){
							//System.out.println( " remover elemeto #"+in+" : " );
							lstInv.remove(in);
						}
					
					}
					
					for ( ProyectFunding fdg : lstInv ) {
						if(
							key.getCompany_id() == fdg.getProyectloanfundingPk().getCompany_id() && 
							key.getProspectus_id() == fdg.getProyectloanfundingPk().getProspectus_id() &&
							key.getProyect_id() == fdg.getProyectloanfundingPk().getProyect_id() &&
							key.getProyect_loan_id() == fdg.getProyectloanfundingPk().getProyect_loan_id() &&
							fdg.getSolicitudFondeo() != null && fdg.getSolicitudFondeo().trim().length() > 0
								){
						
							amount.add(fdg);
							
						}
						
					}
					
					/* ----- */
					
					return amount;
				}
				else{
					return null;
				}
			}else
				return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	protected boolean puedeInvertir ( Integer prospectus_id ){
		
		boolean flag = false;
		
		if( prospectus_id.intValue() == 42621){
			
			flag = true;
			
		}else if( prospectus_id.intValue() ==  43958){
			
			flag = true;
			
		}else if( prospectus_id.intValue() ==  100973){
			
			flag = true;
			
		}else if( prospectus_id.intValue() ==  60604){
			
			flag = true;
			
		}
		
		flag_alto_riesgo = flag;
		
		return flag;
	}
	
	protected Double getMontoMinByProyect( Integer proyect_loan_id ){
		
		try{
		
			Double min = 0D;
			
			ProyectInfo info =  proyectInfoService.getProyectInfoByProyectLoan(proyect_loan_id);
			
			if( info != null ){
			
				min = info.getMin_investment_bite();
			
			}else{
				
				min = 50D;
				
			}
			
			return min;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
