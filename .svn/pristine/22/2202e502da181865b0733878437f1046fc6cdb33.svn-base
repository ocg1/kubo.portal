package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SimulationBase;
import mx.com.kubo.repositories.SimulationBaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class SimulationBaseDaoImp implements SimulationBaseDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	 
	@Override
	public List<SimulationBase> getSimulationBaseListByElements( List<String> riskArray , List<String> genderArray, List<String> purposeArray, List<String> termArray  ){
		
		String query = "from SimulationBase";
		
		try{
			
			String where = "";
			
			boolean flagWhere = false;
			
			if( riskArray != null  && riskArray.size() > 0  ){
				
				where = " where  kubo_score_a in (";
				int i = 0;
				for( String tmp : riskArray){
					
					if(i != 0)
						where += "," ;
					i++;
								
					where += "'"+tmp+"'"; 
					
				}
				
				where += ")";
				
				flagWhere = true;
				
			}
			
			if( genderArray != null  && genderArray.size() > 0  ){
				
				if(!flagWhere)
					where = " where  ";
				else
					where += " and ";
				
				where += " gender_id in (";
				int i = 0;
				for( String tmp : genderArray){
					
					if(i != 0)
						where += "," ;
					i++;
								
					where += "'"+tmp+"'"; 
					
				}
				
				where += ") ";
				
				flagWhere = true;
				
			}
			
			if( purposeArray != null  && purposeArray.size() > 0  ){
				
				if(!flagWhere)
					where = " where  ";
				else
					where += " and ";
				
				where += " UPPER(purpose) in (";
				int i = 0;
				for( String tmp : purposeArray){
					
					if(i != 0)
						where += "," ;
					i++;
								
					where += "'"+tmp.trim().toUpperCase()+"'"; 
					
				}
				
				where += ") ";
				
				flagWhere = true;
				
			}
			
			if( termArray != null  && termArray.size() > 0  ){
				
				if(!flagWhere)
					where = " where  ";
				else
					where += " and ";
				
				where += " (";
				int i = 0;
				for( String tmp : termArray){
					
					if(i != 0)
						where += " or " ;
					i++;
								
					where += " term BETWEEN "+tmp.split("T")[0]+" and "+tmp.split("T")[1]; 
					
				}
				
				where += ") ";
				
				flagWhere = true;
				
			}
			
			return em.createQuery((query+where),SimulationBase.class).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
