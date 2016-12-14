package mx.com.kubo.managedbeans.registro.publicacion;

import javax.faces.event.AjaxBehaviorEvent;

public interface DealIMO 
{
	
	void listener_reanudar_solicitud(AjaxBehaviorEvent evento);
	
	void altaProspectSafiAndProyectLoan();
	
	public void listener_ammount_simulation_deal( AjaxBehaviorEvent e );
	public void listener_term_simulation_deal( AjaxBehaviorEvent e );
	public void listener_frequency_simulation_deal( AjaxBehaviorEvent e );
	public void realizaSimulacionDeal( );
}
