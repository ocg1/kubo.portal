package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.SimulationBase;

public interface SimulationBaseDao {

	public List<SimulationBase> getSimulationBaseListByElements( List<String> riskArray, List<String> genderArray, List<String> purposeArray, List<String> termArray  );
	
}
