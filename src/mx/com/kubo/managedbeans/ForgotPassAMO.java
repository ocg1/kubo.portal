package mx.com.kubo.managedbeans;

import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.model.TempPassword;
import mx.com.kubo.model.TempPasswordPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.Utilities;

public abstract class ForgotPassAMO extends ForgotPassDMO
{	
	protected void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + membership.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_CAMBIO_PASSWORD);
			request_notificar_config.setCalled_FROM("ForgotPassAMO.saveNewPass()");	
			request_notificar_config.setCompany_id(company_id + "");
			request_notificar_config.setProspectus_id(prospectus_id + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("ForgotPassAMO.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void asignar_email_OK()
	{
		if( email != null ){
			is_email_OK = Utilities.validaMail(email);
			
			if(is_email_OK)
			{
				asignar_datos_membership();
				asignar_failed_question_attempts();
				
			} else {
				
				is_email_OK = false;
					
				errorMsg = "Formato de correo electrónico incorrecto.";			
			}			
				
			if(is_email_OK && membership == null)
			{				
				validMail   = false;
				is_email_OK = false;
				
				errorMsg  = "El correo electrónico no existe.";				
			}
			
			if(is_email_OK && !is_membership_active) {
				
				is_email_OK = false;
				
				errorMsg = "La cuenta " + email + " no esta activa. Revisa tu correo electrónico(incluyendo correo no deseado, spam)";						
				
			} else {			
				
				is_email_OK = true;				
			}	
		
		}
	}
	
	protected void asignar_datos_membership() 
	{
		membership = membershipService.getMembershipByEmail(email);
		
		if(membership != null)
		{
			prospectus_id            = membership.getMembershipPK().getProspectus_id();
			company_id               = membership.getMembershipPK().getCompany_id();			
			seqQuest                 = membership.getSecurity_question_id();			
			security_question_id     = membership.getSecurity_question_id();			
			answer                   = membership.getAnswer();			
			is_membership_active     = membership.getIs_active().equals(1);
			is_membership_not_active = membership.getIs_active().equals(0);
			failed_question_attempts = membership.getFailed_question_attempts();			
		} 					
	}
	
	protected void asignar_failed_question_attempts() 
	{				
		if(failed_question_attempts < max_number_attempts)
		{		
			max_number_attempts_reached = false;
			
		} else {
			
			max_number_attempts_reached = true;
			panel_opciones_ENABLED      = false;
			changePass 					= false;
			errorMsg                    = null;
		}				
	}
	
	protected void asignar_pregunta_seguridad() 
	{								
		if(panel_opciones_ENABLED)
		{			
			asignar_security_question();			
						
		} else {
			
			asignar_panel_cambiar_password();			
			asignar_next_question_pool();																								
		}					
	}
			
	private void asignar_security_question() 
	{
		if(is_membership_active && !max_number_attempts_reached)
		{																		
			natural_person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
			natural_person    = naturalPersonService.getNaturalPersonById(natural_person_PK);
											
			name = natural_person.getFirst_name();
												
			lista_security_question_pool = secquestpoolservice.loadSecQuestPoolByProspect(prospectus_id, company_id);
			
			if(lista_security_question_pool == null || lista_security_question_pool.size() == 0)
			{									
				displayQuestion = true;									
			}
			
			security_question  = membership.getSecurityQuestion().getQuestion();
			panel_opciones_ENABLED = false;
			validMail     = true;
			errorMsg      = null;
															
			registar_bitacora_access();
						
		} else if(is_membership_not_active) {
			
			errorMsg = "La cuenta " + email + " no esta activa. Revisa tu correo electrónico(incluyendo correo no deseado, spam)";
			
		} else {
			
			errorMsg = "No tenemos registrada ninguna pregunta de seguridad en esta cuenta, por favor comuníquese con nosotros";
			
		}	
	}

	private void registar_bitacora_access() 
	{
		browser_name     = external.getRequestParameterMap().get("browser_name");
		browser_version  = external.getRequestParameterMap().get("browser_version");
		browser_OS       = external.getRequestParameterMap().get("browser_OS");						
		browser_width    = external.getRequestParameterMap().get("browser_width");
		browser_height   = external.getRequestParameterMap().get("browser_height");
		device_info      = external.getRequestParameterMap().get("device_info");
		user_agent       = external.getRequestParameterMap().get("user_agent");
		
		InicioValuesIMP inicio = (InicioValuesIMP) resolver.getValue(context, null, "inicioValues");
		
		ipAddressClient  = servlet_request.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)
		{
	    	ipAddressClient = servlet_request.getRemoteAddr();
		}
		
		access = new Access();
		
		access.setScreen_id(SCREEN_ID_FORGOT_PASS);
		access.setPercentage(0);
		
		access.setCompany_id          (company_id);
		access.setProspectus_id       (prospectus_id);
		access.setProspectus_id_viewed(prospectus_id);		
		
		access.setWeb_browser         (browser_name);
		access.setWeb_browser_version (browser_version);
		access.setOp_system           (browser_OS);
		access.setUrl_access( inicio.getUrl_value() );
		// access.setVertical_size(Integer.parseInt(browser_height));
		// access.setHorizontal_size(Integer.parseInt(browser_width));
		access.setIpaddress(ipAddressClient);
		access.setUser_agent(device_info);
		access.setDevice_info(user_agent);
		
		
		service_access.add(access,true);
		
		System.out.printf("\nForgotPassAMO.registar_bitacora_access(): OK");
	}

	private void asignar_panel_cambiar_password() 
	{
		if(membership != null && security_question != null && !max_number_attempts_reached)
		{
			answer                   = membership.getAnswer();
			security_question_id     = membership.getSecurity_question_id();			
			failed_question_attempts = membership.getFailed_question_attempts();
			prospectus_id            = membership.getMembershipPK().getProspectus_id();
			company_id               = membership.getMembershipPK().getCompany_id();	
						
			security_answer_upper = Utilities.encrypt(answerQuestion.toUpperCase());
			security_answer_lower = Utilities.encrypt(answerQuestion.toLowerCase());
			
			is_security_answer_OK = answer.equals(security_answer_upper) || answer.equals(security_answer_lower);
			
			if(is_security_answer_OK)
			{					
				is_cambio_password_OK = true;
				
				changePass = true;
				errorMsg   = null;
				changePass = true;
				validMail  = false;	
				
			} else {
				
				is_cambio_password_OK = false;
				
				changePass = false;
				errorMsg   = "La respuesta no es correcta. Vuelve a intentarlo.";
	
				failed_question_attempts++;
				
				membership.setFailed_question_attempts(failed_question_attempts);				
				membershipService.update(membership);	
				
				asignar_failed_question_attempts();
				
				System.out.printf("\nForgotPassAMO.asignar_failed_question_attempts(): " + failed_question_attempts);			
			}											
		}			
	}
	
	private void asignar_next_question_pool() 
	{
		if(!displayQuestion && is_cambio_password_OK)
		{					
			security_question_pool_PK = new SecQuestPoolPK();
			
			security_question_pool_PK.setCompany_id(company_id);
			security_question_pool_PK.setProspectus_id(prospectus_id);
			security_question_pool_PK.setSecurity_question_id(security_question_id);
			
			security_question_pool = secquestpoolservice.getSecQuestPoolByPK(security_question_pool_PK);
			
			if(security_question_pool != null)
			{						
				security_question_pool.setDate_used(new Date());
				
				secquestpoolservice.updateSecQuest(security_question_pool);
				
				security_question_pool = secquestpoolservice.getNextRandomSecQuestPool(prospectus_id, company_id);
				
				membership.setSecurity_question_id(security_question_pool.getPk().getSecurity_question_id());							
				membership.setAnswer(security_question_pool.getAnswer());
				
				membershipService.update(membership);
			}
		}	
	}
	
	protected void enviar_password_temporal_portal() 
	{
		if(is_membership_active)
		{						
			panel_opciones_ENABLED = false;
															
			passTemp = Utilities.getRandomCharacter()
					 + Utilities.getRandomName()
					 + Utilities.getRandomCharacter()
					 + Utilities.digitoVerificador(Utilities.getRandomName())
					 + Utilities.getRandomCharacter();
			
			fechaActual = new Date();
			valid_date = Calendar.getInstance();
			valid_date.setTime(fechaActual);
			valid_date.add(Calendar.DATE, 1);							
			
			tempPassPK = new TempPasswordPK();
			tempPassPK.setCompany_id(company_id);
			
			tempPassword =  new TempPassword();		
			
			tempPassword.setTempPassPK(tempPassPK);
			tempPassword.setPassword(Utilities.encrypt(passTemp));
			tempPassword.setProspectus_id(prospectus_id);
			tempPassword.setCreation_date(fechaActual);
			tempPassword.setValid_date(valid_date.getTime());							
			tempPassword.setIs_used('N');
			
			membership.setPassword(passTemp);						
										
			try 
			{
				notificador = new NotificadorIMP();
				notificador.setEmisor(membership);
				notificador.notificar(Evento.PETICION_CAMBIO_PASSWORD);
				
				if(tempPassService.addNewTempPass(tempPassword))
				{
					faces_action = "sendmail";
					
				} else {
					
					System.out.println("Error al guardar el passwork temporal");
				}
				
			} catch (NotificacionException e) {
				
				System.out.print("Error al enviar el correo para restablecer la contraseña");
			}

		} 				
	}
	
	protected void enviar_password_temporal() 
	{
		if(is_membership_active)
		{								
				panel_opciones_ENABLED = false;
																
				passTemp = Utilities.getRandomCharacter()
						 + Utilities.getRandomName()
						 + Utilities.getRandomCharacter()
						 + Utilities.digitoVerificador(Utilities.getRandomName())
						 + Utilities.getRandomCharacter();
				
				fechaActual = new Date();
				valid_date = Calendar.getInstance();
				valid_date.setTime(fechaActual);
				valid_date.add(Calendar.DATE, 1);							
				
				tempPassPK = new TempPasswordPK();
				tempPassPK.setCompany_id(company_id);
				
				tempPassword =  new TempPassword();		
				
				tempPassword.setTempPassPK(tempPassPK);
				tempPassword.setPassword(Utilities.encrypt(passTemp));
				tempPassword.setProspectus_id(prospectus_id);
				tempPassword.setCreation_date(fechaActual);
				tempPassword.setValid_date(valid_date.getTime());							
				tempPassword.setIs_used('N');
				
				membership.setPassword(passTemp);						
											
				try 
				{
					notificador = new NotificadorIMP();
					notificador.setEmisor(membership);
					notificador.notificar(Evento.PETICION_CAMBIO_PASSWORD);
					
					if(tempPassService.addNewTempPass(tempPassword))
					{
						faces_action = "sendmail";
						
					} else {
						
						System.out.println("Error al guardar el passwork temporal");
					}
					
				} catch (NotificacionException e) {
					
					System.out.print("Error al enviar el correo para restablecer la contraseña");
				}

			} 				
		}
	
	protected void guardar_nuevas_respuestas_seguridad() 
	{
		prospectus_id = membership.getMembershipPK().getProspectus_id();
		company_id    = membership.getMembershipPK().getCompany_id();
		
		security_question_pool_PK = new SecQuestPoolPK();
		
		security_question_pool_PK.setCompany_id(company_id);
		security_question_pool_PK.setProspectus_id(prospectus_id);
		security_question_pool_PK.setSecurity_question_id(getSeqQuest());
		
		security_question_pool = new SecurityQuestionPool();
		
		security_question_pool.setAnswer(Utilities.encrypt(answer.toUpperCase()));
		security_question_pool.setDate_used(new Date());
		security_question_pool.setPk(security_question_pool_PK);
		
		secquestpoolservice.saveSecQuest(security_question_pool);
		
		security_question_pool_PK = new SecQuestPoolPK();
		
		security_question_pool_PK.setCompany_id(company_id);
		security_question_pool_PK.setProspectus_id(prospectus_id);
		security_question_pool_PK.setSecurity_question_id(getSeqQuest2());
		
		security_question_pool = new SecurityQuestionPool();
		
		security_question_pool.setAnswer(Utilities.encrypt(answer2.toUpperCase()));
		security_question_pool.setDate_used(null);
		security_question_pool.setPk(security_question_pool_PK);
		
		secquestpoolservice.saveSecQuest(security_question_pool);
		
		security_question_pool_PK = new SecQuestPoolPK();
		
		security_question_pool_PK.setCompany_id(company_id);
		security_question_pool_PK.setProspectus_id(prospectus_id);
		security_question_pool_PK.setSecurity_question_id(getSeqQuest3());
		
		security_question_pool = new SecurityQuestionPool();
		
		security_question_pool.setAnswer(Utilities.encrypt(answer3.toUpperCase()));
		security_question_pool.setDate_used(null);
		security_question_pool.setPk(security_question_pool_PK);
		
		secquestpoolservice.saveSecQuest(security_question_pool);
		
		security_question_pool = secquestpoolservice.getNextRandomSecQuestPool(prospectus_id, company_id);
		
		membership.setSecurity_question_id(security_question_pool.getPk().getSecurity_question_id());
		membership.setAnswer(security_question_pool.getAnswer());
		
		membershipService.update(membership);									
	}
	
	protected void registrar_change_control() 
	{
		changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(prospectus_id);
		changeCtrl.setOriginal_value(membership.getPassword());
		changeCtrl.setNew_value(passNewEncript);
		changeCtrl.setAfected_table("ln_membership");
		changeCtrl.setField("password");
		changeCtrl.setComments("El usuario modificó su contraseña");
		changeCtrl.setIp(ipAddress);
		
		is_change_control_OK = changeControlService.addChangeControl(changeCtrl, prospectus_id, company_id);
			
	}
}
