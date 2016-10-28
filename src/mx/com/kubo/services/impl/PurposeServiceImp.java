package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.repositories.PurposeDao;
import mx.com.kubo.services.PurposeService;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurposeServiceImp implements PurposeService{

//	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private PurposeDao purposeRepository;
	
	@Override
	public Purpose getPurposeById(PurposePK pk) {
		return purposeRepository.loadSelectedPurpose(pk);
	}

	
	@Override
	public void add(Purpose newPurpose) {
		purposeRepository.savePurpose(newPurpose);		
	}
	
	@Override
	public List<Purpose> getPurposeList() {
		return purposeRepository.loadPurposeList() ;
	}
	
	@Override
	public List<Purpose> loadPurposeListByType(Integer type_id){
		return purposeRepository.loadPurposeListByType(type_id);
	}
	

}
