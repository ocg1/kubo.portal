package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.repositories.ScreenDao;
import mx.com.kubo.services.ScreenService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenServiceImp implements ScreenService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ScreenDao screenRepository;

	@Override
	public Screen getScreenById(ScreenPK pk) {
		return screenRepository.loadSelectedScreen(pk);
	}
	
	@Override
	public List<Screen> getLstScreenByArea(int company_id,String area){
		return screenRepository.getLstScreenByArea( company_id, area);
	}

}
