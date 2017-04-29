package mx.com.kubo.repositories;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFrequency;

public interface AutomaticInvestmentDao 
{
	boolean saveAutomaticInvestment( AutomaticInvestment ai );
	
	boolean updateAutomaticInvestment( AutomaticInvestment ai );
	
	List<AutomaticInvestment> getAutomaticInvestmentList( Date fecha );
	
	boolean esDiaFeriado( Date fecha );
	
	List<AutomaticInvestment> getAutomaticInvestmentListByProspect( Integer prospectus_id );
	
	List<InvestmentFrequency> getInvestmentFrequencyLst();
	
	AutomaticInvestment getAutomaticInvestment(int automatic_investment_id);
}
