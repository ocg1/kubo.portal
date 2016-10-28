package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;


public interface StateDao {

	public StateCat loadSelectedState(StateCatPK pk);
	public void saveState(StateCat newState);
	public List<StateCat> loadStateList();
}
