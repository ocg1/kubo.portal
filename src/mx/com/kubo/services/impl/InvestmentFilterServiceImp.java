package mx.com.kubo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.repositories.InvestmentFilterDao;
import mx.com.kubo.services.InvestmentFilterService;

@Service
public class InvestmentFilterServiceImp implements InvestmentFilterService {

	@Autowired
	private InvestmentFilterDao repository;
	
	public boolean addFilterUsed( InvestmentFilter filter ){
		return repository.addFilterUsed(filter);
	}
	
}
