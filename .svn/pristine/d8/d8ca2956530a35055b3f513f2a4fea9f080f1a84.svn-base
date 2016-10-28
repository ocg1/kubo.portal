package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Role_Searching;

public interface InvestorDao {

	public boolean addInvestor( Investor investor );
	public Investor getInvestorById ( InvestorPK invPk );
	public boolean updateInvestor ( Investor investor );
	public List<Investor> getInvestorList ( );
	public List<Investor> getInvestorListByRole( Role_Searching rolesearching );
	public List<Investor> getInvestorByFiltering( String cad );
	
}
