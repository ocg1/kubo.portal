package mx.com.kubo.managedbeans.portal;

public class MembershipCheckIMP extends MembershipCheckAMO
implements MembershipCheckIMO
{
	public final void init()
	{
		if(membership != null)
		{
			init_membership();			
			init_ejecucion_cierre();
			init_blocked_person();
			
			if(!blocked_person_ENABLED)
			{
				init_canceled_ENABLED();
				init_fail_login_attempts();
				init_inversionista_ENABLED();
			}
			
		} else {
			
			membership_ENABLED = false;
			check_msg_TOKEN = "El usuario " + email + " no existe";
		}
		
		response.put("valido", membership_ENABLED);
		response.put("msg", check_msg_TOKEN);
		response.put("desbloqueo_password_ENABLED", desbloqueo_password_ENABLED);
	}
}
