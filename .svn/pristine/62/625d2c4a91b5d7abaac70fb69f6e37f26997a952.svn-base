package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.InvestorParamPK;
import mx.com.kubo.repositories.InvestorParamDao;

@Repository
public class InvestorParamDaoImp implements Serializable, InvestorParamDao {

	
private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}
	
	public InvestorParam getInvestorParamByPK( InvestorParamPK pk ){
		
		try{
			
			return em.find(InvestorParam.class, pk);
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
		
	}
	
	public List<InvestorParam> getInvestorParamByProspectus( int prospectus_id, int company_id ){
		
		try{
			
			String query="from InvestorParam where pk.company_id = ? and pk.prospectus_id = ? ";
			
			return em.createQuery(query, InvestorParam.class).setParameter(1, company_id).setParameter(2, prospectus_id).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Transactional
	public boolean updateInvestorParam( InvestorParam model ){
		
		try{
			
			em.merge( model );
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	
	@Transactional
	public boolean saveInvestorParam( InvestorParam model ){
		
		try{
			
			em.persist( model );
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
