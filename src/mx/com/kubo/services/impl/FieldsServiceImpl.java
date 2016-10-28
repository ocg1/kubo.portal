package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Fields;
import mx.com.kubo.model.FieldsPK;
import mx.com.kubo.repositories.FieldsDao;
import mx.com.kubo.services.FieldsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldsServiceImpl implements FieldsService {

	@Autowired
	private FieldsDao fieldsDaoRepo;
	
	@Override
	public Fields getFieldsById(FieldsPK fieldPk) {
		return fieldsDaoRepo.getFieldsById(fieldPk);
	}

	@Override
	public List<Fields> getListByScreenId(Integer idScreen,Integer company_id) {
		return fieldsDaoRepo.getListByScreenId(idScreen,company_id);
	}

}
