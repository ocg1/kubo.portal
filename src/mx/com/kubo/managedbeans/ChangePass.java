package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;


@ManagedBean @ViewScoped
public class ChangePass extends ChangePassDMO
implements Serializable 
{
	private static final long serialVersionUID = 7299733593910047562L;

	@PostConstruct
	public void init()
	{		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ELContext elContext = facesContext.getELContext();
		sesion = (SessionBean) facesContext.getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		MembershipPK memPK = new MembershipPK();
		
		memPK.setCompany_id(sesion.getCompany_id());
		memPK.setProspectus_id(sesion.getProspectus_id());
		
		member = membershipService.getMembershipById(memPK);
		
		name = member.getPerson().getFirst_name();
		email = member.getEmail();
		
		if( sesion.getArea() == 'L' ){
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(19); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			diasVal = sysParam.getValue();
		}
		if( sesion.getArea() == 'I' ){
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(20); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			diasVal = sysParam.getValue();
		}
		
		if( sesion.getArea() == 'M' ){
			
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(21); //dias de validez de password para Acreditado
			
			SystemParam sysParam = systemparamservice.loadSelectedSystemParam(sysPK) ;
			
			diasVal = sysParam.getValue();
		}
		
	}
	
	public void savePassword()
	{		
		try
		{			
			String pass = Utilities.encrypt(getNewPass());
			
			if(!passwordhistoryservice.existPassword(pass, sesion.getProspectus_id(), sesion.getCompany_id()) )
			{			
				member.setPassword(pass);
				membershipService.update(member);
				
				PasswordHistoryPK passHPK = new PasswordHistoryPK();
				
				passHPK.setCompany_id(sesion.getCompany_id());
				passHPK.setProspectus_id(sesion.getProspectus_id());
				
				
				PasswordHistory ph = new PasswordHistory();
				ph.setDate_changed(new Date());
				ph.setPassword(pass);
				ph.setPwdhpk(passHPK);
				
				passwordhistoryservice.savePasswordHistory(ph);
				
				init_notificar_evento();
				
				RequestContext requestContext = RequestContext.getCurrentInstance();		
				requestContext.addCallbackParam("saveAction", true );
				
				sesion.setBlochHeader(true);
				
			}else{
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.addCallbackParam("saveAction", false );
				requestContext.addCallbackParam("message", "La contraseña ya ha sido asignada anteriormente. Por favor intente con otra." );
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			RequestContext requestContext = RequestContext.getCurrentInstance();		
			
			requestContext.addCallbackParam("saveAction", false );
			requestContext.addCallbackParam("message", "No se concretó el cambio de contraseña." );
			
		}
		
	}
	
	public String entrar(){
		
		if(sesion.getArea().equals('M')){

			return "controlTable";
			
		}
		else if(sesion.getArea().equals('I')){
			List<SavingAccount> countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			if(countList!=null&&countList.size()>0){
				boolean inversiones= false;
				for(SavingAccount count : countList){
					if(count.getStatus()!=null&&count.getStatus()==1){
						inversiones=true;
						break;
					}
				}
				if(inversiones){
					
					Access access = new Access();
					access.setCompany_id     (sesion.getCompany_id());
					access.setProspectus_id  (sesion.getProspectus_id());
					access.setOp_system      (sesion.getOsbrawser());
					access.setHorizontal_size(sesion.getBrowser_height());
					access.setVertical_size  (sesion.getBrowser_width());
					access.setIpaddress      (sesion.getIP_address_client());
					access.setWeb_browser    (sesion.getNamebrawser());
					access.setWeb_browser_version(sesion.getVersionbrawser());
					access.setScreen_id(15);//pantalla de mis inversiones
					access.setPercentage(0);
					access.setUser_agent     (sesion.getUser_agent());
					access.setDevice_info    (sesion.getDevice_info());
					access.setUrl_access( sesion.getUrl_access() );
					access.setProspectus_id_coach (sesion.getCoachProspectus_id());
					access.setAccess_from		  (sesion.getAccess_from());
					access.setVersion_description (sesion.getVersion_description());
					
					accessService.add(access,true);
					
					
					return "inversiones";
				}else{
					
					return "registrar";
				}
			}else{
				
				String action = "";
				
				if(member.getPerson().getSafi_client_id() != null && member.getPerson().getSafi_client_id().trim().length() > 0){
												
					System.out.println("Session iniciada Satisfactoriamente!! ");
										
					action =  "edocuenta";
					
				}else{
					action = "registrar";
				}
				
				return action;
			}
		
		}else{
			return "registrar";
		}
		
	}
	
	private void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + member.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_CAMBIO_PASSWORD);
			request_notificar_config.setCalled_FROM("ChangePass.init_notificar_evento()");	
			request_notificar_config.setCompany_id(member.getMembershipPK().getCompany_id() + "");
			request_notificar_config.setProspectus_id(member.getMembershipPK().getProspectus_id() + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("ChangePass.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
