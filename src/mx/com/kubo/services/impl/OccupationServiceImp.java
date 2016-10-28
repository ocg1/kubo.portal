package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Occupation;
import mx.com.kubo.model.OccupationPK;
import mx.com.kubo.repositories.OccupationDao;
import mx.com.kubo.services.OccupationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccupationServiceImp implements OccupationService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private OccupationDao occupationRepository;
	
	@Override
	public Occupation getOccupationById(OccupationPK id){
		return occupationRepository.loadSelectedOccupation(id);
	}
	
	@Override
	public void add(Occupation newOccupation){	
		occupationRepository.saveOccupation(newOccupation);
	}
	
	@Override
	public List<Occupation> getOccupationList(){
		return occupationRepository.loadOccupationList();
	}
	
}
