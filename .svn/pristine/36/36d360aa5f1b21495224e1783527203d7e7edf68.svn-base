package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.AddressType;
import mx.com.kubo.model.AddressTypePK;
import mx.com.kubo.repositories.AddressTypeDao;
import mx.com.kubo.services.AddressTypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressTypeServiceImp implements AddressTypeService{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private AddressTypeDao addresstypeRepository;

	@Override
	public AddressType getAddressTypeById(AddressTypePK pk) {
		return addresstypeRepository.loadSelectedAddressType(pk);
	}

	@Override
	public void add(AddressType newAddressType) {
		addresstypeRepository.saveAddressType(newAddressType);		
	}

	@Override
	public List<AddressType> getAddressTypeList() {
		return addresstypeRepository.loadAddressTypeList();
	}
	
	

}
