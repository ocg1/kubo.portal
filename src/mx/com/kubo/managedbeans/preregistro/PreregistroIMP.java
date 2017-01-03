package mx.com.kubo.managedbeans.preregistro;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.managedbeans.portal.IniciaSession;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.GeneradorCodigos;
import mx.com.kubo.tools.Utilities;

@ManagedBean (name = "addNaturalPerson") 
@ViewScoped 
public final class PreregistroIMP extends PreregistroPMO
implements PreregistroIMO, Serializable
{
	private static final long serialVersionUID = 9012042728194467040L;

	@PostConstruct
	public void init()
	{					
		init_session();	
		
		init_session_flags();
		
		init_request_map();
		
		init_natural_person();	
		
		init_registrattion_reason();
		
		init_system_param();
		
		init_suscribe();
		
		init_medium();
							
		lista_security_question = securityQService.getSecurityQuestionList();
	}
		
	public final void listener_codigo_postal(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		ajax_input_text = (HtmlInputText) evento.getComponent();	
		
		codigo_postal = (String) ajax_input_text.getValue();
		
		//System.out.println("PreregistroIMP.listener_codigo_postal(): " + codigo_postal);
		
		zona_cobertura_ENABLED = false;
		codigo_postal_OK       = false;
		
		if(codigo_postal != null && codigo_postal.length() > 0)
		{
			lista_colonias = service_employment.getAsentamientosByCP(codigo_postal);
			
			if(lista_colonias != null && lista_colonias.size() > 0)
			{
				codigo_postal_OK = true;
				
				colonia = lista_colonias.get(0);
				
				colonia_id     = colonia.getNeighborhoodCatPK().getNeighborhood_id();
				estado_ENABLED = colonia.getDelegMunicipio().getEstados().getIs_enabled();
				town_name      = colonia.getDelegMunicipio().getName();
				town_id        = colonia.getDelegMunicipio().getTownCatPK().getTown_id();
				state_name     = colonia.getDelegMunicipio().getEstados().getName();
				state_id       = colonia.getDelegMunicipio().getEstados().getStateCatPK().getState_id();
								
				if(estado_ENABLED.equals("S"))
				{
					zona_cobertura_ENABLED = true;
					
				} else {
					
					zona_cobertura_ENABLED = false;
				}														
			}				
		}
		
		request.addCallbackParam("codigo_postal_OK",  codigo_postal_OK);
		request.addCallbackParam("zona_cobertura_ENABLED", zona_cobertura_ENABLED);
	}
	
	public void listener_valida_FB_id(AjaxBehaviorEvent evento)
	{				
		request = RequestContext.getCurrentInstance();
		
		ajax_input_text = (HtmlInputText) evento.getComponent();
		
		fb_id = (String) ajax_input_text.getValue();
		
		//System.out.println("PreregistroIMP.listener_valida_FB_id(): fb_id: " + fb_id );
		
		init_membership_by_fb_id();			
		
		//System.out.println("PreregistroIMP.listener_email(): " + email + " " + email_OK);							
	}
	
	public final void listener_email(AjaxBehaviorEvent evento)
	{				
		request = RequestContext.getCurrentInstance();
		
		ajax_input_text = (HtmlInputText) evento.getComponent();
		
		email = (String) ajax_input_text.getValue();
		
		validar_email();
		procesar_email();				
		
		//System.out.println("PreregistroIMP.listener_email(): " + email + " " + email_OK);							
	}
	
	public final void listener_email_confirmar(AjaxBehaviorEvent evento)
	{
		log.info("Confirmando Mail ");
		
		if(email != null && confMail!= null && email.length() > 0 && confMail.length() > 0)
		{
			if(email.equals(confMail))
			{
				displayValConfMail     = "block";
				imgValConfMail         = "<img src='../resources/img/check.png' />";
				displayWarningConfMail = ("none");
				warningConfMail        = "<script>"
									   + "$('#confemail').removeClass('requiredClass');"
									   + "varConfEmail = true; alertaQuitar ('#confemail');"
									   + "</script>";
				
			} else {
				
				//log.info("Confirmacion No Valida: " + password + "  -  "+confPass);
				
				displayValConfMail     = "block";
				imgValConfMail         = "<img src='../resources/img/warning.png' width='20px' height='20px' />";
				displayWarningConfMail = "block";
				warningConfMail        = "<script>"
									   + "$('#confemail').addClass('requiredClass');"
									   + "varConfEmail = false;"
									   + "$('#confemail').val('');   "
									   + "alerta ('Error al confirmar tu correo electrónico', '#confemail');"
									   + "$('#confemail').removeClass('requiredClass');"
									   + "</script>";
			
				}
			}else{
				log.info("confMail vacio");
				displayValConfMail     = ("none");
				displayWarningConfMail = ("none");
				warningConfMail = "<script>"
								+ "$('#confemail').removeClass('requiredClass');"
								+ "varConfEmail = false; alertaQuitar ('#confemail');"
								+ "</script>";
			}		
	}
	
	public final String addNewNaturalPersonLoan(){
		
		faces   = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		context  = faces.getELContext();		
		resolver = faces.getApplication().getELResolver();
		
		session = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		session.setArea('L');
		
		area = 'L';
		
		return addNewNaturalPerson();
		
	}
	
	public final String addNewNaturalPersonInv(){
		
		faces   = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		context  = faces.getELContext();		
		resolver = faces.getApplication().getELResolver();
		
		session = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		session.setArea('I');
		
		area = 'I';
		
		return addNewNaturalPerson();
		
	}
	
	
	public final String addNewNaturalPerson() {	
		exitoGuardarProspecto = false;
		flagActiveCode        = false;
		
		prospectus_id = 0;				
		
		navigationRule = null;    
		prospectus     = null;
		
		if( codigo_postal == null || codigo_postal.trim().length() == 0 ){
			
			zona_cobertura_ENABLED = true;
			codigo_postal_OK       = true;
			
		}
		
		request = RequestContext.getCurrentInstance();
		
		if( request != null ){
			request.addPartialUpdateTarget("pnlScript");
		}
		/*		 	
		faces   = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		context  = faces.getELContext();		
		resolver = faces.getApplication().getELResolver();
		
		session = (SessionBean) resolver.getValue(context, null, "sessionBean");
	    */
		faces_msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct"); 
		
	    faces.addMessage(null, faces_msg);  
	    
		user_agent           = external.getRequestParameterMap().get("user_agent");
		device_info          = external.getRequestParameterMap().get("device_info");
	
		session.setUser_agent(user_agent);
		session.setDevice_info(device_info);
		
		
	    asignar_natural_person();
	    		
		procesar_pre_registro();
		/*
		System.out.println( " \n\n " );
		
		System.out.println( "------------------------------------------------" );
		System.out.println( "------------------------------------------------" );
		System.out.println( "PREREGISTRO: " +  pre_registro_OK );
		System.out.println( "------------------------------------------------" );
		System.out.println( "------------------------------------------------" );
		
		System.out.println( " \n\n " );
		*/
		if(pre_registro_OK)
		{
			return NavigationRule.PREREGISTRO.toString();
		}
		
		inicioValues = (InicioValuesIMP) resolver.getValue(context, null, "inicioValues");
		
		if( promotorID == null && inicioValues.getPromotorID() != null ){
			
			 promotorID = inicioValues.getPromotorID() ;
			
		}
		
		if( promotorID != null){
		
			if( Utilities.isNumeric(promotorID) ){
			
				codeID =  promotorID ;
				
				getPromotorByCodeID_portal();
			
			}
			
		}
		
		add_membership();
		add_prospectus();					
				
		if(exitoGuardarProspecto)
		{	
			if( isNumeric( getRefered_search() ) ){
			
				if( ( registration_reason_id == 3 )  &&  ( (utm_partner_id != null && (utm_partner_id.equals("INVITEDBY") ||  utm_partner_id.equalsIgnoreCase("REC") ) ) ||( promotor_id != null && promotor_id != 0 ))  )
				{
					add_recomendado(prospectus_id, 1);
				}
			
			}
			
			//add_phone();
			add_password();
			add_investor();		
			add_address();									
			
			if(!exitoGuardarProspecto)
			{				
				return NavigationRule.TRACKING_PAGE.toString();
			}
		}
	
		add_activation_code();
		update_membership();
		
		if(!exitoGuardarProspecto) {
			return NavigationRule.ACTIVATION_CODE_PAGE.toString();
		} 
				
		notificar_evento();
		
		if(!notificar_evento_OK)
		{
			return NavigationRule.ERROR_ENVIO_NOTIFICACION.toString();										
		}
		
		if(zona_cobertura_ENABLED || area.equals('I'))
		{
			asignar_navigation_rule();
			
		} else {
			
			session.setTemporalUser(natural_person.getFirst_name() + " " + natural_person.getMiddle_name());
			navigationRule = NavigationRule.ZIPCODE_COVERAGE_ZONE;
			
			
			session = (SessionBean) resolver.getValue(context, null, "sessionBean");
			session.setIsnew("S");
			
//			System.out.println( " ===================================================== " );
//			System.out.println( " ===============isNew="+session.getIsnew()+"=============================== " );
//			System.out.println( " ===================================================== " );
			
			
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
							
							infusion.addTAgToContact( contactId , 285 ); // Tag Zona Fuera de Cobertura
					
							
					 }
					 
					
				 
				}catch( Exception e ){
					e.printStackTrace();
				}
				 
			}
			
		}
		
		context  = faces.getELContext();		
		resolver = faces.getApplication().getELResolver();
		
		session = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		System.out.println( "\n\n\n" );
		
		System.out.println( session.getProspectus_id() + " " + session.getEmail() + " " + session.getArea() );
		
		System.out.println( "\n\n\n" );
		
		return navigationRule.toString();				
	}

	public String addNewNaturalPerson_portal()
	{	
		exitoGuardarProspecto = false;
		flagActiveCode        = false;
		
		prospectus_id = 0;	
		
		navigationRule = null;    
		
		request = RequestContext.getCurrentInstance();
		request.addPartialUpdateTarget("pnlScript");
		
		faces = FacesContext.getCurrentInstance();  	
		context  = faces.getELContext();		
		resolver = faces.getApplication().getELResolver();
		
		session = (SessionBean) resolver.getValue(context, null, "sessionBean");
	    
		faces_msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
	    faces.addMessage(null, faces_msg);  
		
	/*
		if(!validaAliasFormat(getNickname()))
		{
			log.info("validaAlias");
			return "preregistro";
		}
	
	*/

	    asignar_natural_person();
	    
		procesar_pre_registro();
		
		if(pre_registro_OK)
		{
			return NavigationRule.PREREGISTRO.toString();
		}
		
		add_prospectus();
		add_membership();
	
		membership.setEmail_verified("N");
			
		exitoGuardarProspecto = prospectusService.saveProspectAndNaturalPersonAndMembership(prospectus, natural_person, membership);
		
		prospectus_id = prospectus.getProspectusPK().getProspectus_id();
	
		if(exitoGuardarProspecto)
		{		
			if( registration_reason_id == 3 && promotor_id != null && promotor_id != 0 )
			{
				add_recomendado(prospectus_id, 1);
			}
		
			int company_id = prospectus.getProspectusPK().getCompany_id();
			
			phonePK  = new PhonePK(prospectus_id, company_id);
			newPhone = new Phone();
		
			newPhone.setPhonePk(phonePK);
			newPhone.setPhone_type_id(6);
			newPhone.setPhone_number( phoneCellProspectus );
			newPhone.setOwned("1");
			newPhone.setArea(prospectus.getArea());
			
			phoneService.addPhone(newPhone, prospectus_id, company_id);
			
			PasswordHistoryPK pssPk = new PasswordHistoryPK();
			PasswordHistory passHis = new PasswordHistory(); 
			
			pssPk.setCompany_id(company_id);			
			pssPk.setProspectus_id(prospectus_id);
								
			passHis.setDate_changed(new Date());
			passHis.setPassword( membership.getPassword() );
			passHis.setPwdhpk( pssPk );
			
			passwordhistoryservice.savePasswordHistory(passHis);
			
			if(getArea().toString().equals("I"))
			{
			
				InvestorPK invPk = new InvestorPK();
			
				invPk.setCompany_id( membership.getMembershipPK().getCompany_id());
				invPk.setProspectus_id(membership.getMembershipPK().getProspectus_id());
				
				Investor inv = new Investor();
				inv.setDate_approved(null);
				inv.setDate_published(null);
				inv.setPk(invPk);
				inv.setStatus_id(0);
			
				
				if(investorservice.addInvestor(inv)){}
					/*{
					System.out.println("Inversionista Dado de alta: "+membership.getMembershipPK().getProspectus_id());
					
				} else {
				
					System.out.println("Error Alta Inversionista: "+membership.getMembershipPK().getProspectus_id());
				}
				*/
			}
			
			try
			{
				log.info("generate prospectus Tracking_Id");
				
				tracking_id = GeneradorCodigos.get_tracking_id(COMPANY_ID, prospectus_id);
				
				prospectus.setTracking_id(tracking_id);
				
				prospectusService.update(prospectus);
				
				exitoGuardarProspecto = true;
				
			} catch(Exception e) {
				try
				{
					log.info("Error al generar Tracking_Id intentando de nuevo");
					
					Thread.sleep(500);
					
					prospectusService.update(prospectus);
					
					exitoGuardarProspecto = true;
					
				} catch(Exception e1) {
					log.info("Error al generar Tracking_Id ");
					exitoGuardarProspecto = false;
					return "trackingPage";
				}
			}
		}
	
		activation_code = GeneradorCodigos.get_activation_code(prospectus_id, COMPANY_ID );
		
		MembershipPK newMembershipPK = new MembershipPK(prospectus_id, COMPANY_ID);
		Membership member            = membershipService.getMembershipById(newMembershipPK);
				
		member.setActivation_code(activation_code);
						
		try
		{
			//ActivationCode			
			log.info("NEW PROSPECTID= "+ prospectus_id);			
			log.info("Create Activation code");
			
			membershipService.update(member);
			
		} catch(Exception e) {
			try
			{
				log.info("Error al generar Activation code intentando de nuevo");
				Thread.sleep(500);
				prospectusService.update(prospectus);
						exitoGuardarProspecto = true;
				
		
			} catch(Exception e1) {
		
	
			log.info("Error al generar Activation code");
					exitoGuardarProspecto = false;
					navigationRule        = NavigationRule.ACTIVATION_CODE_PAGE;
					
					return navigationRule.toString();
			}
		}
	
		if(exitoGuardarProspecto)
		{
			try
			{												
				log.info("Begin send mail");
				log.info("enviando a " +email);
								
				if((getArea() != null && getArea().toString().equals("L") ) || 
				   (getArea() != null && getArea().toString().equals("I")   && 
					isActiveInvestmentSection.equals("S")))
				{	
					try
					{
						notificador = new NotificadorIMP();
						notificador.setEmisor(member);
						notificador.notificar(Evento.REGISTRO_USUARIO);
						
						navigationRule = NavigationRule.WAIT_CONFIRMATION;																	
						
						flagActiveCode = true;
						
						if( member.getRegistration_reason_id() != null && member.getRegistration_reason_id() == 8 &&  
							member.getPriceshoes_number()      !=null  && member.getPriceshoes_number().length() > 0 )
						{
							notificador = new NotificadorIMP();
							notificador.setEmisor(member);
							notificador.notificar(Evento.REGISTRO_SOCIO_PARTNER);							
						}		
													
					} catch(NotificacionException e) {
						e.printStackTrace();
						return NavigationRule.ERROR_ENVIO_NOTIFICACION.toString();
					}
				
				} else if(getArea() != null && getArea().toString().equals("I")) {					
					try 
					{
						notificador = new NotificadorIMP();
						notificador.setEmisor(member);
						notificador.notificar(Evento.PREREGISTRO_INVERSIONISTA);
						
						flagActiveCode = true;
						
					} catch (NotificacionException e) {
						
						e.printStackTrace();
						
						flagActiveCode = false;
					}					
				}				
			} catch(Exception e) {
				log.info("fallo al enviar mail");
				e.getMessage();
				flagActiveCode = false;
			}
		}				
	
		if(flagActiveCode)
		{						
		String nombre = natural_person.getFirst_name() ;
		
		if(natural_person.getMiddle_name() != null && natural_person.getMiddle_name().trim().length() > 0)
		{
			nombre += " " +natural_person.getMiddle_name();
		}
		
		session.setNombre(nombre);
		session.setEmail(email);
		
		if(session!= null && session.getArea() != null && session.getArea().toString().equals("I") && isActiveInvestmentSection.equals("N") )
		{			
			navigationRule =  NavigationRule.WAIT_INVESTMENT;
			
		}else{
				navigationRule =  NavigationRule.WAIT_CONFIRMATION;
			}
				
	//		} else if(session.getPartner()==null || registration_reason_id != 7) 
	//			{
	//				navigationRule =  NavigationRule.WAIT_CONFIRMATION;
	//				
	//			} else if(loginService.verifySession(session, member.email,getPassword())) 
	//				{					
	//						inicializaSimulador(false);
	//						navigationRule = NavigationRule.REGISTRAR;					
	//				
	//				} else 
	//					{				
	//						navigationRule = NavigationRule.ERROR_PRERGISTRO;
	//					}
			
			if(navigationRule.toString().equals(NavigationRule.WAIT_CONFIRMATION.toString())){
			
				member.setIs_active(ACTIVO);
				membershipService.update( member );
				
//				System.out.println("");
//				System.out.println(" --- ENTRANDO A INICIAR SESSION --- "+membership.getEmail()+"  --- "+password);
//				System.out.println("");
				
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
				
				session = (SessionBean) resolver.getValue(context, null, "sessionBean");
				session.setIsnew("S");
				
//				System.out.println( " ===================================================== " );
//				System.out.println( " ===============isNew_Portal="+session.getIsnew()+"=============================== " );
//				System.out.println( " ===================================================== " );
				
				if( session.getArea().toString().equals("I") )
				{
					return NavigationRule.BIENVENIDA_INVERSION.toString();
				
				}else
				{
					return NavigationRule.BIENVENIDA_ACREDITADO.toString();
				}
				
			}else{
				
				return navigationRule.toString();
			}
	
		} else {
			log.info("Error en codigo de activacion");
			navigationRule = NavigationRule.ERROR_PRERGISTRO;
			return navigationRule.toString();
		}		
	}
	
	@SuppressWarnings("finally")
	public String forwardMailAction_portal()
	{			
		Membership  member;
		String      rule = "";
		
		ELContext   elContext;
		SessionBean session;
		
		try 
		{
			member = membershipService.getMembershipByEmail(getForwardMail());
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(member);
			notificador.notificar(Evento.REENVIO_MAIL_CONFIRMACION);
			
			elContext = FacesContext.getCurrentInstance().getELContext();
			session   = (SessionBean) FacesContext.getCurrentInstance()
				    .getApplication()
				    .getELResolver()
				    .getValue(elContext, null, "sessionBean");
			
			session.setNombre(member.getPerson().NombreCompletoNPM());
			session.setEmail(member.getEmail());
			
			rule = NavigationRule.SEND_MAIL.toString();
		
			
		} catch (NotificacionException e) {			
			rule = NavigationRule.ERROR_PRERGISTRO.toString();
		} finally {
			return rule;
		}
	}
	
	@SuppressWarnings("finally")
	public String forwardMailAction()
	{			
		Membership  member;
		String      rule = "";
		
		ELContext   elContext;
		SessionBean session;
		
		try 
		{
			member = membershipService.getMembershipByEmail(getForwardMail());
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(member);
			notificador.notificar(Evento.REENVIO_MAIL_CONFIRMACION);
			
			elContext = FacesContext.getCurrentInstance().getELContext();
			session   = (SessionBean) FacesContext.getCurrentInstance()
				    .getApplication()
				    .getELResolver()
				    .getValue(elContext, null, "sessionBean");
			
			session.setNombre(member.getPerson().NombreCompletoNPM());
			session.setEmail(member.getEmail());
			
			rule = NavigationRule.CONFIRMATION_MAIL.toString();
			
		} catch (NotificacionException e) {			
			rule = NavigationRule.ERROR_PRERGISTRO.toString();
		} finally {
			return rule;
		}
	}
	
	public void onItemReferredSelect(SelectEvent event) 
	{		
	  //System.out.println("Seleccionar Persona que lo recomendo: " +  event.getObject().toString());
	  setRefered_search( event.getObject().toString() );      
	}
	
	public LinkedList<ClientViewFullName> completeinfoclientRefered(String strSearch)
	{
		LinkedList<ClientViewFullName> suggestions = new LinkedList<ClientViewFullName>();
		//Dependiendo del valor del checkBox sera la busqueda.
		//1:Por nombre
		
			suggestions = clientViewFullNameService.getListAllUserForReferred(strSearch);
		
		
		return suggestions;		
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	
}
