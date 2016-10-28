package mx.com.kubo.services;

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

public interface EmploymentService 
{	
	Employment getEmploymentById(EmploymentPK pk);
	
	InegiEconActivityCat searchActivitySector(InegiEconActivityCatPK pk);
	
	List<BmxEconActivityCat> searchActivityList(String descripString);
	
	List<Employment>         getEmploymentList();		
	List<Contract_typeCat>   getContractTypeList();
	List<TenureCat>          getTenureList();
	List<Other_IncomeCat>    getOtherIncomeList();
	List<NeighborhoodCat>    getAsentamientosByCP(String cp);
	List<Employment>         getListEmployByProspect(int prospectID, int companyID);		
	
	String getTenure(int tenure_id);
	
	boolean add                 (Employment newEmployment);
	
	boolean updateEmployment    (Employment employ);
	boolean updateEmploymentBlur(Employment employ, EmploymentPK pk);
	
	boolean removeEmployment    (EmploymentPK pk);
	
	boolean deleteAllEmployment(int prospectID, int companyID);
}
