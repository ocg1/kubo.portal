package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.datamodels.ChangeMailForm;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.refactorizables.ChangeMailRefactorizable;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

@ManagedBean @ViewScoped @SuppressWarnings("serial")
public class ChangeMail extends ChangeMailForm 
implements  ChangeMailRefactorizable, Serializable
{
		
	@PostConstruct
	public void init()
	{		
		ELContext   elContext;
		SessionBean bean;
		
		elContext = FacesContext.getCurrentInstance().getELContext();
		bean      = (SessionBean) FacesContext.getCurrentInstance()
                									 .getApplication()
                									 .getELResolver()
                									 .getValue(elContext, null, "sessionBean");
		
		System.out.println("init. Mail recuperada: "+ bean.getTemporalUser() );
		
		member = membershipService.getMembershipByEmail( bean.getTemporalUser() );
		
		if(member != null){
		
			System.out.println("init. Persona Recuperada: "+ member.getPerson().NombreCompletoNPM() );
			
			phone = phoneService.getPhoneByTypeByArea(member.getMembershipPK().getProspectus_id(), member.getMembershipPK().getCompany_id(), CELLPHONE, member.getPerson().getProspectus().getArea());
			
//			address = addressService.getMaxAddressByType(member.getMembershipPK().getProspectus_id(), member.getMembershipPK().getCompany_id(), CASA);
			
			if( phone !=null && phone.getPhone_number() != null && phone.getPhone_number().length() > 4 )
				displayPhone = true;
			
//			if( address !=null && address.getZip_code() != null && address.getZip_code().length()>3 )
//				displayZipCode = true;
			
		}
		
		newNaturalPerson = new NaturalPerson();	
		
		/*
		notificador      = new Notificador(eventnotificationservice,
										    eventService,
										    mailService,
										    servicecallingService);
		*/
	}
	
 public void confirmMail(){
		System.out.println("Confirmando Mail ");
		if(getEmail()!=null&&getConfMail()!=null&&getEmail().length()>0&&getConfMail().length()>0){
			if(getEmail().equals(getConfMail())){
				canChangeMail = true;
				System.out.println("Confirmacion Valida: "+getConfMail()+"  -  "+getEmail());
				setWarningConfMail("<script>$('#confemail').removeClass('requiredClass');varConfEmail=true;</script>");
			}else{
				System.out.println("Confirmacion No Valida: "+getConfMail()+"  -  "+getEmail());
				canChangeMail = false;
				setWarningConfMail("Error al confirmar tu correo electrónico<script>$('#confemail').addClass('requiredClass');varConfEmail=false;</script>");
			
				}
			}else{
				System.out.println("confMail vacio");
				canChangeMail = false;
				setWarningConfMail("<script>$('#confemail').removeClass('requiredClass');varConfEmail=false;</script>");
			}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	
	
	public void confirmMailNew(){
		System.out.println("Confirmando Mail ");
		if(getEmail()!=null&&getConfMail()!=null&&getEmail().length()>0&&getConfMail().length()>0){
			if(getEmail().equals(getConfMail())){
				canChangeMail = true;
				System.out.println("Confirmacion Valida: "+getConfMail()+"  -  "+getEmail());
				setWarningConfMail("<script>$('#confemail2').removeClass('requiredClass');varConfEmail=true;</script>");
			}else{
				System.out.println("Confirmacion No Valida: "+getConfMail()+"  -  "+getEmail());
				canChangeMail = false;
				setWarningConfMail("<script>$('#confemail2').addClass('requiredClass');varConfEmail=false; alertify.error('Error al confirmar el correo electrónico');</script>");
			
				}
			}else{
				System.out.println("confMail vacio");
				canChangeMail = false;
				setWarningConfMail("<script>$('#confemail2').removeClass('requiredClass');varConfEmail=false;</script>");
			}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	
	public void validaMailPass(){
		
		System.out.println("Get password: " + member.getPassword());
		String tempPass = Utilities.encrypt( getYourpassword() );
		
		canShow = false;
		
		if(tempPass.equals(member.getPassword())){			
			canShow = true;			
		}
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		System.out.println("isvalid: " + canShow);
		
		requestContext.addCallbackParam("isvalid", canShow);
		
	}
	
public void validaEmail(){
		
		System.out.println("Validando mail ");
		
		if(getEmail()!=null&&getEmail().length()>0){
			
			if(validaMail(getEmail())){
				
				System.out.println("Mail Valido; "+getEmail());
				Membership mem = membershipService.getMembershipByEmail(getEmail());
				
				if(mem != null){
					System.out.println("El mail ya existe");
					isValidMail = false;
					
						setWarningMail(	"La dirección de correo: <b>"+getEmail()+"</b> ya existe. <br /> " +
								"<script>$('#email').addClass('requiredClass');varEmail=false;</script>");
					
				}else{
							System.out.println("El mail es nuevo");
							isValidMail = true;
							setWarningMail("<script>$('#email').removeClass('requiredClass');varEmail=true;</script>");
				}
			}else{
				System.out.println("Mail No Valido: "+getEmail());
				isValidMail = false;
				setWarningMail("Formato de correo electrónico incorrecto<script>$('#email').addClass('requiredClass');varEmail=false;</script>");
			}
		}else{
			System.out.println("Mail vacio");
			canChangeMail = false;
			setWarningMail("<script>$('#email').removeClass('requiredClass');varEmail=false;</script>");
		}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	

	public void validaEmailNew(){
		
		System.out.println("Validando mail ");
		
		if(getEmail()!=null&&getEmail().length()>0){
			
			if(validaMail(getEmail())){
				
				System.out.println("Mail Valido; "+getEmail());
				Membership mem = membershipService.getMembershipByEmail(getEmail());
				
				if(mem != null){
					System.out.println("El mail ya existe");
					isValidMail = false;
					
						setWarningMail(	"<script>alertify.error('La dirección de correo ya existe');</script>");
					
				}else{
							System.out.println("El mail es nuevo");
							isValidMail = true;
							setWarningMail("<script>$('#email3').removeClass('requiredClass');varEmail=true;</script>");
				}
			}else{
				System.out.println("Mail No Valido: "+getEmail());
				isValidMail = false;
				setWarningMail("<script>$('#email3').addClass('requiredClass');varEmail=false; alertify.error('Formato de correo electrónico incorrecto');</script>");
			}
		}else{
			System.out.println("Mail vacio");
			canChangeMail = false;
			setWarningMail("<script>$('#email3').removeClass('requiredClass');varEmail=false;</script>");
		}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	
	public void validachange(){
		
		System.out.println("RememberPass : "+rememberPass);
		
		if( rememberPass != null )
		{		
			canShow       = false;
			canChange     = false;
			canChangeMail = false;
			isValidMail   = false;
			
			if(rememberPass == 1)
			{
				canChange = true;
				showPass  = true;
				showName  = false;				
			} else if( rememberPass == 2 ){				
				showName = true;
				showPass = false;				
			}			
		}		
	}
		
	public void validaPass(){
		System.out.println("Validando Password ");
		if(getPassword()!=null&&getConfPass()!=null&&getPassword().length()>0&&getConfPass().length()>0){
			if(getPassword().equals(getConfPass())){
				canChange = true;
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=true;</script>");
				
			}else{
				canChange = false;
				System.out.println("Confirmacion No Valida: "+getPassword()+"  -  "+getConfPass());
				setWarningConfPass("Error al confirmar tu contraseña<script>$('#confpass').addClass('requiredClass');varPass=false;</script>");
			
				}
			}else{
				canChange = false;
				System.out.println("Password vacio");
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=false;</script>");
				
			}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	
	
	
	public void validaPassNew(){
		System.out.println("Validando Password ");
		if(getPassword()!=null&&getConfPass()!=null&&getPassword().length()>0&&getConfPass().length()>0){
			if(getPassword().equals(getConfPass())){
				canChange = true;
				setWarningConfPass("<script>$('#confpass2').removeClass('requiredClass');varPass=true;</script>");
				
			}else{
				canChange = false;
				System.out.println("Confirmacion No Valida: "+getPassword()+"  -  "+getConfPass());
				setWarningConfPass("<script>$('#confpass2').addClass('requiredClass');varPass=false; alertify.error('Error al confirmar tu contraseña');</script>");
			
				}
			}else{
				canChange = false;
				System.out.println("Password vacio");
				setWarningConfPass("<script>$('#confpass2').removeClass('requiredClass');varPass=false;</script>");
				
			}
		
		System.out.println("validaPass: "+canChange+" mail: "+canChangeMail);
		
	}
	
	public void validaInfo(){
		
		System.out.println( "validaInfo!!!!" );
		
		boolean flagPhone = false;
		boolean flagZipCode = false;
		
		if(
				member.getPerson().NombreCompletoNPM().toUpperCase().equals(
						newNaturalPerson.NombreCompletoNPM().toUpperCase()  )
			){
			
			if( phone != null && phone.getPhone_number() != null && phone.getPhone_number().length()>4){
				
				flagPhone =  phone.getPhone_number().equals( "("+getLadaCelProspectus()+")" + getPhoneCellProspectus() );
				
			}else{
				
				flagPhone = true;
				
			}
			
//			if( address != null && address.getZip_code() != null && address.getZip_code().length()>3){
//				
//				flagZipCode =  address.getZip_code().equals( getZip_code() );
//				
//			}else{
				
				flagZipCode = true;
				
//			}
			
		}
		
		canShow = flagPhone&&flagZipCode;
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		System.out.println("isvalid: " + canShow);
		
		requestContext.addCallbackParam("isvalid", canShow);
		
	}
	
	public boolean validaMail(String mailStr){
		
		Pattern p = Pattern.compile("^*\\w+([\\.*\\w([\\-\\w])+])*@\\w([\\-\\w])+\\.\\w{2,4}([\\.*\\w{2,3}])*$");
		//([\\w\\-]+\\.)+[A-Z]{2,4}([\\.\\w])?$
		
		Matcher m = p.matcher(mailStr);
		
		if ( m.matches())
			return true;
		else
			return false;
	}
	
	public String actualizaMail_portal()
	{		
		System.out.println("cambiando mail" + getEmail() );
		member.setEmail(getEmail());
		
		if(rememberPass == 1)
		{		
			member.setPassword(Utilities.encrypt( getYourpassword() ));			
		}
		
		membershipService.update(member);
		if(rememberPass == 1)
		{
		
			PasswordHistoryPK pssPk = new PasswordHistoryPK();
			
			pssPk.setCompany_id(member.getMembershipPK().getCompany_id());
			
			pssPk.setProspectus_id(member.getMembershipPK().getProspectus_id());
			
			PasswordHistory passHis = new PasswordHistory(); 
			
			passHis.setDate_changed(new Date());
			passHis.setPassword( member.getPassword() );
			passHis.setPwdhpk( pssPk );
			
			passwordhistoryservice.savePasswordHistory(passHis);
		
		}
			
		try 
		{
			notificador.setEmisor(member);
			notificador.notificar(Evento.CAMBIO_CUENTA_CORREO);
						
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			
			SessionBean session = (SessionBean) FacesContext.getCurrentInstance()
	                									    .getApplication()
	                									    .getELResolver()
	                									    .getValue(elContext, null, "sessionBean");
			
			session.setNombre(member.getPerson().NombreCompletoNPM());
			session.setEmail(getEmail());
			
			return NavigationRule.SEND_MAIL.toString();
		} catch (NotificacionException e) {			
			e.printStackTrace();
			return NavigationRule.ERROR_PRERGISTRO.toString();
		}	
		
		
						
	}
	
	public String actualizaMail()
	{		
		
		member.setEmail(getEmail());
		
		if(rememberPass == 1)
		{		
			member.setPassword(Utilities.encrypt( getYourpassword() ));			
		}
		
		membershipService.update(member);
		
		if(rememberPass == 1)
		{
		
			PasswordHistoryPK pssPk = new PasswordHistoryPK();
			
			pssPk.setCompany_id(member.getMembershipPK().getCompany_id());
			
			pssPk.setProspectus_id(member.getMembershipPK().getProspectus_id());
			
			PasswordHistory passHis = new PasswordHistory(); 
			
			passHis.setDate_changed(new Date());
			passHis.setPassword( member.getPassword() );
			passHis.setPwdhpk( pssPk );
			
			passwordhistoryservice.savePasswordHistory(passHis);
		
		}
			
		try 
		{
			notificador.setEmisor(member);
			notificador.notificar(Evento.CAMBIO_CUENTA_CORREO);
						
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			
			SessionBean session = (SessionBean) FacesContext.getCurrentInstance()
	                									    .getApplication()
	                									    .getELResolver()
	                									    .getValue(elContext, null, "sessionBean");
			
			session.setNombre(member.getPerson().NombreCompletoNPM());
			session.setEmail(getEmail());
			
			return NavigationRule.WAIT_CONFIRMATION.toString();
		} catch (NotificacionException e) {			
			e.printStackTrace();
			return NavigationRule.ERROR_PRERGISTRO.toString();
		}				
						
	}
	
	

	
	
}