package mx.com.kubo.controller;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.controller.inversion.Inversion;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.AutomaticInvestmentService;
import mx.com.kubo.services.InvestmentProgressService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.tools.Utilities;

public class InversionAutomatica {

	private  List<AutomaticInvestment> listaInversionistas;
	private  List<InvestmentProgress> progressLst;
	
	private   	AutomaticInvestmentService service;
	protected 	ProyectFundingService proyectFundingService;
	protected 	InvestmentProgressService investmentprogressservice;
	
	public InversionAutomatica(){
		
		service = Utilities.findBean("automaticInvestmentServiceImp");
		proyectFundingService = Utilities.findBean("proyectFundingServiceImp");
		investmentprogressservice = Utilities.findBean("investmentProgressServiceImp");
		
		
	}
	
	public void ejecutaInversionAutomatica( Date fechaInversion  ){
		cargaListaInversionistas( fechaInversion );
		realizaInversiones();
	}

	public void cargaListaInversionistas( Date fechaInversion ){
		
		Calendar c = Calendar.getInstance();
		c.setTime( fechaInversion );
		c.set( c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		//c.set( 2015,11, 31, 0, 0, 0);
		
		System.out.println( c.getTime() );
		
		listaInversionistas = service.getAutomaticInvestmentList(c.getTime());
		
	}
	
	private  void realizaInversiones(){
		
		System.out.println( "realizaInversiones " +(listaInversionistas == null ? "Lista Vacia " : listaInversionistas.size()+ " elementos ") );
		
		List<InvestmentProgressDet>  progressdetlst			= new ArrayList<InvestmentProgressDet>();
		List<ServiceCalling>		 lstService 				= new ArrayList<ServiceCalling>();
		List<String> 				 storedString 			= new ArrayList<String>();
		
		progressLst = new ArrayList<InvestmentProgress>();
		
		try{
			
		
			for ( AutomaticInvestment i : listaInversionistas ){
				
				
				
				int progress_flag  = 0;
				
				InvestmentProgress ip = new InvestmentProgress();
				
				Double montoFondeado = 0D;
				
				try{
					
					ip.setAutomatic_investment_id(i.getAutomatic_investment_id());
					ip.setInvestment_date(i.getNext_investment());
					
					ip.setExecute_date(new Date());
					ip.setStatus_progress(0);
					ip.setFilter(i.getFilter());
					ip.setMessage("Iniciando proceso de inversion automática");
					
					progress_flag = 1 ; // Inserta Progress
					investmentprogressservice.saveInvestmentProgress(ip);
					
					progress_flag = 2 ; // Inicializa Servicios Inversion
					Inversion inversion = new Inversion();
					
					List<String> safiCuentas = inversion.getProyectLoanService() .getOnlySAFIAccount( i.getProspectus_id() +"");
					
					progress_flag = 3 ; // Inicializa Saldos
					inversion.inicializaSaldos(safiCuentas);
					
					List<InvestorsAccounts> listInvAccounts = inversion.getListInvAccounts();
					Double ammoutToInv = listInvAccounts.get(0).getSaldo();
					
					String flagRisk ="";
					
					if( i.getFilter().indexOf("'A'") != (-1) || i.getFilter().indexOf("'B'") != (-1) || i.getFilter().indexOf("'C'") != (-1) ||
							i.getFilter().indexOf("'D'") != (-1) || i.getFilter().indexOf("'E'") != (-1)|| i.getFilter().indexOf("'F'") != (-1) ){
						flagRisk = "0";
					}else{
						flagRisk = "1";
					}
					
					inversion.sethTFunding(generaHashFunding( i.getProspectus_id(), i.getCompany_id()));
					
					progress_flag = 4 ; // carga Lista Tienda
					inversion.cargaListaTienda(i.getFilter(), i.getProspectus_id(), i.getCompany_id(), flagRisk,i.getSafi_client_id(),i.getSafi_account_id());
					
					inversion.setInvestmentType("Automática");
					
					progress_flag = 5 ; // calcula Investment Bite
					
					
					
					List<ItemLoanList>  proyectList = inversion.calculaInvestmentBite(i.getProspectus_id(), i.getCompany_id(), ammoutToInv, inversion.getProyectList());
					
					inversion.setProyectList(proyectList);
					
					//proyectList: Lista de proyectos a invertir
					
					inversion.setInvestor_id( i.getProspectus_id()+"" ); //: prospectus_id del inversionista
					inversion.setAccount(i.getSafi_account_id()); //: safi_account_id del inversionista
					inversion.setSafiClientId( i.getSafi_client_id() );//: safiClientId del inversionista 
					
					ip.setNum_projects(inversion.getProyectList().size());
					
					progress_flag = 6 ; // Actualiza Registro Progress 1 
					investmentprogressservice.updateInvestmentProgress(ip);
					
					inversion.setInvestmentprogress( ip );
					inversion.setInvestor_id( i.getProspectus_id()+"" ); //: prospectus_id del inversionista
					
					progress_flag = 7 ; // ejecuta Investment 
					inversion.ejecutaInvestment();
					
					
					montoFondeado = inversion.getMontoinvertido();
					
						progressdetlst.addAll	( inversion.getProgressdetlst() ); //
						lstService.addAll		( inversion.getLstService()		); //
						storedString.addAll		( inversion.getStoredString()	); //
					
					progress_flag = 8 ; // ejecuta SPRecargaListaClienteCredito
					//inversion.ejecutaSPRecargaListaClienteCredito(i.getSafi_client_id());
					
					
					i.setLast_investment(new Date());
					
					progress_flag = 9 ; // actualiza Fecha Siguiente Inversión
					updateNextInvestment( i );
					
					ip.setStatus_progress(1);
					
					String message = "Inversión finalizada: inversiones realizadas - "+inversion.getProyectos() + " - monto invertido:  "+inversion.getMontoinvertido()+" - inversiones NO realizadas: "+inversion.getProyectosNoFondeados()+" - monto NO invertido: "+ inversion.getMontoNOinvertido() ;
					
					ip.setMessage(message);
					ip.setTotal_amount_invested(montoFondeado);
					ip.setEnd_execute_date(new Date());
					
					getProgressLst().add(ip);
					
					progress_flag = 10 ; // Actualizando último progress
					investmentprogressservice.updateInvestmentProgress(ip);
					
					
				
					
					
				}catch( Exception e ){
				
					switch( progress_flag ){
					
						case 1:
							// Inserta Progress
							break;
						case 2:
							// Inicializa Servicios Inversion
							ip.setStatus_progress(97);
							ip.setMessage( "ERROR Inicializa Servicios Inversion: "+ e.getMessage() );
							ip.setTotal_amount_invested(0D);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 3:
							// Inicializa Saldos
							ip.setStatus_progress(97);
							ip.setMessage( "ERROR Inicializa Saldos: "+ e.getMessage() );
							ip.setTotal_amount_invested(0D);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 4:
							// carga Lista Tienda
							ip.setStatus_progress(97);
							ip.setMessage( "ERROR carga Lista Tienda: "+ e.getMessage() );
							ip.setTotal_amount_invested(0D);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 5:
							// calcula Investment Bite
							ip.setStatus_progress(97);
							ip.setMessage( "ERROR calcula Investment Bite: "+ e.getMessage() );
							ip.setTotal_amount_invested(0D);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 6:
							// Actualiza Registro Progress 1 
							ip.setStatus_progress(97);
							ip.setMessage( "ERROR Actualiza Registro Progress 1: "+ e.getMessage() );
							ip.setTotal_amount_invested(0D);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 7:
							// ejecuta Investment
							ip.setStatus_progress(99);
							ip.setMessage( "ERROR Al EJECUTAR LAS INVERSIONES CHECAR QUE INVERSIONES NO SE EJECUTARON: "+ e.getMessage() );
							ip.setTotal_amount_invested(montoFondeado);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 8:
							// ejecuta SPRecargaListaClienteCredito
							ip.setStatus_progress(98);
							ip.setMessage( "Las Inversiones si se realizaron Falló el SPRecargaListaClienteCredito(borra y actualiza creditos fondeados por el cliente): "+ e.getMessage() );
							ip.setTotal_amount_invested(montoFondeado);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 9:
							// actualiza Fecha Siguiente Inversión
							ip.setStatus_progress(98);
							ip.setMessage( "Las Inversiones si se realizaron Falló al actualizar Fecha Siguiente Inversión: "+ e.getMessage() );
							ip.setTotal_amount_invested(montoFondeado);
							ip.setEnd_execute_date(new Date());
							
							break;
						case 10:
							// Actualizando último progress
							ip.setStatus_progress(98);
							ip.setMessage( "Las Inversiones si se realizaron Falló al actualizar último progress: "+ e.getMessage() );
							ip.setTotal_amount_invested(montoFondeado);
							ip.setEnd_execute_date(new Date());
							
							break;
					
					}
					
					//ip.setStatus_progress(99);
					getProgressLst().add(ip);
					investmentprogressservice.updateInvestmentProgress(ip);
					
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Inversion inversionTmp =  new Inversion();
		
		inversionTmp.setProgressdetlst	( progressdetlst );
		inversionTmp.setLstService		( lstService		);
		inversionTmp.setStoredString	( storedString	);
		
		inversionTmp.ejecutaThreadDespuesDeFondeo();
		
	}
	
	private void updateNextInvestment( AutomaticInvestment i ){
		
		Date actual = i.getNext_investment();
		
		Calendar c_next_Investment = Calendar.getInstance();
		c_next_Investment.setTime(actual);
		
		Calendar c_next_Investment_ejecuta = Calendar.getInstance();
		
		Date actual_aply = i.getNext_investment_apply();
		
		c_next_Investment_ejecuta.setTime(actual_aply);
		
		if( i.getFrequency() != null && i.getFrequency().equals("D") ){ //Diario
			
			c_next_Investment.add(Calendar.DATE, 1);
			c_next_Investment_ejecuta = validaDia(c_next_Investment);
			
		}else if( i.getFrequency() != null && i.getFrequency().equals("S")  ){ // Semanal
			
			c_next_Investment.add(Calendar.DATE, 7);
			Calendar cTemp = Calendar.getInstance();
			cTemp.setTime(c_next_Investment.getTime());
			c_next_Investment_ejecuta = validaDia(cTemp);
			
		}else if( i.getFrequency() != null && i.getFrequency().equals("C")  ){ // Catorcenal
			
			c_next_Investment.add(Calendar.DATE, 14);
			Calendar cTemp = Calendar.getInstance();
			cTemp.setTime(c_next_Investment.getTime());
			c_next_Investment_ejecuta = validaDia(cTemp);
			
		}else if( i.getFrequency() != null && i.getFrequency().equals("Q")  ){ // Quincenal
			
			int day = c_next_Investment.get(Calendar.DATE);
			
			if( day == 15 ){
				c_next_Investment.set(Calendar.DATE, c_next_Investment.getActualMaximum(Calendar.DATE));
			}else{
				c_next_Investment.set(Calendar.DATE, 15);
				c_next_Investment.set(Calendar.MONTH, c_next_Investment.get(Calendar.MONTH)+1);
			}
			Calendar cTemp = Calendar.getInstance();
			cTemp.setTime(c_next_Investment.getTime());
			c_next_Investment_ejecuta = validaDia(cTemp);
			
		}else if( i.getFrequency() != null && i.getFrequency().equals("M")  ){ // Mensual
			
			c_next_Investment.add(Calendar.MONTH, 1);
			Calendar cTemp = Calendar.getInstance();
			cTemp.setTime(c_next_Investment.getTime());
			c_next_Investment_ejecuta = validaDia(cTemp);
			
		}else if( i.getFrequency() != null && i.getFrequency().equals("P")  ){ // Personalizada
			//Pendiente
		}
		
		int flagDate = 0;
		
		if( i.getFinished_date() != null ){
		
			if( i.getFinished_date().after( c_next_Investment.getTime()) ||  i.getFinished_date().compareTo( c_next_Investment.getTime() ) == 0 ){
				 flagDate = 1;
			}else{
				flagDate = 2;
			}
		
		}
		
		if( flagDate == 2 ){
		
			i.setIs_active("2");  // Ha llegado la fecha límite para las reinversiones  (finalización de inversiones)
			
		}else{
			
			i.setNext_investment(c_next_Investment.getTime());
			i.setNext_investment_apply(c_next_Investment_ejecuta.getTime());
			
		}
		
		service.updateAutomaticInvestment(i);
	}
	
	private int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);		
	}
	
	private Hashtable<String,List<ProyectFunding> > generaHashFunding( int prospectus_investor_id, int company_investor_id ){
		
		
		List<ProyectFunding> lstFunding =  proyectFundingService.getListProyectFunByInvestor( prospectus_investor_id , company_investor_id );
		
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
	
	private Calendar validaDia( Calendar c ){
		
		boolean flag = false;
		
		while( !flag ){
		
			int dayOfWeek = getDayOfTheWeek( c.getTime() );
			
			if( dayOfWeek == Calendar.SATURDAY ){
				
				c.add(Calendar.DATE, 2);
				flag = false;
				
			}else if( dayOfWeek == Calendar.SUNDAY ){
			
				c.add(Calendar.DATE, 1);
				flag = false;
				
			}else if( service.esDiaFeriado( c.getTime() ) ){
				
				c.add(Calendar.DATE, 1);
				flag = false;
				
			}else{
				flag = true;
			}
			
		}
		
		return c;
		
	}
	
	public List<InvestmentProgress> cargaProgressLst( Date fecha ){
		
		return investmentprogressservice.getInvestmentProgressByDate(fecha);
		
	}

	public List<AutomaticInvestment> getListaInversionistas() {
		return listaInversionistas;
	}

	public void setListaInversionistas(List<AutomaticInvestment> listaInversionistas) {
		this.listaInversionistas = listaInversionistas;
	}

	public List<InvestmentProgress> getProgressLst() {
		return progressLst;
	}

	public void setProgressLst(List<InvestmentProgress> progressLst) {
		this.progressLst = progressLst;
	}

	
}
