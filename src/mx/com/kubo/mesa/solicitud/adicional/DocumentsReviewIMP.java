package mx.com.kubo.mesa.solicitud.adicional;

import java.util.Date;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.DocumentsReviewRequest;
import com.soa.webServices.responses.WsSgbResponse;
import com.soa.webServices.util.InputParam;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ServiceCalling;

public class DocumentsReviewIMP extends DocumentsReviewAMO
implements DocumentsReviewIMO
{
	public void init(Proyect proyect)
	{
		try
		{
					
		locator = new WsSgbRiskServiceLocator();
		service = locator.getWsSgbRisk();
		
		srvCall = new ServiceCalling();
		res     =  new WsSgbResponse();
		
		int company_id      = proyect_loan.getProyectloanPk().getCompany_id();
		int prospectus_id   = proyect_loan.getProyectloanPk().getProspectus_id();
		int proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		NaturalPerson person = proyect_loan.getPerson();
		
		lista_archivos = filesService.getListFilesByProspect(prospectus_id, company_id, proyect_loan_id);
		
		proyect = validaPurpose(proyect);
		
		if(lista_archivos.size() > 0)
		{
			com.soa.webServices.util.Files[] files = null ;
			
			if( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 ) ){
			
				files = new com.soa.webServices.util.Files[lista_archivos.size() + 1];
			
			}else{
				files = new com.soa.webServices.util.Files[lista_archivos.size()];
			}
			
			int i = 0;
			
			for(Files file : lista_archivos )
			{
				params = null;
				
				 if(file.getFilesPk().getFile_type_id() == 1 || ( ( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 )) && ( file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 133 || file.getFilesPk().getFile_type_id().intValue() == 76 ) ) )
				 {
					 params = new InputParam[5];
/*
				     params[0] = new InputParam("1", "6", "Clave de elector", person.getMx_ife_cveelector());
				     params[1] = new InputParam("1", "7", "Emisión",          person.getMx_ife_numemision() +"");
				     params[2] = new InputParam("1", "10", "ocr",             person.getMx_ife_numvertical() );
				     params[3] = new InputParam("1", "11", "seccion",         person.getMx_ife_seccion() );  
*/
					 String name = "";
					 
					if (person.getMiddle_name()!=null&&!person.getMiddle_name().isEmpty())
					{
						name = person.getFirst_name().trim() + " " + person.getMiddle_name().trim();
						
					} else {
						
						name = person.getFirst_name().trim();
					}
					
					if(person.getFather_last_name()!=null&&!person.getFather_last_name().isEmpty())
					{
						name = name + " " + person.getFather_last_name();
					}
					
					if(person.getMother_last_name()!=null&&!person.getMother_last_name().isEmpty())
					{
						name = name + " " + person.getMother_last_name();
					}
				     
					 params[0] = new InputParam("1", null, "1", name);
					 params[1] = new InputParam("1", null, "2", person.getMx_curp());
					 params[2] = new InputParam("1", "6",  "3", person.getMx_ife_cveelector());
					 params[3] = new InputParam("1", "7",  "4", person.getMx_ife_numemision() +"");
					 params[4] = new InputParam("1", "11", "11",person.getMx_ife_seccion() );
				     
				 } else if(file.getFilesPk().getFile_type_id() == 42) {
					 
					 params    = new InputParam[1];
				     params[0] = new InputParam("1", "10", "9", person.getMx_ife_numvertical());
				 }
				 
				 if(( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 )) && ( file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 133 || file.getFilesPk().getFile_type_id().intValue() == 76 ) ){
				 					
					 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "1", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					 i++;
				
					 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "42", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					 i++;
				
				}else{
				
					files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), file.getFilesPk().getFile_type_id()+"", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					i++;	
				
				}
			}
			
			DocumentsReviewRequest docs = new DocumentsReviewRequest(files,proyect_loan.getProyectloanPk().getProyect_loan_id()+"");

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview");
			srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(1);
			
			service_calling.saveServiceCall(srvCall);
			
			res = service.documentsReview(docs );
			
			if(res.getStatus().equals("0"))
			{					
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview: "+((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
				srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(2);
				
				service_calling.saveServiceCall(srvCall);
									
			} else {
				
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
				srvCall.setException(((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
				srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(3);
				
				service_calling.saveServiceCall(srvCall);
				//return false;
			}			
		}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
