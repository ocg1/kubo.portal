package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.repositories.ResidenceDao;
import mx.com.kubo.services.ResidenceService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceServiceImp implements ResidenceService{
	
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private ResidenceDao residenceRepository;
	@Override
	public Residence getResidenceById(ResidencePK pk) {
		return residenceRepository.loadSelectedResidence(pk);
	}
	@Override
	public void add(Residence newResidence) {
		residenceRepository.saveResidence(newResidence);		
	}
	@Override
	public List<Residence> getResidenceList() {
		return residenceRepository.loadResidenceList();
	}
	
	

}
