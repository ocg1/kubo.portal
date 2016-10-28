package mx.com.kubo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

public abstract class DocumentLoaderAMO extends DocumentLoaderDMO
{
	protected void init_file_type()
	{
		file_type_PK = new FileTypePK(CONTRATO_CREDITO_FRIMADO, company_id);
		
		file_type = service_file_type.getFileTypeById(file_type_PK);
		
		nameFile = file_type.getAbreviation();		
		category = file_type.getFileCategory().getAbreviation();
		
		file_category_id = file_type.getFile_category_id();
	}
	
	 public boolean createDirectory(String path)
	 {
		 boolean result = false;
		 
		 try 
		 {
			 File file = new File(path);
			 
			 if (!file.isDirectory()) 
			 {
				 file.mkdirs();
				 
				 result = true;
					
			} else {
					
				result = true;
			}
				
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result = false;
		}
		 
		return result;
			
	 }
	 
	 public boolean deleteFileDirByEqualName(File file,String delthisFile) 
	 {
		boolean bandera = false;
		
		 try 
		 {
			 if(file.isDirectory())
			 {
				    File[] files = file.listFiles();
				    
				    for(int i = 0; i < files.length; i++) 
				    {
				    		if(!files[i].isDirectory())
				    		{
				    			if(files[i].getName().length()>delthisFile.length())
				    			{
					    			String toEqual=files[i].getName().substring(0, delthisFile.length());
					    			
					    			if(toEqual.equals(delthisFile))
					    			{
					    				files[i].delete();	
					    				bandera=true;
					    			}					    							    			
					    			
				    			}
				    		}
				    	/*
				        if(files[i].isDirectory()) {  
				         this.deleteFileDirByEqualName(files[i]);  
				        } else {  
				         files[i].delete();  
				        }  */
				     }
				  }
			
		} catch (Exception e) {
			bandera= false;
		} 
		 
		 return bandera;
		 }
	
	public String getNewName(String thisPath, String nameFile)
	{
			File f = new File(real_PATH + thisPath);
			
			boolean bandera = false;
			
			ArrayList<String> array = new ArrayList<String>();
			
			if(f.isDirectory())
			{
			    File[] files = f.listFiles();
			    
			    for(int i = 0; i < files.length; i++) 
			    {
		    		if(!files[i].isDirectory())
		    		{
		    			if(files[i].getName().length()>nameFile.length())
		    			{
			    			String toEqual=files[i].getName().substring(0, nameFile.length());
			    			
			    			if(toEqual.equals(nameFile))
			    			{
			    				array.add(files[i].getName());
			    				bandera=true;
			    			}					    							    			
			    			
		    			}
		    		}
			     }
			  }
			
			int i =0;
			
			if(bandera)
			{				
				for(String a : array)
				{
					String a1 = a.split("\\.")[0];
					
					if(a1.split("-").length > 1)
					{
						a1 = a1.split("-")[1];
						a1 = a1.split("_")[0];
						
						if(Integer.parseInt(a1) > i)
						{
							i = Integer.parseInt(a1);
						}
					}
				}				
			}
			
			i = i+1;
			nameFile = nameFile+"-"+i;
			
			return nameFile;
	}
	
