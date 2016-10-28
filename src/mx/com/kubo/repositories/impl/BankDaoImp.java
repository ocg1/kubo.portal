package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Bank;
import mx.com.kubo.repositories.BankDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


@Repository
public class BankDaoImp implements BankDao {
	
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	@Override
	public List<Bank> searchBankList(String descripStr,boolean maxNum) {
		log.info("DESCRIPCION ES= "+ descripStr);
		
		List<Bank> bmxEconAct= null;
		if(maxNum){
			bmxEconAct = em.createNamedQuery("findBank",Bank.class)
					.setParameter("description", "%"+descripStr+"%")
					.setMaxResults(5)
					.getResultList();
		}else{
			
			if( descripStr == null || descripStr.trim().length() == 0  ){
				
				bmxEconAct =  em.createQuery("from Bank",Bank.class)
						.getResultList();
				
			}else{
			
				bmxEconAct =  em.createNamedQuery("findBank",Bank.class)
					.setParameter("description", "%"+descripStr+"%")
					.getResultList();
			
			}
			
		}
		
		return bmxEconAct;
	}
	
	@Override
	public Bank getBankByShortName(String descripStr) {
		log.info("DESCRIPCION ES= "+ descripStr);
		try{
			
			Bank bankSel=  em.createQuery("FROM Bank  WHERE short_name = ?",Bank.class)
					.setParameter(1, descripStr)
					.getSingleResult();
			
			return bankSel;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return null;
			
		}
	}
	
}
