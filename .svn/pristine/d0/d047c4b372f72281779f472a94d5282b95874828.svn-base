package mx.com.kubo.repositories.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.repositories.IncomeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IncomeDaoImp implements IncomeDao {
	
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
	public Income getIncomebyID(IncomePK incomePk) {
		// TODO Auto-generated method stub
		return em.find(Income.class, incomePk);
	}
	@Transactional
	@Override
	public boolean addIncome(Income newIncome,int prospectusID,int companyID) {
		Integer idIncome=0;
		idIncome=(Integer) em.createNamedQuery("queryAddIncome")
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idIncome==null){			
			idIncome=1;
		}
		else{
			idIncome++;
			}
		log.info("Maximo de ingresos= "+idIncome);
		newIncome.getIncomePk().setIncome_id(idIncome);
		try {
			em.persist(newIncome);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	@Transactional
	@Override
	public boolean updateIncome(Income income) {
		System.out.println("updateIncome.IncomeDaoImp");
		try{
			em.merge(income);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Income getIncomeByTypeIncomeID(int prospectusID, int companyID,int typeIncomeID) {
		String query="select Max(income_id) from gn_income  where prospectus_id=? and company_id=? and income_type_id=? ";
		Integer id=0;
		id=(Integer)em.createNativeQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, typeIncomeID)
				.getSingleResult();
		log.info("income id es= "+id);
		if(id==null){
			return null;
		}
		else{
			IncomePK incomePk=new IncomePK(id,prospectusID,companyID);	
			return em.find(Income.class,incomePk);
		}
	}

	@Override
	public List<Income> getListIncomeByProspect(int prospectusID, int companyID) {
		String query="from Income i where i.incomePk.prospectus_id=? and i.incomePk.company_id=?";
		List<Income> incomeList=em.createQuery(query,Income.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getResultList();
		
		return incomeList;
	}
	
	@Transactional
	@Override
	public boolean removeIncome(IncomePK incomePk) {
		boolean bandera=false;	
		 try{
			    log.info("Inicia eliminar Income");
			    Income incomex = em.find(Income.class, incomePk);
			    if(incomex!=null)
			    	em.remove(incomex); 
			    log.info("Termina eliminar Income");
			    bandera= true;
		 }
		 catch (Exception e) {
				log.info("Error al eliminar Income"+e.getMessage());
				bandera=false;
			}
		 finally {
			    em.close();
			  }
		 return bandera;
	}

}
