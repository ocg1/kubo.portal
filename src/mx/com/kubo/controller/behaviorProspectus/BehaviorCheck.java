package mx.com.kubo.controller.behaviorProspectus;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.BehaviorBean;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BlacklistIp;
import mx.com.kubo.model.BlacklistPassword;
import mx.com.kubo.model.BlacklistPhone;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FraudeDetection;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.References;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TimeLog;
import mx.com.kubo.services.BehaviorProcessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TimeLogService;
import mx.com.kubo.tools.Utilities;

public class BehaviorCheck {
	
	private final String  EVENTO_NOTIFICACION_COMPARTAMIENTO_INUSUAL = "62"; 
	
	private List<BlacklistPassword> lstBlkPass;
	private List<BlacklistIp> lstBlkIp;
	private List<BlacklistPhone> lstBlkphone;
	
	private List<PasswordHistory> lstUserPass;
	private List<Access> lstUserAccess;
	private List<Phone> lstUserPhones;
	private List<References> lstreferences;
	
	private BehaviorProcessService behaviorprocessservice;
	
	private Change_controlService change_controlService;
	
	private MembershipService membershipservice;
	
	protected TimeLogService timelogservice;
	
	protected SystemParamService systemParamService;
	
	public BehaviorCheck(){
		behaviorprocessservice 	= Utilities.findBean("behaviorProcessServiceImp");
		change_controlService 	= Utilities.findBean("change_controlServiceImp");
		membershipservice		= Utilities.findBean("membershipServiceImp");
		timelogservice			= Utilities.findBean("timeLogServiceImp");
		systemParamService		= Utilities.findBean("systemParamServiceImp");
	}
	
