package mx.com.kubo.mesa.solicitud;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.catalogos.UnusualBehavior;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;

public interface ReporteInusualIMO 
{
	void setService_catalogos(ServiceCatalogosIMO service);
	void setService_PLD      (PrevencionLDService service);
	void setService_access         (AccessService service);
	
	void setSesion(SessionBean sesion);
	void setPerson(NaturalPerson person);
	
	void init();
	void init_behavior_id (AjaxBehaviorEvent event);
	void init_comments    (AjaxBehaviorEvent event);
	void init_reporte();
	
	List<UnusualBehavior> getLista_unusual_behavior();
}
