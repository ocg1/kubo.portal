package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RelationShipPK;
import mx.com.kubo.repositories.RelationShipDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class RelationShipDaoImp implements RelationShipDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	/**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    
	@Override
	public RelationShip getRelationShipByID(RelationShipPK relationShipPk) {
		return em.find(RelationShip.class, relationShipPk);
	}

	@Transactional
	@Override
	public boolean addRelationShip(RelationShip newRelationShip) {		
		try {
			em.persist(newRelationShip);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	@Transactional
	@Override
	public boolean updateRelationShip(RelationShip relationShip) {
		try{
			em.merge(relationShip);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RelationShip> getListRelationShip() {		
		return em.createQuery("from RelationShip",RelationShip.class).getResultList();
	}

	@Override
	public RelationShip getRelationShipByProspectOriginal(int prospectusID,int companyID) {		
		log.info("getRelationShipByProspectOriginal.RealitonshipDaoImp");
		
		String query="select max(r.relationShipPk.prospectus_destiny_id) from RelationShip r where r.relationShipPk.prospectus_origin_id=? and r.relationShipPk.company_id=? ";
		Integer idDistiny=0;
		idDistiny=(Integer)em.createQuery(query)
				.setParameter(1,prospectusID)
				.setParameter(2, companyID)			
				.getSingleResult();
		log.info("Id prospecto destino = "+idDistiny);
		if(idDistiny==null){
			return null;
		}
		else{
			RelationShipPK relationPk=new RelationShipPK(prospectusID,companyID,idDistiny);	
			return em.find(RelationShip.class,relationPk);
		}
		
	}

}
