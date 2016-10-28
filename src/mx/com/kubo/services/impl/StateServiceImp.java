package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.repositories.StateDao;
import mx.com.kubo.services.StateService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImp implements StateService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private StateDao stateRepository;

	@Override
	public StateCat getStateById(StateCatPK id) {
		return stateRepository.loadSelectedState(id);
	}

	@Override
	public void add(StateCat newStateCat) {
		stateRepository.saveState(newStateCat);		
	}

	@Override
	public List<StateCat> getStateList() {
		return stateRepository.loadStateList();
	}
	
	

}
