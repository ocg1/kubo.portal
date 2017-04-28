package mx.com.kubo.managedbeans.preregistro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.managedbeans.ChangeMail;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.managedbeans.portal.IniciaSession;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.Promotor;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusExtra;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.Referred;
import mx.com.kubo.model.ReferredPK;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.UtmPartnerConversion;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.FormatoCadenas;
import mx.com.kubo.tools.GeneradorCodigos;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

public abstract class PreregistroAMO extends PreregistroDMO
{
	protected void init_session() 
	{
		faces    = FacesContext.getCurrentInstance();	
		
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		external = faces.getExternalContext();		
		
		if( resolver != null ){
							
			header = (HeaderBean)  resolver.getValue(context, null, "headerBean");	
			header.validateUser();		
			
			session = (SessionBean) resolver.getValue(context, null, "sessionBean");	
			
				
			
			if (session.getArea() == null ) 
			{
				session.setArea('L');			
			}
			
			area = session.getArea();						
					
			if( sm_email == null )
			{		
				sm_email = session.getSm_email();			
			}
			
			if( first_name == null )
			{		
				first_name = session.getSm_first_name();		
			}	
		
		}
	}
	
	protected void init_request_map() 
	{
		request_map = external.getRequestParameterMap();
		
		first_name       	 = request_map.get("sm_first_name");
		middle_name      	 = request_map.get("sm_middle_name");
		father_last_name 	 = request_map.get("sm_father_last_name");
		mother_last_name     = request_map.get("sm_mother_last_name");		
		//sm_lada_phone_number = request_map.get("sm_lada_phone_number");		
		sm_phone_number	     = request_map.get("sm_phone_number");		
		sm_email			 = request_map.get("sm_email");		
		sm_id 				 = request_map.get("sm_id");  // importante poner dentro de prospectcus		
		partner 			 = request_map.get("partner");  // importante poner dentro de prospectcus
		partner_id           = request_map.get("partner_id");
		utm_partner_id		 = request_map.get("utm_source");	
		user_agent           = request_map.get("user_agent");
		device_info		     = request_map.get("device_info");	
		promotorID		     = request_map.get("promotor");
		havePromotor		 = request_map.get("selectedReg");
		
		utm_campaign		 = request_map.get("utm_campaign");
		
		utm_medium			 = request_map.get("utm_medium");
		
		if(partner == null && partner_id != null)
		{
			partner = partner_id;
		}
		
		if( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 &&  !utm_partner_id.equals("INVITEDBY") &&  !utm_partner_id.equals("REC") )
		{
			partner = utm_partner_id;
		}
		
		if(partner == null ) {
			partner = session.getPartner();
		}
		
		if(  user_agent != null && user_agent.trim().length() > 0 && !user_agent.trim().equalsIgnoreCase("null") ){
		
		session.setUser_agent(user_agent);
		
		}
		
		if(  device_info != null && device_info.trim().length() > 0 && !device_info.trim().equalsIgnoreCase("null") ){
		
			session.setDevice_info(device_info);
		
		}
		
		if( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 && ( utm_partner_id.equals("INVITEDBY") || utm_partner_id.equals("REC") ) ){
			
			initReferred();
			
		}
		
		if( session.getReferred_client() != null && session.getReferred_client().trim().length()>0 ){
			registration_reason_id = RECOMENDACION_CLIENTE_KUBO;
			setRefered_search(session.getReferred_client());
			disabledRegistration   = true;
		}
		
		if( session.getReferred_not_client() != null && session.getReferred_not_client().trim().length()>0 ){
			registration_reason_id = RECOMENDACION;
			setWho_recommends(session.getReferred_not_client());
			disabledRegistration   = true;
		}
		
		if( (session.getOrigen_campaign() == null || session.getOrigen_campaign().length() == 0 ) && (utm_campaign != null && utm_campaign.length() > 0 ) ){
			
			session.setOrigen_campaign( utm_campaign );
			
		}
		
		
		
		
	}
	
	protected void init_natural_person() 
	{
		natural_person = new NaturalPerson();
		
		if(first_name != null || sm_email != null)
		{			
			natural_person.setFirst_name(first_name);
			natural_person.setMiddle_name(middle_name);
			natural_person.setFather_last_name(father_last_name);
			natural_person.setMother_last_name(mother_last_name);	
			
			//ladaCelProspectus      = sm_lada_phone_number;
			phoneCellProspectus    = sm_phone_number;			
			
			fbview = true;
			registration_reason_id = 1;
			
			email = sm_email;
			area  = 'L';
			
			if(sm_email != null && sm_email.trim().length() > 0)
			{
				disabledMail = true;
			}										
		}
	}
	
