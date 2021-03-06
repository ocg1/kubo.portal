package mx.com.kubo.managedbeans.registro.documentacion;

import static mx.com.kubo.files.CatalogoFileType.CREDENCIAL_ELECTOR_FRENTE;
import static mx.com.kubo.files.CatalogoFileType.CREDENCIAL_ELECTOR_REVERSO;
import static mx.com.kubo.files.CatalogoFileType.FM2_FRENTE;
import static mx.com.kubo.files.CatalogoFileType.FM3_REVERSO;
import static mx.com.kubo.files.CatalogoFileType.PASAPORTE;
import static mx.com.kubo.files.CatalogoFileType.SELFIE_IDENTIFICACION;
import static mx.com.kubo.files.CatalogoFileType.CARTILLA;
import static mx.com.kubo.files.CatalogoFileType.CEDULA;
import static mx.com.kubo.files.CatalogoFileType.LICENCIA_REVERSO;
import static mx.com.kubo.files.CatalogoFileType.LICENCIA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import mx.com.kubo.bean.FileForScreenBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.files.CatalogoFileType;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.FileCategory;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.PospectusComment;
import mx.com.kubo.model.PospectusCommentPK;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;
import mx.com.kubo.model.RiskTask;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMP;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

public abstract class AddPldDocumentAMO extends AddPldDocumentDMO  
{	
	protected void init_prevencion() 
	{
		prevPK = new PrevencionLDPK();		
		prevPK.setCompany_id(company_id);
		prevPK.setProspectus_id(prospectus_id);
		
		prevencionld = prevencionldservice.getSelectedPrevencionLDById(prevPK);

		if(prevencionld != null)
		{
			setHasPLD(true);
			
			setPrevMovement_frequency(prevencionld.getMovement_frequency());
			setPrevMaximum_depositStr(prevencionld.getMaximum_deposit()   == null  ? null  : prevencionld.getMaximum_deposit().toString());
			setPrevMaximum_withdrawStr(prevencionld.getMaximum_withdraw() == null  ? null : prevencionld.getMaximum_withdraw().toString());
			setPrevMaximum_balanceStr(prevencionld.getMaximum_balance()   == null  ? null  : prevencionld.getMaximum_balance().toString());
			
		} else {
			
			prevencionld = new PrevencionLD();
			prevencionld.setPk(prevPK);
			
			setHasPLD(false);
			
			setPrevMovement_frequency("NULL");
			setPrevMaximum_depositStr("NULL");
			setPrevMaximum_withdrawStr("NULL");
			setPrevMaximum_balanceStr("NULL");
		}
		
		if( !(sesion.getArea().toString().equals("I")))
		{
			if(prevencionld.getResource_origin() == null || prevencionld.getResource_origin().equals(""))
			{
				prevencionld.setResource_origin("Del préstamo de kubo.financiero");
			}
		}
		
		//System.out.println( " ---------- " + prevencionld.getResource_origin());				
	}
	