	public void checkProcess( int company_id, int prospectus_id, String ipaddress ){
		
		try{
			
			SystemParamPK spkH = new SystemParamPK();
			SystemParam param = null;
			
			spkH = new SystemParamPK();
			
			spkH.setCompany_id(company_id);
			spkH.setSystem_param_id(99);
			
			param = systemParamService.loadSelectedSystemParam(spkH);
			
			if( param != null && param.getValue() != null && param.getValue().equals("S") ){
			
					Date dini = new Date();
					
					boolean is_inussual = false;
					String note_description ="";
					
					BehaviorBean bh = behaviorprocessservice.getBehaviorElements(prospectus_id);
					
					FraudeDetection fdpros =behaviorprocessservice.getFraudeDetection(  company_id,  prospectus_id );
					
					lstBlkPass = bh.getLstBlkPass() ;
					lstBlkIp = bh.getLstBlkIp();
					lstBlkphone = bh.getLstBlkphone();
					
					lstUserPass = bh.getLstUserPass();
					lstUserAccess = bh.getLstUserAccess();
					lstUserPhones = bh.getLstUserPhones();
					lstreferences = bh.getLstreferences();
					
					boolean m = false;
					
					Date dpass1= new Date();
					
					if( lstUserPass != null ){
						
						for( PasswordHistory ps : lstUserPass  ){
							
							for( BlacklistPassword blps : lstBlkPass ){
							
								if( ps.getPassword() != null && ps.getPassword().equals( blps.getPassword_str() ) && blps.getIs_enabled() != null && blps.getIs_enabled().equals("S") ){
									is_inussual = true;
									note_description += "La contraseña que proporciona es de alto riesgo ("+ blps.getPassword_str()+")  \n";
									m = true;
								}
							
							}
							
						}
					}
					
					if(!m){
						
						MembershipPK mpk = new MembershipPK();
						mpk.setCompany_id(company_id);
						mpk.setProspectus_id(prospectus_id);
						
						Membership member = membershipservice.getMembershipById(mpk);
						
						for( BlacklistPassword blps : lstBlkPass ){
							
							if( member.getPassword() != null && member.getPassword().equals( blps.getPassword_str() ) && blps.getIs_enabled() != null && blps.getIs_enabled().equals("S") ){
								is_inussual = true;
								note_description += "La contraseña que proporciona es de alto riesgo ("+ blps.getPassword_str()+")  \n";
								m = true;
							}
						
						}
						
					}
					
					Date dpass2= new Date();
					
					boolean b = false;
					
					Date dacce1= new Date();
					
					if( lstUserAccess != null ){
					
						for( Access a : lstUserAccess ){
							
							for( BlacklistIp blip : lstBlkIp ){
								
								if( a.getIpaddress() != null && a.getIpaddress().equals( blip.getIpaddress() ) && blip.getIs_enabled() != null && blip.getIs_enabled().equals("S") ){
									is_inussual = true;
									note_description += "La dirección IP de donde se conectó es de alto riesgo ("+ a.getIpaddress()+")  \n";
									b = true;
									break;
								}
							
							}
							
							if( b ){
								break;
							}
							
						}
						
					}
					Date dacce2= new Date();
					Date dphon1= new Date();
					if( lstUserPhones != null ){
					
						for( Phone p : lstUserPhones ){
							
							if(p.getPhone_number() != null && p.getPhone_number().trim().length() > 0){
							
								String str =  p.getPhone_number();
								
								if( str.startsWith("044") || str.startsWith("045") ){
									p.setPhone_number( str.substring(3) );
								}
								
								
								for( BlacklistPhone blPh : lstBlkphone ){
								
									if( p.getPhone_number().trim().equals(blPh.getPhone_number() ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
										is_inussual = true;
										note_description += "El número telefónico que proporcionó es de alto riesgo ("+ p.getPhone_number()+")  \n";
										
									}else if( p.getPhone_number() != null &&  p.getPhone_number().trim().length() > 7 && p.getPhone_number().trim().substring(0, 8).equals(blPh.getPhone_number().trim().substring(0, 8) ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
										is_inussual = true;
										note_description += "El número telefónico que proporcionó contiene 8 dígitos iguales a uno de alto riesgo, pudo comprarse en lote  ("+ p.getPhone_number()+")  \n";
										
									}/*else if( p.getPhone_number() != null && p.getPhone_number().trim().length() > 5 && p.getPhone_number().trim().substring(0, 6).equals(blPh.getPhone_number().trim().substring(0, 6) ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
			
										is_inussual = true;
										note_description += "El número telefónico que proporcionó contiene 6 dígitos iguales a uno de alto riesgo, pudo comprarse en lote  ("+ p.getPhone_number()+")  \n";
										
										
									}*/
								
								}
							
							}
							
							
						}
						
					}
					Date dphon2= new Date();
					
					Date dref1= new Date();
					
					if(lstreferences != null){
					
						for(  References r : lstreferences ){
							
							if(r.getPhone() != null && r.getPhone().trim().length() > 0){
								
								String str = r.getPhone();
								
								if( str.startsWith("044") || str.startsWith("044") ){
									r.setPhone( str.substring(3) );
								}
								
								for( BlacklistPhone blPh : lstBlkphone ){
								
									if( r.getPhone().trim().equals(blPh.getPhone_number() ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
										is_inussual = true;
										note_description += "El número telefónico que proporcionó es de alto riesgo ("+ r.getPhone()+")  \n";
										
									}else if( r.getPhone() != null &&  r.getPhone().trim().length() > 7 && r.getPhone().trim().substring(0, 8).equals(blPh.getPhone_number().trim().substring(0, 8) ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
										is_inussual = true;
										note_description += "El número telefónico que proporcionó contiene 8 dígitos iguales a uno de alto riesgo, pudo comprarse en lote  ("+ r.getPhone()+")  \n";
										
									}/*else if( r.getPhone() != null &&  r.getPhone().trim().length() > 5 && r.getPhone().trim().substring(0, 6).equals(blPh.getPhone_number().trim().substring(0, 6) ) && blPh.getIs_enabled() != null && blPh.getIs_enabled().equals("S") ){
										
										is_inussual = true;
										note_description += "El número telefónico que proporcionó contiene 6 dígitos iguales a uno de alto riesgo, pudo comprarse en lote  ("+ r.getPhone() +")  \n";
										
									}*/
								
								}
							
							}
							
						}
						
					}
					
					Date dref2= new Date();
					
					if(is_inussual){
						
						if( fdpros == null){
						
							FraudeDetection fd = new FraudeDetection();
							
							fd.setCompany_id(company_id);
							fd.setCreation_date(new Date());
							fd.setDescription_note(note_description);
							fd.setFraude_type_id(1);
							fd.setProspectus_id(prospectus_id);
							fd.setSeverity("1");
							
							behaviorprocessservice.saveFraudeDetection(fd);
							
							//TODO Envie correo
							sendNotification( company_id,  prospectus_id,true  );
						
						}else{
							
							if( fdpros.getDescription_note() != null && !fdpros.getDescription_note().equals(note_description) ){
								
								
								Change_control change =  new Change_control() ;
								
								change.setAfected_table("ln_fraude_detection");
								
								Change_controlPK change_controlPK = new Change_controlPK() ;
								
								change_controlPK.setCompany_id(company_id);
								change_controlPK.setProspectus_id(prospectus_id);
								
								change.setChange_controlPK(change_controlPK);
								change.setChange_date(new Date());
								change.setChange_prospectus_id(prospectus_id);
								change.setComments("");
								change.setField("description_note");
								change.setNew_value(note_description);
								change.setOriginal_value(fdpros.getDescription_note());
								
								change_controlService.addChangeControl(change, prospectus_id, company_id);
								
								fdpros.setCompany_id(company_id);
								fdpros.setCreation_date(new Date());
								fdpros.setDescription_note(note_description);
								fdpros.setFraude_type_id(1);
								fdpros.setProspectus_id(prospectus_id);
								fdpros.setSeverity("1");
								
								behaviorprocessservice.updateFraudeDetection(fdpros);
								
								sendNotification( company_id,  prospectus_id,false  );
								
								//Envie Correo
								
							}
							
						}
						
					}
					
					Date dfin = new Date();
					
					
					saveTimelog( company_id, prospectus_id, "behaviorcheck.valida Passs" ,dpass1 , dpass2 , ipaddress );
					
					saveTimelog( company_id, prospectus_id, "behaviorcheck.valida Access" ,dacce1 , dacce2 , ipaddress );
					
					saveTimelog( company_id, prospectus_id, "behaviorcheck.valida Phones" ,dphon1 , dphon2 , ipaddress );
					
					saveTimelog( company_id, prospectus_id, "behaviorcheck.valida References" ,dref1 , dref2 , ipaddress );
					saveTimelog( company_id, prospectus_id, "behaviorcheck.total" ,dini , dfin ,ipaddress  );
					
			}
			
			//return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			//return false;
		}
		
	}

	
	private void sendNotification( int company_id, int prospectus_id, boolean is_alta  ){
		
		try{
		
			PublicProyectServiceLocator kubolocator = new  PublicProyectServiceLocator();
			
			PublicProyect kuboservices =  kubolocator.getPublicProyect();
			
			NotificadorConfigRequest notificadorConfigRequest =  new NotificadorConfigRequest();
			
			notificadorConfigRequest.setCompany_id(company_id+"");
			
			if( is_alta ){
			
				notificadorConfigRequest.setCalled_FROM("portal.kubo ALTA_COMPORTAMIENTO");
			
			}else{
			
				notificadorConfigRequest.setCalled_FROM("portal.kubo ACTUALIZACION_COMPORTAMIENTO");
				
			}
			
			notificadorConfigRequest.setProspectus_id(prospectus_id + "");
			
			notificadorConfigRequest.setEvento_id(EVENTO_NOTIFICACION_COMPARTAMIENTO_INUSUAL);
			
			kuboservices.notificar(notificadorConfigRequest);
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}

	public BehaviorProcessService getBehaviorprocessservice() {
		return behaviorprocessservice;
	}

	public void setBehaviorprocessservice(BehaviorProcessService behaviorprocessservice) {
		this.behaviorprocessservice = behaviorprocessservice;
	}
	
public boolean saveTimelog( int company_id, int prospectus_id,  String description , Date init_time , Date final_time, String ipAddressClient  ){
		
		TimeLog timelog = new TimeLog();
		
		timelog.setDescription(description);
		
		timelog.setInit_time(init_time);
		timelog.setFinal_time(final_time);
		
		if( init_time != null && final_time != null ){
			
			long l = final_time.getTime() - init_time.getTime();
			
			Long seg = l/1000;
			
			long m = l%1000;
			
			Long min =  Long.valueOf((seg.intValue()))/60;
			
			seg = Long.valueOf((seg.intValue()))%60;
			
			String str =  min.intValue()+"m " + seg +"s " + m+"ms";
			
		
			timelog.setTotal_lapse(str);
		
		}
		
		timelog.setProspectus_id_viewed(prospectus_id);
		timelog.setProspectus_id(prospectus_id);
		
		timelog.setIp_address(ipAddressClient);
		
		return timelogservice.saveTimeLog(timelog);
		
	}
	
	
}
