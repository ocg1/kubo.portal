package mx.com.kubo.managedbeans.registro.datos;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Gender;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.registro.datos.birthdate.FechaNacimientoIMO;
import mx.com.kubo.registro.datos.citizen.NacionalidadIMO;
import mx.com.kubo.registro.datos.country.BirthCountryIMO;
import mx.com.kubo.registro.datos.domicilio.DomicilioIMO;
import mx.com.kubo.registro.datos.moral.PersonaMoralIMO;
import mx.com.kubo.registro.datos.nombre.PersonNameIMO;
import mx.com.kubo.registro.datos.pais.PaisOrigenIMO;
import mx.com.kubo.registro.datos.state.BirthPlaceIMO;
import mx.com.kubo.registro.verificacion.ProspectoDuplicadoIMO;

public interface BasicDataIMO 
{			
	void setService_prospecto_duplicado(ProspectoDuplicadoIMO service);
		
	void saveData();
	
	void init_address_type  (AjaxBehaviorEvent event);
	void init_CURP_generator(AjaxBehaviorEvent event);
	void init_domicilio     (AjaxBehaviorEvent event);
	void init_legal_address (AjaxBehaviorEvent event);
	void init_neighborhood_text_ENABLED(AjaxBehaviorEvent event);
	
	List<Gender> getLista_de_generos();
	
	     PaisOrigenIMO getPais_origen();
	FechaNacimientoIMO getBirthday();
	   NacionalidadIMO getCitizenship();
	   PersonaMoralIMO getMoral(); 
	     BirthPlaceIMO getState();
	      DomicilioIMO getDomicilio();
	      DomicilioIMO getFiscal();
	     PersonNameIMO getName();
       BirthCountryIMO getCountry();
	
	NaturalPerson getNaturalPerson();	
	
	String getIs_legal_address();
	
	boolean isLegal_address_ENABLED();
	boolean isFiscal_ENABLED();
}
