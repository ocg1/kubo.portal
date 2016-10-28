package mx.com.kubo.portal.reader;

import mx.com.kubo.managedbeans.SessionBean;

public final class ParameterReaderIMP extends ParameterReaderAMO
implements ParameterReaderIMO
{
	public final void init_sesion() 
	{		
		try
		{	
			access_from = parameter.get("access_from");
			
			if(access_from != null)
			{
				read_parameters();		
				
				if(sesion_ENABLED)
				{								
					init_faces_sesion();
					
				} else {
					
					sesion = new SessionBean();
					sesion.setCompany_id(company_id);
					sesion.setProspectus_id(prospectus_id);
					sesion.setCoachProspectus_id(prospectus_id);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();						
		}	
	}
}
