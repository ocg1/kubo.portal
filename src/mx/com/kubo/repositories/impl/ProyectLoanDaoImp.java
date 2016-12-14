package mx.com.kubo.repositories.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.model.ActualizaTiendaCollector;
import mx.com.kubo.model.ApprovalCredit;
import mx.com.kubo.model.InvestmentsAtrasadasEdoCta;
import mx.com.kubo.model.InvestmentDetail;
import mx.com.kubo.model.LoanType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectSaldosEdoCta;
import mx.com.kubo.model.RFCCollector;
import mx.com.kubo.model.SPDeleteCreditsByClientsCollector;
import mx.com.kubo.model.SPProyectActive;
import mx.com.kubo.model.SafiProyecInProcess;
import mx.com.kubo.model.ViewForTiendaExec;
import mx.com.kubo.model.ViewProyectTienda;
import mx.com.kubo.model.spSetOnProyectFunding;
import mx.com.kubo.repositories.ProyectLoanDao;
import mx.com.kubo.model.InfoCalifPorc;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;
import safisrv.ws.InvKuboServicios.SolicitudInversionRequest;
import safisrv.ws.InvKuboServicios.SolicitudInversionResponse;


@Repository
public class ProyectLoanDaoImp extends DAOProyectLoanDMO
implements ProyectLoanDao
{    	
	@Transactional
	public ProyectLoan loadSelectedProyectLoan(ProyectLoanPK pk) 
	{		
		ProyectLoan py =  em.find(ProyectLoan.class, pk);
		
		boolean flagtrans = false;
		
		if(py != null)
		{			
			try
			{				
				Hibernate.initialize(py.getTransunion());
				flagtrans=true;
			
			}catch(EntityNotFoundException nF){
				
				System.out.println(nF.getMessage());
				py.setTransunion(null);
				
				flagtrans = false;
				
			}catch(Exception e){
				
				System.out.println(e.getMessage());
				py.setTransunion(null);
				flagtrans = false;
			}
			
			if(flagtrans)
			{
				try
				{					
					Hibernate.initialize(py.getTransunion().getAjuste1());
					
				} catch(EntityNotFoundException nF) {
					
					py.getTransunion().setAjuste1(null);
					
				} catch(Exception e) {
					
					System.out.println(e.getMessage());
				}
				
				try
				{					
					Hibernate.initialize(py.getTransunion().getAjuste2());
					
				} catch(EntityNotFoundException nF) {
					
					py.getTransunion().setAjuste2(null);
					
				} catch(Exception e) {
					
					System.out.println(e.getMessage());
				}
			}			
		}
		
		return py;
	}

	@Transactional	
	public void saveProyectLoan(ProyectLoan newProyectLoan) 
	{		
		log.info("saveProyectLoan.ProyectLoanDaoImp");
		
		String query = "select MAX(pl.proyectloanPk.proyect_loan_id) from ProyectLoan pl";
		
		int idproyectloan = 0;
		
		if(em.createQuery(query).getSingleResult() != null)
		{
			idproyectloan = (Integer) em.createQuery(query).getSingleResult();
			
			idproyectloan++;
			
		} else {
			
			idproyectloan++;
		}
		
		newProyectLoan.getProyectloanPk().setProyect_loan_id(idproyectloan);
		
		em.persist(newProyectLoan);
	}
	
	@SuppressWarnings("unchecked")
	public List<ViewForTiendaExec> getProyectsByFilterInv(FilterStore filter ) 
	{		
		
		/*
		
		String query = "SELECT l.* " +
				"	From view_for_tienda l , gn_natural_person per " +
				"	WHERE ";
		
		if( x != null && x.trim().length() > 0 )
		{
			query += x + " and ";
		
		}
		
		query += " l.prospectus_id = per.prospectus_id and  l.company_id = per.company_id  "  ;
		
		
		
		switch( typeSearch )
		{		
			case 1://muestra los proyectos en los que ya invirtió
				
				//query += " and l.proyect_loan_id in ( select proyect_loan_id from ln_proyect_funding where prospectus_investor_id = "+prospectus_id+" and company_id = "+ company_id +" ); ";
				
				query += " and l.safi_credit_id in (select F.safi_credit_id from ln_tienda_for_clientesid F where F.ClienteID = "+safi_client_id+"); ";
				
				break;
			
			case 2://muestra los proyectos en los NO haya invertido
				
				//query += " and l.proyect_loan_id not in ( select proyect_loan_id from ln_proyect_funding where prospectus_investor_id = "+prospectus_id+" and company_id = "+ company_id +" ) ";
				//query += " and l.safi_credit_id not in (select F.safi_credit_id from ln_tienda_for_clientesid F  where F.ClienteID = "+safi_client_id+"); ";
				query = " SELECT  l.* 	" +
						"	From  " +
							" view_for_tienda l " +
							" inner join " +
							" gn_natural_person per " +
								" on  " +
							" l.prospectus_id = per.prospectus_id and   " +
							" l.company_id = per.company_id " +
								" left join  " +
							" ln_tienda_for_clientesid F on  " +
							" l.safi_credit_id = F.safi_credit_id and  " +
							" F.ClienteID = "+safi_client_id+" " +
							" where  "  + x +
							" and F.ClienteID is null;	";
						
				break;
				
			default:
				
				query += ";";
				
			break;
		
		}
								
		System.out.println( query );
				
		//List<ViewProyectTienda> proyectLoan = em.createNativeQuery(query,ViewProyectTienda.class).getResultList();
		 
		
		 */
		
		//:Par_CuentaAhoID, :Par_ClienteID,:Par_TipoFondeo, :Par_LoanType, :Par_KuboScore, :Par_GenderID, :Par_Ammount, :Par_DiasPorTrans
		
		System.out.println("Par_CuentaAhoID: " + filter.getCuentaAhoID());
		System.out.println("Par_ClienteID: " + filter.getClienteID());
		System.out.println("Par_TipoFondeo: " + filter.getTipoFondeo());
		System.out.println("Par_LoanType: " + filter.getLoanType());
		System.out.println("Par_KuboScore: " + filter.getKuboScore());
		System.out.println("Par_GenderID: " + filter.getGenderID());
		System.out.println("Par_Ammount: " + filter.getAmmount());
		System.out.println("Par_DiasPorTrans: " + filter.getDiasPorTrans());
		System.out.println("Par_purpose_id: " + filter.getPurpose_id());
		
		List<ViewForTiendaExec> proyectLoan =  em.createNamedQuery("queryTiendaInversionista",ViewForTiendaExec.class)
													.setParameter("Par_CuentaAhoID", filter.getCuentaAhoID())
													.setParameter("Par_ClienteID", filter.getClienteID())
													.setParameter("Par_TipoFondeo", filter.getTipoFondeo())
													.setParameter("Par_LoanType", filter.getLoanType())
													.setParameter("Par_KuboScore", filter.getKuboScore())
													.setParameter("Par_GenderID", filter.getGenderID())
													.setParameter("Par_Ammount", filter.getAmmount())
													.setParameter("Par_DiasPorTrans", filter.getDiasPorTrans())
													.setParameter("Par_purpose_id", filter.getPurpose_id())
													
													.getResultList();
		
		return proyectLoan;
	}
			
	@Transactional
	public boolean update(ProyectLoan proyect_loan)
	{
		try
		{
			em.merge(proyect_loan);
			
			return true;
			
		} catch(Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
	}
		
	public int spSetOnProyectFunding(Integer par_proyect_loan_id,Integer par_proyect_id,Integer par_company_id,Integer par_prospectus_id,Integer par_prospectus_investor_id, Double par_amount, String  solicitudFondeo)
	{
		log.info("spSetOnProyectFunding");
		
		try
		{
			List<spSetOnProyectFunding> insert = null;
			
			insert =  em.createNamedQuery("loanQuery",spSetOnProyectFunding.class)
						.setParameter("par_proyect_loan_id", par_proyect_loan_id)
						.setParameter("par_proyect_id", par_proyect_id)
						.setParameter("par_company_id", par_company_id)
						.setParameter("par_prospectus_id", par_prospectus_id)
						.setParameter("par_prospectus_investor_id", par_prospectus_investor_id)
						.setParameter("par_amount", par_amount)	
						.setParameter("par_solicitudFondeo", solicitudFondeo)
						.getResultList();
			
			if(insert.isEmpty())
			{
				return 1;
				
			} else {
				
				return 0;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return 1;
		}
	}
		
	public ProyectLoan getMaxProyectLoanByProspectDontPublish(Integer prospectus_id, Integer company_id) 
	{	
		log.info("getMaxProyectLoanByProspect.ProyectLoanDaoImp");
		
		String query = "select MAX(pl.proyectloanPk.proyect_loan_id) from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id in (0,1,2)";
				
		Integer proy_loan = (Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();
		
		if(proy_loan == null)
		{
			return null;
		}
		
		query = "from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id in (0,1,2) and pl.proyectloanPk.proyect_loan_id = ?";
		
		ProyectLoan proyectloan = em.createQuery(query,ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3, proy_loan).getSingleResult();
		
		return proyectloan;		
	}
		
	public ProyectLoan getMaxProyectLoanByProspect(Integer prospectus_id, Integer company_id) 
	{
		String query;
		
		log.info("getMaxProyectLoanByProspect.ProyectLoanDaoImp");
		
		query = "select MAX(pl.proyectloanPk.proyect_loan_id) "
		      + " from ProyectLoan pl "
		      + " where pl.proyectloanPk.prospectus_id = ? "
		      + " and   pl.proyectloanPk.company_id    = ?";
		
		
		Integer proy_loan = (Integer) em.createQuery(query)
									    .setParameter(1, prospectus_id)
									    .setParameter(2,company_id)
									    .getSingleResult();
		
		if(proy_loan == null)
		{
			return null;
		}
		
		query = "from ProyectLoan pl "
			  + " where pl.proyectloanPk.prospectus_id   = ? "
			  + " and   pl.proyectloanPk.company_id      = ? "
			  + " and   pl.proyectloanPk.proyect_loan_id = ?";
		
		ProyectLoan proyectloan = em.createQuery(query,ProyectLoan.class)
									.setParameter(1, prospectus_id)
									.setParameter(2,company_id)
									.setParameter(3, proy_loan).getSingleResult();
		
		return proyectloan;
		
	}	
	
	public ProyectLoan getMaxProyectLoanByProspectInReview(Integer prospectus_id, Integer company_id) 
	{	
		log.info("getMaxProyectLoanByProspect.ProyectLoanDaoImp");
		
		String query="select MAX(pl.proyectloanPk.proyect_loan_id) from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = 1";
				
		Integer proy_loan  =(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();
		
		if(proy_loan == null)
		{
			return null;
		}
		
		query = "from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = 1 and pl.proyectloanPk.proyect_loan_id = ?";
		
		ProyectLoan proyectloan = em.createQuery(query,ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3, proy_loan).getSingleResult();
		
		return proyectloan;		
	}
	
	
	public ProyectLoan getMaxProyectLoanByProspectInFunding(Integer prospectus_id, Integer company_id) 
	{	
		log.info("getMaxProyectLoanByProspect.ProyectLoanDaoImp");
		
		String query="select MAX(pl.proyectloanPk.proyect_loan_id) from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = 2";
				
		Integer proy_loan = (Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();
		
		if(proy_loan == null)
		{
			return null;
		}
		
		query="from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = 2 and pl.proyectloanPk.proyect_loan_id = ?";
		
		ProyectLoan proyectloan = em.createQuery(query,ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3, proy_loan).getSingleResult();
		
		return proyectloan;		
	}
	
	public ProyectLoan getMaxProyectLoanByProspectAndStatus(Integer prospectus_id, Integer company_id, Integer status_id) 
	{	
		log.info("getMaxProyectLoanByProspect.ProyectLoanDaoImp");
		
		String query="select MAX(pl.proyectloanPk.proyect_loan_id) from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = ?";
				
		Integer proy_loan = (Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3,status_id).getSingleResult();
		
		if(proy_loan == null)
		{
			return null;
		}
		
		query="from ProyectLoan pl where pl.proyectloanPk.prospectus_id = ? and pl.proyectloanPk.company_id = ? and status_id = ? and pl.proyectloanPk.proyect_loan_id = ?";
		
		ProyectLoan proyectloan = em.createQuery(query,ProyectLoan.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3,status_id).setParameter(4, proy_loan).getSingleResult();
		
		return proyectloan;		
	}
		
	public String setInstitutionalFunding(ProyectLoanPK key)
	{	
		//Integer Cliente = 1;
		Integer CuentaAhorro = 100000024;
		//Integer SolisitudSAFI = 1;
		Integer InvestorID = 1;
		//Integer Account = 100000024;
		
		Integer proyect_id = key.getProyect_id();
		Integer proyect_loan_id = key.getProyect_loan_id(); 
		//Integer prospectus_id = key.getProspectus_id(); 
		Integer company_id = key.getCompany_id();
		
	    ProyectLoan proyectInContext = this.loadSelectedProyectLoan(key);
	    Double investment_Bite = proyectInContext.getAmmount()*0.1;
	   
	    String[] response = this.storeInvestmentInWS00(proyectInContext.getSafi_mx_solicitud_id(), CuentaAhorro, InvestorID, 1.0, investment_Bite);
	    //Resgresar la tasa a cero
	    if(!response[0].equals("0"))
	    {
			return "1";
			
	    } else {
		    this.spSetOnProyectFunding(proyect_loan_id, proyect_id, company_id, 0, InvestorID, investment_Bite,response[1]);
		    proyectInContext.setAmount_founded(proyectInContext.getAmount_founded()+investment_Bite);
		    proyectInContext.setInvestors_number(proyectInContext.getInvestors_number()+1);
		    return "0";
	    }
	}
	
	
	public String[] storeInvestmentInWS00(Integer solicitudCreditoId,Integer cuentaAhorroId,Integer prospectus_investor_id, Double rate, Double investmentBite)
	{
    	try
    	{
    		SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios servicio = locator.getSAFIServiciosSoap11();
			//SolicitudInversionResponse res1 = servicio.solicitudInversion(new SolicitudInversionRequest(solicitudCreditoId.toString(),prospectus_investor_id.toString(),cuentaAhorroId.toString(),investmentBite.toString(),rate.toString()));
			
			SolicitudInversionRequest solicitudInversionRequest = new SolicitudInversionRequest();
			
			solicitudInversionRequest.setClienteID("");
			
			SolicitudInversionResponse res1 = servicio.solicitudInversion(solicitudInversionRequest);
			
			this.ExceptionOnFunding = res1.getMensajeRespuesta();
			
			
			
			String[] res = new String[2];
			
			res[0] = res1.getCodigoRespuesta();
			res[1] = res1.getSolicitudFondeo();
			
			return res; 
			
    	} catch (Exception e) {
    		
    		String[] res = new String[2];
			
			res[0] = "-1";
			res[1] = "-1";
			
			return res; 
			
		}
    }

	public ProyectLoan getProyectLoanByProyectLoanID(Integer proyectLoanID, Integer prospectusID, Integer companyID) 
	{		
		String query = "from ProyectLoan where proyectloanPk.proyect_loan_id = :proyectLoanID and proyectloanPk.prospectus_id = :prospectusID and proyectloanPk.company_id = :companyID";
		
		ProyectLoan proyectLoan = null;
		
		try
		{
			proyectLoan = em.createQuery(query, ProyectLoan.class)
							.setParameter("proyectLoanID", proyectLoanID)
							.setParameter("prospectusID", prospectusID)
							.setParameter("companyID", companyID)
							.getSingleResult();
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return proyectLoan;
	}
	
	public List<SafiProyecInProcess> getProyectLoanInProcessBySafi(String cuenta)
	{		
		try
		{
			String query2 = "from SafiProyecInProcess where CuentaAhoID in( "+cuenta+")"; 
			
			List<SafiProyecInProcess> listOfProyectsTemp = (List<SafiProyecInProcess>)em.createQuery(query2,SafiProyecInProcess.class).getResultList();
			
			if(listOfProyectsTemp!=null)
			{
				System.out.println( "Lista: "+listOfProyectsTemp.size()+" elementos " );
			}
			
			return listOfProyectsTemp;
			
		} catch(Exception e){
			
			e.printStackTrace();
			
			return null;		
		}	
	}	
	
	public List<ProyectLoanActiveInSafi> getProyectLoanActiveInSafiByAccount(String account, String status, char status_delinquentinv)
	{		
		List<ProyectLoanActiveInSafi> listOfProyectsTemp;						
		Integer diaAtrasoID;
		String  ConsultaXDiaAtr = "D", CuentaAhoID = account.replaceAll("'", "");
		
		try
		{					
			if(status.equals("A1")){
				diaAtrasoID = 1;
			} else if(status.equals("A2")){
				diaAtrasoID = 2;
			}else if(status.equals("A3")){
				diaAtrasoID = 3;
			}else if(status.equals("A4")){
				diaAtrasoID = 4;
			}else if(status.equals("A5")){
				diaAtrasoID = 5;
			}else if(status.equals("A6")){
				diaAtrasoID = 6;
			}else if(status.equals("A10")){
				diaAtrasoID = 0;
				ConsultaXDiaAtr = "G";
			}else{
				diaAtrasoID = 0;
			}
			
			log.info("saveAccess.ProyectLoanDaoImp");

			listOfProyectsTemp = em.createNamedQuery("getProyectActiveList",ProyectLoanActiveInSafi.class)
					.setParameter("cuentaAho_ID",Integer.parseInt(CuentaAhoID))
					.setParameter("diaAtrasoID", diaAtrasoID)
					.setParameter("fondeoKuboID", 0)
					.setParameter("proyect_loan_id", 0)
					.setParameter("tipoConsulta", ConsultaXDiaAtr)
					.setParameter("tipoVistaInv", status_delinquentinv)
					.getResultList();
						
			/*String query2 = "from ProyectLoanActiveInSafi where cuentaAhoID in( "+account+")"; 
			
			if(status.equals("A1"))
			{
				query2 += " and  diasAtraso = 0 ";
				
			} else if(status.equals("A2")){
				query2 += " and  diasAtraso > 0 and diasAtraso <= 15 ";
			}else if(status.equals("A3")){
				query2 += " and  diasAtraso > 15 and diasAtraso <= 30 ";
			}else if(status.equals("A4")){
				query2 += " and  diasAtraso > 30 and diasAtraso <= 90 ";
			}else if(status.equals("A5")){
				query2 += " and  diasAtraso > 90 and diasAtraso <= 120 ";
			}else if(status.equals("A6")){
				query2 += " and  diasAtraso > 120 ";
			}else if(status.equals("A7")){
				// query2 += " and  diasAtraso > 0 and diasAtraso <= 15 ";
			}
			ProyectLoanActiveInSafi
			query2 += "order by fondeokuboid desc";
			
			List<ProyectLoanActiveInSafi> listOfProyectsTemp = null;
			
			listOfProyectsTemp = (List<ProyectLoanActiveInSafi>)em.createQuery(query2,ProyectLoanActiveInSafi.class).getResultList();
			*/
			if(listOfProyectsTemp != null)
			{
				System.out.println( "Lista: "+listOfProyectsTemp.size()+" elementos " );
			}
			
			return listOfProyectsTemp;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;			
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProyectLoan> getProyectLoanListTiendaNotInFunding()
	{		
		String query =  "select * from ln_proyect_loan where safi_mx_solicitud_id in (select safi_mx_solicitud_id from ln_mx_tiendacreditos ) "+ 
						" and proyect_loan_id not in ( " +
						"	select  proyect_loan_id from ln_proyect_funding " +
						" ); " ;
		
		try
		{	
			List<ProyectLoan> listOfProyects = null;
			
			listOfProyects = (List<ProyectLoan>) em.createNativeQuery(query,ProyectLoan.class).getResultList();
			
			return listOfProyects;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;			
		}		
	}
	
	public ProyectLoanActiveInSafi getProyectLoanActiveInSafiByID(int kuboFondeoInv_id,int proyect_loan_id)
	{
		String Consulta_Por_Fondeo ="F";
		List<ProyectLoanActiveInSafi> listOfProyectsTemp;
		try{
			log.info("saveAccess.ProyectLoanActiveSafi");
			
			listOfProyectsTemp = em.createNamedQuery("getProyectActiveList",ProyectLoanActiveInSafi.class)
									.setParameter("cuentaAho_ID", 0)
									.setParameter("diaAtrasoID",0)
									.setParameter("fondeoKuboID", kuboFondeoInv_id)
									.setParameter("proyect_loan_id", proyect_loan_id)
									.setParameter("tipoConsulta",Consulta_Por_Fondeo)
									.setParameter("tipoVistaInv",0)
									.getResultList();
			
			if(listOfProyectsTemp!=null)
			{
				System.out.println( "Lista: "+listOfProyectsTemp.size()+" elementos " );
				
				if(listOfProyectsTemp.size() > 0)
				{
					return listOfProyectsTemp.get(0);
				} else {
					return null;
				}
				
			} else {
				
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		/*String query2 = "from ProyectLoanActiveInSafi where fondeokuboid = ? and proyect_loan_id = ?"; 
		
		List<ProyectLoanActiveInSafi> listOfProyectsTemp;
		
		listOfProyectsTemp = (List<ProyectLoanActiveInSafi>)em.createQuery(query2,ProyectLoanActiveInSafi.class)
															  .setParameter(1, kuboFondeoInv_id)
															  .setParameter(2, proyect_loan_id)
															  .getResultList();
		
		if(listOfProyectsTemp!=null)
		{
			System.out.println( "Lista: "+listOfProyectsTemp.size()+" elementos " );
			
			if(listOfProyectsTemp.size() > 0)
			{
				return listOfProyectsTemp.get(0);
			} else {
				return null;
			}
			
		} else {
			
			return null;
		}*/		
	}
	
	public boolean cambioStatus( ProyectLoan proyect_loan, Integer new_status, Date new_date )
	{
		try
		{
				/*
				 * Esta NamedQuery esta definida en la ActualizaTiendaCollector.java
				 */
				List<ActualizaTiendaCollector> res =  em.createNamedQuery("actualizaEstatusProyectLoan",ActualizaTiendaCollector.class)
						
						.setParameter("my_proyect_loan_id",proyect_loan.getProyectloanPk().getProyect_loan_id())
						.setParameter("my_company_id",proyect_loan.getProyectloanPk().getCompany_id())
						.setParameter("my_proyect_id",proyect_loan.getProyectloanPk().getProyect_id())
						.setParameter("my_prospectus_id",proyect_loan.getProyectloanPk().getProspectus_id())
						.setParameter("new_status",new_status)
						.setParameter("new_date",new_date)
						
					.getResultList();  
				
				if(res != null && res.get(0).getRes() == 1 )
				{
					return true;
					
				} else {
					
					return false;
				}
				
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;			
		}		
	}
			
	public Date getLastPublishProyectDate(ProyectLoan proyect_loan)
	{		
		String query2 = "select max(day_published) from ln_proyect_loan where prospectus_id = ? and company_id = ? and status_id <> 0 and day_published is not null;";
		
		try
		{
			Date p = (Date)em.createNativeQuery(query2)
							 .setParameter(1, proyect_loan.getProyectloanPk().getProspectus_id())
							 .setParameter(2, proyect_loan.getProyectloanPk().getCompany_id())
							 .getSingleResult();
			return p;
		
		} catch(Exception e) {
			
			System.out.println("Error en ela carga de la fecha");
			
			return null;
		}		
	}
	
	public Date getDateFirstCreditFromSAFI(ProyectLoan proyect_loan)
	{
		String query2 = "select min(FechaInicio)  from microfin.CREDITOS WHERE Estatus in ('V','B','P') and  ClienteID = ?;";
		
		try
		{
			Date p = (Date)em.createNativeQuery(query2).setParameter(1, proyect_loan.getPerson().getSafi_client_id()).getSingleResult();
			
			return p;
		
		} catch(Exception e) {
			
			System.out.println( "Error en ela carga de la fecha de primer credito" );
			
			return null;
		}
	}
	
	public SPProyectActive getResumenCreditActive( String cuentaAhoId )
	{		
		try
		{			
			return em.createNamedQuery("getResumenProyectActive",SPProyectActive.class).setParameter("cuentaAho_temp",cuentaAhoId).getSingleResult();
		
		} catch(Exception e) {
			
			System.out.println("Error creditos Activos");
			
			return null;
		}
	}
	
	public ProyectSaldosEdoCta getSaldosEdoCtaClient(String cuentaAhoID)
	{
		try
		{
			return em.createNamedQuery("getSaldosEdoCtaClient",ProyectSaldosEdoCta.class).setParameter("CuentaAhoID", cuentaAhoID).getSingleResult();
			
		} catch(Exception e) {
			
			System.out.print("Error en Saldos de Inversiones");
			
			return null;
		}
	}
	
	public InvestmentsAtrasadasEdoCta getInvestmentsAtraEdoCta(String cuentaAhoID)
	{
		try
		{
			return em.createNamedQuery("getInvestmentsAtraEdoCta",InvestmentsAtrasadasEdoCta.class).setParameter("CuentaAhoID", cuentaAhoID).getSingleResult();
			
		} catch(Exception e) {
			
			System.out.print("Error en Saldos de Inversiones");
			
			return null;
		}
	}
	
	public final List<LoanType> getLista_loan_type() 
	{
		List<LoanType> lista_loan_type = null;
		
		lista_loan_type = em.createQuery("from LoanType", LoanType.class).getResultList();
		
		return lista_loan_type;
	
	}
	
	
	public InvestmentDetail getInvestmentDetailByID( int kuboFondeoInv_id){
		
		String query2 = "from InvestmentDetail where fondeoKuboID = ?";
		
		System.out.println("********************fondeoKuboID: "+kuboFondeoInv_id);
		
		return  em.createQuery(query2,InvestmentDetail.class)
				  .setParameter(1, BigInteger.valueOf( Long.parseLong( kuboFondeoInv_id +"" ) ) )
				  .getSingleResult();
		
	}
	
	public boolean ejecutaSPDeleteCreditsByClient(String safi_client_id){
		
		List<SPDeleteCreditsByClientsCollector> collector=  em.createNamedQuery("deleteCreditsByClient",SPDeleteCreditsByClientsCollector.class)
				.setParameter("safi_client_id", Integer.parseInt(safi_client_id))
				.getResultList();
		
		if( collector != null && !collector.isEmpty() && collector.get(0).getNumErr() == 0 )
		{
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	public List<ApprovalCredit> getApprovalUsers( String safi_credit_id){
		
		try{
		
			BigInteger b = new BigInteger ( safi_credit_id );
			
			String query = "from ApprovalCredit where pk.safi_credit_id  = ?" ;
			
			return em.createQuery(query, ApprovalCredit.class).setParameter(1, safi_credit_id).getResultList();
		
			
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
		
	}
	
	public List<InfoCalifPorc> getInfoCalifPorc( String safi_client_id, String cuentaAhoID ){
	
		try{
			
			return em.createNamedQuery("getInfoCalifPorc",InfoCalifPorc.class)
									.setParameter("safi_client_id", safi_client_id)
									.setParameter("cuentaAhoID", cuentaAhoID)
									.getResultList();
			
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
}