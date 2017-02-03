package mx.com.kubo.managedbeans.portal;

import java.util.Date;

import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.registro.verificacion.CierreDelDiaIMP;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMP;

public abstract class MembershipCheckAMO extends MembershipCheckDMO
{
	protected void init_membership() 
	{
		is_canceled = membership.getIs_canceled();
		password    = membership.getPassword();
		company_id  = membership.getMembershipPK().getCompany_id();
		is_blocked  = membership.getIs_blocked();
		is_active   = membership.getIs_active();
		
		failed_login_attempts = membership.getFailed_login_attempts();		
		
		area = person.getProspectus().getArea();
		
		is_safi_client = person.getSafi_client_id() != null && !person.getSafi_client_id().isEmpty();
		
		is_inversionista = area.equals('I');		
		is_mesa_control  = area.equals('M');
		is_acreditado    = area.equals('L');
		
		if(membership.getLast_login_attempt() != null)
		{
			last_login = date_format.format(membership.getLast_login_attempt());
			
		} else {
			
			last_login = date_format.format(new Date());
		}		
	}
	
	protected void init_ejecucion_cierre() 
	{
		if(!is_acreditado && !is_mesa_control && is_safi_client)
		{
			ejecutor = new CierreDelDiaIMP();
			ejecutor.setService_system_param(service_system_param);
			ejecutor.setMembership(membership);
			ejecutor.setSystem_param_id(EJECUCION_CIERRE_DEL_DIA);
			ejecutor.init();
			
			cierre_del_dia_ENABLED = ejecutor.isCierre_del_dia_ENABLED();
			
			if(cierre_del_dia_ENABLED)
			{
				membership_ENABLED = false;						
				
				if(is_inversionista)
				{
					check_msg_TOKEN = MSG_CIERRE_INVERSIONISTA;	
				}		
				
				if(is_mesa_control)
				{
					check_msg_TOKEN = MSG_CIERRE_MESA_CONTROL;	
				}	
			}		
		}
	}
	
	protected void init_blocked_person() 
	{
		if(!cierre_del_dia_ENABLED)
		{
			blocked_person = new PersonaBloqueadaIMP();
			blocked_person.setSesion(sesion);
			blocked_person.setPerson(person);
			blocked_person.setTipo_de_Operacion(OPERACION_PERSONA_BLOQUEADA);
			blocked_person.init();
			
			blocked_person_ENABLED = blocked_person.isBlocked_person_ENABLED();
			
			if(blocked_person_ENABLED)
			{
				membership_ENABLED = false;
				
				check_msg_TOKEN = "Operación de persona bloqueada";
			}
		}
	}
	
	protected void init_canceled_ENABLED() 
	{
		canceled_ENABLED = is_canceled != null && is_canceled.trim().length() > 0 && (!is_canceled.equals("N"));
		
		if(is_canceled != null && is_canceled.equals("SK"))
		{
			desbloqueo_password_ENABLED = false;
		}
		
		if(canceled_ENABLED)
		{
			membership_ENABLED = false;
			
			sb = new StringBuilder();
			sb.append("<span style='font-size: 1.0em; color: #ff0000;'>La cuenta <b>");
			sb.append(email);
			sb.append("</b> está cancelada.<br />");
			sb.append("*Si existe un error o deseas reactivarla de nuevo ponte en contacto con nosotros al teléfono: <b>");
			sb.append(recurso.getString("Kubo_telefono"));
			sb.append("</b>  </span> ");
			
			check_msg_TOKEN = sb.toString();
			
		}
	}
	
	protected void init_fail_login_attempts() 
	{
		if(failed_login_attempts >= 3 && failed_login_attempts < MAX_FAILED_LOGIN)
		{
			sb = new StringBuilder();
			
			sb.append("Has tenido ");
			sb.append(failed_login_attempts);
			sb.append(" intentos fallidos de ");
			sb.append(MAX_FAILED_LOGIN);
			sb.append(" posibles");
			
			check_msg_TOKEN = sb.toString();
			
		}
		
		if(failed_login_attempts >= MAX_FAILED_LOGIN || (is_blocked != null && is_blocked.equals("S")))
		{
			membership_ENABLED = false;
			check_msg_TOKEN = "La contraseña ha sido bloqueda";	
			desbloqueo_password_ENABLED = true;
		}		
	}
	
	protected void init_inversionista_ENABLED() 
	{
		system_param_PK = new SystemParamPK();			
		system_param_PK.setCompany_id(company_id);
		system_param_PK.setSystem_param_id(SECCION_INVERSIONISTA_ENABLED);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		if(system_param.getValue().equals("N") && (is_active != 1) )
		{			
			if(area.equals("I"))
			{
				membership_ENABLED = false;
				check_msg_TOKEN = "Usted está registrado como INVERSIONISTA. Por el momento esta funcionalidad no está disponible, próximamente le informaremos cuándo puede comenzar a invertir. ";
			}
			
		}
	}
}
