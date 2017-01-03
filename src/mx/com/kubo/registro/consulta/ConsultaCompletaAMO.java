package mx.com.kubo.registro.consulta;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.xml.rpc.ServiceException;

import com.soa.model.businessobject.SpScoreKubo;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Prospector;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.Utilities;

public abstract class ConsultaCompletaAMO extends ConsultaCompletaDMO
{
	protected void init_credit_history()
	{
		has_tarjeta_credito     = credithistory.getCreditcard_is_principal();
		has_credito_hipotecario = credithistory.getMortgage_is_principal();
		mortgage_company        = credithistory.getMortgage_company();
		has_credito_automotriz  = credithistory.getCar_is_principal();				
		tarjeta_credito_number  = credithistory.getCreditcard_four_digits();
		tarjeta_credito_company = credithistory.getCreditcard_company();
		
		if(tarjeta_credito_company != null)
		{
			tarjeta_credito_company = tarjeta_credito_company.toUpperCase().replaceAll(" ", "");
			
			tarjeta_credito_company_ENABLED = tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("C&A") 	!= (-1) 
					   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("CANDA") != (-1)
					   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("CYA") != (-1)
					   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("BRADES") != (-1)
					   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("UBURBI") != (-1);
		}
		
		if(mortgage_company != null)
		{
			mortgage_company = mortgage_company.toUpperCase().replaceAll(" ", "");
			
		
			mortgage_company_ENABLED = mortgage_company.indexOf("INFONAVIT") != (-1) ||
					   				   mortgage_company.indexOf("FOVISSSTE") != (-1) ||
					   				   mortgage_company.indexOf("FOVISSTE")  != (-1) ||
					   				   mortgage_company.indexOf("FOVISTE") 	 != (-1); 
				     
		}
				
		has_credito_ENABLED = has_tarjeta_credito != null && has_credito_hipotecario != null && has_credito_automotriz != null;
		
				
		if(!blocked_person_ENABLED)
		{			
			if(has_credito_ENABLED)
			{	
				prospectus_id   = credithistory.getCreditHistoryPK().getProspectus_id();
				company_id      = credithistory.getCreditHistoryPK().getCompany_id();
				 
				score        = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);			
				proyect_loan = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
								
				faces = FacesContext.getCurrentInstance();
				
				elContext = faces.getELContext();
				resolver  = faces.getApplication().getELResolver();
				
				simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
			
				
				if(tarjeta_credito_company_ENABLED)
				{
					if(tarjeta_credito_number != null)
					{
						credithistory.setCreditcard_four_digits(tarjeta_credito_number.charAt(0) +"000");
						
						tarjeta_credito_number = tarjeta_credito_number.charAt(0) +"000";
					}					
				}

				if(tarjeta_credito_company != null && tarjeta_credito_company.indexOf("AZTECA") != (-1))
				{
					has_tarjeta_credito     = 0 ;
					tarjeta_credito_company = null;
					tarjeta_credito_number = "";
					
					credithistory.setCreditcard_four_digits("");
				}

				if(has_credito_hipotecario != null && mortgage_company_ENABLED)
				{					
					has_credito_hipotecario = 0;						
				}
				
			} else {	
				
				asignar_msg_credito_DISABLED();
			}
		}
	}
	
	protected boolean creaProspectSGB()
	{
		try 
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();

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
				
				double monto_Solicitado = simulator.getAmmount();
				
				System.out.println("PreaprobacionAMO(): " + monto_Solicitado);

				WsSgbResponse res = service.prospectAdmin
						( user, password,
						prospectId,// prospectId,
						quitaAcentos( naturalPerson.getFirst_name() ), // firstName,
						quitaAcentos( naturalPerson.getMiddle_name() ), // secondName,
						quitaAcentos( naturalPerson.getFather_last_name() ),// surName,
						quitaAcentos( naturalPerson.getMother_last_name() ),// aditSurName,
						birthday,// birthday,
						naturalPerson.getMx_rfc(),// rfc,
						thisAddress.getStreet(),// street,
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
						monto_Solicitado);
				
				
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
					
					errorMsg = (msg);
					
				}
			
				
			}else{
				errorMsg = (msg);
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
			success = (false);
			wait = (false);
			fail = (false);
			error = (false);
			noHit = (true);
			
			displayErrBurConsult = (false);
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
			success = (false);
			wait = (false);
			fail = (false);
			error = (false);
			noHit = (false);
			
			displayErrBurConsult = (true);
			msgWarningBurConsult = "Ha ocurrido un error al consultar su información."
					+ " Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
			
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
						errorMsg = (" Error al dar de alta el usuario en Mesa de control. Comuníquese con kubo finaciero para solucionar su problema. ");
						success = (false);
						wait = (false);
						fail = (false);
						error = (false);
						noHit = (true);
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
	        	
	        	success = (false);
				wait = (false);
				fail = (false);
				error = (false);
				noHit = (false);
				
				displayErrBurConsult = (true);
				//msgWarningBurConsult = "Lo sentimos, ha superado el limite de consultas a buró de crédito. Por favor póngase en contacto con nuestro centro de atención para poder solucionar su problema.";
				msgWarningBurConsult = "Hemos visto que has intentado varias veces introducir tu información y que por alguna razón no logramos autenticarte ante Buró de Crédito.<br />"
						+ "Por favor, para brindarte un mejor servicio comunícate con nuestro centro de contacto, al teléfono <b>"+recurso.getString("Kubo_telefono")+"</b> o al correo <b>soporte@kubofinanciero.com</b>";
			}
        }	
	}
	
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
			
			faces     = FacesContext.getCurrentInstance();
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();

			navigation = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
			
			navigation.setHasValidScore(true);	
			
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
			
			updateAccess();
			
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
					
					errorMsg = (msgStr);
					
				
					
			}
	
			if(response.getStatus().equals("-3"))
			{
				errorMsg = (" Información errónea: no coinciden domicilios (CP, delegación, estado)");
			}
			
			if(response.getStatus().equals("-4"))
			{
				errorMsg = ("Error en el nombre, acentos, eñes, etc.");
			}

			//ACCION-SOPORTE TÉCNICO SGB
			if(response.getStatus().equals("-5"))
			{
				errorMsg = (" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}
			
			if(response.getStatus().equals("-6"))
			{
				//(-6) ERROR SGB: Jar no encontrado.
				errorMsg = (" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}

			//ACCION-SOPORTE TÉCNICO SGB/BURO
			if(response.getStatus().equals("-7"))
			{
				//(-7)ERROR BURO: Error en el sistema buró de crédito
				errorMsg = (" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
			}
			
			if(response.getStatus().equals("-8"))
			{
				//(-8)ERROR SGB-BURO: Usuario/Password incorrectos
				errorMsg = (" Sin conexión con buro de crédito. Comuníquese con kubo financiero para solucionar el problema ");
				
				Evento evento = Evento.ERROR_DESARROLLO;
				evento.setError("managedbeans.Preaprobacion.callWSSGB(532): (-8)ERROR SGB-BURO: s/Password incorrectos");
				notificar(evento, null,"Sin conexión con buro de crédito.  <br /> Usuario/Password incorrectos",null);													
			}
			
			success = (false);
			wait = (false);
			fail = (false);
			error = (false);
			noHit = (true);
		} else if(response.getStatus().equals("-1.1")){
			errorMsg = (" Faltan datos por proporcionar, Fecha de nacimineto, curp, rfc, calle, colonia, número exterior, número interior, etc. ");
			success = (false);
		
			wait = (false);
			fail = (false);
			error = (false);
			noHit = (true);
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
			
			success = (false);
			wait = (false);
			fail = (false);
			error = (true);
			noHit = (false);									
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
	
	protected void asignar_score() 
	{
		if(score != null)
		{
			if(score.getKubo_score_a() == null || score.getKubo_score_b() == null)
			{					
				score        = getSGBRisk(score);
				consultaBuro = true;					
			} 
	
			if(score.getKubo_score_a() != null && score.getKubo_score_b() != null)
			{
				if((score.getKubo_score_a() + score.getKubo_score_b()).equals("E7") || (score.getKubo_score_a()+score.getKubo_score_b()).equals("N0"))
				{
					successTitle = (false);							
				} else {
					
					successTitle = (true);
					
				}
			}
				
			success = (true);
			wait = (false);
			fail = (false);
			error = (false);
			noHit = (false);
				
			asignar_datos_simulacion();
		}	
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
					
					if(purpose_id != null)
					{
						simulator.setPurpose_id(purpose_id);
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
				
			if( proyect_loan != null && proyect_loan.getMx_solicitud_buro() == null )
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
				
					proyect.setPurpose_id(simulacion_ACTUAL.getPurpose_id());
					
				}else{
				
					simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
					
					if(simulator.getPurpose() != null){
						proyect.setType_id(simulator.getPurpose().getType_id());
						proyect.setPurpose_id(simulator.getPurpose().getPurposePK().getPurpose_id());
					}
				
				}
				
				if(nota_coach_ENABLED)
				{
					proyect.setGoal(nota_del_coach.getNote());
					
					nota_del_coach.setProyect_id(proyect.getProyectoPk().getProyect_id());
				}
								
				proyectService.update(proyect);				
				proyectloanService.update(proyect_loan);
				
				if( service_notas == null  ){
					service_notas = Utilities.findBean("notesServiceImp");
				}
				
				is_nota_OK  = service_notas.updateNote(nota_del_coach);
			
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
				proyect_loan.setLoan_type("NVO");
				proyect_loan.setCci_score(score.getCci_score());
				proyect_loan.setConsulting_date(new Date());
			
				proyect_id    = proyectService.getMaxProyectID();
				prospectus_id = credithistory.getCreditHistoryPK().getProspectus_id();
				company_id    = credithistory.getCreditHistoryPK().getCompany_id();
								
				proyect_PK  = new ProyectPK(proyect_id, prospectus_id, company_id);
				proyect     = new Proyect();
				proyect.setProyectoPk(proyect_PK);
				proyect.setPurpose_id( simulacion_ACTUAL.getPurpose_id());
				
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
				
				if( service_notas == null  ){
					service_notas = Utilities.findBean("notesServiceImp");
				}
				is_nota_OK    = service_notas.updateNote(nota_del_coach);
				
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
			}									
		}
	}
	
	protected Scoring getSGBRisk(Scoring score)
	{		
		try
		{
			String         age     = (calcularEdad(naturalPerson.getDate_of_birth())+"");			
			ServiceCalling srvCall = new ServiceCalling();
	
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setInfo(""
					+ "Invocando Servicio Web getProspectRisk ("
				    + credithistory.getCreditHistoryPK().getProspectus_id() + ","
				    + score.getMx_solicitud_buro()    + "," 
					//+ naturalPerson.getResidence_id() + ","
				    +"1,"
				    + naturalPerson.getGender_id()    + ","
					+ age +","
					+ "3227"  + ")" );
			
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(1);
			servicecallingService.saveServiceCall(srvCall);
			
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk               service = locator.getWsSgbRisk();
			
			
			BCRiskRequest request = new BCRiskRequest();
			
			request.setAge(age+"");
			request.setBursolnum(score.getMx_solicitud_buro());
			request.setBusinessType("3227");
			request.setCompanyId(naturalPerson.getNatPerPK().getCompany_id()+"");
			request.setEventId(Evento.CONSULTA_BC_EXITOSA.getId()+"");
			request.setGender((naturalPerson.getGender_id()+""));
			request.setHomeType(naturalPerson.getResidence_id()+"");
			request.setProspectId(naturalPerson.getNatPerPK().getProspectus_id()+"");
			request.setProspectIdTemp(naturalPerson.getNatPerPK().getProspectus_id()+"");
			
			SpScoreKubo reskubo= service.getProspectRisk(request);
			
			/*
			SpScoreKubo reskubo= service.getProspectRisk(	(credithistory.getCreditHistoryPK().getProspectus_id() + ""), 
															score.getMx_solicitud_buro(), 
															naturalPerson.getResidence_id() + "", 
															naturalPerson.getGender_id() + "", 
															age, 
															"3227");
															*/
			
			if( reskubo !=null && reskubo.getStatus().equals("0") )
			{				
				srvCall = new ServiceCalling();
	
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio getProspectRisk: "+reskubo.getMessage());
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(2);
				
				servicecallingService.saveServiceCall(srvCall);
								
				//cambio para recoleccion de informacion
				if((reskubo.getVocalkubo1()+reskubo.getVocalkubo2()).equals("N0"))
				{
					score.setKubo_score_a("G");
					score.setKubo_score_b("1");
					reskubo.setVocalkubo1("G");
					reskubo.setVocalkubo2("1");
					successTitle = (false);					
				}else if((reskubo.getVocalkubo1()+reskubo.getVocalkubo2()).equals("	E6"))
				{
					score.setKubo_score_a("F");
					score.setKubo_score_b("1");
					reskubo.setVocalkubo1("F");
					reskubo.setVocalkubo2("1");
					successTitle = (false);					
				} else {
					//parte original
					score.setKubo_score_a(reskubo.getVocalkubo1());
					score.setKubo_score_b(reskubo.getVocalkubo2());
					successTitle = (true);
				}
				
				score.setRate(Double.parseDouble(reskubo.getTasaAcre()));
				score.setRate_investor(Double.parseDouble(reskubo.getTasaInv()));
				score.setLiquidity(Double.parseDouble(reskubo.getVoliquidez()));
				score.setOpening_commission(Double.parseDouble(reskubo.getVocomision()));
				score.setRisk_level(reskubo.getVoriesgo());
				score.setKubo_rate(reskubo.getVotasakubo());
			
				scoringService.updateScoring(score);
	
				
				if(!(reskubo.getVocalkubo1()+reskubo.getVocalkubo2()).equals("N0"))
				{
					ELContext elContext = FacesContext.getCurrentInstance().getELContext();
					SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "sessionBean");
					
					/*modificar por una tasa variable*/
					sesion.setRate(Double.parseDouble(reskubo.getTasaAcre()));
					/* fin modificar tasa variable*/
					
					//Lamando al Safi!!
					//callWSSafi();
					
					success = (true);
					wait = (false);
					fail = (false);
					error = (false);
					noHit = (false);										
				} else {
					success = (false);
					wait = (false);
					fail = (true);
					error = (false);
					noHit = (false);					
				}								
			} else {
				
				if(score.getBc_score().equals("-009"))
				{
					score.setKubo_score_a("G");
					score.setKubo_score_b("1");
					score.setRate(61.50D);
					score.setRate_investor(39.98D);
					score.setLiquidity(2.5D);
					score.setOpening_commission(5.00D);
					score.setRisk_level("18.6");
					score.setKubo_rate("21.52");
					
					ELContext elContext = FacesContext.getCurrentInstance().getELContext();
					SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "sessionBean");
					
					/*modificar por una tasa variable*/
					sesion.setRate(score.getRate());
					successTitle = (false);
					
					scoringService.updateScoring(score);
					
					success = (false);
					wait = (false);
					fail = (true);
					error = (false);
					noHit = (false);					
				}
				
				srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setException(reskubo.getMessage());
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(3);
				servicecallingService.saveServiceCall(srvCall);				
			}		
		} catch( ServiceException se ) {
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setException("ServiceException:  "+se.getMessage());
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			servicecallingService.saveServiceCall(srvCall);
			
			se.printStackTrace();
			
		} catch ( RemoteException re  ) {
		
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setException("remoteException:  "+re.getMessage());
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////// R E I N T E N T A N D O ///////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////
						
			String age = (calcularEdad (naturalPerson.getDate_of_birth()) + "");			
			srvCall    = new ServiceCalling();
	
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setInfo("" 
							   + "Invocando Servicio Web getProspectRisk 2da Vez (" 
							   + credithistory.getCreditHistoryPK().getProspectus_id() + "," 
							   + score.getMx_solicitud_buro() + "," 
							   + naturalPerson.getResidence_id() + "," 
							   + naturalPerson.getGender_id() + "," 
							   + age + "," + "3227" + ")" );
			
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(1);
			
			servicecallingService.saveServiceCall(srvCall);
			
			try
			{
				WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
				WsSgbRisk               service = locator.getWsSgbRisk();
				
				BCRiskRequest request = new BCRiskRequest();
				
				request.setAge(age+"");
				request.setBursolnum(score.getMx_solicitud_buro());
				request.setBusinessType("3227");
				request.setCompanyId(naturalPerson.getNatPerPK().getCompany_id()+"");
				request.setEventId(Evento.CONSULTA_BC_EXITOSA.getId()+"");
				request.setGender((naturalPerson.getGender_id()+""));
				request.setHomeType(naturalPerson.getResidence_id()+"");
				request.setProspectId(naturalPerson.getNatPerPK().getProspectus_id()+"");
				request.setProspectIdTemp(naturalPerson.getNatPerPK().getProspectus_id()+"");
				
				SpScoreKubo reskubo= service.getProspectRisk(request);
				
				/*
				SpScoreKubo reskubo =  service.getProspectRisk(	(credithistory.getCreditHistoryPK().getProspectus_id() + ""), 
																score.getMx_solicitud_buro(), 
																naturalPerson.getResidence_id() + "", 
																naturalPerson.getGender_id() + "", 
																age, "3227");
				*/
				if( reskubo !=null && reskubo.getStatus().equals("0") )
				{
					
				}
			
			} catch(RemoteException re2) {
				
				re2.printStackTrace();
				
				srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setException( "remote Exception 2da Vuelta: " + re.getMessage());
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(3);
				
				servicecallingService.saveServiceCall(srvCall);	
			} catch(ServiceException se2) {
				
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setException( "service Exception 2da Vuelta: " + re.getMessage());
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(3);
				
				servicecallingService.saveServiceCall(srvCall);
				
				se2.printStackTrace();				
			}
			
			Writer      writer      = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			
			re.printStackTrace(printWriter);
			
			String msg = re.getMessage();
			
			Evento evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.Preaprobacion.getSGBRisk(1139): "+msg+" Error al ejecutar el servicio de Riesgo");
			notificar(evento, null,msg+" <br /> Error al ejecutar el servicio de Riesgo <br /> "+writer.toString(),null);
			
		} catch(Exception e) {
			
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String msg = e.getMessage();
			
			Evento evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.Preaprobacion.getSGBRisk(1149): "+msg+" Error al ejecutar el servicio de Riesgo");
			notificar(evento, null,msg+" <br /> Error al ejecutar el servicio de Riesgo <br /> "+writer.toString(),null);
			
		}
		
		return score;
		
	}
	
	private int calcularEdad(Date fecha) 
	{
		//String datetext = fecha;
		
		try 
		{
			Calendar birth = new GregorianCalendar();
			Calendar today = new GregorianCalendar();
			int age=0;
			int factor=0;
			//Date birthDate=new SimpleDateFormat("dd-MM-yyyy").parse(datetext);
			Date birthDate= fecha;
			Date currentDate=new Date(); //current date
			birth.setTime(birthDate);
			today.setTime(currentDate);
			if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
				if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
					if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) {
						factor = -1; //Aun no celebra su cumpleaños
					}
				} else {
					factor = -1; //Aun no celebra su cumpleaños
				}
			}
			age=(today.get(Calendar.YEAR)-birth.get(Calendar.YEAR))+factor;
			return age;
		} catch (Exception e) {
			return -1;
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
		
		success = (false);
		wait = (false);
		fail = (false);
		errorMsg = ( msg );
		error = (true);
		noHit = (false);	
	}
	
	protected void asignar_error_consulta(Exception e) 
	{
		writer      = new StringWriter();
		printWriter = new PrintWriter(writer);
		
		e.printStackTrace(printWriter);
		
		String msg    = e.getMessage();			
		Evento evento = Evento.ERROR_DESARROLLO;
		
		evento.setError("managedbeans.Preaprobacion.callWSSGB(877)");
		
		notificar(evento, null,msg+" <br /> "+writer.toString(),null);
		
		srvCall = new ServiceCalling();
		
		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		srvCall.setInfo("Error en Preaprobacio.java. callSGB()");
		srvCall.setException(msg.length()>1999?msg.substring(0,1999):msg);
		srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		srvCall.setStatus(3);
		
		servicecallingService.saveServiceCall(srvCall);
		
		success = (false);
		wait = (false);
		fail = (false);
		error = (true);
		noHit = (false);
	}
	
	private void notificar(Evento evento, Scoring score, String errormsg, ProyectLoan proyect_loan )
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
	
	private String quitaAcentos(String str)
	{		
		if( str != null ) 
		{
			
			String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) 
		    {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i			
		}
		
		return str;
	}
}
