package mx.com.kubo.services.impl;

import mx.com.kubo.model.SellingType;
import mx.com.kubo.model.SellingTypePK;
import mx.com.kubo.repositories.SellingTypeDao;
import mx.com.kubo.services.SellingTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellingTypeServiceImp implements SellingTypeService {

	@Autowired
	private SellingTypeDao sellingtyperepository;
	
	@Override
	public SellingType getSellingTypeByPK(SellingTypePK pk){
		return sellingtyperepository.getSellingTypeByPK(pk);
	}
	
}
