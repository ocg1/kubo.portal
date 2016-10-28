package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.TypeCat;
import mx.com.kubo.repositories.TypeDao;
import mx.com.kubo.services.TypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImp implements TypeService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private TypeDao typeRepository;
	
	@Override
	public List<TypeCat> getTypeList() {
		return typeRepository.loadTypeList();
	}
	
	@Override
	public List<TypeCat> loadTypeListBySelectable(){
		return typeRepository.loadTypeListBySelectable();
	}
	
}
