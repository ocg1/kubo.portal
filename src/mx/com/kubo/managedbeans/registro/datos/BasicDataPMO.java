package mx.com.kubo.managedbeans.registro.datos;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.registro.consulta.Preaprobacion;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospector;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.model.segment.SegmentProspectusPK;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.tools.Utilities;

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
		
		insertaProyectLoan( s , 11, "S" );
		
		insertaSegmento();
		
	}
	
	protected void insertaProyectLoan( Scoring score, int loan_status_id, String is_prospector ){
		
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
			proyect_loan.setStatus_id(loan_status_id);									
			proyect_loan.setTerm_id(simulacion_ACTUAL.getTerm_id());
			proyect_loan.setDeposit_method_id(1);									
			proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
			proyect_loan.setIs_prospector_score(is_prospector);
			
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
	
	protected boolean creaProspectoSGB( Prospectus prospectus_in, NaturalPerson naturalPerson_in ){
		
		try{
		
		Preaprobacion preaprobacion =  (Preaprobacion)       resolver.getValue(context, null, "preaprobacion");
		
		Address domicilio_tmp  = addressService.getAddressById(domicilio.getAddress().getAddressPK());
		
		preaprobacion.setNaturalPerson(naturalPerson_in);
		preaprobacion.setProspectus(prospectus_in);
		preaprobacion.setThisAddress( domicilio_tmp );
		
		Phone thisPhoneFixed = phoneService.getPhoneByTypeByArea(naturalPerson_in.getNatPerPK().getProspectus_id(), naturalPerson_in.getNatPerPK().getCompany_id(), 6, 'L');
		
		if( thisPhoneFixed == null ){
		
			thisPhoneFixed = phoneService.getPhoneByTypeByArea(naturalPerson_in.getNatPerPK().getProspectus_id(), naturalPerson_in.getNatPerPK().getCompany_id(), 5, 'L');
		
			if( thisPhoneFixed != null  ){
				
				System.out.println("phone: "+thisPhoneFixed.getPhone_number());
				preaprobacion.setThisPhoneFixed(thisPhoneFixed);
			
			}
		
		}else{
			
			thisPhoneFixed = new Phone();
			thisPhoneFixed.setPhone_number("");
			preaprobacion.setThisPhoneFixed(thisPhoneFixed);
			
		}
		
		Simulator simulator =  (Simulator) resolver.getValue(context, null, "simulator");;
		
		preaprobacion.setSimulator(simulator);
		
		NotesService notesservice = Utilities.findBean("notesServiceImp");
		
		preaprobacion.setService_notas(notesservice);
		
		System.out.println("preaprobacion msg: "+ preaprobacion.getMsg());
		
		return preaprobacion.creaProspectSGB();
		
		}catch(Exception e){
			return false;
		}
		
		
	}
	
	protected void asignar_credit_history_attempt( NaturalPerson naturalPerson, Address thisAddress, Phone phone_obj ) throws ParseException, RemoteException 
	{
		intento   = new CreditHistoryAttempt();
	
		intento.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		intento.setUser("");
		intento.setPassword("");
		intento.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		intento.setFirst_name(naturalPerson.getFirst_name());
		intento.setMiddle_name(naturalPerson.getMiddle_name());
		intento.setFather_last_name(naturalPerson.getFather_last_name());
		intento.setMother_last_name(naturalPerson.getMother_last_name());
	
		if(naturalPerson.getDate_of_birth() != null)
		{							
			SimpleDateFormat format   = new SimpleDateFormat("yyyy-MM-dd");
			String birthStr = format.format(naturalPerson.getDate_of_birth());
			Date birth    = format.parse(birthStr);
			
			intento.setDate_of_birth(new java.sql.Date(birth.getTime()));
		}
		
		Integer colonia_id = thisAddress.getNeighborhood_id();
		Integer ciudad_id  = thisAddress.getTown_id();
		Integer estado_id  = thisAddress.getState_id();
		
		String colonia = "";
		String municipio = "";
		String thisEstado = "";
		
		if(colonia_id != null)
		{
			NeighborhoodCatPK nPK = new NeighborhoodCatPK();
			
			nPK.setCompany_id(company_id);
			nPK.setNeighborhood_id(colonia_id);
			
			NeighborhoodCat neig = neighborhoodService.getNeighborhoodById(nPK);
			colonia = Utilities.quitaAcentos( neig.getName() );
			
		} else if(thisAddress.getNeighborhood_text() != null){
			
			colonia = Utilities.quitaAcentos( thisAddress.getNeighborhood_text() );
			
		} 

		if(ciudad_id != null)
		{
			TownCatPK tPK = new TownCatPK();
			tPK.setCompany_id(company_id);
			tPK.setTown_id(ciudad_id);
			TownCat town = townService.getTownById(tPK);
			municipio = Utilities.quitaAcentos( town.getName() );
		} 
									
		if(estado_id != null)
		{
			StateCatPK sPK = new StateCatPK();
			sPK.setCompany_id(company_id);
			sPK.setState_id(estado_id);
			StateCat state = service_estado.getStateById(sPK);
			thisEstado = state.getBc_key();
		} 
	
		intento.setMx_rfc(naturalPerson.getMx_rfc());
		intento.setStreet(thisAddress.getStreet());
		intento.setMx_manzana(thisAddress.getMx_manzana());
		intento.setMx_lote(thisAddress.getMx_lote());
		intento.setAddress_number(thisAddress.getAddress_number());
		intento.setApt_number(thisAddress.getApt_number());
		intento.setNeighborhood_name(colonia);
		intento.setTown_name(municipio);
		intento.setState_name(thisEstado);
		intento.setZip_code(thisAddress.getZip_code());
		intento.setCreditcard_is_principal(null);
		intento.setCreditcard_four_digits(null);
		intento.setMortgage_is_principal(null);
		intento.setCar_is_principal(null);
		intento.setTipo_consulta("FIRMA");
		
		String phone = phone_obj.getPhone_number();
		
		if( phone == null || phone.isEmpty())
		{
			intento.setPhone(null);
			
		} else { 
			
			intento.setPhone(phone);
		}
		intento.setMx_curp(naturalPerson.getMx_curp());
								
		intento.setInfo_res("No se realizo la consulta*");
												
		Integer diasValidos        = null;						
		Integer intentosPermitidos = null;
	
		//ID 45 trae los dias de validez de una consulta a buro
		SystemParamPK paramPK     = new SystemParamPK(45,naturalPerson.getNatPerPK().getCompany_id());
		SystemParam system      = systemParamService.loadSelectedSystemParam(paramPK);
		diasValidos = Integer.parseInt(system.getValue());
		
		//el ID 46 trae el numero de intentos permitidos de consulta a buro
		paramPK.setSystem_param_id(46);
		paramPK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		system             = null;
		system             = systemParamService.loadSelectedSystemParam(paramPK);
		intentosPermitidos = Integer.parseInt(system.getValue());					
		
		SimpleDateFormat formatUtilDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		
		//Con los dias validos definidos en system_param se los restamos a la fecha actual para
		//obtener la fecha limite a considerar para obtener el rango de validez de la ocnsulta.
		//fecActual-diasValidos:24-10-13-2 = 22-10-13
		Date fechaLimite       = Utilities.restarFechasDias(new Date(), diasValidos);										
		String fecLimiteStr      = formatUtilDate.format(fechaLimite);
		
		//Se hizo otra variable Date por que no permitio formatear '2013-10-22'
		Date fecLimiteUtilDate = formatUtilDate.parse(fecLimiteStr);
		
		//Consutamos el historico de consultas para saber si ya se realizo una consulta con los
		//mismos datos y en el periodo valido.
		Integer consultasAnteriores = creditAttemptService.getConsultas_anteriores(intento, fecLimiteUtilDate);
		
		System.out.println("PreaprobacionAMO.asignar_credit_history_attempt(consultasAnteriores): " + consultasAnteriores);
		
		if(consultasAnteriores == 0)
		{	
			
			Integer numConsulBuro = creditAttemptService.getCreditHistoryAttemptByCheck(fecLimiteUtilDate, naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id());
	        
	        //Si el numero de intentos permitidos definido en system_param es menor a los intentos permitidos se realiza la consulta.
	        if(intentosPermitidos != null && numConsulBuro != null)
	        {	

				if(numConsulBuro < intentosPermitidos)
					        {
					
								success_consulta_nip = true;
					
					        }else{
					        	
					        	success_consulta_nip = false;
					        	msgWarningBurConsult = "Hemos visto que has intentado varias veces introducir tu información y que por alguna razón no logramos autenticarte ante Buró de Crédito.<br />"
										+ "Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
					        	
					        }
	        
	        }else{
	        	
	        	success_consulta_nip = false;
	        	
	        	msgWarningBurConsult = "Ha ocurrido un error al consultar su información."
						+ " Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
				
	        	
	        }
				
		} else if(consultasAnteriores > 0) {
			/*setSuccess(false);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(true);
			
			setDisplayErrBurConsult(false);*/
			//msgWarningBurConsult = "Ya se realizo una consulta con esos datos, Compruebe que sean correctos. Si el problema continua por favor póngase en contacto con nuestro centro de atención.";
			msgWarningBurConsult = "<b>No nos fue posible autenticarte ante Buro de Crédito con la información que nos proporcionaste.</b>"
					+ "<br /> Por favor considera lo siguiente:"
					+ "<ul>"
					+ "<li>Revisa que tu nombre esté escrito exactamente como está en tu credencial de elector.</li>"
					+ "<li>Verifica tu fecha de cumpleaños.</li>"
					+ "</ul>"
					+ "Si estás seguro de que tu información es correcta, por favor ponte en contacto con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
		} else {
			/*setSuccess(false);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(false);
			
			setDisplayErrBurConsult(true);*/
			msgWarningBurConsult = "Ha ocurrido un error al consultar su información."
					+ " Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
			
		}
	}
	
	
	protected void validaDatosPersonales(){
	
	npPK = new gnNaturalPersonPK();
	
	npPK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
	npPK.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
	
	naturalPerson = service_natural_person.getNaturalPersonById(npPK);
	boolean changeNatPer = false;
	
	if( naturalPerson.getFirst_name() == null && name.getFirst_name() != null ){
		naturalPerson.setFirst_name(name.getFirst_name());
		changeNatPer = true;
	}
	
	if( naturalPerson.getMiddle_name() == null && name.getMiddle_name() != null ){
		naturalPerson.setMiddle_name(name.getMiddle_name());
		changeNatPer = true;
	}
	if( naturalPerson.getFather_last_name() == null && name.getFather_last_name() != null ){
		naturalPerson.setFather_last_name( name.getFather_last_name() );
		changeNatPer = true;
	}
	if( naturalPerson.getMother_last_name() == null && name.getMother_last_name() != null ){
		naturalPerson.setMother_last_name( name.getMother_last_name() );
		changeNatPer = true;
	}
	
	if( naturalPerson.getCountry_id() == null ){
		naturalPerson.setCountry_id(700);
		changeNatPer = true;
	}
	if( naturalPerson.getSector_id() == null ){
		naturalPerson.setSector_id(32);
		changeNatPer = true;
	}
	
	if( changeNatPer ){
		
		generator.setPerson(naturalPerson);
		generator.init_RFC();
		generator.init_CURP();
		
		naturalPerson = generator.getPerson();
	}
	
	if( changeNatPer ){
		
		service_natural_person.update(naturalPerson);
		naturalPerson = service_natural_person.getNaturalPersonById(npPK);
		
	}
	
}

	
protected CreditHistoryAttempt getTemporalCreditHistoryAttempt( CreditHistoryAttempt intento){
		
		CreditHistoryAttempt tmp = new CreditHistoryAttempt();
		tmp.setAddress_number(intento.getAddress_number());
		tmp.setApt_number(intento.getApt_number());
		tmp.setCar_is_principal(intento.getCar_is_principal());
		tmp.setCompany_id(intento.getCompany_id());
		tmp.setConsultation_date(intento.getConsultation_date());
		tmp.setCreditcard_four_digits(intento.getCreditcard_four_digits());
		tmp.setCreditcard_is_principal(intento.getCreditcard_is_principal());
		tmp.setDate_of_birth(intento.getDate_of_birth());
		tmp.setFather_last_name(intento.getFather_last_name());
		tmp.setFirst_name(intento.getFirst_name());
		tmp.setInfo_res(intento.getInfo_res());
		tmp.setIs_check(intento.getIs_check());
		tmp.setMiddle_name(intento.getMiddle_name());
		tmp.setMortgage_is_principal(intento.getMortgage_is_principal());
		tmp.setMother_last_name(intento.getMother_last_name());
		tmp.setMx_curp(intento.getMx_curp());
		tmp.setMx_lada(intento.getMx_lada());
		tmp.setMx_lote(intento.getMx_lote());
		tmp.setMx_manzana(intento.getMx_manzana());
		tmp.setMx_rfc(intento.getMx_rfc());
		tmp.setNeighborhood_name(intento.getNeighborhood_name());
		tmp.setPassword(intento.getPassword());
		tmp.setPhone(intento.getPhone());
		tmp.setProspectus_id(intento.getProspectus_id());
		tmp.setState_name(intento.getState_name());
		tmp.setStatus_res(intento.getStatus_res());
		tmp.setStreet(intento.getStreet());
		tmp.setTown_name(intento.getTown_name());
		tmp.setUser(intento.getUser());
		tmp.setZip_code(intento.getZip_code());
		tmp.setTipo_consulta(intento.getTipo_consulta());
		
		return tmp;
	}
	
	
}
