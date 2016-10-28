package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.repositories.ServiceCallingDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ServiceCallingDaoImp implements ServiceCallingDao {
	
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
	public ServiceCalling loadSelectedServiceCall(int scoring_result_id) {
		return em.find(ServiceCalling.class,scoring_result_id);
	}

	
	@Transactional
	@Override
	public void saveServiceCall(ServiceCalling newScoring) {
		log.info("saveServiceCall.ServiceCallDaoImp");
//		String query="select MAX(s.service_calling_id) from ServiceCalling s";
//		Integer id=0;
//		id=(Integer) em.createQuery(query).getSingleResult();
//		id= (id==null)?0:id;
//		id++;
//		newScoring.setService_calling_id(id);	
		em.persist(newScoring);
	}
	
	@Override
	public ServiceCalling loadServiceCallByProspectus(Integer prospectus_id,Integer company_id) {
		log.info("loadServiceCallbyProspectus.ServiceCallDaoImp");
		String query="select MAX(s.service_calling_id) from ServiceCalling s where s.prospectus_id = ? and s.company_id = ?";
		Integer id=0;
		id=(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();	
		
		if(id!=null)
			return em.find(ServiceCalling.class,id);
		else
			return null;
	}
	
	@Transactional
	@Override
	public boolean updateServiceCall(ServiceCalling newServiceCall){
		log.info("updateExpenses.ExpensesDaoImp");
		try{
			em.merge(newServiceCall);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
