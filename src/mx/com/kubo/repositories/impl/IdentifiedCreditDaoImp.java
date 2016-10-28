package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.IdentifiedCredit;
import mx.com.kubo.model.IdentifiedCreditPK;
import mx.com.kubo.repositories.IdentifiedCreditDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IdentifiedCreditDaoImp implements IdentifiedCreditDao {
	
	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional
	@Override
	public boolean saveIdentifiedCredit(IdentifiedCredit credit){
		
		String query="select MAX(i.pk.identified_credit_id) from IdentifiedCredit i";
		Integer id=0;
		id=(Integer) em.createQuery(query).getSingleResult();	
		
		if(id!=null){
			id++;
		}else{
			id=1;
		}
		
		credit.getPk().setIdentified_credit_id(id);
		
		try{
			em.persist(credit);
			return true;
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public List<IdentifiedCredit> getIdentifiedCreditListByProspectus(int company_id, int prospectus_id){
		
		String query="from IdentifiedCredit i where i.pk.prospectus_id = ? and i.pk.company_id = ?";
		
		try{
			
			return em.createQuery(query,IdentifiedCredit.class)
								.setParameter(1, prospectus_id)
								.setParameter(2, company_id)
								.getResultList();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	@Transactional
	@Override
	public boolean updateIdentifiedCredit(IdentifiedCredit credit){
		
		try{
			
			em.find( IdentifiedCredit.class, credit.getPk() );
			
			em.merge(credit);
			return true;
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Override
	public IdentifiedCredit getIdentifiedCreditByPK(IdentifiedCreditPK pk){
		
		String query="from IdentifiedCredit i where " +
				"	i.pk.prospectus_id = ? and " +
				"	i.pk.company_id = ? and " +
				"	i.pk.original_entity = ? and " +
				"	i.pk.frequency = ?  and " +
				"	i.pk.total_payments = ? and " +
				"	i.pk.start_date = ?";
		
		try{
			
			// SimpleDateFormat frm = new SimpleDateFormat("dd/MM/yyyy");
			
			return em.createQuery(query,IdentifiedCredit.class)
								.setParameter(1, pk.getProspectus_id())
								.setParameter(2, pk.getCompany_id())
								.setParameter(3, pk.getOriginal_entity())
								.setParameter(4, pk.getFrequency())
								.setParameter(5, pk.getTotal_payments() )
								.setParameter(6, pk.getStart_date() )
								.getSingleResult();
			
		}catch( Exception e ){
			
			System.out.println("No se encontró ningún registro");
			return null;
			
		}
	}

}
