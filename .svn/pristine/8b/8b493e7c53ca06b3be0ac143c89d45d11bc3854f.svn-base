package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.AccountType;
import mx.com.kubo.model.AccountTypePK;
import mx.com.kubo.repositories.AccountTypeDao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountTypeDaoImp implements AccountTypeDao{

	private EntityManager em = null;
	
	@Override
	public AccountType getAccountTypeByID(AccountTypePK accountTypePK) {
		return em.find(AccountType.class, accountTypePK);
	}


	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
}
