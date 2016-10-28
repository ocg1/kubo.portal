package mx.com.kubo.repositories.mesa.solicitud.notas;

import java.util.List;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.PriorityType;

public interface NotesDao 
{
	Notes getNoteById(NotesPK pk);
	
	boolean addNote(Notes newNote);
	boolean updateNote(Notes note);
	boolean removeNote(NotesPK pk);
	
	Notes getLastNoteByProyect( int prospectus_id, int company_id, int proyect_id );
	List<Notes> getListaNotasRechazo(ProyectLoan proyecto);
	List<Notes> getListAllNotes();
	List<Notes> getListNotesByProspect(int prospectus_id,int company_id);
	List<NoteType> getListNoteTypes();
	List<PriorityType> getListPrioriType();
	List<Notes> getListNotesByProspectByType   (int prospectus_id, int company_id, int type,       boolean fecDesc );
	List<Notes> getListaNotasPrioridadAlta     (int prospectus_id, int company_id, int proyect_id, boolean fecDesc);
	List<Notes> getListaNotasPrioridadMediaBaja(int prospectus_id, int company_id, int proyect_id, boolean fecDesc);
	List<Notes> getLista_notas_por_tipo(int note_type_id, ProyectLoan proyecto);
	
	Notes getUltima_nota(ProyectLoan proyecto);
}
