package mx.com.kubo.repositories;

import mx.com.kubo.model.SimulationConfig;


public interface SimulationConfigDao {
	public abstract SimulationConfig getSimulationByLoanTypeIDandArea(String loanTypeID, String area, int companyID);
	public abstract SimulationConfig getSimulationByPartnerIDandArea(String partnerID, String area, int companyID);
	public abstract SimulationConfig getSimulationByLoanTypeIDandPartnerIDandArea(String loanTypeID, String partnerID, String area, int companyID);
	public abstract SimulationConfig getSimulationByArea(String area, int companyID);
}
