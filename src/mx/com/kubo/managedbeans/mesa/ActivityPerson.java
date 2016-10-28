package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ShowChangeSession;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.tools.Utilities;

@ManagedBean(name = "activityPerson") @ViewScoped
public class ActivityPerson extends ActivityPersonPMO 
implements ActivityPersonIMO, Serializable 
{
	private static final long serialVersionUID = 5577855014808653123L;

	@PostConstruct
	public void init()
	{
		faces = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		external  = faces.getExternalContext();
		
		sesion        = (SessionBean)            resolver.getValue(context, null, "sessionBean");
		role_function = (RoleFunctionController) resolver.getValue(context, null, "roleFunctionController");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) external.getRequest();
		
		ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR"); 
		
        if(ipAddress == null)  
        {  
          ipAddress = httpServletRequest.getRemoteAddr();  
        }
		
		prospectusStr = (String) external.getRequestParameterMap().get("prospectus");
		companyStr 	  = (String) external.getRequestParameterMap().get("company");		
		
		if(prospectusStr != null)
		{
			prospectus_id = Integer.parseInt(prospectusStr);
		}
		
		if(companyStr != null)
		{
			company_id = Integer.parseInt(companyStr);
		}
		
		if(companyStr != null && prospectusStr != null)
		{
			cargaInfo();
		}				
	}
	
	public final void cargaInfo()
	{
		initValues();
		
		emisor_prospectus_id = sesion.getProspectus_id();
		
		membership_PK = new MembershipPK();
		
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id);
		
		membership = membershipservice.getMembershipById(membership_PK);
		
		area = membership.getPerson().getProspectus().getArea() + "";
		
		attemps = service_historial_consultas.getAttemptsByProspectus(prospectus_id);
		
		panel_consulta_ENABLED = attemps != null && attemps.size() > 0;
		
		if(area.equals("I"))
		{
			panel_inversionista_ENABLED = true;
			
			area_msg = "INVERSIONISTA";
			
			area_class = "area_inversionista";
			
		} else {
			
			panel_inversionista_ENABLED = false;
			
			area_msg = "ACREDITADO";
			
			area_class = "area_acreditado";
		}
				
		init_fecha_activacion();
		init_registration_reason();
		init_telefonos();						
		init_fecha_consulta();
		init_menu();
		init_permisos();
		init_bitacora_change_control();
	}
	
	public final void setListChanges(ChangeBean change)
	{
		faces    = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		showchange = (ShowChangeSession) resolver.getValue(context, null, "showChangeSession");
		
		showchange.setLstChanges(change.getLstChanges());
	}
	
	public final void listener_init_actividad()
	{	
		System.out.println("Iniciando Activity: "+datosInit);
		
		prospectus_id = 0;
		company_id = 0;
		
		if(datosInit != null && datosInit.length() > 0 && (datosInit.split("::")).length > 1)
		{		
			prospectus_id = Integer.parseInt((datosInit.split("::"))[0]);
			company_id = Integer.parseInt((datosInit.split("::"))[1]);
		}
		
		if(prospectus_id != 0 && company_id != 0)
		{
			cargaInfo();
		}		
	}
	
	public final void listener_borrar_consultas()
	{		
		ultima_consulta = service_historial_consultas.getUltima_consulta(prospectus_id, company_id);		
		
		service_historial_consultas.borrar_consulta(ultima_consulta);
		
		String table = "gn_credit_history_attempt";
		String field = "attempt_id";
		String origValue = ultima_consulta.getAttempt_id() + "";
		String newValue  = "";
		String comment = "Se eliminó la consulta del día " + frm.format(ultima_consulta.getConsultation_date());
		
		change_control_OK = saveChangeData(table, field,  origValue, newValue, comment);
		
		if(change_control_OK)
		{
			attemps = service_historial_consultas.getAttemptsByProspectus(prospectus_id);
			
			change_control_bean.setWhyChangeData(null);
			change_control_bean.setHasChange(true);
			
			bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
			if(bitacora_change_control != null && bitacora_change_control.size() > 0)
			{						
				change_control_bean.setLstChanges(bitacora_change_control);
				
			} else {
				
				change_control_bean.setLstChanges(null);	
			}								
		}		
		
		System.out.println("ActivityPerson.listener_borrar_consultas(): OK");
	}
		
	public final void generar_password_activacion()
	{
		try 
		{
			nuevo_password = "";
			
			do
			{
				nuevo_password += Utilities.getRandomCharacter();
				
			} while (nuevo_password.length() < 8);
			
			notificador.setEmisor(membership);
			notificador.notificar(Evento.GENERACION_PASSWORD_ACTIVACION, nuevo_password);
			
			membership.setPassword(Utilities.encrypt(nuevo_password));
			
			membershipservice.update(membership);
			
			System.out.println("ActivityPersonPMO.generar_password_activacion(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}				
	}
		
	public final void updateConsulting()
	{
		attemps = service_historial_consultas.getAttemptsByProspectus(prospectus_id);
		
		init_fecha_consulta();
		init_menu();
	}		
}
