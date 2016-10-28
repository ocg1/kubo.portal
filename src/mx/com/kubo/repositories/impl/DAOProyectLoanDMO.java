package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import mx.com.kubo.model.EconActivityByInv;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.SPProyectActive;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.repositories.ProyectLoanDao;

public abstract class DAOProyectLoanDMO 
implements ProyectLoanDao
{
	Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	protected String ExceptionOnFunding = new String("");
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	public List<ProyectLoan> loadProyectLoanList() 
	{
		List<ProyectLoan> proyectLoan = null;
		
		proyectLoan = em.createQuery("Select pl From ProyectLoan pl, TiendaCreditos tc where  (cast(pl.safi_mx_solicitud_id as integer) = cast(tc.safi_mx_solicitud_id as integer) ) and (( cast(tc.DisponibleFondeo as integer)  )>0) ",ProyectLoan.class).getResultList();
		
		return proyectLoan;
	}
    
	public List<ViewProyectTienda> loadProyectLoanList_TIENDA() 
	{	
		List<ViewProyectTienda> proyectLoan = null;
		
		proyectLoan = (List<ViewProyectTienda>) em.createQuery("from ViewProyectTienda ",ViewProyectTienda.class).getResultList();
		
		return proyectLoan;
	}
	
	public List<ProyectLoan> loadProyectLoanListControlTable() 
	{
		List<ProyectLoan> proyectLoan = null;
		
		proyectLoan = em.createQuery("Select pl From ProyectLoan pl order by pl.day_published desc",ProyectLoan.class).getResultList();
		
		return proyectLoan;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProyectLoan> getProyectsByFilter(String x) 
	{		
		String query = "SELECT l.* From ln_proyect_loan l , ln_proyect p, ln_term t WHERE "+x+" and l.proyect_id = p.proyect_id and l.prospectus_id = p.prospectus_id and l.company_id = p.company_id and l.term_id = t.term_id and l.company_id = t.company_id and l.status_id = 2;";
				
		List<ProyectLoan> proyectLoan = null;
		
		proyectLoan = em.createNativeQuery(query,ProyectLoan.class).getResultList();
		
		return proyectLoan;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProyectLoan> getProyectLoanByFilteringControlTable(String x) 
	{	
		List<ProyectLoan> proyectLoan = null;
		
		proyectLoan = em.createNativeQuery("SELECT l.* From ln_proyect_loan l , ln_proyect p, ln_term t WHERE "+x+" and l.proyect_id = p.proyect_id and l.prospectus_id = p.prospectus_id and l.company_id = p.company_id and l.term_id = t.term_id and l.company_id = t.company_id order by l.day_published desc;",ProyectLoan.class).getResultList();
		
		return proyectLoan;
	}
	
	public List<ProyectLoan> getProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id) 
	{
		String query = " From ProyectLoan  WHERE proyectloanPk.prospectus_id = ? and proyectloanPk.company_id = ? and status_id  = 0";
		
		List<ProyectLoan> proyectLoan = null;
		
		proyectLoan = em.createQuery(query,ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2,company_id).getResultList();
		
		return proyectLoan;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Byte> getOnlyTermId()
	{	
		try{
		
			List<Byte> onlyTerms = null;
			
			onlyTerms = (List<Byte>) em.createNativeQuery("select term_id from ln_proyect_loan where status_id = 2 group by term_id;").getResultList();
			
			return onlyTerms;
		
		}catch(Exception e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getSAFIAccount(String propectus_id)
	{
		List<String> safiAccount = null;
		
		safiAccount = (List<String>) em.createNativeQuery("Select np.safi_client_id from gn_natural_person np where np.prospectus_id = "+propectus_id+";").getResultList();
		
		return safiAccount;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProyectLoan> getListOfScoreTypeProyect(String kuboScore)
	{
		List<ProyectLoan> listOfProyects = null;
		
		listOfProyects = (List<ProyectLoan>) em.createNativeQuery("Select * from ln_proyect_loan where kubo_score_a = '"+kuboScore+"' and status_id = 2;").getResultList();
		
		return listOfProyects;
	}
	
	public List<ProyectLoan> getProyectLoansListByBurSolNum(String bursolnum)
	{
		List<ProyectLoan> listOfProyects = null;
		
		listOfProyects = (List<ProyectLoan>) em.createQuery("from ProyectLoan where mx_solicitud_buro = ?",ProyectLoan.class).setParameter(1, bursolnum).getResultList();
		
		return listOfProyects;		
	}
	
	public List<ProyectLoan> getProyectLoanListByProspectus(int prospectus_id, int company_id)
	{
		List<ProyectLoan> listOfProyects = null;	
		
		listOfProyects = (List<ProyectLoan>) em.createQuery("from ProyectLoan where proyectloanPk.prospectus_id = ? and proyectloanPk.company_id = ? order by day_published desc ",ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
		
		return listOfProyects;		
	}
		
	public List<ProyectLoan> getProyectLoanListByProspectusOrderByProyectLoan(int prospectus_id, int company_id)
	{
		List<ProyectLoan> listOfProyects = null;
		
		listOfProyects = (List<ProyectLoan>) em.createQuery("from ProyectLoan where proyectloanPk.prospectus_id = ? and proyectloanPk.company_id = ? order by proyectloanPk.proyect_loan_id desc ",ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
		
		return listOfProyects;		
	}
	
	public List<ProyectLoan> getProyectLoanListByRole( Role_Searching rolesearching )
	{
		List<ProyectLoan> listOfProyects = null;
		
		listOfProyects = (List<ProyectLoan>) em.createQuery("from ProyectLoan where status_id in ("+rolesearching.getStatus_id()+") order by "+rolesearching.getField_order()+" ",ProyectLoan.class).getResultList();
		
		return listOfProyects;
	}
	
	
	public ProyectLoan getProyectLoanListBySafiCreditID( String safi_credit_id )
	{
		try
		{		
			String query = " Select * from ln_proyect_loan where CAST(safi_credit_id AS UNSIGNED)   =  CAST('"+safi_credit_id+"' AS UNSIGNED) ";
			
			System.out.println( "**** ProyectLoanListBySafiCreditID ****" );
			System.out.println( query );
			
			ProyectLoan listOfProyects = (ProyectLoan) em.createNativeQuery( query ,ProyectLoan.class).getSingleResult();
			
			return listOfProyects;
			
		} catch(Exception e) {
						
			return null;
		}
	}
	
	public ProyectLoan getProyectLoanBySafiSolicitudID( String safi_solicitud_id )
	{		
		try
		{			
			ProyectLoan listOfProyects = (ProyectLoan) em.createQuery("from ProyectLoan where safi_mx_solicitud_id = "+safi_solicitud_id+")  ",ProyectLoan.class).getSingleResult();
			
			return listOfProyects;
			
		} catch(Exception e) {
						
			return null;			
		}	
	}
	
	public List<EconActivityByInv> getEconActivityByInvLst( String safi_client_id, String cuentaAhoID  )
	{
		
//		getEconActivityByInv
//		String query2 = "select * from view_inv_activity where prospectus_id = ?"; 
//		
//		List<EconActivityByInv> listOfEconActivityByInv = null;
//		
//		listOfEconActivityByInv = (List<EconActivityByInv>) em.createNativeQuery(query2, EconActivityByInv.class).setParameter(1, prospectus_id).getResultList();
//		
//		return listOfEconActivityByInv;		
		
		try
		{			
			return em.createNamedQuery("getEconActivityByInv",EconActivityByInv.class)
						.setParameter("safi_client_id",safi_client_id)
						.setParameter("cuentaAhoID",cuentaAhoID)
						.getResultList();
		
		} catch(Exception e) {
			
			System.out.println("Error getEconActivityByInvLst");
			
			return null;
		}
		
	}
}
