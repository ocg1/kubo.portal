package mx.com.kubo.managedbeans.mesa.administracion;

public interface BlockedPersonIMO 
{
	void init();

	void setFile_uploaded_path(String file_uploaded_path);
	
	void setCitizenship(Integer citizenship);
	
	String getPath_file_LOG();
}
