package mx.com.kubo.mesa.solicitud.documentacion;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.model.Files;

public abstract class FileTypeDMO implements FileTypeIMO
{
	protected FilesTypeCategoryBean fileCateg;
	
	protected DocumentationDMO bean;		
	
	protected Set<String> claves;
	
	protected List <Files> files;
	protected List <FilesTypeCategoryBean>  file_type_list;
	protected List<DocumentationDMO> documentacion;
	
	protected Hashtable<String, List<DocumentationDMO>> ht_category_file;
	
	protected String location;
	protected String real_path;
	protected String file_format;
	protected String file_category_name;
	
	protected Integer file_type_id;
	
	protected int format_index;
	
	protected boolean file_type_ENABLED;
	
	protected FileTypeDMO()
	{
		ht_category_file = new Hashtable<String, List<DocumentationDMO>>();
	}
	
	public void setReal_path(String real_path)
	{
		this.real_path = real_path;
	}
	
	public void setFiles(List <Files> files)
	{
		this.files = files;
	}
	
	public void setFile_type_id(Integer file_type_id)
	{
		this.file_type_id = file_type_id;
		
		file_type_ENABLED = true;
	}
	
	public List <FilesTypeCategoryBean> getFile_type_list()
	{
		return file_type_list;
	}
}
