package mx.com.kubo.mesa.solicitud.documentacion;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.ServletContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Reca;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.tools.ImageUtils;

public abstract class DocumentacionAMO extends DocumentacionDMO
{
	protected String init_system_param(Integer system_param_id) 
	{
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id(company_id);
		system_param_PK.setSystem_param_id(system_param_id);		
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		return system_param.getValue();				
	}
	
	protected void init_size_limit() 
	{
		size_limit = Integer.parseInt(size_limit_DEFAULT);
		
		for(String exception_file_type_id : exception_list)
		{
			if(exception_file_type_id.equals(file_type_id.toString()))
			{
				size_limit = Integer.parseInt(size_limit_EXCEPTION);
				
				break;
			}
		}
	}
	
	protected void init_membership(){
		MembershipPK mpk = new MembershipPK(prospectus_id,company_id);
		
		membership = service_membership.getMembershipById(mpk);
	}
	
	protected void init_list_files() 
	{
		lista_files = service_file.getListFilesByProspectOrderByCategory(prospectus_id, company_id , proyect_loan_id);
		
		ht_category_file = new Hashtable<String, List<DocumentationDMO>>();	
		
		 for (Files files : lista_files) 
		 {			 
			 String formatDocument = files.getLocation().substring(files.getLocation().lastIndexOf(".")+1);
			 
			 List<DocumentationDMO> ListaF = ht_category_file.get(files.getFileType().getFileCategory().getName());
			 
			 if(ListaF == null)
			 {
				 ListaF = new ArrayList<DocumentationDMO>();
			 }
			 
			 docBean = new DocumentationDMO();
			 docBean.setProspectus_id(files.getFilesPk().getProspectus_id());
			 docBean.setCompany_id(files.getFilesPk().getCompany_id());
			 docBean.setDescription(files.getFileType().getName());
			 docBean.setUrlImg(files.getLocation());
			 docBean.setOriginalPathImg(FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//")+files.getLocation());
			 docBean.setFormatFile(formatDocument);
			 docBean.setTypeFile(files.getFilesPk().getFile_type_id());
			 docBean.setFile_id(files.getFilesPk().getFile_id());
			 docBean.setProyect_loan_id(files.getFilesPk().getProyect_loan_id());
			 docBean.setApproved(files.getApproved());
			 
			 if( ( files.getFilesPk().getFile_type_id() == 44 || ( files.getFilesPk().getFile_type_id()>=63 && files.getFilesPk().getFile_type_id()<=66 ) ) )
			 {
				 
				 if(files.getReca_id() != null)
				 {				 
					 docBean.setReca_number(files.getReca().getReca_number());
				 
				 } else {
					 
					 docBean.setReca_number("No Definido");					 
				 }				 
			 }
			 
			 ListaF.add(docBean);
			 ht_category_file.put(files.getFileType().getFileCategory().getName(), ListaF);			
		 }
		
		 claves = ht_category_file.keySet();
		 listFiles = new  ArrayList<FilesTypeCategoryBean>();
		 
		 for(String clave : claves)
		 {
			 FilesTypeCategoryBean fileCateg = new FilesTypeCategoryBean(clave, ht_category_file.get(clave));
			 
			 listFiles.add(fileCateg);
		 }
	}

	protected void init_change_control() 
	{
		 changedocument = new ChangeBean();
		 
		 lstTempChang = null;
		 
		 lstTempChang = service_change_control.getListByProspectByAfectedByTable(prospectus_id, company_id, "gn_file");
		 
		 if(proyect_loan != null)
		 {
		 	lstTempChang = service_change_control.getListByProspectByAfectedByTable(proyect_loan.getProyectloanPk().getProspectus_id(),  proyect_loan.getProyectloanPk().getCompany_id(), "gn_file");
		 	
		} else if(inversionista_ENABLED) {
			
			lstTempChang = service_change_control.getListByProspectByAfectedByTable(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(), "gn_file");
		}
		 
		for (Change_control change_control : lstTempChang) 
		{
			if((change_control.getOriginal_value()!=null && change_control.getOriginal_value()!="" && change_control.getOriginal_value().length()>15)
				&&(		change_control.getField().toLowerCase().trim().equals("logo") ||
						change_control.getField().toLowerCase().trim().equals("logo2") ||
						change_control.getField().toLowerCase().trim().equals("logo3") ||
						change_control.getField().toLowerCase().trim().equals("location")
						))
			{
					change_control.setOriginal_value(change_control.getOriginal_value().substring(change_control.getOriginal_value().lastIndexOf("/")+1));
					int index=change_control.getOriginal_value().length()/2;
					String tempString=change_control.getOriginal_value().substring(0,index);
					change_control.setOriginal_value(tempString+" "+change_control.getOriginal_value().substring(index)+" "+tempString);
				
			}
			
			if((change_control.getNew_value()!=null && change_control.getNew_value() != "" 
				&&  change_control.getNew_value().length() > 15)
				&& (change_control.getField().toLowerCase().trim().equals("logo") ||
					change_control.getField().toLowerCase().trim().equals("logo2") ||
					change_control.getField().toLowerCase().trim().equals("logo3") ||
					change_control.getField().toLowerCase().trim().equals("location")
					))
			{
					change_control.setNew_value(change_control.getNew_value().substring(change_control.getNew_value().lastIndexOf("/")+1));
					int index=change_control.getNew_value().length()/2;
					String tempString=change_control.getNew_value().substring(0,index);
					change_control.setNew_value(tempString+" "+change_control.getNew_value().substring(index));				
			}
		}
		 changedocument.setHasChange(lstTempChang!=null && lstTempChang.size()>0?true:false);
		 changedocument.setLstChanges(lstTempChang!=null && lstTempChang.size()>0?lstTempChang:null);	
	}
		
	protected void init_lista_file_type() 
	{
		lista_file_type = service_file_type.getListFileType();
		
		htCategFile = new Hashtable<String, List<SelectItem>>();
		
		for (FileType fileType : lista_file_type) 
		{
			category_id    = fileType.getFile_category_id();
			category_name  = fileType.getFileCategory().getName();
			int file_type_id   = fileType.getFileTypePk().getFile_type_id();
			file_type_name = fileType.getName();
			
			if(category_id != 6 && category_id != 7)
			{
				 ListaF = htCategFile.get(category_name);
				 
				 if(ListaF != null)
				 {		
					 flagInsert = false;
					 
					 for(FilesTypeCategoryBean filecat :listFiles)
					 {						
						 for(DocumentationDMO bean :filecat.getListFiles())
						 {							 
							 if(file_type_id == bean.getTypeFile())
							 {
								 typeF = new SelectItem(fileType.getFileTypePk().getFile_type_id(), fileType.getName()+" (Asignado)");
								 
								 ListaF.add(typeF);
								 
								 flagInsert = true;
								 
								 htCategFile.put(category_name, ListaF);
								 
								 break;
							 }
						 }						 
					 }
					 
					 if(!flagInsert)
					 {							
						 typeF = new SelectItem(file_type_id, file_type_name);
						 
						 ListaF.add(typeF);
						 
						 htCategFile.put(category_name, ListaF);						 
					 }
					 
				 } else {
					 
					 boolean flagInsert = false;
					 
					 for(FilesTypeCategoryBean filecat :listFiles)
					 {						
						 for(DocumentationDMO bean : filecat.getListFiles())
						 {							 
							 if(file_type_id == bean.getTypeFile())
							 {
								 ListaF = new ArrayList<SelectItem>();
								 
								 typeF = new SelectItem(file_type_id, file_type_name + " (Asignado)");
								 
								 ListaF.add(typeF);
								 
								 flagInsert = true;
								 
								 htCategFile.put(category_name, ListaF);
								 
								 break;
							 }
						 }						 
					 }
					 
					 if(!flagInsert)
					 {
						 ListaF = new ArrayList<SelectItem>();	
						 typeF  = new SelectItem(fileType.getFileTypePk().getFile_type_id(), fileType.getName());
						 ListaF.add(typeF);
						 htCategFile.put(fileType.getFileCategory().getName(), ListaF);						 
					 }
				 }	
			}
		}
	}
	
	protected void init_proyect_IMG() 
	{
		lisImgProyect = new ArrayList<SelectItem>();
		
		if(proyecto != null)
		{
			typeF = proyecto.getLogo()!=null?new SelectItem(-1,"Foto 1 (Asignado)"):new SelectItem(-1,"Foto 1");			
			lisImgProyect.add(typeF);
			
			typeF = proyecto.getLogo2()!=null?new SelectItem(-2,"Foto 2 (Asignado)"):new SelectItem(-2,"Foto 2");
			lisImgProyect.add(typeF);
			
			typeF = proyecto.getLogo3()!=null?new SelectItem(-3,"Foto 3 (Asignado)"):new SelectItem(-3,"Foto 3");
			lisImgProyect.add(typeF);
		}
	}

	protected void init_file_type_menu_items() 
	{
		lisDefaultItem = new ArrayList<SelectItem>();
		
		htCategFile.put("Fotos del proyecto", lisImgProyect);									
		htCategFile.put(" ", lisDefaultItem);
		
		Set<String> claves = htCategFile.keySet();
		
		SelectItemGroup[] category = new SelectItemGroup[claves.size()];
		
		int i = 0;
		
		for(String clave : claves)
		{			
			 SelectItem items[] = new SelectItem[htCategFile.get(clave).size()];
			 
			 category[i] = new SelectItemGroup(clave, clave, false, htCategFile.get(clave).toArray(items));
			 
			 i++;			
		}
		
		menuItems = category;	
	}
	
	protected void init_lista_RECA() 
	{
		recaitems = service_reca.getRecaList();				
		
		for(Reca rec : recaitems)
		{			
			if(rec.getValid_to() == null || (rec.getStatus() == 1) )
			{
				rec.setReca_number( rec.getReca_number() +" VIGENTE" );
				reca_id = rec.getPk().getReca_id();
				
			} else {
				
				rec.setReca_number( rec.getReca_number() +" DEL " + formatoDeFecha.format( rec.getValid_from() ) + " AL "+formatoDeFecha.format( rec.getValid_to() ) );				
			}			
		}
	}
	
	protected void enviaContratos(){
		try{
		
		boolean cont_garantias = false;
		boolean cont_medios = false;
		boolean cont_cap = false;
		boolean cont_cred = false;
		boolean have_capt = false;
		
		List<String> lista_archivos_adjuntos = new ArrayList<String>();
		
		String dir_garantias = "";
		String dir_medios = "";
		String dir_cap = "";
		String dir_cred = "";
		
		
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path  = ctx.getRealPath("/");
		
		destination = path+"/resources";
				
		System.out.println( " ** " + destination );
		
		if( lista_files != null ){
			
			for( Files f : lista_files ){
				
				if( f.getFilesPk().getFile_type_id() != null && f.getFilesPk().getFile_type_id().intValue() == 84 ){
					have_capt = true;
					if(f.getApproved() != null && f.getApproved().equals("1")  ){
						cont_medios = true;
						dir_medios = f.getLocation();
						lista_archivos_adjuntos.add(destination+"/"+dir_medios+"::Contrato_Medios_Electronicos.pdf");
					}
				}
				if( f.getFilesPk().getFile_type_id() != null && f.getFilesPk().getFile_type_id().intValue() == 85 ){
					have_capt = true;
					if(f.getApproved() != null && f.getApproved().equals("1")  ){
						cont_garantias = true;
						dir_garantias = f.getLocation();
						lista_archivos_adjuntos.add(destination+"/"+dir_garantias+"::Contrato_Garantia_Irrevocable.pdf");
						
					}
				}
				if( f.getFilesPk().getFile_type_id() != null && f.getFilesPk().getFile_type_id().intValue() == 86 ){
					have_capt = true;
					if(f.getApproved() != null && f.getApproved().equals("1")  ){
						cont_cap = true;
						dir_cap = f.getLocation();
						lista_archivos_adjuntos.add(destination+"/"+dir_cap+"::Contrato_Captacion.pdf");
					}
				}
				if( f.getFilesPk().getFile_type_id() != null && f.getFilesPk().getFile_type_id().intValue() == 44 ){
					
					if(f.getApproved() != null && f.getApproved().equals("1")  ){
						cont_cred = true;
						dir_cred = f.getLocation();
						lista_archivos_adjuntos.add(destination+"/"+dir_cred+"::Contrato_Credito.pdf");
					}
				}
				
			}
			
			if(membership.getPerson().getProspectus().getArea().toString().equals("L")){
				
				if(cont_cred && !have_capt){
				
					notificaEnvioContratos( membership.getMembershipPK().getCompany_id() , membership.getMembershipPK().getProspectus_id(), lista_archivos_adjuntos, membership );
				
					notificaHSFirma( membership.getPerson() );
					
				}else if( cont_cred && have_capt && cont_cap  && cont_garantias && cont_medios ){
					
					notificaEnvioContratos( membership.getMembershipPK().getCompany_id() , membership.getMembershipPK().getProspectus_id(), lista_archivos_adjuntos, membership );
					
					notificaHSFirma( membership.getPerson() );
					
				}
				
			}else{
				
				if(  have_capt && cont_cap  && cont_garantias && cont_medios ){
					
					notificaEnvioContratos( membership.getMembershipPK().getCompany_id() , membership.getMembershipPK().getProspectus_id(), lista_archivos_adjuntos , membership );
					
					notificaHSFirma( membership.getPerson() );
					
					notificaInfusion( membership.getPerson() );
					
				}
				
			}
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private boolean notificaHSFirma( NaturalPerson person ){
		
		if( person != null && person.getProspectus() != null && person.getProspectus().getHs_vid() != null ){	
			
			String valueSys = init_system_param(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			if( valueSys != null && valueSys != null && valueSys.equals("S") ){
				
				HubSpotController hs =  new HubSpotController();
				
				StringBuilder properties = new StringBuilder();
				
				if( person.getProspectus().getArea().toString().equals("L") ){
				
					properties.append("{ \"property\" : \"estatus_prospecto\" , \"value\" : \"firma_contrato_valida\"}");
				
				}else{
				
					properties.append("{ \"property\" : \"estatus_inversionista\" , \"value\" : \"cuenta_activada\"}");
				
				}
				
				hs.updateProspectus(person.getProspectus().getHs_vid(), properties);
				
			 }
		}
		
		return true;
		
	}
	
	private boolean notificaInfusion( NaturalPerson person ){
		
		try{
			
			String valueSys = init_system_param(88);//Indica si infusion está habilitado
			
			 if( valueSys != null && valueSys.equals("S") && person.getProspectus().getInfusion_id() != null ){
			 
					InfusionSoft infusion = new InfusionSoft();
					
					infusion.addTAgToContact( person.getProspectus().getInfusion_id() , 553 ); // tag Llena Registro
			
					
			 }
			 
			 return true;
		 
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	private boolean notificaEnvioContratos( int company_id , int prospectus_id, List<String> lista_archivos_adjuntos, Membership member ){
		
		try{
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "*" );
			System.out.println( "*ARCHIVOS A ENVIAR" );
			System.out.println( "*" );
			System.out.println( "" );
			for( String s : lista_archivos_adjuntos ){
				  
				  System.out.println( s );
				  
			  }
			System.out.println( "" );
			System.out.println( "*" );
			System.out.println( "*" );
			System.out.println( "*" );
			System.out.println( "" );
			System.out.println( "" );
		
				//if( isCredit() ){
				NotificadorIMO notificador = new NotificadorIMP();
				
				/*MembershipPK mpk = new MembershipPK();
				
				mpk.setCompany_id( company_id );
				mpk.setProspectus_id( prospectus_id);
				
				Membership member = membershipService.getMembershipById(mpk);*/
				
				notificador.setEmisor(member);
				
				//List<String> lista_archivos_adjuntos = new ArrayList<String>();
				
				//String destination = FacesContext.getCurrentInstance()
				//		.getExternalContext().getRealPath("");
				
//				lista_archivos_adjuntos.add(destination+"/"+url_contrato_medios_electronicos+"::Contrato_Medios_Electronicos.pdf");
//				lista_archivos_adjuntos.add(destination+"/"+url_contrato_captacion+"::Contrato_Captacion.pdf");
//				lista_archivos_adjuntos.add(destination+"/"+url_contrato_garantias+"::Contrato_Garantia_Irrevocable.pdf");
				
//				if( isCredit() ){
//					lista_archivos_adjuntos.add(destination+"/"+url_contrato_credito+"::Contrato_Credito.pdf");
//				}
				
				notificador.setLista_archivos_adjuntos(lista_archivos_adjuntos);
				
				notificador.notificar(Evento.ENVIO_DE_CONTRATOS);
			
			//}
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	protected void init_proyect_LOGOS() 
	{
		if(proyecto != null)
		{				
			fileLogo = new File(destination + "/" + proyecto.getLogo());
			
			if(fileLogo.exists())
			{
				htWH = ImageUtils.scaleImage(new File(destination + "/" + proyecto.getLogo()), 400, 305);
				
				imageLogo1 = new ImagesBean();
				imageLogo1.setWidth (htWH != null ? (Integer) htWH.get("Width")  : 445);
				imageLogo1.setHeight(htWH != null ? (Integer) htWH.get("Height") : 305);
				imageLogo1.setUrlImg(proyecto.getLogo());	
				
			} else {
				
				imageLogo1 = new ImagesBean();
				imageLogo1.setWidth(150);
				imageLogo1.setHeight(150);
				
				if(female)
				{
					imageLogo1.setUrlImg("/img/sinimagenM.jpg");
					
				} else {
					
					imageLogo1.setUrlImg("/img/sinimagen.jpg");
				}
			}
			
			if(proyecto.getLogo2() != null)
			{
				fileLogo = new File(destination + "/" + proyecto.getLogo2());
				
				if(fileLogo.exists())
				{
					htWH = ImageUtils.scaleImage(new File(destination + "/" + proyecto.getLogo2()), 445, 305);
					
					imageLogo2 = new ImagesBean();
					imageLogo2.setWidth (htWH != null ? (Integer)htWH.get("Width")  : 445);
					imageLogo2.setHeight(htWH != null ? (Integer)htWH.get("Height") : 305);
					imageLogo2.setUrlImg(proyecto.getLogo2());
					 
				} else {
					
					imageLogo2 = null;
				}
				
			} else {
				
				imageLogo2 = null;
			}
				
			if(proyecto.getLogo3() != null)
			{
				fileLogo = new File(destination+"/"+proyecto.getLogo3());
				
				if(fileLogo.exists())
				{
					htWH = ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo3()), 445, 305);
					
					imageLogo3 = new ImagesBean();
					imageLogo3.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
					imageLogo3.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
					imageLogo3.setUrlImg(proyecto.getLogo3());
					
				} else {
					
					imageLogo3 = null;
				}
				
			} else {
				
				imageLogo3 = null;
			}
		}
	}
}
