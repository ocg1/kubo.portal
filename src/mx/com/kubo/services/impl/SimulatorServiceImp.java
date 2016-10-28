package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.repositories.SimulatorDao;
import mx.com.kubo.services.SimulatorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimulatorServiceImp implements SimulatorService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private SimulatorDao simulatorRepository;

	@Override
	public SimulatorBean getSimulatorById(SimulatorPK pk) {
		return simulatorRepository.loadSelectedSimulator(pk);
	}

	@Transactional
	@Override
	public void add(SimulatorBean newSimulator) {
		log.info("add.SimulatorServiceImp");
		simulatorRepository.saveSimulator(newSimulator);		
	}
	
	@Override
	public List<SimulatorBean> getSimulatorList() {
		return simulatorRepository.loadSimulatorList();
	}
	
	@Override
	public SimulatorBean getMaxSimulationProspect(Integer prospectus_id, Integer company_id){
		return simulatorRepository.getMaxSimulationProspect(prospectus_id, company_id);
	}
	
	@Override
	public SimulatorBean getMaxSimulationProspectWithPurpose(Integer prospectus_id, Integer company_id){
		return simulatorRepository.getMaxSimulationProspectWithPurpose(prospectus_id, company_id);
	}
	
	@Override
	public Double getCatBySafi(Double monto,String valorCuotas, Integer diasFrec){
		return simulatorRepository.getCatBySafi( monto, valorCuotas,  diasFrec);
		
	}

}
