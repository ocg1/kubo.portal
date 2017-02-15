package mx.com.kubo.managedbeans.portal;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.kubo.controller.PendingNotificationController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.managedbeans.preregistro.PreregistroIMP;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

import org.primefaces.context.RequestContext;

@ManagedBean @ViewScoped
public class IniciaSession extends IniciaSessionAMO 
implements Serializable
{
	private static final long serialVersionUID = 330590365566268642L;
	
	public void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();		
		external  = faces.getExternalContext();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		if(prospectus_id != null)
		{
			//init_baja_usuario_firmado();
		}		
	}
	
	public void listenerEmail(AjaxBehaviorEvent e)
	{
		input_text =  (HtmlInputText) e.getComponent();
		
		email = (String) input_text.getValue();	
		
		//System.out.println("IniciaSession.listenerEmail() = " + email);
	}
	
	public void iniciaSesion()
	{	
		request = RequestContext.getCurrentInstance();
		faces   = FacesContext.getCurrentInstance();			
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		flagAccount = false;
		
		usr = getEmail();
		pwd = getPassword();

		http_request = (HttpServletRequest) external.getRequest();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		InicioValuesIMP inicio = (InicioValuesIMP) resolver.getValue(elContext, null, "inicioValues");
		
		partner      	= sesion.getPartner();      
		temporalUser 	= sesion.getTemporalUser();
		sessionUsedB 	= sesion.isSessionUsed();
		url_access		= inicio.getUrl_value();
		
		http_session = (HttpSession) external.getSession(false);
		http_session.invalidate();
		
		http_session = (HttpSession) external.getSession(true);
		http_session.setAttribute("new", Boolean.FALSE);
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sesion.setPartner(partner);
		sesion.setTemporalUser(temporalUser);
		sesion.setSessionUsed(sessionUsedB);
		sesion.setEmail(usr);
		sesion.setUrl_access(url_access);
		
		init_sesion();
		
		if( checkLogin ){
		
			login_check = new MembershipCheckIMP();
			login_check.setService_system_param(systemparamservice);
			login_check.setService_login(service_login);
			login_check.setMemmbership(usr);
			login_check.setSesion(sesion);
			login_check.init();
		
			membership = login_check.getMembership();
			
			if( membership != null && membership.getMembershipPK() != null ){
				
				prospectus_id_temp = membership.getMembershipPK().getProspectus_id();
				company_id_temp = membership.getMembershipPK().getCompany_id();
				
			}
			
			warningUser                 = login_check.getCheck_msg_TOKEN();
			membership_ENABLED          = login_check.isMembership_ENABLED();
			desbloqueo_password_ENABLED = login_check.isDesbloqueo_password_ENABLED();	
			
			//kubo_person_ENABLED         = login_check.isKubo_person_ENABLED(); 
			
		}else{
			membership_ENABLED = true;
		}
		
		if(membership_ENABLED)
		{		
			if(service_login.verifySession(sesion,usr,pwd))
			{			
				init_membership();					
				
				if(!isWrongKuboPerson())
				//if(!kubo_person_ENABLED)
				{					
					if(isPassValid( pwd ) )
					{		
						
						initAccess();
						
						init_foto();					
						
						password = "";								
						
						if(sesion.getArea().equals('M'))
						{							
							action =  "controlTable";
								
						} else if(sesion.getArea().equals('I')) {
													
							init_inversionista();
							
						} else {
							
							init_acreditado();
						}	
						
						if( fb_id != null && fb_id.trim().length() > 0 ){
						
							membership.setFb_id(fb_id);
							
							membershipService.update(membership);
							
							sesion.setFb_New( true );
						
						}
						
					} else {
					
						//System.out.println("Session iniciada Satisfactoriamente!! ");
															
						sesion.setBlochHeader(true);
					
						if( changeClientPass ){
						
							action =  "changePasswordClient";
							
						}else{
							
							action =  "changePassword";	
						
						}
					}	
					
				} else {
								
					sesion.sessionOut();
				
					action =  "mensaje";				
				}
			
			} else {
				
				sesion.setPartner(partner);
				
				password = "";
				
				action =  "access";
			}
						
		checaResultado( sesion );
			
		} else {						
			
			displayWarningUser = "block";		
		}
		
		if( warningUser.trim().length() > 0 )
		{								
			request.addCallbackParam("satisfactorio", false);
					
		} else {
			
			request = RequestContext.getCurrentInstance();
			if( request != null ){
				request.addCallbackParam("satisfactorio", true);
			}
		}		
	}			
	
	
	public void creaContactoInfusion(){
		
		try{
			
			System.out.println( " ********** creaContactoInfusion ********** mail:  " + mail_infusion );
			
			SystemParamPK system_param_PK_I = new SystemParamPK();
			
			system_param_PK_I.setCompany_id( 1 );
			system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
			
			 SystemParam system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
			
			 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
			 
					InfusionSoft infusion = new InfusionSoft();
					
					Integer contactId = infusion.altaContacto("", "", mail_infusion  );
					
					infusion.opt_in_emai(mail_infusion, "Acepta Registro Kubo.Financiero");
			
					infusion.addTAgToContact( contactId , 155 ); // obtiene mail popup home
					
			 }
		 
		}catch( Exception e ){
			e.printStackTrace();
		}
		
	}
	
	private void initAccess(){
		init_access(67) ;
	}
	
	private void initAccessFB(){
		init_access(73) ;
	}
	
	
	public void iniciaSesion_Facebook()
	{	
		System.out.println( "Inciando Session con FB" );
		
		request = RequestContext.getCurrentInstance();
		faces   = FacesContext.getCurrentInstance();			
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		flagAccount = false;
		
		usr = getEmail();
		pwd = getPassword();

		http_request = (HttpServletRequest) external.getRequest();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		InicioValuesIMP inicio = (InicioValuesIMP) resolver.getValue(elContext, null, "inicioValues");
		
		partner      	= sesion.getPartner();      
		temporalUser 	= sesion.getTemporalUser();
		sessionUsedB 	= sesion.isSessionUsed();
		url_access		= inicio.getUrl_value();
		
		http_session = (HttpSession) external.getSession(false);
		http_session.invalidate();
		
		http_session = (HttpSession) external.getSession(true);
		http_session.setAttribute("new", Boolean.FALSE);
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sesion.setPartner(partner);
		sesion.setTemporalUser(temporalUser);
		sesion.setSessionUsed(sessionUsedB);
		sesion.setEmail(usr);
		sesion.setUrl_access(url_access);
		
		init_sesion();
		
		login_check = new MembershipCheckIMP();
		login_check.setService_system_param(systemparamservice);
		login_check.setService_login(service_login);
		login_check.setFBMembership(fb_id, fb_email);
		login_check.setSesion(sesion);
		login_check.init();
		
		membership = login_check.getMembership();
		
		warningUser                 = login_check.getCheck_msg_TOKEN();
		membership_ENABLED          = login_check.isMembership_ENABLED();
		desbloqueo_password_ENABLED = login_check.isDesbloqueo_password_ENABLED();		
		//kubo_person_ENABLED         = login_check.isKubo_person_ENABLED(); 
		
		if(membership_ENABLED)
		{		
				sesion.setCompany_id(membership.getMembershipPK().getCompany_id());	
				sesion.setProspectus_id( membership.getMembershipPK().getProspectus_id() );
				sesion.setArea( membership.getPerson().getProspectus().getArea() );
				sesion.setEmail( membership.getEmail() );
				
				init_membership();					
				
				if(!isWrongKuboPerson())
				//if(!kubo_person_ENABLED)
				{					
					if(isClientValid( membership ) || membership.getPerson().getProspectus().getArea().toString().equals("M") )
					{		
						
						//action =  "controlTable";
						request.addCallbackParam("logginStatus", "3");
						
						request.addCallbackParam("nombreCompleto", membership.getPerson().NombreCompletoNPM());
						request.addCallbackParam("email_kubo", membership.getEmail());
						
						if( membership.getPerson().getProspectus().getImage() != null ){
							
							String pathIMG =  "resources/documents/cia_" + membership.getMembershipPK().getCompany_id()
													+"/pros_"+membership.getMembershipPK().getProspectus_id()+"/photo"+ "/" + membership.getPerson().getProspectus().getImage();
							
							request.addCallbackParam("imgen_kubo", pathIMG);
							
						}else{
							request.addCallbackParam("imgen_kubo", "SIN_IMAGEN");
						}
						
						System.out.println( " SI necesita contraseña segura " );
						
						sesion.setCompany_id(null);	
						sesion.setProspectus_id( null );
						sesion.setArea( null );
						sesion.setEmail( null );
						
					} else {
						
					
						if(service_login.verifySessionFB(sesion, membership))
						{
					
							request.addCallbackParam("logginStatus", "1");
							System.out.println( " NO necesita contraseña segura " );
							
							initAccessFB();
							
							init_foto();					
							
							password = "";								
							
							if(sesion.getArea().equals('M'))
							{							
								action =  "controlTable";
									
							} else if(sesion.getArea().equals('I')) {
														
								init_inversionista();
								
							} else {
								
								init_acreditado();
							}	
						
						} else {
							
							if(sesion.isSessionUsed()){
								
								request.addCallbackParam("logginStatus", "4");
								
								System.out.println( " USUARIO CON PROBLEMAS DE ACCESO" );
								
							}else{
								
								request.addCallbackParam("logginStatus", "2");
							
								System.out.println( " USUARIO NO ENCONTRADO EN LA BASE " );
								
							}
							
							sesion.setPartner(partner);
							
							password = "";
							
							action =  "access";
						}
						
					}	
					
				} else {
								
					sesion.sessionOut();
				
					action =  "mensaje";				
				}
			
			
						
		checaResultado( sesion );
			
		} else {	
			
			if( membership == null ){
				
				request.addCallbackParam("logginStatus", "2");
				
				System.out.println( " USUARIO NO ENCONTRADO EN LA BASE " );
				
			}else{
			
				request.addCallbackParam("logginStatus", "5");
				System.out.println( " USUARIO NO PUDO INICIAR SESSION " );
				displayWarningUser = "block";
			
			}
		}
		
		if( warningUser.trim().length() > 0 )
		{								
			request.addCallbackParam("satisfactorio", false);
					
		} else {
			
			request = RequestContext.getCurrentInstance();
			if( request != null ){
				request.addCallbackParam("satisfactorio", true);
			}
		}		
	}
	
	public String iniciaRegistroAcreditado(){
		
		System.out.println( "inicia  --  Registro  --  Acreditado" );
		
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();		
		external  = faces.getExternalContext();
		
//		fb_id,
//		fb_email;
		
		PreregistroIMP preregistro = ( PreregistroIMP ) resolver.getValue(elContext, null, "addNaturalPerson");
		
		preregistro.setEmail(fb_email);
		
		String pass = fb_email.split("@")[0];
		
		preregistro.setPassword(pass);
		
		//preregistro.setFb_id( fb_id );
		
		return preregistro.addNewNaturalPersonLoan();
		
		
		
	}
	
	public String iniciaRegistroInversionista(){
		
		System.out.println( "inicia  --  Registro  --  Inversionista" );
		
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();		
		external  = faces.getExternalContext();
		
//		fb_id,
//		fb_email;
		
		PreregistroIMP preregistro = ( PreregistroIMP ) resolver.getValue(elContext, null, "addNaturalPerson");
		
		preregistro.setEmail(fb_email);
		
		String pass = fb_email.split("@")[0];
		
		preregistro.setPassword(pass);
		
		//preregistro.setFb_id( fb_id );
		
		return preregistro.addNewNaturalPersonInv();
		
		
		
	}
	
	private boolean isClientValid( Membership membership ){
		
		ClientsPK pk = new ClientsPK() ;
		
		pk.setCompany_id( membership.getMembershipPK().getCompany_id() );
		pk.setProspectus_id( membership.getMembershipPK().getProspectus_id() );
		
		Clients client =  clientsservice.getClientById(pk);
		
		if( client != null  ){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public void savePendingNatification(){
		
		request = RequestContext.getCurrentInstance();
		
		PendingNotificationController pnc = new PendingNotificationController();
		
		if( prospectus_id_temp != null ){
			
			pnc.initPendingNotificationOBJ( company_id_temp , prospectus_id_temp, EVENT_TIENDA_DISPONIBLE);
			
			request.addCallbackParam("phone_number", pnc.getPhoneNumber(  company_id_temp,  prospectus_id_temp ));
		
		}
		
	}
	
	
}
