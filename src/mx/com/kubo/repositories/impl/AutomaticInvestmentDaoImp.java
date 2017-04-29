package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFrequency;
import mx.com.kubo.repositories.AutomaticInvestmentDao;

@Repository
public class AutomaticInvestmentDaoImp implements AutomaticInvestmentDao,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	@Override
	public boolean saveAutomaticInvestment( AutomaticInvestment ai ){
		
		try{
		
			em.persist(ai);
			
		return true;
		
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean updateAutomaticInvestment( AutomaticInvestment ai ){
		
		try{
			System.out.println("Ejecuta -- updateAutomaticInvestment: "+ai.getLast_investment());
			em.merge(ai);
			return true;
		
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Override
	public List<AutomaticInvestment> getAutomaticInvestmentList( Date fecha ){
		
		// return em.createQuery("from AutomaticInvestment where next_investment_apply = ? and is_active = 1 ",AutomaticInvestment.class).setParameter(1, fecha).getResultList();
		
		return em.createNamedQuery("collectorAutomaticInvestment",AutomaticInvestment.class)
				.setParameter("par_fecha",  fecha)
				.getResultList();
		
	}
	
	
	@Override
	public boolean esDiaFeriado( Date fecha ){
		
		SimpleDateFormat fr = new SimpleDateFormat("yyyy-MM-dd");
		try{
			
			Date fechas = (Date)  em.createNativeQuery("Select Fecha from microfin.DIASFESTIVOS where Fecha = '"+ fr.format(fecha) +"' ").getSingleResult();
			
			if(fechas == null  ){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<AutomaticInvestment> getAutomaticInvestmentListByProspect( Integer prospectus_id ){
		try{
			
			return em.createQuery("from AutomaticInvestment where prospectus_id = ? " , AutomaticInvestment.class ).setParameter(1, prospectus_id).getResultList();
			
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	
	public List<InvestmentFrequency> getInvestmentFrequencyLst(){
		
		try{
			
			return em.createQuery("from InvestmentFrequency order by order_showed  " , InvestmentFrequency.class ).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public AutomaticInvestment getAutomaticInvestment( int automatic_investment_id )
	{
		try
		{			
			return em.createQuery("from AutomaticInvestment where automatic_investment_id = ? " , AutomaticInvestment.class ).setParameter(1, automatic_investment_id).getSingleResult();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	
}
