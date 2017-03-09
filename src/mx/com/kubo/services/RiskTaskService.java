package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.RiskTask;

public interface RiskTaskService {

	public List<RiskTask> getRiskTaskByBurSolNum( String bursolnum );
	public boolean saveRiskTask( RiskTask risktask );
	public RiskTask getRiskTaskByBurSolNumTaskId( String bursolnum, int task_id  );
	
}
