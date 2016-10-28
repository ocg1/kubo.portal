package mx.com.kubo.repositories.mesa.solicitud.notas;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;

public interface DAONotesMetaInfoIMO 
{
	NotesMetaInfo getMeta_info(Notes nota);
	
	boolean persist(NotesMetaInfo note_meta_info);
	boolean update (NotesMetaInfo note_meta_info);
}
