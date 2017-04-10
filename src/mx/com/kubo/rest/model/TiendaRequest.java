package mx.com.kubo.rest.model;

import java.io.Serializable;

public class TiendaRequest implements Serializable
{
	private static final long serialVersionUID = 8917673845889619421L;
	
	public String company_id;
	public String prospectus_id;
	public String riskCad; 			
	public String termCadIni; 		
	public String flagRisk; 		
	public String destiny_values; 	
	public String genderCad; 		
	public String ammountCadFrom; 	
	public String ammountCadTo;
	
	public String getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	public String getProspectus_id() {
		return prospectus_id;
	}
	
	public void setProspectus_id(String prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
	public String getRiskCad() {
		return riskCad;
	}
	
	public void setRiskCad(String riskCad) {
		this.riskCad = riskCad;
	}
	
	public String getTermCadIni() {
		return termCadIni;
	}
	
	public void setTermCadIni(String termCadIni) {
		this.termCadIni = termCadIni;
	}
	
	public String getFlagRisk() {
		return flagRisk;
	}
	
	public void setFlagRisk(String flagRisk) {
		this.flagRisk = flagRisk;
	}
	
	public String getDestiny_values() {
		return destiny_values;
	}
	
	public void setDestiny_values(String destiny_values) {
		this.destiny_values = destiny_values;
	}
	
	public String getGenderCad() {
		return genderCad;
	}
	
	public void setGenderCad(String genderCad) {
		this.genderCad = genderCad;
	}
	
	public String getAmmountCadFrom() {
		return ammountCadFrom;
	}
	
	public void setAmmountCadFrom(String ammountCadFrom) {
		this.ammountCadFrom = ammountCadFrom;
	}
	
	public String getAmmountCadTo() {
		return ammountCadTo;
	}
	
	public void setAmmountCadTo(String ammountCadTo) {
		this.ammountCadTo = ammountCadTo;
	} 	
}
