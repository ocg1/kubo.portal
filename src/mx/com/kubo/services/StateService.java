package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;

public interface StateService 
{
	StateCat getStateById(StateCatPK id);
	
	void add(StateCat newState);
	
	List<StateCat> getStateList();
}
