package mx.com.kubo.managedbeans.cliente;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.cliente.cuenta.ServiceReferenciaPagoIMP;

@ManagedBean(name = "liquidar_credito") @ViewScoped
public final class CreditoLiquidacionIMP extends CreditoLiquidacionDMO
implements CreditoLiquidacionIMO, Serializable
{
	protected static final long serialVersionUID = 7343541930997229042L;
	
	@PostConstruct
	public void init()
	{	
		
		
		FacesContext faces      = FacesContext.getCurrentInstance();
		ELResolver resolver   	= faces.getApplication().getELResolver();
		ELContext context_EL 	= faces.getELContext();
		
		sesion     = (SessionBean) resolver.getValue(context_EL, null, "sessionBean");	
		
		//sesion = service_sesion.getSesion();
		
		sesion_ENABLED = sesion != null && sesion.getCompany_id() != null && sesion.getProspectus_id() != null;
		
		if(sesion_ENABLED)
		{
			
			gnNaturalPersonPK pk = new gnNaturalPersonPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
			natural_person = persona_service.getNaturalPersonById(pk); 
			
			monto_del_pago = sesion.getSaldo_liquidacion();
			service_referencia_pago = new ServiceReferenciaPagoIMP();
			service_referencia_pago.setAcreditado(natural_person.NombreCompletoNPM());
			service_referencia_pago.setCredito_id(sesion.getCredito_SAFI());
			service_referencia_pago.setCuota     (monto_del_pago);
			
			referencia_pago = service_referencia_pago.getReferencia_pago();
		
			fecha_del_pago = date_format.format(new Date());
			
		} else {
			
			// .redirigir("../../expired.jsf");
		}
	}
	
	public final void listener_monto_del_pago(AjaxBehaviorEvent evento)
	{
		ajax_input_text = (HtmlInputText) evento.getComponent();
		
		monto_del_pago = (String) ajax_input_text.getValue();
		
		System.out.println("CreditoLiquidacionIMP.listener_monto_del_pago(): " + monto_del_pago);
	}
	
	public final void listener_fecha_del_pago(AjaxBehaviorEvent evento)
	{
		ajax_input_text = (HtmlInputText) evento.getComponent();
		
		fecha_del_pago = (String) ajax_input_text.getValue();
		
		calendar 	      = Calendar.getInstance();
		selected_calendar = Calendar.getInstance();		
		
		fecha_pago_ENABLED = false;
		
		try 
		{
			selected_calendar.setTime(date_format.parse(fecha_del_pago));
			
			fecha_pago_ENABLED = selected_calendar.before(calendar);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		} finally {
			
			request = RequestContext.getCurrentInstance();
			request.addCallbackParam("fecha_pago_ENABLED", fecha_pago_ENABLED);
			
			System.out.println("CreditoLiquidacionIMP.listener_fecha_del_pago(): " + fecha_del_pago);			
		}
	}
	
	public final void listener_notificar_pago()
	{				
		try 
		{
			locator = new PublicProyectServiceLocator();
			
			service_kubo = locator.getPublicProyect();
			
			request_notificar_config = new NotificadorConfigRequest();
			
			request_notificar_config.setCompany_id           (sesion.getCompany_id() + "");
			request_notificar_config.setProspectus_id        (sesion.getProspectus_id() + "");			
			request_notificar_config.setSafi_mx_solicitud_id (sesion.getCredito_SAFI());
			
			request_notificar_config.setEvento_id   (NOTIFICACION_DE_PAGO_O_LIQUIDACION);
			request_notificar_config.setCalled_FROM ("CreditoLiquidacionIMP.listener_notificar()");				

			request_notificar_config.setMonto_deposito(monto_del_pago);
			request_notificar_config.setFecha_deposito(fecha_del_pago);
							
			response = service_kubo.notificar(request_notificar_config);						
			
			System.out.println("TestWebServiceIMP.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());		
			
			notificar_pago_OK = response.getStatus().equals("0") ? true: false;
			
			request = RequestContext.getCurrentInstance();
			request.addCallbackParam("notificar_pago_OK", notificar_pago_OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
