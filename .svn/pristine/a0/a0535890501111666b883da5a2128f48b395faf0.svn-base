package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.OfferValues;
import mx.com.kubo.repositories.OfferValuesDao;
import mx.com.kubo.services.OfferValuesService;

@Service
public class OfferValuesServiceImp implements Serializable, OfferValuesService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Autowired
	private OfferValuesDao dao;
	
	public boolean deleteOfferValueByOfferId( Integer offer_id ){
		return dao.deleteOfferValueByOfferId(offer_id);
	}
	
	public boolean deleteOfferValue( OfferValues value ){
		return dao.deleteOfferValue(value);
	}
	
	public boolean saveOfferValues( List<OfferValues> values ){
		return dao.saveOfferValues(values);
	}
	
	public boolean saveOfferValue( OfferValues value ){
		return dao.saveOfferValue(value);
	}
	
	public  List<OfferValues> getOfferValuesByOfferId( Integer offer_id ){
		return dao.getOfferValuesByOfferId(offer_id);
	}

}
