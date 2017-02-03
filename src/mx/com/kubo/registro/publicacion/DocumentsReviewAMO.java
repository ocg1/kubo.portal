package mx.com.kubo.registro.publicacion;

import java.util.Date;

import com.soa.webServices.util.InputParam;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.ServiceCalling;

public abstract class DocumentsReviewAMO extends DocumentsReviewDMO 
{
	protected void save_service_calling(Integer status, String info) 
	{
		srvCall  = new ServiceCalling();
		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(company_id);
		srvCall.setInfo(info);
		srvCall.setProspectus_id(prospectus_id);
		srvCall.setStatus(status);
		
		service_calling.saveServiceCall(srvCall);
	}
	
	protected void init_files() 
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
			 					
				 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "1", file.getLocation(), params, null, prospectus_id+"");
				 i++;
			
				 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "42", file.getLocation(), params, null, prospectus_id+"");
				 i++;
			
			}else{
			
				files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), file.getFilesPk().getFile_type_id()+"", file.getLocation(), params, null, prospectus_id+"");
				i++;	
			
			}
		}
	}
}
