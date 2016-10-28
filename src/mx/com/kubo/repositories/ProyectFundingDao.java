package mx.com.kubo.repositories;

import java.math.BigDecimal;
import java.util.List;

import mx.com.kubo.bean.ObjTmp;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ViewInvestmetInProyect;

public interface ProyectFundingDao 
{	
	ProyectFunding loadSelectedProyectFunding(ProyectFundingPK pk);
	void saveProyectFunding(ProyectFunding newProyectLoan);
	List<ProyectFunding> loadProyectFundingList();
	boolean update(ProyectFunding proyect);
	List<ProyectFunding> getQuery();
	List<ProyectFunding> getProyectByInvestor(String prospectusInvestorId, String proyectLoanId);
	List<ProyectLoan> getProyectOfInvestor(String invId);
	List<BigDecimal> getInstitutionalAmountFunded(ProyectLoanPK key);
	public List<ViewInvestmetInProyect> getListInvestorbyProyectId( Integer credito_id, Integer solicitud_id );
	List<ProyectFunding> getAmmountFundedOnProyectByInvestor(ProyectLoanPK key, Integer InvID );
	List<BigDecimal> getAmountFundedByProyectLoanPK(ProyectLoanPK key);
	List<ProyectFunding> getListProyectFunByInvestor( int prospectus_id , int company_id );
	public List<ObjTmp> getListInvestors ();
}