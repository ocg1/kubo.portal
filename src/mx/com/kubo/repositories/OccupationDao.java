package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Occupation;
import mx.com.kubo.model.OccupationPK;

public interface OccupationDao {
	
	public Occupation loadSelectedOccupation(OccupationPK pk);
	public void saveOccupation(Occupation newOccupation);
	public List<Occupation> loadOccupationList();
	
}
