package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.repositories.SavingAccountDao;
import mx.com.kubo.services.SavingAccountService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingAccountServiceImp implements SavingAccountService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private SavingAccountDao savingAccountRepository;
	
	@Override
	public SavingAccount getSavingAccountByID(SavingAccountPK savingAccountPk) {
		return savingAccountRepository.getSavingAccountByID(savingAccountPk);
	}

	@Override
	public boolean addSavingAccount(SavingAccount newSavingAccount,int prospectusID, int companyID) {
		return savingAccountRepository.addSavingAccount(newSavingAccount, prospectusID, companyID);
	}
	
	@Override
	public SavingAccount getSavingAccountByAccount(String savingAccountStr){
		return savingAccountRepository.getSavingAccountByAccount( savingAccountStr );
	}

	@Override
	public boolean updateSavingAccount(SavingAccount savingAccount) {
		return savingAccountRepository.updateSavingAccount(savingAccount);
	}

	@Override
	public List<SavingAccount> getListAccountByProspect(int prospectID,int companyID) 
	{
		return savingAccountRepository.getListAccountByProspect(prospectID, companyID);
	}
	
	@Override
	public  SavingAccount getSavingAccountByProspectus(int prospectusID,int companyID){
		return savingAccountRepository.getSavingAccountByProspectus(prospectusID, companyID);
	}

	@Override
	public List<SavingAccount> getListAccountActiveByProspect(int prospectID, int companyID) 
	{
		return savingAccountRepository.getListAccountActiveByProspect(prospectID, companyID);
	}
	
	@Override
	public boolean verifyAccount(SavingAccount savingAccount){
		return savingAccountRepository.verifyAccount( savingAccount );
	}
	
	@Override
	public boolean executeSP_Relaciona_Menor(String safi_client_menor, String safi_client_tutor){
		return savingAccountRepository.executeSP_Relaciona_Menor( safi_client_menor,  safi_client_tutor);
	}
	
}
