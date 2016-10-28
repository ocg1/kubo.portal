package mx.com.kubo.services.fondeo.cuenta;

import safisrv.ws.CuentasServicios.AltaConocimientoCtaResponse;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.tools.Utilities;

public abstract class AltaConocimientoCuentaDMO extends ServiceFondeoDMO
{
	protected AltaConocimientoCtaResponse response;	
	
	protected SavingAccount   saving_account;
	protected SavingAccountPK saving_account_PK;
	
	protected PrevencionLD pld;
		
	protected AltaConocimientoCuentaDMO()
	{
		service_PLD            = Utilities.findBean("prevencionLDServiceImp");	
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		service_SAFI           = Utilities.findBean("serviciosSAFIServiceImp");
	}
	
	protected abstract void init_PLD();
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id    = acreditado.getNatPerPK().getCompany_id();
			prospectus_id = acreditado.getNatPerPK().getProspectus_id();
			
			init_PLD();
		}
	}
}
