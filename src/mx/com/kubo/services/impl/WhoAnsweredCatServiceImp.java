package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.WhoAnsweredCat;
import mx.com.kubo.model.WhoAnsweredCatPK;
import mx.com.kubo.repositories.WhoAnsweredCatDao;
import mx.com.kubo.services.WhoAnsweredCatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WhoAnsweredCatServiceImp implements WhoAnsweredCatService {

	@Autowired
	private WhoAnsweredCatDao whoAnsweredRepo;
	
	@Override
	public WhoAnsweredCat getAnsweredByID(WhoAnsweredCatPK pk) {
		return whoAnsweredRepo.getAnsweredByID(pk);
	}

	@Override
	public List<WhoAnsweredCat> getListWhoAnswered() {
		return whoAnsweredRepo.getListWhoAnswered();
	}

}
