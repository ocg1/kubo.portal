package mx.com.kubo.services.fondeo.credito;

import java.text.SimpleDateFormat;
import java.util.List;

import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SolicitudCreditoRequest;
import safisrv.ws.CreditosServicios.SolicitudCreditoResponse;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Deposit_method;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.tools.Utilities;

public abstract class SolicitudCreditoDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios service_SAFI;
	
	protected SolicitudCreditoRequest  request;
	protected SolicitudCreditoResponse response;
	
	protected Deposit_method tipo_deposito;
	
	protected List<ClabeAccount> lista_cuentas_bancarias;	
	
	protected String kubo_score_A;
	protected String kubo_score_B;
	protected String monto_comision;
	protected String pago_inicial;
	protected String periodicidad;
	protected String fecha_registro;	
	
	protected Double monto_solicitado;
	protected Double comision;
	protected Double tasa_activa;
	
	protected Integer estatus_id;
	protected Integer tipo_credito;
	
	protected int frequency_id;
	protected int plazo;	
	
	protected final String SAFI_SOLICITUD_CREDITO_INIT;
	protected final String SAFI_SOLICITUD_CREDITO_OK;
	
	protected SolicitudCreditoDMO()
	{
		service_calling       = Utilities.findBean("serviceCallingServiceImp");
		service_proyect_loan  = Utilities.findBean("proyectLoanServiceImp");
		service_clabe_account = Utilities.findBean("clabeAccountServiceImp");
		
		date_format = new SimpleDateFormat("yyyy-MM-dd");
		
		SAFI_SOLICITUD_CREDITO_INIT = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudCredito(): INIT ";
		SAFI_SOLICITUD_CREDITO_OK   = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudCredito(): OK ";
		
		monto_comision = "0";	
	}
	
	public final void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			company_id         = proyect_loan.getPerson().getNatPerPK().getCompany_id();
			prospectus_id      = proyect_loan.getPerson().getNatPerPK().getProspectus_id();		
			
			SAFI_prospectus_id = proyect_loan.getPerson().getProspectus().getSafi_prospectus_id();
			SAFI_client_id     = proyect_loan.getPerson().getSafi_client_id();			
			SAFI_solicitud_id  = proyect_loan.getSafi_mx_solicitud_id();
			
			estatus_id         = proyect_loan.getStatus_id();
						
			SAFI_solicitud_NEW = SAFI_solicitud_id == null || (SAFI_solicitud_id + "").length() == 0 || estatus_id == null || estatus_id == 0 || (estatus_id + "").length() == 0;			
		}
	}
	
	protected String getMontoComision(ProyectLoan pl)
	{
		comision = 0D;
		
		if(pl.getOpening_commission() != null && (pl.getOpening_commission()+"").length() > 0)
		{
			Double c = pl.getOpening_commission() / 100;
			
			comision = c * pl.getAmmount();
			//comision = ((comision)/(1.16));
			//comision = ((double)Math.round((comision)*100)/100);
		}
		
		return comision.toString();
	}
}
