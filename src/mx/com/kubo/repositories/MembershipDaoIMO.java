package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Promotor;

public interface MembershipDaoIMO 
{	
	Membership loadSelectedMembership(MembershipPK pk);
	Membership loadMembershipByEmail(String email);
	
	public Membership getMembershipbyFBID( String fb_id );
	
	Membership getMemberShipByActivCode(String code);
			
	Promotor getPromotor(Integer prospectus_id);
	
	List<Promotor> getListPromotors();
	
	List<Membership> loadMembershipList();
	List<Membership> getMemberShipForConsulting();
	List<Membership> getMemberShipForAssignPerfil();
	
	List<Membership> getMembershipByArea(Character area);
	List<Membership> getMembershipByArea(Character area, Integer limite_inferior, Integer limite_superior);
	
	List<Membership> getLista_personas_CURP_duplicado(Integer prospectus_id, String curp);
	
	void saveMembership(Membership membership);
	boolean updateMembership(Membership membership);
	
	List<Membership> getMembershipListByRegistrationReasonAndOrigin( int registration_reason_id , String origin_value );
	
	public Membership getMemberShipBySafiClientID( String safi_client_id );
	
	public List<Membership> getInvestorActive();
	
	public boolean bloqueaProspectos( String prospectos );		
}
