package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public class AutorizarCuentaIMP extends AutorizarCuentaAMO
implements ServiceFondeoIMO 
{	
	public void init() 
	{				
		if(autorizar_cuenta_ENABLED)
		{
			init_request();
			init_autorizar_credito();	
			init_response();
		}
	}
}
