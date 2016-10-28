package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ProfileInvestForm;
import mx.com.kubo.repositories.ProfileInvestFormDao;
import mx.com.kubo.services.ProfileInvestFormService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileInvestFormServiceImp implements Serializable, ProfileInvestFormService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProfileInvestFormDao profileinvestformdao;
	
	@Override
	public List<ProfileInvestForm> getProfileInvestFormList(){
		
		return profileinvestformdao.getProfileInvestFormList();
		
	}
	
}