	protected void init_lista_foto_proyecto() 
	{
		imgLogos = null;
		htWH = null;
		
		origFilePath = "";
		formatFile = "";
		thumbFile = null;
		origFile = null;
		
		try 
		{
			if(thisProyect != null && thisProyect.getLogo() != null)
			{				
				listImgLogos = new ArrayList<ImagesBean>();
				
				imgLogos = new ImagesBean();
				
				htWH = ImageUtils.scaleImage(new File(realPath+thisProyect.getLogo()), 445, 305);
				
				imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
				imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
				imgLogos.setUrlImg(thisProyect.getLogo());
				
				
				origFilePath = (thisProyect.getLogo()).substring(0, (thisProyect.getLogo()).lastIndexOf("."));
				formatFile   = (thisProyect.getLogo()).substring((thisProyect.getLogo()).lastIndexOf("."));
				
				thumbFile = new File(realPath+origFilePath+"_thumb"+formatFile);
				origFile = new File(realPath+thisProyect.getLogo());
				
				//Si no existe el archivo thumb se crea
				if(!thumbFile.exists())
				{
					if(origFile.exists())
					{
						//Se crea el archivo temporal.
						Utilities.copyFile(thumbFile.getPath(), new FileInputStream(origFile.getPath()));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(thumbFile.getPath());
					}
				}
				
				imgLogos.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
				origFilePath = "";
				formatFile = "";
				thumbFile = null;
				origFile = null;
				
				
				imgLogos.setPathOriginal(realPath+thisProyect.getLogo());
				imgLogos.setTypeLogo(0);
				imgLogos.setSave(true);
				listImgLogos.add(imgLogos);
				
					if(thisProyect.getLogo2()!=null)
					{
						imgLogos = new ImagesBean();
						htWH = ImageUtils.scaleImage(new File(realPath + thisProyect.getLogo2()), 445, 305);
						imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
						imgLogos.setUrlImg(thisProyect.getLogo2());
						
						origFilePath = (thisProyect.getLogo2()).substring(0, (thisProyect.getLogo2()).lastIndexOf("."));
						formatFile = (thisProyect.getLogo2()).substring((thisProyect.getLogo2()).lastIndexOf("."));
						
						thumbFile = new File(realPath+origFilePath+"_thumb"+formatFile);
						origFile = new File(realPath+thisProyect.getLogo2());
						
						//Si no existe el archivo thumb se crea
						if(!thumbFile.exists())
						{
							if(origFile.exists())
							{
								//Se crea el archivo temporal.
								Utilities.copyFile(thumbFile.getPath(), new FileInputStream(origFile.getPath()));
								
								//Redimenciona y guarda img
								ImageUtils.resizeAndSaveImg(thumbFile.getPath());
							}
						}
						
						
						imgLogos.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
						origFilePath = "";
						formatFile = "";
						thumbFile = null;
						origFile = null;
						
						imgLogos.setPathOriginal(realPath+thisProyect.getLogo2());
						imgLogos.setTypeLogo(1);
						imgLogos.setSave(true);
						listImgLogos.add(imgLogos);
					}
						
					if(thisProyect.getLogo3() != null)
					{
							imgLogos=new ImagesBean();
							
							htWH = ImageUtils.scaleImage(new File(realPath+thisProyect.getLogo3()), 445, 305);
							
							imgLogos.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
							imgLogos.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
							imgLogos.setUrlImg(thisProyect.getLogo3());
							
							origFilePath = (thisProyect.getLogo3()).substring(0, (thisProyect.getLogo3()).lastIndexOf("."));
							formatFile = (thisProyect.getLogo3()).substring((thisProyect.getLogo3()).lastIndexOf("."));
							
							thumbFile = new File(realPath+origFilePath+"_thumb"+formatFile);
							origFile = new File(realPath+thisProyect.getLogo3());
							
							//Si no existe el archivo thumb se crea
							if(!thumbFile.exists())
							{
								if(origFile.exists())
								{
									//Se crea el archivo temporal.
									Utilities.copyFile(thumbFile.getPath(), new FileInputStream(origFile.getPath()));
									
									//Redimenciona y guarda img
									ImageUtils.resizeAndSaveImg(thumbFile.getPath());
								}
							}
							
							imgLogos.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
							origFilePath = "";
							formatFile = "";
							thumbFile = null;
							origFile = null;
							
							imgLogos.setPathOriginal(realPath+thisProyect.getLogo3());
							imgLogos.setTypeLogo(2);
							imgLogos.setSave(true);
							listImgLogos.add(imgLogos);
						}
						
				setDispCheckImgLog("inline");
				setOtherTitle(true);
				
			} else {
				
				listImgLogos = new ArrayList<ImagesBean>();
				
				imgLogos=new ImagesBean();
				imgLogos.setWidth(150);
				imgLogos.setHeight(150);
				imgLogos.setUrlImg("/img/sinimagen.jpg");
				imgLogos.setUrlImgThumb("/img/sinimagen.jpg");
				imgLogos.setPathOriginal(realPath+"/img/sinimagen.jpg");
				imgLogos.setTypeLogo(0);
				imgLogos.setSave(false);
				
				listImgLogos.add(imgLogos);
				setDispCheckImgLog("none");
				setOtherTitle(false);
			}
			
		} catch (Exception e) {
			
			setImgLogo("/img/sinimagen.jpg");
			//e.printStackTrace();
		}
	}
	
