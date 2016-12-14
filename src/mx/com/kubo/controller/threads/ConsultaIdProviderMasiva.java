package mx.com.kubo.controller.threads;

import java.util.Date;
import java.util.List;

import mx.com.kubo.managedbeans.registro.consulta.Preaprobacion;
import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.ProyectLoan;
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

public class ConsultaIdProviderMasiva extends Thread {
	
	
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

	public ConsultaIdProviderMasiva(){
		
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
		
		boolean isOK = true;
		
		if( lst != null && lst.size() >0  ){
		
			for( IdProviderMassive massive : lst ){
				
				TrackingCode tracking = idprovidermassiveservice.getTrackingCodeByCode(codigo_seguimiento);
				
				if( tracking != null && tracking.getEnabled() != null && tracking.getEnabled().equals( "1" ) ){
			
					if( massive.getStatus() != null && massive.getStatus().equals("CARGADO") ){
						try{
						
						RespuestaConsultaMasiva respuesta = ejecutaConsulta( massive.getProspectus_id() , massive.getCompany_id()  );
					
						if( respuesta.getScore() != null ){
							
							Scoring score = respuesta.getScore();
						
							massive.setConsulting_date( score.getResult_datetime() );
							massive.setBc_score(score.getBc_score());
							massive.setMx_solicitud_buro( score.getMx_solicitud_buro() );
							
							if( respuesta.isVigente() ){
								massive.setStatus( "VIGENTE" );
							}else{
								if( respuesta.isNo_hit() ){
									
									massive.setStatus( "NO_HIT" );
									
								}else{
									massive.setStatus( "CONSULTADO" );
								}
							}
						
							idprovidermassiveservice.updateIdProviderMassive(massive);
						
						}
						
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}
				
				}else{
					isOK = false;
					break;
					
				}
			
			}
			
			if( isOK ){
				
				try{
					
					CallSGBRisk ejecutaRiesgo = new CallSGBRisk();
					
					ejecutaRiesgo.setCodigo_seguimiento( getCodigo_seguimiento() );
					
					ejecutaRiesgo.start();
					
				}catch(Exception e){
					
					e.printStackTrace();
					
					
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
	
	private RespuestaConsultaMasiva ejecutaConsulta( int prospectus_id , int company_id ){
		
		boolean isVigente = false;
		boolean no_hit = false;
		
		RespuestaConsultaMasiva respuesta = new RespuestaConsultaMasiva();
		
		 Scoring score = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
		
		Scoring scoreRes = null;
		
		if( score != null  ){
		
			if( score.getResult_datetime() != null && !esConsultaVigente( score.getResult_datetime() ) ){
			
				scoringService.removeScoring(prospectus_id, company_id);
			
			}else{
				isVigente = true;
			}
		
		}
		
		if( !isVigente ){
		
			ProyectLoan lastProyectloan = proyectloanService.getMaxProyectLoanByProspectAndStatus(prospectus_id, company_id, 3) ;
			
			Preaprobacion preaprobacion = initPreaprobacion();
			
			preaprobacion.setProspectus_id(prospectus_id);
			preaprobacion.setCompany_id(company_id);
			
			preaprobacion.setMonto_solicitado( lastProyectloan.getAmmount() );
			
			preaprobacion.setExcecuteJSF(false);
			
			preaprobacion.init_variables();
			
			no_hit =preaprobacion.isNoHit();
			
			preaprobacion.callWSSGB();
			
			scoreRes =  preaprobacion.getScore();
			
			/****************************
			
			ProyectLoan newProyectloan = preaprobacion.getProyect_loan();
			
			ReasignadorIMP reasignador = new ReasignadorIMP();
			
			reasignador.setProyect_loan_NEW(newProyectloan);
			
			reasignador.init(lastProyectloan);
			
			reasignador.crear_lista_documentos(false);
			
			reasignador.copiar_documentos();
		
			*****************************/
			
			respuesta.setVigente(isVigente);
			respuesta.setScore(scoreRes);
			respuesta.setNo_hit(no_hit);
		
		}else{
			
			respuesta.setVigente(isVigente);
			respuesta.setScore(score);
			respuesta.setNo_hit(no_hit);
			
		}
		
		return respuesta;
		
	}
	
	private Boolean esConsultaVigente( Date dia ){
			
			final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
			
			try{
				
				Date hoy = new Date();
				
				Long dias = (hoy.getTime() - dia.getTime() ) / MILLSECS_PER_DAY;
				
				return ( 30 > dias.intValue() );
				
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
				
			}
			
		}

	public String getCodigo_seguimiento() {
		return codigo_seguimiento;
	}

	public void setCodigo_seguimiento(String codigo_seguimiento) {
		this.codigo_seguimiento = codigo_seguimiento;
	}
	
}
