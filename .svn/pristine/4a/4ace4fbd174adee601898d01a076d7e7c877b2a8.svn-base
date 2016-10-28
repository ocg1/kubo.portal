package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InactiveAccount;
import mx.com.kubo.repositories.InactiveAccountDao;
import mx.com.kubo.services.InactiveAccountService;

@Service
public class InactiveAccountServiceImp implements Serializable, InactiveAccountService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InactiveAccountDao repository;
	
	@Override
	public List<InactiveAccount> getInactiveAccountList(){
		
		return repository.getInactiveAccountList();
		
	}
	
}
