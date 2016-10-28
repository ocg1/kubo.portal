package mx.com.kubo.services.mesa.solicitud.notas;

import java.util.List;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.PriorityType;

import org.springframework.stereotype.Service;

@Service("notesServiceImp")
public class NotesServiceImp extends NotesServiceDMO
implements NotesService 
{	
	@Override
	public Notes getNoteById(NotesPK pk) 
	{
		return dao_notes.getNoteById(pk);
	}

	@Override
	public boolean addNote(Notes newNote) 
	{
		return dao_notes.addNote(newNote);
	}

	@Override
	public boolean updateNote(Notes note) 
	{
		is_nota_OK = false;
		
		if(note != null)
		{
			is_nota_OK = dao_notes.updateNote(note);
		}
		
		return is_nota_OK;
	}

	@Override
	public boolean removeNote(NotesPK pk) {
		return dao_notes.removeNote(pk);
	}

	@Override
	public List<Notes> getListAllNotes() {
		return dao_notes.getListAllNotes();
	}

	@Override
	public List<Notes> getListNotesByProspect(int prospectus_id, int company_id) {
		return dao_notes.getListNotesByProspect(prospectus_id, company_id);
	}

	@Override
	public List<NoteType> getListNoteTypes() {
		return dao_notes.getListNoteTypes();
	}

	@Override
	public List<PriorityType> getListPrioriType() {
		return dao_notes.getListPrioriType();
	}
	
	public List<Notes> getLista_notas_del_coach(int prospectus_id, int company_id)
	{
		return getListNotesByProspectByType(prospectus_id, company_id, NOTA_DEL_COACH, true);
	}
	
	public List<Notes> getListNotesByProspectByType(int prospectus_id, int company_id,int type, boolean fecDesc )
	{
		return dao_notes.getListNotesByProspectByType(  prospectus_id, company_id, type, fecDesc );
	}
	
	public List<Notes>  getListaNotasPrioridadMediaBaja(int prospectus_id, int company_id, int proyect_id,boolean fecDesc)
	{
		return dao_notes.getListaNotasPrioridadMediaBaja(prospectus_id, company_id, proyect_id, fecDesc);
	}
	
	@Override
	public List<Notes>  getListaNotasPrioridadAlta(int prospectus_id, int company_id, int proyect_id, boolean fecDesc)
	{
		return dao_notes.getListaNotasPrioridadAlta(prospectus_id, company_id, proyect_id, fecDesc);
	}
	
	public List<Notes> getListaNotasRechazo(ProyectLoan proyecto)
	{
		return dao_notes.getListaNotasRechazo(proyecto);
	}
	
	public Notes getLastNoteByProyect( int prospectus_id, int company_id, int proyect_id )
	{
		return dao_notes.getLastNoteByProyect( prospectus_id, company_id, proyect_id );
	}
	
	public List<Notes> getLista_notas_por_tipo(int note_type_id, ProyectLoan proyecto)
	{
		return dao_notes.getLista_notas_por_tipo(note_type_id, proyecto);
	}
	
	public final Notes getUltima_nota(ProyectLoan proyecto)
	{
		return dao_notes.getUltima_nota(proyecto);
	}
	
	public final int getTipo_ultima_nota(ProyectLoan proyecto)
	{
		ultima_nota = dao_notes.getUltima_nota(proyecto);
			
		note_type_id = ultima_nota.getNoteType().getNoteTypePk().getNote_type_id();
		
		return note_type_id;
	}
}
