package mx.com.kubo.managedbeans.portal;

import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.controller.behaviorProspectus.BehaviorCheck;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.tools.Utilities;

public class IniciaSessionAMO extends IniciaSessionDMO 
{	
	@SuppressWarnings("unchecked")
	protected void init_baja_usuario_firmado() 
	{		
		http_session = (HttpSession) external.getSession(false);
		
		servlet = http_session.getServletContext();
		
		usuarios_firmados = (Hashtable<String, Hashtable<String, HttpSession>>) servlet.getAttribute("usuariosFirmados");
		
		lista_http_session_keys = usuarios_firmados.keys();		
		
		prospectus_PK = new ProspectusPK(prospectus_id, company_id);
		
		prospectus = service_prospectus.getProspectusById(prospectus_PK);
		
		tracking_id = prospectus.getTracking_id();
		
		while(lista_http_session_keys.hasMoreElements()) 
		{					
			http_session_key = lista_http_session_keys.nextElement();
			
			usuario_http_session = usuarios_firmados.get(http_session_key);
			
			http_session = usuario_http_session.get(tracking_id);
			
			if(http_session != null)
			{
				usuarios_firmados.remove(http_session.getId());
				
				http_session.invalidate();	
				
				remove_OK = true;
				
				break;
			}		
		}
	}
	
	protected void init_access(int screen_id) 
	{
		access = new Access();
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		access.setUser_agent(user_agent);
		access.setDevice_info(device_info);
		access.setUrl_access( sesion.getUrl_access() );
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		access.setAccess_datetime(new Date());
		access.setIpaddress(ipAddressClient);
		service_access.add(access, false);
	}
	
	protected void init_sesion() 
	{
		nameb       = external.getRequestParameterMap().get("browser_name");
		verionb     = external.getRequestParameterMap().get("browser_version");
		osb         = external.getRequestParameterMap().get("browser_OS");						
		width       = external.getRequestParameterMap().get("browser_width");			
		height      = external.getRequestParameterMap().get("browser_height");
		user_agent  = external.getRequestParameterMap().get("user_agent");
		device_info = external.getRequestParameterMap().get("device_info");
	
		ipAddressClient  = http_request.getHeader("X-FORWARDED-FOR");
	    
		if(ipAddressClient == null)
		{
	    	ipAddressClient = http_request.getRemoteAddr();
		}

		sesion.setNamebrawser(nameb);
		sesion.setVersionbrawser(verionb);
		sesion.setOsbrawser(osb);
		sesion.setPartner(partner);		
		sesion.setIP_address_client(ipAddressClient);
		sesion.setBrowser_width (Integer.parseInt(width  == null ? "0" : width  == "" ? "0" : width));
		sesion.setBrowser_height(Integer.parseInt(height == null ? "0" : height == "" ? "0" : height));
		sesion.setUser_agent(user_agent);
		sesion.setDevice_info(device_info);
		
		version_description = systemparamservice.getVersion_description();
		
		sesion.setVersion_description(version_description);
	}

	protected void init_foto() 
	{
		String photo = "img/sinimagen.jpg";
		boolean existeImagen = false;
		
		ProspectusPK pk = new ProspectusPK(sesion.getProspectus_id(), sesion.getCompany_id());
		
		prospectus = service_prospectus.getProspectusById(pk);
		
		if (prospectus.getImage() != null && prospectus.getImage().length() > 0)
		{
			photo = "cia_"
					+ prospectus.getProspectusPK().getCompany_id()
					+ "/pros_"
					+ prospectus.getProspectusPK().getProspectus_id()
					+ "/photo/" + prospectus.getImage().split("\\.")[0]+"_thump_150"+"."+prospectus.getImage().split("\\.")[1];
			
			if(!existeArchivo(photo))
			{
				photo = "img/sinimagen.jpg";
				existeImagen = false;
			}else{
				existeImagen = true;
			}
		}
		
		sesion.setUsrImg(photo);
		sesion.setHaveUsrImg(existeImagen);
	}
	
	private boolean existeArchivo(String other)
	{
		//faces = FacesContext.getCurrentInstance();
		//external = faces.getExternalContext();
		
		String hacia = external.getRealPath("/resources/documents/");
		String path  = hacia + "/" + other;
		
		File file = new File(path);
		
		return  file.exists();
	}
	
	protected void init_acreditado() 
	{
		
		BehaviorCheck bc = new BehaviorCheck();
		
		ipAddressClient  = http_request.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)  
		{
	    	ipAddressClient = http_request.getRemoteAddr();  	 
		}
		
		bc.checkProcess(sesion.getCompany_id(), sesion.getProspectus_id(), ipAddressClient);
		
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id(sesion.getCompany_id());
		system_param_PK.setSystem_param_id(ESTADO_CUENTA_ENABLED);
		
