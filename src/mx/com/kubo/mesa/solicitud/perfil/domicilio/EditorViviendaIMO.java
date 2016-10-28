package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Residence;

public interface EditorViviendaIMO 
{
	ChangeBean getChange_control_bitacora();	
	Address    getAddress();
	
	String getVivienda_TOKEN();
	String getCodigo_postal();
	String getMap_ubication();
	String getDelegacion_municipio();
	String getEstado();
	String getMotivo_del_cambio();
	String getColonia_name();
	String getColonia_Text();
	String getColonia_ENABLED();
	String getOtra_colonia_ENABLED();
	String getAlarma_NIVEL_1();
	String getAlarma_NIVEL_2();
	String getAntiguedad();
	String getPerson_type_title();
	
	Integer getColonia_id();
	Integer getTipo_vivienda_id();
	
	Long getCoincidencias_NIVEL_1();
	Long getCoincidencias_NIVEL_2();
	
	List<NeighborhoodCat> getColonias_por_codigo_postal();
	List<Residence>       getLista_tipo_vivienda();
	
	boolean eliminar();
	boolean isAddress_ENABLED();
	boolean isFiscal_ENABLED();
	
	void setSesion     (SessionBean  sesion);
	void setPerson(NaturalPerson person);
	
	void setVivienda_TOKEN_COMPARABLE(String vivienda_TOKEN);
	
	void setAddress_type   (Integer address_type);
	void setBeneficiarie_id(Integer beneficiarie_id);	
	void setProspectus_id(int prospectus_id);
	
	void setAuto_save_ENABLED(boolean enabled);
	void setBitacora_ENABLED (boolean enabled);
	
	void listener_codigo_postal          (AjaxBehaviorEvent evento);
	void listener_mapa_ubicacion         (AjaxBehaviorEvent evento);
	void listener_colonia_SELECTED       (AjaxBehaviorEvent evento);	
	void listener_tipo_vivienda_SELECTED (AjaxBehaviorEvent evento);
	void listener_motivo_del_cambio      (AjaxBehaviorEvent evento);
	void listener_generar_vivienda_TOKEN (AjaxBehaviorEvent evento);
	
	void listener_guardar_edicion();
	void listener_lista_coincidencias();
	void listener_lista_coincidencias_NIVEL_2();
	
	void asignar_mismo_domicilio();	
	
	void init();
}
