package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.SimulationBase;
import mx.com.kubo.repositories.SimulationBaseDao;
import mx.com.kubo.services.SimulationBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationBaseServiceImp implements Serializable,SimulationBaseService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SimulationBaseDao basedao;
	
	@Override
	public List<SimulationBase> getSimulationBaseListByElements( List<String> riskArray, List<String> genderArray, List<String> purposeArray, List<String> termArray ){
		return basedao.getSimulationBaseListByElements( riskArray ,genderArray, purposeArray, termArray );
	}
	
}
