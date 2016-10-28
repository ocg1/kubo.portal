package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.repositories.BusinessDao;
import mx.com.kubo.services.BusinessService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImp 
implements BusinessService, Serializable 
{

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private BusinessDao dao;
	
	
	public Business loadSelectedBusiness(BusinessPK pk) 
	{
		return dao.loadSelectedBusiness(pk);
	}
	
	public boolean save(Business newBusiness) 
	{
		return dao.saveBusiness(newBusiness);		
	}
	
	public List<Business> loadBusinessList() 
	{		
		return dao.loadBusinessList();
	}
	
	public boolean update(Business employ) 
	{
		boolean update_OK;
		
		update_OK = dao.updateBusiness(employ);
		
		if(update_OK)
		{
			log.info("====>>>> Business update successfully");
			
		} else {
			
			log.info("====>>>> Business   NO update");
		}	
		
		return update_OK;
	}
	
	public boolean remove(BusinessPK pk) 
	{
		return dao.removeBusiness(pk);
	}
	
	public List<Business> getListBusinessByProspect(int prospectID,int companyID) {
		return dao.getListBusinessByProspect(prospectID, companyID);
	}
	
	public boolean deleteAllBusiness(int prospectID, int companyID){
		return dao.deleteAllBusiness(prospectID, companyID);
	}
	
	public BmxEconActivityCat findBmxActivityById(String bmxActivityID ,int companyID){
		return dao.findBmxActivityById(bmxActivityID, companyID);
	}
	
}
