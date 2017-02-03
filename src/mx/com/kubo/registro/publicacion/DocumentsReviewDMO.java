package mx.com.kubo.registro.publicacion;

import java.util.List;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.DocumentsReviewRequest;
import com.soa.webServices.responses.WsSgbResponse;
import com.soa.webServices.util.InputParam;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.tools.Utilities;

public abstract class DocumentsReviewDMO 
implements DocumentsReviewIMO
{
	protected FilesService filesService;
	protected ServiceCallingService service_calling;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk     service;
	protected WsSgbResponse response;
	protected DocumentsReviewRequest request;
	
	protected ServiceCalling srvCall;		
	
	protected NaturalPerson person;
	
	protected InputParam[] params;
	protected com.soa.webServices.util.Files[] files;
	
	protected List<Files> lista_archivos;
	
	protected String response_msg;
	
	protected final String MSG_INIT_CALL; 
	protected final String MSG_SUCCESS; 
	
	protected int company_id;
	protected int prospectus_id;
	
	protected DocumentsReviewDMO()
	{
		filesService    = Utilities.findBean("filesServiceImp");
		service_calling = Utilities.findBean("serviceCallingServiceImp");
		
		MSG_INIT_CALL = "Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview";
		MSG_SUCCESS   = "Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview: ";
	}

	public void setPerson(NaturalPerson person) 
	{
		this.person = person;
		
		company_id    = person.getNatPerPK().getCompany_id();
		prospectus_id = person.getNatPerPK().getProspectus_id();
	}
}
