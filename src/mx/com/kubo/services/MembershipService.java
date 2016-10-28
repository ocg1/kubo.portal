package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Promotor;

public interface MembershipService 
{	
	void add(Membership newMembership);
	boolean update(Membership newMembership);
	
	Membership getMembershipById (MembershipPK pk);
	
	Membership getMembershipByEmail (String email);
	Membership getMembershipbyFBID( String fb_id );
	Membership getMemberShipByActivCode (String code);

	Promotor getPromotor(Integer prospectus_id);
	
	List<Promotor> getListPromotors();
	
	List<Membership> getMemberShipForConsulting();
	List<Membership> getMemberShipForAssignPerfil();
	List<Membership> getMembershipList();
	
	List<Membership> getMembershipByArea(Character area);
	List<Membership> getMembershipByArea(Character area, Integer limite_inferior, Integer limite_superior);	
	List<Membership> getLista_personas_CURP_duplicado(Integer prospectus_id, String curp);
	
	List<Membership> getMembershipListByRegistrationReasonAndOrigin( int registration_reason , String origin_value);
	
	Membership getMemberShipBySafiClientID( String safi_client_id );
	
	List<Membership> getInvestorActive();
	
	boolean bloqueaProspectos( String prospectos );
}
