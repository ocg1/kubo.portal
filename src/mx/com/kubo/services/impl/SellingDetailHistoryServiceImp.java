package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.SellingDetailHistory;
import mx.com.kubo.repositories.SellingDetailHistoryDao;
import mx.com.kubo.services.SellingDetailHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellingDetailHistoryServiceImp implements SellingDetailHistoryService {
	
	@Autowired
	private SellingDetailHistoryDao repository;
	
	@Override
	public List<SellingDetailHistory> getSellingDetailHistoryByProyectLoan(int proyect_loan_id){
		return repository.getSellingDetailHistoryByProyectLoan(proyect_loan_id);
	}
	
	@Override
	public List<SellingDetailHistory> getSellingDetailHistoryListByProspectus( int prospectus_id, int company_id ){
		return repository.getSellingDetailHistoryListByProspectus(prospectus_id, company_id);
	}
	
	@Override
    public SellingDetailHistory getLastSellingDetailHistoryByProspectus( int prospectus_id, int company_id ){
		return repository.getLastSellingDetailHistoryByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public boolean saveSellingDetailHistory( SellingDetailHistory newSellingDetailHistory ){
		return repository.saveSellingDetailHistory(newSellingDetailHistory);
	}
	
	@Override
	public boolean deleteSellingDetailHistory( SellingDetailHistory newSellingDetailHistory ){
		return repository.deleteSellingDetailHistory(newSellingDetailHistory);
	}
	
}
