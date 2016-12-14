package mx.com.kubo.managedbeans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;

import mx.com.kubo.controller.efl_connect.EflConnect;
import mx.com.kubo.tools.Utilities;
import sun.misc.BASE64Encoder;

@ManagedBean
@ViewScoped
public class TestUploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4929472689622270313L;


	public void handleFileCredFmDos(FileUploadEvent event)
	{
		String  typeFile = (String) event.getComponent().getAttributes().get("typefile").toString();
		event.getComponent().setId("file_credFm2");
		System.out.println( "typeFile: " + typeFile);
		handleFileUpload(event);
	}
		
	public void handleFileUpload(FileUploadEvent event) 
	{	
		
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		
		String realPath = (faces.getExternalContext().getRealPath("//resources//"));
		
//		requestContext.addPartialUpdateTarget("panelScript");
//		requestContext.addPartialUpdateTarget(":actualPage");
			
		String formatFile="";
		
		if(event != null)
		{
			try
			{
				formatFile = event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));				
			} catch (Exception e) {
				formatFile = ".jpg";
			}
			
		}
		
		
		String pathDocument = "/tmpFiles/";	
		
		String nameFile = "";
		
		if(  true )
		{
			
			
			Utilities.createDirectory(realPath + pathDocument );
			
				
			
				nameFile += Utilities.getRandomName() 
						 + formatFile;
			
			try 
			{
				
				String file_name = realPath + pathDocument +  nameFile;
				
				boolean save_file = saveFileByType(file_name, event.getFile().getInputstream());
				
				System.out.println( "Archivo Guardado Exitosamente: " + save_file );
				
				String imgstr = convertImgToBase64( file_name , formatFile.replace(".", "") );
				
				System.out.println( "ImgBase64: " + imgstr ); 
				
				//TEST EFL_CONNECT
				
				//EflConnect efl_conn = new EflConnect();
				
				//efl_conn.EFLResult("109921");
				
			} catch (Exception e) {
				
				System.out.println("Error al guardar el archivo");
				e.printStackTrace();
				
			}
			
		}

	}
	
	
	private String convertImgToBase64( String path , String imgType ){
		
		try{
		
			BufferedImage img = ImageIO.read(new File( path ));
	        String imgstr;
	        imgstr = encodeToString(img, imgType);
	        
	        return imgstr;
        
		}catch(Exception e){
			
			e.printStackTrace();
			
			return null ;
		}
		
	}
	
	private  String encodeToString(BufferedImage image, String type) {
        
		String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	
	
	public boolean saveFileByType(String fileName,InputStream in)
	{
		 try 
		 {
			 
			 
             OutputStream out = new FileOutputStream(new File(fileName));
             
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) 
             {
                 out.write(bytes, 0, read);
             }
          
             in.close();
             out.flush(); 
             out.close();
             return true;
             
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
			 
		 }
		
	}
	
}
