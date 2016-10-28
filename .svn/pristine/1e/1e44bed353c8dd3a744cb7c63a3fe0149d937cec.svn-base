package mx.com.kubo.services.mesa.solicitud.notas;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.repositories.mesa.solicitud.notas.NotesDao;

public abstract class NotesServiceDMO 
{    
    protected Notes ultima_nota;
    
    protected int note_type_id;
    
    protected final int NOTA_DEL_COACH = 13;
	
	protected boolean is_nota_OK;
	
	@Autowired
	protected NotesDao dao_notes;
}