	protected void init_registrattion_reason() 
	{
		lista_registration_reason = reasonsService.loadRegistrationReasonWithoutOtherList();
		
		if( session.getArea() == null || session.getArea().equals('L'))
		{		
			if( partner != null && partner.length() > 0 && !partner.equals("FB") && !partner.equals("INVITEDBY") && !partner.equals("REC") )
			{			
				session.setPartner(partner);
				
				String partner_str = validaUTMPartner( partner );
				
				if(partner_str == null || partner_str.trim().length() == 0){
					partner_str = partner;
				}
				
				int index = 0;
				
				for( RegistrationReason reason: lista_registration_reason)
				{				
					
					
					
					if( reason.getPartner_id() != null && reason.getPartner_id().equals(partner_str))
					{
						registration_reason_id = reason.getRegPK().getRegistration_reason_id();
						disabledRegistration   = true;
						fbview 				   = false;
						
						reason.setIs_active('S');
						
						lista_registration_reason.add(index, reason);
						
						break;
					}	
					
					index++;
				}	
				
			} else if( partner != null && partner.trim().length() > 0 && partner.equals("FB") ) { 
								
				fbview = true;
				registration_reason_id = 1;
				
			}
			
			if( session.getPartner() != null && session.getPartner().length() > 0)
			{
				int index = 0;
				
				String partner_str = validaUTMPartner( session.getPartner() );
				
				if(partner_str == null || partner_str.trim().length() == 0){
					partner_str = session.getPartner();
				}
				
				for( RegistrationReason reason: lista_registration_reason)
				{			
					if( reason.getPartner_id() != null && partner_str.equals(reason.getPartner_id()))
					{					
						registration_reason_id = reason.getRegPK().getRegistration_reason_id();
						disabledRegistration   = true;
						fbview                 = false;
						
						reason.setIs_active('S');
						
						lista_registration_reason.add(index, reason);
						
						break;					
					}			
					
					index++;
				}			
			}		
		}
	}
	
	protected void init_session_flags() 
	{
		session.setSm_email(sm_email);		
		session.setSm_first_name(first_name);
		
		if(session.isFailedUser())
		{
			setForwardMail("");
			setDisplayWarningUser("block");
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>El usuario <b>" + session.getEmail() + "</b> no existe.</span>");
			
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isFailedPass())
		{
			setForwardMail("");
			setDisplayWarningUser("block");
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>Contraseña incorrecta.</span>");
			setEmail(header.getUser());
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isSessionUsed())
		{
			setForwardMail("");
			setDisplayWarningUser("block");
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>El usuario <b>" + session.getEmail() + "</b> ya cuenta con una sesión abierta.</span>");
			
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isFailedActive())
		{
			setForwardMail(header.getUser());
			setDisplayWarningUser("block");
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>La cuenta "+session.getTemporalUser()+" no esta activa.<br />" +
																			"Revisa tu correo electrónico(incluyendo correo no deseado, spam)<br />" +
																			"<br />*Si no te llegó el correo de activación, da <span class='linkMail' onclick='javascript:chechForward();'> click aquí</span> para enviarte de nuevo tu correo de activación.<br />" +
																			"<br />*Si deseas <span style='font-weight: bold; font-size:1.03em;cursor: pointer;' onclick='javascript:changeMail();'>cambiar tu correo</span>, da click <span style='font-weight: bold; text-decoration: underline;cursor: pointer;' onclick='javascript:changeMail();'>aquí</span> </span> " );
			setEmail(header.getUser());
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
			
			flagActive = true;
		}
		
