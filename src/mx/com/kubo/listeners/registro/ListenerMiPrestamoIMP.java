package mx.com.kubo.listeners.registro;

import java.util.ArrayList;

import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.bean.ActividadEconomicaDMO;

public final class ListenerMiPrestamoIMP extends ListenerMiPrestamoAMO
implements ListenerMiPrestamoIMO
{	
	protected final void init_lista_employment_bean() 
	{					
		lista_employment = service_employment.getListEmployByProspect(prospectus_id, company_id);    
		
		lista_employment_bean = new ArrayList<ActividadEconomicaDMO>();
		
		for(Employment employment : lista_employment) 
		{						
			this.employment = employment;
			
			employment_id = employment.getEmploymentPK().getEmployment_id();
			
			employment_bean = new EmploymentBean();			

		   init_employment_address();
		   init_employment_phone();
		   init_check_IN_OUT();
		   init_employment_antiguedad();
		   
		   employment_bean.setEmployment(this.employment);
		   			  			   		   
		   lista_employment_bean.add(employment_bean);			   		
		}
	}
	
	protected final void init_lista_business_bean() 
	{		
		lista_business = service_business.getListBusinessByProspect(prospectus_id, company_id);	
		
		lista_business_bean = new ArrayList<ActividadEconomicaDMO>();	
		
		for (Business business : lista_business) 
		{
			this.business = business;
			
			business_id = business.getBusinessPK().getBusiness_id();
			
			business_bean = new BusinessBean();													
			
			init_business_address();
			init_business_phone();
			init_business_antiguedad();
			
			business_bean.setBusiness(this.business);
										
			lista_business_bean.add(business_bean);			
		}
	}

	public final boolean update_employment()
	{												
		update_OK = service_employment.updateEmployment(employment);
				
		if(update_OK)
		{						
			for(ActividadEconomicaDMO employment_bean_OLD: lista_employment_bean)
			{				
				EmploymentBean employment_bean = (EmploymentBean) employment_bean_OLD;
				
				employment_id     = employment_bean.getEmployment().getEmploymentPK().getEmployment_id();	
				employment_id_NEW = employment.getEmploymentPK().getEmployment_id();
				
				if(employment_id_NEW == employment_id)
				{					
					employment_bean.setCheck_inH1 (employment.getCheck_in() != null ? employment.getCheck_in().split(":")[0]:"09");
					employment_bean.setCheck_inM1 (employment.getCheck_in() != null ? employment.getCheck_in().split(":")[1]:"00");
					employment_bean.setCheck_outH1(employment.getCheck_out()!= null ? employment.getCheck_out().split(":")[0]:"18");
					employment_bean.setCheck_outM1(employment.getCheck_out()!= null ? employment.getCheck_out().split(":")[1]:"00");
				}
			}			
		} 
		
		return update_OK;
	}
	
	public final boolean update_employment_address()
	{
		if(same_address_ENABLED)
		{									
			asignar_mismo_domicilio(EMPLOYMENT);						
			
		} else {
			
			clear_address();
		}	
		
		update_OK = service_employment.updateEmployment(employment);
		
		return update_OK;
	}
	
	public final boolean update_business()
	{		
		if(business.getSells_sigma() == null || business.getSells_sigma().equals("N"))
		{
			business.setSigma_number(null);
		}
		
		update_OK = service_business.update(business);	
		
		return update_OK;
	}
	
	public final boolean update_business_address()
	{				
		if(same_address_ENABLED)
		{									
			asignar_mismo_domicilio(BUSINESS);						
			
		} else {
			
			clear_address();
		}	
		
		update_OK = service_business.update(business);
		
		return update_OK;
	}
}
