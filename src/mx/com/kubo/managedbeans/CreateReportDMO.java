package mx.com.kubo.managedbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.datamodels.KivaClientDataModel;
import mx.com.kubo.model.ClientCarteraKiva;
import mx.com.kubo.model.ReportType;
import mx.com.kubo.services.ClientCarteraKivaService;
import mx.com.kubo.services.PipelineHistoryService;
import mx.com.kubo.services.ReportLogService;
import mx.com.kubo.services.ReportTypeService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.ViewPipelineService;

import org.primefaces.model.StreamedContent;

public abstract class CreateReportDMO 
{
	@ManagedProperty("#{reportTypeServiceImp}")
	protected ReportTypeService reportTypeService;
	
	@ManagedProperty("#{clientCarteraKivaServiceImp}")
	protected ClientCarteraKivaService clientCarteraKivaService;
	
	@ManagedProperty("#{viewPipelineServiceImp}")
	protected ViewPipelineService viewPipelineService;
	
	@ManagedProperty("#{pipelineHistoryServiceImp}")
	protected PipelineHistoryService pipelineHistoryService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;

	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemParamService;
	
	@ManagedProperty("#{reportLogServiceImp}")
	protected ReportLogService reportLogService;
	
	protected final String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//");
	protected String pathStreamFile = "";
	protected SessionBean sesion;
	protected boolean fileExist = false;
	protected StreamedContent streamFile;
	
	protected SimpleDateFormat fm =  new SimpleDateFormat("yyyy_MM_dd hh:mm:ss");
	protected SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
	protected static SimpleDateFormat fm2 = new SimpleDateFormat("MM/dd/yyyy");
	protected SimpleDateFormat fm3 = new SimpleDateFormat("dd-MM-yyyy");
	
	protected Integer reportSelected = 0;
	protected List<ReportType> reportTypeList = null;
	
	protected List<ClientCarteraKiva> kivaCarteraList;
	protected ClientCarteraKiva[] kivaCarteraSelectedList;
	protected KivaClientDataModel carteraKivaModel;
	protected List<ClientCarteraKiva> ctesNotFound = new ArrayList<ClientCarteraKiva>();
	
	protected List<Date> dateHistoryPipelineList = null;
	protected String datePipelineHistorySelected = null;
	
//	private List<ViewPipeline> viewPipeline;
//	private ViewPipeline[] viewPipelineSelectedList;
//	private ViewPipelineDataModel viewPipelineDataModel;
//	private List<ViewPipeline> ctesNotFoundViewPipeline;
	
	public ReportTypeService getReportTypeService() {
		return reportTypeService;
	}

	public void setReportTypeService(ReportTypeService reportTypeService) {
		this.reportTypeService = reportTypeService;
	}

	public ClientCarteraKivaService getClientCarteraKivaService() {
		return clientCarteraKivaService;
	}

	public void setClientCarteraKivaService(
			ClientCarteraKivaService clientCarteraKivaService) {
		this.clientCarteraKivaService = clientCarteraKivaService;
	}

	public ServiceCallingService getServicecallingService() {
		return servicecallingService;
	}

	public SystemParamService getSystemParamService() {
		return systemParamService;
	}

	public void setSystemParamService(SystemParamService systemParamService) {
		this.systemParamService = systemParamService;
	}

	public ReportLogService getReportLogService() {
		return reportLogService;
	}

	public void setReportLogService(ReportLogService reportLogService) {
		this.reportLogService = reportLogService;
	}

	public void setServicecallingService(ServiceCallingService servicecallingService) {
		this.servicecallingService = servicecallingService;
	}

	public List<ReportType> getReportTypeList() {
		return reportTypeList;
	}

	public void setReportTypeList(List<ReportType> reportTypeList) {
		this.reportTypeList = reportTypeList;
	}

	public List<ClientCarteraKiva> getKivaCarteraList() {
		return kivaCarteraList;
	}

	public void setKivaCarteraList(List<ClientCarteraKiva> kivaCarteraList) {
		this.kivaCarteraList = kivaCarteraList;
	}

	public Integer getReportSelected() {
		return reportSelected;
	}

	public void setReportSelected(Integer reportSelected) {
		this.reportSelected = reportSelected;
	}

	public ClientCarteraKiva[] getKivaCarteraSelectedList() {
		return kivaCarteraSelectedList;
	}

	public void setKivaCarteraSelectedList(ClientCarteraKiva[] kivaCarteraSelectedList) 
	{
		this.kivaCarteraSelectedList = kivaCarteraSelectedList;
	}
	
	public KivaClientDataModel getCarteraKivaModel() {
		return carteraKivaModel;
	}

	public void setCarteraKivaModel(KivaClientDataModel carteraKivaModel) {
		this.carteraKivaModel = carteraKivaModel;
	}

	public boolean isFileExist() {
		return fileExist;
	}

	public void setFileExist(boolean fileExist) {
		this.fileExist = fileExist;
	}

	public StreamedContent getStreamFile() {
		return streamFile;
	}

	public void setStreamFile(StreamedContent streamFile) {
		this.streamFile = streamFile;
	}

	public String getPathStreamFile() {
		return pathStreamFile;
	}

	public void setPathStreamFile(String pathStreamFile) {
		this.pathStreamFile = pathStreamFile;
	}

	public String getRealPath() {
		return realPath;
	}

	public List<ClientCarteraKiva> getCtesNotFound() {
		return ctesNotFound;
	}

	public void setCtesNotFound(List<ClientCarteraKiva> ctesNotFound) {
		this.ctesNotFound = ctesNotFound;
	}

	public ViewPipelineService getViewPipelineService() {
		return viewPipelineService;
	}

	public void setViewPipelineService(ViewPipelineService viewPipelineService) {
		this.viewPipelineService = viewPipelineService;
	}

	public PipelineHistoryService getPipelineHistoryService() {
		return pipelineHistoryService;
	}

	public void setPipelineHistoryService(
			PipelineHistoryService pipelineHistoryService) {
		this.pipelineHistoryService = pipelineHistoryService;
	}

	public List<Date> getDateHistoryPipelineList() {
		return dateHistoryPipelineList;
	}

	public void setDateHistoryPipelineList(List<Date> dateHistoryPipelineList) {
		this.dateHistoryPipelineList = dateHistoryPipelineList;
	}

	public String getDatePipelineHistorySelected() {
		return datePipelineHistorySelected;
	}

	public void setDatePipelineHistorySelected(String datePipelineHistorySelected) {
		this.datePipelineHistorySelected = datePipelineHistorySelected;
	}

}
