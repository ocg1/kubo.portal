package mx.com.kubo.managedbeans.mesa.solicitud.notas;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.PriorityType;
import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.services.mesa.solicitud.notas.ServiceNotasDelCasoIMO;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

public abstract class NotasDelCasoDMO 
implements NotasDelCasoIMO
{
	protected NotesService           service_notas;
	protected ServiceNotasDelCasoIMO service_notas_del_caso;
	protected MotiveService          service_motive;
	protected Change_controlService  service_change_control;	
		
	protected RequestContext request;
	protected FacesContext   faces;
	protected ELContext      context;
	protected ELResolver     resolver;
	
	protected HtmlSelectOneMenu  ajax_select;
	protected HtmlInputTextarea  ajax_input;
	
	protected SessionBean         sesion;
	protected SearchSummaySession sumary;
	
	protected NaturalPerson persona;
	protected Prospectus    prospecto;
	protected ProyectLoan   actualProyect;
		
	protected CasosPospuestosIMO  casosPospuestos;
	protected TipoDeNota          tipo_de_nota;
	
	protected Notes addNote;
	protected Notes editNote;
	protected Notes lastNote;
	protected NotesPK note_PK;
	
	protected Motive   motive;
	protected MotivePK motive_PK;
	
	protected NotesMetaInfo meta_info;
	
	protected List <PriorityType> lstNotePrioriType;
	protected List <NoteType>     lista_tipo_nota;
	protected List <Notes>  lstNoteByProspect;
	protected List <Notes>  listaNotasPrioridadAlta;
	protected List <Notes>  listaNotasPrioridadMediaBaja;
	protected List <Motive> lista_motivos;
	
	protected SimpleDateFormat formatter_date;
	
	protected StringBuilder sb;
	
	protected String cadenaProyecto;
	protected String old_priority_type_id;
	protected String new_priority_type_id; 
	protected String area;
	protected String ipAddressClient;
	protected String descripcion_del_cambio;
	protected String observaciones;
	protected String cambio_estatus_TOKEN;
	protected String meta_info_TOKEN;
	protected String meta_info_JSON;
	protected String tipo_nota_name;
	
	protected Integer tipo_nota_SELECTED;
	protected Integer motive_selected;
	protected Integer motive_id_SELECTED;
	protected Integer tipo_prioridad_SELECTED;
	
	protected int prospectus_id;
	protected int change_prospectus_id;
	protected int company_id;
	protected int proyect_id;	
	protected int motive_id;
	
	protected final int MEDIA = 2;
	
	protected boolean cambio_de_prioridad_OK;
	protected boolean guardar_nota_OK;
	protected boolean datos_documentos_OK;
	protected boolean domicilio_OK;
	protected boolean meta_info_OK;
	protected boolean meta_info_ENABLED;
	protected boolean visita_domiciliaria_ENABLED;
	
	protected NotasDelCasoDMO()
	{
		service_notas          = Utilities.findBean("notesServiceImp");
		service_notas_del_caso = Utilities.findBean("service_notas_del_caso");
		service_motive         = Utilities.findBean("motiveServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
	}
		
	public final void setService_notas(NotesService service) 
	{
		service_notas = service;
	}

	public final void setService_notas_del_caso(ServiceNotasDelCasoIMO service) 
	{
		service_notas_del_caso = service;
	}

	public final void setService_motive(MotiveService service) 
	{
		service_motive = service;
	}

	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}

	public final void setTipo_nota_SELECTED(Integer id_SELECTED) 
	{
		tipo_nota_SELECTED = id_SELECTED;
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan) 
	{
		actualProyect = proyect_loan;
	}
	
	public final void setProyect_id(int proyect_id) 
	{
		this.proyect_id = proyect_id;
	}
	
	public final void setPersona(NaturalPerson persona) 
	{
		this.persona = persona;
	}
	
	public final void setCasosPospuestos(CasosPospuestosIMO casosPospuestos) 
	{
		this.casosPospuestos = casosPospuestos;
	}
	
	public final void setMotive_selected(Integer motive_selected) 
	{
		this.motive_selected = motive_selected;
	}

	public final void setMotive_id_SELECTED(Integer id_SELECTED) 
	{
		motive_id_SELECTED = id_SELECTED;
	}
	
	public final void setDescripcion_del_cambio(String descripcion)
	{
		descripcion_del_cambio = descripcion;
	}
	
	public final void setObservaciones(String observaciones) 
	{
		this.observaciones = observaciones;
	}
	
	public final void setTipo_prioridad_SELECTED(Integer tipo_prioridad_SELECTED) 
	{
		this.tipo_prioridad_SELECTED = tipo_prioridad_SELECTED;
	}
	
	public final void setDatos_documentos_OK(boolean bandera) 
	{
		datos_documentos_OK = bandera;
	}

	public final void setDomicilio_OK(boolean bandera) 
	{
		domicilio_OK = bandera;
	}
	
	public final void setMeta_info_ENABLED(boolean bandera) 
	{
		meta_info_ENABLED = bandera;
	}
	
	public final boolean isVisita_domiciliaria_ENABLED() 
	{
		return visita_domiciliaria_ENABLED;
	}

	public final boolean isMeta_info_ENABLED() 
	{
		return meta_info_ENABLED;
	}

	public final boolean isDatos_documentos_OK() 
	{
		return datos_documentos_OK;
	}

	public final boolean isDomicilio_OK() 
	{
		return domicilio_OK;
	}
	
	public final Integer getTipo_prioridad_SELECTED() 
	{
		return tipo_prioridad_SELECTED;
	}

	public final Integer getMotive_id_SELECTED() 
	{
		return motive_id_SELECTED;
	}
	
	public final Integer getTipo_nota_SELECTED() 
	{
		return tipo_nota_SELECTED;
	}
	
	public final String getTipo_nota_name() 
	{
		return tipo_nota_name;
	}

	public final String getObservaciones() 
	{
		return observaciones;
	}

	public final String getCambio_estatus_TOKEN() 
	{
		return cambio_estatus_TOKEN;
	}

	public final List<Notes> getListaNotasPrioridadAlta() 
	{
		return listaNotasPrioridadAlta;
	}
	
	public final List<Notes> getListaNotasPrioridadMediaBaja() 
	{
		return listaNotasPrioridadMediaBaja;
	}
	
	public final List<Motive> getLista_motivos() 
	{
		return lista_motivos;
	}
	
	public final List<NoteType> getLista_tipo_nota() 
	{
		return lista_tipo_nota;
	}

	public List<PriorityType> getLstNotePrioriType() 
	{
		return lstNotePrioriType;
	}

	public void setLstNotePrioriType(List<PriorityType> lstNotePrioriType) 
	{
		this.lstNotePrioriType = lstNotePrioriType;
	}

	public List<Notes> getLstNoteByProspect() {
		return lstNoteByProspect;
	}

	public void setLstNoteByProspect(List<Notes> lstNoteByProspect) {
		this.lstNoteByProspect = lstNoteByProspect;
	}

	public Notes getAddNote() {
		return addNote;
	}

	public void setAddNote(Notes addNote) {
		this.addNote = addNote;
	}

}
