package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.repositories.ClientsDao;

@Repository
public class ClientsDaoImp implements Serializable, ClientsDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected EntityManager em;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public List<Clients> getListClients(){
		
		try{
		
		String  query = "from Clients ";
		
		return em.createQuery(query, Clients.class).getResultList();
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	public Clients getClientById( ClientsPK pk ){
		
		try{
			
			String  query = "from Clients where pk.prospectus_id = ? and pk.company_id = ? ";
			
			return em.createQuery(query, Clients.class)
							.setParameter(1,pk.getProspectus_id())
							.setParameter(2,pk.getCompany_id())
							.getSingleResult();
			
			}catch(Exception e){
				//e.printStackTrace();
				return null;
			}
		
	}
	public boolean saveClient( Clients client ){
		try{
			
			em.persist(client);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		
		}
	}
	
}
