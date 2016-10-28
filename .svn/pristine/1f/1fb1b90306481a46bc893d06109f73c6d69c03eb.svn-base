package mx.com.kubo.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.bean.ObjTmp;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.ProyectFundingPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.repositories.ProyectFundingDao;
import mx.com.kubo.services.ProyectFundingService;

@Service
public class ProyectFundingServiceImp implements ProyectFundingService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ProyectFundingDao proyectFundingRepository;
	
	@Override
	public ProyectFunding getProyectFundingById(ProyectFundingPK pk) {
		return proyectFundingRepository.loadSelectedProyectFunding(pk);
	}
	
	@Override
	public void add(ProyectFunding newProyectFunding) {
		proyectFundingRepository.saveProyectFunding(newProyectFunding);
		
	}

	@Override
	public List<ProyectFunding> getProyectFundingList() {
		return proyectFundingRepository.loadProyectFundingList();
	}
	
	@Override
	public ProyectFunding findProyect(ProyectFundingPK key){
		return proyectFundingRepository.loadSelectedProyectFunding(key);
	}
	
	@Override
	public boolean update(ProyectFunding otro){
		return proyectFundingRepository.update(otro);
	}
	
	@Override
	public List<ProyectFunding> getproyectbyInvestor(String prospectusInvestorId, String proyectLoanId){
		return proyectFundingRepository.getProyectByInvestor(prospectusInvestorId, proyectLoanId);
	}
	
	@Override
	public List<ProyectLoan> getProyectsOfInvestorById(String id){
		return proyectFundingRepository.getProyectOfInvestor(id);
	}
	

	@Override
	public List<BigDecimal> getIFAmountFunding(ProyectLoanPK key){
		return proyectFundingRepository.getInstitutionalAmountFunded(key);
	}
	
	@Override
	public List<BigDecimal> getAmountFundedByProyectLoanPK(ProyectLoanPK key){
		return proyectFundingRepository. getAmountFundedByProyectLoanPK( key );
	}

	@Override
	public List<ViewInvestmetInProyect> getListInvestorbyProyectId( Integer credito_id, Integer solicitud_id ){
		return proyectFundingRepository.getListInvestorbyProyectId( credito_id, solicitud_id );
	}
	
	@Override
	public List<ProyectFunding> getMaxProyectFundingByInvOnProyect(ProyectLoanPK key, Integer InvID){
		return proyectFundingRepository.getAmmountFundedOnProyectByInvestor(key, InvID);
	}
	
	@Override
	public List<ProyectFunding> getListProyectFunByInvestor( int prospectus_id , int company_id ){
		
		return proyectFundingRepository.getListProyectFunByInvestor(  prospectus_id ,  company_id );
		
	}
	
	@Override
	public List<ObjTmp> getListInvestors (){
	
		return proyectFundingRepository.getListInvestors ();
		
	}
	
}