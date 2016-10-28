package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.OfferRejectionMotive;
import mx.com.kubo.repositories.OfferRejectionMotiveDao;
import mx.com.kubo.services.OfferRejectionMotiveService;

@Service
public class OfferRejectionMotiveServiceImp implements Serializable,OfferRejectionMotiveService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OfferRejectionMotiveDao dao;
	
	public List<OfferRejectionMotive> getOfferRejectionMotiveList(){
		return dao.getOfferRejectionMotiveList();
	}
	
}
