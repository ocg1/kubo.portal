package mx.com.kubo.managedbeans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

@ManagedBean @ViewScoped
public class CropperImage extends CropperImageDMO
implements Serializable
{
	private static final long serialVersionUID = -5139488512794453460L;

	@PostConstruct
	public void init()
	{	
		faces = FacesContext.getCurrentInstance();
		
		setRealPath(faces.getExternalContext().getRealPath("//resources//"));
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		lFileType = fileTypeService.getListFileType();
		
		htCategFile = new Hashtable<String, List<SelectItem>>();			
		
		EditImageSession editImg = (EditImageSession) resolver.getValue(elContext, null, "editImageSession");			
		
		if(sesion.getArea().equals('M'))
		{
			setImgLogo(false);
			setEditImg(editImg);
			setOriginalImgName(getEditImg().getOriginalImage());
			setRotateImgName(getEditImg().getRotateImage());
			
			if(editImg != null && editImg.getFormatImage()!= null)
			{
				formatIspdfFile = editImg.getFormatImage().toUpperCase().equals("PDF")?true:false;
				
			} else {
				
				formatIspdfFile = false;
			}
			
			//thisProyect=proyectService.getMaxProyect(getEditImg().getProspectus_id(),getEditImg().getCompany_id());	
			
			ProyectLoan proyectLoan = proyectLoanService.getProyectLoanByProyectLoanID(getEditImg().getProyectLoanID(), getEditImg().getProspectus_id(), getEditImg().getCompany_id());
			
			if( proyectLoan != null )
			{			
				thisProyect = proyectLoan.getProyect();			
			}
			
			List<Files> filesProspectus = filesService.getListFilesByProspect(getEditImg().getProspectus_id(),getEditImg().getCompany_id(), getEditImg().getProyectLoanID());
			
			SelectItem typeF=null;
			
			for (FileType fileType : lFileType) 
			{
				boolean existFile = false;
				
				if(sesion.getArea().toString().equals("M") || ( fileType.getFile_category_id()!=6 && fileType.getFile_category_id()!=7 && fileType.getFile_category_id()!=8))
				{
					 List<SelectItem> ListaF= htCategFile.get(fileType.getFileCategory().getName());
					 
					 if(editImg.getTypeFileID()==fileType.getFileTypePk().getFile_type_id())
					 {
						 documentTypeDesc=new DocumentationDMO();
						 documentTypeDesc.setDescription(fileType.getName());
						 documentTypeDesc.setTypeFile(fileType.getFileTypePk().getFile_type_id());
						 documentTypeDesc.setPrevName(fileType.getAbreviation());
						 documentTypeDesc.setCurrentName(fileType.getAbreviation());
						 documentTypeDesc.setPrevCategory(fileType.getFileCategory().getAbreviation());
						 documentTypeDesc.setCategoryFile(fileType.getFileCategory().getAbreviation());
					 }
					 
					 for (Files file_iter : filesProspectus) 
					 {
						if(file_iter.getFilesPk().getFile_type_id()==fileType.getFileTypePk().getFile_type_id())
						{
							existFile=true;
							break;
						}
							
					 }
					 
					 if(ListaF!=null)
					 {									
						 typeF= new SelectItem(fileType.getFileTypePk().getFile_type_id(), fileType.getName()+(existFile?" (Asignado)":""));
						 ListaF.add(typeF);	
					 }else{
						 List<SelectItem> LFiles= new ArrayList<SelectItem>();
						 typeF=new SelectItem(fileType.getFileTypePk().getFile_type_id(), fileType.getName()+(existFile?" (Asignado)":""));
						 LFiles.add(typeF);
						 htCategFile.put(fileType.getFileCategory().getName(), LFiles);
					 }	
				}
			}
			
			if(!formatIspdfFile)
			{
				List<SelectItem> lisImgProyect= new ArrayList<SelectItem>();
				
				if(thisProyect != null )
				{
				
					typeF=thisProyect.getLogo()!=null?new SelectItem(-1,"Foto 1 (Asignado)"):new SelectItem(-1,"Foto 1");
					lisImgProyect.add(typeF);
					typeF=thisProyect.getLogo2()!=null?new SelectItem(-2,"Foto 2 (Asignado)"):new SelectItem(-2,"Foto 2");
					lisImgProyect.add(typeF);
					typeF=thisProyect.getLogo3()!=null?new SelectItem(-3,"Foto 3 (Asignado)"):new SelectItem(-3,"Foto 3");
					lisImgProyect.add(typeF);
				
				}
				
				htCategFile.put("Fotos del proyecto", lisImgProyect);
			}
			
			Set<String> claves =htCategFile.keySet();
			SelectItemGroup[] category=new SelectItemGroup[claves.size()];
			 int i=0;
			 for(String clave : claves)
			 {
				 SelectItem items[]=new SelectItem[htCategFile.get(clave).size()];
				 category[i]=new SelectItemGroup(clave,clave,false,htCategFile.get(clave).toArray(items));
				i++;		 
			 }
			 setMenuItems(category);
			 
			 if(getEditImg().getFile_id()!=null && getEditImg().getTypeFileID()>0)
			 {
				 thisFiles = filesService.getFilebyID(new FilesPK(getEditImg().getFile_id(), getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(), getEditImg().getTypeFileID()));
				 
			 }else if(getEditImg().getTypeFileID() < 0) {
				 
				 thisFiles = null;
				 
				 documentTypeDesc=new DocumentationDMO();
				 documentTypeDesc.setTypeFile(getEditImg().getTypeFileID());
				 documentTypeDesc.setPrevCategory("photo");
				 documentTypeDesc.setCategoryFile("photo");
				 documentTypeDesc.setPrevName(getNameFile(getEditImg().getTypeFileID()));
				 documentTypeDesc.setCurrentName(getNameFile(getEditImg().getTypeFileID()));
				 
				 if( thisProyect != null )
				 {
				 
				 switch (getEditImg().getTypeFileID()) 
				 {
					case -1:
						getDocumentTypeDesc().setDescription(thisProyect.getLogo()!=null?"Foto 1 (Asignado)":"Foto 1");
					break;
					
					case -2:
						getDocumentTypeDesc().setDescription(thisProyect.getLogo2()!=null?"Foto 2 (Asignado)":"Foto 2");
					break;
					
					case -3:
						getDocumentTypeDesc().setDescription(thisProyect.getLogo3()!=null?"Foto 3 (Asignado)":"Foto 3");
					break;
					
					default: break;
					}
				 
			 	}
				 
			 }
			
		}else{
			//Secccion para editar fotos que sube el acreditado en la solicitud. NOTA: Aun no tiene la funcionalidad completa.
			if(editImg!=null)
			{
				setEditImg(editImg);
				setOriginalImgName(getEditImg().getOriginalImage());
				setRotateImgName(getEditImg().getRotateImage());
				
				thisProyect = proyectService.getMaxProyect(getEditImg().getProspectus_id(),getEditImg().getCompany_id());
				
				if(editImg.getTypeFileID()==0)
				{
					setImgLogo(true);
					
				} else {
					
					setImgLogo(false);
				}
			}
		}
		
		this.x=0;		
		this.y=0;
		this.x2=50;
		this.y2=50;
	}	

	
	
	public void cropImg()
	{
		x=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x"));
		y=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y"));
		x2=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x2"));
		y2=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y2"));
		
		 try {
			 	BufferedImage res=null;
			 	BufferedImage image=ImageIO.read(new File(getRealPath()+"/temp/"+getRotateImgName()));
			 	res=ImageUtils.cropImage(image, x, y, x2, y2);
			 	if(res!=null )
			 	{
			 		if(getTempOrigRotaImg()!=null){
			 			String remove=getRealPath()+"/temp/"+getRotateImgName();
			 			 if(Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg())){					 				 
			 				 rotateImgName=Utilities.getRandomName()+".png";
			 				 ImageIO.write(res,"png", new File(getRealPath()+"/temp/"+rotateImgName));
			 				 }
			 			 else
			 				 log.info("Error al eliminar previo archivo");	
			 			 
			 			 if( Utilities.deleteThisFile(remove)){
			 				tempOrigRotaImg=Utilities.getRandomName()+".png";
			 				ImageIO.write(res,"png", new File(getRealPath()+"/temp/"+tempOrigRotaImg));
			 				System.out.println("*****************************Termino bien");
			 			 }
			 			 
			 		}else{
			 			String format=getRotateImgName().substring(getRotateImgName().lastIndexOf(".")+1);
			 			ImageIO.write(res,format, new File(getRealPath()+"/temp/"+getRotateImgName()));
			 			tempOrigRotaImg=getRotateImgName();
			 			rotateImgName=Utilities.getRandomName()+".png";
			 			ImageIO.write(res,"png", new File(getRealPath()+"/temp/"+rotateImgName));
			 		}
			 	}else
			 		log.info("Errorr al cortar la imagen");
			 	
			 	activeSave();		
		 } catch (Exception e) {
				e.printStackTrace();
		 }
	}

	public void activeSave()
	{
		if(getTempOrigRotaImg()!=null){
			setDisabled(true);
			setChangeRotateOrCrop(true);
		}else if(getNewAngle()>0.0 || getNewAngle()<0.0){
			setDisabled(true);
			setChangeRotateOrCrop(true);
		}else{
			setDisabled(false);
			setChangeRotateOrCrop(false);
		}
			
	}
	
	public void rotateImage(double degree)
	{
		if (degree>=90)
		      newAngle += 90;
		    else
		    	newAngle -= 90;		   
		if (newAngle == 360 || newAngle ==-360){
			newAngle = 0.0;
		}
		
		if(newAngle.equals(0.0)){
			try {
				if(getTempOrigRotaImg()!=null){								
					if(Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName())){						
						setRotateImgName(getTempOrigRotaImg());
						String formatName=getRotateImgName().substring(getRotateImgName().lastIndexOf(".")+1);						
						InputStream in =new FileInputStream(new File(getRealPath()+"/temp/"+getRotateImgName()));
						setTempOrigRotaImg(Utilities.getRandomName()+"."+formatName);
						Utilities.copyFile(getRealPath()+"/temp/"+getTempOrigRotaImg(), in);
						
					}else
						log.info("Errooooor al eliminar archivo");				
					
				}else{
					InputStream in=new FileInputStream(new File(getOriginalImgName()));				
					Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());		
					setRotateImgName(Utilities.getRandomName()+"."+getEditImg().getFormatImage());
					if(Utilities.copyFile(getRealPath()+"/temp/"+getRotateImgName(), in)){
							log.info("se copio correctamente img original al temporal");
							setTempOrigRotaImg(null);
					}else
							log.info("Error al copiar la oginal al temporal");					
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
				try {
						if(getTempOrigRotaImg()!=null){
							BufferedImage image = ImageIO.read(new File(getRealPath()+"/temp/"+getTempOrigRotaImg()));	
						 	BufferedImage res=null;
						 	if(getNewAngle()==180.0 || getNewAngle()==-180.0){
						 		 res=ImageUtils.rotateImage(getRealPath()+"/temp/"+getTempOrigRotaImg(),180.0);
						 	}else
						 		 res=ImageUtils.rotateImage(image, getNewAngle());
						 	if(res!=null && Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName())){	
						 		setRotateImgName(Utilities.getRandomName()+".png");
						 		ImageIO.write(res,"png", new File(getRealPath()+"/temp/"+getRotateImgName()));
						 	}
						 	else
						 		log.info("Error al rotar");
						}else{		
							
						 	BufferedImage image = ImageIO.read(new File(getOriginalImgName()));	
						 	BufferedImage res=null;
						 	if(getNewAngle()==180.0 || getNewAngle()==-180.0){
						 		 res=ImageUtils.rotateImage(getOriginalImgName(),180.0);
						 	}else
						 		 res=ImageUtils.rotateImage(image, getNewAngle());
						 	
						 	if(res!=null && Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName())){	
						 		setRotateImgName(Utilities.getRandomName()+"."+getEditImg().getFormatImage());
						 		ImageIO.write(res,getEditImg().getFormatImage(), new File(getRealPath()+"/temp/"+getRotateImgName()));
						 		setTempOrigRotaImg(null);
						 	}
						 	else
					 		log.info("Error al rotar");
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		activeSave();	
	}
	
	//Guada edicion de imagen del proyecto pero en la seccion de solicitud de credito (usuario diferente a M) NOTA: aun no esta implementado bien.
	//No esta implementado(Cuando se implemente verificar al momento de borrar la imagen que no afecte a otro proyectos del mismo prospectus_id)
	public void saveLogoProyect()
	{
		String path1 = "/documents";
		path1 += "/cia_" + getEditImg().getCompany_id();
		path1 += "/pros_" + getEditImg().getProspectus_id();
		path1 += "/photo";
		String nameFile="";
		
		if(Utilities.createDirectory(getRealPath()+path1))
		{
			InputStream in=null;
			try 
			{
				in =new FileInputStream(new File(getRealPath()+"/temp/"+getRotateImgName()));
				nameFile=path1+"/proyectphoto_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+getRotateImgName();				
				
				Utilities.deleteFileDirByEqualName(new File(getRealPath()+path1),"proyectphoto");
				if(Utilities.copyFile(getRealPath()+nameFile,in)){
					if(getThisProyect()!=null){
						getThisProyect().setLogo(nameFile);
						if(proyectService.update(getThisProyect())){
								getEditImg().setOriginalImage(getRealPath()+nameFile);
								if(getRotateImgName()!=null)
									Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
								if(getTempOrigRotaImg()!=null)
									Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
						}
							
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public void saveChange()
	{		
		if(isImgLogo()){
			saveLogoProyect();
		}		
		else{

			Integer typeFileID=0;
			Integer origTypeFileID=getEditImg().getTypeFileID();
			String nameFile="";	
			String nameCategory="";
			
		String path1 = "/documents";
		path1 += "/cia_" + getEditImg().getCompany_id();
		path1 += "/pros_" + getEditImg().getProspectus_id();
		path1 += "/";			
		
		if(isChangeReclType() && isChangeRotateOrCrop()){
			typeFileID=documentTypeDesc.getTypeFile();
			nameCategory=documentTypeDesc.getCategoryFile();
			String prevName=documentTypeDesc.getPrevName();
			if(typeFileID!=0 && Utilities.createDirectory(getRealPath() + path1+nameCategory)){
				nameFile=documentTypeDesc.getCurrentName();
				
				try {
					InputStream in=null;
					//Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+nameCategory),nameFile);
					Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+documentTypeDesc.getPrevCategory()),prevName +"_"+ getEditImg().getProyectLoanID());					
					if(typeFileID>0 && origTypeFileID>0){	
						
						Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+nameCategory),nameFile+"_"+ getEditImg().getProyectLoanID());
						
						in =new FileInputStream(new File(getRealPath()+"/temp/"+getRotateImgName()));
						//nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+getRotateImgName();						
						//Se agrego esta parte
						nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+"."+getEditImg().getFormatImage();					
						//Utilities.fileMove(sourceFile, getRealPath()+destFile+"/"+nameFile);
						
			         	thisFiles.getFilesPk().setFile_type_id(typeFileID);
			         	//thisFiles.setLocation(destFile+"/"+nameFile);
			         	
			         	
			         		try{
				        		 //Se comento esta linea para que elimine de l BD el registro que sera sustituido en caso de existir
				        		 //thisFiles.getFilesPk().setFile_type_id(originalTypeFile);
				        		 
				        		 //21/11/13 Se comento esta linea para elinar el archivo anterior antes de insertar el nuevo.
				        		 //filesService.removeFile(thisFiles.getFilesPk());
				        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
				        		 //Elimina ambos registros para poder insertar uno nuevo.
				        		 thisFiles.getFilesPk().setFile_type_id(originalTypeFile);
				        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
				        		 
				        	 }catch(Exception e){
				        		 
				        		 e.printStackTrace();
				        		 
				        	 }
			         	//Se agrego esta parte
				        
			         	//Se comento para realizar el update sobre el registro anterior en la BD
			         	//thisFiles.getFilesPk().setFile_type_id(typeFileID);
			         	thisFiles.getFilesPk().setFile_type_id(typeFileID);
						
						if(saveFileByType(getRealPath() + path1+nameCategory+"/"+nameFile,typeFileID,path1+nameCategory+"/"+nameFile,in)){
							if(getRotateImgName()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
							if(getTempOrigRotaImg()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
						}else{
							log.info("Error al guardar el archivo");
						}
					}else{
						Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+nameCategory),nameFile +"_"+ getEditImg().getProyectLoanID());
						nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+getRotateImgName();												
						if(savePhotoProyect(getRealPath()+"/temp/"+getRotateImgName(),typeFileID,path1+nameCategory+"/"+nameFile,getRealPath() + path1+nameCategory+"/"+nameFile)){
							if(getRotateImgName()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
							if(getTempOrigRotaImg()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
						}else{
							log.info("Error al guardar el archivo");
						}
					}
					
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
				}
			}
			
			
		}else if(isChangeReclType()){
			typeFileID=documentTypeDesc.getTypeFile();
			nameCategory=documentTypeDesc.getCategoryFile();
			
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
			
			  String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
		        if(ipAddress == null)  
		        {  
		          ipAddress = httpServletRequest.getRemoteAddr();  
		        }		        			 			
				
			 String sourceFile=getEditImg().getOriginalImage();
			 String destFile=path1+nameCategory;
			 nameFile=documentTypeDesc.getCurrentName();
			 
			 //21/11/13		Se agrego esta linea para eliminar archivos de otro rubro y poder sustituirlo por el nuevo
			 Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+nameCategory),nameFile+"_"+ getEditImg().getProyectLoanID());
			 
			 if(getThisFiles()!=null && typeFileID>0 && origTypeFileID>0){
				 
				 
				 
				 //Reclasificando documento
				 if(Utilities.createDirectory(sourceFile) && Utilities.createDirectory(getRealPath()+destFile))
				 {			
					nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+"."+getEditImg().getFormatImage();					
					Utilities.fileMove(sourceFile, getRealPath()+destFile+"/"+nameFile);
					
		         	thisFiles.getFilesPk().setFile_type_id(typeFileID);
		         	thisFiles.setLocation(destFile+"/"+nameFile);
		         	
		         	if(isChangeReclType()){
			        	 
		         		try{
			        		 //Se comento esta linea para que elimine de l BD el registro que sera sustituido en caso de existir
			        		 //thisFiles.getFilesPk().setFile_type_id(originalTypeFile);
			        		 
			        		 //21/11/13 Se comento esta linea para elinar el archivo anterior antes de insertar el nuevo.
			        		 //filesService.removeFile(thisFiles.getFilesPk());
			        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
			        		 //Elimina ambos registros para poder insertar uno nuevo.
			        		 thisFiles.getFilesPk().setFile_type_id(originalTypeFile);
			        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
			        		 
			        	 }catch(Exception e){
			        		 
			        		 e.printStackTrace();
			        		 
			        	 }
		         		
			        }
		         	//Se comento para realizar el update sobre el registro anterior en la BD
		         	//thisFiles.getFilesPk().setFile_type_id(typeFileID);
		         	thisFiles.getFilesPk().setFile_type_id(typeFileID);
		         	
		         	
		         	//if(filesService.updateFile(thisFiles)){
		         	if(filesService.addFile(thisFiles, thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id())){
		         		Change_controlPK changeCtrlPK=new Change_controlPK();
		    			changeCtrlPK.setProspectus_id(getEditImg().getProspectus_id());
		    			changeCtrlPK.setCompany_id(getEditImg().getCompany_id());
		    			
		    			Change_control changeCtrl=new Change_control();
		    			changeCtrl.setChange_controlPK(changeCtrlPK);
		    			changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		    			changeCtrl.setNew_value(getDocumentTypeDesc().getDescription());
		    			
		    			for (FileType iterFileT : getlFileType()) {
		    				if(iterFileT.getFileTypePk().getFile_type_id()==origTypeFileID){
		    					changeCtrl.setOriginal_value(iterFileT.getName());
		    					break;
		    				}
							
						}    			    		
		    			changeCtrl.setAfected_table("gn_file");
		    			changeCtrl.setField("file_type_id");
		    			changeCtrl.setComments("Por reclasificación de documento");
		    			changeCtrl.setIp(ipAddress);
		    			if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
		    				log.info("Se guardaron los datos en tabla de control");
		    				if(getRotateImgName()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
							if(getTempOrigRotaImg()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
		    			}else
		    				log.info("Uppps error al guardar en tabla de control");
		         	}else{
		         		System.out.println("Error al actualizar el tipo de documento");
		         	}
				 }else
					 System.out.println("Error");
			 } else{
				 if(Utilities.createDirectory(getRealPath()+destFile))
				 {		
					 nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+"."+getEditImg().getFormatImage();
					 
					 if(savePhotoProyect(sourceFile, typeFileID, path1+nameCategory+"/"+nameFile, getRealPath()+destFile+"/"+nameFile)){
						 if(getRotateImgName()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
						 if(getTempOrigRotaImg()!=null)
								Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
					}else
						log.info("Error al guardar el archivo");
				 }else
					 System.out.println("Error al crear directorio destino");
				 
			 }
		}else if(isChangeRotateOrCrop()){
			typeFileID=getEditImg().getTypeFileID();
			nameCategory=documentTypeDesc.getCategoryFile();
			String prevName=documentTypeDesc.getPrevName();
			
			if(typeFileID!=0 && Utilities.createDirectory(getRealPath() + path1+nameCategory)){
				nameFile=documentTypeDesc.getCurrentName();
				
				try {
					InputStream in=null;
					Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+documentTypeDesc.getPrevCategory()),prevName +"_"+ getEditImg().getProyectLoanID());
					
					 if(getThisFiles()!=null && typeFileID>0 && origTypeFileID>0){
						 
						 Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+nameCategory),nameFile+"_"+ getEditImg().getProyectLoanID());
						 
						 in =new FileInputStream(new File(getRealPath()+"/temp/"+getRotateImgName()));
							nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+getRotateImgName();						
						
							if(saveFileByType(getRealPath() + path1+nameCategory+"/"+nameFile,typeFileID,path1+nameCategory+"/"+nameFile,in)){
								if(getRotateImgName()!=null)
									Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
								if(getTempOrigRotaImg()!=null)
									Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
							}else{
								log.info("Error al guardar el archivo");
								}
					 }else{
						 if(Utilities.createDirectory(getRealPath() + path1+nameCategory))
						 {			
							 nameFile+="_"+getEditImg().getProyectLoanID()+"_"+getEditImg().getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+"."+getEditImg().getFormatImage();
							 
							 if(savePhotoProyect(getRealPath()+"/temp/"+getRotateImgName(),typeFileID,path1+nameCategory+"/"+nameFile,getRealPath() + path1+nameCategory+"/"+nameFile)){
								 if(getRotateImgName()!=null)
										Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
								 if(getTempOrigRotaImg()!=null)
										Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());
							}else
								log.info("Error al guardar el archivo");
						 }else
							 System.out.println("Error al crear directorio destino");
					 }
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
				}
			}
		}			
	}
}

	public boolean savePhotoProyect(String sourcefile,int typeFile,String path,String destFile)
	{
		boolean result=false;
		Integer origTypeFile=getEditImg().getTypeFileID();		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    if(ipAddress == null)  
	        {  
	          ipAddress = httpServletRequest.getRemoteAddr();  
	        }	 

		String nameField=null;
		String newValue=null;
		
	    if(typeFile<0 && origTypeFile <0){
	    	//Esta reclasificando foto a otra foto (foto1,foto2,foto3)	
	    	switch (typeFile) {
			case -1://asignando para Foto 1 del proyecto
				getThisProyect().setLogo(path);
				getThisProyect().setMetadata1(null);
				
				if(isChangeReclType()){
					if(origTypeFile==-2){
						getThisProyect().setLogo2(null);
						getThisProyect().setMetadata2(null);
					}else{
						getThisProyect().setLogo3(null);
						getThisProyect().setMetadata3(null);
					}
				}
				nameField="logo";
				newValue="Foto 1";
				break;
			case -2://asignando para Foto 2 del proyecto
				getThisProyect().setLogo2(path);
				getThisProyect().setMetadata2(null);
				
				if(isChangeReclType()){
					if(origTypeFile==-1){
						getThisProyect().setLogo(null);
						getThisProyect().setMetadata1(null);
					}else{
						getThisProyect().setLogo3(null);
						getThisProyect().setMetadata3(null);
					}
				}
				nameField="logo2";
				newValue="Foto 2";
				break;
			case -3://asignando para Foto 3 del proyecto
				getThisProyect().setLogo3(path);
				if(getThisProyect().getMetadata3()!=null){
					getThisProyect().setMetadata3(null);
				}
				
				if(isChangeReclType()){
					if(origTypeFile==-2){
						getThisProyect().setLogo2(null);
						getThisProyect().setMetadata2(null);
					}else{
						getThisProyect().setLogo(null);
						getThisProyect().setMetadata1(null);
					}
				}
				nameField="logo3";
				newValue="Foto 3";
				break;
			default:
				break;
			}	
	    	
	    }else if(typeFile>0 && origTypeFile<0){
	    	//Reclasifica foto a otro tipo diferente de foto
	    		switch (origTypeFile) {
				case -1://asignando para Foto 1 del proyecto
					getThisProyect().setLogo(null);
					getThisProyect().setMetadata1(null);
					nameField="logo";
					newValue="Foto 1";
					break;
				case -2://asignando para Foto 2 del proyecto
					getThisProyect().setLogo2(null);
					getThisProyect().setMetadata2(null);
					nameField="logo2";
					newValue="Foto 2";
					break;
				case -3://asignando para Foto 3 del proyecto
					getThisProyect().setLogo3(null);
					getThisProyect().setMetadata3(null);
					nameField="logo3";
					newValue="Foto 3";
					break;
				default:
					break;
				}	
	    		
	    		
	            //Eliminar archivo anterior para guardar el nuevo
	    		try{//typeFile
	        		 if(filesService.removeFileByProyectLoanID(getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(), typeFile))
	        		 {
	        			 System.out.println("Se elimino");
	        		 }else
	        			 System.out.println("Se elimino");
	        	 }catch(Exception e){
	        		 e.printStackTrace();
	        	 }
	    		
	    		//Reclasificando foto del proyecto a nuevo documentos
	    		 thisFiles=new Files();
	           	 //FilesPK filePk=new FilesPK(getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(), getEditImg().getTypeFileID());
	    		 FilesPK filePk=new FilesPK(getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(), typeFile);
	           	 thisFiles.setFilesPk(filePk);
	           	 
	           	 thisFiles.setUpload_date(new Date());
	           	 
	           	 //thisFiles.getFilesPk().setFile_type_id(typeFile);
		         thisFiles.setLocation(path);
		         thisFiles.setApproved("1");
		         filesService.addFile(thisFiles, getEditImg().getProspectus_id(), getEditImg().getCompany_id());	    			    		
	    		
	    }else if(typeFile<0 && origTypeFile>0){
			
			switch (typeFile) {
			case -1://asignando para Foto 1 del proyecto
				getThisProyect().setLogo(path);
				if(getThisProyect().getMetadata1()!=null){
					getThisProyect().setMetadata1(null);
				}
				nameField="logo";
				newValue="Foto 1";
				break;
			case -2://asignando para Foto 2 del proyecto
				getThisProyect().setLogo2(path);
				if(getThisProyect().getMetadata2()!=null){
					getThisProyect().setMetadata2(null);
				}
				nameField="logo2";
				newValue="Foto 2";
				break;
			case -3://asignando para Foto 3 del proyecto
				getThisProyect().setLogo3(path);
				if(getThisProyect().getMetadata3()!=null){
					getThisProyect().setMetadata3(null);
				}
				nameField="logo3";
				newValue="Foto 3";
				break;
			default:
				break;
			}	
			
			
			// Eliminar el registro en file.
			
	    	if(filesService.removeFileByProyectLoanID(getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(),getEditImg().getTypeFileID())){
				log.info("Se elimino completamente el registro");
			}else
				log.info("Error al eliminar el archivo");
	    }
	    
	    
		if(Utilities.fileMove(sourcefile, destFile))
		{
			String sourceThumbFile = sourcefile.substring(0, sourcefile.lastIndexOf(".")) + "_thumb" + sourcefile.substring(sourcefile.lastIndexOf("."));
			String destThumbFile = destFile.substring(0, destFile.lastIndexOf(".")) + "_thumb" + destFile.substring(destFile.lastIndexOf("."));
			
			//Si el archivo thumb origen existe se elimina para que no quede basura.
			File thumbFile = new File(sourceThumbFile);
			if(thumbFile.exists()){
				thumbFile.delete();
			}
			
			try {
			//Como el thum no esta en la carpeta temp, se crea a partir de la imagen original.
		    File sourceFile = new File(destFile);
				Utilities.copyFile(destThumbFile, new FileInputStream(sourceFile));
				//Redimenciona y guarda img
				ImageUtils.resizeAndSaveImg(destThumbFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	    		if(proyectService.update(getThisProyect())){
		    		Change_controlPK changeCtrlPK=new Change_controlPK();
					changeCtrlPK.setProspectus_id(getEditImg().getProspectus_id());
					changeCtrlPK.setCompany_id(getEditImg().getCompany_id());
					Change_control changeCtrl=new Change_control();
					changeCtrl.setChange_controlPK(changeCtrlPK);
					changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
					changeCtrl.setAfected_table("ln_proyect");
					changeCtrl.setField(nameField);		
					changeCtrl.setIp(ipAddress);
					if(typeFile<0 && origTypeFile <0){
						changeCtrl.setOriginal_value("Foto "+origTypeFile*-1);						
						changeCtrl.setNew_value(newValue);
						
					}else if(typeFile<0 && origTypeFile>0){
						changeCtrl.setNew_value(newValue);	
						for (FileType iterFileT : getlFileType()) {
	        				if(iterFileT.getFileTypePk().getFile_type_id()==origTypeFile){
	        					changeCtrl.setOriginal_value(iterFileT.getName());
	        					break;
	        				}
	    					
	    				} 
					}
					else{
						changeCtrl.setOriginal_value(newValue);						
						changeCtrl.setNew_value(getDocumentTypeDesc().getDescription());
					}
					
		    		if(isChangeReclType() && isChangeRotateOrCrop()){                			            			            			            			            			            			   			    		            		
						changeCtrl.setComments("Por reclasificación de documento");
						if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
							changeCtrl.setComments("Por edición de imagen");
							if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
								result=true;
					         }            				
						}else
							log.info("Uppps error al guardar en tabla de control");
					}else if(isChangeRotateOrCrop()){
						changeCtrl.setComments("Por edición de imagen");
						if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
							result=true;
					      }			
					}else if(isChangeReclType()){
						changeCtrl.setComments("Por reclasificación de documento");
						if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
							result=true;
					      }	
					}
		    		
		    		
	    		}else{
	    			result=false;
	    		}
	    	
	    
		}
	    
	    return result;
	}
	
	public boolean saveFileByType(String fileName,int typeFile,String path,InputStream in)
	{
		boolean result=false;
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    if(ipAddress == null)  
	        {  
	          ipAddress = httpServletRequest.getRemoteAddr();  
	        }
			 
		if(Utilities.copyFile(fileName, in))
		{
            
            if(thisFiles==null)
            {
	           	 thisFiles=new Files();
	           	 FilesPK filePk=new FilesPK(getEditImg().getProspectus_id(), getEditImg().getCompany_id(), getEditImg().getProyectLoanID(), typeFile);
	           	 thisFiles.setFilesPk(filePk);
	           	 thisFiles.setUpload_date(new Date());
	           	 //thisFiles.setFile_type_id(typeFile);
		         thisFiles.setLocation(path);
		         thisFiles.setApproved("1");
		         
		         if(isChangeReclType()){
		        	 try{
		        		 filesService.removeFile(thisFiles.getFilesPk());
		        	 }catch(Exception e){
		        		 e.printStackTrace();
		        	 }
		         }
		         
		         if(filesService.addFile(thisFiles, getEditImg().getProspectus_id(), getEditImg().getCompany_id())){
		        	 setOriginalImgName(fileName);
		        	 result=true;
		         }else
		        	 result=false;
		        	 
            }
            else{            	 
            	
            	thisFiles.setLocation(path);
            	thisFiles.setUpload_date(new Date());
            	
            	if(isChangeReclType())
            	{
		        	 try
		        	 {
		        		 
		        		 thisFiles.getFilesPk().setFile_type_id(originalTypeFile );
		        		 
		        		 //filesService.removeFile(thisFiles.getFilesPk());
		        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
		        		 //Elimina ambos registros para poder insertar uno nuevo.
		        		 thisFiles.getFilesPk().setFile_type_id(originalTypeFile);
		        		 filesService.removeFileByProyectLoanID(thisFiles.getFilesPk().getProspectus_id(), thisFiles.getFilesPk().getCompany_id(), thisFiles.getFilesPk().getProyect_loan_id(), thisFiles.getFilesPk().getFile_type_id());
		        		 
		        	 }catch(Exception e){
		        		 e.printStackTrace();
		        	 }
		         }
            	
            	thisFiles.getFilesPk().setFile_type_id(typeFile);
            	
            	if(filesService.updateFile(thisFiles))
            	{
            		setOriginalImgName(fileName);
            		
            		Change_controlPK changeCtrlPK=new Change_controlPK();
        			changeCtrlPK.setProspectus_id(getEditImg().getProspectus_id());
        			changeCtrlPK.setCompany_id(getEditImg().getCompany_id());
        			Change_control changeCtrl=new Change_control();
        			changeCtrl.setChange_controlPK(changeCtrlPK);
        			changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
        			changeCtrl.setAfected_table("gn_file");
        			changeCtrl.setField("file_type_id");
        			changeCtrl.setIp(ipAddress);
        			changeCtrl.setNew_value(getDocumentTypeDesc().getDescription());
        			
        			for (FileType iterFileT : getlFileType()) {
        				if(iterFileT.getFileTypePk().getFile_type_id()==getEditImg().getTypeFileID()){
        					changeCtrl.setOriginal_value(iterFileT.getName());
        					break;
        				}
    					
    				} 
            		if(isChangeReclType() && isChangeRotateOrCrop()){                			            			            			            			            			            			   			    		            		
            			changeCtrl.setComments("Por reclasificación de documento");
            			if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
            				changeCtrl.setComments("Por edición de imagen");
            				if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
            					log.info("Se guardo correctamente");
            				}            				
            			}else
            				log.info("Uppps error al guardar en tabla de control");
            		}else if(isChangeRotateOrCrop()){
            			changeCtrl.setComments("Por edición de imagen");
        				if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id())){
        					log.info("Se guardo correctamente");
        				}
            			
            		}
            		
            		result=true;
            	}else
            		result=false;
            			
            }
            
          }
		
		return result;
	}
	
	public void cancelCrop()
	{
		try 
		{
			if(getRotateImgName()!=null)
				Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
			if(getTempOrigRotaImg()!=null)
				Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeReclasificationTypeFile()
	{		
		originalTypeFile = getEditImg().getTypeFileID();
		
		if(getEditImg().getTypeFileID()!=getDocumentTypeDesc().getTypeFile())
		{
			setDisabled(true);	
			setChangeReclType(true);
			
			if(getDocumentTypeDesc().getTypeFile()>0)
			{
				for (FileType iterFileT : getlFileType()) {
					if(iterFileT.getFileTypePk().getFile_type_id()==getDocumentTypeDesc().getTypeFile()){
						getDocumentTypeDesc().setCategoryFile(iterFileT.getFileCategory().getAbreviation());
						getDocumentTypeDesc().setDescription(iterFileT.getName());
						getDocumentTypeDesc().setCurrentName(iterFileT.getAbreviation());
						break;
					}
					
				}
			}else{
				getDocumentTypeDesc().setCategoryFile("photo");
				getDocumentTypeDesc().setCurrentName(getNameFile(getDocumentTypeDesc().getTypeFile()));
				switch (getDocumentTypeDesc().getTypeFile()) {
				case -1:
					getDocumentTypeDesc().setDescription(thisProyect.getLogo()!=null?"Foto 1 (Asignado)":"Foto 1");
					break;
				case -2:
					getDocumentTypeDesc().setDescription(thisProyect.getLogo2()!=null?"Foto 2 (Asignado)":"Foto 2");
					break;
				case -3:
					getDocumentTypeDesc().setDescription(thisProyect.getLogo3()!=null?"Foto 3 (Asignado)":"Foto 3");
					break;

				default:
					break;
				}				
			}
			
		}else if(isChangeRotateOrCrop()){
			setDisabled(true);
			setChangeReclType(false);
		}else{
			setDisabled(false);
			setChangeReclType(false);
		}
	}
	
	public void removeThisFile()
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    if(ipAddress == null)  
	        {  
	          ipAddress = httpServletRequest.getRemoteAddr();  
	        }
	    
		Change_controlPK changeCtrlPK=new Change_controlPK();
		changeCtrlPK.setProspectus_id(getEditImg().getProspectus_id());
		changeCtrlPK.setCompany_id(getEditImg().getCompany_id());
		
		Change_control changeCtrl=new Change_control();
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setOriginal_value(getDocumentTypeDesc().getDescription());		
		changeCtrl.setNew_value("");
		changeCtrl.setIp(ipAddress);
		
		if(getDocumentTypeDesc().getTypeFile()<0)
		{
			changeCtrl.setAfected_table("ln_proyect");
			String fieldAfected="";
			
			switch (getDocumentTypeDesc().getTypeFile()) 
			{
				case -1:
					fieldAfected="logo";
					getThisProyect().setLogo(null);
					getThisProyect().setMetadata1(null);
					break;
				case -2:
					fieldAfected="logo2";
					getThisProyect().setLogo2(null);
					getThisProyect().setMetadata2(null);
					break;
				case -3:
					fieldAfected="logo3";
					getThisProyect().setLogo3(null);
					getThisProyect().setMetadata3(null);
					break;
	
				default:break;
			}
			
			changeCtrl.setField(fieldAfected);
			changeCtrl.setComments("Se elimino "+fieldAfected+" del proyecto");
			
			if(proyectService.update(getThisProyect()))
			{
				if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id()))
				{
					System.out.println("Se guardo completamente el archivo");
					
					try 
					{
						
						String origFilePath = (getEditImg().getOriginalImage()).substring(0, getEditImg().getOriginalImage().lastIndexOf("."));
						String formatFile = (getEditImg().getOriginalImage()).substring((getEditImg().getOriginalImage()).lastIndexOf("."));
						
						Utilities.deleteThisFile(origFilePath+"_thumb"+formatFile);
						Utilities.deleteThisFile(getEditImg().getOriginalImage());
						
						if(getRotateImgName()!=null)
							Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
						if(getTempOrigRotaImg()!=null)
							Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else
				System.out.println("Error al eliminar el archivo");
		}else{
			changeCtrl.setAfected_table("gn_file");
			changeCtrl.setField("");
			changeCtrl.setComments("Se elimino el archivo");
			
			if(filesService.removeFile(getThisFiles().getFilesPk()))
			{
				if(changeControlService.addChangeControl(changeCtrl,getEditImg().getProspectus_id() ,getEditImg().getCompany_id()))
				{
					System.out.println("Se guardo completamente el archivo");
					
					try 
					{
						Utilities.deleteThisFile(getEditImg().getOriginalImage());
						
						if(getRotateImgName()!=null)
							Utilities.deleteThisFile(getRealPath()+"/temp/"+getRotateImgName());
						if(getTempOrigRotaImg()!=null)
							Utilities.deleteThisFile(getRealPath()+"/temp/"+getTempOrigRotaImg());	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else
				System.out.println("Error al eliminar el archivo");
		}								
	}
	
	public String getNameFile(Integer typeFileID)
	{
		String nameFile="";
		
		switch (typeFileID)
		{
			case -1://establece parte del nombre del proyecto foto 1
				nameFile="proyectphoto1";
			break;
			
			case -2://establece parte del nombre del proyecto foto 2
				nameFile="proyectphoto2";
			break;
			
			case -3://establece parte del nombre del proyecto foto 3
				nameFile="proyectphoto3";
			break;
			
			default: break;
		}		
		return nameFile;
	}
}