		system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
		
		
			
		if(width==null)
		{
			width = "1000";
		}
						
		if((system_param.getValue()!=null && system_param.getValue().equals("S")) || (system_param.getValue()!=null && system_param.getValue().equals("N") && membership.getIs_active().equals(1)))
		{								
			if(person.getSafi_client_id() != null && person.getSafi_client_id().trim().length() > 0)
			{		
				
				List<ProyectLoan> lstpy = proyectloanservice.getProyectLoanListByProspectusOrderByProyectLoan(person.getNatPerPK().getProspectus_id(), person.getNatPerPK().getCompany_id());
				
				if( lstpy == null || lstpy.size() == 0 ){
					action =  "registrar";
				}else{
				
					boolean flagClient  = false;
					
					for( ProyectLoan p : lstpy ){
						
						if( p.getStatus_id() == 3 ){
							
							flagClient = true;
							break;
						}
						
					}
					
					if( flagClient ){
						
						System.out.println("Session iniciada Satisfactoriamente!! ");
						
						action =  "edocuenta";
						
					}else{
						
						action =  "registrar";
					
					}
				
				}
				
		} else {
			
			System.out.println("Session iniciada Satisfactoriamente!! ");
			
			proyectloan = proyectloanservice.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if(init_rechazo_automatico_ENABLED()){
				
				action =  "rechazadoPantalla";
				
			}else{
			
				action =  "registrar";
			}
			
		}
			
		} else {
			
			//System.out.println("Session iniciada Satisfactoriamente!! ");
				
			action =  "registrar";
		}
		
