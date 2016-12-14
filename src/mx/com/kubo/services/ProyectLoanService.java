package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.model.ApprovalCredit;
import mx.com.kubo.model.EconActivityByInv;
// import mx.com.kubo.model.InvestmentsAtrasadasEdoCta;
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
import mx.com.kubo.model.InfoCalifPorc;


public interface ProyectLoanService 
{
	List<ProyectLoan> getProyectLoanList();
	List<ProyectLoan> getProyectLoanListControlTable();
	List<ProyectLoan> getProyectLoanByFiltering            (String x);
	List<ProyectLoan> getProyectLoanByFilteringControlTable(String x);
	List<ProyectLoan> getListOfOneType(String kuboScore);	
	List<ProyectLoan> getProyectLoansListByBurSolNum(String bursolnum);
	List<ProyectLoan> getProyectLoanListByProspectus                  (int prospectus_id, int company_id);
	List<ProyectLoan> getProyectLoanListByProspectusOrderByProyectLoan(int prospectus_id, int company_id);	
	List<ProyectLoan> getProyectLoanListByRole( Role_Searching rolesearching );	
	List<ProyectLoan> getProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id);		
	List<ProyectLoan> getProyectLoanListTiendaNotInFunding();
	
	List<LoanType> getLista_loan_type();
	List<ViewForTiendaExec>       getProyectLoanByFilteringInv(FilterStore filter );
	List<SafiProyecInProcess>     getProyectLoanInProcessBySafi(String cuenta);
	List<EconActivityByInv>       getEconActivityByInvLst( String safi_client_id, String cuentaAhoID );
	List<ProyectLoanActiveInSafi> getProyectLoanActiveInSafiByAccount(String account,String status, char status_delinquentinv);	
	
	List<Byte>   getOnlyTermID();
	List<String> getOnlySAFIAccount(String propectus_id);
	
	ProyectLoan getMaxProyectLoanByProspectInReview   (Integer prospectus_id, Integer company_id);
	ProyectLoan getMaxProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id) ;
	ProyectLoan getMaxProyectLoanByProspect           (Integer prospectus_id, Integer company_id);
	ProyectLoan getMaxProyectLoanByProspectInFunding  (Integer prospectus_id, Integer company_id);
	ProyectLoan getMaxProyectLoanByProspectAndStatus(Integer prospectus_id, Integer company_id, Integer status_id) ;

	ProyectLoan getProyectLoanByProyectLoanID(Integer proyectLoanID, Integer prospectusID, Integer companyID);	
	ProyectLoan getProyectLoanListBySafiCreditID(String safi_credit_id);
	ProyectLoan getProyectLoanBySafiSolicitudID (String safi_solicitud_id);
	ProyectLoan getProyectLoanById(ProyectLoanPK pk);	
	
	Date getLastPublishProyectDate (ProyectLoan proyect_loan);
	Date getDateFirstCreditFromSAFI(ProyectLoan proyect_loan);
	
	SPProyectActive getResumenCreditActive(String cuentaAhoId);
	
	ProyectSaldosEdoCta getSaldosEdoCtaClient(String cuentaAhoID);
	
	// InvestmentsAtrasadasEdoCta getInvestmentsAtraEdoCta(String cuentaAhoID);
	
	List<ViewProyectTienda> loadProyectLoanList_TIENDA() ;
	
	ProyectLoan findProyect(ProyectLoanPK key);
	
	ProyectLoanActiveInSafi getProyectLoanActiveInSafiByID(int kuboFondeoInv_id,int proyect_loan_id);
	
	String setKuboInstitutionalFunding(ProyectLoanPK key);
	
	int spSetOnProyectFunding(Integer par_proyect_loan_id, Integer par_proyect_id,Integer par_company_id, Integer par_prospectus_id, Integer par_prospectus_investor_id, Double par_amount,String solicitudFondeo);
	
	boolean update(ProyectLoan otro);			
	boolean cambioStatus(ProyectLoan proyect_loan, Integer new_status, Date new_date );
	
	void add(ProyectLoan newProyectLoan);	
	
	InvestmentDetail getInvestmentDetailByID( int kuboFondeoInv_id);
	
	public boolean ejecutaSPDeleteCreditsByClient(String safi_client_id);
	
	public List<ApprovalCredit> getApprovalUsers( String safi_credit_id );
	
	public List<InfoCalifPorc> getInfoCalifPorc( String safi_client_id, String cuentaAhoID );
}