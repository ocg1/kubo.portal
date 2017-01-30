package mx.com.kubo.managedbeans.mesa.solicitud.notas;

import java.util.Date;

import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;
import static mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota.VISITA_DOMICILIARIA;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
 
public final class NotasDelCasoIMP extends NotasDelCasoPMO
implements NotasDelCasoIMO
{			
	public void init()
	{
		faces    = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
        sesion  = (SessionBean) resolver.getValue(context, null, "sessionBean");
        
        change_prospectus_id = sesion.getCoachProspectus_id() != null ? sesion.getCoachProspectus_id() : sesion.getProspectus_id();
       
        lista_tipo_nota   = service_notas.getListNoteTypes();				
		lstNotePrioriType = service_notas.getListPrioriType();				
	}
	
	public final void loadNotes()
	{
		addNote = new Notes();
		
		addNote.setNote_type_id(null);
		addNote.setNote(null);
		
		int company_id    = persona.getNatPerPK().getCompany_id();
		int prospectus_id = persona.getNatPerPK().getProspectus_id();
		
		lstNoteByProspect = service_notas.getListNotesByProspect(prospectus_id, company_id);
	}
	
	public final void addNewNote()
	{
		if(addNote != null && addNote.getNote_type_id() != null && addNote.getNote() != null)
		{
			int company_id    = persona.getNatPerPK().getCompany_id();
			int prospectus_id = persona.getNatPerPK().getProspectus_id();
			
			NotesPK pk = new NotesPK(prospectus_id, company_id);
			
			addNote.setNotesPk(pk);
			addNote.setChange_date(new Date());			
			addNote.setChange_prospectus_id(change_prospectus_id);
			
			if(actualProyect != null)
			{
				addNote.setProyect_id(actualProyect.getProyect().getProyectoPk().getProyect_id());
			}
			
			if(service_notas.addNote(addNote))
			{
				loadNotes();
			}
		}
	}
	
	public final void listener_tipo_nota_SELECTED(AjaxBehaviorEvent evento)
	{		
		ajax_select = (HtmlSelectOneMenu) evento.getComponent();
		tipo_nota_SELECTED = (Integer) ajax_select.getValue();
		
		request = RequestContext.getCurrentInstance();		
		
		if(tipo_nota_SELECTED > 0)
		{												
			procesar_tipo_nota();			
		} 
						
		//System.out.println("NotasDelCasoIMP.listener_tipo_nota_SELECTED(): " + tipo_nota_SELECTED);
	}
	
	public final void listener_tipo_prioridad_SELECTED(AjaxBehaviorEvent evento)
	{
		ajax_select = (HtmlSelectOneMenu) evento.getComponent();
		tipo_prioridad_SELECTED = (Integer) ajax_select.getValue();
		
		//System.out.println("NotasDelCasoIMP.listener_tipo_prioridad_SELECTED(): " + tipo_prioridad_SELECTED);
		
		addNote.setPriority_type_id(tipo_prioridad_SELECTED);
		
		new_priority_type_id = Integer.toString(tipo_prioridad_SELECTED);
	}
	
	public final void listener_motive_id_SELECTED(AjaxBehaviorEvent evento)
	{
		ajax_select = (HtmlSelectOneMenu) evento.getComponent();
		motive_id_SELECTED = (Integer) ajax_select.getValue();
		
		//System.out.println("NotasDelCasoIMP.listener_motive_id_SELECTED(): " + motive_id_SELECTED);
		
		addNote.setMotive_id(motive_id_SELECTED);
	}
	
	public final void listener_observaciones(AjaxBehaviorEvent evento)
	{
		ajax_input = (HtmlInputTextarea) evento.getComponent();
		observaciones = (String) ajax_input.getValue();
		
		//System.out.println("NotasDelCasoIMP.listener_observaciones(): " + observaciones);
		
		addNote.setNote(observaciones);
	}
	
	public final void listener_datos_documentos_OK()
	{
		//System.out.println("NotasDelCasoIMP.listener_datos_documentos_OK(): " + datos_documentos_OK);
	}
	
	public final void listener_domicilio_OK()
	{
		//System.out.println("NotasDelCasoIMP.listener_domicilio_OK(): " + domicilio_OK);
	}
	
	public final void listener_editar_nota_SELECTED(ActionEvent evento)
	{
		Notes nota = (Notes) evento.getComponent().getAttributes().get("nota_SELECTED");
				
		observaciones           = nota.getNote();
		tipo_nota_name          = nota.getNoteType().getName();
		tipo_nota_SELECTED      = nota.getNote_type_id();
		tipo_prioridad_SELECTED = nota.getPriority_type_id();		
		motive_id_SELECTED      = nota.getMotive_id();
		
		if(motive_id_SELECTED == null)
		{
			motive_id_SELECTED = -1;
		}
		
		addNote = nota;
				
		//System.out.println("NotasDelCasoIMP.listener_editar_nota_SELECTED(): " + observaciones);
	}
	
	public final void listener_init_edicion()
	{	
		request = RequestContext.getCurrentInstance();
		
		procesar_tipo_nota();
		
		old_priority_type_id = Integer.toString(tipo_prioridad_SELECTED);
		
		meta_info_JSON = service_notas_del_caso.getMeta_info_JSON(addNote);
		
		if(meta_info_JSON == null)
		{
			meta_info_JSON = "EMPTY";
			
		} else {
			
			datos_documentos_OK = service_notas_del_caso.getDatos_documentos_OK();
			domicilio_OK        = service_notas_del_caso.getDomicilio_OK();
		}
				
		request.addCallbackParam("prioridad_id",   tipo_prioridad_SELECTED);
		request.addCallbackParam("tipo_nota_id",   tipo_nota_SELECTED);	
		request.addCallbackParam("tipo_motivo_id", motive_id_SELECTED);
		request.addCallbackParam("meta_info_JSON", meta_info_JSON);
		
		//System.out.println("NotasDelCasoIMP.listener_editar_nota(): " + meta_info_JSON);
	}
	
	public final void cargar_todas_las_notas()
	{
		request = RequestContext.getCurrentInstance();				
		
		listaNotasPrioridadMediaBaja = service_notas.getListNotesByProspect(prospectus_id, company_id);
		
		request.addCallbackParam("todas_las_notas_ENABLED", "true" );
		
		//System.out.println("NotasDelCasoIMP.cargar_todas_las_notas(): OK");
	}

	public final void cargar_notas_by_proyect()
	{
		request = RequestContext.getCurrentInstance();
		
		listaNotasPrioridadMediaBaja = service_notas.getListaNotasPrioridadMediaBaja(prospectus_id, company_id, proyect_id, true);
		
		request.addCallbackParam("todas_las_notas_ENABLED", "false" );
		
		//System.out.println("NotasDelCasoIMP.cargar_notas_by_proyect(): OK");
	}
		
	public final void agregar_nota_NEW()
	{
		request = RequestContext.getCurrentInstance();
		
		if(addNote != null && addNote.getNote_type_id() != null && addNote.getNote() != null)
		{			
			asignar_nota_NEW();
						
			guardar_nota_OK = service_notas.addNote(addNote);
			
			if(guardar_nota_OK)
			{
				if(tipo_de_nota == VISITA_DOMICILIARIA)
				{				
					service_notas_del_caso.setDatos_documentos_OK(datos_documentos_OK);
					service_notas_del_caso.setDomicilio_OK(domicilio_OK);
					service_notas_del_caso.init_meta_info(addNote);
					
					meta_info_OK = service_notas_del_caso.persist_meta_info();
				}
				
				init_nota();
				init_lista_notas();
				
				if(lastNote != null )
				{					
					if(lastNote.getMotive_id() != null)
					{						
						motive_PK = new MotivePK();
						
						motive_PK.setCompany_id(company_id);
						motive_PK.setMotive_id(lastNote.getMotive_id());
						
						motive = service_motive.getMotiveByPK(motive_PK);		
						
						if(! area.equals("I"))
						{
							asignar_cambio_estatus_TOKEN();	
						}
						
					} else {
						
						cambio_estatus_TOKEN ="";
					}
					
				} else {
					
					cambio_estatus_TOKEN ="";
				}
				
				request.addCallbackParam("values", cambio_estatus_TOKEN);				
			}			
		}
	}
		
	public final void init_nota()
	{			
		addNote = new Notes();
		addNote.setNote_type_id(null);
		addNote.setNote(null);
		
		editNote = new Notes();
		editNote.setNote_type_id(null);
		editNote.setNote(null);		
		
		tipo_nota_SELECTED      = null;
		tipo_prioridad_SELECTED = null;
		motive_id_SELECTED      = null;
		observaciones           = null;
		datos_documentos_OK = false;
		domicilio_OK        = false;
		
		//System.out.println("NotasDelCasoIMP.init_nota(): OK");
	}
	
	public final void init_lista_notas()
	{
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();
		area          = persona.getProspectus().getArea().toString();
		
		if( area.equals("L") ){
			
			lstNoteByProspect            = service_notas.getListNotesByProspect(prospectus_id, company_id);
			listaNotasPrioridadAlta      = service_notas.getListaNotasPrioridadAlta     (prospectus_id, company_id, proyect_id, true);			
			listaNotasPrioridadMediaBaja = service_notas.getListaNotasPrioridadMediaBaja(prospectus_id, company_id, proyect_id, true);			
			lastNote                     = service_notas.getLastNoteByProyect( prospectus_id, company_id, proyect_id);
		
		}else if( area.equals("I") ){
		
			listaNotasPrioridadMediaBaja = service_notas.getListNotesByProspect(prospectus_id, company_id);
			
		}
		
		if(!area.equals("I"))
		{
			asignar_cambio_estatus_TOKEN();
		}		
	}
		
	public final String getMotiveDescription(Integer motive_id)
	{		
		System.out.println("NotasDelCasoIMP.getMotiveDescription(): " + motive_id);
		
		String name = "";
		
		prospecto = persona.getProspectus();
		company_id = prospecto.getProspectusPK().getCompany_id();
		
		motive_PK = new MotivePK();		
		motive_PK.setCompany_id(company_id);
		motive_PK.setMotive_id(motive_id);
		
		motive = service_motive.getMotiveByPK(motive_PK);
		
		if(motive != null)
		{			
			name =  "(" + motive.getDescription() + ")";			
		}
		
		return name;		
	}	
	
	public final String getMetaInfoTOKEN(Notes nota)
	{
		System.out.println("NotasDelCasoIMP.getMetaInfoTOKEN(): " + nota);
		
		meta_info_TOKEN = service_notas_del_caso.getMeta_info_TOKEN(nota);
		
		return meta_info_TOKEN;
	}
	
	public final void editarNotaAltaPrioridad(RowEditEvent evento)
	{
		Notes nota = (Notes) evento.getObject();
		
		old_priority_type_id = "1";
		new_priority_type_id = Integer.toString(nota.getPriority_type_id());
		
		if(nota.getPriority_type_id() > 1)
		{
			if(service_notas.updateNote(nota))
			{
				//System.out.println(transaction_message_edicion());
				
				if(saveChangeData("gn_notes", "priority_type_id", old_priority_type_id, new_priority_type_id, "Modificación de la prioridad en la Nota del Caso"))
				{
					//System.out.println("Se registró la modificación en gn_change_control");
					
					init_lista_notas();			
				}	
			}
		}
	}
		
	public final void editarNotaDelCaso()
	{							
		cambio_de_prioridad_OK = service_notas.updateNote(addNote);
		
		if(cambio_de_prioridad_OK)
		{
			if(tipo_de_nota == VISITA_DOMICILIARIA)
			{				
				service_notas_del_caso.setDatos_documentos_OK(datos_documentos_OK);
				service_notas_del_caso.setDomicilio_OK(domicilio_OK);
				service_notas_del_caso.init_meta_info(addNote);
				
				meta_info_OK = service_notas_del_caso.update_meta_info();
			}
			
			//System.out.println(transaction_message_edicion());
			
			cambio_de_prioridad_OK = saveChangeData("gn_notes", "priority_type_id", old_priority_type_id, new_priority_type_id, "Modificación de la prioridad en la Nota del Caso");
			
			if(cambio_de_prioridad_OK)
			{
				//System.out.println("Se registró la modificación en gn_change_control");
				
				init_nota();
				init_lista_notas();										
			}			
		}						
	}
	
	public final void crear_nueva_nota(TipoDeNota tipo) 
	{	
		prospectus_id = actualProyect.getProyectloanPk().getProspectus_id();
		company_id    = actualProyect.getProyectloanPk().getCompany_id();		
		proyect_id    = actualProyect.getProyect().getProyectoPk().getProyect_id();
		
		note_PK = new NotesPK(prospectus_id, company_id);
		
		addNote.setProyect_id(proyect_id);
		addNote.setNotesPk(note_PK);
		addNote.setChange_date(new Date());
		addNote.setChange_prospectus_id(sesion.getProspectus_id());
		addNote.setPriority_type_id(MEDIA);
		addNote.setMotive_id(motive_selected);
		addNote.setNote(observaciones);
		
		tipo_de_nota = tipo;
		
		addNote.setNote_type_id(tipo_de_nota.getNote_type_id());						
		
		guardar_nota_OK = service_notas.addNote(addNote);
		
		if(guardar_nota_OK)
		{
			init_nota();
			init_lista_notas();
		}
	}
}