	protected boolean saveFileByType(String fileName, int typeFile, String path, InputStream in)
	{
		try 
		{			 						 			 			 	           
	            changeCtrlPK = new Change_controlPK();
	    		changeCtrlPK.setProspectus_id(prospectus_id);
	    		changeCtrlPK.setCompany_id(company_id);
	    		
	    		changeCtrl = new Change_control();
	    		changeCtrl.setChange_controlPK(changeCtrlPK);
	    		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
			 
			 
			 String formatFile = null;
			 
			 try
			 {
				 formatFile =  fileName.substring(fileName.lastIndexOf(".")+1);
					
			 } catch (Exception e) {
				 
				formatFile = null;
			}
			 
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
           
           if(typeFile > 0)
           {
	        	Files thisFiles = service_files.getFileByTypeID(prospectus_id, company_id, proyect_loan_id, typeFile);
	        	
	            if(thisFiles==null)
	            {
	            	
	           	 thisFiles = new Files();
	           	 
	           	 FilesPK filePk=new FilesPK(prospectus_id, company_id, proyect_loan_id, typeFile);
	           	 
	           	 thisFiles.setFilesPk(filePk);
	           	 thisFiles.setUpload_date(new Date());	           	 
	           	 
	           	 changeCtrl.setOriginal_value(thisFiles.getLocation()==null?"":thisFiles.getLocation());
	           	 changeCtrl.setNew_value(path);
	           	 changeCtrl.setAfected_table("gn_file");
	           	 changeCtrl.setField("location");
       		
	           	 thisFiles.setLocation(path);
	           	 
	           	 if(typeFile == 44 || ( typeFile >= 63 && typeFile <= 66 ))
	           	 {
	           		 thisFiles.setReca_id(reca_id);
	           		 
	           	 } else {
	           		 
	           		thisFiles.setReca_id(null);
	           	 }
	           	 
	           	 thisFiles.setApproved("1");
	           	 
	           	 
/*	           	 
	           	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
	           	 {
	           		 metadata = Utilities.getMetadataPDF(new File(fileName));
	           		 
	           	 } else {            	
	           		 
	           		 metadata = ImageUtils.getMetadata(new File(fileName));
	           	 }
*/	           	 
	           	 
				 if(metadata != null)
				 {
					 thisFiles.setMetadata(metadata);
					 
				 } else {
					 
					 thisFiles.setMetadata(null);
				 }
						
				 service_files.addFile(thisFiles, prospectus_id, company_id);
					 
	            } else {
	            	
	           	 thisFiles.getFilesPk().setFile_type_id(typeFile);
	           	 
	           	 changeCtrl.setOriginal_value(thisFiles.getLocation()==null?"":thisFiles.getLocation());
	           	 changeCtrl.setNew_value(path);
	           	 changeCtrl.setAfected_table("gn_file");
	           	 changeCtrl.setField("location");
	           	 
	           	 thisFiles.setLocation(path);
	           	 thisFiles.setUpload_date(new Date());
	           	 
	           	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
	           	 {
	           		 metadata=Utilities.getMetadataPDF(new File(fileName));
	           		 
	           	 } else {
	           		 
	           		 metadata=ImageUtils.getMetadata(new File(fileName));
	           	 }
	           	 
				 if(metadata!=null)
				 {
					 thisFiles.setMetadata(metadata);
					 
				 } else {
					 
					 thisFiles.setMetadata(null);
				 }
				 
				 if( typeFile == 44 || ( typeFile>=63 && typeFile <= 66 ) )
				 {
	           		 thisFiles.setReca_id(reca_id);
	           		 
				 } else {
					 
	           		thisFiles.setReca_id(null);
				 }
					 
				 service_files.updateFile(thisFiles);
	            }
	            
           } else {
           	 if(proyect!=null)
           	 {           		            		            		 
    	    		switch (typeFile) 
    	    		{
    				case -1://asignando para Foto 1 del proyecto
    					proyect.setLogo(path);
    					if(proyect.getMetadata1()!=null){
    						proyect.setMetadata1(null);
    					}
    					
    					changeCtrl.setOriginal_value(proyect.getLogo()==null?"":proyect.getLogo());
      	           	 	changeCtrl.setNew_value(path);
      	           	 	changeCtrl.setAfected_table("ln_proyect");
      	           	 	changeCtrl.setField("logo");
    					
    					break;
    				case -2://asignando para Foto 2 del proyecto
    					proyect.setLogo2(path);
    					if(proyect.getMetadata2()!=null){
    						proyect.setMetadata2(null);
    					}
    					
    					changeCtrl.setOriginal_value(proyect.getLogo2()==null?"":proyect.getLogo2());
      	           	 	changeCtrl.setNew_value(path);
      	           	 	changeCtrl.setAfected_table("ln_proyect");
      	           	 	changeCtrl.setField("logo2");
    					
    					break;
    				case -3://asignando para Foto 3 del proyecto
    					proyect.setLogo3(path);
    					if(proyect.getMetadata3()!=null){
    						proyect.setMetadata3(null);
    					}
    					
    					changeCtrl.setOriginal_value(proyect.getLogo3()==null?"":proyect.getLogo3());
      	           	 	changeCtrl.setNew_value(path);
      	           	 	changeCtrl.setAfected_table("ln_proyect");
      	           	 	changeCtrl.setField("logo3");
    					
    					break;
    				default:
    					break;
    				}	
    	    		
    	    		if(service_proyect.update(proyect))
    	    		{
    	    			
    	    		}
    	    		
           	 }
           }
           
          
   		
   		changeCtrl.setComments("Actualización de documentos, mesa de control");
   		changeCtrl.setIp(sesion.getIP_address_client());
   		
   		if(service_change_control.addChangeControl(changeCtrl, prospectus_id,company_id))
   		{
   		
   		}
           
           return true;
          
           } catch (IOException e) {
          	 e.printStackTrace();
          	 return false;
           }
		
	}
}
