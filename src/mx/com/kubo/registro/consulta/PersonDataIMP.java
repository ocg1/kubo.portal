package mx.com.kubo.registro.consulta;

import org.primefaces.context.RequestContext;

public final class PersonDataIMP extends PersonDataAMO
implements PersonDataIMO
{
	public final void init()
	{		
		i = 0;
		flag = false;
		displayAction = true;
					
		if(prospectus != null)
		{
			i = 1;
			flag = true;
		}
					
		init_credit_history();
		init_person();			
		init_address();						
		init_phone();						
		init_message_ERROR();
	}
				
	public final void getPersonData()
	{
		request = RequestContext.getCurrentInstance();								
		
		init_access();
		init_address_TOKEN();
		init_person_DATA();
		init_credit_history_DATA();
				
		request.addCallbackParam("displayAction", true);	
		request.addCallbackParam("isActive", true);
	}
}
