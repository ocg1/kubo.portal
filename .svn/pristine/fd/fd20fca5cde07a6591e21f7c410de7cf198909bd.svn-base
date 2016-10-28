package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;

public interface BusinessService 
{
	boolean save   (Business newBusiness);
	boolean update (Business business);
	
	boolean remove (BusinessPK pk);
	
	boolean deleteAllBusiness(int prospectID, int companyID);
	
	Business loadSelectedBusiness(BusinessPK pk);
	
	BmxEconActivityCat findBmxActivityById(String bmxActivityID, int companyID);
		
	List<Business> loadBusinessList();
	List<Business> getListBusinessByProspect(int prospectID, int companyID);
	
		
}
