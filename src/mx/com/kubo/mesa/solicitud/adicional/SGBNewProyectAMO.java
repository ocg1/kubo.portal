package mx.com.kubo.mesa.solicitud.adicional;

import java.util.Date;

import com.mx.kubo.sgbws.models.dto.NewProjectRequestDTO;

import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ServiceCalling;

public abstract class SGBNewProyectAMO extends SGBNewProyectDMO
{
	protected void init_service_calling(Integer status) 
	{
		srvCall = new ServiceCalling();

		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(company_id);		
		srvCall.setProspectus_id(prospectus_id);
		srvCall.setStatus(status);
		
		switch(status)
		{
			case INIT:
				srvCall.setInfo(SGB_INIT_MSG);
			break;
			
			case RESPONSE_OK:								
				if(response.getMessage().length() > 120)
				{
					response_msg = response.getMessage().substring(0,120);
					
				} else {
					
					response_msg = response.getMessage();
				}
				
				srvCall.setInfo(SGB_RESPONSE_MSG + response_msg);
			break;
			
			case ERROR:
				if(response.getMessage().length() > 120)
				{
					response_msg = response.getMessage().substring(0,120);
					
				} else {
					
					response_msg = response.getMessage();
				}
								
				srvCall.setException(response_msg);
			break;
		}

		service_calling.saveServiceCall(srvCall);
	}
	
	protected void init_new_project_request() 
	{
		request = new NewProjectRequestDTO();
		
		request.setCompanyId (Integer.toString(company_id));
		request.setProspectId(Integer.toString(prospectus_id));
		request.setProjectId (Integer.toString(proyect_loan_id));
		request.setProductId (Integer.toString(type_id));
		request.setMxNumPagos(Integer.toString(term_id));
		request.setMxFrec    (Integer.toString(frequency_id));
		request.setAmount    (Double.toString(ammount));
		request.setMxTasa    (Double.toString(rate));
		request.setMxComisionApertura(Double.toString(opening_commission));
		request.setBursolnum(bursolnum);
		request.setLoan_type(loan_type);									
		request.setIs_collection_solution(is_collection_solution);
		request.setIs_automatic_aproved(is_automatic_aproved);
	}
	
	protected void init_creation_date() 
	{	
		if(membership == null)
		{			
			membershipPK = new MembershipPK();
			
			membershipPK.setCompany_id(company_id);
			membershipPK.setProspectus_id(prospectus_id);
			
			membership =  membershipservice.getMembershipById(membershipPK);				
		}
		
		if( membership.getFile_creation_date() == null )
		{
			membership.setFile_creation_date(new Date());
			
			membershipservice.update(membership);
		}
	}
	
	protected void init_cambio_status() 
	{
		if( proyect_loan.getStatus_id() != 11 )
		{
		
			change_status_ENABLED = proyectloanService.cambioStatus(proyect_loan, 1, new Date());
		
		} else {
			
			change_status_ENABLED = proyectloanService.cambioStatus(proyect_loan, 11, new Date());			
		}
	}
}
