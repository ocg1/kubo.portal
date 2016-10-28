package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Marital_StatusPK;
import mx.com.kubo.repositories.MaritalStatusDao;
import mx.com.kubo.services.MaritalStatusService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaritalStatusServiceImp implements MaritalStatusService{
	
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private MaritalStatusDao maritalStatusRepository;
	@Override
	public Marital_Status getMaritalStatusById(Marital_StatusPK pk) {
		return maritalStatusRepository.loadSelectedMaritalStatus(pk);
	}
	@Override
	public void add(Marital_Status newMaritalStatus) {
		maritalStatusRepository.saveMaritalStatus(newMaritalStatus);		
	}
	@Override
	public List<Marital_Status> getMaritalStatusList() {
		return maritalStatusRepository.loadMaritalStatusList();
	}
	
	

}
