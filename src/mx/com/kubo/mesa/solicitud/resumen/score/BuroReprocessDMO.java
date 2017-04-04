package mx.com.kubo.mesa.solicitud.resumen.score;

import com.mx.kubo.sgbws.models.dto.ReprocessBuroDataDTO;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.tools.Utilities;

public abstract class BuroReprocessDMO 
implements BuroReprocessIMO 
{
	protected ServiceCallingService service_calling;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk     service_SGB;
	protected WsSgbResponse response;
	
	protected ReprocessBuroDataDTO request;
	
	protected ServiceCalling srvCall;
	
	protected NaturalPerson person;
	
	protected String response_msg;
	protected String response_status;
	protected String mxSolicitudBuro;	
	
	protected final String SGB_INIT_MSG;
	protected final String SGB_RESPONSE_MSG;
	protected final String SUCCESS = "1";
	protected final String ID_PROVIDER = "3";
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer age;
	protected Integer gender_id;
	protected Integer residence_id;
	
	protected boolean reproccess_OK;
	
	protected final int INIT = 1;
	protected final int RESPONSE_OK = 2;
	protected final int ERROR = 3;
	
	protected BuroReprocessDMO()
	{
		SGB_INIT_MSG = "Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.buroReprocess";
		SGB_RESPONSE_MSG = "Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.buroReprocess: ";
	  
		service_calling = Utilities.findBean("serviceCallingServiceImp");
	}
	
	public void setPerson(NaturalPerson person)
	{
		this.person = person;
		
		   company_id = person.getNatPerPK().getCompany_id();
		prospectus_id = person.getNatPerPK().getProspectus_id();
		
				 age = person.getEdad();
		residence_id = person.getResidence_id();		         
		   gender_id = person.getGender_id();
	}
	
	public void setMxSolicitudBuro(String mxSolicitudBuro)
	{
		this.mxSolicitudBuro = mxSolicitudBuro;
	}

	public boolean isReproccess_OK() 
	{
		return reproccess_OK;
	}

	public String getResponse_msg() 
	{
		return response_msg;
	}

	public String getResponse_status() 
	{
		return response_status;
	}
}
