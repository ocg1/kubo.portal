package mx.com.kubo.managedbeans;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Role_Searching_PK;
import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TasasAcreditado;
import mx.com.kubo.model.TiendaCreditos;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.tools.NumberToLetterConverter;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.ConsultaInverResponse;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;
import safisrv.ws.InvKuboServicios.SolicitudInversionRequest;
import safisrv.ws.InvKuboServicios.SolicitudInversionResponse;

@SuppressWarnings("serial")
@ManagedBean (name = "listaCreditos")
@ViewScoped

public class ListaCreditos 
							extends 		ListaCreditosDMO
							implements 	ListaCreditosIMO, 
											Serializable 
{	
	@PostConstruct
	public void init()
	{
		
		Calendar cargaINIT = Calendar.getInstance();
	    cargaINIT.setTime(new Date());
				
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		sesion = (SessionBean) FacesContext.getCurrentInstance()
												.getApplication().getELResolver()
												.getValue(elContext, null, "sessionBean");
		
		System.setProperty("java.awt.headless", "true");
		
		if(sesion.getRole_id() !=null )
		{
			setPermissions(sesion.getRole_id());
		}
		
		proyectList = new ArrayList<ItemLoanList>();
		
		proyectListForInvesInd = new ArrayList<ItemLoanList>(); 
		
		this.PID_cliente = sesion.getProspectus_id().toString();
		
		//Parametros del sistema
		
		
		
		initPerson();
		
		if(sesion.getArea()=='I'){
			
			Calendar cargaCuentas_1 = Calendar.getInstance();
			cargaCuentas_1.setTime(new Date());
			
			this.SAFI_cuenta = proyectLoanService.getOnlySAFIAccount(this.PID_cliente);
			setupClientSAFIData(this.SAFI_cuenta);
			
			SystemParamPK sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(6);//parametro que trae el número máximo de veces que un inversionista puede invertir en cada proyecto;
			
			SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
			
			vecesQuePuedeFondear = sys.getValue();
			
			
			this.saldoActual = this.getTotalCreditOnAccounts();
			if(listInvAccounts!=null){
				if(listInvAccounts.size()>0){
					
					
					if(listInvAccounts.size()==1) {
						 
						 tagAccount=listInvAccounts.get(0).getAccount();
						 setCuentaActual(listInvAccounts.get(0).getAccount());
						 setSaldoActual(listInvAccounts.get(0).getSaldo());
						 
						 
					}else{
						tagAccount="Ninguna cuenta";
					}
					
				}
			}else{
				tagAccount="No hay cuenta";
			}
			//this.proyectFundingByInvestor = proyectFundingService.getproyectbyInvestor(sesion.getProspectus_id().toString(), "-1");
			
			Calendar cargaCuentas_2 = Calendar.getInstance();
			cargaCuentas_2.setTime(new Date());
			
			long difCuentas1 = cargaCuentas_2.getTimeInMillis() - cargaCuentas_1.getTimeInMillis();
			
			System.out.println("Tiempo en Cargar Cuentas: "+difCuentas1+" milisegundos");
			
			try{
				
				Calendar cargaService_1 = Calendar.getInstance();
				cargaService_1.setTime(new Date());
				
				locatorSafi = new SAFIServiciosServiceLocator();
				servicioSafi = locatorSafi.getSAFIServiciosSoap11();
				
				Calendar cargaService_2 = Calendar.getInstance();
				cargaService_2.setTime(new Date());
				
				long difService1 = cargaService_2.getTimeInMillis() - cargaService_1.getTimeInMillis();
				
				System.out.println("Tiempo en Cargar Servicios: "+difService1+" milisegundos");
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		Calendar cargaRole1T = Calendar.getInstance();
		cargaRole1T.setTime(new Date());
		
		rolesearching = null ;
		
		if( sesion.getRole_id() != null && !sesion.getArea().toString().equals("I") )
		{
			Role_Searching_PK pk = new Role_Searching_PK();
			pk.setCompany_id(sesion.getCompany_id());
			pk.setRole_id(sesion.getRole_id());
			pk.setArea_used("L");
			rolesearching = rolesearchingservice.getRolesearchingbyPK(pk);
		}
		
		Calendar cargaRole2T = Calendar.getInstance();
		cargaRole2T.setTime(new Date());
		
		long difRole1 = cargaRole2T.getTimeInMillis() - cargaRole1T.getTimeInMillis();
		
		System.out.println("Tiempo en Calcular Role: "+difRole1+" milisegundos");
		
		scriptStatus = "<script>";
		
		Calendar cargaStatus1T = Calendar.getInstance();
		cargaStatus1T.setTime(new Date());
		
		lstStatus = statusproyectcatservice.getListStatusProyectCat();
		
		for( StatusProyectCat stts :lstStatus){
			
			if(getCheckedByStatus(stts.getStatusPK().getStatus_id()))
				scriptStatus += "$('#status_"+stts.getStatusPK().getStatus_id()+"').attr('checked', true);";
			
		}
		
		String calif[] = {"chkA","chkB","chkC","chkD","chkE"};
		
		for(int i = 0; i<calif.length; i++)
		{
			scriptStatus += "$('#"+calif[i]+"').attr('checked', true);";
		}

		scriptStatus += "</script>";
		
		
		Calendar cargastatus2T = Calendar.getInstance();
		cargastatus2T.setTime(new Date());
		
		long difStatus1 = cargastatus2T.getTimeInMillis() - cargaStatus1T.getTimeInMillis();
		
		System.out.println("Tiempo en Calcular Status: "+difStatus1+" milisegundos");
		
		setupList();
		
		
		
		if( sesion.getArea().toString().equals("I") )
		{
			
			if( saldoTotal  < montoMaximo )
			{
				displayInvestAction=false;
				
			}else{
				
				displayInvestAction=true;
				
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
			    
			    
			    Calendar cargaCIB1T = Calendar.getInstance();
				cargaCIB1T.setTime(new Date());
				
			    calculaInvestmentBite(  );
			    
			    Calendar cargaCIB1T_2 = Calendar.getInstance();
			    cargaCIB1T_2.setTime(new Date());
				
				long difCL1 = cargaCIB1T_2.getTimeInMillis() - cargaCIB1T.getTimeInMillis();
				
				System.out.println("Tiempo en Calcular InvestmentBite: "+difCL1+" milisegundos");
				
				aToInv = 0;
			    bToInv = 0;
			    cToInv = 0;
			    dToInv = 0;
			    eToInv = 0;
			    
			}
			
			displayMsgMaxSug = true;
			displayMsgMinSug = true;
			
			if( getSaldoActual() != null ){
				
				if(getSaldoActual() > montoLimiteMaxSugerido){
					displayMsgMaxSug = true;
				}else if( getSaldoActual() < montoLimiteMinSugerido ){
					displayMsgMinSug = true;
				}
				
			}
			
		}
		
		
		Calendar cargaINIT_2 = Calendar.getInstance();
	    cargaINIT_2.setTime(new Date());
		
		long difCL1 = cargaINIT_2.getTimeInMillis() - cargaINIT.getTimeInMillis();
		
		System.out.println("Tiempo en Cargar INIT: "+difCL1+" milisegundos");
		
	}
	
	public Double getTotalCreditOnAccounts()
	{
		Double suma=0.00;
		
		try {
			
			for (InvestorsAccounts iterElement : getListInvAccounts()) 
			{
				suma=suma+iterElement.getSaldo();
			}
			
		} catch (Exception e) {
			log.info("Se genero un error al hacer la suma del saldo de cuentas");
		}
		
		return suma;
	}
	
	private void setupList(){
		
		setMontoMaximo();
		
		List<ProyectLoan> temporalProyectList = new ArrayList<ProyectLoan>();
		
		List<ViewProyectTienda> temporalProyectListView = new ArrayList<ViewProyectTienda>() ;
		
		if( rolesearching == null || sesion.getArea().equals('I') ){
			
			if(sesion.getArea().equals('M'))
				temporalProyectList = proyectLoanService.getProyectLoanListControlTable();
			else{//inversionista
				
				Calendar cargaList1T = Calendar.getInstance();
				cargaList1T.setTime(new Date());
				
				temporalProyectListView = proyectLoanService.loadProyectLoanList_TIENDA();
				
				Calendar cargaList1T_2 = Calendar.getInstance();
				cargaList1T_2.setTime(new Date());
				
				long difCL1 = cargaList1T_2.getTimeInMillis() - cargaList1T.getTimeInMillis();
				
				System.out.println("Tiempo en cargar Lista: "+difCL1+" milisegundos");
			}
			
		}else{ 
			
			temporalProyectList = proyectLoanService.getProyectLoanListByRole( rolesearching );
			
		}
		
		// onlyTermOfProyectLoan = proyectLoanService.getOnlyTermID();
		
		Calendar cargaRP1T = Calendar.getInstance();
		cargaRP1T.setTime(new Date());
		
		if( sesion.getArea().equals('M') ){
			createProyectList(temporalProyectList);
		}else{
			createProyectListView(temporalProyectListView);
		}
		
		Calendar cargaRP1T_2 = Calendar.getInstance();
		cargaRP1T_2.setTime(new Date());
		
		long difCL1 = cargaRP1T_2.getTimeInMillis() - cargaRP1T.getTimeInMillis();
		
		System.out.println("Tiempo en Reprocesar Lista: "+difCL1+" milisegundos");
		
	}
	
	public  void reloadList()
	{
			
		this.onlyTermOfProyectLoan = proyectLoanService.getOnlyTermID();
		
		if( sesion.getArea().equals('M') ){
			
			List<ProyectLoan> temporalProyectList = proyectLoanService.getProyectLoanList();
			
			createProyectList(temporalProyectList);
		}else{
			
			List<ViewProyectTienda> temporalProyectListView = proyectLoanService.loadProyectLoanList_TIENDA();
			
			createProyectListView(temporalProyectListView);
		}
		
		calculaInvestmentBite(  );
	}	
	
	private void setupClientSAFIData(List<String> SAFIcuenta)
	{
		try
		{
			
			Calendar cargaServ_CuentasT_1 = Calendar.getInstance();
			cargaServ_CuentasT_1.setTime(new Date());
			
			safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorAccount = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CuentasServicios.SAFIServicios servCliente = locatorAccount.getSAFIServiciosSoap11();
			safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse resCliente = servCliente.consultaCuentasPorCliente(new safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest(SAFIcuenta.get(0).toString()));

			
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
			
			long difCL1 = cargaServ_cuenta_2.getTimeInMillis() - cargaServ_CuentasT_1.getTimeInMillis();
			
			System.out.println("Tiempo en Consultar WS Cuentas: "+difCL1+" milisegundos");
			 
			Calendar cargaServ_Saldo_1 = Calendar.getInstance();
			cargaServ_Saldo_1.setTime(new Date());
//			
//			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
//			SAFIServicios serviceSafi = locator.getSAFIServiciosSoap11();
//			
//			ConsultaInverResponse replyB = serviceSafi.consultaInver(new ConsultaInverRequest(naturalPerson.getSafi_client_id(),listInvAccounts.get(0).getAccount()));
//			
//			if(replyB != null){
//				
//				saldoTotal = new Double(replyB.getSaldoTotal()==null?"0.0":replyB.getSaldoTotal());
//				
//			}
//			
			SaldoInversionista saldoObj = saldoinversionistaservice.getSaldoByAccount(listInvAccounts.get(0).getAccount());
			
			if(saldoObj != null){
				saldoTotal = saldoObj.getSaldoTotal();
			}else{
				saldoTotal = 0D;
			}
//			
			System.out.println("SALDO TOTAL: "+saldoTotal );
			
			Calendar cargaServ_Saldo_2 = Calendar.getInstance();
			cargaServ_Saldo_2.setTime(new Date());
			
			long difSaldo1 = cargaServ_Saldo_2.getTimeInMillis() - cargaServ_Saldo_1.getTimeInMillis();
			
			System.out.println("Tiempo en Consultar WS Saldos: "+difSaldo1+" milisegundos");
			
		}catch (Exception e) {
		
			e.printStackTrace();
			
		}
	}
	
	public boolean getEnabledFlag(ProyectLoan proyect,Integer InvId)
	{
		String proyectLoanId = ( ( proyect.getProyectloanPk().getProyect_loan_id() ) + "" );
		List<ProyectFunding> proyectFundingByInvestor = proyectFundingService.getproyectbyInvestor(InvId.toString(), proyectLoanId);
		
		if (proyect.getAmount_founded()>=proyect.getAmmount()) 
				return true;
		else if (proyectFundingByInvestor.size()>=Integer.parseInt(vecesQuePuedeFondear)) 
	    		return true;
	    else if ((saldoActual)<=0.00) 
				return true;
//		else if (proyect.getDaysLeft()>(long)0) 
//	    		return false;
	    else if ((proyect.getAmmount()-proyect.getAmount_founded())<=0.00) 
	    	return true;
	    else return false;
	}
	
	public void updateRow(ActionEvent e)
	{
		String invertionValue = e.getComponent().getAttributes().get("investmentBite").toString();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		//System.out.println(" -*-*- Entrando a invertir -*-*- ");
		
		String proyectId = e.getComponent().getAttributes().get("proyectId").toString();
		String proyectLoanId = e.getComponent().getAttributes().get("proyectloanid").toString();
		String prospectusId = e.getComponent().getAttributes().get("prospectusid").toString();
		String companyId = e.getComponent().getAttributes().get("companyid").toString();
		String investorId = e.getComponent().getAttributes().get("investorId").toString();
		
		double investment_Bite = Double.parseDouble(invertionValue);
		int proyect_id = Integer.parseInt(proyectId);
		int proyect_loan_id = Integer.parseInt(proyectLoanId);
	    Integer prospectus_id = Integer.parseInt(prospectusId);
	    int company_id = Integer.parseInt(companyId);	
	    int invId = Integer.parseInt(investorId);
	    String  account = getTagAccount();
	    
	    creditFounded = 0;
	    ammountFounded = 0D;
	    investmentBite = 0D;
	    
	    creditNotFounded = 0;
	    ammountNotFounded = 0D;
		
	    investmentAction(proyect_loan_id,proyect_id,prospectus_id,company_id,invId,account,investment_Bite, true, null);
	    
	    
	    requestContext.addPartialUpdateTarget("creditos");
	    
	}
	
	public void investmentAction(int proyect_loan_id,int proyect_id,int prospectus_id,int company_id,int invId,String account,Double investment_Bite,boolean recalcaulaTabla, ItemLoanList item )
	{	   
//		if( item == null ){
//		    
//			ProyectLoanPK proyectKey = new ProyectLoanPK(proyect_loan_id,proyect_id,prospectus_id,company_id);
//		    ProyectLoan proyectInContext=proyectLoanService.findProyect(proyectKey);
//		    
//		    safi_credit_id = proyectInContext.getSafi_credit_id();
//		    safi_solicitud_id = ( proyectInContext.getSafi_mx_solicitud_id() == null ) ? null : ( proyectInContext.getSafi_mx_solicitud_id() + "" ) ;
//		    
//		}
	    String response = "";
	    
	    String solicitudID = null;
	    String creditoID = null;
	    
	    String fundingType = null;
	    
	    if(item.getSafi_credit_id() != null){
	    	creditoID = item.getSafi_credit_id();
	    	solicitudID = "0";
	    	fundingType = "C";
	    }else{
	    	solicitudID = item.getSafi_solicitud_id()+"";
	    	creditoID = "0";
	    	fundingType = "S";
	    }
	    
	    ServiceCalling srvCall = new ServiceCalling();
	    srvCall.setAcces_datetime(new Date());
	    srvCall.setCompany_id(company_id);
	    srvCall.setStatus(1);
	    srvCall.setProspectus_id(invId);
	    srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudInversion: crd = "+creditoID+" monto: "+investment_Bite);
	    servicecallingService.saveServiceCall(srvCall);
	    
	    //System.out.println("Invocando: " + solicitudID+" , "+creditoID+" , "+account+" , "+naturalPerson.getSafi_client_id()+" , "+item.getRate_investor()+" , "+investment_Bite+" , "+fundingType);
	    
	    Calendar calW1 = Calendar.getInstance();
    	calW1.setTime(new Date());
	    
	    response = this.storeInvestmentInWS( solicitudID, creditoID, account,naturalPerson.getSafi_client_id(), item.getRate_investor(), investment_Bite,fundingType,company_id,invId);
	    
	    Calendar cW31 = Calendar.getInstance();
		cW31.setTime(new Date());

		Long difW3 = cW31.getTimeInMillis() - calW1.getTimeInMillis();
		
		System.out.println("Tardó en Ejecutar storeInvestmentInWS : "+difW3+" milisegundos");
	    
	    if(response.equals("0") ||  response.equals("00") || response.equals("000")){
	    	
	    	
	    	Calendar cal1 = Calendar.getInstance();
	    	cal1.setTime(new Date());
		    
	    	proyectLoanService.spSetOnProyectFunding(proyect_loan_id, proyect_id, company_id, prospectus_id, invId, investment_Bite,"");
		    
	    	Calendar c31 = Calendar.getInstance();
			c31.setTime(new Date());

			Long dif3 = c31.getTimeInMillis() - cal1.getTimeInMillis();
			
			System.out.println("Tardó en Ejecutar spSetOnProyectFunding : "+dif3+" milisegundos");
			
        	this.saldoActual = this.saldoActual - investment_Bite;
        	
        	if(recalcaulaTabla){
        		setupClientSAFIData(this.SAFI_cuenta);
        		List<ProyectLoan> temporalProyectList = proyectLoanService.getProyectLoanList();
			    createProyectList(temporalProyectList);
			    //proyectInContext=proyectLoanService.findProyect(proyectKey);
			    calculaInvestmentBite(  );
			    
        	}
		    
		    creditFounded++;
		    ammountFounded = ammountFounded + investment_Bite;
			//requestContext.addPartialUpdateTarget("creditos");
			
	   }else{
		   
		   creditNotFounded++;
		   ammountNotFounded += ammountNotFounded;
		   
	   }
	}
	
	public Double getExpectedPerformanceForProyect(Double cachito,Double tasa,Integer plazo,Integer idPagos)
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
		if (rendimiento < 0.00) rendimiento = 0.00;
		return (double)Math.round(rendimiento*100)/100;
	}
	
	private Double generaMontoCuota(Double tasaPeriodo,Double numCuota,Double cachito)
	{
		Double intper = tasaPeriodo;
		Double num = (Math.pow((1+intper), numCuota))*intper;
		Double den = (Math.pow((1+intper), numCuota))-1;
		Double montoAPagar = cachito*(num/den);
		return (double)Math.round(montoAPagar*100)/100;
	}

    public void updateByFiltering(ActionEvent e)
    {
    	String concatRiskCad = new String("");
    	String concatAmmountCad = new String("");
    	String concatPhraseCad = new String("");
    	String concatTermCad = new String("");
    	String cad = new String("");
    	String riskCad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadena1");
    	String termCad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadena2");
    	
    	if( sesion.getArea().toString().equals("I") ){
	    	System.out.println("Maximo Plazo en meses: "+ termCad);
	    	
	    	termCad = termCad.split("T")[1];
	    	
	    	Integer intTmp = Integer.parseInt( termCad ) * 30;
	    	
	    	termCad = intTmp.toString();
	    	
	    	System.out.println("Maximo Plazo en dias: "+ termCad);
    	
    	}else{
    		termCad = "";
    		System.out.println("sin Term: "+ termCad);
    		
    	}
    		
    	String genderCad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadenaGender");
    	
    	String ammountCadFrom = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inputFromBtn");
    	String ammountCadTo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inputToBtn");
    	
    	String concatGenderCad = "";
    	
    	//String phraseCad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inputPhrase");
    	
    	String getStatus=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadena3");
    	
    	if(!riskCad.equals(""))
    	{
    		concatRiskCad = "l.kubo_score_a in (";
    		concatRiskCad = concatRiskCad+riskCad+")";
    		concatRiskCad=getStatus!=""?concatRiskCad+" and l.status_id in("+getStatus+") ":concatRiskCad+"";
    	}
    	else{
    		concatRiskCad = "l.kubo_score_a = '' ";
    		concatRiskCad= ( ( getStatus!="" ) ? (concatRiskCad+"l.status_id in("+getStatus+") " ) : (concatRiskCad+"") );
    	}    	    
    	
    	if( genderCad !=null && genderCad.trim().length()>0 && sesion.getArea().equals('I') ){
    		if(concatRiskCad.trim().length()>0)
    			concatGenderCad = " and ";
    		
    		concatGenderCad += "per.gender_id in ("+genderCad+")";
    		
    	}
    	
    	if(ammountCadTo !=null && ammountCadFrom!= null && (!ammountCadFrom.equals("") || !ammountCadTo.equals(""))){
    		if(ammountCadFrom == ""){
    			ammountCadFrom = "0";
    		}
    		
    		if(Double.parseDouble(ammountCadFrom)>Double.parseDouble(ammountCadTo)){
    			this.setLabelOnFilteringByAmmount("El limite inferior no puede ser mayor al limite superior.");
    			return;
    		}
    		if(!concatRiskCad.equals("")){
    			concatAmmountCad = " and l.ammount BETWEEN '"+ammountCadFrom+"' and '"+ammountCadTo+"' ";			
    		}
    		else{
    			concatAmmountCad = "l.ammount BETWEEN '"+ammountCadFrom+"' and '"+ammountCadTo+"' ";			

    		}
    	}
    	else{
    		concatAmmountCad = "";
    	}
    	
    	if(concatRiskCad!= null && !concatRiskCad.equals("") || !concatAmmountCad.equals("") || !concatPhraseCad.equals("")){
    		
    		if(sesion.getArea().toString().equals("I")){
    			
    			if(!termCad.equals("")){
	    			concatTermCad = " and tc.DiasPorTrans <= ("+termCad+") ";
	    		}
	    		else{
	    			concatTermCad = "";
	    		}
    			
    		}else{
    		
	    		if(!termCad.equals("")){
	    			concatTermCad = " and t.term_id IN ("+termCad+") ";
	    		}
	    		else{
	    			concatTermCad = "";
	    		}
    		
    		}
    		
    	}
    	else{
    		if(termCad != null && !termCad.equals("")){
    			concatTermCad = " t.term_id IN ("+termCad+") ";
    		}
    		else{
    			concatTermCad = "";
    		}
    	}
    	
    	this.setLabelOnFilteringByAmmount("");
    	
    	cad = concatRiskCad+concatGenderCad+concatAmmountCad+concatPhraseCad+concatTermCad;
    	
    	List<ProyectLoan> temporalProyectList;
    	
    	if(cad != null && !cad.equals("")){
    		
    		if(sesion.getArea().equals('M')){
    			
    			temporalProyectList = proyectLoanService.getProyectLoanByFilteringControlTable(cad);
    			createProyectList(temporalProyectList);
    		
    		}else if(sesion.getArea().equals('I')){
//    			
//    			List<ViewProyectTienda> temporalProyectListView = proyectLoanService.getProyectLoanByFilteringInv(cad, typeSearch, sesion.getProspectus_id().intValue(), sesion.getCompany_id().intValue());
//    			createProyectListView(temporalProyectListView);
//    			
    		}else{
    			
    			temporalProyectList = proyectLoanService.getProyectLoanByFiltering(cad);
    			createProyectList(temporalProyectList);
    			
    		}
    		
    		
    		
    		proyectListForInvesInd = new ArrayList<ItemLoanList>();
    		ammountFoundedInv = 0D;
    		calculaInvestmentBite();
    		
    	}
    	
    	String[] varstatus = getStatus.split(",");
    	scriptStatus = "<script>";
    	if(varstatus != null && varstatus.length >0 ){
    		
			for(int i = 0; i < varstatus.length ; i++ ){
	    		
	    		
	    		scriptStatus += "$('#status_"+varstatus[i].replaceAll("'", "")+"').attr('checked', true);";
	    		
	    	}
			
		}
    	
    	if(riskCad != null && riskCad.length()  >0){
    		
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
    		
    	}
    	
    	scriptStatus += "</script>";
	    	
    	setPageDataTable();
    	
    	
    	
    }
    
    public void setPageDataTable() {
        DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent(":table_proyect_list:creditos");
        
        d.setFirst(0);
        
    }
    
    public Double getExpectedPerformanceByInvestorId(String InvId)
    {
    	Double suma=0.0;
    	List<ProyectLoan> theList = proyectFundingService.getProyectsOfInvestorById(InvId);
    	
    	if(Double.valueOf(InvId)>0.0)
    	{
    		for(Object x: theList)
    		{
        		Object[] pair = (Object[]) x;
        		BigDecimal z =  (BigDecimal)pair[0];
        		BigDecimal y = (BigDecimal) pair[1];
        		Byte t = (Byte) pair[2];
        		Byte f = (Byte) pair[3];
        		Double amount = z.doubleValue();
        		Double rate = y.doubleValue();
        		Integer term = t.intValue();
        		Integer freq = f.intValue();
        		suma=suma+getExpectedPerformanceForProyect(amount,rate,term,freq);
        	}
        	return suma;
    	}
    	else return 0.0;
    	
    }
    
    public String storeInvestmentInWS(String solicitudCreditoId,String safiCreditId,  String cuentaAhorroId,String prospectus_investor_id, Double rate, Double investmentBite,String fundingType, int company_id,int prospectus_id)
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
			
			SolicitudInversionResponse res1 = servicioSafi.solicitudInversion(solicitudInversionRequest);
			
			//System.out.println("- " + res1.getInfoDetalleCuotas() );
			//System.out.println("- " + res1.getCodigoRespuesta() );
			//System.out.println("- " + res1.getMensajeRespuesta() );
			//System.out.println("- " + res1.getSolicitudFondeo() );
			
			this.ExceptionOnFunding = res1.getMensajeRespuesta(); 
			
			ServiceCalling srvCall;
			
			if(!res1.getCodigoRespuesta().equals("0") &&  !res1.getCodigoRespuesta().equals("00") && !res1.getCodigoRespuesta().equals("000"))
			{
		    	srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(this.ExceptionOnFunding);
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingService.saveServiceCall(srvCall);
				
		    }else{
				
				srvCall = new ServiceCalling();
			    srvCall.setAcces_datetime(new Date());
			    srvCall.setCompany_id(company_id);
			    srvCall.setStatus(2);
			    srvCall.setProspectus_id( prospectus_id );
			    
			    String v = res1.getInfoDetalleCuotas();
			    
			    if(v.length()>1900)
			    {
			    	v= v.substring(0,1900);
			    }
			    
			    srvCall.setInfo("Regresando Satisfactoriamente de solicitudInversion: "+v);
			    
			    servicecallingService.saveServiceCall(srvCall);
			    
			}
			
			return res1.getCodigoRespuesta();
			
    	}catch (Exception e) {
    		
    		e.printStackTrace();
    		return "-1";
    		
		}
    	
    }

	/*public void setCuentaActual(String cuentaActual) {
		if(cuentaActual==null){
			this.cuentaActual = "-1";
		}
		else this.cuentaActual = cuentaActual;
	}*/

	/*
	public void handleAccountChange(ValueChangeEvent ce){
		if(ce.getNewValue()!=null){
			this.setTagActual(ce.getNewValue().toString());
			this.setCuentaActual(this.tagAndCuenta.get(this.tagActual));
			this.saldoActual=Double.parseDouble(this.cuentaAndSaldo.get(this.cuentaActual));
		}
		else{
			this.saldoActual = this.getTotalCreditOnAccounts();
			this.cuentaActual = "-1";
			this.tagActual = "-1";
		}
		
	}*/

	public void changeAccount(){
		
		if(getCuentaActual()!=null && getCuentaActual()!="")
		{
			setTagAccount(getCuentaActual());
			
			try {
				
				for (InvestorsAccounts iterElement : getListInvAccounts()) 
				{
					 if(iterElement.getAccount().equals(getCuentaActual()))
					 {
						 setCuentaActual(iterElement.getAccount());
						 setSaldoActual(iterElement.getSaldo());
						 break;
					 }
				}
				
			}catch (Exception e) 
			{
				log.info("Se genero un error al hacer la suma del saldo de cuentas");
			}
			
		}else{
			
			setTagAccount("Ninguna cuenta");
			setCuentaActual("");
			setSaldoActual(getTotalCreditOnAccounts());
			
		}
		
		if(getSaldoActual() > montoLimiteMaxSugerido){
			displayMsgMaxSug = true;
		}else if( getSaldoActual() < montoLimiteMinSugerido ){
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


	public void setTagActual(String tagActual) {
		if(tagActual == null){
			this.tagActual = "-1";
		}
		else this.tagActual = tagActual;
	}
	
	public String getHtmlCodeForTermFilter(){
		String htmlCode = new String("");
		int dividendoPar = 0;
		for(byte x: onlyTermOfProyectLoan){
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
	
	public String getKuboBarPorcent(ProyectLoan proyect){
		try {
			List<BigDecimal> amount = this.proyectFundingService.getIFAmountFunding(proyect.getProyectloanPk());
			//ProyectLoan proyect = this.proyectLoanService.findProyect(key);
			
			if(amount.size()>0){
				BigDecimal kuboAmmount=amount.get(0);
				
				String KFAmmount = kuboAmmount.toString();
				Double montoDeProyecto = proyect.getBottomPorcentParametrized(KFAmmount);
				
				if(montoDeProyecto<=0.0){
					return "width: 0.0%;";
				}
				else{
					return "width: "+montoDeProyecto.toString()+"%;";
				}
			}else{
				return "width: 0.0%;";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "width: 0.0%;"; 
		}
		
	}
	
	public String getKuboBarPorcentFromView(ViewProyectTienda proyect){
		try {
//			List<BigDecimal> amount = this.proyectFundingService.getIFAmountFunding(proyect.getProyectloanPk());
//			//ProyectLoan proyect = this.proyectLoanService.findProyect(key);
//			
//			if(amount.size()>0){
//				BigDecimal kuboAmmount=amount.get(0);
//				
//				String KFAmmount = kuboAmmount.toString();
//				Double montoDeProyecto = proyect.getBottomPorcentParametrized(KFAmmount);
//				
//				if(montoDeProyecto<=0.0){
//					return "width: 0.0%;";
//				}
//				else{
//					return "width: "+montoDeProyecto.toString()+"%;";
//				}
//			}else{
//				return "width: 0.0%;";
//			}
			
			return "width: 0.0%";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "width: 0.0%;"; 
		}
		
	}
	
	public List<ProyectFunding> getMaxInvertionOnProyectFromInvestor(ProyectLoanPK key, Integer InvID){
		try {
			if(key!=null && InvID!=null)
			{
				List<ProyectFunding> amount = this.proyectFundingService.getMaxProyectFundingByInvOnProyect(key, InvID);
				
				if(amount.size()>0)
				{
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
		
		boolean flag = false;
		
		if(rolesearching == null ){
		
			flag =  true;
			
		}else{
			String val[] = rolesearching.getStatus_id().split(",");
			for( int i = 0; i< val.length; i++ ){
				
				if( val[i].equals( status.toString() )) {
				
					flag = true;
					break;
					
				}else{
					
					flag =  false;
					
				}
			}
			
		}
		
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
		
		int total= this.proyectList.size();
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
		
		if (sesion.getArea().equals('M')) 
		{		
			cal1 = Calendar.getInstance();			
			cal1.setTime(new Date());									
			
			control_table = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
			
			evento_AJAX.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
			
			summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
						
			summarysesion.setPerson(false);			
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);			
			summarysesion.setTypeLog("EVA");
			
			navigation = null;
			
			request.addPartialUpdateTarget("form_Prin");
			request.addPartialUpdateTarget("actualPage");
			request.addPartialUpdateTarget("pnlCancel");
			
			validaIsCancel( summarysesion.getSearchSummary() );
			control_table.cambiaPagina(evento_AJAX);
			
			
			
		} else {	
			
			summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
			
			navigation = "resumen";
		}
		
		proyectList = new ArrayList<ItemLoanList>();
	}
	
	public String redireNavigation()
	{
		return getNavigation();
	}
	
	public void goToLogs(ActionEvent e)
	{
		String value=(String) e.getComponent().getAttributes().get("proyectAtrr").toString();	
		String valueLog=(String) e.getComponent().getAttributes().get("gotypeLog").toString();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		if (sesion.getArea().equals('M')) {
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			
			MenuControlTableBean navMenu= (MenuControlTableBean) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "menuControlTableBean");			
			
			e.getComponent().getAttributes().put("section", "controlTable/Logs::16::menu4");
			navMenu.cambiaPagina(e);
			setLogs_navegation(null);
			
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
	}
	
	public String actionNavLogs(){
		return getLogs_navegation();
	}
	
	private void createProyectListView(List<ViewProyectTienda> temporalProyectListView){
		
		proyectList = new ArrayList<ItemLoanList>();
		
		for(ViewProyectTienda prln : temporalProyectListView ){
			
			ItemLoanList item = new ItemLoanList();
			
			if(sesion.getArea() == 'I'){
				
				TiendaCreditos tienda = tiendacreditosservice.getTiendaCreditosItemBySolOrCred( prln.getSafi_mx_solicitud_id(), prln.getSafi_credit_id() );
				
				if(tienda != null){
					
					////System.out.println("tienda != null "+tienda.getSafi_mx_solicitud_id());
					
					item.setActualTerm( tienda.getPlazoEnDias()+" Días" );
					
					if( tienda.getSafi_credit_id()==null || Integer.parseInt( tienda.getSafi_credit_id() ) == 0){
						
						
						ProyectLoanPK tmpPk = new  ProyectLoanPK();
						
						tmpPk.setCompany_id(prln.getProyectloanPk().getCompany_id());
						tmpPk.setProspectus_id(prln.getProyectloanPk().getProspectus_id());
						tmpPk.setProyect_id(prln.getProyectloanPk().getProyect_id());
						tmpPk.setProyect_loan_id(prln.getProyectloanPk().getProyect_loan_id());
						
						List<BigDecimal> ammountlst =proyectFundingService.getAmountFundedByProyectLoanPK(tmpPk);
						
						BigDecimal deci = BigDecimal.ZERO;
						
						for(BigDecimal amm : ammountlst ){
							
							////System.out.println(amm);
							
							deci = deci.add(new BigDecimal( amm+"" ));
							
						}
						
						Double disponible = prln.getAmmount()-((prln.getAmmount()*10)/100)-(Double.parseDouble( deci.toString() ));
						
						item.setAvailableAmmount(disponible);
						
					}else{
						
						item.setAvailableAmmount( Double.parseDouble( tienda.getDisponibleFondeo() ) );
						
					}
					
					item.setAvailableDays( tienda.getDiasPorTrans() + " Días" );
					
					item.setAmmount( tienda.getMontoCredito() );
					
					if( prln.getSafi_credit_id() != null ){
						
						item.setActualAmmount( tienda.getSaldoCredito() );
						item.setDaysLeftStrInv("DESEMBOLSADO");
						
					}else{
						
						item.setActualAmmount( tienda.getMontoCredito() );
						item.setDaysLeftStrInv(prln.getDaysLeft()+" Días");
						
					}
					
//					List<ViewInvestmetInProyect> listInvestors = new ArrayList<ViewInvestmetInProyect>(); 
//					
//					if( prln.getSafi_credit_id() != null && !prln.getSafi_credit_id().equals("0") ){
//						
//						listInvestors = (List<ViewInvestmetInProyect>)proyectFundingService.getListInvestorbyProyectId( Integer.parseInt( prln.getSafi_credit_id() ) , null );
//					
//					}else if(  prln.getSafi_mx_solicitud_id() != null && !prln.getSafi_mx_solicitud_id().equals("0")  ) {
//						listInvestors = (List<ViewInvestmetInProyect>)proyectFundingService.getListInvestorbyProyectId( null,  prln.getSafi_mx_solicitud_id()  );
//					}
//					
//					
//					if( listInvestors != null ){
//						item.setNumInvestors( listInvestors.size()+"" );
//					}
					
					
				
				}else{
					
					////System.out.println("tienda == null prln_id = "+prln.getProyectloanPk().getProyect_loan_id()+" mx_solicitud: "+prln.getSafi_mx_solicitud_id()+" credit:  "+prln.getSafi_credit_id() );
					
					item.setActualAmmount( null );
					
					item.setActualTerm( null );
					
					item.setAvailableAmmount( null);
					
					item.setAvailableDays( null );
					
					item.setAmmount(prln.getAmmount());
					
				}
				
				// aSIGNACION DE MONTO QUE PUEDE INVERTIR CADA INVERSIONISTA
				item.setInvestment_bite(investmentBiteVAL);
				
			}else{
				
				item.setActualAmmount( null );
				
				item.setActualTerm( null );
				
				item.setAvailableAmmount( null);
				
				item.setAvailableDays( null );
				
				item.setAmmount(prln.getAmmount());
				
				item.setInvestment_bite(prln.getInvestment_bite());
				
				item.setNumInvestors(prln.getInvestorsInt()+"");
				
			}
			
				
				item.setAmmountLeft(prln.getAmmountLeft());
				item.setAmount_founded(prln.getAmount_founded());
				item.setBarPorcentTotal(prln.getBarPorcentTotal());
				item.setBc_score_range(prln.getBc_score_range());
				item.setBottomPorcent(prln.getBottomPorcent());
				item.setCompany_id(prln.getProyectloanPk().getCompany_id());
				item.setCompleteName(prln.getPerson().NombreCompletoNPM());
				item.setDaysLeft(prln.getDaysLeft());
				
				// Desabilitado para fondeo Global 2014-04-08 RMB
				// item.setEnabledFundingBtn(getEnabledFlag(prln,sesion.getProspectus_id()));
				// prueba para fondeo Global
				item.setEnabledFundingBtn(false);
				
				item.setEnabledFundingDetailBtn(prln.getEnabledBottomDetail());
				item.setExpectedPerformanceForProyect(getExpectedPerformanceForProyect(prln.getInvestment_bite2(saldoActual+""), prln.getRate_investor(), prln.getTerm().getMonths(), prln.getFrequency_id()));
				item.setFrequency_id(prln.getFrequency_id());
				//item.setInvestment_bite(prln.getInvestment_bite());
				item.setKubo_score_a(prln.getKubo_score_a());
				item.setKubo_score_b(prln.getKubo_score_b());
				item.setKuboBarPorcent(getKuboBarPorcentFromView(prln));
				
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
				item.setSaldoActual(saldoActual);
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
				
				item.setProyectFunding(getMaxInvertionOnProyectFromInvestor(prln.getProyectloanPk(), sesion.getProspectus_id()));
			
			proyectList.add(item);
			
			
		}
	}
	
	private void createProyectList(List<ProyectLoan> temporalProyectList)
	{
		
		proyectList = new ArrayList<ItemLoanList>();
		
		for(ProyectLoan prln : temporalProyectList ){
			
			ItemLoanList item = new ItemLoanList();
			
			if(sesion.getArea() == 'I'){
				
				TiendaCreditos tienda = tiendacreditosservice.getTiendaCreditosItemBySolOrCred( prln.getSafi_mx_solicitud_id(), prln.getSafi_credit_id() );
				
				if(tienda != null){
					
					////System.out.println("tienda != null "+tienda.getSafi_mx_solicitud_id());
					
					item.setActualTerm( tienda.getPlazoEnDias()+" Días" );
					
					if( tienda.getSafi_credit_id()==null || Integer.parseInt( tienda.getSafi_credit_id() ) == 0){
						
						
						ProyectLoanPK tmpPk = new  ProyectLoanPK();
						
						tmpPk.setCompany_id(prln.getProyectloanPk().getCompany_id());
						tmpPk.setProspectus_id(prln.getProyectloanPk().getProspectus_id());
						tmpPk.setProyect_id(prln.getProyectloanPk().getProyect_id());
						tmpPk.setProyect_loan_id(prln.getProyectloanPk().getProyect_loan_id());
						
						List<BigDecimal> ammountlst =proyectFundingService.getAmountFundedByProyectLoanPK(tmpPk);
						
						BigDecimal deci = BigDecimal.ZERO;
						
						for(BigDecimal amm : ammountlst ){
							
							////System.out.println(amm);
							
							deci = deci.add(new BigDecimal( amm+"" ));
							
						}
						
						Double disponible = prln.getAmmount()-((prln.getAmmount()*10)/100)-(Double.parseDouble( deci.toString() ));
						
						item.setAvailableAmmount(disponible);
						
					}else{
						
						item.setAvailableAmmount( Double.parseDouble( tienda.getDisponibleFondeo() ) );
						
					}
					
					item.setAvailableDays( tienda.getDiasPorTrans() + " Días" );
					
					item.setAmmount( tienda.getMontoCredito() );
					
					if( prln.getSafi_credit_id() != null ){
						
						item.setActualAmmount( tienda.getSaldoCredito() );
						item.setDaysLeftStrInv("DESEMBOLSADO");
						
					}else{
						
						item.setActualAmmount( tienda.getMontoCredito() );
						item.setDaysLeftStrInv(prln.getDaysLeft()+" Días");
						
					}
					
//					List<ViewInvestmetInProyect> listInvestors = new ArrayList<ViewInvestmetInProyect>(); 
//					
//					if( prln.getSafi_credit_id() != null && !prln.getSafi_credit_id().equals("0") ){
//						
//						listInvestors = (List<ViewInvestmetInProyect>)proyectFundingService.getListInvestorbyProyectId( Integer.parseInt( prln.getSafi_credit_id() ) , null );
//					
//					}else if(  prln.getSafi_mx_solicitud_id() != null && !prln.getSafi_mx_solicitud_id().equals("0")  ) {
//						listInvestors = (List<ViewInvestmetInProyect>)proyectFundingService.getListInvestorbyProyectId( null,  prln.getSafi_mx_solicitud_id()  );
//					}
//					
//					
//					if( listInvestors != null ){
//						item.setNumInvestors( listInvestors.size()+"" );
//					}
					
					
				
				}else{
					
					////System.out.println("tienda == null prln_id = "+prln.getProyectloanPk().getProyect_loan_id()+" mx_solicitud: "+prln.getSafi_mx_solicitud_id()+" credit:  "+prln.getSafi_credit_id() );
					
					item.setActualAmmount( null );
					
					item.setActualTerm( null );
					
					item.setAvailableAmmount( null);
					
					item.setAvailableDays( null );
					
					item.setAmmount(prln.getAmmount());
					
				}
				
				// aSIGNACION DE MONTO QUE PUEDE INVERTIR CADA INVERSIONISTA
				item.setInvestment_bite(investmentBiteVAL);
				
			}else{
				
				item.setActualAmmount( null );
				
				item.setActualTerm( null );
				
				item.setAvailableAmmount( null);
				
				item.setAvailableDays( null );
				
				item.setAmmount(prln.getAmmount());
				
				item.setInvestment_bite(prln.getInvestment_bite());
				
				//item.setNumInvestors(prln.getInvestorsInt()+"");
				
			}
			
				
				item.setAmmountLeft(prln.getAmmountLeft());
				item.setAmount_founded(prln.getAmount_founded());
				item.setBarPorcentTotal(prln.getBarPorcentTotal());
				item.setBc_score_range(prln.getBc_score_range());
				item.setBottomPorcent(prln.getBottomPorcent());
				item.setCompany_id(prln.getProyectloanPk().getCompany_id());
				item.setCompleteName(prln.getPerson().NombreCompletoNPM());
				item.setDaysLeft(prln.getDaysLeft());
				
				// Desabilitado para fondeo Global 2014-04-08 RMB
				// item.setEnabledFundingBtn(getEnabledFlag(prln,sesion.getProspectus_id()));
				// prueba para fondeo Global
				item.setEnabledFundingBtn(false);
				
				item.setEnabledFundingDetailBtn(prln.getEnabledBottomDetail());
				item.setExpectedPerformanceForProyect(getExpectedPerformanceForProyect(prln.getInvestment_bite2(saldoActual+""), prln.getRate_investor(), prln.getTerm().getMonths(), prln.getFrequency_id()));
				item.setFrequency_id(prln.getFrequency_id());
				//item.setInvestment_bite(prln.getInvestment_bite());
				item.setKubo_score_a(prln.getKubo_score_a());
				item.setKubo_score_b(prln.getKubo_score_b());
				item.setKuboBarPorcent(getKuboBarPorcent(prln));
				
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
				item.setSaldoActual(saldoActual);
				item.setStatus_id(prln.getStatus_id());
				item.setTerm_months(prln.getTerm().getMonths());
				item.setVerification_score(prln.getVerification_score());
				item.setVerificationClass(prln.getVerificationClass()+"");
				item.setVerificationRange(prln.getVerificationRange());
				
				item.setName(prln.getStatusProyect().getName());
				item.setUrl_img(prln.getStatusProyect().getUrl_img());
				
				item.setSafi_credit_id( prln.getSafi_credit_id() );
				item.setSafi_solicitud_id( prln.getSafi_mx_solicitud_id() );
				
				
				item.setProyectFunding(getMaxInvertionOnProyectFromInvestor(prln.getProyectloanPk(), sesion.getProspectus_id()));
			
			proyectList.add(item);
			
			
		}
		
	}
	
	private void setPermissions(int role_id)
	{
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		RoleFunctionController rfc = (RoleFunctionController) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "roleFunctionController");
		
		List<RoleFunction> rolefunctionlistbyrole = rfc.getFunctionByRole(role_id);
		
		for(RoleFunction rf : rolefunctionlistbyrole)
		{
			
			if(rf.getPk().getFunction_id() == 1){ //permiso para Fondear
				
				fundingFunction = true;
				
			}
			
		}
		
	}
	
	

	public void getGarantia(){
		////System.out.println("Generando garantía..............");
		
			
			if (valuesforGarantia != null){ 
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
				
				
				Double porcentaje = Math.rint(active.getPorcentajefondeo()*100)/100;
				
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
		        	 str += "<td style='text-align:left;vertical-align: top;'>FECHA</td>";
		        	 str += "<td style='text-align:center;padding-right: 8px;vertical-align: top;'>CAPITAL</td>";
		        	 str += "<td style='text-align:center;padding-right: 8px;vertical-align: top;'>INTERÉS</td>";
		        	 str += "<td style='text-align:center;padding-right: 8px;vertical-align: top;'>RETENCIÓN <br /> DE ISR</td>";
		        	 str += "<td style='text-align:right;padding-right: 8px;vertical-align: top;'>TOTAL</td>";
		        	 str += "<tr>";
		        	 
		        	 for( AmortizacionInversionista amort : lst ){
		        		 Double total = 0D;
		        		 str += "<tr>";
		        		 	str += "<td style='text-align:left;' >"+formatStr1.format(amort.getFechaExigible())+"</td>";
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

	public void inviertePaquete(  ){
		
		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		
		invierteList(proyectList);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime( new Date() );
		
		long l = c2.getTimeInMillis() - c.getTimeInMillis();
		
		Long seg = l/1000;
		
		long m = l%1000;
		
		Long min =  Long.valueOf((seg.intValue()))/60;
		
		seg = Long.valueOf((seg.intValue()))%60;
		
		System.out.println( "Tiempo total en Ejecutar el poceso de fondeo: " + min.intValue()+"m " + seg +"s " + m+"ms");
		
	}
	
	public void invierteListSel(  ){
		invierteList(proyectListForInvesInd);
	}
	
	public void invierteList( List<ItemLoanList> listForInv ){
		
		int LIST_INV_SIZE = listForInv.size();
		
		try{
				
				ServiceCalling srvCall = new ServiceCalling();
			    srvCall.setAcces_datetime(new Date());
			    srvCall.setCompany_id(listForInv.get(0).getCompany_id());
			    srvCall.setStatus(1);
			    srvCall.setProspectus_id(sesion.getProspectus_id());
			    srvCall.setInfo("Iniciando Inversión por Paquete ("+LIST_INV_SIZE+") ");
			    servicecallingService.saveServiceCall(srvCall);
				
			    int inicio = srvCall.getService_calling_id();
				
			    srvCall = new ServiceCalling();
			    srvCall.setAcces_datetime(new Date());
			    srvCall.setCompany_id(listForInv.get(0).getCompany_id());
			    srvCall.setStatus(1);
			    srvCall.setProspectus_id(sesion.getProspectus_id());
			    srvCall.setInfo("Iniciando Inversión por Paquete inicio "+inicio+" ( 0 de "+LIST_INV_SIZE+" ) ");
			    servicecallingService.saveServiceCall(srvCall);
			    
			    int i = 0;
			    
			    creditFounded = 0;
			    ammountFounded = 0D;
			    investmentBite = 0D;
			    
			    creditNotFounded = 0;
			    ammountNotFounded = 0D;
			    
			    String  account = getTagAccount();
			    
				for(ItemLoanList proyectInv : listForInv){
					
					investmentBite = proyectInv.getInvestment_bite();
					
				    if(proyectInv.getInvestment_bite() > 0)
				    {
					    investmentAction(proyectInv.getProyect_loan_id(),proyectInv.getProyect_id(),proyectInv.getProspectus_id(),proyectInv.getCompany_id(),sesion.getProspectus_id(),account,proyectInv.getInvestment_bite(),false,proyectInv);
				    }
				    
				    i++;
				    
				    srvCall.setAcces_datetime(new Date());
				    srvCall.setCompany_id(proyectInv.getCompany_id());
				    srvCall.setStatus(1);
				    srvCall.setProspectus_id(sesion.getProspectus_id());
				    srvCall.setInfo("Invirtiendo inicio "+inicio+" ( "+i+" de "+LIST_INV_SIZE+" ) ");
				    servicecallingService.updateServiceCall(srvCall);
				    
				}
				
		}catch( Exception e ){
			
			e.printStackTrace();
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
		    srvCall.setCompany_id(listForInv.get(0).getCompany_id());
		    srvCall.setStatus(1);
		    srvCall.setProspectus_id(sesion.getProspectus_id());
		    srvCall.setInfo("Ocurrió un ERROR a la hora de fondeo:"+e.getMessage()+" --- Solo se fondearon "+LIST_INV_SIZE+" proyectos ");
		    srvCall.setException( e.getMessage() );
		    
		    servicecallingService.updateServiceCall(srvCall);
			
		}finally{
			
			
			Calendar pc = Calendar.getInstance();
			pc.setTime( new Date() );
			
			//Actualizamos la tienda
			tiendacreditosservice.actualizaTienda();
			
			//Actualizamos Saldo
			setupClientSAFIData(this.SAFI_cuenta);
			
			//Recargamos la Lista
			setupList();
			
			calculaInvestmentBite(  );
		
			Calendar pc1 = Calendar.getInstance();
			pc1.setTime( new Date() );
			
			long l = pc1.getTimeInMillis() - pc.getTimeInMillis();
			
			Long seg = l/1000;
			
			long m = l%1000;
			
			Long min =  Long.valueOf((seg.intValue()))/60;
			
			seg = Long.valueOf((seg.intValue()))%60;
			
			System.out.println( "Tiempo total en Ejecutar el poceso PostFondeo: " + min.intValue()+"m " + seg +"s " + m+"ms");
			
		}
		
	}
	
	private void setMontoMaximo(){
		
		SystemParamPK sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(sesion.getCompany_id());
		sysPk.setSystem_param_id(52);//Monto máximo a invertir por paquete
		SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		montoMaximo = 0D;
		
		if(sys != null){
			montoMaximo = Double.parseDouble(sys.getValue());
		}
		
		displayInvButton = (saldoActual - montoMaximo) >0;
		
	}
	
	public void validaMontoAInvertir(){
		
		System.out.println("validaMontoAInvertir: " + getAmmoutToInv() + "  maximo a invertir: " + montoTotal );
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if( getAmmoutToInv() > montoTotal){
			
			System.out.println("Monto mayor !!");
			
			requestContext.addCallbackParam("hasError", true );
			requestContext.addCallbackParam("montoSugerido", montoTotal );
			requestContext.addCallbackParam("msgInv", " El moto que desea invertir supera el saldo disponible de su cuenta " );
			
		}else if( getAmmoutToInv() < montoMinG){
			System.out.println("Monto mayor !!");
			
			requestContext.addCallbackParam("hasError", true );
			requestContext.addCallbackParam("montoSugerido", montoMinG );
			requestContext.addCallbackParam("msgInv", " El moto que desea invertir es menor al monto mínimo permitido" );
		}else{
			
			calculaInvestmentBite();
			
			requestContext.addCallbackParam("hasError", false );
			requestContext.addCallbackParam("montoSugerido", "" );
			requestContext.addCallbackParam("msgInv", "" );
			
		}
		
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
	
	public void calculaInvestmentBite(  ){
		
		//InvestorsAccounts account = null;
		
		Double monto_a_invertir_acum = 0D;
		
		SystemParamPK sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(sesion.getCompany_id());
		sysPk.setSystem_param_id(55);//Monto mínimo que se puede invertir en cada proyecto
		
		SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		Double montoMin = Double.parseDouble( sys.getValue() );

		montoMinG = montoMin;
		
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
		
//		
//		Double porcMaxPry = 0.0D;
//		Double porcMaxSaldo = 0.0D;
//		
//		if(saldoTotal >= 250 && saldoTotal <= 10000 )
//		{
//			porcMaxPry 		= 1.0D;
//			porcMaxSaldo 	= 2.5D;
//			
//		}else if(saldoTotal > 10000 && saldoTotal <= 100000 ) 
//		{
//			porcMaxPry 		= 1.5D;
//			porcMaxSaldo 	= 2.5D;
//			
//		}else if(saldoTotal > 100000 && saldoTotal <= 250000) 
//		{
//			porcMaxPry 		= 1.5D;
//			porcMaxSaldo 	= 2.5D;
//			
//		}else if(saldoTotal > 250000 && saldoTotal <= 500000) 
//		{
//			porcMaxPry 		= 1.5D;
//			porcMaxSaldo 	= 2.5D;
//			
//		}else if(saldoTotal > 500000 ) 
//		{
//			porcMaxPry 		= 1.5D;
//			porcMaxSaldo 	= 2.5D;
//			
//		}
//		
		if(sesion.getArea().toString().equals("I"))
		{
		
//			for(int i = 0 ; i < listInvAccounts.size() ; i++ )
//			{
//				
//				if(tagAccount.equals( listInvAccounts.get( i ).getAccount() ))
//				{
//				
//					account =  listInvAccounts.get(i );
//				
//				}
//				
//			}
//			
//			
//			
//			if(account != null)
//			{
//				
//				//System.out.println( " ---- "+account.getSaldo()+ "   ---  " + Double.parseDouble( (proyectList.size())  + "") );
//				
//				//monto_a_invertir = account.getSaldo();
//				
//				monto_a_invertir_acum = 0D;
			
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
				Double biteAmmountIni = 0D;
				
				if(investmentBiteVAL<montoMin)
				{
					
					biteAmmountIni = montoMin;
					
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
				
				if(maxAmmountBalance < montoMin){
					maxAmmountBalance = montoMin;
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
						
						
						Double maxProyectAmmount =  (item.getAmmount()*porcMaxPry)/100;
						
						Double max_monto_a_fondear = maxProyectAmmount>maxAmmountBalance?maxAmmountBalance:maxProyectAmmount;
						
						// System.out.println( "-- proyecto: "+item.getProyect_loan_id() + " maximo a invertir:  " + maxProyectAmmount);
						
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
						
//						if( item.getAmmountPreviousFounded() != null && flagListFunding ){
//						
							//List<ProyectFunding> lstFunding =  proyectFundingService.getproyectbyInvestor( (sesion.getProspectus_id()+"") , (item.getProyect_loan_id()+"") );
//							
						String key = item.getProspectus_id() + "::"+ item.getProyect_loan_id();
						
						List<ProyectFunding> lstFunding =  hTFunding.get(key);
						
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
							
//							if( t > item.getAvailableAmmount() || !flgfirst ){
								
								t = (Math.floor(t*100))/100;
								
								item.setInvestment_bite(t);
								
								// System.out.println("Cachito asignado al proyecto: "+biteAmmount);
								
								monto_a_invertir_acum += biteAmmount;
								 
								// System.out.println("Acumulado de inversión al proyecto: "+item.getInvestment_bite() );
							
//							}else{
//								
//								System.out.println( "No se fondea en esta vuelta: sobre pasa el monto disponible" );
//								//continue;
//								
//							}
							
						}else{
							
							if( monto_a_invertir_temp < montoMin && item.getInvestment_bite() != 0  )
							{
								
								if( (item.getInvestment_bite() + monto_a_invertir_temp)<= max_monto_a_fondear )
								{
									
									item.setAvailableAmmount(  item.getAvailableAmmount()- monto_a_invertir_temp);
									
									Double t = item.getInvestment_bite()+monto_a_invertir_temp;
									
									t = (Math.floor(t*100))/100;
									
									item.setInvestment_bite(t);
									
									
									
									System.out.println("Cachito asignado al proyecto: "+monto_a_invertir_temp );
									 
									System.out.println("Acumulado de inversión al proyecto: "+item.getInvestment_bite() );
									
									monto_a_invertir_acum += monto_a_invertir_temp;
									
									monto_a_invertir_temp = 0D;
									
									break;
									
								}
								
								
								
							}else{
							
									System.out.println( "monto no suficiente: disponible- "+monto_a_invertir_temp+"  cachito"+ biteAmmount);
									continue;
									
							}
							
						}
						
					}
				}
				
			}
			
			/* 
			 * Se remueven todos los proyectos en los que no se puede invertir
			 */
			
			System.out.println("Inicia: " + proyectList.size() );
			
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
			
			System.out.println("primer recorte: " + proyectList.size() );
			
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
			
			System.out.println("segundo recorte: " + proyectList.size() );
			
			for( ItemLoanList t : lstFondeadoPrev ){
				
				proyectList.add(t);
				
			}
			
			asignaListForInvest();
			
			
			
			//Se envian al final los proyectos que no se pueden fondear 
			for( ItemLoanList t : temp ){
				
				if( t.getAvailableAmmount() > montoMin ){
				
					t.setBlnChck(false);
					
					proyectList.add(t);
				}
			}
			
			for( ItemLoanList t : lstFondeadoPrev_SinInv ){
				
				proyectList.add(t);
				
			}
			
			System.out.println("prviamente fondeados: "+previous);
			System.out.println(" se removieron "+r+" elementos");
			
			System.out.println("finalizó con: " + proyectList.size() );
			
			
			monto_a_invertir = monto_a_invertir_acum;
			
			
			
		}
		
		
		
		
	}
	
	private void asignaListForInvest(){
		
		proyectListForInvesInd = new ArrayList<ItemLoanList>(); 
		ammountFoundedInv = 0D;
		
		for( ItemLoanList item : proyectList ){
			proyectListForInvesInd.add(item);
			
			ammountFoundedInv += item.getInvestment_bite();
			
		}
		
	}
	
	public void pruebaTienda(){
		
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
		
		if( lstProyectTienda != null && lstProyectTienda.size()>0 ){
			for( ProyectLoan pl : lstProyectTienda ){
				if( pl.getRate_investor() == null || pl.getRate_investor() == 0D ){
					
					TasasAcreditado tasa =  tasaacreditadoservice.getTasaAcreditadoByRate(pl.getRate());
					
					pl.setRate_investor(tasa.getRate_investor());
					
					proyectLoanService.update(pl);
					
				}
			}
		}
		
		
	}
	
	private String getHtml(String type){
		
		String res = "";
		
		try{
			File archivo = null; 
			
			  FileReader fr = null;
			  BufferedReader br = null;
			  StringBuilder sb = new StringBuilder();
			  
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsf/docs/");
			  //String path = new File("Escritorio/Desarrollo_Kubo/Kubodb/WebContent/jsf/docs").getAbsolutePath();
			  
			  if(type.equals("ticket")){
			  
				  path += "/ticketInv.html";
				  
			  }else if( type.equals("Garantia") ){
				  
				  path += "/comprobante_garantia.html";
				  
			  }
			  
			  //////System.out.println(path);
			  
			  archivo = new File (path);
			  fr = new FileReader (archivo);
			  br = new BufferedReader(fr);
			
			    // Lectura del fichero
			String linea;
			
			while((linea=br.readLine())!=null){
				
				sb.append(linea);
				
			}
		
			res= sb.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	private void initPerson()
	{
		 naturalPerson= naturalPersonService.getNaturalPersonById(new gnNaturalPersonPK(sesion.getProspectus_id(),sesion.getCompany_id()));
	}
	
	public void generaListForInv(AjaxBehaviorEvent e){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		System.out.println(" -*-*- Entrando a invertir generaListForInv -*-*- ");
		
		ItemLoanList item = (ItemLoanList) e.getComponent().getAttributes().get("proyectloanItem");
		
		boolean flagRestaInv = false;
		
		if( getNewBiteInv() != null && getNewBiteInv().trim().length()>0  && !item.isBlnChck() && item.getInvestment_bite() != null && Double.parseDouble(getNewBiteInv().trim()) != 0D){
			ammountFoundedInv -= Double.parseDouble( getNewBiteInv().trim() );
			flagRestaInv = true;
			
			System.out.println(" Monto que se elimino1: "+getNewBiteInv());
		}
		
		if(item.isBlnChck()){
			proyectListForInvesInd.add(item);
			
			ammountFoundedInv += item.getInvestment_bite();
			System.out.println("Monto despues de sumar: "+ammountFoundedInv+"   monto a sumar: "+item.getInvestment_bite());
			requestContext.addCallbackParam("displayInput", true);
			
			Double roundVal = (double)Math.round((item.getInvestment_bite())*100)/100;
			
			requestContext.addCallbackParam("montoFunded", roundVal);
			requestContext.addCallbackParam("proyect_loan_id", item.getProyect_loan_id());
			
		}else{
			proyectListForInvesInd.remove(item);
			
			if(!flagRestaInv){
				ammountFoundedInv -= item.getInvestment_bite();
				System.out.println(" Monto que se elimino2: "+item.getInvestment_bite());
			}
			
			requestContext.addCallbackParam("displayInput", false);
			requestContext.addCallbackParam("proyect_loan_id", item.getProyect_loan_id());
		}
		
		////System.out.println( item.isBlnChck()+"_"+ item.getProyect_loan_id()+"_"+item.getProyect_id()+"_"+item.getProspectus_id() );
	}
	
	public void asignaBite(AjaxBehaviorEvent e){
		
		String valAnt = "";
		Double maxAmmountTemp = ammountFoundedInv;
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		ItemLoanList item = (ItemLoanList) e.getComponent().getAttributes().get("proyectloanItem");
		
		System.out.println("nuevo valor : " + item.getInvestment_bite() + " valAnt: " + getNewBiteInv() );
		
		valAnt = getNewBiteInv();
		
		if(item.getInvestment_bite() == null || item.getInvestment_bite() == 0 ){
			
			if( valAnt != null && valAnt.trim().length()>0 ){
				
				ammountFoundedInv = ammountFoundedInv - Double.parseDouble(valAnt) ;
				proyectListForInvesInd.remove(item);
				item.setInvestment_bite(0D);
				proyectListForInvesInd.add(item);
				
			}
			
			setNewBiteInv( null );
			return;
		}
		
		
		
		if( valAnt != null && valAnt.trim().length()>0 ){
			maxAmmountTemp = ammountFoundedInv - Double.parseDouble(valAnt) ;
		}
		
		setNewBiteInv(item.getInvestment_bite() + "" );
		
		if( getNewBiteInv().trim().length()==0)
			return;
		
		
		
		System.out.println("Proyecto Seleccionado: " + item.getProyect_loan_id() );
		
		Double newVal = 0D;
		String msgInv = "";
		Double monto_sugerido = 0D;
		
		boolean flagNotInv1 = false;
		
		try
		{
			newVal = Double.parseDouble(getNewBiteInv());
		
		}catch(Exception e1 ){
			
			msgInv = "Cantidad Incorrecta ";
			monto_sugerido = 0D;
			flagNotInv1 = true;
			
			requestContext.addCallbackParam("hasError", (flagNotInv1));
			requestContext.addCallbackParam("msgInv", msgInv);
			requestContext.addCallbackParam("montoSugerido", monto_sugerido);
			requestContext.addCallbackParam("proyect_loan_id",item.getProyect_loan_id());
			
			return ;
		}
		
		
		
		if( newVal>0 && newVal < montoMinG){
			msgInv = "El monto debe ser mayor al monto mínimo que son "+dec.format(montoMinG);
			monto_sugerido = montoMinG;
			flagNotInv1 = true;
		}
		
		else if(newVal > maximoInvBySaldoG && !flagNotRule){
			msgInv = "El monto no debe superar "+dec.format(maximoInvBySaldoG)+" ya que es el "+porcMaxSaldoG+"% de tu saldo total.";
			
			maximoInvBySaldoG = (Math.rint(maximoInvBySaldoG*100)/100);
			monto_sugerido = maximoInvBySaldoG;
			flagNotInv1 = true;
		}
		else if(newVal > item.getAvailableAmmount()){
			msgInv = "El monto no debe superar  "+dec.format(item.getAvailableAmmount())+" ya que es el monto disponible con el que cuenta el proyecto";
			monto_sugerido = (Math.rint(item.getAvailableAmmount()*100)/100);
			flagNotInv1 = true;
		}
		else if((newVal + maxAmmountTemp) > montoSaldoG){
			msgInv = "El monto a invertir supera los " + dec.format(montoSaldoG);
			monto_sugerido = 0D;
			flagNotInv1 = true;
		}
		else{
			
			Double montoPorcentaje = (maxPorcPryG*item.getAmmount())/100;
			
			if(newVal > montoPorcentaje){
				msgInv = "El monto a invertir supera el límite permitido para este proyecto que es de "+dec.format(montoPorcentaje);
				monto_sugerido = 0D;
				flagNotInv1 = true;
			}else if( item.getAmmountPreviousFounded() != null)
				{
					if( ( item.getAmmountPreviousFounded() + newVal) > montoPorcentaje)
					{
						msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera el límite permitido para este proyecto que es de "+dec.format(montoPorcentaje);
						monto_sugerido = 0D;
						flagNotInv1 = true;
						
					}else if( ( item.getAmmountPreviousFounded() + newVal) > maximoInvBySaldoG ){
						msgInv = "El proyecto ya cuenta con un fondeo previo de "+dec.format( item.getAmmountPreviousFounded() )+", que sumado con lo que quiere invertir supera los "+dec.format(maximoInvBySaldoG)+" que es el "+porcMaxSaldoG+"% de tu saldo total.";
						
						maximoInvBySaldoG = (Math.rint(maximoInvBySaldoG*100)/100);
						monto_sugerido = maximoInvBySaldoG - item.getAmmountPreviousFounded();
						
						if( montoMinG > monto_sugerido )
						{
							monto_sugerido = 0D;
						
						}
						flagNotInv1 = true;
					}
				}
			
			
		}
		
		if( item.getAvailableAmmount() < montoMinG ){
			msgInv = "El monto disponible  es menor al monto mínimo que son "+dec.format(montoMinG);
			monto_sugerido = 0D;
			flagNotInv1 = true;
		}
		
		//if(!flagNotInv1){
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
		
		setNewBiteInv( null );
			
		requestContext.addCallbackParam("hasError", (flagNotInv1));
		requestContext.addCallbackParam("msgInv", msgInv);
		requestContext.addCallbackParam("montoSugerido", monto_sugerido);
		requestContext.addCallbackParam("proyect_loan_id",item.getProyect_loan_id());
		
	}
	
	public void quitarSeleccion(){
		
		proyectListForInvesInd.clear();
		
		for( ItemLoanList item : proyectList ){
			item.setBlnChck(false);
		}
		
		ammountFoundedInv = 0D;
		
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
	
	public void calculaConfirm(){
		
		aToInv=0;
		bToInv=0;
		cToInv=0;
		dToInv=0;
		eToInv=0;
		monto_a_invertir_total = 0D;
		
		for( ItemLoanList item : getProyectListForInvesInd() ){
			
			if( item.getKubo_score_a().equals("A") ){
				aToInv++;
			}
			if( item.getKubo_score_a().equals("B") ){
				bToInv++;
			}
			if( item.getKubo_score_a().equals("C") ){
				cToInv++;
			}
			if( item.getKubo_score_a().equals("D") ){
				dToInv++;
			}
			if( item.getKubo_score_a().equals("E") ){
				eToInv++;
			}
			monto_a_invertir_total = monto_a_invertir_total +item.getInvestment_bite();
		}
		
		System.out.println("Termina el Cálculo");
		
	}
	
	private Hashtable<String,List<ProyectFunding> > generaHashFunding(){
		
		
		List<ProyectFunding> lstFunding =  proyectFundingService.getListProyectFunByInvestor( sesion.getProspectus_id() , sesion.getCompany_id() );
		
		Hashtable<String,List<ProyectFunding> > ht = new Hashtable<String,List<ProyectFunding> >();
		
		for( ProyectFunding pf : lstFunding ){
			
			String key = pf.getProyectloanfundingPk().getProspectus_id()+"::"+pf.getProyectloanfundingPk().getProyect_loan_id();
			
			List<ProyectFunding> lstTmp = ht.get(key);
			
			if( lstTmp == null ){
				lstTmp = new ArrayList<ProyectFunding>();
			}
			
			lstTmp.add(pf);
			
			ht.put(key, lstTmp);
			
		}
		
		return ht;
		
	}
	
}