package mx.com.kubo.mesa.autenticacion.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

public interface BuscadorIMO 
{
	List<ClientViewFullName> init_auto_complete(String search_TOKEN);
	
	void setAuto_complete_TOKEN(String auto_complete_TOKEN);
	void setCompany_id(Integer company_id);
	void setSesion_search(SearchSummaySession sesion_search);
	
	void listener_filtro_area(AjaxBehaviorEvent evento);
	void init_busqueda_prospecto(ActionEvent evento);	
	void init_prospectus();
	
	Membership getMembership();
	
	boolean isProspectus_OK();
}
