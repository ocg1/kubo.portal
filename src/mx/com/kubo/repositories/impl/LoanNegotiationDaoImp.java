package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.repositories.LoanNegotiationDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoanNegotiationDaoImp  implements Serializable, LoanNegotiationDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	public LoanNegotiation loadSelectedLoanNegotiation(LoanNegotiationPK pk) {
		try{
			return em.find(LoanNegotiation.class,pk);
		}catch(Exception e){
			return null;
		}
	}
	
	@Transactional
	@Override
	public void saveLoanNegotiation(LoanNegotiation newLoanNegotiation) {
		log.info("saveLoanNegotiation.LoanNegotiationDaoImp");
		String query="select MAX(a.pk.negotiation_id) from LoanNegotiation a";
		Integer id=0;
		id=(Integer) em.createQuery(query).getSingleResult();	
		if(id == null)
			id=1;
		else
			id++;
		newLoanNegotiation.getPk().setNegotiation_id(id);
		em.persist(newLoanNegotiation);
	}
	
	@Override
	public LoanNegotiation loadMaxLoanNegotiation(Integer prospectus_id, Integer company_id, Integer proyect_loan_id, Integer proyect_id ) {
		log.info("loadMaxLoanNegotiation.LoanNegotiationDaoImp");
		String query="select MAX(a.pk.negotiation_id) from LoanNegotiation a where a.pk.prospectus_id = ? and a.pk.company_id = ? and a.pk.proyect_loan_id = ? and a.pk.proyect_id = ? and status is null ";
		Integer id=0;
		id=(Integer)  em.createQuery(query)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.setParameter(3, proyect_loan_id)
						.setParameter(4, proyect_id)
						.getSingleResult();
		if(id == null)
			return null;
		else{
			LoanNegotiationPK pk = new LoanNegotiationPK();
			pk.setCompany_id(company_id);
			pk.setNegotiation_id(id);
			pk.setProspectus_id(prospectus_id);
			pk.setProyect_loan_id(proyect_loan_id);
			pk.setProyect_id(proyect_id);
			return em.find(LoanNegotiation.class,pk);
		}
		
	}
	
	@Transactional
	@Override
	public void update(LoanNegotiation newLoanNegotiation) {
		log.info("updateLoanNegotiation.LoanNegotiationDaoImp");
		em.merge(newLoanNegotiation);
	}
	
	

}
