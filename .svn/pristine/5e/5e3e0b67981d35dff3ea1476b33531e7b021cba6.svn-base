package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.Offer;
import mx.com.kubo.repositories.OfferDao;
import mx.com.kubo.services.OfferService;

@Service
public class OfferServiceImp implements Serializable, OfferService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OfferDao dao;
	
	public Offer getOfferByBurSolNum( String bursolnum ){
		return dao.getOfferByBurSolNum(bursolnum);
	}
	
	public boolean updateOffer( Offer offer ){
		return dao.updateOffer(offer);
	}
	
	public boolean saveOffer( Offer offer ){
		return dao.saveOffer(offer);
	}
	
}
