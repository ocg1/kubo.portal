package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Motive;
import mx.com.kubo.model.MotivePK;

public interface MotiveService {

	public List<Motive> getMotiveStatusListByStatus(Integer status_id);
	public List<Motive> getMotiveStatusListByNoteType(Integer note_type_id);
	public Motive getMotiveByPK( MotivePK pk );
	
}
