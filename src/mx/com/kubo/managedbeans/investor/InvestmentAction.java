package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ItemInversion;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.controller.ThreadInvestmentAction;
import mx.com.kubo.controller.inversion.Inversion;
import mx.com.kubo.managedbeans.ListaCreditos;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ServiceCalling;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;

import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.ConsultaInverResponse;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;
import safisrv.ws.InvKuboServicios.SolicitudInversionRequest;
import safisrv.ws.InvKuboServicios.SolicitudInversionResponse;

@ManagedBean
@ViewScoped
public class InvestmentAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private 			List<ItemLoanList>   			proyectList;
	private 			List<String>   					storedString;
	private 			int 							numProyectToInv;
	private 			List<ServiceCalling> 			lstService;
	private				List<ProyectFunding>			lstProyectosFondeados;
//	protected 			SAFIServiciosServiceLocator 	locatorSafi;
//	protected 			SAFIServicios 					servicioSafi;
	protected 			String  						account;
	protected 			String 							investor_id;
	protected 			String							saldoActual;
	protected 			String 							safiClientId;
	static private 	Double 							montoinvertido 		= 0D;
	static private 	Double							montoNOinvertido 	= 0D;
	static private 	int 							proyectos 			= 0;
	//static private 	int 							porcentaje 			= 0;
	static private 	int 							proyectosNoFondeados = 0;
	private 			List<ItemInversion>				listToInv;
	private				int								listSize;
	private  		Double tasaPonderada = 0D;
	
	@PostConstruct
	public void init(){
		
		System.out.println("Init InvestmentAction");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		InvestmentList nav = (InvestmentList) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "investmentList");
		
		/*
		ListaCreditos lista_creditos 	= (ListaCreditos) viewMap.get("listaCreditos");
		proyectlist_to_inv 				= lista_creditos.getProyectListForInvesInd();
		account_to_inv 					= lista_creditos.getTagAccount();
		investor_id						= sesion.getProspectus_id();
		safi_client_id					= lista_creditos.getNaturalPerson().getSafi_client_id();
		*/
		setProyectList( nav.getProyectListForInvesInd() );
		
		setAccount(nav.getTagAccount());
		
		setInvestor_id(nav.getSesion().getProspectus_id().toString());
		
		setSafiClientId( nav.getNaturalPerson().getSafi_client_id() );
		
		if( getProyectList() != null ){
			listSize = getProyectList().size();
		}else{
			listSize = 1;
		}
		
		if( getProyectList() != null ){
		
			numProyectToInv = getProyectList().size();
//			try{
//				
//				locatorSafi = new SAFIServiciosServiceLocator();
//				servicioSafi = locatorSafi.getSAFIServiciosSoap11();
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
		
		}else{
			numProyectToInv = 0 ;
		}
		
	}
	
	public void procesaInversiones(){
		
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
			
			requestContext.addCallbackParam("invList", new JSONArray(tmp.toArray(),true).toString());
			requestContext.addCallbackParam("tasaPonderada", tasaPonderada );
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void procesaNoInversiones(){
		
		try{
		
			RequestContext requestContext = RequestContext.getCurrentInstance();
			List<ItemInversion> tmp = new ArrayList<ItemInversion>();
			
			for( ItemInversion item : listToInv ){
				
				if( item.getStatus().equals("0") ){
					tmp.add(item);
				}
				
			}
			
			requestContext.addCallbackParam("invList", new JSONArray(tmp.toArray(),true).toString());
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void investmentActionFnc(){
		
		Calendar cT1 = Calendar.getInstance();
		cT1.setTime( new Date() );
		
		InvestorSession investorSession = null;
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		Inversion inversion = new Inversion();
		
		try{
			
			investorSession = (InvestorSession) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(elContext, null, "investorSession");
			
			InvestmentList nav = (InvestmentList) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(elContext, null, "investmentList");
			
			nav.setHold_selected(false);
			
			setProyectList( investorSession.getListToInvestment() );
			
			lstProyectosFondeados = investorSession.getInvestmentList();
			
			inversion.setLstProyectosFondeados( lstProyectosFondeados );
			
			inversion.setAccount(getAccount());
			
			inversion.setInvestor_id(getInvestor_id());
			
			inversion.setSafiClientId( getSafiClientId() );
			
			
			if( proyectList !=null ){
				
				System.out.println("investmentActionFnc");
				inicializaVariables();
				
				Calendar cI1 = Calendar.getInstance();
				cI1.setTime( new Date() );
				
				inversion.setProyectList( proyectList );
				
				inversion.setInvestmentprogress( null );
				inversion.setInvestmentType("Masiva");
				
				inversion.ejecutaInvestment();
				
				proyectos = inversion.getProyectos();
				proyectosNoFondeados = inversion.getProyectosNoFondeados();
				
				listToInv = inversion.getListToInv();
				montoinvertido = inversion.getMontoinvertido();
				tasaPonderada = inversion.getTasaPonderada();
				
				montoNOinvertido = inversion.getMontoNOinvertido();
				
				Calendar cI2 = Calendar.getInstance();
				cI2.setTime( new Date() );
				
				Long l = cI2.getTimeInMillis() - cI1.getTimeInMillis()  ;
				
				System.out.println( "Tiempo en ejecutar las Inversiones: "+l+" milisegundos " );
				
			}else{
				System.out.println("investmentActionFnc == null");
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			investorSession.setInvestmentList( inversion.getLstProyectosFondeados() );
		  
			Calendar cF = Calendar.getInstance();
			cF.setTime( new Date() );
			
			if( proyectList != null ){
				
				inversion.ejecutaThreadDespuesDeFondeo();
				 //
				inversion.ejecutaSPRecargaListaClienteCredito(getSafiClientId());
				System.out.println("Finalizó Ejecucion de peticion!!");
				
				NavigationInvest nav = (NavigationInvest) FacesContext.getCurrentInstance()
						.getApplication().getELResolver()
						.getValue(elContext, null, "navigationInvest");
				
				nav.setProyectlist_to_inv( null );
				
				proyectList = null;
				System.out.println("safiClientId: "+safiClientId +"  account: "+account);
				
				try{
					
					Calendar cAcc = Calendar.getInstance();
					cAcc.setTime( new Date() );
					
					ConsultaInverResponse replyB = inversion.getServicioInvKuboSafi().consultaInver(new ConsultaInverRequest( safiClientId, account ));
				
					if(replyB != null){
					
						saldoActual = replyB.getSaldoTotal()==null?"0.0":replyB.getSaldoTotal();
					
					}
					
					Calendar cAcc2 = Calendar.getInstance();
					cAcc2.setTime( new Date() );
					
					Long lacc = cAcc2.getTimeInMillis() - cAcc.getTimeInMillis();
					
					System.out.println( "Tiempo en cargar la cuenta: "+lacc+" milisegundos " );
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
			Calendar cF2 = Calendar.getInstance();
			cF2.setTime( new Date() );
			
			Long lF = cF2.getTimeInMillis() - cF.getTimeInMillis();
			
			System.out.println( "Tiempo que tardo en ejecucion del Finally: " + lF + " milisegundos " );
			
		}
		
		Calendar cT2 = Calendar.getInstance();
		cT2.setTime( new Date() );
		
		Long lT = cT2.getTimeInMillis() - cT1.getTimeInMillis()  ;
		
		System.out.println( "Tiempo TOTAL en ejecutar las Inversiones: "+lT+" milisegundos " );
		
	}

	/*public String[] storeInvestmentInWS(String solicitudCreditoId,String safiCreditId,  String cuentaAhorroId,String prospectus_investor_id, Double rate, Double investmentBite,String fundingType, int company_id,int prospectus_id, ItemLoanList item )
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
			
			SolicitudInversionResponse res1 = servicioSafi.solicitudInversion(solicitudInversionRequest);
			
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
				proyectosNoFondeados++;
				listToInv.add( inv );
				montoNOinvertido += item.getInvestment_bite(); 
				
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
				
				listToInv.add( inv );
				
				ProyectFunding funding = new ProyectFunding();
				
				funding.setAmount(item.getInvestment_bite());
				funding.setKubo_score_a(item.getKubo_score_a());
				funding.setKubo_score_b(item.getKubo_score_b());
				
				ProyectFundingPK fundingPK = new ProyectFundingPK();
				
				fundingPK.setCompany_id(item.getCompany_id());
				fundingPK.setProspectus_id(item.getProspectus_id());
				fundingPK.setProspectus_investor_id( Integer.parseInt(prospectus_investor_id));
				fundingPK.setProyect_id(item.getProyect_id());
				fundingPK.setProyect_loan_id(item.getProyect_loan_id());
				
				funding.setProyectloanfundingPk(fundingPK);
				
				funding.setFunding_date(new Date());
				funding.setFunding_time(new Time( (new Date()).getTime() ));
				
				funding.setSolicitudFondeo( res1.getSolicitudFondeo() );
				
				lstProyectosFondeados.add( funding );
				
				//montoinvertido = montoinvertido + item.getInvestment_bite(); 
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
	*/
//	public void calculaPorcentaje(){
//		
//		Double d  = Double.parseDouble( ( ((proyectos + proyectosNoFondeados )*100)/listSize ) +"" ) ;
//		
//		porcentaje = d.intValue();
//		
//		proyectosNoFondeados+=11;
//		
//		proyectos += 12;
//		
//		porcentaje = porcentaje + 5;
//		
//	}
	/*
	private void ejecutaInvestment(){
		
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
		    	
		    }
			
		}
		
		procesaInversiones();
		
	}
	*/
	
	/*
	private void initServiceCalling(int company_id, int invId, String creditoID, String investment_Bite){
		
		ServiceCalling srvCall = new ServiceCalling();
	    srvCall.setAcces_datetime(new Date());
	    srvCall.setCompany_id(company_id);
	    srvCall.setStatus(1);
	    srvCall.setProspectus_id(invId);
	    srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudInversion: crd = "+creditoID+" monto: "+investment_Bite);
	    lstService.add(srvCall);
		
	}
	*/
	
	
	private void inicializaVariables(){
		lstService = null;
		storedString = null;
	}
	

	public List<ItemLoanList> getProyectList() {
		return proyectList;
	}


	public void setProyectList(List<ItemLoanList> proyectList) {
		this.proyectList = proyectList;
	}


	public int getNumProyectToInv() {
		return numProyectToInv;
	}


	public void setNumProyectToInv(int numProyectToInv) {
		this.numProyectToInv = numProyectToInv;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getInvestor_id() {
		return investor_id;
	}


	public void setInvestor_id(String investor_id) {
		this.investor_id = investor_id;
	}


	public List<String> getStoredString() {
		return storedString;
	}


	public void setStoredString(List<String> storedString) {
		this.storedString = storedString;
	}


	public String getSaldoActual() {
		return saldoActual;
	}


	public void setSaldoActual(String saldoActual) {
		this.saldoActual = saldoActual;
	}


	public String getSafiClientId() {
		return safiClientId;
	}


	public void setSafiClientId(String safiClientId) {
		this.safiClientId = safiClientId;
	}

	public Double getMontoinvertido() {
		return montoinvertido;
	}
	
	public int getProyectos() {
		return proyectos;
	}


	public void setProyectos(int proyectos) {
		this.proyectos = proyectos;
	}

	/*
	public int getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	*/

	public int getProyectosNoFondeados() {
		return proyectosNoFondeados;
	}


	public void setProyectosNoFondeados(int proyectosNoFondeados) {
		this.proyectosNoFondeados = proyectosNoFondeados;
	}


	public List<ItemInversion> getListToInv() {
		return listToInv;
	}


	public void setListToInv(List<ItemInversion> listToInv) {
		this.listToInv = listToInv;
	}


	public Double getMontoNOinvertido() {
		return montoNOinvertido;
	}

	/*
	public void setMontoNOinvertido(Double montoNOinvertido) {
		this.montoNOinvertido = montoNOinvertido;
	}


	public void setMontoinvertido(Double montoinvertido) {
		this.montoinvertido = montoinvertido;
	}*/

	public Double getTasaPonderada() {
		return tasaPonderada;
	}

	public void setTasaPonderada(Double tasaPonderada) {
		this.tasaPonderada = tasaPonderada;
	}
	
}
