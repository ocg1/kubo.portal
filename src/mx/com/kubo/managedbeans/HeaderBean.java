package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.Utilities;

@ManagedBean(name = "headerBean") @ViewScoped
public final class HeaderBean extends HeaderBeanAMO
implements Serializable, HeaderBeanIMO
{	
	private static final long serialVersionUID = 2478904289925876158L;

	@PostConstruct
	public void init()
	{		
		try
		{			
			
			
			if(faces == null){
				faces     = FacesContext.getCurrentInstance();
			}
			if(faces != null && elContext == null){
				elContext = faces.getELContext();
			}
			if( faces != null && external == null ){
				external  = faces.getExternalContext();
			}
			if(faces != null && resolver == null){
				resolver  = faces.getApplication().getELResolver();
			}
			
			if( resolver != null ){
				
				sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
				
				if( sesion.getProspectus_id() != null ){
				
					//nav = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
					
				}
			}
			
			if( external != null ){
			
				active = (String) external.getRequestParameterMap().get("counter");		
				role   = (String) external.getRequestParameterMap().get("role");
			
			}
			
			SystemParamPK sPK =  new SystemParamPK();
			
			sPK.setCompany_id(1);
			sPK.setSystem_param_id(91);// Registro de Form Analytics
			
			SystemParam sys = service_system_param.loadSelectedSystemParam(sPK);
			
			if( sys != null && sys.getValue() != null ){
				enabledAnalytics = sys.getValue();
			}
			
			//System.out.println("HeaderBean.init(): OK");
		
		} catch( Exception e ) {
			
			//System.out.println("HeaderBean.init(): ERROR");
			
			e.printStackTrace();						
		}
		
		if(active != null)
		{
			setActiveStr(active);
		}

		if(role != null && role.equals("investment"))
		{			
			sesion.setArea('I');
			sesion.setAreaReg("I");			
		}
		
		version_description = service_system_param.getVersion_description();
		
		
		
		if( sesion != null &&  sesion.getProspectus_id() != null)
		{
			sesion.setVersion_description(version_description);
			
			name                = sesion.getNombre();
			trackId             = sesion.getTrackId();
			fecha_acceso_ACTUAL = sesion.getFecha_acceso_ACTUAL();
			fecha_acceso_LAST   = sesion.getFecha_acceso_LAST();
			
			header  = "headerIn.xhtml";
			
			if(sesion.getArea() != null && sesion.getArea().equals('M'))
			{
				if(!sesion.isBlochHeader())
				{
					action = "controlTable";
					
				} else {
					
					action = "";
				}
				
			} else {
				
				if(sesion.getArea() != null && sesion.getArea().equals('I'))
				{
					countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
				
					if(countList != null && countList.size() > 0)
					{
						boolean inversiones= false;
						
						for(SavingAccount count : countList)
						{
							if(count.getStatus()!=null&&count.getStatus()==1)
							{
								inversiones=true;
								break;
							}
						}
					
						if(inversiones)
						{													
							//service_access.add(INVERSIONES, 0, sesion);
							
							if(!sesion.isBlochHeader())
							{
								this.action="inversiones";
							} else {
								this.action="";
							}
							
						} else {
							
							if(!sesion.isBlochHeader())
							{
								this.action = "registrar";
								
							} else {
								
								this.action = "";
							}
							
						}
					}
				
				} else {
				
					sysPK = new SystemParamPK();
				
					sysPK.setCompany_id(sesion.getCompany_id());
					sysPK.setSystem_param_id(13);//Bandera que indica si se encuentra disponible la pantalla de Estado de Cuenta
					
					sysParam = service_system_param.loadSelectedSystemParam(sysPK);
				
				
					if(sysParam.getValue() != null && sysParam.getValue().equals("S"))
					{
						MembershipPK mpk = new MembershipPK();
						
						mpk.setCompany_id(sesion.getCompany_id());
						mpk.setProspectus_id(sesion.getProspectus_id());
						Membership p = membershipService.getMembershipById(mpk);
						
						if( p.getPerson().getSafi_client_id()!=null && p.getPerson().getSafi_client_id().trim().length()>0  )
						{
							if(!sesion.isBlochHeader())
							{
								this.action="edocuenta";
							} else {
								this.action="";
							}
							
						} else {
							
							if(!sesion.isBlochHeader())
							{
								this.action="registrar";
							} else {
								this.action="";
							}
						}
					
					} else {
						
						if(!sesion.isBlochHeader())
						{
							this.action = "registrar";
							
						} else {
							
							this.action = "";
						}
					}
				}	
				
			}
		}
		
		if( sesion != null && (sesion.isFailedPass() || sesion.isFailedActive() || sesion.isCanceled() ))
		{
			setUser(sesion.getTemporalUser());
		} else {
			if (sesion != null) {
			sesion.setTemporalUser(null);
			}
		}
		
		//System.out.println( "HeaderBean: INIT() Finaliza OK" );
		
	}
	
	public final String iniciaSesion()
	{						
		faces     = FacesContext.getCurrentInstance();	
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		servlet_request = (HttpServletRequest) external.getRequest();
		
		String usr = getUser();
		String pwd = getPassword();
		
		boolean flagAccount = false;
		/*
		System.out.println("");
		System.out.println( "***********************************INICIANDO**SESSION******************************" );
		System.out.println("");
		*/
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		partner      = sesion.getPartner();      //nos traemos el valor del Partner si es que existe
		temporalUser = sesion.getTemporalUser(); //nos traemos el usuario temporal
		sessionUsedB = sesion.isSessionUsed();
		userAgent    = sesion.getUser_agent();
		deviceInfo   = sesion.getDevice_info();
		String isnew = sesion.getIsnew();
		userAgent  = external.getRequestParameterMap().get("user_agent");
						
		sessionUsed = (HttpSession) external.getSession(false);
		sessionUsed.invalidate();
		
		sessionUsed = (HttpSession) external.getSession(true);
		sessionUsed.setAttribute("new", Boolean.FALSE);
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sesion.setPartner(partner); //lo volvemos a poner para que no se pierda
		
		if( userAgent != null && userAgent.trim().length() > 0  ){
			sesion.setUser_agent(userAgent);
		}
		
		sesion.setDevice_info(deviceInfo);
		sesion.setTemporalUser(temporalUser);
		sesion.setSessionUsed(sessionUsedB);
		sesion.setEmail(usr);	
		sesion.setIsnew(isnew);
		if(loginService.verifySession(sesion, usr,pwd))
		{		

			if(!isWrongKuboPerson())
			{				

				if(isPassValid( pwd ) )
				{		
//					System.out.println("");
//					System.out.println( "***********************************if*03******************************" );
//					System.out.println("");
					nameb   = external.getRequestParameterMap().get("namebraw");
					verionb = external.getRequestParameterMap().get("verbraw");
					osb     = external.getRequestParameterMap().get("osbraw");						
					width   = external.getRequestParameterMap().get("browser_width");
					height  = external.getRequestParameterMap().get("browser_height");
					
					ipAddressClient  = servlet_request.getHeader("X-FORWARDED-FOR");  
				    
					if(ipAddressClient == null)
					{
				    	ipAddressClient = servlet_request.getRemoteAddr();
					}
					
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					
//					System.out.println( "Accesso desde la IP: "+ ipAddressClient);
//					
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
					
					sesion.setNamebrawser(nameb);
					sesion.setVersionbrawser(verionb);
					sesion.setOsbrawser(osb);
					sesion.setPartner(partner);				
					sesion.setIP_address_client(ipAddressClient);
					sesion.setBrowser_width (Integer.parseInt(width  == null ? "0" : width  == "" ? "0" : width));
					sesion.setBrowser_height(Integer.parseInt(height == null ? "0" : height == "" ? "0" : height));
											
					setTrackId(sesion.getTrackId());
					
					menu = "Entrar";
					
					this.name = sesion.getNombre();
					
					setPassword("");
		
					setWarningUserMsg("");
					setWarninguserdisp(false);
					setWarninguserbut(true);
					
					fecha_acceso_ACTUAL = sesion.getFecha_acceso_ACTUAL();
					fecha_acceso_LAST   = sesion.getFecha_acceso_LAST();
						
					this.header = "headerIn.xhtml";
						
					if(sesion.getArea().equals('M'))
					{							
						if(!sesion.isBlochHeader())
						{
							this.action = "controlTable";
							
						} else { 
							
							this.action = "";
						}
						
						return "controlTable";
							
					} else if(sesion.getArea().equals('I')) {
							
						Calendar initInv_1 = Calendar.getInstance();
						initInv_1.setTime(new Date());
						
						InvestorPK invPk = new InvestorPK();
						
						invPk.setCompany_id(sesion.getCompany_id());
						invPk.setProspectus_id(sesion.getProspectus_id());
						
						Investor inv = investorservice.getInvestorById(invPk);
							
						if(inv == null)
						{								
							inv = new Investor();
							inv.setDate_approved(null);
							inv.setDate_published(null);
							inv.setPk(invPk);
							inv.setStatus_id(0);
							/*
							if(investorservice.addInvestor(inv))
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
										boolean f = false;
										
										f = savingaccountservice.verifyAccount(count);
										
										if(!inversiones)
										{
											inversiones = f;
										}
										
										if(inversiones)
										{
											InvestorPK invPk2 = new InvestorPK();
											
											invPk2.setCompany_id(sesion.getCompany_id());
											invPk2.setProspectus_id(sesion.getProspectus_id());
											
											Investor inv2 = investorservice.getInvestorById(invPk2);
											inv2.setStatus_id(3);
											investorservice.updateInvestor(inv2);
										}											
									}										
								}									
							}
								
							if(inversiones)
							{
								//service_access.add(INVERSIONES, 0, sesion);
								
								if(!sesion.isBlochHeader())
								{
									this.action="inversiones";
									
								} else {
									
									this.action="";
								}
								
								Calendar intInv_2 = Calendar.getInstance();
								intInv_2.setTime(new Date());
								long difBr_2 = intInv_2.getTimeInMillis() - initInv_1.getTimeInMillis();
								
								//System.out.println("Tiempo en inicializar Inversionista: "+difBr_2+" milisegundos");
								
								return "inversiones";
								
							} else {
								
								if(!sesion.isBlochHeader())
								{
									this.action = "/";
									
								} else {
									
									this.action = "";
								}
									
								if(inv != null && inv.getStatus_id() != 0)
								{
									Access access = new Access();
									access.setCompany_id     (sesion.getCompany_id());
									access.setProspectus_id  (sesion.getProspectus_id());
									access.setOp_system      (sesion.getOsbrawser());
									access.setHorizontal_size(sesion.getBrowser_width());
									access.setVertical_size  (sesion.getBrowser_height());
									access.setIpaddress      (sesion.getIP_address_client());
									access.setWeb_browser    (sesion.getNamebrawser());
									access.setWeb_browser_version(sesion.getVersionbrawser());
									access.setVersion_description(sesion.getVersion_description());
									
									access.setUser_agent      (sesion.getUser_agent());
									access.setDevice_info    (sesion.getDevice_info());
									
									access.setScreen_id(18);//pantalla de mis inversiones
									access.setPercentage(0);
									access.setUrl_access( sesion.getUrl_access() );
									access.setProspectus_id_coach (sesion.getCoachProspectus_id());
									access.setAccess_from		  (sesion.getAccess_from());
									
									service_access.add(access, false);
									
									ScreenPK pk = new ScreenPK();
									pk.setCompany_id(sesion.getCompany_id());
									pk.setScreen_id(18);
									Screen  sc = screenservice.getScreenById(pk);
									sesion.setLastPage( sc.getName() );
									
								}
									
								return "registrar";
							}
							
						} else {
							
							if(!sesion.isBlochHeader())
							{
								this.action = "registrar";
								
							} else {
								
								this.action = "";
							}
							
							return "registrar";
						}
							
					} else {
							
						if(sesion.getArea().toString().equals("L"))
						{	
							
							if(sesion.getLastPage()!= null && sesion.getLastPage().equals("registro/cierre") ){
								inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),true);
							}else{
								inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),false);
							}
							
						}
						
						SystemParamPK sysPk = new SystemParamPK();
						
						sysPk.setCompany_id(sesion.getCompany_id());
						sysPk.setSystem_param_id(13);//Bandera que indica si se encuentra disponible la pantalla de Estado de Cuenta
						SystemParam thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
							
						if(width==null)
						{
							width = "1000";
						}
							
						MembershipPK mpk = new MembershipPK();
						
						mpk.setCompany_id(sesion.getCompany_id());
						mpk.setProspectus_id(sesion.getProspectus_id());
						Membership p = membershipService.getMembershipById(mpk);
							
						if((thisSystem.getValue()!=null && thisSystem.getValue().equals("S")) || 
						   (thisSystem.getValue()!=null && thisSystem.getValue().equals("N") && p.getIs_active().equals(1)))
						{								
							if(p.getPerson().getSafi_client_id() != null && p.getPerson().getSafi_client_id().trim().length() > 0)
							{									
								log.info("Session iniciada Satisfactoriamente!! ");
								
								if(!sesion.isBlochHeader())
								{
									this.action="edocuenta";
									
								} else {
									
									this.action="";
								}
								
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("-----------------------------width:"+width+"-----------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
								
									
//								if(Integer.parseInt(width) < 590)
//								{
//									this.action = "mobileRegistro";
//									
//									sesion.setMobile(true);
//									
//									return "mobileRegistro";
//									
//								} else {
									
									return "edocuenta";
								//}
									
							} else {
								
								log.info("Session iniciada Satisfactoriamente!! ");
								
								ProyectLoan proyectloan = proyectloanservice.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
								
								boolean flagRechazado = false;
								
								if( proyectloan != null && proyectloan.getStatus_id() != null && proyectloan.getStatus_id() ==11 ){
									SystemParamPK spk = new SystemParamPK();
									spk.setCompany_id(sesion.getCompany_id());
									spk.setSystem_param_id(65);
									SystemParam sys = service_system_param.loadSelectedSystemParam( spk );
									
									if( sys != null ){
										
										Calendar c = Calendar.getInstance();
										c.setTime(proyectloan.getConsulting_date());
										Calendar c2 = Calendar.getInstance();
										c2.setTime( new Date() );
										
										long l = c2.getTimeInMillis() - c.getTimeInMillis();
										
										long dif = l /((24 * 60 * 60 * 1000)); 
										
										if( Integer.parseInt(sys.getValue()) < dif ){
											flagRechazado = true;
										}
										
									}
									
								}
								
								if( flagRechazado )
								{									
									Access access = new Access();
									access.setCompany_id     (sesion.getCompany_id());
									access.setProspectus_id  (sesion.getProspectus_id());
									access.setOp_system      (sesion.getOsbrawser());
									access.setHorizontal_size(sesion.getBrowser_width());
									access.setVertical_size  (sesion.getBrowser_height());
									access.setIpaddress      (sesion.getIP_address_client());
									access.setWeb_browser    (sesion.getNamebrawser());									
									access.setUser_agent      (sesion.getUser_agent());
									access.setDevice_info    (sesion.getDevice_info());
									access.setScreen_id(6);//pantalla de rechazo automático
									access.setPercentage(0);
									access.setUrl_access( sesion.getUrl_access() );
									access.setProspectus_id_coach (sesion.getCoachProspectus_id());
									access.setAccess_from		  (sesion.getAccess_from());
									access.setWeb_browser_version(sesion.getVersionbrawser());
									access.setVersion_description(sesion.getVersion_description());
									
									service_access.add(access, false);
									
									ScreenPK scpk = new ScreenPK();
									scpk.setCompany_id(sesion.getCompany_id());
									scpk.setScreen_id(6);
									Screen screen =  screenservice.getScreenById(scpk);
									
									sesion.setLastPage(screen.getName());									
								}
								
								if(!sesion.isBlochHeader())
								{
									this.action = "registrar";
									
								} else {
									
									this.action = "";
								}
									
								
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("-----------------------------width:"+width+"-----------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
								
//								if(Integer.parseInt(width) < 590)
//								{
//									this.action = "mobileRegistro";
//									
//									sesion.setMobile(true);
//									
//									return "mobileRegistro";
//									
//								} else {
									return "registrar";
								//}
							}
								
						} else {
							
							log.info("Session iniciada Satisfactoriamente!! ");
							
							if(!sesion.isBlochHeader())
							{
								this.action = "registrar";
								
							} else {
								
								this.action = "";
							}
							/*	
							System.out.println("---------------------------------------------------------------------");
							System.out.println("---------------------------------------------------------------------");
							System.out.println("-----------------------------width:"+width+"-----------------------------");
							System.out.println("---------------------------------------------------------------------");
							System.out.println("---------------------------------------------------------------------");
							*/
//							if(Integer.parseInt(width) < 590)
//							{
//								this.action = "mobileRegistro";
//								
//								sesion.setMobile(true);
//								
//								return "mobileRegistro";
//								
//							} else {
								
								return "registrar";
							//}								
						}
					}	
						
				} else {
					
					log.info("Session iniciada Satisfactoriamente!! ");
					
					this.action = "";
					
					sesion.setBlochHeader(true);
					
					return "changePassword";						
				}	
				
			} else {
				
				this.action = "Entar";
				
				sesion.sessionOut();
				
				return "mensaje";				
			}
			
		} else {
			
			sesion.setPartner(partner);
			
//			sesion.setTemporalUser(temporalUser);
//			sesion.setSessionUsed(sessionUsedB);
			
			setUser(sesion.getTemporalUser());
			setPassword("");
			addMessage("Nombre de usuario o contraseña incorrecta, verifique tambien que su cuenta se encuentre activa.");
			
			return "access";
		}
	}

	public String SignOut()
	{
		
		try{
		
			faces     = FacesContext.getCurrentInstance();
			external  = faces.getExternalContext();
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			
			sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			sesion.setLog_out_ENABLED(true);
			
			String partner = sesion.getPartner();
			
			this.menu = "Entrar";
			this.user = sesion.getNickname();
			this.header = "headerOut.xhtml";
			
			external.getSessionMap().remove("NavigationBean");
			
			simulator = (Simulator) resolver.getValue(elContext, null, "simulator");		
			simulator.clearSim();
			
			sessionUsed = (HttpSession) external.getSession(false);	
			
			String sessId = sessionUsed.getId();
			
			sessionUsed.invalidate();
			
			ServletContext servlet     = sessionUsed.getServletContext();
			
			Hashtable<String, String> ht = (Hashtable<String, String>)servlet.getAttribute( "logOutuser" );
			
			//System.out.println( "Session LogOut: " + sessId );
			
			if( ht != null && ht.containsKey( sessId) ){
				//System.out.println( "Quitando session : " + sessId );
				ht.remove(sessId);
			}else{
				//System.out.println( "Session No encontrada : " + sessId );
			}
			
			try{
			
			sessionUsed = (HttpSession) external.getSession(true);
			sessionUsed.setAttribute("new", Boolean.FALSE);  
			sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			sesion.sessionOut();
			sesion.setLog_out_ENABLED(false);
			sesion.setPartner(partner);
			
			
			}catch( IllegalStateException i ){
				
				sessionUsed = null;
				sesion.sessionOut();
				sesion.setLog_out_ENABLED(false);
				sesion.setPartner(partner);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
		
		}catch( IllegalStateException ie ){
			
			sessionUsed = null;
			sesion.sessionOut();
			sesion.setLog_out_ENABLED(false);
			sesion.setPartner(partner);
			
		}catch(Exception ee){
			
			ee.printStackTrace();
			
		}
		
		return "kubofinanciero";
	}
		
	public void validaMailNum()
	{		
		if(!loginService.validateEmailActNum(getActiveStr(), getUser()))
		{
			setErrorMail("<span style='font-size: 1.1em; color: #ff0000;'>El correo electrónico proporcionado es inválido</span>");
			setErrorMailDis("block");
			setValMail("0");
			
		} else {
			
			setErrorMail("");
			setErrorMailDis("none");
			setValMail("1");
		}		
	}
	
	public void validaPassword()
	{
		if(!loginService.validateEmailPass(getActiveStr(), getUser(),getPassword()))
		{
			setErrorPass("<span style='font-size: 1.1em; color: #ff0000;'>La contraseña proporcionada es inválida</span>");
			setErrorPassDis("block");
			setValPass("0");
		} else {
			setErrorPass("");
			setErrorPassDis("none");
			setValPass("1");
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public void validateUser()
	{		
		if(getUser()!=null&&getUser().length()>0)
		{
			Hashtable res = loginService.validateUser( getUser());
			boolean valido = (Boolean)res.get("valido");
			String msg = (String)res.get("msg");
			
			if(msg.length()>0)
			{
				setWarningUserMsg("<span style='font-size: .8em; color: #ff0000;'>"+msg+"</span>");
				setWarninguserdisp(true);
				
			}else{
				
				setWarningUserMsg("");
				setWarninguserdisp(false);
				
			}
			
			setWarninguserbut(valido);
			
						
		}
	}
	
	public boolean isWrongKuboPerson()
	{
		faces = FacesContext.getCurrentInstance();	
		
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(sesion.getArea()=='M' && sesion.getEmail().indexOf("@kubofinanciero.com") == (-1))
		{
			return true;
		}
		
		return false;
	}
	
	public void activeSession()
	{
		log.info("activeSession pass= " + Utilities.encrypt(getAnswer()));
						
//		if(loginService.activeSession(getUser(),getPassword(),getActiveStr()))
//		{
//			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//			SessionBean sesion 
//			    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
//			        .getELResolver().getValue(elContext, null, "sessionBean");
//			this.menu = "Entrar";
//			this.user = sesion.getNickname();
//			this.header = "headerIn.xhtml";
//			
//			setPassword("");
//			
			///// set SecQuestPool
			
			SecQuestPoolPK secPK = new SecQuestPoolPK();
			
			secPK.setCompany_id(sesion.getCompany_id());
			secPK.setProspectus_id(sesion.getProspectus_id());
			secPK.setSecurity_question_id(getSeqQuest());
			
			SecurityQuestionPool secQuestPool = new SecurityQuestionPool();
			
			secQuestPool.setAnswer(Utilities.encrypt(answer.toUpperCase()));
			secQuestPool.setDate_used(null);
			secQuestPool.setPk(secPK);
			
			secquestpoolservice.saveSecQuest(secQuestPool);
			
			/*
			 * 
			 */
			
			secPK = new SecQuestPoolPK();
			
			secPK.setCompany_id(sesion.getCompany_id());
			secPK.setProspectus_id(sesion.getProspectus_id());
			secPK.setSecurity_question_id(getSeqQuest2());
			
			secQuestPool = new SecurityQuestionPool();
			
			secQuestPool.setAnswer(Utilities.encrypt(answer2.toUpperCase()));
			secQuestPool.setDate_used(null);
			secQuestPool.setPk(secPK);
			
			secquestpoolservice.saveSecQuest(secQuestPool);
			
			/*
			 * 
			 */
			
			secPK = new SecQuestPoolPK();
			
			secPK.setCompany_id(sesion.getCompany_id());
			secPK.setProspectus_id(sesion.getProspectus_id());
			secPK.setSecurity_question_id(getSeqQuest3());
			
			secQuestPool = new SecurityQuestionPool();
			
			secQuestPool.setAnswer(Utilities.encrypt(answer3.toUpperCase()));
			secQuestPool.setDate_used(null);
			secQuestPool.setPk(secPK);
			
			secquestpoolservice.saveSecQuest(secQuestPool);
			
			////////////////////////
			
			secQuestPool = secquestpoolservice.getNextRandomSecQuestPool(sesion.getProspectus_id(), sesion.getCompany_id());
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id(sesion.getCompany_id());
			mpk.setProspectus_id(sesion.getProspectus_id());
			Membership p = membershipService.getMembershipById(mpk);
			p.setAnswer(secQuestPool.getAnswer());
			p.setSecurity_question_id(secQuestPool.getPk().getSecurity_question_id());
//			p.setActivation_date(new Date());
//			p.setLast_login(new Date());
			membershipService.update(p);
			log.info("Session Activada Satisfactoriamente!! ");
			setAnswer(null);
			setSeqQuest(0);
			
			InvestorPK invPk = new InvestorPK();
			
			invPk.setCompany_id(sesion.getCompany_id());
			invPk.setProspectus_id(sesion.getProspectus_id());
			
			Investor inv = investorservice.getInvestorById(invPk);
				
			if(inv == null)
			{								
				inv = new Investor();
				inv.setDate_approved(null);
				inv.setDate_published(null);
				inv.setPk(invPk);
				inv.setStatus_id(0);
				/*
				if(investorservice.addInvestor(inv))
				{
					System.out.println("Inversionista Dado de alta: "+sesion.getProspectus_id());
					
				} else {
					
					System.out.println("Error Alta Inversionista: "+sesion.getProspectus_id());
				}
				*/
			}
			
			try{
				inicializaSimulador(sesion.getCompany_id(),sesion.getProspectus_id(),false);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//return "registrar";
			RequestContext request =  RequestContext.getCurrentInstance();
			request.addCallbackParam("is_saved_OK", true);
			
//		}
//		else{
//			 	 
//			 	return "activationCountSign";
//			}
	}
	
	public void addMessage(String summary) 
	{  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
	} 
	
	public String getCreditosPersonales() {
		
		//System.out.println("creditos personales listo");
		
		return "creditos_personales";
	}
	
	
	@Override
	public final String iniciaSesionCoach( Membership member_coach, Membership member_user, boolean inicia_con_usuario  )
	{		
		
//		faces     = FacesContext.getCurrentInstance();	
//		external  = faces.getExternalContext();
//		elContext = faces.getELContext();
//		resolver  = faces.getApplication().getELResolver();
		
		servlet_request = (HttpServletRequest) external.getRequest();
		
		SignOut();
		
		
		
		String usr = "";
		String pwd = "";
		
		boolean flagAccount = false;
		
//		System.out.println("");
//		System.out.println( "***********************************INICIANDO**SESSION**COACH***********************" );
//		System.out.println("");
//		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sessionUsedB = sesion.isSessionUsed();
				
		sessionUsed = (HttpSession) external.getSession(false);
		sessionUsed.invalidate();
		
		sessionUsed = (HttpSession) external.getSession(true);
		sessionUsed.setAttribute("new", Boolean.FALSE);
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(inicia_con_usuario){
			
			usr = member_user.getEmail();
			pwd = member_user.getPassword();
			
			if( member_user.getRegistration_reason() != null ){
			
				partner      = member_user.getRegistration_reason().getPartner_id();      //nos traemos el valor del Partner si es que existe
			
			}
			sesion.setCoachProspectus_id( member_coach.getMembershipPK().getProspectus_id() );
			
		}else{
			
			usr = member_coach.getEmail();
			pwd = member_coach.getPassword();
			sesion.setCoachProspectus_id(null);
		}
		
		sesion.setPartner(partner); //lo volvemos a poner para que no se pierda
		sesion.setTemporalUser(temporalUser);
		sesion.setSessionUsed(sessionUsedB);
		sesion.setEmail(usr);	
		
		if(loginService.verifySessionEncrypt(sesion, usr,pwd))
		{		
//			System.out.println("");
//			System.out.println( "***********************************if*01******************************" );
//			System.out.println("");
//			if(!isWrongKuboPerson())
//			{				
//				System.out.println("");
//				System.out.println( "***********************************if*02******************************" );
//				System.out.println("");
//				if(isPassValid( pwd ) )
//				{		
//					System.out.println("");
//					System.out.println( "***********************************if*03******************************" );
//					System.out.println("");
					nameb   = external.getRequestParameterMap().get("namebraw");
					verionb = external.getRequestParameterMap().get("verbraw");
					osb     = external.getRequestParameterMap().get("osbraw");						
					width   = external.getRequestParameterMap().get("browser_width");
					height  = external.getRequestParameterMap().get("browser_height");
					
					ipAddressClient  = servlet_request.getHeader("X-FORWARDED-FOR");  
				    
					if(ipAddressClient == null)
					{
				    	ipAddressClient = servlet_request.getRemoteAddr();
					}
					
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					
//					System.out.println( "Accesso desde la IP: "+ ipAddressClient);
//					
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
//					System.out.println( "*******************************************************************" );
					
					sesion.setNamebrawser(nameb);
					sesion.setVersionbrawser(verionb);
					sesion.setOsbrawser(osb);
					sesion.setPartner(partner);				
					sesion.setIP_address_client(ipAddressClient);
					sesion.setBrowser_width (Integer.parseInt(width  == null ? "0" : width  == "" ? "0" : width));
					sesion.setBrowser_height(Integer.parseInt(height == null ? "0" : height == "" ? "0" : height));
											
					setTrackId(sesion.getTrackId());
					
					menu = "Entrar";
					
					this.name = sesion.getNombre();
					
					setPassword("");
		
					setWarningUserMsg("");
					setWarninguserdisp(false);
					setWarninguserbut(true);
					
					fecha_acceso_ACTUAL = sesion.getFecha_acceso_ACTUAL();
					fecha_acceso_LAST   = sesion.getFecha_acceso_LAST();
						
					this.header = "headerIn.xhtml";
						
					if(sesion.getArea().equals('M'))
					{							
						if(!sesion.isBlochHeader())
						{
							this.action = "controlTable";
							
						} else { 
							
							this.action = "";
						}
						
						return "controlTable";
							
					} else if(sesion.getArea().equals('I')) {
							
						Calendar initInv_1 = Calendar.getInstance();
						initInv_1.setTime(new Date());
						
						InvestorPK invPk = new InvestorPK();
						
						invPk.setCompany_id(sesion.getCompany_id());
						invPk.setProspectus_id(sesion.getProspectus_id());
						
						Investor inv = investorservice.getInvestorById(invPk);
							
						if(inv == null)
						{								
							inv = new Investor();
							inv.setDate_approved(null);
							inv.setDate_published(null);
							inv.setPk(invPk);
							inv.setStatus_id(0);
							/*
							if(investorservice.addInvestor(inv))
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
										boolean f = false;
										
										f = savingaccountservice.verifyAccount(count);
										
										if(!inversiones)
										{
											inversiones = f;
										}
										
										if(inversiones)
										{
											InvestorPK invPk2 = new InvestorPK();
											
											invPk2.setCompany_id(sesion.getCompany_id());
											invPk2.setProspectus_id(sesion.getProspectus_id());
											
											Investor inv2 = investorservice.getInvestorById(invPk2);
											inv2.setStatus_id(3);
											investorservice.updateInvestor(inv2);
										}											
									}										
								}									
							}
								
							if(inversiones)
							{
								
								Access access = new Access();
								access.setCompany_id     (sesion.getCompany_id());
								access.setProspectus_id  (sesion.getProspectus_id());
								access.setOp_system      (sesion.getOsbrawser());
								access.setHorizontal_size(sesion.getBrowser_width());
								access.setVertical_size  (sesion.getBrowser_height());
								access.setIpaddress      (sesion.getIP_address_client());
								access.setWeb_browser    (sesion.getNamebrawser());
								access.setWeb_browser_version(sesion.getVersionbrawser());
								access.setVersion_description(sesion.getVersion_description());
								access.setScreen_id(15);//pantalla de mis inversiones
								access.setPercentage(0);
								access.setUrl_access( sesion.getUrl_access() );
								access.setProspectus_id_coach (sesion.getCoachProspectus_id());
								access.setAccess_from		  (sesion.getAccess_from());								
								
								service_access.add(access, false);
								
								if(!sesion.isBlochHeader())
								{
									this.action="inversiones";
									
								} else {
									
									this.action="";
								}
								
								Calendar intInv_2 = Calendar.getInstance();
								intInv_2.setTime(new Date());
								long difBr_2 = intInv_2.getTimeInMillis() - initInv_1.getTimeInMillis();
								
								// System.out.println("Tiempo en inicializar Inversionista: "+difBr_2+" milisegundos");
								
								return "inversiones";
								
							} else {
								
								if(!sesion.isBlochHeader())
								{
									this.action = "/";
									
								} else {
									
									this.action = "";
								}
									
								if(inv != null && inv.getStatus_id() != 0)
								{
									Access access = new Access();
									access.setCompany_id     (sesion.getCompany_id());
									access.setProspectus_id  (sesion.getProspectus_id());
									access.setOp_system      (sesion.getOsbrawser());
									access.setHorizontal_size(sesion.getBrowser_width());
									access.setVertical_size  (sesion.getBrowser_height());
									access.setIpaddress      (sesion.getIP_address_client());
									access.setWeb_browser    (sesion.getNamebrawser());
									access.setWeb_browser_version(sesion.getVersionbrawser());
									access.setVersion_description(sesion.getVersion_description());
									access.setScreen_id(18);//pantalla de mis inversiones
									access.setPercentage(0);
									access.setUrl_access( sesion.getUrl_access() );
									access.setProspectus_id_coach (sesion.getCoachProspectus_id());
									access.setAccess_from		  (sesion.getAccess_from());
									
									service_access.add(access, false);
									
									ScreenPK pk = new ScreenPK();
									pk.setCompany_id(sesion.getCompany_id());
									pk.setScreen_id(18);
									Screen  sc = screenservice.getScreenById(pk);
									sesion.setLastPage( sc.getName() );
									
								}
									
								return "registrar";
							}
							
							
							
						} else {
							
							if(!sesion.isBlochHeader())
							{
								this.action = "registrar";
								
							} else {
								
								this.action = "";
							}
							
							return "registrar";
						}
							
					} else {
							
						if(sesion.getArea().toString().equals("L"))
						{	
							
							if(sesion.getLastPage()!= null && sesion.getLastPage().equals("registro/cierre") ){
								inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),true);
							}else{
								inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),false);
							}
							
						}
						
						SystemParamPK sysPk = new SystemParamPK();
						
						sysPk.setCompany_id(sesion.getCompany_id());
						sysPk.setSystem_param_id(13);//Bandera que indica si se encuentra disponible la pantalla de Estado de Cuenta
						SystemParam thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
							
						if(width==null)
						{
							width = "1000";
						}
							
						MembershipPK mpk = new MembershipPK();
						
						mpk.setCompany_id(sesion.getCompany_id());
						mpk.setProspectus_id(sesion.getProspectus_id());
						Membership p = membershipService.getMembershipById(mpk);
							
						if((thisSystem.getValue()!=null && thisSystem.getValue().equals("S")) || 
						   (thisSystem.getValue()!=null && thisSystem.getValue().equals("N") && p.getIs_active().equals(1)))
						{								
							if(p.getPerson().getSafi_client_id() != null && p.getPerson().getSafi_client_id().trim().length() > 0)
							{									
								log.info("Session iniciada Satisfactoriamente!! ");
								
								if(!sesion.isBlochHeader())
								{
									this.action="edocuenta";
									
								} else {
									
									this.action="";
								}
								
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("-----------------------------width:"+width+"-----------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
								
									
//								if(Integer.parseInt(width) < 590)
//								{
//									this.action = "mobileRegistro";
//									
//									sesion.setMobile(true);
//									
//									return "mobileRegistro";
//									
//								} else {
									
									return "edocuenta";
								//}
									
							} else {
								
								log.info("Session iniciada Satisfactoriamente!! ");
								
								ProyectLoan proyectloan = proyectloanservice.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
								
								boolean flagRechazado = false;
								
								if( proyectloan != null && proyectloan.getStatus_id() != null && proyectloan.getStatus_id() ==11 ){
									SystemParamPK spk = new SystemParamPK();
									spk.setCompany_id(sesion.getCompany_id());
									spk.setSystem_param_id(65);
									SystemParam sys = service_system_param.loadSelectedSystemParam( spk );
									
									if( sys != null ){
										
										Calendar c = Calendar.getInstance();
										c.setTime(proyectloan.getConsulting_date());
										Calendar c2 = Calendar.getInstance();
										c2.setTime( new Date() );
										
										long l = c2.getTimeInMillis() - c.getTimeInMillis();
										
										long dif = l /((24 * 60 * 60 * 1000)); 
										
										if( Integer.parseInt(sys.getValue()) < dif ){
											flagRechazado = true;
										}
										
									}
									
								}
								
								if( flagRechazado )
								{									
									Access access = new Access();
									access.setCompany_id     (sesion.getCompany_id());
									access.setProspectus_id  (sesion.getProspectus_id());
									access.setOp_system      (sesion.getOsbrawser());
									access.setHorizontal_size(sesion.getBrowser_width());
									access.setVertical_size  (sesion.getBrowser_height());
									access.setIpaddress      (sesion.getIP_address_client());
									access.setWeb_browser    (sesion.getNamebrawser());
									access.setWeb_browser_version(sesion.getVersionbrawser());
									access.setVersion_description(sesion.getVersion_description());
									access.setScreen_id(6);//pantalla de rechazo automático
									access.setPercentage(0);
									access.setUrl_access( sesion.getUrl_access() );
									access.setProspectus_id_coach (sesion.getCoachProspectus_id());
									access.setAccess_from		  (sesion.getAccess_from());
									
									service_access.add(access, false);
									
									ScreenPK scpk = new ScreenPK();
									scpk.setCompany_id(sesion.getCompany_id());
									scpk.setScreen_id(6);
									Screen screen =  screenservice.getScreenById(scpk);
									
									sesion.setLastPage(screen.getName());									
								}
								
								if(!sesion.isBlochHeader())
								{
									this.action = "registrar";
									
								} else {
									
									this.action = "";
								}
									
								
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("-----------------------------width:"+width+"-----------------------------");
//								System.out.println("---------------------------------------------------------------------");
//								System.out.println("---------------------------------------------------------------------");
								
//								if(Integer.parseInt(width) < 590)
//								{
//									this.action = "mobileRegistro";
//									
//									sesion.setMobile(true);
//									
//									return "mobileRegistro";
//									
//								} else {
									return "registrar";
								//}
							}
								
						} else {
							
							log.info("Session iniciada Satisfactoriamente!! ");
							
							if(!sesion.isBlochHeader())
							{
								this.action = "registrar";
								
							} else {
								
								this.action = "";
							}
								
//							System.out.println("---------------------------------------------------------------------");
//							System.out.println("---------------------------------------------------------------------");
//							System.out.println("-----------------------------width:"+width+"-----------------------------");
//							System.out.println("---------------------------------------------------------------------");
//							System.out.println("---------------------------------------------------------------------");
							
//							if(Integer.parseInt(width) < 590)
//							{
//								this.action = "mobileRegistro";
//								
//								sesion.setMobile(true);
//								
//								return "mobileRegistro";
//								
//							} else {
								
								return "registrar";
							//}								
						}
					}	
						
			/*	} else {
					
					log.info("Session iniciada Satisfactoriamente!! ");
					
					this.action = "";
					
					sesion.setBlochHeader(true);
					
					return "changePassword";						
				}	
				
			} else {
				
				this.action = "Entar";
				
				sesion.sessionOut();
				
				return "mensaje";				
			} */
			
		} else {
			
			sesion.setPartner(partner);
			
//			sesion.setTemporalUser(temporalUser);
//			sesion.setSessionUsed(sessionUsedB);
			
			setUser(sesion.getTemporalUser());
			setPassword("");
			addMessage("Nombre de usuario o contraseña incorrecta, verifique tambien que su cuenta se encuentre activa.");
			
			return "access";
		}
	}
	
	
}




