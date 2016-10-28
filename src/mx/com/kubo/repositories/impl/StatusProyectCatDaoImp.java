package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.EstatusConfig;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.repositories.StatusProyectCatDao;

import org.springframework.stereotype.Repository;

@Repository
public class StatusProyectCatDaoImp 
implements StatusProyectCatDao
{
	private EntityManager    em = null;
	private StatusProyectCat estatus;
	
	private String query;
	
	private List<EstatusConfig>    lista_estatus_config;
	private List<StatusProyectCat> lista_estatus;

	@PersistenceContext
	public void setEm(EntityManager em) 
	{
		this.em = em;
	}

	@Override
	public List<StatusProyectCat> getListStatusProyectCat() 
	{
		lista_estatus = em.createQuery("from StatusProyectCat", StatusProyectCat.class).getResultList();
		
		return lista_estatus;
	}

	@Override
	public StatusProyectCat getStatusProyectCatByPK(StatusProyectCatPK estatus_PK) 
	{		
		query = "from StatusProyectCat where status_id = :statusID and company_id = :companyID";
		
		estatus = em.createQuery(query, StatusProyectCat.class)
						     .setParameter("statusID",  estatus_PK.getStatus_id())
						     .setParameter("companyID", estatus_PK.getCompany_id())
						     .getSingleResult();
		
		return estatus;
	}

	@Override
	public List<StatusProyectCat> getListStatusProyectCatByIsEnabled() 
	{	
		lista_estatus = em.createQuery("from StatusProyectCat where is_enabled = 'S' order by name ",StatusProyectCat.class).getResultList();
		
		return lista_estatus;
	}
	
	public final List<EstatusConfig> getListaEstatus_by_EstatusConfig(Integer estatus_id) 
	{											
		query = "from EstatusConfig where estatus_PK.status_id = :estatus_id";
		
		lista_estatus_config = em.createQuery(query, EstatusConfig.class).setParameter("estatus_id", estatus_id).getResultList();
		
		return lista_estatus_config;
	}
}
