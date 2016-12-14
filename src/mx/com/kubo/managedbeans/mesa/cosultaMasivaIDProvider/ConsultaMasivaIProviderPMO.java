package mx.com.kubo.managedbeans.mesa.cosultaMasivaIDProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.TrackingCode;
import mx.com.kubo.tools.Utilities;

public class ConsultaMasivaIProviderPMO extends ConsultaMasivaIProviderDMO {
	
	
	
	protected String cargaDocumento( FileUploadEvent event, Date fecha_carga , String campana , FacesContext faces  )
	{
	
		String formatFile = "";
		String component_id = event.getComponent().getId();
		String nameFile="alta_masiva";
		String fileName = null;
		
		SimpleDateFormat smf = new SimpleDateFormat("dd' de 'MMMM' de 'yyyy' 'HH':'mm':'ss");
		
		
		
		String fecha =  smf.format( fecha_carga ) ;
		
		String pathDocument = "";
		String pathHistoric = "";
		
		String realPath = (faces.getExternalContext().getRealPath("//resources//"));
		
		if(event != null)
		{
			try
			{
				
				formatFile = event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
				
			} catch (Exception e) {
				
				formatFile = ".txt";
				
			}
			
			pathDocument = "/documents/alta_masiva_id_provider/";		
			pathHistoric = "/historic/alta_masiva_id_provider/";
			
			Utilities.createDirectory(realPath + pathDocument );
			Utilities.createDirectory(realPath + pathHistoric );
			
		}
		
		try{
		
			InputStream in = event.getFile().getInputstream();
			
			System.out.println( "component_id: " + component_id + " formatFile: " + formatFile );
			
			nameFile+="_"+campana+"_"+fecha+formatFile;
			
			fileName = realPath + pathDocument+nameFile;
			
			OutputStream out = new FileOutputStream(new File(fileName));
            
            int read = 0;
            byte[] bytes = new byte[1024];
         
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }
         
            out.flush(); 
            out.close();
            
            String 	fileNameHist = realPath + pathHistoric+nameFile;
            
            Utilities.copyFile(fileNameHist, in);
            
            in.close();
            
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
		return fileName;
		
	}
	
	protected void insertaRegistro( String[] elementos, String code ){
		
		IdProviderMassive massive = new IdProviderMassive();
		
		massive.setCompany_id(COMPANY_ID);
		massive.setLoad_date(new Date());
		massive.setProspectus_id( Integer.parseInt(elementos[0]));
		massive.setStatus("CARGADO");
		massive.setTracking_code(code);
		
		idprovidermassiveservice.saveIdProviderMassive(massive);
		
	}
	
	protected String generaCodigo(){
		
		try{
			
			boolean flag = false ;
			
			String code = "";
			
			while( !flag ){
				
				code = "";
				
				Random rand = new Random();

				 int randomNum = rand.nextInt((max - min) + 1) + min;

				 String randomStr =  randomNum+"";
				    
				 while(randomStr.length()<7){
					  
					 randomStr = "0"+randomStr;
					 
				 }
				
				code = randomStr;
				
				TrackingCode tracking = new TrackingCode();
				
				tracking.setCreation_date( new Date() );
				tracking.setDescription("CONSULTA MASIVA IDPROVIDER");
				tracking.setEnabled("1");
				tracking.setStatus("ACTIVO");
				tracking.setTracking_code(code);
				
				flag = idprovidermassiveservice.saveTrackingCode(tracking);
				
			}
			
			return code;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	protected void iniciaConsultaMasiva(String pathFile, Date fecha_carga, String code , FacesContext faces) 
	{				
		
		System.out.println( "iniciaConsultaMasiva ..." );
		
		FileReader 		fr = null;
		BufferedReader 	br = null;
		
		try 
	    {
	    	File archivo = new File (pathFile);
	    	fr      = new FileReader (archivo);
	    	br      = new BufferedReader(fr);
	    	
	    	String linea = "";
	    	
		    while((linea = br.readLine()) != null)
		    {		        	 
		    	String registro =  (linea);
		    	
		    	String[] elementos = registro.split(separador);
		    	
		    	insertaRegistro( elementos,  code );
		    	
		    	
		    }
		    
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		
		
		
	}
	
	

}
