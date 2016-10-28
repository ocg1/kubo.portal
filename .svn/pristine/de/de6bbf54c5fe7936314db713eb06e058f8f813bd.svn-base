package mx.com.kubo.test;

import java.io.InputStream;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;

public abstract class DocumentLoaderDMO 
implements DocumentLoaderIMO
{
	protected FilesService    service_files;
	protected FileTypeService service_file_type;	
	protected ProyectService  service_proyect;
	protected Change_controlService service_change_control;
	protected ProyectLoanService    service_proyect_loan;
	
	protected ProyectLoan proyect_loan;
	protected Proyect proyect;
	
	protected FileType   file_type;
	protected FileTypePK file_type_PK;
	
	protected Change_control   changeCtrl;
	protected Change_controlPK changeCtrlPK;
	
	protected SessionBean sesion;
	
	protected InputStream stream;
	
	protected StringBuilder sb;
	
	protected String metadata; 	
	protected String real_PATH;
	protected String nameFile="";
	protected String category="";
	protected String pathFile="";
	protected String pathDocument;
	protected String pathHistoric;
	protected String formatFile = ".pdf";
	protected String nameFileHist;
	
	protected Integer reca_id;
	
	protected int company_id;
	protected int prospectus_id;
	protected int proyect_loan_id;
	protected int file_category_id;
	
	protected final int CONTRATO_CREDITO_FRIMADO = 44;
	
	protected boolean deleteHistory;
	protected boolean upload_OK;
		
	public final void setService_files(FilesService service)
	{
		service_files = service;
	}
	
	public final void setService_file_type(FileTypeService service)
	{
		service_file_type = service;
	}
	
	public void setService_proyect_loan(ProyectLoanService service)
	{
		service_proyect_loan = service;
	}
	
	public void setService_proyect(ProyectService service)
	{
		service_proyect = service;
	}
	
	public void setService_change_control(Change_controlService service)
	{
		service_change_control = service;
	}
	
	public final void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan)
	{
		this.proyect_loan = proyect_loan;
		
		company_id      = proyect_loan.getProyectloanPk().getCompany_id();
		prospectus_id   = proyect_loan.getProyectloanPk().getProspectus_id();
		proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		pathDocument = "/documents/cia_" + company_id + "/pros_" + prospectus_id +  "/";		
		pathHistoric = "/historic/cia_"  + company_id + "/pros_" + prospectus_id + "/";
		
		proyect = proyect_loan.getProyect();
	}
	
	public final void setInputStream(InputStream input_stream)
	{
		stream = input_stream;
	}
	
	public final void setReal_PATH(String real_PATH)
	{
		this.real_PATH = real_PATH;
	}
	
	public void setReca_id(Integer reca_id) 
	{
		this.reca_id = reca_id;
	}
	
	public final boolean isUpload_OK()
	{
		return upload_OK;
	}
}