		if(session.isCanceled())
		{
			setForwardMail(header.getUser());
			setDisplayWarningUser("block");
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>La cuenta <b>"+session.getTemporalUser()+"</b> está cancelada.<br />" +
																			"*Si existe un error o deseas reactivarla de nuevo ponte en contacto con nosotros al teléfono: <b>"+recurso.getString("Kubo_telefono")+"</b>  </span> " );
		
			
			setEmail(header.getUser());
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(true);
		}
		
		if(session.isFailedTracking())
		{
			setForwardMail("");
			setDisplayWarningUser("block");
			
			ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
			setWarningUser("<span style='font-size: .8em; color: #ff0000;'>La cuenta '<b>"+header.getUser()+"</b>' tiene un error en su número de folio, póngase en contacto con <b>soporte-kubo</b> para solucionar el problema. <b>"+recurso.getString("Kubo_telefono")+"</b>  </span>");
			
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isHoldMail())
		{
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setHoldMail(false);
			session.setCanceled(false);
			setEmail(header.getUser());
			//header.setUser(session.getTemporalUser());
		}
	}
	
	protected void init_system_param() 
	{
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id( 1 );
		system_param_PK.setSystem_param_id(SECCION_INVERSIONISTA_ENABLED);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
						
		if(system_param != null)
		{		
			isActiveInvestmentSection = system_param.getValue();		
		}
		
		version_description = service_system_param.getVersion_description();
		
		session.setVersion_description(version_description);
		
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id( 1 );
		system_param_PK.setSystem_param_id(PEDIR_CONTRASENA_SEGURA);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
						
		if(system_param != null && system_param.getValue() != null )
		{		
			setPide_contrasena_segura(system_param.getValue().equals("S"));	
		}
		
	}
	
	protected void init_suscribe()
	{
		selectedSuscribe = new ArrayList<String>();		
		selectedSuscribe.add("S");
		
		String suscribe = "N";
		
		for (String s : selectedSuscribe) 
		{
			if(s.equals("S"))
			{
				suscribe = s;
			}
		}
		
		subscribeVal = suscribe;
		
	}
	
	protected void init_medium(){
		
		if( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 &&  !utm_partner_id.equals("INVITEDBY") &&  !utm_partner_id.equals("REC") && utm_medium != null && utm_medium.trim().length() > 0 ){
			medium = utm_medium;
		}else{
			if( session.getMedium() != null && session.getMedium().trim().length() > 0 && session.getPartner() != null && (!session.getPartner().equalsIgnoreCase("INVITEDBY") || !session.getPartner().equalsIgnoreCase("REC") ) ){
				medium = session.getMedium();
			}
		}
	}
	
	protected void init_membership_by_fb_id() 
	{
		
		membership = membershipService.getMembershipbyFBID( fb_id );
		
		if(membership != null)
		{
			// System.out.println("El fb_id ya está asignado al prospectus_id : " + membership.getMembershipPK().getProspectus_id() + " email: " + membership.getEmail() );
			
			warningMail = "<script>alertify.alert(' La cuenta de Facebook  ya se encuentra ligada a la cuenta con email: <strong>" + membership.getEmail() +"</strong> de Kubo.financiero.');</script>";
			
			request.addCallbackParam("isFBValid", false);
			
		}else{
			
			// System.out.println("El fb_id no ha sido asignado ... OK!! " + fb_id );
			request.addCallbackParam("isFBValid", true);
			
		}
		
	}
	
	protected void init_membership() 
	{
		membership = membershipService.getMembershipByEmail(email);
		
		if(membership != null)
		{
			log.info("El mail ya existe");
			
			warningMail = "<script>";
			
			if( membership.getIs_active() == 0 )
			{
				displayWarningMail = "block";
				
				request.addCallbackParam("warningMail", MEMBER_NOT_ACTIVE);
				
				membership.setIs_active(1);
				
				membershipService.update(membership);
				
				// warningMail += "alertify.error('El correo "+membership.getEmail()+" ya se encuentra registrado. Para iniciar sesión, dale click en ENTRAR');";
				
			} else {
											
				request.addCallbackParam("warningMail", MEMBER_ACTIVE);
				// warningMail += "alertify.error('El correo "+membership.getEmail()+" ya se encuentra registrado. Para iniciar sesión, dale click en ENTRAR');";
			}
			
			
			warningMail += "$('#email').removeClass('requiredClass');varEmail=false;</script>";
			
		} else {
			
			displayValMail     = "block";
			displayWarningMail = "none";
			
			imgValMail = "<img src='../resources/img/check.png' /><script> varEmail=true;</script> ";	
			warningMail = "<script>$('#email').removeClass('requiredClass');varEmail=true;</script>";
			
			request.addCallbackParam("warningMail", MEMBER_NEW);
		}
	}
	
	protected void asignar_natural_person() 
	{
	    first_name       = FormatoCadenas.formatoNombre(natural_person.getFirst_name());
	    middle_name      = FormatoCadenas.formatoNombre(natural_person.getMiddle_name());
	    father_last_name = FormatoCadenas.formatoNombre(natural_person.getFather_last_name());
	    mother_last_name = FormatoCadenas.formatoNombre(natural_person.getMother_last_name());
	    
		natural_person.setFirst_name(first_name);
		natural_person.setMiddle_name(middle_name);
		natural_person.setFather_last_name(father_last_name);
		natural_person.setMother_last_name(mother_last_name);	
		
		if(zona_cobertura_ENABLED)
		{
			natural_person.setIs_coverage_zone('S');
			
		} else {
			
			natural_person.setIs_coverage_zone('N');
		}
	}
	
	protected void add_membership() 
	{
		membership = new Membership();
		
		
		membership.setEmail(email);		
		membership.setPassword(Utilities.encrypt(password));		
		membership.setContract("contract");		
		membership.setAccept_subscribe(subscribeVal);		
		membership.setIs_active(0);
		membership.setIs_blocked("N");
		membership.setCreation_date(new Date());
		
		membership.setFb_id(fb_id);
		
		if( registration_reason_id == null ){
			registration_reason_id =  14;// internet (organico)
		}
		membership.setRegistration_reason_id(registration_reason_id);
		
		membership.setOrigin(session.getOrigen_campaign());
		membership.setUrl_origin(session.getUrl_value());
		membership.setEmail_verified("N");
		membership.setUtm_medium(medium);
		
		if( area != null && area.toString().equals("I") ){
			membership.setIs_client_pass("S");
		}else{
			
			if( isPide_contrasena_segura() ){
				
				if( fb_id == null || fb_id.trim().equals("") ){
					membership.setIs_client_pass("S");
				}else{
					membership.setIs_client_pass("N");
				}
				
			}else{
				
				membership.setIs_client_pass("N");
				
			}
		}
		
		if( registration_reason_id != null && registration_reason_id == 8)
		{
			membership.setPriceshoes_number(priceshoes_number);
			
			if(promotor_id != null && promotor_id != 0 )
			{
				membership.setPromotor_id(promotor_id);
			} else {
				membership.setPromotor_id(null);
			}
			
		} else {
			membership.setPriceshoes_number(null);
		}
		
		if( registration_reason_id != null && (registration_reason_id == 3 || registration_reason_id == 4) )
		{
			membership.setWho_recommends(who_recommends);
			
		} else {
			
			membership.setWho_recommends(null);
		}
		
		if(session.getPartner() != null && registration_reason_id != null && registration_reason_id == 7 )
		{
			membership.setRegistration_reason_id(7);
			membership.setPromotor_id(null);
			membership.setOther_registration_reason(session.getPartner());
			
		} else {		
			
			membership.setRegistration_reason_id(registration_reason_id);
			
			if(promotor_id != null && promotor_id != 0 )
			{
				membership.setPromotor_id(promotor_id);
				
			} else {
				
				membership.setPromotor_id(null);
			}
			
			membership.setOther_registration_reason(other_registration_reason);		
		}
	}
	
	protected void add_prospectus()
	{
		company_id = 1;
		prospectus_PK = new ProspectusPK(0, company_id);		
		prospectus    = new Prospectus();		
		
		prospectus.setProspectusPK(prospectus_PK);
		prospectus.setArea(area == 'L' ? 'L' : 'I'); 		
		prospectus.setPerson_type('F');				
		
		if( natural_person.getFirst_name() == null ){
			natural_person.setFirst_name("");
		}
		
		exitoGuardarProspecto = prospectusService.saveProspectAndNaturalPersonAndMembership(prospectus, natural_person, membership);
		
		if(exitoGuardarProspecto)
		{
			membership = membershipService.getMembershipByEmail(email);
			
			prospectus = membership.getPerson().getProspectus();
			
			prospectus_id = prospectus.getProspectusPK().getProspectus_id();
			
			ProspectusExtra extra =  new ProspectusExtra();
			try{
				extra.setProspectus_id(prospectus_id);
				extra.setValue1_ps( password );
				
				prospectusextraservice.saveProspectusExtra(extra);
				
			}catch(Exception e){
				
			}
			
			add_tracking_id();
			
		}			
	}
	
	protected void add_recomendado(Integer prospectusId, Integer company_id )
	{
		// System.out.println("Asignando quien lo recomendó Recomendacion .. ");
	
		ReferredPK refPK = new ReferredPK();
		
		RequestContext rc = RequestContext.getCurrentInstance();
			

		MembershipPK mpk = new MembershipPK( prospectusId , company_id );
		Membership member = membershipService.getMembershipById(mpk);
		
		refPK.setProspectus_id_destiny( member.getMembershipPK().getProspectus_id() );
		
		refPK.setProspectus_id_origin (Integer.parseInt( getRefered_search() ) );
		
		refPK.setCompany_id( company_id );
		
		Referred newReferred = new Referred();
		
		newReferred.setPk( refPK );
		newReferred.setDate_referred( new Date() );
			
		if( referredservice.addReferred( newReferred ) )
		{				
			rc.addCallbackParam( "accion","1" );//Agregar
			//verificaRecomendado();
			//validaRegistrationReason();
			mpk = new MembershipPK( Integer.parseInt( getRefered_search() ) , company_id  );
			Membership referredIni = membershipService.getMembershipById(mpk);
			String valorNuevo = referredIni.getPerson().NombreCompletoNPM();
			
			member.setWho_recommends(valorNuevo);
			
			membershipService.update(member);				
		}
	}
	
	protected void add_phone() 
	{
		company_id = prospectus.getProspectusPK().getCompany_id();
		
		phonePK  = new PhonePK(prospectus_id, company_id);
		newPhone = new Phone();
		
		newPhone.setPhonePk(phonePK);
		newPhone.setPhone_type_id(6);
		newPhone.setPhone_number( phoneCellProspectus );
		newPhone.setOwned("1");
		newPhone.setArea(prospectus.getArea());
		
		phoneService.addPhone(newPhone, prospectus_id, company_id);
	}
	
	protected void add_password() 
	{
		pssPk = new PasswordHistoryPK();
		pssPk.setCompany_id(company_id);			
		pssPk.setProspectus_id(prospectus_id);
		
		/*
		System.out.println( "------------------------------------------------" );
		System.out.println( "------------------------------------------------" );
		System.out.println( "company_id: " +  company_id );
		System.out.println( "prospectus_id: " +  prospectus_id );
		System.out.println( "membership.getPassword(): " +  membership.getPassword() );
		System.out.println( "------------------------------------------------" );
		System.out.println( "------------------------------------------------" );
		*/
		passHis = new PasswordHistory(); 										
		passHis.setDate_changed(new Date());
		passHis.setPassword( membership.getPassword() );
		passHis.setPwdhpk( pssPk );
		
		passwordhistoryservice.savePasswordHistory(passHis);	
	}
	
	protected void add_investor() 
	{
		if(area.toString().equals("I"))
		{			
			investor_PK = new InvestorPK();				
			investor_PK.setCompany_id( membership.getMembershipPK().getCompany_id());
			investor_PK.setProspectus_id(membership.getMembershipPK().getProspectus_id());
			
			investor = new Investor();
			investor.setPk(investor_PK);
			investor.setDate_approved(null);
			investor.setDate_published(null);
			
			investor.setStatus_id(0);
		
			if(investorservice.addInvestor(investor)){}
		/*	{
				System.out.println("Inversionista Dado de alta: "+membership.getMembershipPK().getProspectus_id());
				
			} else {
				
				System.out.println("Error Alta Inversionista: "+membership.getMembershipPK().getProspectus_id());
			}*/
		}
	}
	
	protected void add_address() 
	{
		
		if( codigo_postal != null && codigo_postal.trim().length() > 0 ){
		
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK = new AddressPK();
			address_PK.setCompany_id(company_id);			
			address_PK.setProspectus_id(prospectus_id);
			address_PK.setAddress_id(address_id);
			
			address = new Address();
			address.setAddressPK(address_PK);
			address.setAddress_type_id(CASA);		
			address.setCountry_id(MEXICO);
			address.setZip_code(codigo_postal);
			address.setNeighborhood_id(colonia_id);
			address.setTown_id(town_id);
			address.setState_id(state_id);
			
			save_address_OK = service_address.add(address);
		
		}
	}
	
	private void add_tracking_id() 
	{
		try
		{						
			log.info("generate prospectus Tracking_Id");
			
			tracking_id = GeneradorCodigos.get_tracking_id(COMPANY_ID, prospectus_id);
			
			//System.out.println("PreregistroAMO.add_tracking_id(): " + tracking_id);
			
			prospectus.setTracking_id(tracking_id);
			
			prospectusService.update(prospectus);
			
			exitoGuardarProspecto = true;
			
		} catch(Exception e) {
			try
			{							
				log.info("Error al generar Tracking_Id intentando de nuevo");
				
				Thread.sleep(500);
				
				tracking_id = GeneradorCodigos.get_tracking_id(COMPANY_ID, prospectus_id);
				
				prospectus.setTracking_id(tracking_id);
				
				prospectusService.update(prospectus);
				
				exitoGuardarProspecto = true;
				
			} catch(Exception e1) {
				
				log.info("Error al generar Tracking_Id ");
				
				exitoGuardarProspecto = false;
			}
		}
	}
	
	protected void add_activation_code() 
	{
		activation_code = GeneradorCodigos.get_activation_code(prospectus_id, COMPANY_ID);
		
		membership_PK = new MembershipPK(prospectus_id, COMPANY_ID);
		
		membership = membershipService.getMembershipById(membership_PK);
				
		membership.setActivation_code(activation_code);
	}
	
	protected void update_membership() 
	{
		try
		{	
			log.info("NEW PROSPECTID= " + prospectus_id);			
			log.info("Create Activation code");
			
			membershipService.update(membership);
			
		} catch(Exception e) {
			try
			{
				log.info("Error al generar Activation code intentando de nuevo");
				
				Thread.sleep(500);
				
				membershipService.update(membership);
				
				exitoGuardarProspecto = true;
				
			} catch(Exception e1) {
				
				log.info("Error al generar Activation code");
				
				exitoGuardarProspecto = false;
			}
		}	
	}
	
	protected void notificar_evento() 
	{
		notificar_evento_OK = true;
		
		if(exitoGuardarProspecto)
		{
			try
			{												
				log.info("Begin send mail");
				log.info("enviando a " + email);
								
				if((area != null && area.toString().equals("L") ) && isActiveInvestmentSection.equals("S"))
				{	
					try
					{
						notificador = new NotificadorIMP();
						notificador.setEmisor(membership);
						notificador.notificar(Evento.REGISTRO_USUARIO);
						
						navigationRule = NavigationRule.WAIT_CONFIRMATION;																	
						
						flagActiveCode = true;
						
						if( membership.getRegistration_reason_id() != null && membership.getRegistration_reason_id() == 8 &&  membership.getPriceshoes_number() !=null  && membership.getPriceshoes_number().length() > 0 )
						{
							notificador = new NotificadorIMP();
							notificador.setEmisor(membership);
							notificador.notificar(Evento.REGISTRO_SOCIO_PARTNER);							
						}		
													
					} catch(NotificacionException e) {
						
						e.printStackTrace();
						notificar_evento_OK = false;
						
						flagActiveCode = true;
						
						
					}
					
					notificar_evento_OK = true;
					
				} else if(area != null && area.toString().equals("I")) {					
					
					try 
					{			
						notificador = new NotificadorIMP();
						notificador.setEmisor(membership);
						notificador.notificar(Evento.PREREGISTRO_INVERSIONISTA);
						
						flagActiveCode = true;
						
						navigationRule = NavigationRule.WAIT_CONFIRMATION;			
					
						
					} catch (NotificacionException e) {	
					
						e.printStackTrace();
						
//						flagActiveCode = false;		
//						notificar_evento_OK = false;
						
						flagActiveCode = true;
						navigationRule = NavigationRule.WAIT_CONFIRMATION;
						
					}						
				}	
				
			} catch(Exception e) {
				
				e.printStackTrace();
				//System.out.println("fallo al enviar mail: " + e.getMessage() );
				
				flagActiveCode = false;			
			}		
		}	
	}
	
	protected void asignar_navigation_rule() 
	{				
		if(flagActiveCode)
		{						
			String nombre = natural_person.getFirst_name() ;
			
			if(natural_person.getMiddle_name() != null && natural_person.getMiddle_name().trim().length() > 0)
			{
				nombre += " " +natural_person.getMiddle_name();
			}
			
			session.setNombre(nombre);
			session.setEmail(email);
			session.setIsnew("S");
			session.setProspectus_id(prospectus_id);
			
			
			if(session!= null && session.getArea() != null && session.getArea().toString().equals("I") && isActiveInvestmentSection.equals("N") )
			{			
				navigationRule =  NavigationRule.WAIT_INVESTMENT;
				
			} else {
				
				navigationRule =  NavigationRule.WAIT_CONFIRMATION;
			}
				
	//		} else if(session.getPartner()==null || registration_reason_id != 7) 
	//			{
	//				navigationRule =  NavigationRule.WAIT_CONFIRMATION;
	//				
	//			} else if(loginService.verifySession(session, member.getEmail(),getPassword())) 
	//				{					
	//						inicializaSimulador(false);
	//						navigationRule = NavigationRule.REGISTRAR;					
	//				
	//				} else 
	//					{				
	//						navigationRule = NavigationRule.ERROR_PRERGISTRO;
	//					}
			
			if(navigationRule.toString().equals(NavigationRule.WAIT_CONFIRMATION.toString()))
			{			
				membership.setIs_active(ACTIVO);
				membershipService.update(membership);
								
				//System.out.println("\n --- ENTRANDO A INICIAR SESSION --- "+membership.getEmail()+"  --- "+getPassword() + "\n");
				
//				header = (HeaderBean) resolver.getValue(context, null, "headerBean");				
//				header.setUser(membership.getEmail());
//				header.setPassword(password);				
//				header.iniciaSesion();
				
				
				if( session.getUrl_value() == null || session.getUrl_value().trim().length() == 0 ){
					
					InicioValuesIMP inicioV = (InicioValuesIMP) resolver.getValue(context, null, "inicioValues");
					String url_access		= inicioV.getUrl_value();
					
					session.setUrl_access(url_access);
				
				}else{
					session.setUrl_access(session.getUrl_value());
				}
				
				IniciaSession inicio = (IniciaSession) resolver.getValue(context, null, "iniciaSession");
				inicio.setEmail(membership.getEmail());
				inicio.setPassword(password);				
				inicio.iniciaSesion();
				
				Simulator simulator = (Simulator) resolver.getValue(context, null, "simulator");
				
				simulator.setAmmount(0D);
				simulator.simulaCred(false);
				
				if( session.getArea().toString().equals("I") )
				{
					navigationRule = NavigationRule.BIENVENIDA_INVERSION;
					
					try{
						
						SystemParamPK system_param_PK_I = new SystemParamPK();
						
						system_param_PK_I.setCompany_id( 1 );
						system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
						
						 SystemParam system_param_I = service_system_param.loadSelectedSystemParam(system_param_PK_I);
						
						 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
						 
								InfusionSoft infusion = new InfusionSoft();
								
								Integer contactId = infusion.altaContacto("", "", membership.getEmail());
								
								prospectus.setInfusion_id(contactId);
								
								prospectusService.update( prospectus );
								
								infusion.opt_in_emai(membership.getEmail(), "Acepta Registro Kubo.Financiero");
								
								infusion.addTAgToContact( contactId , 549 ); // tag Llena Registro
						
								
						 }
						 
						 callSaveHubspot( membership );
					 
					}catch( Exception e ){
						e.printStackTrace();
					}

					
				
				} else {
					
					if(session!= null && session.getArea() != null && session.getArea().toString().equals("L") && membership.getRegistration_reason_id() != null && membership.getRegistration_reason_id() != 6 )
					{
						try{
						
							SystemParamPK system_param_PK_I = new SystemParamPK();
							
							system_param_PK_I.setCompany_id( 1 );
							system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
							
							 SystemParam system_param_I = service_system_param.loadSelectedSystemParam(system_param_PK_I);
							
							 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
							 
									InfusionSoft infusion = new InfusionSoft();
									
									Integer contactId = infusion.altaContacto("", "", membership.getEmail());
									
									prospectus.setInfusion_id(contactId);
									
									prospectusService.update( prospectus );
									
									infusion.opt_in_emai(membership.getEmail(), "Acepta Registro Kubo.Financiero");
									
									infusion.addTAgToContact( contactId , 157 ); // tag Llena Registro
							
									
							 }
							 
							 callSaveHubspot( membership );
						 
						}catch( Exception e ){
							e.printStackTrace();
						}
						 
					}
						
					
					navigationRule =  NavigationRule.BIENVENIDA_ACREDITADO;
				}
				
			} 
	
		} else {
			
			log.info("Error en codigo de activacion");
			
			navigationRule = NavigationRule.ERROR_PRERGISTRO;
		}
	}
			
	public void setChangeMailAction()
	{
		//System.out.println("setChangeMailAction temporalUser: " + getForwardMailChng());
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance()
                									    .getApplication()
                									    .getELResolver()
                									    .getValue(elContext, null, "sessionBean");
		
		//System.out.println("setTemporalUser: " + getForwardMailChng());
		
		sesion.setTemporalUser( getForwardMailChng() );
		
		 Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		 ChangeMail change = (ChangeMail)viewMap.get("changeMail");
		 change.init();
	}
	
	public void setForwardMailAction()
	{
		//System.out.println(" setForwardMailAction temporalUser: " + getForwardMail());
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance()
                									    .getApplication()
                									    .getELResolver()
                									    .getValue(elContext, null, "sessionBean");
		
		//System.out.println("senMailForChange: "+getForwardMail());
		
		sesion.setTemporalUser( getForwardMail() );
		
	}
	
	public void getPromotorByCodeID_portal()
	{
		if(codeID != null && !codeID.equals(""))
		{
			boolean flag = false;
			Promotor promotorOut = new Promotor();
			
			for(Promotor promotorIn : membershipService.getListPromotors())
			{
				if(getCodeID().equals(promotorIn.getCode_id()))
				{
					promotorOut = promotorIn;
					flag = true;
					break;
				}
				
			}
			
			if(flag)
			{
				setPromotor_id(promotorOut.getPromotorPk().getPromotor_id());
				setDisplayWarningPromotor("none");
				setWarningPromotorOff("");
				setWarningPromotorOn("<script>varPromo=true; 	alertaQuitar ('#cbo-promotor'); $('#cbo-promotor').removeClass('requiredClass');</script>");
			} else {
				setPromotor_id(null);
				setDisplayWarningPromotor("block");
				setWarningPromotorOff("<script> alerta ('La clave del promotor no existe', '#cbo-promotor');  $('#cbo-promotor').addClass('requiredClass');</script>");
				setWarningPromotorOn("<script>$('#cbo-promotor').addClass('requiredClass');varPromo=false;</script>");
			}
			
		} else {
			setPromotor_id(null);
			setDisplayWarningPromotor("none");
			setWarningPromotorOff("");
			setWarningPromotorOn("<script>varPromo=true;</script>");
		}
		
	}
	
	public void getPromotorByCodeID()
	{
		if(codeID != null && !codeID.equals(""))
		{
			boolean flag = false;
			Promotor promotorOut = new Promotor();
			
			for(Promotor promotorIn : membershipService.getListPromotors())
			{
				if(getCodeID().equals(promotorIn.getCode_id()))
				{
					promotorOut = promotorIn;
					flag = true;
					break;
				}
				
			}
			
			if(flag)
			{
				setPromotor_id(promotorOut.getPromotorPk().getPromotor_id());
				setDisplayWarningPromotor("none");
				setWarningPromotorOff("");
				setWarningPromotorOn("<script>varPromo=true; alertaQuitar ('#cbo-promotor');</script>");
			}else{
				setPromotor_id(null);
				setDisplayWarningPromotor("block");
				setWarningPromotorOff("La clave del promotor no existe");
				setWarningPromotorOn("<script>$('#cbo-promotor').addClass('requiredClass');varPromo=false; alerta ('La clave del promotor no existe', '#cbo-promotor'); </script>");
			}
		}else{
			setPromotor_id(null);
			setDisplayWarningPromotor("none");
			setWarningPromotorOff("");
			setWarningPromotorOn("<script>varPromo=true; alertaQuitar ('#cbo-promotor');</script>");
		}
		
	}
	
	public void clearWarn()
	{
		
//		setDisplayWarningUser("false");
//		setWarningUser("");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		try{
			if(flagActive)
				Thread.sleep(3000, 0);
			
			flagActive = false;
		}catch(Exception e ){
			flagActive = false;
		}
		HeaderBean header 
	    = (HeaderBean) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "headerBean");
		header.setUser(email);
		header.validateUser();
	}
	
	public void inicializaSimulador(boolean isSafi )
	{
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		Simulator simulator 
		    = (Simulator) FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, "simulator");
		SessionBean sesion 
	    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "sessionBean");
		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(sesion.getProspectus_id(), sesion.getCompany_id()) ;
		
		if(sim!=null){
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			if(sesion.getRate()!=null)
				simulator.setTasaTotal(sesion.getRate());
			else
				simulator.setTasaTotal(61.50D);
			simulator.setTerm_id(sim.getTerm_id());
			simulator.setPurpose_id(sim.getPurpose_id());
			//simulator.simulaCred();
		}
		
		simulator.simulaCred( isSafi );		
	}

	private void initReferred(){
		
		if( utm_medium != null && !utm_medium.trim().isEmpty() ){
			
			Membership membership = membershipService.getMembershipByEmail(utm_medium);
			
			if( membership != null ){
				
				session.setReferred_client(membership.getMembershipPK().getProspectus_id()+"");
				session.setReferred_not_client("");
				
			}else{
				
				session.setReferred_client("");
				session.setReferred_not_client(utm_medium);
				
			}
		}
		
	}
	
	
