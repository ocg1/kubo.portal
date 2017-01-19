package mx.com.kubo.managedbeans.registro.consulta;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.controller.behaviorProspectus.BehaviorCheck;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMP;
import mx.com.kubo.tools.Utilities;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

public abstract class PreaprobacionAMO extends PreaprobacionPMO
{		
	protected void init_prospectus() 
	{
		prospectusPK    = new ProspectusPK();
		prospectusPK.setCompany_id(company_id);
		prospectusPK.setProspectus_id(prospectus_id);
			
		prospectus = prospectusService.getProspectusById(prospectusPK);
		
		if(prospectus != null)
		{
			   i = 1;
			flag = true;
		}
	}
	
	protected void init_credit_history() 
	{
		if(flag)
		{
			creditHistoryPK = new CreditHistoryPK();
			creditHistoryPK.setCompany_id(company_id);
			creditHistoryPK.setProspectus_id(prospectus_id);
			
			credithistory = credithistoryService.getCreditHistoryById(creditHistoryPK);
			
			if(credithistory != null)
			{
				   i = 2;
				flag = true;
				
			} else {
				
				flag = false;
			}
		}
	}
	
	protected void init_person() 
	{
		if(flag)
		{
			naturalPersonPK = new gnNaturalPersonPK();
			naturalPersonPK.setCompany_id(company_id);
			naturalPersonPK.setProspectus_id(prospectus_id);
			
			naturalPerson = naturalPersonService.getNaturalPersonById(naturalPersonPK);
			
			if(naturalPerson != null)
			{
				blocked = new PersonaBloqueadaIMP();
				blocked.setSesion(sesion);
				blocked.setPerson(naturalPerson);
				blocked.init();
				
				blocked_person_ENABLED = blocked.isBlocked_person_ENABLED();
				
				if(blocked_person_ENABLED)
				{
					flag = false;
					wait = false;
					
				} else {
					
					i = 3;
					flag = true;
				}
				
				
			} else {
				
				flag = false;
			}
		}
	}
	
	protected void init_address() 
	{
		if(flag)
		{
			thisAddress = addressService.getMaxAddressByType(naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id(), 1);
			
			if(thisAddress != null)
			{
				i = 4;
				
				boolean flagNumber = false;
				
				if(thisAddress.getAddress_number() != null && thisAddress.getAddress_number().length() > 0)
				{
					flagNumber = true;
				}
				
				if(thisAddress.getApt_number() != null && thisAddress.getApt_number().length() > 0) {
					flagNumber = true;
				}
				
				if(thisAddress.getMx_manzana() != null && thisAddress.getMx_manzana().length() > 0) {
					flagNumber = true;
				}
				
				if(thisAddress.getMx_lote() != null && thisAddress.getMx_lote().length() > 0) {
					
					flagNumber = true;
				}
				
				if(!flagNumber)
				{
					thisAddress.setAddress_number("SN");
					addressService.update(thisAddress);
				}
				
				flag = true;
				
			} else {
				
				flag = false;
			}
		}
	}
	
	protected void init_phone() 
	{
		if(flag)
		{
//			thisPhoneFixed = phoneService.getPhoneByTypeByArea(naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id(), 5,sesion.getArea());
//			
//			if(thisPhoneFixed !=  null)
//			{
				i = 5;
				flag = true;
//			} else {
//				flag = false;
//			}
		}
	}
	
	protected void init_membership() 
	{
		if(flag)
		{
			membership_PK = new MembershipPK(); 
			membership_PK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			membership_PK.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			
			membership = membershipservice.getMembershipById(membership_PK);
			
			if(membership !=  null)
			{
				init_promotor_id();
				init_nota_del_coach();				
				
				   i = 6;
				flag = true;
				
			} else {
				flag = false;
			}
		}
	}
	
	protected void init_credit_history_DATA() 
	{
		if(flag)
		{
			
			if( credithistory.getCreditcard_is_principal() != null && credithistory.getCreditcard_is_principal() == 1 && ( credithistory.getCreditcard_four_digits() == null ||  credithistory.getCreditcard_four_digits().trim().length() != 4 ) )
			{
				flag = false;
			}else{
				i = 7;
				flag = true;
			}
			
		}
	}
	