	protected void init_pospectus_comment(){
		
		List<PospectusComment> lstPC = null;
		
		if( naturalperson != null && naturalperson.getProspectus().getArea().toString().equals("L") ){
		
			lstPC = pospectuscommentservice.getPospectusCommentByType(prospectus_id, proyect_loan_id  , 1);
		
		}else{
			
			lstPC = pospectuscommentservice.getPospectusCommentByType(prospectus_id, 1  , 1);
		}
		
		has_prospectuscomment = false;
		
		if(lstPC != null && lstPC.size() > 0){
			
			prospectuscomment = lstPC.get(0);
			has_prospectuscomment = true;
			
		}else{
			
			prospectuscomment = new PospectusComment();
			PospectusCommentPK pcpk = new PospectusCommentPK();
			pcpk.setComment_id(1);
			pcpk.setComment_type_id(1);
			pcpk.setCompany_id(1);
			pcpk.setProspectus_id(prospectus_id);
			prospectuscomment.setPk(pcpk);
			prospectuscomment.setProspectus_id_to(0);
			prospectuscomment.setProyect_loan_id(proyect_loan_id);
			has_prospectuscomment = false;
			
		}
		
	}
	
	protected void init_lista_documentos() 
	{
		if(sesion.getArea().toString().equals("L")) 
		{			
			listFiles = filesService.getListFilesByProspect(prospectus_id, company_id, proyect_loan_id);
			
		} else if(sesion.getArea().toString().equals("I")) {
			
			listFiles = filesService.getListFilesByProspect(prospectus_id, company_id, 0);
		}
		
		dispCheckCredFm2 = ("none");
		dispSelfieIdentificacion = ("none");
		dispCheckCompActEcon = "none";
		dispCheckCompIncome = "none";
		dispCheckAcredProBusiness = "none";
		dispCheckCompDomi = "none";
		
		if(listFiles.size() > 0)
		{
			lDocAddedCompActEcon      = new ArrayList<FileForScreenBean>();
			lDocAddedCompIncome       = new ArrayList<FileForScreenBean>();
			lDocAddedAcredProBusiness = new ArrayList<FileForScreenBean>();
			lDocAddedCompDomi         = new ArrayList<FileForScreenBean>();
			lDocAddedCredFm2          = new ArrayList<FileForScreenBean>();
			
			
			
			for(Files regis: listFiles)
			{
				file_type_id  = regis.getFilesPk().getFile_type_id();				
				uploaded_text = regis.getFileType().getUploaded_text();
				int file_category = regis.getFileType().getFile_category_id();
				
				file_type = CatalogoFileType.getInstance(file_type_id);
				
				if((file_type == CREDENCIAL_ELECTOR_FRENTE || file_type == CREDENCIAL_ELECTOR_REVERSO || file_type == CARTILLA ||  file_type == PASAPORTE || file_type == CEDULA  || file_type == LICENCIA || file_type == LICENCIA_REVERSO  ) && naturalperson.getCitizenship() == NACIONAL  )
				{
					dispCheckCredFm2 = ("none");
					FileForScreenBean file = new FileForScreenBean(regis);
					
					validaImagen( file );
					
					lDocAddedCredFm2.add(file);	
					
					if( naturalperson.getIdentification_type_id() != null && ( naturalperson.getIdentification_type_id() == 1 || naturalperson.getIdentification_type_id() == 2 || naturalperson.getIdentification_type_id() == 5 )  ){ 
						
						if(lDocAddedCredFm2.size() == 2 ){
							dispCheckCredFm2 = "inline";
						}
						
					}else{
						
						if(lDocAddedCredFm2.size() == 1 ){
							dispCheckCredFm2 = "inline";
						}
						
					}
					
				} else if((file_type == FM2_FRENTE || file_type == FM3_REVERSO || file_type == PASAPORTE) && naturalperson.getCitizenship() == EXTRANJERO){
					dispCheckCredFm2 = ("none");
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCredFm2.add(file);	
					
					if(lDocAddedCredFm2.size() == 3 ){
						dispCheckCredFm2 = "inline";
					}
					
					
				} else if( file_type == SELFIE_IDENTIFICACION ) {
					
					dispSelfieIdentificacion = ("inline");
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					setSelfieIdentification(file);	
					
				//} else if(file_type_id >= 3 && file_type_id <= 8){
				} else if(file_category == 2){ // Comprobante de actividad económica
					dispCheckCompActEcon = "inline";
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompActEcon.add(file);		
					
				//} else if((file_type_id >= 9 && file_type_id <= 14) || (file_type_id >= 48 && file_type_id <= 58) || (file_type_id >= 126 && file_type_id <= 130 ) ){
				}else if(file_category == 3){ // Comprobante de Ingresos
					dispCheckCompIncome = "inline";
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompIncome.add(file);
					
				//} else if(file_type_id >= 15 && file_type_id <= 23){
				}else if(file_category == 4){ // Acreditación de propiedad del negocio 
					dispCheckAcredProBusiness = "inline";
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedAcredProBusiness.add(file);
					
				//} else if(file_type_id >= 24 && file_type_id<=29){
				}else if(file_category == 5){ // Comprobante de domicilio
					dispCheckCompDomi = "inline";
					FileForScreenBean file = new FileForScreenBean(regis);
					validaImagen( file );
					lDocAddedCompDomi.add(file);
					
					regis_Dom = regis;
					
				}
			}
			
			/*if( naturalperson.getCitizenship() == EXTRANJERO ){
				
				if( naturalperson.getCountry_of_residence() != null && naturalperson.getCountry_of_residence().intValue() == 1 ){
					dispCheckCompDomi = "inline";
				}
				
			} */
			
			//Determinar si ya subio parte frontal y reverso tanto para IFE or FM2
			
			if( (lDocAddedCredFm2.size() == 3 && naturalperson.getCitizenship() == EXTRANJERO))
			{
				dispCheckCredFm2 = "inline";
			
			}else{

				if( naturalperson.getIdentification_type_id() != null && ( naturalperson.getIdentification_type_id() == 1 || naturalperson.getIdentification_type_id() == 2 )  ){
					
					if(lDocAddedCredFm2.size() == 2 ){
						dispCheckCredFm2 = "inline";
					}
					
				}else{
					
					if(lDocAddedCredFm2.size() == 1 ){
						dispCheckCredFm2 = "inline";
					}
					
				}
				
			}
		
		}else{
			
			lDocAddedCompActEcon      = new ArrayList<FileForScreenBean>();
			lDocAddedCompIncome       = new ArrayList<FileForScreenBean>();
			lDocAddedAcredProBusiness = new ArrayList<FileForScreenBean>();
			lDocAddedCompDomi         = new ArrayList<FileForScreenBean>();
			lDocAddedCredFm2          = new ArrayList<FileForScreenBean>();
			
			selfieIdentification = null ;
			dispSelfieIdentificacion = "none";
		}
	}
	
