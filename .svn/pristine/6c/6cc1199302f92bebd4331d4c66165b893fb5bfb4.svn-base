package mx.com.kubo.services.impl;

import mx.com.kubo.model.SimulationConfig;
import mx.com.kubo.repositories.SimulationConfigDao;
import mx.com.kubo.services.SimulationConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SimulationConfigServiceImp implements SimulationConfigService{
	
	@Autowired
	private SimulationConfigDao simulationConfigDao;
	
	@Override
	public SimulationConfig getSimulationByLoanTypeIDandArea(String loanTypeID, String area, int companyID) {
		// TODO Auto-generated method stub
		return simulationConfigDao.getSimulationByLoanTypeIDandArea(loanTypeID, area, companyID);
	}

	@Override
	public SimulationConfig getSimulationByPartnerIDandArea(
			String partnerID, String area, int companyID) {
		// TODO Auto-generated method stub
		return simulationConfigDao.getSimulationByPartnerIDandArea(partnerID, area, companyID);
	}
	
	@Override
	public SimulationConfig getSimulationByLoanTypeIDandPartnerIDandArea(
			String loanTypeID, String partnerID, String area, int companyID) {
		// TODO Auto-generated method stub
		return simulationConfigDao.getSimulationByLoanTypeIDandPartnerIDandArea(loanTypeID, partnerID, area, companyID);
	}

	@Override
	public SimulationConfig getSimulationByArea(String area, int companyID) {
		// TODO Auto-generated method stub
		return simulationConfigDao.getSimulationByArea(area, companyID);
	}

}
