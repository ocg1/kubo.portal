package mx.com.kubo.registro.datos.domicilio;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Residence;
import mx.com.kubo.registro.datos.DatosPersonalesIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ResidenceService;

public interface DomicilioIMO extends DatosPersonalesIMO
{
	void setService_address       (AddressService service);
	void setService_employment (EmploymentService service);
	void setService_residence   (ResidenceService service);
	void setService_prospectus (ProspectusService service);
	
	void setAddress_type_id(int address_type_id);
	
	void setResidence_ENABLED        (boolean residence_ENABLED);
	void setNeighborhood_text_ENABLED(boolean neighborhood_text_ENABLED);
	
	void init();
	void init_zipcode          (AjaxBehaviorEvent event);	
	void init_neighborhood_id  (AjaxBehaviorEvent event);	
	void init_neighborhood_text(AjaxBehaviorEvent event);
	void init_street           (AjaxBehaviorEvent event);
	void init_address_number   (AjaxBehaviorEvent event);
	void init_apt_number       (AjaxBehaviorEvent event);
	void init_mx_manzana       (AjaxBehaviorEvent event);
	void init_mx_lote          (AjaxBehaviorEvent event);
	void init_residence_id     (AjaxBehaviorEvent event);
	void init_first_reference  (AjaxBehaviorEvent event);
	void init_second_reference (AjaxBehaviorEvent event);
	void init_description      (AjaxBehaviorEvent event);
	
	void init_reset();
	void init_copy(DomicilioIMO to_copy);	
	
	void setSplitLatLong();
	
	List<NeighborhoodCat> getColonias_por_codigo_postal();
	List<Residence> getResidencelist();
	
	Address getAddress();
	
	String getZipcode();
	String getLatLong();
	String getDelegMun();
	String getEstado();
	
	Integer getNeighborhood_id();
	
	boolean isResidence_ENABLED();	
	boolean isCoverage_zone_ENABLED();
	boolean isNeighborhood_text_ENABLED();
	boolean isSave_address_OK();
}
