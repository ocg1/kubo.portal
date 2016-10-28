package mx.com.kubo.managedbeans.mesa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean @ViewScoped
public class ElectronicFile extends ElectronicFileAMO
implements Serializable, ElectronicFileIMO
{
	private static final long serialVersionUID = 6042615943752089710L;

	@PostConstruct
	public void init()
	{			
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		external  = faces.getExternalContext();
		
		realPath = external.getRealPath("//resources//");
				
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		sesion    = (SessionBean)         resolver.getValue(elContext, null, "sessionBean");
		
		System.out.println("Entrando con este valor: "+searchsum.getSearchSummary());
		
			if(searchsum.getSearchSummary() != null)
			{											
				search_documentation();	
				
				cadenaProyecto = searchsum.getSearchSummary();
				
				passingProyectB = null; 
				
				if(cadenaProyecto.split("::").length > 2)
				{
					proyect_loan_id = Integer.parseInt(cadenaProyecto.split("::")[0]);
					proyect_id      = Integer.parseInt(cadenaProyecto.split("::")[1]);
					prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[2]);
					company_id      = Integer.parseInt(cadenaProyecto.split("::")[3]);
							
					 passingProyectB = new ProyectLoanPK(proyect_loan_id, proyect_id, prospectus_id, company_id);
					 
				}else{
					
					proyect_loan_id = 1;
					proyect_id      = 1;
					prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[0]);
					company_id      = Integer.parseInt(cadenaProyecto.split("::")[1]);
					
					passingProyectB = new ProyectLoanPK(proyect_loan_id, proyect_id, prospectus_id, company_id);
				}
				
				
				proyectLoan = proyectLoanService.findProyect(passingProyectB);
				
				menu = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
				
				
				mpk = new  MembershipPK();
				
				if(cadenaProyecto.split("::").length > 2)
				{
					mpk.setCompany_id ( Integer.parseInt( cadenaProyecto.split("::")[3] ) );
					mpk.setProspectus_id ( Integer.parseInt( cadenaProyecto.split("::")[2] ) );
					
				} else {
					
					mpk.setCompany_id ( Integer.parseInt( cadenaProyecto.split("::")[1] ) );
					mpk.setProspectus_id ( Integer.parseInt( cadenaProyecto.split("::")[0] ) );
				}
				
				member = membershipService.getMembershipById(mpk);				
				
				if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") )
				{
					
					menu.setProspectus_is_canceled( true );
					menu.setCanceledReason(member.getIs_canceled());
					
				}else{
					
					menu.setProspectus_is_canceled( false );
					menu.setCanceledReason("");
					
				}
				
			}
			
			searchsum.setPerson(false);
			
	}

	public void search_Request(ActionEvent e)
	{	
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");			
		sesion    = (SessionBean)         resolver.getValue(elContext, null, "sessionBean");
		
		//Prospectus prospectus=prospectusService.getProspectByTrackingID(getSearch());
						
		Map<String, Object> viewMap = faces.getViewRoot().getViewMap();
		viewMap.clear();
		
//		if(searchsum.getSearchSummary()==null){
			if( getSearch()!=null)
			{											
				//Prospectus prospectus=prospectusService.getProspectByTrackingID(getSearch());
				
				prospectusPK = new ProspectusPK();
				prospectusPK.setProspectus_id(Integer.parseInt(getSearch()));
				prospectusPK.setCompany_id(sesion.getCompany_id());	
				
				
				prospectus = prospectusService.getProspectusById(prospectusPK);
				
				proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(prospectus.getProspectusPK().getProspectus_id(), prospectus.getProspectusPK().getCompany_id());
				
				
				value = proyectLoan.getProyectloanPk().getProyect_loan_id()+"::"+proyectLoan.getProyectloanPk().getProyect_id()+"::"+proyectLoan.getProyectloanPk().getProspectus_id()+"::"+proyectLoan.getProyectloanPk().getCompany_id()+"::";
				
				searchsum.setSearchSummary(getValue());
			}
//		}else{
//			String cadenaProyecto = searchsum.getSearchSummary();
//			ProyectLoanPK passingProyectB = new ProyectLoanPK(Integer.parseInt(cadenaProyecto.split("::")[0]),Integer.parseInt(cadenaProyecto.split("::")[1]),Integer.parseInt(cadenaProyecto.split("::")[2]),Integer.parseInt(cadenaProyecto.split("::")[3]));
//			proyectLoan = proyectLoanService.findProyect(passingProyectB);
//			setSearch(proyectLoan.getPerson().getProspectus().getTracking_id());
//			setValue( searchsum.getSearchSummary() );
//		}

			request = RequestContext.getCurrentInstance();
		
		if(proyectLoan != null)
		{
				
				listDocumentation =null;
				listDocCapPago =null;
				listDocAddress =null;
				listAddedContracts =null;
			
				listDocsContracts = null;
				listDocSIC = null;
				listFilesP = null;
				
				searchsum.setTypeLog("EVA");
				
				search_documentation();
				
				request.addCallbackParam("isValid", true);
				
				haveConsult = false;

		} else {
			
			request.addCallbackParam("isValid", false);
			 
		}
		
	}
		
	public void uploadFileContracts(FileUploadEvent event) 
	{
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		
			if(searchsum.getSearchSummary() !=null)
			{
				String proyectLoanID = searchsum.getSearchSummary().split("::")[0].trim();
				String proyectID     = searchsum.getSearchSummary().split("::")[1].trim();
				String prospectusID  = searchsum.getSearchSummary().split("::")[2].trim();
				String companyID     = searchsum.getSearchSummary().split("::")[3].trim();
				String formatFile="";
				
				if(event != null)
				{
					try
					{
						formatFile =  event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")+1);
						
					} catch (Exception e) {
						
						log.info("Por defaulf pone .JPG");
						
						formatFile="jpg";
					}
					
				}
				
				String nameFile="";			
				String path1 = "/documents";
				path1 += "/cia_" + companyID ;
				path1 += "/pros_" +prospectusID;
				path1 += "/";
				
				if( getTypeContract()!=0 && Utilities.createDirectory(getRealPath() + path1+"Contract"))
				{								
					switch (getTypeContract()) 
					{
						case 44:	//Por el momento solo se  puede adjuntar un contrato si se anexan mas aka pueden ir condicionando			
							nameFile="contract";
						break;
						
						case 45:
							nameFile="Pagare";
						break;
						
						default: break;
					}
					
					//Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+"Contract"),nameFile);
					Utilities.deleteFileDirByEqualName(new File(getRealPath() + path1+"Contract"),nameFile+proyectLoanID);
					
					//nameFile+="_"+prospectusID+"_"+proyectID+"_"+Utilities.getRandomName()+"."+formatFile;
					nameFile+="_"+proyectLoanID+"_"+prospectusID+"_"+proyectID+"_"+Utilities.getRandomName()+"."+formatFile;
					
					try 
					{
						if(saveFileByType(getRealPath() + path1+"Contract/"+nameFile,getTypeContract(),path1+"Contract/"+nameFile,event.getFile().getInputstream()))
						{								
							log.info("Se guardo correctamente el archivo");
							
							addDocsContracts();
							
						} else {
							
							log.info("Error al guardar el archivo");
						}
							//No se pudo guardar el archivo.
					} catch (Exception e) {
						log.info("Error al guardar el archivo");
					}
				}
			}							
	}
	 
	public boolean saveFileByType(String fileName,int typeFile,String path,InputStream in)
	{
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		
		 try 
		 {
			 if(searchsum.getSearchSummary() !=null)
			 {
					Integer prospectusID = Integer.parseInt(searchsum.getSearchSummary().split("::")[2].trim());
					Integer companyID    = Integer.parseInt(searchsum.getSearchSummary().split("::")[3].trim());
					
			 log.info("!!!!!!!!!!!!!!!!! RUTA ARCHIVO= "+path);
             // write the inputStream to a FileOutputStream
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
                  
            //thisFiles=filesService.getFileByTypeID(prospectusID, companyID, typeFile);
             if(thisFiles==null)
             {
            	 
            	 setThisFiles(new Files());
            	//FilesPK filePk=new FilesPK(prospectusID,companyID);
            	 //getThisFiles().setFilesPk(filePk);
            	 getThisFiles().setUpload_date(new Date());
            	 getThisFiles().getFilesPk().setFile_type_id(typeFile);
            	 getThisFiles().setLocation(path);
            	 getThisFiles().setApproved("1");            	 
            	 filesService.addFile(getThisFiles(),prospectusID , companyID);
             } else {            	 
            	 getThisFiles().getFilesPk().setFile_type_id(typeFile);
            	 getThisFiles().setLocation(path);
            	 getThisFiles().setUpload_date(new Date());
            	 filesService.updateFile(getThisFiles());
             }
			 }
             log.info("!!!!!!!!!!!!!!!!!     SE guardo y CREO EL ARCHIVO  CORRECTAMENTE     !!!!!!!!!!!!!!!!");
             return true;
            
             } catch (IOException e) {
            	 log.info("!!!!!!!!!! UUUUPS ERROOOOOOR AL SUBIR EL ARCHIVO  "+e.getMessage());
            	 return false;
             }
	}
	
	public void search_expedient(ActionEvent event)
	{
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		external  = faces.getExternalContext();
		
		int searchID = Integer.parseInt((String) event.getComponent().getAttributes().get("searchID"));
		
		log.info("ID de busqueda es ="+searchID);
		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		sesion    = (SessionBean)         resolver.getValue(elContext, null, "sessionBean");
		
		if(searchsum.getSearchSummary()!= null)
		{
			chart = null;
			
			init_menu_item(searchID);			
			
		} else {
			
			log.info("No hay se encontro prospectus");
		}
	}
	
	public void editDocument(ActionEvent e)
	{
		String documents = (String) e.getComponent().getAttributes().get("documents").toString();
		request = RequestContext.getCurrentInstance();
		
			String formatFile="";
			String nameFile="";
			Integer typeFileID=null;
			String pathOriginalImg=null;
			
		if(documents!=null)
		{
			try
			{			
				pathOriginalImg=documents.split("::")[2];
				formatFile= pathOriginalImg.substring(pathOriginalImg.lastIndexOf(".")+1);
				
			} catch (Exception ex) {
				log.info("Por defaulf pone .JPG");
				formatFile="jpg";
			}			
		}
		
		try 
		{				
			nameFile=Utilities.getRandomName()+"."+formatFile;	
			typeFileID=Integer.parseInt(documents.split("::")[3]);
			InputStream in=new FileInputStream(new File(pathOriginalImg));
			
			if(Utilities.copyFile(getRealPath()+"/temp/"+nameFile,in))
			{				
				ELContext elContext = FacesContext.getCurrentInstance().getELContext();
				EditImageSession editImg= (EditImageSession) FacesContext.getCurrentInstance()
		                .getApplication().getELResolver()
		                .getValue(elContext, null, "editImageSession");
				editImg.setProspectus_id(Integer.parseInt(documents.split("::")[0]));
				editImg.setCompany_id(Integer.parseInt(documents.split("::")[1]));
				editImg.setOriginalImage(pathOriginalImg);
				editImg.setRotateImage(nameFile);
				editImg.setFormatImage(formatFile);
				editImg.setTypeFileID(typeFileID);
				editImg.setFile_id(Integer.parseInt(documents.split("::")[4]));
				
				if(formatFile.toUpperCase().equals("PDF")){
					request.addCallbackParam("format",formatFile.toUpperCase());
				}else{
					Hashtable<String,Integer> wh=ImageUtils.getWidthAndHeightImg(new File(pathOriginalImg));
					request.addCallbackParam("Width",wh.get("Width"));
					request.addCallbackParam("Height",wh.get("Height"));
					request.addCallbackParam("format",formatFile.toUpperCase());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void loadDocumentation()
	{
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		if(isCreden_fm2_dip())
		{
			listFilesP = null;
			
			search_documentation();
		}
	}
	
	public List<ClientViewFullName> completeinfoclient(String strSearch)
	{
		int MAIL = 3;
		
		List<ClientViewFullName> suggestions = new ArrayList<ClientViewFullName>();
		
		if(getRadioTypeSearch() == 1)
		{
			suggestions = clientViewFullNameService.getListClientByName(strSearch);
			
		} else if(getRadioTypeSearch() == MAIL) {
			
			suggestions = clientViewFullNameService.getListClientByEmail(strSearch);
		}
		
		return suggestions;
		
	}
	
}
