package mx.com.kubo.services.impl;

import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.repositories.SimulationCacheDao;
import mx.com.kubo.services.SimulationCacheService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationCacheServiceImp implements SimulationCacheService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private SimulationCacheDao simulationCacheDAO;
	
	
	
	@Override
	public Simulation_Cache getMaxByAmmountRateTermFrequency(Double ammount, Double rate, Integer term, Integer frequency, Integer companyID) {
		return simulationCacheDAO.getMaxByAmmountRateTermFrequency(ammount, rate, term, frequency, companyID);
	}

	@Override
	public boolean add(Simulation_Cache newSimulationCache) {
		return simulationCacheDAO.add(newSimulationCache);
	}

	@Override
	public boolean update(Simulation_Cache newSimulationCache) {
		// TODO Auto-generated method stub
		return simulationCacheDAO.update(newSimulationCache);
	}
	
}
