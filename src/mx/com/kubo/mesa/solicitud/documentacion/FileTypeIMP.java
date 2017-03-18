package mx.com.kubo.mesa.solicitud.documentacion;

import java.util.ArrayList;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.model.Files;

public class FileTypeIMP extends FileTypeAMO 
implements FileTypeIMO
{
	public void init()
	{					
		 for (Files file : files) 
		 {			
			 location = file.getLocation();
			 
			 format_index = location.lastIndexOf(".") + 1;			 
			  file_format = location.substring(format_index);
			        
			  int file_type_id = file.getFilesPk().getFile_type_id();
			        
			 file_category_name = file.getFileType().getFileCategory().getName();
			 			 
			 documentacion = ht_category_file.get(file_category_name);
			 
			 if(documentacion == null)
			 {
				 documentacion = new ArrayList<DocumentationDMO>();
			 }			
			 
			 if(!file_type_ENABLED)
			 {
				 init_documentacion_bean(file);
				 
				 documentacion.add(bean);
			 }
			 
			 else if(file_type_ENABLED && (this.file_type_id == file_type_id))
			 {
				 init_documentacion_bean(file);
				 
				 documentacion.add(bean);
			 }
			 
			 ht_category_file.put(file_category_name, documentacion);			
		 }
		 
		 init_file_type_list();		
	}
}
