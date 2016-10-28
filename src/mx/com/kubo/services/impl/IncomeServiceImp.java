package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.repositories.IncomeDao;
import mx.com.kubo.services.IncomeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImp 
implements IncomeService 
{

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IncomeDao incomeRepository;
	
	@Override
	public Income getIncomebyID(IncomePK incomePk) 
	{		
		return incomeRepository.getIncomebyID(incomePk);
	}

	@Override
	public boolean addIncome(Income newIncome, int prospectusID, int companyID) 
	{		
		return incomeRepository.addIncome(newIncome, prospectusID, companyID);
	}

	@Override
	public boolean updateIncome(Income income) 
	{		
		return incomeRepository.updateIncome(income);
	}

	@Override
	public Income getIncomeByTypeIncomeID(int prospectusID, int companyID,int typeIncomeID) 
	{		
		return incomeRepository.getIncomeByTypeIncomeID(prospectusID, companyID, typeIncomeID);
	}

	@Override
	public List<Income> getListIncomeByProspect(int prospectusID, int companyID) 
	{	
		return incomeRepository.getListIncomeByProspect(prospectusID, companyID);
	}

	@Override
	public boolean removeIncome(IncomePK incomePk) 
	{		
		return incomeRepository.removeIncome(incomePk);
	}

}
