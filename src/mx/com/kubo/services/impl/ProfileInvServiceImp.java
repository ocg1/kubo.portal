package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.investor.OtherinvestmentCat;
import mx.com.kubo.repositories.ProfileInvDao;
import mx.com.kubo.services.ProfileInvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileInvServiceImp implements Serializable, ProfileInvService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProfileInvDao profileinvdao;
	
	@Override
	public ProfileInv getProfileInvByProspectus( int prospectus_id, int company_id ){
		return profileinvdao.getProfileInvByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public ProfileInv  saveProfileInv( ProfileInv profile ){
		return profileinvdao.saveProfileInv(profile);
	}

	
	@Override
	public boolean  updateProfileInv( ProfileInv profile ){
		return profileinvdao.updateProfileInv(profile);
	}
	
	@Override
	public boolean  removeProfileInv( ProfileInv profile ){
		return profileinvdao.removeProfileInv(profile);
	}
	
	@Override
	public List<OtherinvestmentCat> getOtherinvestmentCat(){
		return profileinvdao.getOtherinvestmentCat();
	}
	
}
