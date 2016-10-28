package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.repositories.FrequencyDao;
import mx.com.kubo.services.FrequencyService;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrequencyServiceImp implements FrequencyService,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private FrequencyDao frequencyRepository;

	@Override
	public Frequency getFrequencyById(int id) {
		return frequencyRepository.loadSelectedFrequency(id);
	}

	@Override
	public void add(Frequency newFrequency) {
		frequencyRepository.saveFrequency(newFrequency);		
	}

	@Override
	public List<Frequency> getFrequencyList() {
		return frequencyRepository.loadFrequencyList();
	}
	
	

}