		if(action.equals( "registrar") )
		{	
			
			if(sesion.getLastPage()!= null && sesion.getLastPage().equals("registro/cierre") )
			{
				inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),true);
				
			} else {
				
				inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),false);
			}
			
		}
		
	}
	
	private boolean init_rechazo_automatico_ENABLED() 
	{
		boolean flagRechazado = false;
		
		int SEGMENTO_RIESGO_BURO = 1;
		
		int SEGMENTO_RECHAZADO = 3;
		
		int RISK_PROCESSED = 1;
		
		//int STATUS_RECHAZADO = 11;
		
		if( proyectloan != null && proyectloan.getStatus_id() != null && proyectloan.getStatus_id().intValue() != 0 && proyectloan.getStatus_id().intValue() != 1 && proyectloan.getStatus_id().intValue() != 2 && proyectloan.getStatus_id().intValue() != 3  )
		{
//			system_param_PK = new SystemParamPK();
//			system_param_PK.setCompany_id(sesion.getCompany_id());
//			system_param_PK.setSystem_param_id(65);
//			
//			system_param = systemparamservice.loadSelectedSystemParam( system_param_PK );
//			
//			if( system_param != null )
//			{									
//				Calendar c = Calendar.getInstance();
//				c.setTime(proyectloan.getConsulting_date());
//				Calendar c2 = Calendar.getInstance();
//				c2.setTime( new Date() );
//				
//				long l = c2.getTimeInMillis() - c.getTimeInMillis();
//				
//				long dif = l /((24 * 60 * 60 * 1000)); 
//				
//				if( Integer.parseInt(system_param.getValue()) < dif )
//				{
			
			List<SegmentProspectus> lst = segment_prospectus_service.loadSegmentProspectListByType( proyectloan.getProyectloanPk().getProspectus_id() , proyectloan.getProyectloanPk().getCompany_id(), SEGMENTO_RIESGO_BURO);
			
			if( lst != null && lst.size() == 1 && lst.get(0).getPk().getSegment_id() == SEGMENTO_RECHAZADO  ){
				
				Scoring score = scoring_service.loadMaxScoringByProspectus(proyectloan.getProyectloanPk().getProspectus_id(), proyectloan.getProyectloanPk().getCompany_id());
				
				if( score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == RISK_PROCESSED  ){
					flagRechazado = true;
				}
				
				if(score == null){
					flagRechazado = true;
				}
			
			}else{
				
				if( lst == null || lst.size() == 0){
					flagRechazado = true;
				}else{
					
					/*Scoring score = scoring_service.loadMaxScoringByProspectus(proyectloan.getProyectloanPk().getProspectus_id(), proyectloan.getProyectloanPk().getCompany_id());
					
					
					if(score == null){ */
						flagRechazado = true;
					//}
					
				}
				
			}
//				}									
//			}									
		}
		
//		if( flagRechazado )
//		{									
//			init_access(RECHAZO_AUTOMATICO);
//			
//			screen_PK = new ScreenPK();
//			screen_PK.setCompany_id(sesion.getCompany_id());
//			screen_PK.setScreen_id(RECHAZO_AUTOMATICO);
//			
//			screen =  screenservice.getScreenById(screen_PK);
//			
//			sesion.setLastPage(screen.getName());
//			
//		}
		
		return flagRechazado;
		
	}

	protected void init_inversionista() 
	{
		investor_PK = new InvestorPK();
		
		investor_PK.setCompany_id(sesion.getCompany_id());
		investor_PK.setProspectus_id(sesion.getProspectus_id());
		
		investor = investorservice.getInvestorById(investor_PK);
		
		if(investor == null)
		{								
			investor = new Investor();
			investor.setDate_approved(null);
			investor.setDate_published(null);
			investor.setPk(investor_PK);
			investor.setStatus_id(0);
			/*
			if(investorservice.addInvestor(investor))
			{
				System.out.println("Inversionista Dado de alta: "+sesion.getProspectus_id());
				
			} else {
				
				System.out.println("Error Alta Inversionista: "+sesion.getProspectus_id());
			} */
		}
		
		countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		
		if(countList != null && countList.size() > 0)
		{
			boolean inversiones = false;
		
			for(SavingAccount count : countList)
			{
				if(count.getStatus() != null && count.getStatus() == 1)
				{
					inversiones = true;
					break;
					
				} else if( count.getStatus() != null && count.getStatus() == 0)
				{
					flagAccount = true;
				}
			}
			
			if(flagAccount)
			{									
				for(SavingAccount count : countList)
				{										
					if(count.getStatus() == null || count.getStatus() == 0)
					{
						boolean verify_account_ENABLED = false;
						
						verify_account_ENABLED = savingaccountservice.verifyAccount(count);
						
						if(!inversiones)
						{
							inversiones = verify_account_ENABLED;
						}
						
						if(inversiones)
						{
							InvestorPK invPk2 = new InvestorPK();
							
							invPk2.setCompany_id(sesion.getCompany_id());
							invPk2.setProspectus_id(sesion.getProspectus_id());
							
							Investor inv2 = investorservice.getInvestorById(invPk2);
							inv2.setStatus_id(3);
							
							investorservice.updateInvestor(inv2);
													
							init_client();							
						}											
					}										
				}									
			}
			
			if(inversiones)
			{
				init_access(INVERSIONES_SALDOS_MOVIMIENTOS);								
				
				action =  "inversiones";
				
			} else {				
				
				if(investor != null && investor.getStatus_id() != 0)
				{
					init_access(INVERSIONES_ALTA_CLABE_INTERBANCARIA);
					
					screen_PK = new ScreenPK();
					screen_PK.setCompany_id(sesion.getCompany_id());
					screen_PK.setScreen_id(INVERSIONES_ALTA_CLABE_INTERBANCARIA);
					
					screen = screenservice.getScreenById(screen_PK);
					
					sesion.setLastPage(screen.getName());
				}
					
				action =  "registrar";
			}
						
		} else {
			
			if(!sesion.isBlochHeader())
			{
				this.action = "registrar";
				
			} 
			
			action =  "registrar";
		}
	}
	
	private void init_client() 
	{
		if( !sesion.isIsClient() )
		{																				
			cliente_PK = new ClientsPK();
			
			cliente = new Clients();
			
			cliente.setPk(cliente_PK);
			cliente.setSafi_client_id(person.getSafi_client_id());
			
			clientsservice.saveClient(cliente);
			
			sesion.setIsClient(true);								
		}
	}

	protected void init_membership() 
	{
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(sesion.getCompany_id());
		membership_PK.setProspectus_id(sesion.getProspectus_id());
		
		membership = membershipService.getMembershipById(membership_PK);
		
		promotor = membershipService.getPromotor(sesion.getProspectus_id());
		
		if(promotor != null)
		{
			sesion.setPromotor_id(promotor.getPromotorPk().getPromotor_id());
		}
		
		person = membership.getPerson();
	}
	

	public boolean isWrongKuboPerson()
	{
		faces = FacesContext.getCurrentInstance();	
		
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(sesion.getArea()=='M' && sesion.getEmail().indexOf("@kubofinanciero.com") == (-1))
		{
			return true;
		}
		
		return false;
	}
	
	protected boolean isPassValid( String pwd )
	{
		Integer days = 0;
		boolean flag = false;
		
		pwd = Utilities.encrypt(pwd);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, "sessionBean");
		
		if( sesion.getArea() == 'L' )
		{
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(19); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			days = Integer.parseInt(sysParam.getValue());
		}
		
		if( sesion.getArea() == 'I' )
		{
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(20); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			days = Integer.parseInt(sysParam.getValue());
		}
		
		if( sesion.getArea() == 'M' )
		{
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(21); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			days = Integer.parseInt(sysParam.getValue());
		}
			
		if( sesion.isIsClient() && (membership.getIs_client_pass() == null || membership.getIs_client_pass().equals("N")) ){
			
			flag = false;
			
			changeClientPass = true;
			
		}else if(days != (-1)){
		
					changeClientPass = false;
			
					PasswordHistory pwdH =  passwordhistoryservice.getPasswordHistorySelec(pwd, sesion.getProspectus_id(), sesion.getCompany_id());
					
					if( pwdH != null ){
						
						Date now = new Date();
						
						Long diferencia= ( now.getTime() - pwdH.getDate_changed().getTime() )/ MILLSECS_PER_DAY;
						
						if(diferencia > days){
							
							flag = false;
							
						}else{
							
							flag = true;
							
						}
						
					}else{
						
						PasswordHistoryPK pwdhpk = new PasswordHistoryPK();
						pwdhpk.setCompany_id(sesion.getCompany_id());
						pwdhpk.setProspectus_id(sesion.getProspectus_id());
						
						pwdH = new PasswordHistory();
						pwdH.setPassword(pwd);
						pwdH.setDate_changed(new Date());
						pwdH.setPwdhpk(pwdhpk);
						
						if( passwordhistoryservice.savePasswordHistory(pwdH) ){
							flag = true;
						}
						else{
							flag = false;
						}
					}
			}else{
				changeClientPass = false;
				flag = true;
			}
			
			return flag;
	}
	
	
	protected void inicializaSimulador(Integer company_id,Integer prospectus_id, boolean  isSafi)
	{
		faces     = FacesContext.getCurrentInstance();
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
				
		simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
		sesion    = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(prospectus_id, company_id) ;
		
		if(sim != null)
		{
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			
			if(sesion.getRate()!= null)
			{
				simulator.setTasaTotal(sesion.getRate());
				
			} else {
				
				simulator.setTasaTotal(61.50D);
			}
			
			if( sim.getTerm_id() != null )
			{
				simulator.setTerm_id(sim.getTerm_id());
			}
			
			if( sim.getPurpose_id() != null )
			{
				simulator.setPurpose_id(sim.getPurpose_id());
			}
		}
		
		simulator.simulaCred( isSafi );		
	}
	
	
	protected void checaResultado( SessionBean session )
	{
		
		warningUser = "";
		displayWarningUser = "none";
		
		if(session.isFailedUser())
		{
//			setForwardMail("");
			displayWarningUser = "block";
			warningUser = "<span>El usuario <b>" + session.getEmail() + "</b> no existe.</span>";
			
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
//			setForwardMail("");
			displayWarningUser = "block";
			warningUser = "<span>Contraseña incorrecta.</span>";
			setEmail(getEmail());
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
//			setForwardMail("");
			displayWarningUser = "block";
			warningUser = "<span >El usuario <b>" + session.getEmail() + "</b> ya cuenta con una sesión abierta.</span>";
			
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isFailedActive()){
//			setForwardMail(header.getUser());
			displayWarningUser = "block";
			warningUser = "<script>  alertEmailConfimation();</script>"+ "<span class='correoSesion' style='display:none;'>" +session.getTemporalUser()
						    + "</span>"
						    + "<script>"
							+ "$('#email').addClass('requiredClass');"
							+ "varEmail=false;"
							+ "</script>";
			setEmail(getEmail());
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
			
		//	flagActive = true;
		}
		
		if(session.isCanceled())
		{
//			setForwardMail(header.getUser());
			displayWarningUser = "block";
			
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
			warningUser = "<span style='font-size: .8em; color: #ff0000;'>La cuenta <b>"
					    + session.getTemporalUser()+"</b> está cancelada.<br />" 
					    + "*Si existe un error o deseas reactivarla de nuevo ponte en contacto con nosotros al teléfono: <b>"
					    + recurso.getString("Kubo_telefono")
					    + "</b>  </span> " ;
		
			
			email =  getEmail();
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
//			setForwardMail("");
			displayWarningUser = "block";
			
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
			
			warningUser = "<span>La cuenta '<b>"+getEmail()+"</b>' tiene un error en su número de folio, póngase en contacto con <b>soporte-kubo</b> para solucionar el problema. <b>"+recurso.getString("Kubo_telefono")+"</b>  </span>";
			
			session.setTemporalUser("");
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setCanceled(false);
		}
		
		if(session.isHoldMail()){
			session.setFailedActive(false);
			session.setFailedPass(false);
			session.setFailedUser(false);
			session.setSessionUsed(false);
			session.setFailedTracking(false);
			session.setHoldMail(false);
			session.setCanceled(false);
			setEmail( getEmail() );
			//header.setUser(session.getTemporalUser());
		}
	}
	
}
