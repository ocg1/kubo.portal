package mx.com.kubo.test;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Reca;
import mx.com.kubo.registro.verificacion.PersonaBloqueadaIMO;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.RecaService;

public abstract class TestDashboardDMO 
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService service_files;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService service_file_type;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService service_proyect;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
		
	@ManagedProperty("#{recaServiceImp}")
	protected RecaService service_reca;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext      context;
	protected ELResolver     resolver;	
	protected ExternalContext external;
	protected HtmlInputText input_text;
	
	protected SessionBean sesion;
	
	protected PersonaBloqueadaIMO blocked_person;
	protected DocumentLoaderIMO loader;
	
	protected ProyectLoan proyect_loan;
	
	protected File contrato;
	protected File folder;
	
	protected InputStream input_stream;
	
	protected List <Reca> recaitems;
	
	protected StringBuilder sb;
	
	protected String folder_name;
	protected String file_name;
	protected String folder_path;
	protected String safi_credit_id;
	protected String real_PATH; 
	
	protected String[] lista_files;
	
	protected SimpleDateFormat date_format;
	
	protected Integer reca_id;
	
	protected int blocked_person_TOTAL;
	protected int endIndex;
	protected int beginIndex;
	
	protected boolean upload_OK;
	
	public void setService_proyect_loan(ProyectLoanService service)
	{
		service_proyect_loan = service;
	}
	
	public void setService_files(FilesService service)
	{
		service_files = service;
	}
	
	public void setService_file_type(FileTypeService service)
	{
		service_file_type = service;
	}
	
	public void setService_proyect(ProyectService service)
	{
		service_proyect = service;
	}
	
	public void setService_change_control(Change_controlService service)
	{
		service_change_control = service;
	}	
	
	public final void setService_reca(RecaService service) 
	{
		service_reca = service;
	}
}
