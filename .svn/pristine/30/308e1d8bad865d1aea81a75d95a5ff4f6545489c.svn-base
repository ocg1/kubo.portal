package mx.com.kubo.services.impl;

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
import mx.com.kubo.repositories.EmploymentDao;
import mx.com.kubo.services.EmploymentService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class EmploymentServiceImp
implements EmploymentService 
{
	@Autowired
	private EmploymentDao dao;
	
	private Logger log = Logger.getLogger(getClass());
	
	private final int COMPANY_ID = 1;
	
	public Employment getEmploymentById(EmploymentPK pk) 
	{
		return dao.loadSelectedEmployment(pk);
	}
	
	public List<Employment> getEmploymentList() 
	{		
		return dao.loadEmploymentList();
	}
	
	public List<NeighborhoodCat> getAsentamientosByCP(String cp) 
	{
		return dao.getAsentamientoByCP(cp);
	}
	
	public List<Contract_typeCat> getContractTypeList() 
	{
		return dao.loadContracTypeList();
	}
	
	public List<TenureCat> getTenureList() 
	{
		return dao.loadTenureList();
	}
	
	public String getTenure(int tenure_id) 
	{
		return dao.getTenure(tenure_id, COMPANY_ID).getName();
	}
	
	public List<Other_IncomeCat> getOtherIncomeList() 
	{
		return dao.loadOtherIncomeList();
	}
	
	public List<Employment> getListEmployByProspect(int prospectID,int companyID) 
	{		
		return dao.getListEmployByProspect(prospectID, companyID);
	}
	
	public boolean add(Employment newEmployment) 
	{
		return dao.saveEmployment(newEmployment);		
	}
		
	public boolean updateEmployment(Employment employ) 
	{
		boolean update_OK = dao.updateEmployment(employ);
	
		if(update_OK)
		{
			log.info("====>>>> Employment update successfully");
			
		} else {
			
			log.info("====>>>> Employment  NO update");	
		}
		
		return update_OK;
	}
	
	public boolean updateEmploymentBlur(Employment employ, EmploymentPK pk) 
	{
		return  dao.updateEmploymentBlur(employ, pk);
	}
	
	public boolean removeEmployment(EmploymentPK pk) 
	{
		return dao.removeEmployment(pk);
	}
	
	public boolean deleteAllEmployment(int prospectID,int companyID)
	{
		return dao.deleteAllEmployment(prospectID, companyID);
	}
	
	public List<BmxEconActivityCat> searchActivityList(String descripString) 
	{
		return dao.searchActivityList(descripString);
	}
	
	public InegiEconActivityCat searchActivitySector(InegiEconActivityCatPK pk) 
	{
		return dao.searchActivitySector(pk);
	}	

}
