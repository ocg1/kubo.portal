package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Tutor;
import mx.com.kubo.repositories.TutorDao;
import mx.com.kubo.services.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorServiceImp implements Serializable, TutorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TutorDao tutorrepository;
	
	@Override
	public boolean addTutor( Tutor tutor ){
		return tutorrepository.addTutor(tutor);
	}
	
	@Override
	public List<Tutor> getTutorByProspectus( Integer prospectus_id, Integer company_id ){
		return tutorrepository.getTutorByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public List<Tutor> getProspectusFromTutor( Integer prospectus_id, Integer company_id ){
		return tutorrepository.getProspectusFromTutor(prospectus_id, company_id);
	}
	
	
	
}
