package mx.com.kubo.controller.threads;

import java.util.Date;
import java.util.List;

import mx.com.kubo.managedbeans.registro.consulta.Preaprobacion;
import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.TrackingCode;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.CreditHistoryAttemptService;
import mx.com.kubo.services.CreditHistoryService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.IdProviderMassiveService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectorService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.tools.Utilities;

public class CallSGBRisk extends Thread {
	
	
	private  NotificadorIMP notificador;
	
	private  ProspectusService prospectusService;
	
	private  ScoringService scoringService;
	
	private  NaturalPersonService naturalPersonService;
	
	private  CreditHistoryService credithistoryService;
	
	private  AddressService addressService;
	
	private  PhoneService phoneService;
	
	private  TownService townService;
	
	private  NeighborhoodService neighborhoodService;
	
	private  ServiceCallingService servicecallingService;
	
	private  StateService stateService;
	
	private  EventNotificationService eventnotificationservice; 
	
	private  ProyectLoanService proyectloanService;
	
	private  ProyectService proyectService;
	
	private  SimulatorService simulatorService;
	
	private  MembershipService membershipservice;
	
	private  CreditHistoryAttemptService creditAttemptService;
	
	private  SystemParamService systemParamService;
	
	private  NotesService service_notas;
	
	private  AccessService accessService;	
	
	private  ProspectorService prospector_service; 
	
	private String codigo_seguimiento;
	
	private IdProviderMassiveService  idprovidermassiveservice;

	public CallSGBRisk(){
		
		inicializaciones();
		
	}
	
	private  void  inicializaciones(){
		
		notificador = Utilities.findBean("notificadorImp");
		
		prospectusService =Utilities.findBean("prospectusServiceImp");
		
		scoringService = Utilities.findBean("scoringServiceImp");
		
		naturalPersonService = Utilities.findBean("naturalPersonServiceImp");
		
		credithistoryService = Utilities.findBean("creditHistoryServiceImp");
		
		addressService = Utilities.findBean("addressServiceImp");
		
		phoneService = Utilities.findBean("phoneServiceImp");
		
		townService = Utilities.findBean("townServiceImp");
		
		neighborhoodService = Utilities.findBean("neighborhoodServiceImp");
		
		servicecallingService = Utilities.findBean("serviceCallingServiceImp");
		
		stateService = Utilities.findBean("stateServiceImp");
		
		eventnotificationservice = Utilities.findBean("eventNotificationServiceImp");
		
		proyectloanService = Utilities.findBean("proyectLoanServiceImp");
		
		proyectService = Utilities.findBean("proyectServiceImp");
		
		simulatorService = Utilities.findBean("simulatorServiceImp");
		
		membershipservice = Utilities.findBean("membershipServiceImp");
		
		creditAttemptService = Utilities.findBean("creditHistoryAttemptServiceImp");
		
		systemParamService = Utilities.findBean("systemParamServiceImp");;
		
		service_notas = Utilities.findBean("notesServiceImp") ;
		
		accessService = Utilities.findBean("accessServiceImp");
			
		prospector_service = Utilities.findBean("prospectorServiceImp");
		
		idprovidermassiveservice = Utilities.findBean("idProviderMassiveServiceImp");
		
	}
	
