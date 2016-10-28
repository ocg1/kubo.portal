package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;


public interface TownDao 
{
	TownCat loadSelectedTown(TownCatPK pk);
	
	void saveTown(TownCat newTown);
	
	List<TownCat> loadTownList();
	List<TownCat> loadTownList(int state_id);
}
