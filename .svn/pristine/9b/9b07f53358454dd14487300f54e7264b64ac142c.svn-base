package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.util.List;

import org.primefaces.event.SelectEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ServiceSearchRequestIMO;

public interface SearchEngineIMO 
{
	void setService_search_request(ServiceSearchRequestIMO service);
	void setService_membership          (MembershipService service);
	void setService_proyect_loan       (ProyectLoanService service);
	
	void setSesion(SessionBean sesion);
	void setSummary_request(SummaryRequest summary_request);
	void setSesion_search_request(SearchSummaySession sesion_search_request);
	
	List<UserViewFullNameForCoach> init_search(String search_TOKEN);
	
	void init_membership(SelectEvent event);
	
	void init_summary_request();
}
