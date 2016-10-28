package mx.com.kubo.services.mesa.solicitud.estatus;

import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.ProyectLoan;

public interface EditorEstatusIMO 
{			
	void setEmisor_prospectus_id(int emisor_prospectus_id);
	void setCaso_pospuesto(CasosPospuestosIMO caso_pospuesto);
	
	void setEstatus_ORIGINAL(EstatusProyectLoan estatus_ORIGINAL);
	void setEstatus_NEW     (EstatusProyectLoan estatus_NEW);
	
	void setMotivo_id_selected(String motivo_id_selected);
	void setDescripcion_motivo(String descripcion_motivo);
	
	void init_proyect_loan(ProyectLoan proyect_loan);
	boolean guardar_cambio_de_estatus();
}
