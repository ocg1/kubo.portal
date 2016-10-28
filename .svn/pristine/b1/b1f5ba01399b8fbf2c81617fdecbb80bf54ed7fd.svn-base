package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.InvestmentsAtrasadasEdoCta;
import mx.com.kubo.repositories.InvestmentsAtraEdoCtaDao;

import org.apache.log4j.Logger;																  
import org.springframework.stereotype.Repository;

@Repository
public class InvestmentsAtraEdoCtaDaoImp implements InvestmentsAtraEdoCtaDao,Serializable{
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public InvestmentsAtrasadasEdoCta getInvestmentsAtraEdoCta(String cuentaAhoID) {
		try{
			//Integer cuentaID =  100008874;
			
			@SuppressWarnings("unchecked")
			InvestmentsAtrasadasEdoCta InversionesAtrasadas	=	em.createNamedQuery("getInvestmentsAtraEdoCta",InvestmentsAtrasadasEdoCta.class)
					.setParameter("cuentaAhoID",cuentaAhoID )
					.getSingleResult();
			
			return InversionesAtrasadas;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
	
}
