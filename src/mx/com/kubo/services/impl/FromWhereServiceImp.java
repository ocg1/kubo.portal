package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.FromWhereCat;
import mx.com.kubo.model.FromWhereCatPK;
import mx.com.kubo.repositories.FromWhereCatDao;
import mx.com.kubo.services.FromWhereCatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromWhereServiceImp implements FromWhereCatService {

	@Autowired
	private FromWhereCatDao fromWhereRepository;
	
	@Override
	public FromWhereCat getFromWhereByID(FromWhereCatPK pk) {
		return fromWhereRepository.getFromWhereByID(pk);
	}

	@Override
	public List<FromWhereCat> getListFromWhere() {
		return fromWhereRepository.getListFromWhere();
	}

}
