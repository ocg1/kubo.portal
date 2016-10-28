package mx.com.kubo.services.impl;

import mx.com.kubo.model.ComparisonMod;
import mx.com.kubo.repositories.ComparisonDao;
import mx.com.kubo.services.ComparisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComparisonServiceImp implements ComparisonService{
	
	@Autowired
	private ComparisonDao comparisonDao;

	@Override
	public boolean add(ComparisonMod newComparison) {
		// TODO Auto-generated method stub
		return comparisonDao.add(newComparison);
	}

}
