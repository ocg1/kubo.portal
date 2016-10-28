package mx.com.kubo.services.impl;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.Version;
import mx.com.kubo.repositories.SystemParamDao;
import mx.com.kubo.services.SystemParamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "systemParamServiceImp")
public class SystemParamServiceImp  
implements SystemParamService
{	
	@Autowired
	private SystemParamDao dao;
	
	private String version_description = null;
	
	public SystemParam loadSelectedSystemParam(SystemParamPK id)
	{
		return dao.loadSelectedSystemParam(id);
	}
	
	public boolean updateSelectedSystemParam(SystemParam  sp ){
		return dao.updateSelectedSystemParam(sp);
	}	

	public String getVersion_description() 
	{		
		Version version_ACTUAL;		
		
		if(version_description == null)
		{
			version_ACTUAL = dao.getVersion_ACTUAL();
			
			if(version_ACTUAL != null)
			{
				version_description = version_ACTUAL.getDescription();
			}
		}
								
		return version_description;
	}
}
