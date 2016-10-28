package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.repositories.AccessDao;
import mx.com.kubo.repositories.Change_controlDAO;
import mx.com.kubo.repositories.ProspectusDao;
import mx.com.kubo.repositories.SystemParamDao;
import mx.com.kubo.services.AccessService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceAccessDMO 
implements AccessService 
{
	protected Logger log = Logger.getLogger(getClass());
	
	@Autowired protected AccessDao         dao;	
	@Autowired protected Change_controlDAO dao_change_control;	
	@Autowired protected ProspectusDao     dao_prospectus;
	@Autowired protected SystemParamDao    dao_system_param;
	
	public Access getAccessById(int id) 
	{
		return dao.loadSelectedAccess(id);
	}
	
	public Access getMaxAccess(Integer prospectus_id, Integer company_id) 
	{
		return dao.loadMaxAccess(prospectus_id, company_id);
	}
	
	public Access getMaxAccessByScreen(int prospectus_id, int company_id, int screen_id )
	{
		return dao.getMaxAccessByScreen( prospectus_id, company_id, screen_id );
	}
	
	public List<AccessCollector> loadMenu(Integer prospectus_id, Integer company_id,Character area) 
	{
		return dao.loadMenu(prospectus_id, company_id,area);
	}
	
	public Access loadMaxAccess(Integer prospectus_id, Integer company_id)
	{
		return dao.loadMaxAccess(prospectus_id, company_id);
	}
	
	public List<AccessCollector> getMenuIncomplete(Integer prospectus_id, Integer company_id,Character area)
	{
		return dao.getMenuIncomplete( prospectus_id,  company_id, area);
	}
}
