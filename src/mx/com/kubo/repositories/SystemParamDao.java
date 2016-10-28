package mx.com.kubo.repositories;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.Version;

public interface SystemParamDao 
{
	SystemParam loadSelectedSystemParam(SystemParamPK id);
	
	boolean updateSelectedSystemParam(SystemParam  sp );

	Version getVersion_ACTUAL();	
}
