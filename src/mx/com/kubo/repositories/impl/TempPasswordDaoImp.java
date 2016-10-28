package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TempPassword;
import mx.com.kubo.model.TempPasswordPK;
import mx.com.kubo.repositories.TempPasswordDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TempPasswordDaoImp implements TempPasswordDao {
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
	public TempPassword getTempPassByID(TempPasswordPK tempPK) {
		return em.find(TempPassword.class, tempPK);
	}

	@Transactional
	@Override
	public boolean addNewTempPass(TempPassword newTempPass) {
		Integer idTempPass=0;
		String query="select MAX(t.tempPassPK.temp_password_id) from TempPassword t where t.tempPassPK.company_id = ?";
		
		idTempPass=(Integer) em.createQuery(query)
				.setParameter(1, newTempPass.getTempPassPK().getCompany_id())
				.getSingleResult();
		if(idTempPass==null){			
			idTempPass=1;
		}
		else{
			idTempPass++;
			}
		newTempPass.getTempPassPK().setTemp_password_id(idTempPass);
		try {
			em.persist(newTempPass);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}

	@Transactional
	@Override
	public boolean updateTempPass(TempPassword tempass) {
		try{
			em.merge(tempass);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TempPassword getTempPassByPass(String pass) {
		List<TempPassword> temp = em.createQuery(
			    "from TempPassword where password= ? ", TempPassword.class).setParameter(1,pass)
			    .getResultList();
		if(temp.size()>0){
			return temp.get(0);
		}
		 return null;
	}

}
