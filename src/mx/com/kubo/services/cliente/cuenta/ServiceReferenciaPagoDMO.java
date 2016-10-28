package mx.com.kubo.services.cliente.cuenta;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.referencia_pago_panel.PanelDAO;
import mx.com.kubo.referencia_pago_panel.PanelEMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.session.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ServiceReferenciaPagoDMO 
{		
	protected PanelDAO dao;	
		
	protected AccessService service_access; 
	
	protected ProspectusService prospecto_service;
	
	protected NaturalPersonService persona_service;
	
	protected SessionBean   sesion;
	protected Prospectus    prospecto; 
	protected NaturalPerson persona;	
	
	protected Access 		 access;	
	
	protected PanelEMO panel;
	
	protected StringBuilder log_message;
	
	protected String acreditado;
	protected String credito_id;
	protected String cuota;
	protected String saldo_liquidacion;
	
	protected Integer prospectus_id, company_id;
	
	protected final int REFERENCIA_PAGO = 30;
	protected final int REFERENCIA_PAGO_LIQUIDACION = 42;
	
	public final void setService_access(AccessService service)
	{
		service_access = service;
	}
	
//	public final void setService_session(SessionService service) 
//	{
//		service_session = service;
//	}

	public final void setAcreditado(String acreditado) 
	{
		this.acreditado = acreditado;
	}

	public final void setCredito_id(String credit_id) 
	{
		this.credito_id = credit_id;
	}

	public final void setCuota(String cuota) 
	{
		this.cuota = cuota;
	}

	public final void setSaldo_liquidacion(String saldo)
	{
		saldo_liquidacion = saldo;
	}
	
	public void setProspectus_id( Integer prospectus_id ){
		this.prospectus_id = prospectus_id;
	}
	
	public void setCompany_id( Integer company_id ){
		this.company_id = company_id;
	}

	public  String getSaldo_liquidacion()
	{
		return saldo_liquidacion;
	}
	
}
