package mx.com.kubo.managedbeans.registro.consulta;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.consulta.AutorizadorIMO;
import mx.com.kubo.registro.consulta.PersonDataIMO;
import mx.com.kubo.registro.consulta.historial.car.CarIMO;
import mx.com.kubo.registro.consulta.historial.card.CreditCardIMO;
import mx.com.kubo.registro.consulta.historial.mortage.MortageIMO;
import mx.com.kubo.registro.consulta.historial.SearchRequestIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.CreditHistoryService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

public interface CreditHistoryControllerIMO 
{
	void setService_membership    (MembershipService    service);
	void setService_notas         (NotesService         service);
	void setService_credit_history(CreditHistoryService service);
	void setServicioProyecto      (ProyectLoanService   service);
	void setScoringService        (ScoringService       service);
	void setResidenciaService     (ResidenceService     service);
	void setBankService           (BankService          service);
	void setPhoneService          (PhoneService         service);
	void setAddressService        (AddressService       service);
	
	   PersonDataIMO getData();
	SearchRequestIMO getBank_engine();
	   CreditCardIMO getCreditcard();
	      MortageIMO getMortage();
	          CarIMO getCar();
	  AutorizadorIMO getConsulta();
	
	String getNota_del_coach();
	
	void listener_asignar_nota_del_coach (AjaxBehaviorEvent evento);	
	
	void listener_guardar_nota_del_coach();
	
	void saveCreditHistory();
}
