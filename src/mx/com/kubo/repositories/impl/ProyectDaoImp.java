package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.repositories.ProyectDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProyectDaoImp 
implements ProyectDao 
{

	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private String query;
	
	private List<Proyect> lista_proyectos;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	
	@Override
	public Proyect loadSelectedProyect(ProyectPK pk) 
	{		
		return em.find(Proyect.class, pk);		
	}
	

	@Transactional
	@Override
	public boolean updateProyect(Proyect newProyect) 
	{
		log.info("updateProyect.ProyectDaoImp");
		
		try
		{
			em.merge(newProyect);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean saveProyect(Proyect thisproyect) 
	{
		log.info("saveProyect.ProyectDaoImp");
		
		try
		{
			em.persist(thisproyect);
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}			
	}
	
	
	@Override
	public List<Proyect> loadProyectList() 
	{			
		lista_proyectos = em.createQuery("Select p From Proyect p", Proyect.class).getResultList();
		
		return lista_proyectos;
	}
	
	@Override
	public List<Proyect> loadProyectListByProspect(Prospectus prospecto)
	{
		int prospectus_id = prospecto.getProspectusPK().getProspectus_id();
		int company_id   = prospecto.getProspectusPK().getCompany_id();
		
		query = "Select p From Proyect p "
		      + "where prospectus_id = ? "
		      + "and company_id      = ?";
		
		lista_proyectos = em.createQuery(query, Proyect.class)
						    .setParameter(1, prospectus_id)
						    .setParameter(2, company_id)
						    .getResultList();
		
		return lista_proyectos;
	}


	@Override
	public int getMaxProyectID() 
	{
		query = "select MAX(p.proyectoPk.proyect_id) from Proyect p";
		
		int idproyect = 0;
		
		if(em.createQuery(query).getSingleResult() != null)
		{
			idproyect = (Integer) em.createQuery(query).getSingleResult();
			idproyect++;
			
		} else {
			
			idproyect++;
		}
		
	 return idproyect;
	}


	@Override
	public Proyect getMaxProyect(int prospectusID,int companyID) 
	{
		String query = "select MAX(p.proyect_id) "
				     + "from ln_proyect p "
				     + "where prospectus_id = ? "
				     + "and company_id      = ?";
		
		Integer id = 0;
		
		id = (Integer) em.createNativeQuery(query)
						 .setParameter(1, prospectusID)
						 .setParameter(2, companyID)
						 .getSingleResult();
		
		if(id == null)
		{
			return null;
			
		} else {
			ProyectPK pPk = new ProyectPK(id, prospectusID, companyID);
			
			return em.find(Proyect.class, pPk);
		}
		
	}
	
	@Override
	public Proyect getMaxProyectWithPurpose(int prospectusID,int companyID) 
	{
		String query = "select MAX(p.proyect_id) "
				     + "from ln_proyect p "
				     + "where prospectus_id = ? "
				     + "and company_id      = ? " +
				       "and purpose_id is not null and type_id is not null ";
		
		Integer id = 0;
		
		id = (Integer) em.createNativeQuery(query)
						 .setParameter(1, prospectusID)
						 .setParameter(2, companyID)
						 .getSingleResult();
		
		if(id == null || id == 0)
		{
			return null;
			
		} else {
			ProyectPK pPk = new ProyectPK(id, prospectusID, companyID);
			
			return em.find(Proyect.class, pPk);
		}
		
	}
	
}
