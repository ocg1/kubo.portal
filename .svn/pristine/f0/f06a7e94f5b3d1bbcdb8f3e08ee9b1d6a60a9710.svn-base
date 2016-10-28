package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public class AltaRelacionadosCuentaIMP extends AltaRelacionadosCuentaAMO
implements ServiceFondeoIMO
{
	public void init() 
	{
		if(SAFI_related_person_NEW)
		{
			view_client_info = service_client_INFO.getClientInfo(prospectus_id, company_id);
			
			init_request();
			init_alta_relacionado();
		}
	}
}
