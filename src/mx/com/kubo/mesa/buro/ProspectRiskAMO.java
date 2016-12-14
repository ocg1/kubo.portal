package mx.com.kubo.mesa.buro;

import java.util.Date;

import com.soa.webServices.request.BCRiskRequest;

import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.notificaciones.notificables.Evento;

public abstract class ProspectRiskAMO extends ProspectRiskDMO 
{
	protected void init_service_calling(int status_id, String info) 
	{
		srvCall = new ServiceCalling();
		
		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(company_id);		
		srvCall.setProspectus_id(prospectus_id);
		srvCall.setInfo(info);
		srvCall.setStatus(status_id);		
		srvCall.setException(exception);				
		
		servicecallingService.saveServiceCall(srvCall);
	}
	
	protected void init_request() 
	{
		request = new BCRiskRequest();
		
		request.setCompanyId(company_id + "");
		request.setProspectId(prospectus_id + "");
		request.setProspectIdTemp(prospectus_id + "");
		request.setAge(age + "");
		request.setGender(gender_id + "");
		request.setEventId(Evento.CONSULTA_BC_MANUAL.getId()+"");		
		request.setBusinessType("3227");					
		request.setBursolnum(null);
		
		if( residence_id != null )
		{
			request.setHomeType(residence_id + "");
			
		} else {
			
			request.setHomeType("1");
		}		
	}

	protected void init_response() 
	{		
		score = new Scoring();
		
		score.setResult_datetime(new Date());
		score.setBc_score_date(new Date());		
		score.setIs_consulting_for_renovation(is_consulting_for_renovation);
		score.setCompany_id(company_id);
		score.setProspectus_id(prospectus_id);
		score.setBc_score  (prospect_bc_risk_response.getBurScore());									
		score.setCci_score (prospect_bc_risk_response.getIcc());		
		score.setKubo_rate (prospect_bc_risk_response.getVotasakubo());
		score.setMx_folio  (prospect_bc_risk_response.getBurFol());
		score.setRisk_level(prospect_bc_risk_response.getVoriesgo());
		score.setMx_solicitud_buro(prospect_bc_risk_response.getBurSolNum());	
		
		String kubo_score = prospect_bc_risk_response.getVocalkubo1() + prospect_bc_risk_response.getVocalkubo2();
		
		if(kubo_score.equals("N0"))
		{
			score.setKubo_score_a("G");
			score.setKubo_score_b("1");			
		}
		
		else if(kubo_score.equals("E6"))
		{
			score.setKubo_score_a("F");
			score.setKubo_score_b("1");
		} 
		
		else
		{
			score.setKubo_score_a(prospect_bc_risk_response.getVocalkubo1());
			score.setKubo_score_b(prospect_bc_risk_response.getVocalkubo2());
		}
		
		if(prospect_bc_risk_response.getVoliquidez() != null)
		{
			score.setLiquidity(Double.parseDouble(prospect_bc_risk_response.getVoliquidez()));
		}
				
		if(prospect_bc_risk_response.getVocomision() != null)
		{
			score.setOpening_commission(Double.parseDouble(prospect_bc_risk_response.getVocomision()));
		}				
		
		if(prospect_bc_risk_response.getTasaAcre() != null)
		{
			score.setRate(Double.parseDouble(prospect_bc_risk_response.getTasaAcre()));
		}
		
		if(prospect_bc_risk_response.getTasaInv() != null)
		{
			score.setRate_investor(Double.parseDouble(prospect_bc_risk_response.getTasaInv()));
		}		
		
		scoringService.saveScoring(score);		
	}

}
