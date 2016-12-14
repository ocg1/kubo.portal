package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.ContactWay;
import mx.com.kubo.model.ContactWayProspectus;
import mx.com.kubo.repositories.ContactWayProspectusDao;

@Repository
public class ContactWayProspectusDaoImp implements ContactWayProspectusDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public List<ContactWayProspectus> getContactWayProspectusList( int company_id, int prospectus_id ){
		
		try{
			
			String query = "from ContactWayProspectus where pk.company_id = ? and pk.prospectus_id = ?";
			
			return em.createQuery(query, ContactWayProspectus.class).setParameter(1, company_id).setParameter(2, prospectus_id).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	public List<ContactWay> getContactWayCatEnabled(){
		
		try{
			
			String query = "from ContactWay where enabled = 1 order by menu_order";
			
			return em.createQuery(query, ContactWay.class).getResultList();
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	public List<ContactWay> getAllContactWayCat(){
		
		try{
			
			String query = "from ContactWay";
			
			return em.createQuery(query, ContactWay.class).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Transactional
	public boolean saveContactWayProspectus( ContactWayProspectus contact ){
		
		try{
			
			em.persist(contact);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Transactional
	public boolean removeContactWayProspectus( ContactWayProspectus contact ){
		
		try{
			
			ContactWayProspectus tem = em.find(ContactWayProspectus.class, contact.getPk());
			
			em.remove(tem);
			return true; 
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
