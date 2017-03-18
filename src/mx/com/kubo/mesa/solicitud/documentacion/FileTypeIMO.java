package mx.com.kubo.mesa.solicitud.documentacion;

import java.util.List;

import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.model.Files;

public interface FileTypeIMO 
{
	void setReal_path(String real_path);
	
	 void setFile_type_id(Integer file_type_id);
	 
	void setFiles(List <Files> files);
	
	void init();
	
	List <FilesTypeCategoryBean> getFile_type_list();
}
