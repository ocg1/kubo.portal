package mx.com.kubo.services.impl;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.EstatusConfig;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.repositories.StatusProyectCatDao;
import mx.com.kubo.services.StatusProyectCatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "statusProyectCatServiceImp")
public class StatusProyectCatServiceImp 
implements StatusProyectCatService
{
	@Autowired
	private StatusProyectCatDao dao;
	
	public List<StatusProyectCat> getListStatusProyectCat() 
	{		
		return dao.getListStatusProyectCat();
	}
	
	public StatusProyectCat getStatusProyectCatByPK(StatusProyectCatPK pk) 
	{
		return dao.getStatusProyectCatByPK(pk);
	}

	public List<StatusProyectCat> getListStatusProyectCatByIsEnabled() 
	{		
		return dao.getListStatusProyectCatByIsEnabled();
	}
	
	public final List<StatusProyectCat> getListaEstatus_by_EstatusConfig(Integer estatus_id)
	{
		List<StatusProyectCat> lista_estatus;
		List<EstatusConfig>    lista_estatus_config;
		
		lista_estatus_config = dao.getListaEstatus_by_EstatusConfig(estatus_id);
		
		lista_estatus = new ArrayList<StatusProyectCat>();
		
		for(EstatusConfig estatus_config: lista_estatus_config)
		{
			lista_estatus.add(estatus_config.getEstatus_to());
		}
		
		return lista_estatus;
	}
}