	protected void init_message_ERROR() 
	{
		switch (i)
		{
			case 0:
				messageInit   = "Error al cargar los datos del prospecto";
				displayAction = false;
			break;
				
			case 1:
				messageInit   = "Error al cargar los datos del historial crediticio";
				displayAction = false;
			break;
				
			case 2:
				messageInit   = "Error al cargar los datos de la persona";
				displayAction = false;
			break;
				
			case 3:
				messageInit   = "Error al cargar los datos de la dirección de la persona. Regrese al paso 1 para proporcionar datos faltantes, Gracias ";
				displayAction = false;
			break;
				
			case 4:
				messageInit   = "Error al cargar los datos telefónicos de la persona. Regrese al paso 1 para proporcionar datos faltantes, Gracias";
				displayAction = false;
			break;
				
			case 5:
				messageInit   = "Error al cargar los datos de membresía del cliente....";
				displayAction = true;
			break;
			
			case 6:
				messageInit   = "Existe un error con la información de su Tarjeta de Credito. Regrese al paso 2 para proporcionar datos faltantes, Gracias";
				displayAction = true;
			break;
				
			case 7:
				messageInit   = "Por favor espera un momento, tu información está siendo procesada...";
				displayAction = true;
			break;
		}
	}
	
	protected void init_promotor_id() 
	{
		promotor_id = membership.getPromotor_id();
		
		if(promotor_id != null)
		{
			change_prospectus_id = membership.getPromotor().getMembership().getMembershipPK().getProspectus_id();
			
		} else {
			
			change_prospectus_id = prospectus_id;
		}
	}
	
	protected void init_nota_del_coach() 
	{				
		if( service_notas != null ){
			lista_notas_del_coach = service_notas.getLista_notas_del_coach(prospectus_id, company_id);
			
			if(lista_notas_del_coach != null && lista_notas_del_coach.size() > 0)
			{
				nota_del_coach = lista_notas_del_coach.get(LAST);
				
				if(nota_del_coach.getProyect_id() == null)
				{
					nota_coach_ENABLED = true;
					
				} else {
					
					nota_coach_ENABLED = false;
				}
			}
		}
	}
	
