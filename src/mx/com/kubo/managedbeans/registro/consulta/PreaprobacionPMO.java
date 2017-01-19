package mx.com.kubo.managedbeans.registro.consulta;

import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Prospector;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

public abstract class PreaprobacionPMO extends PreaprobacionDMO
{
	protected void procesar_respuesta_SGB() 
	{		
		if(response.getStatus().equals("0"))
		{				
			consultaBuro = true;
			
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.getProspectIDProvider: "+response.getMessage());
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(2);
	
			servicecallingService.saveServiceCall(srvCall);
			
			bur_sol_num = response.getSolNum();
			
			//TODO
			enviaHS( true );
			
			score = new Scoring();
			prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
			company_id    = credithistory.getCreditHistoryPK().getCompany_id();
			score.setProspectus_id(prospectus_id);
			score.setCompany_id(company_id);
			score.setMx_folio(response.getBurFol());
			score.setBc_score(response.getBurScore());
			score.setResult_datetime(new Date());
			score.setMx_solicitud_buro(bur_sol_num);
			score.setCci_score(response.getIcc());
	
			if(Integer.parseInt( score.getBc_score()) < 0 )
			{
				displayGreen = true;
				displayBlue  = false;
			} else if(Integer.parseInt( score.getBc_score()) >= 652 ) {
				displayGreen = false;
				displayBlue  = true;
			} else {
				displayGreen = false;
				displayBlue  = false;
			}
			
			cv2 = new ConvertCalendar(new Long(response.getBurDate()));
			
			score.setBc_score_date(cv2.getDate());
			
			scoringService.saveScoring(score);
			
			if( excecuteJSF ){
			
				faces     = FacesContext.getCurrentInstance();
				elContext = faces.getELContext();
				resolver  = faces.getApplication().getELResolver();
	
				navigation = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
				
				navigation.setHasValidScore(true);
				
				navigation.setFlagBCScore(true);
			
			}
			
			success_OK = true;
			
			if(membership.getUrl_origin() != null ){
				
				String url = membership.getUrl_origin();
				
				if( url.indexOf("hid=") > -1 ){
					
					hid_value = url.substring(  (url.indexOf("hid=")+4) );
					
					if( hid_value.length() > 0 ){
						
						hid_flag = true;
					}
					
				}
				
			}
			
			updateProspector();
			
			if(excecuteJSF){
			updateAccess();
			}
			
		} else if(response.getStatus().equals("-2")||response.getStatus().equals("-3")||response.getStatus().equals("-4")||
				   response.getStatus().equals("-5")||response.getStatus().equals("-6")||response.getStatus().equals("-7")||
				   response.getStatus().equals("-8"))
		{
			
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setException(response.getMessage());
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//TODO
			enviaHS( false );
			
			/*
			if(resbur.getStatus().equals("-1")){
				(-1) ERROR KUBO-SGB: Prospecto no encontrado
			}*/
	
			if(response.getStatus().equals("-2"))
			{
				//(-2) ERROR CLIENTE: No autenticado.
				/*setErrorMsg("El prospecto no fue autenticado, significa que alguna de las siguientes preguntas están mal:" +
						"¿Tiene tarjeta de crédito?		En caso de q tenga tarjeta, ¿Cuáles son los últimos 4 dígitos de su tarjeta?" +
						"¿Tiene o ha tenido crédito hipotecario?" +
						"¿Tiene o ha tenido crédito automotriz?");
						*/
				
				
				
					credit_company_name    = credithistory.getCredit_company_name();
					is_this_credit_company = (credit_company_name != null && credit_company_name.trim().toUpperCase().indexOf( "LIVERP" )  != (-1)) 
										   ||(credit_company_name != null && credit_company_name.trim().toUpperCase().indexOf( "PALACIO" ) != (-1))
										   ||(credit_company_name != null && credit_company_name.trim().toUpperCase().indexOf( "HIERRO" )  != (-1));
					
					
					
					if (is_this_credit_company)
					{													
						msgStr += "<li><b>Si tu tarjeta es Liverpool&reg; o Palacio de Hierro&reg;, verifica por favor que sea VISA o MASTERCARD. </b> En caso contrario, intenta con otra tarjeta. </li>";													
					}
							
							
					msgStr += "<li>Recuerda que el hecho de que tu Tarjeta de Crédito esté en ceros, no implica que está cancelada.  Si no has cancelado tu tarjeta de crédito, por favor intenta de nuevo indicando tu los últimos cuatro dígitos de dicha tarjeta de crédito.</li>"
							+ "</ul>"
							+ "Si estás seguro de que tu información es correcta, por favor ponte en contacto con nuestro centro de contacto, al teléfono <b>"
							+ recurso.getString("Kubo_telefono")
							+ "</b> o al correo <b>soporte@kubofinanciero.com</b>";
					
					setErrorMsg(msgStr);
					
				
					
			}
	
			if(response.getStatus().equals("-3"))
			{
				setErrorMsg(" Información errónea: no coinciden domicilios (CP, delegación, estado)");
			}
			
			if(response.getStatus().equals("-4"))
			{
				setErrorMsg("Error en el nombre, acentos, eñes, etc.");
			}

			//ACCION-SOPORTE TÉCNICO SGB
			if(response.getStatus().equals("-5"))
			{
				setErrorMsg(" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}
			
			if(response.getStatus().equals("-6"))
			{
				//(-6) ERROR SGB: Jar no encontrado.
				setErrorMsg(" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}

			//ACCION-SOPORTE TÉCNICO SGB/BURO
			if(response.getStatus().equals("-7"))
			{
				//(-7)ERROR BURO: Error en el sistema buró de crédito
				setErrorMsg(" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}
			
			if(response.getStatus().equals("-8"))
			{
				//(-8)ERROR SGB-BURO: Usuario/Password incorrectos
				setErrorMsg(" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
				
				Evento evento = Evento.ERROR_DESARROLLO;
				evento.setError("managedbeans.Preaprobacion.callWSSGB(532): (-8)ERROR SGB-BURO: s/Password incorrectos");
				notificar(evento, null,"Sin conexión con buro de crédito.  <br /> Usuario/Password incorrectos",null);													
			}
			
			setSuccess(false);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(true);
		} else if(response.getStatus().equals("-1.1")){
			setErrorMsg(" Faltan datos por proporcionar, Fecha de nacimineto, curp, rfc, calle, colonia, número exterior, número interior, etc. ");
			setSuccess(false);
		
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(true);
		} else {
			Evento evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.Preaprobacion.callWSSGB(550):"+response.getStatus() + " - "+ response.getMessage());
			notificar(evento, null, response.getStatus() + " - "+ response.getMessage(),null);
			
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setException(response.getStatus() + " - "+response.getMessage());
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			servicecallingService.saveServiceCall(srvCall);
			
			setSuccess(false);
			setWait(false);
			setFail(false);
			setError(true);
			setNoHit(false);									
		}
	}
	
	private void updateProspector(){
		
		if( naturalPerson.getSafi_client_id() == null || naturalPerson.getSafi_client_id().trim().length() == 0 ){
		
			Prospector prospector = prospector_service.getMaxProspector(sesion.getProspectus_id(), sesion.getCompany_id());
			
			prospector.setIs_processed("S");
			
			prospector_service.updateProspector(prospector);
			
		}
		
	}
	
	private void updateAccess(){
		
		Access access = new Access();
		
		System.out.println( "Preaprobacion insertando screen 3" );
		
		access.setCompany_id( sesion.getCompany_id() );
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(3);
		access.setPercentage(100);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);
		
	}
	
	protected void notificar(Evento evento, Scoring score, String errormsg, ProyectLoan proyect_loan )
	{		
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);
			notificador.notificar(evento, score, proyect_loan, errormsg);
			
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}				
	}
	
	protected void enviaHS( boolean esAutenticado ){
		
		if( naturalPerson != null && naturalPerson.getProspectus() != null && naturalPerson.getProspectus().getHs_vid() != null ){	
			
			SystemParamPK system_param_PK_I = new SystemParamPK();
			
			system_param_PK_I.setCompany_id( 1 );
			system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			 SystemParam system_param_I = systemParamService.loadSelectedSystemParam(system_param_PK_I);
			
			if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
				
				HubSpotController hs =  new HubSpotController();
				
				StringBuilder properties = new StringBuilder();
				
				if( esAutenticado ){
				
				properties.append("{ \"property\" : \"tiene_id_provider\" , \"value\" : \"Si\"}," +
									"{ \"property\" : \"consulto_buro\" , \"value\" : \"Si\"}"
									);
				
				}else{
					
					properties.append("{ \"property\" : \"tiene_id_provider\" , \"value\" : \"No\"}," +
							"{ \"property\" : \"consulto_buro\" , \"value\" : \"No\"}"
							);
					
				}
				
					hs.updateProspectus(naturalPerson.getProspectus().getHs_vid(), properties);
				
				
						
						
			 }
			
		}
		
		
	}
}
