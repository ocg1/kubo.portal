package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.repositories.PhoneDao;

public abstract class DAOPhoneDMO 
implements PhoneDao 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	private List<Phone> lisPhone;
	
	private TypedQuery<Phone> typed;
	
	private StringBuilder sb;
	
	private String query;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	public Phone getPhoneByID(PhonePK pk) 
	{
		return em.find(Phone.class, pk);
	}
	
	public List<Phone> getPhoneByEmploymentListByArea(int prospectusID,int companyID, int employmentID,Character area) 
	{
		sb = new StringBuilder();
		
		sb.append("select p from Phone p ");
		sb.append("where p.phonePk.prospectus_id = ? ");
		sb.append("and   p.phonePk.company_id    = ? ");
		sb.append("and   p.employment_id         = ? ");
		sb.append("and   p.area                  = ? ");
		
		query = sb.toString();
		
		typed = em.createQuery(query, Phone.class);
		
		typed.setParameter(1, prospectusID);
		typed.setParameter(2, companyID);
		typed.setParameter(3, employmentID);
		typed.setParameter(4, area);
		
		lisPhone = typed.getResultList();
		
		return lisPhone;
	}
		
	public List<Phone> getPhoneByProspectusList(int prospectusID,int companyID) 
	{
		String query="select p from Phone p where p.phonePk.prospectus_id=? and p.phonePk.company_id=?  order by phone_type_id";
		lisPhone=em.createQuery(query,Phone.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getResultList();
		return lisPhone;
	}

	
	public List<Phone> getPhoneByBusinessListByArea(int prospectusID, int companyID,int businessID,Character area) 
	{
		String query="select p from Phone p where p.phonePk.prospectus_id=? and p.phonePk.company_id=? and p.business_id=? and p.area=?";
		lisPhone=em.createQuery(query,Phone.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, businessID)
				.setParameter(4, area)
				.getResultList();
		return lisPhone;
	}
	
	public List<Phone> loadAllPhones()
	{
		String query="from Phone";
		List<Phone> lisPhone=em.createQuery(query,Phone.class)
				.getResultList();
		return lisPhone;
	}
	
	public List<PhoneType> loadAllPhoneType() 
	{
		String query="from PhoneType";
		List<PhoneType> lisPhoneType=em.createQuery(query,PhoneType.class)
				.getResultList();
		return lisPhoneType;
	}
	
	public List<Phone> getPhoneListByNumber( String  phone_str,int prospectus_id , int company_id )
	{		
		String query="from Phone where phonePk.prospectus_id <> ? and phone_number = ? and phonePk.company_id = ?";
		
		List<Phone> lst = em.createQuery(query,Phone.class)
								.setParameter(1, prospectus_id)
								.setParameter(2, phone_str)
								.setParameter(3, company_id)
								.getResultList();		
		return lst;		
	}
	
	public Phone getPhoneByTypeByEmploymentByArea(int prospectusID, int companyID,int phoneType, int employmentID,Character area) 
	{
		String query="select Max(phone_id) from gn_phone p where prospectus_id=? and company_id=? and phone_type_id=? and p.employment_id=? and p.area=?";
		Integer id=0;
		id=(Integer)em.createNativeQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, phoneType)
				.setParameter(4, employmentID)
				.setParameter(5, area)
				.getSingleResult();
		log.info("phone employ es= "+id);
		if(id==null){
			return null;
		}
		else{
			log.info("DAO ELSE es= "+id);
			PhonePK phonePk=new PhonePK(id,prospectusID,companyID);	
			log.info("ELSE2 es= "+id);
			return em.find(Phone.class,phonePk);
		}
	}

	
	public Phone getPhoneByTypeByBusinessByArea(int prospectusID, int companyID,int phoneType, int businessID,Character area) {
		String query="select Max(phone_id) from gn_phone p where prospectus_id=? and company_id=? and phone_type_id=? and p.business_id=? and p.area=?";
		Integer id=0;
		
		id=(Integer)em.createNativeQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, phoneType)
				.setParameter(4, businessID)
				.setParameter(5, area)
				.getSingleResult();
		log.info("phone business es= "+id);
		if(id==null){
			return null;
		}
		else{
			PhonePK phonePk=new PhonePK(id,prospectusID,companyID);
			return em.find(Phone.class,phonePk);
		}
		
	}
	
	
	public Phone getPhoneByTypeByArea(int prospectusID, int companyID,int phoneType,Character area) 
	{
		String query="select Max(phone_id) from gn_phone p where prospectus_id=? and company_id=? and phone_type_id=? and p.area=?";
		Integer id=0;
		
		id=(Integer)em.createNativeQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, phoneType)
				.setParameter(4, area)
				.getSingleResult();
		
		log.info("phone type es= "+id);
		
		if(id == null)
		{
			return null;
			
		} else {
			
			PhonePK phonePk=new PhonePK(id,prospectusID,companyID);
			return em.find(Phone.class,phonePk);
		}
		
	}
	
	public List<Phone> getPhoneListByType (int prospectusID, int companyID, int phone_type_id)
	{
		String query="select p from Phone p where p.phonePk.prospectus_id=? and p.phonePk.company_id=? and p.phone_type_id=?";
		lisPhone=em.createQuery(query,Phone.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, phone_type_id)
				.getResultList();
		return lisPhone;
	}
}