	public void asignar_consulta( String isCheck  ) throws RemoteException 
	{
		numConsulBuro = creditAttemptService.getCreditHistoryAttemptByCheck(fecLimiteUtilDate, naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id());
        
        //Si el numero de intentos permitidos definido en system_param es menor a los intentos permitidos se realiza la consulta.
        if(intentosPermitidos != null && numConsulBuro != null)
        {				        	
	        if(numConsulBuro < intentosPermitidos)
	        {								        	
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setInfo("Invocando Servicio Web getProspectIDProvider("
								+ user + "," + password + "," 
								+ (credithistory.getCreditHistoryPK().getProspectus_id()+"") + "," 
								+ (has_tarjeta_credito==0?"F":"V")    + "," //indCreditCard, 
								+ (tarjeta_credito_number )               + "," //creditCardTermination, 
								+ (has_credito_hipotecario==0?"F":"V") 	  + ","//indmortgage, 
								+ (has_credito_automotriz==0?"F":"V")//indCarLoan
								+ ")");
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(1);
			
				servicecallingService.saveServiceCall(srvCall);
			
				// ++++++   Consulta a Buró de Crédito   ++++++
				
				prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
				String indCreditCard = has_tarjeta_credito     == 0 ? "F" : "V";
				String indmortgage   = has_credito_hipotecario == 0 ? "F" : "V";
				String indCarLoan    = has_credito_automotriz  == 0 ? "F" : "V";
				
				response = service.getProspectIDProvider(user, password, prospectus_id + "", indCreditCard, tarjeta_credito_number, indmortgage, indCarLoan);																						
				// ++++++   Fin de Consulta a Buró de Crédito   ++++++
				
				//Se guarda la respuesta y fecha del intento.
				
				intento.setCreditcard_is_principal(has_tarjeta_credito);
				intento.setCreditcard_four_digits(tarjeta_credito_number);
				intento.setMortgage_is_principal(has_credito_hipotecario);
				intento.setCar_is_principal(has_credito_automotriz);
				
				intento.setStatus_res(response.getStatus());
				intento.setInfo_res(response.getMessage());;
				intento.setConsultation_date(new Date());
				
				intento.setIs_check(isCheck);
				
				CreditHistoryAttempt  tmp = getTemporalCreditHistoryAttempt(intento); 
				creditAttemptService.add(tmp);
				
				System.out.println( flagDoubleCheck_2 +" - "+ flagDoubleCheck + "" );
					
				if( excecuteJSF ){
					
					faces = FacesContext.getCurrentInstance();
					
					HttpServletRequest httpServletRequest = (HttpServletRequest) faces.getExternalContext().getRequest(); 

					String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
						    
							if(ipAddressClient == null)  
							{
						    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
							}
				
					BehaviorCheck bc = new BehaviorCheck();
					
					bc.checkProcess(sesion.getCompany_id(), sesion.getProspectus_id(), ipAddressClient);
					
					
				}
					
					
				
				if(response.getStatus().equals("-1"))
				{									
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
					srvCall.setInfo("Error al Invocar Servicio Web getProspectIDProvider()");
					srvCall.setException(response.getMessage());
					srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					srvCall.setStatus(3);
					
					servicecallingService.saveServiceCall(srvCall);
				
					is_prospect_SGB_OK = creaProspectSGB();
					
					if(!is_prospect_SGB_OK)
					{
						setErrorMsg(" Error al dar de alta el usuario en Mesa de control. Comuníquese con kubo finaciero para solucionar su problema. ");
						setSuccess(false);
						setWait(false);
						setFail(false);
						setError(false);
						setNoHit(true);
						return;
					} else {
						// ++++++   Consulta a Buró de Crédito   ++++++
						
						srvCall = new ServiceCalling();
						
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
						srvCall.setInfo("Invocando Servicio Web getProspectIDProvider(" +
									user + "," + password + "," 
									+ (credithistory.getCreditHistoryPK().getProspectus_id()+"") + "," 
									+ (has_tarjeta_credito==0?"F":"V")    + "," //indCreditCard, 
									+ (tarjeta_credito_number )               + "," //creditCardTermination, 
									+ (has_credito_hipotecario==0?"F":"V")      + "," //indmortgage, 
									+ (has_credito_automotriz==0?"F":"V")+") 2da vuelta.");//indCarLoan												
						srvCall.setException(response.getMessage());
						srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
						srvCall.setStatus(3);
						
						servicecallingService.saveServiceCall(srvCall);
						
						response= service.getProspectIDProvider(	user, 
																password, 
																(credithistory.getCreditHistoryPK().getProspectus_id()+""), 
																has_tarjeta_credito==0?"F":"V",//indCreditCard, 
																tarjeta_credito_number ,//creditCardTermination, 
																has_credito_hipotecario==0?"F":"V",//indmortgage, 
																has_credito_automotriz==0?"F":"V"//indCarLoan
																);
						// ++++++   Fin de Consulta a Buró de Crédito   ++++++
						}
					}
				
				if( response.getStatus().equals("-2") && has_credito_hipotecario == 1 && flagDoubleCheck_2 ){
					 
					System.out.println("----- 1");
					
					has_credito_hipotecario = 0;
					
					credithistory.setMortgage_is_principal(null);
					
					asignar_consulta( "0" );
					
				}else if( response.getStatus().equals("-2") && has_credito_automotriz == 1 && flagDoubleCheck_2 ){
					
					System.out.println("----- 2");
					
					has_credito_automotriz = 0;
					
					credithistory.setCar_is_principal( null );
					
					asignar_consulta( "0" );
					
				} else if( flagDoubleCheck ){ 
					
					System.out.println("flagDoubleCheck");
					
					has_credito_hipotecario = 1;
					
					credithistory.setMortgage_is_principal(1);
					
					flagDoubleCheck_2 = false;
					
					flagDoubleCheck = false;
					
					asignar_consulta( "0" );
					
					
				}else{
				
					procesar_respuesta_SGB();
				
				}
				
	        } else {
	        	setSuccess(false);
				setWait(false);
				setFail(false);
				setError(false);
				setNoHit(false);
				
				setDisplayErrBurConsult(true);
				//msgWarningBurConsult = "Lo sentimos, ha superado el limite de consultas a buró de crédito. Por favor póngase en contacto con nuestro centro de atención para poder solucionar su problema.";
				msgWarningBurConsult = "Hemos visto que has intentado varias veces introducir tu información y que por alguna razón no logramos autenticarte ante Buró de Crédito.<br />"
						+ "Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
			}
        }	
	}

