package mx.com.kubo.controller.threads;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mx.com.kubo.controller.InversionAutomatica;
import mx.com.kubo.controller.PendingNotificationController;
import mx.com.kubo.controller.shortURL.GeneraURLCorta;
import mx.com.kubo.controller.shortURL.RequestShortURL;
import mx.com.kubo.controller.shortURL.ResponseShortURL;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.SMSRequestService;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.AyudaDocumentos;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.ClientesEnMora;
import mx.com.kubo.model.CobranzaPreventiva;
import mx.com.kubo.model.InactiveAccount;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.MovementNotification;
import mx.com.kubo.model.MovementNotificationPK;
import mx.com.kubo.model.MovementToVerify;
import mx.com.kubo.model.PendingNotification;
import mx.com.kubo.model.SafiDepositoRefere;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AutomaticInvestmentService;
import mx.com.kubo.services.AyudaDocumentosService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ClientesEnMoraService;
import mx.com.kubo.services.CobranzaPreventivaService;
import mx.com.kubo.services.InactiveAccountService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MovementNotificationService;
import mx.com.kubo.services.MovementToVerifyService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public class VerificadorInicio extends Thread  {
		
	
	//  MOVEMENT_ID 
		private final int VERIFICADEPOSITOS = 1;
		private final int VERIFICARETIROS = 2;
		private final int VERIFICAPRIORITARIOSSINPUBLICAR = 3;
		private final int BLOQUEO_DE_CUENTAS_INCACTIVAS = 4;
		private final int SMS_COBRANZA_PREVENTIVA = 5;
		private final int SMS_COBRANZA_CLIENTES_MORA_1_30 = 6;
		private final int SMS_COBRANZA_CLIENTES_MORA_31_60 = 7;
		private final int SMS_COBRANZA_CLIENTES_MORA_61_90 = 8;
		private final int SMS_COBRANZA_CLIENTES_MORA_75_80 = 9;
		private final int SMS_COBRANZA_CLIENTES_MORA_81_90 = 10;
		private final int SMS_AYUDA_DOCUMENTACION_DIA_1 = 11;
		private final int SMS_AYUDA_DOCUMENTACION_DIA_2 = 12;
		private final int SMS_AYUDA_DOCUMENTACION_DIA_3 = 13;
		private final int SMS_AYUDA_DOCUMENTACION_DIA_4 = 14;
		private final int SMS_AYUDA_DOCUMENTACION_DIA_5 = 15;
		private final int AVISO_TIENDA_DISPONIBLE = 16;
		private final int NOTIFICA_TABLERO_NORMATIVO = 17;
		private final int INVERSIONES_AUTOMATICAS = 18;

	
	//  FIN MOVEMENT_ID 
		
		
		private final int EVENT_COBRANZA_CLIENTES_MORA_1_30 = 48;
		private final int EVENT_COBRANZA_CLIENTES_MORA_31_60 = 51;
		private final int EVENT_COBRANZA_CLIENTES_MORA_61_90 = 52;
		private final int EVENT_COBRANZA_CLIENTES_MORA_75_80 = 53;
		private final int EVENT_COBRANZA_CLIENTES_MORA_81_90 = 54;
		
		private final int EVENT_AYUDA_DOCUMENTACION_DIA_1 = 55;
		private final int EVENT_AYUDA_DOCUMENTACION_DIA_2 = 56;
		private final int EVENT_AYUDA_DOCUMENTACION_DIA_3 = 57;
		private final int EVENT_AYUDA_DOCUMENTACION_DIA_4 = 58;
		private final int EVENT_AYUDA_DOCUMENTACION_DIA_5 = 59;
		
		private final int EVENT_RESUMEN_TABLERO_NORMATIVO = 61;
		
		
		private final int KUBO_USER=0;
		
		// VARIABLES DE MINUTOS EN QUE SE REPETIRÁ 
		private int int_VERIFICAPRIORITARIOSSINPUBLICAR = 1;
		private int int_VERIFICADEPOSITOS = 1;
		private int int_VERIFICARETIROS = 1;
		private int int_BLOQUEO_DE_CUENTAS_INCACTIVAS = 1;
		private int int_SMS_COBRANZA_PREVENTIVA = 1;
		private int int_SMS_COBRANZA_CLIENTES_MORA_1_30 = 1;
		private int int_SMS_COBRANZA_CLIENTES_MORA_31_60 = 1;
		private int int_SMS_COBRANZA_CLIENTES_MORA_61_90 = 1;
		private int int_SMS_COBRANZA_CLIENTES_MORA_75_80 = 1;
		private int int_SMS_COBRANZA_CLIENTES_MORA_81_90 = 1;
		private int int_TIENDA_DISPONIBLE = 1;
		private int int_SMS_AYUDA_DOCUMENTACION_DIA_1 = 1;
		private int int_SMS_AYUDA_DOCUMENTACION_DIA_2 = 1;
		private int int_SMS_AYUDA_DOCUMENTACION_DIA_3 = 1;
		private int int_SMS_AYUDA_DOCUMENTACION_DIA_4 = 1;
		private int int_SMS_AYUDA_DOCUMENTACION_DIA_5 = 1;
		private int int_TABLERO_NORMATIVO = 1;
		private int int_INVERSIONES_AUTOMATICAS = 1;
		// FIN VARIABLES DE MINUTOS EN QUE SE REPETIRÁ 
		
		
		private 	SystemParamService 	systemparamservice;
		
		private 	Integer 			MINUTOS_EN_ESPERA = 1;
		
		protected 	SimpleDateFormat 	formatter;
		
		protected 	SimpleDateFormat 	formatter_2;
		
		private   	MovementNotificationService movement_notification_service;
		
		private 	CobranzaPreventivaService cobranzaPreventivaService;
		
		private 	ClientesEnMoraService clientesenmoraservice;
		
		private   	MovementToVerifyService movements_to_verify_service;
		
		private 	InactiveAccountService inactiveAccountService;
		
		protected Change_controlService service_change_control;
		
		protected MembershipService service_membership;
		
		protected AyudaDocumentosService ayudadocumentosservice;
		
		private   	AutomaticInvestmentService service;
		
		private 	List<MovementToVerify>	movements;
		
		public VerificadorInicio(){
			
			System.out.println(" Inicializando VerificadorInicio");
			
			inicializaciones();
		}
		
		private  void  inicializaciones(){
			
			formatter 						=  new SimpleDateFormat("dd 'de' MMMMM 'del' yyyy hh:mm:ss aaa");	
			
			formatter_2						=  new SimpleDateFormat("yyyy-MM-dd");
			
			systemparamservice 				=  Utilities.findBean("systemParamServiceImp");
			
			movements_to_verify_service		=  Utilities.findBean("movementToVerifyServiceImp");
			
			movement_notification_service	=  Utilities.findBean("movementNotificationServiceImp");
			
			inactiveAccountService			=  Utilities.findBean("inactiveAccountServiceImp");
			
			service_change_control			=  Utilities.findBean("change_controlServiceImp");
			
			service_membership				=  Utilities.findBean("membershipServiceImp");
			
			cobranzaPreventivaService		=  Utilities.findBean("cobranzaPreventivaServiceImp");
			
			clientesenmoraservice			=  Utilities.findBean("clientesEnMoraServiceImp");
			
			ayudadocumentosservice			=  Utilities.findBean("ayudaDocumentosServiceImp"); 
			
			service = Utilities.findBean("automaticInvestmentServiceImp");
			
		}
		
		@Override
		public void run() {
			
			Calendar c2 = Calendar.getInstance();
			
			Calendar c1 = Calendar.getInstance();
			
			c1.setTime(new Date());
			
			System.out.println( "Ejecutando Run VerificadorInicio " );
			
			int i = 0;
			
			int_VERIFICAPRIORITARIOSSINPUBLICAR = 1;
			int_VERIFICADEPOSITOS = 1;
			int_VERIFICARETIROS = 1;
			
			try{
			
				Thread.sleep( 30000 );

				inicializaciones();

				while ( true ){
				
					if( systemparamservice != null && movements_to_verify_service != null && movement_notification_service != null ){
			
						while ( true ){
						
							try{
								
								c2 = Calendar.getInstance();
								c2.setTime(new Date());
								
								// long l1 = c2.getTimeInMillis() - c1.getTimeInMillis();
								
								System.out.println( "Asignando orden: " );
								getMovementsToVerify();
								
								System.out.println( "ejecutando verificaciones: " );
								
								verificaciones();
								
								asigna_Tiempo_De_Espera();
								
								System.out.println( "Minutos que tardará en ejecutar la verificación: " + MINUTOS_EN_ESPERA +"  Hora Actual: "+ formatter.format(new Date() ) );
								
								Thread.sleep( MINUTOS_EN_ESPERA * 60000 );
								
							}catch(Exception e){
								
								Thread.sleep( 1000 );
								
								System.out.println( "CATCH - 1 EN WHILE .... VerificadorInicio " );
								e.printStackTrace();
								i++;
								if(i > 20){
									break;
								}
								
							}
						
						}
						
					}else{
						
						Thread.sleep( 10000 );

						inicializaciones();
						
						i++;
						
						if(i > 20){
							break;
						}
						
					}
				
				
				
					if(i > 20){
						break;
					}
				
				}
				
				}catch( Exception e ){
					
					System.out.println( "CATCH - 2 EN WHILE .... VerificadorInicio " );
					e.printStackTrace();
					
				}finally{
					
					System.out.println( "FINALLY - 2 EN WHILE .... VerificadorInicio " );
					
				}
			
			Calendar c4 = Calendar.getInstance();
			c4.setTime(new Date());
			
			
			long l4 = c4.getTimeInMillis() - c1.getTimeInMillis();
			System.out.println( "Tiempo TOTAL en ejecutar EL THREAD: "+l4+" milisegundos" );
			
			System.out.println("Finaliza Run");
			
		}
		
		private void verificaciones(){
			
			System.out.println( "Ejecuta Verificaciones .. " );
			
			if( movements != null ){
				
				for( MovementToVerify move : movements ){
					
					switch ( move.getPk().getMovement_id() ){
					
						case VERIFICADEPOSITOS:
							
							try{
								if ( move.getMinutes_to_reply_event() == null || move.getMinutes_to_reply_event() <= int_VERIFICADEPOSITOS ){
									System.out.println( " 1 - Ejecutando Depósito a cuenta ( Inversionista )" );
									verificaDepositos();
									
									int_VERIFICADEPOSITOS = 1;
									
								}else{
									int_VERIFICADEPOSITOS ++;
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
								
							break;
						
						case VERIFICARETIROS:
							
							try{
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_VERIFICARETIROS ){

									System.out.println( " 2 - Ejecutando Retiro de Efectivo ( Inversionista )" );
									
									int_VERIFICARETIROS = 1;
								
								}else{
									int_VERIFICARETIROS ++;
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
						
							break;
						
						case VERIFICAPRIORITARIOSSINPUBLICAR:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_VERIFICAPRIORITARIOSSINPUBLICAR ){
								
									System.out.println( " 3 - Ejecutando Envio de SMS para Prospectos Prioritarios sin publicar" );
									llamadaServicioPrioritariosNoPublicados();
									
									int_VERIFICAPRIORITARIOSSINPUBLICAR = 1;
									
								}else{
									int_VERIFICAPRIORITARIOSSINPUBLICAR ++;
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case BLOQUEO_DE_CUENTAS_INCACTIVAS:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_BLOQUEO_DE_CUENTAS_INCACTIVAS ){
								
									System.out.println( " 3 - Bloqueo de cuentas inactivas" );
								
									bloqueoCuentasInactivas();
									
									int_BLOQUEO_DE_CUENTAS_INCACTIVAS = 1;
									
								}else{
									
									int_BLOQUEO_DE_CUENTAS_INCACTIVAS ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_COBRANZA_PREVENTIVA:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_PREVENTIVA ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDiaAntes(c_next_apply);
											
											System.out.println( " 5 - SMS_COBRANZA_PREVENTIVA" );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											llamadaServicioCobranzaPreventiva( f.format(c_next_apply_temp.getTime()) );
											
											 //llamadaServicioCobranzaPreventiva( "2016-04-25" );
											
											int_SMS_COBRANZA_PREVENTIVA = 1;
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
									
										}else{
											
											int_SMS_COBRANZA_PREVENTIVA = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_PREVENTIVA ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
							
						case SMS_COBRANZA_CLIENTES_MORA_1_30:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_CLIENTES_MORA_1_30 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 6 - SMS_COBRANZA_CLIENTES_MORA(1-30) " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioCobranzaClientesMora( f.format(c_next_apply_temp.getTime()) , EVENT_COBRANZA_CLIENTES_MORA_1_30 );
											
											//llamadaServicioCobranzaClientesMora( "2016-04-26" );
											
											int_SMS_COBRANZA_CLIENTES_MORA_1_30 = 1;
											
										}else{
											
											int_SMS_COBRANZA_CLIENTES_MORA_1_30 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_CLIENTES_MORA_1_30 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_COBRANZA_CLIENTES_MORA_31_60:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_CLIENTES_MORA_31_60 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 6 - SMS_COBRANZA_CLIENTES_MORA(31-60) " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioCobranzaClientesMora( f.format(c_next_apply_temp.getTime()) , EVENT_COBRANZA_CLIENTES_MORA_31_60 );
											
											//llamadaServicioCobranzaClientesMora( "2016-04-26" );
											
											int_SMS_COBRANZA_CLIENTES_MORA_31_60 = 1;
											
										}else{
											
											int_SMS_COBRANZA_CLIENTES_MORA_31_60 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_CLIENTES_MORA_31_60 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_COBRANZA_CLIENTES_MORA_61_90:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_CLIENTES_MORA_61_90 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 6 - SMS_COBRANZA_CLIENTES_MORA(61-90) " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioCobranzaClientesMora( f.format(c_next_apply_temp.getTime()) , EVENT_COBRANZA_CLIENTES_MORA_61_90 );
											
											//llamadaServicioCobranzaClientesMora( "2016-04-26" );
											
											int_SMS_COBRANZA_CLIENTES_MORA_61_90 = 1;
											
										}else{
											
											int_SMS_COBRANZA_CLIENTES_MORA_61_90 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_CLIENTES_MORA_61_90 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_COBRANZA_CLIENTES_MORA_75_80:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_CLIENTES_MORA_75_80 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 6 - SMS_COBRANZA_CLIENTES_MORA(75-80) " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioCobranzaClientesMora( f.format(c_next_apply_temp.getTime()) , EVENT_COBRANZA_CLIENTES_MORA_75_80 );
											
											//llamadaServicioCobranzaClientesMora( "2016-04-26" );
											
											int_SMS_COBRANZA_CLIENTES_MORA_75_80 = 1;
											
										}else{
											
											int_SMS_COBRANZA_CLIENTES_MORA_75_80 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_CLIENTES_MORA_75_80 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_COBRANZA_CLIENTES_MORA_81_90:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_COBRANZA_CLIENTES_MORA_81_90 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
										
//										SimpleDateFormat f1 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
//										
//										Calendar TODAY = Calendar.getInstance();
//										
//										TODAY.setTime( f1.parse("22/04/2016 14:00:00") );
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 6 - SMS_COBRANZA_CLIENTES_MORA(81-90) " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd" );
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioCobranzaClientesMora( f.format(c_next_apply_temp.getTime()) , EVENT_COBRANZA_CLIENTES_MORA_81_90 );
											
											//llamadaServicioCobranzaClientesMora( "2016-04-26" );
											
											int_SMS_COBRANZA_CLIENTES_MORA_81_90 = 1;
											
										}else{
											
											int_SMS_COBRANZA_CLIENTES_MORA_81_90 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_COBRANZA_CLIENTES_MORA_81_90 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_AYUDA_DOCUMENTACION_DIA_1:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_AYUDA_DOCUMENTACION_DIA_1 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 11 - SMS_AYUDA_DOCUMENTACION_DIA_1 " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioAyudaDocumentos( EVENT_AYUDA_DOCUMENTACION_DIA_1 );
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_1 = 1;
											
										}else{
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_1 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_AYUDA_DOCUMENTACION_DIA_1 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_AYUDA_DOCUMENTACION_DIA_2:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_AYUDA_DOCUMENTACION_DIA_2 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 11 - SMS_AYUDA_DOCUMENTACION_DIA_2 " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioAyudaDocumentos( EVENT_AYUDA_DOCUMENTACION_DIA_2 );
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_2 = 1;
											
										}else{
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_2 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_AYUDA_DOCUMENTACION_DIA_2 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
							
						case SMS_AYUDA_DOCUMENTACION_DIA_3:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_AYUDA_DOCUMENTACION_DIA_3 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 11 - SMS_AYUDA_DOCUMENTACION_DIA_3 " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioAyudaDocumentos( EVENT_AYUDA_DOCUMENTACION_DIA_3 );
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_3 = 1;
											
										}else{
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_3 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_AYUDA_DOCUMENTACION_DIA_3 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
							
						case SMS_AYUDA_DOCUMENTACION_DIA_4:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_AYUDA_DOCUMENTACION_DIA_4 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 11 - SMS_AYUDA_DOCUMENTACION_DIA_4 " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioAyudaDocumentos( EVENT_AYUDA_DOCUMENTACION_DIA_4 );
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_4 = 1;
											
										}else{
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_4 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_AYUDA_DOCUMENTACION_DIA_4 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
						case SMS_AYUDA_DOCUMENTACION_DIA_5:
							
							try{
								
								if ( move.getMinutes_to_reply_event() == null ||  move.getMinutes_to_reply_event() <= int_SMS_AYUDA_DOCUMENTACION_DIA_5 ){
								
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 11 - SMS_AYUDA_DOCUMENTACION_DIA_5 " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
											
											llamadaServicioAyudaDocumentos( EVENT_AYUDA_DOCUMENTACION_DIA_5 );
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_5 = 1;
											
										}else{
											
											int_SMS_AYUDA_DOCUMENTACION_DIA_5 = 1;
											
										}
									
									}
									
								}else{
									
									int_SMS_AYUDA_DOCUMENTACION_DIA_5 ++;
									
								}	
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
							
							break;
							
							
						case AVISO_TIENDA_DISPONIBLE:
							
							try{
								if ( move.getMinutes_to_reply_event() == null || move.getMinutes_to_reply_event() <= int_TIENDA_DISPONIBLE ){
									System.out.println( " 16 - Aviso a inversionistas que la tienda ya está disponible despues de que habia sido deshabilitada" );
									
									verificaTiendaDisponible();
									
									int_TIENDA_DISPONIBLE = 1;
									
								}else{
									int_TIENDA_DISPONIBLE ++;
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
								
							break;
							
						case NOTIFICA_TABLERO_NORMATIVO:
							
							try{
								if ( move.getMinutes_to_reply_event() == null || move.getMinutes_to_reply_event() <= int_TABLERO_NORMATIVO ){
									System.out.println( " 17 - Aviso a inversionistas que la tienda ya está disponible despues de que habia sido deshabilitada" );
									
									
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 17 - TABLERO NORMATIVO " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
									
											notificaTableroNormativo();
											
											int_TABLERO_NORMATIVO = 1;
											
										}else{
											int_TABLERO_NORMATIVO = 1;
										}
										
									}
									
									
									
								}else{
									int_TABLERO_NORMATIVO ++;
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
								
							break;
							
							
						case INVERSIONES_AUTOMATICAS:
							
							try{
								if ( move.getMinutes_to_reply_event() == null || move.getMinutes_to_reply_event() <= int_INVERSIONES_AUTOMATICAS ){
									System.out.println( " 16 - Aviso a inversionistas que la tienda ya está disponible despues de que habia sido deshabilitada" );
									
									if( move.getNext_day_to_apply() != null ){
										
										Calendar c_next_apply = Calendar.getInstance();
										
										c_next_apply.setTime(move.getNext_day_to_apply());
										
										Calendar TODAY = Calendar.getInstance();
									
										if( c_next_apply.before( TODAY ) ){
										
											Calendar c_next_apply_temp = Calendar.getInstance();
											
											c_next_apply_temp.setTime( c_next_apply.getTime() );
											
											c_next_apply.add(Calendar.DATE, 1);
											c_next_apply = validaDia(c_next_apply);
											
											System.out.println( " 18 - INVERSIONES AUTOMÁTICAS " );
										
											c_next_apply_temp.add(Calendar.DATE, 1);
											
											c_next_apply_temp = validaDia(c_next_apply_temp);
											
											move.setNext_day_to_apply(c_next_apply.getTime());
											
											movements_to_verify_service.updateMovementToVerify(move);
									
											verificaInversionesAutomaticas();
										
											int_INVERSIONES_AUTOMATICAS = 1;
											
										}else{
											int_INVERSIONES_AUTOMATICAS = 1;
										}
										
									}
									
									
									
								}else{
									int_INVERSIONES_AUTOMATICAS ++;
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
								
							}
								
							break;
							
						default:
							
							System.out.println( " Default" );
							
							break;
					
					}
					
				}
				
			}
			
			
		}
		
		private void getMovementsToVerify(){
			
			if( movements_to_verify_service != null ){
				movements = movements_to_verify_service.getMovementToVerifyActiveList();
			}
		}
		
		private void asigna_Tiempo_De_Espera(){
			
			System.out.println( "asigna_Tiempo_De_Espera .. " );
			
			SystemParamPK spk = new SystemParamPK() ;
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(69); // Lapso de tiempo para realizar verficar acciones de aplicacion (  en Minutos )
			
			SystemParam param = systemparamservice.loadSelectedSystemParam(spk);
			
			if( param != null && param.getValue() != null ){
			
				if( Integer.parseInt( param.getValue() ) > 0 ){
				
					MINUTOS_EN_ESPERA = Integer.parseInt( param.getValue() );
				
				}else{
					
					MINUTOS_EN_ESPERA = 1;
					
				}
			
			}else{
				
				MINUTOS_EN_ESPERA = 1;
				
			}
			
		}
		
		
		
		private void verificaDepositos(){
		
			Date fecha_inicio_de_verificaciones_sys 		= new Date();
			String fecha_de_origen_STR						= "";
			List< SafiDepositoRefere > lstSafiDeposito 		= null;
			
			SystemParamPK spk = new SystemParamPK();
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(72); // Fecha a partir de la cual se comienzan a validar los movimientos ( Depositos, Retiros )
			
			if( systemparamservice != null ){
			
				SystemParam param = systemparamservice.loadSelectedSystemParam(spk);
				
				if( param != null && param.getValue() != null ){
					
					fecha_de_origen_STR =  param.getValue();
					
					try{
					
						fecha_inicio_de_verificaciones_sys = formatter_2.parse( fecha_de_origen_STR );
					
					}catch( Exception e ){
					
						e.printStackTrace();
						
					}
					
				}
				
			}
			
			if( movement_notification_service != null ){
				
			MovementNotification movement = movement_notification_service.getMaxMovementNotification();
			
			if ( movement != null ) {
				
				System.out.println( "ultimo movimiento: " + movement.getPk().getFolioCargaID() + " fecha: "+movement.getFecha_carga() );
				 
				if( fecha_inicio_de_verificaciones_sys.before(  movement.getFecha_carga() ) ){
				
					System.out.println( " Fecha  " + fecha_inicio_de_verificaciones_sys + " before " + movement.getFecha_carga() );
					
					if( movement.getPk().getFolioCargaID() != null ){
						
						System.out.println( "Buscando depues del Folio:  "+ movement.getPk().getFolioCargaID() );
						
						lstSafiDeposito = movement_notification_service.getMovementToSafiByFolioCarga( movement.getPk().getFolioCargaID() );
						
					}else{
						
						System.out.println( "ELSE  FolioCargaID == NULL " );
						
						lstSafiDeposito = movement_notification_service.getMovementToSafiByFecha( fecha_inicio_de_verificaciones_sys );
						
					}
				
				}else{
					
					System.out.println( "ELSE  Fecha  " + fecha_inicio_de_verificaciones_sys + " after " + movement.getFecha_carga() );
					
					lstSafiDeposito = movement_notification_service.getMovementToSafiByFecha( fecha_inicio_de_verificaciones_sys );
					
				}
				
			}else{
				
				System.out.println( "Por fecha: " + fecha_de_origen_STR );
				
				lstSafiDeposito = movement_notification_service.getMovementToSafiByFecha( fecha_inicio_de_verificaciones_sys );
				
			}
			
			
		
			if( lstSafiDeposito != null && lstSafiDeposito.size() > 0 ){
				
				System.out.println( "SafiDepositoRefere: " + lstSafiDeposito.size() );
				
				for( SafiDepositoRefere deposito : lstSafiDeposito ){
					
					System.out.println( "deposito: " + deposito.getFolioCargaID() + " fecha: " + deposito.getFechaAplica()  );
					
					MovementNotification newMovement = new MovementNotification ();
					
					newMovement.setFecha_carga( deposito.getFechaAplica() );
					newMovement.setMontoMov( deposito.getMontoMov() );
					newMovement.setStatus_notification( 0 );
					
					MovementNotificationPK mpk = new MovementNotificationPK();
					
					mpk.setCompany_id( 1 );
					mpk.setFolioCargaID( deposito.getFolioCargaID() );
					mpk.setMovement_id( 1 );
					
					newMovement.setPk( mpk );
					
					movement_notification_service.saveMovementNotification( newMovement );
					
				}
				
				//TODO llamada al Servicio
				
				llamdaServicio();
				
			}else{
				
					List< MovementNotification > lst =  movement_notification_service.getMovementNotificationInStatusCeroList();
					
					if ( lst != null && lst.size() > 0  ) {
						
						//TODO llamada al Servicio
						llamdaServicio();
						
					}
				
				}
				
			}else{
				
				inicializaciones();
				
			}
			
		}

		
		

		private void llamdaServicio(){
			
			try{
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				WsResponse res = py.notificaDepositos();
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
				
			}catch( Exception e ){
				
			}
			
		}
		
		private void bloqueoCuentasInactivas(){
			try{
				
				List<InactiveAccount> lst =  inactiveAccountService.getInactiveAccountList();
				
				if(lst != null){
					
					String prospectos = "";
					
					int i = 0;
					
					for( InactiveAccount in : lst ){
						
						if(i != 0 ){
							prospectos += ",";
						}
						prospectos += in.getProspectus_id();
						i++;
						
						insertaChangeControl( in.getProspectus_id(), 1 );
						
					}
					
					actualizaMembership( prospectos );
					
					System.out.println( i + " usarios Bloqueados <--- " );
					
				}
				
			}catch( Exception e ){
				e.printStackTrace();
			}
		}
		
		private void verificaTiendaDisponible(){
			
			SystemParamPK spk = new SystemParamPK() ;
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(89); // Ejecución de cierre del día
			
			SystemParam param = systemparamservice.loadSelectedSystemParam(spk);
			
			String valCierreDia = param.getValue();
			
			spk = new SystemParamPK() ;
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(71); // Bandera que indica si se encuentra deshabilitada la tienda
			
			param = systemparamservice.loadSelectedSystemParam(spk);
			
			String valNoTienda  = param.getValue();
			
			spk = new SystemParamPK() ;
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(70); // Bandera que indica si existe un error al cargar la tienda
			
			param = systemparamservice.loadSelectedSystemParam(spk);
			
			String valNoTiendaError  = param.getValue();
			
			if ( valCierreDia != null && valNoTienda != null && valNoTiendaError != null && valCierreDia.equals("N") && valNoTienda.equals("N") && valNoTiendaError.equals("N") ){
				
				verificaPendientesPorNotificar();
				
			}
		}

		private void notificaTableroNormativo(){
			
			try{
			
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();										
				request_notificar_config.setCompany_id("1");
				request_notificar_config.setProspectus_id("0");	
				request_notificar_config.setEvento_id(EVENT_RESUMEN_TABLERO_NORMATIVO+"");
				request_notificar_config.setCalled_FROM("AdministrationProfileAMO.init_notificar_evento()");						
			
				WsResponse res  = py.notificar(request_notificar_config);
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
			
			}catch(Exception e){
				
			}
			
		}
											
		private void verificaInversionesAutomaticas(){
			
			try{
				
				InversionAutomatica inversionAutomatica = new InversionAutomatica();
				
				Date fechaInversion = new Date();
				
				inversionAutomatica.cargaListaInversionistas( fechaInversion );
				
				inversionAutomatica.ejecutaInversionAutomatica( fechaInversion );
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
				
		}

		
		private void llamadaServicioAyudaDocumentos( int event_id ){
			try{
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				SMSRequestService request = new SMSRequestService() ;
				
				List<AyudaDocumentos> clientes = null;
				
				switch( event_id ){
				
					case EVENT_AYUDA_DOCUMENTACION_DIA_1 :
						
						clientes = ayudadocumentosservice.getAyudaDocumentosList(1);
						
						break;
						
					case EVENT_AYUDA_DOCUMENTACION_DIA_2 :
						
						clientes = ayudadocumentosservice.getAyudaDocumentosList(2);
						
						break;
						
					case EVENT_AYUDA_DOCUMENTACION_DIA_3 :
						
						clientes = ayudadocumentosservice.getAyudaDocumentosList(3);
						
						break;
						
					case EVENT_AYUDA_DOCUMENTACION_DIA_4 :
						
						clientes = ayudadocumentosservice.getAyudaDocumentosList(4);
						
						break;
						
					case EVENT_AYUDA_DOCUMENTACION_DIA_5 :
						
						clientes = ayudadocumentosservice.getAyudaDocumentosList(5);
						
						break;
				
				
				}
				
				
				
				if( clientes != null && clientes.size() > 0 ){
					
					String[] prospectuslst = new String[ clientes.size() ];
				
					int i = 0;
					
					for( AyudaDocumentos c : clientes ){
						
						prospectuslst[i] = c.getProspectus_id()+"";
						i++;
						
					}
				
				
				request.setEmisor_id(KUBO_USER+"");
				request.setProspectus_id(prospectuslst);
				request.setEvent_id(""+event_id);
				
				request.setMessage( "AYUDA DOCUMENTOS" );
				
				
				WsResponse res = py.enviaSMS(request);
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
				
				}else{
					
					System.out.println( "Sin prospectus que enviar" );
					
				}
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
		}
		
		private void llamadaServicioCobranzaClientesMora( String fecha, Integer event_id ){
			
			try{
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				SMSRequestService request = new SMSRequestService() ;
				
				List<ClientesEnMora> clientes =  clientesenmoraservice.getClientesEnMora( event_id );
				
				if( clientes != null && clientes.size() > 0 ){
					
					String[] prospectuslst = new String[ clientes.size() ];
				
					int i = 0;
					
					for( ClientesEnMora c : clientes ){
						
						prospectuslst[i] = c.getProspectus_id()+"";
						i++;
						
					}
				
				
				request.setEmisor_id(KUBO_USER+"");
				request.setProspectus_id(prospectuslst);
				request.setEvent_id(""+event_id);
				
				request.setMessage( fecha );
				
				
				WsResponse res = py.enviaSMS(request);
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
				
				}else{
					
					System.out.println( "Sin prospectus que enviar" );
					
				}
				
			}catch( Exception e ){
				
				e.printStackTrace();
				
			}
			
		}
		
		private void llamadaServicioCobranzaPreventiva( String fecha ){
			
			try{
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				SMSRequestService request = new SMSRequestService() ;
				
				List<CobranzaPreventiva> cobranza = cobranzaPreventivaService.getCobranzaPreventivaLst(fecha);
				
				if( cobranza != null && cobranza.size() > 0 ){
					
					String[] prospectuslst = new String[ cobranza.size() ];
				
					int i = 0;
					
					for( CobranzaPreventiva c : cobranza ){
						
						prospectuslst[i] = c.getProspectus_id()+"";
						i++;
						
					}
				
				
				request.setEmisor_id(KUBO_USER+"");
				request.setProspectus_id(prospectuslst);
				request.setEvent_id("47");
				
				request.setMessage( fecha );
				
				
				WsResponse res = py.enviaSMS(request);
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
				
				}else{
					
					System.out.println( "Sin prospectus que enviar" );
					
				}
				
			}catch( Exception e ){
				
			}
			
		}
		
		private void llamadaServicioPrioritariosNoPublicados(){
			
			try{
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect py = locator.getPublicProyect();
				
				WsResponse res = py.notificaSMSSinPublicar();
				
				System.out.println( "res: " +res.getFolio() );
				
				System.out.println( "res: " +res.getMessage() );
				
			}catch( Exception e ){
				
			}
			
		}

		private void actualizaMembership( String prospectos ){
			
			service_membership.bloqueaProspectos(prospectos);
			
		}
		
		private void insertaChangeControl( int prospectus_id, int  company_id  ){
			
			saveChangeData("ln_membership", "is_blocked", "N", "S", "BLOQUEADO POR INACTIVIDAD", prospectus_id,  company_id);
			
		}
		
		private boolean saveChangeData(String table, String field, String origValue, String newValue, String comment, int prospectus_id, int company_id)
		{
			Change_controlPK changeCtrlPK = new Change_controlPK();
			
			changeCtrlPK.setProspectus_id(prospectus_id);
			changeCtrlPK.setCompany_id(company_id);
			
			Change_control changeCtrl = new Change_control();
			
			changeCtrl.setChange_controlPK(changeCtrlPK);
			changeCtrl.setChange_prospectus_id(KUBO_USER);
			changeCtrl.setAfected_table(table);
			changeCtrl.setIp("LOCAL");			
			changeCtrl.setOriginal_value(origValue);
			changeCtrl.setNew_value(newValue);
			changeCtrl.setField(field);
			changeCtrl.setComments(comment);
			
			if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
			{
				return true;	
			} else {
				return false;
			}
		}
		
		
		private Calendar validaDiaAntes( Calendar c ){
			
			boolean flag = false;
			
			boolean flagFeriado = false;
			
			while( !flag ){
			
				int dayOfWeek = getDayOfTheWeek( c.getTime() );
				
				if( dayOfWeek == Calendar.SATURDAY ){
					
					c.add(Calendar.DATE, 2);
					flag = false;
					
					flagFeriado = true;
					
				}else if( dayOfWeek == Calendar.SUNDAY ){
				
					c.add(Calendar.DATE, 1);
					flag = false;
					
					flagFeriado = true;
					
				}else if( service.esDiaFeriado( c.getTime() ) ){
					
					c.add(Calendar.DATE, 1);
					flag = false;
					flagFeriado = true;
					
				}else{
					flag = true;
				}
				
			}
			
			
			if( flagFeriado ){
				c.add(Calendar.DATE, -1);
			}
			
			return c;
			
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
		
		private void verificaPendientesPorNotificar(){
			
			
			PendingNotificationController pnc = new PendingNotificationController();
			
			List<PendingNotification> lstPN = pnc.getPendingNotificationStatusCero();
			
			if( lstPN != null && lstPN.size() > 0 ){
				
				for( PendingNotification pn : lstPN ){
				
					if( preparaSMS( pn.getCompany_id() , pn.getProspectus_id() ) ){
						
						pn.setNotification_date(new Date());
						pn.setStatus_id(1);
						
						pnc.updatePendingNotification(pn);
						
					}
				
				}
				
			}
			
			
		}
		
		private boolean preparaSMS( Integer company_id, Integer prospectus_id ){
			
			MembershipPK mpk = new MembershipPK(prospectus_id, company_id);
			
			Membership mem = service_membership.getMembershipById(mpk);
			
			GeneraURLCorta shortURl = new GeneraURLCorta();
			
			RequestShortURL request = new RequestShortURL();
			
			request.setCompany_id(mem.getPerson().getNatPerPK().getCompany_id()+"");
			request.setProspectus_id(mem.getPerson().getNatPerPK().getProspectus_id()+"");
			//request.setLongURL("http://www.kubofinanciero.com/Kubo/Portal/acreditado/preregistro/comenzar-registro.xhtml?selectedReg=s");
			
			request.setLongURL("http://www.kubofinanciero.com/Kubo/Portal/index.xhtml?iniciarSesion=true&email_access="+mem.getEmail());
			
			ResponseShortURL response =   shortURl.generaConsultaCorta( request );
		
			if( response != null && response.getStatus().equals("0") ){
				
				return enviaSMS(response.getShortURL(), mem.getPerson().getNatPerPK().getProspectus_id(), mem.getPerson().getFirst_name());
				 
			}else{
				return false;
			}
			
		}
		
		private boolean enviaSMS(String url, Integer prospectus_id,String nombre){
			
			//System.out.println( tokenGen );
			boolean flag = false; 
			
			try{
				
				PublicProyectServiceLocator kubolocator = new  PublicProyectServiceLocator();
				
				PublicProyect kuboservices =  kubolocator.getPublicProyect();
				
				SMSRequestService request =  new SMSRequestService() ;
				
				request.setEmisor_id( "0" );
				
				request.setBursolnum(null);
				request.setCampaign(null);
				
				request.setEvent_id("65");
				
				String msg = Utilities.capilizeString( nombre ) + ", todo en orden, ya puedes regresar a invertir en Kubo " + url;
				
				request.setMessage(msg);
				
				
				String[] str = {""+prospectus_id+""};
				
				request.setProspectus_id(str);
				
				kuboservices.enviaSMS(request);
				
				flag = true;
			
			}catch(Exception e){
				
				e.printStackTrace();
				flag = false;
			}

			return flag ;
			
		}
		
		private int getDayOfTheWeek(Date d){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(d);
			return cal.get(Calendar.DAY_OF_WEEK);		
		}
	
}
