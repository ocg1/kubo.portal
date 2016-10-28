package mx.com.kubo.managedbeans.cliente;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;

import org.primefaces.context.RequestContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.referencia_pago_panel.PanelAMO;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.cliente.cuenta.ServiceReferenciaPagoIMP;
import mx.com.kubo.session.SessionService;

public abstract class CreditoLiquidacionDMO 
implements CreditoLiquidacionIMO
{
	
	protected ServiceReferenciaPagoIMP service_referencia_pago;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService persona_service;
	
//	@ManagedProperty("#{sessionService}")
//	protected SessionService servi00ce_sesion;	
	
	protected PublicProyectServiceLocator locator;
	protected PublicProyect service_kubo;
	
	protected NotificadorConfigRequest  request_notificar_config;
	protected WsResponse response;
	
	protected SessionBean   sesion;
	protected NaturalPerson natural_person;
	
	protected PanelAMO referencia_pago;
	
	protected RequestContext request;
	protected HtmlInputText  ajax_input_text;
	
	protected Calendar calendar;
	protected Calendar selected_calendar;
	
	protected SimpleDateFormat date_format;
	
	protected String fecha_del_pago;
	protected String monto_del_pago;
	
	protected final String NOTIFICACION_DE_PAGO_O_LIQUIDACION;
	
	protected boolean sesion_ENABLED;
	protected boolean fecha_pago_ENABLED;
	protected boolean notificar_pago_OK;
	
	protected CreditoLiquidacionDMO()
	{
		date_format = new SimpleDateFormat("dd/MM/yyyy");
		
		NOTIFICACION_DE_PAGO_O_LIQUIDACION = "28";
		
		//service_referencia_pago = new ServiceReferenciaPagoIMP(); 
	}

	public final void setService_referencia_pago(ServiceReferenciaPagoIMP service) 
	{
		service_referencia_pago = service;
	}

	
	public final void setFecha_del_pago(String fecha)
	{
		fecha_del_pago = fecha;
	}

	public final void setMonto_del_pago(String monto)
	{
		monto_del_pago = monto;
	}
	
	 public final PanelAMO getPanel()
	 {
		 return referencia_pago;
	 }

	public final String getFecha_del_pago() 
	{
		return fecha_del_pago;
	}

	public final String getMonto_del_pago() 
	{
		return monto_del_pago;
	}

	public NaturalPersonService getPersona_service() {
		return persona_service;
	}

	public void setPersona_service(NaturalPersonService persona_service) {
		this.persona_service = persona_service;
	}
}
