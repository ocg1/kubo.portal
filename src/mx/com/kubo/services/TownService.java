package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;

public interface TownService 
{
	TownCat getTownById(TownCatPK id);
	
	void add(TownCat newTown);
	
	List<TownCat> getTownList();
	List<TownCat> loadTownList(int state_id);
}
