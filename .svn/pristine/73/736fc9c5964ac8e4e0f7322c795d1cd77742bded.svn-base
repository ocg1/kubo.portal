package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Contract_typeCat;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.InegiEconActivityCatPK;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Other_IncomeCat;
import mx.com.kubo.model.TenureCat;

public interface EmploymentDao 
{
	
	Employment loadSelectedEmployment(EmploymentPK pk);
	InegiEconActivityCat searchActivitySector(InegiEconActivityCatPK pk);
	
	TenureCat getTenure(int tenure_id, int company_id);
		
	List<BmxEconActivityCat> searchActivityList(String descripString);
	List<NeighborhoodCat> getAsentamientoByCP(String cp);
	List<Contract_typeCat> loadContracTypeList();
	List<TenureCat> loadTenureList();
	List<Other_IncomeCat> loadOtherIncomeList();
	
	List<Employment> getListEmployByProspect(int prospectID, int companyID);
	List<Employment> loadEmploymentList();
	
	boolean saveEmployment (Employment newEmployment);
	boolean updateEmployment(Employment employ);
	boolean updateEmploymentBlur(Employment employ, EmploymentPK pk);
	boolean removeEmployment(EmploymentPK pk);
	boolean deleteAllEmployment(int prospectID,int companyID);

}