	protected void init_lista_credenciales() 
	{
		listCredFm2 = fileTypeService.getListFileTypeByCategory(1);
		
		if(naturalperson.getCitizenship()!=null && naturalperson.getCitizenship()==1)
		{					
			numCredFm2 = "2";
			tiCredFm2  = "Frente y vuelta de tu credencial de elector vigente";
			
			listCREFM2 = new ArrayList<FileType>();
			
			for (FileType iterelement : listCredFm2) 
			{
				file_type = CatalogoFileType.getInstance(iterelement.getFileTypePk().getFile_type_id());
				
				if(file_type == CREDENCIAL_ELECTOR_FRENTE || file_type == CREDENCIAL_ELECTOR_REVERSO  || file_type == CARTILLA ||  file_type == PASAPORTE || file_type == CEDULA  || file_type == LICENCIA || file_type == LICENCIA_REVERSO  )
				{
					listCREFM2.add(iterelement);
				}
				
				if( file_type == SELFIE_IDENTIFICACION ){
					
				}
			}
			
		} else {
			numCredFm2 = "2";
			tiCredFm2  = "FM2";
			
			listCREFM2 = new ArrayList<FileType>();
			
			for (FileType iterelement : listCredFm2) 
			{
				file_type = CatalogoFileType.getInstance(iterelement.getFileTypePk().getFile_type_id());
				
				if(file_type == FM2_FRENTE || file_type == FM3_REVERSO || file_type == PASAPORTE)
				{
					listCREFM2.add(iterelement);
				}
			}
			
			Collections.sort(listCREFM2, FileType.fileTypeIdComp);
			
			for (FileType iterelement : listCredFm2) 
			{
				System.out.println( iterelement.getName()  );
			}
		}
	}
	
