package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Tutor;

public interface TutorDao {

	public boolean addTutor( Tutor tutor );
	
	public List<Tutor> getTutorByProspectus( Integer prospectus_id, Integer company_id );
	
	public List<Tutor> getProspectusFromTutor( Integer prospectus_id, Integer company_id );
	
}
