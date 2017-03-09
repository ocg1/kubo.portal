package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.RiskTask;
import mx.com.kubo.repositories.RiskTaskDao;
import mx.com.kubo.services.RiskTaskService;


@Service
public class RiskTaskServiceImp implements RiskTaskService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RiskTaskDao dao;

	public List<RiskTask> getRiskTaskByBurSolNum( String bursolnum ){
		return dao.getRiskTaskByBurSolNum( bursolnum );
	}
	
	public boolean saveRiskTask( RiskTask risktask ){
		return dao.saveRiskTask(  risktask );
	}
	
	public RiskTask getRiskTaskByBurSolNumTaskId( String bursolnum, int task_id  ){
		return dao.getRiskTaskByBurSolNumTaskId(  bursolnum,  task_id  );
	}
	
}
