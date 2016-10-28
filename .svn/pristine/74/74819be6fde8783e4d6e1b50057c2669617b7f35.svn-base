package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RoleAssignment;
import mx.com.kubo.repositories.RoleAssignmentDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleAssignmentDaoImp implements RoleAssignmentDao {
	
	
	private EntityManager em = null;
	
	 @PersistenceContext
	    public void setEntityManager(EntityManager em) {
	        this.em = em;
	    }
	
	@Override
	@Transactional
	public boolean saveRoleAssignment( RoleAssignment role ){
		try{
			
			String hql= "select max(pk.role_assignment_id) from RoleAssignment";
			
			List list = em.createQuery(hql).getResultList();
			
			int maxID = (((Integer)list.get(0))==null)?0:((Integer)list.get(0)).intValue();
			
			maxID++;
			
			role.getPk().setRole_assignment_id(maxID);
			
			em.persist(role);
			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	@Override
	@Transactional
	public boolean updateRoleAssignment( RoleAssignment role ){
		
		try{
			RoleAssignment roley = em.find(RoleAssignment.class, role.getPk());
		    
			if(roley!=null)
		    	em.merge(role);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean removeRoleAssignment( RoleAssignment role ){
		try{
			
			
			RoleAssignment roley = em.find(RoleAssignment.class, role.getPk());
		    if(roley!=null)
		    	em.remove(roley); 
		    
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public RoleAssignment getActualRoleAssignmentByProspectus(int prospectus_id, int company_id){
		
		String query = "from RoleAssignment where status_id = 'V' and pk.prospectus_id = ? and pk.company_id = ?  ";
		
		try{
			
			List<RoleAssignment> lst =  em.createQuery(query,RoleAssignment.class)
												.setParameter(1, prospectus_id)
												.setParameter(2, company_id)
												.getResultList();
			
			if( lst!= null && lst.size()>0 ){
				
				return lst.get(0);
				
			}else{
				
				return null;
				
			}
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	public RoleAssignment getPendingRoleAssignmentByProspectus(int prospectus_id, int company_id){
		
		String query = "from RoleAssignment where status_id = 'P' and pk.prospectus_id = ? and pk.company_id = ?  ";
		
		try{
			
			List<RoleAssignment> lst =  em.createQuery(query,RoleAssignment.class)
											.setParameter(1, prospectus_id)
											.setParameter(2, company_id)
											.getResultList();
			
			if( lst!= null && lst.size()>0 ){
				
				return lst.get(0);
				
			}else{
				
				return null;
				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
		
	}
	
}
