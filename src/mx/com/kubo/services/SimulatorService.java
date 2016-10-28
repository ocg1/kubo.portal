package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;

public interface SimulatorService {
	
	public abstract SimulatorBean getSimulatorById(SimulatorPK pk);
	public abstract void add(SimulatorBean newSimulator);
	public abstract List<SimulatorBean> getSimulatorList();
	public SimulatorBean getMaxSimulationProspect(Integer prospectus_id, Integer company_id);
	public SimulatorBean getMaxSimulationProspectWithPurpose(Integer prospectus_id, Integer company_id);
	public Double getCatBySafi(Double monto,String valorCuotas, Integer diasFrec);
	
	
}
