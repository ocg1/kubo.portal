package mx.com.kubo.repositories.impl;

import mx.com.kubo.model.ExistPhone;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.repositories.PhoneDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PhoneDaoImp extends DAOPhoneDMO
implements PhoneDao 
{    		
	@Transactional	
	public boolean addPhone(Phone newPhone,int prospectusID,int companyID) 
	{
		String query="select MAX(p.phonePk.phone_id) from Phone p where p.phonePk.prospectus_id=? and p.phonePk.company_id=?";
		Integer idphone=0;
		idphone=(Integer) em.createQuery(query)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idphone==null){			
			idphone=1;
		}
		else{
			idphone++;
			}
		log.info("Maximo id phone business= "+idphone);
		
		newPhone.getPhonePk().setPhone_id(idphone);
		try {
			em.persist(newPhone);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	@Transactional	
	public boolean updatePhone(Phone phone) 
	{
		log.info("updatePhone.PhoneDaoImp");
		try{
			em.merge(phone);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
		
	@Transactional	
	public boolean removePhone(PhonePK pkPhone) 
	{
		boolean bandera=false;
		
		try
		{
			    log.info("Inicia eliminar");
			    Phone phonex = em.find(Phone.class, pkPhone);
			    if(phonex!=null)
			    	em.remove(phonex); 
			    log.info("Termina eliminar");
			    bandera= true;
			    
		 } catch (Exception e) {
				log.info("Error"+e.getMessage());
				bandera=false;
		} finally {
			    em.close();
	    }
		
		return bandera;
	}
	
	public String existPhone(Phone newPhone, int prospectusID,int companyID )
	{		
		ExistPhone res =  em.createNamedQuery("existePhoneSP",ExistPhone.class)
				.setParameter("phone_str"	 ,newPhone.getPhone_number())
				.setParameter("prospectus_id",prospectusID)
			.getSingleResult();
		
		String str =  res.getPersona()+";;"+res.getReferencia() ;
		
		return str;
	}
}