	protected void init_validity_date(  ){
		
	
		
		if( regis_Dom != null && regis_Dom.getValidity_date() != null ){
			
			frm = new SimpleDateFormat("dd/MM/yyyy");
			
			Date d = regis_Dom.getValidity_date();
			
			String s = frm.format(d);
			
			String sr[] = s.split("/");
			
			dayD = Integer.parseInt( sr[0] ) +"";
			
			monthD  = catalogo_meses_nombres[ ( Integer.parseInt( sr[1] ) - 1 ) ];
			
			yearD = sr[2];
			
		}
		
	}
	
	protected void init_file_category() 
	{
		listCategory   = fileTypeService.getListFileCatergory();
		
		for(FileCategory reg: listCategory)
		{			
			switch (reg.getFileCategoryPk().getFile_category_id()) 
			{
				case 1:
					tiCredFm2 = (naturalperson.getCitizenship() != null && naturalperson.getCitizenship()==1) ? "Frente y vuelta de tu credencial de elector vigente" : "FM2";
					 
					if(sesion.getArea()=='I')
					{
						numCredFm2 = ("1");
					} else {
						numCredFm2 = ("2");
					}					
				break;
				
				case 2:
					
					if(totalBusiness>0)
					{
						tiCompActEcon = reg.getName();										
						numCompActEcon = "5";
					}
					
				break;
			
				case 3:				
					tiCompIncome = reg.getName();
					
					numCompIncome = (totalBusiness == 0) ? "3" : "3";
				break;
				
			case 4:
				if(totalBusiness > 0)
				{
					tiAcredProBusiness  = reg.getName();					
					numAcredProBusiness = "6";
				}				
		
				break;
				
			case 5:
				tiCompDomi = reg.getName();
				
				if(sesion.getArea() =='I')
				{
					numCompDomi = "2";
					
				} else {
					
					numCompDomi = (totalBusiness == 0) ? "4" : "4";
				}
			break;
			
			default: break;
			}
		}
	}
	
	protected void init_employment_business() 
	{
		listEmployment  = employmentService.getListEmployByProspect(prospectus_id, company_id);
		listBusiness    = businessservice.getListBusinessByProspect(prospectus_id, company_id);
		
		totalEmploy   = listEmployment.size();		
		totalBusiness = listBusiness.size();
		
		listCompIncome = fileTypeService.getListFileTypeByCategory(3);
		
		if(totalEmploy!=0 && totalBusiness!=0)
		{
			listCompActEcon      = fileTypeService.getListFileTypeByCategory(2);			
			listAcredProBusiness = fileTypeService.getListFileTypeByCategory(4);
			
			displayBussiness = "block";
			
			listCboIncome = listCompIncome;
						
		} else if(totalEmploy!=0 && totalBusiness==0) {
			
			listCboIncome = new ArrayList<FileType>();
			
			for(FileType registro: listCompIncome)
			{			
				if(registro!=null && registro.getFileTypePk().getFile_type_id()==10 || registro.getFileTypePk().getFile_type_id()==11 || registro.getFileTypePk().getFile_type_id()==12 || registro.getFileTypePk().getFile_type_id() == 128 )
				{					
					listCboIncome.add(registro);
				}														
			}	
			
		} else if(totalBusiness!=0 && totalEmploy==0 ) { //Solo tiene negocio
			
			displayBussiness = "block";
			
			listCompActEcon      = fileTypeService.getListFileTypeByCategory(2);
			listAcredProBusiness = fileTypeService.getListFileTypeByCategory(4);
			listCboIncome = new ArrayList<FileType>();
			
			for(FileType registro: listCompIncome)
			{
				if(registro != null && registro.getFileTypePk().getFile_type_id() == 9 || registro.getFileTypePk().getFile_type_id()==11 || registro.getFileTypePk().getFile_type_id()==13 || registro.getFileTypePk().getFile_type_id()==14  )
				{
					listCboIncome.add(registro);
				}
			}			
		}		
	}
	
	protected void init_third_party_name() 
	{
		pr_first_name       = prevencionld.getPr_first_name()       != null ? prevencionld.getPr_first_name() :  "";
		pr_father_last_name = prevencionld.getPr_father_last_name() != null ? prevencionld.getPr_father_last_name() :  "";
		pr_mother_last_name = prevencionld.getPr_mother_last_name() != null ? prevencionld.getPr_mother_last_name() :  "";
		
		sb = new StringBuilder();
		sb.append(pr_first_name).append(" ");
		sb.append(pr_father_last_name).append(" ");
		sb.append(pr_mother_last_name);
		
		third_party_name = sb.toString();
	}
	
