package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;

public interface SimulatorDao {
	
	public SimulatorBean loadSelectedSimulator(SimulatorPK pk);
	public void saveSimulator(SimulatorBean newSimulator);
	public List<SimulatorBean> loadSimulatorList();
	public SimulatorBean getMaxSimulationProspect(Integer prospectus_id, Integer company_id);
	public Double getCatBySafi(Double monto,String valorCuotas, Integer diasFrec);
	public SimulatorBean getMaxSimulationProspectWithPurpose(Integer prospectus_id, Integer company_id);
	
}
