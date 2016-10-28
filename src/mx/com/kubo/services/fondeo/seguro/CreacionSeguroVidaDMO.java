package mx.com.kubo.services.fondeo.seguro;

import safisrv.ws.CreditosServicios.SegurosVidaRequest;
import safisrv.ws.CreditosServicios.SegurosVidaResponse;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.tools.Utilities;

public abstract class CreacionSeguroVidaDMO extends ServiceFondeoDMO 
{
	protected SegurosVidaRequest  request;
	protected SegurosVidaResponse response;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected String cobertura;
	protected String companiaAsegura;
	protected String polizaSeguro;
	
	protected Double monto_prima;
	protected Double monto_fondeo;
	
	protected int proyect_loan_id;
	protected int proyect_id;
	
	protected CreacionSeguroVidaDMO()
	{
		service_system_param   = Utilities.findBean("systemParamServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		service_SAFI           = Utilities.findBean("serviciosSAFIServiceImp");
		
		cobertura       = "";
		companiaAsegura = "";
		polizaSeguro    = "";
	}
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id     = acreditado.getNatPerPK().getCompany_id();
			prospectus_id  = acreditado.getNatPerPK().getProspectus_id();
			SAFI_client_id = acreditado.getSafi_client_id();
			
			SAFI_credit_id  = proyect_loan.getSafi_credit_id();	
			SAFI_seguro_id  = proyect_loan.getSafi_mx_seguro_id();
			
			proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
			proyect_id      = proyect_loan.getProyect().getProyectoPk().getProyect_id();
			monto_prima     = proyect_loan.getAmmount();
			monto_fondeo    = proyect_loan.getAmount_founded();
			
			init_saving_account();
			
			SAFI_seguro_NEW = (SAFI_seguro_id == null || SAFI_seguro_id.length() == 0) && (SAFI_credit_id != null && SAFI_credit_id.length() > 0);
		}
	}
	
	protected abstract void init_saving_account();
}
