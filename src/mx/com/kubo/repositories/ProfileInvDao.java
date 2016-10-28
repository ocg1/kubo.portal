package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.investor.OtherinvestmentCat;

public interface ProfileInvDao {

	public ProfileInv getProfileInvByProspectus( int prospectus_id, int company_id );
	
	public ProfileInv  saveProfileInv( ProfileInv profile );

	public boolean  updateProfileInv( ProfileInv profile );
	
	public boolean  removeProfileInv( ProfileInv profile );
	
	public List<OtherinvestmentCat> getOtherinvestmentCat();
	
}
