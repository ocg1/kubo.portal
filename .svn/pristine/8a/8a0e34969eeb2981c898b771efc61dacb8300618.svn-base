package mx.com.kubo.services.impl;

import mx.com.kubo.model.AccountType;
import mx.com.kubo.model.AccountTypePK;
import mx.com.kubo.repositories.AccountTypeDao;
import mx.com.kubo.services.AccountTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeServiceImp implements AccountTypeService{

	@Autowired
	AccountTypeDao accountTypeDao;
	
	@Override
	public AccountType getAccountTypeByID(AccountTypePK accountTypePK) {
		return accountTypeDao.getAccountTypeByID(accountTypePK);
	}

}
