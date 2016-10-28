package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.SimulationBase;

public interface SimulationBaseService {

	public List<SimulationBase> getSimulationBaseListByElements( List<String> riskArray , List<String> genderArray, List<String> purposeArray, List<String> termArray  );
	
}
