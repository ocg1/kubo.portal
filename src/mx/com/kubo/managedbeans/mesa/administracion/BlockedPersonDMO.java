package mx.com.kubo.managedbeans.mesa.administracion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.services.BlockedPersonService;
import mx.com.kubo.tools.Utilities;

public abstract class BlockedPersonDMO implements BlockedPersonIMO 
{
	protected BlockedPersonService service_blocked_person;
	protected BlockedPerson person;
	
	protected File file_uploaded;
	protected File file_LOG;
	protected FileReader fr;
	protected FileWriter fw;
	protected BufferedReader br;
	protected BufferedWriter bw;
	
	protected Date blocked_date;
	
	protected Locale locale;	
	protected SimpleDateFormat format;
		
	protected StringBuilder sb_log;	
		
	protected String path_file_LOG = "";			
	protected String row_DATA   = "";
	protected String file_uploaded_path;
	protected String file_name;
	protected String file_name_LOG;
	
	protected String[] blocked_person_ROW;
	
	protected final String SEPARADOR  = "#";
	
	protected Integer citizenship;
	
	protected int counter_row;
	protected int counter_insert;
		
	protected boolean b_error;
	
	protected final int ROW_TITLES = 1;
	
	protected BlockedPersonDMO()
	{
		service_blocked_person = Utilities.findBean("blockedPersonServiceImp");						
		
		locale = new Locale("es","mx");		
		format = new SimpleDateFormat("dd-MMM-yy", locale);		   
		
		sb_log = new StringBuilder(); 
		
		blocked_person_ROW = new String[11];
	}
	
	public String getPath_file_LOG()
	{
		return path_file_LOG;
	}
	
	public void setCitizenship(Integer citizenship)
	{
		this.citizenship = citizenship;
	}
	
	public void setFile_uploaded_path(String file_uploaded_path) 
	{
		this.file_uploaded_path = file_uploaded_path;	
		
		init_file_uploaded();
	}

	private void init_file_uploaded() 
	{
		try
		{
			file_uploaded = new File (file_uploaded_path);
			
			if(file_uploaded != null)
			{
				    file_name = file_uploaded.getName();
				path_file_LOG = file_uploaded.getPath();
	    		
	    		file_name_LOG = file_name.split("\\.")[0];
	    		
	    		int index = path_file_LOG.indexOf(file_name);
	    		
	    		path_file_LOG = path_file_LOG.substring(0, index);
	    	    
	    		path_file_LOG += file_name_LOG + "_LOG.csv";
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
