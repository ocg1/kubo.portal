package mx.com.kubo.managedbeans.registro.publicacion;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.impl.SystemParamServiceImp;

public interface DealIMO 
{
	void setSimulatorService        (SimulatorService         service);
	void setProyectloanService      (ProyectLoanService       service);
	void setProyectService          (ProyectService           service);
	void setScoringService          (ScoringService           service);
	void setProspectusService       (ProspectusService        service);
	void setNaturalPersonService    (NaturalPersonService     service);
	void setAddressService          (AddressService           service);
	void setPhoneService            (PhoneService             service);
	void setTownService             (TownService              service);
	void setNeighborhoodService     (NeighborhoodService      service);
	void setProyectloanservice      (ProyectLoanService       service);
	void setServicecallingService   (ServiceCallingService    service);
	void setProyectservice          (ProyectService           service);
	void setFileTypeService         (FileTypeService          service);
	void setFilesService            (FilesService             service);
	void setAccessService           (AccessService            service);
	void setNotificador             (NotificadorIMO           service);
	void setReasignador_service     (ReasignadorIMO           service);
	void setSystem_param_service    (SystemParamServiceImp    service);
	void setMembershipservice       (MembershipService        service);
	void setEventnotificationservice(EventNotificationService service);
	void setClabeaccountservice     (ClabeAccountService      service);
	void setServicecallingRepository(ServiceCallingDao        service);
	void setBankService             (BankService              service);	
	
	void listener_reanudar_solicitud(AjaxBehaviorEvent evento);
	
	void altaProspectSafiAndProyectLoan();
	
	public void listener_ammount_simulation_deal( AjaxBehaviorEvent e );
	public void listener_term_simulation_deal( AjaxBehaviorEvent e );
	public void listener_frequency_simulation_deal( AjaxBehaviorEvent e );
	public void realizaSimulacionDeal( );
}
