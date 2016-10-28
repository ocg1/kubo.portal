package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ProfileFormValue;
import mx.com.kubo.repositories.ProfileFormValueDao;
import mx.com.kubo.services.ProfileFormValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileFormValueServiceImp implements Serializable, ProfileFormValueService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProfileFormValueDao profileformvaluedao;
	
	public List<ProfileFormValue> getProfileFormValueListByProspectus( int prospectus_id , int company_id  ){
		
		return profileformvaluedao.getProfileFormValueListByProspectus(prospectus_id, company_id);
		
	}
	
	public boolean saveProfileFormValue(ProfileFormValue profile){
		
		return profileformvaluedao.saveProfileFormValue(profile);
		
	}
	
	public boolean removeProfileFormValue(ProfileFormValue profile){
		return profileformvaluedao.removeProfileFormValue(profile);
	}
	
	public boolean updateProfileFormValue(ProfileFormValue profile){
		return profileformvaluedao.updateProfileFormValue(profile);
	}
	public boolean getHaInvertidoEnAcciones( int prospectus_id , int company_id  ){
		return profileformvaluedao.getHaInvertidoEnAcciones( prospectus_id , company_id  );
	}
}
