package mx.com.kubo.mesa.solicitud.documentacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;

public abstract class FileTypeAMO extends FileTypeDMO 
{
	protected  void init_documentacion_bean(Files file) 
	{
		FilesPK pk = file.getFilesPk();
		
		  Integer prospectus_id = pk.getProspectus_id();
		  Integer    company_id = pk.getCompany_id();
		        Integer file_id = pk.getFile_id();		
		Integer proyect_loan_id = pk.getProyect_loan_id();		
		  Integer  file_type_id = pk.getFile_type_id();
		  
		     String description = file.getFileType().getName();		   
		
		 bean = new DocumentationDMO();
		 bean.setProspectus_id(prospectus_id);
		 bean.setCompany_id(company_id);
		 bean.setDescription(description);
		 bean.setUrlImg(location);
		 bean.setOriginalPathImg(real_path + location);
		 bean.setFormatFile(file_format);		 
		 bean.setFile_id(file_id);
		 bean.setProyect_loan_id(proyect_loan_id);
		 bean.setApproved(file.getApproved());		 		 		 
		 bean.setTypeFile(file_type_id);
		 
		 boolean contratos_ENABLED = ( file_type_id == 44 || ( file_type_id >= 63 && file_type_id <= 66 ));
		 
		 if(contratos_ENABLED)
		 {			 
			 if(file.getReca_id() != null)
			 {				 
				 bean.setReca_number(file.getReca().getReca_number());
			 
			 } else {
				 
				 bean.setReca_number("No Definido");					 
			 }				 
		 }
	}
	
	protected void init_file_type_list() 
	{
		 claves = ht_category_file.keySet();
		 
		 file_type_list = new  ArrayList<FilesTypeCategoryBean>();
		 
		 for(String clave : claves)
		 {
			 List<DocumentationDMO> documentacion = ht_category_file.get(clave);
			 
			 fileCateg = new FilesTypeCategoryBean(clave, documentacion);
			 
			 file_type_list.add(fileCateg);
		 }
	}
}
