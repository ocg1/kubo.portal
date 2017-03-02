package mx.com.kubo.mesa.administracion;

import java.io.InputStream;
import java.io.OutputStream;

import org.primefaces.model.UploadedFile;

import mx.com.kubo.managedbeans.SessionBean;

public abstract class DocumentUploaderDMO 
implements DocumentUploaderIMO
{
	protected SessionBean sesion;
	
	protected UploadedFile file_uploaded;
	
	protected InputStream in;
	protected OutputStream out;
	
	protected String realPath;
	protected String formatFile = "";
	protected String nameFile;
	protected String file_uploaded_path;		
	protected String pathDocument = "";
	protected String pathHistoric = "";	
	
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		int company_id = sesion.getCompany_id();
		
		pathDocument = "/documents/cia_" + company_id + "/blocked_person/";		
		pathHistoric = "/historic/cia_"  + company_id + "/blocked_person/";
	}

	public void setFile(UploadedFile file_uploaded)
	{
		this.file_uploaded = file_uploaded;
		
		if(file_uploaded != null)
		{
			try
			{
				String filename = file_uploaded.getFileName();
				
				int extension = filename.lastIndexOf(".");
				
				formatFile = filename.substring(extension);
				
				nameFile = "blocked_person" + formatFile;								
				
			} catch (Exception e) {
				
				e.printStackTrace();		
			}								
		}
	}
	
	public void setRealPath(String realPath)
	{
		this.realPath = realPath;
	}
	
	public String getFile_uploaded_path() 
	{
		return file_uploaded_path;
	}
}