	protected void asignar_credit_history_attempt() throws ParseException, RemoteException 
	{
		intento   = new CreditHistoryAttempt();
	
		intento.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		intento.setUser(user);
		intento.setPassword(password);
		intento.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		intento.setFirst_name(naturalPerson.getFirst_name());
		intento.setMiddle_name(naturalPerson.getMiddle_name());
		intento.setFather_last_name(naturalPerson.getFather_last_name());
		intento.setMother_last_name(naturalPerson.getMother_last_name());
	
		if(naturalPerson.getDate_of_birth() != null)
		{							
			format   = new SimpleDateFormat("yyyy-MM-dd");
			birthStr = format.format(naturalPerson.getDate_of_birth());
			birth    = format.parse(birthStr);
			
			intento.setDate_of_birth(new java.sql.Date(birth.getTime()));
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
		intento.setCreditcard_is_principal(has_tarjeta_credito);
		intento.setCreditcard_four_digits(tarjeta_credito_number);
		intento.setMortgage_is_principal(has_credito_hipotecario);
		intento.setCar_is_principal(has_credito_automotriz);
		
		if(lada != null  && lada.indexOf("+") != (-1))
		{						
			lada = lada.replaceAll("\\+", "");
			lada = lada.trim();
			intento.setMx_lada(Integer.parseInt(lada));
			
		} else {
			
			intento.setMx_lada(0);							
		}
		
		if(phone.isEmpty())
		{
			intento.setPhone(null);
			
		} else { 
			
			intento.setPhone(phone);
		}
		intento.setMx_curp(naturalPerson.getMx_curp());
								
		intento.setInfo_res("No se realizo la consulta*");
												
		diasValidos        = null;						
		intentosPermitidos = null;
	
		//ID 45 trae los dias de validez de una consulta a buro
		paramPK     = new SystemParamPK(45,naturalPerson.getNatPerPK().getCompany_id());
		system      = systemParamService.loadSelectedSystemParam(paramPK);
		diasValidos = Integer.parseInt(system.getValue());
		
		//el ID 46 trae el numero de intentos permitidos de consulta a buro
		paramPK.setSystem_param_id(46);
		paramPK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		system             = null;
		system             = systemParamService.loadSelectedSystemParam(paramPK);
		intentosPermitidos = Integer.parseInt(system.getValue());					
		
		formatUtilDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		
		//Con los dias validos definidos en system_param se los restamos a la fecha actual para
		//obtener la fecha limite a considerar para obtener el rango de validez de la ocnsulta.
		//fecActual-diasValidos:24-10-13-2 = 22-10-13
		fechaLimite       = Utilities.restarFechasDias(new Date(), diasValidos);										
		fecLimiteStr      = formatUtilDate.format(fechaLimite);
		
		//Se hizo otra variable Date por que no permitio formatear '2013-10-22'
		fecLimiteUtilDate = formatUtilDate.parse(fecLimiteStr);
		
		//Consutamos el historico de consultas para saber si ya se realizo una consulta con los
		//mismos datos y en el periodo valido.
		consultasAnteriores = creditAttemptService.getConsultas_anteriores(intento, fecLimiteUtilDate);
		
		System.out.println("PreaprobacionAMO.asignar_credit_history_attempt(consultasAnteriores): " + consultasAnteriores);
		
		flagDoubleCheck = false;
		flagDoubleCheck_2 = true;
		
		if(consultasAnteriores == 0)
		{	
			
			flagDoubleCheck = ( has_credito_hipotecario == 1 && has_credito_automotriz ==1 );
			
			asignar_consulta("1");
	        
		} else if(consultasAnteriores > 0) {
			setSuccess(false);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(true);
			
			setDisplayErrBurConsult(false);
			//msgWarningBurConsult = "Ya se realizo una consulta con esos datos, Compruebe que sean correctos. Si el problema continua por favor póngase en contacto con nuestro centro de atención.";
			msgWarningBurConsult = "<b>No nos fue posible autenticarte ante Buro de Crédito con la información que nos proporcionaste.</b>"
					+ "<br /> Por favor considera lo siguiente:"
					+ "<ul>"
					+ "<li>Revisa que tu nombre esté escrito exactamente como está en tu credencial de elector.</li>"
					+ "<li>Verifica tu fecha de cumpleaños.</li>"
					+ "<li>Si has contratado una tarjeta de crédito, crédito hipotecario o crédito de auto hace menos de un mes, es probable que aún no esté reflejado en buró de crédito, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
					+ "<li>Si has tenido un crédito de auto o hipotecario que ya liquidaste hace más de 3 meses, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
					+ "<li>Si tienes un crédito hipotecario de INFONAVIT, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
					+ "<li>Recuerda que el hecho de que tu Tarjeta de Crédito esté en ceros, no implica que está cancelada.  Si no has cancelado tu tarjeta de crédito, por favor intenta de nuevo indicando tu los últimos cuatro dígitos de dicha tarjeta de crédito.</li>"
					+ "</ul>"
					+ "Si estás seguro de que tu información es correcta, por favor ponte en contacto con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
		} else {
			setSuccess(false);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(false);
			
			setDisplayErrBurConsult(true);
			msgWarningBurConsult = "Ha ocurrido un error al consultar su información."
					+ " Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
			
		}
	}
	
	public boolean creaProspectSGB()
	{
		System.out.println("+++++    LLAMANDO AL SGB    +++++");

		try 
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();// yyyymmdd. 19860131

			prospectus_id = prospectus.getProspectusPK().getProspectus_id();
			company_id    = prospectus.getProspectusPK().getCompany_id();
			
			user       = "";
			password   = "";
			birthday   = "";
			lada       = "";
			phone      = "";
			colonia    = "";
			municipio  = "";
			thisEstado = "";
			msg        = "";
			prospectId = "" + prospectus_id;

			boolean flag = true;

			Integer colonia_id = thisAddress.getNeighborhood_id();
			Integer ciudad_id  = thisAddress.getTown_id();
			Integer estado_id  = thisAddress.getState_id();
			
			if(colonia_id != null)
			{
				nPK = new NeighborhoodCatPK();
				
				nPK.setCompany_id(company_id);
				nPK.setNeighborhood_id(colonia_id);
				
				neig = neighborhoodService.getNeighborhoodById(nPK);
				colonia = quitaAcentos( neig.getName() );
				
			} else if(thisAddress.getNeighborhood_text() != null){
				
				colonia = quitaAcentos( thisAddress.getNeighborhood_text() );
				
			} else {
				msg += "La colonia no fue proporcionada. ";
				flag = false;
			}

			if(ciudad_id != null)
			{
				tPK = new TownCatPK();
				tPK.setCompany_id(company_id);
				tPK.setTown_id(ciudad_id);
				town = townService.getTownById(tPK);
				municipio = quitaAcentos( town.getName() );
			} else {
				msg += "Delegacion o municipio no proporcionados. ";
				flag = false;
			}
										
			if(estado_id != null)
			{
				sPK = new StateCatPK();
				sPK.setCompany_id(company_id);
				sPK.setState_id(estado_id);
				state = stateService.getStateById(sPK);
				thisEstado = state.getBc_key();
			} else {
				msg += "Estado no proporcionado. ";
				flag = false;
			}
				

			format = new SimpleDateFormat("yyyyMMdd");
			
			if(naturalPerson.getDate_of_birth() != null)
			{
				birthday = format.format(naturalPerson.getDate_of_birth());				
			}else{
				msg += "Fecha de nacimiento no proporcionada. ";
				flag = false;
			}
			
			if(thisPhoneFixed != null){
				
				if(thisPhoneFixed.getPhone_number()!=null && (thisPhoneFixed.getPhone_number().split("\\)")).length>0 )
				{
					String[] thisPhone = thisPhoneFixed.getPhone_number().split("\\)");
					
					if(thisPhone != null && thisPhone.length >1){
					
						lada = thisPhone[0];
						lada = lada.replace("(", "");
						lada = lada.trim();
						
						phone = thisPhone[1];
						phone = phone.replace("-", "");
						phone = phone.replace(" ", "");
						phone = phone.trim();
					}else{
						lada="";
						phone = thisPhone[0];
						phone = phone.replace("-", "");
						phone = phone.replace(" ", "");
						phone = phone.trim();
					}
					
				}else{
					msg += "Número de teléfono no proporcionado. ";
					flag = false;
				}
				
			}else{
				lada = "";
				phone = "";
			}
			
			if (flag) 
			{

				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setInfo("Invocando Servicio Web prospectAdmin:("+
							prospectId+","+ quitaAcentos( naturalPerson.getFirst_name() )+","+ quitaAcentos( naturalPerson.getMiddle_name() )+","+quitaAcentos( naturalPerson.getFather_last_name() )+","+
							quitaAcentos( naturalPerson.getMother_last_name() ) +","+birthday+","+naturalPerson.getMx_rfc()+","+thisAddress.getStreet()+","+
							thisAddress.getMx_manzana()+","+thisAddress.getMx_lote()+","+thisAddress.getAddress_number()+","+thisAddress.getApt_number()+","+
							colonia+","+municipio+","+ quitaAcentos( thisEstado )+","+thisAddress.getZip_code()+","+lada+","+phone+","+naturalPerson.getMx_curp()+","+
							naturalPerson.getProspectus().getTracking_id()
						+")");
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(1);
				
				servicecallingService.saveServiceCall(srvCall);
				
				//monto_solicitado = 0D;
				if( simulator != null ){
					monto_solicitado = simulator.getAmmount();
				}
				
				System.out.println("PreaprobacionAMO(): " + monto_solicitado);

				WsSgbResponse res = service.prospectAdmin
						( user, password,
						prospectId,// prospectId,
						quitaAcentos( naturalPerson.getFirst_name() ), // firstName,
						quitaAcentos( naturalPerson.getMiddle_name() ), // secondName,
						quitaAcentos( naturalPerson.getFather_last_name() ),// surName,
						quitaAcentos( naturalPerson.getMother_last_name() ),// aditSurName,
						birthday,// birthday,
						naturalPerson.getMx_rfc(),// rfc,
						quitaAcentos( thisAddress.getStreet() ),// street,
						thisAddress.getMx_manzana(),// mx_manzana,
						thisAddress.getMx_lote(),// mx_lote,
						thisAddress.getAddress_number(),// mx_numExterior,
						thisAddress.getApt_number(),// mx_numInterior,
						quitaAcentos( colonia ),// mx_colonia,
						quitaAcentos( municipio ),// mx_municipio,
						quitaAcentos( thisEstado ),// mx_estado,
						thisAddress.getZip_code(),// mx_codPostal,
						lada,// mx_lada,
						phone, // phoneNumber
						naturalPerson.getMx_curp(),// curp
						naturalPerson.getProspectus().getTracking_id(),
						monto_solicitado);
				
				
				if(res != null && res.getStatus().equals("0")){
				
					srvCall = new ServiceCalling();
	
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
					srvCall.setInfo("Regresando Satisfactoriamente de Servicio Web prospectAdmin: "+res.getMessage());
					srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					srvCall.setStatus(2);
					
					servicecallingService.saveServiceCall(srvCall);
					
					System.out.println("****************************************************************************************************");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("");
					System.out.println("SGB Web Service: ");
					System.out.println("Codigo Status: " + res.getStatus());
					System.out.println("Mensaje de Respuesta: " + res.getMessage());
					System.out.println("");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("****************************************************************************************************");
					System.out.println("fin");
					
				}else{
					
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
					srvCall.setException((res==null)?"Respuesta Null" : res.getMessage());
					srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
					System.out.println("****************************************************************************************************");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("");
					System.out.println((res==null)?"SGB Web Service: RESPUESTA NULL " : res.getMessage());
					System.out.println("");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("****************************************************************************************************");
					
					msg = (res==null)?"SGB Web Service: RESPUESTA NULL " : res.getMessage();
						
					flag= false;
					
					setErrorMsg(msg);
					
				}
			
				
			}else{
				setErrorMsg(msg);
			}
			
			return flag;
			
		} catch (Exception e) {
			
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String msg = e.getMessage();
			
			Evento evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.Preaprobacion.creaProspectSGB(605): " + msg);
			
			notificar(evento, null, msg + " <br /> " + writer.toString(), null);
			
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setInfo("Error al ejecutar Servicio Web prospectAdmin");
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			String exception =  e.getStackTrace().toString().length()>990?e.getStackTrace().toString().substring(0,990):e.getStackTrace().toString();
			
			srvCall.setException(exception);
			
			servicecallingService.saveServiceCall(srvCall);
			return false;
		}
	}
	
