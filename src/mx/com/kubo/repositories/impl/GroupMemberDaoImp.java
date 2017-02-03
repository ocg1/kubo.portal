package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.GroupMember;
import mx.com.kubo.repositories.GroupMemberDao;

@Repository
public class GroupMemberDaoImp implements Serializable, GroupMemberDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public boolean saveMember( GroupMember gm ){
		
		try{
			
			em.persist(gm);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean updateMember( GroupMember gm ){
		
		try{
			
			em.merge(gm);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean deleteMember( GroupMember gm ){
		
		try{
			
			em.remove(gm.getPk());
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<GroupMember> getMembersGroupByGroupId( Integer group_id ){
	
		try{
			
			return em.createQuery("from GroupMember where pk.investor_group_id = ? ", GroupMember.class).setParameter(1, group_id).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<GroupMember> getMembersGroupByProspectus( Integer prospectus_id ){
		
		try{
			
			return em.createQuery("from GroupMember where pk.prospectus_id = ? ", GroupMember.class).setParameter(1, prospectus_id).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
