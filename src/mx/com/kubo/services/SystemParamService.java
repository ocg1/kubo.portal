package mx.com.kubo.services;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public interface SystemParamService 
{
	SystemParam loadSelectedSystemParam(SystemParamPK id);
	
	boolean updateSelectedSystemParam(SystemParam  sp );

	String getVersion_description();	
}
