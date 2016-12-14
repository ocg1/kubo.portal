package mx.com.kubo.mesa.buro;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Scoring;

public interface ProspectRiskIMO 
{
	void setPerson(NaturalPerson person);
	
	void setConsulting_renovation_ENABLED(boolean consulting_renovation_ENABLED);
	
	void init();
	
	Scoring getScore();	
	
	String getMessageErrorConsulta();
	
	boolean isProspect_risk_ENABLED();
}
