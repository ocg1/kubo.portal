package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;
import mx.com.kubo.repositories.IncomeTypeDao;
import mx.com.kubo.services.IncomeTypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("incomeTypeServiceImp")
public class IncomeTypeServiceImp 
implements IncomeTypeService
{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IncomeTypeDao incomeTypeRepository;
	
	@Override
	public IncomeType getIncomeTypeById(IncomeTypePK id) {
		return incomeTypeRepository.loadSelectedIncomeType(id);
	}
	
	@Override
	public void add(IncomeType newIncomeType) {
		incomeTypeRepository.saveIncomeType(newIncomeType);		
	}

	@Override
	public List<IncomeType> getIncomeTypeList() {
		return incomeTypeRepository.loadIncomeTypeList();
	}
	
	@Override
	public List<IncomeType> getIncomeTypeListOrderByConsec() {
		return incomeTypeRepository.getIncomeTypeListOrderByConsec();
	}
}
