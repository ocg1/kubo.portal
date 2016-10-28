package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.InvestmentProgress;

public interface InvestmentProgressService {

	public boolean saveInvestmentProgress( InvestmentProgress ip );
	public boolean updateInvestmentProgress( InvestmentProgress ip );
	public List<InvestmentProgress> getInvestmentProgressByDate( Date date );
	
}
