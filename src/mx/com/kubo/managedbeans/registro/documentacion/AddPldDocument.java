package mx.com.kubo.managedbeans.registro.documentacion;

import static mx.com.kubo.files.CatalogoFileType.CREDENCIAL_ELECTOR_FRENTE;
import static mx.com.kubo.files.CatalogoFileType.CREDENCIAL_ELECTOR_REVERSO;
import static mx.com.kubo.files.CatalogoFileType.FM2_FRENTE;
import static mx.com.kubo.files.CatalogoFileType.FM3_REVERSO;
import static mx.com.kubo.files.CatalogoFileType.PASAPORTE;
import static mx.com.kubo.files.CatalogoFileType.SELFIE_IDENTIFICACION;
import static mx.com.kubo.files.CatalogoFileType.CARTILLA;
import static mx.com.kubo.files.CatalogoFileType.CEDULA;
import static mx.com.kubo.files.CatalogoFileType.LICENCIA;
import static mx.com.kubo.files.CatalogoFileType.LICENCIA_REVERSO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.FileForScreenBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.controller.sgbRest.SgbHelp;
import mx.com.kubo.files.CatalogoFileType;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Expenses;

import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.Income;

import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.InfoNotification;
import mx.com.kubo.model.InfoScreen;
import mx.com.kubo.model.InfoScreenPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SegmentAction;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.model.segment.SegmentProspectusPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SegmentActionService;
import mx.com.kubo.services.SegmentProspectusService;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "addPldDocument") @ViewScoped
public class AddPldDocument extends AddPldDocumentAMO
implements Serializable
{
	private static final long serialVersionUID = -7079567388468301756L;

	@PostConstruct
	public void init()
	{	
		//System.out.println( " ++ INIT AddPldDocument" );
		
		faces    = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		external = faces.getExternalContext();
		
		realPath = (faces.getExternalContext().getRealPath("//resources//"));
				
		sesion  = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		if( isSesion_DISABLED() ){
			return;
		}
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		sameAddress = "N";
		
		if( !(sesion.getArea().toString().equals("I")))
		{
			thisProyect     = proyectService.getMaxProyect(prospectus_id,company_id);
			thisProyectLoan =  proyectLoanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			proyect_loan_id = thisProyectLoan.getProyectloanPk().getProyect_loan_id();
			
			enabled_whatsapp = thisProyectLoan.getStatus_id() != 11;
			
		}else{
			enabled_whatsapp = false;
		}
		
		MembershipPK mpk = new MembershipPK(prospectus_id, company_id);
		
		membership = service_membership.getMembershipById(mpk);
		
		naturalperson = membership.getPerson();
		
		natural_person_PK = naturalperson.getNatPerPK();
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(company_id);
		spk.setSystem_param_id(92);
		
		SystemParam sys = systemparamservice.loadSelectedSystemParam(spk); // Hora de Inicio de Actividades Kubo
		
		horaInicioSys = sys.getValue();
		
		spk.setCompany_id(company_id);
		spk.setSystem_param_id(93);
		
		sys =systemparamservice.loadSelectedSystemParam(spk); // Hora de Término de Actividades Kubo
		
		horaTerminoSys = sys.getValue();
		
		int citizenship = NACIONAL;
		
		if( naturalperson.getCitizenship() != null){
			
			citizenship = naturalperson.getCitizenship();
		
		}else{
			naturalperson.setCitizenship(NACIONAL);
		}
		
		if(citizenship == NACIONAL)
		{
			datos_credencial_elector_ENABLED = true;
		} 
		
		if(citizenship == EXTRANJERO)
		{
			datos_credencial_elector_ENABLED = false;
		}
												
		listCompDomi = fileTypeService.getListFileTypeByCategory(5);
		
		init_prevencion();		
		init_third_party_name();
		init_lista_foto_proyecto();
		init_lista_documentos();		
		init_lista_credenciales();
		init_employment_business();	
		init_file_category();
		
		if( !(sesion.getArea().toString().equals("I")))
		{
			initTask();
		}
		
		init_address();
		
		init_same_Address();
		
		init_lista_dias_meses();
		init_lista_years();	
		
		init_validity_date();
		
		init_pospectus_comment();
		
		lista_tipo_credencial = service_catalogos.getLista_tipo_credencial();	
		
		/* if( naturalperson  != null && naturalperson.getCountry_of_residence() != null && naturalperson.getCountry_of_residence().intValue() == 1 ){
			sameAddress = "S";
		}*/
		
		sameAddress = "N";
		
	}
	
	public final void listener_clave_elector(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		clave_elector = (String) input_text.getValue();
		
		System.out.println("AddPldDocument.listener_clave_elector(): " + clave_elector);
		
		naturalperson.setMx_ife_cveelector(clave_elector);
		
		saveData();
	}
	
	public final void listener_tipo_credencial(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		tipo_credencial = (Integer) input_radio.getValue();
		
		System.out.println("AddPldDocument.listener_tipo_credencial(): " + tipo_credencial);
		
		sameAddress = "N";
		
		naturalperson.setIdentification_type_id(tipo_credencial);
			
			if( sesion.getArea().toString().equals("L") ){
				
				if(info != null){
				
				 info.setMx_ife_domicilio(sameAddress);
				 proyectLoanInfoService.updateProyectLoanInfo(info);
				 
				}else{
					
					info =  new ProyectLoanInfo();
					
					ProyectLoanInfoPK pkInfo = new ProyectLoanInfoPK();
					 
					 pkInfo.setCompany_id( thisProyectLoan.getProyectloanPk().getCompany_id());
					 pkInfo.setProspectus_id( thisProyectLoan.getProyectloanPk().getProspectus_id() );
					 pkInfo.setProyect_id( thisProyectLoan.getProyectloanPk().getProyect_id() );
					 pkInfo.setProyect_loan_id( thisProyectLoan.getProyectloanPk().getProyect_loan_id() );
					
					 info.setPk(pkInfo);
					 info.setMx_ife_domicilio("N");
					 proyectLoanInfoService.saveProyectLoanInfo(info);
					
				}
				
			 }else if( sesion.getArea().toString().equals("I") ){
				 
				 if( inv != null ){
					
					 inv = new Investor();
					 
					 InvestorPK invPk = new InvestorPK();
						
					invPk.setCompany_id(company_id);
					invPk.setProspectus_id(prospectus_id);
					
					inv.setPk(invPk);
					
					inv.setMx_ife_domicilio( sameAddress );
					investorservice.addInvestor(inv);
					 
				 }else{
				 
					 inv.setMx_ife_domicilio( sameAddress );
					 investorservice.updateInvestor(inv);
				 
				 }
				 
			 }
			
		saveData();
		
		request.addCallbackParam("tipo_credencial", tipo_credencial);
	}
	
	public final void listener_numero_emision(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		mx_ife_numemision = (Integer) input_text.getValue();
		
		System.out.println("AddPldDocument.listener_numero_emision(): " + mx_ife_numemision);
		
		naturalperson.setMx_ife_numemision(mx_ife_numemision);
		
		saveData();
	}
	
	public final void listener_ife_seccion(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		ife_seccion = (String) input_text.getValue();
		
		System.out.println("AddPldDocument.listener_ife_seccion(): " + ife_seccion);
		
		naturalperson.setMx_ife_seccion(ife_seccion);
		
		saveData();
	}
	
	public final void listener_numero_vertical_OCR(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		mx_ife_numvertical = (String) input_text.getValue();
		
		System.out.println("AddPldDocument.listener_numero_vertical_OCR(): " + mx_ife_numvertical);
		
		naturalperson.setMx_ife_numvertical(mx_ife_numvertical);
		
		saveData();
	}
	
	public final void init_third_party_name(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();				
		
		init_third_party_name();		
		
		request.addCallbackParam("third_party_name", third_party_name);
	}
	
	public final void saveData()
	{
		if(naturalperson != null)
		{
			service_natural_person.update(naturalperson);
		}
	}

	public void listDocumentAdded()
	{
		lDocAddedCredFm2          = new ArrayList<FileForScreenBean>();
		lDocAddedCompActEcon      = new ArrayList<FileForScreenBean>();
		lDocAddedCompIncome       = new ArrayList<FileForScreenBean>();
		lDocAddedAcredProBusiness = new ArrayList<FileForScreenBean>();
		lDocAddedCompDomi         = new ArrayList<FileForScreenBean>();
		
		//Se modifico la siguiente linea para agregar el proyectLoanID.
		if(thisProyectLoan!= null)
		{			
			setListFiles(filesService.getListFilesByProspect(sesion.getProspectus_id(), sesion.getCompany_id(), thisProyectLoan.getProyectloanPk().getProyect_loan_id()));
			
		} else if(sesion.getArea().toString().equals("I")){
			
			setListFiles(filesService.getListFilesByProspect(sesion.getProspectus_id(), sesion.getCompany_id(), 1 ));
		}
		
		
		if(getListFiles().size() > 0)
		{
			for(Files regis: listFiles)
			{
				file_type_id  = regis.getFilesPk().getFile_type_id();				
				uploaded_text = regis.getFileType().getUploaded_text();
				
				file_type     = CatalogoFileType.getInstance(file_type_id);
				
				if(file_type == CREDENCIAL_ELECTOR_FRENTE || file_type == CREDENCIAL_ELECTOR_REVERSO || file_type == CARTILLA ||  file_type == PASAPORTE || file_type == CEDULA || file_type == LICENCIA || file_type == LICENCIA_REVERSO  )
				{	
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCredFm2.add(file);
					
				} else if(file_type == FM2_FRENTE || file_type == FM3_REVERSO || file_type == PASAPORTE) {
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCredFm2.add(file);
					
				} else if(file_type_id >=3 && file_type_id <= 8){
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompActEcon.add(file);	
					
				} else if(file_type_id >= 9 && file_type_id <= 14 || ( file_type_id >=48 && file_type_id <= 58  || (file_type_id >= 126 && file_type_id <= 130 ) )){
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompIncome.add(file);
					
				} else if(file_type_id >= 15 && file_type_id <= 23){	
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedAcredProBusiness.add(file);
					
				} else if( (file_type_id >= 24 && file_type_id <= 29 ) || ( file_type_id >= 122 && file_type_id <= 125 ) ){
					
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompDomi.add(file);
					
				} else if( file_type == SELFIE_IDENTIFICACION ){
					dispSelfieIdentificacion = "inline";
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					setSelfieIdentification(file);
				}
			}
		}
	}
	
	public void uploadFileLogo(FileUploadEvent event){
		String formatFile="";
		String nameFile="";
		String nameFileThumb="";
		String metadata;
		
		String pathDocument = "/documents/cia_" + sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+ "/photo";		
		String pathHistoric = "/historic/cia_"+sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+"/photo";
		
		if(event!=null){
			try{			
				formatFile= event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));				
			}
			catch (Exception e) {
				log.info("Por defaulf pone .JPG");
				formatFile=".jpg";
			}
			
		}		
		
		if(thisProyect!=null && Utilities.createDirectory(realPath + pathDocument) && Utilities.createDirectory(realPath + pathHistoric)){						
			try {
				ImagesBean imgLogos=null;
				Hashtable<String, Integer> htWH=null;
				String randomName = "";
				
				if(getListImgLogos().size()==1){
					if(getListImgLogos().get(0).isSave()){
						randomName = Utilities.getRandomName(); 
						//nameFile="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;						
						nameFile="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+formatFile;
						nameFileThumb="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+"_thumb"+formatFile;
						Utilities.deleteFileDirByEqualName(new File(realPath+pathDocument),"proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
						Utilities.deleteFileDirByEqualName(new File(realPath+pathHistoric),"proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
						
						getThisProyect().setLogo2(pathDocument+nameFile);
						Utilities.copyFile(realPath + pathDocument+nameFile, event.getFile().getInputstream());
						Utilities.copyFile(realPath + pathHistoric+nameFile, event.getFile().getInputstream());
						
						Utilities.copyFile(realPath + pathDocument+nameFileThumb, event.getFile().getInputstream());
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
						
						imgLogos=new ImagesBean();
						htWH=ImageUtils.scaleImage(new File(realPath+pathDocument+nameFile), 445, 305);
						imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);						
						imgLogos.setUrlImg(pathDocument+nameFile);
						imgLogos.setUrlImgThumb(pathDocument+nameFileThumb);
						imgLogos.setPathOriginal(realPath+pathDocument+nameFile);
						imgLogos.setTypeLogo(1);	
						imgLogos.setSave(true);
						getListImgLogos().add(imgLogos);
						setOtherTitle(true);
						
							metadata=ImageUtils.getMetadata(new File(realPath+pathDocument+nameFile));
							if(metadata!=null)
								getThisProyect().setMetadata2(metadata);
							else
								getThisProyect().setMetadata2(null);
						
					}else{
						
						randomName = Utilities.getRandomName(); 
						//nameFile="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ Utilities.getRandomName()+formatFile;						
						nameFile="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ randomName+formatFile;
						nameFileThumb="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ randomName+"_thumb"+formatFile;
						
						Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto1"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
						Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto1"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
						getThisProyect().setLogo(pathDocument+nameFile);
						Utilities.copyFile(realPath + pathDocument+nameFile, event.getFile().getInputstream());
						Utilities.copyFile(realPath + pathHistoric+nameFile, event.getFile().getInputstream());
						
						Utilities.copyFile(realPath + pathDocument+nameFileThumb, event.getFile().getInputstream());
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
						
						imgLogos=new ImagesBean();
						htWH=ImageUtils.scaleImage(new File(realPath+pathDocument+nameFile), 445, 305);
						imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);	
						imgLogos.setUrlImg(pathDocument+nameFile);
						imgLogos.setUrlImgThumb(pathDocument+nameFileThumb);
						imgLogos.setPathOriginal(realPath+pathDocument+nameFile);
						imgLogos.setSave(true);
						imgLogos.setTypeLogo(0);
						getListImgLogos().clear();
						getListImgLogos().add(0, imgLogos);
						setOtherTitle(true);
						
							metadata=ImageUtils.getMetadata(new File(realPath+pathDocument+nameFile));
							if(metadata!=null)
								getThisProyect().setMetadata1(metadata);
							else
								getThisProyect().setMetadata1(null);
					}
						
						
				}else if(getListImgLogos().size()==2){
					randomName = Utilities.getRandomName(); 
					//nameFile="/proyectphoto3_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ Utilities.getRandomName()+formatFile;
					nameFile="/proyectphoto3_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ randomName+formatFile;
					nameFileThumb="/proyectphoto3_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+ randomName+"_thumb"+formatFile;
					getThisProyect().setLogo3(pathDocument+nameFile);					
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					
					Utilities.copyFile(realPath + pathDocument+nameFile, event.getFile().getInputstream());
					Utilities.copyFile(realPath + pathHistoric+nameFile, event.getFile().getInputstream());
					
					Utilities.copyFile(realPath + pathDocument+nameFileThumb, event.getFile().getInputstream());
					
					//Redimenciona y guarda img
					ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
					
					
					imgLogos=new ImagesBean();
					htWH=ImageUtils.scaleImage(new File(realPath+pathDocument+nameFile), 445, 305);
					imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
					imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);	
					imgLogos.setUrlImg(pathDocument+nameFile);
					imgLogos.setUrlImgThumb(pathDocument+nameFileThumb);
					imgLogos.setPathOriginal(realPath+pathDocument+nameFile);
					imgLogos.setSave(true);
					imgLogos.setTypeLogo(2);
					
					getListImgLogos().add(imgLogos);
					setOtherTitle(true);

						metadata=ImageUtils.getMetadata(new File(realPath+pathDocument+nameFile));
						if(metadata!=null)
							getThisProyect().setMetadata3(metadata);
						else
							getThisProyect().setMetadata3(null);
						
				}
				
				proyectService.update(getThisProyect());
				setThisDocumentId("checkImgLogo");
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Error al copiar el logo");
			}
			setImgLogo(pathDocument+nameFile);
		}
	}
	
	public void removeFileLogo(ActionEvent e) throws FileNotFoundException{
		int typeLogo=Integer.parseInt((String) e.getComponent().getAttributes().get("valueTypeLogo").toString());
		String pathDocument = "/documents/cia_" + sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+ "/photo";		
		String pathHistoric = "/historic/cia_"+sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+"/photo";
		
		
		if(thisProyect!=null){
				switch (typeLogo) {
				case 1:		
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					
					if(getThisProyect().getLogo3()!=null){
						String formatFile="";
						try{			
							formatFile= getThisProyect().getLogo3().substring(getThisProyect().getLogo3().lastIndexOf("."));				
						}
						catch (Exception ex) {
							log.info("Por defaulf pone .JPG");
							formatFile=".jpg";
						}
							File forigDoc=new File(realPath+getThisProyect().getLogo3());
							File forigHist= new File(realPath+pathHistoric+"/"+forigDoc.getName());
							
							//Se obtiene todo el path de la imagen orignal sin la extencion para modificarla y crear un thumb
							String OrigFilePath = (realPath+getThisProyect().getLogo3()).substring(0, (realPath+getThisProyect().getLogo3()).lastIndexOf("."));
							
							File forigDocThumb = new File(OrigFilePath+"_thumb"+formatFile);
							
							
							
							
							if(forigDoc.exists() && forigHist.exists()){
								
								
								//Si no existe el archivo thumb se crea
								if(!forigDocThumb.exists()){
									//Se crea el archivo temporal.
									Utilities.copyFile(OrigFilePath+"_thumb"+formatFile, new FileInputStream(forigDoc));
									
									//Redimenciona y guarda img
									ImageUtils.resizeAndSaveImg(OrigFilePath+"_thumb"+formatFile);
								}
								String randomName = Utilities.getRandomName();
								//String nameRename="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
								String nameRename="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+formatFile;
								String nameRenameThumb="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+"_thumb"+formatFile;
								
									 File fdestDoc=new File(realPath+pathDocument+nameRename);		
									 File fdestHist=new File(realPath+pathHistoric+nameRename);
									 File fdestDocThumb = new File(realPath+pathDocument+nameRenameThumb);
									 forigDoc.renameTo(fdestDoc);
									 forigHist.renameTo(fdestHist);
									 forigDocThumb.renameTo(fdestDocThumb);
									 getThisProyect().setLogo2(pathDocument+nameRename);
									 getThisProyect().setMetadata2(getThisProyect().getMetadata3());
									 getListImgLogos().get(2).setTypeLogo(1);
									 getListImgLogos().get(2).setPathOriginal(realPath+pathDocument+nameRename);	
									 getThisProyect().setLogo3(null);
									 getThisProyect().setMetadata3(null);
							}else log.info("No existe el archivo");					
					}else{
						getThisProyect().setLogo2(null);
						getThisProyect().setMetadata2(null);
					}
					break;
				case 2:					
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					getThisProyect().setLogo3(null);
					getThisProyect().setMetadata3(null);
					break;
				default:
					break;
				}
				getListImgLogos().remove(typeLogo);						
				proyectService.update(getThisProyect());
			
		}
	}
	
	public void editFileLogo(Integer index)
	{
		
		editFileAction (  index , getFileLogo() );
		
	}
	
	public void editFileLogo(FileUploadEvent event){
		String nameComponent=event.getComponent().getClientId();
		Integer typeLog=Integer.parseInt(nameComponent.split(":")[1]);
		
		editFileAction (  typeLog ,event.getFile() );
        
	}
		
	public void editFileAction (Integer  typeLog , UploadedFile evFile) {
		
		log.info("El tipo de logo a modificar es= "+typeLog);
		
		String formatFile="";
		String nameFile="";
		String nameFileThumb = "";
		String metadata;
		String randomName = "";
		
		String pathDocument = "/documents/cia_" + sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+ "/photo";		
		String pathHistoric = "/historic/cia_"+sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+"/photo";
		
		
		if(evFile!=null){
			try{			
				formatFile= evFile.getFileName().substring(evFile.getFileName().lastIndexOf("."));				
			}
			catch (Exception e) {
				log.info("Por defaulf pone .JPG");
				formatFile=".jpg";
			}
			
		}		
		
		if(thisProyect!=null && Utilities.createDirectory(realPath + pathDocument) && Utilities.createDirectory(realPath + pathHistoric)){						
			try {

				Hashtable<String, Integer> htWH=null;
				
				switch (typeLog) {
				case 0:
					
					randomName = Utilities.getRandomName();
					
					//nameFile="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
					
					nameFile="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+formatFile;
					nameFileThumb="/proyectphoto1_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+"_thumb"+formatFile;
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto1"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto1"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());										
					
					getThisProyect().setLogo(pathDocument+nameFile);
					
					if(Utilities.copyFile(realPath + pathDocument+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathHistoric+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathDocument+nameFileThumb, evFile.getInputstream())){
						metadata=ImageUtils.getMetadata(new File(realPath + pathDocument+nameFile));
						
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
						
						if(metadata!=null)
							getThisProyect().setMetadata1(metadata);
						else
							getThisProyect().setMetadata1(null);
					}
					
					break;
				case 1:
					randomName = Utilities.getRandomName();
					//nameFile="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
					nameFile="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+formatFile;
					nameFileThumb="/proyectphoto2_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+"_thumb"+formatFile;
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto2"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());		
					
					getThisProyect().setLogo2(pathDocument+nameFile); 
					
					if(Utilities.copyFile(realPath + pathDocument+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathHistoric+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathDocument+nameFileThumb, evFile.getInputstream())){
						metadata=ImageUtils.getMetadata(new File(realPath + pathDocument+nameFile));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
						
						if(metadata!=null)
							getThisProyect().setMetadata2(metadata);
						else
							getThisProyect().setMetadata2(null);
					}
					
					
					break;
				case 2:
					randomName = Utilities.getRandomName();
					nameFile="/proyectphoto3_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+formatFile;
					nameFileThumb="/proyectphoto3_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+randomName+"_thumb"+formatFile;
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), "proyectphoto3"+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
						
					getThisProyect().setLogo3(pathDocument+nameFile);
					
					if(Utilities.copyFile(realPath + pathDocument+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathHistoric+nameFile, evFile.getInputstream()) &&
					   Utilities.copyFile(realPath + pathDocument+nameFileThumb, evFile.getInputstream())){
						metadata=ImageUtils.getMetadata(new File(realPath + pathDocument+nameFile));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(realPath + pathDocument+nameFileThumb);
						
						if(metadata!=null)
							getThisProyect().setMetadata3(metadata);
						else
							getThisProyect().setMetadata3(null);
					}
					break;
				default:
					break;
				}		
				
				for (ImagesBean itera : getListImgLogos()) {
					if(itera.getTypeLogo().equals(typeLog)){
						htWH=ImageUtils.scaleImage(new File(realPath+pathDocument+nameFile), 445, 305);
						itera.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						itera.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
						itera.setUrlImg(pathDocument+nameFile);
						itera.setUrlImgThumb(pathDocument+nameFileThumb);
						itera.setPathOriginal(realPath+pathDocument+nameFile);
						itera.setSave(true);
						itera.setTypeLogo(typeLog);
						break;
					}
					
				}			
				proyectService.update(getThisProyect());
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Error al copiar el logo");
			}
		}
	}
	
	public void handleFileIdentifyType(FileUploadEvent event)
	{
		int  typeFile = getIdentifyTypeId();
		event.getComponent().setId("file_credIdentificacion");
		setTypeCredFm2(typeFile);
		handleFileUpload(event);
	}
	
	public void handleFileCredFmDos(FileUploadEvent event)
	{
		int  typeFile = Integer.parseInt((String) event.getComponent().getAttributes().get("typefile").toString());
		event.getComponent().setId("file_credFm2");
		setTypeCredFm2(typeFile);
		handleFileUpload(event);
	}
		
	public void handleFileUpload(FileUploadEvent event) 
	{	
		
		System.out.println("----handleFileUpload---");
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelScript");
		
		String component_id = event.getComponent().getId();
		
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
		
		String typeID = event.getComponent().getId();
		
		String pathDocument = "/documents/cia_" + sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+ "/";		
		String pathHistoric = "/historic/cia_"  + sesion.getCompany_id()+"/pros_" + sesion.getProspectus_id()+"/";				
		
		if( ( typeID.equals("file_credFm2") || typeID.equals("file_credIdentificacion")  ) && getTypeCredFm2() != 0 )
		{
			FileType fileType = fileTypeService.getFileTypeById(new FileTypePK(getTypeCredFm2(),sesion.getCompany_id()));
			String nameFile   = fileType.getAbreviation();
			String category   = fileType.getFileCategory().getAbreviation();
			
			Utilities.createDirectory(realPath + pathDocument + category);
			Utilities.createDirectory(realPath + pathHistoric + category);
			
			if(thisProyectLoan != null)
			{
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
			}else if(sesion.getArea().toString().equals("I")){
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile);
				//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile);
			}
				
			if(thisProyectLoan != null)
			{
				nameFile += "_" + thisProyectLoan.getProyectloanPk().getProyect_loan_id() + "_" 
						 + sesion.getProspectus_id() + "_" 
						 +(getThisProyect() != null ? getThisProyect().getProyectoPk().getProyect_id() : "") + "_" 
						 + Utilities.getRandomName() 
						 + formatFile;
				
			} else if(sesion.getArea().toString().equals("I")){
				nameFile += "_" + sesion.getProspectus_id() + "_" + Utilities.getRandomName() + formatFile;
			}
			
			try 
			{
				
				String file_name = realPath + pathDocument + category + "/" + nameFile;
				String historico = realPath + pathHistoric + category + "/" + nameFile;
				String path      = pathDocument  + category + "/" + nameFile;
				
				boolean save_file = saveFileByType(file_name, getTypeCredFm2(), path, event.getFile().getInputstream());
				
				boolean copy_file = Utilities.copyFile(historico, event.getFile().getInputstream());				
				
				if(save_file && copy_file)
				{	
					if( getTypeCredFm2() != 119 ){
					
						listDocumentAdded();
						
						setThisDocumentId(null);
						
						if( (lDocAddedCredFm2.size() == 3 && naturalperson.getCitizenship() == EXTRANJERO) )
						{
							setThisDocumentId("checkCredFm2");
							
						}else{
							
							if( naturalperson.getIdentification_type_id() != null && ( naturalperson.getIdentification_type_id() == 1 || naturalperson.getIdentification_type_id() == 2 || naturalperson.getIdentification_type_id() == 5 )  ){
							
								if(lDocAddedCredFm2.size() == 2 ){
									setThisDocumentId("checkCredFm2");
								}
								
							}else{
								
								if(lDocAddedCredFm2.size() == 1 ){
									setThisDocumentId("checkCredFm2");
								}
								
							}
							
						}
					
					}else{
						
						listDocumentAdded();
						
					}
					
				} else {
					
					setThisDocumentId(null);
				}
				
			} catch (Exception e) {
				log.info("Error al guardar el archivo");
				e.printStackTrace();
				setThisDocumentId(null);
			}
			
			if( getTypeCredFm2() != 119 ){
			
				requestContext.addPartialUpdateTarget("listCredFm2");
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("listCredFm2");
			
			}else{
			
				requestContext.addPartialUpdateTarget("sectionSelfie");
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("sectionSelfie");
			
			}
			
		} else {
			
			if(typeID.equals("file_compActEcom") && getTypeCompActEcon()!=0)
			{
				FileType fileType=fileTypeService.getFileTypeById(new FileTypePK(getTypeCompActEcon(),sesion.getCompany_id()));
				String nameFile=fileType.getAbreviation();
				String category=fileType.getFileCategory().getAbreviation();
				
				Utilities.createDirectory(realPath + pathDocument+category);
				Utilities.createDirectory(realPath + pathHistoric+category);
				
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				
				nameFile+="_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
				// si todo bien a guardar
				try 
				{
					if(saveFileByType(realPath + pathDocument+category+"/"+nameFile,getTypeCompActEcon(),pathDocument+category+"/"+nameFile,event.getFile().getInputstream())&& Utilities.copyFile(realPath + pathHistoric+category+"/"+nameFile,event.getFile().getInputstream())){
						// se guardo correctamente.						
						listDocumentAdded();						
						setThisDocumentId("checkCompActEcon");
					}
					else{
						setThisDocumentId(null);
					}
						//No se pudo guardar el archivo.
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
					setThisDocumentId(null);
				}
				
			}
			else if(typeID.equals("file_compIncome") && getTypeCompIncome()!=0){
				FileType fileType=fileTypeService.getFileTypeById(new FileTypePK(getTypeCompIncome(),sesion.getCompany_id()));
				String nameFile=fileType.getAbreviation();
				String category=fileType.getFileCategory().getAbreviation();
				
				Utilities.createDirectory(realPath + pathDocument+category);				
				Utilities.createDirectory(realPath + pathHistoric+category);
				
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				
				nameFile+="_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
				// si todo bien a guardar
				try {
					if(saveFileByType(realPath + pathDocument+category+"/"+nameFile,getTypeCompIncome(),pathDocument+category+"/"+nameFile,event.getFile().getInputstream())&& Utilities.copyFile(realPath + pathHistoric+category+"/"+nameFile,event.getFile().getInputstream())){
						// se guardo correctamente.
						listDocumentAdded();						
						setThisDocumentId("checkTypeLoan");
					}
					else{
						setThisDocumentId(null);
				}
						//No se pudo guardar el archivo.
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
					setThisDocumentId(null);
				}
			}
			else if(typeID.equals("file_acredProBus") && getTypeAcredProBusiness()!=0 ){
				FileType fileType=fileTypeService.getFileTypeById(new FileTypePK(getTypeAcredProBusiness(),sesion.getCompany_id()));
				String nameFile=fileType.getAbreviation();
				String category=fileType.getFileCategory().getAbreviation();
				
				Utilities.createDirectory(realPath + pathDocument+category);
				Utilities.createDirectory(realPath + pathHistoric+category);
				
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				
				nameFile+="_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id()+"_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
				// si todo bien a guardar
				try {
					if(saveFileByType(realPath + pathDocument+category+"/"+nameFile,getTypeAcredProBusiness(),pathDocument+category+"/"+nameFile,event.getFile().getInputstream()) && Utilities.copyFile(realPath + pathHistoric+category+"/"+nameFile,event.getFile().getInputstream())){
						// se guardo correctamente.
						listDocumentAdded();
						setThisDocumentId("checkAcredProBus");
					}
					else{
						setThisDocumentId(null);
					}
						//No se pudo guardar el archivo.
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
					setThisDocumentId(null);
				}
			}
			else if(typeID.equals("file_compDomi") && getTypeCompDomi()!=0){
				FileType fileType=fileTypeService.getFileTypeById(new FileTypePK(getTypeCompDomi(),sesion.getCompany_id()));
				String nameFile=fileType.getAbreviation();
				String category=fileType.getFileCategory().getAbreviation();
				
				Utilities.createDirectory(realPath + pathDocument+category);				
				Utilities.createDirectory(realPath + pathHistoric+category);
				
				if(thisProyectLoan!=null){
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
					//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile+"_"+thisProyectLoan.getProyectloanPk().getProyect_loan_id());
				}else if(sesion.getArea().toString().equals("I")){
					Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument+category),nameFile);
					//Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric+category),nameFile);
				}
				
				if(thisProyectLoan!=null){
					nameFile+="_"+sesion.getProspectus_id()+"_"+ (getThisProyect()!=null?getThisProyect().getProyectoPk().getProyect_id():"")+"_"+Utilities.getRandomName()+formatFile;
				}else if(sesion.getArea().toString().equals("I")){
					nameFile+="_"+sesion.getProspectus_id()+"_"+Utilities.getRandomName()+formatFile;
				}
				// si todo bien a guardar
				try {
					if(saveFileByType(realPath + pathDocument+category+"/"+nameFile,getTypeCompDomi(),pathDocument+category+"/"+nameFile,event.getFile().getInputstream()) && Utilities.copyFile(realPath + pathHistoric+category+"/"+nameFile,event.getFile().getInputstream())){
						// se guardo correctamente.
						listDocumentAdded();
						setThisDocumentId("checkCompDomi");
					}
					else{
						setThisDocumentId(null);
					}
						//No se pudo guardar el archivo.
				} catch (Exception e) {
					log.info("Error al guardar el archivo");
					setThisDocumentId(null);
				}
			}
		}
		
		
		if(component_id.equals("file_compIncome"))
		{
			//requestContext.addPartialUpdateTarget("listloan");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("listloan");
			
		}else if(component_id.equals("file_compDomi")){
			
			requestContext.addPartialUpdateTarget("listacompDomi");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("listacompDomi");
			
		}else if(component_id.equals("file_compActEcom")){
			
			requestContext.addPartialUpdateTarget("listCompActEcon");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("listCompActEcon");
			
		}else if(component_id.equals("file_acredProBus")){
			
			requestContext.addPartialUpdateTarget("listacredProBus");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("listacredProBus");
		}

	}
	
	public void deleteFile(){
		
		try 
		 {
		
				boolean flagSelfie = false;;
			
				String value = getDeleteString();
				
				System.out.println("deleteFile: "+getDeleteString());
				
				String[] valueArray = value.split("::");
				
				String location = valueArray[ (valueArray.length-1) ];
				
				String[] loca = location.split("/");
				
				String nameFile = loca[ loca.length - 1 ];
				
				String pathDocument = location.substring(0, location.indexOf( nameFile));
				
				Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument),nameFile);
				
				FilesPK filesPk = new FilesPK();
				
				filesPk.setCompany_id(		Integer.parseInt( valueArray[0] ));
				filesPk.setFile_id(			Integer.parseInt( valueArray[1] ));
				filesPk.setFile_type_id(	Integer.parseInt( valueArray[2] ));
				filesPk.setProspectus_id(	Integer.parseInt( valueArray[3] ));
				filesPk.setProyect_loan_id(	Integer.parseInt( valueArray[4] ));
				
				if( filesPk.getFile_type_id() == 119 ){
					flagSelfie = true;
				}
				
				filesService.removeFile(filesPk);
				
				init_employment_business();
				init_lista_documentos();		
				init_lista_credenciales();
				
				if( flagSelfie ){
					selfieIdentification = null;
					dispSelfieIdentificacion = "none";
				}
				
				System.out.println("deleteFile: OK");
				
		 }catch(Exception e ){
			 e.printStackTrace();
		 }
		
	}
	
	public boolean saveFileByType(String fileName,int typeFile,String path,InputStream in)
	{
		 try 
		 {
			 String metadata;			 
			 String formatFile = null;	
			 
			 try
			 {
				formatFile = fileName.substring(fileName.lastIndexOf(".") + 1);	
				
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
             
             if(thisProyectLoan != null)
             {
            	 thisFiles = filesService.getFileByTypeID(sesion.getProspectus_id(), sesion.getCompany_id(), thisProyectLoan.getProyectloanPk().getProyect_loan_id(), typeFile);
            	 
             }else if(sesion.getArea().toString().equals("I")){
            	 
            	 thisFiles = filesService.getFileByTypeID(sesion.getProspectus_id(), sesion.getCompany_id(), 1 , typeFile);
             }
             
             
             if(thisFiles==null)
             {					
            	 setThisFiles(new Files());
            	 FilesPK filePk =  null;
            	 if( thisProyectLoan != null )
            	 {
            		 filePk = new FilesPK(sesion.getProspectus_id(), sesion.getCompany_id(), thisProyectLoan.getProyectloanPk().getProyect_loan_id(), typeFile);
             	 }else if(sesion.getArea().toString().equals("I")){
             		 filePk = new FilesPK(sesion.getProspectus_id(), sesion.getCompany_id(), 1, typeFile);
            	 }
            	 getThisFiles().setFilesPk(filePk);
            	 getThisFiles().setUpload_date(new Date());
            	 getThisFiles().getFilesPk().setFile_type_id(typeFile);
            	 getThisFiles().setLocation(path);
            	 getThisFiles().setApproved("1");
            	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
            		 metadata=Utilities.getMetadataPDF(new File(fileName));
            	 else            		 
            		 metadata=ImageUtils.getMetadata(new File(fileName));
            	 
				 if(metadata!=null)
					 getThisFiles().setMetadata(metadata);
				 else
					 getThisFiles().setMetadata(null);
					
            	 filesService.addFile(getThisFiles(), sesion.getProspectus_id(), sesion.getCompany_id());
             }
             else{            	 
            	 getThisFiles().getFilesPk().setFile_type_id(typeFile);
            	 getThisFiles().setLocation(path);
            	 getThisFiles().setUpload_date(new Date());
            	 if(formatFile.equals("pdf") || formatFile.equals("PDF"))
            		 metadata=Utilities.getMetadataPDF(new File(fileName));
            	 else            		 
            		 metadata=ImageUtils.getMetadata(new File(fileName));
            	 
				 if(metadata!=null)
					 getThisFiles().setMetadata(metadata);
				 else
					 getThisFiles().setMetadata(null);
				 
            	 filesService.updateFile(getThisFiles());
             }
             log.info("!!!!!!!!!!!!!!!!!     SE guardo y CREO EL ARCHIVO  CORRECTAMENTE     !!!!!!!!!!!!!!!!");
             return true;
            
             } catch (IOException e) {
            	 log.info("!!!!!!!!!! UUUUPS ERROOOOOOR AL SUBIR EL ARCHIVO  "+e.getMessage());
            	 return false;
             }
		
	}
	
	public void validaMontoMax(String type){
		
		CapitalNeto capitalneto = capitalnetoservice.getMaxCapitalNeto();
		
		boolean flag_mdep = false;
		boolean flag_mbal = false;
		
		boolean flag_mndep=false;
		boolean flag_mnbal=false;
		
		Double montoMinInv = 100D;
		
		if(capitalneto != null){
			
			if ( prevencionld.getMaximum_deposit()!= null && capitalneto.getMx_capital_neto() <= prevencionld.getMaximum_deposit() ){
				prevencionld.setMaximum_deposit( null);
				flag_mdep=true;
			}
			if ( prevencionld.getMaximum_balance() != null && capitalneto.getMx_capital_neto() <= prevencionld.getMaximum_balance() ){
				prevencionld.setMaximum_balance( null );
				flag_mbal=true;
			}
			
		}
		
		if ( prevencionld.getMaximum_deposit()!= null && montoMinInv >= prevencionld.getMaximum_deposit() ){
			prevencionld.setMaximum_deposit( null);
			flag_mndep=true;
		}
		if ( prevencionld.getMaximum_balance() != null && montoMinInv >= prevencionld.getMaximum_balance() ){
			prevencionld.setMaximum_balance( null );
			flag_mnbal=true;
		}
		
		savePrevencionLD( type );
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if(flag_mdep){
			requestContext.addCallbackParam("flagMDep", true);
		}
		if(flag_mbal){
			requestContext.addCallbackParam("flagMBal", true);
		}
		if(flag_mndep){
			requestContext.addCallbackParam("flagMINDep", true);
		}
		if(flag_mnbal){
			requestContext.addCallbackParam("flagMINBal", true);
		}
		
		requestContext.addCallbackParam("MONTOMAX", dec.format( capitalneto.getMx_capital_neto() ));
		
		requestContext.addCallbackParam("MONTOMIN", dec.format( montoMinInv ));
	}
	
	public void savePrevencionLD(String type){
		try{
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
			  String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
		        if(ipAddress == null)  
		        {  
		          ipAddress = httpServletRequest.getRemoteAddr();  
		        }
			Change_controlPK changeCtrlPK=new Change_controlPK();
			changeCtrlPK.setProspectus_id(sesion.getProspectus_id());
			changeCtrlPK.setCompany_id(sesion.getCompany_id());
			
			Change_control changeCtrl=new Change_control();
			changeCtrl.setChange_controlPK(changeCtrlPK);
			changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
			changeCtrl.setAfected_table("gn_mx_pld");
			changeCtrl.setIp(ipAddress);
			
			if(type.equals("movement_frequency") && !getPrevencionld().getMovement_frequency().equals(getPrevMovement_frequency())){
				changeCtrl.setOriginal_value(getPrevMovement_frequency());
				changeCtrl.setNew_value(prevencionld.getMovement_frequency());
				changeCtrl.setField("movement_frequency");
				changeCtrl.setComments("El usuario modificó la frecuencia con la que realizará movimientos, en su cuestionario de PLD");			
				setPrevMovement_frequency(prevencionld.getMovement_frequency());
				if(changeControlService.addChangeControl(changeCtrl, sesion.getProspectus_id(),sesion.getCompany_id())){
					savePrevencionLD();
				}else{
					log.info("Error al guardar en la tabla change control");
				}
			}else if(getPrevencionld() !=null && getPrevencionld().getMaximum_deposit() != null &&
						type.equals("maximum_depositStr") && 
						!getPrevencionld().getMaximum_deposit().equals(
																	getPrevMaximum_depositStr()
																	)
					){
				changeCtrl.setOriginal_value(getPrevMaximum_depositStr());
				changeCtrl.setNew_value(prevencionld.getMaximum_deposit().toString());
				changeCtrl.setField("maximum_deposit");
				changeCtrl.setComments("El usuario modificó su monto máximo por cada depósito, en su cuestionario de PLD");	
				setPrevMaximum_depositStr(prevencionld.getMaximum_deposit().toString());
				if(changeControlService.addChangeControl(changeCtrl, sesion.getProspectus_id(),sesion.getCompany_id())){
					savePrevencionLD();
				}else{
					log.info("Error al guardar en la tabla change control");
				}
				
			}else if( getPrevencionld() !=null && getPrevencionld().getMaximum_withdraw() != null && type.equals("maximum_withdrawStr") && !getPrevencionld().getMaximum_withdraw().equals(getPrevMaximum_withdrawStr())){
				changeCtrl.setOriginal_value(getPrevMaximum_withdrawStr());
				changeCtrl.setNew_value(prevencionld.getMaximum_withdraw().toString());
				changeCtrl.setField("maximum_withdraw");
				changeCtrl.setComments("El usuario modificó su monto máximo por cada retiro, en su cuestionario de PLD");	
				setPrevMaximum_withdrawStr(prevencionld.getMaximum_withdraw().toString());
				if(changeControlService.addChangeControl(changeCtrl, sesion.getProspectus_id(),sesion.getCompany_id())){
					savePrevencionLD();
				}else{
					log.info("Error al guardar en la tabla change control");
				}
			}else if( getPrevencionld() !=null && getPrevencionld().getMaximum_balance() != null && type.equals("maximum_balanceStr") && !getPrevencionld().getMaximum_balance().equals(getPrevMaximum_balanceStr())){
				changeCtrl.setOriginal_value(getPrevMaximum_balanceStr());
				changeCtrl.setNew_value(prevencionld.getMaximum_balance().toString());
				changeCtrl.setField("maximum_balance");
				changeCtrl.setComments("El usuario modificó su saldo máximo de captación, en su cuestionario de PLD");	
				setPrevMaximum_balanceStr(prevencionld.getMaximum_balance().toString());
				if(changeControlService.addChangeControl(changeCtrl, sesion.getProspectus_id(),sesion.getCompany_id())){
					savePrevencionLD();
				}else{
					log.info("Error al guardar en la tabla change control");
				}
			}
		
		}catch(Exception e){
			System.out.println("SavePLD");
		}
	}
	
	public void savePrevencionLD(){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ----  GUARDANDO PrevencionLD  ----  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if(isHasPLD()){
			prevencionldservice.updatePrevencionLD(getPrevencionld());
			setHasPLD(true);
		}else{
			prevencionldservice.savePrevencionLD(getPrevencionld());
			setHasPLD(true);
		}
	}
	
	public String getDisplayKuboRelative() 
	{	
		if(getPrevencionld().getHas_kubo_relative() != null && getPrevencionld().getHas_kubo_relative().equals("S"))
		{
			displayKuboRelative = "block";
		} else {
			displayKuboRelative = "none";
		}
		
		return displayKuboRelative;
	}

	public String getDisplayPepRelative() 
	{	
		if(getPrevencionld().getHas_pep_relative()!=null && getPrevencionld().getHas_pep_relative().equals("S"))
			displayPepRelative="block";
		else
			displayPepRelative="none";
		
		return displayPepRelative;
	}
		
	public String getMaximum_balanceStr() {
		maximum_balanceStr=getPrevencionld().getMaximum_balance()!=null ? num.format(getPrevencionld().getMaximum_balance()):null;
		return maximum_balanceStr;
	}
	
	public void setMaximum_balanceStr(String maximum_balanceStr) {
		this.maximum_balanceStr = maximum_balanceStr;
		getPrevencionld().setMaximum_balance(Double.parseDouble(maximum_balanceStr.replaceAll(",", "")));
		validaMontoMax("maximum_balanceStr");
	}
	
	public String getMaximum_withdrawStr() {	
		maximum_withdrawStr=getPrevencionld().getMaximum_withdraw()!=null?num.format(getPrevencionld().getMaximum_withdraw()):null;
		return maximum_withdrawStr;
	}
	
	public void setMaximum_withdrawStr(String maximum_withdrawStr) {		
		this.maximum_withdrawStr = maximum_withdrawStr;
		getPrevencionld().setMaximum_withdraw(Double.parseDouble(maximum_withdrawStr.replaceAll(",", "")));
	}
	
	public String getMaximum_depositStr() {		
		maximum_depositStr=getPrevencionld().getMaximum_deposit()!=null?num.format(getPrevencionld().getMaximum_deposit()):null;
		return maximum_depositStr;
	}
	
	public void setMaximum_depositStr(String maximum_depositStr) {
		this.maximum_depositStr = maximum_depositStr;
		getPrevencionld().setMaximum_deposit(Double.parseDouble(maximum_depositStr.replaceAll(",", "")));
		validaMontoMax("maximum_depositStr");
	}
	
	/*
	 * 
	 * V A L I D A   S E G M E N T O   D E   P U B L I C A C I O N
	 *  
	 */
	
	protected final int SEGMENT_TYPE_PUBLISH = 2;
	
	protected boolean canCheckScore = false;
	
	protected boolean enabledSegment = false;
	
	protected Scoring score;
	
	protected Screen screen;
	
	protected SegmentProspectus segment;
	
	protected boolean flagRepeat = false;
	
	protected String pageCaramelo = "popCaramelo/comodin";
	
	protected boolean isvalid = false;
	
	private final int PUBLICACION_PRIORITARIO = 4;
	private final int PUBLICACION_BUENO = 5;
	private final int PUBLICACION_GRIS = 6;
	private final int SIN_HISTORIAL = 7;
	
	protected List< SegmentProspectus > segmentList;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringservice;
	
	@ManagedProperty("#{segmentProspectusServiceImp}")
	protected SegmentProspectusService   segmentprospectusservice;
	
	@ManagedProperty("#{segmentActionServiceImp}")
	protected SegmentActionService segmentactionservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	
	public void checaSegmentoPublicacion(){
		
		System.out.println("  -------------    AddPldDocument.checaSegmentoPublicacion   ------------- ");
		
		if( thisProyectLoan != null && thisProyectLoan.getStatus_id() == 0 ){
		
				checkEnabledScore();
				
				if(score != null){
					
					checkEnabledSegment();
					
					if( enabledSegment ){
					
						if(canCheckScore){
							
							flagRepeat = false;
							
							screen = null;
							
							SegmentAction segment_action =  generateSegmentActionScreen();
							
							if( screen != null ){
								pageCaramelo = screen.getName() ;
								isvalid = segment_action.getEnabled() == 1;
								System.out.println("  -------------    Segmento: "+ segment.getSegment().getName() +"   ------------- ");
							}else{
								pageCaramelo = "popCaramelo/comodin";
								isvalid = false;
							}
							
							
							
						}else{
							
							flagRepeat = true;
							pageCaramelo = "popCaramelo/comodin";
							isvalid = false;
						
						}
					
					}else{
						
						flagRepeat = false;
						pageCaramelo = "popCaramelo/comodin";
						isvalid = false;
						
					}
					
				}else{
					
					flagRepeat = false;
					pageCaramelo = "popCaramelo/comodin";
					isvalid = false;
					
				}
				
				
				RequestContext request   = RequestContext.getCurrentInstance();
				request.addCallbackParam("flagRepeat", flagRepeat);
				request.addCallbackParam("pageSend", pageCaramelo);
				request.addCallbackParam("isvalid", isvalid);
				
		}else{
			
			RequestContext request   = RequestContext.getCurrentInstance();
			request.addCallbackParam("flagRepeat", flagRepeat);
			request.addCallbackParam("pageSend", pageCaramelo);
			request.addCallbackParam("isvalid", false);
			
		}
		
	}
	
	private void checkEnabledScore(){
		
		if( sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
		
			score = scoringservice.loadMaxScoringByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if( score != null && score.getRisk_processed() != null && score.getRisk_processed() == 1  ){
				
				canCheckScore = true;
				
			}else{
				canCheckScore = false;
			}
		
		}else{
			
			canCheckScore = false;
			score = null;
		}
		
	}
	
	
	private void checkEnabledSegment(){
		
		if( sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
			
			segmentList = segmentprospectusservice.loadSegmentProspectListByType(sesion.getProspectus_id(), sesion.getCompany_id(), SEGMENT_TYPE_PUBLISH );
			
			generaSegmento();
			
			if( segmentList != null && segmentList.size() > 0 ){
				//nos traemos el primer segmento, ya que para este procedimiento solo se trae un registro por segment_type = 2 ( PUBLICACION )
				
				segment = segmentList.get(0);
				
				if(segment.getSegment().getEnabled() == 1){
					
					enabledSegment = true;
					
				}else{
					enabledSegment = false;
				}
				
				sesion.setSegment_name( segment.getSegment().getName() );
				sesion.setKubo_score( score.getKubo_score_a() + score.getKubo_score_b() );
				
				
			}else{
				enabledSegment = true;
			}
		
		}else{
			enabledSegment = false;
		}
		
	}
	
	private SegmentAction generateSegmentActionScreen(){
		
		if( segment != null ){
		
			SegmentAction sa = segmentactionservice.getSegmentActionBySegment( segment.getPk().getSegment_id(), sesion.getCompany_id(), 1 );
			
			if ( sa != null ) {
				
				ScreenPK spk = new  ScreenPK();
				
				spk.setCompany_id(sesion.getCompany_id());
				spk.setScreen_id(Integer.parseInt(sa.getSegment_action()));
				
				screen = screenservice.getScreenById(spk);
				
			}
			
			
			return sa;
		
		}else{
			return null;
		}
		
	}

	
	private void generaSegmento(){
		
		Double prob =  getProbabilidadPago();
		Double liq = getIndiceLiquidez();
		int segment_id; 
		
		if( prob != null && prob.intValue() != 3.0 ){
		
			if(  prob.intValue() == 2 ){
				
				segment_id = SIN_HISTORIAL;
				
			}else if( prob <= 1.000000D &&  prob >= .980000D &&  liq >= 1.75 ){
				
				segment_id = PUBLICACION_PRIORITARIO;
				
			}else if ( prob < .98D &&  prob >= .95D &&  liq >= 1.75 ){
				
				segment_id = PUBLICACION_BUENO;
				
			}else {
				
				segment_id = PUBLICACION_GRIS;
				
			}
			
			SegmentProspectus segment1 = new SegmentProspectus();
			
			if( segmentList == null || segmentList.size() == 0 ){
				
				segment1.setAssign_date(new Date());
				
				SegmentProspectusPK spk = new SegmentProspectusPK() ;
				
				spk.setCompany_id(sesion.getCompany_id());
				spk.setProspectus_id(sesion.getProspectus_id());
				spk.setSegment_id(segment_id);
				spk.setSegment_type_id(SEGMENT_TYPE_PUBLISH);
				
				segment1.setPk(spk);
				
				segmentprospectusservice.saveSegmentProspectus(segment1);
				
				if( segmentList == null ){
					segmentList = new ArrayList<SegmentProspectus>();
				}
				
	//			segmentList.add(segment1);
				
			}else{
				
				segment1 = segmentList.get(0);
				
				if( segment1.getPk().getSegment_id() != segment_id ){
					
					segment1.getPk().setSegment_id( segment_id );
					segmentprospectusservice.saveSegmentProspectus(segment1);
					
				}
				
	//			segmentList.clear();
	//			segmentList.add(segment1);
				
			}
			
			segmentList.clear();
			segmentList = segmentprospectusservice.loadSegmentProspectListByType(sesion.getProspectus_id(), sesion.getCompany_id(), SEGMENT_TYPE_PUBLISH );
			
		}
		
	}
	
	private Double  getProbabilidadPago(){
		
		String r =   thisProyectLoan.getR_data();
		String p = "";
		
		if( r != null ){
			
			String[] output = r.split(" ");
			
			if( output.length > 1 ){
				p = output[1];
			}
		}
		
		if( p != null && !p.equals("") ){
		
			if( p.equals("SIN_HISTORIA") || p.equals("SIK_HISTORIA") ){
				
				return Double.parseDouble("2");
				
			}else if( p.equals("SIN_PROYECTO") || p.equals("SIK_PROYECTO") ){
				
				return Double.parseDouble("3");
				
			}else if( p.equals("ERROR") ){
				
				return null;
				
			}else if( p.equals("ERROR_EVALUACION_R") ){
				
				return null;
				
			}else{
				
				try{
				
					return Double.parseDouble(p);
				
				}catch(Exception e){
					return null;
				}
			
			}
			
		}else{
			return null;
		}
	}
	
	private Double  getIndiceLiquidez(){
		
		Double ingresos = calculaTotalIncome();
		
		Double gastos = calculaTotalExpenses();
		
		Double cuota = thisProyectLoan.getPayment();
		
		
		
		return (ingresos - gastos) / cuota;
	
	}
	
	
	public boolean isEnabledSegment() {
		return enabledSegment;
	}

	public void setEnabledSegment(boolean enabledSegment) {
		this.enabledSegment = enabledSegment;
	}

	public ScoringService getScoringservice() {
		return scoringservice;
	}

	public void setScoringservice(ScoringService scoringservice) {
		this.scoringservice = scoringservice;
	}

	public SegmentProspectusService getSegmentprospectusservice() {
		return segmentprospectusservice;
	}

	public void setSegmentprospectusservice(SegmentProspectusService segmentprospectusservice) {
		this.segmentprospectusservice = segmentprospectusservice;
	}

	public SegmentActionService getSegmentactionservice() {
		return segmentactionservice;
	}

	public void setSegmentactionservice(SegmentActionService segmentactionservice) {
		this.segmentactionservice = segmentactionservice;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public ScreenService getScreenservice() {
		return screenservice;
	}

	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}
	
	
	private Double calculaTotalIncome()
	{
		
		Double totalIncome = 0.0;
		List<Income>listIncome = new ArrayList<Income>() ; 
		
		if( thisProyectLoan !=null )
		{
			
			List<IncomeType> listIncomeType=service_income_type.getIncomeTypeListOrderByConsec();
			listIncome = ingresosService.getListIncomeByProspect(thisProyectLoan.getProyectloanPk().getProspectus_id(), thisProyectLoan.getProyectloanPk().getCompany_id());
				
			for(IncomeType itertype: listIncomeType)
			{
				
				for(Income registro3: listIncome)
				{
					if(registro3.getIncome_type_id().equals(itertype.getPk().getIncome_type_id()))
					{
							
						if(itertype.getObs_income_type().equals("IE")){
							totalIncome += (itertype.getOperation_sign() * registro3.getAmmount());
						}
						
					}
				}
				
			}
				
		}
		
		return totalIncome;
		
	}
	
	
	private Double calculaTotalExpenses()
	{		
		
		boolean flagTotalExpenses = false;
		Double totalExpenseTemp = 0D;
		
		Double totalExpenses = 0.0;
		
		List <Expenses> 	   listExpenses;
		
		if(thisProyectLoan != null)
		{		
			totalExpenses = 0.0;
			
			listExpenses = egresosService.getListExpensesByProspect(thisProyectLoan.getProyectloanPk().getProspectus_id() , thisProyectLoan.getProyectloanPk().getCompany_id()) ;
			
			for(Expenses registro4: listExpenses)
			{
				if( registro4.getExpense_type_id() != 14 ){ //Gastos Totales
					
					if(registro4.getAmmount()!=null)
					{
						totalExpenses += registro4.getAmmount();
					}
					
					
				}else{
					
					flagTotalExpenses = true;
					totalExpenseTemp = registro4.getAmmount();
					
				}
					
			}
			
			if( flagTotalExpenses ){
				
				totalExpenses = totalExpenseTemp;
				
			}
		}
		
		return totalExpenses;
		
	}

	public String getPageCaramelo() {
		return pageCaramelo;
	}

	public void setPageCaramelo(String pageCaramelo) {
		this.pageCaramelo = pageCaramelo;
	}
	
	
	/*
	 * 
	 *  F I N   V A L I D A   S E G M E N T O   D E   P U B L I C A C I O N
	 *  
	 */
	
	
	
	private boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=AddPldDocument");
							
			try 
			{
				System.out.println( "Redirigiendo desde AddPldDocument: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		return request.getContextPath();
	}
	
	public void changeSameAddress(AjaxBehaviorEvent e){
		
//		String val = (String) ((HtmlSelectOneRadio) e.getComponent()).getValue();
//		
//		System.out.println( "SameAddress: " + sameAddress + " val: " + val );
		
		sameAddress = "N";
		
		if( sesion.getArea().toString().equals("L") ){
			if(info != null){
			 
				info.setMx_ife_domicilio(sameAddress);
				proyectLoanInfoService.updateProyectLoanInfo(info);
				
			}
			
		 }else if( sesion.getArea().toString().equals("I") ){
			 
			 if( inv != null ){
			 
				 inv.setMx_ife_domicilio( sameAddress );
				 investorservice.updateInvestor(inv);
				 
			 }
			 
		 }
		
	}
	
	public void initValidityDate( AjaxBehaviorEvent e ){
		
		String id =		(String) 	((HtmlSelectOneMenu) e.getComponent()).getId();
		String val = 	(String) 	((HtmlSelectOneMenu) e.getComponent()).getValue();
		
		System.out.println( "ID: " + id + " value: " + val);
		
		String d = "0";
		String m = "0";
		String y = "0";
		
		String fecha = "";
		
		if( !dayD.equals("0") && !monthD.equals("0") && !yearD.equals("0") ){
			
//			if( id.equals("dayD") ){
				
				if( Integer.parseInt( dayD ) < 10){
					d="0"+dayD;
				}else{
					d=dayD;
				}
				
				
//			}else if( id.equals("monthD") ){
				
				
				for (int i = 0; i < catalogo_meses_nombres.length; i++) 
				{
					if ((catalogo_meses_nombres[i]).equals(monthD)) 
					{
						
						int y1 = i+1;
						
						if ((y1 ) < 10)
						{
							m = "0" + (y1);
							
						} else {
							
							m = (y1) + "";
						}
						
						break;
					}
				}
				
				
//			}else if( id.equals("yearD") ){
//				
//			}
				
				y = yearD ;
			
			try{
			
			fecha = d+"/"+m+"/"+y;
			
			Date date = frm.parse(fecha);
			
			System.out.println( fecha + "   --   " + date);
			
			if( sesion.getArea().toString().equals("L") ){
			
			listFiles = filesService.getListFilesByProspect( thisProyectLoan.getProyectloanPk().getProspectus_id() , thisProyectLoan.getProyectloanPk().getCompany_id(), thisProyectLoan.getProyectloanPk().getProyect_loan_id());
			
			for( Files f : listFiles ){
				
				if( f.getFilesPk().getFile_type_id() >= 24 && f.getFilesPk().getFile_type_id()<=29 ){
					
					f.setValidity_date(date);
					
					filesService.updateFile(f);
					
					break;
					
				}
			}
					
			}else{
				
				listFiles = filesService.getListFilesByProspect( sesion.getProspectus_id() , sesion.getCompany_id(), 1);
				
				for( Files f : listFiles ){
					
					if( f.getFilesPk().getFile_type_id() >= 24 && f.getFilesPk().getFile_type_id()<=29 ){
						
						f.setValidity_date(date);
						
						filesService.updateFile(f);
						
						break;
						
					}
				}
				
			}
			
			
			}catch(Exception e1){
				
				e1.printStackTrace();
				
			}
			
			
		}
		
		System.out.println( "Fecha: " + d + "/" + m + "/" + y );
		
	}
	
	public void checaSGBHelp(){
		
		RequestContext request   = RequestContext.getCurrentInstance();
		
		if( sesion.getArea().toString().equals("L") && sesion.getCoachProspectus_id() == null && membership.getRegistration_reason_id() != null && membership.getRegistration_reason_id().intValue() != 6 && thisProyectLoan.getStatus_id().intValue() == 0 ){
		
			System.out.println( "checaSGBHelp" );
			
			List<InfoNotification>  lstNoti = infonotificationservice.getInfoNotificationBy( thisProyectLoan.getProyectloanPk().getCompany_id(), thisProyectLoan.getProyectloanPk().getProspectus_id(), thisProyectLoan.getProyectloanPk().getProyect_loan_id() , HELP_NOTIFICATION_SCREEN );
			
			if( lstNoti == null || lstNoti.size() == 0 ){
				
				InfoScreenPK scpk = new InfoScreenPK();
				
				scpk.setCompany_id(sesion.getCompany_id());
				scpk.setInfo_screen_id(HELP_NOTIFICATION_SCREEN);
				
				InfoScreen scrNot = infoscreenservice.getInfoScreenById(scpk);
				
				if( scrNot != null && scrNot.getIs_enabled() != null && scrNot.getIs_enabled().equals("1") ){
				
					Scoring score =  scoreservice.loadMaxScoringByProspectus(prospectus_id, company_id);
				
					if( score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == 1 ){
						
						if( isKuboScoreValid( score ) ){
							
							
							
							if( !esDiaFeriado() ){
							
								if( validaHora() ){
									
									request.addCallbackParam("isHabil", true);
									
								}else{
									
									request.addCallbackParam("isHabil", false);
									
								}
								
							}else{
								
								
								
								request.addCallbackParam("isHabil", false);
								
							}
							
							request.addCallbackParam("isValid", true);
							
						}
						
					}else{
						
						request.addCallbackParam("isHabil", false);
						request.addCallbackParam("isValid", false);
					}
					
				}else{
					
					request.addCallbackParam("isHabil", false);
					request.addCallbackParam("isValid", false);
				}
				
			}else{
				
				request.addCallbackParam("isHabil", false);
				request.addCallbackParam("isValid", false);
				
			}
			
		}else{
			
			request.addCallbackParam("isHabil", false);
			request.addCallbackParam("isValid", false);
			
		}
		
	}
	 
	public void saveInfoNotification(){
		
		InfoNotification info = new InfoNotification();
		
		info.setCompany_id(company_id);
		info.setInfo_screen_id(HELP_NOTIFICATION_SCREEN);
		info.setProspectus_id(prospectus_id);
		info.setProyect_loan_id(proyect_loan_id);
		info.setViewed_date(new Date());
		
		infonotificationservice.saveInfoNotificationBy(info);
		
	}

	private boolean isKuboScoreValid( Scoring score ){
		
		if( score.getKubo_score_a().equals("F") || score.getKubo_score_a().equals("G") || score.getKubo_score_a().equals("K")  ){
		
			return false;
		
		}else if( score.getKubo_score_a().equals("E") && score.getKubo_score_b().equals("5") ){
			
			return false;
			
		}else{
			
			return true;
			
		}
		
	}
	
	private boolean esDiaFeriado(){
		
		boolean flag = false;
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		
		int dayOfWeek = getDayOfTheWeek( c.getTime() );
		
		if( dayOfWeek == Calendar.SATURDAY ){
			
			flag = false;
			
		}else if( dayOfWeek == Calendar.SUNDAY ){
		
			flag = false;
			
		}else if( service.esDiaFeriado( c.getTime() ) ){
			
			flag = false;
			
			
		}else{
			flag = true;
		}
		
		return !flag;
		
	}
	
	private int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);		
	}
	
	private boolean validaHora(){
		
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("HH:mm", new Locale("es","MX"));
		Date today = new Date();
		String result = formatter.format(today);
		
		String horaActual = result.split(":")[0];
		String minutosActual = result.split(":")[1];
		
		String horaInicio = horaInicioSys.split(":")[0];
		String minutosInicio = horaInicioSys.split(":")[1];
		
		String horaTermino = horaTerminoSys.split(":")[0];
		String minutosTermino = horaTerminoSys.split(":")[1];
		
		if( 
			Integer.parseInt( horaActual ) < Integer.parseInt( horaInicio ) ||
			Integer.parseInt( horaActual ) > Integer.parseInt( horaTermino )
				
		){
			return false;
		}
		
		else if( 
				Integer.parseInt( horaActual ) == Integer.parseInt( horaInicio ) &&
				Integer.parseInt( minutosActual ) >= Integer.parseInt( minutosInicio )
					
			){
				return true;
			}
		
		else if( 
				Integer.parseInt( horaActual ) == Integer.parseInt( horaTermino ) &&
				Integer.parseInt( minutosActual ) <= Integer.parseInt( minutosTermino )
					
			){
				return true;
			}
		
		else if( 
				Integer.parseInt( horaActual ) == Integer.parseInt( horaInicio ) &&
				Integer.parseInt( minutosActual ) < Integer.parseInt( minutosInicio )
					
			){
				return false;
			}
		
		else if( 
				Integer.parseInt( horaActual ) == Integer.parseInt( horaTermino ) &&
				Integer.parseInt( minutosActual ) > Integer.parseInt( minutosTermino )
					
			){
				return false;
			}
		
		else if( 
				Integer.parseInt( horaActual ) >= Integer.parseInt( horaInicio ) &&
				Integer.parseInt( horaActual ) <= Integer.parseInt( horaTermino )
					
			){
				return true;
			}
		
		else{
			return false;
		}
		
	}
	
	public void needSGBHelp(){
		
		notificaSGBHELP();
		
		InfoNotification info = new InfoNotification();
		
		info.setCompany_id(company_id);
		info.setInfo_screen_id(HELP_SEND_NOTIFICATION);
		info.setProspectus_id(prospectus_id);
		info.setProyect_loan_id(proyect_loan_id);
		info.setViewed_date(new Date());
		
		infonotificationservice.saveInfoNotificationBy(info);
		
		
		
		System.out.println( "needSGBHelp" );
	}
	public void noSGBHelp(){
		
		InfoNotification info = new InfoNotification();
		
		info.setCompany_id(company_id);
		info.setInfo_screen_id(NO_HELP_NOTIFICATION);
		info.setProspectus_id(prospectus_id);
		info.setProyect_loan_id(proyect_loan_id);
		info.setViewed_date(new Date());
		
		infonotificationservice.saveInfoNotificationBy(info);
		
		System.out.println( "noSGBHelp" );
	}
	
	private void notificaSGBHELP(){
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(company_id);
		spk.setSystem_param_id(94);
		
		SystemParam sys = systemparamservice.loadSelectedSystemParam(spk); // URLHELPSGB
		
		String url_str = sys.getValue();
		
		String phones = getPhones();
		
		String json_str =
		"{		" +
		"	\"channelId\" : \"A0001\" 	,	" +   
		"	\"prospectusId\" :	"+prospectus_id+"  ,	" +
		"	\"projectLoanId\" : "+proyect_loan_id+"		," +
		"	\"fullName\"  : \""+membership.getPerson().NombreCompletoNPM()+"\"	,	" +
		"	\"email\"  :	 \""+membership.getEmail()+"\" ,	" +
		"	\"phoneNumber\" : \" " + phones + "  \",		" +
		"	\"branchId\" : \"S0001\","
		+ "	\"origin\": \"PORTALKUBO\"		" +
		"	}		" ;
		
		servicecallingService.registrar( 0, prospectus_id, company_id, "SOLICITA AYUDA SGB: " + json_str);
		
		SgbHelp sgb = new SgbHelp();
		
		sgb.setUrl_str(url_str);
		sgb.setInput(json_str);
		
		sgb.connect();
		
		if(sgb.getStatus() > 0){
		
			servicecallingService.registrar( 0, prospectus_id, company_id, " REGRESA SOLICITA AYUDA SGB: " + sgb.getResponse());
		
		}else{
			servicecallingService.registrar(3, prospectus_id, company_id, "ERROR SGB CONNECT ", sgb.getStatus() + " - " +  sgb.getResponse());
		}
		
		
	}
	
	private String getPhones(){
		
		String phones = "";
		
		List<Phone> lstCasa = service_telefono.getPhoneListByType(prospectus_id, company_id, 5);
		List<Phone> lstCel = service_telefono.getPhoneListByType(prospectus_id, company_id, 6);
		
		int i = 0;
		
		if( lstCasa != null && lstCasa.size() > 0 ){
			for( Phone phone : lstCasa ){
				if(i != 0){
					phones += " | ";
				}
				phones += " " + phone.getPhoneType().getName() + " : " + phone.getPhone_number() + " ";
				
			}
			
		}
		
		if( lstCel != null && lstCel.size() > 0 ){
			for( Phone phone : lstCel ){
				
				if(phones.length() > 0){
					phones += " | ";
				}
				
				phones += " " + phone.getPhoneType().getName() + " : " + phone.getPhone_number() + " ";
				
			}
			
		}
		
		return phones;
	}
	
	
	public void saveProspectusComment(){
		
		if( has_prospectuscomment ){
		
			if( prospectuscomment.getComment() != null && prospectuscomment.getComment().trim().length() > 0 ){
				
				prospectuscomment.setComment_date(new Date());
				pospectuscommentservice.updatePospectusComment(prospectuscomment);
				
			}else{
				
				pospectuscommentservice.removePospectusComment(prospectuscomment);
				
			}
		
		}else {
			pospectuscommentservice.savePospectusComment(prospectuscomment);
			has_prospectuscomment = true;
		}
		
	}
}
