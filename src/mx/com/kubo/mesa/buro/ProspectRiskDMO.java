package mx.com.kubo.mesa.buro;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;
import com.soa.webServices.responses.ProspectBCRiskResponse;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.tools.Utilities;

public abstract class ProspectRiskDMO
implements ProspectRiskIMO
{
	protected ServiceCallingService servicecallingService;
	protected ScoringService scoringService;
	
	protected ServiceCalling srvCall;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk service;
	
	protected BCRiskRequest request;
	protected ProspectBCRiskResponse prospect_bc_risk_response;		
	
	protected NaturalPerson person;
	
	protected Scoring score;
	
	protected String exception;
	protected String stack_trace;
	protected String messageErrorConsulta;
	protected String is_consulting_for_renovation = "N";
	
	protected final String INIT_MSG;
	protected final String SUCCESS_MSG;
	protected final String ERROR_MSG;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer residence_id;
	
	protected int age;
	protected int gender_id;
	
	protected boolean prospect_risk_ENABLED;
	
	ProspectRiskDMO()
	{
		servicecallingService = Utilities.findBean("serviceCallingServiceImp");
		       scoringService = Utilities.findBean("scoringServiceImp");
		
		   INIT_MSG = "Invocando Servicio Web consulta a buró manual getProspectBCRisk SGB";
		SUCCESS_MSG = "Regresando Satisfactorioamente de Servicio Web consulta a buró manual getProspectBCRisk SGB";		
		  ERROR_MSG = "Error ConsultingController.createConsulting (220)";
	}
	
	public void setPerson(NaturalPerson person)
	{
		this.person = person;
			
				  age = person.getEdad();
		   company_id = person.getNatPerPK().getCompany_id();
		prospectus_id = person.getNatPerPK().getProspectus_id();				  
		    gender_id = person.getGender_id();
		 residence_id = person.getResidence_id();
	}
	
	public void setConsulting_renovation_ENABLED(boolean consulting_renovation_ENABLED)
	{
		if(consulting_renovation_ENABLED)
		{
			is_consulting_for_renovation = "S";
		}
	}
	
	public Scoring getScore()
	{
		return score;
	}
	
	public String getMessageErrorConsulta()
	{
		return messageErrorConsulta;
	}
	
	public boolean isProspect_risk_ENABLED()
	{
		return prospect_risk_ENABLED;
	}
		
}
