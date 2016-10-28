package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.bean.FunctionBean;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.RoleFunctionPK;
import mx.com.kubo.repositories.RoleFunctionDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleFunctionDaoImp implements RoleFunctionDao, Serializable {
	
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
	  public List<RoleFunction> loadRoleFunctionList(){
		  List<RoleFunction> rolefunctionlist=em.createQuery("from RoleFunction",RoleFunction.class).getResultList();
			return rolefunctionlist;
	  }
	  
	  @Override
	  @Transactional
		public boolean deleteAndSaveRoleFunctions(List<FunctionBean> fnc, int company,int role ){
		  
			  List<RoleFunction>lst = loadRoleFunctionList();
			  for(RoleFunction rf : lst){
				  if(rf.getPk().getRole_id() == role){
					  em.remove( rf);
				  }
			  }
			  
			  for(FunctionBean func : fnc){
				  if(func != null){
					  
					  int i = func.getFunction_id();
					  RoleFunctionPK pk = new RoleFunctionPK();
					  pk.setCompany_id(company);
					  pk.setFunction_id(i);
					  pk.setRole_id(role);
					  
					  RoleFunction rf = new RoleFunction();
					  rf.setComments(func.getDescription());
					  rf.setPk(pk);
					  
					  em.persist(rf);
					  
				  }
			  }
			  
			
			return true;
		}
	  
	  
	  @Override
		public List<RoleFunction> getLstFunctionByRole( int role, int company_id ){
			
			return em.createQuery("from RoleFunction where pk.role_id = ? and company_id = ?  ",RoleFunction.class).setParameter(1, role).setParameter(2, company_id).getResultList();
			
		}
		
		

}
