package mx.com.kubo.mesa.solicitud.adicional;

import java.util.List;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;
import com.soa.webServices.util.InputParam;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.SimulatorService;

public abstract class DocumentsReviewDMO 
{
	protected FilesService filesService;
	protected ServiceCallingDao service_calling;
	protected ProyectService proyectService;
	protected PurposeService purposeService;
	protected SimulatorService simulatorService;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk     service;
	protected WsSgbResponse res;
	
	protected ServiceCalling srvCall;
	
	protected ProyectLoan proyect_loan;
	
	protected InputParam[] params;
	
	protected List<Files> lista_archivos;
}
