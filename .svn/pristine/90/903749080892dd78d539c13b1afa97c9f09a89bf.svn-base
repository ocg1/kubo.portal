package mx.com.kubo.services;

import java.math.BigDecimal;
import java.util.List;

import mx.com.kubo.bean.ObjTmp;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ViewInvestmetInProyect;


public interface ProyectFundingService {
	public abstract ProyectFunding getProyectFundingById(ProyectFundingPK pk);
	public abstract void add(ProyectFunding newProyectFunding);
	public abstract List<ProyectFunding> getProyectFundingList();
	public abstract ProyectFunding findProyect(ProyectFundingPK key);
	public abstract boolean update(ProyectFunding otro);
	public abstract List<ProyectFunding> getproyectbyInvestor(String prospectusInvestorId, String proyectLoanId);
	public abstract List<ProyectLoan> getProyectsOfInvestorById(String id);
	public List<ViewInvestmetInProyect> getListInvestorbyProyectId( Integer credito_id, Integer solicitud_id );
	public List<BigDecimal> getIFAmountFunding(ProyectLoanPK key);
	public List<ProyectFunding> getMaxProyectFundingByInvOnProyect(ProyectLoanPK key, Integer InvID);
	public List<BigDecimal> getAmountFundedByProyectLoanPK(ProyectLoanPK key);
	public List<ProyectFunding> getListProyectFunByInvestor( int prospectus_id , int company_id );
	public List<ObjTmp> getListInvestors ();
}

//=======
//	public List<Membership> getListInvestorbyProyectId(int proyectId, int companyId);
//}>>>>>>> .r1358
