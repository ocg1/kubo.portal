package mx.com.kubo.repositories.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.repositories.BeneficiariesDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BeneficiariesDaoImp 
implements BeneficiariesDao 
{
	
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private String query;
	
	private List<Beneficiaries> lista_beneficiarios;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	@Override
	public Beneficiaries getBeneficiariesByID(BeneficiariesPK beneficiariesPk) {
		return em.find(Beneficiaries.class, beneficiariesPk);
	}
	
	@Transactional
	@Override
	public boolean addBeneficiaries(Beneficiaries newBeneficiaries,int prospectusID, int companyID) {
		Integer idBenefic=0;
		idBenefic=(Integer) em.createNamedQuery("queryAddBenefic")
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idBenefic==null){			
			idBenefic=1;
		}
		else{
			idBenefic++;
			}
		log.info("Maximo de idBenefic= "+idBenefic);
		newBeneficiaries.getBeneficPk().setBeneficiarie_id(idBenefic);
		try {
			em.persist(newBeneficiaries);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	@Transactional
	@Override
	public boolean updateBeneficiaries(Beneficiaries beneficiaries) {
		try{
			if(beneficiaries.getPercentage() != null){
				
				
				/*
				Double val =  Double.parseDouble( beneficiaries.getPercentage() + "");
				val = val*100000000000000L;
				val = Double.parseDouble(Math.round(val)+"" );
				val = val/1000000000000000L;
				beneficiaries.setPercentage( BigDecimal.valueOf(val));
				*/
			}
			
			System.out.println( "porcentaje: "+ beneficiaries.getPercentage() );
			
			em.merge(beneficiaries);
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Beneficiaries> getListBeneficiariesByProspect(int prospectID,int companyID) {
		List<Beneficiaries> benefic = em.createQuery(
			    "from Beneficiaries b where b.beneficPk.prospectus_id = ? and b.beneficPk.company_id = ?", Beneficiaries.class)
			    .setParameter(1, prospectID)
			    .setParameter(2, companyID)
			    .getResultList();
		return benefic;
		
	}

	@Override
	public List<Beneficiaries> getListBeneficByProspectByAccount(int prospectID, int companyID, int account) 
	{
		query = "from Beneficiaries b "
			  + "where b.beneficPk.prospectus_id   = ? "
			  + "and b.beneficPk.company_id        = ? "
			  + "and b.beneficPk.saving_account_id = ?";
		
		lista_beneficiarios = em.createQuery(query, Beneficiaries.class)
			    .setParameter(1, prospectID)
			    .setParameter(2, companyID)
			    .setParameter(3, account)
			    .getResultList();
		
		return lista_beneficiarios;
	}


	@Override
	public boolean saveBeneficByID(Beneficiaries beneficiaries) {
		try {
			em.persist(beneficiaries);
			return true;			
		} catch (Exception e) {
			log.info("Error a guardar benficiario : "+e.getMessage());
			return false;
		}
	}

	@Transactional
	@Override
	public boolean removeBenefic(BeneficiariesPK beneficPk) {
		boolean bandera=false;	
		 try{
			    log.info("Inicia eliminar");
			    Beneficiaries beneficx = em.find(Beneficiaries.class, beneficPk);
			    if(beneficx!=null)
			    	em.remove(beneficx); 
			    log.info("Termina eliminar");
			    bandera= true;
		 }
		 catch (Exception e) {
				log.info("Erro"+e.getMessage());
				bandera=false;
			}
		 finally {
			    em.close();
			  }
		 return bandera;
	}

}
