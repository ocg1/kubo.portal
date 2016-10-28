package mx.com.kubo.repositories.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.bean.ObjTmp;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.repositories.ProyectFundingDao;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProyectFundingDaoImp implements ProyectFundingDao{
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public ProyectFunding loadSelectedProyectFunding(ProyectFundingPK pk) {
		return em.find(ProyectFunding.class, pk);
	}
	
	@Transactional
	@Override
	public void saveProyectFunding(ProyectFunding newProyectFunding) {
		
		log.info("saveProyectFunding.ProyectFundingDaoImp");
		String query="select MAX(pl.proyectfundongPk.proyect_loan_id) from ProyectFunding pl";
		int idproyectFunding=0;
		
		if(em.createQuery(query).getSingleResult() !=null){
			idproyectFunding=(Integer) em.createQuery(query).getSingleResult();
			idproyectFunding++;
		}
		else{
			idproyectFunding++;
			}
		ProyectFundingPK proyectFundingpk=new ProyectFundingPK(idproyectFunding,1,1,1,1);
		newProyectFunding.setProyectloanfundingPk(proyectFundingpk);
		em.persist(newProyectFunding);
	}
	
	@Override
	public List<ProyectFunding> loadProyectFundingList() {
		List<ProyectFunding> proyectFunding=em.createQuery("Select pl From ProyectFunding pl",ProyectFunding.class).getResultList();
		return proyectFunding;
	}
	
	
	@Transactional
	@Override
	public boolean update(ProyectFunding proyect){
		try{
			em.merge(proyect);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<ProyectFunding> getQuery(){
		List<ProyectFunding> proyectFundingQ=em.createQuery("select * from ln_proyect_loan ln left join ln_proyect_funding pf on (ln.proyect_loan_id = pf.proyect_loan_id and ln.proyect_id = pf.proyect_id and ln.prospectus_id = pf.prospectus_id and ln.company_id = pf.company_id and pf.prospectus_investor_id = 6 and pf.operation_number = ( select max(pf.operation_number) from ln_proyect_funding pf where pf.prospectus_investor_id = 6 and pf.proyect_id = ln.proyect_id and pf.proyect_loan_id = ln.proyect_loan_id and pf.prospectus_id = ln.prospectus_id and pf.company_id = ln.company_id));",ProyectFunding.class).getResultList();
		return proyectFundingQ;
	}
	
	@Override
	public List<ProyectFunding> getProyectByInvestor(String prospectusInvestorId, String proyectLoanId){
		List<ProyectFunding> proyectFundingQ=em.createQuery("from ProyectFunding where proyectloanfundingPk.prospectus_investor_id = "+prospectusInvestorId +" and proyectloanfundingPk.proyect_loan_id = "+proyectLoanId,ProyectFunding.class).getResultList();
		return proyectFundingQ;
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<ProyectLoan> getProyectOfInvestor(String invId){
		List<ProyectLoan> proyectFundingQ = (List<ProyectLoan>) em.createNativeQuery("Select pf.amount, sr.rate_investor , te.months, pl.frequency_id From ln_proyect_funding pf, ln_proyect_loan pl, ln_term te, gn_scoring_result sr where pf.prospectus_investor_id = "+invId+" and pf.prospectus_id = pl.prospectus_id and pf.proyect_loan_id = pl.proyect_loan_id and pf.company_id = pl.company_id and pf.proyect_id = pl.proyect_id and te.term_id = pl.term_id and te.company_id = pl.company_id and pl.company_id = sr.company_id and pl.prospectus_id = sr.prospectus_id and pl.status_id = 2;").getResultList();
		return proyectFundingQ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> getInstitutionalAmountFunded(ProyectLoanPK key){
		Integer proyectLoanID = key.getProyect_loan_id();
		Integer prospectusID = key.getProspectus_id();
		Integer companyID = key.getCompany_id();
		Integer proyectID = key.getProyect_id();
		List<BigDecimal> list = (List<BigDecimal>) em.createNativeQuery("Select amount from ln_proyect_funding where proyect_loan_id = "+proyectLoanID.toString()+" and proyect_id = "+proyectID.toString()+" and prospectus_id = "+prospectusID.toString()+" and company_id = "+companyID.toString()+" and prospectus_investor_id = 0;").getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> getAmountFundedByProyectLoanPK(ProyectLoanPK key){
		
		Integer proyectLoanID	= key.getProyect_loan_id();
		
		Integer proyectID 		= key.getProyect_id();
		
		List<BigDecimal> list 	= (List<BigDecimal>) em.createNativeQuery("Select amount from ln_proyect_funding where proyect_loan_id = "+proyectLoanID.toString()+" and proyect_id = "+proyectID.toString()).getResultList();
		
		return list;
		
	}

	
	
	@Override
	public List<ViewInvestmetInProyect> getListInvestorbyProyectId( Integer credito_id, Integer solicitud_id ){
//		
//		String queryInvestorbyProyectId = "SELECT * FROM ln_membership WHERE prospectus_id in (SELECT prospectus_investor_id FROM ln_proyect_funding WHERE proyect_loan_id="+proyect_loan_Id+" and company_id="+companyId+")";
//

//		String queryInvestorbyProyectId = "from ProyectFunding WHERE proyectloanfundingPk.proyect_loan_id  = ? and proyectloanfundingPk.company_id=?";
//		
//		List<ProyectFunding> proyectFundingQ = em.createQuery(queryInvestorbyProyectId, ProyectFunding.class)
//															.setParameter(1, proyect_loan_Id)
//															.setParameter(2, companyId)
//															.getResultList();
		
		
		List<ViewInvestmetInProyect> proyectFundingQ = null;
		
		try{
			
			if(credito_id != null){
			
				String queryInvestorbyProyectId = ""
						+ ""
						+ "select F.CreditoID credito_id, " + 
						" F.ClienteID investor, " + 
						" 0 solicitud_id, " + 
						" sum(F.MontoFondeo) amount, " + 
						" if(((to_days(`C`.`FechaVencimien`) - to_days((select " +  
									" `microfin`.`PARAMETROSSIS`.`FechaSistema` " + 
										" from " + 
										" `microfin`.`PARAMETROSSIS`))) < 0), " + 
										" 0, " + 
										" (to_days(`C`.`FechaVencimien`) - to_days((select " +  
										"     `microfin`.`PARAMETROSSIS`.`FechaSistema` " + 
											" from " + 
											" `microfin`.`PARAMETROSSIS`)))) DiasPorTrans, " + 
						" ifnull(DisponibleFondeo,0) DisponibleFondeo, " + 
						" C.Estatus Estatus, " + 
						" PorcentajeFondeo PorcentajeFondeo " + 
						" from microfin.FONDEOKUBO F, microfin.CREDITOS C left join ln_mx_tiendacreditos ln on (ln.safi_credit_id = C.CreditoID) " + 
						" WHERE F.CreditoID = ? " + 
						" and F.CreditoID = C.CreditoID " + 
						" group by F.ClienteID , F.CreditoID"  ;
				
				
				
				
				
				proyectFundingQ = em.createNativeQuery(queryInvestorbyProyectId, ViewInvestmetInProyect.class)
																.setParameter(1, credito_id)
																.getResultList();
			
			}else if(solicitud_id != null){
				
				String queryInvestorbyProyectId = "from ViewInvestmetInProyect WHERE proyectloanfundingPk.solicitud_id = ? ";
				
				proyectFundingQ = em.createQuery(queryInvestorbyProyectId, ViewInvestmetInProyect.class)
																	.setParameter(1, solicitud_id)
																	.getResultList();
				
			}
			
			return  proyectFundingQ;
		
		}catch(Exception e ){
			
			e.printStackTrace();
		}
		
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProyectFunding> getAmmountFundedOnProyectByInvestor(ProyectLoanPK key, Integer InvID ){
		Integer proyectLoanID = key.getProyect_loan_id();
		Integer prospectusID = key.getProspectus_id();
		Integer companyID = key.getCompany_id();
		Integer proyectID = key.getProyect_id();
		//select amount, funding_date from ln_proyect_funding where prospectus_investor_id = 0 and proyect_loan_id = 1 and proyect_id = 2 and prospectus_id = 1 and company_id = 1;
		List<ProyectFunding> list = (List<ProyectFunding>) em.createNativeQuery("SELECT * FROM ln_proyect_funding WHERE prospectus_investor_id = "+InvID+" and proyect_loan_id = "+proyectLoanID+" and proyect_id = "+proyectID+" and prospectus_id = "+prospectusID+" and company_id = "+companyID+";",ProyectFunding.class).getResultList();
		return list;
	}
	
	@Override
	public List<ProyectFunding> getListProyectFunByInvestor( int prospectus_id , int company_id ){
		
		List<ProyectFunding> proyectFunding=em.createQuery("From ProyectFunding pl where proyectloanfundingPk.prospectus_investor_id = ? and proyectloanfundingPk.company_id = ?  ",ProyectFunding.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.getResultList();
		return proyectFunding;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjTmp> getListInvestors (){
		
		try{
			
//			List<ObjTmp> result = (List<ObjTmp>) session.createCriteria(ViewInvestmetInProyect.class)
//					              .setProjection ( 
//					            		           Projections.projectionList()
//					            		                                       .add( Projections.count("proyectloanfundingPk.investor") )
//					            		                                       .add( Projections.groupProperty("proyectloanfundingPk.credito_id") ) 
//					            		          ).list()  ;
		
			
		String 	queryInvestorbyProyectId =" select count(investor) as investor, credito_id from view_investment_in_proyect group by credito_id; ";
			
		List<ObjTmp>proyectFundingQ = em.createNativeQuery(queryInvestorbyProyectId, ObjTmp.class)
														.getResultList();
		
		return proyectFundingQ;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	

}