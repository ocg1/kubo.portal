package mx.com.kubo.managedbeans.generales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;


@ManagedBean @ViewScoped
public class DocumentUpload extends DocumentUploadDMO
implements Serializable 
{
	private static final long serialVersionUID = 8529972011030137609L;
		
	public boolean fileUpload(InputStream stream,Proyect proyect,int company_id,int prospectus_id,int file_category, int file_type_id,boolean deleteHistory, boolean isControl,String formatFile, int proyect_loan_id,Integer reca_id) 
	{
		try
		{
		boolean flag;
			
		String pathDocument = "/documents/cia_" + company_id+"/pros_" + prospectus_id+ "/";		
		String pathHistoric = "/historic/cia_"+company_id+"/pros_" + prospectus_id+"/";
		
		if(file_type_id < 0)
		{
			nameFile = getNameFile(file_type_id);
			category = "photo";
			
		} else {
			FileType fileType=fileTypeService.getFileTypeById(new FileTypePK(file_type_id, company_id));
			nameFile = fileType.getAbreviation();
			category = fileType.getFileCategory().getAbreviation();
		}
		
		String nameFileHist = "";
		//Antes
		//String pathFile = createPathRemoveExistFile(file_category,pathDocument,pathHistoric,nameFile, deleteHistory);
		String pathFile = createPathRemoveExistFile(file_category,pathDocument,pathHistoric,nameFile, deleteHistory, proyect_loan_id);
		if(!deleteHistory){
			
			nameFileHist = getNewName(pathHistoric+pathFile,  nameFile);
			
			nameFileHist+="_"+proyect_loan_id+"_"+prospectus_id+"_"+ (proyect!=null?proyect.getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
		}else{
			nameFileHist = nameFile+formatFile;
		}			
		
			nameFile+="_"+proyect_loan_id+"_"+prospectus_id+"_"+ (proyect!=null?proyect.getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName();
			if(isControl){
				nameFile+="-control";
			}
			nameFile+=formatFile;
			
			boolean bool1 = saveFileByType(getRealPath() + pathDocument+pathFile+nameFile,file_type_id,pathDocument+pathFile+nameFile,stream,prospectus_id,company_id,proyect, proyect_loan_id, reca_id);
			
			InputStream stream2 =new FileInputStream(getRealPath() + pathDocument+pathFile+nameFile);
			
			boolean bool2 = Utilities.copyFile(getRealPath() + pathHistoric+pathFile+nameFileHist,stream2);
			if( bool1 && bool2){
				flag = true;
			}else{
				flag = false;
			}
			
			
			return flag;
			
		} catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}						
	}
	
	private String getRealPath()
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//");
	}
	
	private boolean saveFileByType(String fileName,int typeFile,String path,InputStream in,int prospectus_id, int company_id,Proyect proyect, int proyect_loan_id, Integer reca_id){
		 try {
			 String metadata;
			 //log.info("!!!!!!!!!!!!!!!!! RUTA ARCHIVO= "+path);
			 
			 
			 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	            SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance()
	                    .getApplication().getELResolver()
	                    .getValue(elContext, null, "sessionBean");
	            
	            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
	    		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    		
	    	    if(ipAddress == null)  
	    	        {  
	    	          ipAddress = httpServletRequest.getRemoteAddr();  
	    	        }
	            
	            Change_controlPK changeCtrlPK=new Change_controlPK();
	    		changeCtrlPK.setProspectus_id(prospectus_id);
	    		changeCtrlPK.setCompany_id(company_id);
	    		
	    		Change_control changeCtrl=new Change_control();
	    		changeCtrl.setChange_controlPK(changeCtrlPK);
	    		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
			 
			 
			 String formatFile=null;
			 
			 try{
					formatFile= fileName.substring(fileName.lastIndexOf(".")+1);				
				}
				catch (Exception e) {					
					formatFile=null;
				}
			 
            OutputStream out = new FileOutputStream(new File(fileName));
            
            int read = 0;
            byte[] bytes = new byte[1024];
         
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
         
            in.close();
            out.flush(); 
            out.close();
            if(typeFile>0){
	        	Files thisFiles=filesService.getFileByTypeID(prospectus_id, company_id, proyect_loan_id, typeFile);
	            if(thisFiles==null){
	            	
	           	 thisFiles = new Files();
	           	 //FilesPK filePk=new FilesPK(prospectus_id, company_id);
	           	 FilesPK filePk=new FilesPK(prospectus_id, company_id, proyect_loan_id, typeFile);
	           	 thisFiles.setFilesPk(filePk);
	           	 thisFiles.setUpload_date(new Date());
	           	 //thisFiles.getFilesPk().setFile_type_id(typeFile);
	           	 
	           	 changeCtrl.setOriginal_value(thisFiles.getLocation()==null?"":thisFiles.getLocation());
	           	 changeCtrl.setNew_value(path);
	           	 changeCtrl.setAfected_table("gn_file");
	           	 changeCtrl.setField("location");
        		
	           	 thisFiles.setLocation(path);
	           	 
	           	 if( typeFile == 44 || ( typeFile>=63 && typeFile <= 66 ) )
	           		 thisFiles.setReca_id(reca_id);
	           	 else
	           		thisFiles.setReca_id(null);
	           	 
	           	 thisFiles.setApproved("1");
	           	 
	           	 
	           	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
	           		 metadata=Utilities.getMetadataPDF(new File(fileName));
	           	 else            		 
	           		 metadata=ImageUtils.getMetadata(new File(fileName));
	           	 
					 if(metadata!=null)
						 thisFiles.setMetadata(metadata);
					 else
						 thisFiles.setMetadata(null);
						
	           	 filesService.addFile(thisFiles, prospectus_id, company_id);
	            }
	            else{            	 
	           	 thisFiles.getFilesPk().setFile_type_id(typeFile);
	           	 
	           	 changeCtrl.setOriginal_value(thisFiles.getLocation()==null?"":thisFiles.getLocation());
	           	 changeCtrl.setNew_value(path);
	           	 changeCtrl.setAfected_table("gn_file");
	           	 changeCtrl.setField("location");
	           	 
	           	 thisFiles.setLocation(path);
	           	 thisFiles.setUpload_date(new Date());
	           	 
	           	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
	           		 metadata=Utilities.getMetadataPDF(new File(fileName));
	           	 else            		 
	           		 metadata=ImageUtils.getMetadata(new File(fileName));
	           	 
				 if(metadata!=null)
					 thisFiles.setMetadata(metadata);
				 else
					 thisFiles.setMetadata(null);
				 
				 if( typeFile == 44 || ( typeFile>=63 && typeFile <= 66 ) )
	           		 thisFiles.setReca_id(reca_id);
	           	 else
	           		thisFiles.setReca_id(null);
					 
	           	 filesService.updateFile(thisFiles);
	            }
            }else{
            	 if(proyect!=null){
            		 
            		 
            		 
     	    		switch (typeFile) {
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
     	    		
     	    		if(proyectService.update(proyect)){
     	    			
     	    		}
     	    		
            	 }
            }
            
           
    		
    		changeCtrl.setComments("Actualización de documentos, mesa de control");
    		changeCtrl.setIp(ipAddress);
    		
    		if(changeControlService.addChangeControl(changeCtrl, prospectus_id,company_id)){
    			//log.info("Se guardaron los datos en tabla de control");
    			
    		}//else
            
            return true;
           
            } catch (IOException e) {
           	 log.info("!!!!!!!!!! UUUUPS ERROOOOOOR AL SUBIR EL ARCHIVO  "+e.getMessage());
           	 return false;
            }
		
	}
	
	private String getNameFile(Integer typeFileID){
		String nameFile="";
		switch (typeFileID){
		case -1://establece parte del nombre del proyecto foto 1
			nameFile="proyectphoto1";
			break;
		case -2://establece parte del nombre del proyecto foto 2
			nameFile="proyectphoto2";
			break;
		case -3://establece parte del nombre del proyecto foto 3
			nameFile="proyectphoto3";
			break;
		}
		return nameFile;
	}
	
		String pathFile="";
		//Antes
		//private String createPathRemoveExistFile(int fileCategory,String pathDocument, String pathHistoric,String nameFile, boolean deleteHistory){
		private String createPathRemoveExistFile(int fileCategory,String pathDocument, String pathHistoric,String nameFile, boolean deleteHistory, int proyect_loan_id){
		
		/*
		switch (fileCategory){
			
		case -1:
			Utilities.createDirectory(getRealPath() + pathDocument+category);
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
				String thisPath = pathHistoric+category;
				nameFile = getNewName(thisPath,  nameFile);
			}
			pathFile = category+"/";
			break;
		
		case 1:
			Utilities.createDirectory(getRealPath() + pathDocument+category);
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
				String thisPath = pathHistoric+category;
				nameFile = getNewName(thisPath,  nameFile);
			}
			pathFile = category+"/";
			break;
		case 2:
			Utilities.createDirectory(getRealPath() + pathDocument+category);
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
				String thisPath = pathHistoric+category;
				nameFile = getNewName(thisPath,  nameFile);
			}
			
			pathFile=category+"/";
			break;
		case 3:
			Utilities.createDirectory(getRealPath() + pathDocument+category);				
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
				String thisPath = pathHistoric+category;
				nameFile = getNewName(thisPath,  nameFile);
			}
			pathFile = category+"/";
			break;
		case 4:
			Utilities.createDirectory(getRealPath() + pathDocument+category);
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
					String thisPath = pathHistoric+category;
					nameFile = getNewName(thisPath,  nameFile);
			}
			
			pathFile =category+"/";
			break;
		case 5:
			Utilities.createDirectory(getRealPath() + pathDocument+category);				
			Utilities.createDirectory(getRealPath() + pathHistoric+category);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
			if(deleteHistory)
				Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			else{
				String thisPath = pathHistoric+category;
				nameFile = getNewName(thisPath,  nameFile);
			}
			
			pathFile = category+"/";
			break;		
		default:
			break;
		}
		*/
		
		Utilities.createDirectory(getRealPath() + pathDocument+category);				
		Utilities.createDirectory(getRealPath() + pathHistoric+category);
		
		//Antes 20/11/13 se modifico para evitar eliminar fotos en clientes con mas de un proyecto
		//Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile);
		Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathDocument+category),nameFile+"_"+proyect_loan_id);
		if(deleteHistory)
			//Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile);
			
			Utilities.deleteFileDirByEqualName(new File(getRealPath() + pathHistoric+category),nameFile+"_"+proyect_loan_id);
		else{
			String thisPath = pathHistoric+category;
			nameFile = getNewName(thisPath,  nameFile);
		}
		
		pathFile = category+"/";
		
		return pathFile;
	}
	
	public String getNewName(String thisPath, String nameFile)
	{
			File f = new File(getRealPath() + thisPath);
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
}
