package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;


public interface ProyectDao 
{	
	boolean updateProyect(Proyect thisproyect);
	boolean saveProyect(Proyect newProyect);
	
	int getMaxProyectID();
	
	Proyect loadSelectedProyect(ProyectPK pk);
	Proyect getMaxProyect(int prospectusID,int companyID);
	Proyect getMaxProyectWithPurpose(int prospectusID,int companyID) ;
	
	List<Proyect> loadProyectList();	
	List<Proyect> loadProyectListByProspect(Prospectus prospecto);
}
