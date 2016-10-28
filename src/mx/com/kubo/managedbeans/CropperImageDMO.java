package mx.com.kubo.managedbeans;

import java.util.Hashtable;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;

public abstract class CropperImageDMO 
{		
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService  employmentService;
	
	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService businessservice;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	protected FacesContext faces;
	protected    ELContext elContext;
	
	protected ELResolver resolver;
	
	protected Logger log = Logger.getLogger(getClass());
	
	protected SessionBean sesion;
	
	protected EditImageSession editImg;
	protected Proyect thisProyect;
    protected DocumentationDMO documentTypeDesc;    
    protected Files thisFiles;    
    
	protected SelectItem[] menuItems;
	
	protected List<FileType> lFileType;
	
	protected Hashtable<String,List<SelectItem>> htCategFile;	
	
	protected String originalImgName;
	protected String rotateImgName;
    protected String cropImgName;
    protected String tempOrigRotaImg;
    protected String realPath;
    
    protected Double newAngle=0.0;
    
    protected Integer originalTypeFile;
    
    protected int widthImg;
    protected int heightImg;
    protected int x;
    protected int y;
    protected int x2;
    protected int y2;
    
    protected boolean isImgLogo=false;
    protected boolean disabled=false;
    protected boolean formatIspdfFile=false;
    protected boolean changeReclType=false;
    protected boolean changeRotateOrCrop=false;
    
	public String getOriginalImgName() {
		return originalImgName;
	}
	public void setOriginalImgName(String originalImgName) {
		this.originalImgName = originalImgName;
	}
	public String getRotateImgName() {
		return rotateImgName;
	}
	public void setRotateImgName(String rotateImgName) {
		this.rotateImgName = rotateImgName;
	}
	public String getCropImgName() {
		return cropImgName;
	}
	public void setCropImgName(String cropImgName) {
		this.cropImgName = cropImgName;
	}
	public Double getNewAngle() {
		return newAngle;
	}
	public void setNewAngle(Double newAngle) {
		this.newAngle = newAngle;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}	
	public EditImageSession getEditImg() {
		return editImg;
	}
	public void setEditImg(EditImageSession editImg) {
		this.editImg = editImg;
	}	
	public ProyectService getProyectService() {
		return proyectService;
	}
	public void setProyectService(ProyectService proyectService) {
		this.proyectService = proyectService;
	}
	public EmploymentService getEmploymentService() {
		return employmentService;
	}
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}
	public BusinessService getBusinessservice() {
		return businessservice;
	}
	public void setBusinessservice(BusinessService businessservice) {
		this.businessservice = businessservice;
	}
	public FileTypeService getFileTypeService() {
		return fileTypeService;
	}
	public void setFileTypeService(FileTypeService fileTypeService) {
		this.fileTypeService = fileTypeService;
	}
	public FilesService getFilesService() {
		return filesService;
	}
	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}	
	public Proyect getThisProyect() {
		return thisProyect;
	}
	public void setThisProyect(Proyect thisProyect) {
		this.thisProyect = thisProyect;
	}	
	public boolean isImgLogo() {
		return isImgLogo;
	}
	public void setImgLogo(boolean isImgLogo) {
		this.isImgLogo = isImgLogo;
	}
	public int getWidthImg() {
		return widthImg;
	}
	public void setWidthImg(int widthImg) {
		this.widthImg = widthImg;
	}
	public int getHeightImg() {
		return heightImg;
	}
	public void setHeightImg(int heightImg) {
		this.heightImg = heightImg;
	}	
	public String getTempOrigRotaImg() {
		return tempOrigRotaImg;
	}
	public void setTempOrigRotaImg(String tempOrigRotaImg) {
		this.tempOrigRotaImg = tempOrigRotaImg;
	}	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}	
	public DocumentationDMO getDocumentTypeDesc() {
		return documentTypeDesc;
	}
	public void setDocumentTypeDesc(DocumentationDMO documentTypeDesc) {
		this.documentTypeDesc = documentTypeDesc;
	}
	public SelectItem[] getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(SelectItem[] menuItems) {
		this.menuItems = menuItems;
	}	
	public boolean isChangeReclType() {
		return changeReclType;
	}
	public void setChangeReclType(boolean changeReclType) {
		this.changeReclType = changeReclType;
	}	
	public boolean isChangeRotateOrCrop() {
		return changeRotateOrCrop;
	}
	public void setChangeRotateOrCrop(boolean changeRotateOrCrop) {
		this.changeRotateOrCrop = changeRotateOrCrop;
	}	
	public Change_controlService getChangeControlService() {
		return changeControlService;
	}
	public void setChangeControlService(Change_controlService changeControlService) {
		this.changeControlService = changeControlService;
	}
	
	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}
	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}
	public List<FileType> getlFileType() {
		return lFileType;
	}
	public void setlFileType(List<FileType> lFileType) {
		this.lFileType = lFileType;
	}	
	public Files getThisFiles() {
		return thisFiles;
	}
	public void setThisFiles(Files thisFiles) {
		this.thisFiles = thisFiles;
	}		
	public boolean isFormatIspdfFile() {
		return formatIspdfFile;
	}
	public void setFormatIspdfFile(boolean formatIspdfFile) {
		this.formatIspdfFile = formatIspdfFile;
	}
}
