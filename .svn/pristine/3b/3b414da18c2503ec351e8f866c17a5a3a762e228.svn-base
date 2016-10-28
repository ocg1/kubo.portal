package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Promotor;
import mx.com.kubo.repositories.MembershipDaoIMO;
import mx.com.kubo.services.MembershipService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipServiceImp 
implements MembershipService 
{
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private MembershipDaoIMO dao;

	
	public Membership getMembershipById(MembershipPK pk) {
		return dao.loadSelectedMembership(pk);
	}
	
	public Membership getMembershipbyFBID( String fb_id ){
		return dao.getMembershipbyFBID( fb_id );
	}
	
	public Membership getMembershipByEmail(String email) {
		return dao.loadMembershipByEmail(email);
	}

	@Transactional	
	public void add(Membership newMembership) {
		log.info("add.MembershipImp");
		dao.saveMembership(newMembership);		
	}
	
	@Transactional	
	public boolean update(Membership newMembership) 
	{
		return dao.updateMembership(newMembership);		
	}
	
	
	public List<Membership> getMembershipList() {
		return dao.loadMembershipList();
	}

	
	public List<Promotor> getListPromotors() 
	{
		return dao.getListPromotors();
	}
	
	public final Promotor getPromotor(Integer prospectus_id)
	{
		return dao.getPromotor(prospectus_id);
	}
	
	public Membership getMemberShipByActivCode(String code){
		return dao.getMemberShipByActivCode(code);
	}
	
	
	public List<Membership> getMemberShipForConsulting(){
		return dao.getMemberShipForConsulting();
	}
	
	
	public List<Membership> getMemberShipForAssignPerfil(){
		return dao.getMemberShipForAssignPerfil();
	}
	
	public List<Membership> getMembershipByArea(Character area)
	{
		return dao.getMembershipByArea(area);
	}
	
	public List<Membership> getMembershipByArea(Character area, Integer limite_inferior, Integer limite_superior)
	{
		return dao.getMembershipByArea(area, limite_inferior, limite_superior);
	}
	
	public List<Membership> getMembershipListByRegistrationReasonAndOrigin( int registration_reason_id , String origin_value ){
		
		return dao.getMembershipListByRegistrationReasonAndOrigin( registration_reason_id , origin_value );
		
	}
	
	public List<Membership> getLista_personas_CURP_duplicado(Integer prospectus_id, String curp){
		return dao.getLista_personas_CURP_duplicado( prospectus_id,  curp);
	}
	
	public Membership getMemberShipBySafiClientID( String safi_client_id ){
		return dao.getMemberShipBySafiClientID( safi_client_id );
	}
	
	public List<Membership> getInvestorActive(){
		return dao.getInvestorActive();
	}
	
	public boolean bloqueaProspectos( String prospectos ){
		return dao.bloqueaProspectos( prospectos );
	}
	
}
