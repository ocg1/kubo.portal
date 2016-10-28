package mx.com.kubo.managedbeans.mesa.solicitud.perfil;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InvestorCategory;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.InstitutionalInvestorService;
import mx.com.kubo.services.ProyectLoanService;

public interface FondeadorIMO 
{
	void setService_fondeador(InstitutionalInvestorService service);
	void setService_proyect_loan(ProyectLoanService        service);
	void setService_change_control(Change_controlService   service);
	
	void setProyect_loan(ProyectLoan proyect_loan);
	void setPersona(NaturalPerson persona);
	void setSesion(SessionBean sesion);
		
	void init();
	void init_kubo_property       (AjaxBehaviorEvent evento);
	void init_investor_category_id(AjaxBehaviorEvent evento);
	void init_crear_cartera();
	
	boolean isInvestor_category_ENABLED();
	
	ProyectLoan getProyect_loan();
	ChangeBean  getChange_bean();
	
	List<InstitutionalInvestor> getLista_fondeadores();
	List<InvestorCategory> getLista_investor_category();	
}
