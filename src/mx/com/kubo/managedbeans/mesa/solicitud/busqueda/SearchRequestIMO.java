package mx.com.kubo.managedbeans.mesa.solicitud.busqueda;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

public interface SearchRequestIMO 
{	
	void setRadioTypeSearch(int filtro_SELECTED);
	void setFiltro_area_SELECTED(Integer filtro_SELECTED);
	void setSearch(String search);	
	
	void listener_buscar_solicitud(ActionEvent e);	
	void listener_filtro_area(AjaxBehaviorEvent evento);
	
	SearchEngineIMO getCoach_engine(); 
	
	String getSearch();
	
	Integer getFiltro_area_SELECTED();
	
	int getRadioTypeSearch();
	
	boolean isDisplayUser();
	boolean isFiltro_area_ENABLED();	
	
	List<ClientViewFullName> completeinfoclient(String strSearch);
}
