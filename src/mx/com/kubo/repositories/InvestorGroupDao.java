package mx.com.kubo.repositories;


import mx.com.kubo.model.InvestorGroup;

public interface InvestorGroupDao {

	public boolean saveInvestorGroup( InvestorGroup group );
	public boolean updateInvestorGroup( InvestorGroup group );
	
}
