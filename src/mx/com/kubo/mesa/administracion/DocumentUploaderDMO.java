package mx.com.kubo.mesa.administracion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import mx.com.kubo.managedbeans.SessionBean;

public abstract class DocumentUploaderDMO 
implements DocumentUploaderIMO
{
	protected SessionBean sesion;
	
	protected UploadedFile file;
	
	protected SimpleDateFormat smf;
	
	protected Date fecha_carga;
	
	protected String realPath;
	protected String formatFile = "";
	//String component_id = event.getComponent().getId();
	protected String nameFile = "blocked_person";
	protected String file_uploaded_path;		
	protected String pathDocument = "";
	protected String pathHistoric = "";
	protected String fecha;
	
	protected DocumentUploaderDMO()
	{
		smf = new SimpleDateFormat("yyyyMMdd");
		
		fecha_carga = new Date();
		
		fecha =  smf.format( fecha_carga ) ;
	}
	
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		int company_id = sesion.getCompany_id();
		
		pathDocument = "/documents/cia_" + company_id + "/blocked_person/";		
		pathHistoric = "/historic/cia_"  + company_id + "/blocked_person/";
	}

	public void setFile(UploadedFile file)
	{
		this.file = file;
		
		if(file != null)
		{
			try
			{
				String filename = file.getFileName();
				
				int extension = filename.lastIndexOf(".");
				
				formatFile = filename.substring(extension);
				
			} catch (Exception e) {
				
				formatFile = ".txt";				
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
	
	public Date getFecha_carga()
	{
		return fecha_carga;
	}
}
