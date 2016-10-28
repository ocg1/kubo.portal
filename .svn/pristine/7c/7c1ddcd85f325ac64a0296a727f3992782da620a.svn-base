package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.SecQuestPoolPK;
import mx.com.kubo.model.SecurityQuestionPool;
import mx.com.kubo.repositories.SecurityQuestionPoolDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SecurityQuestionPoolDaoImp 
implements SecurityQuestionPoolDao 
{	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) 
	{
	    this.em = em;
	}
		
	public List<SecurityQuestionPool> loadSecQuestPoolByProspect(int prospectus_id, int company_id)
	{		
		String query = "from SecurityQuestionPool sq  where sq.pk.prospectus_id = ? and sq.pk.company_id = ? ";
		
		TypedQuery<SecurityQuestionPool> typed;
		List<SecurityQuestionPool> security_question_pool;
		
		typed = em.createQuery(query , SecurityQuestionPool.class);
		typed.setParameter(1,prospectus_id);
		typed.setParameter(2,company_id);
		
		security_question_pool = typed.getResultList();
		
		return security_question_pool;
		
	}
		
	@Transactional
	public boolean removeSecQuest(SecQuestPoolPK pk)
	{
		try
		{
			 SecurityQuestionPool secQuest = em.find(SecurityQuestionPool.class, pk);
			 
			 em.remove(secQuest);
			 
			 return true;
			 
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	public SecurityQuestionPool getNextRandomSecQuestPool(int prospectus_id, int company_id)
	{
		String query_1 = "from SecurityQuestionPool sq  where sq.pk.prospectus_id = ? and sq.pk.company_id = ? and sq.date_used is null ";
		String query_2 = "from SecurityQuestionPool sq  where sq.pk.prospectus_id = ? and sq.pk.company_id = ? ";
		
		List<SecurityQuestionPool> lstSecQuest;
		TypedQuery<SecurityQuestionPool> typed;
		
		typed = em.createQuery(query_1, SecurityQuestionPool.class);
		typed.setParameter(1,prospectus_id);
		typed.setParameter(2,company_id);		
		
		lstSecQuest = typed.getResultList();
		
		SecurityQuestionPool secReturn = null;
		int thisVal = 0;
		
		if(lstSecQuest != null && lstSecQuest.size()>0)
		{			
			int[] numeros = new int [ lstSecQuest.size() ];
			
			int i = 0;
			
			for(SecurityQuestionPool sec : lstSecQuest)
			{
				numeros[i] = sec.getPk().getSecurity_question_id();
				i++;
			}
			
			Double x = (Math.random()*numeros.length+0);
			thisVal = numeros[x.intValue()];
			
		} else {
			
			typed = em.createQuery(query_2, SecurityQuestionPool.class);
			typed.setParameter(1,prospectus_id);
			typed.setParameter(2,company_id);
			
			lstSecQuest = typed.getResultList();
			
			int[] numeros = new int [ lstSecQuest.size() ];
			int i = 0;
			
			if(lstSecQuest !=null && lstSecQuest.size() > 0)
			{				
				for(SecurityQuestionPool sec : lstSecQuest )
				{					
					sec.setDate_used(null);
					
					em.merge(sec);
					
					numeros[i] = sec.getPk().getSecurity_question_id();
					i++;
				}				
			}
			
			Double x = (Math.random()*numeros.length+0);
			thisVal = numeros[x.intValue()];
			
		}
		
		for(SecurityQuestionPool sec : lstSecQuest)
		{
			if(thisVal == sec.getPk().getSecurity_question_id() )
			{
				secReturn = sec;
				break;
			}
		}
		
		return secReturn;		
	}
		
	@Transactional
	public boolean saveSecQuest( SecurityQuestionPool secQuest ){
		try{
			em.persist(secQuest);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean updateSecQuest( SecurityQuestionPool secQuest )
	{
		try
		{
			em.merge(secQuest);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	public SecurityQuestionPool getSecQuestPoolByPK(SecQuestPoolPK pk)
	{		
		try
		{
			return em.find( SecurityQuestionPool.class, pk );
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
}
