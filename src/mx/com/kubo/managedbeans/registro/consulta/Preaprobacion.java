package mx.com.kubo.managedbeans.registro.consulta;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.rpc.ServiceException;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.notificaciones.notificables.Evento;

import com.soa.model.businessobject.SpScoreKubo;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;

@ManagedBean (name = "preaprobacion") @ViewScoped 
public final class Preaprobacion extends PreaprobacionAMO 
implements PreaprobacionIMO, Serializable
{		
	private static final long serialVersionUID = 2433361820862313007L;

	@PostConstruct
	public void init()
	{		
		System.out.println("Preaprobacion.init()");
		
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		company_id    = sesion.getCompany_id();
		prospectus_id = sesion.getProspectus_id();
									
		i = 0;
		flag = false;
						
		init_prospectus();
		init_credit_history();
		init_person();
		init_address();							
		init_phone();
		init_membership();		
		init_credit_history_DATA();						
		init_message_ERROR();
		
/*		
		if(i == 7)
		{
			consulta = new ConsultaCompletaIMP();
			consulta.setCredithistory(credithistory);
			consulta.setBlocked_person_ENABLED(blocked_person_ENABLED);
			consulta.init();
		}
*/		
	}

	public void callWSSGB()
	{			
		try
		{
			System.out.println("Preaprobacion.callWSSGB()");
			
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");				
			
			has_tarjeta_credito     = credithistory.getCreditcard_is_principal();
			has_credito_hipotecario = credithistory.getMortgage_is_principal();
			has_credito_automotriz  = credithistory.getCar_is_principal();
			
			tarjeta_credito_company = credithistory.getCreditcard_company();
			tarjeta_credito_number  = credithistory.getCreditcard_four_digits();
			
			has_credito_ENABLED = has_tarjeta_credito     != null 
							   && has_credito_hipotecario != null
							   && has_credito_automotriz  != null;
			
			tarjeta_credito_company_ENABLED = tarjeta_credito_company != null 
										  && (tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("C&A") 	!= (-1) 
										   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("CANDA") != (-1)
										   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("CYA") != (-1)
										   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("BRADES") != (-1)
										   || tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("UBURBI") != (-1));
			
			request = RequestContext.getCurrentInstance();
			request.addCallbackParam("blocked_person_ENABLED", blocked_person_ENABLED);
			
			if(!blocked_person_ENABLED)
			{			
				if(has_credito_ENABLED)
				{			
					
					locator = new WsSgbRiskServiceLocator();
					service = locator.getWsSgbRisk();
					
					user     = "";
					password = "";
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
	
					if(tarjeta_credito_company != null && (tarjeta_credito_company.toUpperCase().replaceAll(" ", "").indexOf("AZTECA") 	!= (-1)))
					{
						has_tarjeta_credito     = 0 ;
						tarjeta_credito_company = null;
						tarjeta_credito_number = "";
						credithistory.setCreditcard_four_digits("");
					}
	
					if(has_credito_hipotecario != null && credithistory.getMortgage_company() != null &&
					  (
					   credithistory.getMortgage_company().toUpperCase().replaceAll(" ", "").indexOf("FOVISSSTE") 	!= (-1) ||
					   credithistory.getMortgage_company().toUpperCase().replaceAll(" ", "").indexOf("FOVISSTE") 	!= (-1) ||
					   credithistory.getMortgage_company().toUpperCase().replaceAll(" ", "").indexOf("FOVISTE") 	!= (-1))  
				     )
					{
						
							has_credito_hipotecario = 0;
							
					}
					
					
					if(score == null)
					{		
						is_prospect_SGB_OK = creaProspectSGB();
						
						if(is_prospect_SGB_OK)
						{		
							asignar_credit_history_attempt();
							
						} else {
								setSuccess(false);
								setWait(false);
								setFail(false);
								setErrorMsg(" Error en el proceso de alta en Mesa de control comuniquese a kubo.financiero. Disculpe las molestias. ");
								setError(true);
								setNoHit(false);
						}
					}	
					
					asignar_score();
					
					init_access();					
					
				} else {	
					
					asignar_msg_credito_DISABLED();
				}
			}
			
		} catch(Exception e) {
			
			asignar_error_consulta(e);
		}		
	}	


	private void asignar_score() 
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
					setSuccessTitle(false);							
				} else {
					
					setSuccessTitle(true);
					
				}
			}
				
			setSuccess(true);
			setWait(false);
			setFail(false);
			setError(false);
			setNoHit(false);
				
			asignar_datos_simulacion();
		}	
	}

	private Scoring getSGBRisk(Scoring score)
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
					setSuccessTitle(false);					
				}else if((reskubo.getVocalkubo1()+reskubo.getVocalkubo2()).equals("	E6"))
				{
					score.setKubo_score_a("F");
					score.setKubo_score_b("1");
					reskubo.setVocalkubo1("F");
					reskubo.setVocalkubo2("1");
					setSuccessTitle(false);					
				} else {
					//parte original
					score.setKubo_score_a(reskubo.getVocalkubo1());
					score.setKubo_score_b(reskubo.getVocalkubo2());
					setSuccessTitle(true);
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
					
					setSuccess(true);
					setWait(false);
					setFail(false);
					setError(false);
					setNoHit(false);										
				} else {
					setSuccess(false);
					setWait(false);
					setFail(true);
					setError(false);
					setNoHit(false);					
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
					setSuccessTitle(false);
					
					scoringService.updateScoring(score);
					
					setSuccess(false);
					setWait(false);
					setFail(true);
					setError(false);
					setNoHit(false);					
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
	
	private void asignar_error_consulta(Exception e) 
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
		
		setSuccess(false);
		setWait(false);
		setFail(false);
		setError(true);
		setNoHit(false);
	}
	
	public  int calcularEdad(Date fecha) 
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
		
}