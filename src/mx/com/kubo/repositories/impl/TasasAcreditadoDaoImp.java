package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TasasAcreditado;
import mx.com.kubo.repositories.TasasAcreditadoDao;

import org.springframework.stereotype.Repository;

@Repository
public class TasasAcreditadoDaoImp implements TasasAcreditadoDao, Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	
	@Override
	public TasasAcreditado getTasaAcreditadoByRate(Double rate){
		
		String query = "from TasasAcreditado where rate = ? ";
		try{
			
			List<TasasAcreditado> lsttasa = em.createQuery(query,TasasAcreditado.class).setParameter(1, rate).getResultList();
			
			
			if( lsttasa!=null && lsttasa.size()>0){
				return lsttasa.get(0);
			}
			else
				return null;
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	public List<TasasAcreditado> getListTasasAcreditado(){
		
		String query = "from TasasAcreditado order by tasas_acreditado_id";
		try{
			
			List<TasasAcreditado> lst = em.createQuery(query,TasasAcreditado.class).getResultList();
			return lst;
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
