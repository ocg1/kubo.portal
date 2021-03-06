package mx.com.kubo.mesa.solicitud.documentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.kubows.AcceptedFileRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.managedbeans.generales.DocumentUpload;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

public final class DocumentacionIMP extends DocumentacionAMO
implements DocumentacionIMO
{
	public final void init()
	{
		membership_PK = new MembershipPK(prospectus_id, company_id);
		
		membership = service_membership.getMembershipById(membership_PK);
		
		init_list_files();
		init_change_control();										
		init_lista_file_type();
		init_proyect_IMG();
		init_file_type_menu_items();								
		init_lista_RECA();
		
		size_limit_DEFAULT   = init_system_param(SIZE_LIMIT_DEFAULT);
		size_limit_EXCEPTION = init_system_param(SIZE_LIMIT_EXCEPTION);		
		exception_list       = init_system_param(EXCEPTION_LIST).split(",");				
	}

	public final void init_file_type_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) event.getComponent();
		
		file_type_id = Integer.parseInt(select_one_menu.getValue().toString());
		
		reca_ENABLED = file_type_id == CONTRATO_CREDITO_FIRMADO || (file_type_id >= CONTRATO_CREDITO_FIRMADO_02 && file_type_id <= CONTRATO_CREDITO_FIRMADO_05);
		
		logo_ENABLED = file_type_id == -1 || file_type_id == -2 || file_type_id == -3;
		
		file_type_ENABLED = false;
		
		init_size_limit();
				
		request.addCallbackParam("file_type_id", file_type_id);
		request.addCallbackParam("reca_ENABLED", reca_ENABLED);
		request.addCallbackParam("size_limit", size_limit);
	}
	
	public final void init_reca_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) event.getComponent();
		
		reca_id = Integer.parseInt(select_one_menu.getValue().toString());
		
		request.addCallbackParam("reca_id", reca_id);
	}
	
	public final void handleFileUpload(FileUploadEvent event) 
	{
		try
		{
			faces = FacesContext.getCurrentInstance();
			resolver  = faces.getApplication().getELResolver();
			elContext = faces.getELContext();
			external  = faces.getExternalContext();
			
			documentUpload  = (DocumentUpload) resolver.getValue(elContext, null, "documentUpload");
			
			format = "";
				
			try
			{
				file_name = event.getFile().getFileName();
				
				format = file_name.substring(file_name.lastIndexOf("."));
				
			} catch (Exception e) {
				
				format = ".jpg";
			}
				
			filetypepk = new FileTypePK() ;
			filetypepk.setCompany_id(company_id);
			filetypepk.setFile_type_id(file_type_id);
				
			filetype = service_file_type.getFileTypeById(filetypepk);
				
			if(file_type_id == -1 || file_type_id == -2 || file_type_id == -3)
			{
				filetype = new FileType();
				filetype.setFile_category_id(-1);
				filetype.setFileTypePk(filetypepk);
			}
				
			try
			{
				if(proyect_loan != null)
				{
					documentUpload.fileUpload(event.getFile().getInputstream(), proyecto, company_id, prospectus_id , filetype.getFile_category_id(), file_type_id, false, false, format, proyect_loan_id, reca_id);
					
				} else {
					
					documentUpload.fileUpload(event.getFile().getInputstream(), proyecto, persona.getNatPerPK().getCompany_id(), persona.getNatPerPK().getProspectus_id(), filetype.getFile_category_id(), file_type_id, false, false, format, 1,reca_id);
				}
				
			} catch(Exception e) {
				
				e.printStackTrace();
			}				
			
			htWH     = null;
			fileLogo = null;
			
/*			
			destination = external.getRealPath("/resources/");
*/			
		
			init();
			init_proyect_LOGOS();
				
		} catch(Exception e) {
			
			e.printStackTrace();
		}		
	}
	
	public void editDocument(ActionEvent event)
	{
		request = RequestContext.getCurrentInstance();
		faces   = FacesContext.getCurrentInstance();
		
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		external  = faces.getExternalContext();
		
/*		
		String realPath = external.getRealPath("//resources//");
*/		
		
		String documents = (String) event.getComponent().getAttributes().get("documents").toString();
						
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
				formatFile=formatFile.toLowerCase();
				
			} catch (Exception ex) {
				
				formatFile="jpg";
			}			
		}
		
		try 
		{				
			nameFile = Utilities.getRandomName()+"."+formatFile;
			
			typeFileID = Integer.parseInt(documents.split("::")[3]);
			
			InputStream in = new FileInputStream(new File(pathOriginalImg));
			
			if(Utilities.copyFile(real_path+"/temp/"+nameFile,in))
			{				
				elContext = faces.getELContext();
				
				resolver = faces.getApplication().getELResolver();
				
				editImg = (EditImageSession) resolver.getValue(elContext, null, "editImageSession");
				
				editImg.setProspectus_id(Integer.parseInt(documents.split("::")[0]));
				editImg.setCompany_id   (Integer.parseInt(documents.split("::")[1]));
				editImg.setFile_id      (Integer.parseInt(documents.split("::")[4]));								
				editImg.setProyectLoanID(Integer.parseInt(documents.split("::")[5]));
				editImg.setOriginalImage(pathOriginalImg);
				editImg.setRotateImage(nameFile);
				editImg.setFormatImage(formatFile);
				editImg.setTypeFileID(typeFileID);
				
				
				if(formatFile.toUpperCase().equals("PDF"))
				{
					request.addCallbackParam("format",formatFile.toUpperCase());
					
				} else {
					
					wh = ImageUtils.getWidthAndHeightImg(new File(pathOriginalImg));
					
					request.addCallbackParam("Width", wh.get("Width"));
					request.addCallbackParam("Height",wh.get("Height"));
					request.addCallbackParam("format",formatFile.toUpperCase());
				}								
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}		
	}
	
	boolean flagNot = true;
	
	public void validaArchivo()
	{
		try
		{			
			if( flagNot )
			{
				
				flagNot = !flagNot;
			
			locator = new  PublicProyectServiceLocator();
			
			public_proyect =  locator.getPublicProyect();
			
			String[] val =  valAutorizaDoc.split("::");
			
			System.out.println( "valAutorizaDoc: " + valAutorizaDoc );
			
			
			if( val != null && val.length >2 )
			{
			
			//             0                      1                 2                 3                   4               5
			//	'#{file.prospectus_id}::#{file.company_id}::#{file.typeFile}::#{file.file_id}::#{file.proyect_loan_id}::VALID'
				
			String valid = val[5];
			String file_type_id = val[ 2 ];
			String proyect_loan_id = val[ 4 ];
			String message = "";
			
			acceptedfile = new AcceptedFileRequest() ;
			
			acceptedfile.setAccepted( valid.equals("S") );
			acceptedfile.setAccepted_prospectus_id( accepted_prospectus_id+"" );
			acceptedfile.setClient_prospectus_id(persona.getNatPerPK().getProspectus_id() + "");
			acceptedfile.setCompany_id(persona.getNatPerPK().getCompany_id() + "");
			acceptedfile.setFile_type_id(file_type_id);
			acceptedfile.setMessage(message);
			acceptedfile.setProyect_loan_id(proyect_loan_id);
									
			response =  public_proyect.acceptedFile(acceptedfile);
			
			boolean success = false;
			
			if( response.getStatus() !=  null && response.getStatus().equals("0")  ) 
			{
				success = true;
			}else{
				success = false;
			}
			
			String mensage = response.getMessage();
			
			String msg[] = mensage.split("::");
			
			
			
			String mensage_txt =  "";
			
				for( int i = 0 ; i < msg.length; i++ )
				{
					mensage_txt += "<br />" + msg[i] ;
				}
				
				request   = RequestContext.getCurrentInstance();
				
				
				request.addCallbackParam("mensage_txt", mensage_txt);
				request.addCallbackParam("success", success);
			
				init();
				
				enviaContratos();
				
			}
						
			} else {
				
				flagNot = !flagNot;
			}
			
		} catch( Exception ex ) {
			
			ex.printStackTrace();
			
		}					
	}
}
