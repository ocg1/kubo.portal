package mx.com.kubo.services.fondeo.cuenta;

import java.text.SimpleDateFormat;
import java.util.List;

import safisrv.ws.CuentasServicios.AltaCuentaRequest;
import safisrv.ws.CuentasServicios.AltaCuentaResponse;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.tools.Utilities;

public abstract class CreacionCuentaDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{	
	protected AltaCuentaRequest  request;	
	protected AltaCuentaResponse response;
		
	protected List<SavingAccount> lista_saving_account_ACTIVA;
	protected List<SavingAccount> lista_saving_account;
	
	protected List<ClabeAccount> lista_bancos;
	
	protected String msgerror;
	protected String fechaReg;
	protected String tipoCuentaID;
	protected String edoCuenta;
	protected String clabe;
	
	protected final String SAFI_CREACION_CUENTA_ERROR;
	
	protected Integer deposit_method_id;
	protected Integer electronic_statement;		
	
	protected CreacionCuentaDMO()
	{
		service_calling        = Utilities.findBean("serviceCallingServiceImp");
		service_clabe_account  = Utilities.findBean("clabeAccountServiceImp");
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		service_SAFI           = Utilities.findBean("serviciosSAFIServiceImp"); 
		
		date_format = new SimpleDateFormat("yyyy-MM-dd");
		
		SAFI_CREACION_CUENTA_ERROR  = "SAFIServiciosServiceLocator.serviciosSAFIservice.createCuentaSAFI(): ERROR";
		
		edoCuenta = "D";
		clabe     = "";
	}
	
	public final void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			       acreditado = proyect_loan.getPerson();
			deposit_method_id = proyect_loan.getDeposit_method_id();
			
			company_id           = acreditado.getNatPerPK().getCompany_id();
			prospectus_id        = acreditado.getNatPerPK().getProspectus_id();
			area                 = acreditado.getProspectus().getArea();
			electronic_statement = acreditado.getProspectus().getElectronic_statement();
			SAFI_client_id       = acreditado.getSafi_client_id();			
			
			init_saving_account();
						
			SAFI_account_NEW = (SAFI_client_id != null && saving_account != null) && (SAFI_account_id == null || SAFI_account_id.equals("") || SAFI_account_id.equals("0"));	
		}		
	}
	
	protected abstract void init_saving_account();
}