	protected File scaleImg( File fOrigen,int maxWidth, int maxHeight) 
	{
		try 
		{
			
			String tmpPath = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/temp/");
			System.out.println("noombre: "+fOrigen.getName());
			tmpPath += "/"+(new Date()).getTime()+"."+(fOrigen.getName().split("\\.")[1]);
			
			File thisFile = new File(tmpPath);
			
			FileInputStream fis = new FileInputStream(fOrigen);
			
			FileUtils.copyInputStreamToFile(fis, thisFile);
			
			BufferedImage img = ImageIO.read(thisFile);
			int xac = (int) img.getWidth();
			int yac = (int) img.getHeight();

			int x = maxWidth;
			int y = maxHeight;
			double resx = 0;
			double resy = 0;
			double resxy = 0;
			System.out.println("ANTES:");
			System.out.println("XAC :" + xac);
			System.out.println("YAC :" + yac);

			boolean flagcambia = false;
			if (xac > x) {
				resx = x / (double) xac;
				double yy = resx * (double) yac;
				y = (int) yy;
				flagcambia = true;
				resxy = resx;
			} else if (yac > y) {
				resy = y / (double) yac;
				double xx = resy * (double) xac;
				x = (int) xx;
				flagcambia = true;
				resxy = resy;
			}

			System.out.println("DESPUES DEL CAMBIO:");
			System.out.println("X :" + x);
			System.out.println("Y :" + y);

			if (flagcambia) {
				BufferedImage novo = new BufferedImage(x, y,
						BufferedImage.TYPE_INT_RGB);// la nueva escala

				Graphics2D g = novo.createGraphics();
				g.setColor(Color.WHITE);
				g.fill(new Rectangle(0, 0, x, y));
				AffineTransform at = null;
				System.out.println("DENTRO:");
				System.out.println("RESX :" + resx);
				System.out.println("RESY :" + resy);
				/*
				 * if(resx>resy) at =
				 * AffineTransform.getScaleInstance(resy,resy);
				 * 
				 * if(resx<resy) at =
				 * AffineTransform.getScaleInstance(resx,resx);
				 * 
				 * if(resx==resy)
				 */
				at = AffineTransform.getScaleInstance(resxy, resxy);

				g.drawRenderedImage(img, at);
				ImageIO.write(novo, "JPG", thisFile);

				img = ImageIO.read(thisFile);

				xac = (int) img.getWidth();
				yac = (int) img.getHeight();
				
				System.out.println("Fin:");
				System.out.println("RESX :" + xac);
				System.out.println("RESY :" + yac);

			}
			
			  
			return thisFile;
			
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}
	
	private void saveFile(File imgOrig ,String path,  int prospectus_id, int company_id) 
	{

		/*
		 * String inicio =
		 * FacesContext.getCurrentInstance().getExternalContext()
		 * .getRealPath("/resources/temp/");
		 * 
		 * 
		 * validateDir(inicio, true); inicio += "/"+getPhoto();
		 */
		
		//TODO
		
//		String hacia = "";
//		String path1 = "";
		
//		validateDir(hacia + path1, true);
		
//		path1 += "/";
//		Calendar cal2 = Calendar.getInstance();

//		String nameThum2 = "photo_"
//				+ prospectus_id + "_"
//				+ (((cal2.getTimeInMillis()) / 1000) + "").substring(3)
//				+ "_thump_150"+".jpg";
		
		try {
			
			File tmpFl = scaleImg(imgOrig,300, 300);
			
			System.out.println( tmpFl.getAbsolutePath()+"  --  "+tmpFl.getCanonicalPath()+" -- "+tmpFl.getPath() );
					
			InputStream is = new FileInputStream(tmpFl.getPath() );
			
			Utilities.copyFile(path,  is);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//hacia += path1;
		//fileMove(inicio, hacia);		
		
	}
	
	protected void validaImagen( FileForScreenBean file ){
	
		if( file.getLocation() != null && file.getLocation().split("\\.").length>1  ){
			
			if( isIMG( file.getLocation().split("\\.")[1]) ){
				
				String path = "";
				String pathBase = FacesContext.getCurrentInstance().getExternalContext()
						.getRealPath("/resources/");
				path += file.getLocation().split("\\.")[0];
				path += "_300_X_300."  ;
				path += file.getLocation().split("\\.")[1];
				
				File thumpImg = new File(pathBase + path);
				
				if( !thumpImg.exists() ){
					
					File imgOrig = new File(pathBase +file.getLocation());
					
					if(imgOrig.exists()){
						
						saveFile(imgOrig, ( pathBase + path ),file.getProspectus_id(),file.getCompany_id()  );
						file.setLocation_thump(path);
						
					}else{
						
						file.setLocation_thump("/img/no-logo.jpg");
						
					}
					
				}else{
					
					file.setLocation_thump(file.getLocation().split("\\.")[0] + "_300_X_300"+ "." + file.getLocation().split("\\.")[1]);
				}
				
				
				
			}else if ( (file.getLocation().split("\\.")[1]).toUpperCase().equals("PDF") ){
				
				file.setLocation_thump("/img/PDF-icon.png");
				
			}
			
		}
	
	}
	
	 protected boolean validateDir(String path, boolean action) 
		{
			File file = new File(path);
			
			boolean isDirectory = file.isDirectory();
			
			if (!isDirectory) 
			{
				System.out.println("·#·#·#·#· Creando la ruta: " + path);
				file.mkdirs();
			} else {
				System.out.println("·#·#·#·#· Ya existe la ruta: " + path);
			}
			
			return isDirectory;
		}
	 
	 
	 protected void init_address(){
		
		 EditorViviendaIMP editor_domicilio = new EditorViviendaIMP();
			
		 editor_domicilio.setSesion(sesion);
		 editor_domicilio.setPerson(naturalperson);
		 editor_domicilio.init();
		 
		 Address address = service_address.getMaxAddressByType(prospectus_id, company_id, 1);
		
		 AddressTokenIMP token = new AddressTokenIMP();
		 token.setAntiguedad(0);
		 token.setResidence(null);
				token.setAddress(address);
		
				
		 direccion = token.getAddress_TOKEN();
		 
		 direccion = direccion.substring(( direccion.indexOf("Ubicada En ") + ("Ubicada En ").length() ));
				 
				 
		 
		
		 
	 }
	 
	 protected void init_same_Address(){
		 
		/* if( sesion.getArea().toString().equals("L") ){
			 
			 ProyectLoanInfoPK pkInfo = new ProyectLoanInfoPK();
			 
			 pkInfo.setCompany_id( thisProyectLoan.getProyectloanPk().getCompany_id());
			 pkInfo.setProspectus_id( thisProyectLoan.getProyectloanPk().getProspectus_id() );
			 pkInfo.setProyect_id( thisProyectLoan.getProyectloanPk().getProyect_id() );
			 pkInfo.setProyect_loan_id( thisProyectLoan.getProyectloanPk().getProyect_loan_id() );
			 
			 info = proyectLoanInfoService.getProyectLoanInfo(pkInfo);
			 
			 if( info != null ){
				 
				 sameAddress = info.getMx_ife_domicilio();
				 
			 }else{
				 
				 info =  new ProyectLoanInfo();
				 info.setPk(pkInfo);
				 info.setMx_ife_domicilio("N");
				 proyectLoanInfoService.saveProyectLoanInfo(info);
			 }
			 
		 }else */ if( sesion.getArea().toString().equals("I") ){
			 
			InvestorPK invPk = new InvestorPK();
			
			invPk.setCompany_id(company_id);
			invPk.setProspectus_id(prospectus_id);
			 
			inv = investorservice.getInvestorById(invPk);
			
			/* if( inv != null ){
				
				if( naturalperson.getCitizenship() != null && naturalperson.getCitizenship() == NACIONAL && inv.getMx_ife_domicilio() != null ){
					
					sameAddress = inv.getMx_ife_domicilio();
				 
				}else if( naturalperson.getCitizenship() != null &&  naturalperson.getCitizenship() == EXTRANJERO  ){
						
					if( inv.getMx_ife_domicilio() != null){
						
						sameAddress = inv.getMx_ife_domicilio();
						
					}else{
					
						sameAddress = "N";
						
					}
					
				}
				
			}else{ */
				
			if( inv == null ){
			
				inv = new Investor();
				inv.setPk(invPk);
				inv.setMx_ife_domicilio("N");
				investorservice.addInvestor(inv);
				sameAddress = "N";
				
			}
			 
		  } 
		 
		 sameAddress = "N";
		 
	 }
	 
	 protected void init_lista_dias_meses()
		{
			lista_dias  = new ArrayList<String>();
			lista_meses = new ArrayList<String>();
			lista_years = new ArrayList<String>();
			
			dayD   = "0";
			monthD = "0";
			yearD  = "0";
			
			for (int i = 1; i <= 31; i++) 
			{
				lista_dias.add(i + "");
			}
			
			for (int i = 0; i < 12; i++) 
			{
				lista_meses.add(catalogo_meses_nombres[i]);
			}
		}
	 
	 
	 protected void init_lista_years() 
		{		
		 
		 frm = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar fecha = Calendar.getInstance();
			
			
			
			Date date = fecha.getTime();
			
			Integer thisyear = Integer.parseInt(frm.format(date).split("/")[2]);
			
			Integer thismonth = Integer.parseInt(frm.format(date).split("/")[1]);
		 	
			Integer nextYear = thisyear;
			
			if(thismonth <= 3 ){
				thisyear = thisyear - 1;
			}
			
			if( thismonth >10  ){
				nextYear = nextYear + 1;
			}
						
			for (int i = thisyear; i <= nextYear ; i++) 
			{
				lista_years.add(i + "");
			}
		}
	 
	
	private boolean isIMG( String extension ){
		
		return extension.toUpperCase().equals("JPG") || extension.toUpperCase().equals("JPEG") || extension.toUpperCase().equals("PNG") || extension.toUpperCase().equals("GIF");
		
	}
	
	protected void initTask(){
		System.out.println( "Revisa Tareas" );
		
		RiskTask risktask = risktaskservice.getRiskTaskByBurSolNumTaskId(thisProyectLoan.getMx_solicitud_buro(), TAREA1);
		
		if( risktask != null ){
			
			if( !risktask.getTask().getIs_enabled().equals("0") ){
				tarea1 = ( risktask.getTask_value().equals("0") );
			}else{
				tarea1 = true; //siempre se piden los documentos
			}
			
		}else{
			
			getTareas( );
			
		}
		
	}
	
	private void getTareas( ){
		
		try{
			
			String r_data = thisProyectLoan.getR_data();
			
			if( r_data != null ){
			
				if( r_data.indexOf("TAREAS") != (-1) ){
					
					String taresStr = "";
					
					taresStr = r_data.substring(r_data.indexOf("TAREAS"));
					
					taresStr = taresStr.substring( taresStr.indexOf("[")+1 , taresStr.indexOf("]") );
					
					if( taresStr.indexOf(" ") != (-1) ){
						taresStr = taresStr.replaceAll(" ","");
					}
					
					taresStr = taresStr.trim();
					
					RiskTask risktask = new RiskTask();
					
					risktask.setCompany_id( thisProyectLoan.getProyectloanPk().getCompany_id());
					risktask.setMx_solicitud_buro( thisProyectLoan.getMx_solicitud_buro());
					risktask.setProspectus_id(thisProyectLoan.getProyectloanPk().getProspectus_id());
					risktask.setTask_id(TAREA1);
					risktask.setTask_value((taresStr.split(",")[0]).trim());
					
					risktaskservice.saveRiskTask(risktask);
					
					risktask = risktaskservice.getRiskTaskByBurSolNumTaskId(thisProyectLoan.getMx_solicitud_buro(), TAREA1);

					if( !risktask.getTask().getIs_enabled().equals("0") ){
					
						tarea1 = ( risktask.getTask_value().equals("0") );
						
					}else{
						tarea1 = true;//siempre se piden los documentos
					}
					
					
					risktask = new RiskTask();
					
					risktask.setCompany_id( thisProyectLoan.getProyectloanPk().getCompany_id());
					risktask.setMx_solicitud_buro( thisProyectLoan.getMx_solicitud_buro());
					risktask.setProspectus_id(thisProyectLoan.getProyectloanPk().getProspectus_id());
					risktask.setTask_id(TAREA2);
					risktask.setTask_value((taresStr.split(",")[1]).trim());
					
					risktaskservice.saveRiskTask(risktask);
					
				}
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
}
