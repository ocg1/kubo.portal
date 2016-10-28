package mx.com.kubo.repositories.mesa.solicitud.notas;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;

import org.springframework.transaction.annotation.Transactional;

public abstract class DAONotasDelCasoAMO extends DAONotasDelCasoDMO
implements NotesDao
{
	@Transactional
	public boolean addNote(Notes newNote) 
	{
		query_TOKEN = "select MAX(n.notesPk.note_id) from Notes n "
			        + " where n.notesPk.prospectus_id = ? "
			        + " and   n.notesPk.company_id    = ? ";
		
		note_id = 0;
		
		if( newNote != null && newNote.getNotesPk() != null ){
		
			query =  em.createQuery(query_TOKEN);
			query.setParameter(1, newNote.getNotesPk().getProspectus_id());
			query.setParameter(2, newNote.getNotesPk().getCompany_id());
					
			note_id = (Integer) query.getSingleResult();
			
			if(note_id == null)
			{			
				note_id = 1;
				
			} else {
				
				note_id++;
			}		
			
			newNote.getNotesPk().setNote_id(note_id);
			
			try 
			{
				em.persist(newNote);
				
				return true;
				
			} catch (Exception e) {
				
				return false;
			}
			
		}else{
			return true;
		}
	}
	
	@Transactional
	public boolean updateNote(Notes note) 
	{
		try
		{
			em.merge(note);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	public boolean removeNote(NotesPK pk) 
	{
		remove_OK = false;
		
		 try
		 {
			nota = em.find(Notes.class, pk);
			
			if(nota != null)
			{
				em.remove(nota); 
			}
			    
			remove_OK = true;
			
		 } catch (Exception e) {
			 
			 e.printStackTrace();
			 
			 remove_OK = false;
			 
		} finally {
			
			em.close();
		}
		 
		return remove_OK;
	}
}
