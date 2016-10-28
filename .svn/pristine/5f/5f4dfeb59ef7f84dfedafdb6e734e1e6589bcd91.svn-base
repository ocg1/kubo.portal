	package mx.com.kubo.repositories.impl;

	import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.repositories.BusinessDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

	@Repository
	public class BusinessDaoImp implements BusinessDao,Serializable {
		
		private static final long serialVersionUID = 1L;
		
		Logger log = Logger.getLogger(getClass());
		
		private EntityManager em = null;

		private List<Business> lista_business;
		
	    @PersistenceContext
	    public void setEntityManager(EntityManager em) {
	        this.em = em;
	    }

		@Override
		public Business loadSelectedBusiness(BusinessPK pk) {
			return em.find(Business.class,pk);
		}

		@Transactional
		@Override
		public boolean saveBusiness(Business newBusiness) {
			try {
			
			/*String query="select MAX(e.businessPK.business_id) from Business e";
			int idBusiness=0;
			if(em.createQuery(query).getSingleResult() !=null){
				idBusiness=(Integer) em.createQuery(query).getSingleResult();
				idBusiness++;
			}
			else{
				idBusiness++;
				}
			newBusiness.getBusinessPK().setBusiness_id(idBusiness);*/
			em.persist(newBusiness);
			return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		@Override
		public List<Business> loadBusinessList() 
		{
			lista_business = em.createQuery("from Business ", Business.class).getResultList();
			
			return lista_business;
		}

		@Transactional
		@Override
		public boolean removeBusiness(BusinessPK pk) {
			boolean bandera=false;	
			 try{
				    log.info("Inicia eliminar");
				    Business employx = em.find(Business.class, pk);
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

		
		@Transactional
		@Override
		public boolean updateBusiness(Business business) {
			try {
				em.merge(business);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Erro al actualizar negocio"+e.getMessage());
				return false;
			}
		}

		@Override
		public List<Business> getListBusinessByProspect(int prospectID,int companyID) {			
			List<Business> bussines = em.createQuery(
				    "from Business e where e.businessPK.prospectus_id = ? and e.businessPK.company_id = ?", Business.class).setParameter(1, prospectID).setParameter(2, companyID)
				    .getResultList();
			return bussines;
		}
		
		@Transactional
		@Override
		public boolean deleteAllBusiness(int prospectID,int companyID) {
			try {		
			String query="delete from gn_business e  where e.prospectus_id = ? and e.company_id = ?";
			em.createNativeQuery(query).setParameter(1, prospectID).setParameter(2, companyID);
			return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		@Override
		public BmxEconActivityCat findBmxActivityById(String bmxActivityID ,int companyID){
			BmxEconActivityCat bmxEconAct= null;
			try {
				bmxEconAct = em.createNamedQuery("findBmxActivityById",BmxEconActivityCat.class)
					.setParameter("company_id", companyID)
					.setParameter("bmx_econ_activity_id", bmxActivityID)
					.getSingleResult();
			}catch(Exception e){
				//e.printStackTrace();
			}
			return bmxEconAct;
			
		}
		
		
	}

