package mx.com.kubo.session;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.gnNaturalPersonPK;

import org.springframework.stereotype.Service;

@Service @SuppressWarnings("serial")
public class SessionService extends SessionServiceDMO
implements Serializable
{	
	public final SessionBean getSesionBEan() 
	{		
		faces      = FacesContext.getCurrentInstance();
		resolver   = faces.getApplication().getELResolver();
		context_EL = faces.getELContext();
		sesion     = (SessionBean) resolver.getValue(context_EL, null, "sessionBean");	
				
		return sesion;
	}
	
	public final void redirigir(String url)
	{
		external = faces.getExternalContext();
		
		try 
		{
	        external.redirect(url);	
	        
		} catch (IOException e) {
			
		       e.printStackTrace();
		}			
	}
	
	public final Integer getProspectusFromAutocomplete() 
	{
		SearchSummaySession searchsum;
		searchsum = (SearchSummaySession) resolver.getValue(context_EL, null, "searchSummaySession");
		
		String value = searchsum.getSearchSummary();
		
		if(value != null)
		{
			/*
			value = proyectLoan.getProyectloanPk().getProyect_loan_id() + "::"
				  + proyectLoan.getProyectloanPk().getProyect_id()     + "::"
				  + proyectLoan.getProyectloanPk().getProspectus_id()  + "::"
				  + proyectLoan.getProyectloanPk().getCompany_id();
			*/
			
			String valueS[] = value.split("::");
			
			if(valueS != null && valueS.length > 3)
			{				
				prospectus_id = Integer.parseInt(valueS[2]);
				company_id    = Integer.parseInt(valueS[3]);
				
				if(prospectus_id != null && company_id != null )
				{					
					
				}								
			}						
		}	
		
		return 	prospectus_id;
	}
	
	public final Integer getCompanyFromAutocomplete()
	{
		return 	company_id;
	}
	
	
	public final Prospectus getProspectus(Integer prospecto_id, Integer company_id)
	{
		prospecto_PK = new ProspectusPK();
		
		prospecto_PK.setProspectus_id(prospecto_id);
		prospecto_PK.setCompany_id(company_id);
		
		return prospecto_service.getProspectusById(prospecto_PK);
	}
		
	public NaturalPerson getNaturalPerson(Integer prospectus_id, Integer company_id) 
	{
        persona_PK = new gnNaturalPersonPK();
        
        persona_PK.setCompany_id(company_id);
        persona_PK.setProspectus_id(prospectus_id);
        
        return persona_service.getNaturalPersonById(persona_PK);
	}
	
}
