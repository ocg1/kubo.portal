package mx.com.kubo.managedbeans.investor;

public abstract class DisposicionEfectivoAMO extends DisposicionEfectivoDMO 
{			
	protected void init_lista_cuentas() 
	{
		if(listInvAccounts != null)
		{			
			if(listInvAccounts.size() > 0)
			{				
				if(listInvAccounts.size() == 1) 
				{					
					 tagAccount = listInvAccounts.get(0).getAccount();
					 
					 cuentaActual = listInvAccounts.get(0).getAccount();
					 saldoActual  = listInvAccounts.get(0).getSaldo();
					 
				} else {
					
					tagAccount   = "Ninguna cuenta";
					cuentaActual = "0";
					saldoActual  = 0D;
				}			
			}	
			
		} else {
			
			tagAccount   = "No hay cuenta";
			cuentaActual = "0";
			saldoActual  = 0D;		
		}	
	}
	
	protected void init_cuenta_CLABE() 
	{		
		lstClabes = clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, company_id);
		
		if(lstClabes != null && lstClabes.size() == 1)
		{
			
			clabeSel = lstClabes.get(0).getMx_clabe() == null ? "" : lstClabes.get(0).getMx_clabe() ;
			bnkStr   = lstClabes.get(0).getBank_description() == null ? "" : lstClabes.get(0).getBank_description() ;
			
			
		} else {
			
			clabeSel = "";			
		}	
	}
}
