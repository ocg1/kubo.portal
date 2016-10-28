package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.EstatusConfig;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;

public interface StatusProyectCatDao 
{
	StatusProyectCat getStatusProyectCatByPK(StatusProyectCatPK statusProyectCatPK);
	
	List<StatusProyectCat> getListStatusProyectCat();
	List<StatusProyectCat> getListStatusProyectCatByIsEnabled();	
	
	List<EstatusConfig> getListaEstatus_by_EstatusConfig(Integer estatus_id);
}
