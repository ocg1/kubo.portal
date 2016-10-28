package mx.com.kubo.services.impl;

import mx.com.kubo.model.TempPassword;
import mx.com.kubo.model.TempPasswordPK;
import mx.com.kubo.repositories.TempPasswordDao;
import mx.com.kubo.services.TempPasswordService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempPasswordServiceImp implements TempPasswordService {
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	TempPasswordDao tempPassRepository;
	
	@Override
	public TempPassword getTempPassByID(TempPasswordPK tempPK) {
		return tempPassRepository.getTempPassByID(tempPK);
	}

	@Override
	public boolean addNewTempPass(TempPassword newTempPass) {		
		return tempPassRepository.addNewTempPass(newTempPass);
	}

	@Override
	public boolean updateTempPass(TempPassword tempass) {
		return tempPassRepository.updateTempPass(tempass);
	}

	@Override
	public TempPassword getTempPassByPass(String pass) {
		return tempPassRepository.getTempPassByPass(pass);
	}

}
