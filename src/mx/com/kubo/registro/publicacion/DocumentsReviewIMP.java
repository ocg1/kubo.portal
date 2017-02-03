package mx.com.kubo.registro.publicacion;

import java.util.Date;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.DocumentsReviewRequest;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.model.ServiceCalling;

public class DocumentsReviewIMP extends DocumentsReviewAMO
implements DocumentsReviewIMO
{
	public void init()
	{
		try
		{					
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();
					
			response =  new WsSgbResponse();
			
			lista_archivos = filesService.getListFilesByProspect(prospectus_id, company_id, 1);
		
			if(lista_archivos.size() > 0)
			{
				init_files();
				
				request = new DocumentsReviewRequest(files, "");
	
				save_service_calling(1, MSG_INIT_CALL);
				
				response = service.documentsReview(request);
			
				if(response.getStatus().equals("0"))
				{										 
					if(response.getMessage().length() > 120)
					{					
						response_msg = response.getMessage().substring(0,120);
					
					} else {
						
						response_msg = response.getMessage();	
					}
					
					String info = MSG_SUCCESS + response_msg;
					
					save_service_calling(2, info);
										
				} else {
				
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(company_id);
					srvCall.setException(((response.getMessage().length()>120)?response.getMessage().substring(0,120):response.getMessage()));
					srvCall.setProspectus_id(prospectus_id);
					srvCall.setStatus(3);
					
					service_calling.saveServiceCall(srvCall);
				}			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
