package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ProfileCategoryInv;
import mx.com.kubo.repositories.ProfileCategoryDao;
import mx.com.kubo.services.ProfileCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileCategoryServiceImp implements Serializable , ProfileCategoryService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProfileCategoryDao profilecategoryrepository;
	
	public List<ProfileCategoryInv> getListProfileCategory(){
		
		return profilecategoryrepository.getListProfileCategory();
		
	}

}
