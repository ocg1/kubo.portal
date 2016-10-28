package mx.com.kubo.managedbeans.mesa.solicitud.notas;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.services.mesa.solicitud.notas.ServiceNotasDelCasoIMO;

import org.primefaces.event.RowEditEvent;

public interface NotasDelCasoIMO 
{	
	void setCasosPospuestos(CasosPospuestosIMO casosPospuestos);
	void setPersona(NaturalPerson persona);
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setTipo_nota_SELECTED (Integer id_SELECTED);
	void setMotive_selected    (Integer motive_selected);
	
	void setProyect_id(int proyect_id);
	
	void setObservaciones(String observaciones);
	
	void setService_change_control (Change_controlService  service);
	void setService_motive         (MotiveService          service);
	void setService_notas_del_caso (ServiceNotasDelCasoIMO service);
	void setService_notas          (NotesService           service);
	
	void cargar_notas_by_proyect();
	void cargar_todas_las_notas();
	
	void init();
	void init_nota();
	void init_lista_notas();
	
	void loadNotes();
	void addNewNote();
	void agregar_nota_NEW();
	void crear_nueva_nota(TipoDeNota tipo);
	void editarNotaAltaPrioridad(RowEditEvent event);
	void editarNotaDelCaso();
	
	void listener_tipo_nota_SELECTED      (AjaxBehaviorEvent evento);
	void listener_tipo_prioridad_SELECTED (AjaxBehaviorEvent evento);
	void listener_motive_id_SELECTED      (AjaxBehaviorEvent evento);
	void listener_observaciones           (AjaxBehaviorEvent evento);
	void listener_editar_nota_SELECTED    (ActionEvent       evento);
	void listener_init_edicion();
	void listener_datos_documentos_OK();
	void listener_domicilio_OK();	
	
	String getMotiveDescription(Integer motive_id);
	String getMetaInfoTOKEN(Notes nota);
	
	Integer getTipo_nota_SELECTED();
	
	boolean isDatos_documentos_OK();
	boolean isDomicilio_OK();
	boolean isMeta_info_ENABLED();
	boolean isVisita_domiciliaria_ENABLED();
	
	List<Notes>    getListaNotasPrioridadAlta();
	List<Notes>    getListaNotasPrioridadMediaBaja();
	List<NoteType> getLista_tipo_nota();
	List<Motive>   getLista_motivos();
}
