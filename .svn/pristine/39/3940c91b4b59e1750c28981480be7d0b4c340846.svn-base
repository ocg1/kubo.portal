package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.repositories.InvestorDao;
import mx.com.kubo.services.InvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "investorServiceImp")
public class InvestorServiceImp
implements Serializable, InvestorService 
{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InvestorDao investorRepository;
	
	@Override
	public boolean addInvestor( Investor investor ){
		return investorRepository.addInvestor(investor);
	}
	
	@Override
	public Investor getInvestorById ( InvestorPK invPk ){
		return investorRepository.getInvestorById(invPk);
	}
	
	@Override
	public boolean updateInvestor ( Investor investor ){
		return investorRepository.updateInvestor(investor);
	}
	
	@Override
	public List<Investor> getInvestorList ( ){
		
		return investorRepository.getInvestorList();
		
	}
	
	@Override
	public List<Investor> getInvestorListByRole( Role_Searching rolesearching ){
		return investorRepository.getInvestorListByRole( rolesearching );
	}
	
	@Override
	public List<Investor> getInvestorByFiltering( String cad ){
		return investorRepository.getInvestorByFiltering( cad );
	}
	
}
