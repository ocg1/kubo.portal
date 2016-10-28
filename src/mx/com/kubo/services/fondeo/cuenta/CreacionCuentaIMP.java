package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.services.fondeo.ServiceFondeoIMO;

public final class CreacionCuentaIMP extends CreacionCuentaAMO
implements ServiceFondeoIMO
{
	public void init() 
	{	
		try
		{												
			if(SAFI_account_NEW)
			{				
				init_values();
				init_request();
				init_cuenta();
				init_response();
				
			}else{
				cuenta_OK = true;
			}
			
		} catch(Exception e) {																			
			
			if(e.getStackTrace().toString().length() > 1999)
			{
				msgerror = e.getStackTrace().toString().substring(0,1999);
				
			} else {
				
				msgerror = e.getStackTrace().toString();
			}
			
			lista_errores.add(SAFI_CREACION_CUENTA_ERROR);
			
			service_calling.registrar(ERROR, prospectus_id, company_id, SAFI_CREACION_CUENTA_ERROR, msgerror);
			
			SAFI_account_id = null;
		}		
	}
}
