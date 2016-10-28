package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestmentParamCat;
import mx.com.kubo.repositories.InvestmentParamCatDao;
import mx.com.kubo.services.InvestmentParamCatService;

@Service
public class InvestmentParamCatServiceImp implements Serializable, InvestmentParamCatService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InvestmentParamCatDao dao;
	
	public List<InvestmentParamCat> getInvestmentParamCat(){
		
		return dao.getInvestmentParamCat();
		
	}
	
}
