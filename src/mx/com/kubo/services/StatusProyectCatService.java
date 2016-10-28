package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;

public interface StatusProyectCatService 
{
	StatusProyectCat getStatusProyectCatByPK(StatusProyectCatPK statusProyectCatPK);
	
	List<StatusProyectCat> getListStatusProyectCat();
	List<StatusProyectCat> getListStatusProyectCatByIsEnabled();
	List<StatusProyectCat> getListaEstatus_by_EstatusConfig(Integer estatus_id);
}