	protected void asignar_msg_credito_DISABLED() 
	{
		msg = " Error en el proceso de validacion de preguntas, ####  ";			
		int i = 0;
		
		if( has_tarjeta_credito == null )
		{
			msg += "<br />¿Eres titular de alguna tarjeta de crédito vigente? ";
			i++;
		}
		
		if(has_credito_hipotecario == null)
		{
			msg += "<br />¿Tienes o has tenido algún crédito hipotecario?";
			i++;
		}
		
		if(has_credito_automotriz == null)
		{
			msg += "<br />¿Tienes o has tenido algún crédito automotriz?";
			i++;
		}
	
		if(i > 1)
		{
			msg = msg.replace("####", "las preguntas");
		} else {
			msg = msg.replace("####", "la pregunta");
		}
		
		setSuccess(false);
		setWait(false);
		setFail(false);
		setErrorMsg( msg );
		setError(true);
		setNoHit(false);	
	}
	
	protected void asignar_datos_simulacion() 
	{
		if(consultaBuro)
		{							
			simulacion_ACTUAL = simulatorService.getMaxSimulationProspect(credithistory.getCreditHistoryPK().getProspectus_id(), credithistory.getCreditHistoryPK().getCompany_id());
			
			if(simulacion_ACTUAL == null)
			{
				faces = FacesContext.getCurrentInstance();
				
				elContext = faces.getELContext();
				resolver  = faces.getApplication().getELResolver();
				
				simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
				
				Integer purpose_id = simulator.getPurpose_id();
				
				simulator.init(); 						
				simulator.simulaCred(false);
				
				company_id    = credithistory.getCreditHistoryPK().getCompany_id();
				prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
				
				simulacion_ACTUAL = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
			
				if(simulacion_ACTUAL == null)
				{							
					simulacion_ACTUAL = new SimulatorBean();
					simulacion_ACTUAL.setAmmount(simulator.getAmmount());
					simulacion_ACTUAL.setBc_score(0);
					simulacion_ACTUAL.setDate(new Date());
					simulacion_ACTUAL.setFrequency_id(simulator.getFrequency_id());
					simulacion_ACTUAL.setMx_cat(simulator.getCat());
					simulacion_ACTUAL.setNum_payments(simulator.getTerm_id());
					simulacion_ACTUAL.setPayment(simulator.getMontoCuota());
					simulacion_ACTUAL.setPeriod_rate(simulator.getTasaPeriodo());
					
					if(purpose_id != null && purpose_id.intValue() != 0 )
					{
						simulator.setPurpose_id(purpose_id);
						simulacion_ACTUAL.setPurpose_id(simulator.getPurpose_id());
						
					}else{
						
						simulator.setPurpose_id(1);
						simulacion_ACTUAL.setPurpose_id(simulator.getPurpose_id());
						
					}
					
					simulacion_ACTUAL.setSim_order(0);
				
					simulator_PK = new SimulatorPK();
					simulator_PK.setCompany_id(company_id);
					simulator_PK.setProspectus_id(prospectus_id);
					simulacion_ACTUAL.setSimulatorPK(simulator_PK);
					simulacion_ACTUAL.setTerm_id(simulator.getTerm_id());
					simulacion_ACTUAL.setTitle("");
					simulacion_ACTUAL.setTotal_interest(simulator.getInteres());
					simulacion_ACTUAL.setTotal_payment(simulator.getTotalPagar());
					simulacion_ACTUAL.setYearly_interest(simulator.getInteres());
					simulacion_ACTUAL.setYearly_rate(simulator.getTasaTotal());							
				}						
			}					
		///////en caso de que venga sim = null crear una simulacion nueva con los valores por defecto
							
		//Veficar si ya existe Poyect loan						
			if( proyect_loan != null && (proyect_loan.getMx_solicitud_buro() == null || (proyect_loan.getStatus_id() != null && proyect_loan.getStatus_id().intValue() == 0 ) ) )
			{				
				monto_solicitado = simulacion_ACTUAL.getAmmount();
				proyect_loan.setAmmount(simulacion_ACTUAL.getAmmount());
				proyect_loan.setFrequency_id(simulacion_ACTUAL.getFrequency_id());
				proyect_loan.setFunding_type('T');
				proyect_loan.setKubo_score_a(score.getKubo_score_a());
				proyect_loan.setKubo_score_b(score.getKubo_score_b());
				proyect_loan.setBc_score(Integer.parseInt(score.getBc_score()));
				proyect_loan.setMx_cat(simulacion_ACTUAL.getMx_cat());								
				proyect_loan.setMin_ammount(simulacion_ACTUAL.getAmmount());								
				proyect_loan.setPayment(simulacion_ACTUAL.getPayment());								
				proyect_loan.setRate(score.getRate());								
				proyect_loan.setRate_with_opening(score.getRate());								
				proyect_loan.setTerm_id(simulacion_ACTUAL.getTerm_id());
				proyect_loan.setDeposit_method_id(1);
				proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());												
				proyect_loan.setConsulting_date(new Date());						
				proyect_loan.setVerification_score(1);						
				proyect_loan.setOpening_commission(score.getOpening_commission());
				proyect_loan.setLiquidity(score.getLiquidity());
				proyect_loan.setRate_investor(score.getRate_investor());
				proyect_loan.setCci_score(score.getCci_score());
				
				proyect = proyect_loan.getProyect();
				
				if( simulacion_ACTUAL.getPurpose_id() != null){
				
					if( simulacion_ACTUAL.getPurpose_id().intValue() != 0  ){
					
						proyect.setPurpose_id(simulacion_ACTUAL.getPurpose_id());
					
					}else{
						proyect.setType_id(1100);
						proyect.setPurpose_id( 1 );
					}
					
				}else{
				
					simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
					
					if(simulator.getPurpose() != null && simulator.getPurpose().getPurposePK().getPurpose_id() != 0 ){
						
						proyect.setType_id(simulator.getPurpose().getType_id());
						proyect.setPurpose_id(simulator.getPurpose().getPurposePK().getPurpose_id());
						
					}else{
						
						proyect.setType_id(1100);
						proyect.setPurpose_id(1);
						
					}
				
				}
				
				if(nota_coach_ENABLED)
				{
					proyect.setGoal(nota_del_coach.getNote());
					
					nota_del_coach.setProyect_id(proyect.getProyectoPk().getProyect_id());
				}
								
				proyectService.update(proyect);				
				proyectloanService.update(proyect_loan);
				
				
				if( nota_del_coach != null ){
				
					if( service_notas == null  ){
						service_notas = Utilities.findBean("notesServiceImp");
					}
					
					is_nota_OK  = service_notas.updateNote(nota_del_coach);
				
				}
			
			} else {//Creamos proyecto y proyect loan.
				
				proyect_loan = new ProyectLoan();
				monto_solicitado = simulacion_ACTUAL.getAmmount();
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
				
				if( loan_type == null || loan_type.trim().length() == 0 ){
					proyect_loan.setLoan_type("NVO");
				}else{
					proyect_loan.setLoan_type( loan_type );
				}
				
				proyect_loan.setCci_score(score.getCci_score());
				proyect_loan.setConsulting_date(new Date());
			
				proyect_id    = proyectService.getMaxProyectID();
				prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
				company_id    = credithistory.getCreditHistoryPK().getCompany_id();
								
				proyect_PK  = new ProyectPK(proyect_id, prospectus_id, company_id);
				proyect     = new Proyect();
				proyect.setProyectoPk(proyect_PK);
				
				proyect.setPurpose_id( simulacion_ACTUAL.getPurpose_id());
				
				
				if(simulacion_ACTUAL.getPurpose_id() != null && simulacion_ACTUAL.getPurpose_id().intValue() != 0 ){
					
					proyect.setPurpose_id(simulacion_ACTUAL.getPurpose_id());
					
				}else{
					
					proyect.setType_id(1100);
					proyect.setPurpose_id(1);
					
				}
				
				
				if(nota_coach_ENABLED)
				{
					proyect.setGoal(nota_del_coach.getNote());
					
					nota_del_coach.setProyect_id(proyect_id);
				}								
				
				membership_PK = new MembershipPK(prospectus_id, company_id);
				
				membership = membershipservice.getMembershipById(membership_PK);
			
				if(membership.getRegistration_reason() != null && membership.getRegistration_reason().getPartner_id() != null)
				{
					proyect.setPartner_id(membership.getRegistration_reason().getPartner_id());
				} else {
					proyect.setPartner_id(null);
				}												
			
				proyect_loan.setRate_investor(score.getRate_investor());
				
				is_proyect_OK = proyectService.add(proyect);
				
				if(nota_del_coach != null){
				
					if( service_notas == null  ){
						service_notas = Utilities.findBean("notesServiceImp");
					}
					is_nota_OK    = service_notas.updateNote(nota_del_coach);
				}
				
				if(is_proyect_OK)
				{							
					prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
					company_id    = credithistory.getCreditHistoryPK().getCompany_id();
													
					proyect = proyectService.getMaxProyect(prospectus_id, company_id);
					
					company_id    = proyect.getProyectoPk().getCompany_id();
					prospectus_id = proyect.getProyectoPk().getProspectus_id();
					proyect_id    = proyect.getProyectoPk().getProyect_id();
					
					proyect_loan_PK = new  ProyectLoanPK();
					
					proyect_loan_PK.setCompany_id(company_id);
					proyect_loan_PK.setProspectus_id(prospectus_id);
					proyect_loan_PK.setProyect_id(proyect_id);
					proyect_loan_PK.setProyect_loan_id(0);		
					
					proyect_loan.setProyectloanPk(proyect_loan_PK);
					
					proyect_loan.setRate(score.getRate());
					proyect_loan.setStatus_id(0);									
					proyect_loan.setTerm_id(simulacion_ACTUAL.getTerm_id());
					proyect_loan.setDeposit_method_id(1);									
					proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
					
					proyectloanService.add(proyect_loan);
					
					proyect_loan_PK = proyect_loan.getProyectloanPk();
					
					proyect_loan = proyectloanService.getProyectLoanById(proyect_loan_PK);							
				}												
			}//fin else myPyLn == null
							
			//TODO No se notificará en esta parte del proceso ya que la respuesta se pasó a KuboServices
			//notificar(Evento.CONSULTA_BC_EXITOSA, score,"",myPyLn );										
		}
	}
	
	private String quitaAcentos(String str){
		try{
		if( str != null ) {
			
			String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i

		    str = str.replaceAll("\"","");
			
			str = str.replaceAll("\\\\","");
			
			str = str.replaceAll("/","");
			
			str = str.replaceAll("'","");
			
			str = str.replaceAll("&","");
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return str;
	}
	
	protected void init_access() 
	{
		if(consultaBuro)
		{	
			access = new Access();
			
			access.setCompany_id( sesion.getCompany_id() );
			access.setProspectus_id(sesion.getProspectus_id());
			access.setScreen_id(SCREEN_SUCCESS_CONSULTA_BC);
			access.setPercentage(100);
			access.setWeb_browser(sesion.getNamebrawser());
			access.setWeb_browser_version(sesion.getVersionbrawser());
			access.setOp_system(sesion.getOsbrawser());
			access.setHorizontal_size(sesion.getBrowser_width());
			access.setVertical_size(sesion.getBrowser_height());
			access.setIpaddress(sesion.getIP_address_client());
			access.setUser_agent(sesion.getUser_agent());
			access.setDevice_info(sesion.getDevice_info());
			access.setProspectus_id_coach( sesion.getCoachProspectus_id() );		
			access.setAccess_from		  (sesion.getAccess_from());
			access.setVersion_description (sesion.getVersion_description());
			access.setUrl_access		  (sesion.getUrl_access());
			accessService.add(access, true);	
		}
	}
	
	private CreditHistoryAttempt getTemporalCreditHistoryAttempt( CreditHistoryAttempt intento){
		
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
		tmp.setState_name(intento.getStreet());
		tmp.setStatus_res(intento.getStatus_res());
		tmp.setStreet(intento.getStreet());
		tmp.setTown_name(intento.getTown_name());
		tmp.setUser(intento.getUser());
		tmp.setZip_code(intento.getZip_code());
		
		return tmp;
	}
	
}
