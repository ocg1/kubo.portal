package mx.com.kubo.repositories.impl;

import java.util.Date;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.InegiEconActivityCatPK;
import mx.com.kubo.model.TenureCat;
import mx.com.kubo.repositories.EmploymentDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmploymentDaoImp extends DAOEmploymentDMO
implements EmploymentDao 
{	
	@Transactional
	public boolean saveEmployment(Employment newEmployment) 
	{
		try 
		{		
/*
		String query="select MAX(e.employmentPK.employment_id) from Employment e";
		int idEmployment=0;
		
		if(em.createQuery(query).getSingleResult() !=null)
		{
			idEmployment=(Integer) em.createQuery(query).getSingleResult();
			idEmployment++;
			
		} else {
		
			idEmployment++;
		}
		
		newEmployment.getEmploymentPK().setEmployment_id(idEmployment);
		newEmployment.setPhone_id(1);
*/
			
		newEmployment.setCapture_date(new Date());
		
		em.persist(newEmployment);
		
		return true;
		
		} catch (Exception e) {
			
			return false;
		}
	}
	
	@Transactional
	public boolean updateEmployment(Employment employ) 
	{				   
	    try 
	    {
	    	em.merge(employ);
	    	
	    	return true;
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
			
			log.info("Error: " + e.getMessage());
			
			return false;
		}
	}

	@Transactional
	public boolean removeEmployment(EmploymentPK pk) 
	{
		boolean bandera=false;
		
		 try
		 {
			    log.info("Inicia eliminar");
			    Employment employx = em.find(Employment.class, pk);
			    if(employx!=null)
			    	em.remove(employx); 
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
	
	public InegiEconActivityCat searchActivitySector(InegiEconActivityCatPK pk) 
	{
		log.info("Entro den DAO search");
		catag = null;
		
		try {
			catag=em.find(InegiEconActivityCat.class,pk);
		} catch (Exception e) {
			log.info("Exception:-------------------------->"+e.getMessage());
		}
		return catag;
		
	}
	
	public final TenureCat getTenure(int tenure_id, int company_id)
	{ 
		query = "from TenureCat t where t.tenureCatPK.tenure_id = ? and t.tenureCatPK.company_id = ?";
		
		typed = em.createQuery(query, TenureCat.class);
		typed.setParameter(1, tenure_id);
		typed.setParameter(2, company_id);
		
		tenure = typed.getSingleResult();
		
		return tenure;
	}
	
	@Transactional	
	public boolean updateEmploymentBlur(Employment employ, EmploymentPK pk) 
	{
		boolean bandera=false;	
		/*
	    
	    try {
	    	log.info("Inicia Actualizacion BLUR");		    	
	    	Employment employx = em.find(Employment.class, pk);
	    	
	    	employx.setPosition(employ.getPosition());
	    	employx.setContract_type_id(employ.getContract_type_id());
	    	employx.setNss(employ.getNss());
	    	employx.setSector_bussines_id(employ.getSector_bussines_id());
	    	employx.setTenure_id(employ.getTenure_id());
	    	employx.setPrevious_job_tenure_id(employ.getPrevious_job_tenure_id());
	    	employx.setCheck_in(employ.getCheck_in());
	    	employx.setCheck_out(employ.getCheck_out());
	    	
	    	log.info("Termina Actualizacion BLUR ");

	    	bandera= true;
		} catch (Exception e) {
			log.info("Erro"+e.getMessage());
			bandera=false;
		}
	    finally{
	    	em.close();		    */	
	    
	    return bandera;
	}
	
	@Transactional
	public boolean deleteAllEmployment(int prospectID,int companyID) 
	{
		try 
		{		
			query = "delete from gn_employment e where e.prospectus_id = ? and e.company_id = ?";
			
			em.createNativeQuery(query).setParameter(1, prospectID).setParameter(2, companyID);
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
}
