package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class AltaConocimientoCuentaIMP extends AltaConocimientoCuentaAMO
implements ServiceFondeoIMO
{	
	public final void init() 
	{		
		if(SAFI_account_NEW)
		{
			init_alta_conocimiento_cuenta();
			init_response();
		}
	}
}
