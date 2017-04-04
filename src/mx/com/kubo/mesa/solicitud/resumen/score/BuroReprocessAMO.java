package mx.com.kubo.mesa.solicitud.resumen.score;

import java.util.Date;

import com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO;

import mx.com.kubo.model.ServiceCalling;

public abstract class BuroReprocessAMO extends BuroReprocessDMO
{
	protected void init_request()
	{
		try
		{
			request = new ReprocessBuroDataDTO();		
			request.setBusinessType("3227");
			request.setMxTipoConsultaBuro(ID_PROVIDER);
			request.setMxSolicitudBuro(mxSolicitudBuro);
			request.setCompanyId(Integer.toString(company_id));
			request.setProspectusId(Integer.toString(prospectus_id));
			request.setProspectusIdPromotor(Integer.toString(prospectus_id));	
			request.setAge(Integer.toString(age));
			request.setGenderId(Integer.toString(gender_id));
			request.setResidenceId(Integer.toString(residence_id));
		
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
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
				srvCall.setInfo(SGB_RESPONSE_MSG + response_msg);
			break;
			
			case ERROR:							
				srvCall.setException(response_msg);
			break;
		}

		service_calling.saveServiceCall(srvCall);
	}
}
