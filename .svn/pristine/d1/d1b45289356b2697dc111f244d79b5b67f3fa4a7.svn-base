package mx.com.kubo.managedbeans.mesa;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.reportes.buro.ChartBackBeanIMO;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.mesa.solicitud.PldNotification;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;

public abstract class ElectronicFileDMO
implements ElectronicFileIMO
{
	protected Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{clientViewFullNameServiceImp}")
	protected ClientViewFullNameService clientViewFullNameService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService service_PLD;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELResolver resolver;
	protected ELContext elContext;
	protected ExternalContext external;
	
	protected SessionBean sesion;
	protected Membership member;
	protected MembershipPK mpk;
	protected ProspectusPK prospectusPK;
	protected Prospectus   prospectus;
	
	protected SearchSummaySession searchsum;
	protected MenuControlTableBean menu;
	protected SummaryRequest summary;
	protected ChartBackBeanIMO chart;
	
	protected ProyectLoan proyectLoan;
	protected ProyectLoanPK passingProyectB;
	protected PrevencionLD   pld;
	protected PrevencionLDPK pld_PK;
	protected PldNotification pld_notification;

	protected List<DocumentationDMO> listDocumentation;
	protected List<DocumentationDMO> listDocCapPago;
	protected List<DocumentationDMO> listDocAddress;
	protected List<DocumentationDMO> listAddedContracts;
	protected List<Files> listFilesP;
	protected List<FileType>listDocsContracts;
	protected List <ProyectLoan> listProyectLoan;
	protected List<DocumentationDMO> listDocSIC;
	protected List<PldNotification> lista_pld_notification;
	
	protected Files thisFiles;
	
	private String search;
	protected String value;
	protected String realPath;
	protected String titleStr;	
	protected String contMsgStr; 
	protected String pageStr="templates/request.xhtml";	
	protected final String COMMENT_PROPIETARIO_REAL = "El cliente declaró que es el propietario real";
	protected final String COMMENT_TERCERO          = "El cliente declaró que actuó a nombre de un tercero";
	private String burSolNum;	
	protected String cadenaProyecto;
	protected String comments;
	
	protected Character third_party;

	private int radioTypeSearch = 1;
	private int typeContract;
	protected int proyect_loan_id;
	protected int proyect_id;
	protected int prospectus_id ;
	protected int company_id;
	
	protected static final int IDENTIFICACION  = 1;
	protected static final int SOLICITUD       = 2;
	protected static final int CAPACIDAD_PAGO  = 4;
	protected static final int CONTRATOS       = 5;
	protected static final int CONSULTAS       = 6;
	protected static final int BITACORA_COBRANZA     = 7;
	protected static final int COMPROBANTE_DOMICILIO = 8;
	protected static final int GARANTIAS = 9;
	protected static final int ESTADOS_FINANCIEROS   = 10;
	protected static final int PROPIETARIO_REAL      = 11;
	protected static final int ESTADO_CUENTA = 61;
	
	protected boolean creden_fm2_dip=false;	
	protected boolean capacidad_pago_disp=false;
	protected boolean doc_address_disp=false;
	protected boolean doc_contracts_disp=false;
	protected boolean valGraphicDisp = false;	
	protected boolean haveConsult  = false;	
	protected boolean include_disp = false ; 
	protected boolean msg_disp = false;	
	protected boolean propietario_real_ENABLED;
	protected boolean dispListSIC;	
	protected boolean idProvider; 		

	public String getSearch() {
		return search;
	}

	public String getValue() {
		return value;
	}

	public void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}

	public void setProspectusService(ProspectusService service) 
	{
		prospectusService = service;
	}

	public void setClientViewFullNameService(ClientViewFullNameService service) 
	{
		clientViewFullNameService = service;
	}
	
	public void setFileTypeService(FileTypeService service) 
	{
		fileTypeService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setService_PLD(PrevencionLDService service) 
	{
		service_PLD = service;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public List<DocumentationDMO> getListDocumentation() 
	{
		return listDocumentation;
	}

	public void setFilesService(FilesService service) 
	{
		filesService = service;
	}	
	
	public List<ProyectLoan> getListaProyectLoan() 
	{
		return listProyectLoan;
	}

	public List<Files> getListFilesP() {
		return listFilesP;
	}

	public String getRealPath() 
	{
		return realPath;
	}
	
	public final boolean isCreden_fm2_dip() 
	{
		return creden_fm2_dip;
	}
	
	public final boolean isCapacidad_pago_disp() 
	{
		return capacidad_pago_disp;
	}
	
	public final boolean isDoc_address_disp() 
	{
		return doc_address_disp;
	}
	
	public List<DocumentationDMO> getListDocCapPago() {
		return listDocCapPago;
	}

	public List<DocumentationDMO> getListDocAddress() {
		return listDocAddress;
	}
	
	public List<DocumentationDMO> getListAddedContracts() {
		return listAddedContracts;
	}
	
	public List<FileType> getListDocsContracts() {
		return listDocsContracts;
	}
	
	public int getTypeContract() {
		return typeContract;
	}
	
	public final boolean isDoc_contracts_disp() 
	{
		return doc_contracts_disp;
	}
	
	public void setTypeContract(int typeContract) {
			this.typeContract = typeContract;		
	}

	public Files getThisFiles() {
		return thisFiles;
	}
	
	public void setThisFiles(Files thisFiles) {
		this.thisFiles = thisFiles;
	}
	
	public String getBurSolNum() {
		return burSolNum;
	}
	public void setBurSolNum(String burSolNum) {
		this.burSolNum = burSolNum;
	}
	
	public final ProyectLoan getProyectLoan() 
	{
		return proyectLoan;
	}

	public final boolean isInclude_disp() 
	{
		return include_disp;
	}

	public String getPageStr() 
	{
		return pageStr;
	}
	
	public boolean isValGraphicDisp() 
	{
		return valGraphicDisp;
	}
	
	public final String getTitleStr() 
	{
		return titleStr;
	}

	public final String getContMsgStr() 
	{
		return contMsgStr;
	}
	
	public final boolean isMsg_disp() 
	{
		return msg_disp;
	}
	
	public List<DocumentationDMO> getListDocSIC() 
	{
		return listDocSIC;
	}
	
	public final List<PldNotification> getLista_pld_notification()
	{
		return lista_pld_notification;
	}
	
	public final boolean isDispListSIC() 
	{
		return dispListSIC;
	}
	
	public boolean isIdProvider() {
		return idProvider;
	}
	public void setIdProvider(boolean idProvider) {
		this.idProvider = idProvider;
	}
	
	public int getRadioTypeSearch() 
	{
		return radioTypeSearch;
	}
	
	public void setRadioTypeSearch(int radioTypeSearch) 
	{
		this.radioTypeSearch = radioTypeSearch;
	}	
}
