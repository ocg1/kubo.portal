package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.OperationCostHistory;
import mx.com.kubo.repositories.OperationCostHistoryDao;
import mx.com.kubo.services.OperationCostHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationCostHistoryServiceImp  implements OperationCostHistoryService {
	
	@Autowired
	private OperationCostHistoryDao repository;
	
	@Override
	public List<OperationCostHistory> getOperationCostHistoryByProyectLoan(int proyect_loan_id){
		return repository.getOperationCostHistoryByProyectLoan(proyect_loan_id);
	}
	
	@Override
	public List<OperationCostHistory> getOperationCostHistoryListByProspectus( int prospectus_id, int company_id ){
		return repository.getOperationCostHistoryListByProspectus(prospectus_id, company_id);
	}
	
	@Override
    public OperationCostHistory getLastOperationCostHistoryByProspectus( int prospectus_id, int company_id ){
		return repository.getLastOperationCostHistoryByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public boolean saveOperationCostHistory( OperationCostHistory newOperationCostHistory ){
		return repository.saveOperationCostHistory(newOperationCostHistory);
	}
	
	@Override
	public boolean deleteOperationCostHistory( OperationCostHistory newOperationCostHistory ){
		return repository.deleteOperationCostHistory(newOperationCostHistory);
	}
	
}
