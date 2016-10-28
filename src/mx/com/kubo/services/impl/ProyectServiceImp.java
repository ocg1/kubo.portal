package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.repositories.ProyectDao;
import mx.com.kubo.services.ProyectService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "proyectServiceImp")
public class ProyectServiceImp 
implements ProyectService
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ProyectDao proyectRepository;

	@Override
	public Proyect getProyectById(ProyectPK pk) {
		return proyectRepository.loadSelectedProyect(pk);
	}
	
	@Override
	public boolean update(Proyect thisproyect) {		
		return proyectRepository.updateProyect(thisproyect);
	}
	

	@Override
	public boolean add(Proyect newProyect) {
		return  proyectRepository.saveProyect(newProyect);
	}

	@Override
	public List<Proyect> getProyectList() 
	{
		return proyectRepository.loadProyectList();
	}
	
	@Override
	public List<Proyect> loadProyectListByProspect(Prospectus prospecto)
	{
		return proyectRepository.loadProyectListByProspect(prospecto);
	}

	@Override
	public int getMaxProyectID() {
		return proyectRepository.getMaxProyectID();
	}

	@Override
	public Proyect getMaxProyect(int prospectusID, int companyID) {		
		return proyectRepository.getMaxProyect(prospectusID, companyID);
	}
	
	@Override
	public Proyect getMaxProyectWithPurpose(int prospectusID, int companyID) {		
		return proyectRepository.getMaxProyectWithPurpose(prospectusID, companyID);
	}
	
	

}
