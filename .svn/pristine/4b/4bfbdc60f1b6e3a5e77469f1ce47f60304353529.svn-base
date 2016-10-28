package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.IncomeHistory;
import mx.com.kubo.repositories.IncomeHistoryDao;
import mx.com.kubo.services.IncomeHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeHistoryServiceImp implements IncomeHistoryService {
	
	@Autowired
	private IncomeHistoryDao repository;
	
	@Override
	public List<IncomeHistory> getIncomeHistoryByProyectLoan(int proyect_loan_id){
		return repository.getIncomeHistoryByProyectLoan(proyect_loan_id);
	}
	
	@Override
	public List<IncomeHistory> getIncomeHistoryListByProspectus( int prospectus_id, int company_id ){
		return repository.getIncomeHistoryListByProspectus(prospectus_id, company_id);
	}
	
	@Override
    public IncomeHistory getLastIncomeHistoryByProspectus( int prospectus_id, int company_id ){
		return repository.getLastIncomeHistoryByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public boolean saveIncomeHistory( IncomeHistory newIncomeHistory ){
		return repository.saveIncomeHistory(newIncomeHistory);
	}
	
	@Override
	public boolean deleteIncomeHistory( IncomeHistory newIncomeHistory ){
		return repository.deleteIncomeHistory(newIncomeHistory);
	}
	
	
}
