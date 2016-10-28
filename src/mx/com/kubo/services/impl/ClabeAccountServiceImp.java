package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.repositories.ClabeAccountDao;
import mx.com.kubo.services.ClabeAccountService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "clabeAccountServiceImp")
public class ClabeAccountServiceImp 
implements ClabeAccountService, Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ClabeAccountDao clabeAccountRepository;
	
	@Override
	public ClabeAccount loadSelectedClabeAccount(ClabeAccountPK pk){
		return clabeAccountRepository.loadSelectedClabeAccount(pk);
	}
	
	@Override
	public void saveClabeAccount(ClabeAccount newClabeAccount){
		clabeAccountRepository.saveClabeAccount(newClabeAccount);
	}
	
	@Override
	public void updateClabeAccount(ClabeAccount newClabeAccount){
		clabeAccountRepository.updateClabeAccount(newClabeAccount);
	}
	
	@Override
	public void removeClabeAccount(ClabeAccount newClabeAccount){
		clabeAccountRepository.removeClabeAccount(newClabeAccount);
	}
	
	@Override
	public List<ClabeAccount> loadClabeAccountListByProspectus(int prospectus_id, int company_id){
		return clabeAccountRepository.loadClabeAccountListByProspectus(prospectus_id, company_id);
	}

}
