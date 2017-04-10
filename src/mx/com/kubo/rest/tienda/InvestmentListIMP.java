package mx.com.kubo.rest.tienda;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.core.Response;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.SearchSummaySession;

import mx.com.kubo.managedbeans.investor.InvestorSession;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.model.InvestmentFilterPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectInfo;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.TasasAcreditado;
import mx.com.kubo.model.TiendaCreditos;
import mx.com.kubo.model.ViewForTiendaExec;
import mx.com.kubo.rest.model.TiendaRequest;
import mx.com.kubo.rest.tienda.accounts.CuentasClienteIMP;
import mx.com.kubo.tools.NumberToLetterConverter;

import org.primefaces.context.RequestContext;

public class InvestmentListIMP extends InvestmentListAMO
implements InvestmentListIMO
{	
	public void init()
	{
/*					
		investorSession = (InvestorSession) resolver.getValue(elContext, null, "investorSession");		
*/																																		
		purposelst = purposeservice.getPurposeList();
		lstStatus  = statusproyectcatservice.getListStatusProyectCat();
		
		cuenta = new CuentasClienteIMP();
		cuenta.setSesion(sesion);
		cuenta.init();
		
		saldoTotal      = cuenta.getSaldoTotal();
		listInvAccounts = cuenta.getListInvAccounts();
		
		inversion = new SAFIInvestmentIMP();
		inversion.setSaldoTotal(saldoTotal);
		inversion.setListInvAccounts(listInvAccounts);
		inversion.init();
		
		inicializaSaldos();
		init_system_param();											
			
		calculaSaldoActual();														
			
		try
		{					
			servicioSafi = inversion.getServicioInvKuboSafi();				
				
		} catch(Exception e) {
						
			e.printStackTrace();
		}																															
		
		setMontoMaximo();
		
		cargaListaTienda();		

				
		if( saldoTotal  < montoMaximo )
		{
			displayInvestAction = false;
			
		} else {
			
			displayInvestAction = true;					
		}
				
				asignaPerfil();
				
				if( displayInvestAction )
				{					
				    for(int i = 0 ; i < listInvAccounts.size() ; i++ )
					{						
						if(tagAccount.equals( listInvAccounts.get( i ).getAccount() ))
						{					
							ammoutToInv = ((InvestorsAccounts) listInvAccounts.get(i )).getSaldo();
							montoTotal = ammoutToInv;						
						}					
					}				  
					
					calculaInversionPorProyecto(false);				   				   
					
					aToInv = 0;
				    bToInv = 0;
				    cToInv = 0;
				    dToInv = 0;
				    eToInv = 0;				    
				}
				
				displayMsgMaxSug = true;
				displayMsgMinSug = true;
				
				if( saldoActual != null )
				{					
					if(saldoActual > montoLimiteMaxSugerido)
					{
						displayMsgMaxSug = true;
						
					}
					
					else if( saldoActual < montoLimiteMinSugerido )
					{
						displayMsgMinSug = true;
					}					
				}				
										
			inicializaListas();
			
			flagNotRule   = inversion.isFlagNotRule();
			flagMin_E5_E4 = inversion.isFlagMin_E5_E4();
			
			responseJSON = Response.ok(proyectList_A).build();
		}			
	
		public void updateByFiltering(TiendaRequest request)
		{	    		    	
	    	String riskCad 			= request.getRiskCad();
	    	String termCadIni 		= request.getTermCadIni();
	    	String flagRisk 		= request.getFlagRisk();
	    	String destiny_values 	= request.getDestiny_values();
	    	String genderCad 		= request.getGenderCad();
	    	String ammountCadFrom 	= request.getAmmountCadFrom();
	    	String ammountCadTo 	= request.getAmmountCadTo();
	    	
	    	System.out.println( "flagRisk: " + flagRisk );
	    	
	    	String genderStr =	"";
	    	
	    	if( genderCad !=null && genderCad.trim().length()>0)
	    	{
	    		
	    		if( genderCad.indexOf("2")!=(-1))
	    		{	    			
	    			genderStr += "M";
	    		}
	    		
	    		if( genderCad.indexOf("1")!=(-1))
	    		{
	    			genderStr += "H";
	    		}
			}
	    	
	    	destiny_str = destiny_values;
	    	
	    	risk_str = riskCad;
	    	
	    	// CREA FILTRO
	    	
	    	String strQuery = "term:"+termCadIni +"||risk:"+riskCad+        "||gender:"+ genderStr+"||typeSearch:"+typeSearch+"||between:"+ammountCadFrom+"_"+ammountCadTo+"||previousType:"+previousType+"||destinyValues:"+destiny_values;
	    	
	    	lastFilter = strQuery;
	    	
			cuenta = new CuentasClienteIMP();
			cuenta.setSesion(sesion);
			cuenta.init();
			
			saldoTotal      = cuenta.getSaldoTotal();
			listInvAccounts = cuenta.getListInvAccounts();
			
			inversion = new SAFIInvestmentIMP();
			inversion.setSaldoTotal(saldoTotal);
			inversion.setListInvAccounts(listInvAccounts);
			inversion.init();
	    	
	    	inversion.cargaListaTienda(strQuery, sesion.getProspectus_id(), sesion.getCompany_id(), flagRisk+"",naturalPerson.getSafi_client_id(), listInvAccounts.get(0).getAccount() );
	    	
	    	scriptStatus = inversion.getScriptStatus();
	    	
	    	if( !hold_selected )
	    	{	    	
		    	calculaInversionPorProyecto(false);
		    	
	    		proyectList = inversion.getProyectList();
    		
	    	} else {	    			    		
	    		
	    		calculaInversionPorProyectoManteniendoSeleccionados(); 	    			    		
	    	}
	    	
	    	filter = inversion.getFilter();
    		
    		System.out.println( "*Antes updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
    		asignaListForInvest();
    		
    		System.out.println( "*Despues updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
	    	InvestmentFilter filterInvestment = new InvestmentFilter();
	    	
	    	InvestmentFilterPK fpk = new InvestmentFilterPK();
	    	
	    	fpk.setCompany_id(sesion.getCompany_id());
	    	fpk.setProspectus_id(sesion.getProspectus_id());
	    	
	    	filterInvestment.setFilter(strQuery);
	    	filterInvestment.setFilter_date_used(new Date());
	    	filterInvestment.setPk(fpk);
	    	
	    	investmentFilterServiceImp.addFilterUsed(filterInvestment);
	    	
	    	inicializaListas();
	    	
			responseJSON = Response.ok(proyectList_A).build();
		}

		public void updateByFiltering(ActionEvent e)
	    {
	    	
	    	Map<String , String > map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    	
	    	String riskCad 			= map.get("cadena1");
	    	String termCadIni 		= map.get("cadena2");
	    	String flagRisk 		= map.get("flagRisk");
	    	String destiny_values 	= map.get("destiny_str");
	    	String genderCad 		= map.get("cadenaGender");
	    	String ammountCadFrom 	= map.get("inputFromBtn");
	    	String ammountCadTo 	= map.get("inputToBtn");
	    	
	    	System.out.println( "flagRisk: " + flagRisk );
	    	
	    	//String phraseCad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inputPhrase");
	    	// String getStatus=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadena3");
	    	
	    	String genderStr	=	"";
	    	
	    	if( genderCad !=null && genderCad.trim().length()>0)
	    	{
	    		
	    		if( genderCad.indexOf("2")!=(-1))
	    		{	    			
	    			genderStr += "M";
	    		}
	    		
	    		if( genderCad.indexOf("1")!=(-1))
	    		{
	    			genderStr += "H";
	    		}
			}
	    	
	    	destiny_str = destiny_values;
	    	
	    	risk_str = riskCad;
	    	
	    	// CREA FILTRO
	    	
	    	String strQuery = "term:"+termCadIni +"||risk:"+riskCad+        "||gender:"+ genderStr+"||typeSearch:"+typeSearch+"||between:"+ammountCadFrom+"_"+ammountCadTo+"||previousType:"+previousType+"||destinyValues:"+destiny_values;
	    	
	    	lastFilter = strQuery;
	    	
	    	inversion.cargaListaTienda(strQuery, sesion.getProspectus_id(), sesion.getCompany_id(), flagRisk+"",naturalPerson.getSafi_client_id(), listInvAccounts.get(0).getAccount() );
	    	
	    	scriptStatus = inversion.getScriptStatus();
	    	
	    	if( !hold_selected )
	    	{	    	
		    	calculaInversionPorProyecto(false);
		    	
	    		proyectList = inversion.getProyectList();
    		
	    	} else {	    			    		
	    		
	    		calculaInversionPorProyectoManteniendoSeleccionados(); 	    			    		
	    	}
	    	
	    	filter = inversion.getFilter();
    		
    		System.out.println( "*Antes updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
    		asignaListForInvest();
    		
    		System.out.println( "*Despues updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
	    	InvestmentFilter filterInvestment = new InvestmentFilter();
	    	
	    	InvestmentFilterPK fpk = new InvestmentFilterPK();
	    	
	    	fpk.setCompany_id(sesion.getCompany_id());
	    	fpk.setProspectus_id(sesion.getProspectus_id());
	    	
	    	filterInvestment.setFilter(strQuery);
	    	filterInvestment.setFilter_date_used(new Date());
	    	filterInvestment.setPk(fpk);
	    	
	    	investmentFilterServiceImp.addFilterUsed(filterInvestment);
	    	
	    	inicializaListas();
	    	
	    }
	    	    	    
	    public void filteringAfterIndivInversion()
	    {	    	
// CREA FILTRO
	    	
	    	String strQuery = lastFilter;
	    	
	    	inversion.cargaListaTienda(strQuery, sesion.getProspectus_id(), sesion.getCompany_id(), flagRisk+"",naturalPerson.getSafi_client_id(), listInvAccounts.get(0).getAccount() );
	    	
	    	scriptStatus = inversion.getScriptStatus();
	    	
	    	if( !hold_selected ){
	    	
		    	calculaInversionPorProyecto(false); //
	    		proyectList = inversion.getProyectList();
    		
	    	}else{
	    		
	    		//List<ItemLoanList> proyectListTmp = inversion.getProyectList();
	    		
	    		calculaInversionPorProyectoManteniendoSeleccionados(); //
	    		
	    		
	    	}
	    	
	    	filter = inversion.getFilter();
    		
    		System.out.println( "*Antes updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
    		asignaListForInvest();
    		
    		System.out.println( "*Despues updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
    		
	    	InvestmentFilter filterInvestment = new InvestmentFilter();
	    	
	    	InvestmentFilterPK fpk = new InvestmentFilterPK();
	    	
	    	fpk.setCompany_id(sesion.getCompany_id());
	    	fpk.setProspectus_id(sesion.getProspectus_id());
	    	
	    	filterInvestment.setFilter(strQuery);
	    	filterInvestment.setFilter_date_used(new Date());
	    	filterInvestment.setPk(fpk);
	    	
	    	investmentFilterServiceImp.addFilterUsed(filterInvestment);
	    	
	    	inicializaListas();	    	    	
	    }	  	
	    
		public void changeAccount(){
			
			if(cuentaActual != null && cuentaActual != "")
			{
				tagAccount = cuentaActual;
				
				try {
					
					for (InvestorsAccounts iterElement : listInvAccounts) 
					{
						 if(iterElement.getAccount().equals(cuentaActual))
						 {
							 cuentaActual = iterElement.getAccount();
							 saldoActual = iterElement.getSaldo();
							 break;
						 }
					}
					
				}catch (Exception e) 
				{
					log.info("Se genero un error al hacer la suma del saldo de cuentas");
				}
				
			} else {
				
				tagAccount = "Ninguna cuenta";
				cuentaActual = "";
				saldoActual = getTotalCreditOnAccounts();				
			}
			
			if(saldoActual > montoLimiteMaxSugerido)
			{
				displayMsgMaxSug = true;
				
			} else if( saldoActual < montoLimiteMinSugerido ) {
				
				displayMsgMinSug = true;
			}			
		}
			
		public void setActualProyectForPopUp(ActionEvent e)
		{	
			String invertionValue = e.getComponent().getAttributes().get("investmentbite2").toString();
			String proyectId = e.getComponent().getAttributes().get("proyectId2").toString();
			String proyectLoanId = e.getComponent().getAttributes().get("proyectloanid2").toString();
			String prospectusId = e.getComponent().getAttributes().get("prospectusid2").toString();
			String companyId = e.getComponent().getAttributes().get("companyid2").toString();
			
			double investment_Bite = Double.parseDouble(invertionValue);
			int proyect_id = Integer.parseInt(proyectId);
			int proyect_loan_id = Integer.parseInt(proyectLoanId);
		    int prospectus_id = Integer.parseInt(prospectusId);
		    int company_id = Integer.parseInt(companyId);	
			
			ProyectLoanPK key = new ProyectLoanPK(proyect_loan_id,proyect_id,prospectus_id,company_id);
			this.selectedRowProyectLoan = proyectLoanService.getProyectLoanById(key);
			this.ammountFounedScooped = investment_Bite;
			this.proyectFundingByInvestor = proyectFundingService.getproyectbyInvestor(this.PID_cliente, proyectLoanId);
			
		}	
		
		public String getHtmlCodeForTermFilter()
		{
			String htmlCode = new String("");
			int dividendoPar = 0;
			
			if( onlyTermOfProyectLoan != null )
			{
			
				for(byte x: onlyTermOfProyectLoan)
				{
					if(dividendoPar%2==0) htmlCode = htmlCode+"<tr>";
					htmlCode = htmlCode + " <td><input type='checkbox' name='checkbox' id='chkTF"+x+"'/></td><td><span id='TF"+x+"'>"+x+" Meses.</span></td>";
					dividendoPar = dividendoPar + 1;
					if((dividendoPar%2)==0.0){
						htmlCode = htmlCode+"</tr> ";
					}
				}
				if((dividendoPar%2.0)!=0.0){
					return htmlCode+"</tr>";
				}
				else{
					return htmlCode;
				}
			
			}else{
				return "";
			}
		}
		
		public String getDirectionalLink(ProyectLoanPK proyect,int plId,Integer InvId,String actualCredit){
			String proyectLoanId = Integer.toString(plId);
			List<ProyectFunding> proyectFundingByInvestor = proyectFundingService.getproyectbyInvestor(InvId.toString(), proyectLoanId);
			ProyectLoan actualProyect=proyectLoanService.findProyect(proyect);
			
			if (actualProyect.getAmount_founded()>=actualProyect.getAmmount()) return "";
		    if (proyectFundingByInvestor.size()>=2) return "";
			if (Double.parseDouble(actualCredit)<=0.00) return "";
		    if (actualProyect.getDaysLeft()>(long)0) return "#popupdata";
		    if ((actualProyect.getAmmount()-actualProyect.getAmount_founded())<=0.00) return "";
		    else return "";
		}
		
		public String getEnabledForDetailBtn(ProyectLoanPK key, Integer InvID){
				
			List<ProyectFunding> amount = this.proyectFundingService.getMaxProyectFundingByInvOnProyect(key, InvID);
				
			if(amount.size()>0)
			{
				return "false";
			}
			else{
				return "true";
			}
			
		}

		public List<Double> getListOfPercentByKuboScore() {
			return listOfPercentByKuboScore;
		}
		
		public Double getPorcentByStatus(Integer status){
			int total=this.proyectList.size();
			Double min = 0.0;
			for(ItemLoanList p : proyectList){
				if(p.getStatus_id().equals(status)){
					min++;
				}
			}	
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
			Double valor = 0d;
			if(total>0)
				valor = (((100*total)-100*(total-min))/total);
			if(valor>100.00)
				return 100.00;
			else 
				return Double.valueOf(formateador.format(valor)); 
			
		}
		
		public boolean getCheckedByStatus(Integer status){
			
			boolean flag = true;
			
			return flag;
		}
		
		public boolean getCheckedByCalif(String calif){
			
			boolean flag = true;
			
			return flag;
			
		}
		
		public Double getPorcentByKuboScore(String letterScore){
			int total= this.proyectList.size();
			Double min= 0.0;
			for(ItemLoanList p : proyectList){
				if(p.getKubo_score_a().equals(letterScore)){
					min ++;
				}
			}		
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
			Double valor = 0d;
			if(total>0)
				valor = (((100*total)-100*(total-min))/total);
			if(valor>100.00)
				return 100.00;
			else 
				return Double.valueOf(formateador.format(valor)); 
		}
		
		public Integer getNumByKuboScore(String letterScore){
			
			Integer min= 0;
			
			for(ItemLoanList p : proyectList){
				
				if(p.getKubo_score_a().equals(letterScore)){
					min ++;
				}
				
			}		
			
			return min;
			
		}
		
		public String getFrequencyByProyect(Integer freq){
			if(freq == 1){
				return "Semanas";
			}
			else if(freq == 2){
				return "Catorcenas";
			}
			else if(freq == 3){
				return "Quincenas";
			}
			else if(freq == 4){
				return "Meses";
			}
			else{
				return "desconocido";
			}
		}
			
		public final void initSearch(ActionEvent evento_AJAX)
		{
			proyect_loan_SEARCH_TOKEN = (String) evento_AJAX.getComponent().getAttributes().get("proyectAtrr").toString();
			
			request   = RequestContext.getCurrentInstance();		
			faces     = FacesContext.getCurrentInstance();
			
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			
			
			if (sesion.getArea().equals('I')) {
				
				navigation = null;
				
				navigationinvest = (NavigationInvest) resolver.getValue(elContext, null, "navigationInvest");
				
				evento_AJAX.getComponent().getAttributes().put("seccionInv", "summary");
				evento_AJAX.getComponent().getAttributes().put("showDisplayInv", "true");
				
				navigationinvest.setFlagDispEdoCuenta(true);
				
				summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
				summarysesion.setTypeLog("SOL");
				summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
				
				summarysesion.setShowInvestPnl(true);
				
				navigationinvest.changePage(evento_AJAX);
				
				request = RequestContext.getCurrentInstance();
				request.addPartialUpdateTarget("pnlContentInvest");	
				request.addPartialUpdateTarget("panelContentListProyect");
				
			} else {	
				
				summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
				summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
				
				navigation = "resumen";
			}
			
		}
		
		public void goToLogs(ActionEvent e)
		{
			String value=(String) e.getComponent().getAttributes().get("proyectAtrr").toString();	
			String valueLog=(String) e.getComponent().getAttributes().get("gotypeLog").toString();
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "searchSummaySession");
			
			summarysesion.setSearchSummary(null);
			summarysesion.setSearchSummary(value);
			summarysesion.setTypeLog(valueLog);
			
			navigation=null;
			
			NavigationInvest navigationinvest = (NavigationInvest) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "navigationInvest");
			
			e.getComponent().getAttributes().put("seccionInv", "logs");
			
			navigationinvest.changePage(e);
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addPartialUpdateTarget("pnlContentInvest");	
			requestContext.addPartialUpdateTarget("panelContentListProyect");				
		}
		
		public void getGarantia()
		{									
				if (valuesforGarantia != null)
				{ 
					////System.out.println(valuesforGarantia);
					
					ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
				
					Integer proyect_loan_id		= Integer.parseInt( valuesforGarantia.split("::")[0] );
					Integer kuboFondeoInv_id	= Integer.parseInt( valuesforGarantia.split("::")[1] );
				
					
					ProyectLoanActiveInSafi active = proyectLoanService.getProyectLoanActiveInSafiByID(kuboFondeoInv_id,proyect_loan_id);
					
					ticketStr = getHtml("Garantia");
					
					ticketStr=ticketStr.replace("###NOMBRE###", naturalPerson.NombreCompletoNPM());
					ticketStr=ticketStr.replace("###RFC###", naturalPerson.getMx_rfc());
					ticketStr=ticketStr.replace("###CLIENTE###", naturalPerson.getSafi_client_id());
					ticketStr=ticketStr.replace("###NUMCREDITO###", active.getSafi_credit_id()+"");
					ticketStr=ticketStr.replace("###NUM_INVER###", active.getFondeokuboid()+"");
					
					ticketStr=ticketStr.replace("###CUENTA###",active.getCuentaAhoID()+"" );
					
					
					Double porcentaje = Math.floor(active.getPorcentajefondeo()*100)/100;
					
					ticketStr=ticketStr.replace("###PORCENTAJE###",porcentaje+"%" );
					
					ticketStr=ticketStr.replace("###FECHAINICIO###", formatStr5.format(active.getFechaInicioInv()));
					
					ticketStr=ticketStr.replace("###FECHAFIN###", formatStr5.format( active.getFechaVencimientoInv()));
					
					ticketStr=ticketStr.replace("###MONTOINV###", dec.format(active.getMontofondeoinv())+"");
					
					ticketStr=ticketStr.replace("###TASA_INV###", active.getRate_investor()+"%");
					
					ticketStr=ticketStr.replace("###RIESGO_KUBO###", active.getKubo_score_a()+active.getKubo_score_b()+"");
					
					ticketStr=ticketStr.replace("###FREQ###", active.getFrequency().getName()+"");
					
					ticketStr=ticketStr.replace("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofipo")+"");
					
					ticketStr=ticketStr.replace("###RFC_KUBO###", recurso.getString( "rfc_kubo_sofipo" )+"");
					
					ticketStr=ticketStr.replace("###TELEFONO###", recurso.getString("Kubo_telefono")+"");
					
					ticketStr=ticketStr.replace("###FECHA_PAGO###",formatStr5.format(active.getFechaInicioInv())+"");
					
					String sTotal = (active.getMontofondeoinv()+"");
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
			         
			         sTotal = (active.getAmmount()+"");
					 centavos ="";
					 pesos = sTotal.split("\\.")[0];
				         
				         if(sTotal.split("\\.").length > 1)
				        	 centavos = sTotal.split("\\.")[1];
				         
				         if(centavos.length()<2){
				        	 centavos += "0";
				         }
				         
				         letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
				         
				         ticketStr = ticketStr.replaceAll("###IMPORTE_CREDITO_LETRA###", letter );
						
				         ticketStr = ticketStr.replaceAll("###CENTAVOS_CREDITO###", centavos );
				         
				         ticketStr = ticketStr.replaceAll("###MONTO_CRED###","\\"+ dec.format( active.getAmmount() ) );
			         
			         List<AmortizacionInversionista> lst = amortInverService.getAmortizacionInversionistaListByIdInver( active.getFondeokuboid());
			         
			         String str = "";
			         Double tot = 0D;
			         int count = 0;
			         
			         if(lst != null && lst.size()>0 ){
			        	 
			        	 str += "<tr>";
			        	 str += "<td style='text-align:center;vertical-align: top;'>FECHA</td>";
			        	 str += "<td style='text-align:center;padding-left: 8px;vertical-align: top;'>CAPITAL</td>";
			        	 str += "<td style='text-align:center;padding-left: 8px;vertical-align: top;'>INTERÉS</td>";
			        	 str += "<td style='text-align:center;padding-left: 8px;vertical-align: top;'>RETENCIÓN <br /> DE ISR</td>";
			        	 str += "<td style='text-align:center;padding-left: 8px;vertical-align: top;'>TOTAL</td>";
			        	 str += "<tr>";
			        	 
			        	 for( AmortizacionInversionista amort : lst ){
			        		 Double total = 0D;
			        		 str += "<tr>";
			        		 	str += "<td style='text-align:center;' >"+formatStr1.format(amort.getFechaExigible())+"</td>";
			        		 	str += "<td style='text-align:right;' >\\"+dec.format( amort.getCapital() )+
			        		 			"</td>";
			        		 	str += "<td style='text-align:right;' >\\"+dec.format( amort.getInteresGenerado() )+
			        		 			"</td>";
			        		 	str += "<td style='text-align:right;padding-right: 30px;' >\\"+dec.format( amort.getInteresRetener() )+
			        		 			"</td>";
			        		 
			        		 	total = (amort.getCapital() + amort.getInteresGenerado() ) - amort.getInteresRetener();
			        		 	
			        		 	tot += total;
			        		 	
			        		 	str += "<td style='text-align:right;' >\\"+ dec.format( total ) +"</td>";
			        		 
			        		 str += "</tr>";
			        		 
			        		 count++;
			        		 
			        	 }
			        	 
			         }
			         
			         ticketStr = ticketStr.replaceAll("###DETALLE###", str );
			         ticketStr = ticketStr.replaceAll("###TOTAL_PAGADO###", "\\"+dec.format( tot ) );
			         ticketStr=ticketStr.replace("###NUM_PAGOS###", count+"");
			         
			         
			         String sello = ""+naturalPerson.getSafi_client_id()+"|"+active.getCuentaAhoID()+"|"+active.getFondeokuboid()+"|"+active.getSafi_credit_id()+"|"+formatStr.format(active.getFechaInicioInv())+"|"+active.getAmmount()+"|"+active.getMontofondeoinv()+"|"+porcentaje;
						
					ticketStr = ticketStr.replaceAll("###SELLO_DIGITAL###", sello );
			         
				}

				valuesforticket = null;

		}
		
		public void validaMontoAInvertir(){
			
			//System.out.println("validaMontoAInvertir: " + ammoutToInv + "  maximo a invertir: " + montoTotal );
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			
			if( ammoutToInv > montoTotal){
				
				//System.out.println("Monto mayor !!");
				
				requestContext.addCallbackParam("hasError", true );
				requestContext.addCallbackParam("montoSugerido", montoTotal );
				requestContext.addCallbackParam("msgInv", " El moto que desea invertir supera el saldo disponible de su cuenta " );
				
			}else if( ammoutToInv < montoMinG){
				//System.out.println("Monto mayor !!");
				
				requestContext.addCallbackParam("hasError", true );
				requestContext.addCallbackParam("montoSugerido", montoMinG );
				requestContext.addCallbackParam("msgInv", " El moto que desea invertir es menor al monto mínimo permitido" );
			}else{
				
				calculaInvestmentBite();
				
				requestContext.addCallbackParam("hasError", false );
				requestContext.addCallbackParam("montoSugerido", "" );
				requestContext.addCallbackParam("msgInv", "" );
				
			}
			
			temp ++;
			
		}
			
		public String montoMaxStr()
		{
			Locale locale = new Locale("es","mx");
			java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
			
			return dec.format(montoMaximo);		
		}
		
		public String montoinvertirStr(){
			
			Locale locale = new Locale("es","mx");
			java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
			
			return dec.format(monto_a_invertir);
		}
		
		public void calculaInvestmentBiteInProyectSel(  ){
			
			System.out.println( "-- calculaInvestmentBiteInProyectSel" );
			calculaInversionPorProyecto( true );
			
		}
		
		public void calculaInvestmentBite(  ){
			
			calculaInversionPorProyecto(false);
			
			
			/*  
			
			//InvestorsAccounts account = null;
			
			Double monto_a_invertir_acum = 0D;
			
			SystemParamPK sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(55);//Monto mínimo que se puede invertir en cada proyecto
			
			SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double montoMinGRAL = Double.parseDouble( sys.getValue() );

			montoMinG = montoMinGRAL;
			
			
			sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(56);//Porcentaje del monto otorgado que sirve como tope máximo del monto a invertir
			
			sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double porcMaxPry = Double.parseDouble( sys.getValue() );
			
			maxPorcPryG = porcMaxPry;
			
			sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(57);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir
			
			sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double porcMaxSaldo = Double.parseDouble( sys.getValue() );
			
			Hashtable<String,List<ProyectFunding> > hTFunding = generaHashFunding();
			
			sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(74);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (E5)
			
			sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double porcMaxSaldoProyE5 = Double.parseDouble( sys.getValue() );
			
			sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(76);//Porcentaje del saldo total que sirve como tope máximo del monto a invertir para proyectos de alto riesgo (E4)
			
			sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double porcMaxSaldoProyE4 = Double.parseDouble( sys.getValue() );
			
			sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(77);//Monto mínimo para invertir en proyectos de alto riesgo(E4,E5)
			
			sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			Double montoMinE4E5 = Double.parseDouble( sys.getValue() );
			
			montoMinE4E5G = montoMinE4E5;
//			
//			Double porcMaxPry = 0.0D;
//			Double porcMaxSaldo = 0.0D;
//			
//			if(saldoTotal >= 250 && saldoTotal <= 10000 )
//			{
//				porcMaxPry 		= 1.0D;
//				porcMaxSaldo 	= 2.5D;
//				
//			}else if(saldoTotal > 10000 && saldoTotal <= 100000 ) 
//			{
//				porcMaxPry 		= 1.5D;
//				porcMaxSaldo 	= 2.5D;
//				
//			}else if(saldoTotal > 100000 && saldoTotal <= 250000) 
//			{
//				porcMaxPry 		= 1.5D;
//				porcMaxSaldo 	= 2.5D;
//				
//			}else if(saldoTotal > 250000 && saldoTotal <= 500000) 
//			{
//				porcMaxPry 		= 1.5D;
//				porcMaxSaldo 	= 2.5D;
//				
//			}else if(saldoTotal > 500000 ) 
//			{
//				porcMaxPry 		= 1.5D;
//				porcMaxSaldo 	= 2.5D;
//				
//			}
//			
			if(sesion.getArea().toString().equals("I"))
			{
			
//				for(int i = 0 ; i < listInvAccounts.size() ; i++ )
//				{
//					
//					if(tagAccount.equals( listInvAccounts.get( i ).getAccount() ))
//					{
//					
//						account =  listInvAccounts.get(i );
//					
//					}
//					
//				}
//				
//				
//				
//				if(account != null)
//				{
//					
//					//System.out.println( " ---- "+account.getSaldo()+ "   ---  " + Double.parseDouble( (proyectList.size())  + "") );
//					
//					//monto_a_invertir = account.getSaldo();
//					
//					monto_a_invertir_acum = 0D;
				
				if(ammoutToInv!=null){
				
					monto_a_invertir_temp 	= ammoutToInv;
					
					montoSaldoG = monto_a_invertir_temp;
					
					investmentBiteVAL =  ammoutToInv / Double.parseDouble( (proyectList.size())  + "");
					
				}else{
					
					//System.out.println( " ---- account == null   ---  " + Double.parseDouble( (proyectList.size())  + "") );
					investmentBiteVAL = 251D;
					
				}
				
				if(sesion.getArea().toString().equals("I")){
					
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
					
					for( ItemLoanList item :  proyectList)
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
					// System.out.println("Lista de Proyectos: "+proyectList.size());
					
					while( monto_a_invertir_temp >0 && y<3 ){
						
						if(y>0)
							flgfirst = true;
						
						y++;
						
						// System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+y );
						// System.out.println( "Vuelta No. "+y +"  -- monto disponible: "+monto_a_invertir_temp );
						
						for( ItemLoanList item :  proyectList){
							
							Double biteAmmount = 0D;
							Double montoMin = 0D;
							
							Double maxProyectAmmount =  (item.getAmmount()*porcMaxPry)/100;
							
							Double max_monto_a_fondear = maxProyectAmmount>maxAmmountBalance?maxAmmountBalance:maxProyectAmmount;
							
							String itemKuboScore = item.getKubo_score_a()+item.getKubo_score_b();
							
							if( ( itemKuboScore.equals("E4") || itemKuboScore.equals("E5") ) ){
							
								if(itemKuboScore.equals("E4")){
									
									max_monto_a_fondear = maxProyectAmmount>maximoInvBySaldoPryE4?maximoInvBySaldoPryE4:maxProyectAmmount ;
									
								}else if( itemKuboScore.equals("E5") ) {
									
									max_monto_a_fondear = maxProyectAmmount>maximoInvBySaldoPryE5?maximoInvBySaldoPryE5:maxProyectAmmount ;
									
								}
								
								montoMin = montoMinE4E5;
								
							}else{
								
								montoMin = montoMinGRAL;
								
							}
							
							if( maxProyectAmmount > max_monto_a_fondear){
								maxProyectAmmount = max_monto_a_fondear;
							}
							
							// System.out.println( "-- proyecto: "+item.getProyect_loan_id() + " maximo a invertir:  " + maxProyectAmmount + " Riesgo: " + itemKuboScore + " maximo a inverti (saldo) "+max_monto_a_fondear );
							
							if( biteAmmountIni > max_monto_a_fondear ){
								
								if( max_monto_a_fondear < montoMin ){
									
									// System.out.println( "Sin Fondeo: el monto que le toca no alcanza el monto mínimo -- "+max_monto_a_fondear );
									continue;
									
								}else{
								
									biteAmmount = max_monto_a_fondear;
									// System.out.println( "1cachito: "+ biteAmmount);
								
								}
								
							}else{
								
								biteAmmount = biteAmmountIni;
								// System.out.println( "2cachito: "+ biteAmmount);
							}
							
//							if( item.getAmmountPreviousFounded() != null && flagListFunding ){
//							
								//List<ProyectFunding> lstFunding =  proyectFundingService.getproyectbyInvestor( (sesion.getProspectus_id()+"") , (item.getProyect_loan_id()+"") );
//								
							String key = item.getProspectus_id() + "::"+ item.getProyect_loan_id();
							
							List<ProyectFunding> lstFunding =  hTFunding.get(key);
							
//								flagListFunding = false;
								
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
												// System.out.println("..monto no suficiente: monto acumulado: "+sum+"  maximo montoa a fondear: "+max_monto_a_fondear+" -  monto calculado: "+m);
												continue;
											}
											
											
										}
									
									}else{
										// System.out.println("fondeado previamente");
										
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
										// System.out.println("Sin Fondeo: el monto disponible no alcanza el monto mínimo --- "+item.getAvailableAmmount());
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
										// System.out.println("sin Fondeo, sobrepasaría monto saldo máximo: "+( item.getInvestment_bite()+biteAmmount ));
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
								
								monto_a_invertir_temp = monto_a_invertir_temp - biteAmmount;
								
								// item.setAvailableAmmount(  item.getAvailableAmmount()- biteAmmount);
								
								Double t = item.getInvestment_bite()+biteAmmount;
								
//								if( t > item.getAvailableAmmount() || !flgfirst ){
									
									t = (Math.floor(t*100))/100;
									
									item.setInvestment_bite(t);
									
									// System.out.println("Cachito asignado al proyecto 1: "+biteAmmount);
									
									monto_a_invertir_acum += biteAmmount;
									 
									// System.out.println("Acumulado de inversión al proyecto 1: "+item.getInvestment_bite() );
								
//								}else{
//									
//									System.out.println( "No se fondea en esta vuelta: sobre pasa el monto disponible" );
//									//continue;
//									
//								}
								
							}else{
								
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
									
									
									
								}else{
								
										// System.out.println( "monto no suficiente: disponible- "+monto_a_invertir_temp+"  cachito"+ biteAmmount);
										continue;
										
								}
								
							}
							
						}
					}
					
				}
				
				/* 
				 * Se remueven todos los proyectos en los que no se puede invertir
				 * --  /
				
				//System.out.println("Inicia: " + proyectList.size() );
				
				Iterator<ItemLoanList> itr = proyectList.iterator();
				
				int r = 0;
				int previous = 0;
				
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
					    	previous ++;
					    	
					    }else{
					    	item.setBlnChck(false);
					    	lstFondeadoPrev_SinInv.add( item );
					    }
						
						
					    itr.remove();
					    
					}
					
				}
				
				//System.out.println("primer recorte: " + proyectList.size() );
				
				Iterator<ItemLoanList> itr2 = proyectList.iterator();
				
				while ( itr2.hasNext()) 
				{
					
					ItemLoanList item = itr2.next();

				    if (item.getInvestment_bite() == 0D) {
				    	temp.add( item );
				       itr2.remove();
				       r++;
				    }else{
				    	item.setBlnChck(true);
				    }

				}
				
				///System.out.println("segundo recorte: " + proyectList.size() );
				
				for( ItemLoanList t : lstFondeadoPrev ){
					
					proyectList.add(t);
					
				}
				
				asignaListForInvest();
				
				
				
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
							
								proyectList.add(t);
								
							}
							
						}else{
							
							proyectList.add(t);
							
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
							
								proyectList.add(t);
								
							}
							
						}else{
					
							proyectList.add(t);
					
					}
					
				}
				
				//System.out.println("prviamente fondeados: "+previous);
				//System.out.println(" se removieron "+r+" elementos");
				
				//System.out.println("finalizó con: " + proyectList.size() );
				
				
				monto_a_invertir = monto_a_invertir_acum;
				
				
				
			}
			
			
			*/
			
		}
		
		public void pruebaTienda()
		{			
			//Normalizacion de proyect_funding
			
			//List<ProyectLoan> lstProyectForFunding =  proyectLoanService.getProyectLoanListTiendaNotInFunding();
			/*
			if(lstProyectForFunding != null )
			{
				for(ProyectLoan pl :lstProyectForFunding)
				{
					if(pl != null){
						proyectLoanService.spSetOnProyectFunding(pl.getProyectloanPk().getProyect_loan_id(), pl.getProyect().getProyectoPk().getProyect_id(),
							pl.getProyectloanPk().getCompany_id(), pl.getProyectloanPk().getProspectus_id(), 
							0, pl.getAmmount()-pl.getAmount_founded());
					}
				}
			}
			*/
			// Actualiza Tasa Inversionista
			List<ProyectLoan> lstProyectTienda = proyectLoanService.getProyectLoanList();	
			
			if( lstProyectTienda != null && lstProyectTienda.size()>0 )
			{
				for( ProyectLoan pl : lstProyectTienda )
				{
					if( pl.getRate_investor() == null || pl.getRate_investor() == 0D )
					{						
						TasasAcreditado tasa =  tasaacreditadoservice.getTasaAcreditadoByRate(pl.getRate());
						
						pl.setRate_investor(tasa.getRate_investor());
						
						proyectLoanService.update(pl);						
					}
				}
			}						
		}
		
		public void generaListForInv(AjaxBehaviorEvent e){
			RequestContext requestContext = RequestContext.getCurrentInstance();
			// System.out.println(" -*-*- Entrando a invertir generaListForInv -*-*- ");
			
			ItemLoanList item = (ItemLoanList) e.getComponent().getAttributes().get("proyectloanItem");
			
			boolean flagRestaInv = false;
			
			if( newBiteInv != null && newBiteInv.trim().length()>0  && !item.isBlnChck() && item.getInvestment_bite() != null && Double.parseDouble(newBiteInv.trim()) != 0D){
				ammountFoundedInv -= Double.parseDouble( newBiteInv.trim() );
				flagRestaInv = true;
				
				//System.out.println(" Monto que se elimino1: "+newBiteInv);
			}
			
			if(item.isBlnChck()){
				proyectListForInvesInd.add(item);
				item.setInvestment_bite(0D);
				ammountFoundedInv += item.getInvestment_bite();
				//System.out.println("Monto despues de sumar: "+ammountFoundedInv+"   monto a sumar: "+item.getInvestment_bite());
				requestContext.addCallbackParam("displayInput", true);
				
				Double roundVal = (double)Math.round((item.getInvestment_bite())*100)/100;
				
				requestContext.addCallbackParam("montoFunded", roundVal);
				requestContext.addCallbackParam("proyect_loan_id", item.getProyect_loan_id());
				
			}else{
				proyectListForInvesInd.remove(item);
				
				if(!flagRestaInv){
					ammountFoundedInv -= item.getInvestment_bite();
					// System.out.println(" Monto que se elimino2: "+item.getInvestment_bite());
					
					if( (item.getKubo_score_a()+item.getKubo_score_b()).equals("E5") ){
						
						montoDisponibleEn_E5 = montoDisponibleEn_E5 + item.getInvestment_bite();
						
					}
					
				}
				
				requestContext.addCallbackParam("displayInput", false);
				requestContext.addCallbackParam("proyect_loan_id", item.getProyect_loan_id());
			}
			
			////System.out.println( item.isBlnChck()+"_"+ item.getProyect_loan_id()+"_"+item.getProyect_id()+"_"+item.getProspectus_id() );
		}
		
		public void asignaBite(AjaxBehaviorEvent e)
		{			
			String valAnt = "";
			String itemKuboScore = "";
			Double maxAmmountTemp = ammountFoundedInv;
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			ItemLoanList item = (ItemLoanList) e.getComponent().getAttributes().get("proyectloanItem");
			
			///System.out.println("nuevo valor : " + item.getInvestment_bite() + " valAnt: " + newBiteInv );
			
			valAnt = newBiteInv;
			
			if(item.getInvestment_bite() == null || item.getInvestment_bite() == 0 ){
				
				if( valAnt != null && valAnt.trim().length()>0 ){
					
					ammountFoundedInv = ammountFoundedInv - Double.parseDouble(valAnt) ;
					proyectListForInvesInd.remove(item);
					item.setInvestment_bite(0D);
					proyectListForInvesInd.add(item);
					
					if( (item.getKubo_score_a()+item.getKubo_score_b()).equals("G1") || (item.getKubo_score_a()+item.getKubo_score_b()).equals("F1") ){
						montoInvertido_F_G = montoInvertido_F_G - Double.parseDouble(valAnt);
					}
					
					if( (item.getKubo_score_a()+item.getKubo_score_b()).equals("E5") ){
						montoDisponibleEn_E5 = montoDisponibleEn_E5 + Double.parseDouble(valAnt);
					}
					
				}
				
				newBiteInv =  null;
				
				return;
			}
			
			
			
			if( valAnt != null && valAnt.trim().length()>0 ){
				maxAmmountTemp = ammountFoundedInv - Double.parseDouble(valAnt) ;
			}
			
			newBiteInv = item.getInvestment_bite() + "" ;
			
			if( newBiteInv.trim().length()==0)
				return;
			
			itemKuboScore = item.getKubo_score_a()+item.getKubo_score_b();
			
			System.out.println("Proyecto Seleccionado: " + item.getProyect_loan_id() + " Riesgo: " + itemKuboScore );
			
			Double newVal = 0D;
			String msgInv = "";
			Double monto_sugerido = 0D;
			
			Double montoDisponibleEn_E5_ini = inversion.initMontoDisponibleEn_E5();
			
			boolean flagNotInv1 = false;
			
			try
			{
				newVal = Double.parseDouble(newBiteInv);
			
			}catch(Exception e1 ){
				
				msgInv = "Cantidad Incorrecta ";
				monto_sugerido = 0D;
				flagNotInv1 = true;
				
				requestContext.addCallbackParam("hasError", (flagNotInv1));
				requestContext.addCallbackParam("msgInv", msgInv);
				requestContext.addCallbackParam("montoSugerido", monto_sugerido);
				requestContext.addCallbackParam("proyect_loan_id",item.getProyect_loan_id());
				//System.out.println(msgInv);
				return ;
			}
			
			
			//System.out.println( "  newVal = " + newVal + " maxAmmountTemp = " + maxAmmountTemp + "  montoSaldoG = " + montoSaldoG );
			
			Double montoMinThis = 0D;
			Double maximoInvBySaldoPryE4E5 = 0D;
			Double porcMaxSaldoPryE4E5G = 0D;
			
			Double montoMinTemp = 0D;
			
			if( itemKuboScore.equals("E5") || itemKuboScore.equals("E4") ){
				montoMinThis = montoMinE4E5G;
				
				if(  itemKuboScore.equals("E5") ){
					
					montoDisponibleEn_E5 = montoDisponibleEn_E5 + Double.parseDouble( valAnt );
					
					maximoInvBySaldoPryE4E5 = maximoInvBySaldoPryE5;
					porcMaxSaldoPryE4E5G = porcMaxSaldoPryE5G;
					
				}else if( itemKuboScore.equals("E4") ){
					maximoInvBySaldoPryE4E5 = maximoInvBySaldoPryE4;
					porcMaxSaldoPryE4E5G = porcMaxSaldoPryE4G;
				}
				
			}else if( itemKuboScore.equals("F1") || itemKuboScore.equals("G1") ){
				
				montoMinThis = montoMinF1G1;
				
			}else{
			
				montoMinThis = montoMinG;
			
			}
			
			ProyectInfo info  = proyectInfoService.getProyectInfoByProyectLoan( item.getProyect_loan_id() );
			 
			if( info != null ){
			
				montoMinTemp = info.getMin_investment_bite();
			 
			}
			
			if( montoMinTemp > montoMinThis ){
			
				montoMinThis = montoMinTemp;
				
			}
			
			if( itemKuboScore.equals("F1") || itemKuboScore.equals("G1") ){
				
				System.out.println( maximoInvBySaldoPryF1G1 + " - - "  + porcMaxSaldoPryF1G1_G + " - - "+ montoInvertido_F_G +" - - "+limiteMaximoInversion_F_G);
				System.out.println("nuevo valor : " + item.getInvestment_bite() + " valAnt: " + newBiteInv );
				
				String key = item.getProspectus_id() + "::"+ item.getProyect_loan_id();
				
				List<ProyectFunding> lstFunding = null;
				
				Hashtable<String, List<ProyectFunding>> HT_Tmp = inversion.gethTFunding();
				
				
				if( HT_Tmp != null ){
				
					lstFunding = HT_Tmp.get(key);
					
				}
				
				Double funding_prev = 0D;
				
				if( lstFunding != null ){
					
					for( ProyectFunding funding : lstFunding ){
						
						funding_prev = funding_prev + funding.getAmount() ;
						
					}
					
					System.out.println( "Monto Invertido previamente1: " +funding_prev );
					
					
					
				}
				
				System.out.println( "Monto Invertido previamente: " +funding_prev );
				
				if( newVal>0 && newVal < montoMinThis){
				
					Double d = (Math.floor(montoMinThis*100)/100);
					msgInv = "El monto  mínimo a invertir es de "+dec.format(d) +" .";
					monto_sugerido = d;
					flagNotInv1 = true;
					newVal = d;
					montoInvertido_F_G =+newVal;
					
				}else if( newVal > item.getAvailableAmmount() ){
					
					msgInv = "El monto supera los "+dec.format(item.getAvailableAmmount()) +" que es el monto disponible del proyecto.";
					
					Double d = item.getAvailableAmmount();
					
					if(item.getAvailableAmmount() > maximoInvBySaldoPryF1G1 ){
						
						d = maximoInvBySaldoPryF1G1;
						
					}
					
					if( d > maximoInvBySaldoPryF1G1 ){
						
						d = maximoInvBySaldoPryF1G1;
						
					}
					
					if( d < montoMinF1G1 ){
						d = 0D;
					}
					
					if( d != 0 ){
					monto_sugerido = (Math.floor(d*100)/100);;
					}else{
						monto_sugerido = 0d;
					}
					
					montoInvertido_F_G =+monto_sugerido;
					flagNotInv1 = true;
					
				} else if(newVal>maximoInvBySaldoPryF1G1){
					
					Double d = (Math.floor(maximoInvBySaldoPryF1G1*100)/100);
					msgInv = "El monto supera los "+dec.format(d) +" que es el "+porcMaxSaldoPryF1G1_G+"% de tu saldo total.";
					monto_sugerido = d;
					
					if( limiteMaximoInversion_F_G < ( montoInvertido_F_G+monto_sugerido )){
						
						monto_sugerido = limiteMaximoInversion_F_G - montoInvertido_F_G;
						
						if(monto_sugerido< montoMinThis){
							monto_sugerido = 0D;
							msgInv = " Debido a que previamente ya has invertido un total de "+dec.format(montoInvertido_F_G) +" en proyectos con calificación F y G, con el monto que propones se superan los "+dec.format(limiteMaximoInversion_F_G) +" que es el límite del  "+porcent_suma_F1_G1_G+"%  que tienes de tu saldo total.";;
						}
					}
					
					flagNotInv1 = true;
					montoInvertido_F_G =+monto_sugerido;
					
				}else if(limiteMaximoInversion_F_G < (montoInvertido_F_G+newVal)){
					//Double d = (Math.floor(maximoInvBySaldoPryF1G1*100)/100);
					msgInv = " Debido a que previamente ya has invertido un total de "+dec.format(montoInvertido_F_G) +" en proyectos con calificación F y G, con el monto que propones se superan los "+dec.format(limiteMaximoInversion_F_G) +" que es el límite del  "+porcent_suma_F1_G1_G+"%  que tienes de tu saldo total.";
					monto_sugerido = 0D;
					flagNotInv1 = true;
				
				}else if( (newVal) > inversion.getMontoMaximoPorProyecto_F_G() ){
				
					//Double d = (Math.floor(maximoInvBySaldoPryF1G1*100)/100);
					
					
						monto_sugerido = inversion.getMontoMaximoPorProyecto_F_G();
						monto_sugerido = (Math.floor(monto_sugerido*100)/100);
						
					
					
					msgInv = " El moto supera los " + dec.format(inversion.getMontoMaximoPorProyecto_F_G()) +" que es el límite del  "+ inversion.getPorcMaxSaldoProyF1_G1() +"%  que tienes de tu saldo total por proyecto de este tipo.";
					flagNotInv1 = true;
					
				}else if( (newVal + funding_prev ) > inversion.getMontoMaximoPorProyecto_F_G() ){
				
					//Double d = (Math.floor(maximoInvBySaldoPryF1G1*100)/100);
					
					if((inversion.getMontoMaximoPorProyecto_F_G() - funding_prev) >= montoMinF1G1 ){
						
						monto_sugerido = inversion.getMontoMaximoPorProyecto_F_G() - funding_prev;
						monto_sugerido = (Math.floor(monto_sugerido*100)/100);
						
					}else{
						
						monto_sugerido = 0D;
						
					}
					
					msgInv = " Debido a que previamente ya has invertido un total de "+dec.format(funding_prev) +" en este proyecto, con el monto que propones se superan los "+dec.format(inversion.getMontoMaximoPorProyecto_F_G()) +" que es el límite del  "+ inversion.getPorcMaxSaldoProyF1_G1() +"%  que tienes de tu saldo total por proyecto.";
					flagNotInv1 = true;
					
				}else{
					montoInvertido_F_G =+newVal; 
				}
				/*
				montoInvertido_F_G = inversion.getMontoInvertido_F_G();
				maximoInvBySaldoPryF1G1 = inversion.getMontoMaximoPorProyecto_F_G();
				porcMaxSaldoPryF1G1_G = inversion.getPorcMaxSaldoProyF1_G1();
				porcent_suma_F1_G1_G = inversion.getPorcent_suma_F1_G1_G();
				limiteMaximoInversion_F_G = inversion.getLimiteMaximoInversion_F_G();
				*/
			}
			
			else if( newVal>0 && newVal < montoMinThis){
				
				if( montoMinThis > maximoInvBySaldoPryE4E5 && ( itemKuboScore.equals("E5") || itemKuboScore.equals("E4") ) ){
					
					msgInv = "No puedes invertir en este proyecto ya que el monto mínimo a invertir es de  "+dec.format(montoMinThis) +" y tu máximo "
							+ "monto a invertir para este tipo de proyecto es de "+dec.format(maximoInvBySaldoPryE4E5)+" que es el "+porcMaxSaldoPryE4E5G+"% de tu saldo total.";
					monto_sugerido = 0D;
					flagNotInv1 = true;
					//System.out.println(msgInv);
					
				}else{
				
					msgInv = "El monto debe ser mayor al monto mínimo que son "+dec.format(montoMinThis);
					monto_sugerido = montoMinThis;
					flagNotInv1 = true;
					//System.out.println(msgInv);
					
				}
				
			} else if( (itemKuboScore.equals("E5") || itemKuboScore.equals("E4") )  ){
				
				if( (newVal + item.getAmmountPreviousFounded()) > maximoInvBySaldoPryE4E5 ){
					
					if( itemKuboScore.equals("E4") ){
					
						if( item.getAmmountPreviousFounded() > 0 ){
						
						msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
						
						}else{
							msgInv = "El monto que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
						}
					
						monto_sugerido =  maximoInvBySaldoPryE4E5 - item.getAmmountPreviousFounded();
						
						if( monto_sugerido < montoMinE4E5G ){
							monto_sugerido = 0D;
						}
						
						flagNotInv1 = true;
					
					}
					
				}else if( montoMinThis > maximoInvBySaldoPryE4E5 ){
					
					msgInv = "No puedes invertir en este proyecto ya que el monto mínimo a invertir es de  "+dec.format(montoMinThis) +" y tu máximo "
							+ "monto a invertir para este tipo de poyecto es de "+dec.format(maximoInvBySaldoPryE4E5)+" que es el "+porcMaxSaldoPryE4E5G+"% de tu saldo total.";
					monto_sugerido = 0D;
					flagNotInv1 = true;
					//System.out.println(msgInv);
					
				}else if( newVal > maximoInvBySaldoPryE4E5 ) {
				
					if( flagMin_E5_E4 ){
						
						msgInv = "Debido a que se trata de un proyecto con calificación "+itemKuboScore+ ",el monto no debe superar "+dec.format(maximoInvBySaldoPryE4E5)+" ya que es el monto máximo a invertir para este tipo de poyectos. ";
						
					}else{
					
						msgInv = "Debido a que se trata de un proyecto con calificación "+itemKuboScore+ ",el monto no debe superar "+dec.format(maximoInvBySaldoPryE4E5)+" ya que es el "+porcMaxSaldoPryE4E5G+"% de tu saldo total.";
					
					}
					
					if( (maximoInvBySaldoPryE4E5 - item.getAmmountPreviousFounded()) < montoMinThis   ){
						
						monto_sugerido = 0D;
						
						flagNotInv1 = true;
						
					}else{
					
						monto_sugerido = (Math.floor((maximoInvBySaldoPryE4E5 - item.getAmmountPreviousFounded())*100)/100);
						
						flagNotInv1 = true;
					
					}
				
				}
				
				if( itemKuboScore.equals("E5") && !flagNotInv1 ){
					
					if( (montoDisponibleEn_E5 - newVal) < 0 && flagMasivo ){
						
						msgInv = "El monto que propones, junto con tus inversiones y selecciones anteriores supera el "+porcMaxSUMSaldoProyE5+"% de tu saldo total.";
						
						if( montoDisponibleEn_E5 < montoMinThis ){
							monto_sugerido = 0D;
						}else{
							monto_sugerido = montoDisponibleEn_E5;
							montoDisponibleEn_E5 = 0D;
						}
						
						
						flagNotInv1 = true;
						
					}else if(flagMasivo){
						
						if(maximoInvBySaldoPryE4E5 <= (newVal+ item.getAmmountPreviousFounded()) ){
						
							if( item.getAmmountPreviousFounded() > 0 ){
								msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
							}else{
								msgInv = "El monto que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
							}
							montoDisponibleEn_E5 = 0D;
							flagNotInv1 = true;
							
						}else{
						montoDisponibleEn_E5 = montoDisponibleEn_E5 -  newVal ;
						}
					}else if( !flagMasivo ){
						
						//
						
						if( (montoDisponibleEn_E5_ini - newVal) < 0 ){
							
							msgInv = "El monto que propones, junto con tus inversiones y selecciones anteriores supera el "+porcMaxSUMSaldoProyE5+"% de tu saldo total.";
							
							if( montoDisponibleEn_E5_ini < montoMinThis ){
								monto_sugerido = 0D;
							}else{
								monto_sugerido = montoDisponibleEn_E5_ini;
								montoDisponibleEn_E5_ini = 0D;
							}
							
							
							flagNotInv1 = true;
							
						}else {
							
							if(maximoInvBySaldoPryE4E5 <= (newVal+ item.getAmmountPreviousFounded()) ){
							
								if( item.getAmmountPreviousFounded() > 0 ){
									msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
								}else{
									msgInv = "El monto que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(maximoInvBySaldoPryE4E5);
								}
								montoDisponibleEn_E5_ini = 0D;
								flagNotInv1 = true;
								
							}else{
								montoDisponibleEn_E5_ini = montoDisponibleEn_E5_ini -  newVal ;
							}
						
						}
						//
						
					}
					
				}
			
			} else if(newVal > maximoInvBySaldoG && !flagNotRule){
				
					msgInv = "El monto no debe superar "+dec.format(maximoInvBySaldoG)+" ya que es el "+porcMaxSaldoG+"% de tu saldo total.";
					
					monto_sugerido = (Math.floor(maximoInvBySaldoG*100)/100);
					//monto_sugerido = maximoInvBySaldoG2;
					//System.out.println(msgInv);
					flagNotInv1 = true;
				
			}else if(newVal > item.getAvailableAmmount()){
				msgInv = "El monto no debe superar  "+dec.format(item.getAvailableAmmount())+" ya que es el monto disponible con el que cuenta el proyecto";
				monto_sugerido = (Math.floor(item.getAvailableAmmount()*100)/100);
				//System.out.println(msgInv);
				flagNotInv1 = true;
			}
			else if( ( (newVal + maxAmmountTemp) > montoSaldoG ) && flagMasivo ){
				msgInv = "El monto a invertir supera los " + dec.format(montoSaldoG);
				monto_sugerido = 0D;
				//System.out.println(msgInv);
				flagNotInv1 = true;
			
			}else if( !flagMasivo && newVal > montoSaldoG  ){
				msgInv = "El monto a invertir supera los " + dec.format(montoSaldoG);
				monto_sugerido = 0D;
				//System.out.println(msgInv);
				flagNotInv1 = true;
			}
			else{
				
				Double montoPorcentaje = (maxPorcPryG*item.getAmmount())/100;
				
				if(newVal > montoPorcentaje){
					msgInv = "El monto a invertir supera el límite permitido para este proyecto que es de "+dec.format(montoPorcentaje);
					monto_sugerido = 0D;
					flagNotInv1 = true;
					//System.out.println(msgInv);
				}else if( item.getAmmountPreviousFounded() != null)
					{
						if( ( item.getAmmountPreviousFounded() + newVal) > montoPorcentaje)
						{
							if( item.getAmmountPreviousFounded() > 0 ){
								msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(montoPorcentaje);
							}else{
								msgInv = "El monto quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(montoPorcentaje);
							}
							monto_sugerido = 0D;
							flagNotInv1 = true;
							//System.out.println(msgInv);
							
						}else if( ( item.getAmmountPreviousFounded() + newVal) > maximoInvBySaldoG ){
							
							if( item.getAmmountPreviousFounded() > 0 ){
							
								msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera los "+dec.format(maximoInvBySaldoG)+" que es el "+porcMaxSaldoG+"% de tu saldo total.";
								
							}else{
								msgInv = "El monto que quiere invertir supera los "+dec.format(maximoInvBySaldoG)+" que es el "+porcMaxSaldoG+"% de tu saldo total.";
							}
							
							Double maximoInvBySaldoG2 = (Math.floor(maximoInvBySaldoG*100)/100);
							monto_sugerido = maximoInvBySaldoG2 - item.getAmmountPreviousFounded();
							
							if( montoMinG > monto_sugerido )
							{
								monto_sugerido = 0D;
							
							}
							//System.out.println(msgInv);
							flagNotInv1 = true;
						}
					}
				
				
			}
			
			if( item.getAvailableAmmount() < montoMinG ){
				msgInv = "El monto disponible  es menor al monto mínimo que son "+dec.format(montoMinG);
				monto_sugerido = 0D;
				flagNotInv1 = true;
			}
			
			
				proyectListForInvesInd.remove(item);
				
				monto_sugerido = (double)Math.round(monto_sugerido*100)/100;
				
				if(flagNotInv1)
					newVal = monto_sugerido;
				
				
				if(valAnt == null || valAnt.trim().length() == 0)
					valAnt = "0";
				
				ammountFoundedInv -= Double.parseDouble( valAnt );
				ammountFoundedInv += newVal;
				
				
				
					
				item.setInvestment_bite(newVal);
				
				proyectListForInvesInd.add(item);
			//}
			
			newBiteInv = null;
			
			if( monto_sugerido < montoMinThis && monto_sugerido > 0D ){
				monto_sugerido = 0D;
			}
				
			requestContext.addCallbackParam("hasError", (flagNotInv1));
			requestContext.addCallbackParam("msgInv", msgInv);
			requestContext.addCallbackParam("montoSugerido", monto_sugerido);
			requestContext.addCallbackParam("proyect_loan_id",item.getProyect_loan_id());
			
		}
		
		public void quitarSeleccion(){
			
			Calendar pc = Calendar.getInstance();
			pc.setTime( new Date() );
			
			proyectListForInvesInd.clear();
			
			for( ItemLoanList item : proyectList ){
				item.setBlnChck(false);
			}
			
			ammountFoundedInv = 0D;
			
			montoDisponibleEn_E5	= inversion.initMontoDisponibleEn_E5();
			montoInvertido_F_G 		= inversion.getMontoInvertido_F_G();
			
			Calendar pc1 = Calendar.getInstance();
			pc1.setTime( new Date() );
			
			long l = pc1.getTimeInMillis() - pc.getTimeInMillis();
			
			//System.out.println( "Tiempo total quitar seleccion: " + l +"ms");
			
			
		}
		
		public boolean actualizaSaldoDisponible(ProyectLoan proyectInContext){
			
			TiendaCreditos tiendaRegistro = tiendacreditosservice.getTiendaCreditosItemBySolOrCred(proyectInContext.getSafi_mx_solicitud_id(), null);
			
			Double d = (Double.parseDouble(tiendaRegistro.getDisponibleFondeo()) - proyectInContext.getInvestment_bite());
			
				tiendaRegistro.setDisponibleFondeo(d+"");
					
			tiendacreditosservice.update(tiendaRegistro);
			
			return true;
		}
		
		public void validaIsCancel(String value)
		{		
			faces     = FacesContext.getCurrentInstance();
			
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			
			control_table = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
			
			membership_PK = new  MembershipPK();
			
			membership_PK.setCompany_id 	( Integer.parseInt( value.split("::")[3] ) );
			membership_PK.setProspectus_id 	( Integer.parseInt( value.split("::")[2] ) );
			
			member = membershipService.getMembershipById(membership_PK);
			
			if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") )
			{
				
				control_table.setProspectus_is_canceled( true );
				control_table.setCanceledReason(member.getIs_canceled());
				
			}else{
				
				control_table.setProspectus_is_canceled( false );
				control_table.setCanceledReason("");
				
			}		
		}
		
		public void asignaPerfil(){
			
			profile =  profileinvservice.getProfileInvByProspectus( sesion.getProspectus_id() , sesion.getCompany_id() );
			
			if( profile != null ){
				
					if( profile.getProfile_user_sel() != null ){
						
						profileStr = profile.getProfile_user_sel().getProfile_category();
						maxInvBiteRecomded = profile.getProfile_user_sel().getTop_investment_bite();
						
					}if( profile.getProfile_cal() != null ){
						
						profileStr = profile.getProfile_cal().getProfile_category();
						maxInvBiteRecomded = profile.getProfile_cal().getTop_investment_bite();
						
					}else{
					
						profileStr = "";
						maxInvBiteRecomded = ""; 
					}
				
				
				
			}
			
		}
		
		public void calculaConfirm()
		{
			
			aToInv=0;
			bToInv=0;
			cToInv=0;
			dToInv=0;
			eToInv=0;
			fToInv=0;
			gToInv=0;
			
			tasaPon_A=0D;
			tasaPon_B=0D;
			tasaPon_C=0D;
			tasaPon_D=0D;
			tasaPon_E=0D;
			tasaPon_F=0D;
			tasaPon_G=0D;
			tasaPonderada=0D;
			
			Double montoInv_A=0D;
			Double montoInv_B=0D;
			Double montoInv_C=0D;
			Double montoInv_D=0D;
			Double montoInv_E=0D;
			Double montoInv_F=0D;
			Double montoInv_G=0D;
			
			monto_a_invertir_total = 0D;
			
			for( ItemLoanList item : proyectListForInvesInd )
			{
				
				if( item.getKubo_score_a().equals("A") ){
					montoInv_A += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("B") ){
					montoInv_B += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("C") ){
					montoInv_C += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("D") ){
					montoInv_D += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("E") ){
					montoInv_E += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("F") ){
					montoInv_F += item.getInvestment_bite();
				}
				if( item.getKubo_score_a().equals("G") ){
					montoInv_G += item.getInvestment_bite();
				}
				
				System.out.println( item.getInvestment_bite() );
				
				monto_a_invertir_total = monto_a_invertir_total +item.getInvestment_bite();
			}
			
			for( ItemLoanList item : proyectListForInvesInd ){
				
				if( item.getKubo_score_a().equals("A") ){
					aToInv++;
					tasaPon_A += (item.getInvestment_bite()/montoInv_A) * item.getRate_investor();
				}
				if( item.getKubo_score_a().equals("B") ){
					bToInv++;
					tasaPon_B += (item.getInvestment_bite()/montoInv_B) * item.getRate_investor();
				}
				if( item.getKubo_score_a().equals("C") ){
					cToInv++;
					tasaPon_C += (item.getInvestment_bite()/montoInv_C) * item.getRate_investor();
				}
				if( item.getKubo_score_a().equals("D") ){
					dToInv++;
					if( item.getRate_investor() != null ){
					tasaPon_D += (item.getInvestment_bite()/montoInv_D) * item.getRate_investor();
					}
				}
				if( item.getKubo_score_a().equals("E") ){
					eToInv++;
					tasaPon_E += (item.getInvestment_bite()/montoInv_E) * item.getRate_investor();
				}
				if( item.getKubo_score_a().equals("F") ){
					fToInv++;
					tasaPon_F += (item.getInvestment_bite()/montoInv_F) * item.getRate_investor();
				}
				if( item.getKubo_score_a().equals("G") ){
					gToInv++;
					tasaPon_G += (item.getInvestment_bite()/montoInv_G) * item.getRate_investor();
				}
				
				if(  item.getRate_investor() != null ){
				
					tasaPonderada += (item.getInvestment_bite()/monto_a_invertir_total) * item.getRate_investor();
				
				}
				
			}
			
			//System.out.println("Termina el Cálculo");
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			
			InvestorSession investorSession = (InvestorSession) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(elContext, null, "investorSession");
			
			investorSession.setListToInvestment(proyectListForInvesInd);
			
		}
					
		public ItemLoanList getProyectSeleccionado( int proyect_loan_id , int proyect_id, int  prospectus_id , int company_id )
		{			
			ItemLoanList itemRes = null;
			
			for ( ItemLoanList item : proyectList )
			{
				
				if( item.getProyect_loan_id() == proyect_loan_id && item.getProyect_id() == proyect_id && item.getProspectus_id() == prospectus_id && item.getCompany_id() == company_id)
				{
					itemRes = item;
					break;
				}
				
			}
			
			return itemRes;
			
		}
		
		public void setNewBiteInvManual ( AjaxBehaviorEvent evento)
		{
			HtmlInputText ajax_input_text = (HtmlInputText) evento.getComponent();
			String s = (String) ajax_input_text.getValue();
			//System.out.println( "Element: "+ ajax_input_text + " --------------- ajax_input_text:  " + s);
		}
		
		public void preparaProyectList( FilterStore filter)
		{ 
			//Metodo llamado desde NavigationInvestment
		
			List<ViewForTiendaExec> temporalProyectListView = proyectLoanService.getProyectLoanByFilteringInv( filter );
			
			inversion.createProyectListView(temporalProyectListView,sesion.getProspectus_id().intValue(), sesion.getCompany_id().intValue(), cuentaActual );
			
			proyectListForInvesInd = new ArrayList<ItemLoanList>();
			ammountFoundedInv = 0D;
			
			proyectList = inversion.getProyectList();
			
			proyectList = inversion.calculaInvestmentBite( sesion.getProspectus_id().intValue(), sesion.getCompany_id().intValue(),monto_a_invertir, proyectList );
			
			inversion.setProyectList(proyectList);
			
			inicializaListas();
			
			asignaListForInvest();
			
		}
				
		public void insertaAccessTienda(){
			
			Access access = new Access();
			access.setCompany_id     (sesion.getCompany_id());
			access.setProspectus_id  (sesion.getProspectus_id());
			access.setOp_system      (sesion.getOsbrawser());
			access.setHorizontal_size(sesion.getBrowser_width());
			access.setVertical_size  (sesion.getBrowser_height());
			access.setIpaddress      (sesion.getIP_address_client());
			access.setWeb_browser    (sesion.getNamebrawser());
			access.setWeb_browser_version(sesion.getVersionbrawser());
			access.setVersion_description(sesion.getVersion_description());
			
			access.setUser_agent      (sesion.getUser_agent());
			access.setDevice_info    (sesion.getDevice_info());
			
			access.setScreen_id(58);//TIENDA
			access.setPercentage(0);
			
			access.setUrl_access		  (sesion.getUrl_access());
			
			access.setProspectus_id_coach (sesion.getCoachProspectus_id());
			access.setAccess_from		  (sesion.getAccess_from());
			
			service_access.add(access, true);
			
		}
				
		public void showMoreProyects(){
			proyectList_B = new ArrayList<ItemLoanList>();
			if( moreProyects ){
				int i = 0;
				for(ItemLoanList a : proyectList){
					
					if( maxProxect < i  ){
						proyectList_B.add(a);
					}
					i++;
				
				}
			
			}
			
		}
		
		public void changeHoldSel(){
			hold_selected = !hold_selected;
			System.out.println(" Mantiene Seleccionados: " + hold_selected);
		}		
}
