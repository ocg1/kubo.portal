package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;



public interface BusinessDao {
	
	public Business loadSelectedBusiness(BusinessPK pk);
	public boolean saveBusiness(Business newBusiness);
	public List<Business> loadBusinessList();
	public boolean updateBusiness(Business business);
	public boolean removeBusiness(BusinessPK pk);
	public List<Business> getListBusinessByProspect(int prospectID,int companyID);
	public boolean deleteAllBusiness(int prospectID,int companyID);
	public BmxEconActivityCat findBmxActivityById(String bmxActivityID ,int companyID);
	
}
