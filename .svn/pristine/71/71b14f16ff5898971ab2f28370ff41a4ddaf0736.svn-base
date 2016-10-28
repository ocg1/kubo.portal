package mx.com.kubo.mesa.solicitud.perfil;

import java.util.List;

import mx.com.kubo.bean.EconActivityBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ViewEconomicInfo;

public interface ActividadEconomicaIMO 
{
	void setSesion(SessionBean sesion);
	void setPerson(NaturalPerson person);
	
	void setNameVisible(boolean bandera);
	
	void init();
	
	void save(EconActivityBean actividad_economica);
	
	void setLista_business  (List<Business>   lista_business);
	void setLista_employment(List<Employment> lista_employment);
	
	List<ViewEconomicInfo> listener_autocomplete(String query);		
}
