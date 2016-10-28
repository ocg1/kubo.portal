package mx.com.kubo.test.acreditado;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.mesa.solicitud.estatus.EditorEstatusIMO;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

public interface PanelSeleccionIMO 
{
	void listener_acreditado_selected   (SelectEvent       evento);
	void listener_proyecto_selected     (SelectEvent       evento);	
	void listener_proyect_loan_selected (SelectEvent       evento);	
	void listener_status_id_selected    (AjaxBehaviorEvent evento);	
	void listener_proyect_loan_edited   (RowEditEvent      evento);
	
	void guardar_cambio_de_estatus();
		
	void setService_membership       (MembershipService       service);
	void setService_proyect          (ProyectService          service);	
	void setService_proyect_loan     (ProyectLoanService      service);
	void setService_status           (StatusProyectCatService service);
	void setService_motivos          (MotiveService           service);
	void setService_editor_estatus   (EditorEstatusIMO        service);
	
	void setCaso_pospuesto        (CasosPospuestosIMO caso_pospuesto);
	
	void setUsuario_selected      (Membership selected);
	void setAcreditado_selected   (Membership  selected);
	
	void setProyecto_selected     (Proyect     selected);
	void setProyect_loan_selected (ProyectLoan selected);
	
	void setStatus_id_selected    (String      selected);
	void setMotivo_id_selected    (String      selected);
	void setDescripcion_motivo    (String      descripcion);
	
	CasosPospuestosIMO getCaso_pospuesto() ;
	
	Membership getUsuario_selected();
	Membership getAcreditado_selected();
	
	Proyect     getProyecto_selected();
	ProyectLoan getProyect_loan_selected();
	
	String getStatus_id_selected();
	String getMotivo_id_selected();
	String getDescripcion_motivo();
	
	List<Membership> getLista_membership();
	List<Membership> getLista_filtrada();
	List<Membership> getLista_acreditados();
	
	List<Proyect>          getLista_proyectos();
	List<ProyectLoan>      getLista_proyect_loan();
	List<StatusProyectCat> getLista_status();
	List<Motive>           getLista_motivos();
}
