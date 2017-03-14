package mx.com.kubo.rest.tienda.accounts;

public class CuentasClienteIMP extends CuentasClienteAMO
implements CuentasClienteIMO
{
	public void init()
	{
		try
		{					
			init_request();
			
			response = servCuentasCliente.consultaCuentasPorCliente(request);
			
			init_response();			
			init_investor_account();
			init_saldo_total();		

		} catch (Exception e) {
		
			e.printStackTrace();			
		}	
	}
}
