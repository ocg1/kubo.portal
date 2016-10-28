package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.bean.jackson.AplicationPublicationInvestorDataDTO;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.datamodels.AddInvestorSafiForm;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.refactorizables.AddInvestorSafiRefactorizable;

@ManagedBean @ViewScoped @SuppressWarnings("serial")
public class AddInvestorSafi extends AddInvestorSafiForm 
implements AddInvestorSafiRefactorizable, Serializable
{

	@PostConstruct
	public void init() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		InvestorPK invPk = new InvestorPK();
		
		List<SavingAccount> countList;
		
		
		invPk.setCompany_id(sesion.getCompany_id());
		invPk.setProspectus_id(sesion.getProspectus_id());
		
		Investor inv = investorservice.getInvestorById(invPk);
		
		if( inv != null && inv.getStatus_id()!=null && inv.getStatus_id()!=0){
			successFull = true;
			
			countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if(countList!=null&&countList.size()>0){
			
				for(SavingAccount count : countList){

					if ( count.getStatus() == 1 ){
						
						flagaccount = true;
						successFull = false;
						
					}
					
				}
				
			}
			
		}
		
		if(!successFull){
			
			List<AccessCollector> menuIncomplete = accessService.loadMenu(sesion.getProspectus_id(),sesion.getCompany_id(),sesion.getArea());
			
			if(menuIncomplete!=null&&menuIncomplete.size()>0){			
				boolean flag = false;
				ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
				MenuRegBean menureq=null;
				listRequiredMenu=new ArrayList<MenuRegBean>();
				for(AccessCollector access : menuIncomplete){				
					if(access!=null&&access.getPercentage()!=null&&access.getPercentage()<100 && access.getScreen_id()!=18){
						
						menureq=new MenuRegBean();
						menureq.setTargetItem(access.getName());
						menureq.setScreenid(access.getScreen_id());
						menureq.setIdItem("menu"+access.getMenu_order());
						menureq.setNameItem(recurso.getString(access.getResource_name()));
						listRequiredMenu.add(menureq);
						flag = true;
					}else if(access.getPercentage()==null && access.getScreen_id()!=6){
						menureq=new MenuRegBean();
						menureq.setTargetItem(access.getName());
						menureq.setScreenid(access.getScreen_id());
						menureq.setIdItem("menu"+access.getMenu_order());
						menureq.setNameItem(recurso.getString(access.getResource_name()));
						flag = true;
						listRequiredMenu.add(menureq);
					}
				}
				if(flag && !flagaccount){
					
					setErrorDisp(true);
					
					return;
				}
			}
			
		}
//		
//		SavingAccount saving = savingaccountservice.getSavingAccountByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
//		
//		if(saving !=null && saving.getSafi_account_id() != null){
//			successFull = true;
//		}else{
//		
//			if( saving !=null && saving.getStatus() == 0){
//				displayAction = true;
//			}else{
//				displayAction = false;
//			}
//			successFull = false;
//		}
//		
	}
	
	public boolean finalizaSolicitud(){
		
		
		//int event = 10; // evento Finalización de llenado de solicitud de inversión
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion 
		    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, "sessionBean");
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(sesion.getCompany_id());
		mpk.setProspectus_id(sesion.getProspectus_id());
		
		Membership membership = membershipservice.getMembershipById(mpk);
		
		if(notificacion( Evento.SOLICITUD_INVERSIONISTA_EXITOSA , "" , membership )){
			
			InvestorPK invPk = new InvestorPK();
			
			invPk.setCompany_id(sesion.getCompany_id());
			invPk.setProspectus_id(sesion.getProspectus_id());
			
			Investor inv = investorservice.getInvestorById(invPk);
			
			inv.setStatus_id(1);
			inv.setDate_published(new Date());
			
			try{
				
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
				
				 SystemParam system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") && membership.getPerson().getProspectus().getInfusion_id() != null ){
				 
						InfusionSoft infusion = new InfusionSoft();
						
						infusion.addTAgToContact( membership.getPerson().getProspectus().getInfusion_id() , 551 ); // tag Llena Registro
				
						
				 }
				 
				//HUBSPOT CONSULTA PROSPECTOR RECHAZADO
					if( membership.getPerson() != null && membership.getPerson().getProspectus() != null && membership.getPerson().getProspectus().getHs_vid() != null ){	
						
						system_param_PK_I = new SystemParamPK();
						
						system_param_PK_I.setCompany_id( 1 );
						system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
						
						 system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
						
						if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
							
							HubSpotController hs =  new HubSpotController();
							
							String properties = "{ \"property\" : \"estatus_inversionista\" , \"value\" : \"solicitud_finalizada\"}";
							
							//String properties = "estatus_inversionista=finalizo_solicitud&hbuhu&";
							
							hs.updateProspectus(membership.getPerson().getProspectus().getHs_vid(), properties);
							
							//hs.createField( properties);
							
						 }
					}
				 
			 
			}catch( Exception e ){
				e.printStackTrace();
			}

			
			
			if(investorservice.updateInvestor(inv)){
				
				System.out.println("Actualizacion de status: 1");
				
				registraAcceso( sesion, 20, 100);
				registraAcceso( sesion, 18, 100);
				
			}else{
				
				System.out.println("Error en la Actualizacion de status: 0");
				
			}
			
			msg = "Tus datos como inversionista han sido creados satisfactoriamente";
			 success =true;
			 error =false;
			 wait =false;
			 successFull = true;
		}else{
			msg = "Error al dar de alta la cuenta del inversionista";
			success =false;
			 error =true;
			 wait =false;
		}
		
		return true;
	}
	
	public void setSuccessFullOn(){
		successFull = true;
	}
	
	public boolean notificacion(Evento evento ,String errormsg, Membership membership ){
		
		/*
		Notificador notificador = new Notificador(eventnotificationservice,
				  eventService,
				  mailService,
				  servicecallingService);
		*/
		
		creaProspect_INV_SGB( membership );
		
		try {
			notificador.setEmisor(membership);
			notificador.notificar(evento, null, null, errormsg);
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}
		
		try {
			notificador.setEmisor(membership);
			notificador.notificar(Evento.SOLICITUD_INVERSIONISTA_FINALIZADA);
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}
		
		return true;
	}
	/*
	public boolean notificacion(int evento ,String errormsg, Membership membership ){
		
		List<EventNotification> receptores;
		SendMail                notificacion;
		SimpleDateFormat        formato;
		ControlNotificacion   control;
		ArrayList<Membership>   receptoresTO, receptoresCC, receptoresCCO;
		SMTPServerNotificacion  cuentaSMTP;
		
		int 			        smtp_server_id;	
		boolean                isSendToPromotor = false;
		
		receptores = eventnotificationservice.loadListProspectusByEvent(evento);				
		
		if(receptores != null)
		{
			int ito  = 0;
			int icc  = 0;
			int icco = 0;
			
			String strTo       = "";
			String strCC       = "";
			String strCCO      = "";
			String accountCode = "";
			
			receptoresTO  = new ArrayList<Membership>();
			receptoresCC  = new ArrayList<Membership>();
			receptoresCCO = new ArrayList<Membership>();
			
			if(evento == Evento.ERROR_DESARROLLO.getId())
			{
				if(receptores != null    && 
				    receptores.size() > 0 && 
				   (receptores.get(0).getEvent().getIs_send_to_promotor() + "").equals("S"))
				{														
						isSendToPromotor = true;
				}
			 
				for(EventNotification destinatario: receptores)
				{														
					if(destinatario.getMail_to() == null || destinatario.getMail_to().toUpperCase().equals("TO"))
					{
						if(ito > 0)
						{
							strTo += ",";
						}
						
						strTo += destinatario.getMembership().getEmail();
						receptoresTO.add(destinatario.getMembership());
						ito++;
					}
					
					if(destinatario.getMail_to() != null && destinatario.getMail_to().toUpperCase().equals("CC"))
					{
						if(icc > 0)
						{
							strCC += ",";
						}
						
						strCC += destinatario.getMembership().getEmail();
						receptoresCC.add(destinatario.getMembership());
						icc++;
					}
					
					if(destinatario.getMail_to() != null && destinatario.getMail_to().toUpperCase().equals("CCO"))
					{
						if(icco > 0)
						{
							strCCO += ",";
						}
						
						strCCO += destinatario.getMembership().getEmail();
						receptoresCCO.add(destinatario.getMembership());
						icco++;
					}
					
					accountCode = destinatario.getEvent().getAccountType().getAccount_code();
				}
			}
			
			notificacion = new SendMail();			
			formato      = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));			
			
			notificacion.setStrTo(strTo);
			
			if(strCC.trim().replaceAll(",","").length() > 0)
			{
				notificacion.setStrCC(strCC);
			}
			
			if(strCCO.trim().replaceAll(",","").length() > 0)
			{
				notificacion.setStrCCO(strCCO);
			}
			
			notificacion.setProspectName(membership.getPerson().NombreCompletoNPM());
			notificacion.setProspectus_id(membership.getPerson().getNatPerPK().getProspectus_id()+"");			
			notificacion.setProspectus_id(membership.getMembershipPK().getProspectus_id()+"");			
			notificacion.setStrFecha(formato.format(new Date()));
			//sm.setProspectusID(null);
						
			if(evento == Evento.INVERSIONISTA_SOLICITUD_EXITOSA.getId())
			{				
				notificacion.setAccountCode(accountCode);									
				notificacion.setPubliInversion(true);				
				notificacion.setEmailAcred(membership.getEmail());
				
				String registrationReason = validaRegistrationReason(membership);

				notificacion.setRegistrationReason(registrationReason);				
			}
						
			if(evento == Evento.ERROR_DESARROLLO.getId())
			{
				notificacion.setError(true);
				notificacion.setErrormsg(errormsg);
			}						
											
			if(evento != Evento.CONSULTA_BC_EXITOSA.getId())
			{				
				try 
				{	
					control        = new ControlNotificacion(mailService);
					
					//smtp_server_id = destinatarios.get(0).getEvent().getAccountType().getAccountTypePK().getAccount_type_id();			
					cuentaSMTP     = new SMTPServerNotificacion(0);
					
					control.setSMTPServer    (cuentaSMTP);					
					control.setEvento        (Evento.ERROR_DESARROLLO);						
					
					control.setEmisor        (membership);
					
					control.setReceptoresTO  (receptoresTO);
					control.setReceptoresCC  (receptoresCC);
					control.setReceptoresCCO (receptoresCCO);												
					
					control.registrarProceso();					
					control.notificar        (notificacion);						
					control.registrar();
					
					System.out.println("Mail Enviado Satisfactoriamente a "+strTo);
				} catch (ErrorNotificacionException e) {
					System.out.println("Fallo al enviar mail a "+strTo);
				}
				
			} else {
				for(EventNotification prospecto: receptores)
				{
					strTo  = prospecto.getMembership().getEmail();
					strCC  = "";
					strCCO = "";
					
					notificacion.setStrTo(strTo);
					notificacion.setProspectusID(prospecto.getMembership().getMembershipPK().getProspectus_id());
					
					try 
					{					
						control        = new ControlNotificacion(mailService);
						smtp_server_id = receptores.get(0).getEvent().getAccountType().getAccountTypePK().getAccount_type_id();
						cuentaSMTP     = new SMTPServerNotificacion(smtp_server_id);
						
						control.setSMTPServer (cuentaSMTP);
						control.setEvento     (Evento.CONSULTA_BC_EXITOSA);
						control.setSendType	  (SendTypeNotificacion.TO);
						
						control.setEmisor     (membership);
						control.setReceptor   (prospecto.getMembership());
												
						control.registrarProceso();
						control.notificar      (notificacion);
						control.registrar();
												
						
						System.out.println("Mail Enviado Satisfactoriamente a "+strTo);
					} catch (ErrorNotificacionException e) {
						System.out.println("Fallo al enviar mail a "+strTo);
					}
				}
			}
			
			if(isSendToPromotor)
			{
				if(membership.getPromotor() != null && membership.getPromotor().getMembership() != null)
				{
					strTo  = membership.getPromotor().getMembership().getEmail();
					strCC  = "";
					strCCO = "";

					EventPK eventPK = new EventPK(Evento.ENVIO_CORREO_ESPEC_PROMOTORES.getId(), membership.getMembershipPK().getCompany_id());
					Event event = eventService.getEventByID(eventPK);
					
					notificacion.setAccountCode(event.getAccountType().getAccount_code());					
					notificacion.setProspectusID(membership.getPromotor().getMembership().getPerson().getProspectus().getProspectusPK().getProspectus_id());
					notificacion.setStrTo(strTo);
					
					try {		
						control        = new ControlNotificacion(mailService);
						smtp_server_id = receptores.get(0).getEvent().getAccountType().getAccountTypePK().getAccount_type_id();
						cuentaSMTP     = new SMTPServerNotificacion(smtp_server_id);
						
						control.setSMTPServer (cuentaSMTP);
						control.setEvento(Evento.ENVIO_CORREO_ESPEC_PROMOTORES);
						
						control.setEmisor(membership);
						control.setReceptor(membership.getPromotor().getMembership());
												
						control.registrarProceso();
						control.notificar(notificacion);
						control.registrar();
						
						System.out.println("Mail Enviado Satisfactoriamente al promotor:"+membership.getPromotor().getName());
					} catch (ErrorNotificacionException e) {
						System.out.println("Fallo al enviar el mail al promotor:"+membership.getPromotor().getName());					
					}
				}
			}
			
		}
		
		return true;
	}

	private String validaRegistrationReason( Membership membership ){
		boolean flag = false;
		String registrationReason ="";
		if(membership !=null && membership.getRegistration_reason()!=null){
			if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 7){ //Otro
				//registrationReason = membershipship.getRegistration_reason().getName() ;
				if(membership.getOther_registration_reason()!=null){
					flag = true;
					registrationReason = " "+membership.getOther_registration_reason();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 3){
				
				if(membership.getWho_recommends()!=null){
					flag = true;
					registrationReason = " Recomendado por "+membership.getWho_recommends();
				}else{
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 8){ //PriceShoes
				
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
					
					if(membership.getPriceshoes_number()!=null && membership.getPriceshoes_number().trim().length() >0 ){
						
						registrationReason = " con numero de socio "+membership.getPriceshoes_number();
						
					}
					
			}else if(membership.getRegistration_reason() != null && membership.getRegistration_reason_id() != 6 ){
				
				flag = true;
				registrationReason = " "+membership.getRegistration_reason().getName();
				
			}
			
			if(membership.getPromotor_id()!=null && membership.getPromotor_id()>0 ){ //Promotor
				
				if(membership.getPromotor()!=null){
					
					if(flag ){
					
						registrationReason += " asignado al Promotor "+membership.getPromotor().getName();
						
					}else{
						
						registrationReason += " Promotor "+membership.getPromotor().getName();
						
					}
					
				}
				
			}else{
				
				registrationReason += " sin promotor asignado ";
				
			}
			
		}
		
		return registrationReason;
		
	}
*/
	public void cargaInfo (){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().
										getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(sesion.getCompany_id());
		mpk.setProspectus_id(getProspectus_id());
		
		Membership membership = membershipservice.getMembershipById(mpk);
		
		setPersonSel( membership.getPerson() );
		
	}
	
	private void registraAcceso( SessionBean sesion, int screen, int porcent){
		
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id( sesion.getProspectus_id() );
		access.setScreen_id( screen );
		access.setPercentage( porcent );
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUrl_access( sesion.getUrl_access() );
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access,true);
		
	}
	
	
	private void creaProspect_INV_SGB( Membership membership )
	{
		System.out.println("+++++    LLAMANDO AL SGB    +++++");

		try 
		{
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk  service = locator.getWsSgbRisk();// yyyymmdd. 19860131
			
			String clabe = "";
			
			String banco = "";
			
			String _JSON_str = "";
			
			AplicationPublicationInvestorDataDTO ap_INV = new AplicationPublicationInvestorDataDTO();
			
			List<ClabeAccount> accountList    = clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, membership.getMembershipPK().getCompany_id());
			
			if( accountList != null && accountList.size() > 0  ){
			
				banco = accountList.get(0).getBank_description();
				clabe = accountList.get(0).getMx_clabe();
				
			}
			
			CountryPK cpk = new CountryPK(membership.getPerson().getCountry_id(),membership.getMembershipPK().getCompany_id() );
			
			Country c = countryService.getCountryById( cpk );
			
			StateCatPK stPK = null;
			
			StateCat st = null;
			
			if( membership.getPerson().getState_id() != null ){
			
				stPK = new  StateCatPK(membership.getPerson().getState_id() ,membership.getMembershipPK().getCompany_id() );
				st =  service_estado.getStateById(stPK);
			
			}
		
			if( c != null){
				ap_INV .setCountryOfBirthName( c.getName() );
			}
			
			ap_INV .setDateOfBirth( membership.getPerson().getDate_of_birth() );
			ap_INV .setFatherLastName(membership.getPerson().getFather_last_name());
			ap_INV .setFirstName(membership.getPerson().getFirst_name());
			ap_INV .setGenderId(membership.getPerson().getGender_id());
			ap_INV .setMail(membership.getEmail());
			ap_INV .setMiddleName(membership.getPerson().getMiddle_name());
			ap_INV .setMotherLastName(membership.getPerson().getMother_last_name());
			ap_INV .setMxBankDescription(banco);
			ap_INV .setMxClabe(clabe);
			ap_INV .setMxCurp(membership.getPerson().getMx_curp());
			ap_INV .setMxRfc(membership.getPerson().getMx_rfc());
			ap_INV .setProspectusId(membership.getMembershipPK().getProspectus_id());
			ap_INV .setReason(membership.getRegistration_reason().getName());
			
			if(st != null){
				ap_INV .setStateOfBirthName( st.getName() );
			}
			ap_INV .setStatusId(1);
			
			ObjectMapper mapper = new ObjectMapper();
			
							//Object to JSON in String
			try {
				
				String jsonInString = mapper.writeValueAsString(ap_INV);
				_JSON_str = jsonInString;
				
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
				
			}
			
			// +
			
			
			System.out.println( "******" );
			System.out.println( "***SGB*REQUEST**" );
			System.out.println( _JSON_str );
			System.out.println( "******" );
			System.out.println( "******" );
			
			service.aplicationPublicationInvestor(_JSON_str);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}

}

