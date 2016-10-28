package mx.com.kubo.repositories.mesa.solicitud.notas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.PriorityType;

import org.apache.log4j.Logger;

public abstract class DAONotasDelCasoDMO 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;

	protected Query query;
	
	protected Notes nota;
	protected NotesMetaInfo meta_info;
	
	protected List<Notes> lista_notas;
	
	protected TypedQuery<Notes> tq;
	protected TypedQuery<NotesMetaInfo> tq_meta;	
	
	protected String query_TOKEN;
	
	protected Integer note_id;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_id;
	
	protected boolean remove_OK;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

	public Notes getNoteById(NotesPK pk) 
	{
		return em.find(Notes.class,pk);
	}
	
	public List<Notes> getListAllNotes() 
	{
		return em.createQuery("from Notes", Notes.class).getResultList();
	}
	
	public List<NoteType> getListNoteTypes() 
	{
		return em.createQuery("from NoteType", NoteType.class).getResultList();
	}
	
	public List<PriorityType> getListPrioriType() 
	{
		return em.createQuery("from PriorityType", PriorityType.class).getResultList();
	}
}
