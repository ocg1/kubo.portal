package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.AddressType;
import mx.com.kubo.model.AddressTypePK;


public interface AddressTypeService {
	public abstract AddressType getAddressTypeById(AddressTypePK pk);
	public abstract void add(AddressType newAddressType);
	public abstract List<AddressType> getAddressTypeList();

}
