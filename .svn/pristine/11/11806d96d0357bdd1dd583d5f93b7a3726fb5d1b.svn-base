package mx.com.kubo.managedbeans.mesa.reportes.buro;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.IdentifiedCreditService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RecommendationTypeService;
import mx.com.kubo.services.SimulationConfigService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

public interface ChartBackBeanIMO 
{
	void setProyectLoanService     (ProyectLoanService        service);
	void setNoteService            (NotesService              service);
	void setBankService            (BankService               service);
	void setRecommendationservice  (RecommendationTypeService service);
	void setIdentifiedcreditservice(IdentifiedCreditService   service);
	void setMembershipservice      (MembershipService         service);
	void setChangeControlService   (Change_controlService     service);
	void setNaturalPersonService   (NaturalPersonService      service);
	void setSystemparamservice     (SystemParamService        service);
	void setAccessService          (AccessService             service);
	void setSimulationConfigService(SimulationConfigService   service);
		
	Notes getNota_del_coach();
	
	ProyectLoan getProyecto();
	
	String getCci_score();
	String getKubo_score_class();
	String getCoach_nombre();
	String getCoach_fecha_nota();
	
	Character getKubo_score_letter();
	Character getKubo_score_number();
	
	Integer getConsultas_propias();
	
	boolean isIdProvider();
	boolean isNota_coach_ENABLED();
	
	void setBurSolNum(String burSolNum);
	void loadChart();
}
