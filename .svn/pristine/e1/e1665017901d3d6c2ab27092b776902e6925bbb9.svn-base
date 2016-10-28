package mx.com.kubo.managedbeans.registro.datos;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Prospector;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.model.segment.SegmentProspectusPK;

public abstract class BasicDataPMO extends BasicDataAMO
{
	
	public final void listener_edited_mx_rfc(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();			
		
		RFC = (String) input_text.getValue();
		
		comentario = "El usuario decide modificar su RFC";
		
		change_control_bean = new ChangeBean(ChangeControlEMO.REGISTRO_EDICION_RFC);	
				
		change_control_bean.setNewValue(getEdited_mx_rfc());
		change_control_bean.setOrigValue(getNaturalPerson().getMx_rfc());
		change_control_bean.setWhyChangeData(comentario);		
		
		registrarChangeControl(change_control_bean);
		
		naturalPerson.setMx_rfc(RFC);
		
		saveData();
		
		System.out.println("BasicData.listener_edited_mx_rfc(): " + RFC);
	}
	
	public final void listener_edited_mx_curp(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		CURP = (String) input_text.getValue();
		
		comentario = "El usuario decide modificar su CURP";
		
		change_control_bean = new ChangeBean(ChangeControlEMO.REGISTRO_EDICION_CURP);
				
		change_control_bean.setNewValue(getEdited_mx_curp());
		change_control_bean.setOrigValue(getNaturalPerson().getMx_curp());	
		change_control_bean.setWhyChangeData(comentario);
				
		registrarChangeControl(change_control_bean);
		
		naturalPerson.setMx_curp(CURP);
		
		saveData();
		
		System.out.println("BasicData.listener_edited_mx_curp(): " + CURP);
	}
					
	public final void registrarChangeControl(ChangeBean field) 
	{										
		if(field.isValueChanged())
		{		
			saveChangeData(field);
			
			if(change_control_OK)
			{												
				System.out.println("BasicDataPMO.registrarChangeControl(): ");	
				System.out.println("ChangeControl." + field.getChange_control().getAfected_table() + "." + field.getChange_control().getField() + "\n");		
			}
		}
	}
	
	public void saveChangeData(ChangeBean field) 
	{					
		change_control_PK = new Change_controlPK();
		change_control    = new Change_control();		
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_control_PK);		
		change_control.setChange_prospectus_id(change_prospectus_id);
		
		change_control.setAfected_table(field.getChange_control().getAfected_table());	
		change_control.setField(field.getChange_control().getField());
		
		change_control.setOriginal_value(field.getOrigValue());
		change_control.setNew_value(field.getNewValue());
		
