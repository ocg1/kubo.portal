package mx.com.kubo.mesa.solicitud.documentacion;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.kubows.AcceptedFileRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.generales.DocumentUpload;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Reca;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.RecaService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public abstract class DocumentacionDMO 
implements DocumentacionIMO
{
	protected           RecaService service_reca;
	protected       FileTypeService service_file_type;
	protected          FilesService service_file;
	protected    SystemParamService service_system_param;
	protected Change_controlService service_change_control;	
	protected     MembershipService service_membership;
	
	protected  RequestContext request;
	protected    FacesContext faces;
	protected       ELContext elContext;	
	protected ExternalContext external;
	
	protected ELResolver resolver;
	
	protected HtmlSelectOneMenu select_one_menu;
	
	protected SessionBean   sesion;
	protected NaturalPerson persona;
	protected Prospectus    prospectus;
	protected Membership   membership;
	protected MembershipPK membership_PK;
	
	protected ProyectLoan proyect_loan;
	protected Proyect     proyecto;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	protected ChangeBean changedocument;
	protected SelectItem        typeF;
//	protected DocumentationDMO docBean;
	protected DocumentUpload documentUpload;
	protected EditImageSession editImg;
	
	protected PublicProyectServiceLocator locator;
	protected PublicProyect public_proyect;
	protected WsResponse response;
	protected AcceptedFileRequest acceptedfile;
	
	protected FileTypePK filetypepk;
	protected FileType   filetype;
	protected File       fileLogo;
	
	protected FileTypeIMO types;
	
	protected ImagesBean imageLogo1;
	protected ImagesBean imageLogo2;
	protected ImagesBean imageLogo3;
	
	protected List <SelectItem>  lisImgProyect;
	protected List <SelectItem>  ListaF;
	protected List <SelectItem> lisDefaultItem;
	
	protected List <Files> files;
	protected List <FileType>  lista_file_type;
	protected List <FilesTypeCategoryBean>  listFiles;	
	protected List <Reca> recaitems;
	protected List <Change_control> lstTempChang;
	
//	protected Set<String> claves;
	
	protected Hashtable<String, Integer> htWH;
	protected Hashtable<String, List<SelectItem>> htCategFile;
//	protected Hashtable<String, List<DocumentationDMO>> ht_category_file;
	protected Hashtable<String,Integer> wh;
	
	protected SelectItem[] menuItems;
	
	protected SimpleDateFormat formatoDeFecha;
	
	protected String real_path;
	protected String category_name;
	protected String file_type_name;
	protected String format;
	protected String file_name;
	//protected String destination;
	protected String valAutorizaDoc;
	protected String size_limit_DEFAULT;
	protected String size_limit_EXCEPTION;	
	
	protected String[] exception_list; 
	
	protected Integer reca_id;	
	protected Integer file_type_id;
	protected Integer size_limit;
	
	protected int accepted_prospectus_id;
	protected int prospectus_id;
	protected int company_id;
	protected int category_id;
	protected int proyect_loan_id;
	
	protected final int CONTRATO_CREDITO_FIRMADO    = 44; 
	protected final int CONTRATO_CREDITO_FIRMADO_02 = 63;
	protected final int CONTRATO_CREDITO_FIRMADO_05 = 66;
	protected final int SIZE_LIMIT_DEFAULT   = 84;
	protected final int SIZE_LIMIT_EXCEPTION = 85;
	protected final int EXCEPTION_LIST       = 86;
	
	protected boolean female;	
	protected boolean inversionista_ENABLED;
	protected boolean reca_ENABLED;
	protected boolean logo_ENABLED;
	protected boolean file_type_ENABLED;
	
	protected DocumentacionDMO()
	{
		service_reca           = Utilities.findBean("recaServiceImp");
		service_file_type      = Utilities.findBean("fileTypeServiceImp");
		service_file           = Utilities.findBean("filesServiceImp");
		service_system_param   = Utilities.findBean("systemParamServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_membership	   = Utilities.findBean("membershipServiceImp");
		
		formatoDeFecha   = new SimpleDateFormat("dd/MM/yyyy");								
		
		file_type_id = 0;
		category_id  = 0;
		
		category_name  = "";
		file_type_name = "";
	}

	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		accepted_prospectus_id = sesion.getCoachProspectus_id() != null ? sesion.getCoachProspectus_id() : sesion.getProspectus_id();
	}

	public final void setPersona(NaturalPerson persona) 
	{
		this.persona = persona;
		
		prospectus = persona.getProspectus();
		
		inversionista_ENABLED = prospectus.getArea().toString().equals("I");
		
		if(inversionista_ENABLED) 
		{			
			prospectus_id = persona.getNatPerPK().getProspectus_id();
			company_id    = persona.getNatPerPK().getCompany_id();
			proyect_loan_id = 1;									
		}
	}

	public final void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{
			prospectus_id   = proyect_loan.getProyectloanPk().getProspectus_id();
			company_id      = proyect_loan.getProyectloanPk().getCompany_id();
			proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
			
			proyecto = proyect_loan.getProyect();														
		} 
	}
	
	public void setFile_type_id(Integer file_type_id)
	{
		this.file_type_id = file_type_id;
		
		file_type_ENABLED = true;
	}
	
	public void setReal_path(String real_path)
	{
		this.real_path = real_path;
	}
	
	public final boolean isFile_type_ENABLED() 
	{
		return file_type_ENABLED;
	}
	
	public final boolean isReca_ENABLED() 
	{
		return reca_ENABLED;
	}
	
	public final boolean isLogo_ENABLED() 
	{
		return logo_ENABLED;
	}

	public final Integer getFile_type_id() 
	{
		return file_type_id;
	}
	
	public final ChangeBean getChangedocument() 
	{
		return changedocument;
	}
	
	public final SelectItem[] getMenuItems() 
	{
		return menuItems;
	}
	
	public final List<FilesTypeCategoryBean> getListFiles() 
	{
		return listFiles;
	}
	
	public final List<Reca> getRecaitems() 
	{
		return recaitems;
	}
	
	public final Integer getReca_id() 
	{
		return reca_id;
	}

	public final Integer getSize_limit() 
	{
		return size_limit;
	}
	
	public String getValAutorizaDoc() 
	{
		return valAutorizaDoc;
	}

	public void setValAutorizaDoc(String valAutorizaDoc) 
	{
		this.valAutorizaDoc = valAutorizaDoc;
	}
}
