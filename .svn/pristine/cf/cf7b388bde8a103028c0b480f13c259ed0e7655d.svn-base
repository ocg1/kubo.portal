package mx.com.kubo.managedbeans.portal;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.registro.verificacion.CierreDelDiaIMO;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMO;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public abstract class MembershipCheckDMO
implements MembershipCheckIMO
{
	protected LoginServiceIMO    service_login;
	protected SystemParamService service_system_param;
	protected MembershipService membershipservice;
	
	protected PersonaBloqueadaIMO blocked_person;
	protected     CierreDelDiaIMO ejecutor;
	
	protected SessionBean sesion;
	protected Membership membership;
	protected NaturalPerson person;
	protected Prospectus prospecto;
	
	protected List<Membership> lista_membership;
	
	protected Hashtable<String, Object> response;
	protected ResourceBundle recurso;
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected StringBuilder sb;
	
	protected SimpleDateFormat date_format;
	
	protected String is_blocked;
	protected String is_canceled;
	protected String last_login;
	protected String email;
	protected String password;
	protected String check_msg_TOKEN;
	
	protected String fb_id;
	protected String fb_mail;
	
	protected Character area;
	
	protected Integer is_active;
	protected Integer failed_login_attempts;
	
	protected int company_id;
	
	protected final int OPERACION_PERSONA_BLOQUEADA = 1;
	protected final int SECCION_INVERSIONISTA_ENABLED = 51;
	protected final int MAX_FAILED_LOGIN = 5;
	protected final int EJECUCION_CIERRE_DEL_DIA = 89;
	
	protected final String MSG_CIERRE_INVERSIONISTA;
	protected final String MSG_CIERRE_MESA_CONTROL;
	
	protected boolean membership_ENABLED;
	protected boolean desbloqueo_password_ENABLED;
	protected boolean canceled_ENABLED;
	protected boolean blocked_person_ENABLED;
	protected boolean kubo_person_ENABLED;
	protected boolean cierre_del_dia_ENABLED;
	protected boolean mesa_ENABLED;
	protected boolean is_inversionista;
	protected boolean is_mesa_control;
	protected boolean is_acreditado;
	
	protected MembershipCheckDMO()
	{
		response = new Hashtable<String, Object>();
		
		date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
		
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		MSG_CIERRE_INVERSIONISTA = recurso.getString("cierre_del_dia_INVERSIONISTA");
		MSG_CIERRE_MESA_CONTROL  = recurso.getString("cierre_del_dia_MESA_DE_CONTROL");
		
		check_msg_TOKEN = "";
		 
		membership_ENABLED  = true;
		kubo_person_ENABLED = false;				 
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;				
				
/*		
		if(sesion.getArea() == 'M' && sesion.getEmail().indexOf("@kubofinanciero.com") == (-1))
		{
			kubo_person_ENABLED = true;
		}
*/		
	}
	
	public final void setFBMembership(String fb_id,String fb_mail) 
	{
		this.fb_id = fb_id;
		
		membership = service_login.getMembershipByfb_id(fb_id);
		
		if( membership != null ){
			
			person = membership.getPerson();
			
			prospecto = person.getProspectus();
			
			if(prospecto.getArea() == 'M' && membership.getEmail().indexOf("@kubofinanciero.com") > -1)
			{
				kubo_person_ENABLED = true;
			}
			
		}else{
		
			membership = service_login.getMembershipByEmail(fb_mail);	
			
			if(membership != null)
			{		
				person = membership.getPerson();
				
				membershipservice = Utilities.findBean("membershipServiceImp");
				
				prospecto = person.getProspectus();
				
				if(prospecto.getArea() == 'M' && membership.getEmail().indexOf("@kubofinanciero.com") > -1)
				{
					kubo_person_ENABLED = true;
				}
				
				membership.setFb_id(fb_id);
				
				membershipservice.update( membership );
				
				membership = service_login.getMembershipByfb_id(fb_id);
				
			}
		
		}
		
		
		
	}

	public final void setMemmbership(String email) 
	{
		this.email = email;
		
		membership = service_login.getMembershipByEmail(email);	
		
		if(membership != null)
		{		
			person = membership.getPerson();
			
			prospecto = person.getProspectus();
			
			if(prospecto.getArea() == 'M' && membership.getEmail().indexOf("@kubofinanciero.com") > -1)
			{
				kubo_person_ENABLED = true;
			}
		}
	}

	public final void setService_login(LoginServiceIMO service) 
	{
		service_login = service;
	}

	public final void setService_system_param(SystemParamService service) 
	{
		service_system_param = service;
	}
	
	public final boolean isMembership_ENABLED() 
	{
		return membership_ENABLED;
	}

	public final boolean isDesbloqueo_password_ENABLED() 
	{
		return desbloqueo_password_ENABLED;
	}

	public final boolean isBlocked_person_ENABLED() 
	{
		return blocked_person_ENABLED;
	}
	
	public final boolean isKubo_person_ENABLED()
	{
		return kubo_person_ENABLED;
	}

	public final String getCheck_msg_TOKEN() 
	{
		return check_msg_TOKEN;
	}

	public Membership getMembership() {
		return membership;
	}
}
