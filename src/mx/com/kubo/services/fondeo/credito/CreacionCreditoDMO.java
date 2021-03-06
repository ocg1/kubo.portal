package mx.com.kubo.services.fondeo.credito;

import java.util.Date;
import java.util.List;

import com.soa.model.businessobject.BurResume;

import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CreditosServicios.CreaCreditoRequest;
import safisrv.ws.CreditosServicios.CreaCreditoResponse;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class CreacionCreditoDMO extends ServiceFondeoDMO 
{
	protected CreaCreditoRequest  request;	
	protected CreaCreditoResponse response;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk service;
	
	protected BurResume resume;
	protected ClabeAccount cuenta_bancaria;
	
	protected List<ClabeAccount> lista_cuentas_bancarias;
	
	protected ConvertCalendar calendar;
	
	protected Date date_consulta;
	
	protected String solicitud_buro_id;
	protected String mx_clabe;
	protected String fecha_consulta;
	protected String folio_consulta;
	
	protected Integer deposit_method_id;
	
	protected boolean creacion_credito_ENABLED;
	
	protected CreacionCreditoDMO()
	{
		service_clabe_account = Utilities.findBean("clabeAccountServiceImp"); 
		service_SAFI          = Utilities.findBean("serviciosSAFIServiceImp");
		service_proyect_loan  = Utilities.findBean("proyectLoanServiceImp");
		service_system_param  = Utilities.findBean("systemParamServiceImp");
	}
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			       acreditado = proyect_loan.getPerson();
			deposit_method_id = proyect_loan.getDeposit_method_id();
			
			company_id     = acreditado.getNatPerPK().getCompany_id();
			prospectus_id  = acreditado.getNatPerPK().getProspectus_id();
			
			SAFI_solicitud_id  = proyect_loan.getSafi_mx_solicitud_id();
			SAFI_credit_id     = proyect_loan.getSafi_credit_id();
			solicitud_buro_id  = proyect_loan.getMx_solicitud_buro();
			
			SAFI_solicitud_ENABLED = SAFI_solicitud_id != null && !SAFI_solicitud_id.equals("") && !SAFI_solicitud_id.equals("0");			
			SAFI_credit_NEW        = SAFI_credit_id    == null || SAFI_credit_id.length() == 0;
		}
	}
}
