package mx.com.kubo.services.fondeo.cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;

import safisrv.ws.CuentasServicios.AutorizaCuentaRequest;
import safisrv.ws.CuentasServicios.AutorizaCuentaResponse;
import safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CuentasServicios.SAFIServicios;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.tools.Utilities;

public abstract class AutorizarCuentaDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{
	protected AutorizaCuentaRequest  request;
	protected AutorizaCuentaResponse response;
	
	protected SAFIServiciosServiceLocator locator;	
	protected SAFIServicios SAFI_web_service;
	
	protected String fecha_TODAY;
	
	protected boolean autorizar_cuenta_ENABLED;
	
	protected AutorizarCuentaDMO()
	{
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		
		locator = new SAFIServiciosServiceLocator();
		
		date_format = new SimpleDateFormat("yyyy-MM-dd");	
		
		fecha_TODAY = date_format.format(new Date());
	}	
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id       = acreditado.getNatPerPK().getCompany_id();
			prospectus_id    = acreditado.getNatPerPK().getProspectus_id(); 		
					
			init_saving_account();
			
			autorizar_cuenta_ENABLED = SAFI_account_id != null && SAFI_cuenta_autorizada < ACTIVADA;
		}
	}
	
	protected abstract void init_saving_account();
}
