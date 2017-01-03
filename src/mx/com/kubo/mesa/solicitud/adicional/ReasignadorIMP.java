package mx.com.kubo.mesa.solicitud.adicional;

import static mx.com.kubo.change_control.ChangeControlEMO.CREDITO_ADICIONAL_CON_CONSULTA;
import static mx.com.kubo.change_control.ChangeControlEMO.CREDITO_ADICIONAL_SIN_CONSULTA;
import static mx.com.kubo.change_control.ChangeControlEMO.RENOVACION_CREDITO;

import java.util.ArrayList;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.SimulatorBean;

public final class ReasignadorIMP extends ReasignadorPMO
implements ReasignadorIMO
{	
	public final void setProyect_loan_reasignable(ProyectLoan proyect_loan)
	{
		setProyectloan(proyect_loan);
	}
	
	public final void init(ProyectLoan proyect_loan_ACTUAL)
	{
		proyect_loan   = proyect_loan_ACTUAL;
		
		company_id     = proyect_loan.getPerson().getNatPerPK().getCompany_id();
		prospectus_id  = proyect_loan.getPerson().getNatPerPK().getProspectus_id();		
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(company_id);
		mpk.setProspectus_id(prospectus_id);
		
		membership =  membershipservice.getMembershipById(mpk);
		
	}
	
	public void init_renovacion_aprobacion_automatica(String loan_type)
	{		
		
		company_id     = proyect_loan.getPerson().getNatPerPK().getCompany_id();
		prospectus_id  = proyect_loan.getPerson().getNatPerPK().getProspectus_id();
		
		SimulatorBean simulation = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
		
		score = scoreService.loadMaxScoringByProspectus(prospectus_id, company_id);
		
		proyect_loan_NEW = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
		
		proyect_NEW = proyect_loan_NEW.getProyect();
		
		allocater = new ScoreAllocIMP();		
		allocater.setProyect_loan(proyect_loan_NEW);
		allocater.setScore(score);
		allocater.setSimulation(simulation);				
		allocater.init();
		
		proyect_loan_NEW = allocater.getProyect_loan();
				
		int proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		proyect_loan_NEW.setLoan_type(loan_type);
		proyect_loan_NEW.setPrevious_proyect_loan_id(proyect_loan_id);
		proyect_loan_NEW.setIs_automatic_aproved(is_automatic_aproved);
		
		proyect_loan_NEW.setIs_published("S");
		
		proyectloanService.update(proyect_loan_NEW);				
		
						
		ProyectPK project_actual_PK = proyect_loan_NEW.getProyect().getProyectoPk();		
					
		proyect_loan.getProyect().setProyectoPk(project_actual_PK);
		
		proyectService.update(proyect_loan.getProyect());
		
		
		Proyect project_actual = proyectService.getProyectById(project_actual_PK);
				
		int proyect_id = project_actual_PK.getProyect_id();
				
		String logo  = proyect_loan_NEW.getProyect().getLogo();
		String logo2 = proyect_loan_NEW.getProyect().getLogo2();
		String logo3 = proyect_loan_NEW.getProyect().getLogo3();
		
		proyect_loan_id = proyect_loan_NEW.getProyectloanPk().getProyect_loan_id();								
		
		if(logo != null)
		{
			String pathDocument = copy_proyect_photo(logo, 1, proyect_loan_id, proyect_id);
			
			project_actual.setLogo(pathDocument);
		}		
		
		if(logo2 != null)
		{
			String pathDocument = copy_proyect_photo(logo2, 2, proyect_loan_id, proyect_id);
			
			project_actual.setLogo2(pathDocument);
		}	
		
		if(logo3 != null)
		{
			String pathDocument = copy_proyect_photo(logo3, 3, proyect_loan_id, proyect_id);
			
			project_actual.setLogo3(pathDocument);
		}	
		
		proyectService.update(project_actual);
			
/*			
		if(tipo_credito_adicional == NUEVA_CONSULTA_DISABLED)
		{
			registrar_change_control(new ChangeBean(CREDITO_ADICIONAL_SIN_CONSULTA), emisor_prospectus_id);
		}
		
		if(tipo_credito_adicional == NUEVA_CONSULTA_ENABLED)
		{
			registrar_change_control(new ChangeBean(CREDITO_ADICIONAL_CON_CONSULTA), emisor_prospectus_id);
		}
*/			
			
		crear_lista_documentos(VALIDACION_VIGENCIA_DISABLED);
		copiar_documentos();
		
		callSGB(proyect_NEW, proyect_loan_NEW);
			
//		registrar_change_control(new ChangeBean(RENOVACION_CREDITO), emisor_prospectus_id);		
	}	

	public final void renovar_solicitud_de_credito(TipoCreditoAdicional tipo_credito_adicional)
	{
		int emisor_prospectus_id = sesion.getProspectus_id();
		
		crear_proyect_loan_NEW(tipo_credito_adicional, 3);
		
		proyect_loan_NEW = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
		
		crear_lista_documentos(VALIDACION_VIGENCIA_DISABLED);
		copiar_documentos();
		
		callSGB(proyect_NEW, proyect_loan_NEW);
		
		registrar_change_control(new ChangeBean(RENOVACION_CREDITO), emisor_prospectus_id);
	}
		
	public final void crear_nuevo_proyecto(TipoCreditoAdicional tipo_credito_adicional, int loan_type)
	{	
		int emisor_prospectus_id = sesion.getProspectus_id();
		
		company_id     = proyect_loan.getPerson().getNatPerPK().getCompany_id();
		prospectus_id  = proyect_loan.getPerson().getNatPerPK().getProspectus_id();
		
		crear_proyect(prospectus_id, company_id);													
			
		if(is_proyect_NEW_OK)
		{					
			crear_proyect_loan_NEW(tipo_credito_adicional, loan_type);						
			
			proyect_loan_NEW = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);	
			
			if(tipo_credito_adicional == TipoCreditoAdicional.NUEVA_CONSULTA_DISABLED)
			{
				registrar_change_control(new ChangeBean(CREDITO_ADICIONAL_SIN_CONSULTA), emisor_prospectus_id);
			}
			
			if(tipo_credito_adicional == TipoCreditoAdicional.NUEVA_CONSULTA_ENABLED)
			{
				registrar_change_control(new ChangeBean(CREDITO_ADICIONAL_CON_CONSULTA), emisor_prospectus_id);
			}
		}			
	}
	
	public final void crear_lista_documentos(boolean validacion_de_vigencia) 
	{
		faces    = FacesContext.getCurrentInstance();
		external = faces.getExternalContext();
		
		proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		lista_files_loaded = filesService.getListFilesByProspectOrderByCategory(prospectus_id, company_id, proyect_loan_id);
		
		lista_files = new ArrayList<DocumentationDMO>(lista_files_loaded.size());
		
		for (Files file : lista_files_loaded)
		{
			 real_path = external.getRealPath("//resources//") + file.getLocation();
			 
			 url_img        = file.getLocation();
			 formatDocument = file.getLocation().substring(file.getLocation().lastIndexOf(".") + 1);
			 			 
			 uploaded_date  = file.getUpload_date();
			 category_id    = file.getFileType().getFile_category_id();
			 dias_vigencia  = file.getFileType().getFileCategory().getValidity();			 
			 file_type      = file.getFileType().getName();
			 
			 prospectus_id  = file.getFilesPk().getProspectus_id();
			 company_id     = file.getFilesPk().getCompany_id();			 			 			 		
			 file_type_id   = file.getFilesPk().getFile_type_id();			 
			 file_id        = file.getFilesPk().getFile_id();			
			 
			 docBean = new DocumentationDMO();
				 
			 docBean.setProspectus_id(prospectus_id);
			 docBean.setCompany_id(company_id);
			 
			 docBean.setDescription(file_type);
			 docBean.setUrlImg(url_img);
			 
			 docBean.setOriginalPathImg(real_path);
			 docBean.setFormatFile(formatDocument);
			 
			 docBean.setTypeFile(file_type_id);
			 docBean.setFile_id(file_id);
			 docBean.setProyect_loan_id(proyect_loan_id);
			 
			 if(validacion_de_vigencia)
			 {
				 asignar_vigencia_del_documento(getFecha_vigente(uploaded_date, dias_vigencia));
				 
				 if(isDocumento_vigente_DISABLED() && category_id != 8)
				 {
					 lista_files.add(docBean);
				 }
				 
			 } else {
				 
				 if(category_id != 8)
				 {
					 lista_files.add(docBean);
				 }
			 }
		}
	}
	
	public final void copiar_documentos() 
	{
		for(DocumentationDMO documento: lista_files)
		{
			error_al_mover = copiar_archivos(documento);
			
			if(error_al_mover)
			{
				break;
			} 
		}
	}
		
	public final boolean callSGB(Proyect proyect, ProyectLoan proyect_loan)
	{
		new_proyect_OK = false;
		
		try
		{				
			this.proyect_loan = proyect_loan;
			
			init_documents_review(proyect);
			
			publicator = new SGBNewProyectIMP();
			publicator.setProyect(proyect);
			publicator.setProyect_loan(proyect_loan);
			publicator.setIs_automatic_aproved(is_automatic_aproved);
			publicator.init();
			
			new_proyect_OK = publicator.isChange_status_ENABLED();								
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			new_proyect_OK = false;
		}
		
		return new_proyect_OK;
	}
}
