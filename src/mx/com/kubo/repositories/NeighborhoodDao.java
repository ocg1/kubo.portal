package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;


public interface NeighborhoodDao {

	public NeighborhoodCat loadSelectedNeighborhood(NeighborhoodCatPK pk);
	public void saveNeighborhood(NeighborhoodCat newMaritalStatus);
	public List<NeighborhoodCat> loadNeighborhoodList();
}
