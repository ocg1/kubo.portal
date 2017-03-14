package mx.com.kubo.rest.tienda.accounts;

import java.util.ArrayList;

import mx.com.kubo.bean.InvestorsAccounts;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;

public abstract class CuentasClienteAMO extends CuentasClienteDMO 
{
	protected void init_request() 
	{
		SAFI_cuenta = proyectLoanService.getOnlySAFIAccount(prospectus_id + "");
		
		String cuenta = SAFI_cuenta.get(0).toString();
		
		request = new ConsultaCuentasPorClienteRequest(cuenta);
	}
	
	protected void init_response() 
	{
		response_OK = response.getInfocuenta() != null && response.getCodigoRespuesta() != null && response.getCodigoRespuesta()[0].equals("0");

		if(response_OK)
		{
			String cuenta_INFO = response.getInfocuenta()[0];					 
		
			cuentas = cuenta_INFO.split("\\&\\|\\&");
		}
	}
	
	protected void init_investor_account() 
	{
		 if(response_OK)
         {	             
             listInvAccounts = new ArrayList<InvestorsAccounts>();	             	              
                		
              for (int i = 0; i < cuentas.length; i++) 
              {	                	
                	String[] vars = cuentas[i].split("\\&\\;\\&",3);
                	
                	if(vars.length == 3)
                	{
                		account = new InvestorsAccounts();
                		
                		account.setAccount(vars[0]);
                		account.setAccountName(vars[1]);
                		account.setSaldo(vars[2].equals("null")?0.00:Double.parseDouble(vars[2]));
                		
                		listInvAccounts.add(account);
                	}             			                	
				}
            }	            

		 accountList = listInvAccounts.get(0).getAccount();	
	}
	
	protected void init_saldo_total() 
	{
		saldoObj = saldoinversionistaservice.getSaldoByAccount(accountList);
		
		if(saldoObj != null)
		{
			saldoTotal = saldoObj.getSaldoTotal();
			
		} else {
			
			saldoTotal = 0D;
		}
	}
}
