package mx.com.kubo.managedbeans.perfil;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.ProspectusExtra;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

@ManagedBean (name = "perfilController") @ViewScoped 
public final class PerfilControllerIMP extends PerfilControllerPMO
implements PerfilControllerIMO, Serializable 
{			
	private static final long serialVersionUID = 3038237382464743709L;

	@PostConstruct
	public void init() 
	{
		faces    = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		ip_address    = sesion.getIP_address_client();		
		company_id    = sesion.getCompany_id();
		prospectus_id = sesion.getProspectus_id();
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id);
		
		membership = membershipService.getMembershipById(membership_PK);
		
		preference_id_ORIGINAL = membership.getNotification_preference_id();
		
		lista_notification_preference = service_catalogos.getLista_notification_preference();
		
		natural_person_PK = new gnNaturalPersonPK();
		natural_person_PK.setCompany_id(company_id);
		natural_person_PK.setProspectus_id(sesion.getProspectus_id());
		
		person = naturalPersonService.getNaturalPersonById(natural_person_PK);
		
		prevPerson = new NaturalPerson();
		
		prevPerson.setFirst_name      (person.getFirst_name());
		prevPerson.setMiddle_name     (person.getMiddle_name());
		prevPerson.setFather_last_name(person.getFather_last_name());
		prevPerson.setMother_last_name(person.getMother_last_name());
				
		header = (HeaderBean) resolver.getValue(context, null, "headerBean");		
		header.setDispBtnCred(false);
		
		safi_client_id = membership.getPerson().getSafi_client_id();		
		area           = membership.getPerson().getProspectus().getArea().toString();
		
		if(area.equals("L"))
		{		
			if(safi_client_id == null || safi_client_id.trim().length() == 0)
			{				
				hasScoring = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id) != null ? true : false;
				
			} else {
				
				hasScoring = true;
			}
						
		} else if(area.equals("I")) {
			
			countList = savingaccountservice.getListAccountByProspect(prospectus_id, company_id);
			
			boolean inversiones = false;
			
			if(countList != null && countList.size() > 0)
			{				
				for(SavingAccount count : countList)
				{
					if(count.getStatus()!=null&&count.getSafi_account_id() != null)
					{
						inversiones = true;
						break;
					}
				}				
			}
			
			hasScoring = inversiones;					
		}
		
		setCompleteName(getCompleteNameStr());	
		
		prevCompletName = getCompleteName();
		
		catalogo_preguntas_seguridad = service_preguntas.getSecurityQuestionList();				
		pool_preguntas_seguridad     = service_pool_preguntas.loadSecQuestPoolByProspect(prospectus_id, company_id);
		
		init_preguntas_seguridad();
		
		if(pool_preguntas_seguridad != null && pool_preguntas_seguridad.size() > 0 )
		{			
			pantalla_a_mostrar = PANEL_EDICION;
			
		} else {
			
			pantalla_a_mostrar = PANEL_SELECCION;
		}
		
		correo = new EditorCorreoIMP();
			
		registar_bitacora_access(SCREEN_ID_PERFIL);
	}

	public final void init_notification_preference_id(AjaxBehaviorEvent evento)
	{
		request  = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		preference_id_NEW = (Integer) select_menu.getValue();
		
		request.addCallbackParam("preference_id", preference_id_NEW);
	}
	
	public final void persist_preference_id()
	{
		request  = RequestContext.getCurrentInstance();
		
		membership.setNotification_preference_id(preference_id_NEW);
		
		is_saved_OK = membershipService.update(membership);
		
		if(is_saved_OK)
		{
			init_change_control();
		}
		
		request.addCallbackParam("is_saved_OK", is_saved_OK);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
	
	public final void listener_pregunta_selected(AjaxBehaviorEvent evento)
	{
		request  = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		pregunta_selected = Integer.parseInt((String) input_text.getValue());
		
		request.addCallbackParam("pregunta_selected", pregunta_selected);
	}
	
	public final void listener_catalogo_preguntas(AjaxBehaviorEvent evento)
	{
		request  = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		question_id = Integer.parseInt((String) select_menu.getValue());
		
		System.out.printf("\nPerfilController.listener_catalogo_preguntas(): " + question_id);				
		
		request.addCallbackParam("cambio_pregunta_OK", is_cambio_pregunta_OK());
	}
	
	public final void listener_respuesta(AjaxBehaviorEvent evento)
	{
		request  = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		respuesta = (String) input_text.getValue();
		
		respuesta = Utilities.encrypt(respuesta.toUpperCase());
		
		request.addCallbackParam("respuesta", respuesta);
	}
	
	public final void guardar_cambio_pregunta()
	{
		request  = RequestContext.getCurrentInstance();

		procesar_cambio_pregunta();
		
		if(is_saved_OK)
		{
			init_preguntas_seguridad();
		}
		
		request.addCallbackParam("is_saved_OK", is_saved_OK);
	}
	
	public final void comprobar_respuesta()
	{
		request = RequestContext.getCurrentInstance();
		
		procesar_respuesta();
				
		request.addCallbackParam("is_answer_OK", is_answer_OK);
	}

	public boolean validatePass()
	{
		if(loginService.validatePass(membership.getEmail(), getPass()))
		{
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public boolean confPass()
	{
		if(!newPass.equals(newPassConf))
		{			
			return false;
			
		} else {
			
			return true;
		}
	}
	
	public final void saveNewMail()
	{		
		boolean flag = false;
		request  = RequestContext.getCurrentInstance();
		faces    = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		if(!getMembership().getEmail().equals(getNewemail()))
		{
			if(Utilities.validaMail(getNewemail()))
			{
				if(!loginService.verifyEmail(getNewemail()))
				{
									
					try 
					{
						String correo_anterior;
						correo_anterior = membership.getEmail();
						membership.setEmail(getNewemail());
						
						notificador = new NotificadorIMP();
						notificador.setEmisor(membership);
						notificador.notificar(Evento.PETICION_CAMBIO_CUENTA_CORREO);
						
						msg = getNewemail();
						
						if(saveChangeData("ln_membership", "email", correo_anterior, getNewemail(), "Solicito cambio de correo"))
						{
							HeaderBean sesion = (HeaderBean) resolver.getValue(context, null, "headerBean");
							sesion.SignOut();
							SessionBean session = (SessionBean) resolver.getValue(context, null, "sessionBean");
							session.setNombre(person.getFirst_name());
							session.setEmail(getNewemail());
							flag = true;
						} else {
							flag=false;
						}	
							
					} catch (NotificacionException e) {						
						e.printStackTrace();						
					}				
				
				}else{
					flag=false;
					msg="El correo "+getNewemail()+" ya existe.";
				}
			}else{
				flag=false;
				msg="El correo "+getNewemail()+" no es valido.";
			}
		}else{
			flag=false;
			msg="No se detectaron cambios al correo.";
		}
		request.addCallbackParam("isValid", flag);
		request.addCallbackParam("msg",getMsg());
		request.addCallbackParam("typeedit",2);
	}
	
	public void saveChangeName()
	{
		RequestContext requestContext = RequestContext.getCurrentInstance();	
		String msg="";
		boolean result=false;
		String affectedFields="";
		if(getPerson().getFirst_name()!="" && getPerson().getFather_last_name()!="" && getPerson().getMother_last_name()!=null){
			
			if(!person.getFirst_name().equals(prevPerson.getFirst_name()) 
					|| !person.getMiddle_name().equals(prevPerson.getMiddle_name()) 
					|| !person.getFather_last_name().equals(prevPerson.getFather_last_name())
					|| !person.getMother_last_name().equals(prevPerson.getMother_last_name()))
			{
					naturalPersonService.update(getPerson());
					getCompleteNameStr();		
					sesion.setNombre(getPerson().getFirst_name());		
					if(!prevPerson.getFirst_name().equals(person.getFirst_name()))
						affectedFields+="first_name,";
						if(!prevPerson.getMiddle_name().equals(person.getMiddle_name()))
							affectedFields+="middle_name,";
							if(!prevPerson.getFather_last_name().equals(person.getFather_last_name()))
								affectedFields+="father_last_name,";
								if(!prevPerson.getMother_last_name().equals(person.getMother_last_name()))
									affectedFields+="mother_last_name";
					
								int indexEnd= affectedFields.lastIndexOf(",");
					affectedFields=affectedFields.substring(0,indexEnd>1?indexEnd:affectedFields.length()-1);	
					
					if(saveChangeData("gn_natural_person",affectedFields,prevCompletName,completeName,"Se modifico el nombre.")){
						prevCompletName=getCompleteName();	
						prevPerson.setFirst_name(person.getFirst_name());
						prevPerson.setMiddle_name(person.getMiddle_name());
						prevPerson.setFather_last_name(person.getFather_last_name());
						prevPerson.setMother_last_name(person.getMother_last_name());
						msg="Se aplicaron los cambios correctamente";
						
						saveFullName();
						
						result=true;
					}else{
						result=false;
						msg="Uppps ocurrio un error interno.";
					}
			}else{
				result=false;
				msg="No se detectaron cambios en el nombre.";
			}
		}else{
			msg="Completa campos requeridos (*).";
			result=false;
		}
		requestContext.addCallbackParam("isValid", result);
		requestContext.addCallbackParam("msg",msg);
		requestContext.addCallbackParam("typeedit",1);
	}
	
	public void saveNewPass()
	{
		boolean flagsuccess = false;
		
		request = RequestContext.getCurrentInstance();
		
		if(validatePass())
		{			
			if(confPass())
			{
				
				String encriptNewPass = Utilities.encrypt(getNewPass());
				//Guardamos el  cambio en la tabla change_control
				
				if( !passwordhistoryservice.existPassword(encriptNewPass, membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id()) )
				{
				
					
					/*++++++++++++++++++++++++++++++++++++++++++++++++*/
					if(saveChangeData("ln_membership", "password", membership.getPassword(), encriptNewPass, "El usuario modificó su contraseña"))
					{
						
						membership.setPassword(encriptNewPass);						
						membershipService.update(membership);
						msg="Contraseña modificada satisfactoriamente.";
						
						ProspectusExtra extra =  new ProspectusExtra();
						
						extra.setProspectus_id(membership.getMembershipPK().getProspectus_id());
						extra.setValue1_ps( Utilities.encodeBase64(getNewPass()));
						
						prospectusextraservice.saveProspectusExtra(extra);
						
						PasswordHistoryPK pwdhpk = new PasswordHistoryPK();
						pwdhpk.setCompany_id(sesion.getCompany_id());
						pwdhpk.setProspectus_id(sesion.getProspectus_id());
						
						PasswordHistory pwdH = new PasswordHistory();
						pwdH.setPassword( encriptNewPass );
						pwdH.setDate_changed(new Date());
						pwdH.setPwdhpk(pwdhpk);
						
						if( passwordhistoryservice.savePasswordHistory(pwdH) ){
							
						}
						
						init_notificar_evento();						
						
						flagsuccess = true;
						
					}else{
						
						flagsuccess=false;
						msg="Error al agregar cambios";
						
					}
				
				}else{
					
					msg= "La contaseña ya la haz proporcionado anteriormente, por tu seguridad prueba con una nueva contraseña .";
					
				}
				
			}else{
				
				msg="Las contraseñas no coinciden";
				
			}
			
		}else{
			
			msg="Contraseña actual incorrecta.";
			
		}	
		
		request.addCallbackParam("isValid", flagsuccess);
		request.addCallbackParam("msg", msg);	
		request.addCallbackParam("typeedit",3);
	}

	public void addSequerityQuest()
	{		
		request  = RequestContext.getCurrentInstance();
		faces    = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		HeaderBean header = (HeaderBean) resolver.getValue(context, null, "headerBean");
		
		header.activeSession();
		
		pool_preguntas_seguridad = service_pool_preguntas.loadSecQuestPoolByProspect(prospectus_id, company_id);
		
		init_preguntas_seguridad();
		
		pantalla_a_mostrar = "secciones/perfil/preguntas_seguridad/panel_edicion.xhtml";
	}
	

private void saveFullName(){
		
		String first_name       = person.getFirst_name()        == null ? "" : person.getFirst_name().trim();	
		String middle_name      = person.getMiddle_name()       == null ? ""  : person.getMiddle_name().trim();
		String father_last_name = person.getFather_last_name()  == null ? "" : person.getFather_last_name().trim();
		String mother_last_name = person.getMother_last_name()  == null ? "" : person.getMother_last_name().trim();
		
		String fullnameStr = first_name; 
		
		if( middle_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr +=  middle_name;
			
		}
		if( father_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr += father_last_name;
			
		}
		if( mother_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr	+= mother_last_name ;
			
		}
		
		FullNamePK fpk = new FullNamePK();
		
		fpk.setCompany_id(person.getNatPerPK().getCompany_id());
		fpk.setProspectus_id(person.getNatPerPK().getProspectus_id());
		
		FullName fullname = fullnameservice.getFullName(fpk);
		
		if( fullname == null ){
			
			fullname = new FullName();
			
			fullname.setPk(fpk);
			fullname.setEmail( membership.getEmail());
			fullname.setFull_name(fullnameStr);
			
			fullnameservice.saveFullName(fullname);
			
		}else{
			
			fullname.setFull_name(fullnameStr);
			fullnameservice.updateFullName(fullname);
			
		}
		
	}
	
	
}
