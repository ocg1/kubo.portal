package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;

public interface ProyectService 
{
	boolean update(Proyect thisproyect);
	boolean add(Proyect newProyect);
	
	int getMaxProyectID();
	
	Proyect getProyectById(ProyectPK pk);
	Proyect getMaxProyect(int prospectusID, int companyID);
	Proyect getMaxProyectWithPurpose(int prospectusID, int companyID);
	
	List<Proyect> getProyectList();
	List<Proyect> loadProyectListByProspect(Prospectus prospecto);
}
