package mx.com.kubo.services.impl;

import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.model.ApprovalCredit;
import mx.com.kubo.model.EconActivityByInv;
import mx.com.kubo.model.InfoCalifPorc;
import mx.com.kubo.model.InvestmentsAtrasadasEdoCta;
import mx.com.kubo.model.InvestmentDetail;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectSaldosEdoCta;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.SPProyectActive;
import mx.com.kubo.model.SafiProyecInProcess;
import mx.com.kubo.model.ViewForTiendaExec;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.repositories.ProyectLoanDao;
import mx.com.kubo.services.ProyectLoanService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "proyectLoanServiceImp")
public class ProyectLoanServiceImp 
implements ProyectLoanService 
{	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ProyectLoanDao dao;

	public ProyectLoan getProyectLoanById(ProyectLoanPK pk) 
	{
		return dao.loadSelectedProyectLoan(pk);
	}

	public void add(ProyectLoan newProyectLoan
			) 
	{
		dao.saveProyectLoan(newProyectLoan);
	}

	public List<ProyectLoan> getProyectLoanList() 
	{
		return dao.loadProyectLoanList();
	}
	
	public List<ProyectLoan> getProyectLoanListControlTable() 
	{
		return dao.loadProyectLoanListControlTable();
	}
	
	public ProyectLoan findProyect(ProyectLoanPK key)
	{
		return dao.loadSelectedProyectLoan(key);
	}

	public boolean update(ProyectLoan otro)
	{
		return dao.update(otro);
	}
	
	public int spSetOnProyectFunding(Integer par_proyect_loan_id,Integer par_proyect_id,Integer par_company_id, Integer par_prospectus_id,Integer par_prospectus_investor_id,Double par_amount,String solicitudFondeo)
	{ 
		return dao.spSetOnProyectFunding(par_proyect_loan_id, par_proyect_id, par_company_id, par_prospectus_id, par_prospectus_investor_id, par_amount,solicitudFondeo);
	}
	
	public List<ProyectLoan> getProyectLoanByFiltering(String x)
	{
		return dao.getProyectsByFilter(x);
	}
	
	public List<ProyectLoan> getProyectLoanByFilteringControlTable(String x)
	{
		return dao.getProyectLoanByFilteringControlTable(x);
	}
	
	public ProyectLoan getMaxProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id)
	{
		return dao.getMaxProyectLoanByProspectDontPublish(prospectus_id, company_id);
	}
	
	public ProyectLoan getMaxProyectLoanByProspectInReview(Integer prospectus_id, Integer company_id)
	{
		return dao.getMaxProyectLoanByProspectInReview(prospectus_id, company_id);
	}
	
	public ProyectLoan getMaxProyectLoanByProspect(Integer prospectus_id, Integer company_id)
	{
		return dao.getMaxProyectLoanByProspect(prospectus_id, company_id);
	}
	
	public ProyectLoan getMaxProyectLoanByProspectInFunding(Integer prospectus_id, Integer company_id)
	{
		return dao.getMaxProyectLoanByProspectInFunding(prospectus_id, company_id);
	}
	
	public ProyectLoan getMaxProyectLoanByProspectAndStatus(Integer prospectus_id, Integer company_id, Integer status_id) {
		return dao.getMaxProyectLoanByProspectAndStatus( prospectus_id,  company_id,  status_id) ;
	}
	
	public List<Byte> getOnlyTermID()
	{
		return dao.getOnlyTermId();
	}
	
	public List<String> getOnlySAFIAccount(String propectus_id)
	{
		return dao.getSAFIAccount(propectus_id);
	}
	
	public String setKuboInstitutionalFunding(ProyectLoanPK key)
	{
		return dao.setInstitutionalFunding(key);
	}
	
	public List<ProyectLoan> getListOfOneType(String kuboScore)
	{
		return dao.getListOfScoreTypeProyect(kuboScore);
	}

	public List<ProyectLoan> getProyectLoansListByBurSolNum(String bursolnum)
	{
		return dao.getProyectLoansListByBurSolNum( bursolnum);
	}
	
	public List<ProyectLoan> getProyectLoanListByProspectus(int prospectus_id, int company_id)
	{	
		return dao.getProyectLoanListByProspectus(prospectus_id, company_id);	
	}
	
	public List<ProyectLoan> getProyectLoanListByProspectusOrderByProyectLoan(int prospectus_id, int company_id)
	{	
		return dao.getProyectLoanListByProspectus(prospectus_id, company_id);	
	}
	
	public List<ProyectLoan> getProyectLoanListByRole( Role_Searching rolesearching )
	{	
		return dao.getProyectLoanListByRole(rolesearching);	
	}
	
	public List<ProyectLoan> getProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id)
	{
		return dao.getProyectLoanByProspectDontPublish( prospectus_id,  company_id);
	}
		
	//public List<ViewProyectTienda> getProyectLoanByFilteringInv(String x, int typeSearch, int prospectus_id, int company_id ) 
	public List<ViewForTiendaExec> getProyectLoanByFilteringInv(FilterStore filter ) 
	{	
		return dao.getProyectsByFilterInv(  filter );
	}

	public ProyectLoan getProyectLoanByProyectLoanID(Integer proyectLoanID, Integer prospectusID, Integer companyID) 
	{
		return dao.getProyectLoanByProyectLoanID(proyectLoanID, prospectusID, companyID);
	}

	public ProyectLoan getProyectLoanListBySafiCreditID( String safi_credit_id )
	{
		return dao.getProyectLoanListBySafiCreditID(safi_credit_id);
	}
	
	public List<SafiProyecInProcess> getProyectLoanInProcessBySafi(String cuenta)
	{
		return dao.getProyectLoanInProcessBySafi(cuenta);
	}
	
	public List<ProyectLoanActiveInSafi> getProyectLoanActiveInSafiByAccount(String account, String status,char status_delinquentinv)
	{
		return dao.getProyectLoanActiveInSafiByAccount( account, status,status_delinquentinv);	
	}
	
	public List<ProyectLoan> getProyectLoanListTiendaNotInFunding()
	{
		return dao.getProyectLoanListTiendaNotInFunding();
	}
		
	public ProyectLoanActiveInSafi getProyectLoanActiveInSafiByID(int kuboFondeoInv_id,int proyect_loan_id)
	{
		return dao.getProyectLoanActiveInSafiByID(kuboFondeoInv_id,proyect_loan_id);		
	}
	
	public List<EconActivityByInv> getEconActivityByInvLst( String safi_client_id, String cuentaAhoID )
	{	
		return dao.getEconActivityByInvLst( safi_client_id,  cuentaAhoID);	
	}
	
	public boolean cambioStatus( ProyectLoan proyect_loan, Integer new_status, Date new_date )
	{
		return dao.cambioStatus(proyect_loan, new_status, new_date);
	}
	
	public Date getLastPublishProyectDate( ProyectLoan proyect_loan )
	{
		return dao.getLastPublishProyectDate( proyect_loan );
	}
	
	public Date getDateFirstCreditFromSAFI( ProyectLoan proyect_loan )
	{
		return dao. getDateFirstCreditFromSAFI( proyect_loan );
	}
	
	public SPProyectActive getResumenCreditActive( String cuentaAhoId )
	{
		return dao.getResumenCreditActive( cuentaAhoId );
	}
	
	public ProyectLoan getProyectLoanBySafiSolicitudID( String safi_solicitud_id )
	{
		return dao.getProyectLoanBySafiSolicitudID( safi_solicitud_id );
	}
	
	public List<ViewProyectTienda> loadProyectLoanList_TIENDA() 
	{
		return dao.loadProyectLoanList_TIENDA();
	}
	
	public ProyectSaldosEdoCta	getSaldosEdoCtaClient(String cuentaAhoID)
	{
		return dao.getSaldosEdoCtaClient(cuentaAhoID);
	}

	public InvestmentsAtrasadasEdoCta getInvestmentsAtraEdoCta(String cuentaAhoID)
	{
		return dao.getInvestmentsAtraEdoCta(cuentaAhoID);
	}
	
	public List<LoanType> getLista_loan_type() 
	{		
		return dao.getLista_loan_type();
	}
	
	public InvestmentDetail getInvestmentDetailByID( int kuboFondeoInv_id)
	{
		return dao.getInvestmentDetailByID( kuboFondeoInv_id );
	}
	
	public boolean ejecutaSPDeleteCreditsByClient(String safi_client_id){
		return dao.ejecutaSPDeleteCreditsByClient(safi_client_id);
	}
	
	public List<ApprovalCredit> getApprovalUsers( String safi_credit_id){
		
		return dao.getApprovalUsers( safi_credit_id);
		
	}
	
	public List<InfoCalifPorc> getInfoCalifPorc( String safi_client_id, String cuentaAhoID ){
		return dao.getInfoCalifPorc(safi_client_id, cuentaAhoID);
	}
	
}