		change_control.setComments(field.getWhyChangeData());
		change_control.setIp(field.getIp());
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
	}

	public void inciaAccess(){
		
		if( sesion.getProspectus_id() != null ){
		
			if( !sesion.getArea().toString().equals("M") ){
			
				Access access = new Access();
				
				access.setCompany_id(sesion.getCompany_id());
				access.setProspectus_id(sesion.getProspectus_id());
					
				if( sesion.getArea().toString().equals("I") ){
					access.setScreen_id(9);
				}else{
					access.setScreen_id(2);
				}
				
				Access accessTmp = accessService.getMaxAccessByScreen(
																	sesion.getProspectus_id(), 
																	sesion.getCompany_id(), 
																	access.getScreen_id() 
																	);
				
				if(accessTmp != null){
				
				access.setPercentage( accessTmp.getPercentage() );
				
				}else{
					access.setPercentage(0);
				}
				access.setWeb_browser(sesion.getNamebrawser());
				access.setWeb_browser_version(sesion.getVersionbrawser());
				access.setOp_system(sesion.getOsbrawser());
				access.setHorizontal_size(sesion.getBrowser_width());
				access.setVertical_size(sesion.getBrowser_height());
				access.setUser_agent(sesion.getUser_agent());
				access.setDevice_info(sesion.getDevice_info());
				access.setIpaddress(sesion.getIP_address_client());
				access.setUrl_access		  (sesion.getUrl_access());
				
				
				access.setProspectus_id_coach (sesion.getCoachProspectus_id());
				access.setAccess_from		  (sesion.getAccess_from());
				access.setVersion_description (sesion.getVersion_description());
				
				accessService.add(access, true);
			
			}
			
		}
		
	}
	
	protected void validaConsulta(){
		
		if( naturalPerson.getSafi_client_id() == null || naturalPerson.getSafi_client_id().trim().length() == 0 ) {
		
			SystemParamPK spk = new SystemParamPK();
			
			spk.setCompany_id(1);
			spk.setSystem_param_id(80);
			
			SystemParam sp = systemParamService.loadSelectedSystemParam(spk);
			
			if( sp != null && sp.getValue().equals("S") ){
				
			
			
				Prospector prospector = prospector_service.getMaxProspector(sesion.getProspectus_id(), sesion.getCompany_id());
				
				if( prospector != null ){
					
					if( prospector.getIs_valid() != null && prospector.getIs_valid().equals("N")  ){
					
						protectorValid	= true;
						
						
						consultValid    = true;
					
					}else if( prospector.getIs_valid() != null && prospector.getIs_valid().equals("S") && prospector.getIs_processed() != null && prospector.getIs_processed().equals("S") ){
						
						protectorValid	= true;
						
						Scoring s = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
						 
						 if (s == null){
							 protectorValid	= false;
							 consultValid    = false;
						 }else{
							 protectorValid	= true;
							 consultValid    = true;
						 }
						
						
					}else if( prospector.getIs_valid() != null && prospector.getIs_valid().equals("S") && prospector.getIs_processed() != null && prospector.getIs_processed().equals("N") ){
						
						protectorValid	= true;
						booleanListo 	= true;
						consultValid    = false;
						
					}
					
					
				}else{
					protectorValid	= false;
					consultValid    = false;
				}
			
			}else{
				
				protectorValid	= true;
				booleanListo 	=false;
				Scoring s = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
				 
				 if (s == null){
					 consultValid    = false;
				 }else{
					 consultValid    = true;
				 }
				
			}
		
		}else{
		
			protectorValid	= true;
			booleanListo 	=false;
			 Scoring s = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
			 
			 if (s == null){
				 consultValid    = false;
			 }else{
				 consultValid    = true;
			 }
			
		}
		
	}
	
	protected void insertaProspector( ResponseShortScore res ){
		
		Prospector prospector = new  Prospector();
		
		prospector.setBc_score(res.getScore());
		prospector.setCompany_id(company_id);
		prospector.setIs_active(res.getServicioActivo()?"S":"N");
		prospector.setIs_processed(res.getValido()?"N":"S");
		prospector.setIs_valid(res.getValido()?"S":"N");
		prospector.setMx_solicitud_buro( res.getBurSolNum() );
		prospector.setProspectus_id(prospectus_id);
		prospector.setConsulting_date(new Date());
		prospector_service.saveProspector(prospector);
		
	}
	
	protected void insertaScoring( ResponseShortScore res ){
		
		Scoring s =  new Scoring();
		
		s.setBc_score(res.getScore());
		s.setBc_score_date(new Date());
		s.setCci_score("000");
		s.setCompany_id(company_id);
		s.setKubo_rate("21.52");
		s.setKubo_score_a("G");
		s.setKubo_score_b("1");
		s.setLiquidity(2.0);
		s.setMx_folio("00000000");
		s.setMx_solicitud_buro(res.getBurSolNum());
		s.setOpening_commission(5.0);
		s.setProspectus_id(prospectus_id);
		s.setRate(61.50);
		s.setRate_investor(39.98);
		s.setResult_datetime(new Date());
		s.setIs_prospector("S");
		s.setRisk_processed(1);
		
		scoringService.saveScoring(s);
		
		insertaProyectLoan( s );
		
		insertaSegmento();
		
	}
	
	protected void insertaProyectLoan( Scoring score ){
		
		SimulatorBean simulacion_ACTUAL = simulatorService.getMaxSimulationProspect(score.getProspectus_id(), score.getCompany_id());
		
		ProyectLoan proyect_loan = new ProyectLoan();
		proyect_loan.setAmmount(simulacion_ACTUAL.getAmmount());
		proyect_loan.setDays_online(15);
		proyect_loan.setFrequency_id(simulacion_ACTUAL.getFrequency_id());
		proyect_loan.setFunding_type('T');
		proyect_loan.setKubo_score_a(score.getKubo_score_a()==null?"":score.getKubo_score_a());
		proyect_loan.setKubo_score_b(score.getKubo_score_b()==null?"":score.getKubo_score_b());
		proyect_loan.setBc_score(Integer.parseInt(score.getBc_score()));
		proyect_loan.setMx_cat(simulacion_ACTUAL.getMx_cat());
		proyect_loan.setRate_with_opening(score.getRate());
		proyect_loan.setOpening_commission_amount((score.getOpening_commission()*simulacion_ACTUAL.getAmmount())/100);
		proyect_loan.setOpening_payment("D");
		proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
		proyect_loan.setMin_ammount(simulacion_ACTUAL.getAmmount());								
		proyect_loan.setPayment(simulacion_ACTUAL.getPayment());
		proyect_loan.setVerification_score(1);
		proyect_loan.setOpening_commission(score.getOpening_commission());
		proyect_loan.setLiquidity(score.getLiquidity());
		proyect_loan.setLoan_type("NVO");
		proyect_loan.setCci_score(score.getCci_score());
		proyect_loan.setConsulting_date(new Date());
	
		Integer proyect_id    = proyectService.getMaxProyectID();
		prospectus_id = score.getProspectus_id();
		company_id    = score.getCompany_id();
						
		ProyectPK proyect_PK  = new ProyectPK(proyect_id, prospectus_id, company_id);
		Proyect proyect     = new Proyect();
		proyect.setProyectoPk(proyect_PK);
		
		if( simulacion_ACTUAL.getPurpose_id() != null && simulacion_ACTUAL.getPurpose_id().intValue() != 0 ){
		
			proyect.setPurpose_id( simulacion_ACTUAL.getPurpose_id());
		
		}else{
			
			proyect.setPurpose_id( 1);
			
		}
		
		MembershipPK membership_PK = new MembershipPK(prospectus_id, company_id);
		
		membership = membershipService.getMembershipById(membership_PK);
	
		if(membership.getRegistration_reason() != null && membership.getRegistration_reason().getPartner_id() != null)
		{
			proyect.setPartner_id(membership.getRegistration_reason().getPartner_id());
		} else {
			proyect.setPartner_id(null);
		}												
	
		proyect_loan.setRate_investor(score.getRate_investor());
		
	
		boolean is_proyect_OK = proyectService.add(proyect);
		
		if(is_proyect_OK)
		{							
			prospectus_id = score.getProspectus_id();
			company_id    = score.getCompany_id();
											
			proyect = proyectService.getMaxProyect(prospectus_id, company_id);
			
			company_id    = proyect.getProyectoPk().getCompany_id();
			prospectus_id = proyect.getProyectoPk().getProspectus_id();
			proyect_id    = proyect.getProyectoPk().getProyect_id();
			
			ProyectLoanPK proyect_loan_PK = new  ProyectLoanPK();
			
			proyect_loan_PK.setCompany_id(company_id);
			proyect_loan_PK.setProspectus_id(prospectus_id);
			proyect_loan_PK.setProyect_id(proyect_id);
			proyect_loan_PK.setProyect_loan_id(0);		
			
			proyect_loan.setProyectloanPk(proyect_loan_PK);
			
			proyect_loan.setRate(score.getRate());
			proyect_loan.setStatus_id(11);									
			proyect_loan.setTerm_id(simulacion_ACTUAL.getTerm_id());
			proyect_loan.setDeposit_method_id(1);									
			proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
			proyect_loan.setIs_prospector_score("S");
			
			proyectloanService.add(proyect_loan);
		
	}
		
	}
	
	private void insertaSegmento(){
		
		SegmentProspectus segment = new SegmentProspectus();
		SegmentProspectusPK spk = new SegmentProspectusPK();
		
		spk.setCompany_id(company_id);
		spk.setProspectus_id(prospectus_id);
		spk.setSegment_id(RECHAZADO_AUTOMATICAMENTE);
		spk.setSegment_type_id(RIESGO_BURO);
		
		segment.setAssign_date(new Date());
		segment.setPk(spk);
		segmentprospectusservice.saveSegmentProspectus(segment);
		
	}
	
	protected void enviaNotificacion(){
		
		try{
		
			PublicProyectServiceLocator pp_loacator = new PublicProyectServiceLocator();
			PublicProyect public_proyect = pp_loacator.getPublicProyect();
			
			NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();		
			request_notificar_config.setCompany_id(sesion.getCompany_id()+"" );
			request_notificar_config.setProspectus_id(sesion.getProspectus_id()+"");
			request_notificar_config.setCalled_FROM("BasicData.ConsultaProspector.enviaNotificacion.PublicProyect");
			request_notificar_config.setEvento_id("41");
	
			public_proyect.notificar(request_notificar_config);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