private boolean callSaveHubspot( Membership membership ){
		
		try{
			
			SystemParamPK system_param_PK_I = new SystemParamPK();
			
			system_param_PK_I.setCompany_id( 1 );
			system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			 SystemParam system_param_I = service_system_param.loadSelectedSystemParam(system_param_PK_I);
			 
			 MembershipPK mpk = new MembershipPK(membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id());
			 
			 membership = membershipService.getMembershipById(mpk);
			
			 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
			
					HubSpotController hs =  new HubSpotController();
					
					StringBuilder properties = new StringBuilder();
					
					properties.append("{ \"property\" : \"prospectoid\" , \"value\" : \""+membership.getMembershipPK().getProspectus_id()+"\"}," +
						 "{ \"property\" : \"email\" , 		 \"value\" : \""+membership.getEmail()+"\"}, "
						 + "{\"property\": \"procedencia\", \"value\": \"portal_kubo\" }");
					
					if( membership.getRegistration_reason() != null && membership.getRegistration_reason().getPartner_id() != null && membership.getRegistration_reason().getPartner_id().trim().length() > 0 ){
						properties.append( ",{\"property\": \"source\" , 		 \"value\" : \""+membership.getRegistration_reason().getPartner_id()+"\"}");
					}
					
					if(membership.getUtm_medium() != null && membership.getUtm_medium().trim().length() > 0){
						properties .append( ",{\"property\": \"medium\" , 		 \"value\" : \""+membership.getUtm_medium()+"\"}" );
					}
					
					if(membership.getOrigin() != null && membership.getOrigin().trim().length() > 0){
						properties .append(  ",{\"property\": \"campaign\" , 		 \"value\" : \""+membership.getOrigin()+"\"}" );
					}
				
					properties .append(  ",{\"property\": \"area\" , 		 \"value\" : \""+membership.getPerson().getProspectus().getArea()+"\"}" );
					
					properties .append(  ",{\"property\": \"tipo_cliente\" , 		 \"value\" : \""+
					
											( membership.getPerson().getProspectus().getArea().toString().equals("L")?"acreditado":membership.getPerson().getProspectus().getArea().toString().equals("I")?"inversionista":"" )
					
											+"\"}" );
					
					Integer l = hs.saveProspectus(properties);
					
					prospectus.setHs_vid(l);
					
					prospectusService.update( prospectus );
					
			 }
			 
			 return true;
					
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
		
	
	// return true;
	}

private String validaUTMPartner( String partner_str ){
	
	String res = "";
	
	UtmPartnerConversion utmp = reasonsService.existUTM(partner_str);
	
	if( utmp != null ){
		res = utmp.getPartner_id();
	}
	
	return res;
	
}
	
	
}