	@Override
	public void run() {
		
		
		List<IdProviderMassive> lst = idprovidermassiveservice.getIdProviderMassiveByTrackingCode(codigo_seguimiento);
		
		//boolean isOK = true;
		
		if( lst != null && lst.size() >0  ){
		
			for( IdProviderMassive massive : lst ){
				
				TrackingCode tracking = idprovidermassiveservice.getTrackingCodeByCode(codigo_seguimiento);
				
				if( tracking != null && tracking.getEnabled() != null && tracking.getEnabled().equals( "1" ) ){
			
					if( massive.getStatus() != null && massive.getStatus().equals("CONSULTADO") ){
					
						try{
						
						ejecutaConsulta( massive.getProspectus_id() , massive.getCompany_id()  );
					
						massive.setSend_risk_date(new Date());
						
						boolean flag = false;
						
						int i = 0;
						
						while( !flag ){
						
							
							Scoring score = scoringService.loadMaxScoringByProspectus(massive.getProspectus_id(),  massive.getCompany_id());
							if( score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == 1 ){
								flag = true;
								System.out.println( "Riesgo ok: " + massive.getMx_solicitud_buro() );
								break;
								
							}else{
								
								i++;
								
								if( i > 9 ){
									System.out.println( "3min: " + massive.getMx_solicitud_buro() );
									break;
								}
								
								try{
									System.out.println( "Esperando Riesgo: " + massive.getMx_solicitud_buro() );
									Thread.sleep(20000);
									
								}catch(Exception e){
									
									e.printStackTrace();
									
								}
								
							}
							
							
						}
						
						if( flag ){
							
							massive.setStatus( "RIESGO EJECUTADO " );
							
						}else{
						
							massive.setStatus( "RIESGO EJECUTADO SIN REGRESO" );
						
						}
					
						idprovidermassiveservice.updateIdProviderMassive(massive);
						
						
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}
				
				}else{
					//isOK = false;
					break;
					
				}
			
			}
			
			
		
		}
		
		
	}
	
	public Preaprobacion initPreaprobacion(){
		
		Preaprobacion preaprobacion = new Preaprobacion();
		
		preaprobacion.setNotificador(notificador);
		
		preaprobacion.setProspectusService(prospectusService );
		
		preaprobacion.setScoringService(scoringService);
		
		preaprobacion.setNaturalPersonService(naturalPersonService);
		
		preaprobacion.setCredithistoryService(credithistoryService);
		
		preaprobacion.setAddressService(addressService);
		
		preaprobacion.setPhoneService(phoneService);
		
		preaprobacion.setTownService(townService);
		
		preaprobacion.setNeighborhoodService(neighborhoodService);
		
		preaprobacion.setServicecallingService(servicecallingService );
		
		preaprobacion.setStateService(stateService);
		
		preaprobacion.setEventnotificationservice(eventnotificationservice);
		
		preaprobacion.setProyectloanService(proyectloanService);
		
		preaprobacion.setProyectService(proyectService);
		
		preaprobacion.setSimulatorService(simulatorService);
		
		preaprobacion.setMembershipservice(membershipservice);
		
		preaprobacion.setCreditAttemptService(creditAttemptService );
		
		preaprobacion.setSystemParamService(systemParamService);
		
		preaprobacion.setService_notas(service_notas) ;
		
		preaprobacion.setAccessService(accessService);
			
		preaprobacion.setProspector_service(prospector_service);
		
		return preaprobacion;
	}
	
	private void ejecutaConsulta( int prospectus_id , int company_id ){
		
			//ProyectLoan lastProyectloan = proyectloanService.getMaxProyectLoanByProspectAndStatus(prospectus_id, company_id, 3) ;
			
			Scoring score = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
			
			Preaprobacion preaprobacion = initPreaprobacion();
			
			preaprobacion.setProspectus_id(prospectus_id);
			preaprobacion.setCompany_id(company_id);
			
			preaprobacion.init_variables();
			
			preaprobacion.setScore(score);
			
			preaprobacion.setExcecuteJSF(false);
			
			preaprobacion.setConsultaBuro(true);
			
			preaprobacion.asignar_score();
			
			/****************************/
			
			/*
			
			ProyectLoan newProyectloan = preaprobacion.getProyect_loan();
			
			ReasignadorIMP reasignador = new ReasignadorIMP();
			
			reasignador.setMembershipservice(membershipservice);
			
			reasignador.setProyect_loan_NEW(newProyectloan);
			
			reasignador.init(lastProyectloan);
			
			reasignador.crear_lista_documentos(false);
			
			reasignador.copiar_documentos();
			*/
		
			/*****************************/
			
		
	}
	
	

	public String getCodigo_seguimiento() {
		return codigo_seguimiento;
	}

	public void setCodigo_seguimiento(String codigo_seguimiento) {
		this.codigo_seguimiento = codigo_seguimiento;
	}
	
}

