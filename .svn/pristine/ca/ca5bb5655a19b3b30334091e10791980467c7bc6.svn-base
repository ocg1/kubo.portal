package mx.com.kubo.managedbeans.cliente;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.referencia_pago_panel.PanelAMO;
import mx.com.kubo.session.SessionService;

public interface CreditoLiquidacionIMO 
{
	//void setService_sesion(SessionService service);
	
	void setFecha_del_pago(String fecha);
	void setMonto_del_pago(String monto);
	
	void listener_monto_del_pago(AjaxBehaviorEvent evento);
	void listener_fecha_del_pago(AjaxBehaviorEvent evento);
	void listener_notificar_pago();
	
	PanelAMO getPanel();
	
	String getFecha_del_pago();
	String getMonto_del_pago();
}
